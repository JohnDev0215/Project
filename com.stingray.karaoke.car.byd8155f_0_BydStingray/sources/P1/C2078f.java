package p1;

import h2.AbstractC1347d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import n7.AbstractC1998m;
import n7.v;
import r9.z;

/* JADX INFO: renamed from: p1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2078f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f20653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f20654b;
    public boolean c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f20655e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Serializable f20656f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Serializable f20657g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f20658h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f20659i;

    public C2078f(o1.e eVar) {
        this.f20653a = 0;
        this.f20654b = true;
        this.c = true;
        this.f20656f = new ArrayList();
        new ArrayList();
        this.f20658h = null;
        this.f20659i = new C2074b();
        this.f20657g = new ArrayList();
        this.d = eVar;
        this.f20655e = eVar;
    }

    public void a(C2079g c2079g, int i7, ArrayList arrayList, m mVar) {
        p pVar = c2079g.d;
        if (pVar.c == null) {
            o1.e eVar = (o1.e) this.d;
            if (pVar == eVar.d || pVar == eVar.f20288e) {
                return;
            }
            if (mVar == null) {
                mVar = new m();
                mVar.f20672a = null;
                mVar.f20673b = new ArrayList();
                mVar.f20672a = pVar;
                arrayList.add(mVar);
            }
            pVar.c = mVar;
            mVar.f20673b.add(pVar);
            C2079g c2079g2 = pVar.f20684h;
            for (InterfaceC2077e interfaceC2077e : c2079g2.f20668k) {
                if (interfaceC2077e instanceof C2079g) {
                    a((C2079g) interfaceC2077e, i7, arrayList, mVar);
                }
            }
            C2079g c2079g3 = pVar.f20685i;
            for (InterfaceC2077e interfaceC2077e2 : c2079g3.f20668k) {
                if (interfaceC2077e2 instanceof C2079g) {
                    a((C2079g) interfaceC2077e2, i7, arrayList, mVar);
                }
            }
            if (i7 == 1 && (pVar instanceof n)) {
                for (InterfaceC2077e interfaceC2077e3 : ((n) pVar).f20674k.f20668k) {
                    if (interfaceC2077e3 instanceof C2079g) {
                        a((C2079g) interfaceC2077e3, i7, arrayList, mVar);
                    }
                }
            }
            Iterator it = c2079g2.l.iterator();
            while (it.hasNext()) {
                a((C2079g) it.next(), i7, arrayList, mVar);
            }
            Iterator it2 = c2079g3.l.iterator();
            while (it2.hasNext()) {
                a((C2079g) it2.next(), i7, arrayList, mVar);
            }
            if (i7 == 1 && (pVar instanceof n)) {
                Iterator it3 = ((n) pVar).f20674k.l.iterator();
                while (it3.hasNext()) {
                    a((C2079g) it3.next(), i7, arrayList, mVar);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:162:0x0260 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0008 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0185  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(o1.e r17) {
        /*
            Method dump skipped, instruction units count: 791
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.C2078f.b(o1.e):void");
    }

    public void c() {
        ArrayList<p> arrayList = (ArrayList) this.f20656f;
        arrayList.clear();
        o1.e eVar = (o1.e) this.f20655e;
        eVar.d.f();
        eVar.f20288e.f();
        arrayList.add(eVar.d);
        arrayList.add(eVar.f20288e);
        HashSet hashSet = null;
        for (o1.d dVar : eVar.f20332r0) {
            if (dVar instanceof o1.h) {
                C2082j c2082j = new C2082j(dVar);
                dVar.d.f();
                dVar.f20288e.f();
                c2082j.f20682f = ((o1.h) dVar).f20383v0;
                arrayList.add(c2082j);
            } else {
                if (dVar.y()) {
                    if (dVar.f20284b == null) {
                        dVar.f20284b = new C2076d(dVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(dVar.f20284b);
                } else {
                    arrayList.add(dVar.d);
                }
                if (dVar.z()) {
                    if (dVar.c == null) {
                        dVar.c = new C2076d(dVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(dVar.c);
                } else {
                    arrayList.add(dVar.f20288e);
                }
                if (dVar instanceof o1.i) {
                    arrayList.add(new C2083k(dVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((p) it.next()).f();
        }
        for (p pVar : arrayList) {
            if (pVar.f20680b != eVar) {
                pVar.d();
            }
        }
        ArrayList arrayList2 = (ArrayList) this.f20657g;
        arrayList2.clear();
        o1.e eVar2 = (o1.e) this.d;
        e(eVar2.d, 0, arrayList2);
        e(eVar2.f20288e, 1, arrayList2);
        this.f20654b = false;
    }

    public int d(o1.e eVar, int i7) {
        ArrayList arrayList;
        int i10;
        long jMax;
        float f10;
        o1.e eVar2 = eVar;
        ArrayList arrayList2 = (ArrayList) this.f20657g;
        int size = arrayList2.size();
        long j3 = 0;
        int i11 = 0;
        long jMax2 = 0;
        while (i11 < size) {
            p pVar = ((m) arrayList2.get(i11)).f20672a;
            if (!(pVar instanceof C2076d) ? !(i7 != 0 ? (pVar instanceof n) : (pVar instanceof l)) : ((C2076d) pVar).f20682f != i7) {
                C2079g c2079g = (i7 == 0 ? eVar2.d : eVar2.f20288e).f20684h;
                C2079g c2079g2 = (i7 == 0 ? eVar2.d : eVar2.f20288e).f20685i;
                boolean zContains = pVar.f20684h.l.contains(c2079g);
                C2079g c2079g3 = pVar.f20685i;
                boolean zContains2 = c2079g3.l.contains(c2079g2);
                long j10 = pVar.j();
                C2079g c2079g4 = pVar.f20684h;
                if (zContains && zContains2) {
                    long jB = m.b(c2079g4, j3);
                    arrayList = arrayList2;
                    long jA = m.a(c2079g3, j3);
                    long j11 = jB - j10;
                    int i12 = c2079g3.f20663f;
                    i10 = i11;
                    if (j11 >= (-i12)) {
                        j11 += (long) i12;
                    }
                    long j12 = c2079g4.f20663f;
                    long j13 = ((-jA) - j10) - j12;
                    if (j13 >= j12) {
                        j13 -= j12;
                    }
                    o1.d dVar = pVar.f20680b;
                    if (i7 == 0) {
                        f10 = dVar.f20289e0;
                    } else if (i7 == 1) {
                        f10 = dVar.f20291f0;
                    } else {
                        dVar.getClass();
                        f10 = -1.0f;
                    }
                    float f11 = f10 > 0.0f ? (long) ((j11 / (1.0f - f10)) + (j13 / f10)) : 0L;
                    jMax = (((long) c2079g4.f20663f) + ((((long) ((f11 * f10) + 0.5f)) + j10) + ((long) AbstractC1347d.a(1.0f, f10, f11, 0.5f)))) - ((long) c2079g3.f20663f);
                } else {
                    arrayList = arrayList2;
                    i10 = i11;
                    jMax = zContains ? Math.max(m.b(c2079g4, c2079g4.f20663f), ((long) c2079g4.f20663f) + j10) : zContains2 ? Math.max(-m.a(c2079g3, c2079g3.f20663f), ((long) (-c2079g3.f20663f)) + j10) : (pVar.j() + ((long) c2079g4.f20663f)) - ((long) c2079g3.f20663f);
                }
            } else {
                arrayList = arrayList2;
                jMax = j3;
                i10 = i11;
            }
            jMax2 = Math.max(jMax2, jMax);
            i11 = i10 + 1;
            arrayList2 = arrayList;
            eVar2 = eVar;
            j3 = 0;
        }
        return (int) jMax2;
    }

    public void e(p pVar, int i7, ArrayList arrayList) {
        C2079g c2079g;
        Iterator it = pVar.f20684h.f20668k.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            c2079g = pVar.f20685i;
            if (!zHasNext) {
                break;
            }
            InterfaceC2077e interfaceC2077e = (InterfaceC2077e) it.next();
            if (interfaceC2077e instanceof C2079g) {
                a((C2079g) interfaceC2077e, i7, arrayList, null);
            } else if (interfaceC2077e instanceof p) {
                a(((p) interfaceC2077e).f20684h, i7, arrayList, null);
            }
        }
        for (InterfaceC2077e interfaceC2077e2 : c2079g.f20668k) {
            if (interfaceC2077e2 instanceof C2079g) {
                a((C2079g) interfaceC2077e2, i7, arrayList, null);
            } else if (interfaceC2077e2 instanceof p) {
                a(((p) interfaceC2077e2).f20685i, i7, arrayList, null);
            }
        }
        if (i7 == 1) {
            for (InterfaceC2077e interfaceC2077e3 : ((n) pVar).f20674k.f20668k) {
                if (interfaceC2077e3 instanceof C2079g) {
                    a((C2079g) interfaceC2077e3, i7, arrayList, null);
                }
            }
        }
    }

    public void f(int i7, int i10, int i11, int i12, o1.d dVar) {
        C2074b c2074b = (C2074b) this.f20659i;
        c2074b.f20644a = i7;
        c2074b.f20645b = i11;
        c2074b.c = i10;
        c2074b.d = i12;
        ((InterfaceC2075c) this.f20658h).b(dVar, c2074b);
        dVar.S(c2074b.f20646e);
        dVar.N(c2074b.f20647f);
        dVar.f20261F = c2074b.f20649h;
        dVar.J(c2074b.f20648g);
    }

    public void g() {
        C2073a c2073a;
        for (o1.d dVar : ((o1.e) this.d).f20332r0) {
            if (!dVar.f20282a) {
                int[] iArr = dVar.f20311q0;
                boolean z6 = false;
                int i7 = iArr[0];
                int i10 = iArr[1];
                int i11 = dVar.f20313s;
                int i12 = dVar.f20314t;
                boolean z10 = i7 == 2 || (i7 == 3 && i11 == 1);
                if (i10 == 2 || (i10 == 3 && i12 == 1)) {
                    z6 = true;
                }
                C2080h c2080h = dVar.d.f20681e;
                boolean z11 = c2080h.f20667j;
                C2080h c2080h2 = dVar.f20288e.f20681e;
                boolean z12 = c2080h2.f20667j;
                if (z11 && z12) {
                    f(1, c2080h.f20664g, 1, c2080h2.f20664g, dVar);
                    dVar.f20282a = true;
                } else if (z11 && z6) {
                    f(1, c2080h.f20664g, 2, c2080h2.f20664g, dVar);
                    if (i10 == 3) {
                        dVar.f20288e.f20681e.f20669m = dVar.l();
                    } else {
                        dVar.f20288e.f20681e.d(dVar.l());
                        dVar.f20282a = true;
                    }
                } else if (z12 && z10) {
                    f(2, c2080h.f20664g, 1, c2080h2.f20664g, dVar);
                    if (i7 == 3) {
                        dVar.d.f20681e.f20669m = dVar.r();
                    } else {
                        dVar.d.f20681e.d(dVar.r());
                        dVar.f20282a = true;
                    }
                }
                if (dVar.f20282a && (c2073a = dVar.f20288e.l) != null) {
                    c2073a.d(dVar.f20285b0);
                }
            }
        }
    }

    public String toString() {
        switch (this.f20653a) {
            case 1:
                ArrayList arrayList = new ArrayList();
                if (this.f20654b) {
                    arrayList.add("isRegularFile");
                }
                if (this.c) {
                    arrayList.add("isDirectory");
                }
                Long l = (Long) this.f20655e;
                if (l != null) {
                    arrayList.add("byteCount=" + l);
                }
                Long l10 = (Long) this.f20656f;
                if (l10 != null) {
                    arrayList.add("createdAt=" + l10);
                }
                Long l11 = (Long) this.f20657g;
                if (l11 != null) {
                    arrayList.add("lastModifiedAt=" + l11);
                }
                Long l12 = (Long) this.f20658h;
                if (l12 != null) {
                    arrayList.add("lastAccessedAt=" + l12);
                }
                Map map = (Map) this.f20659i;
                if (!map.isEmpty()) {
                    arrayList.add("extras=" + map);
                }
                return AbstractC1998m.f0(arrayList, ", ", "FileMetadata(", ")", null, 56);
            default:
                return super.toString();
        }
    }

    public C2078f(boolean z6, boolean z10, z zVar, Long l, Long l10, Long l11, Long l12, Map map) {
        this.f20653a = 1;
        A7.m.f(map, "extras");
        this.f20654b = z6;
        this.c = z10;
        this.d = zVar;
        this.f20655e = l;
        this.f20656f = l10;
        this.f20657g = l11;
        this.f20658h = l12;
        this.f20659i = n7.z.y(map);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2078f(boolean z6, boolean z10, z zVar, Long l, Long l10, Long l11, Long l12) {
        this(z6, z10, zVar, l, l10, l11, l12, v.l);
        this.f20653a = 1;
    }
}
