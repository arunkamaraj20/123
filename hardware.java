<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<ImageView android:id="@+id/imageView"android:layout_width="match_parent"android:layout_height="match_parent"android:scaleType="centerCrop"/>

<Button android:id="@+id/captureBtn"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_alignParentBottom="true"android:layout_centerHorizontal="true"android:text="Capture"/>

</RelativeLayout>

MainActivity.java

package com.example.hardwarecamera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private static final int REQUEST_IMAGE_CAPTURE = 1;
private static final int PERMISSION_REQUEST_CODE = 2;
private ImageView imageView;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

Button captureBtn = findViewById(R.id.captureBtn);
imageView = findViewById(R.id.imageView);

captureBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
if (checkPermission()) {
dispatchTakePictureIntent();
} else {
requestPermission();
}
}
});
}

private boolean checkPermission() {
int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
return cameraPermission == PackageManager.PERMISSION_GRANTED;
}

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA },
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission is required to capture photos",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}

AndroidManifest

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"><uses-permission android:name="android.permission.CAMERA"/><uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-permission android:name="android.permission.RECORD_AUDIO"/><uses-permission android:name="android.permission.READ_PHONE_STATE"/><uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/><uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/><uses-
permission android:name="android.permission.ACCESS_WIFI_STATE"/><uses-
permission android:name="android.permission.BLUETOOTH"/><uses-
permission android:name="android.permission.BLUETOOTH_ADMIN"/>

<uses-
permission android:name="android.permission.POST_NOTIFICATIONS"/><uses-
permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-
permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-
permission android:name="android.permission.INTERNET"/><uses-
permission android:name="android.permission.SEND_SMS"/><
application android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.Hardwarecamera"tools:targetApi="31"><
activity android:name=".MainActivity"android:exported="true"android:label="@string/app_name"android:theme="@style/Theme.Hardwarecamera"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>