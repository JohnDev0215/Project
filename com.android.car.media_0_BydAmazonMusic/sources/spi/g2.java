package spi;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g2 implements ob {
    public final o2 a;

    /* JADX INFO: compiled from: CollectionTypeAdapterFactory.java */
    public static final class a<E> extends nb<Collection<E>> {
        public final nb<E> a;
        public final b8<? extends Collection<E>> b;

        public a(j4 j4Var, Type type, nb<E> nbVar, b8<? extends Collection<E>> b8Var) {
            this.a = new pb(j4Var, nbVar, type);
            this.b = b8Var;
        }

        @Override // spi.nb
        public final Object a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            Collection<E> collectionA = this.b.a();
            o5Var.a();
            while (o5Var.i()) {
                collectionA.add(this.a.a(o5Var));
            }
            o5Var.e();
            return collectionA;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Object obj) throws IOException {
            Collection collection = (Collection) obj;
            if (collection == null) {
                v5Var.i();
                return;
            }
            v5Var.b();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.a.b(v5Var, it.next());
            }
            v5Var.e();
        }
    }

    public g2(o2 o2Var) {
        this.a = o2Var;
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        Type type = vbVar.b;
        Class<? super T> cls = vbVar.a;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type typeF = b.f(type, cls, Collection.class);
        if (typeF instanceof WildcardType) {
            typeF = ((WildcardType) typeF).getUpperBounds()[0];
        }
        Class cls2 = typeF instanceof ParameterizedType ? ((ParameterizedType) typeF).getActualTypeArguments()[0] : Object.class;
        return new a(j4Var, cls2, j4Var.e(new vb<>(cls2)), this.a.a(vbVar));
    }
}
