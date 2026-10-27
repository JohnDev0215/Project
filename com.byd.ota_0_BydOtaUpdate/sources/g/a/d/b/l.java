package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class l {

    public static class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f5517a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ h f5518b;

        public a(d dVar, h hVar) {
            this.f5517a = dVar;
            this.f5518b = hVar;
        }

        @Override // g.a.d.b.o
        public p a(p pVar) {
            k kVar = pVar instanceof k ? (k) pVar : null;
            int iA = l.a(this.f5517a);
            int i2 = iA > 257 ? 6 : 5;
            int i3 = 1 << i2;
            if (b(kVar, i3)) {
                return kVar;
            }
            int i4 = ((iA + i2) - 1) / i2;
            h[] hVarArr = new h[i2 + 1];
            hVarArr[0] = this.f5518b;
            for (int i5 = 1; i5 < i2; i5++) {
                hVarArr[i5] = hVarArr[i5 - 1].I(i4);
            }
            hVarArr[i2] = hVarArr[0].G(hVarArr[1]);
            this.f5517a.B(hVarArr);
            h[] hVarArr2 = new h[i3];
            hVarArr2[0] = hVarArr[0];
            for (int i6 = i2 - 1; i6 >= 0; i6--) {
                h hVar = hVarArr[i6];
                int i7 = 1 << i6;
                for (int i8 = i7; i8 < i3; i8 += i7 << 1) {
                    hVarArr2[i8] = hVarArr2[i8 - i7].a(hVar);
                }
            }
            this.f5517a.B(hVarArr2);
            k kVar2 = new k();
            kVar2.d(this.f5517a.e(hVarArr2, 0, i3));
            kVar2.e(hVarArr[i2]);
            kVar2.f(i2);
            return kVar2;
        }

        public final boolean b(k kVar, int i2) {
            return kVar != null && c(kVar.a(), i2);
        }

        public final boolean c(f fVar, int i2) {
            return fVar != null && fVar.b() >= i2;
        }
    }

    public static int a(d dVar) {
        BigInteger bigIntegerX = dVar.x();
        return bigIntegerX == null ? dVar.u() + 1 : bigIntegerX.bitLength();
    }

    public static k b(h hVar) {
        d dVarI = hVar.i();
        return (k) dVarI.D(hVar, "bc_fixed_point", new a(dVarI, hVar));
    }
}
