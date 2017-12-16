package com.example.capitales;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Spinner spinner;
	Button btnSubmit;
	TextView txtcapital;
	DatabaseHandler db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = new DatabaseHandler(this);
//        db.addCapital(new Capital("Guatemala", "Guatemala"));
//        db.addCapital(new Capital("El Salvador", "San Salvador"));
//        db.addCapital(new Capital("Honduras", "Tegucigalpa"));
        cargarinfo();
        addItemsOnSpinner();
    	addListenerOnButton();
    }

    private void cargarinfo() {
    	try
    	{
    	    InputStream fraw =
    	        getResources().openRawResource(R.raw.capitales);
    	 
    	    BufferedReader brin =
    	        new BufferedReader(new InputStreamReader(fraw));
    	 
    	    String linea = brin.readLine();
    	    
    	   while((linea = brin.readLine()) != null){
    	    	
    	    	String[] a = linea.split("\t");

    	    	if(db.getCapital(a[1]) == null){
    	    	db.addCapital(new Capital(a[0],a[1], a[2]));
    	    	}else{ break; }
    	    
    	    }
    	    brin.close();
    	    fraw.close();
    	}
    	catch (Exception ex)
    	{
    	    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
    	}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void addItemsOnSpinner() {
    	 
    	spinner = (Spinner) findViewById(R.id.spinner);
    	List<String> list = db.getAllPaises();
    	Collections.sort(list);
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	spinner.setAdapter(dataAdapter);
    	
      }
    
    public void addListenerOnButton() {
    	 
    	spinner = (Spinner) findViewById(R.id.spinner);
    	btnSubmit = (Button) findViewById(R.id.button1);
        txtcapital = (TextView) findViewById(R.id.textView3);
    	btnSubmit.setOnClickListener(new OnClickListener() {
     
    	  @Override
    	  public void onClick(View v) {
     
    	   /* Toast.makeText(MainActivity.this,
    		"OnClickListener : " + 
                    "\nSpinner 1 : "+ String.valueOf(spinner.getSelectedItem()),
    			Toast.LENGTH_SHORT).show();*/
    	    Capital c = db.getCapital(String.valueOf(spinner.getSelectedItem()));
    	    
    	    txtcapital.setText(c.capital);
    	    
    	    //txtcapital.refreshDrawableState();
    	  }
     
    	});
      }
}
