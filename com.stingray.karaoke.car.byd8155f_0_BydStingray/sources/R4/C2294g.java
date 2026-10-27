package r4;

import A4.q;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.bumptech.glide.j;
import com.bumptech.glide.l;
import d4.C1068a;
import d4.C1069b;
import d4.C1071d;
import g4.k;
import h4.InterfaceC1359a;
import java.util.ArrayList;
import z4.C2768d;

/* JADX INFO: renamed from: r4.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2294g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1071d f21829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f21830b;
    public final ArrayList c;
    public final l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC1359a f21831e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f21832f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21833g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public j f21834h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C2292e f21835i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f21836j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C2292e f21837k;
    public Bitmap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2292e f21838m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21839n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21840p;

    public C2294g(com.bumptech.glide.b bVar, C1071d c1071d, int i7, int i10, Bitmap bitmap) {
        m4.c cVar = m4.c.f19187b;
        InterfaceC1359a interfaceC1359a = bVar.l;
        com.bumptech.glide.e eVar = bVar.f12667n;
        Context baseContext = eVar.getBaseContext();
        A4.h.c(baseContext, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        l lVarB = com.bumptech.glide.b.a(baseContext).f12668p.b(baseContext);
        Context baseContext2 = eVar.getBaseContext();
        A4.h.c(baseContext2, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        l lVarB2 = com.bumptech.glide.b.a(baseContext2).f12668p.b(baseContext2);
        lVarB2.getClass();
        j jVarT = new j(lVarB2.l, lVarB2, Bitmap.class, lVarB2.f12711m).a(l.f12710t).a(((w4.f) ((w4.f) ((w4.f) new w4.f().d(k.f14907b)).r()).n()).h(i7, i10));
        this.c = new ArrayList();
        this.d = lVarB;
        Handler handler = new Handler(Looper.getMainLooper(), new Z4.e(this, 1));
        this.f21831e = interfaceC1359a;
        this.f21830b = handler;
        this.f21834h = jVarT;
        this.f21829a = c1071d;
        c(cVar, bitmap);
    }

    public final void a() {
        int i7;
        if (!this.f21832f || this.f21833g) {
            return;
        }
        C2292e c2292e = this.f21838m;
        if (c2292e != null) {
            this.f21838m = null;
            b(c2292e);
            return;
        }
        this.f21833g = true;
        C1071d c1071d = this.f21829a;
        C1069b c1069b = c1071d.l;
        int i10 = c1069b.c;
        long jUptimeMillis = SystemClock.uptimeMillis() + ((long) ((i10 <= 0 || (i7 = c1071d.f13369k) < 0) ? 0 : (i7 < 0 || i7 >= i10) ? -1 : ((C1068a) c1069b.f13352e.get(i7)).f13347i));
        int i11 = (c1071d.f13369k + 1) % c1071d.l.c;
        c1071d.f13369k = i11;
        this.f21837k = new C2292e(this.f21830b, i11, jUptimeMillis);
        j jVarX = this.f21834h.a((w4.f) new w4.f().m(new C2768d(Double.valueOf(Math.random())))).x(c1071d);
        jVarX.w(this.f21837k, null, jVarX, A4.h.f419a);
    }

    public final void b(C2292e c2292e) {
        this.f21833g = false;
        boolean z6 = this.f21836j;
        Handler handler = this.f21830b;
        if (z6) {
            handler.obtainMessage(2, c2292e).sendToTarget();
            return;
        }
        if (!this.f21832f) {
            this.f21838m = c2292e;
            return;
        }
        if (c2292e.f21828r != null) {
            Bitmap bitmap = this.l;
            if (bitmap != null) {
                this.f21831e.k(bitmap);
                this.l = null;
            }
            C2292e c2292e2 = this.f21835i;
            this.f21835i = c2292e;
            ArrayList arrayList = this.c;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                C2290c c2290c = (C2290c) ((InterfaceC2293f) arrayList.get(size));
                Object callback = c2290c.getCallback();
                while (callback instanceof Drawable) {
                    callback = ((Drawable) callback).getCallback();
                }
                if (callback == null) {
                    c2290c.stop();
                    c2290c.invalidateSelf();
                } else {
                    c2290c.invalidateSelf();
                    C2292e c2292e3 = c2290c.l.f21814a.f21835i;
                    if ((c2292e3 != null ? c2292e3.f21826p : -1) == r5.f21829a.l.c - 1) {
                        c2290c.f21818q++;
                    }
                    int i7 = c2290c.f21819r;
                    if (i7 != -1 && c2290c.f21818q >= i7) {
                        c2290c.stop();
                    }
                }
            }
            if (c2292e2 != null) {
                handler.obtainMessage(2, c2292e2).sendToTarget();
            }
        }
        a();
    }

    public final void c(e4.l lVar, Bitmap bitmap) {
        A4.h.c(lVar, "Argument must not be null");
        A4.h.c(bitmap, "Argument must not be null");
        this.l = bitmap;
        this.f21834h = this.f21834h.a(new w4.f().p(lVar, true));
        this.f21839n = q.c(bitmap);
        this.o = bitmap.getWidth();
        this.f21840p = bitmap.getHeight();
    }
}
