package tech.hackdtu.developers2.theftsecurity.Fragments;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import tech.hackdtu.developers2.theftsecurity.R;
import tech.hackdtu.developers2.theftsecurity.Services.MyService;
import tech.hackdtu.developers2.theftsecurity.Utils.MyAdmin;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.DEVICE_POLICY_SERVICE;

/**
 * Created by ishaandhamija on 14/10/17.
 */

public class LockFragment extends Fragment {

    ImageView lock;
    DevicePolicyManager devicePolicyManager;
    ActivityManager activityManager;
    ComponentName componentName;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_lock, container, false);

        lock = (ImageView) rootView.findViewById(R.id.lock);
        devicePolicyManager = (DevicePolicyManager) getContext().getSystemService(DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
        componentName = new ComponentName(getContext(), MyAdmin.class);

        sharedpreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean active = devicePolicyManager.isAdminActive(componentName);
                if (active) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("isLocked", "yes");
                    editor.commit();
                    Intent i = new Intent(getContext(), MyService.class);
                    getContext().startService(i);
                    devicePolicyManager.lockNow();

                }
                else {
                    Toast.makeText(getContext(), "Activate Device Administrator in Settings", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}