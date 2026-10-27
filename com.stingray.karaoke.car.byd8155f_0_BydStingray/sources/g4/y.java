package g4;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import l5.C1778f;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class y implements g, com.bumptech.glide.load.data.d {
    public final i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h f14961m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14962n;
    public int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e4.e f14963p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f14964q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14965r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile k4.p f14966s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public File f14967t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public z f14968u;

    public y(h hVar, i iVar) {
        this.f14961m = hVar;
        this.l = iVar;
    }

    @Override // com.bumptech.glide.load.data.d
    public final void c(Exception exc) {
        this.l.a(this.f14968u, exc, this.f14966s.c, 4);
    }

    @Override // g4.g
    public final void cancel() {
        k4.p pVar = this.f14966s;
        if (pVar != null) {
            pVar.c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void d(Object obj) {
        this.l.b(this.f14963p, obj, this.f14966s.c, 4, this.f14968u);
    }

    @Override // g4.g
    public final boolean e() {
        List list;
        ArrayList arrayListC;
        ArrayList arrayListA = this.f14961m.a();
        boolean z6 = false;
        if (arrayListA.isEmpty()) {
            return false;
        }
        h hVar = this.f14961m;
        com.bumptech.glide.h hVarA = hVar.c.a();
        Class<?> cls = hVar.d.getClass();
        Class cls2 = hVar.f14866g;
        Class cls3 = hVar.f14870k;
        C1778f c1778f = hVarA.f12692h;
        A4.o oVar = (A4.o) ((AtomicReference) c1778f.f18638m).getAndSet(null);
        if (oVar == null) {
            oVar = new A4.o(cls, cls2, cls3);
        } else {
            oVar.f429a = cls;
            oVar.f430b = cls2;
            oVar.c = cls3;
        }
        synchronized (((C2465e) c1778f.f18639n)) {
            list = (List) ((C2465e) c1778f.f18639n).get(oVar);
        }
        ((AtomicReference) c1778f.f18638m).set(oVar);
        List list2 = list;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            k4.t tVar = hVarA.f12687a;
            synchronized (tVar) {
                arrayListC = tVar.f18208a.c(cls);
            }
            Iterator it = arrayListC.iterator();
            while (it.hasNext()) {
                for (Class cls4 : hVarA.c.A((Class) it.next(), cls2)) {
                    if (!hVarA.f12690f.s(cls4, cls3).isEmpty() && !arrayList.contains(cls4)) {
                        arrayList.add(cls4);
                    }
                }
            }
            C1778f c1778f2 = hVarA.f12692h;
            List listUnmodifiableList = Collections.unmodifiableList(arrayList);
            synchronized (((C2465e) c1778f2.f18639n)) {
                ((C2465e) c1778f2.f18639n).put(new A4.o(cls, cls2, cls3), listUnmodifiableList);
            }
            list2 = arrayList;
        }
        if (list2.isEmpty()) {
            if (File.class.equals(this.f14961m.f14870k)) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.f14961m.d.getClass() + " to " + this.f14961m.f14870k);
        }
        while (true) {
            List list3 = this.f14964q;
            if (list3 != null && this.f14965r < list3.size()) {
                this.f14966s = null;
                while (!z6 && this.f14965r < this.f14964q.size()) {
                    List list4 = this.f14964q;
                    int i7 = this.f14965r;
                    this.f14965r = i7 + 1;
                    k4.q qVar = (k4.q) list4.get(i7);
                    File file = this.f14967t;
                    h hVar2 = this.f14961m;
                    this.f14966s = qVar.a(file, hVar2.f14864e, hVar2.f14865f, hVar2.f14868i);
                    if (this.f14966s != null && this.f14961m.c(this.f14966s.c.a()) != null) {
                        this.f14966s.c.f(this.f14961m.o, this);
                        z6 = true;
                    }
                }
                return z6;
            }
            int i10 = this.o + 1;
            this.o = i10;
            if (i10 >= list2.size()) {
                int i11 = this.f14962n + 1;
                this.f14962n = i11;
                if (i11 >= arrayListA.size()) {
                    return false;
                }
                this.o = 0;
            }
            e4.e eVar = (e4.e) arrayListA.get(this.f14962n);
            Class cls5 = (Class) list2.get(this.o);
            e4.l lVarE = this.f14961m.e(cls5);
            h hVar3 = this.f14961m;
            this.f14968u = new z(hVar3.c.f12676a, eVar, hVar3.f14872n, hVar3.f14864e, hVar3.f14865f, lVarE, cls5, hVar3.f14868i);
            File fileD = hVar3.f14867h.a().d(this.f14968u);
            this.f14967t = fileD;
            if (fileD != null) {
                this.f14963p = eVar;
                this.f14964q = this.f14961m.c.a().f(fileD);
                this.f14965r = 0;
            }
        }
    }
}
