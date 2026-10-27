package X8;

import S8.A;
import S8.AbstractC0721s;
import S8.B;
import S8.C0712i;
import S8.F;
import S8.q0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC0721s implements B {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f10558t = AtomicIntegerFieldUpdater.newUpdater(g.class, "runningWorkers$volatile");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ B f10559n;
    public final AbstractC0721s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f10560p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f10561q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final j f10562r;
    private volatile /* synthetic */ int runningWorkers$volatile;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Object f10563s;

    /* JADX WARN: Multi-variable type inference failed */
    public g(AbstractC0721s abstractC0721s, int i7, String str) {
        B b10 = abstractC0721s instanceof B ? (B) abstractC0721s : null;
        this.f10559n = b10 == null ? A.f8081a : b10;
        this.o = abstractC0721s;
        this.f10560p = i7;
        this.f10561q = str;
        this.f10562r = new j();
        this.f10563s = new Object();
    }

    @Override // S8.B
    public final F I(long j3, q0 q0Var, InterfaceC2194h interfaceC2194h) {
        return this.f10559n.I(j3, q0Var, interfaceC2194h);
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        Runnable runnableP;
        this.f10562r.a(runnable);
        if (f10558t.get(this) >= this.f10560p || !Q() || (runnableP = P()) == null) {
            return;
        }
        this.o.L(this, new h5.t(9, this, runnableP, false));
    }

    @Override // S8.AbstractC0721s
    public final void M(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        Runnable runnableP;
        this.f10562r.a(runnable);
        if (f10558t.get(this) >= this.f10560p || !Q() || (runnableP = P()) == null) {
            return;
        }
        this.o.M(this, new h5.t(9, this, runnableP, false));
    }

    public final Runnable P() {
        while (true) {
            Runnable runnable = (Runnable) this.f10562r.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f10563s) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10558t;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f10562r.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean Q() {
        synchronized (this.f10563s) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10558t;
            if (atomicIntegerFieldUpdater.get(this) >= this.f10560p) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // S8.B
    public final void i(long j3, C0712i c0712i) {
        this.f10559n.i(j3, c0712i);
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        String str = this.f10561q;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.o);
        sb.append(".limitedParallelism(");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f10560p, ')');
    }
}
