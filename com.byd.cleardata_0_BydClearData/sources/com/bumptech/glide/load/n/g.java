package com.bumptech.glide.load.n;

import com.bumptech.glide.load.n.h;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class g<Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n.a<?>> f2113a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.load.g> f2114b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.e f2115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f2116d;
    private int e;
    private int f;
    private Class<?> g;
    private h.e h;
    private com.bumptech.glide.load.i i;
    private Map<Class<?>, com.bumptech.glide.load.m<?>> j;
    private Class<Transcode> k;
    private boolean l;
    private boolean m;
    private com.bumptech.glide.load.g n;
    private com.bumptech.glide.g o;
    private j p;
    private boolean q;
    private boolean r;

    g() {
    }

    void a() {
        this.f2115c = null;
        this.f2116d = null;
        this.n = null;
        this.g = null;
        this.k = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.p = null;
        this.f2113a.clear();
        this.l = false;
        this.f2114b.clear();
        this.m = false;
    }

    com.bumptech.glide.load.n.a0.b b() {
        return this.f2115c.a();
    }

    List<com.bumptech.glide.load.g> c() {
        if (!this.m) {
            this.m = true;
            this.f2114b.clear();
            List<n.a<?>> listG = g();
            int size = listG.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVar = listG.get(i);
                if (!this.f2114b.contains(aVar.f2250a)) {
                    this.f2114b.add(aVar.f2250a);
                }
                for (int i2 = 0; i2 < aVar.f2251b.size(); i2++) {
                    if (!this.f2114b.contains(aVar.f2251b.get(i2))) {
                        this.f2114b.add(aVar.f2251b.get(i2));
                    }
                }
            }
        }
        return this.f2114b;
    }

    com.bumptech.glide.load.n.b0.a d() {
        return this.h.a();
    }

    j e() {
        return this.p;
    }

    int f() {
        return this.f;
    }

    List<n.a<?>> g() {
        if (!this.l) {
            this.l = true;
            this.f2113a.clear();
            List listI = this.f2115c.g().i(this.f2116d);
            int size = listI.size();
            for (int i = 0; i < size; i++) {
                n.a<?> aVarA = ((com.bumptech.glide.load.o.n) listI.get(i)).a(this.f2116d, this.e, this.f, this.i);
                if (aVarA != null) {
                    this.f2113a.add(aVarA);
                }
            }
        }
        return this.f2113a;
    }

    <Data> t<Data, ?, Transcode> h(Class<Data> cls) {
        return this.f2115c.g().h(cls, this.g, this.k);
    }

    Class<?> i() {
        return this.f2116d.getClass();
    }

    List<com.bumptech.glide.load.o.n<File, ?>> j(File file) {
        return this.f2115c.g().i(file);
    }

    com.bumptech.glide.load.i k() {
        return this.i;
    }

    com.bumptech.glide.g l() {
        return this.o;
    }

    List<Class<?>> m() {
        return this.f2115c.g().j(this.f2116d.getClass(), this.g, this.k);
    }

    <Z> com.bumptech.glide.load.l<Z> n(v<Z> vVar) {
        return this.f2115c.g().k(vVar);
    }

    com.bumptech.glide.load.g o() {
        return this.n;
    }

    <X> com.bumptech.glide.load.d<X> p(X x) {
        return this.f2115c.g().m(x);
    }

    Class<?> q() {
        return this.k;
    }

    <Z> com.bumptech.glide.load.m<Z> r(Class<Z> cls) {
        com.bumptech.glide.load.m<Z> mVar = (com.bumptech.glide.load.m) this.j.get(cls);
        if (mVar == null) {
            Iterator<Map.Entry<Class<?>, com.bumptech.glide.load.m<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, com.bumptech.glide.load.m<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    mVar = (com.bumptech.glide.load.m) next.getValue();
                    break;
                }
            }
        }
        if (mVar != null) {
            return mVar;
        }
        if (!this.j.isEmpty() || !this.q) {
            return com.bumptech.glide.load.p.c.c();
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
    <R> void u(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.g gVar, int i, int i2, j jVar, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar2, com.bumptech.glide.load.i iVar, Map<Class<?>, com.bumptech.glide.load.m<?>> map, boolean z, boolean z2, h.e eVar2) {
        this.f2115c = eVar;
        this.f2116d = obj;
        this.n = gVar;
        this.e = i;
        this.f = i2;
        this.p = jVar;
        this.g = cls;
        this.h = eVar2;
        this.k = cls2;
        this.o = gVar2;
        this.i = iVar;
        this.j = map;
        this.q = z;
        this.r = z2;
    }

    boolean v(v<?> vVar) {
        return this.f2115c.g().n(vVar);
    }

    boolean w() {
        return this.r;
    }

    boolean x(com.bumptech.glide.load.g gVar) {
        List<n.a<?>> listG = g();
        int size = listG.size();
        for (int i = 0; i < size; i++) {
            if (listG.get(i).f2250a.equals(gVar)) {
                return true;
            }
        }
        return false;
    }
}
