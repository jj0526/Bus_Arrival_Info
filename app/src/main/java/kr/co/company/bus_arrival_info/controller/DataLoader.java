package kr.co.company.bus_arrival_info.controller;

import android.util.Log;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.company.bus_arrival_info.model.BusInfo;
import kr.co.company.bus_arrival_info.model.Station;
import kr.co.company.bus_arrival_info.model.NearBus;

public class DataLoader {

    public static String apiRequest(String stSrch) throws IOException {
        URL url = GenUrl.generate(stSrch);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public static String apiRequest(String arsId, String busNum) throws IOException {
        URL url = GenUrl.generate(arsId, busNum);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public static String apiRequest(String tmX, String tmY, String radius) throws IOException {
        URL url = GenUrl.generate(tmX, tmY, radius);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }


    public static ArrayList<Station> ParseStationJson(String jsonData) throws JSONException {
        ArrayList<Station> stations = new ArrayList<Station>();
        JSONObject obj = new JSONObject(jsonData);
        JSONObject msgBody = (JSONObject)obj.get("msgBody");
        JSONArray itemList = (JSONArray)msgBody.get("itemList");

        for (int i = 0; i < itemList.length(); i++) {
            JSONObject temp = itemList.getJSONObject(i);
            String stNm = temp.getString("stNm");
            String arsId = temp.getString("arsId");
            stations.add(new Station(stNm, arsId));
        }

        return stations;
    }

    public static ArrayList<BusInfo> ParseBusInfoJson(String jsonData, String targetBusNum) throws JSONException {
        ArrayList<BusInfo> busInfos = new ArrayList<BusInfo>();

        // TimerTask 테스트로 트래픽 초과
        try {
            JSONObject obj = new JSONObject(jsonData);
            Log.d("JsonLog", obj.toString());
            JSONObject msgBody = (JSONObject)obj.get("msgBody");
            JSONArray itemList = (JSONArray)msgBody.get("itemList");
            for (int i = 0; i < itemList.length(); i++) {
                JSONObject temp = itemList.getJSONObject(i);
                String adirection = temp.getString("adirection");
                String arrmsg1= temp.getString("arrmsg1");
                String arrmsg2= temp.getString("arrmsg2");
                String busRouteAbrv= temp.getString("busRouteAbrv");
                if (targetBusNum == "None" || targetBusNum.equals(busRouteAbrv)) {
                    busInfos.add(new BusInfo(adirection, arrmsg1, arrmsg2, busRouteAbrv));
                }
            }
        } catch (Exception e) {
            Log.d("error", e.toString());
        }


        return busInfos;
    }

    public static ArrayList<NearBus> ParseNearBus(String jsonData) throws JSONException {
        ArrayList<NearBus> NearBuses = new ArrayList<NearBus>();

        // TimerTask 테스트로 트래픽 초과
        try {
            JSONObject obj = new JSONObject(jsonData);
            Log.d("JsonLog", obj.toString());
            JSONObject msgBody = (JSONObject)obj.get("msgBody");
            JSONArray itemList = (JSONArray)msgBody.get("itemList");
            for (int i = 0; i < itemList.length(); i++) {
                JSONObject temp = itemList.getJSONObject(i);
                String arrmsg1= temp.getString("stationId");
                String arrmsg2= temp.getString("stationNm");
                String tmX = temp.getString("gpsX");
                String tmY = temp.getString("gpsY");
                Log.d("maps", "dataloader" +  tmX + tmY);
                NearBuses.add(new NearBus(arrmsg1, arrmsg2, tmX, tmY));

            }
        } catch (Exception e) {
            Log.d("error", e.toString());
        }


        return NearBuses;
    }
}
