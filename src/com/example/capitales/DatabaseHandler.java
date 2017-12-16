package com.example.capitales;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	 // All Static variables
    // Database Version
    private static final int version = 8;
    // Database Name
    private static final String name = "capitales";
 
    // Capital table name
    private static final String TABLE_CAPITALES = "capitales";
 
    // Capital Table Columns names
    private static final String ID = "id";
    private static final String CONTINENTE = "continente";
    private static final String PAIS = "pais";
    private static final String CAPITAL = "capital";
    
	public DatabaseHandler(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_CAPITALES_TABLE = "CREATE TABLE " + TABLE_CAPITALES + "("
	                + ID + " INTEGER PRIMARY KEY," + CONTINENTE + " TEXT," + PAIS + " TEXT,"
	                + CAPITAL + " TEXT" + ")";
	        db.execSQL(CREATE_CAPITALES_TABLE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAPITALES);
 
        // Create tables again
        onCreate(db);
	}

	//METODOS DE MANIPULACION
   // add
	void addCapital(Capital capital) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(CONTINENTE, capital.getContinente());
        values.put(PAIS, capital.getPais()); // pais
        values.put(CAPITAL, capital.getCapital()); // capital
 
        // Inserting Row
        db.insert(TABLE_CAPITALES, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single capital
    Capital getCapital(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CAPITALES, new String[] { ID, CONTINENTE,
                PAIS, CAPITAL }, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Capital capital = new Capital(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return capital;
    }
    
    Capital getCapital(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Capital capital = null;
 try{
        Cursor cursor = db.query(TABLE_CAPITALES, new String[] { ID,CONTINENTE,
                PAIS, CAPITAL }, PAIS + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        capital = new Capital(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3)); 
        
 }catch(Exception e){
	 
 }
        // return contact
        return capital;
    }
    // Getting All Contacts
    public List<Capital> getAllCapitales() {
        List<Capital> capitalesList = new ArrayList<Capital>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CAPITALES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Capital capital = new Capital();
                capital.setID(Integer.parseInt(cursor.getString(0)));
                capital.setContinente(cursor.getString(1));
                capital.setPais(cursor.getString(2));
                capital.setCapital(cursor.getString(3));
                // Adding contact to list
                capitalesList.add(capital);
            } while (cursor.moveToNext());
        }
 
        // return capital list
        return capitalesList;
    }
    
    public List<String> getAllPaises() {
        List<String> capitalesList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CAPITALES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                capitalesList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
 
        // return capital list
        return capitalesList;
    }
}
