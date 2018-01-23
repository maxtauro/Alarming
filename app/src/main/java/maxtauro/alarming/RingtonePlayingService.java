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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // play the ringtone
        mediaSong = MediaPlayer.create(this, R.raw.railroad_bell);
        mediaSong.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){

        // Tell the user we stopped
        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
    }
}
