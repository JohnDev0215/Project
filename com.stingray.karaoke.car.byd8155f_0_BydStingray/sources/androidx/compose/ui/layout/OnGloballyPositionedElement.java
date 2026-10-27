package androidx.compose.ui.layout;

import C0.P;
import E0.V;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/OnGloballyPositionedElement;", "LE0/V;", "LC0/P;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OnGloballyPositionedElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11614a;

    public OnGloballyPositionedElement(InterfaceC2782k interfaceC2782k) {
        this.f11614a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnGloballyPositionedElement) {
            return this.f11614a == ((OnGloballyPositionedElement) obj).f11614a;
        }
        return false;
    }

    public final int hashCode() {
        return this.f11614a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        P p10 = new P();
        p10.f866y = this.f11614a;
        return p10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((P) nVar).f866y = this.f11614a;
    }
}
