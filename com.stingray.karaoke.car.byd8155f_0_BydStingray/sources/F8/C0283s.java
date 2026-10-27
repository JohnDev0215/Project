package F8;

import q8.C2203g;
import q8.C2206j;

/* JADX INFO: renamed from: F8.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0283s extends AbstractC0282q implements Z {
    public final AbstractC0282q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AbstractC0287w f2479p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0283s(AbstractC0282q abstractC0282q, AbstractC0287w abstractC0287w) {
        super(abstractC0282q.f2477m, abstractC0282q.f2478n);
        A7.m.f(abstractC0282q, "origin");
        A7.m.f(abstractC0287w, "enhancement");
        this.o = abstractC0282q;
        this.f2479p = abstractC0287w;
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        AbstractC0282q abstractC0282q = this.o;
        A7.m.f(abstractC0282q, "type");
        AbstractC0287w abstractC0287w = this.f2479p;
        A7.m.f(abstractC0287w, "type");
        return new C0283s(abstractC0282q, abstractC0287w);
    }

    @Override // F8.a0
    public final a0 L0(boolean z6) {
        return AbstractC0268c.A(this.o.L0(z6), this.f2479p.K0().L0(z6));
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        AbstractC0282q abstractC0282q = this.o;
        A7.m.f(abstractC0282q, "type");
        AbstractC0287w abstractC0287w = this.f2479p;
        A7.m.f(abstractC0287w, "type");
        return new C0283s(abstractC0282q, abstractC0287w);
    }

    @Override // F8.a0
    public final a0 N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return AbstractC0268c.A(this.o.N0(h10), this.f2479p);
    }

    @Override // F8.AbstractC0282q
    public final A O0() {
        return this.o.O0();
    }

    @Override // F8.AbstractC0282q
    public final String P0(C2203g c2203g, C2203g c2203g2) {
        A7.m.f(c2203g, "renderer");
        C2206j c2206j = c2203g2.f21281a;
        c2206j.getClass();
        return ((Boolean) c2206j.f21329m.a(C2206j.f21298W[11], c2206j)).booleanValue() ? c2203g.Y(this.f2479p) : this.o.P0(c2203g, c2203g2);
    }

    @Override // F8.Z
    public final AbstractC0287w i() {
        return this.f2479p;
    }

    @Override // F8.Z
    public final a0 i0() {
        return this.o;
    }

    @Override // F8.AbstractC0282q
    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f2479p + ")] " + this.o;
    }
}
