package kr.co.company.bus_arrival_info.model;

public class BusInfo {
    private String adirection;
    private String arrmsg1;
    private String arrmsg2;

    private String busRouteAbrv;

    public BusInfo(String adirection, String arrmsg1, String arrmsg2, String busRouteAbrv) {
        this.adirection = adirection;
        this.arrmsg1 = arrmsg1;
        this.arrmsg2 = arrmsg2;
        this.busRouteAbrv = busRouteAbrv;
    }

    public String getAdirection() {
        return adirection;
    }

    public void setAdirection(String adirection) {
        this.adirection = adirection;
    }

    public String getArrmsg1() {
        return arrmsg1;
    }

    public void setArrmsg1(String arrmsg1) {
        this.arrmsg1 = arrmsg1;
    }

    public String getArrmsg2() {
        return arrmsg2;
    }

    public void setArrmsg2(String arrmsg2) {
        this.arrmsg2 = arrmsg2;
    }

    public String getBusRouteAbrv() {
        return busRouteAbrv;
    }

    public void setBusRouteAbrv(String busRouteAbrv) {
        this.busRouteAbrv = busRouteAbrv;
    }
}
