package d7;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f13536m;

    public /* synthetic */ m(o oVar, int i7) {
        this.l = i7;
        this.f13536m = oVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                o oVar = this.f13536m;
                int i7 = oVar.f13553w;
                if (i7 == 3 || i7 == 0) {
                    oVar.f13553w = 1;
                    oVar.b1();
                }
                break;
            default:
                o oVar2 = this.f13536m;
                int i10 = oVar2.f13553w;
                if (i10 == 1 || i10 == 2) {
                    oVar2.a1();
                    oVar2.f13553w = 3;
                    oVar2.N0("close", new Object[0]);
                }
                break;
        }
    }
}
