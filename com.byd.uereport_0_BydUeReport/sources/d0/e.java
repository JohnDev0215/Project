package d0;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f4283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public IconCompat f4284b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h[] f4285c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final h[] f4286d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4287e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f4288f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f4289g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f4290h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        @Deprecated
        public int f4291i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public CharSequence f4292j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public PendingIntent f4293k;

        public PendingIntent a() {
            return this.f4293k;
        }

        public boolean b() {
            return this.f4287e;
        }

        public h[] c() {
            return this.f4286d;
        }

        public Bundle d() {
            return this.f4283a;
        }

        public IconCompat e() {
            int i8;
            if (this.f4284b == null && (i8 = this.f4291i) != 0) {
                this.f4284b = IconCompat.b(null, "", i8);
            }
            return this.f4284b;
        }

        public h[] f() {
            return this.f4285c;
        }

        public int g() {
            return this.f4289g;
        }

        public boolean h() {
            return this.f4288f;
        }

        public CharSequence i() {
            return this.f4292j;
        }

        public boolean j() {
            return this.f4290h;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static final class b {
        public static Notification.BubbleMetadata a(b bVar) {
            if (bVar == null) {
                return null;
            }
            new Notification.BubbleMetadata.Builder();
            throw null;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class c {
        public String A;
        public Bundle B;
        public Notification E;
        public RemoteViews F;
        public RemoteViews G;
        public RemoteViews H;
        public String I;
        public String K;
        public long L;
        public boolean N;
        public b O;
        public Notification P;
        public boolean Q;

        @Deprecated
        public ArrayList<String> R;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f4294a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f4297d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f4298e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PendingIntent f4299f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PendingIntent f4300g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public RemoteViews f4301h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Bitmap f4302i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public CharSequence f4303j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f4304k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f4305l;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f4307n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public d f4308o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public CharSequence f4309p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public CharSequence[] f4310q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f4311r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f4312s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f4313t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public String f4314u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public boolean f4315v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public String f4316w;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public boolean f4318y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public boolean f4319z;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f4295b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList<a> f4296c = new ArrayList<>();

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f4306m = true;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public boolean f4317x = false;
        public int C = 0;
        public int D = 0;
        public int J = 0;
        public int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f4294a = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.f4305l = 0;
            this.R = new ArrayList<>();
            this.N = true;
        }

        public static CharSequence c(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public Notification a() {
            return new f(this).b();
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public c d(boolean z7) {
            i(16, z7);
            return this;
        }

        public c e(PendingIntent pendingIntent) {
            this.f4299f = pendingIntent;
            return this;
        }

        public c f(CharSequence charSequence) {
            this.f4298e = c(charSequence);
            return this;
        }

        public c g(CharSequence charSequence) {
            this.f4297d = c(charSequence);
            return this;
        }

        public c h(int i8) {
            Notification notification = this.P;
            notification.defaults = i8;
            if ((i8 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public final void i(int i8, boolean z7) {
            if (z7) {
                Notification notification = this.P;
                notification.flags = i8 | notification.flags;
            } else {
                Notification notification2 = this.P;
                notification2.flags = (~i8) & notification2.flags;
            }
        }

        public c j(int i8) {
            this.f4305l = i8;
            return this;
        }

        public c k(int i8) {
            this.P.icon = i8;
            return this;
        }

        public c l(CharSequence charSequence) {
            this.P.tickerText = c(charSequence);
            return this;
        }

        public c m(long j8) {
            this.P.when = j8;
            return this;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static abstract class d {
    }

    public static Bundle a(Notification notification) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 19) {
            return notification.extras;
        }
        if (i8 >= 16) {
            return g.c(notification);
        }
        return null;
    }
}
