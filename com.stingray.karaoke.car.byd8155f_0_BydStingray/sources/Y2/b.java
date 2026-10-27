package Y2;

import B2.C0074n;
import d5.C1094p;
import d5.I;
import d5.a0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b implements d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1094p f10631n;
    public final I l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long[] f10632m;

    static {
        a0 a0Var = a0.l;
        C0074n c0074n = new C0074n(20);
        a0Var.getClass();
        f10631n = new C1094p(c0074n, a0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(d5.d0 r20) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Y2.b.<init>(d5.d0):void");
    }

    @Override // Y2.d
    public final int a(long j3) {
        int iB = AbstractC1343A.b(this.f10632m, j3, false);
        if (iB < this.l.size()) {
            return iB;
        }
        return -1;
    }

    @Override // Y2.d
    public final long d(int i7) {
        AbstractC1346c.c(i7 < this.l.size());
        return this.f10632m[i7];
    }

    @Override // Y2.d
    public final List g(long j3) {
        int iE = AbstractC1343A.e(this.f10632m, j3, false);
        return iE == -1 ? I.z() : (I) this.l.get(iE);
    }

    @Override // Y2.d
    public final int h() {
        return this.l.size();
    }
}
