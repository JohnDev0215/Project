package coil.compose;

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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcoil/compose/ContentPainterElement;", "LE0/V;", "Lcoil/compose/ContentPainterNode;", "Lr0/a;", "painter", "Lr0/a;", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ContentPainterElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f12646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0103j f12647b;
    public final float c;
    public final C1883l d;
    private final AbstractC2268a painter;

    public ContentPainterElement(AsyncImagePainter asyncImagePainter, c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l) {
        this.painter = asyncImagePainter;
        this.f12646a = cVar;
        this.f12647b = interfaceC0103j;
        this.c = f10;
        this.d = c1883l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContentPainterElement)) {
            return false;
        }
        ContentPainterElement contentPainterElement = (ContentPainterElement) obj;
        return m.a(this.painter, contentPainterElement.painter) && m.a(this.f12646a, contentPainterElement.f12646a) && m.a(this.f12647b, contentPainterElement.f12647b) && Float.compare(this.c, contentPainterElement.c) == 0 && m.a(this.d, contentPainterElement.d);
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b((this.f12647b.hashCode() + ((this.f12646a.hashCode() + (this.painter.hashCode() * 31)) * 31)) * 31, 31, this.c);
        C1883l c1883l = this.d;
        return iB + (c1883l == null ? 0 : c1883l.hashCode());
    }

    @Override // E0.V
    public final n i() {
        return new ContentPainterNode(this.painter, this.f12646a, this.f12647b, this.c, this.d);
    }

    @Override // E0.V
    public final void l(n nVar) {
        ContentPainterNode contentPainterNode = (ContentPainterNode) nVar;
        boolean zA = f.a(contentPainterNode.getPainter().h(), this.painter.h());
        contentPainterNode.K0(this.painter);
        contentPainterNode.f12649y = this.f12646a;
        contentPainterNode.f12650z = this.f12647b;
        contentPainterNode.f12648A = this.c;
        contentPainterNode.B = this.d;
        if (!zA) {
            AbstractC0156f.n(contentPainterNode);
        }
        AbstractC0156f.m(contentPainterNode);
    }

    public final String toString() {
        return "ContentPainterElement(painter=" + this.painter + ", alignment=" + this.f12646a + ", contentScale=" + this.f12647b + ", alpha=" + this.c + ", colorFilter=" + this.d + ')';
    }
}
