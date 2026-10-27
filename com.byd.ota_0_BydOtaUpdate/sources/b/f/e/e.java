package b.f.e;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
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
        public final Bundle f1578a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public IconCompat f1579b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h[] f1580c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final h[] f1581d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1582e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1583f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f1584g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f1585h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        @Deprecated
        public int f1586i;
        public CharSequence j;
        public PendingIntent k;

        public PendingIntent a() {
            return this.k;
        }

        public boolean b() {
            return this.f1582e;
        }

        public h[] c() {
            return this.f1581d;
        }

        public Bundle d() {
            return this.f1578a;
        }

        public IconCompat e() {
            int i2;
            if (this.f1579b == null && (i2 = this.f1586i) != 0) {
                this.f1579b = IconCompat.b(null, "", i2);
            }
            return this.f1579b;
        }

        public h[] f() {
            return this.f1580c;
        }

        public int g() {
            return this.f1584g;
        }

        public boolean h() {
            return this.f1583f;
        }

        public CharSequence i() {
            return this.j;
        }

        public boolean j() {
            return this.f1585h;
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
        public Context f1587a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f1590d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f1591e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public PendingIntent f1592f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PendingIntent f1593g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public RemoteViews f1594h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Bitmap f1595i;
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
        public ArrayList<a> f1588b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList<a> f1589c = new ArrayList<>();
        public boolean m = true;
        public boolean x = false;
        public int C = 0;
        public int D = 0;
        public int J = 0;
        public int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f1587a = context;
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
            return new f(this).b();
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public final Bitmap d(Bitmap bitmap) {
            if (bitmap == null || Build.VERSION.SDK_INT >= 27) {
                return bitmap;
            }
            Resources resources = this.f1587a.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(b.f.b.f1540b);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(b.f.b.f1539a);
            if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                return bitmap;
            }
            double dMin = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
            return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * dMin), (int) Math.ceil(((double) bitmap.getHeight()) * dMin), true);
        }

        public c e(String str) {
            this.A = str;
            return this;
        }

        public c f(PendingIntent pendingIntent) {
            this.f1592f = pendingIntent;
            return this;
        }

        public c g(CharSequence charSequence) {
            this.f1591e = c(charSequence);
            return this;
        }

        public c h(CharSequence charSequence) {
            this.f1590d = c(charSequence);
            return this;
        }

        public c i(PendingIntent pendingIntent) {
            this.P.deleteIntent = pendingIntent;
            return this;
        }

        public final void j(int i2, boolean z) {
            if (z) {
                Notification notification = this.P;
                notification.flags = i2 | notification.flags;
            } else {
                Notification notification2 = this.P;
                notification2.flags = (~i2) & notification2.flags;
            }
        }

        public c k(Bitmap bitmap) {
            this.f1595i = d(bitmap);
            return this;
        }

        public c l(boolean z) {
            j(2, z);
            return this;
        }

        public c m(int i2, int i3, boolean z) {
            this.r = i2;
            this.s = i3;
            this.t = z;
            return this;
        }

        public c n(int i2) {
            this.P.icon = i2;
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
            return g.c(notification);
        }
        return null;
    }
}
