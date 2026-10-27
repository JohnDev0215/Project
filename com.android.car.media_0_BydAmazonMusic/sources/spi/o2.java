package spi;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o2 {
    public final Map<Type, b5<?>> a;
    public final q9 b = q9.a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class a<T> implements b8<T> {
        public final /* synthetic */ b5 a;
        public final /* synthetic */ Type b;

        public a(o2 o2Var, b5 b5Var, Type type) {
            this.a = b5Var;
            this.b = type;
        }

        @Override // spi.b8
        public final T a() {
            return (T) this.a.a();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class b<T> implements b8<T> {
        public final /* synthetic */ b5 a;
        public final /* synthetic */ Type b;

        public b(o2 o2Var, b5 b5Var, Type type) {
            this.a = b5Var;
            this.b = type;
        }

        @Override // spi.b8
        public final T a() {
            return (T) this.a.a();
        }
    }

    public o2(Map<Type, b5<?>> map) {
        this.a = map;
    }

    public final <T> b8<T> a(vb<T> vbVar) {
        p2 p2Var;
        Type type = vbVar.b;
        Class<? super T> cls = vbVar.a;
        b5<?> b5Var = this.a.get(type);
        if (b5Var != null) {
            return new a(this, b5Var, type);
        }
        b5<?> b5Var2 = this.a.get(cls);
        if (b5Var2 != null) {
            return new b(this, b5Var2, type);
        }
        b8<T> v2Var = null;
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.b.a(declaredConstructor);
            }
            p2Var = new p2(this, declaredConstructor);
        } catch (NoSuchMethodException unused) {
            p2Var = null;
        }
        if (p2Var != null) {
            return p2Var;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            v2Var = SortedSet.class.isAssignableFrom(cls) ? new q2(this) : EnumSet.class.isAssignableFrom(cls) ? new r2(this, type) : Set.class.isAssignableFrom(cls) ? new s2(this) : Queue.class.isAssignableFrom(cls) ? new t2(this) : new u2(this);
        } else if (Map.class.isAssignableFrom(cls)) {
            v2Var = ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new v2(this) : ConcurrentMap.class.isAssignableFrom(cls) ? new j2(this) : SortedMap.class.isAssignableFrom(cls) ? new k2(this) : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new vb(((ParameterizedType) type).getActualTypeArguments()[0]).a)) ? new m2(this) : new l2(this);
        }
        return v2Var != null ? v2Var : new n2(this, cls, type);
    }

    public final String toString() {
        return this.a.toString();
    }
}
