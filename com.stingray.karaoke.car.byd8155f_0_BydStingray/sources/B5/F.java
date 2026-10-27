package B5;

/* JADX INFO: loaded from: classes.dex */
public final class F implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I f653m;

    public /* synthetic */ F(I i7, int i10) {
        this.l = i10;
        this.f653m = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                H h10 = this.f653m.f662f;
                h10.sendMessage(h10.obtainMessage(1));
                return;
            default:
                synchronized (this.f653m.l) {
                    this.f653m.c();
                    break;
                }
                return;
        }
    }
}
