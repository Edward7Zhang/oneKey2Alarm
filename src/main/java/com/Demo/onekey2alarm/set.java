package com.Demo.onekey2alarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
public class set extends Activity implements OnClickListener{
	private Button set_back,clear,change,bt_schoolpoliceTel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.set);
		set_back = (Button) findViewById(R.id.bt_back_set);
		clear = (Button) findViewById(R.id.bt_clear);
		change = (Button) findViewById(R.id.bt_change);
		bt_schoolpoliceTel = (Button) findViewById(R.id.bt_schoolpoliceTel);
		set_back.setOnClickListener(this);
		clear.setOnClickListener(this);
		change.setOnClickListener(this);
		bt_schoolpoliceTel.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_back_set:
			setResult(4);
			finish();
			break;
		case R.id.bt_clear:
			cleanInternalCache(getApplicationContext());
			Toast.makeText(getApplicationContext(), "��������ɹ�", Toast.LENGTH_SHORT).show();  
			break;
		case R.id.bt_change:
			contacts.mContext.deleteDatabase(DBAdapter.DATABASE_NAME);
			cleanApplicationData(getApplicationContext(),"saveMessages.txt","saveAddress.txt","saveAidN.txt","saveAidA.txt","saveAidB.txt","saveAidW.txt","saveAidH.txt","saveschoolpoliceTel.txt");
			Toast.makeText(getApplicationContext(), "��������������", Toast.LENGTH_SHORT).show();
			break;
		case R.id.bt_schoolpoliceTel:
			Intent intent = new Intent(set.this, schoolpoliceTel.class);
			startActivity(intent);
			break;
		}
	}

    /** * �����Ӧ���ڲ�����(/data/data/com.xxx.xxx/cache) * * @param context */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }
    /** * �����Ӧ���������ݿ�(/data/data/com.xxx.xxx/databases) * * @param context */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }
    /**
     * * �����Ӧ��SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }
    /** * �����������Ӧ�����ݿ� * * @param context * @param dbName */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }
    /** * ���/data/data/com.xxx.xxx/files�µ����� * * @param context */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }
    /**
     * * ����ⲿcache�µ�����(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }
    /** * ����Զ���·���µ��ļ���ʹ����С�ģ��벻Ҫ��ɾ������ֻ֧��Ŀ¼�µ��ļ�ɾ�� * * @param filePath */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }
    /** * �����Ӧ�����е����� * * @param context * @param filepath */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }
    /** * ɾ������ ����ֻ��ɾ��ĳ���ļ����µ��ļ�����������directory�Ǹ��ļ������������� * * @param directory */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
