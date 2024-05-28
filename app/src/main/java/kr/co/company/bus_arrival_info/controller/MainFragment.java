package kr.co.company.bus_arrival_info.controller;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.OnMapReadyCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import kr.co.company.bus_arrival_info.R;
import java.util.ArrayList;
import kr.co.company.bus_arrival_info.model.NearBus;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuInflater;
import com.google.android.gms.maps.MapsInitializer;

public class MainFragment extends Fragment implements OnMapReadyCallback{


    String tmX, tmY;
    private ArrayList<NearBus> nearBusstops;

    View rootView;
    MapView mapView;

    public MainFragment(String tmX, String tmY, ArrayList<NearBus> nearBusstops) {
        this.tmX = tmX;
        this.tmY = tmY;
        this.nearBusstops = nearBusstops;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        mapView = rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);



        return rootView;
    }

    public MainFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Double x = Double.parseDouble(tmX);
        Double y = Double.parseDouble(tmY);

        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(y, x), 16);

        googleMap.animateCamera(cameraUpdate);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(y, x))
                .title("My location"));
        int nearBusstopCount = nearBusstops.size();
        Log.d("maps", "Near bus stop count: " + nearBusstopCount);
        // Add markers for nearBusstops
        for (NearBus nearBusstop : nearBusstops) {

            Double busX = Double.parseDouble(nearBusstop.getTmX());
            Double busY = Double.parseDouble(nearBusstop.getTmY());
            String stationName = nearBusstop.getStationNm();
            Log.d("maps", "busX : "+ busX);
            // Add marker for each near bus stop
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(busY, busX))
                    .title(stationName));
        }


    }

}
