package defpackage;

import android.os.Bundle;
import androidx.core.app.NotificationCompat$Builder;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class no1 {
    public NotificationCompat$Builder a;
    public CharSequence b;
    public boolean c = false;

    public void a(Bundle bundle) {
        if (this.c) {
            bundle.putCharSequence("android.summaryText", this.b);
        }
        String strC = c();
        if (strC != null) {
            bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", strC);
        }
    }

    public abstract void b(oo1 oo1Var);

    public String c() {
        return null;
    }

    public final void f(NotificationCompat$Builder notificationCompat$Builder) {
        if (this.a != notificationCompat$Builder) {
            this.a = notificationCompat$Builder;
            if (notificationCompat$Builder != null) {
                notificationCompat$Builder.d(this);
            }
        }
    }

    public void d() {
    }

    public void e() {
    }
}
