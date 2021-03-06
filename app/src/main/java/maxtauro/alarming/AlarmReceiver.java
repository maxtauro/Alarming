package maxtauro.alarming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.StringBufferInputStream;

/**
 * Created by maxtauro on 2018-01-22.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){
        Log.e("In the receiver class", "Yasss");

        // get alarmState from the intent
        boolean alarmState = intent.getExtras().getBoolean("stopAlarm");
        String alarmTime = intent.getExtras().getString("Alarm");

        Log.i("is the alarm on?", String.valueOf(alarmState));
        Log.e("this alarm is: " , alarmTime);

        //create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // pass the extra string from main to ringtone playing service
        service_intent.putExtra("stopAlarm", alarmState);

        // start the ringtone
        context.startService(service_intent);
    }

}
