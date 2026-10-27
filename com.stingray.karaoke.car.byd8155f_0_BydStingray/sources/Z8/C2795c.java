package z8;

import A7.m;
import F8.A;
import F8.AbstractC0287w;
import Q7.InterfaceC0652e;

/* JADX INFO: renamed from: z8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2795c implements InterfaceC2796d {
    public final InterfaceC0652e l;

    public C2795c(InterfaceC0652e interfaceC0652e) {
        m.f(interfaceC0652e, "classDescriptor");
        this.l = interfaceC0652e;
    }

    @Override // z8.InterfaceC2796d
    public final AbstractC0287w b() {
        A aJ = this.l.j();
        m.e(aJ, "classDescriptor.defaultType");
        return aJ;
    }

    public final boolean equals(Object obj) {
        C2795c c2795c = obj instanceof C2795c ? (C2795c) obj : null;
        return m.a(this.l, c2795c != null ? c2795c.l : null);
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Class{");
        A aJ = this.l.j();
        m.e(aJ, "classDescriptor.defaultType");
        sb.append(aJ);
        sb.append('}');
        return sb.toString();
    }
}
