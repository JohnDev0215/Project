package com.byd.auto_photo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.util.e;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class ApiService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        w.c("Photo ApiService onBind");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        w.c("Photo ApiService onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getBooleanExtra("close_album", false)) {
            String stringExtra = intent.getStringExtra("pkgName");
            w.c("======close_album=======>" + stringExtra);
            if (!e.I(stringExtra)) {
                return super.onStartCommand(intent, i, i2);
            }
            w.c("Photo ApiService receive close album message");
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.byd.ACTION_ALBUM_CLOSE"));
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        w.c("Photo ApiService onUnbind");
        return true;
    }
}
