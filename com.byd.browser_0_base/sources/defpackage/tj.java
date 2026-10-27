package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tj implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ vj f;

    public /* synthetic */ tj(vj vjVar, int i) {
        this.e = i;
        this.f = vjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        vj vjVar = this.f;
        switch (i) {
            case 0:
                vjVar.y();
                break;
            default:
                vjVar.x();
                break;
        }
    }
}
