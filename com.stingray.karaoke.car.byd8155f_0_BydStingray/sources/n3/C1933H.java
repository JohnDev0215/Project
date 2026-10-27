package n3;

/* JADX INFO: renamed from: n3.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1933H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f19827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f19828b;
    public final int c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f19829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f19830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f19831g;

    public C1933H(boolean z6, boolean z10, int i7, boolean z11, boolean z12, int i10, int i11) {
        this.f19827a = z6;
        this.f19828b = z10;
        this.c = i7;
        this.d = z11;
        this.f19829e = z12;
        this.f19830f = i10;
        this.f19831g = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C1933H)) {
            return false;
        }
        C1933H c1933h = (C1933H) obj;
        return this.f19827a == c1933h.f19827a && this.f19828b == c1933h.f19828b && this.c == c1933h.c && A7.m.a(null, null) && A7.m.a(null, null) && A7.m.a(null, null) && this.d == c1933h.d && this.f19829e == c1933h.f19829e && this.f19830f == c1933h.f19830f && this.f19831g == c1933h.f19831g;
    }

    public final int hashCode() {
        return ((((((((((((((((this.f19827a ? 1 : 0) * 31) + (this.f19828b ? 1 : 0)) * 31) + this.c) * 923521) + (this.d ? 1 : 0)) * 31) + (this.f19829e ? 1 : 0)) * 31) + this.f19830f) * 31) + this.f19831g) * 31) - 1) * 31) - 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(C1933H.class.getSimpleName());
        sb.append("(");
        if (this.f19827a) {
            sb.append("launchSingleTop ");
        }
        if (this.f19828b) {
            sb.append("restoreState ");
        }
        int i7 = this.f19831g;
        int i10 = this.f19830f;
        if (i10 != -1 || i7 != -1) {
            sb.append("anim(enterAnim=0x");
            sb.append(Integer.toHexString(i10));
            sb.append(" exitAnim=0x");
            sb.append(Integer.toHexString(i7));
            sb.append(" popEnterAnim=0x");
            sb.append(Integer.toHexString(-1));
            sb.append(" popExitAnim=0x");
            sb.append(Integer.toHexString(-1));
            sb.append(")");
        }
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }
}
