package p;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bm0 extends q62 {
    public final HashMap m = new HashMap();

    @Override // p.q62
    public final m62 a(Object obj) {
        return (m62) this.m.get(obj);
    }

    @Override // p.q62
    public final Object b(Object obj) {
        Object objB = super.b(obj);
        this.m.remove(obj);
        return objB;
    }

    public final Object c(Object obj, Object obj2) {
        m62 m62VarA = a(obj);
        if (m62VarA != null) {
            return m62VarA.e;
        }
        HashMap map = this.m;
        m62 m62Var = new m62(obj, obj2);
        this.l++;
        m62 m62Var2 = this.e;
        if (m62Var2 == null) {
            this.c = m62Var;
            this.e = m62Var;
        } else {
            m62Var2.k = m62Var;
            m62Var.l = m62Var2;
            this.e = m62Var;
        }
        map.put(obj, m62Var);
        return null;
    }
}
