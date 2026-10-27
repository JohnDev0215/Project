package T8;

import A7.m;
import Q5.O;
import S8.AbstractC0721s;
import S8.AbstractC0727y;
import S8.B;
import S8.C0712i;
import S8.E;
import S8.F;
import S8.h0;
import S8.q0;
import android.os.Handler;
import android.os.Looper;
import h5.t;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC0721s implements B {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Handler f8930n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f8931p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final d f8932q;

    public d(Handler handler, String str, boolean z6) {
        this.f8930n = handler;
        this.o = str;
        this.f8931p = z6;
        this.f8932q = z6 ? this : new d(handler, str, true);
    }

    @Override // S8.B
    public final F I(long j3, final q0 q0Var, InterfaceC2194h interfaceC2194h) {
        if (j3 > 4611686018427387903L) {
            j3 = 4611686018427387903L;
        }
        if (this.f8930n.postDelayed(q0Var, j3)) {
            return new F() { // from class: T8.c
                @Override // S8.F
                public final void a() {
                    this.l.f8930n.removeCallbacks(q0Var);
                }
            };
        }
        P(interfaceC2194h, q0Var);
        return h0.l;
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        if (this.f8930n.post(runnable)) {
            return;
        }
        P(interfaceC2194h, runnable);
    }

    @Override // S8.AbstractC0721s
    public final boolean N(InterfaceC2194h interfaceC2194h) {
        return (this.f8931p && m.a(Looper.myLooper(), this.f8930n.getLooper())) ? false : true;
    }

    public final void P(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        AbstractC0727y.e(interfaceC2194h, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Z8.e eVar = E.f8084a;
        Z8.d.f11010n.L(interfaceC2194h, runnable);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (dVar.f8930n == this.f8930n && dVar.f8931p == this.f8931p) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f8931p ? 1231 : 1237) ^ System.identityHashCode(this.f8930n);
    }

    @Override // S8.B
    public final void i(long j3, C0712i c0712i) {
        t tVar = new t(c0712i, 8, this);
        if (j3 > 4611686018427387903L) {
            j3 = 4611686018427387903L;
        }
        if (this.f8930n.postDelayed(tVar, j3)) {
            c0712i.u(new O(this, 4, tVar));
        } else {
            P(c0712i.f8118p, tVar);
        }
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        d dVar;
        String str;
        Z8.e eVar = E.f8084a;
        d dVar2 = X8.m.f10573a;
        if (this == dVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                dVar = dVar2.f8932q;
            } catch (UnsupportedOperationException unused) {
                dVar = null;
            }
            str = this == dVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.o;
        if (string == null) {
            string = this.f8930n.toString();
        }
        return this.f8931p ? android.hardware.bydauto.ac.tsimpl.a.h(string, ".immediate") : string;
    }

    public d(Handler handler) {
        this(handler, null, false);
    }
}
