package i.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
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
            final  String[] member = getResources().getStringArray(R.array.shopping_list);
            final ArrayList <String> shoplist = new ArrayList<String>();
            shoplist.addAll(Arrays.asList(member));

                    //Основной код списка
                    setContentView(R.layout.activity_main3);
        ListView listView = (ListView) findViewById(R.id.listView);
          final  EditText editText = (EditText) findViewById(R.id.editText);


try {
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_list_item_1, shoplist);
    listView.setAdapter(adapter);
//Обработчик нажатия на клавишу энтер и добавления в список
    editText.setOnKeyListener(new View.OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            //
            if (event.getAction() == KeyEvent.ACTION_DOWN)
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    shoplist.add(0, editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    return true;
                }
            return false;
        }
    });
}
catch (Exception e)
{ Toast toast = Toast.makeText(getApplicationContext(),
        "Приложение КухнЯ говорит Ошибка."+ "\r\n"+"Программа мертва, мне жаль..."  , Toast.LENGTH_LONG);
    toast.show();
}
    }
}



