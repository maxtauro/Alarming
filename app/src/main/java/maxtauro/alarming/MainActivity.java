package maxtauro.alarming;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    //to Make our alarm manager;
    AlarmManager alarmManager;
    TimePicker alarmTimepicker;
    TextView alarmStatusText;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        // initialize alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // initialze timepicker
        alarmTimepicker = (TimePicker) findViewById(R.id.timePicker);

        // initialize textbox
        alarmStatusText = (TextView) findViewById(R.id.alarmStatus);
        alarmStatusText.setText("Alarm not set");

        // create and instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        // initialize buttons
        Button alarmSet = (Button) findViewById(R.id.alarm_set);
        Button alarmCancel = (Button) findViewById(R.id.alarm_cancel);

        // create an intent to the alarm receiver class
        final Intent receiverIntent = new Intent(this.context, AlarmReceiver.class);

        // create onClick listener to stop the alarm of undo an alarm set
        alarmSet.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // get the values of the hour and minute;
                int hour = alarmTimepicker.getHour();
                int minute = alarmTimepicker.getMinute();

                calendar.set(Calendar.HOUR_OF_DAY, alarmTimepicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimepicker.getMinute());

                // convert the int values to strings
                String hourString = String.valueOf(hour);
                String minuteString = String.valueOf(minute);

                if (hour > 12) hourString = String.valueOf(hour - 12);
                if (minute < 10) minuteString = "0" + String.valueOf(minute);

                setAlarmText(hourString + ":" + minuteString);

                // create a pending intent that delays the intent
                // until the specified calendar time
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                        0,
                        receiverIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                // set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

            ;
        });

        alarmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmText("Alarm off");
                alarmManager.cancel(pendingIntent);
            }

            ;
        });
    }

        private void setAlarmText(String output) {
            alarmStatusText.setText(output);
        }






}
