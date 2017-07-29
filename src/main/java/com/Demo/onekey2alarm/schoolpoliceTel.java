package com.Demo.onekey2alarm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class schoolpoliceTel extends Activity implements OnClickListener{
	Button bt_back_schoolpoliceTel,bt_save_schoolpoliceTel;
	private Context mContext; 
    String fileName = "saveschoolpoliceTel.txt";
    public static EditText et_schoolpoliceTel;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.schoolploicetel);
			mContext = getApplicationContext();
			initView();
			readFileToText();
			bt_back_schoolpoliceTel = (Button) findViewById(R.id.bt_back_schoolpoliceTel);
			bt_back_schoolpoliceTel.setOnClickListener(this);
		}
		@Override
		public void onClick(View v) {
			
				finish();
			}	
		
		private void initView() {
			et_schoolpoliceTel = (EditText) findViewById(R.id.et_schoolpoliceTel);
			bt_save_schoolpoliceTel = (Button) findViewById(R.id.bt_save_schoolpoliceTel);
	        bt_save_schoolpoliceTel.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	try {  
	                    //�����ļ���������  
	            		FileHelper fHelper = new FileHelper(mContext);
	                    fHelper.save(fileName, et_schoolpoliceTel.getText().toString());  
	                    Toast.makeText(getApplicationContext(), "����д��ɹ�", Toast.LENGTH_SHORT).show();  
	                } catch (Exception e) {  
	                    //д���쳣ʱ  
	                    e.printStackTrace();  
	                    Toast.makeText(getApplicationContext(), "����д��ʧ��", Toast.LENGTH_SHORT).show();  
	                }  
	            }
	        });
	    }
		
		public void readFileToText(){
			//����һ��detail��Ĭ��Ϊ���������Ҫ���������  
	        String detail = ""; 
	        //String txtads = filePath+fileName;
	        FileHelper fHelper2 = new FileHelper(getApplicationContext());  
	        try {  
	            //�õ���������ļ�������ļ����ݣ���Ϊ����д������ͬ���ļ�������Ҫ�����ļ���������ļ�����  
	        	String fname = fileName;  
	            //����read()���������������õ��ļ����������ص����ݸ�ֵ��detail  
	            detail = fHelper2.read(fileName);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	        //Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show(); 
	        et_schoolpoliceTel.setText(detail);
		}
	 
	    // ���ַ���д�뵽�ı��ļ���
	    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
	        // �����ļ���֮���������ļ�����Ȼ�����
	        makeFilePath(filePath, fileName);
	 
	        String strFilePath = filePath + fileName;
	        // ÿ��д��ʱ��������д
	        String strContent = strcontent + "\r\n";
	        try {
	            File file = new File(strFilePath);
	            if (!file.exists()) {
	                Log.d("TestFile", "Create the file:" + strFilePath);
	                file.getParentFile().mkdirs();
	                file.createNewFile();
	            }
	            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
	            raf.seek(file.length());
	            raf.write(strContent.getBytes());
	            raf.close();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    // �����ļ�
	    public File makeFilePath(String filePath, String fileName) {
	        File file = null;
	        makeRootDirectory(filePath);
	        try {
	            file = new File(filePath + fileName);
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return file;
	    }
	 
	    // �����ļ���
	    public static void makeRootDirectory(String filePath) {
	        File file = null;
	        try {
	            file = new File(filePath);
	            if (!file.exists()) {
	                file.mkdir();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	


}
