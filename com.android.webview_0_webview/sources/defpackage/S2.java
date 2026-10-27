package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class S2 implements Runnable {
    public final /* synthetic */ Object s;
    public final /* synthetic */ T2 t;

    public S2(T2 t2, Object obj) {
        this.t = t2;
        this.s = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.u.a(this.s);
    }
}
