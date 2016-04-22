package i.layout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitchenv12.AboutActivity;
import com.example.kitchenv12.MainActivity;
import com.example.kitchenv12.R;
import java.util.ArrayList;
import java.util.Arrays;

public class Main3Activity extends AppCompatActivity{

        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
try {
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
    toolbar.setTitle("Список продуктов");
}catch (Exception e)
{
    exept(e,"припиливании тулбара.");
}


            // Принимаем заголовок (имя файла хранения)
            final String header = getIntent().getStringExtra("header");

            // Устанавливаем заготовок
            Main3Activity.this.setTitle(getIntent().getStringExtra("title"));
            final TextView logo = (TextView) findViewById(R.id.textView3);
            logo.setText(getIntent().getStringExtra("title"));


            // Загрузка списка из файла
            final ArrayList<String> list = loadArrayList(header); // загружаем

            // Определение переменных
            ListView listView = (ListView) findViewById(R.id.listView);
            final EditText editText = (EditText) findViewById(R.id.editText);

            // Адаптер для отображения списка в listView
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            // Обработчик нажатия на клавиатуре Enter и созранения нового элемента списка
            try {
                editText.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN)
                            if (keyCode == KeyEvent.KEYCODE_ENTER && editText.getText().length() != 0) {
                                editText.requestFocus();
                                list.add(0, editText.getText().toString());
                                adapter.notifyDataSetChanged();
                                editText.setText("");

                                // Убирает клавиатуру, временный вариант
                                editText.setEnabled(false);
                                editText.setEnabled(true);

                                // Сохранения списка с новым элементом в файл
                                try {
                                    saveArrayList(header, list); // сохраняем
                                } catch (Exception e)
                                {exept(e,"сохранении списка в файл.");}
                                        return true;
                            }
                        return false;
                    }
                });
            } catch (Exception e) {
                exept(e,"создании новой записи.");
            }

            // Функциональное меню в списке ListView
            try {
                // Обработчик долгого нажатия
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position,
                                                   long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();
                        // Открывает диалог с выбором функций
                        openQuitDialog(adapter, selectedItem, editText, list);
                        return true;
                    }
                });

            } catch (Exception e) {
                exept(e,"открытии функций списка.");
            }
        }


            // Метод вызова диалога, с выбором функций для элемента списка
            private void openQuitDialog(final ArrayAdapter<String> adapter,final String selectedItem,final EditText editText, final ArrayList<String> list) {
                AlertDialog.Builder quitDialog = new AlertDialog.Builder(Main3Activity.this);
                quitDialog.setTitle("Выбран элемент : " + selectedItem);

                quitDialog.setPositiveButton("Удалить ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        adapter.remove(selectedItem);
                        list.remove(selectedItem);
                        // Сохранения списка после удаления
                        try {
                            saveArrayList("Spisok_pokupok", list); // сохраняем
                        } catch (Exception e)
                        {exept(e,"сохранении списка в файл.");}
                    }
                });

                quitDialog.setNegativeButton("Редактировать ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        editText.setText(selectedItem);
                        adapter.remove(selectedItem);
                        editText.requestFocus();
                    }
                });

                quitDialog.show();
            }

    // Сохранение списка в файл
    private boolean saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
        return true;
    }
// Загрузка списка из файла
    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }
    // Сообщение об ошибке (код ошибки, имя действия)
    public void exept(Exception e, String doit)
    {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Приложение КухнЯ: Ошибка при " +doit+ "\r\n" + "Код ошибки: "+e, Toast.LENGTH_LONG);
        toast.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {if(getIntent().getStringExtra("title").equals("Список покупок"))
            {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        return true;
    }
}





