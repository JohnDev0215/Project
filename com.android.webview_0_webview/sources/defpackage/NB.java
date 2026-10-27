package defpackage;

import android.view.KeyEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NB implements Runnable {
    public final /* synthetic */ KeyEvent s;
    public final /* synthetic */ QB t;

    public NB(QB qb, KeyEvent keyEvent) {
        this.t = qb;
        this.s = keyEvent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.sendKeyEvent(this.s);
    }
}
