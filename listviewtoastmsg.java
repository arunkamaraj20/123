Activit_main.xml:

<?xml version="1.0"encoding="utf-8"?>

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"

xmlns:tools="http://schemas.android.com/tools"

android:layout_width="match_parent"

android:layout_height="match_parent"

android:padding="16dp"

tools:context=".MainActivity">

<TextView

android:id="@+id/textView"

android:layout_width="match_parent"

android:layout_height="wrap_content"

android:layout_marginTop="16dp"

android:text="Retail Shelf Monitoring App!"

android:textAlignment="center"

android:textColor="#9C27B0"

android:textSize="34sp"/>

<ListView

android:id="@+id/listView"

android:layout_width="match_parent"

android:layout_height="587dp"

android:layout_below="@+id/textView"

android:layout_marginTop="12dp"/>

</RelativeLayout>

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

        String[] machines = { "Chocolate", "Milk", "Juice", "Fruit", "Prawn" };

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