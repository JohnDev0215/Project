package o;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4399a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4400b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4401c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4402d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4403e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4404f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4405g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4406h = false;

    public int a() {
        return this.f4405g ? this.f4399a : this.f4400b;
    }

    public int b() {
        return this.f4399a;
    }

    public int c() {
        return this.f4400b;
    }

    public int d() {
        return this.f4405g ? this.f4400b : this.f4399a;
    }

    public void e(int i3, int i4) {
        this.f4406h = false;
        if (i3 != Integer.MIN_VALUE) {
            this.f4403e = i3;
            this.f4399a = i3;
        }
        if (i4 != Integer.MIN_VALUE) {
            this.f4404f = i4;
            this.f4400b = i4;
        }
    }

    public void f(boolean z3) {
        if (z3 == this.f4405g) {
            return;
        }
        this.f4405g = z3;
        if (!this.f4406h) {
            this.f4399a = this.f4403e;
            this.f4400b = this.f4404f;
            return;
        }
        if (z3) {
            int i3 = this.f4402d;
            if (i3 == Integer.MIN_VALUE) {
                i3 = this.f4403e;
            }
            this.f4399a = i3;
            int i4 = this.f4401c;
            if (i4 == Integer.MIN_VALUE) {
                i4 = this.f4404f;
            }
            this.f4400b = i4;
            return;
        }
        int i5 = this.f4401c;
        if (i5 == Integer.MIN_VALUE) {
            i5 = this.f4403e;
        }
        this.f4399a = i5;
        int i6 = this.f4402d;
        if (i6 == Integer.MIN_VALUE) {
            i6 = this.f4404f;
        }
        this.f4400b = i6;
    }

    public void g(int i3, int i4) {
        this.f4401c = i3;
        this.f4402d = i4;
        this.f4406h = true;
        if (this.f4405g) {
            if (i4 != Integer.MIN_VALUE) {
                this.f4399a = i4;
            }
            if (i3 != Integer.MIN_VALUE) {
                this.f4400b = i3;
                return;
            }
            return;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f4399a = i3;
        }
        if (i4 != Integer.MIN_VALUE) {
            this.f4400b = i4;
        }
    }
}
