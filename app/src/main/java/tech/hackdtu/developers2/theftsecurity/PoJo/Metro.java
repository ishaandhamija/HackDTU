package tech.hackdtu.developers2.theftsecurity.PoJo;

/**
 * Created by sharaddadhich on 14/10/17.
 */

public class Metro {

    String name;
    String Line;
    String LocLat,LocLong;

    public Metro(String name, String line, String locLat, String locLong) {
        this.name = name;
        Line = line;
        LocLat = locLat;
        LocLong = locLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public String getLocLat() {
        return LocLat;
    }

    public void setLocLat(String locLat) {
        LocLat = locLat;
    }

    public String getLocLong() {
        return LocLong;
    }

    public void setLocLong(String locLong) {
        LocLong = locLong;
    }
}



