package l3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: renamed from: l3.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1757m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f18585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18586b;
    public Object c;
    public Object d;

    public AbstractC1757m(AbstractServiceC1765v abstractServiceC1765v) {
        this.d = abstractServiceC1765v;
        this.f18585a = new ArrayList();
    }

    public void A() {
    }

    public void B(InterfaceC1744I interfaceC1744I, Handler handler) {
        synchronized (this.f18585a) {
            try {
                this.c = new WeakReference(interfaceC1744I);
                B5.H h10 = (B5.H) this.d;
                B5.H h11 = null;
                if (h10 != null) {
                    h10.removeCallbacksAndMessages(null);
                }
                if (interfaceC1744I != null && handler != null) {
                    h11 = new B5.H(this, handler.getLooper(), 17);
                }
                this.d = h11;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a(C1740E c1740e) {
    }

    public void b(C1740E c1740e, int i7) {
    }

    public void c(String str, Bundle bundle, ResultReceiver resultReceiver) {
    }

    public void d(String str, Bundle bundle) {
    }

    public void e() {
    }

    public boolean f(Intent intent) {
        return false;
    }

    public void g() {
    }

    public void h() {
    }

    public void i(String str, Bundle bundle) {
    }

    public void j(String str, Bundle bundle) {
    }

    public void k(Uri uri, Bundle bundle) {
    }

    public void l() {
    }

    public void m(String str, Bundle bundle) {
    }

    public void n(String str, Bundle bundle) {
    }

    public void o(Uri uri, Bundle bundle) {
    }

    public void p(C1740E c1740e) {
    }

    public void q() {
    }

    public void r(long j3) {
    }

    public void s(float f10) {
    }

    public void t(Y y10) {
    }

    public void u(Y y10) {
    }

    public void v(int i7) {
    }

    public void w(int i7) {
    }

    public void x() {
    }

    public void y() {
    }

    public void z(long j3) {
    }

    public AbstractC1757m() {
        this.f18585a = new Object();
        this.f18586b = new C1743H(this);
        this.c = new WeakReference(null);
    }
}
