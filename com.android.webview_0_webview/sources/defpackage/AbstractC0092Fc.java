package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: Fc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0092Fc {
    public final Context a;
    public final C0020Bc b;
    public final InterfaceC1772xc c;
    public final C0289Qc d;
    public final Looper e;
    public final int f;
    public final InterfaceC1114ld g;
    public final C0500ad h;

    public AbstractC0092Fc(Context context, C0020Bc c0020Bc, InterfaceC1772xc interfaceC1772xc, C0074Ec c0074Ec) {
        Cif.g(context, "Null context is not permitted.");
        Cif.g(c0020Bc, "Api must not be null.");
        Cif.g(c0074Ec, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.a = context.getApplicationContext();
        this.b = c0020Bc;
        this.c = null;
        this.e = c0074Ec.b;
        this.d = new C0289Qc(c0020Bc, null);
        C0500ad c0500adC = C0500ad.c(this.a);
        this.h = c0500adC;
        this.f = c0500adC.g.getAndIncrement();
        this.g = c0074Ec.a;
        Handler handler = this.h.m;
        handler.sendMessage(handler.obtainMessage(7, this));
    }

    public C0345Te a() {
        C0345Te c0345Te = new C0345Te();
        c0345Te.a = null;
        Set setEmptySet = Collections.emptySet();
        if (c0345Te.b == null) {
            c0345Te.b = new C1083l1();
        }
        c0345Te.b.addAll(setEmptySet);
        c0345Te.d = this.a.getClass().getName();
        c0345Te.c = this.a.getPackageName();
        return c0345Te;
    }

    public AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        abstractC0343Tc.g();
        C0500ad c0500ad = this.h;
        C1554te c1554te = new C1554te(1, abstractC0343Tc);
        Handler handler = c0500ad.m;
        handler.sendMessage(handler.obtainMessage(4, new C0892he(c1554te, c0500ad.h.get(), this)));
        return abstractC0343Tc;
    }

    public AbstractC1559tj c(AbstractC1224nd abstractC1224nd) {
        C1614uj c1614uj = new C1614uj();
        C0500ad c0500ad = this.h;
        InterfaceC1114ld interfaceC1114ld = this.g;
        Objects.requireNonNull(c0500ad);
        C1664ve c1664ve = new C1664ve(1, abstractC1224nd, c1614uj, interfaceC1114ld);
        Handler handler = c0500ad.m;
        handler.sendMessage(handler.obtainMessage(4, new C0892he(c1664ve, c0500ad.h.get(), this)));
        return c1614uj.a;
    }

    public InterfaceC1882zc d(Looper looper, C0415Xc c0415Xc) {
        C0381Ve c0381VeA = a().a();
        C0020Bc c0020Bc = this.b;
        Cif.i(c0020Bc.a != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return c0020Bc.a.a(this.a, looper, c0381VeA, this.c, c0415Xc, c0415Xc);
    }

    public BinderC1059ke e(Context context, Handler handler) {
        return new BinderC1059ke(context, handler, a().a(), BinderC1059ke.h);
    }
}
