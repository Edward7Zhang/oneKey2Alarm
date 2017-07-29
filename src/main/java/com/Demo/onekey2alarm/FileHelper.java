package com.Demo.onekey2alarm;

import android.content.Context;  

import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  

public class FileHelper {  
  
    private Context mContext;  
  
    //�ղ������캯���������ֵΪ��ʱ��������  
    public FileHelper() {  
    }  
  
    public FileHelper(Context mContext) {  
        super();  
        this.mContext = mContext;  
    }  
  
    /* 
    * �����ļ�����ķ�����д�뵽�ļ��У������������ 
    * */  
    public void save(String name, String content) throws Exception {  
        //Context.MODE_PRIVATEȨ�ޣ�ֻ�����������ܷ��ʣ�����д������ݻḲ���ı���ԭ������  
        FileOutputStream output = mContext.openFileOutput(name, Context.MODE_PRIVATE);  
        output.write(content.getBytes());  //��String�ַ������ֽ�������ʽд�뵽�������  
        output.close();         //�ر������  
    }  
  
  
    /* 
    * �����ļ���ȡ�ķ��� 
    * */  
    public String read(String filename) throws IOException {  
        //���ļ�������  
        FileInputStream input = mContext.openFileInput(filename);  
        //����1M�Ļ�����  
        byte[] temp = new byte[1024];  
        //�����ַ�������  
        StringBuilder sb = new StringBuilder("");  
        int len = 0;  
        //��ȡ�ļ����ݣ����ļ����ݳ��ȴ���0ʱ��  
        while ((len = input.read(temp)) > 0) {  
            //�����������ӵ�β��  
            sb.append(new String(temp, 0, len));  
        }  
        //�ر�������  
        input.close();  
        //�����ַ���  
        return sb.toString();  
    }  
  
}  