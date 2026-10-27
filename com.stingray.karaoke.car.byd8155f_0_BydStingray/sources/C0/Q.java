package C0;

import E0.InterfaceC0173x;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends g0.n implements InterfaceC0173x {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f867y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f868z;

    @Override // E0.InterfaceC0173x
    public final void u(long j3) {
        if (a1.j.a(this.f868z, j3)) {
            return;
        }
        this.f867y.b(new a1.j(j3));
        this.f868z = j3;
    }

    @Override // g0.n
    public final boolean w0() {
        return true;
    }
}
