https://tinyurl.com/33wuvbze

AndroidManifesr<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"package="com.example.alertdialogapp">

<application android:allowBackup="true"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.AlertDialogApp"><activity android:name=".MainActivity"android:exported="true"><intent-filter><action android:name="android.intent.action.MAIN"/><category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest>MainActivity
package com.example.alertdialogapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show the alert dialog
        showAlertDialog();
    }

    private void showAlertDialog() {
        final String[] items = { "Stock Details", "Live Market Price", "Help and Support" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an Item")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action when an item is clicked
                        Toast.makeText(MainActivity.this, "You selected: " + items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action when positive button is clicked
                        Toast.makeText(MainActivity.this, "OK button clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action when negative button is clicked
                        Toast.makeText(MainActivity.this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}activity_main.xml<?xml version="1.0"encoding="utf-8"?><LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"android:layout_width="match_parent"android:layout_height="match_parent"android:orientation="vertical"android:gravity="center"android:padding="16dp">

<TextView
        android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Welcome to Dashboard"android:textSize="18sp"/></LinearLayout
>
