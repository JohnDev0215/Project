package b.b.f;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f835a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f836b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f837c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f838d = Integer.MIN_VALUE;
    public int e = 0;
    public int f = 0;
    public boolean g = false;
    public boolean h = false;

    public int a() {
        return this.g ? this.f835a : this.f836b;
    }

    public int b() {
        return this.f835a;
    }

    public int c() {
        return this.f836b;
    }

    public int d() {
        return this.g ? this.f836b : this.f835a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f835a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f836b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f835a = this.e;
            this.f836b = this.f;
            return;
        }
        if (z) {
            int i = this.f838d;
            if (i == Integer.MIN_VALUE) {
                i = this.e;
            }
            this.f835a = i;
            int i2 = this.f837c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f836b = i2;
            return;
        }
        int i3 = this.f837c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.e;
        }
        this.f835a = i3;
        int i4 = this.f838d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f836b = i4;
    }

    public void g(int i, int i2) {
        this.f837c = i;
        this.f838d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f835a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f836b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f835a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f836b = i2;
        }
    }
}
