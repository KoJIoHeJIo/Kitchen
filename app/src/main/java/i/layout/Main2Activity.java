package i.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.kitchenv12.AboutActivity;
import com.example.kitchenv12.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onClick (View view)
    {
        //Эти 2 строки создают новое активити и передают ему управление
        Intent intent = new Intent(Main2Activity.this, i.layout.Main3Activity.class);
        intent.putExtra("header", "SpisokPokupok");
        intent.putExtra("title", "Список покупок");
        startActivity(intent);
    }
    public void onClickabout (View view)
    {
        Intent intent = new Intent(Main2Activity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void onClickprod (View view)
    {
        try {
            Intent intent = new Intent(Main2Activity.this, SpisokProduktov.class);
            startActivity(intent);
        }catch (Exception e){}
    }
    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            this.finish();
        else
            Toast.makeText(getBaseContext(), "Нажмите \"Назад\" еще раз для выхода. ",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
