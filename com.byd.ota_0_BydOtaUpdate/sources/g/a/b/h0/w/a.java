package g.a.b.h0.w;

/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long[] f4987a;

    @Override // g.a.b.h0.w.b
    public void a(byte[] bArr) {
        this.f4987a = d.c(bArr);
    }

    @Override // g.a.b.h0.w.b
    public void b(long j, byte[] bArr) {
        long[] jArrH = d.h();
        if (j > 0) {
            long[] jArrJ = g.a.f.a.j(this.f4987a);
            do {
                if ((1 & j) != 0) {
                    d.f(jArrH, jArrJ);
                }
                d.i(jArrJ, jArrJ);
                j >>>= 1;
            } while (j > 0);
        }
        d.a(jArrH, bArr);
    }
}
