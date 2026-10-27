package androidx.navigation;

/* JADX INFO: compiled from: NavOptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2729c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2730d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2731e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2732f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2733g;

    /* JADX INFO: compiled from: NavOptions.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2734a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2736c;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2735b = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2737d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2738e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2739f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2740g = -1;

        public o a() {
            return new o(this.f2734a, this.f2735b, this.f2736c, this.f2737d, this.f2738e, this.f2739f, this.f2740g);
        }

        public a b(int i7) {
            this.f2737d = i7;
            return this;
        }

        public a c(int i7) {
            this.f2738e = i7;
            return this;
        }

        public a d(boolean z6) {
            this.f2734a = z6;
            return this;
        }

        public a e(int i7) {
            this.f2739f = i7;
            return this;
        }

        public a f(int i7) {
            this.f2740g = i7;
            return this;
        }

        public a g(int i7, boolean z6) {
            this.f2735b = i7;
            this.f2736c = z6;
            return this;
        }
    }

    o(boolean z6, int i7, boolean z7, int i8, int i9, int i10, int i11) {
        this.f2727a = z6;
        this.f2728b = i7;
        this.f2729c = z7;
        this.f2730d = i8;
        this.f2731e = i9;
        this.f2732f = i10;
        this.f2733g = i11;
    }

    public int a() {
        return this.f2730d;
    }

    public int b() {
        return this.f2731e;
    }

    public int c() {
        return this.f2732f;
    }

    public int d() {
        return this.f2733g;
    }

    public int e() {
        return this.f2728b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        return this.f2727a == oVar.f2727a && this.f2728b == oVar.f2728b && this.f2729c == oVar.f2729c && this.f2730d == oVar.f2730d && this.f2731e == oVar.f2731e && this.f2732f == oVar.f2732f && this.f2733g == oVar.f2733g;
    }

    public boolean f() {
        return this.f2729c;
    }

    public boolean g() {
        return this.f2727a;
    }

    public int hashCode() {
        return ((((((((((((g() ? 1 : 0) * 31) + e()) * 31) + (f() ? 1 : 0)) * 31) + a()) * 31) + b()) * 31) + c()) * 31) + d();
    }
}
