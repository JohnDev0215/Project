package androidx.compose.ui.draw;

import A7.m;
import C0.InterfaceC0103j;
import E0.AbstractC0156f;
import E0.V;
import g0.c;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import m0.f;
import n0.C1883l;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/draw/PainterElement;", "LE0/V;", "Landroidx/compose/ui/draw/PainterNode;", "Lr0/a;", "painter", "Lr0/a;", "getPainter", "()Lr0/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class PainterElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f11551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f11552b;
    public final InterfaceC0103j c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1883l f11553e;
    private final AbstractC2268a painter;

    public PainterElement(AbstractC2268a abstractC2268a, boolean z6, c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l) {
        this.painter = abstractC2268a;
        this.f11551a = z6;
        this.f11552b = cVar;
        this.c = interfaceC0103j;
        this.d = f10;
        this.f11553e = c1883l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PainterElement)) {
            return false;
        }
        PainterElement painterElement = (PainterElement) obj;
        return m.a(this.painter, painterElement.painter) && this.f11551a == painterElement.f11551a && m.a(this.f11552b, painterElement.f11552b) && m.a(this.c, painterElement.c) && Float.compare(this.d, painterElement.d) == 0 && m.a(this.f11553e, painterElement.f11553e);
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b((this.c.hashCode() + ((this.f11552b.hashCode() + AbstractC1347d.d(this.painter.hashCode() * 31, 31, this.f11551a)) * 31)) * 31, 31, this.d);
        C1883l c1883l = this.f11553e;
        return iB + (c1883l == null ? 0 : c1883l.hashCode());
    }

    @Override // E0.V
    public final n i() {
        return new PainterNode(this.painter, this.f11551a, this.f11552b, this.c, this.d, this.f11553e);
    }

    @Override // E0.V
    public final void l(n nVar) {
        PainterNode painterNode = (PainterNode) nVar;
        boolean z6 = painterNode.f11556y;
        boolean z10 = this.f11551a;
        boolean z11 = z6 != z10 || (z10 && !f.a(painterNode.getPainter().h(), this.painter.h()));
        painterNode.M0(this.painter);
        painterNode.f11556y = z10;
        painterNode.f11557z = this.f11552b;
        painterNode.f11554A = this.c;
        painterNode.B = this.d;
        painterNode.f11555C = this.f11553e;
        if (z11) {
            AbstractC0156f.n(painterNode);
        }
        AbstractC0156f.m(painterNode);
    }

    public final String toString() {
        return "PainterElement(painter=" + this.painter + ", sizeToIntrinsics=" + this.f11551a + ", alignment=" + this.f11552b + ", contentScale=" + this.c + ", alpha=" + this.d + ", colorFilter=" + this.f11553e + ')';
    }
}
