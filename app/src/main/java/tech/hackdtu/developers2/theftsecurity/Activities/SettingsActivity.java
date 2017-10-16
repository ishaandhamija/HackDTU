package tech.hackdtu.developers2.theftsecurity.Activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import tech.hackdtu.developers2.theftsecurity.R;
import tech.hackdtu.developers2.theftsecurity.Utils.MyAdmin;

public class SettingsActivity extends AppCompatActivity {

    Switch adminswitch;

    public static final Integer RESULT_ENABLE = 111;
    DevicePolicyManager devicePolicyManager;
    ActivityManager activityManager;
    ComponentName componentName;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    EditText delayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        componentName = new ComponentName(this, MyAdmin.class);

        delayTime = (EditText) findViewById(R.id.delayTime);

        if (sharedpreferences.getString("alarmDelay", null) != null) {
            delayTime.setText(sharedpreferences.getString("alarmDelay", null));
        }

        adminswitch = (Switch) findViewById(R.id.adminswitch);
        if (sharedpreferences.getString("admin", null) != null) {
            if (sharedpreferences.getString("admin", null).equals("enabled")) {
                adminswitch.setChecked(true);
            }
        }

        adminswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text");
                    startActivityForResult(intent, RESULT_ENABLE);
                }
                else {
                    devicePolicyManager.removeActiveAdmin(componentName);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("admin", "disabled");
                    editor.commit();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 111 :
                if (resultCode == Activity.RESULT_OK) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("admin", "enabled");
                    editor.commit();
                    adminswitch.setChecked(true);
                }
                else {
                    Toast.makeText(this, "Admin Disabled", Toast.LENGTH_SHORT).show();
                    adminswitch.setChecked(false);
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("alarmDelay", delayTime.getText().toString());
        editor.commit();
        super.onBackPressed();
    }
}