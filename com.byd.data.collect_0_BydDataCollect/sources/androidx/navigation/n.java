package androidx.navigation;

/* JADX INFO: compiled from: NavOptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1719a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1720b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1721c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1722d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1723e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1724f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1725g;

    /* JADX INFO: compiled from: NavOptions.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1726a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1728c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1727b = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1729d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1730e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1731f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f1732g = -1;

        public n a() {
            return new n(this.f1726a, this.f1727b, this.f1728c, this.f1729d, this.f1730e, this.f1731f, this.f1732g);
        }

        public a b(int i2) {
            this.f1729d = i2;
            return this;
        }

        public a c(int i2) {
            this.f1730e = i2;
            return this;
        }

        public a d(boolean z) {
            this.f1726a = z;
            return this;
        }

        public a e(int i2) {
            this.f1731f = i2;
            return this;
        }

        public a f(int i2) {
            this.f1732g = i2;
            return this;
        }

        public a g(int i2, boolean z) {
            this.f1727b = i2;
            this.f1728c = z;
            return this;
        }
    }

    n(boolean z, int i2, boolean z2, int i3, int i4, int i5, int i6) {
        this.f1719a = z;
        this.f1720b = i2;
        this.f1721c = z2;
        this.f1722d = i3;
        this.f1723e = i4;
        this.f1724f = i5;
        this.f1725g = i6;
    }

    public int a() {
        return this.f1722d;
    }

    public int b() {
        return this.f1723e;
    }

    public int c() {
        return this.f1724f;
    }

    public int d() {
        return this.f1725g;
    }

    public int e() {
        return this.f1720b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        return this.f1719a == nVar.f1719a && this.f1720b == nVar.f1720b && this.f1721c == nVar.f1721c && this.f1722d == nVar.f1722d && this.f1723e == nVar.f1723e && this.f1724f == nVar.f1724f && this.f1725g == nVar.f1725g;
    }

    public boolean f() {
        return this.f1721c;
    }

    public boolean g() {
        return this.f1719a;
    }

    public int hashCode() {
        return ((((((((((((g() ? 1 : 0) * 31) + e()) * 31) + (f() ? 1 : 0)) * 31) + a()) * 31) + b()) * 31) + c()) * 31) + d();
    }
}
