package kr.co.company.bus_arrival_info.controller;


import kr.co.company.bus_arrival_info.model.NearBus;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import kr.co.company.bus_arrival_info.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kr.co.company.bus_arrival_info.model.BusInfo;
import kr.co.company.bus_arrival_info.model.Station;

public class SearchActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private String getData;
    private EditText editStation;
    private EditText editBusNum;
    private ListView listView;
    private ListView listView2;
    ArrayAdapter adapter;
    ArrayAdapter adapter2;

    private boolean isTimerTaskOn = false;
    TimerTask timerTask;
    Timer timer;

    private ArrayList<Station> stations = new ArrayList<Station>();
    private ArrayList<String> stationNames = new ArrayList<String>();
    private ArrayList<BusInfo> busInfos = new ArrayList<BusInfo>();
    private ArrayList<String> busInfoStrings = new ArrayList<String>();
    //private ArrayList<BusInfo> busInfos = new ArrayList<BusInfo>();
    private ArrayList<NearBus> nearBuses = new ArrayList<NearBus>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchscreen);
        editStation = (EditText) findViewById(R.id.editStation);
        editBusNum = (EditText) findViewById(R.id.editBusNum);
        listView = (ListView) findViewById(R.id.listview);
        listView2 = (ListView) findViewById(R.id.listview2);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stationNames);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, busInfoStrings);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        Button button1 = (Button) findViewById(R.id.getGPS);

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SearchActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            0);
                } else {
                    Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();
                        //Toast.makeText(SearchActivity.this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();
                        // 서울 사시는 분들은 tmX와 tmY를 바꿔주세요
                        //String tmX = String.valueOf(longitude);
                        //String tmY = String.valueOf(latitude);
                        String tmX = "127.100079";
                        String tmY = "37.513326";
                        String radius = "200";
                        try {
                            nearBusGPS(tmX, tmY, radius);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }else {
                        Toast.makeText(SearchActivity.this, "Last known location is not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            Toast.makeText(SearchActivity.this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();



        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };


    public void search(View view) throws IOException {

        String stationName = editStation.getText().toString();
        busInfoStrings.clear();
        adapter.notifyDataSetChanged();

        if (isTimerTaskOn) {
            timerTask.cancel();
            isTimerTaskOn = false;
        }

        new Thread(() -> {
            stations.clear();
            stationNames.clear();

            try {

                getData = DataLoader.apiRequest(stationName);
                Log.d("data", getData);
                stations = DataLoader.ParseStationJson(getData);

                for (Station station : stations) {
                    stationNames.add(station.getName());
                }

                Log.d("data", stationNames.toString());

                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String)parent.getItemAtPosition(position);
                String stationId = "";
                for (Station station : stations) {
                    if (data == station.getName()) {
                        stationId = station.getArsId();
                    }
                }
                Toast.makeText(SearchActivity.this, data + " " + stationId, Toast.LENGTH_SHORT).show();

                editStation.setText(data);
                String targetBusNum = editBusNum.getText().toString();
                if (targetBusNum.isEmpty()) {
                    targetBusNum = "None";
                }

                String finalStationId = stationId;
                String finalTargetBusNum = targetBusNum;
                RequestBusInfos(finalStationId, finalTargetBusNum);


                stations.clear();
                stationNames.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void renew(View view) {
        adapter2.notifyDataSetChanged();
    }

    private void RequestBusInfos(String stationId, String busNum) {
        busInfos.clear();
        busInfoStrings.clear();
        new Thread(() -> {
            try {
                String jsonData = DataLoader.apiRequest(stationId, busNum);
                busInfos = DataLoader.ParseBusInfoJson(jsonData, busNum);

                for (BusInfo busInfo : busInfos) {
                    busInfoStrings.add(busInfo.getBusRouteAbrv() + " " + busInfo.getAdirection() + "\n" + busInfo.getArrmsg1() + "\n" + busInfo.getArrmsg2());
                }

                Log.d("data", "busInfoStrings : " + busInfoStrings.toString());

                runOnUiThread(() -> {
                    adapter2.notifyDataSetChanged();
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }).start();


    }




    public void nearBusGPS(String tmX, String tmY, String radius) throws IOException {

        nearBuses.clear();
        stationNames.clear(); // Clear stationNames list
        Log.d("data", tmX+ tmY+ radius);
        new Thread(() -> {
            try {
                String jsonData = DataLoader.apiRequest(tmX, tmY, radius);
                nearBuses = DataLoader.ParseNearBus(jsonData);
                for (NearBus nearBus : nearBuses) {
                    stationNames.add(nearBus.getStationNm());
                }

                Log.d("data", "busInfoStrings : "+stationNames.toString());

                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();

                });
            } catch (IOException e) {
                Log.d("data", "RuntimeException");
                throw new RuntimeException(e);
            } catch (JSONException e) {
                Log.d("data", "RuntimeException");
                throw new RuntimeException(e);
            }
        }).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                editStation.setText(data);
                Toast.makeText(SearchActivity.this, "Selected Station: " + data, Toast.LENGTH_SHORT).show();
                stations.clear();
                stationNames.clear();
                adapter.notifyDataSetChanged();
            }
        });


    }

}

