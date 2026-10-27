package P8;

import h8.AbstractC1386c;
import java.util.Iterator;
import java.util.NoSuchElementException;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class l implements Iterator, InterfaceC2189c, B7.a {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f5475m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f5476n;
    public InterfaceC2189c o;

    public final RuntimeException b() {
        int i7 = this.l;
        if (i7 == 4) {
            return new NoSuchElementException();
        }
        if (i7 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.l);
    }

    public final void c(Object obj, s7.h hVar) {
        this.f5475m = obj;
        this.l = 3;
        this.o = hVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return C2195i.l;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        while (true) {
            int i7 = this.l;
            if (i7 != 0) {
                if (i7 != 1) {
                    if (i7 == 2 || i7 == 3) {
                        return true;
                    }
                    if (i7 == 4) {
                        return false;
                    }
                    throw b();
                }
                Iterator it = this.f5476n;
                A7.m.c(it);
                if (it.hasNext()) {
                    this.l = 2;
                    return true;
                }
                this.f5476n = null;
            }
            this.l = 5;
            InterfaceC2189c interfaceC2189c = this.o;
            A7.m.c(interfaceC2189c);
            this.o = null;
            interfaceC2189c.resumeWith(m7.q.f19267a);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i7 = this.l;
        if (i7 == 0 || i7 == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i7 == 2) {
            this.l = 1;
            Iterator it = this.f5476n;
            A7.m.c(it);
            return it.next();
        }
        if (i7 != 3) {
            throw b();
        }
        this.l = 0;
        Object obj = this.f5475m;
        this.f5475m = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) throws Throwable {
        AbstractC1386c.u(obj);
        this.l = 4;
    }
}
