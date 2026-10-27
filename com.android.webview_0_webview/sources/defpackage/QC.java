package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class QC implements Runnable {
    public final /* synthetic */ RC s;

    public QC(RC rc) {
        this.s = rc;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.a.dismiss();
        this.s.b.removeAllViews();
    }
}
