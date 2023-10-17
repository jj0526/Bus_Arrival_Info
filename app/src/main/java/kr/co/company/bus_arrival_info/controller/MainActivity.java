package kr.co.company.bus_arrival_info.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kr.co.company.bus_arrival_info.R;
import kr.co.company.bus_arrival_info.model.BusInfo;
import kr.co.company.bus_arrival_info.model.Station;

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editStation = (EditText) findViewById(R.id.editStation);
        editBusNum = (EditText)  findViewById(R.id.editBusNum);
        listView = (ListView) findViewById(R.id.listview);
        listView2 = (ListView) findViewById(R.id.listview2);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stationNames);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, busInfoStrings);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
    }

    public void search(View view) throws IOException {

        String stationName = editStation.getText().toString();
        busInfoStrings.clear();
        adapter2.notifyDataSetChanged();

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
                Toast.makeText(MainActivity.this, data + " " + stationId, Toast.LENGTH_SHORT).show();

                String targetBusNum = editBusNum.getText().toString();
                if (targetBusNum.isEmpty()) {
                    targetBusNum = "None";
                }

                String finalStationId = stationId;
                String finalTargetBusNum = targetBusNum;

                /*
                new Thread(() -> {
                    timer = new Timer();

                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            RequestBusInfos(finalStationId, finalTargetBusNum);
                        }
                    };

                    isTimerTaskOn = true;
                    timer.schedule(timerTask,0,1000);
                }).start();
                */

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

                Log.d("data", busInfoStrings.toString());

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
}