package T;

import S8.AbstractC0727y;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2194h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O implements InterfaceC0769u0 {
    public final InterfaceC2785n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final X8.c f8210m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public S8.k0 f8211n;

    public O(InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n) {
        this.l = interfaceC2785n;
        this.f8210m = AbstractC0727y.a(interfaceC2194h);
    }

    @Override // T.InterfaceC0769u0
    public final void a() throws IllegalAccessException, InvocationTargetException {
        S8.k0 k0Var = this.f8211n;
        if (k0Var != null) {
            CancellationException cancellationException = new CancellationException("Old job was still running!");
            cancellationException.initCause(null);
            k0Var.g(cancellationException);
        }
        this.f8211n = AbstractC0727y.w(this.f8210m, null, null, this.l, 3);
    }

    @Override // T.InterfaceC0769u0
    public final void c() throws IllegalAccessException, InvocationTargetException {
        S8.k0 k0Var = this.f8211n;
        if (k0Var != null) {
            k0Var.x(new Q());
        }
        this.f8211n = null;
    }

    @Override // T.InterfaceC0769u0
    public final void d() throws IllegalAccessException, InvocationTargetException {
        S8.k0 k0Var = this.f8211n;
        if (k0Var != null) {
            k0Var.x(new Q());
        }
        this.f8211n = null;
    }
}
