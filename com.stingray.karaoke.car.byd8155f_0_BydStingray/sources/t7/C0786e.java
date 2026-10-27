package T7;

import Q7.InterfaceC0654g;
import java.util.Collection;
import java.util.List;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: T7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0786e implements F8.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ D8.v f8834a;

    public C0786e(D8.v vVar) {
        this.f8834a = vVar;
    }

    @Override // F8.K
    public final N7.i l() {
        return AbstractC2564d.e(this.f8834a);
    }

    @Override // F8.K
    public final boolean m() {
        return true;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        return this.f8834a;
    }

    @Override // F8.K
    public final Collection o() {
        Collection collectionO = this.f8834a.e1().B0().o();
        A7.m.e(collectionO, "declarationDescriptor.un…pe.constructor.supertypes");
        return collectionO;
    }

    @Override // F8.K
    public final List p() {
        List list = this.f8834a.B;
        if (list != null) {
            return list;
        }
        A7.m.j("typeConstructorParameters");
        throw null;
    }

    public final String toString() {
        return "[typealias " + this.f8834a.getName().b() + ']';
    }
}
