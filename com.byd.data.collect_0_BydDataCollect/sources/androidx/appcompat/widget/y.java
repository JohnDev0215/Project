package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f742a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f743b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f744c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f745d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f746e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f747f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f748g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f749h = false;

    y() {
    }

    public int a() {
        return this.f748g ? this.f742a : this.f743b;
    }

    public int b() {
        return this.f742a;
    }

    public int c() {
        return this.f743b;
    }

    public int d() {
        return this.f748g ? this.f743b : this.f742a;
    }

    public void e(int i2, int i3) {
        this.f749h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f746e = i2;
            this.f742a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f747f = i3;
            this.f743b = i3;
        }
    }

    public void f(boolean z) {
        if (z == this.f748g) {
            return;
        }
        this.f748g = z;
        if (!this.f749h) {
            this.f742a = this.f746e;
            this.f743b = this.f747f;
            return;
        }
        if (z) {
            int i2 = this.f745d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f746e;
            }
            this.f742a = i2;
            int i3 = this.f744c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f747f;
            }
            this.f743b = i3;
            return;
        }
        int i4 = this.f744c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f746e;
        }
        this.f742a = i4;
        int i5 = this.f745d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f747f;
        }
        this.f743b = i5;
    }

    public void g(int i2, int i3) {
        this.f744c = i2;
        this.f745d = i3;
        this.f749h = true;
        if (this.f748g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f742a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f743b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f742a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f743b = i3;
        }
    }
}
