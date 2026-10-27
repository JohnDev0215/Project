package W2;

import B2.C0073m;
import e2.M;
import h2.t;
import java.io.EOFException;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f9734b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9735e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f9736f = new int[255];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t f9737g = new t(255);

    public final boolean a(C0073m c0073m, boolean z6) throws M, EOFException {
        boolean zK;
        boolean zK2;
        this.f9733a = 0;
        this.f9734b = 0L;
        this.c = 0;
        this.d = 0;
        this.f9735e = 0;
        t tVar = this.f9737g;
        tVar.E(27);
        try {
            zK = c0073m.k(tVar.f15241a, 0, 27, z6);
        } catch (EOFException e10) {
            if (!z6) {
                throw e10;
            }
            zK = false;
        }
        if (!zK || tVar.x() != 1332176723) {
            return false;
        }
        if (tVar.v() != 0) {
            if (z6) {
                return false;
            }
            throw M.b("unsupported bit stream revision");
        }
        this.f9733a = tVar.v();
        this.f9734b = tVar.k();
        tVar.m();
        tVar.m();
        tVar.m();
        int iV = tVar.v();
        this.c = iV;
        this.d = iV + 27;
        tVar.E(iV);
        try {
            zK2 = c0073m.k(tVar.f15241a, 0, this.c, z6);
        } catch (EOFException e11) {
            if (!z6) {
                throw e11;
            }
            zK2 = false;
        }
        if (!zK2) {
            return false;
        }
        for (int i7 = 0; i7 < this.c; i7++) {
            int iV2 = tVar.v();
            this.f9736f[i7] = iV2;
            this.f9735e += iV2;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        if (r10 == (-1)) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
    
        if (r9.o >= r10) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        if (r9.s(1) == (-1)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0056, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(B2.C0073m r9, long r10) {
        /*
            r8 = this;
            long r0 = r9.o
            long r2 = r9.l()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Le
            r0 = r2
            goto Lf
        Le:
            r0 = r1
        Lf:
            h2.AbstractC1346c.c(r0)
            h2.t r8 = r8.f9737g
            r0 = 4
            r8.E(r0)
        L18:
            r3 = -1
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 == 0) goto L27
            long r4 = r9.o
            r6 = 4
            long r4 = r4 + r6
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 >= 0) goto L46
        L27:
            byte[] r4 = r8.f15241a
            boolean r4 = r9.k(r4, r1, r0, r2)     // Catch: java.io.EOFException -> L2e
            goto L2f
        L2e:
            r4 = r1
        L2f:
            if (r4 == 0) goto L46
            r8.H(r1)
            long r3 = r8.x()
            r5 = 1332176723(0x4f676753, double:6.58182753E-315)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L42
            r9.f590q = r1
            return r2
        L42:
            r9.i(r2)
            goto L18
        L46:
            if (r3 == 0) goto L4e
            long r4 = r9.o
            int r8 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r8 >= 0) goto L56
        L4e:
            int r8 = r9.s(r2)
            r0 = -1
            if (r8 == r0) goto L56
            goto L46
        L56:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: W2.f.b(B2.m, long):boolean");
    }
}
