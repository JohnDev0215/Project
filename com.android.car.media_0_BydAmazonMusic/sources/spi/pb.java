package spi;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import spi.s9;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pb<T> extends nb<T> {
    public final j4 a;
    public final nb<T> b;
    public final Type c;

    public pb(j4 j4Var, nb<T> nbVar, Type type) {
        this.a = j4Var;
        this.b = nbVar;
        this.c = type;
    }

    @Override // spi.nb
    public final T a(o5 o5Var) {
        return this.b.a(o5Var);
    }

    @Override // spi.nb
    public final void b(v5 v5Var, T t) {
        nb<T> nbVarE = this.b;
        Type type = this.c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.c) {
            nbVarE = this.a.e(new vb<>(type));
            if (nbVarE instanceof s9.a) {
                nb<T> nbVar = this.b;
                if (!(nbVar instanceof s9.a)) {
                    nbVarE = nbVar;
                }
            }
        }
        nbVarE.b(v5Var, t);
    }
}
