package c.b.b.r;

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
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Type, c.b.b.e<?>> f4174a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class a<T> implements c.b.b.r.e<T> {
        public a() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: c.b.b.r.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class C0095b<T> implements c.b.b.r.e<T> {
        public C0095b() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class c<T> implements c.b.b.r.e<T> {
        public c() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class d<T> implements c.b.b.r.e<T> {
        public d() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new LinkedTreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class e<T> implements c.b.b.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.b.r.h f4179a = c.b.b.r.h.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f4180b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Type f4181c;

        public e(Class cls, Type type) {
            this.f4180b = cls;
            this.f4181c = type;
        }

        @Override // c.b.b.r.e
        public T a() {
            try {
                return (T) this.f4179a.d(this.f4180b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f4181c + ". Register an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class f<T> implements c.b.b.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.b.b.e f4183a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f4184b;

        public f(c.b.b.e eVar, Type type) {
            this.f4183a = eVar;
            this.f4184b = type;
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) this.f4183a.a(this.f4184b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class g<T> implements c.b.b.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.b.b.e f4186a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f4187b;

        public g(c.b.b.e eVar, Type type) {
            this.f4186a = eVar;
            this.f4187b = type;
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) this.f4186a.a(this.f4187b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class h<T> implements c.b.b.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f4189a;

        public h(Constructor constructor) {
            this.f4189a = constructor;
        }

        @Override // c.b.b.r.e
        public T a() {
            try {
                return (T) this.f4189a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f4189a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f4189a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class i<T> implements c.b.b.r.e<T> {
        public i() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class j<T> implements c.b.b.r.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f4192a;

        public j(Type type) {
            this.f4192a = type;
        }

        @Override // c.b.b.r.e
        public T a() {
            Type type = this.f4192a;
            if (!(type instanceof ParameterizedType)) {
                throw new JsonIOException("Invalid EnumSet type: " + this.f4192a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f4192a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class k<T> implements c.b.b.r.e<T> {
        public k() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class l<T> implements c.b.b.r.e<T> {
        public l() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class m<T> implements c.b.b.r.e<T> {
        public m() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class n<T> implements c.b.b.r.e<T> {
        public n() {
        }

        @Override // c.b.b.r.e
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public b(Map<Type, c.b.b.e<?>> map) {
        this.f4174a = map;
    }

    public <T> c.b.b.r.e<T> a(c.b.b.s.a<T> aVar) {
        Type typeF = aVar.f();
        Class<? super T> clsD = aVar.d();
        c.b.b.e<?> eVar = this.f4174a.get(typeF);
        if (eVar != null) {
            return new f(eVar, typeF);
        }
        c.b.b.e<?> eVar2 = this.f4174a.get(clsD);
        if (eVar2 != null) {
            return new g(eVar2, typeF);
        }
        c.b.b.r.e<T> eVarB = b(clsD);
        if (eVarB != null) {
            return eVarB;
        }
        c.b.b.r.e<T> eVarC = c(typeF, clsD);
        return eVarC != null ? eVarC : d(typeF, clsD);
    }

    public final <T> c.b.b.r.e<T> b(Class<? super T> cls) {
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

    public final <T> c.b.b.r.e<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new C0095b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(c.b.b.s.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).d())) ? new d() : new c();
        }
        return null;
    }

    public final <T> c.b.b.r.e<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public String toString() {
        return this.f4174a.toString();
    }
}
