package androidx.compose.animation;

import A7.m;
import E0.V;
import g0.n;
import kotlin.Metadata;
import w.C2569E;
import w.F;
import w.G;
import w.x;
import x.r0;
import x.x0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/animation/EnterExitTransitionElement;", "LE0/V;", "Lw/E;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class EnterExitTransitionElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x0 f11392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0 f11393b;
    public final r0 c;
    public final r0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final F f11394e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final G f11395f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final InterfaceC2772a f11396g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x f11397h;

    public EnterExitTransitionElement(x0 x0Var, r0 r0Var, r0 r0Var2, r0 r0Var3, F f10, G g7, InterfaceC2772a interfaceC2772a, x xVar) {
        this.f11392a = x0Var;
        this.f11393b = r0Var;
        this.c = r0Var2;
        this.d = r0Var3;
        this.f11394e = f10;
        this.f11395f = g7;
        this.f11396g = interfaceC2772a;
        this.f11397h = xVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnterExitTransitionElement)) {
            return false;
        }
        EnterExitTransitionElement enterExitTransitionElement = (EnterExitTransitionElement) obj;
        return m.a(this.f11392a, enterExitTransitionElement.f11392a) && m.a(this.f11393b, enterExitTransitionElement.f11393b) && m.a(this.c, enterExitTransitionElement.c) && m.a(this.d, enterExitTransitionElement.d) && m.a(this.f11394e, enterExitTransitionElement.f11394e) && m.a(this.f11395f, enterExitTransitionElement.f11395f) && m.a(this.f11396g, enterExitTransitionElement.f11396g) && m.a(this.f11397h, enterExitTransitionElement.f11397h);
    }

    public final int hashCode() {
        int iHashCode = this.f11392a.hashCode() * 31;
        r0 r0Var = this.f11393b;
        int iHashCode2 = (iHashCode + (r0Var == null ? 0 : r0Var.hashCode())) * 31;
        r0 r0Var2 = this.c;
        int iHashCode3 = (iHashCode2 + (r0Var2 == null ? 0 : r0Var2.hashCode())) * 31;
        r0 r0Var3 = this.d;
        return this.f11397h.hashCode() + ((this.f11396g.hashCode() + ((this.f11395f.f23069a.hashCode() + ((this.f11394e.f23067a.hashCode() + ((iHashCode3 + (r0Var3 != null ? r0Var3.hashCode() : 0)) * 31)) * 31)) * 31)) * 31);
    }

    @Override // E0.V
    public final n i() {
        return new C2569E(this.f11392a, this.f11393b, this.c, this.d, this.f11394e, this.f11395f, this.f11396g, this.f11397h);
    }

    @Override // E0.V
    public final void l(n nVar) {
        C2569E c2569e = (C2569E) nVar;
        c2569e.f23064y = this.f11392a;
        c2569e.f23065z = this.f11393b;
        c2569e.f23055A = this.c;
        c2569e.B = this.d;
        c2569e.f23056C = this.f11394e;
        c2569e.f23057D = this.f11395f;
        c2569e.f23058E = this.f11396g;
        c2569e.f23059F = this.f11397h;
    }

    public final String toString() {
        return "EnterExitTransitionElement(transition=" + this.f11392a + ", sizeAnimation=" + this.f11393b + ", offsetAnimation=" + this.c + ", slideAnimation=" + this.d + ", enter=" + this.f11394e + ", exit=" + this.f11395f + ", isEnabled=" + this.f11396g + ", graphicsLayerBlock=" + this.f11397h + ')';
    }
}
