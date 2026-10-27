package com.byd.globalnotification.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import b.c.a.h.e.b;
import com.byd.globalnotification.R;

/* JADX INFO: loaded from: classes.dex */
public class StorageService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public StorageManager f2346a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f2347b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StorageEventListener f2348c = new a();

    public class a extends StorageEventListener {
        public a() {
        }

        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            b bVar;
            Context applicationContext;
            int i3;
            super.onVolumeStateChanged(volumeInfo, i, i2);
            if (i2 == 2) {
                b.c.a.h.b.b("StorageEventListener=state_mounted");
                bVar = StorageService.this.f2347b;
                applicationContext = b.c.a.h.d.b.a().getApplicationContext();
                i3 = R.string.media_mounted;
            } else {
                if (i2 != 5) {
                    return;
                }
                b.c.a.h.b.b("StorageEventListener=state_ejecting");
                bVar = StorageService.this.f2347b;
                applicationContext = b.c.a.h.d.b.a().getApplicationContext();
                i3 = R.string.media_removed;
            }
            bVar.f1797f = applicationContext.getString(i3);
            StorageService.this.f2347b.a();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Context applicationContext = b.c.a.h.d.b.a().getApplicationContext();
        if (this.f2347b != null) {
            b.c.a.h.b.b("getSdcardNotification,notification.cancel()");
            b bVar = this.f2347b;
            NotificationManager notificationManager = bVar.f1794c;
            if (notificationManager != null) {
                notificationManager.cancel(bVar.h);
            }
        }
        b bVar2 = new b(applicationContext);
        this.f2347b = bVar2;
        bVar2.f1796e = applicationContext.getString(R.string.app_name);
        this.f2347b = this.f2347b;
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        this.f2346a = storageManager;
        storageManager.registerListener(this.f2348c);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f2346a.unregisterListener(this.f2348c);
        super.onDestroy();
    }
}
