package defpackage;

/* JADX INFO: renamed from: kP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1051kP implements Runnable {
    public final /* synthetic */ ViewOnTouchListenerC1218nP s;

    public RunnableC1051kP(ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP) {
        this.s = viewOnTouchListenerC1218nP;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.s.f.isShowing()) {
            this.s.f.dismiss();
        }
    }
}
