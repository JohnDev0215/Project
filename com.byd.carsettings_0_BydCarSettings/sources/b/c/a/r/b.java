package b.c.a.r;

import com.google.gson.JsonIOException;
import com.google.gson.internal.LinkedTreeMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* JADX INFO: compiled from: ConstructorConstructor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Type, b.c.a.e<?>> f2203a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class a<T> implements b.c.a.r.e<T> {
        public a() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: b.c.a.r.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class C0090b<T> implements b.c.a.r.e<T> {
        public C0090b() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class c<T> implements b.c.a.r.e<T> {
        public c() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class d<T> implements b.c.a.r.e<T> {
        public d() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new LinkedTreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class e<T> implements b.c.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.c.a.r.h f2208a = b.c.a.r.h.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f2209b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Type f2210c;

        public e(Class cls, Type type) {
            this.f2209b = cls;
            this.f2210c = type;
        }

        @Override // b.c.a.r.e
        public T a() {
            try {
                return (T) this.f2208a.d(this.f2209b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f2210c + ". Register an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class f<T> implements b.c.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.c.a.e f2212a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f2213b;

        public f(b.c.a.e eVar, Type type) {
            this.f2212a = eVar;
            this.f2213b = type;
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) this.f2212a.a(this.f2213b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class g<T> implements b.c.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.c.a.e f2215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f2216b;

        public g(b.c.a.e eVar, Type type) {
            this.f2215a = eVar;
            this.f2216b = type;
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) this.f2215a.a(this.f2216b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class h<T> implements b.c.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f2218a;

        public h(Constructor constructor) {
            this.f2218a = constructor;
        }

        @Override // b.c.a.r.e
        public T a() {
            try {
                return (T) this.f2218a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f2218a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f2218a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class i<T> implements b.c.a.r.e<T> {
        public i() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class j<T> implements b.c.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f2221a;

        public j(Type type) {
            this.f2221a = type;
        }

        @Override // b.c.a.r.e
        public T a() {
            Type type = this.f2221a;
            if (!(type instanceof ParameterizedType)) {
                throw new JsonIOException("Invalid EnumSet type: " + this.f2221a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f2221a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class k<T> implements b.c.a.r.e<T> {
        public k() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class l<T> implements b.c.a.r.e<T> {
        public l() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class m<T> implements b.c.a.r.e<T> {
        public m() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class n<T> implements b.c.a.r.e<T> {
        public n() {
        }

        @Override // b.c.a.r.e
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public b(Map<Type, b.c.a.e<?>> map) {
        this.f2203a = map;
    }

    public <T> b.c.a.r.e<T> a(b.c.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        b.c.a.e<?> eVar = this.f2203a.get(typeF);
        if (eVar != null) {
            return new f(eVar, typeF);
        }
        b.c.a.e<?> eVar2 = this.f2203a.get(clsD);
        if (eVar2 != null) {
            return new g(eVar2, typeF);
        }
        b.c.a.r.e<T> eVarB = b(clsD);
        if (eVarB != null) {
            return eVarB;
        }
        b.c.a.r.e<T> eVarC = c(typeF, clsD);
        return eVarC != null ? eVarC : d(typeF, clsD);
    }

    public final <T> b.c.a.r.e<T> b(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new h(declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final <T> b.c.a.r.e<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new C0090b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(b.c.a.s.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).d())) ? new d() : new c();
        }
        return null;
    }

    public final <T> b.c.a.r.e<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public String toString() {
        return this.f2203a.toString();
    }
}
