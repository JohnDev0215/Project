package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0 implements Runnable {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I0 f17741m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1679y0 f17742n;
    public final /* synthetic */ Runnable o;

    public /* synthetic */ C0(I0 i02, Runnable runnable, C1679y0 c1679y0) {
        this.f17741m = i02;
        this.o = runnable;
        this.f17742n = c1679y0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                I0 i02 = this.f17741m;
                i02.getClass();
                this.o.run();
                i02.f17800g.f18014e.Q(this.f17742n);
                break;
            default:
                I0 i03 = this.f17741m;
                i03.f17815x = this.f17742n;
                this.o.run();
                i03.f17815x = null;
                break;
        }
    }

    public /* synthetic */ C0(I0 i02, C1679y0 c1679y0, Runnable runnable) {
        this.f17741m = i02;
        this.f17742n = c1679y0;
        this.o = runnable;
    }
}
