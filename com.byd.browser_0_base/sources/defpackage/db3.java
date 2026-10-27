package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class db3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ db3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((eb3) obj).b();
                break;
            default:
                i7 i7Var = (i7) ((t80) obj).b;
                i7Var.c(null);
                oj2.O("Android.RequestDesktopSite.PerSiteIphDismissed.AppMenuOpened", i7Var.b());
                break;
        }
    }
}
