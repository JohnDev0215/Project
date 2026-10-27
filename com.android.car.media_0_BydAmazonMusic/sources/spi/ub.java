package spi;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ub implements ob {
    public final /* synthetic */ Class a;
    public final /* synthetic */ nb b;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public class a extends nb<Object> {
        public final /* synthetic */ Class a;

        public a(Class cls) {
            this.a = cls;
        }

        @Override // spi.nb
        public final Object a(o5 o5Var) {
            Object objA = ub.this.b.a(o5Var);
            if (objA == null || this.a.isInstance(objA)) {
                return objA;
            }
            StringBuilder sbK = c.k("Expected a ");
            sbK.append(this.a.getName());
            sbK.append(" but was ");
            sbK.append(objA.getClass().getName());
            throw new r5(sbK.toString());
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Object obj) {
            ub.this.b.b(v5Var, obj);
        }
    }

    public ub(Class cls, nb nbVar) {
        this.a = cls;
        this.b = nbVar;
    }

    @Override // spi.ob
    public final <T2> nb<T2> a(j4 j4Var, vb<T2> vbVar) {
        Class<? super T2> cls = vbVar.a;
        if (this.a.isAssignableFrom(cls)) {
            return new a(cls);
        }
        return null;
    }

    public final String toString() {
        StringBuilder sbK = c.k("Factory[typeHierarchy=");
        sbK.append(this.a.getName());
        sbK.append(",adapter=");
        sbK.append(this.b);
        sbK.append("]");
        return sbK.toString();
    }
}
