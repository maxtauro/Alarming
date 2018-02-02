package maxtauro.alarming;

/**
 * Created by maxtauro on 2018-01-29.
 */

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);


        //get listview object from xml,
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView

        final String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        final List<String> valuesList = new ArrayList<String>(Arrays.asList(values));

        //define a new adapter
        //first parameter - context
        //second parameter - layout for the row
        //third parameter - ID of the TextView to which the data is written
        //fourth - the array of the data

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,valuesList);

        //assign adapter to listView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?>parent, View view, int position, long id){

                                                //List view clicked item index
                                                int itemPosition = position;

                                                //List view clicked item value
                                                String itemValue = (String) listView.getItemAtPosition(itemPosition);

                                                //show alert
                                                Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                                                        .show();
                                            }

                                        }

        );

        //define add button
        Button addButton = (Button) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {
                                             Log.e("CLicking button",String.valueOf(System.currentTimeMillis()));
                                             valuesList.add(String.valueOf(System.currentTimeMillis()));
                                             adapter.notifyDataSetChanged();
                                         }

                                     }

        );

    }


}

