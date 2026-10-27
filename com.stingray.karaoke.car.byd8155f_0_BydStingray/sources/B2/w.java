package B2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;

/* JADX INFO: loaded from: classes.dex */
public class w implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f602b;
    public final Object c;

    public /* synthetic */ w(int i7, long j3, Object obj) {
        this.f601a = i7;
        this.c = obj;
        this.f602b = j3;
    }

    @Override // B2.F
    public final boolean h() {
        switch (this.f601a) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                return true;
        }
    }

    @Override // B2.F
    public final E j(long j3) {
        switch (this.f601a) {
            case 0:
                x xVar = (x) this.c;
                AbstractC1346c.k(xVar.f611k);
                C1489n1 c1489n1 = xVar.f611k;
                long[] jArr = (long[]) c1489n1.f16624m;
                int iE = AbstractC1343A.e(jArr, AbstractC1343A.j((((long) xVar.f605e) * j3) / 1000000, 0L, xVar.f610j - 1), false);
                long j10 = iE == -1 ? 0L : jArr[iE];
                long[] jArr2 = (long[]) c1489n1.f16625n;
                long j11 = iE != -1 ? jArr2[iE] : 0L;
                int i7 = xVar.f605e;
                long j12 = (j10 * 1000000) / ((long) i7);
                long j13 = this.f602b;
                G g7 = new G(j12, j11 + j13);
                if (j12 == j3 || iE == jArr.length - 1) {
                    return new E(g7, g7);
                }
                int i10 = iE + 1;
                return new E(g7, new G((jArr[i10] * 1000000) / ((long) i7), j13 + jArr2[i10]));
            case 1:
                return (E) this.c;
            default:
                D2.b bVar = (D2.b) this.c;
                E eB = bVar.f1145i[0].b(j3);
                int i11 = 1;
                while (true) {
                    D2.e[] eVarArr = bVar.f1145i;
                    if (i11 >= eVarArr.length) {
                        return eB;
                    }
                    E eB2 = eVarArr[i11].b(j3);
                    if (eB2.f508a.f511b < eB.f508a.f511b) {
                        eB = eB2;
                    }
                    i11++;
                }
                break;
        }
    }

    @Override // B2.F
    public final long l() {
        switch (this.f601a) {
        }
        return this.f602b;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public w(long j3) {
        this(j3, 0L);
        this.f601a = 1;
    }

    public w(long j3, long j10) {
        this.f601a = 1;
        this.f602b = j3;
        G g7 = j10 == 0 ? G.c : new G(0L, j10);
        this.c = new E(g7, g7);
    }
}
