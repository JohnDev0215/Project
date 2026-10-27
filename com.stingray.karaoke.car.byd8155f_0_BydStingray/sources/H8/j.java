package H8;

import A7.m;
import Q7.InterfaceC0654g;
import java.util.Collection;
import java.util.Set;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class j extends e {
    @Override // H8.e, y8.n
    public final /* bridge */ /* synthetic */ Collection a(C2061f c2061f, Y7.b bVar) {
        a(c2061f, bVar);
        throw null;
    }

    @Override // H8.e, y8.n
    public final /* bridge */ /* synthetic */ Collection b(C2061f c2061f, Y7.b bVar) {
        b(c2061f, bVar);
        throw null;
    }

    @Override // H8.e, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        m.f(bVar, "location");
        throw new IllegalStateException(this.f2857b + ", required name: " + c2061f);
    }

    @Override // H8.e, y8.p
    public final Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        m.f(fVar, "kindFilter");
        m.f(interfaceC2782k, "nameFilter");
        throw new IllegalStateException(this.f2857b);
    }

    @Override // H8.e, y8.n
    public final Set e() {
        throw new IllegalStateException();
    }

    @Override // H8.e, y8.n
    public final Set f() {
        throw new IllegalStateException();
    }

    @Override // H8.e, y8.n
    public final Set g() {
        throw new IllegalStateException();
    }

    @Override // H8.e
    /* JADX INFO: renamed from: h */
    public final Set a(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        throw new IllegalStateException(this.f2857b + ", required name: " + c2061f);
    }

    @Override // H8.e
    /* JADX INFO: renamed from: i */
    public final Set b(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        throw new IllegalStateException(this.f2857b + ", required name: " + c2061f);
    }

    @Override // H8.e
    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("ThrowingScope{"), this.f2857b, '}');
    }
}
