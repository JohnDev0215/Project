package Y;

import A7.E;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class f extends d {
    public final e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f10594p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f10595q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f10596r;

    public f(e eVar, n[] nVarArr) {
        super(eVar.f10591n, nVarArr);
        this.o = eVar;
        this.f10596r = eVar.f10592p;
    }

    public final void d(int i7, m mVar, Object obj, int i10) {
        int i11 = i10 * 5;
        n[] nVarArr = this.l;
        if (i11 <= 30) {
            int iW = 1 << l9.d.W(i7, i11);
            if (mVar.h(iW)) {
                nVarArr[i10].b(mVar.d, Integer.bitCount(mVar.f10601a) * 2, mVar.f(iW));
                this.f10588m = i10;
                return;
            } else {
                int iT = mVar.t(iW);
                m mVarS = mVar.s(iT);
                nVarArr[i10].b(mVar.d, Integer.bitCount(mVar.f10601a) * 2, iT);
                d(i7, mVarS, obj, i10 + 1);
                return;
            }
        }
        n nVar = nVarArr[i10];
        Object[] objArr = mVar.d;
        nVar.b(objArr, objArr.length, 0);
        while (true) {
            n nVar2 = nVarArr[i10];
            if (A7.m.a(nVar2.l[nVar2.f10604n], obj)) {
                this.f10588m = i10;
                return;
            } else {
                nVarArr[i10].f10604n += 2;
            }
        }
    }

    @Override // Y.d, java.util.Iterator
    public final Object next() {
        if (this.o.f10592p != this.f10596r) {
            throw new ConcurrentModificationException();
        }
        if (!this.f10589n) {
            throw new NoSuchElementException();
        }
        n nVar = this.l[this.f10588m];
        this.f10594p = nVar.l[nVar.f10604n];
        this.f10595q = true;
        return super.next();
    }

    @Override // Y.d, java.util.Iterator
    public final void remove() {
        if (!this.f10595q) {
            throw new IllegalStateException();
        }
        boolean z6 = this.f10589n;
        e eVar = this.o;
        if (!z6) {
            E.c(eVar).remove(this.f10594p);
        } else {
            if (!z6) {
                throw new NoSuchElementException();
            }
            n nVar = this.l[this.f10588m];
            Object obj = nVar.l[nVar.f10604n];
            E.c(eVar).remove(this.f10594p);
            d(obj != null ? obj.hashCode() : 0, eVar.f10591n, obj, 0);
        }
        this.f10594p = null;
        this.f10595q = false;
        this.f10596r = eVar.f10592p;
    }
}
