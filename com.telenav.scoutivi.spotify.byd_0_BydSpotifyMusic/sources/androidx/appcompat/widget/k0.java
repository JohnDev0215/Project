package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1102a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1103b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1104c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1105d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1106e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1107f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1108g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1109h = false;

    k0() {
    }

    public int a() {
        return this.f1108g ? this.f1102a : this.f1103b;
    }

    public int b() {
        return this.f1102a;
    }

    public int c() {
        return this.f1103b;
    }

    public int d() {
        return this.f1108g ? this.f1103b : this.f1102a;
    }

    public void e(int i7, int i8) {
        this.f1109h = false;
        if (i7 != Integer.MIN_VALUE) {
            this.f1106e = i7;
            this.f1102a = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.f1107f = i8;
            this.f1103b = i8;
        }
    }

    public void f(boolean z6) {
        if (z6 == this.f1108g) {
            return;
        }
        this.f1108g = z6;
        if (!this.f1109h) {
            this.f1102a = this.f1106e;
            this.f1103b = this.f1107f;
            return;
        }
        if (z6) {
            int i7 = this.f1105d;
            if (i7 == Integer.MIN_VALUE) {
                i7 = this.f1106e;
            }
            this.f1102a = i7;
            int i8 = this.f1104c;
            if (i8 == Integer.MIN_VALUE) {
                i8 = this.f1107f;
            }
            this.f1103b = i8;
            return;
        }
        int i9 = this.f1104c;
        if (i9 == Integer.MIN_VALUE) {
            i9 = this.f1106e;
        }
        this.f1102a = i9;
        int i10 = this.f1105d;
        if (i10 == Integer.MIN_VALUE) {
            i10 = this.f1107f;
        }
        this.f1103b = i10;
    }

    public void g(int i7, int i8) {
        this.f1104c = i7;
        this.f1105d = i8;
        this.f1109h = true;
        if (this.f1108g) {
            if (i8 != Integer.MIN_VALUE) {
                this.f1102a = i8;
            }
            if (i7 != Integer.MIN_VALUE) {
                this.f1103b = i7;
                return;
            }
            return;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.f1102a = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.f1103b = i8;
        }
    }
}
