package spi;

import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import spi.cn;
import spi.i8;
import spi.pt;
import spi.ti;
import spi.w5;

/* JADX INFO: loaded from: classes.dex */
public final class v5<Transcode> {
    public final List<ti.a<?>> a = new ArrayList();
    public final List<ig> b = new ArrayList();
    public com.bumptech.glide.c c;
    public Object d;
    public int e;
    public int f;
    public Class<?> g;
    public w5.e h;
    public lk i;
    public Map<Class<?>, qt<?>> j;
    public Class<Transcode> k;
    public boolean l;
    public boolean m;
    public ig n;
    public Priority o;
    public i6 p;
    public boolean q;
    public boolean r;

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    public final List<ig> a() {
        if (!this.m) {
            this.m = true;
            this.b.clear();
            ArrayList arrayList = (ArrayList) c();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ti.a aVar = (ti.a) arrayList.get(i);
                if (!this.b.contains(aVar.a)) {
                    this.b.add(aVar.a);
                }
                for (int i2 = 0; i2 < aVar.b.size(); i2++) {
                    if (!this.b.contains(aVar.b.get(i2))) {
                        this.b.add(aVar.b.get(i2));
                    }
                }
            }
        }
        return this.b;
    }

    public final g6 b() {
        return ((i8.c) this.h).a();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final List<ti.a<?>> c() {
        if (!this.l) {
            this.l = true;
            this.a.clear();
            List listF = this.c.b.f(this.d);
            int size = listF.size();
            for (int i = 0; i < size; i++) {
                ti.a<?> aVarB = ((ti) listF.get(i)).b(this.d, this.e, this.f, this.i);
                if (aVarB != null) {
                    this.a.add(aVarB);
                }
            }
        }
        return this.a;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final <Data> vg<Data, ?, Transcode> d(Class<Data> cls) {
        vg<Data, ?, Transcode> vgVar;
        ArrayList arrayList;
        in inVar;
        in inVar2;
        Registry registry = this.c.b;
        Class<?> cls2 = this.g;
        Class<Transcode> cls3 = this.k;
        wg wgVar = registry.i;
        yi andSet = wgVar.b.getAndSet(null);
        if (andSet == null) {
            andSet = new yi();
        }
        andSet.a(cls, cls2, cls3);
        synchronized (wgVar.a) {
            vgVar = (vg) wgVar.a.get(andSet);
        }
        wgVar.b.set(andSet);
        Objects.requireNonNull(registry.i);
        if (wg.c.equals(vgVar)) {
            return null;
        }
        if (vgVar != null) {
            return vgVar;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Class<?> cls4 : (ArrayList) registry.c.b(cls, cls2)) {
            for (Class cls5 : (ArrayList) registry.f.a(cls4, cls3)) {
                cn cnVar = registry.c;
                synchronized (cnVar) {
                    arrayList = new ArrayList();
                    Iterator<String> it = cnVar.a.iterator();
                    while (it.hasNext()) {
                        List<cn.a<?, ?>> list = cnVar.b.get(it.next());
                        if (list != null) {
                            for (cn.a<?, ?> aVar : list) {
                                if (aVar.a.isAssignableFrom((Class<?>) cls) && cls4.isAssignableFrom(aVar.b)) {
                                    arrayList.add(aVar.c);
                                }
                            }
                        }
                    }
                }
                pt ptVar = registry.f;
                synchronized (ptVar) {
                    if (!cls5.isAssignableFrom(cls4)) {
                        for (pt.a<?, ?> aVar2 : ptVar.a) {
                            if (aVar2.a.isAssignableFrom(cls4) && cls5.isAssignableFrom(aVar2.b)) {
                                inVar = aVar2.c;
                            }
                        }
                        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls4 + " to " + cls5);
                    }
                    inVar = cu.a;
                    inVar2 = inVar;
                }
                arrayList2.add(new x5(cls, cls4, cls5, arrayList, inVar2, registry.j));
            }
        }
        vg<Data, ?, Transcode> vgVar2 = arrayList2.isEmpty() ? null : new vg<>(cls, cls2, cls3, arrayList2, registry.j);
        wg wgVar2 = registry.i;
        synchronized (wgVar2.a) {
            wgVar2.a.put(new yi(cls, cls2, cls3), (vg<?, ?, ?>) (vgVar2 != null ? vgVar2 : wg.c));
        }
        return vgVar2;
    }

    public final List<Class<?>> e() {
        List<Class<?>> list;
        List<Class<?>> listE;
        Registry registry = this.c.b;
        Class<?> cls = this.d.getClass();
        Class<?> cls2 = this.g;
        Class<Transcode> cls3 = this.k;
        wi wiVar = registry.h;
        yi andSet = wiVar.a.getAndSet(null);
        if (andSet == null) {
            andSet = new yi(cls, cls2, cls3);
        } else {
            andSet.a(cls, cls2, cls3);
        }
        synchronized (wiVar.b) {
            list = wiVar.b.get(andSet);
        }
        wiVar.a.set(andSet);
        List<Class<?>> list2 = list;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            vi viVar = registry.a;
            synchronized (viVar) {
                listE = viVar.a.e(cls);
            }
            Iterator it = ((ArrayList) listE).iterator();
            while (it.hasNext()) {
                for (Class cls4 : (ArrayList) registry.c.b((Class) it.next(), cls2)) {
                    if (!((ArrayList) registry.f.a(cls4, cls3)).isEmpty() && !arrayList.contains(cls4)) {
                        arrayList.add(cls4);
                    }
                }
            }
            wi wiVar2 = registry.h;
            List<Class<?>> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            synchronized (wiVar2.b) {
                wiVar2.b.put(new yi(cls, cls2, cls3), listUnmodifiableList);
            }
            list2 = arrayList;
        }
        return list2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        r1 = (spi.f8<X>) r3.b;
     */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<spi.g8$a<?>>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <X> spi.f8<X> f(X r6) throws com.bumptech.glide.Registry.NoSourceEncoderAvailableException {
        /*
            r5 = this;
            com.bumptech.glide.c r0 = r5.c
            com.bumptech.glide.Registry r0 = r0.b
            spi.g8 r0 = r0.b
            java.lang.Class r1 = r6.getClass()
            monitor-enter(r0)
            java.util.List<spi.g8$a<?>> r2 = r0.a     // Catch: java.lang.Throwable -> L37
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L37
        L11:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L37
            if (r3 == 0) goto L28
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L37
            spi.g8$a r3 = (spi.g8.a) r3     // Catch: java.lang.Throwable -> L37
            java.lang.Class<T> r4 = r3.a     // Catch: java.lang.Throwable -> L37
            boolean r4 = r4.isAssignableFrom(r1)     // Catch: java.lang.Throwable -> L37
            if (r4 == 0) goto L11
            spi.f8<T> r1 = r3.b     // Catch: java.lang.Throwable -> L37
            goto L29
        L28:
            r1 = 0
        L29:
            monitor-exit(r0)
            if (r1 == 0) goto L2d
            return r1
        L2d:
            com.bumptech.glide.Registry$NoSourceEncoderAvailableException r0 = new com.bumptech.glide.Registry$NoSourceEncoderAvailableException
            java.lang.Class r6 = r6.getClass()
            r0.<init>(r6)
            throw r0
        L37:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.v5.f(java.lang.Object):spi.f8");
    }

    public final <Z> qt<Z> g(Class<Z> cls) {
        qt<Z> qtVar = (qt) this.j.get(cls);
        if (qtVar == null) {
            Iterator<Map.Entry<Class<?>, qt<?>>> it = this.j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, qt<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    qtVar = (qt) next.getValue();
                    break;
                }
            }
        }
        if (qtVar != null) {
            return qtVar;
        }
        if (!this.j.isEmpty() || !this.q) {
            return (qt<Z>) du.b;
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean h(Class<?> cls) {
        return d(cls) != null;
    }
}
