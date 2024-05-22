<?xml version="1.0"encoding="utf-8"?>

<menu xmlns:android="http://schemas.android.com/apk/res/android">

<item

android:id="@+id/edit"

android:title="Our Story"/>

<item

android:id="@+id/delete"

android:title="First Aid Kit Essentials"/>

<item

android:id="@+id/tip"

android:title="Cybersecurity Tips"/>

</menu>

package com.example.accident;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;

import android.view.ContextMenu;

import android.view.MenuInflater;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    TextView textView;

 String fe[] = {"About Us","Emergency Checklist","Personal Safety tips"};

 @Override 

 protected void onCreate(Bundle savedInstanceState) { 

 super.onCreate(savedInstanceState); 

 setContentView(R.layout.activity_main); 

 listView=(ListView)findViewById(R.id.listView); 

 ArrayAdapter<String> adapter=new  

ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fe); 

 listView.setAdapter(adapter); 

 // Register the ListView for Context menu 

 registerForContextMenu(listView); 

 }

 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo  menuInfo) { 

 super.onCreateContextMenu(menu, v, menuInfo); 

 MenuInflater inflater = getMenuInflater(); 

 inflater.inflate(R.menu.contextvalues, menu); 

 menu.setHeaderTitle("Select The Action"); 

 }

 @Override 

 public boolean onContextItemSelected(MenuItem item) { 

 if(item.getItemId()==R.id.edit){ 

 Toast.makeText(getApplicationContext(),"Read our story",Toast.LENGTH_LONG).show();  } 

 else if(item.getItemId()==R.id.delete){ 

 Toast.makeText(getApplicationContext(),"Check you have all the  

essentials",Toast.LENGTH_LONG).show(); 

 } 

 else if(item.getItemId()==R.id.tip){ 

 Toast.makeText(getApplicationContext(),"Explore the cybersecurity  

tips",Toast.LENGTH_LONG).show(); 

 } 

 else{ 

 return false; 

 } 

 return true; }
}
