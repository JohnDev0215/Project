package r;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7658a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7659b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7660c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7661d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7662e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7663f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f7664g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7665h = false;

    public int a() {
        return this.f7664g ? this.f7658a : this.f7659b;
    }

    public int b() {
        return this.f7658a;
    }

    public int c() {
        return this.f7659b;
    }

    public int d() {
        return this.f7664g ? this.f7659b : this.f7658a;
    }

    public void e(int i8, int i9) {
        this.f7665h = false;
        if (i8 != Integer.MIN_VALUE) {
            this.f7662e = i8;
            this.f7658a = i8;
        }
        if (i9 != Integer.MIN_VALUE) {
            this.f7663f = i9;
            this.f7659b = i9;
        }
    }

    public void f(boolean z7) {
        if (z7 == this.f7664g) {
            return;
        }
        this.f7664g = z7;
        if (!this.f7665h) {
            this.f7658a = this.f7662e;
            this.f7659b = this.f7663f;
            return;
        }
        if (z7) {
            int i8 = this.f7661d;
            if (i8 == Integer.MIN_VALUE) {
                i8 = this.f7662e;
            }
            this.f7658a = i8;
            int i9 = this.f7660c;
            if (i9 == Integer.MIN_VALUE) {
                i9 = this.f7663f;
            }
            this.f7659b = i9;
            return;
        }
        int i10 = this.f7660c;
        if (i10 == Integer.MIN_VALUE) {
            i10 = this.f7662e;
        }
        this.f7658a = i10;
        int i11 = this.f7661d;
        if (i11 == Integer.MIN_VALUE) {
            i11 = this.f7663f;
        }
        this.f7659b = i11;
    }

    public void g(int i8, int i9) {
        this.f7660c = i8;
        this.f7661d = i9;
        this.f7665h = true;
        if (this.f7664g) {
            if (i9 != Integer.MIN_VALUE) {
                this.f7658a = i9;
            }
            if (i8 != Integer.MIN_VALUE) {
                this.f7659b = i8;
                return;
            }
            return;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.f7658a = i8;
        }
        if (i9 != Integer.MIN_VALUE) {
            this.f7659b = i9;
        }
    }
}
