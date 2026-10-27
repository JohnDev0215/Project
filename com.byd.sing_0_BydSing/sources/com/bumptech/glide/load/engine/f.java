package com.bumptech.glide.load.engine;

import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.j.n;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DecodeHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class f<Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n.a<?>> f2776a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.c> f2777b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.d f2778c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f2779d;
    private int e;
    private int f;
    private Class<?> g;
    private DecodeJob.e h;
    private com.bumptech.glide.load.e i;
    private Map<Class<?>, com.bumptech.glide.load.h<?>> j;
    private Class<Transcode> k;
    private boolean l;
    private boolean m;
    private com.bumptech.glide.load.c n;
    private Priority o;
    private h p;
    private boolean q;
    private boolean r;

    f() {
    }

    void a() {
        this.f2778c = null;
        this.f2779d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.f2776a.clear();
        this.l = false;
        this.f2777b.clear();
        this.m = false;
    }

    com.bumptech.glide.load.engine.x.b b() {
        return this.f2778c.a();
    }

    List<com.bumptech.glide.load.c> c() {
        if (!this.m) {
            this.m = true;
            this.f2777b.clear();
            List<n.a<?>> listG = g();
            int size = listG.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVar = listG.get(i);
                if (!this.f2777b.contains(aVar.f2996a)) {
                    this.f2777b.add(aVar.f2996a);
                }
                for (int i2 = 0; i2 < aVar.f2997b.size(); i2++) {
                    if (!this.f2777b.contains(aVar.f2997b.get(i2))) {
                        this.f2777b.add(aVar.f2997b.get(i2));
                    }
                }
            }
        }
        return this.f2777b;
    }

    com.bumptech.glide.load.engine.y.a d() {
        return this.h.a();
    }

    h e() {
        return this.p;
    }

    int f() {
        return this.f;
    }

    List<n.a<?>> g() {
        if (!this.l) {
            this.l = true;
            this.f2776a.clear();
            List listI = this.f2778c.g().i(this.f2779d);
            int size = listI.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVarA = ((com.bumptech.glide.load.j.n) listI.get(i)).a(this.f2779d, this.e, this.f, this.i);
                if (aVarA != null) {
                    this.f2776a.add(aVarA);
                }
            }
        }
        return this.f2776a;
    }

    <Data> q<Data, ?, Transcode> h(Class<Data> cls) {
        return this.f2778c.g().h(cls, this.g, this.k);
    }

    Class<?> i() {
        return this.f2779d.getClass();
    }

    List<com.bumptech.glide.load.j.n<File, ?>> j(File file) throws Registry.NoModelLoaderAvailableException {
        return this.f2778c.g().i(file);
    }

    com.bumptech.glide.load.e k() {
        return this.i;
    }

    Priority l() {
        return this.o;
    }

    List<Class<?>> m() {
        return this.f2778c.g().j(this.f2779d.getClass(), this.g, this.k);
    }

    <Z> com.bumptech.glide.load.g<Z> n(s<Z> sVar) {
        return this.f2778c.g().k(sVar);
    }

    com.bumptech.glide.load.c o() {
        return this.n;
    }

    <X> com.bumptech.glide.load.a<X> p(X x) throws Registry.NoSourceEncoderAvailableException {
        return this.f2778c.g().m(x);
    }

    Class<?> q() {
        return this.k;
    }

    <Z> com.bumptech.glide.load.h<Z> r(Class<Z> cls) {
        com.bumptech.glide.load.h<Z> hVar = (com.bumptech.glide.load.h) this.j.get(cls);
        if (hVar == null) {
            Iterator<Map.Entry<Class<?>, com.bumptech.glide.load.h<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, com.bumptech.glide.load.h<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    hVar = (com.bumptech.glide.load.h) next.getValue();
                    break;
                }
            }
        }
        if (hVar != null) {
            return hVar;
        }
        if (!this.j.isEmpty() || !this.q) {
            return com.bumptech.glide.load.k.c.c();
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    int s() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean t(Class<?> cls) {
        return h(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    <R> void u(com.bumptech.glide.d dVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, h hVar, Class<?> cls, Class<R> cls2, Priority priority, com.bumptech.glide.load.e eVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, DecodeJob.e eVar2) {
        this.f2778c = dVar;
        this.f2779d = obj;
        this.n = cVar;
        this.e = i;
        this.f = i2;
        this.p = hVar;
        this.g = cls;
        this.h = eVar2;
        this.k = cls2;
        this.o = priority;
        this.i = eVar;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    boolean v(s<?> sVar) {
        return this.f2778c.g().n(sVar);
    }

    boolean w() {
        return this.r;
    }

    boolean x(com.bumptech.glide.load.c cVar) {
        List<n.a<?>> listG = g();
        int size = listG.size();
        for (int i = 0; i < size; i++) {
            if (listG.get(i).f2996a.equals(cVar)) {
                return true;
            }
        }
        return false;
    }
}
