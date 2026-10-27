package androidx.compose.foundation;

import A7.m;
import C.k;
import E0.InterfaceC0163m;
import E0.V;
import g0.n;
import kotlin.Metadata;
import y.d0;
import y.e0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/IndicationModifierElement;", "LE0/V;", "Ly/d0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class IndicationModifierElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f11415b;

    public IndicationModifierElement(k kVar, e0 e0Var) {
        this.f11414a = kVar;
        this.f11415b = e0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndicationModifierElement)) {
            return false;
        }
        IndicationModifierElement indicationModifierElement = (IndicationModifierElement) obj;
        return m.a(this.f11414a, indicationModifierElement.f11414a) && m.a(this.f11415b, indicationModifierElement.f11415b);
    }

    public final int hashCode() {
        return this.f11415b.hashCode() + (this.f11414a.hashCode() * 31);
    }

    @Override // E0.V
    public final n i() {
        InterfaceC0163m interfaceC0163mB = this.f11415b.b(this.f11414a);
        d0 d0Var = new d0();
        d0Var.f23897A = interfaceC0163mB;
        d0Var.H0(interfaceC0163mB);
        return d0Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        d0 d0Var = (d0) nVar;
        InterfaceC0163m interfaceC0163mB = this.f11415b.b(this.f11414a);
        d0Var.I0(d0Var.f23897A);
        d0Var.f23897A = interfaceC0163mB;
        d0Var.H0(interfaceC0163mB);
    }
}
