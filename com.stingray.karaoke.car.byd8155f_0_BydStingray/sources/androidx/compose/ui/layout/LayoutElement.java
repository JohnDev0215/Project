package androidx.compose.ui.layout;

import A7.m;
import C0.C0116x;
import E0.V;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/LayoutElement;", "LE0/V;", "LC0/x;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class LayoutElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2786o f11612a;

    public LayoutElement(InterfaceC2786o interfaceC2786o) {
        this.f11612a = interfaceC2786o;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutElement) && m.a(this.f11612a, ((LayoutElement) obj).f11612a);
    }

    public final int hashCode() {
        return this.f11612a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C0116x c0116x = new C0116x();
        c0116x.f920y = this.f11612a;
        return c0116x;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C0116x) nVar).f920y = this.f11612a;
    }

    public final String toString() {
        return "LayoutElement(measure=" + this.f11612a + ')';
    }
}
