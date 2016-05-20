package i.layout;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.LoginAndComeIn.R;

public class ShowCookBook extends AppCompatActivity {
    private static final int NOTIFY_ID = 109;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cook_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Напоминание установленно", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                PushUpMessage();
            }
        });
    }
    private void PushUpMessage()
    {
        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context, ShowCookBook.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.mainico)
                        // Большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.cake))
                        // Текст в строке состояния
                .setTicker("Ты просил напомнить тебе...!")
                        // Время срабатывания напоминания
                .setWhen(System.currentTimeMillis()+15000)
                .setAutoCancel(true)
                        // Заголовок уведомления
                .setContentTitle("Напоминашка")
                        // Текст уведомления
                .setContentText("Время приготовить вкусное блюдо!");

        // Notification notification = builder.getNotification(); // до API 16
        try {
            Notification notification;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.build();
                NotificationManager notificationManager = (NotificationManager) context
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFY_ID, notification);
            }

        }
        catch (Exception ignored){}

    }
}
