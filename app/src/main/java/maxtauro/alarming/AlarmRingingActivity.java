package maxtauro.alarming;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by maxtauro on 2018-02-09.
 */

public class AlarmRingingActivity extends AppCompatActivity {
    private EditText dismissText;
    private TextView instructText;
    private Button dismissButton;
    public RSAScheme rsaScheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ringing_activity);

        final MediaPlayer mediaSong = MediaPlayer.create(this, R.raw.alien_drum);
        mediaSong.start();
        mediaSong.setLooping(true);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //Start w/o delay
        //vibrate for 100 ms
        //sleep for 1000 ms
        long[] vibratePattern = {0, 100, 1000};
        v.vibrate(vibratePattern,0); // 0 means repeat indefinitely

        rsaScheme = new RSAScheme();

        instructText = (TextView) findViewById(R.id.RSAText);
        dismissButton = (Button) findViewById(R.id.dismissButton);
        dismissText = (EditText) findViewById(R.id.dismiss_text);

        instructText.setText(rsaScheme.rsaAsText());

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dismissAttempt = dismissText.getText().toString().trim();
                System.out.println(String.valueOf(rsaScheme.decryption_key)+ " is the answer.  Guess is: " + dismissAttempt);

                if (dismissAttempt.equals(String.valueOf(rsaScheme.decryption_key))){
                    mediaSong.stop();
                    mediaSong.reset();
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() { }

}
