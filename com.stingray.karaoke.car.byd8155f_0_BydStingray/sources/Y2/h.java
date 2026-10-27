package Y2;

import B2.C;
import B2.C0073m;
import B2.L;
import B2.r;
import B2.s;
import B2.v;
import e2.C1207o;
import e2.C1208p;
import e2.M;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import u2.T;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class h implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f10638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1208p f10639b;
    public final ArrayList c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public L f10641f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f10642g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f10643h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long[] f10644i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f10645j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f10640e = AbstractC1343A.f15198f;
    public final t d = new t();

    public h(l lVar, C1208p c1208p) {
        this.f10638a = lVar;
        C1207o c1207oA = c1208p.a();
        c1207oA.f14246m = e2.L.l("application/x-media3-cues");
        c1207oA.f14244j = c1208p.f14316n;
        c1207oA.f14233H = lVar.s();
        this.f10639b = new C1208p(c1207oA);
        this.c = new ArrayList();
        this.f10643h = 0;
        this.f10644i = AbstractC1343A.f15199g;
        this.f10645j = -9223372036854775807L;
    }

    @Override // B2.r
    public final void a() {
        if (this.f10643h == 5) {
            return;
        }
        this.f10638a.d();
        this.f10643h = 5;
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        AbstractC1346c.j(this.f10643h == 0);
        T t10 = (T) tVar;
        L lU = t10.u(0, 3);
        this.f10641f = lU;
        lU.d(this.f10639b);
        t10.e();
        t10.w(new C(-9223372036854775807L, new long[]{0}, new long[]{0}));
        this.f10643h = 1;
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) throws M, EOFException, InterruptedIOException {
        int i7 = this.f10643h;
        AbstractC1346c.j((i7 == 0 || i7 == 5) ? false : true);
        if (this.f10643h == 1) {
            int iQ = ((C0073m) sVar).f588n != -1 ? AbstractC2673a.q(((C0073m) sVar).f588n) : 1024;
            if (iQ > this.f10640e.length) {
                this.f10640e = new byte[iQ];
            }
            this.f10642g = 0;
            this.f10643h = 2;
        }
        int i10 = this.f10643h;
        ArrayList arrayList = this.c;
        if (i10 == 2) {
            byte[] bArr = this.f10640e;
            if (bArr.length == this.f10642g) {
                this.f10640e = Arrays.copyOf(bArr, bArr.length + 1024);
            }
            byte[] bArr2 = this.f10640e;
            int i11 = this.f10642g;
            C0073m c0073m = (C0073m) sVar;
            int iN = c0073m.n(bArr2, i11, bArr2.length - i11);
            if (iN != -1) {
                this.f10642g += iN;
            }
            long j3 = c0073m.f588n;
            if ((j3 != -1 && this.f10642g == j3) || iN == -1) {
                try {
                    long j10 = this.f10645j;
                    this.f10638a.h(this.f10640e, 0, this.f10642g, j10 != -9223372036854775807L ? new k(j10, true) : k.c, new G2.a(this, 3));
                    Collections.sort(arrayList);
                    this.f10644i = new long[arrayList.size()];
                    for (int i12 = 0; i12 < arrayList.size(); i12++) {
                        this.f10644i[i12] = ((g) arrayList.get(i12)).l;
                    }
                    this.f10640e = AbstractC1343A.f15198f;
                    this.f10643h = 4;
                } catch (RuntimeException e10) {
                    throw M.a(e10, "SubtitleParser failed.");
                }
            }
        }
        if (this.f10643h == 3) {
            if (((C0073m) sVar).s(((C0073m) sVar).f588n != -1 ? AbstractC2673a.q(((C0073m) sVar).f588n) : 1024) == -1) {
                long j11 = this.f10645j;
                for (int iE = j11 == -9223372036854775807L ? 0 : AbstractC1343A.e(this.f10644i, j11, true); iE < arrayList.size(); iE++) {
                    d((g) arrayList.get(iE));
                }
                this.f10643h = 4;
            }
        }
        return this.f10643h == 4 ? -1 : 0;
    }

    public final void d(g gVar) {
        AbstractC1346c.k(this.f10641f);
        byte[] bArr = gVar.f10637m;
        int length = bArr.length;
        t tVar = this.d;
        tVar.getClass();
        tVar.F(bArr.length, bArr);
        this.f10641f.a(tVar, length, 0);
        this.f10641f.b(gVar.l, 1, length, 0, null);
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        int i7 = this.f10643h;
        AbstractC1346c.j((i7 == 0 || i7 == 5) ? false : true);
        this.f10645j = j10;
        if (this.f10643h == 2) {
            this.f10643h = 1;
        }
        if (this.f10643h == 4) {
            this.f10643h = 3;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        return true;
    }
}
