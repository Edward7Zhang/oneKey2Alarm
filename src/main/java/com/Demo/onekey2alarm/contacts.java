package com.Demo.onekey2alarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class contacts extends Activity implements OnItemLongClickListener{
	
	private Button con_back,bt_Add;
	static ListView listView;
	private ArrayAdapter<String> adapter;
	private SimpleAdapter simpleadapter;
	private ArrayList<Map<String,Object>>dataList = new ArrayList<Map<String,Object>>();
	static String number;
	String name; 
	static String id;
	public static EditText msn; 
	private boolean isfrist=true;
	static Context mContext; 
	private List<String> curList = new ArrayList<String>();  
	DBAdapter db = new DBAdapter(this);  
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contacts);
		mContext = getApplicationContext();
	
		listView = (ListView) findViewById(R.id.listV);
		//为 ListView 的所有 item 注册 ContextMenu   
		this.registerForContextMenu(listView);  

        listView.setOnItemLongClickListener(this);
        intint();
        
		con_back = (Button) findViewById(R.id.bt_back_con);
		con_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentc = new Intent(contacts.this, menu.class);
				setResult(2);
				finish();
			}
		});
		bt_Add = (Button) findViewById(R.id.bt_Add);
		bt_Add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent data = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(data, 0);
			}
		});
	}
	//get all contacts
	public void intint(){
		db.open(); 
        Cursor c = db.getAllContacts();
        if (c.moveToFirst()) 
        { 
            do {
            	id = c.getString(c.getColumnIndex("_id"));
            	name = c.getString(c.getColumnIndex("name"));
                number = c.getString(c.getColumnIndex("phonenum"));
            	Map<String, Object> map = new HashMap<String, Object>();
            	map.put("contacts_name", name);
            	map.put("contacts_num", number);
     	        dataList.add(map);
     	        simpleadapter = new SimpleAdapter(this, dataList, R.layout.contact_text, new String[]{"contacts_name","contacts_num"}, 
		 				new int[]{R.id.contacts_name,R.id.contacts_num});
            	listView.setAdapter(simpleadapter); 
               // DisplayContact(c); 
                	
            }while (c.moveToNext()); 
            db.close();
        } 
	}
	public void DisplayContact(Cursor c)  
    {  
        Toast.makeText(this ,   
                "id: "+c.getString(0) + "\n" +  
                "Name: "+c.getString(1) + "\n"+  
                "phonenum: "+c.getString(2),   
                Toast.LENGTH_SHORT).show();  
    }  

	//获取选择的号码：
		protected void onActivityResult (int requestCode, int resultCode, Intent data) 
		{
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == Activity.RESULT_OK) {
	            ContentResolver reContentResolverol = getContentResolver();
	             Uri contactData = data.getData();
	             @SuppressWarnings("deprecation")
	            Cursor cursor = managedQuery(contactData, null, null, null, null);
	             cursor.moveToFirst();
	             name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
	            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
	                     null, 
	                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, 
	                     null, 
	                     null);
	             while (phone.moveToNext()) {
	                 number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));	      
	                 }
	             isfrist=false;
	           //add a contact  
	             db.open(); 
	             long id = db.insertContact(name, number);
	     		if(isfrist !=true)
	     		{	
	     			//get a contact  
	     	        db.open();
	     	       Cursor a = db.getContact(id);
	     	       
	     	        if (a.moveToLast()){
		     			 Map<String, Object> map = new HashMap<String, Object>();
		     	            map.put("contacts_name", name);
		     	            map.put("contacts_num", number);
		     	            dataList.add(map);
		     	            simpleadapter = new SimpleAdapter(this, dataList, R.layout.contact_text, new String[]{"contacts_name","contacts_num"}, 
		     		 				new int[]{R.id.contacts_name,R.id.contacts_num});
		     	            listView.setAdapter(simpleadapter);  
		     	            simpleadapter.notifyDataSetChanged();
	     	           // DisplayContact(a);
	     	            
	     	        }
	     	        else 
	     	            Toast.makeText(this, "No contact found!", Toast.LENGTH_SHORT).show(); 
	     		}
	     		db.close(); 
			}
	             
	  }
		
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				final int position, final long id) {
			// TODO Auto-generated method stub
			//定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框  
	        AlertDialog.Builder builder=new Builder(contacts.this);  
	        builder.setMessage("确定删除?");  
	        builder.setTitle("提示"); 
	     
	        //添加AlertDialog.Builder对象的setPositiveButton()方法  
	        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	        	public void onClick(DialogInterface dialog, int which) { 
	                if(dataList.remove(position)!=null){ 
	                	//delete a contact 
	                    db.open();
	                    //Cursor a = db.getContact(1);	
	                    Cursor cursor = getcursor();
	                    cursor.moveToPosition(position);
	                    int i = cursor.getInt(cursor.getColumnIndex("_id"));
	                    	if (db.deleteContact(i)){
	                    		
	                    		Toast.makeText(contacts.this, "Delete successful!", Toast.LENGTH_LONG).show(); 
	                    		System.out.println("success"); }     
	                }else {  
	                    System.out.println("failed");
	                    Toast.makeText(contacts.this, "Delete failed!", Toast.LENGTH_LONG).show(); 
	                }  
	                db.close();
	                simpleadapter.notifyDataSetChanged(); 
	                listView.invalidate();
	                }
	                //Toast.makeText(getBaseContext(), "删除列表项", Toast.LENGTH_SHORT).show();  
	            
	        });  
	          
	        //添加AlertDialog.Builder对象的setNegativeButton()方法  
	        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
	              
	            public void onClick(DialogInterface dialog, int which) {  
	                  
	            }  
	        });  
	          
	        builder.create().show();  
	        return false;  
	    }
		//获得表中内容并返回一个游标Cursor
	    public Cursor getcursor(){
	    SQLiteDatabase db = DBAdapter.DBHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery( 
		        "SELECT * FROM contacts", null);
		return cursor;
		    }
		 
	}  
	
    
	

	
	


		
		