package com.byd.carsettings.ui.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class MessageCenterActivity extends AppCompatActivity implements View.OnClickListener {
    public final void b() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("001", "channel_name", 4));
        }
        notificationManager.notify(1, new NotificationCompat.Builder(this).setContentTitle("This is content title").setContentText("hello world!").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MessageCenterActivity.class), 0)).setAutoCancel(true).setChannelId("001").setDefaults(-1).setPriority(2).build());
    }

    public void e() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public final void f() {
        findViewById(R.id.tv_msg_center).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_msg_center) {
            b();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
        super.setContentView(R.layout.activity_message_center);
        f();
    }
}
