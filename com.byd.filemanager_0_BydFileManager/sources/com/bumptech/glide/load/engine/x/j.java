package com.bumptech.glide.load.engine.x;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: LruArrayPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements com.bumptech.glide.load.engine.x.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<a, Object> f3104a = new h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f3105b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class<?>, NavigableMap<Integer, Integer>> f3106c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, com.bumptech.glide.load.engine.x.a<?>> f3107d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3108e;
    private int f;

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f3109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3110b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class<?> f3111c;

        a(b bVar) {
            this.f3109a = bVar;
        }

        @Override // com.bumptech.glide.load.engine.x.m
        public void a() {
            this.f3109a.c(this);
        }

        void b(int i, Class<?> cls) {
            this.f3110b = i;
            this.f3111c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f3110b == aVar.f3110b && this.f3111c == aVar.f3111c;
        }

        public int hashCode() {
            int i = this.f3110b * 31;
            Class<?> cls = this.f3111c;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            return "Key{size=" + this.f3110b + "array=" + this.f3111c + '}';
        }
    }

    /* JADX INFO: compiled from: LruArrayPool.java */
    private static final class b extends d<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.engine.x.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a() {
            return new a(this);
        }

        a e(int i, Class<?> cls) {
            a aVarB = b();
            aVarB.b(i, cls);
            return aVarB;
        }
    }

    public j(int i) {
        this.f3108e = i;
    }

    private void f(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapM = m(cls);
        Integer num = (Integer) navigableMapM.get(Integer.valueOf(i));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapM.remove(Integer.valueOf(i));
                return;
            } else {
                navigableMapM.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
    }

    private void g() {
        h(this.f3108e);
    }

    private void h(int i) {
        while (this.f > i) {
            Object objF = this.f3104a.f();
            com.bumptech.glide.o.j.d(objF);
            com.bumptech.glide.load.engine.x.a aVarI = i(objF);
            this.f -= aVarI.b(objF) * aVarI.c();
            f(aVarI.b(objF), objF.getClass());
            if (Log.isLoggable(aVarI.a(), 2)) {
                Log.v(aVarI.a(), "evicted: " + aVarI.b(objF));
            }
        }
    }

    private <T> com.bumptech.glide.load.engine.x.a<T> i(T t) {
        return j(t.getClass());
    }

    private <T> com.bumptech.glide.load.engine.x.a<T> j(Class<T> cls) {
        com.bumptech.glide.load.engine.x.a<T> gVar = (com.bumptech.glide.load.engine.x.a) this.f3107d.get(cls);
        if (gVar == null) {
            if (cls.equals(int[].class)) {
                gVar = new i();
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
                }
                gVar = new g();
            }
            this.f3107d.put(cls, gVar);
        }
        return gVar;
    }

    private <T> T k(a aVar) {
        return (T) this.f3104a.a(aVar);
    }

    private <T> T l(a aVar, Class<T> cls) {
        com.bumptech.glide.load.engine.x.a<T> aVarJ = j(cls);
        T t = (T) k(aVar);
        if (t != null) {
            this.f -= aVarJ.b(t) * aVarJ.c();
            f(aVarJ.b(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(aVarJ.a(), 2)) {
            Log.v(aVarJ.a(), "Allocated " + aVar.f3110b + " bytes");
        }
        return aVarJ.newArray(aVar.f3110b);
    }

    private NavigableMap<Integer, Integer> m(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f3106c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f3106c.put(cls, treeMap);
        return treeMap;
    }

    private boolean n() {
        int i = this.f;
        return i == 0 || this.f3108e / i >= 2;
    }

    private boolean o(int i) {
        return i <= this.f3108e / 2;
    }

    private boolean p(int i, Integer num) {
        return num != null && (n() || num.intValue() <= i * 8);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized void a(int i) {
        try {
            if (i >= 40) {
                b();
            } else if (i >= 20 || i == 15) {
                h(this.f3108e / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized void b() {
        h(0);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> T c(int i, Class<T> cls) {
        return (T) l(this.f3105b.e(i, cls), cls);
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> void d(T t) {
        Class<?> cls = t.getClass();
        com.bumptech.glide.load.engine.x.a<T> aVarJ = j(cls);
        int iB = aVarJ.b(t);
        int iC = aVarJ.c() * iB;
        if (o(iC)) {
            a aVarE = this.f3105b.e(iB, cls);
            this.f3104a.d(aVarE, t);
            NavigableMap<Integer, Integer> navigableMapM = m(cls);
            Integer num = (Integer) navigableMapM.get(Integer.valueOf(aVarE.f3110b));
            Integer numValueOf = Integer.valueOf(aVarE.f3110b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapM.put(numValueOf, Integer.valueOf(iIntValue));
            this.f += iC;
            g();
        }
    }

    @Override // com.bumptech.glide.load.engine.x.b
    public synchronized <T> T e(int i, Class<T> cls) {
        Integer numCeilingKey;
        numCeilingKey = m(cls).ceilingKey(Integer.valueOf(i));
        return (T) l(p(i, numCeilingKey) ? this.f3105b.e(numCeilingKey.intValue(), cls) : this.f3105b.e(i, cls), cls);
    }
}
