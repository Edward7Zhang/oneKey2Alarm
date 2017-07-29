package com.Demo.onekey2alarm;

import android.app.Activity;
import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.SQLException;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  
import android.util.Log;  
import android.widget.Toast;
  
public class DBAdapter extends Activity{  
	 
    static final String KEY_ROWID = "_id";  
    static final String KEY_NAME = "name";  
    static final String KEY_PHONENUM = "phonenum";  
    static final String TAG = "DBAdapter";  
      
    static final String DATABASE_NAME = "MyDB.db";  
    static final String DATABASE_TABLE = "contacts";  
    static final int DATABASE_VERSION = 1;  
      
    static final String DATABASE_CREATE =   
            "create table contacts( _id integer primary key autoincrement, " +   
            "name text not null, phonenum text not null);";  
    final Context context;  
      
    static DatabaseHelper DBHelper;  
    static SQLiteDatabase db;  
      
    public DBAdapter(Context cxt)  
    {  
        this.context = cxt;  
        DBHelper = new DatabaseHelper(context);  
    }  
      
    static class DatabaseHelper extends SQLiteOpenHelper  
    {  
  
        DatabaseHelper(Context context)  
        {  
            super(context, DATABASE_NAME, null, DATABASE_VERSION);  
        }  
        @Override  
        public void onCreate(SQLiteDatabase db) {  
            // TODO Auto-generated method stub  
            try  
            {  
                db.execSQL(DATABASE_CREATE);  
            }  
            catch(SQLException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
        @Override  
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
            // TODO Auto-generated method stub  
            Log.wtf(TAG, "Upgrading database from version "+ oldVersion + "to "+  
             newVersion + ", which will destroy all old data");  
            db.execSQL("DROP TABLE IF EXISTS contacts");  
            onCreate(db);  
        }  
    }  
      
    //open the database  
    public DBAdapter open() throws SQLException  
    {  
        db = DBHelper.getWritableDatabase();  
        return this;  
    }  
    //close the database  
    public void close()  
    {  
        DBHelper.close();  
    }  
      
    //insert a contact into the database  
    public long insertContact(String name, String phonenum)  
    {  
        ContentValues initialValues = new ContentValues();  
        initialValues.put(KEY_NAME, name);  
        initialValues.put(KEY_PHONENUM, phonenum);  
        return db.insert(DATABASE_TABLE, null, initialValues);  
    }  
    //delete a particular contact  
    public boolean deleteContact(long rowId)  
    {  
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" +rowId, null) > 0;  
    }  
    //retreves all the contacts  
    public Cursor getAllContacts()  
    {  
        return db.query(DATABASE_TABLE, new String[]{ KEY_ROWID,KEY_NAME,KEY_PHONENUM}, null, null, null, null, null);  
    }  
    
    //retreves a particular contact  
    public Cursor getContact(long rowId) throws SQLException  
    {  
        Cursor mCursor =   
                db.query(true, DATABASE_TABLE, new String[]{ KEY_ROWID,  
                         KEY_NAME, KEY_PHONENUM}, KEY_ROWID + "=" + rowId, null, null, null, null, null);  
        if (mCursor != null)  
            mCursor.moveToFirst();  
        return mCursor;  
    }  
    //updates a contact  
    public boolean updateContact(long rowId, String name, String phonenum)  
    {  
        ContentValues args = new ContentValues();  
        args.put(KEY_NAME, name);  
        args.put(KEY_PHONENUM, phonenum);  
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" +rowId, null) > 0;  
    }  
    
    public Cursor query(String key)
    {
    	return db.query(true, DATABASE_TABLE, new String[]{ KEY_ROWID,  
                         KEY_NAME, KEY_PHONENUM}, KEY_ROWID + "=" + key, null, null, null, null, null); 
    }
    public boolean delete(SQLiteDatabase mDb, String table, int id) {  
    	 String whereClause = "id=?";  
    	 String[] whereArgs = new String[] { String.valueOf(id) };  
    	 try {  
    	 mDb.delete(table, whereClause, whereArgs);  
    	 } catch (SQLException e) {  
    	 Toast.makeText(getApplicationContext(), "É¾³ýÊý¾Ý¿âÊ§°Ü",Toast.LENGTH_LONG).show();  
    	 return false;  
    	 }  
    	 return true;  
    	 }  
     
}  