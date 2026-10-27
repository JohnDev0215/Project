package B2;

import e2.C1208p;
import e2.InterfaceC1201i;
import java.io.EOFException;

/* JADX INFO: renamed from: B2.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0076p implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f597a = new byte[4096];

    @Override // B2.L
    public final void a(h2.t tVar, int i7, int i10) {
        tVar.I(i7);
    }

    @Override // B2.L
    public final void b(long j3, int i7, int i10, int i11, K k10) {
    }

    @Override // B2.L
    public final int c(InterfaceC1201i interfaceC1201i, int i7, boolean z6) throws EOFException {
        byte[] bArr = this.f597a;
        int iN = interfaceC1201i.n(bArr, 0, Math.min(bArr.length, i7));
        if (iN != -1) {
            return iN;
        }
        if (z6) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // B2.L
    public final void d(C1208p c1208p) {
    }
}
