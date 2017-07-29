package com.Demo.onekey2alarm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class aid  extends Activity{
		public static EditText N,A,B,W,H;
		private Button save;
		private Context mContext; 
	    String fileNameN = "saveAidN.txt",
	    		fileNameA = "saveAidA.txt",
	    		fileNameB = "saveAidB.txt",
	    		fileNameW = "saveAidW.txt",
	    		fileNameH = "saveAidH.txt";
	    private Button aid_back;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.aid);
			//Toast.makeText(messages.this, MainActivity.Messages,1).show();
			mContext = getApplicationContext();
			initView();
			readFileToText();
			
		}
		private void initView() {
			N = (EditText) findViewById(R.id.et_name);
			A = (EditText) findViewById(R.id.et_age);
			B = (EditText) findViewById(R.id.et_blood);
			W = (EditText) findViewById(R.id.et_wight);
			H = (EditText) findViewById(R.id.et_hight);
	        save = (Button) findViewById(R.id.bt_save_aid);
	        aid_back = (Button) findViewById(R.id.bt_back_aid);
	        aid_back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					finish();
				}
			});
	        save.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	try {  
	                    //保存文件名和内容  
	            		FileHelper fHelperN = new FileHelper(mContext);
	            		FileHelper fHelperA = new FileHelper(mContext);
	            		FileHelper fHelperB = new FileHelper(mContext);
	            		FileHelper fHelperW = new FileHelper(mContext);
	            		FileHelper fHelperH = new FileHelper(mContext);
	                    fHelperN.save(fileNameN, N.getText().toString());
	                    fHelperA.save(fileNameA, A.getText().toString());
	                    fHelperB.save(fileNameB, B.getText().toString());
	                    fHelperW.save(fileNameW, W.getText().toString());
	                    fHelperH.save(fileNameH, H.getText().toString());
	                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();  
	                } catch (Exception e) {  
	                    //写入异常时  
	                    e.printStackTrace();  
	                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();  
	                }  
	            }
	        });
	    }
		
		public void readFileToText(){
			//定论一个detail，默认为空用来存放要输出的内容  
	        String detailN = ""; 
	        String detailA = "";
	        String detailB = "";
	        String detailW = "";
	        String detailH = "";
	        
	        //String txtads = filePath+fileName;
	        FileHelper fHelper2 = new FileHelper(getApplicationContext());  
	        try {  
	            //得到输入框中文件名获得文件内容，因为可以写入多个不同名文件，所以要根据文件名来获得文件内容  
	        	//String fname = fileName;  
	            //调用read()方法，传入上面获得的文件保，将返回的内容赋值给detail  
	            detailN = fHelper2.read(fileNameN);
	            detailA = fHelper2.read(fileNameA);
	            detailB = fHelper2.read(fileNameB);
	            detailW = fHelper2.read(fileNameW);
	            detailH = fHelper2.read(fileNameH);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	        //Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show(); 
	        N.setText(detailN);
	        A.setText(detailA);
	        B.setText(detailB);
	        W.setText(detailW);
	        H.setText(detailH);
		}
	 
	    // 将字符串写入到文本文件中
	    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
	        // 生成文件夹之后，再生成文件，不然会出错
	        makeFilePath(filePath, fileName);
	 
	        String strFilePath = filePath + fileName;
	        // 每次写入时，都换行写
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
	 
	    // 生成文件
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
	 
	    // 生成文件夹
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
