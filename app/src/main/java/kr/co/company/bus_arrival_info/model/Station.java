package kr.co.company.bus_arrival_info.model;

public class Station {
    private String name;
    private String arsId;

    public Station(String name, String arsId) {
        this.name = name;
        this.arsId = arsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArsId() {
        return arsId;
    }

    public void setArsId(String arsId) {
        this.arsId = arsId;
    }
}
