package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f666a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f667b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f668c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f669d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f670e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    x() {
    }

    public int a() {
        return this.g ? this.f666a : this.f667b;
    }

    public int b() {
        return this.f666a;
    }

    public int c() {
        return this.f667b;
    }

    public int d() {
        return this.g ? this.f667b : this.f666a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.f670e = i;
            this.f666a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f667b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f666a = this.f670e;
            this.f667b = this.f;
            return;
        }
        if (z) {
            int i = this.f669d;
            if (i == Integer.MIN_VALUE) {
                i = this.f670e;
            }
            this.f666a = i;
            int i2 = this.f668c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f667b = i2;
            return;
        }
        int i3 = this.f668c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.f670e;
        }
        this.f666a = i3;
        int i4 = this.f669d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f667b = i4;
    }

    public void g(int i, int i2) {
        this.f668c = i;
        this.f669d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f666a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f667b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f666a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f667b = i2;
        }
    }
}
