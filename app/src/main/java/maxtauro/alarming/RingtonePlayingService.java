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
        boolean stopAlarm = intent.getExtras().getBoolean("stopAlarm");

        Log.e("should the alarm stop?", String.valueOf(stopAlarm));
        if(!stopAlarm){ //start
            Intent cancelAlarmIntent = new Intent(RingtonePlayingService.this, AlarmRingingActivity.class);
            cancelAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cancelAlarmIntent.putExtra("Intent", intent);
            RingtonePlayingService.this.startActivity(cancelAlarmIntent);
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        // Tell the user we stopped
        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
    }

}
