package tech.hackdtu.developers2.theftsecurity.Activities;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tech.hackdtu.developers2.theftsecurity.R;

public class AlertActivity extends AppCompatActivity {


    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);


        btnClose = (Button) findViewById(R.id.btn_Close);

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.siren);
        mp.start();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
            }
        });

    }
}
