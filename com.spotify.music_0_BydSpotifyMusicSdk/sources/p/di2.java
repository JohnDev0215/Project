package p;

import android.app.Notification;
import android.os.Build;
import androidx.work.impl.foreground.SystemForegroundService;

/* JADX INFO: loaded from: classes.dex */
public final class di2 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Notification e;
    public final /* synthetic */ int k;
    public final /* synthetic */ SystemForegroundService l;

    public di2(SystemForegroundService systemForegroundService, int i, Notification notification, int i2) {
        this.l = systemForegroundService;
        this.c = i;
        this.e = notification;
        this.k = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = Build.VERSION.SDK_INT;
        int i2 = this.k;
        Notification notification = this.e;
        int i3 = this.c;
        SystemForegroundService systemForegroundService = this.l;
        if (i >= 31) {
            gi2.a(systemForegroundService, i3, notification, i2);
        } else if (i >= 29) {
            fi2.a(systemForegroundService, i3, notification, i2);
        } else {
            systemForegroundService.startForeground(i3, notification);
        }
    }
}
