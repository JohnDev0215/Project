package spi;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r<E> extends nb<Object> {
    public static final a c = new a();
    public final Class<E> a;
    public final nb<E> b;

    /* JADX INFO: compiled from: ArrayTypeAdapter.java */
    public static class a implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            Type type = vbVar.b;
            boolean z = type instanceof GenericArrayType;
            if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
            return new r(j4Var, j4Var.e(new vb<>(genericComponentType)), b.e(genericComponentType));
        }
    }

    public r(j4 j4Var, nb<E> nbVar, Class<E> cls) {
        this.b = new pb(j4Var, nbVar, cls);
        this.a = cls;
    }

    @Override // spi.nb
    public final Object a(o5 o5Var) throws IOException {
        if (o5Var.v() == s5.NULL) {
            o5Var.r();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        o5Var.a();
        while (o5Var.i()) {
            arrayList.add(this.b.a(o5Var));
        }
        o5Var.e();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.a, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // spi.nb
    public final void b(v5 v5Var, Object obj) throws IOException {
        if (obj == null) {
            v5Var.i();
            return;
        }
        v5Var.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.b(v5Var, (E) Array.get(obj, i));
        }
        v5Var.e();
    }
}
