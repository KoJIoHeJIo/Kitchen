package i.layout;

import android.app.ListActivity;
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

public class Main3Activity extends ListActivity {
    // Строка, которую мы выводим в список
    String[] mSign =  {"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева",
            "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mSign);
        setListAdapter(mAdapter);
    }

    public void onListItemClick (ListView parent, View v, int position, long id) {
        Toast.makeText(getApplicationContext(), mSign[position], Toast.LENGTH_SHORT).show();
    }
}
