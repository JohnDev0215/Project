package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Lu implements Runnable {
    public final Mu s;
    public final Object t;
    public final Callback u;

    public Lu(Mu mu, Object obj, Callback callback) {
        this.s = mu;
        this.t = obj;
        this.u = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        Mu mu = this.s;
        Object obj = this.t;
        Callback callback = this.u;
        if (mu.c == obj && mu.d.s.contains(callback)) {
            callback.a(mu.c);
        }
    }
}
