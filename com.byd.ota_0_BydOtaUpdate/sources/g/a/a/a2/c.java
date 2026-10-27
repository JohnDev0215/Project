package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class c extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.p2.f f3750c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.w2.g[] f3751d;

    public c(g.a.a.p2.f fVar, g.a.a.w2.g[] gVarArr) {
        this.f3750c = fVar;
        g.a.a.w2.g[] gVarArr2 = new g.a.a.w2.g[gVarArr.length];
        this.f3751d = gVarArr2;
        System.arraycopy(gVarArr, 0, gVarArr2, 0, gVarArr.length);
    }

    public c(r rVar) {
        int i2 = 0;
        this.f3750c = g.a.a.p2.f.i(rVar.q(0));
        r rVarN = r.n(rVar.q(1));
        this.f3751d = new g.a.a.w2.g[rVarN.size()];
        while (true) {
            g.a.a.w2.g[] gVarArr = this.f3751d;
            if (i2 == gVarArr.length) {
                return;
            }
            gVarArr[i2] = g.a.a.w2.g.h(rVarN.q(i2));
            i2++;
        }
    }

    public static c i(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj != null) {
            return new c(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3750c);
        fVar.a(new c1(this.f3751d));
        return new c1(fVar);
    }

    public g.a.a.w2.g[] g() {
        g.a.a.w2.g[] gVarArr = this.f3751d;
        g.a.a.w2.g[] gVarArr2 = new g.a.a.w2.g[gVarArr.length];
        System.arraycopy(gVarArr, 0, gVarArr2, 0, gVarArr.length);
        return gVarArr2;
    }

    public g.a.a.p2.f h() {
        return this.f3750c;
    }
}
