activity_main.xml<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<Button android:id="@+id/buttonenable"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="ENABLE BLUETOOTH"android:layout_marginTop="16dp"/>

<Button android:id="@+id/buttondisable"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="DISABLE BLUETOOTH"android:layout_below="@+id/buttonenable"android:layout_marginTop="16dp"/>

</RelativeLayout>

MainActivity.java
package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BLUETOOTH = 1;

    private final BroadcastReceiver bluetoothStateReceiver=new BroadcastReceiver(){@Override public void onReceive(Context context,Intent intent){String action=intent.getAction();if(action!=null&&action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);if(state==BluetoothAdapter.STATE_TURNING_ON||state==BluetoothAdapter.STATE_ON){Toast.makeText(MainActivity.this,"Bluetooth enabled",Toast.LENGTH_SHORT).show();}else if(state==BluetoothAdapter.STATE_TURNING_OFF||state==BluetoothAdapter.STATE_OFF){Toast.makeText(MainActivity.this,"Bluetooth disabled",Toast.LENGTH_SHORT).show();}}}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if Bluetooth is not supported
        }

        Button enableBtn = findViewById(R.id.buttonenable);
        Button disableBtn = findViewById(R.id.buttondisable);

        enableBtn.setOnClickListener(v -> enableBluetooth());
        disableBtn.setOnClickListener(v -> disableBluetooth());
    }

    private void enableBluetooth() {
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                // Check if we have the BLUETOOTH_ADMIN permission
                if (checkSelfPermission(
                        android.Manifest.permission.BLUETOOTH_ADMIN) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    // Permission not granted, request it from the user
                    requestPermissions(new String[] { android.Manifest.permission.BLUETOOTH_ADMIN },
                            REQUEST_ENABLE_BLUETOOTH);
                } else {
                    // Permission granted, start Bluetooth enable intent
                    bluetoothAdapter.enable(); // Directly enable Bluetooth
                }
            } else {
                Toast.makeText(this, "Bluetooth is already enabled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void disableBluetooth() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        } else {
            Toast.makeText(this, "Bluetooth is already disabled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothStateReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(bluetoothStateReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permission granted, now enable Bluetooth
                enableBluetooth();
            } else {
                Toast.makeText(this, "Permission denied, cannot enable Bluetooth", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

AndroidManifest

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"><uses-permission android:name="android.permission.BLUETOOTH"/><uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>

<application
android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.BluetoothApp"tools:targetApi="31"><activity
android:name=".MainActivity"android:exported="true"android:label="@string/app_name"android:theme="@style/Theme.BluetoothApp"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>