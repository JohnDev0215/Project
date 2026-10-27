package a.b.p;

/* JADX INFO: loaded from: classes.dex */
public class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f329a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f330b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f331c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f332d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f333e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f334f = 0;
    public boolean g = false;
    public boolean h = false;

    public void a(int i, int i2) {
        this.f331c = i;
        this.f332d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f329a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f330b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f329a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f330b = i2;
        }
    }
}
