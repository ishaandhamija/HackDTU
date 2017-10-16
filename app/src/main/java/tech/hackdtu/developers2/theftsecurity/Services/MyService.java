package tech.hackdtu.developers2.theftsecurity.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import tech.hackdtu.developers2.theftsecurity.R;

/**
 * Created by ishaandhamija on 14/10/17.
 */

public class MyService extends Service implements SensorEventListener {


    public static SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;
    public static Context ctx;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public static MediaPlayer mp;
    public static Boolean doneOnce = false;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ctx = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
            } else {
                Log.d("durr", "onSensorChanged: ");
                if ((sharedpreferences.getString("isLocked", null) != null)
                    && (sharedpreferences.getString("isLocked", null).equals("yes"))
                        && (doneOnce == true)) {
                    if (sharedpreferences.getString("alarmDelay", null) == null) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ((mp == null) || (!mp.isPlaying())) {
                                    mp = MediaPlayer.create(MyService.this, R.raw.siren);
                                    mp.start();
                                }
                            }
                        }, 5000);
                    }
                    else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ((mp == null) || (!mp.isPlaying())) {
                                    mp = MediaPlayer.create(MyService.this, R.raw.siren);
                                    mp.start();
                                }
                            }
                        }, Integer.parseInt(sharedpreferences.getString("alarmDelay", null)) * 1000);
                    }
                }
                doneOnce = true;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}