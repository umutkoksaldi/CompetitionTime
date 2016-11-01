package com.company.umutmucahit.competitiontime;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Signin - Signs the user in, has an sqlite database.
 * Created by UmutMucahit on 5/10/2015.
 */

public class SignUp extends ActionBarActivity implements View.OnClickListener {

    Button sign, log;
    EditText name, pass, passagain;
    SQLiteDatabase db = null;
    boolean done = false;
    SQLiteDatabase leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Init gui components.
        sign = (Button) findViewById(R.id.signIn);
        log = (Button) findViewById(R.id.log);
        name = (EditText) findViewById(R.id.name1);
        pass = (EditText) findViewById(R.id.pass1);
        passagain = (EditText) findViewById(R.id.passagain);

        sign.setOnClickListener(this);
        log.setOnClickListener(this);

        // Open the database and create a table if it does not exists.
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists login(name varchar,password varchar,unlocks varchar,coins varchar,bil varchar,geo varchar,gen varchar,hist varchar,prog varchar,math varchar,sports varchar,welcome varchar,round varchar,bilc varchar,progc varchar,genc varchar,geoc varchar,sportsc varchar,mathc varchar,histc varchar,cred varchar,flag varchar)");
    }

    @Override
    public void onClick(View v) {
        // Check if the user wants to log in again.
        if (v == log) {
            finish();
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Login"));
        }
        // Check for the user input, if the two passwords are correct and all inputs are valid.
        // Give toast error messages if they are not.
        else {
            String n = name.getText().toString();
            String p = pass.getText().toString();
            String pa = passagain.getText().toString();
            String q = "000000";

            if (n == null || n.equals(""))
                show("Invalid username!");
            else if (p == null || p.equals("") || pa == null || pa.equals(""))
                show("Invalid passowrd");
            else if (!p.equals(pa))
                show("Two passwords do not match");
            else {

                Cursor c = db.rawQuery("select * from login where name='" + n + "'", null);
                c.moveToFirst();
                if (c.getCount() > 0)
                    show("The account with that username already exists");
                    // If no errors are thrown, log the user into the database and create a player item, then launch into main menu.
                    // Close the database after. Use default check values for the first time user.
                else {
                    db.execSQL("insert into login values('" + n + "','" + p + "','" + q + "','" + 0 + "','" + 1 + "','" + 1 + "','" + 1 + "','" + 1 + "','" + 1 + "','" + 1 + "','" + 1 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','nothing')");
                    TitleScreen.user = new Player(n, p);
                    db.close();
                    startActivity(new Intent("com.company.umutmucahit.competitiontime.MainMenu"));
                    finish();
                }
            }
        }
    }

            // Stop and start the music player on pause and resume.
            @Override
            protected void onPause () {
                super.onPause();
                db.close();
                TitleScreen.player.pause();
            }

            @Override
            protected void onResume () {
                super.onResume();
                if (TitleScreen.music)
                    TitleScreen.player.start();
            }

        public void show (String str)
        {
            Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_sign_in, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
