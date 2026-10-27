package g.a.b.e0;

/* JADX INFO: loaded from: classes.dex */
public class c extends b {
    @Override // g.a.b.e0.b, g.a.b.h
    public byte[] a() {
        int i2 = this.f4836b;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (true) {
            this.f4835a.nextBytes(bArr);
            g.a.b.j0.c.c(bArr);
            i3++;
            if (i3 >= 20 || (!g.a.b.j0.d.g(bArr, 0, i2) && g.a.b.j0.d.f(bArr, 0))) {
                break;
            }
        }
        if (g.a.b.j0.d.g(bArr, 0, i2) || !g.a.b.j0.d.f(bArr, 0)) {
            throw new IllegalStateException("Unable to generate DES-EDE key");
        }
        return bArr;
    }

    @Override // g.a.b.e0.b, g.a.b.h
    public void b(g.a.b.r rVar) {
        this.f4835a = rVar.a();
        int iB = (rVar.b() + 7) / 8;
        this.f4836b = iB;
        if (iB == 0 || iB == 21) {
            this.f4836b = 24;
        } else if (iB == 14) {
            this.f4836b = 16;
        } else if (iB != 24 && iB != 16) {
            throw new IllegalArgumentException("DESede key must be 192 or 128 bits long.");
        }
    }
}
