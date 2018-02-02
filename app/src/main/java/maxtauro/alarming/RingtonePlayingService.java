package maxtauro.alarming;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by maxtauro on 2018-01-22.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //state of the alarm
        boolean alarmState = intent.getExtras().getBoolean("alarmState");

        Log.e("should the alarm start?", String.valueOf(alarmState));

        mediaSong = MediaPlayer.create(this, R.raw.railroad_bell);
        mediaSong.start();

        /*if (alarmState) {
            Log.e("starting alarm","");
            mediaSong.start();
        }
        else if (!alarmState) {
            Log.e("stoping alarm","");
            mediaSong.stop();
            mediaSong.reset();
        }*/
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){

        // Tell the user we stopped
        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
    }
}
