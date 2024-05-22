<?xml version="1.0" encoding="utf-8"?>

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"

    xmlns:app="http://schemas.android.com/apk/res-auto"

    xmlns:tools="http://schemas.android.com/tools"

    android:layout_width="match_parent"

    android:layout_height="match_parent"

    android:paddingLeft="16dp"

    android:paddingTop="16dp"

    android:paddingRight="16dp"

    android:paddingBottom="16dp"

    tools:context=".MainActivity">

    <TextView

        android:id="@+id/textView"

        android:layout_width="match_parent"

        android:layout_height="wrap_content"

        android:layout_marginTop="16dp"

        android:text="Retail Shelf Monitoring App!"

        android:textAlignment="center"

        android:textColor="#9C27B0"

        android:textSize="34sp" />

    <TextView

        android:id="@+id/textView1"

        android:layout_width="match_parent"

        android:layout_height="wrap_content"

        android:layout_marginTop="110dp"

        android:text="Shop Owner Profile!"

        android:textAlignment="center"

       

            

 

 

 

 

 

 

android:textColor="#9C27B0"

        android:textSize="34sp" />

    <EditText

        android:id="@+id/editText1"

        android:layout_width="match_parent"

        android:layout_height="48dp"

        android:layout_below="@+id/textView"

        android:layout_marginTop="70dp"

        android:hint="Enter your Name" />

    <EditText

        android:id="@+id/editText11"

        android:layout_width="match_parent"

        android:layout_height="50dp"

        android:layout_below="@+id/editText1"

        android:layout_marginTop="20dp"

        android:hint="Enter your Age"

        android:textColorLink="#090909" />

    <EditText

        android:id="@+id/editText2"

        android:layout_width="match_parent"

        android:layout_height="48dp"

        android:layout_below="@id/editText11"

        android:layout_marginTop="20dp"

        android:hint="Store Name" />

    <EditText

        android:id="@+id/editText3"

        android:layout_width="match_parent"

        android:layout_height="48dp"

        android:layout_below="@id/editText2"

        android:layout_marginTop="28dp"

        android:hint="Location" />

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

<EditText   android:id="@+id/editText4"

        android:layout_width="match_parent"

        android:layout_height="48dp"

        android:layout_below="@id/editText2"

        android:layout_marginTop="90dp"

        android:hint="Contact Number" />

      <Button  android:id="@+id/button"

        android:layout_width="wrap_content"

        android:layout_height="wrap_content"

        android:layout_below="@id/editText3"

        android:layout_centerHorizontal="true"

        android:layout_marginTop="65dp"

        android:text="Continue"/></RelativeLayout>

MainActivity.java:

package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String[] machines = {"Chocolate", "Milk", "Juice", "Fruit", "Prawn"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, machines);

        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

       

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);

                String message = "You have clicked " + selectedItem + " successfully.";

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }

        });

    }

}

Activit_second.xml:

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"

    xmlns:tools="http://schemas.android.com/tools"

    android:layout_width="match_parent"

    android:layout_height="match_parent"

    android:padding="16dp"

    tools:context=".SecondActivity">

    <TextView android:id="@+id/textView"

        android:layout_width="wrap_content"

        android:layout_height="wrap_content"

        android:textSize="30sp"

        android:textColor="@android:color/black"

        android:layout_centerInParent="true"/>

</RelativeLayout>

SecondActivity.java:

package com.example.retailshelf;

import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

    TextView textView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("key")) {

            String data = intent.getStringExtra("key");

            textView.setText("Welcome, "+data);

        }

    }

}