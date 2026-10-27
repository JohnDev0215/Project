package c.b.a.t;

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
    public final Map<Type, c.b.a.g<?>> f2916a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class a<T> implements c.b.a.t.e<T> {
        public a() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: c.b.a.t.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class C0067b<T> implements c.b.a.t.e<T> {
        public C0067b() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class c<T> implements c.b.a.t.e<T> {
        public c() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class d<T> implements c.b.a.t.e<T> {
        public d() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new LinkedTreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class e<T> implements c.b.a.t.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.a.t.h f2921a = c.b.a.t.h.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f2922b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Type f2923c;

        public e(Class cls, Type type) {
            this.f2922b = cls;
            this.f2923c = type;
        }

        @Override // c.b.a.t.e
        public T a() {
            try {
                return (T) this.f2921a.d(this.f2922b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f2923c + ". Register an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class f<T> implements c.b.a.t.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.b.a.g f2925a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f2926b;

        public f(c.b.a.g gVar, Type type) {
            this.f2925a = gVar;
            this.f2926b = type;
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) this.f2925a.a(this.f2926b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class g<T> implements c.b.a.t.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.b.a.g f2928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Type f2929b;

        public g(c.b.a.g gVar, Type type) {
            this.f2928a = gVar;
            this.f2929b = type;
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) this.f2928a.a(this.f2929b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class h<T> implements c.b.a.t.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Constructor f2931a;

        public h(Constructor constructor) {
            this.f2931a = constructor;
        }

        @Override // c.b.a.t.e
        public T a() {
            try {
                return (T) this.f2931a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f2931a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f2931a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class i<T> implements c.b.a.t.e<T> {
        public i() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class j<T> implements c.b.a.t.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f2934a;

        public j(Type type) {
            this.f2934a = type;
        }

        @Override // c.b.a.t.e
        public T a() {
            Type type = this.f2934a;
            if (!(type instanceof ParameterizedType)) {
                throw new JsonIOException("Invalid EnumSet type: " + this.f2934a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f2934a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class k<T> implements c.b.a.t.e<T> {
        public k() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class l<T> implements c.b.a.t.e<T> {
        public l() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class m<T> implements c.b.a.t.e<T> {
        public m() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    public class n<T> implements c.b.a.t.e<T> {
        public n() {
        }

        @Override // c.b.a.t.e
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public b(Map<Type, c.b.a.g<?>> map) {
        this.f2916a = map;
    }

    public <T> c.b.a.t.e<T> a(c.b.a.u.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        c.b.a.g<?> gVar = this.f2916a.get(typeE);
        if (gVar != null) {
            return new f(gVar, typeE);
        }
        c.b.a.g<?> gVar2 = this.f2916a.get(clsC);
        if (gVar2 != null) {
            return new g(gVar2, typeE);
        }
        c.b.a.t.e<T> eVarB = b(clsC);
        if (eVarB != null) {
            return eVarB;
        }
        c.b.a.t.e<T> eVarC = c(typeE, clsC);
        return eVarC != null ? eVarC : d(typeE, clsC);
    }

    public final <T> c.b.a.t.e<T> b(Class<? super T> cls) {
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

    public final <T> c.b.a.t.e<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new C0067b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(c.b.a.u.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).c())) ? new d() : new c();
        }
        return null;
    }

    public final <T> c.b.a.t.e<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public String toString() {
        return this.f2916a.toString();
    }
}
