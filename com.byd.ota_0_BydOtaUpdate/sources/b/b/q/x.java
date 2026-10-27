package b.b.q;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1113a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1114b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1115c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1116d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1117e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1118f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1119g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1120h = false;

    public int a() {
        return this.f1119g ? this.f1113a : this.f1114b;
    }

    public int b() {
        return this.f1113a;
    }

    public int c() {
        return this.f1114b;
    }

    public int d() {
        return this.f1119g ? this.f1114b : this.f1113a;
    }

    public void e(int i2, int i3) {
        this.f1120h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.f1117e = i2;
            this.f1113a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1118f = i3;
            this.f1114b = i3;
        }
    }

    public void f(boolean z) {
        if (z == this.f1119g) {
            return;
        }
        this.f1119g = z;
        if (!this.f1120h) {
            this.f1113a = this.f1117e;
            this.f1114b = this.f1118f;
            return;
        }
        if (z) {
            int i2 = this.f1116d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f1117e;
            }
            this.f1113a = i2;
            int i3 = this.f1115c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f1118f;
            }
            this.f1114b = i3;
            return;
        }
        int i4 = this.f1115c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f1117e;
        }
        this.f1113a = i4;
        int i5 = this.f1116d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f1118f;
        }
        this.f1114b = i5;
    }

    public void g(int i2, int i3) {
        this.f1115c = i2;
        this.f1116d = i3;
        this.f1120h = true;
        if (this.f1119g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f1113a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f1114b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1113a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1114b = i3;
        }
    }
}
