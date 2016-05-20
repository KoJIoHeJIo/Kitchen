package com.example.LoginAndComeIn;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Класс входа в программу
 */
public class LoginActivity extends AppCompatActivity {
    private static final String MY_SETTINGS = "first";
    private ImageButton ib;
    private TextView logo;
    private TextView message;
    private AutoCompleteTextView pass;
    private AutoCompleteTextView login;
    private Button goon;
    private SharedPreferences sp;

    /**
     * Создание окна входа в программу
     * @param savedInstanceState - Сохранение
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Заставка приложения
        ib = (ImageButton) findViewById(R.id.imageButton);
        logo = (TextView) findViewById(R.id.logo);
        message = (TextView) findViewById(R.id.massage);
        pass = (AutoCompleteTextView) findViewById(R.id.pass);
        login = (AutoCompleteTextView) findViewById(R.id.login);
        goon = (Button) findViewById(R.id.goon);
        sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);

        // Заставка на 2 секунды
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ib.setVisibility(View.INVISIBLE);

                // Проверяем, первый ли раз запущенна программа
                boolean hasVisited = sp.getBoolean("hasVisited", false);
                // Параметр для меню управления паролем.
                @SuppressWarnings("UnusedAssignment") boolean passon = sp.getBoolean("passon", false);

                if (!hasVisited) {
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("hasVisited", true);
                    e.putString("login", "");
                    e.putString("password", "");
                    e.apply();
                    // Подтверждение действия

                }
                LogIn();
            }
        }, 2000);

    }

    /**
     * Метод авторизации
     */
    private void LogIn() {
        String name = sp.getString("login", "");
        final String password = sp.getString("password", "");
        // Если имя пользователя не указано
        if (name.equals("")) {
            registration();
        }
        // Если имя указанно и пароль не указан
        else if (!name.equals("") && password.equals("")) {
            message.setVisibility(View.VISIBLE);
            login.setText(name);
            message.setText("Здравствуй " + name);
            comeIn();
        } else if (!name.equals("") && !password.equals("")) {
            message.setVisibility(View.VISIBLE);
            pass.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            goon.setVisibility(View.VISIBLE);
            goon.setText("Войти");
            goon.setEnabled(true);
            login.setText(name);
            login.setEnabled(false);
            message.setText("Введите пароль");
            goon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    checkLogin();
                }
            });
        }
    }

    /**
     * Регистрация нового пользователя
     */
    private void registration() {
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
        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegistration();
            }
        });
    }

    /**
     * Вход в основную часть программы
      */
    private void comeIn() {
        ib.setVisibility(View.VISIBLE);
        pass.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        goon.setVisibility(View.GONE);
        // Таймер отображения заставки, по окончании делает ее
        // невидимой и начинает авторизацию
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Создание нового активити и передача ему управления
                    Intent intent = new Intent(LoginActivity.this, i.layout.MenuActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Приложение КухнЯ: Ошибка." + "\r\n" +
                                    "Переход на Главное меню не возможен" +
                                    "\r\n" + e, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }, 1500);
    }

    /**
     * Отметка о регистрации нового пользователя
     * @return - сигнал об успехе\провале регистрации
     */
        private boolean checkRegistration(){
        if (login.getText().toString().equals("")) {
            message.setText("Вы забыли ввести Логин.");
            return false;
        } else {
            SharedPreferences.Editor e = sp.edit();
            e.putString("login", login.getText().toString());
            if (!pass.getText().toString().equals("")) {
                e.putString("password", pass.getText().toString());
            }
            e.apply();
            comeIn();
            return true;
        }
    }

    /**
     * Отметка о входе в программу
     * @return - сигнал успеха\провала авторизации
     */
    private boolean checkLogin(){
        // Проверка на пустой пароль
        if (pass.getText().length() == 0) {
            message.setText("Пароль не может быть пустым");
            return false;
        }
        String vrpass = sp.getString("password", "");
        // Проверка верности пароля
        if (pass.getText().toString().equals(vrpass)) {
            message.setText("Добро пожаловать " + login.getText().toString());
            comeIn();
            return true;
        }
        // Пароль неверный
        else {
            message.setText("Пароль невереный!");
            return false;
        }
    }

    /**
     * Возврат инструмента записи в файл
     * @return
     */
    public SharedPreferences getSp() {
        return sp;
    }

    /**
     * Возврат указателя на форму ввода логина
     * @return
     */
    public AutoCompleteTextView getLogin() {
        return login;
    }

    /**
     * Возврат указателя на форму ввода пароля
     * @return
     */
    public AutoCompleteTextView getPass() {
        return pass;
    }
}