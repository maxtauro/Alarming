package maxtauro.alarming;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.app.DialogFragment;
import android.app.Dialog;
import java.util.Calendar;
import android.widget.TimePicker;

import static android.content.Context.ALARM_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    AlarmListPage alarmListPage = new AlarmListPage();
    AlarmManager alarmManager;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =  new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        //Create and return a new instance of TimePickerDialog
        return timePickerDialog;
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //AlarmObject alarm = new AlarmObject(hourOfDay, minute);
        //alarm.setAlarm();

        AlarmListPage callingActivity = (AlarmListPage) getActivity();
        callingActivity.addAlarmToList(hourOfDay,minute);
    }


}