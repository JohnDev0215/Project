package com.bumptech.glide;

import A7.D;
import e4.InterfaceC1226b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import k4.q;
import k4.r;
import k4.s;
import k4.t;
import k4.v;
import k4.w;
import l3.P;
import l5.C1778f;
import l8.C1789g;
import v4.C2548a;
import v4.C2549b;
import v4.C2550c;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f12687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D f12688b;
    public final C1789g c;
    public final D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.bumptech.glide.load.data.i f12689e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final D f12690f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final D f12691g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1778f f12692h = new C1778f();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2549b f12693i = new C2549b();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final P f12694j;

    public h() {
        P p10 = new P(new E1.d(20), new Y4.e(2), new io.sentry.hints.i(2));
        this.f12694j = p10;
        this.f12687a = new t(p10);
        this.f12688b = new D(10, false);
        this.c = new C1789g(9);
        this.d = new D(12, false);
        this.f12689e = new com.bumptech.glide.load.data.i();
        this.f12690f = new D(8, false);
        this.f12691g = new D(11, false);
        List listAsList = Arrays.asList("Animation", "Bitmap", "BitmapDrawable");
        ArrayList arrayList = new ArrayList(listAsList.size());
        arrayList.add("legacy_prepend_all");
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        arrayList.add("legacy_append");
        C1789g c1789g = this.c;
        synchronized (c1789g) {
            try {
                ArrayList<String> arrayList2 = new ArrayList((ArrayList) c1789g.l);
                ((ArrayList) c1789g.l).clear();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((ArrayList) c1789g.l).add((String) it2.next());
                }
                for (String str : arrayList2) {
                    if (!arrayList.contains(str)) {
                        ((ArrayList) c1789g.l).add(str);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a(Class cls, InterfaceC1226b interfaceC1226b) {
        D d = this.f12688b;
        synchronized (d) {
            d.l.add(new C2548a(cls, interfaceC1226b));
        }
    }

    public final void b(Class cls, e4.k kVar) {
        D d = this.d;
        synchronized (d) {
            d.l.add(new v4.d(cls, kVar));
        }
    }

    public final void c(Class cls, Class cls2, r rVar) {
        t tVar = this.f12687a;
        synchronized (tVar) {
            w wVar = tVar.f18208a;
            synchronized (wVar) {
                try {
                    v vVar = new v(cls, cls2, rVar);
                    ArrayList arrayList = wVar.f18219a;
                    arrayList.add(arrayList.size(), vVar);
                } catch (Throwable th) {
                    throw th;
                }
            }
            tVar.f18209b.f8070a.clear();
        }
    }

    public final void d(String str, Class cls, Class cls2, e4.j jVar) {
        C1789g c1789g = this.c;
        synchronized (c1789g) {
            c1789g.z(str).add(new C2550c(cls, cls2, jVar));
        }
    }

    public final ArrayList e() {
        ArrayList arrayList;
        D d = this.f12691g;
        synchronized (d) {
            arrayList = d.l;
        }
        if (arrayList.isEmpty()) {
            throw new g("Failed to find image header parser.");
        }
        return arrayList;
    }

    public final List f(Object obj) {
        List listUnmodifiableList;
        t tVar = this.f12687a;
        tVar.getClass();
        Class<?> cls = obj.getClass();
        synchronized (tVar) {
            s sVar = (s) tVar.f18209b.f8070a.get(cls);
            listUnmodifiableList = sVar == null ? null : sVar.f18207a;
            if (listUnmodifiableList == null) {
                listUnmodifiableList = Collections.unmodifiableList(tVar.f18208a.a(cls));
                if (((s) tVar.f18209b.f8070a.put(cls, new s(listUnmodifiableList))) != null) {
                    throw new IllegalStateException("Already cached loaders for model: " + cls);
                }
            }
        }
        if (listUnmodifiableList.isEmpty()) {
            throw new g("Failed to find any ModelLoaders registered for model class: " + obj.getClass());
        }
        int size = listUnmodifiableList.size();
        List listEmptyList = Collections.emptyList();
        boolean z6 = true;
        for (int i7 = 0; i7 < size; i7++) {
            q qVar = (q) listUnmodifiableList.get(i7);
            if (qVar.b(obj)) {
                if (z6) {
                    listEmptyList = new ArrayList(size - i7);
                    z6 = false;
                }
                listEmptyList.add(qVar);
            }
        }
        if (!listEmptyList.isEmpty()) {
            return listEmptyList;
        }
        throw new g("Found ModelLoaders for model class: " + listUnmodifiableList + ", but none that handle this specific model instance: " + obj);
    }

    public final com.bumptech.glide.load.data.g g(Object obj) {
        com.bumptech.glide.load.data.g gVarB;
        com.bumptech.glide.load.data.i iVar = this.f12689e;
        synchronized (iVar) {
            try {
                A4.h.b(obj);
                com.bumptech.glide.load.data.f fVar = (com.bumptech.glide.load.data.f) ((HashMap) iVar.f12725m).get(obj.getClass());
                if (fVar == null) {
                    Iterator it = ((HashMap) iVar.f12725m).values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        com.bumptech.glide.load.data.f fVar2 = (com.bumptech.glide.load.data.f) it.next();
                        if (fVar2.a().isAssignableFrom(obj.getClass())) {
                            fVar = fVar2;
                            break;
                        }
                    }
                }
                if (fVar == null) {
                    fVar = com.bumptech.glide.load.data.i.f12724n;
                }
                gVarB = fVar.b(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        return gVarB;
    }

    public final void h(com.bumptech.glide.load.data.f fVar) {
        com.bumptech.glide.load.data.i iVar = this.f12689e;
        synchronized (iVar) {
            ((HashMap) iVar.f12725m).put(fVar.a(), fVar);
        }
    }

    public final void i(Class cls, Class cls2, s4.a aVar) {
        D d = this.f12690f;
        synchronized (d) {
            d.l.add(new s4.b(cls, cls2, aVar));
        }
    }
}
