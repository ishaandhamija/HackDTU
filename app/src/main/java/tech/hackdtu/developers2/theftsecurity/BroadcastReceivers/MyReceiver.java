package tech.hackdtu.developers2.theftsecurity.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorEventListener;

import tech.hackdtu.developers2.theftsecurity.Services.MyService;

import static tech.hackdtu.developers2.theftsecurity.Services.MyService.mp;

/**
 * Created by ishaandhamija on 14/10/17.
 */

public class MyReceiver extends BroadcastReceiver {


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    public void onReceive(Context arg0, Intent intent) {

        sharedpreferences = MyService.ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.stop();
                    MyService.mSensorManager.unregisterListener((SensorEventListener) MyService.ctx);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("isLocked", "no");
                    editor.commit();
                }
            }
        }
    }
}