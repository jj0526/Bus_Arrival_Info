package kr.co.company.bus_arrival_info.model;

public class NearBus {
    private String stationId;
    private String stationNm;


    public NearBus(String arrmsg1, String arrmsg2) {
        this.stationId = arrmsg1;
        this.stationNm = arrmsg2;
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

}