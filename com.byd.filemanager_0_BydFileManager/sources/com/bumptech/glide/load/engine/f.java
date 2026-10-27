package com.bumptech.glide.load.engine;

import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.i.n;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DecodeHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class f<Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n.a<?>> f3011a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.c> f3012b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.e f3013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f3014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3015e;
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
        this.f3013c = null;
        this.f3014d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.f3011a.clear();
        this.l = false;
        this.f3012b.clear();
        this.m = false;
    }

    com.bumptech.glide.load.engine.x.b b() {
        return this.f3013c.a();
    }

    List<com.bumptech.glide.load.c> c() {
        if (!this.m) {
            this.m = true;
            this.f3012b.clear();
            List<n.a<?>> listG = g();
            int size = listG.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVar = listG.get(i);
                if (!this.f3012b.contains(aVar.f3221a)) {
                    this.f3012b.add(aVar.f3221a);
                }
                for (int i2 = 0; i2 < aVar.f3222b.size(); i2++) {
                    if (!this.f3012b.contains(aVar.f3222b.get(i2))) {
                        this.f3012b.add(aVar.f3222b.get(i2));
                    }
                }
            }
        }
        return this.f3012b;
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
            this.f3011a.clear();
            List listI = this.f3013c.g().i(this.f3014d);
            int size = listI.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVarA = ((com.bumptech.glide.load.i.n) listI.get(i)).a(this.f3014d, this.f3015e, this.f, this.i);
                if (aVarA != null) {
                    this.f3011a.add(aVarA);
                }
            }
        }
        return this.f3011a;
    }

    <Data> q<Data, ?, Transcode> h(Class<Data> cls) {
        return this.f3013c.g().h(cls, this.g, this.k);
    }

    Class<?> i() {
        return this.f3014d.getClass();
    }

    List<com.bumptech.glide.load.i.n<File, ?>> j(File file) throws Registry.NoModelLoaderAvailableException {
        return this.f3013c.g().i(file);
    }

    com.bumptech.glide.load.e k() {
        return this.i;
    }

    Priority l() {
        return this.o;
    }

    List<Class<?>> m() {
        return this.f3013c.g().j(this.f3014d.getClass(), this.g, this.k);
    }

    <Z> com.bumptech.glide.load.g<Z> n(s<Z> sVar) {
        return this.f3013c.g().k(sVar);
    }

    com.bumptech.glide.load.c o() {
        return this.n;
    }

    <X> com.bumptech.glide.load.a<X> p(X x) throws Registry.NoSourceEncoderAvailableException {
        return this.f3013c.g().m(x);
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
            return com.bumptech.glide.load.j.c.c();
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    int s() {
        return this.f3015e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean t(Class<?> cls) {
        return h(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    <R> void u(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, h hVar, Class<?> cls, Class<R> cls2, Priority priority, com.bumptech.glide.load.e eVar2, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, DecodeJob.e eVar3) {
        this.f3013c = eVar;
        this.f3014d = obj;
        this.n = cVar;
        this.f3015e = i;
        this.f = i2;
        this.p = hVar;
        this.g = cls;
        this.h = eVar3;
        this.k = cls2;
        this.o = priority;
        this.i = eVar2;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    boolean v(s<?> sVar) {
        return this.f3013c.g().n(sVar);
    }

    boolean w() {
        return this.r;
    }

    boolean x(com.bumptech.glide.load.c cVar) {
        List<n.a<?>> listG = g();
        int size = listG.size();
        for (int i = 0; i < size; i++) {
            if (listG.get(i).f3221a.equals(cVar)) {
                return true;
            }
        }
        return false;
    }
}
