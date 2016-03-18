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

    // Строка, которую мы выводим в список
    String[] mSign = {"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева",
            "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_spinner_item, mSign);
try {

    listView.setAdapter(adapter);
}
catch (Exception e)
{
    ProgressDialog.show(Main3Activity.this, "Ошибка:"+e, "Подождите, операция выполняется").show();
}

    }

}



