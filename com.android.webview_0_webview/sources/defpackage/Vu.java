package defpackage;

import android.view.Choreographer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Vu implements Choreographer.FrameCallback {
    public final /* synthetic */ Runnable a;

    public Vu(Wu wu, Runnable runnable) {
        this.a = runnable;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        this.a.run();
    }
}
