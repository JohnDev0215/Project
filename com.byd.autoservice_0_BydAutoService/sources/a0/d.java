package a0;

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
public class d {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f20a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public IconCompat f21b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final g[] f22c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final g[] f23d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f24e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f25f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f26g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f27h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        @Deprecated
        public int f28i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public CharSequence f29j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public PendingIntent f30k;

        public PendingIntent a() {
            return this.f30k;
        }

        public boolean b() {
            return this.f24e;
        }

        public g[] c() {
            return this.f23d;
        }

        public Bundle d() {
            return this.f20a;
        }

        public IconCompat e() {
            int i3;
            if (this.f21b == null && (i3 = this.f28i) != 0) {
                this.f21b = IconCompat.b(null, "", i3);
            }
            return this.f21b;
        }

        public g[] f() {
            return this.f22c;
        }

        public int g() {
            return this.f26g;
        }

        public boolean h() {
            return this.f25f;
        }

        public CharSequence i() {
            return this.f29j;
        }

        public boolean j() {
            return this.f27h;
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
        public Context f31a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f34d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f35e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PendingIntent f36f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PendingIntent f37g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public RemoteViews f38h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Bitmap f39i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public CharSequence f40j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f41k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f42l;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f44n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public AbstractC0001d f45o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public CharSequence f46p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public CharSequence[] f47q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f48r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f49s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f50t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public String f51u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public boolean f52v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public String f53w;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public boolean f55y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public boolean f56z;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f32b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList<a> f33c = new ArrayList<>();

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f43m = true;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public boolean f54x = false;
        public int C = 0;
        public int D = 0;
        public int J = 0;
        public int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f31a = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.f42l = 0;
            this.R = new ArrayList<>();
            this.N = true;
        }

        public static CharSequence c(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public Notification a() {
            return new e(this).b();
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public c d(CharSequence charSequence) {
            this.f35e = c(charSequence);
            return this;
        }

        public c e(CharSequence charSequence) {
            this.f34d = c(charSequence);
            return this;
        }

        public c f(int i3) {
            Notification notification = this.P;
            notification.defaults = i3;
            if ((i3 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public c g(int i3) {
            this.f42l = i3;
            return this;
        }

        public c h(CharSequence charSequence) {
            this.P.tickerText = c(charSequence);
            return this;
        }

        public c i(long j3) {
            this.P.when = j3;
            return this;
        }
    }

    /* JADX INFO: renamed from: a0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NotificationCompat.java */
    public static abstract class AbstractC0001d {
    }

    public static Bundle a(Notification notification) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            return notification.extras;
        }
        if (i3 >= 16) {
            return f.c(notification);
        }
        return null;
    }
}
