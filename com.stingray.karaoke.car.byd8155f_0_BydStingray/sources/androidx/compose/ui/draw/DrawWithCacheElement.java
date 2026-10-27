package androidx.compose.ui.draw;

import A7.m;
import E0.V;
import g0.n;
import k0.C1590b;
import k0.C1591c;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/DrawWithCacheElement;", "LE0/V;", "Lk0/b;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DrawWithCacheElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11549a;

    public DrawWithCacheElement(InterfaceC2782k interfaceC2782k) {
        this.f11549a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DrawWithCacheElement) && m.a(this.f11549a, ((DrawWithCacheElement) obj).f11549a);
    }

    public final int hashCode() {
        return this.f11549a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        return new C1590b(new C1591c(), this.f11549a);
    }

    @Override // E0.V
    public final void l(n nVar) {
        C1590b c1590b = (C1590b) nVar;
        c1590b.f17612A = this.f11549a;
        c1590b.H0();
    }

    public final String toString() {
        return "DrawWithCacheElement(onBuildDrawCache=" + this.f11549a + ')';
    }
}
