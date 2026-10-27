package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f555a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f556b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f557c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f558d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f559e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    x() {
    }

    public int a() {
        return this.g ? this.f555a : this.f556b;
    }

    public int b() {
        return this.f555a;
    }

    public int c() {
        return this.f556b;
    }

    public int d() {
        return this.g ? this.f556b : this.f555a;
    }

    public void e(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.f559e = i;
            this.f555a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f556b = i2;
        }
    }

    public void f(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f555a = this.f559e;
            this.f556b = this.f;
            return;
        }
        if (z) {
            int i = this.f558d;
            if (i == Integer.MIN_VALUE) {
                i = this.f559e;
            }
            this.f555a = i;
            int i2 = this.f557c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f556b = i2;
            return;
        }
        int i3 = this.f557c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.f559e;
        }
        this.f555a = i3;
        int i4 = this.f558d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f556b = i4;
    }

    public void g(int i, int i2) {
        this.f557c = i;
        this.f558d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f555a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f556b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f555a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f556b = i2;
        }
    }
}
