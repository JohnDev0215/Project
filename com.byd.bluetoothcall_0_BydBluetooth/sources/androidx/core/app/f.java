package androidx.core.app;

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
public class f {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Bundle f1200a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private IconCompat f1201b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final i[] f1202c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final i[] f1203d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1204e;
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
            return this.f1204e;
        }

        public i[] c() {
            return this.f1203d;
        }

        public Bundle d() {
            return this.f1200a;
        }

        public IconCompat e() {
            int i;
            if (this.f1201b == null && (i = this.i) != 0) {
                this.f1201b = IconCompat.b(null, "", i);
            }
            return this.f1201b;
        }

        public i[] f() {
            return this.f1202c;
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
    public static class c {
        String A;
        Bundle B;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        String K;
        long L;
        boolean N;
        b O;
        Notification P;
        boolean Q;

        @Deprecated
        public ArrayList<String> R;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f1205a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        CharSequence f1208d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        CharSequence f1209e;
        PendingIntent f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
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
        boolean y;
        boolean z;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1206b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ArrayList<a> f1207c = new ArrayList<>();
        boolean m = true;
        boolean x = false;
        int C = 0;
        int D = 0;
        int J = 0;
        int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f1205a = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.l = 0;
            this.R = new ArrayList<>();
            this.N = true;
        }

        protected static CharSequence c(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public Notification a() {
            return new g(this).b();
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public c d(CharSequence charSequence) {
            this.f1209e = c(charSequence);
            return this;
        }

        public c e(CharSequence charSequence) {
            this.f1208d = c(charSequence);
            return this;
        }

        public c f(int i) {
            Notification notification = this.P;
            notification.defaults = i;
            if ((i & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public c g(int i) {
            this.l = i;
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
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            return notification.extras;
        }
        if (i >= 16) {
            return h.c(notification);
        }
        return null;
    }
}
