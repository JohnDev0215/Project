package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Ou implements Runnable {
    public final Su s;
    public final Object t;

    public Ou(Su su, Object obj) {
        this.s = su;
        this.t = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.k(this.t);
    }
}
