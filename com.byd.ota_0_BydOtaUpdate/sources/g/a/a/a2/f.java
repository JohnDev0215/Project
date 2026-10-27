package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;
import g.a.f.a;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class f extends l implements Iterable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.e[] f3756c;

    public f(r rVar) {
        this.f3756c = new g.a.a.e[rVar.size()];
        int i2 = 0;
        while (true) {
            g.a.a.e[] eVarArr = this.f3756c;
            if (i2 == eVarArr.length) {
                return;
            }
            eVarArr[i2] = e.j(rVar.q(i2));
            i2++;
        }
    }

    public f(e[] eVarArr) {
        g.a.a.e[] eVarArr2 = new g.a.a.e[eVarArr.length];
        this.f3756c = eVarArr2;
        System.arraycopy(eVarArr, 0, eVarArr2, 0, eVarArr.length);
    }

    public static f g(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return new f(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return new c1(this.f3756c);
    }

    @Override // java.lang.Iterable
    public Iterator<g.a.a.e> iterator() {
        return new a.C0097a(this.f3756c);
    }
}
