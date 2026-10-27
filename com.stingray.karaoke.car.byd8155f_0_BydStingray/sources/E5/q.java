package E5;

import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    public abstract Object a(u uVar);

    public final Object b(String str) {
        C2319i c2319i = new C2319i();
        c2319i.c0(str);
        v vVar = new v(c2319i);
        Object objA = a(vVar);
        if (vVar.z() == 10) {
            return objA;
        }
        throw new A9.k("JSON document was not fully consumed.");
    }

    public final G5.b c() {
        return this instanceof G5.b ? (G5.b) this : new G5.b(this);
    }

    public abstract void d(x xVar, Object obj);
}
