package i.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kitchenv12.R;

public class Main3Activity extends AppCompatActivity{

    // Источник списка
    String[] mSign = {"Хлеб белый", "Молоко 3.2% жирности", "Мясо", "Яйца битые", "Спички самовоспламеняющиеся", "Курица, грудка",
            "Масло", "Халва", "Вино красное, полусладкое 1781 года", "Шашлык свинной", "Вода Норинга 1л", "Сайра в консерве"};



        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            String[] shoplist = getResources().getStringArray(R.array.shopping_list);

        //Основной код списка
        setContentView(R.layout.activity_main3);
        ListView listView = (ListView) findViewById(R.id.listView);

try {
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_list_item_1, shoplist);
    listView.setAdapter(adapter);
}
catch (Exception e)
{ Toast toast = Toast.makeText(getApplicationContext(),
        "Приложение КухнЯ говорит Ошибка."+ "\r\n"+"Программа мертва, мне жаль..."  , Toast.LENGTH_LONG);
    toast.show();
}
    }
}



