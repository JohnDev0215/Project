package b.h.d;

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
public class g {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f2374a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public IconCompat f2375b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final j[] f2376c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final j[] f2377d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2378e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2379f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f2380g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f2381h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        @Deprecated
        public int f2382i;
        public CharSequence j;
        public PendingIntent k;

        public PendingIntent a() {
            return this.k;
        }

        public boolean b() {
            return this.f2378e;
        }

        public j[] c() {
            return this.f2377d;
        }

        public Bundle d() {
            return this.f2374a;
        }

        public IconCompat e() {
            int i2;
            if (this.f2375b == null && (i2 = this.f2382i) != 0) {
                this.f2375b = IconCompat.b(null, "", i2);
            }
            return this.f2375b;
        }

        public j[] f() {
            return this.f2376c;
        }

        public int g() {
            return this.f2380g;
        }

        public boolean h() {
            return this.f2379f;
        }

        public CharSequence i() {
            return this.j;
        }

        public boolean j() {
            return this.f2381h;
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
        public Context f2383a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f2386d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f2387e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PendingIntent f2388f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PendingIntent f2389g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public RemoteViews f2390h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Bitmap f2391i;
        public CharSequence j;
        public int k;
        public int l;
        public boolean n;
        public d o;
        public CharSequence p;
        public CharSequence[] q;
        public int r;
        public int s;
        public boolean t;
        public String u;
        public boolean v;
        public String w;
        public boolean y;
        public boolean z;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f2384b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList<a> f2385c = new ArrayList<>();
        public boolean m = true;
        public boolean x = false;
        public int C = 0;
        public int D = 0;
        public int J = 0;
        public int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f2383a = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.l = 0;
            this.R = new ArrayList<>();
            this.N = true;
        }

        public static CharSequence c(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public Notification a() {
            return new h(this).b();
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public c d(CharSequence charSequence) {
            this.f2387e = c(charSequence);
            return this;
        }

        public c e(CharSequence charSequence) {
            this.f2386d = c(charSequence);
            return this;
        }

        public c f(int i2) {
            Notification notification = this.P;
            notification.defaults = i2;
            if ((i2 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public c g(int i2) {
            this.l = i2;
            return this;
        }

        public c h(CharSequence charSequence) {
            this.P.tickerText = c(charSequence);
            return this;
        }

        public c i(long j) {
            this.P.when = j;
            return this;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static abstract class d {
    }

    public static Bundle a(Notification notification) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            return notification.extras;
        }
        if (i2 >= 16) {
            return i.c(notification);
        }
        return null;
    }
}
