package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.no1;
import defpackage.oo1;
import defpackage.s02;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NotificationCompat$Builder {
    public final Notification A;
    public boolean B;
    public Icon C;

    @Deprecated
    public final ArrayList<String> D;
    public final Context a;
    public final ArrayList<androidx.core.app.a> b;
    public final ArrayList<s02> c;
    public final ArrayList<androidx.core.app.a> d;
    public CharSequence e;
    public CharSequence f;
    public PendingIntent g;
    public IconCompat h;
    public int i;
    public boolean j;
    public no1 k;
    public CharSequence l;
    public int m;
    public int n;
    public boolean o;
    public String p;
    public boolean q;
    public boolean r;
    public String s;
    public Bundle t;
    public int u;
    public int v;
    public Notification w;
    public final String x;
    public long y;
    public final boolean z;

    public static class a {
        public static AudioAttributes a(AudioAttributes.Builder builder) {
            return builder.build();
        }

        public static AudioAttributes.Builder b() {
            return new AudioAttributes.Builder();
        }

        public static AudioAttributes.Builder c(AudioAttributes.Builder builder, int i) {
            return builder.setContentType(i);
        }

        public static AudioAttributes.Builder d(AudioAttributes.Builder builder, int i) {
            return builder.setUsage(i);
        }
    }

    public NotificationCompat$Builder(Context context, String str) {
        this.b = new ArrayList<>();
        this.c = new ArrayList<>();
        this.d = new ArrayList<>();
        this.j = true;
        this.r = false;
        this.u = 0;
        this.v = 0;
        Notification notification = new Notification();
        this.A = notification;
        this.a = context;
        this.x = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.i = 0;
        this.D = new ArrayList<>();
        this.z = true;
    }

    public static CharSequence b(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public final Notification a() {
        Bundle bundle;
        oo1 oo1Var = new oo1(this);
        NotificationCompat$Builder notificationCompat$Builder = oo1Var.c;
        no1 no1Var = notificationCompat$Builder.k;
        if (no1Var != null) {
            no1Var.b(oo1Var);
        }
        if (no1Var != null) {
            no1Var.e();
        }
        Notification notificationBuild = oo1Var.b.build();
        if (no1Var != null) {
            no1Var.d();
        }
        if (no1Var != null) {
            notificationCompat$Builder.k.getClass();
        }
        if (no1Var != null && (bundle = notificationBuild.extras) != null) {
            no1Var.a(bundle);
        }
        return notificationBuild;
    }

    public final void c(int i, boolean z) {
        Notification notification = this.A;
        if (z) {
            notification.flags = i | notification.flags;
        } else {
            notification.flags = (~i) & notification.flags;
        }
    }

    public final void d(no1 no1Var) {
        if (this.k != no1Var) {
            this.k = no1Var;
            if (no1Var != null) {
                no1Var.f(this);
            }
        }
    }

    @Deprecated
    public NotificationCompat$Builder(Context context) {
        this(context, null);
    }
}
