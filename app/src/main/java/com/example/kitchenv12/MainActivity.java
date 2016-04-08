package com.example.kitchenv12;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Заставка приложения
        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        //Надпись кухня
        final TextView tv = (TextView) findViewById(R.id.textView2);

        //Таймер
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    ib.setVisibility(ImageButton.INVISIBLE);
                    LogIn();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Приложение КухнЯ: Ошибка." + "\r\n" + "Переход на Главное меню не возможен" + "\r\n" + e, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }, 1000);
    }

    public void comeIn ()
    {
        //Эти 2 строки создают новое активити и передают ему управление
        Intent intent = new Intent(MainActivity.this, i.layout.Main2Activity.class);
        startActivity(intent);
    }
    ///////////////////////////////////////////////////work
public void LogIn ()
{
//Ввод пароля
    final EditText pass = (EditText) findViewById(R.id.pass);
    String names = "userspass";
    final String name = loadfile(names); // загружаем
    String passw = "password";
    final String pasw = loadfile(passw); // загружаем
    if (name!=null)
    {pass.setVisibility(TextView.VISIBLE);
        do {
                pass.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN)
                            if (keyCode == KeyEvent.KEYCODE_ENTER && pass.getText().length() != 0) {
                                pass.requestFocus();
                                if(pass.getText().toString()==pasw)
                                {comeIn();}
                                else { Toast toast = Toast.makeText(getApplicationContext(),
                                        "Пароль введен неверно." + "\r\n" + "Попробуйте еще раз"
                                        , Toast.LENGTH_LONG);
                                    toast.show();}
                            }
                        return false;
                    }
                });}while (1!=1);





    }
}
    ///////////////////////////////////////////////////////////////work
//Метод записи в файл
    private void saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
    }
    //Метод загрузки списка из файла
    private String loadfile(String name) {
        SharedPreferences prefs = getSharedPreferences(name, MODE_PRIVATE);
        String file = prefs.toString();
        return file;
    }
}







