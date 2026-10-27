package x6;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23788a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23789b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f23790e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f23791f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f23792g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f23793h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23794i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f23795j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f23796k;
    public final int l;

    public b(int i7, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20) {
        this.f23788a = i7;
        this.f23789b = i10;
        this.c = i11;
        this.d = i12;
        this.f23790e = i13;
        this.f23791f = i14;
        this.f23792g = i15;
        this.f23793h = i16;
        this.f23794i = i17;
        this.f23795j = i18;
        this.f23796k = i19;
        this.l = i20;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f23788a == bVar.f23788a && this.f23789b == bVar.f23789b && this.c == bVar.c && this.d == bVar.d && this.f23790e == bVar.f23790e && this.f23791f == bVar.f23791f && this.f23792g == bVar.f23792g && this.f23793h == bVar.f23793h && this.f23794i == bVar.f23794i && this.f23795j == bVar.f23795j && this.f23796k == bVar.f23796k && this.l == bVar.l;
    }

    public final int hashCode() {
        return Integer.hashCode(this.l) + AbstractC2641j.b(this.f23796k, AbstractC2641j.b(this.f23795j, AbstractC2641j.b(this.f23794i, AbstractC2641j.b(this.f23793h, AbstractC2641j.b(this.f23792g, AbstractC2641j.b(this.f23791f, AbstractC2641j.b(this.f23790e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC2641j.b(this.f23789b, Integer.hashCode(this.f23788a) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LyricsColorConfig(soloPrimary=");
        sb.append(this.f23788a);
        sb.append(", soloSecondary=");
        sb.append(this.f23789b);
        sb.append(", duet1Primary=");
        sb.append(this.c);
        sb.append(", duet1Secondary=");
        sb.append(this.d);
        sb.append(", duet2Primary=");
        sb.append(this.f23790e);
        sb.append(", duet2Secondary=");
        sb.append(this.f23791f);
        sb.append(", duet3Primary=");
        sb.append(this.f23792g);
        sb.append(", duet3Secondary=");
        sb.append(this.f23793h);
        sb.append(", duet4Primary=");
        sb.append(this.f23794i);
        sb.append(", duet4Secondary=");
        sb.append(this.f23795j);
        sb.append(", singerBackground=");
        sb.append(this.f23796k);
        sb.append(", credits=");
        return M5.b.h(sb, this.l, ")");
    }
}
