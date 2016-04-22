package i.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.example.kitchenv12.AboutActivity;
import com.example.kitchenv12.MainActivity;
import com.example.kitchenv12.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void onClick (View view)
    {
        //Эти 2 строки создают новое активити и передают ему управление
        Intent intent = new Intent(MenuActivity.this, WorkListActivity.class);
        intent.putExtra("header", "SpisokPokupok");
        intent.putExtra("title", "Список покупок");
        startActivity(intent);
    }
    public void onClickabout (View view)
    {
        Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void onClickprod (View view)
    {
        try {
            Intent intent = new Intent(MenuActivity.this, SpisokProduktov.class);
            startActivity(intent);
        }catch (Exception e){}
    }
}
