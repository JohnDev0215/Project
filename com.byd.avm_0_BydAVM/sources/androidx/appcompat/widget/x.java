package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f503a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f504b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f505c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f506d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    x() {
    }

    public int a() {
        return this.g ? this.f503a : this.f504b;
    }

    public int b() {
        return this.f503a;
    }

    public int c() {
        return this.f504b;
    }

    public int d() {
        return this.g ? this.f504b : this.f503a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f503a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f504b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f503a = this.e;
            this.f504b = this.f;
            return;
        }
        if (z) {
            int i = this.f506d;
            if (i == Integer.MIN_VALUE) {
                i = this.e;
            }
            this.f503a = i;
            int i2 = this.f505c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f504b = i2;
            return;
        }
        int i3 = this.f505c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.e;
        }
        this.f503a = i3;
        int i4 = this.f506d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f504b = i4;
    }

    public void g(int i, int i2) {
        this.f505c = i;
        this.f506d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f503a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f504b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f503a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f504b = i2;
        }
    }
}
