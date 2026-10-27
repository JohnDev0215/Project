package z2;

import e2.q0;

/* JADX INFO: renamed from: z2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC2762h implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f24317m;

    public /* synthetic */ RunnableC2762h(i iVar, u uVar, int i7) {
        this.l = i7;
        this.f24317m = uVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f24317m.getClass();
                break;
            case 1:
                this.f24317m.d();
                break;
            default:
                this.f24317m.e();
                break;
        }
    }

    public /* synthetic */ RunnableC2762h(i iVar, u uVar, q0 q0Var) {
        this.l = 0;
        this.f24317m = uVar;
    }
}
