package com.google.gson.internal;

import com.google.gson.JsonIOException;
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
    private final Map<Type, com.google.gson.e<?>> f4007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.l.b f4008b = com.google.gson.internal.l.b.a();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class a<T> implements com.google.gson.internal.f<T> {
        a() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.gson.internal.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class C0074b<T> implements com.google.gson.internal.f<T> {
        C0074b() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class c<T> implements com.google.gson.internal.f<T> {
        c() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class d<T> implements com.google.gson.internal.f<T> {
        d() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new LinkedTreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class e<T> implements com.google.gson.internal.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.google.gson.internal.j f4013a = com.google.gson.internal.j.b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4014b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Type f4015c;

        e(Class cls, Type type) {
            this.f4014b = cls;
            this.f4015c = type;
        }

        @Override // com.google.gson.internal.f
        public T a() {
            try {
                return (T) this.f4013a.c(this.f4014b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f4015c + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class f<T> implements com.google.gson.internal.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.gson.e f4017a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f4018b;

        f(com.google.gson.e eVar, Type type) {
            this.f4017a = eVar;
            this.f4018b = type;
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) this.f4017a.a(this.f4018b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class g<T> implements com.google.gson.internal.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.gson.e f4020a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f4021b;

        g(com.google.gson.e eVar, Type type) {
            this.f4020a = eVar;
            this.f4021b = type;
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) this.f4020a.a(this.f4021b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class h<T> implements com.google.gson.internal.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f4023a;

        h(Constructor constructor) {
            this.f4023a = constructor;
        }

        @Override // com.google.gson.internal.f
        public T a() {
            try {
                return (T) this.f4023a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f4023a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f4023a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class i<T> implements com.google.gson.internal.f<T> {
        i() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class j<T> implements com.google.gson.internal.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f4026a;

        j(Type type) {
            this.f4026a = type;
        }

        @Override // com.google.gson.internal.f
        public T a() {
            Type type = this.f4026a;
            if (!(type instanceof ParameterizedType)) {
                throw new JsonIOException("Invalid EnumSet type: " + this.f4026a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + this.f4026a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class k<T> implements com.google.gson.internal.f<T> {
        k() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class l<T> implements com.google.gson.internal.f<T> {
        l() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class m<T> implements com.google.gson.internal.f<T> {
        m() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class n<T> implements com.google.gson.internal.f<T> {
        n() {
        }

        @Override // com.google.gson.internal.f
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public b(Map<Type, com.google.gson.e<?>> map) {
        this.f4007a = map;
    }

    private <T> com.google.gson.internal.f<T> b(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.f4008b.b(declaredConstructor);
            }
            return new h(declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> com.google.gson.internal.f<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new C0074b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(com.google.gson.s.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).c())) ? new d() : new c();
        }
        return null;
    }

    private <T> com.google.gson.internal.f<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public <T> com.google.gson.internal.f<T> a(com.google.gson.s.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        com.google.gson.e<?> eVar = this.f4007a.get(typeE);
        if (eVar != null) {
            return new f(eVar, typeE);
        }
        com.google.gson.e<?> eVar2 = this.f4007a.get(clsC);
        if (eVar2 != null) {
            return new g(eVar2, typeE);
        }
        com.google.gson.internal.f<T> fVarB = b(clsC);
        if (fVarB != null) {
            return fVarB;
        }
        com.google.gson.internal.f<T> fVarC = c(typeE, clsC);
        return fVarC != null ? fVarC : d(typeE, clsC);
    }

    public String toString() {
        return this.f4007a.toString();
    }
}
