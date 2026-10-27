package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GK implements Runnable {
    public final /* synthetic */ TK s;

    public GK(TK tk) {
        this.s = tk;
    }

    @Override // java.lang.Runnable
    public void run() {
        TK tk = this.s;
        if (tk.k) {
            if (tk.m) {
                tk.m = false;
            } else {
                tk.b();
            }
        }
    }
}
