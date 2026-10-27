package e3;

import android.text.Layout;

/* JADX INFO: renamed from: e3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1224g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f14412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14413b;
    public boolean c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f14414e;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f14420k;
    public String l;
    public Layout.Alignment o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Layout.Alignment f14423p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C1219b f14425r;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14415f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14416g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f14417h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14418i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f14419j = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14421m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14422n = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14424q = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f14426s = Float.MAX_VALUE;

    public final void a(C1224g c1224g) {
        int i7;
        Layout.Alignment alignment;
        Layout.Alignment alignment2;
        String str;
        if (c1224g != null) {
            if (!this.c && c1224g.c) {
                this.f14413b = c1224g.f14413b;
                this.c = true;
            }
            if (this.f14417h == -1) {
                this.f14417h = c1224g.f14417h;
            }
            if (this.f14418i == -1) {
                this.f14418i = c1224g.f14418i;
            }
            if (this.f14412a == null && (str = c1224g.f14412a) != null) {
                this.f14412a = str;
            }
            if (this.f14415f == -1) {
                this.f14415f = c1224g.f14415f;
            }
            if (this.f14416g == -1) {
                this.f14416g = c1224g.f14416g;
            }
            if (this.f14422n == -1) {
                this.f14422n = c1224g.f14422n;
            }
            if (this.o == null && (alignment2 = c1224g.o) != null) {
                this.o = alignment2;
            }
            if (this.f14423p == null && (alignment = c1224g.f14423p) != null) {
                this.f14423p = alignment;
            }
            if (this.f14424q == -1) {
                this.f14424q = c1224g.f14424q;
            }
            if (this.f14419j == -1) {
                this.f14419j = c1224g.f14419j;
                this.f14420k = c1224g.f14420k;
            }
            if (this.f14425r == null) {
                this.f14425r = c1224g.f14425r;
            }
            if (this.f14426s == Float.MAX_VALUE) {
                this.f14426s = c1224g.f14426s;
            }
            if (!this.f14414e && c1224g.f14414e) {
                this.d = c1224g.d;
                this.f14414e = true;
            }
            if (this.f14421m != -1 || (i7 = c1224g.f14421m) == -1) {
                return;
            }
            this.f14421m = i7;
        }
    }
}
