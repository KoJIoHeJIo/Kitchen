package i.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.example.LoginAndComeIn.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    /**
     * Переход на окно список покупок
     * @param view - сигнал нажатия на кнопку
     */
    public void onClicktolist (View view)
    {
        //Эти 2 строки создают новое активити и передают ему управление
        Intent intent = new Intent(MenuActivity.this, WorkListActivity.class);
        intent.putExtra("header", "SpisokPokupok");
        intent.putExtra("title", "Список покупок");
        startActivity(intent);
    }

    /**
     * Переход на окно "о программе"
     * @param view - сигнал нажатия на кнопку
     */
    public void onClicktoabout (View view)
    {
        Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на окно списка продуктов
     * @param view - сигнал нажатия на кнопку
     */
    public void onClicktofood (View view)
    {
        try {
            Intent intent = new Intent(MenuActivity.this, SpisokProduktov.class);
            startActivity(intent);
        }catch (Exception e){}
    }

    /**
     * Переход на окно рецептов
     * @param view - сигнал нажатия на кнопку
     */
    public void onClicktobook (View view)
    {
        try {
            Intent intent = new Intent(MenuActivity.this, ShowCookBook.class);
            startActivity(intent);
        }catch (Exception e){}
    }
}
