package e7;

import c7.InterfaceC1035a;
import h5.t;
import k7.C1707a;

/* JADX INFO: renamed from: e7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1233c implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14472a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C1235e f14473b;

    public /* synthetic */ C1233c(C1235e c1235e, int i7) {
        this.f14472a = i7;
        this.f14473b = c1235e;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f14472a) {
            case 0:
                C1707a.a(new t(22, this, objArr, false));
                break;
            case 1:
                this.f14473b.N0("requestHeaders", objArr[0]);
                break;
            case 2:
                C1707a.a(new t(23, this, objArr, false));
                break;
            case 3:
                C1707a.a(new t(24, this, objArr, false));
                break;
            default:
                C1707a.a(new t(25, this, objArr, false));
                break;
        }
    }
}
