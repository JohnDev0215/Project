package androidx.appcompat.widget;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f286a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f287b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f288c = Integer.MIN_VALUE;
    private int d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    L() {
    }

    public int a() {
        return this.g ? this.f286a : this.f287b;
    }

    public int b() {
        return this.g ? this.f287b : this.f286a;
    }

    public void c(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f286a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f287b = i2;
        }
    }

    public void d(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f286a = this.e;
            this.f287b = this.f;
            return;
        }
        if (z) {
            int i = this.d;
            if (i == Integer.MIN_VALUE) {
                i = this.e;
            }
            this.f286a = i;
            int i2 = this.f288c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f;
            }
            this.f287b = i2;
            return;
        }
        int i3 = this.f288c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.e;
        }
        this.f286a = i3;
        int i4 = this.d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f;
        }
        this.f287b = i4;
    }

    public void e(int i, int i2) {
        this.f288c = i;
        this.d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f286a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f287b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f286a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f287b = i2;
        }
    }
}
