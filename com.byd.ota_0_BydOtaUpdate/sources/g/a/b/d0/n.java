package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class n extends v {
    public static boolean c(byte b2, int i2) {
        return (b2 & (1 << i2)) != 0;
    }

    public static byte[] d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int i2 = 0; i2 != 8; i2++) {
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 != 8; i5++) {
                int iJ = g.a.f.f.j(bArr, i5 * 4);
                if (c(bArr2[i2], i5)) {
                    i3 += iJ;
                } else {
                    i4 += iJ;
                }
            }
            byte[] bArr4 = new byte[8];
            g.a.f.f.g(i3, bArr4, 0);
            g.a.f.f.g(i4, bArr4, 4);
            g.a.b.h0.k kVar = new g.a.b.h0.k(new u());
            kVar.init(true, new g.a.b.j0.w0(new g.a.b.j0.y0(new g.a.b.j0.s0(bArr), bArr3), bArr4));
            kVar.a(bArr, 0, bArr, 0);
            kVar.a(bArr, 8, bArr, 8);
            kVar.a(bArr, 16, bArr, 16);
            kVar.a(bArr, 24, bArr, 24);
        }
        return bArr;
    }

    @Override // g.a.b.d0.v, g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        byte[] bArrB;
        g.a.b.j0.s0 s0Var;
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        g.a.b.j0.z0 z0Var = (g.a.b.j0.z0) iVar;
        if (z0Var.a() instanceof g.a.b.j0.y0) {
            s0Var = (g.a.b.j0.s0) ((g.a.b.j0.y0) z0Var.a()).a();
            bArrB = ((g.a.b.j0.y0) z0Var.a()).b();
        } else {
            bArrB = null;
            s0Var = (g.a.b.j0.s0) z0Var.a();
        }
        g.a.b.j0.s0 s0Var2 = new g.a.b.j0.s0(d(s0Var.a(), z0Var.b(), bArrB));
        super.init(z, bArrB != null ? new g.a.b.j0.z0(new g.a.b.j0.y0(s0Var2, bArrB), z0Var.b()) : new g.a.b.j0.z0(s0Var2, z0Var.b()));
    }
}
