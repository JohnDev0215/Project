package g.a.a.v2;

import g.a.a.l;
import g.a.a.q;
import g.a.a.t;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f4084c;

    public b(t tVar) {
        this.f4084c = tVar;
    }

    public static b h(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(t.o(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this.f4084c;
    }

    public a g() {
        if (this.f4084c.size() == 0) {
            return null;
        }
        return a.g(this.f4084c.r(0));
    }

    public a[] i() {
        int size = this.f4084c.size();
        a[] aVarArr = new a[size];
        for (int i2 = 0; i2 != size; i2++) {
            aVarArr[i2] = a.g(this.f4084c.r(i2));
        }
        return aVarArr;
    }

    public boolean j() {
        return this.f4084c.size() > 1;
    }
}
