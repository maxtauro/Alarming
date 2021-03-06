package maxtauro.alarming;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by maxtauro on 2018-01-30.
 */

public class AlarmObject extends AlarmListPage {

    int hour;
    int minute;
    boolean isSet = false; //isSet means the alarm is set and should go off
                           //!isSet means the alarm is not set and should not go off
    boolean isAM;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Context context;

    public AlarmObject(int hr, int min, AlarmManager alarmManager) {
        this.hour = hr;
        this.minute = min;
        this.alarmManager = alarmManager;
    }

    public AlarmObject(int hr, int min, AlarmManager alarmManager,  boolean bSet) {

        this.hour = hr;
        this.minute = min;
        this.alarmManager = alarmManager;
        this.isSet = bSet;
    }

    public void setAlarm(Context context){

        this.context = context;

        // create an intent to the alarm receiver class
        final Intent receiverIntent = new Intent(this.context, AlarmReceiver.class);

        // create and instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        if(!isSet){
            Log.d("Setting Alarm", "");
            isSet = true;
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            //put an extra string into receiverIntent
            //tells the clock that the alarm is on
            receiverIntent.putExtra("stopAlarm",false);
            receiverIntent.putExtra("Alarm", this.toString());

            // create a pending intent that delays the intent
            // until the specified calendar time
            pendingIntent = PendingIntent.getBroadcast(context,
                    0,
                    receiverIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            // set the alarm manager
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        else if(isSet){ //the alarm will be canceled
            isSet = false;
            alarmManager.cancel(pendingIntent);

            // tells the clock that the alarm should be off
            receiverIntent.putExtra("stopAlarm",true);

            //stop the ringtone
            sendBroadcast(receiverIntent);
        }
    }

    public void cancelAlarm(){
        isSet = false;
        alarmManager.cancel(pendingIntent);
    }

    public int timeAsInt(){
        return (this.hour*100 + this.minute);
    }

    public String toString(){
        String sMinute = String.valueOf(minute);
        String sHour = String.valueOf(hour);
        if (hour > 12){
            sHour = String.valueOf(hour - 12);
            isAM = false;
        }
        if (minute < 10) sMinute = "0" + String.valueOf(minute);

        String text = sHour + ":" + sMinute;

        if(isAM) text+= " am";
        else text += " pm";

        return text;
    }
}
