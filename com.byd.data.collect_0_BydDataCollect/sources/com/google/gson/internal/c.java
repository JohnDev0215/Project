package com.google.gson.internal;

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
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Type, com.google.gson.f<?>> f4537a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.m.b f4538b = com.google.gson.internal.m.b.a();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class a<T> implements com.google.gson.internal.h<T> {
        a() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new ConcurrentHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class b<T> implements com.google.gson.internal.h<T> {
        b() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new TreeMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.gson.internal.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class C0085c<T> implements com.google.gson.internal.h<T> {
        C0085c() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new LinkedHashMap();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class d<T> implements com.google.gson.internal.h<T> {
        d() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new com.google.gson.internal.g();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class e<T> implements com.google.gson.internal.h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.google.gson.internal.l f4543a = com.google.gson.internal.l.b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4544b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Type f4545c;

        e(Class cls, Type type) {
            this.f4544b = cls;
            this.f4545c = type;
        }

        @Override // com.google.gson.internal.h
        public T a() {
            try {
                return (T) this.f4543a.c(this.f4544b);
            } catch (Exception e2) {
                throw new RuntimeException("Unable to invoke no-args constructor for " + this.f4545c + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e2);
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class f<T> implements com.google.gson.internal.h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.gson.f f4547a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f4548b;

        f(com.google.gson.f fVar, Type type) {
            this.f4547a = fVar;
            this.f4548b = type;
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) this.f4547a.a(this.f4548b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class g<T> implements com.google.gson.internal.h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.gson.f f4550a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Type f4551b;

        g(com.google.gson.f fVar, Type type) {
            this.f4550a = fVar;
            this.f4551b = type;
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) this.f4550a.a(this.f4551b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class h<T> implements com.google.gson.internal.h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f4553a;

        h(Constructor constructor) {
            this.f4553a = constructor;
        }

        @Override // com.google.gson.internal.h
        public T a() {
            try {
                return (T) this.f4553a.newInstance(null);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Failed to invoke " + this.f4553a + " with no args", e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to invoke " + this.f4553a + " with no args", e4.getTargetException());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class i<T> implements com.google.gson.internal.h<T> {
        i() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new TreeSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class j<T> implements com.google.gson.internal.h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Type f4556a;

        j(Type type) {
            this.f4556a = type;
        }

        @Override // com.google.gson.internal.h
        public T a() {
            Type type = this.f4556a;
            if (!(type instanceof ParameterizedType)) {
                throw new com.google.gson.k("Invalid EnumSet type: " + this.f4556a.toString());
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new com.google.gson.k("Invalid EnumSet type: " + this.f4556a.toString());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class k<T> implements com.google.gson.internal.h<T> {
        k() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new LinkedHashSet();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class l<T> implements com.google.gson.internal.h<T> {
        l() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new ArrayDeque();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class m<T> implements com.google.gson.internal.h<T> {
        m() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: ConstructorConstructor.java */
    class n<T> implements com.google.gson.internal.h<T> {
        n() {
        }

        @Override // com.google.gson.internal.h
        public T a() {
            return (T) new ConcurrentSkipListMap();
        }
    }

    public c(Map<Type, com.google.gson.f<?>> map) {
        this.f4537a = map;
    }

    private <T> com.google.gson.internal.h<T> b(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.f4538b.b(declaredConstructor);
            }
            return new h(declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> com.google.gson.internal.h<T> c(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new i() : EnumSet.class.isAssignableFrom(cls) ? new j(type) : Set.class.isAssignableFrom(cls) ? new k() : Queue.class.isAssignableFrom(cls) ? new l() : new m();
        }
        if (Map.class.isAssignableFrom(cls)) {
            return ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new n() : ConcurrentMap.class.isAssignableFrom(cls) ? new a() : SortedMap.class.isAssignableFrom(cls) ? new b() : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(com.google.gson.v.a.b(((ParameterizedType) type).getActualTypeArguments()[0]).c())) ? new d() : new C0085c();
        }
        return null;
    }

    private <T> com.google.gson.internal.h<T> d(Type type, Class<? super T> cls) {
        return new e(cls, type);
    }

    public <T> com.google.gson.internal.h<T> a(com.google.gson.v.a<T> aVar) {
        Type typeE = aVar.e();
        Class<? super T> clsC = aVar.c();
        com.google.gson.f<?> fVar = this.f4537a.get(typeE);
        if (fVar != null) {
            return new f(fVar, typeE);
        }
        com.google.gson.f<?> fVar2 = this.f4537a.get(clsC);
        if (fVar2 != null) {
            return new g(fVar2, typeE);
        }
        com.google.gson.internal.h<T> hVarB = b(clsC);
        if (hVarB != null) {
            return hVarB;
        }
        com.google.gson.internal.h<T> hVarC = c(typeE, clsC);
        return hVarC != null ? hVarC : d(typeE, clsC);
    }

    public String toString() {
        return this.f4537a.toString();
    }
}
