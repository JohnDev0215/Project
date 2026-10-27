package defpackage;

import J.N;
import java.lang.ref.WeakReference;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Ur, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0376Ur implements Runnable {
    public WeakReference s;
    public boolean t;
    public boolean u;
    public String v;

    public RunnableC0376Ur(AwContents awContents, String str) {
        this.s = new WeakReference(awContents);
        this.v = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwContents awContents = (AwContents) this.s.get();
        if (awContents == null) {
            return;
        }
        if (this.u) {
            if (this.t) {
                awContents.t.a().a(this.v);
            } else {
                C0212Lo c0212LoA = awContents.t.a();
                String strD = c0212LoA.d(this.v);
                if (strD != null) {
                    c0212LoA.a.edit().putBoolean(strD, false).apply();
                }
            }
        }
        boolean z = this.t;
        String str = this.v;
        if (awContents.t(0)) {
            return;
        }
        N.M0cKB_Do(awContents.s, awContents, z, str);
    }
}
