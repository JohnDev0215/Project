package w;

import h2.AbstractC1347d;
import x.InterfaceC2615D;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.c f23139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f23140b;
    public final InterfaceC2615D c;
    public final boolean d;

    public t(g0.c cVar, InterfaceC2782k interfaceC2782k, InterfaceC2615D interfaceC2615D, boolean z6) {
        this.f23139a = cVar;
        this.f23140b = interfaceC2782k;
        this.c = interfaceC2615D;
        this.d = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return A7.m.a(this.f23139a, tVar.f23139a) && A7.m.a(this.f23140b, tVar.f23140b) && A7.m.a(this.c, tVar.c) && this.d == tVar.d;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.d) + ((this.c.hashCode() + ((this.f23140b.hashCode() + (this.f23139a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChangeSize(alignment=");
        sb.append(this.f23139a);
        sb.append(", size=");
        sb.append(this.f23140b);
        sb.append(", animationSpec=");
        sb.append(this.c);
        sb.append(", clip=");
        return AbstractC1347d.i(sb, this.d, ')');
    }
}
