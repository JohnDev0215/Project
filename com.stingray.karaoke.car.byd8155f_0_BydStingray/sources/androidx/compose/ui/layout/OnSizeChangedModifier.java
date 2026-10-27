package androidx.compose.ui.layout;

import C0.Q;
import E0.V;
import H7.H;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/OnSizeChangedModifier;", "LE0/V;", "LC0/Q;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OnSizeChangedModifier extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11615a;

    public OnSizeChangedModifier(InterfaceC2782k interfaceC2782k) {
        this.f11615a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnSizeChangedModifier) {
            return this.f11615a == ((OnSizeChangedModifier) obj).f11615a;
        }
        return false;
    }

    public final int hashCode() {
        return this.f11615a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        Q q4 = new Q();
        q4.f867y = this.f11615a;
        q4.f868z = H.c(Integer.MIN_VALUE, Integer.MIN_VALUE);
        return q4;
    }

    @Override // E0.V
    public final void l(n nVar) {
        Q q4 = (Q) nVar;
        q4.f867y = this.f11615a;
        q4.f868z = H.c(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
}
