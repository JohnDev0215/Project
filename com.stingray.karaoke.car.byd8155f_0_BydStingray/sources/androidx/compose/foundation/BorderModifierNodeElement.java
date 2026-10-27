package androidx.compose.foundation;

import A7.m;
import E0.V;
import g0.n;
import k0.C1590b;
import kotlin.Metadata;
import n0.AbstractC1887p;
import n0.InterfaceC1866P;
import y.C2715u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/BorderModifierNodeElement;", "LE0/V;", "Ly/u;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class BorderModifierNodeElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC1887p f11401b;
    public final InterfaceC1866P c;

    public BorderModifierNodeElement(float f10, AbstractC1887p abstractC1887p, InterfaceC1866P interfaceC1866P) {
        this.f11400a = f10;
        this.f11401b = abstractC1887p;
        this.c = interfaceC1866P;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderModifierNodeElement)) {
            return false;
        }
        BorderModifierNodeElement borderModifierNodeElement = (BorderModifierNodeElement) obj;
        return a1.e.a(this.f11400a, borderModifierNodeElement.f11400a) && this.f11401b.equals(borderModifierNodeElement.f11401b) && m.a(this.c, borderModifierNodeElement.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.f11401b.hashCode() + (Float.hashCode(this.f11400a) * 31)) * 31);
    }

    @Override // E0.V
    public final n i() {
        return new C2715u(this.f11400a, this.f11401b, this.c);
    }

    @Override // E0.V
    public final void l(n nVar) {
        C2715u c2715u = (C2715u) nVar;
        float f10 = c2715u.B;
        float f11 = this.f11400a;
        boolean zA = a1.e.a(f10, f11);
        C1590b c1590b = c2715u.f24010E;
        if (!zA) {
            c2715u.B = f11;
            c1590b.H0();
        }
        AbstractC1887p abstractC1887p = c2715u.f24008C;
        AbstractC1887p abstractC1887p2 = this.f11401b;
        if (!m.a(abstractC1887p, abstractC1887p2)) {
            c2715u.f24008C = abstractC1887p2;
            c1590b.H0();
        }
        InterfaceC1866P interfaceC1866P = c2715u.f24009D;
        InterfaceC1866P interfaceC1866P2 = this.c;
        if (m.a(interfaceC1866P, interfaceC1866P2)) {
            return;
        }
        c2715u.f24009D = interfaceC1866P2;
        c1590b.H0();
    }

    public final String toString() {
        return "BorderModifierNodeElement(width=" + ((Object) a1.e.b(this.f11400a)) + ", brush=" + this.f11401b + ", shape=" + this.c + ')';
    }
}
