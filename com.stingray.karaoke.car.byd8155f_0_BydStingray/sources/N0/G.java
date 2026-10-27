package N0;

import S0.InterfaceC0702n;
import h2.AbstractC1347d;
import java.util.List;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f4340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f4341b;
    public final List c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4342e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4343f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a1.b f4344g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a1.k f4345h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InterfaceC0702n f4346i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f4347j;

    public G(C0411f c0411f, L l, List list, int i7, boolean z6, int i10, a1.b bVar, a1.k kVar, InterfaceC0702n interfaceC0702n, long j3) {
        this.f4340a = c0411f;
        this.f4341b = l;
        this.c = list;
        this.d = i7;
        this.f4342e = z6;
        this.f4343f = i10;
        this.f4344g = bVar;
        this.f4345h = kVar;
        this.f4346i = interfaceC0702n;
        this.f4347j = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof G)) {
            return false;
        }
        G g7 = (G) obj;
        return A7.m.a(this.f4340a, g7.f4340a) && A7.m.a(this.f4341b, g7.f4341b) && A7.m.a(this.c, g7.c) && this.d == g7.d && this.f4342e == g7.f4342e && C7.a.F(this.f4343f, g7.f4343f) && A7.m.a(this.f4344g, g7.f4344g) && this.f4345h == g7.f4345h && A7.m.a(this.f4346i, g7.f4346i) && a1.a.b(this.f4347j, g7.f4347j);
    }

    public final int hashCode() {
        return Long.hashCode(this.f4347j) + ((this.f4346i.hashCode() + ((this.f4345h.hashCode() + ((this.f4344g.hashCode() + AbstractC2641j.b(this.f4343f, AbstractC1347d.d((((this.c.hashCode() + M5.b.b(this.f4340a.hashCode() * 31, 31, this.f4341b)) * 31) + this.d) * 31, 31, this.f4342e), 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextLayoutInput(text=");
        sb.append((Object) this.f4340a);
        sb.append(", style=");
        sb.append(this.f4341b);
        sb.append(", placeholders=");
        sb.append(this.c);
        sb.append(", maxLines=");
        sb.append(this.d);
        sb.append(", softWrap=");
        sb.append(this.f4342e);
        sb.append(", overflow=");
        int i7 = this.f4343f;
        sb.append((Object) (C7.a.F(i7, 1) ? "Clip" : C7.a.F(i7, 2) ? "Ellipsis" : C7.a.F(i7, 3) ? "Visible" : "Invalid"));
        sb.append(", density=");
        sb.append(this.f4344g);
        sb.append(", layoutDirection=");
        sb.append(this.f4345h);
        sb.append(", fontFamilyResolver=");
        sb.append(this.f4346i);
        sb.append(", constraints=");
        sb.append((Object) a1.a.l(this.f4347j));
        sb.append(')');
        return sb.toString();
    }
}
