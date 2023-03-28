package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


//Step 4 Create MainActivity
public class MainActivity extends AppCompatActivity {

    //Step 6
    //Create an ArrayList with TODO Objects
    private ArrayList<TODO> elements;

    //Create MyListAdapter Object
    private MyListAdapter myAdapter;

    //Create TODO Class object
    TODO todo;

    //Main Method
    //This is where code execution begins
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 8
        //Cast xml components with Java objects
        EditText editText = (EditText) findViewById(R.id.editText);
        Switch swUrgernt = findViewById(R.id.switch2);
        Button addButton = findViewById(R.id.myButton);

        elements = new ArrayList<>();

        //Step 9
        //addButton click listener
        addButton.setOnClickListener(click -> {

            //When ADD button is clicked
            //We get text from editText
            String listItem = editText.getText().toString();

            todo = new TODO();

            //set value of edittext to todo Object
            todo.setTodoText(listItem);
            //set urgent switch value to todo object
            todo.setUrgent(swUrgernt.isChecked());

            //add todo object to arraylist
            elements.add(todo);

            //refresh list
            myAdapter.notifyDataSetChanged();

            //Clear textview and uncheck switch
            editText.setText("");
            swUrgernt.setChecked(false);
        });

        //Cast listview from xml to java
        ListView myList = findViewById(R.id.myList);

        //Step 10
        //set Adapter to listview
        myList.setAdapter(myAdapter = new MyListAdapter());

        //Step 11
        //code when user click on list item
        myList.setOnItemClickListener((parent, view, pos, id) -> {
//            elements.remove(pos);
//            myAdapter.notifyDataSetChanged();
        });

        //Step 12
        //When user clicks on list item for long press we display dialog
        myList.setOnItemLongClickListener((p, b, pos, id) -> {


            View newView = getLayoutInflater().inflate(R.layout.todo, null);
            TextView tView = newView.findViewById(R.id.textGoesHere);

            //alert dialog that asks user to deleter item
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            //tView.setText(elements.get(pos).getTodoText());

            //set values of alert dialog
            alertDialogBuilder.setTitle("Do you want to delete this?")

                    //What is the message:
                    .setMessage("The selected row is: " + pos +
                            "\n " + elements.get(pos).todoText)

                    //what the Yes button does:
                    .setPositiveButton("Yes", (click, arg) -> {
                        elements.remove(elements.get(pos));
                        myAdapter.notifyDataSetChanged();
                    })

                    //What the No button does:
                    .setNegativeButton("No", (click, arg) -> {
                    })
                    //.setView(newView)
                    //Show the dialog
                    .create().show();
            return true;
        });
    }

    //Step 13
    //Create MyListAdapter from BaseAdapter
    private class MyListAdapter extends BaseAdapter {

        //Creating methods for elements

        public int getCount() {
            return elements.size();
        }

        public TODO getItem(int position) {
            return elements.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        //Step 14
        //In this method we will set Listview items and background color in case of urgent
        public View getView(int position, View old, ViewGroup parent) {

            View newView = old;
            LayoutInflater inflater = getLayoutInflater();

            //make a new row:
            if (newView == null) {
                newView = inflater.inflate(R.layout.todo, parent, false);
            }

            //set what the text should be for this row:
            TextView tView = newView.findViewById(R.id.textGoesHere);
            tView.setText(getItem(position).todoText);

            //Checking if item is urgent or not
            if (getItem(position).isUrgent) {

                //if item is urgent we change background color of list item to red
                tView.setBackgroundColor(Color.RED);

                //and text color to white
                tView.setTextColor(Color.WHITE);
            } else {
                //otherwise keep default colors
                tView.setBackgroundColor(Color.WHITE);
                tView.setTextColor(Color.GRAY);
            }

            //return it to be put in the table
            return newView;
        }
    }
}


//Step 5
//Create TODO class with variables and methods
class TODO {

    //Step 6 Create variables
    //String variable for todoTexts
    String todoText;
    //Boolean variable for specify todo item is urgent or not
    boolean isUrgent;

    //Step 7
    //Create Getter and Setter methods
    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }
}