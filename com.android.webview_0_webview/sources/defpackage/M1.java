package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class M1 {
    public Context a;
    public CharSequence d;
    public CharSequence e;
    public PendingIntent f;
    public int g;
    public N1 i;
    public int j;
    public int k;
    public boolean l;
    public Bundle n;
    public String q;
    public Notification t;

    @Deprecated
    public ArrayList u;
    public ArrayList b = new ArrayList();
    public ArrayList c = new ArrayList();
    public boolean h = true;
    public boolean m = false;
    public int o = 0;
    public int p = 0;
    public int r = 0;
    public int s = 0;

    public M1(Context context, String str) {
        Notification notification = new Notification();
        this.t = notification;
        this.a = context;
        this.q = str;
        notification.when = System.currentTimeMillis();
        this.t.audioStreamType = -1;
        this.g = 0;
        this.u = new ArrayList();
    }

    public static CharSequence c(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public M1 a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.b.add(new K1(i, charSequence, pendingIntent));
        return this;
    }

    public Notification b() {
        Notification notificationBuild;
        O1 o1 = new O1(this);
        N1 n1 = o1.b.i;
        if (n1 != null) {
            new Notification.BigTextStyle(o1.a).setBigContentTitle(null).bigText(((L1) n1).c);
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 26 && i < 24) {
            o1.a.setExtras(o1.f);
            notificationBuild = o1.a.build();
        } else {
            notificationBuild = o1.a.build();
        }
        Objects.requireNonNull(o1.b);
        if (n1 != null) {
            Objects.requireNonNull(o1.b.i);
        }
        if (n1 != null) {
            Bundle bundle = notificationBuild.extras;
        }
        return notificationBuild;
    }

    public final void d(int i, boolean z) {
        if (z) {
            Notification notification = this.t;
            notification.flags = i | notification.flags;
        } else {
            Notification notification2 = this.t;
            notification2.flags = (~i) & notification2.flags;
        }
    }

    public M1 e(N1 n1) {
        if (this.i != n1) {
            this.i = n1;
            if (n1.a != this) {
                n1.a = this;
                e(n1);
            }
        }
        return this;
    }
}
