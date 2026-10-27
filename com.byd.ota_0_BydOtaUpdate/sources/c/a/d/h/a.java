package c.a.d.h;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import b.f.e.e;
import com.byd.ota.R;
import com.byd.ota.broadcast.NotificationBroadcastReceiver;

/* JADX INFO: compiled from: NotificationUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2496a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f2497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e.c f2499d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public NotificationManager f2500e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public NotificationBroadcastReceiver f2501f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2502g;

    public a() {
        throw new UnsupportedOperationException("NotificationUtil: U can't new me");
    }

    public static a e(Context context) {
        if (f2497b == null) {
            synchronized (a.class) {
                if (f2497b == null) {
                    f2497b = new a(context);
                }
            }
        }
        return f2497b;
    }

    public void a() {
        c.a.b.d.e.c(f2496a, "cancelNotification");
        NotificationBroadcastReceiver notificationBroadcastReceiver = this.f2501f;
        if (notificationBroadcastReceiver != null) {
            this.f2498c.unregisterReceiver(notificationBroadcastReceiver);
            this.f2501f = null;
        }
        this.f2500e.cancel(0);
    }

    public PendingIntent b(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent("vendor.intent.action.NOTIFICATION_CONTENT_INTENT"), 0);
    }

    public PendingIntent c(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent("vendor.intent.action.NOTIFICATION_DELETE_INTENT"), 0);
    }

    public void d(int i2, int i3) {
        c.a.b.d.e.c(f2496a, "createNotification->curIndex = " + i2 + ", maxIndex = " + i3);
        try {
            NotificationChannel notificationChannel = new NotificationChannel("download_package_channel", "download package", 2);
            notificationChannel.setDescription("download upgrade package files");
            this.f2500e.createNotificationChannel(notificationChannel);
            e.c cVarM = new e.c(this.f2498c, "download_package_channel").h(String.format(this.f2498c.getResources().getString(R.string.notification_content_title), Integer.valueOf(i2 + 1), Integer.valueOf(i3))).g(String.format(this.f2498c.getResources().getString(R.string.notification_content_text), 0, 0)).n(this.f2498c.getApplicationInfo().icon).k(BitmapFactory.decodeResource(this.f2498c.getResources(), this.f2498c.getApplicationInfo().icon)).e("progress").l(true).f(b(this.f2498c)).i(c(this.f2498c)).m(100, 0, false);
            this.f2499d = cVarM;
            this.f2500e.notify(0, cVarM.a());
        } catch (Exception e2) {
            c.a.b.d.e.b(f2496a, "createNotification->e = " + e2.toString());
        }
    }

    public void f(int i2, int i3, long j, long j2) {
        String str = f2496a;
        c.a.b.d.e.b(str, "updateNotification->progress = " + j + ", contentLength = " + j2);
        if (this.f2499d == null) {
            c.a.b.d.e.b(str, "updateNotification->builder is null");
            return;
        }
        if (System.currentTimeMillis() - this.f2502g > 1000) {
            this.f2502g = System.currentTimeMillis();
            if (j >= 0) {
                this.f2499d.h(String.format(this.f2498c.getResources().getString(R.string.notification_content_title), Integer.valueOf(i2 + 1), Integer.valueOf(i3)));
                this.f2499d.g(String.format(this.f2498c.getResources().getString(R.string.notification_content_text), Long.valueOf(j), Long.valueOf(j2)));
                this.f2499d.m((int) j2, (int) j, false);
                c.a.b.d.e.b(str, "updateNotification->notify");
                this.f2500e.notify(0, this.f2499d.a());
            }
        }
    }

    public a(Context context) {
        this.f2498c = context;
        this.f2500e = (NotificationManager) context.getSystemService("notification");
    }
}
