package e7;

import c7.InterfaceC1035a;
import h5.t;

/* JADX INFO: renamed from: e7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1231a implements InterfaceC1035a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int[] f14469b;
    public final /* synthetic */ t c;

    public /* synthetic */ C1231a(int[] iArr, t tVar, int i7) {
        this.f14468a = i7;
        this.f14469b = iArr;
        this.c = tVar;
    }

    @Override // c7.InterfaceC1035a
    public final void q(Object... objArr) {
        switch (this.f14468a) {
            case 0:
                AbstractC1232b.f14470A.fine("pre-pause polling complete");
                int[] iArr = this.f14469b;
                int i7 = iArr[0] - 1;
                iArr[0] = i7;
                if (i7 == 0) {
                    this.c.run();
                }
                break;
            default:
                AbstractC1232b.f14470A.fine("pre-pause writing complete");
                int[] iArr2 = this.f14469b;
                int i10 = iArr2[0] - 1;
                iArr2[0] = i10;
                if (i10 == 0) {
                    this.c.run();
                }
                break;
        }
    }
}
