package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class o62 extends p62 implements Iterator {
    public m62 c;
    public m62 e;

    @Override // p.p62
    public final void a(m62 m62Var) {
        m62 m62Var2;
        m62 m62Var3;
        m62 m62Var4 = null;
        if (this.c == m62Var && m62Var == this.e) {
            this.e = null;
            this.c = null;
        }
        m62 m62Var5 = this.c;
        if (m62Var5 == m62Var) {
            switch (((l62) this).k) {
                case 0:
                    m62Var3 = m62Var5.l;
                    break;
                default:
                    m62Var3 = m62Var5.k;
                    break;
            }
            this.c = m62Var3;
        }
        m62 m62Var6 = this.e;
        if (m62Var6 == m62Var) {
            m62 m62Var7 = this.c;
            if (m62Var6 != m62Var7 && m62Var7 != null) {
                switch (((l62) this).k) {
                    case 0:
                        m62Var2 = m62Var6.k;
                        break;
                    default:
                        m62Var2 = m62Var6.l;
                        break;
                }
                m62Var4 = m62Var2;
            }
            this.e = m62Var4;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.e != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        m62 m62Var;
        m62 m62Var2 = this.e;
        m62 m62Var3 = this.c;
        if (m62Var2 != m62Var3 && m62Var3 != null) {
            switch (((l62) this).k) {
                case 0:
                    m62Var = m62Var2.k;
                    break;
                default:
                    m62Var = m62Var2.l;
                    break;
            }
        } else {
            m62Var = null;
        }
        this.e = m62Var;
        return m62Var2;
    }
}
