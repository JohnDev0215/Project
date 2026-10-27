package G2;

import B2.AbstractC0062b;
import B2.C0069i;
import B2.C0073m;
import B2.InterfaceC0070j;
import B2.v;
import B2.x;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC0070j {
    public final x l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2650m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final v f2651n = new v();

    public b(x xVar, int i7) {
        this.l = xVar;
        this.f2650m = i7;
    }

    public final long a(C0073m c0073m) throws EOFException, InterruptedIOException {
        long j3;
        v vVar;
        x xVar;
        boolean zB;
        int iQ;
        while (true) {
            long jL = c0073m.l();
            j3 = c0073m.f588n;
            long j10 = j3 - 6;
            vVar = this.f2651n;
            xVar = this.l;
            if (jL >= j10) {
                break;
            }
            long jL2 = c0073m.l();
            byte[] bArr = new byte[2];
            c0073m.k(bArr, 0, 2, false);
            int i7 = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
            int i10 = this.f2650m;
            if (i7 != i10) {
                c0073m.f590q = 0;
                c0073m.e((int) (jL2 - c0073m.o), false);
                zB = false;
            } else {
                t tVar = new t(16);
                System.arraycopy(bArr, 0, tVar.f15241a, 0, 2);
                byte[] bArr2 = tVar.f15241a;
                int i11 = 0;
                for (int i12 = 2; i11 < 14 && (iQ = c0073m.q(i12 + i11, 14 - i11, bArr2)) != -1; i12 = 2) {
                    i11 += iQ;
                }
                tVar.G(i11);
                c0073m.f590q = 0;
                c0073m.e((int) (jL2 - c0073m.o), false);
                zB = AbstractC0062b.b(tVar, xVar, i10, vVar);
            }
            if (zB) {
                break;
            }
            c0073m.e(1, false);
        }
        if (c0073m.l() < j3 - 6) {
            return vVar.f600a;
        }
        c0073m.e((int) (j3 - c0073m.l()), false);
        return xVar.f610j;
    }

    @Override // B2.InterfaceC0070j
    public final C0069i d(C0073m c0073m, long j3) throws EOFException, InterruptedIOException {
        long j10 = c0073m.o;
        long jA = a(c0073m);
        long jL = c0073m.l();
        c0073m.e(Math.max(6, this.l.c), false);
        long jA2 = a(c0073m);
        return (jA > j3 || jA2 <= j3) ? jA2 <= j3 ? new C0069i(-2, jA2, c0073m.l()) : new C0069i(-1, jA, j10) : new C0069i(0, -9223372036854775807L, jL);
    }
}
