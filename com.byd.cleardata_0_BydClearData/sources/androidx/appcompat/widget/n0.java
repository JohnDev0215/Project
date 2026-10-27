package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f983a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f984b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f985c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f986d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    n0() {
    }

    public int a() {
        return this.g ? this.f983a : this.f984b;
    }

    public int b() {
        return this.f983a;
    }

    public int c() {
        return this.f984b;
    }

    public int d() {
        return this.g ? this.f984b : this.f983a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f983a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f984b = i2;
        }
    }

    public void f(boolean z) {
        int i;
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (this.h) {
            if (z) {
                int i2 = this.f986d;
                if (i2 == Integer.MIN_VALUE) {
                    i2 = this.e;
                }
                this.f983a = i2;
                i = this.f985c;
                if (i == Integer.MIN_VALUE) {
                }
            } else {
                int i3 = this.f985c;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = this.e;
                }
                this.f983a = i3;
                i = this.f986d;
                if (i == Integer.MIN_VALUE) {
                }
            }
            this.f984b = i;
        }
        this.f983a = this.e;
        i = this.f;
        this.f984b = i;
    }

    public void g(int i, int i2) {
        this.f985c = i;
        this.f986d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f983a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f984b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f983a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f984b = i2;
        }
    }
}
