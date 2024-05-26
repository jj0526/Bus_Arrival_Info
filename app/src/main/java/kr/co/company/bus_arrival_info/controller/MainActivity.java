package kr.co.company.bus_arrival_info.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
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
        setContentView(R.layout.mainscreen);
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

}