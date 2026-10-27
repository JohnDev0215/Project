package S8;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class J extends K {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q0 f8088n;

    public J(long j3, q0 q0Var) {
        super(j3);
        this.f8088n = q0Var;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        this.f8088n.run();
    }

    @Override // S8.K
    public final String toString() {
        return super.toString() + this.f8088n;
    }
}
