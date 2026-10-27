package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f700a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f701b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f702c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f703d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f704e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f705f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f706g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f707h = false;

    x() {
    }

    public int a() {
        return this.f706g ? this.f700a : this.f701b;
    }

    public int b() {
        return this.f700a;
    }

    public int c() {
        return this.f701b;
    }

    public int d() {
        return this.f706g ? this.f701b : this.f700a;
    }

    public void e(int i, int i2) {
        this.f707h = false;
        if (i != Integer.MIN_VALUE) {
            this.f704e = i;
            this.f700a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f705f = i2;
            this.f701b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.f706g) {
            return;
        }
        this.f706g = z;
        if (!this.f707h) {
            this.f700a = this.f704e;
            this.f701b = this.f705f;
            return;
        }
        if (z) {
            int i = this.f703d;
            if (i == Integer.MIN_VALUE) {
                i = this.f704e;
            }
            this.f700a = i;
            int i2 = this.f702c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f705f;
            }
            this.f701b = i2;
            return;
        }
        int i3 = this.f702c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.f704e;
        }
        this.f700a = i3;
        int i4 = this.f703d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f705f;
        }
        this.f701b = i4;
    }

    public void g(int i, int i2) {
        this.f702c = i;
        this.f703d = i2;
        this.f707h = true;
        if (this.f706g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f700a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f701b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f700a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f701b = i2;
        }
    }
}
