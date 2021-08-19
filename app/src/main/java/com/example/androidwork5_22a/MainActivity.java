package com.example.androidwork5_22a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static boolean memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button lognBtn = findViewById(R.id.loginBtn);
        Button regBtn = findViewById(R.id.registerBtn);
        lognBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText login = findViewById(R.id.enterlogin);
        EditText pass = findViewById(R.id.enterpassword);
        String lg = login.getText().toString();
        String ps = pass.getText().toString();
        CheckBox checkBoxMemory = findViewById(R.id.checkBox);
        switch (v.getId()) {
            case R.id.loginBtn:
                if (lg.equals("")) {
                    Toast.makeText(this, "Заполните поле логин", Toast.LENGTH_SHORT).show();
                } else if (ps.equals("")) {
                    Toast.makeText(this, "Заполните поле пароль", Toast.LENGTH_SHORT).show();
                } else {
                    String lgps = "";
                    if (checkBoxMemory.isChecked()) {
                        lgps = FileUtils.load(getApplicationContext());
                    } else {
                        lgps = FileUtils.readFile(getApplicationContext());
                    }
                    String[] lgpas = lgps.split(";");
                    String logn = "";
                    String pasw = "";
                    try {
                        logn = lgpas[0];
                        pasw = lgpas[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }

                    if (logn.equals(lg) && pasw.equals(ps)) {
                        Toast.makeText(this, "Вы вошли в учётную запись", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Вы ввели неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    }


                }

                break;
            case R.id.registerBtn:
                if (lg.equals("")) {
                    Toast.makeText(this, "Заполните поле логин", Toast.LENGTH_SHORT).show();
                } else if (ps.equals("")) {
                    Toast.makeText(this, "Заполните поле пароль", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkBoxMemory.isChecked()) {
                        FileUtils.write(getApplicationContext(), lg, ps);
                    } else {
                        FileUtils.writeFile(getApplicationContext(), lg, ps);
                    }
                    login.setText(null);
                    pass.setText(null);
                    Toast.makeText(this, "Вы зарегестрировались", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    public static void saveCheck(SharedPreferences.Editor myEditor) {
        myEditor.putBoolean("memory", memory);
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    private void save() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", memory);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }
}

