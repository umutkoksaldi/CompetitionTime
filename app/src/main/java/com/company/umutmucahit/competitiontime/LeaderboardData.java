package com.company.umutmucahit.competitiontime;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * LeaderBoardDate - Holds the data for the leaderboard.
 * Created by UmutMucahit on 5/11/2015.
 */
public class LeaderboardData
{
    ArrayList<LItem> leaderboard;

    // Initializes the arraylist by default.
    public LeaderboardData()
    {
        leaderboard = new ArrayList<>();
    }

    // Sets the leaderboard arraylist, reading each row in the database and sorts the arraylist.
    public void setLeaderboard(SQLiteDatabase db)
    {
        Cursor c = db.rawQuery("select * from person", null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                leaderboard.add(new LItem(c.getString(c.getColumnIndex("name")), Integer.parseInt(c.getString(c.getColumnIndex("score")))));
                c.moveToNext();
            }
        }
        c.close();
        Collections.sort(leaderboard, Collections.reverseOrder());
    }

    // Returns a string representation of the leaderboard.
    public String getLeaderboard ()
    {
        String result = "";
        for (LItem i : leaderboard)
        {
            result += i.toString() + "\n";
        }
        return result;
    }

    // Insert given parameter to both the database and the leaderboard, sort the leaderboard again.
    public void updateLeaderboard (String name, int score, SQLiteDatabase db)
    {
        ContentValues c = new ContentValues();
        c.put("name", name);
        c.put("score", score +"");
        db.insert("person", null, c);
        leaderboard.add(new LItem(name, score));
        Collections.sort(leaderboard, Collections.reverseOrder());
    }

    // Inner class that combines the name and the score of the user. With toString and getName methods.
    public class LItem implements Comparable {
        String name;
        int score;

        public LItem(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + "\t\t\t\t" + score;
        }

        public String getName() {return name;}

        // Compares the score of the object in order to sort them.
        @Override
        public int compareTo(Object o) {
            LItem another = (LItem) o;
            if (score > another.score)
                return 1;
            else if (score == another.score)
                return 0;
            else if (score < another.score)
                return -1;
            return -27;
        }
    }
}
