package com.example.androidwork5_22a;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class FileUtils {

    static final String LOG = "myLogs";


    public static void writeFile(Context context, String datal, String datap) {
        try  {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("login", MODE_PRIVATE)));
            writer.write(datal + "\n" + datap);
            writer.close();
            Log.d(LOG, "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public String readFile(Context context) {
        String line = "";
        String result = "";
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("login")));
            while ((line = reader.readLine()) != null) {
                result = result + line + ";";
                Log.d(LOG, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String load(Context context) {
        String line = "";
        String result = "";
        if (isExternalStorageWritable()) {

            File file = new File(context.getApplicationContext().getExternalFilesDir(
                    null), "login.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file));) {
                while ((line = br.readLine()) != null) {
                    result = (result + line + ";");
                    Log.d(LOG, line + "c sd");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void write(Context context, String datal, String datap) {
        if (isExternalStorageWritable()) {
            File file = new File(context.getExternalFilesDir(null), "login.txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(datal + "\n" + datap);
                bw.close();
                Log.d(LOG, "Файл записан на sd");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}


