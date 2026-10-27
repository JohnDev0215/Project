package r;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f21534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21535b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f21536e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21537f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21538g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f21539h;

    public final void a(int i7, int i10) {
        this.c = i7;
        this.d = i10;
        this.f21539h = true;
        if (this.f21538g) {
            if (i10 != Integer.MIN_VALUE) {
                this.f21534a = i10;
            }
            if (i7 != Integer.MIN_VALUE) {
                this.f21535b = i7;
                return;
            }
            return;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.f21534a = i7;
        }
        if (i10 != Integer.MIN_VALUE) {
            this.f21535b = i10;
        }
    }
}
