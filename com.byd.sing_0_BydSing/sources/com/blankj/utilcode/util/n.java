package com.blankj.utilcode.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.core.app.e;
import com.blankj.utilcode.util.w;

/* JADX INFO: compiled from: NotificationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: compiled from: NotificationUtils.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f2614a = new a(w.a().getPackageName(), w.a().getPackageName(), 3);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private NotificationChannel f2615b;

        public a(String str, CharSequence charSequence, int i) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.f2615b = new NotificationChannel(str, charSequence, i);
            }
        }

        public NotificationChannel b() {
            return this.f2615b;
        }
    }

    public static Notification a(a aVar, w.b<e.c> bVar) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            ((NotificationManager) w.a().getSystemService("notification")).createNotificationChannel(aVar.b());
        }
        e.c cVar = new e.c(w.a());
        if (i >= 26) {
            cVar.c(aVar.f2615b.getId());
        }
        if (bVar != null) {
            bVar.accept(cVar);
        }
        return cVar.a();
    }
}
