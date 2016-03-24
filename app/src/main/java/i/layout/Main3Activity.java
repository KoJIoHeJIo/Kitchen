package i.layout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.kitchenv12.R;
import java.util.ArrayList;
import java.util.Arrays;

public class Main3Activity extends AppCompatActivity{

        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Источник списка
            final String[] member = getResources().getStringArray(R.array.shopping_list);
            final ArrayList<String> shoplist = new ArrayList<String>();
            shoplist.addAll(Arrays.asList(member));

            //Основной код списка
            setContentView(R.layout.activity_main3);
            ListView listView = (ListView) findViewById(R.id.listView);
            final EditText editText = (EditText) findViewById(R.id.editText);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_list_item_1, shoplist);
            listView.setAdapter(adapter);
            try {

                //Обработчик нажатия на клавишу энтер и добавления в список
                editText.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN)
                            if (keyCode == KeyEvent.KEYCODE_ENTER && editText.getText().length() != 0) {
                                shoplist.add(0, editText.getText().toString());
                                adapter.notifyDataSetChanged();
                                editText.setText("");
                                //Убирает клавиатуру, временный вариант
                                editText.setEnabled(false);
                                editText.setEnabled(true);
                                return true;
                            }
                        return false;
                    }
                });
            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Приложение КухнЯ: Ошибка." + "\r\n" + "Добавление нового элемента невозможно.", Toast.LENGTH_LONG);
                toast.show();
            }

                try{
                //Обработчик долгого нажатия на список
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position,
                                                   long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();

                        openQuitDialog(adapter, selectedItem,editText);
                        return true;
                    }
                });

            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Приложение КухнЯ говорит Ошибка." + "\r\n" + "Программа мертва, мне жаль...", Toast.LENGTH_LONG);
                toast.show();
            }
        }

    //Метод для диалога перед удалением элемента списка
            private void openQuitDialog(final ArrayAdapter<String> adapter,final String selectedItem,final EditText editText) {
                AlertDialog.Builder quitDialog = new AlertDialog.Builder(Main3Activity.this);
                quitDialog.setTitle("Выбран : " + selectedItem);

                quitDialog.setPositiveButton("Удалить ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        adapter.remove(selectedItem);
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
    }




