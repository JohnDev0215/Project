package b.b.q;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1840a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1841b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1842c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1843d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1844e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1845f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1846g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1847h = false;

    public int a() {
        return this.f1846g ? this.f1840a : this.f1841b;
    }

    public int b() {
        return this.f1840a;
    }

    public int c() {
        return this.f1841b;
    }

    public int d() {
        return this.f1846g ? this.f1841b : this.f1840a;
    }

    public void e(int i2, int i3) {
        this.f1847h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f1844e = i2;
            this.f1840a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1845f = i3;
            this.f1841b = i3;
        }
    }

    public void f(boolean z) {
        if (z == this.f1846g) {
            return;
        }
        this.f1846g = z;
        if (!this.f1847h) {
            this.f1840a = this.f1844e;
            this.f1841b = this.f1845f;
            return;
        }
        if (z) {
            int i2 = this.f1843d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f1844e;
            }
            this.f1840a = i2;
            int i3 = this.f1842c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f1845f;
            }
            this.f1841b = i3;
            return;
        }
        int i4 = this.f1842c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f1844e;
        }
        this.f1840a = i4;
        int i5 = this.f1843d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f1845f;
        }
        this.f1841b = i5;
    }

    public void g(int i2, int i3) {
        this.f1842c = i2;
        this.f1843d = i3;
        this.f1847h = true;
        if (this.f1846g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f1840a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f1841b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1840a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1841b = i3;
        }
    }
}
