package d5;

import c5.InterfaceC1028e;
import java.util.Iterator;
import java.util.NoSuchElementException;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class N extends s0 {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13391m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f13392n;
    public final Iterator o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f13393p;

    public N() {
        this.l = 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Iterator
    public final boolean hasNext() {
        Object next;
        int i7 = this.l;
        if (i7 == 4) {
            throw new IllegalStateException();
        }
        int iC = AbstractC2641j.c(i7);
        if (iC == 0) {
            return true;
        }
        if (iC == 2) {
            return false;
        }
        this.l = 4;
        switch (this.f13392n) {
            case 0:
                do {
                    Iterator it = this.o;
                    if (!it.hasNext()) {
                        this.l = 3;
                        next = null;
                    } else {
                        next = it.next();
                    }
                    break;
                } while (!((InterfaceC1028e) this.f13393p).apply(next));
                break;
            default:
                do {
                    Iterator it2 = this.o;
                    if (!it2.hasNext()) {
                        this.l = 3;
                        next = null;
                    } else {
                        next = it2.next();
                    }
                    break;
                } while (!((m0) this.f13393p).f13443m.contains(next));
                break;
        }
        this.f13391m = next;
        if (this.l == 3) {
            return false;
        }
        this.l = 1;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.l = 2;
        Object obj = this.f13391m;
        this.f13391m = null;
        return obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public N(Iterator it, InterfaceC1028e interfaceC1028e) {
        this();
        this.f13392n = 0;
        this.o = it;
        this.f13393p = interfaceC1028e;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public N(m0 m0Var) {
        this();
        this.f13392n = 1;
        this.f13393p = m0Var;
        this.o = m0Var.l.iterator();
    }
}
