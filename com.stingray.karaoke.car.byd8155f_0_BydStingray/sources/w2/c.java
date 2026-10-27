package W2;

import B2.AbstractC0062b;
import B2.x;
import G.i0;
import e2.C1208p;
import h2.t;
import io.sentry.C1489n1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public x f9727n;
    public i0 o;

    @Override // W2.i
    public final long b(t tVar) {
        byte[] bArr = tVar.f15241a;
        if (bArr[0] != -1) {
            return -1L;
        }
        int i7 = (bArr[2] & 255) >> 4;
        if (i7 == 6 || i7 == 7) {
            tVar.I(4);
            tVar.C();
        }
        int iS = AbstractC0062b.s(i7, tVar);
        tVar.H(0);
        return iS;
    }

    @Override // W2.i
    public final boolean c(t tVar, long j3, M.t tVar2) {
        byte[] bArr = tVar.f15241a;
        x xVar = this.f9727n;
        if (xVar == null) {
            x xVar2 = new x(17, bArr);
            this.f9727n = xVar2;
            tVar2.l = xVar2.c(Arrays.copyOfRange(bArr, 9, tVar.c), null);
            return true;
        }
        byte b10 = bArr[0];
        if ((b10 & 127) != 3) {
            if (b10 != -1) {
                return true;
            }
            i0 i0Var = this.o;
            if (i0Var != null) {
                i0Var.l = j3;
                tVar2.f4151m = i0Var;
            }
            ((C1208p) tVar2.l).getClass();
            return false;
        }
        C1489n1 c1489n1T = AbstractC0062b.t(tVar);
        x xVar3 = new x(xVar.f603a, xVar.f604b, xVar.c, xVar.d, xVar.f605e, xVar.f607g, xVar.f608h, xVar.f610j, c1489n1T, xVar.l);
        this.f9727n = xVar3;
        i0 i0Var2 = new i0();
        i0Var2.f2584n = xVar3;
        i0Var2.o = c1489n1T;
        i0Var2.l = -1L;
        i0Var2.f2583m = -1L;
        this.o = i0Var2;
        return true;
    }

    @Override // W2.i
    public final void d(boolean z6) {
        super.d(z6);
        if (z6) {
            this.f9727n = null;
            this.o = null;
        }
    }
}
