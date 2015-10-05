package com.example.yishuo.c301as1;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by yishuo on 9/23/15.
 */
public class FileReadWrite{
    public int modeNum; // 0: default; 1: single player mode; 2: two player mode 3: three player mode; 4: four player mode

    private static final String FSINGLE = "fsingle.sav";
    private static final String FTwoPlayers = "f2.sav";
    private static final String FThreePlayers = "f3.sav";
    private static final String FFourPlayers = "f4.sav";

    public ArrayList<Float> singletime = new ArrayList<Float>();
    public ArrayList<Integer> twoPlayersTime = new ArrayList<Integer>();
    public ArrayList<Integer> threePlayersTime = new ArrayList<Integer>();
    public ArrayList<Integer> fourPlayersTime = new ArrayList<Integer>();

    public int sizesingle = singletime.size();
    public int sizetwo = twoPlayersTime.size();
    public int sizethree = threePlayersTime.size();
    public int sizefour = fourPlayersTime.size();

    public void saveFile(Context context) {
        // single player
        try {
            FileOutputStream fos = context.openFileOutput(FSINGLE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(singletime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // two players
        try {
            FileOutputStream fos = context.openFileOutput(FTwoPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(twoPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // three players
        try {
            FileOutputStream fos = context.openFileOutput(FThreePlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(threePlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // four players
        try {
            FileOutputStream fos = context.openFileOutput(FFourPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(fourPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        sizesingle = singletime.size();
        sizetwo = twoPlayersTime.size();
        sizethree = threePlayersTime.size();
        sizefour = fourPlayersTime.size();
    }


    public void loadFile(Context context) {
        // single player
        try {
            FileInputStream fis = context.openFileInput(FSINGLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Float>>() {
            }.getType();
            singletime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            singletime = new ArrayList<Float>();
        }

        // two players
        try {
            FileInputStream fis = context.openFileInput(FTwoPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            twoPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            twoPlayersTime = new ArrayList<Integer>();
            twoPlayersTime.add(0);
            twoPlayersTime.add(0);
        }

        // three palyers
        try {
            FileInputStream fis = context.openFileInput(FThreePlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            threePlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            threePlayersTime = new ArrayList<Integer>();
            threePlayersTime.add(0);
            threePlayersTime.add(0);
            threePlayersTime.add(0);
        }

        // fourplayers
        try {
            FileInputStream fis = context.openFileInput(FFourPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            fourPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            fourPlayersTime = new ArrayList<Integer>();
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
        }
        sizesingle = singletime.size();
        sizetwo = twoPlayersTime.size();
        sizethree = threePlayersTime.size();
        sizefour = fourPlayersTime.size();
    }

    public void clearFile(Context context) {
        // clear the ArrayList and save it to the corresponding file
        int i;
        singletime = new ArrayList<Float>();
        twoPlayersTime = new ArrayList<Integer>();
        for (i = 0; i < 2; i++) {
            twoPlayersTime.add(0);
        }
        threePlayersTime = new ArrayList<Integer>();
        for (i = 0; i < 3; i++) {
            threePlayersTime.add(0);
        }
        fourPlayersTime = new ArrayList<Integer>();
        for(i = 0; i < 4; i++) {
            fourPlayersTime.add(0);
        }
        saveFile(context);
    }
}
