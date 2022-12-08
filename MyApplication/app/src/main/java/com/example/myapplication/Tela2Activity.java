package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Tela2Activity extends AppCompatActivity {

    Button ligarl;
    boolean ligado = false;
    CameraManager cameraManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        ligarl = findViewById(R.id.ligarl);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        ligarl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ligado){
                    try {
                        cameraManager.setTorchMode("0", false);
                        ligado = false;
                        ligarl.setText("Ligar");
                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }

                }else{
                    try {
                        cameraManager.setTorchMode("0", true);
                        ligado = true;
                        ligarl.setText("Desligar");
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }
            }
        });



    }
}