package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0671y;

/* JADX INFO: loaded from: classes.dex */
public final class j extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22502b;

    public j(String str) {
        super(m7.q.f19267a);
        this.f22502b = str;
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        A7.m.f(interfaceC0671y, "module");
        return H8.i.c(H8.h.f2869E, this.f22502b);
    }

    @Override // t8.g
    public final Object b() {
        throw new UnsupportedOperationException();
    }

    @Override // t8.g
    public final String toString() {
        return this.f22502b;
    }
}
