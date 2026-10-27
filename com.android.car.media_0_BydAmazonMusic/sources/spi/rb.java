package spi;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rb implements ob {
    public final /* synthetic */ Class a;
    public final /* synthetic */ nb b;

    public rb(Class cls, nb nbVar) {
        this.a = cls;
        this.b = nbVar;
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        if (vbVar.a == this.a) {
            return this.b;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sbK = c.k("Factory[type=");
        sbK.append(this.a.getName());
        sbK.append(",adapter=");
        sbK.append(this.b);
        sbK.append("]");
        return sbK.toString();
    }
}
