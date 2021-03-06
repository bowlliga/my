package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Bluetooth extends AppCompatActivity {
    Switch btOnOffSwitch; //블루투스 온오프 스위치버튼
    BluetoothAdapter bluetoothAdapter;


    final static int BT_REQUEST_ENABLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btOnOffSwitch = findViewById(R.id.btOnOffSwitch);
        btOnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    blueoothOn();

                }else{
                    bluetoothOff();

                }
            }
        });

        //기기가 블루투스를 지원하는지 알아오는 메소드
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }//onCreate()


    //블루투스 On 메소드
    private void blueoothOn() {
        //블루투스 켜기
        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),
                    "블루투스를 지원하지 않는 기기 입니다.", Toast.LENGTH_SHORT).show();
        }else {
            if (bluetoothAdapter.isEnabled()){
                Toast.makeText(getApplicationContext(),
                        "블루투스가 이미 켜져 있습니다.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),
                        "블루투스가 켜져 있지 않습니다.", Toast.LENGTH_SHORT).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }//blueoothOn()

    //블루투스 Off 메소드
    private void bluetoothOff() {
        //블루투스 끄기
        if (bluetoothAdapter.isEnabled()){
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(),
                    "블루투스가 꺼졌습니다.", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(),
                    "블루투스가 이미 꺼져 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }//bluetoothOff()
}