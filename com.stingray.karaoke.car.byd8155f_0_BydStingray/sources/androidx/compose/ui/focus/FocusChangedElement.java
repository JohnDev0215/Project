package androidx.compose.ui.focus;

import A7.m;
import E0.V;
import g0.n;
import kotlin.Metadata;
import l0.C1710a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusChangedElement;", "LE0/V;", "Ll0/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusChangedElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11561a;

    public FocusChangedElement(InterfaceC2782k interfaceC2782k) {
        this.f11561a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusChangedElement) && m.a(this.f11561a, ((FocusChangedElement) obj).f11561a);
    }

    public final int hashCode() {
        return this.f11561a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C1710a c1710a = new C1710a();
        c1710a.f18323y = this.f11561a;
        return c1710a;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C1710a) nVar).f18323y = this.f11561a;
    }

    public final String toString() {
        return "FocusChangedElement(onFocusChanged=" + this.f11561a + ')';
    }
}
