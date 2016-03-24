package com.example.kitchenv12;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.textView2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    //Эти 2 строки создают новое активити и передают ему управление
                    Intent intent = new Intent(MainActivity.this, i.layout.Main2Activity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Приложение КухнЯ: Ошибка." + "\r\n" + "Переход на Главное меню не возможен" + "\r\n" + e, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }, 2000);
    }

    public void onClick (View view)
    {
        Intent intent = new Intent(MainActivity.this, i.layout.Main2Activity.class);
        startActivity(intent);
    }


}







