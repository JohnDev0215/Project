package androidx.compose.foundation.text.modifiers;

import A7.m;
import C7.a;
import E0.V;
import M5.b;
import N.h;
import N0.C0411f;
import N0.L;
import S0.InterfaceC0702n;
import g0.n;
import h2.AbstractC1347d;
import java.util.List;
import kotlin.Metadata;
import n0.InterfaceC1892u;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringElement;", "LE0/V;", "LN/h;", "Ln0/u;", "color", "Ln0/u;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextAnnotatedStringElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f11509a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f11510b;
    public final InterfaceC0702n c;
    private final InterfaceC1892u color;
    public final InterfaceC2782k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f11511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f11512f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f11513g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f11514h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f11515i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final InterfaceC2782k f11516j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final InterfaceC2782k f11517k;

    public TextAnnotatedStringElement(C0411f c0411f, L l, InterfaceC0702n interfaceC0702n, InterfaceC2782k interfaceC2782k, int i7, boolean z6, int i10, int i11, List list, InterfaceC2782k interfaceC2782k2, InterfaceC1892u interfaceC1892u, InterfaceC2782k interfaceC2782k3) {
        this.f11509a = c0411f;
        this.f11510b = l;
        this.c = interfaceC0702n;
        this.d = interfaceC2782k;
        this.f11511e = i7;
        this.f11512f = z6;
        this.f11513g = i10;
        this.f11514h = i11;
        this.f11515i = list;
        this.f11516j = interfaceC2782k2;
        this.color = interfaceC1892u;
        this.f11517k = interfaceC2782k3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextAnnotatedStringElement)) {
            return false;
        }
        TextAnnotatedStringElement textAnnotatedStringElement = (TextAnnotatedStringElement) obj;
        return m.a(this.color, textAnnotatedStringElement.color) && m.a(this.f11509a, textAnnotatedStringElement.f11509a) && m.a(this.f11510b, textAnnotatedStringElement.f11510b) && m.a(this.f11515i, textAnnotatedStringElement.f11515i) && m.a(this.c, textAnnotatedStringElement.c) && this.d == textAnnotatedStringElement.d && this.f11517k == textAnnotatedStringElement.f11517k && a.F(this.f11511e, textAnnotatedStringElement.f11511e) && this.f11512f == textAnnotatedStringElement.f11512f && this.f11513g == textAnnotatedStringElement.f11513g && this.f11514h == textAnnotatedStringElement.f11514h && this.f11516j == textAnnotatedStringElement.f11516j && m.a(null, null);
    }

    public final int hashCode() {
        int iHashCode = (this.c.hashCode() + b.b(this.f11509a.hashCode() * 31, 31, this.f11510b)) * 31;
        InterfaceC2782k interfaceC2782k = this.d;
        int iD = (((AbstractC1347d.d(AbstractC2641j.b(this.f11511e, (iHashCode + (interfaceC2782k != null ? interfaceC2782k.hashCode() : 0)) * 31, 31), 31, this.f11512f) + this.f11513g) * 31) + this.f11514h) * 31;
        List list = this.f11515i;
        int iHashCode2 = (iD + (list != null ? list.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k2 = this.f11516j;
        int iHashCode3 = (iHashCode2 + (interfaceC2782k2 != null ? interfaceC2782k2.hashCode() : 0)) * 961;
        InterfaceC1892u interfaceC1892u = this.color;
        int iHashCode4 = (iHashCode3 + (interfaceC1892u != null ? interfaceC1892u.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k3 = this.f11517k;
        return iHashCode4 + (interfaceC2782k3 != null ? interfaceC2782k3.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        InterfaceC1892u interfaceC1892u = this.color;
        List list = this.f11515i;
        InterfaceC2782k interfaceC2782k = this.f11517k;
        C0411f c0411f = this.f11509a;
        L l = this.f11510b;
        InterfaceC0702n interfaceC0702n = this.c;
        InterfaceC2782k interfaceC2782k2 = this.d;
        int i7 = this.f11511e;
        boolean z6 = this.f11512f;
        int i10 = this.f11513g;
        int i11 = this.f11514h;
        InterfaceC2782k interfaceC2782k3 = this.f11516j;
        h hVar = new h();
        hVar.f4269y = c0411f;
        hVar.f4270z = l;
        hVar.f4256A = interfaceC0702n;
        hVar.B = interfaceC2782k2;
        hVar.f4257C = i7;
        hVar.f4258D = z6;
        hVar.f4259E = i10;
        hVar.f4260F = i11;
        hVar.f4261G = list;
        hVar.f4262H = interfaceC2782k3;
        hVar.f4263I = interfaceC1892u;
        hVar.f4264J = interfaceC2782k;
        return hVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    @Override // E0.V
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(g0.n r13) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement.l(g0.n):void");
    }
}
