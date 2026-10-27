package defpackage;

import defpackage.m00;
import java.io.Serializable;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class hv implements m00, Serializable {
    public final m00 e;
    public final m00.b f;

    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    public static final class a implements Serializable {
        private static final long serialVersionUID = 0;
        public final m00[] e;

        public a(m00[] m00VarArr) {
            this.e = m00VarArr;
        }

        private final Object readResolve() {
            m00 m00VarPlus = dh0.e;
            for (m00 m00Var : this.e) {
                m00VarPlus = m00VarPlus.plus(m00Var);
            }
            return m00VarPlus;
        }
    }

    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    public static final class b extends z41 implements nr0<String, m00.b, String> {
        public static final b f = new b();

        public b() {
            super(2);
        }

        @Override // defpackage.nr0
        public final String d(String str, m00.b bVar) {
            String str2 = str;
            m00.b bVar2 = bVar;
            i21.e(str2, "acc");
            i21.e(bVar2, "element");
            if (str2.length() == 0) {
                return bVar2.toString();
            }
            return str2 + ", " + bVar2;
        }
    }

    /* JADX INFO: compiled from: CoroutineContextImpl.kt */
    public static final class c extends z41 implements nr0<vi3, m00.b, vi3> {
        public final /* synthetic */ m00[] f;
        public final /* synthetic */ da2 g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(m00[] m00VarArr, da2 da2Var) {
            super(2);
            this.f = m00VarArr;
            this.g = da2Var;
        }

        @Override // defpackage.nr0
        public final vi3 d(vi3 vi3Var, m00.b bVar) {
            m00.b bVar2 = bVar;
            i21.e(vi3Var, "<anonymous parameter 0>");
            i21.e(bVar2, "element");
            da2 da2Var = this.g;
            int i = da2Var.e;
            da2Var.e = i + 1;
            this.f[i] = bVar2;
            return vi3.a;
        }
    }

    public hv(m00.b bVar, m00 m00Var) {
        i21.e(m00Var, "left");
        i21.e(bVar, "element");
        this.e = m00Var;
        this.f = bVar;
    }

    private final Object writeReplace() {
        int iA = a();
        m00[] m00VarArr = new m00[iA];
        da2 da2Var = new da2();
        fold(vi3.a, new c(m00VarArr, da2Var));
        if (da2Var.e == iA) {
            return new a(m00VarArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final int a() {
        int i = 2;
        while (true) {
            m00 m00Var = this.e;
            this = m00Var instanceof hv ? (hv) m00Var : null;
            if (this == null) {
                return i;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this != obj) {
            if (!(obj instanceof hv)) {
                return false;
            }
            hv hvVar = (hv) obj;
            if (hvVar.a() != a()) {
                return false;
            }
            while (true) {
                m00.b bVar = this.f;
                if (!i21.a(hvVar.get(bVar.getKey()), bVar)) {
                    zA = false;
                    break;
                }
                m00 m00Var = this.e;
                if (!(m00Var instanceof hv)) {
                    i21.c(m00Var, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                    m00.b bVar2 = (m00.b) m00Var;
                    zA = i21.a(hvVar.get(bVar2.getKey()), bVar2);
                    break;
                }
                this = (hv) m00Var;
            }
            if (!zA) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.m00
    public final <R> R fold(R r, nr0<? super R, ? super m00.b, ? extends R> nr0Var) {
        return nr0Var.d((Object) this.e.fold(r, nr0Var), this.f);
    }

    @Override // defpackage.m00
    public final <E extends m00.b> E get(m00.c<E> cVar) {
        i21.e(cVar, "key");
        while (true) {
            E e = (E) this.f.get(cVar);
            if (e != null) {
                return e;
            }
            m00 m00Var = this.e;
            if (!(m00Var instanceof hv)) {
                return (E) m00Var.get(cVar);
            }
            this = (hv) m00Var;
        }
    }

    public final int hashCode() {
        return this.f.hashCode() + this.e.hashCode();
    }

    @Override // defpackage.m00
    public final m00 minusKey(m00.c<?> cVar) {
        i21.e(cVar, "key");
        m00.b bVar = this.f;
        m00.b bVar2 = bVar.get(cVar);
        m00 m00Var = this.e;
        if (bVar2 != null) {
            return m00Var;
        }
        m00 m00VarMinusKey = m00Var.minusKey(cVar);
        return m00VarMinusKey == m00Var ? this : m00VarMinusKey == dh0.e ? bVar : new hv(bVar, m00VarMinusKey);
    }

    @Override // defpackage.m00
    public final m00 plus(m00 m00Var) {
        return m00.a.a(this, m00Var);
    }

    public final String toString() {
        return "[" + ((String) fold("", b.f)) + ']';
    }
}
