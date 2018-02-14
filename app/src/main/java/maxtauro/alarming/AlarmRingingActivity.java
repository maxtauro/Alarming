package maxtauro.alarming;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by maxtauro on 2018-02-09.
 */

public class AlarmRingingActivity extends AppCompatActivity {
    private EditText dismissText;
    private Button dismissButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ringing_activity);

        final MediaPlayer mediaSong = MediaPlayer.create(this, R.raw.alien_drum);
        mediaSong.start();
        dismissButton = (Button) findViewById(R.id.dismissButton);
        dismissText = (EditText) findViewById(R.id.dismiss_text);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast msg = Toast.makeText(getBaseContext(), dismissText.getText(), Toast.LENGTH_LONG);
                msg.show();
                mediaSong.stop();
                mediaSong.reset();

            }
        });
    }
}
