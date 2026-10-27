package p;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zu0 extends pw0 {
    public final long e;
    public final boolean k;
    public long l;

    public zu0(de2 de2Var, long j, boolean z) {
        super(de2Var);
        this.e = j;
        this.k = z;
    }

    @Override // p.pw0, p.de2
    public final long t(fo foVar, long j) throws IOException {
        v03.j(foVar, "sink");
        long j2 = this.l;
        long j3 = this.e;
        if (j2 > j3) {
            j = 0;
        } else if (this.k) {
            long j4 = j3 - j2;
            if (j4 == 0) {
                return -1L;
            }
            j = Math.min(j, j4);
        }
        long jT = super.t(foVar, j);
        if (jT != -1) {
            this.l += jT;
        }
        long j5 = this.l;
        if ((j5 >= j3 || jT != -1) && j5 <= j3) {
            return jT;
        }
        if (jT > 0 && j5 > j3) {
            long j6 = foVar.e - (j5 - j3);
            fo foVar2 = new fo();
            foVar2.L(foVar);
            foVar.Y(foVar2, j6);
            foVar2.X();
        }
        throw new IOException("expected " + j3 + " bytes but got " + this.l);
    }
}
