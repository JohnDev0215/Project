package g4;

import A7.D;
import androidx.datastore.preferences.protobuf.g0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import l8.C1789g;
import v4.C2549b;
import v4.C2550c;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f14862a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f14863b = new ArrayList();
    public com.bumptech.glide.e c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f14864e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14865f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Class f14866g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public A4.i f14867h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e4.h f14868i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public A4.d f14869j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Class f14870k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f14871m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e4.e f14872n;
    public com.bumptech.glide.f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k f14873p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f14874q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f14875r;

    public final ArrayList a() {
        boolean z6 = this.f14871m;
        ArrayList arrayList = this.f14863b;
        if (!z6) {
            this.f14871m = true;
            arrayList.clear();
            ArrayList arrayListB = b();
            int size = arrayListB.size();
            for (int i7 = 0; i7 < size; i7++) {
                k4.p pVar = (k4.p) arrayListB.get(i7);
                if (!arrayList.contains(pVar.f18205a)) {
                    arrayList.add(pVar.f18205a);
                }
                int i10 = 0;
                while (true) {
                    List list = pVar.f18206b;
                    if (i10 < list.size()) {
                        if (!arrayList.contains(list.get(i10))) {
                            arrayList.add(list.get(i10));
                        }
                        i10++;
                    }
                }
            }
        }
        return arrayList;
    }

    public final ArrayList b() {
        boolean z6 = this.l;
        ArrayList arrayList = this.f14862a;
        if (!z6) {
            this.l = true;
            arrayList.clear();
            List listF = this.c.a().f(this.d);
            int size = listF.size();
            for (int i7 = 0; i7 < size; i7++) {
                k4.p pVarA = ((k4.q) listF.get(i7)).a(this.d, this.f14864e, this.f14865f, this.f14868i);
                if (pVarA != null) {
                    arrayList.add(pVarA);
                }
            }
        }
        return arrayList;
    }

    public final v c(Class cls) {
        v vVar;
        ArrayList arrayList;
        s4.a aVar;
        com.bumptech.glide.h hVarA = this.c.a();
        Class cls2 = this.f14866g;
        Class cls3 = this.f14870k;
        C2549b c2549b = hVarA.f12693i;
        A4.o oVar = (A4.o) c2549b.f23004b.getAndSet(null);
        if (oVar == null) {
            oVar = new A4.o();
        }
        oVar.f429a = cls;
        oVar.f430b = cls2;
        oVar.c = cls3;
        synchronized (c2549b.f23003a) {
            vVar = (v) c2549b.f23003a.get(oVar);
        }
        c2549b.f23004b.set(oVar);
        hVarA.f12693i.getClass();
        if (C2549b.c.equals(vVar)) {
            return null;
        }
        if (vVar != null) {
            return vVar;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Class<?> cls4 : hVarA.c.A(cls, cls2)) {
            for (Class cls5 : hVarA.f12690f.s(cls4, cls3)) {
                C1789g c1789g = hVarA.c;
                synchronized (c1789g) {
                    arrayList = new ArrayList();
                    Iterator it = ((ArrayList) c1789g.l).iterator();
                    while (it.hasNext()) {
                        List<C2550c> list = (List) ((HashMap) c1789g.f18861m).get((String) it.next());
                        if (list != null) {
                            for (C2550c c2550c : list) {
                                if (c2550c.f23005a.isAssignableFrom(cls) && cls4.isAssignableFrom(c2550c.f23006b)) {
                                    arrayList.add(c2550c.c);
                                }
                            }
                        }
                    }
                }
                D d = hVarA.f12690f;
                synchronized (d) {
                    if (!cls5.isAssignableFrom(cls4)) {
                        for (s4.b bVar : d.l) {
                            if (bVar.f22365a.isAssignableFrom(cls4) && cls5.isAssignableFrom(bVar.f22366b)) {
                                aVar = bVar.c;
                            }
                        }
                        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls4 + " to " + cls5);
                    }
                    aVar = s4.c.f22367m;
                }
                arrayList2.add(new j(cls, cls4, cls5, arrayList, aVar, hVarA.f12694j));
            }
        }
        v vVar2 = arrayList2.isEmpty() ? null : new v(cls, cls2, cls3, arrayList2, hVarA.f12694j);
        C2549b c2549b2 = hVarA.f12693i;
        synchronized (c2549b2.f23003a) {
            c2549b2.f23003a.put(new A4.o(cls, cls2, cls3), vVar2 != null ? vVar2 : C2549b.c);
        }
        return vVar2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        r0 = r2.f23002b;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final e4.InterfaceC1226b d(java.lang.Object r5) {
        /*
            r4 = this;
            com.bumptech.glide.e r4 = r4.c
            com.bumptech.glide.h r4 = r4.a()
            A7.D r4 = r4.f12688b
            java.lang.Class r0 = r5.getClass()
            monitor-enter(r4)
            java.util.ArrayList r1 = r4.l     // Catch: java.lang.Throwable -> L2b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L2b
        L13:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L2d
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L2b
            v4.a r2 = (v4.C2548a) r2     // Catch: java.lang.Throwable -> L2b
            java.lang.Class r3 = r2.f23001a     // Catch: java.lang.Throwable -> L2b
            boolean r3 = r3.isAssignableFrom(r0)     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L13
            e4.b r0 = r2.f23002b     // Catch: java.lang.Throwable -> L2b
            monitor-exit(r4)
            goto L2f
        L2b:
            r5 = move-exception
            goto L4a
        L2d:
            monitor-exit(r4)
            r0 = 0
        L2f:
            if (r0 == 0) goto L32
            return r0
        L32:
            com.bumptech.glide.g r4 = new com.bumptech.glide.g
            java.lang.Class r5 = r5.getClass()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Failed to find source encoder for data class: "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            throw r4
        L4a:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: g4.h.d(java.lang.Object):e4.b");
    }

    public final e4.l e(Class cls) {
        e4.l lVar = (e4.l) this.f14869j.get(cls);
        if (lVar == null) {
            Iterator it = ((g0) this.f14869j.entrySet()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    lVar = (e4.l) entry.getValue();
                    break;
                }
            }
        }
        if (lVar != null) {
            return lVar;
        }
        if (!this.f14869j.isEmpty() || !this.f14874q) {
            return m4.c.f19187b;
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }
}
