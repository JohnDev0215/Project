package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class n62 extends p62 implements Iterator {
    public m62 c;
    public boolean e = true;
    public final /* synthetic */ q62 k;

    public n62(q62 q62Var) {
        this.k = q62Var;
    }

    @Override // p.p62
    public final void a(m62 m62Var) {
        m62 m62Var2 = this.c;
        if (m62Var == m62Var2) {
            m62 m62Var3 = m62Var2.l;
            this.c = m62Var3;
            this.e = m62Var3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.e) {
            return this.k.c != null;
        }
        m62 m62Var = this.c;
        return (m62Var == null || m62Var.k == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.e) {
            this.e = false;
            this.c = this.k.c;
        } else {
            m62 m62Var = this.c;
            this.c = m62Var != null ? m62Var.k : null;
        }
        return this.c;
    }
}
