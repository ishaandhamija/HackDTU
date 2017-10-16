package tech.hackdtu.developers2.theftsecurity.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sharaddadhich on 14/10/17.
 */


public class ConnectionChangeReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent )
    {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (activeNetInfo != null) {

//                Toast.makeText(context, "Active Network Type : " + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
//            if(activeNetInfo.getTypeName().equals(StationAlarmFragment.metro.get((StationAlarmFragment.to)-1).getName()))
                if (activeNetInfo.getTypeName().equals("WIFI")) {
                    Intent gotomain = new Intent();
                    gotomain.setClassName("tech.hackdtu.developers2.theftsecurity", "tech.hackdtu.developers2.theftsecurity.Activities.AlertActivity");
                    gotomain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(gotomain);
                }
            }
            if (mobNetInfo != null) {
//                Toast.makeText(context, "Mobile Network Type : " + mobNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        }
}


