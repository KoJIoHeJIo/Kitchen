package com.example.kitchenv12;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;


public class MainActivity extends AppCompatActivity {
    private static final String MY_SETTINGS = "first";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Заставка приложения
        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        // Надпись кухня
        final TextView logo = (TextView) findViewById(R.id.logo);
        // Сообщения в процессе авторизации и регистрации
        final TextView message = (TextView) findViewById(R.id.massage);

        // Поле ввода пароля
        final AutoCompleteTextView pass = (AutoCompleteTextView) findViewById(R.id.pass);
        // Поле ввода логина
        final AutoCompleteTextView login = (AutoCompleteTextView) findViewById(R.id.login);

       // Заставка на 2 секунды
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { ib.setVisibility(View.INVISIBLE);

            }
        }, 2000);

        // Проверяем, первый ли раз запущенна программа
        final SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        boolean passon = sp.getBoolean("passon", false);

        if (!hasVisited) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.putString("login", "admin");
            e.putString("password", "admin");
            e.commit();
             // Подтверждение действия
            e.apply();
            // Принудительный процесс регистрации
           // registration(sp,message,login,pass);
            LogIn(sp, message, login, pass);
        }
            else{
            // Начинаем процесс авторизации
                LogIn(sp, message, login, pass);
                }

    }


    // Вход в основную часть программы
    public void comeIn ()
    {
    // Таймер отображения заставки, по окончании делает ее невидимой и начинает авторизацию
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Создание нового активити и передача ему управления
                    Intent intent = new Intent(MainActivity.this, i.layout.Main2Activity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Приложение КухнЯ: Ошибка." + "\r\n" + "Переход на Главное меню не возможен" + "\r\n" + e, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }, 1500);
    }



    ///////////////////////////////////////////////////working

public void LogIn (final SharedPreferences sp, final TextView message,final AutoCompleteTextView login,final AutoCompleteTextView pass )
{
    String name = sp.getString("login",new String());
    final String password = sp.getString("password",new String());
    // Если имя пользователя не указано
   if(name.equals("")){registration(sp,message,login,pass);}
    // Если имя указанно и пароль не указан
    else if(!name.equals("") && password.equals(""))
   {
       message.setVisibility(View.VISIBLE);
       login.setText(name);
       message.setText("Здравствуй " + name);
       comeIn();
   }
    else if(!name.equals("") && !password.equals(""))
   {
       message.setVisibility(View.VISIBLE);
       pass.setVisibility(View.VISIBLE);
       login.setVisibility(View.VISIBLE);
       login.setText(name);
       login.setEnabled(false);
       message.setText("Введите пароль");
       pass.setOnKeyListener(new View.OnKeyListener() {
                                          public boolean onKey(View v, int keyCode, KeyEvent event) {
                                              if (event.getAction() == KeyEvent.ACTION_DOWN)
                                              {
                                                  if (keyCode == KeyEvent.KEYCODE_ENTER)
                                                  {
                                                      // Проверка на пустой пароль
                                                      if (pass.getText().length() == 0) {
                                                          message.setText("Пароль не может быть пустым");
                                                          return false;
                                                      }
                                                      String vrpass = sp.getString("password",new String());
                                                      // Проверка верности пароля
                                                      if (pass.getText().toString().equals(vrpass)) {
                                                          String userName = pass.getText().toString();
                                                          message.setText("Здравствуй " + userName);
                                                          comeIn();
                                                          return true;
                                                      }
                                                      // Пароль неверный
                                                      else
                                                      {
                                                          message.setText("Пароль невереный!");
                                                          return false;
                                                      }

                                                  }
                                                      return false;
                                              }
                                              return false;
                                          }
                                      }
       );

   }

}


    // Регистрация нового пользователя
    private void registration(final SharedPreferences sp,final TextView message,final AutoCompleteTextView login,final AutoCompleteTextView pass )
    {
        message.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        final Button goon = (Button) findViewById(R.id.goon);
        goon.setEnabled(true);
        goon.setVisibility(View.VISIBLE);
        String hello = "Введите логин и, при необходимости, пароль ";
        message.setText(hello);
        login.isFocusable();
        // Проверка на нажатие кнопки Авторизации
        goon.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(login.getText().toString().equals("")){message.setText("Вы забыли ввести Логин.");}
                         else {
                        SharedPreferences.Editor e = sp.edit();
                        e.putString("login", login.getText().toString());
                        if(!pass.getText().toString().equals("")){e.putString("password", pass.getText().toString());}
                        e.apply();
                        e.commit();
                    comeIn();
                    }
                }
                return false;
            }
        });
            }

            ///////////////////////////////////////////////////////////////working
//Метод записи в файл

        }







