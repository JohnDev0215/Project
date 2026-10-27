package defpackage;

import android.view.SurfaceView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TP implements Runnable {
    public final /* synthetic */ VP s;

    public TP(VP vp) {
        this.s = vp;
    }

    @Override // java.lang.Runnable
    public void run() {
        SurfaceView surfaceView = this.s.l;
        if (surfaceView != null) {
            surfaceView.setBackgroundResource(0);
        }
    }
}
