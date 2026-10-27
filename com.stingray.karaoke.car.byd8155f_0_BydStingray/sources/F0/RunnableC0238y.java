package F0;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: F0.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0238y implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f2296m;

    public /* synthetic */ RunnableC0238y(InterfaceC2772a interfaceC2772a, int i7) {
        this.l = i7;
        this.f2296m = interfaceC2772a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f2296m.a();
                break;
            case 1:
                this.f2296m.a();
                break;
            default:
                this.f2296m.a();
                break;
        }
    }
}
