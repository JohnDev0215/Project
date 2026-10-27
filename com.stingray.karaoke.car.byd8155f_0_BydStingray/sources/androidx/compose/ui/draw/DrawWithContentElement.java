package androidx.compose.ui.draw;

import A7.m;
import E0.V;
import g0.n;
import k0.C1594f;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/DrawWithContentElement;", "LE0/V;", "Lk0/f;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DrawWithContentElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11550a;

    public DrawWithContentElement(InterfaceC2782k interfaceC2782k) {
        this.f11550a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawWithContentElement) && m.a(this.f11550a, ((DrawWithContentElement) obj).f11550a);
    }

    public final int hashCode() {
        return this.f11550a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        C1594f c1594f = new C1594f();
        c1594f.f17617y = this.f11550a;
        return c1594f;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C1594f) nVar).f17617y = this.f11550a;
    }

    public final String toString() {
        return "DrawWithContentElement(onDraw=" + this.f11550a + ')';
    }
}
