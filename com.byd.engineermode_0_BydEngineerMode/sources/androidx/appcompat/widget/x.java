package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f493a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f494b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f495c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f496d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    x() {
    }

    public int a() {
        return this.g ? this.f493a : this.f494b;
    }

    public int b() {
        return this.f493a;
    }

    public int c() {
        return this.f494b;
    }

    public int d() {
        return this.g ? this.f494b : this.f493a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f493a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f494b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f493a = this.e;
            this.f494b = this.f;
            return;
        }
        if (z) {
            int i = this.f496d;
            if (i == Integer.MIN_VALUE) {
                i = this.e;
            }
            this.f493a = i;
            int i2 = this.f495c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f494b = i2;
            return;
        }
        int i3 = this.f495c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.e;
        }
        this.f493a = i3;
        int i4 = this.f496d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f494b = i4;
    }

    public void g(int i, int i2) {
        this.f495c = i;
        this.f496d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f493a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f494b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f493a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f494b = i2;
        }
    }
}
