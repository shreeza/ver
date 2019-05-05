package com.example.shailee.camo;


//class to make database access

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseAccess {
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c= null;

    //private constructor creation so that this class wont be accessed or instantiated by any other classses
    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }
    //to return the single instance of the database

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    //to open the connection to the database

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    //to close the connection

    public void close(){
        if(db!=null){
           this.db.close();
        }

    }
    public String getData(String commonName){
        c=db.rawQuery("Select scientific_name, family, genus from leaf where common_name = ?",new String[]{"commonName"});
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext()){
            String scientificName=c.getString(0);
            String Family=c.getString(1);
            String Genus=c.getString(2);
            buffer.append(" "+scientificName+ " "+Family+ " "+ Genus);
        }
        return buffer.toString();

    }




}
