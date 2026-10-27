package androidx.compose.foundation.text.modifiers;

import A7.m;
import C7.a;
import E0.V;
import M5.b;
import N.k;
import N0.L;
import S0.InterfaceC0702n;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import n0.InterfaceC1892u;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleElement;", "LE0/V;", "LN/k;", "Ln0/u;", "color", "Ln0/u;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextStringSimpleElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f11518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f11519b;
    public final InterfaceC0702n c;
    private final InterfaceC1892u color;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11520e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f11521f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f11522g;

    public TextStringSimpleElement(String str, L l, InterfaceC0702n interfaceC0702n, int i7, boolean z6, int i10, int i11, InterfaceC1892u interfaceC1892u) {
        this.f11518a = str;
        this.f11519b = l;
        this.c = interfaceC0702n;
        this.d = i7;
        this.f11520e = z6;
        this.f11521f = i10;
        this.f11522g = i11;
        this.color = interfaceC1892u;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStringSimpleElement)) {
            return false;
        }
        TextStringSimpleElement textStringSimpleElement = (TextStringSimpleElement) obj;
        return m.a(this.color, textStringSimpleElement.color) && m.a(this.f11518a, textStringSimpleElement.f11518a) && m.a(this.f11519b, textStringSimpleElement.f11519b) && m.a(this.c, textStringSimpleElement.c) && a.F(this.d, textStringSimpleElement.d) && this.f11520e == textStringSimpleElement.f11520e && this.f11521f == textStringSimpleElement.f11521f && this.f11522g == textStringSimpleElement.f11522g;
    }

    public final int hashCode() {
        int iD = (((AbstractC1347d.d(AbstractC2641j.b(this.d, (this.c.hashCode() + b.b(this.f11518a.hashCode() * 31, 31, this.f11519b)) * 31, 31), 31, this.f11520e) + this.f11521f) * 31) + this.f11522g) * 31;
        InterfaceC1892u interfaceC1892u = this.color;
        return iD + (interfaceC1892u != null ? interfaceC1892u.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        InterfaceC1892u interfaceC1892u = this.color;
        k kVar = new k();
        kVar.f4284y = this.f11518a;
        kVar.f4285z = this.f11519b;
        kVar.f4275A = this.c;
        kVar.B = this.d;
        kVar.f4276C = this.f11520e;
        kVar.f4277D = this.f11521f;
        kVar.f4278E = this.f11522g;
        kVar.f4279F = interfaceC1892u;
        return kVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    @Override // E0.V
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(g0.n r13) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextStringSimpleElement.l(g0.n):void");
    }
}
