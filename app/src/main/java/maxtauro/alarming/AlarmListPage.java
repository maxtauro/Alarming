package maxtauro.alarming;

/**
 * Created by maxtauro on 2018-01-29.
 */

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AlarmListPage extends AppCompatActivity {
    ListView listView;
    AlarmManager alarmManager;
    List<String> alarmList = new ArrayList<String>();
    List<AlarmObject> alarmsList = new ArrayList<AlarmObject>();
    ArrayAdapter<AlarmObject> adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        this.context = this;

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //get listview object from xml,
        listView = (ListView) findViewById(R.id.list);

        alarmsList = new ArrayList<AlarmObject>();

        alarmList = new ArrayList<String>();

        adapter = new ArrayAdapter<AlarmObject>(this, android.R.layout.simple_list_item_1, android.R.id.text1,alarmsList);

        //assign adapter to listView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?>parent, View view, int position, long id){

            //to edit the alarm

        }
        });

        //define add button
        Button addButton = (Button) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLicking button", String.valueOf(System.currentTimeMillis()));
                //alarmList.add(String.valueOf(System.currentTimeMillis()));
                adapter.notifyDataSetChanged();

                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
    }


    public void addAlarmToList(int hour, int min) {
        Log.e("got to addToList, ", "");
        AlarmObject alarm = new AlarmObject(hour,min,alarmManager);
        alarmsList.add(alarm);
        //alarmList.add(alarm.alarmText());
        adapter.notifyDataSetChanged();
    }
}

