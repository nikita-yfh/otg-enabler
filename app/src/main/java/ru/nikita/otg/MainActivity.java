package ru.nikita.otg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    public static void sudo(String...strings) {
        try{
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            for (String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                su.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void enable(View view){
    	sudo("echo 1 > /sys/devices/platform/tran_battery/OTG_CTL");
    }
    public void disable(View view){
    	sudo("echo 0 > /sys/devices/platform/tran_battery/OTG_CTL");
    }
}