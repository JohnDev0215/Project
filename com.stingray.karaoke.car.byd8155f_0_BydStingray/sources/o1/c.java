package o1;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import p1.AbstractC2081i;
import p1.o;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20251b;
    public boolean c;
    public final d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f20252e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f20253f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h1.e f20256i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet f20250a = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20254g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20255h = Integer.MIN_VALUE;

    public c(d dVar, int i7) {
        this.d = dVar;
        this.f20252e = i7;
    }

    public final void a(c cVar, int i7) {
        b(cVar, i7, Integer.MIN_VALUE, false);
    }

    public final boolean b(c cVar, int i7, int i10, boolean z6) {
        if (cVar == null) {
            j();
            return true;
        }
        if (!z6 && !i(cVar)) {
            return false;
        }
        this.f20253f = cVar;
        if (cVar.f20250a == null) {
            cVar.f20250a = new HashSet();
        }
        HashSet hashSet = this.f20253f.f20250a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.f20254g = i7;
        this.f20255h = i10;
        return true;
    }

    public final void c(int i7, ArrayList arrayList, o oVar) {
        HashSet hashSet = this.f20250a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                AbstractC2081i.b(((c) it.next()).d, i7, arrayList, oVar);
            }
        }
    }

    public final int d() {
        if (this.c) {
            return this.f20251b;
        }
        return 0;
    }

    public final int e() {
        c cVar;
        if (this.d.f20295h0 == 8) {
            return 0;
        }
        int i7 = this.f20255h;
        return (i7 == Integer.MIN_VALUE || (cVar = this.f20253f) == null || cVar.d.f20295h0 != 8) ? this.f20254g : i7;
    }

    public final c f() {
        int i7 = this.f20252e;
        int iC = AbstractC2641j.c(i7);
        d dVar = this.d;
        switch (iC) {
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
                return null;
            case 1:
                return dVar.f20267L;
            case 2:
                return dVar.f20268M;
            case 3:
                return dVar.f20265J;
            case 4:
                return dVar.f20266K;
            default:
                throw new AssertionError(AbstractC1347d.u(i7));
        }
    }

    public final boolean g() {
        HashSet hashSet = this.f20250a;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((c) it.next()).f().h()) {
                return true;
            }
        }
        return false;
    }

    public final boolean h() {
        return this.f20253f != null;
    }

    public final boolean i(c cVar) {
        if (cVar == null) {
            return false;
        }
        d dVar = cVar.d;
        int i7 = this.f20252e;
        int i10 = cVar.f20252e;
        if (i10 == i7) {
            return i7 != 6 || (dVar.f20261F && this.d.f20261F);
        }
        switch (AbstractC2641j.c(i7)) {
            case 0:
            case 7:
            case 8:
                return false;
            case 1:
            case 3:
                boolean z6 = i10 == 2 || i10 == 4;
                if (dVar instanceof h) {
                    return z6 || i10 == 8;
                }
                return z6;
            case 2:
            case 4:
                boolean z10 = i10 == 3 || i10 == 5;
                if (dVar instanceof h) {
                    return z10 || i10 == 9;
                }
                return z10;
            case 5:
                return (i10 == 2 || i10 == 4) ? false : true;
            case 6:
                return (i10 == 6 || i10 == 8 || i10 == 9) ? false : true;
            default:
                throw new AssertionError(AbstractC1347d.u(i7));
        }
    }

    public final void j() {
        HashSet hashSet;
        c cVar = this.f20253f;
        if (cVar != null && (hashSet = cVar.f20250a) != null) {
            hashSet.remove(this);
            if (this.f20253f.f20250a.size() == 0) {
                this.f20253f.f20250a = null;
            }
        }
        this.f20250a = null;
        this.f20253f = null;
        this.f20254g = 0;
        this.f20255h = Integer.MIN_VALUE;
        this.c = false;
        this.f20251b = 0;
    }

    public final void k() {
        h1.e eVar = this.f20256i;
        if (eVar == null) {
            this.f20256i = new h1.e(1);
        } else {
            eVar.c();
        }
    }

    public final void l(int i7) {
        this.f20251b = i7;
        this.c = true;
    }

    public final String toString() {
        return this.d.f20297i0 + ":" + AbstractC1347d.u(this.f20252e);
    }
}
