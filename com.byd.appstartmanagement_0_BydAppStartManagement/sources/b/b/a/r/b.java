package b.b.a.r;

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
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Type, b.b.a.e<?>> f392a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class a<T> implements b.b.a.r.e<T> {
        public a() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: b.b.a.r.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class C0028b<T> implements b.b.a.r.e<T> {
        public C0028b() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class c<T> implements b.b.a.r.e<T> {
        public c() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class d<T> implements b.b.a.r.e<T> {
        public d() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new LinkedTreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class e<T> implements b.b.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.b.a.r.h f397a = b.b.a.r.h.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f398b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Type f399c;

        public e(Class cls, Type type) {
            this.f398b = cls;
            this.f399c = type;
        }

        @Override // b.b.a.r.e
        public T a() {
            try {
                return (T) this.f397a.d(this.f398b);
            } catch (Exception e) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f399c + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class f<T> implements b.b.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.e f401a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f402b;

        public f(b.b.a.e eVar, Type type) {
            this.f401a = eVar;
            this.f402b = type;
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) this.f401a.a(this.f402b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class g<T> implements b.b.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.e f404a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f405b;

        public g(b.b.a.e eVar, Type type) {
            this.f404a = eVar;
            this.f405b = type;
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) this.f404a.a(this.f405b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class h<T> implements b.b.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f407a;

        public h(Constructor constructor) {
            this.f407a = constructor;
        }

        @Override // b.b.a.r.e
        public T a() {
            try {
                return (T) this.f407a.newInstance(null);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Failed to invoke " + this.f407a + " with no args", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to invoke " + this.f407a + " with no args", e3.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class i<T> implements b.b.a.r.e<T> {
        public i() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class j<T> implements b.b.a.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f410a;

        public j(Type type) {
            this.f410a = type;
        }

        @Override // b.b.a.r.e
        public T a() {
            Type type = this.f410a;
            if (!(type instanceof ParameterizedType)) {
                throw new JsonIOException("Invalid EnumSet type: " + this.f410a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f410a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class k<T> implements b.b.a.r.e<T> {
        public k() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class l<T> implements b.b.a.r.e<T> {
        public l() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class m<T> implements b.b.a.r.e<T> {
        public m() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class n<T> implements b.b.a.r.e<T> {
        public n() {
        }

        @Override // b.b.a.r.e
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public b(Map<Type, b.b.a.e<?>> map) {
        this.f392a = map;
    }

    public <T> b.b.a.r.e<T> a(b.b.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        b.b.a.e<?> eVar = this.f392a.get(typeF);
        if (eVar != null) {
            return new f(eVar, typeF);
        }
        b.b.a.e<?> eVar2 = this.f392a.get(clsD);
        if (eVar2 != null) {
            return new g(eVar2, typeF);
        }
        b.b.a.r.e<T> eVarB = b(clsD);
        if (eVarB != null) {
            return eVarB;
        }
        b.b.a.r.e<T> eVarC = c(typeF, clsD);
        return eVarC != null ? eVarC : d(typeF, clsD);
    }

    public final <T> b.b.a.r.e<T> b(Class<? super T> cls) {
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

    public final <T> b.b.a.r.e<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new C0028b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(b.b.a.s.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).d())) ? new d() : new c();
        }
        return null;
    }

    public final <T> b.b.a.r.e<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public String toString() {
        return this.f392a.toString();
    }
}
