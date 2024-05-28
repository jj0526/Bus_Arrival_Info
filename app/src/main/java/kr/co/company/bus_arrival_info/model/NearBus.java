package kr.co.company.bus_arrival_info.model;

public class NearBus {
    private String stationId;
    private String stationNm;
    private String tmX;
    private String tmY;


    public NearBus(String arrmsg1, String arrmsg2, String tmX, String tmY) {
        this.stationId = arrmsg1;
        this.stationNm = arrmsg2;
        this.tmX = tmX;
        this.tmY = tmY;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String arrmsg1) {
        this.stationId = arrmsg1;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String arrmsg2) {
        this.stationNm = arrmsg2;
    }

    public String getTmX() {
        return tmX;
    }

    public void setTmX(String tmX) {
        this.tmX = tmX;
    }

    public String getTmY() {
        return tmY;
    }

    public void setTmY(String tmY) {
        this.tmY = tmY;
    }
}