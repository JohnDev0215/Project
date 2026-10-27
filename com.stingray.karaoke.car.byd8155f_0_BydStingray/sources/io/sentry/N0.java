package io.sentry;

import io.sentry.protocol.C1496a;
import io.sentry.protocol.C1498c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class N0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public V f15893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public io.sentry.protocol.E f15894b;
    public String c;
    public io.sentry.protocol.n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f15895e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Y1 f15896f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ConcurrentHashMap f15897g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ConcurrentHashMap f15898h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CopyOnWriteArrayList f15899i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final F1 f15900j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile R1 f15901k;
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f15902m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f15903n;
    public final C1498c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CopyOnWriteArrayList f15904p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public h3.y f15905q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public io.sentry.protocol.t f15906r;

    public N0(F1 f12) {
        this.f15895e = new ArrayList();
        this.f15897g = new ConcurrentHashMap();
        this.f15898h = new ConcurrentHashMap();
        this.f15899i = new CopyOnWriteArrayList();
        this.l = new Object();
        this.f15902m = new Object();
        this.f15903n = new Object();
        this.o = new C1498c();
        this.f15904p = new CopyOnWriteArrayList();
        this.f15906r = io.sentry.protocol.t.f16774m;
        this.f15900j = f12;
        int maxBreadcrumbs = f12.getMaxBreadcrumbs();
        this.f15896f = maxBreadcrumbs > 0 ? new Y1(new C1463f(maxBreadcrumbs)) : new Y1(new C1493p());
        this.f15905q = new h3.y(2);
    }

    public final void a() {
        synchronized (this.f15902m) {
            this.f15893a = null;
        }
        for (Q q4 : this.f15900j.getScopeObservers()) {
            q4.a(null);
            q4.f(null, this);
        }
    }

    public final void b(io.sentry.protocol.t tVar) {
        this.f15906r = tVar;
        Iterator<Q> it = this.f15900j.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().c(tVar);
        }
    }

    public final void c(String str) {
        this.c = str;
        C1498c c1498c = this.o;
        C1496a c1496a = (C1496a) c1498c.f(C1496a.class, "app");
        if (c1496a == null) {
            c1496a = new C1496a();
            c1498c.c(c1496a);
        }
        if (str == null) {
            c1496a.f16681t = null;
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(str);
            c1496a.f16681t = arrayList;
        }
        Iterator<Q> it = this.f15900j.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().g(c1498c);
        }
    }

    public final Object clone() {
        return new N0(this);
    }

    public final void d(String str, String str2) {
        ConcurrentHashMap concurrentHashMap = this.f15897g;
        concurrentHashMap.put(str, str2);
        for (Q q4 : this.f15900j.getScopeObservers()) {
            q4.b(str, str2);
            q4.d(concurrentHashMap);
        }
    }

    public final void e(V v10) {
        synchronized (this.f15902m) {
            try {
                this.f15893a = v10;
                for (Q q4 : this.f15900j.getScopeObservers()) {
                    if (v10 != null) {
                        q4.a(v10.getName());
                        q4.f(v10.p(), this);
                    } else {
                        q4.a(null);
                        q4.f(null, this);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final h3.y f(M0 m02) {
        h3.y yVar;
        synchronized (this.f15903n) {
            m02.j(this.f15905q);
            yVar = new h3.y(this.f15905q);
        }
        return yVar;
    }

    public N0(N0 n02) {
        io.sentry.protocol.E e10;
        Y1 y12;
        this.f15895e = new ArrayList();
        this.f15897g = new ConcurrentHashMap();
        this.f15898h = new ConcurrentHashMap();
        this.f15899i = new CopyOnWriteArrayList();
        this.l = new Object();
        this.f15902m = new Object();
        this.f15903n = new Object();
        this.o = new C1498c();
        this.f15904p = new CopyOnWriteArrayList();
        this.f15906r = io.sentry.protocol.t.f16774m;
        this.f15893a = n02.f15893a;
        this.f15901k = n02.f15901k;
        this.f15900j = n02.f15900j;
        io.sentry.protocol.E e11 = n02.f15894b;
        io.sentry.protocol.n nVar = null;
        if (e11 != null) {
            e10 = new io.sentry.protocol.E();
            e10.l = e11.l;
            e10.f16657n = e11.f16657n;
            e10.f16656m = e11.f16656m;
            e10.f16658p = e11.f16658p;
            e10.o = e11.o;
            e10.f16659q = e11.f16659q;
            e10.f16660r = e11.f16660r;
            e10.f16661s = h6.r.p(e11.f16661s);
            e10.f16662t = h6.r.p(e11.f16662t);
        } else {
            e10 = null;
        }
        this.f15894b = e10;
        this.c = n02.c;
        this.f15906r = n02.f15906r;
        io.sentry.protocol.n nVar2 = n02.d;
        if (nVar2 != null) {
            nVar = new io.sentry.protocol.n();
            nVar.l = nVar2.l;
            nVar.f16751p = nVar2.f16751p;
            nVar.f16749m = nVar2.f16749m;
            nVar.f16750n = nVar2.f16750n;
            nVar.f16752q = h6.r.p(nVar2.f16752q);
            nVar.f16753r = h6.r.p(nVar2.f16753r);
            nVar.f16755t = h6.r.p(nVar2.f16755t);
            nVar.f16758w = h6.r.p(nVar2.f16758w);
            nVar.o = nVar2.o;
            nVar.f16756u = nVar2.f16756u;
            nVar.f16754s = nVar2.f16754s;
            nVar.f16757v = nVar2.f16757v;
        }
        this.d = nVar;
        this.f15895e = new ArrayList(n02.f15895e);
        this.f15899i = new CopyOnWriteArrayList(n02.f15899i);
        C1457d[] c1457dArr = (C1457d[]) n02.f15896f.toArray(new C1457d[0]);
        int maxBreadcrumbs = n02.f15900j.getMaxBreadcrumbs();
        if (maxBreadcrumbs > 0) {
            y12 = new Y1(new C1463f(maxBreadcrumbs));
        } else {
            y12 = new Y1(new C1493p());
        }
        for (C1457d c1457d : c1457dArr) {
            y12.add(new C1457d(c1457d));
        }
        this.f15896f = y12;
        ConcurrentHashMap concurrentHashMap = n02.f15897g;
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            if (entry != null) {
                concurrentHashMap2.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.f15897g = concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3 = n02.f15898h;
        ConcurrentHashMap concurrentHashMap4 = new ConcurrentHashMap();
        for (Map.Entry entry2 : concurrentHashMap3.entrySet()) {
            if (entry2 != null) {
                concurrentHashMap4.put((String) entry2.getKey(), entry2.getValue());
            }
        }
        this.f15898h = concurrentHashMap4;
        this.o = new C1498c(n02.o);
        this.f15904p = new CopyOnWriteArrayList(n02.f15904p);
        this.f15905q = new h3.y(n02.f15905q);
    }
}
