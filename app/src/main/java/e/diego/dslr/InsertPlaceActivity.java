package e.diego.dslr;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


import e.diego.dslr.Model.MyMap;
import e.diego.dslr.Util.ConstantsUtils;

public class InsertPlaceActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    private GoogleMap mMap;
    private Marker marker;
    private EditText name;
    private MyMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_place);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        name = findViewById(R.id.eName);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng milano = new LatLng(45.4652317, 9.1876072);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(milano, 4));
        mMap.setOnMapLongClickListener(this);
    }

    public void onSavePlace(View view){
        if (!name.getText().toString().equals("") && marker != null){
            initMap();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(ConstantsUtils.MAP_OBJECT, myMap);
            setResult(Activity.RESULT_OK, intent);
            finish();

        }else {
            if(name.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Insert Your Place Name", Toast.LENGTH_SHORT).show();
            }
            if (marker == null){
                Toast.makeText(getApplicationContext(), "Add A Marker With A Long Click", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initMap(){
        myMap = new MyMap();

        myMap.setLatitude(marker.getPosition().latitude);
        myMap.setLongitude(marker.getPosition().longitude);
        myMap.setNamePlace(name.getText().toString());
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(InsertPlaceActivity.this);
        List<Address> list;
        try {
            list = geocoder.getFromLocation(latLng.latitude,
                    latLng.longitude, 1);
        } catch (IOException e) {
            return;
        }
        Address address = list.get(0);
        if (marker != null) {
            marker.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .title(address.getLocality())
                .position(new LatLng(latLng.latitude,
                        latLng.longitude));
        marker = mMap.addMarker(options);
    }
}
