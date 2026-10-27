package com.byd.globalnotification;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import b.a.a.a.a;
import b.c.a.f.u;
import b.c.a.h.b;
import com.byd.globalnotification.services.StorageService;

/* JADX INFO: loaded from: classes.dex */
public class NotificationApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        if (u.f1756d == null) {
            try {
                u uVar = new u();
                uVar.registerAsSystemService(this, new ComponentName(getPackageName(), getClass().getCanonicalName()), -1);
                b.b("service.registerAsSystemService success in NotificationApplication service = " + uVar);
            } catch (Throwable th) {
                StringBuilder sbD = a.d("service.registerAsSystemService failed : ");
                sbD.append(th.getMessage());
                b.a(sbD.toString());
            }
        }
        startService(new Intent(this, (Class<?>) StorageService.class));
        b.b(" NotificationApplication --> onCreate:==packTime=2025-04-21 14:07");
    }
}
