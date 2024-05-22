Reverse Geocoder

Code:

package com.example.reversegeocoder;

import android.Manifest;

import android.content.pm.PackageManager;

import android.location.Address;

import android.location.Geocoder;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import java.util.List;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;

    Reg.No:21 IT010

    private EditText addressEditText;

    private Button markLocationButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addressEditText = findViewById(R.id.address_edit_text);

        markLocationButton = findViewById(R.id.mark_location_button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        markLocationButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String address = addressEditText.getText().toString().trim();

                if (!address.isEmpty()) {

                    markLocationOnMap(address);

                } else {

                    Toast.makeText(MainActivity.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

@Override

Reg.No:21IT010 

public void onMapReady(GoogleMap googleMap) { 

mMap = googleMap; 

if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) { 

mMap.setMyLocationEnabled(true); 

} else { 

ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE); 

} 

}

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    mMap.setMyLocationEnabled(true);

                }

            } else {

                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();

            }

        }

    }

private void markLocationOnMap(String address){ 

Geocoder geocoder = new Geocoder(this, Locale.getDefault()); 

try {

Reg.No:21IT010 

List<Address> addresses = geocoder.getFromLocationName(address, 1); 

if (addresses != null && addresses.size() > 0) { 

Address location = addresses.get(0); 

double latitude = location.getLatitude(); 

double longitude = location.getLongitude(); 

LatLng latLng = new LatLng(latitude, longitude); 

mMap.clear(); 

mMap.addMarker(new MarkerOptions().position(latLng).title(address)); 

mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f)); 

} else { 

Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show(); 

} 

} catch (IOException e) { 

e.printStackTrace(); 

} 

}

}