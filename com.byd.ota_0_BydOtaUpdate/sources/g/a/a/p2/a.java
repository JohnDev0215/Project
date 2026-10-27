package g.a.a.p2;

import g.a.a.f0;
import g.a.a.o1;

/* JADX INFO: loaded from: classes.dex */
public class a extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c[] f3954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3955d;

    public a(g.a.a.r rVar) {
        this.f3955d = true;
        this.f3954c = new c[rVar.size()];
        int i2 = 0;
        while (true) {
            c[] cVarArr = this.f3954c;
            if (i2 == cVarArr.length) {
                this.f3955d = rVar instanceof f0;
                return;
            } else {
                cVarArr[i2] = c.i(rVar.q(i2));
                i2++;
            }
        }
    }

    public a(c[] cVarArr) {
        this.f3955d = true;
        this.f3954c = cVarArr;
    }

    public static a h(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        int i2 = 0;
        while (true) {
            c[] cVarArr = this.f3954c;
            if (i2 == cVarArr.length) {
                break;
            }
            fVar.a(cVarArr[i2]);
            i2++;
        }
        return this.f3955d ? new f0(fVar) : new o1(fVar);
    }

    public c[] g() {
        return this.f3954c;
    }
}
