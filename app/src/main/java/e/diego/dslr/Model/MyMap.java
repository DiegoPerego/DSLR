package e.diego.dslr.Model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created by Diego on 27/12/2017.
 */

public class MyMap implements Serializable{

    private double latitude;
    private double longitude;
    private String namePlace;

    public MyMap(double latitude, double longitude, String namePlace) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.namePlace = namePlace;
    }

    public MyMap() {
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.namePlace = null;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }
}
