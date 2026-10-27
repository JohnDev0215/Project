package b.g.a;

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
        public final Bundle f937a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public IconCompat f938b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h[] f939c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final h[] f940d;
        public boolean e;
        public boolean f;
        public final int g;
        public final boolean h;

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

        public h[] c() {
            return this.f940d;
        }

        public Bundle d() {
            return this.f937a;
        }

        public IconCompat e() {
            int i;
            if (this.f938b == null && (i = this.i) != 0) {
                this.f938b = IconCompat.b(null, "", i);
            }
            return this.f938b;
        }

        public h[] f() {
            return this.f939c;
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
        public Context f941a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f944d;
        public CharSequence e;
        public PendingIntent f;
        public PendingIntent g;
        public RemoteViews h;
        public Bitmap i;
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
        public ArrayList<a> f942b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ArrayList<a> f943c = new ArrayList<>();
        public boolean m = true;
        public boolean x = false;
        public int C = 0;
        public int D = 0;
        public int J = 0;
        public int M = 0;

        public c(Context context, String str) {
            Notification notification = new Notification();
            this.P = notification;
            this.f941a = context;
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

        public c d(CharSequence charSequence) {
            this.e = c(charSequence);
            return this;
        }

        public c e(CharSequence charSequence) {
            this.f944d = c(charSequence);
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
            return g.c(notification);
        }
        return null;
    }
}
