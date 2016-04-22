package i.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitchenv12.R;

public class SpisokProduktov extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisok_produktov);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Я работаю!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Продукты");
        final TextView logo = (TextView) findViewById(R.id.textView3);
        logo.setText("Продукты");


        ListView listView = (ListView)findViewById(R.id.listView);
        String[] catnames = getResources().getStringArray(R.array.SistemSpProd);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, catnames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long arg3) {
                switch (position) {
                    // Холодильник
                    case 0:
                        Intent intent = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent.putExtra("header", "holodilnik");
                        intent.putExtra("title", "Холодильник");
                        startActivity(intent);
                        break;
                    // Морозильник
                    case 1:
                        Intent intent1 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent1.putExtra("header", "morozilnik");
                        intent1.putExtra("title", "Морозильник");
                        startActivity(intent1);
                        break;
                    // Кладовка
                    case 2:
                        Intent intent2 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent2.putExtra("header", "kladovka");
                        intent2.putExtra("title", "Кладовка");
                        startActivity(intent2);
                        break;
                    // Шкаф
                    case 3:
                        Intent intent3 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent3.putExtra("header", "shkaf");
                        intent3.putExtra("title", "Шкаф");
                        startActivity(intent3);
                        break;
                    // Ящик
                    case 4:
                        Intent intent4 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent4.putExtra("header", "Yaschik");
                        intent4.putExtra("title", "Ящик");
                        startActivity(intent4);
                        break;
                    // Полка
                    case 5:
                        Intent intent5 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent5.putExtra("header", "Polka");
                        intent5.putExtra("title", "Полка");
                        startActivity(intent5);
                        break;
                    // Тумба
                    case 6:
                        Intent intent6 = new Intent(SpisokProduktov.this, Main3Activity.class);
                        // Передача данных между окнами (ключ-значение)
                        intent6.putExtra("header", "Tumba");
                        intent6.putExtra("title", "Тумба");
                        startActivity(intent6);
                        break;
                    case 7:
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Ты тоже няшка! ^_^", Toast.LENGTH_LONG);
                        toast.show();
                        break;
                }
            }
        });

    }
}

