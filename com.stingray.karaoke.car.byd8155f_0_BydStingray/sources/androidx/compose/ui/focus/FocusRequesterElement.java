package androidx.compose.ui.focus;

import A7.m;
import E0.V;
import g0.n;
import kotlin.Metadata;
import l0.C1723n;
import l0.C1725p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusRequesterElement;", "LE0/V;", "Ll0/p;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusRequesterElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1723n f11564a;

    public FocusRequesterElement(C1723n c1723n) {
        this.f11564a = c1723n;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FocusRequesterElement) && m.a(this.f11564a, ((FocusRequesterElement) obj).f11564a);
    }

    public final int hashCode() {
        return this.f11564a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C1725p c1725p = new C1725p();
        c1725p.f18350y = this.f11564a;
        return c1725p;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C1725p c1725p = (C1725p) nVar;
        c1725p.f18350y.f18349a.n(c1725p);
        C1723n c1723n = this.f11564a;
        c1725p.f18350y = c1723n;
        c1723n.f18349a.b(c1725p);
    }

    public final String toString() {
        return "FocusRequesterElement(focusRequester=" + this.f11564a + ')';
    }
}
