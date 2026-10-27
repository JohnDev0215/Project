package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import com.android.volley.BuildConfig;
import java.util.ArrayList;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Bundle f1371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private IconCompat f1372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final i[] f1373c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final i[] f1374d;
        private boolean e;
        boolean f;
        private final int g;
        private final boolean h;

        @Deprecated
        public int i;
        public CharSequence j;
        public PendingIntent k;

        public PendingIntent a() {
            return this.k;
        }

        public boolean b() {
            return this.e;
        }

        public i[] c() {
            return this.f1374d;
        }

        public Bundle d() {
            return this.f1371a;
        }

        public IconCompat e() {
            int i;
            if (this.f1372b == null && (i = this.i) != 0) {
                this.f1372b = IconCompat.b(null, BuildConfig.FLAVOR, i);
            }
            return this.f1372b;
        }

        public i[] f() {
            return this.f1373c;
        }

        public int g() {
            return this.g;
        }

        public boolean h() {
            return this.f;
        }

        public CharSequence i() {
            return this.j;
        }

        public boolean j() {
            return this.h;
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
    public static abstract class d {
    }

    public static Bundle a(Notification notification) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            return notification.extras;
        }
        if (i >= 16) {
            return g.c(notification);
        }
        return null;
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class c {
        String A;
        Bundle B;
        int C;
        int D;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        int J;
        String K;
        long L;
        int M;
        boolean N;
        b O;
        Notification P;
        boolean Q;

        @Deprecated
        public ArrayList<String> R;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f1375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1376b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ArrayList<a> f1377c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        CharSequence f1378d;
        CharSequence e;
        PendingIntent f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
        boolean m;
        boolean n;
        d o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean x;
        boolean y;
        boolean z;

        public c(Context context, String str) {
            this.f1376b = new ArrayList<>();
            this.f1377c = new ArrayList<>();
            this.m = true;
            this.x = false;
            this.C = 0;
            this.D = 0;
            this.J = 0;
            this.M = 0;
            Notification notification = new Notification();
            this.P = notification;
            this.f1375a = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.l = 0;
            this.R = new ArrayList<>();
            this.N = true;
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

        public c c(String str) {
            this.I = str;
            return this;
        }

        public c d(PendingIntent pendingIntent) {
            this.f = pendingIntent;
            return this;
        }

        public c e(long j) {
            this.P.when = j;
            return this;
        }

        @Deprecated
        public c(Context context) {
            this(context, null);
        }
    }
}
