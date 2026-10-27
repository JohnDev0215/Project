package S8;

import java.lang.reflect.InvocationTargetException;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class q0 extends X8.q implements Runnable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f8131p;

    public q0(long j3, AbstractC2410c abstractC2410c) {
        super(abstractC2410c, abstractC2410c.getContext());
        this.f8131p = j3;
    }

    @Override // S8.d0
    public final String V() {
        return super.V() + "(timeMillis=" + this.f8131p + ')';
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        AbstractC0727y.m(this.f8101n);
        w(new p0(android.hardware.bydauto.ac.tsimpl.a.f(this.f8131p, " ms", new StringBuilder("Timed out waiting for ")), this));
    }
}
