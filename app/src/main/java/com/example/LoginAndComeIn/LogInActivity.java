package com.example.LoginAndComeIn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import i.layout.MenuActivity;


public class LogInActivity extends AppCompatActivity {
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
        // Кнопка регистрации
        final Button goon = (Button) findViewById(R.id.goon);

        // Заставка на 2 секунды
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { ib.setVisibility(View.INVISIBLE);

        // Проверяем, первый ли раз запущенна программа
        final SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        // Параметр для меню управления паролем.
        boolean passon = sp.getBoolean("passon", false);


        if (!hasVisited) {
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.putString("login", "");
            e.putString("password", "");
             // Подтверждение действия
            e.apply();
            // Принудительный процесс регистрации

        }
                LogIn(sp, message, login, pass,goon);

            }
        }, 2000);

    }

    /**
     * Проводит авторизацию,
     * проверяет наличие логина и пароля,
     * сверяет полученный пароль с установленным.
     * @param sp - Инструмент записи данны в память устройства
     * @param message - Указатель на область вывода сообщений
     * @param login -  текстовое поле для ввода логина
     * @param pass - текстовое поле для ввода пароля
     * @param GoOnButton - Кнопка действия
     */
public void LogIn (final SharedPreferences sp, final TextView message,final AutoCompleteTextView login,final AutoCompleteTextView pass, final Button GoOnButton )
{
    final String name = sp.getString("login",new String());
    final String password = sp.getString("password",new String());
    // Если имя пользователя не указано
   if(name.equals("")){
       Registration(sp, message, login, pass);}
    // Если имя указанно и пароль не указан
    else if(!name.equals("") && password.equals(""))
   {
       message.setVisibility(View.VISIBLE);
       login.setText(name);
       message.setText("@string/welcome" + name);//TEST
       ComeInMenu(login, pass, GoOnButton);
   }
    else if(!name.equals("") && !password.equals(""))
   {
       message.setVisibility(View.VISIBLE);
       pass.setVisibility(View.VISIBLE);
       login.setVisibility(View.VISIBLE);
       GoOnButton.setVisibility(View.VISIBLE);
       GoOnButton.setText("Войти");
       GoOnButton.setEnabled(true);
       login.setText(name);
       login.setEnabled(false);
       message.setText("Введите пароль");
       GoOnButton.setOnTouchListener(new View.OnTouchListener() {

                                         @Override
                                         public boolean onTouch(View v, MotionEvent event) {
                                             if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                                 // Проверка на пустой пароль
                                                 if (pass.getText().length() == 0) {
                                                     message.setText("Пароль не может быть пустым");
                                                     return false;
                                                 }
                                                 String vrpass = sp.getString("password", new String());
                                                 // Проверка верности пароля
                                                 if (pass.getText().toString().equals(vrpass)) {
                                                     String name = login.getText().toString();
                                                             message.setText("@string/welcome" + name);
                                                     ComeInMenu(login, pass, GoOnButton);
                                                     return true;
                                                 }
                                                 // Пароль неверный
                                                 else {
                                                     message.setText("Пароль невереный!");
                                                     return false;
                                                 }

                                             }
                                             return false;
                                         }

                                     }

       );

   }

}

    /**
     * Регистрация нового пользователя,
     * запись данных нового пользователя на устройство
     * @param sp - Инструмент записи настройки
     * @param message - Указатель на область вывода сообщений
     * @param login - Текстовое поле для ввода логина
     * @param pass - Текстовое поле для ввода пароля
     */
    private void Registration(final SharedPreferences sp, final TextView message, final AutoCompleteTextView login, final AutoCompleteTextView pass)
    {
        message.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        final Button goon = (Button) findViewById(R.id.goon);
        goon.setEnabled(true);
        goon.setVisibility(View.VISIBLE);
        String hello = "Введите ваш новый логин и пароль ";
        message.setText(hello);
        login.isFocusable();
        // Проверка на нажатие кнопки Авторизации
        goon.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(login.getText().toString().equals("")){message.setText("Вы забыли ввести Логин.");}
                         else {
                        String name = login.getText().toString();
                        message.setText("Добро пожаловать " + name );
                        SharedPreferences.Editor e = sp.edit();
                        e.putString("login", login.getText().toString());
                        if(!pass.getText().toString().equals("")){e.putString("password", pass.getText().toString());}
                        e.apply();
                    ComeInMenu(login, pass, goon);
                    }
                }
                return false;
            }
        });
            }

    /**
     * Преход в основное меню,
     * после прохождения регистрации или авторизации
     * @param login - Текстовое поле для ввода логина
     * @param pass - Текстовое поле для ввода пароля
     * @param goon - Кнопка действия
     */
    public void ComeInMenu(final AutoCompleteTextView login, final AutoCompleteTextView pass, final Button goon)
    {

        final ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
        ib.setVisibility(View.VISIBLE);
        pass.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        goon.setVisibility(View.GONE);
        // Таймер отображения заставки, по окончании делает ее невидимой и начинает авторизацию
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Создание нового активити и передача ему управления
                    Intent intent = new Intent(LogInActivity.this, MenuActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Приложение КухнЯ: Ошибка." + "\r\n" + "Переход на Главное меню не возможен" + "\r\n" + e, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }, 1500);
    }


        }







