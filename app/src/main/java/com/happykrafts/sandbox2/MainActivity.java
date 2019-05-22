package com.happykrafts.sandbox2;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;


public class MainActivity extends AppCompatActivity {
    private Button mBtnLoad;
    private TextView mTvStatus;
    private EditText mEtMsgFromApp1;
    String fullPath;

    String packageName = "com.happykrafts.sandbox1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLoad = findViewById(R.id.bt_load);
        mEtMsgFromApp1 = findViewById(R.id.et_msgFrom1);
        mTvStatus = findViewById(R.id.tv_status);
    }


    public void  loadFile(View view){
        PackageManager packageManager = getPackageManager();
        try {
           ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName,PackageManager.GET_META_DATA);
           mTvStatus.setText(applicationInfo.dataDir);
           fullPath = applicationInfo.dataDir + "/files/RAHUL.txt";
           readFile(fullPath);

        } catch (PackageManager.NameNotFoundException e) {
            mTvStatus.setText(e.toString());
        }

    }
    public void readFile(String fullPath){

        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(new File(fullPath));
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while ((read = fileInputStream.read())!= -1){
                buffer.append((char) read);
            }
//            L.s(this," "+buffer);
            mEtMsgFromApp1.setText(buffer);
            mTvStatus.setTextColor(Color.GREEN);
            mTvStatus.setText(buffer+"\n Read Sucessfully from\n"+ fullPath);



        }catch (Exception e ){

            mTvStatus.setText(e.toString());
            mTvStatus.setTextColor(Color.RED);
        }
        finally {
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch (Exception e ){

                    mTvStatus.setTextColor(Color.RED);
                    mTvStatus.setText(e.toString());

                }
            }
        }

    }
}
