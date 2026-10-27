package p;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class hk {
    public final bi1 a;
    public final Service b;
    public int c;

    public hk(Service service, bi1 bi1Var) {
        this.b = service;
        this.a = bi1Var;
        NotificationManager notificationManager = (NotificationManager) service.getSystemService("notification");
        if (notificationManager != null && notificationManager.getNotificationChannel("SpotifyForegroundNotification") == null) {
            notificationManager.createNotificationChannel(new NotificationChannel("SpotifyForegroundNotification", service.getString(R.string.notification_channel_playback_name), 2));
        }
    }

    public final Notification a() {
        Service service = this.b;
        xn1 xn1Var = new xn1();
        xn1Var.b = new ArrayList();
        xn1Var.c = new ArrayList();
        xn1Var.d = new ArrayList();
        xn1Var.g = true;
        Notification notification = new Notification();
        xn1Var.l = notification;
        xn1Var.a = service;
        xn1Var.j = "SpotifyForegroundNotification";
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        xn1Var.f = 0;
        xn1Var.m = new ArrayList();
        xn1Var.k = true;
        String string = this.b.getString(R.string.notification_playback_title);
        String strSubSequence = string;
        if (string != null) {
            int length = string.length();
            strSubSequence = string;
            if (length > 5120) {
                strSubSequence = string.subSequence(0, 5120);
            }
        }
        xn1Var.e = strSubSequence;
        notification.icon = R.drawable.icn_notification;
        yn1 yn1Var = new yn1();
        yn1Var.b = this.a.a.c;
        xn1Var.a(yn1Var);
        xn1Var.f = -1;
        jo1 jo1Var = new jo1(xn1Var);
        zn1 zn1Var = ((xn1) jo1Var.d).h;
        if (zn1Var != null) {
            yn1 yn1Var2 = (yn1) zn1Var;
            if (Build.VERSION.SDK_INT >= 34) {
                vn1.d((Notification.Builder) jo1Var.c, vn1.b(wn1.a(vn1.a(), null, 0, null, Boolean.FALSE), null, yn1Var2.b));
            } else {
                vn1.d((Notification.Builder) jo1Var.c, vn1.b(vn1.a(), null, yn1Var2.b));
            }
        }
        Notification notificationA = ao1.a((Notification.Builder) jo1Var.c);
        ((xn1) jo1Var.d).getClass();
        if (zn1Var != null) {
            ((xn1) jo1Var.d).h.getClass();
        }
        if (zn1Var != null) {
            Bundle bundle = notificationA.extras;
        }
        return notificationA;
    }

    public final void b() {
        int i = this.c + 1;
        this.c = i;
        Service service = this.b;
        if (i == 1) {
            try {
                service.startForeground(42, a());
                service.startService(new Intent(service, service.getClass()));
            } catch (Exception e) {
                lc.o("Did not start foreground service", e);
                c();
            }
        }
    }

    public final void c() {
        int i = this.c;
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        this.c = i2;
        if (i2 == 0) {
            Service service = this.b;
            service.stopSelf();
            service.stopForeground(true);
        }
    }
}
