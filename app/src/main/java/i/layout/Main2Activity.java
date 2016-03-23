package i.layout;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ProgressBar;

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
        startActivity(intent);
    }
    public void onClickabout (View view)
    { double alf = 0.01;
        Toast toast = Toast.makeText(getApplicationContext(),
                "Приложение КухнЯ"+ "\r\n"+"Версия приложения:" + alf+"v" , Toast.LENGTH_SHORT);
        toast.show();

    }


}
