package com.example.kitchenv12;

import android.content.Context;
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
        final TextView tv = (TextView) findViewById(R.id.textView2);

        // Проверяем, первый ли раз запущенна программа
        final SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        // Текст приветствия
        final EditText hi = (EditText) findViewById(R.id.Hello);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();
             // Подтверждение действия
            e.apply();

            registration(sp);
        }
else{

    }
        LogIn(sp,hi);
        //registration(sp);
    }


    // Вход в основную часть программы
    public void comeIn ()
    {// Таймер отображения заставки, по окончании делает ее невидимой и начинает авторизацию
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
        }, 1000);
    }

    ///////////////////////////////////////////////////working

public void LogIn (final SharedPreferences sp,EditText hi)
{
    String name = sp.getString("userName",new String());
    hi.setText(name);
    // Если имя пользователя не указано
   if(name.equals("")){registration(sp);}
    // Если имя указанно
    else if(!name.equals(""))
   {
       hi.setText("Здравствуй "+name);
       comeIn();
   }

}
    // Регистрация нового пользователя
    private void registration(final SharedPreferences sp )
    { // Строка Объявления
        final EditText hi = (EditText) findViewById(R.id.Hello);
        final EditText userNameLogin = (EditText) findViewById(R.id.name);

        String hello = "Введите ваше имя: ";
        hi.setText(hello);
        userNameLogin.isFocusable();

        userNameLogin.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {if(event.getAction() == KeyEvent.ACTION_DOWN)
            {if (keyCode == KeyEvent.KEYCODE_ENTER && userNameLogin.getText().length() != 0)
            {
                // сохраняем текст, введенный до нажатия Enter в переменную
                String userName = userNameLogin.getText().toString();
                userNameLogin.setText("");
                hi.setText("Здравствуй " + userName);
                SharedPreferences.Editor e = sp.edit();
                e.putString("userName", userName);
                e.commit();
                comeIn();
                return true;
            }
                return false;
            }
                return false;
            }
        }
        );
            }

            ///////////////////////////////////////////////////////////////working
//Метод записи в файл
            private void savefile(String name, String textin) {
                File file = new File(textin, name);
            }

            //Метод загрузки списка из файла
            private String loadfile(String name) {
                SharedPreferences prefs = getSharedPreferences(name, MODE_PRIVATE);
                String file = prefs.toString();
                return file;
            }

        }







