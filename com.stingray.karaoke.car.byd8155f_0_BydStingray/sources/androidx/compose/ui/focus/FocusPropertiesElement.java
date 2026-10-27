package androidx.compose.ui.focus;

import A7.m;
import E0.V;
import g0.n;
import kotlin.Metadata;
import l0.C1717h;
import l0.C1720k;
import l0.C1722m;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusPropertiesElement;", "LE0/V;", "Ll0/m;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusPropertiesElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1720k f11563a;

    public FocusPropertiesElement(C1720k c1720k) {
        this.f11563a = c1720k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusPropertiesElement) && m.a(this.f11563a, ((FocusPropertiesElement) obj).f11563a);
    }

    public final int hashCode() {
        return C1717h.f18333n.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C1722m c1722m = new C1722m();
        c1722m.f18347y = this.f11563a;
        return c1722m;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C1722m) nVar).f18347y = this.f11563a;
    }

    public final String toString() {
        return "FocusPropertiesElement(scope=" + this.f11563a + ')';
    }
}
