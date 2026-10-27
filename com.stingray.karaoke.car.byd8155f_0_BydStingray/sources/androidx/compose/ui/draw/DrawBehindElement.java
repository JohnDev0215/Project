package androidx.compose.ui.draw;

import A7.m;
import E0.V;
import g0.n;
import k0.C1592d;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/DrawBehindElement;", "LE0/V;", "Lk0/d;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DrawBehindElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11548a;

    public DrawBehindElement(InterfaceC2782k interfaceC2782k) {
        this.f11548a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawBehindElement) && m.a(this.f11548a, ((DrawBehindElement) obj).f11548a);
    }

    public final int hashCode() {
        return this.f11548a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C1592d c1592d = new C1592d();
        c1592d.f17616y = this.f11548a;
        return c1592d;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C1592d) nVar).f17616y = this.f11548a;
    }

    public final String toString() {
        return "DrawBehindElement(onDraw=" + this.f11548a + ')';
    }
}
