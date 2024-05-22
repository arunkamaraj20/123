d)Display a button that opens a web page using an implicit intent.

Code:

Activit_main.xml:

<?xml version="1.0"encoding="utf-8"?>

<RelativeLayout

xmlns:android="http://schemas.android.com/apk/res/android"

xmlns:app="http://schemas.android.com/apk/res-auto"

xmlns:tools="http://schemas.android.com/tools"

android:layout_width="match_parent"

android:layout_height="match_parent"

tools:context=".MainActivity">

<Button android:id="@+id/openWebPageButton"

android:layout_width="wrap_content"

android:layout_height="wrap_content"

android:layout_centerInParent="true"

android:layout_centerVertical="true"

android:text="REFER RETAIL STORE IN MADURAI"/>

</RelativeLayout>

MainActivity.java:

package com.example.retailshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button openWebPageButton = findViewById(R.id.openWebPageButton);

        openWebPageButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String webpageUrl = "www.google.com";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpageUrl));

                if (intent.resolveActivity(getPackageManager()) != null) {

                    startActivity(intent);

                } else {

                    System.out.println("No app to handle this intent.");

                }

            }

        });

    }

}