package X8;

import S8.A;
import S8.AbstractC0721s;
import S8.B;
import S8.C0712i;
import S8.F;
import S8.q0;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class n extends AbstractC0721s implements B {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ B f10574n;
    public final AbstractC0721s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f10575p;

    /* JADX WARN: Multi-variable type inference failed */
    public n(AbstractC0721s abstractC0721s, String str) {
        B b10 = abstractC0721s instanceof B ? (B) abstractC0721s : null;
        this.f10574n = b10 == null ? A.f8081a : b10;
        this.o = abstractC0721s;
        this.f10575p = str;
    }

    @Override // S8.B
    public final F I(long j3, q0 q0Var, InterfaceC2194h interfaceC2194h) {
        return this.f10574n.I(j3, q0Var, interfaceC2194h);
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        this.o.L(interfaceC2194h, runnable);
    }

    @Override // S8.AbstractC0721s
    public final void M(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        this.o.M(interfaceC2194h, runnable);
    }

    @Override // S8.AbstractC0721s
    public final boolean N(InterfaceC2194h interfaceC2194h) {
        return this.o.N(interfaceC2194h);
    }

    @Override // S8.B
    public final void i(long j3, C0712i c0712i) {
        this.f10574n.i(j3, c0712i);
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        return this.f10575p;
    }
}
