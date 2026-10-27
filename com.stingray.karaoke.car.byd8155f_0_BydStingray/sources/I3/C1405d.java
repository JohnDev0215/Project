package i3;

import B2.C0073m;
import B2.L;
import B2.r;
import B2.s;
import B2.t;
import u2.T;

/* JADX INFO: renamed from: i3.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1405d implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f15737a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L f15738b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InterfaceC1403b f15739e;
    public int c = 0;
    public long d = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f15740f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f15741g = -1;

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        T t10 = (T) tVar;
        this.f15737a = t10;
        this.f15738b = t10.u(0, 1);
        t10.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01b9  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r25, B2.v r26) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i3.C1405d.c(B2.s, B2.v):int");
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.c = j3 == 0 ? 0 : 4;
        InterfaceC1403b interfaceC1403b = this.f15739e;
        if (interfaceC1403b != null) {
            interfaceC1403b.c(j10);
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        return h6.r.b((C0073m) sVar);
    }
}
