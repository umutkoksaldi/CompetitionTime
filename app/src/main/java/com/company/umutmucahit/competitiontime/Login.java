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
 * Login activity- Manages the login made by the user, includes a SQLite database.
 * Created by UmutMucahit on 5/10/2015.
 */

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button login, sign;
    EditText name, pass;
    SQLiteDatabase db = null;
    SQLiteDatabase leaderboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init gui elements.
        login = (Button) findViewById(R.id.login);
        sign = (Button) findViewById(R.id.signIn);
        name = (EditText) findViewById(R.id.name1);
        pass = (EditText) findViewById(R.id.password);
        login.setOnClickListener(this);
        sign.setOnClickListener(this);

        // Open the database.
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        //db.execSQL("create table if not exists login(name varchar,password varchar,flag varchar)");
    }

    @Override
    public void onClick(View v) {
        // Check for blank and incorrect entry errors and display Toast messages accordingly..
        if (v == sign)
        {
            startActivity(new Intent("com.company.umutmucahit.competitiontime.SignUp"));
            finish();
        }
        else if (v == login) {
            String n = name.getText().toString();
            String p = pass.getText().toString();

            if (n == null || n.equals(""))
                show("Username cannot be blank!");
            else if (p == null || p.equals(""))
                show("Password cannot be blank!");
            else
            {
                try {
                    // If no errors occured, log the user in, creating a new player object.
                    // Then close the database and the cursor.
                    Cursor c = db.rawQuery("select * from login where name='" + n + "' and password='" + p + "'", null);
                    c.moveToFirst();
                    if (c.getCount() > 0) {
                        startActivity(new Intent("com.company.umutmucahit.competitiontime.MainMenu"));
                        TitleScreen.user = new Player(n, p);
                        db.close();
                        finish();
                    } else
                        show("Incorrect username and password combination");
                    c.close();
                } catch (SQLiteException e)
                {
                    show("Incorrect username and password combination   ");
                }
            }
        }
    }

    // Stop and start the music player on pause and resume.
    @Override
    protected void onPause() {
        super.onPause();
        db.close();
        TitleScreen.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TitleScreen.music)
            TitleScreen.player.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void show(String str)
    {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
}
