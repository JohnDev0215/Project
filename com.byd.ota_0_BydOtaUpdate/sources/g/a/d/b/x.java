package g.a.d.b;

import g.a.d.b.d;
import g.a.d.b.h;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class x extends g.a.d.b.a {

    public static class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h.b f5550a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ byte f5551b;

        public a(h.b bVar, byte b2) {
            this.f5550a = bVar;
            this.f5551b = b2;
        }

        @Override // g.a.d.b.o
        public p a(p pVar) {
            if (pVar instanceof y) {
                return pVar;
            }
            y yVar = new y();
            yVar.b(s.d(this.f5550a, this.f5551b));
            return yVar;
        }
    }

    public static h.b d(h.b bVar, byte[] bArr) {
        d.b bVar2 = (d.b) bVar.i();
        h.b[] bVarArrA = ((y) bVar2.D(bVar, "bc_wtnaf", new a(bVar, bVar2.o().t().byteValue()))).a();
        h.b[] bVarArr = new h.b[bVarArrA.length];
        for (int i2 = 0; i2 < bVarArrA.length; i2++) {
            bVarArr[i2] = (h.b) bVarArrA[i2].z();
        }
        h.b bVar3 = (h.b) bVar.i().v();
        int i3 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i3++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                bVar3 = (h.b) bVar3.L(i3).a(b2 > 0 ? bVarArrA[b2 >>> 1] : bVarArr[(-b2) >>> 1]);
                i3 = 0;
            }
        }
        return i3 > 0 ? bVar3.L(i3) : bVar3;
    }

    @Override // g.a.d.b.a
    public h c(h hVar, BigInteger bigInteger) {
        if (!(hVar instanceof h.b)) {
            throw new IllegalArgumentException("Only ECPoint.AbstractF2m can be used in WTauNafMultiplier");
        }
        h.b bVar = (h.b) hVar;
        d.b bVar2 = (d.b) bVar.i();
        int iU = bVar2.u();
        byte bByteValue = bVar2.o().t().byteValue();
        byte bC = s.c(bByteValue);
        return e(bVar, s.j(bigInteger, iU, bByteValue, bVar2.H(), bC, (byte) 10), bByteValue, bC);
    }

    public final h.b e(h.b bVar, z zVar, byte b2, byte b3) {
        z[] zVarArr = b2 == 0 ? s.f5529d : s.f5531f;
        return d(bVar, s.l(b3, zVar, (byte) 4, BigInteger.valueOf(16L), s.g(b3, 4), zVarArr));
    }
}
