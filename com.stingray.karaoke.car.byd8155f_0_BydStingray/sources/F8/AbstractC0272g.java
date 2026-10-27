package F8;

import A.I0;
import Q7.InterfaceC0654g;
import java.util.Collection;
import java.util.List;

/* JADX INFO: renamed from: F8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0272g implements K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.d f2467b;

    public AbstractC0272g(E8.o oVar) {
        A7.m.f(oVar, "storageManager");
        this.f2467b = new E8.d((E8.l) oVar, new I0(this, 15), new A.F(this, 14));
    }

    public abstract Collection b();

    public abstract AbstractC0287w c();

    public abstract Q7.O d();

    @Override // F8.K
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final List o() {
        return ((C0270e) this.f2467b.a()).f2463b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof K) || obj.hashCode() != hashCode()) {
            return false;
        }
        K k10 = (K) obj;
        if (k10.p().size() != p().size()) {
            return false;
        }
        InterfaceC0654g interfaceC0654gN = n();
        InterfaceC0654g interfaceC0654gN2 = k10.n();
        if (interfaceC0654gN2 == null || H8.i.f(interfaceC0654gN) || r8.d.o(interfaceC0654gN) || H8.i.f(interfaceC0654gN2) || r8.d.o(interfaceC0654gN2)) {
            return false;
        }
        return f(interfaceC0654gN2);
    }

    public abstract boolean f(InterfaceC0654g interfaceC0654g);

    public List g(List list) {
        return list;
    }

    public final int hashCode() {
        int i7 = this.f2466a;
        if (i7 != 0) {
            return i7;
        }
        InterfaceC0654g interfaceC0654gN = n();
        int iIdentityHashCode = (H8.i.f(interfaceC0654gN) || r8.d.o(interfaceC0654gN)) ? System.identityHashCode(this) : r8.d.g(interfaceC0654gN).f20571a.hashCode();
        this.f2466a = iIdentityHashCode;
        return iIdentityHashCode;
    }
}
