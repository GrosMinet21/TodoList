package com.example.thibault.todoproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class muchwow extends AppCompatActivity {
    //protected DataBase db;
    List<Task> list;
    MyAdapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_muchwow);
        //db = new DataBase(this);
        //list = db.getAllTasks();
        adapt = new MyAdapter(this, R.layout.checkbox, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
    }

    public void addTaskNow(View v) {
        EditText t = (EditText) findViewById(R.id.editText1);
        String s = t.getText().toString();
        if (s.equalsIgnoreCase("")) {
            Toast.makeText(this, "enter the task description first!!",
            Toast.LENGTH_LONG);
        } else {
            Task task = new Task(s, 0);
            //db.addTask(task);
            t.setText("");
            adapt.add(task);
            adapt.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_muchwow, menu);
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
    private class MyAdapter extends ArrayAdapter<Task> {
        Context context;
        List<Task> taskList = new ArrayList<Task>();
        int layoutResourceId;
        public MyAdapter(Context context, int layoutResourceId,
                         List<Task> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.taskList = objects;
            this.context = context;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox chk = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.checkbox,
                        parent, false);
                chk = (CheckBox) convertView.findViewById(R.id.chkStatus);
                convertView.setTag(chk);
                chk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Task changeTask = (Task) cb.getTag();
                        changeTask.setStatus(cb.isChecked() == true ? 1 : 0);
                        //db.updateTask(changeTask);
                        Toast.makeText(
                                getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() + " is "
                                        + cb.isChecked(), Toast.LENGTH_LONG)
                                .show();
                    }
                });
            } else {
                chk = (CheckBox) convertView.getTag();
            }
            Task current = taskList.get(position);
            chk.setText(current.getTaskName());
            chk.setChecked(current.getStatus() == 1 ? true : false);
            chk.setTag(current);
            return convertView;
        }
    }
}
