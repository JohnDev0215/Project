package D2;

import B2.C0064d;
import B2.C0073m;
import B2.r;
import B2.s;
import h2.AbstractC1343A;
import h2.t;
import io.sentry.hints.i;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f1139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0064d f1140b;
    public final boolean c;
    public final i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1141e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public B2.t f1142f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f1143g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f1144h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e[] f1145i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f1146j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public e f1147k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f1148m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f1149n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1150p;

    public b(int i7, i iVar) {
        this.d = iVar;
        this.c = (i7 & 1) == 0;
        this.f1139a = new t(12);
        this.f1140b = new C0064d(1);
        this.f1142f = new i(1);
        this.f1145i = new e[0];
        this.f1148m = -1L;
        this.f1149n = -1L;
        this.l = -1;
        this.f1144h = -9223372036854775807L;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f1141e = 0;
        if (this.c) {
            tVar = new P(tVar, this.d);
        }
        this.f1142f = tVar;
        this.f1146j = -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:162:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0109  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r23, B2.v r24) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 1102
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: D2.b.c(B2.s, B2.v):int");
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f1146j = -1L;
        this.f1147k = null;
        for (e eVar : this.f1145i) {
            if (eVar.f1163j == 0) {
                eVar.f1161h = 0;
            } else {
                eVar.f1161h = eVar.f1165m[AbstractC1343A.e(eVar.l, j3, true)];
            }
        }
        if (j3 != 0) {
            this.f1141e = 6;
        } else if (this.f1145i.length == 0) {
            this.f1141e = 0;
        } else {
            this.f1141e = 3;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        t tVar = this.f1139a;
        ((C0073m) sVar).k(tVar.f15241a, 0, 12, false);
        tVar.H(0);
        if (tVar.j() != 1179011410) {
            return false;
        }
        tVar.I(4);
        return tVar.j() == 541677121;
    }
}
