package androidx.compose.ui.layout;

import A7.m;
import C0.C0111s;
import E0.V;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/LayoutIdElement;", "LE0/V;", "LC0/s;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class LayoutIdElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f11613a;

    public LayoutIdElement(Object obj) {
        this.f11613a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutIdElement) && m.a(this.f11613a, ((LayoutIdElement) obj).f11613a);
    }

    public final int hashCode() {
        return this.f11613a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C0111s c0111s = new C0111s();
        c0111s.f912y = this.f11613a;
        return c0111s;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C0111s) nVar).f912y = this.f11613a;
    }

    public final String toString() {
        return "LayoutIdElement(layoutId=" + this.f11613a + ')';
    }
}
