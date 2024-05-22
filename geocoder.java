package com.example.googlemap;

import android.Manifest;

import android.content.pm.PackageManager;

import android.location.Address;

import android.location.Geocoder;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

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

    private Button pickupLocationButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pickupLocationButton = findViewById(R.id.pickup_location_button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        pickupLocationButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // Fetch pickup location

                LatLng pickupLocation = mMap.getCameraPosition().target;

                getAddressFromLocation(pickupLocation);

            }
        });
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

        } else {

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    LOCATION_PERMISSION_REQUEST_CODE);

        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override

            public void onMapClick(LatLng latLng) {

                // Add marker on map click

                mMap.clear();

                mMap.addMarker(new MarkerOptions().position(latLng));

            }
        });
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

    private void getAddressFromLocation(LatLng location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {

            List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
            if (addresses != null && addresses.size() > 0) {

                Address address = addresses.get(0);

                String addressString = address.getAddressLine(0);

                // Use addressString as required

                Toast.makeText(this, addressString, Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
