package defpackage;

/* JADX INFO: renamed from: ju, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1019ju extends Iu {
    public final /* synthetic */ C1406qu a;

    public BinderC1019ju(C1406qu c1406qu) {
        this.a = c1406qu;
    }

    @Override // defpackage.Ju
    public void b() {
        synchronized (C1406qu.A) {
            this.a.z = true;
        }
        this.a.a.post(new RunnableC0963iu(this));
    }

    @Override // defpackage.Ju
    public void r(int i) {
        this.a.a.post(new RunnableC0908hu(this, i));
    }
}
