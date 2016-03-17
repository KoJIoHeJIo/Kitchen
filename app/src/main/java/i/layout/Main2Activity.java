package i.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
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
        Intent intent = new Intent(Main2Activity.this, i.layout.Main3Activity.class);
        startActivity(intent);
    }


}
