package com.example.thibault.todoproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Model[] modelItems = new Model[0];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);
        Button add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long[] id = new long[lv.getCheckedItemIds().length];
                id = lv.getCheckedItemIds();
                for(int i = 0; i<lv.getCheckedItemCount(); i++){
                    modelItems[(int)id[i]].value = (modelItems[(int)id[i]].value +1 )/2 ;
                }
                EditText task = (EditText) findViewById(R.id.editText);
                Model[] neu = new Model[modelItems.length+1];
                for(int i = 0;i<modelItems.length; i++){
                    neu[i] = modelItems[i];
                }
                neu[modelItems.length] = new Model(task.getText().toString(), 0);
                modelItems = new Model[neu.length];
                modelItems = neu;
                update();
            }
        });

    }
    public void update(){
        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
