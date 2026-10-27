package g.a.b.e0;

/* JADX INFO: loaded from: classes.dex */
public class b extends g.a.b.h {
    @Override // g.a.b.h
    public byte[] a() {
        byte[] bArr = new byte[8];
        do {
            this.f4835a.nextBytes(bArr);
            g.a.b.j0.c.c(bArr);
        } while (g.a.b.j0.c.b(bArr, 0));
        return bArr;
    }

    @Override // g.a.b.h
    public void b(g.a.b.r rVar) {
        super.b(rVar);
        int i2 = this.f4836b;
        if (i2 == 0 || i2 == 7) {
            this.f4836b = 8;
        } else if (i2 != 8) {
            throw new IllegalArgumentException("DES key must be 64 bits long.");
        }
    }
}
