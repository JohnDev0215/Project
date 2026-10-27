package defpackage;

import android.os.IBinder;
import android.view.WindowManager;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FA implements Runnable {
    public final /* synthetic */ AA s;
    public final /* synthetic */ IBinder t;

    public FA(DialogOverlayImpl dialogOverlayImpl, AA aa, IBinder iBinder) {
        this.s = aa;
        this.t = iBinder;
    }

    @Override // java.lang.Runnable
    public void run() {
        IBinder iBinder;
        AA aa = this.s;
        IBinder iBinder2 = this.t;
        aa.a();
        if (aa.b == null || aa.a == null) {
            return;
        }
        if (iBinder2 == null || !((iBinder = aa.d.token) == null || iBinder2 == iBinder)) {
            aa.a.x();
            aa.a = null;
            aa.c();
            return;
        }
        WindowManager.LayoutParams layoutParams = aa.d;
        if (layoutParams.token == iBinder2) {
            return;
        }
        layoutParams.token = iBinder2;
        aa.b.getWindow().setAttributes(aa.d);
        aa.c = new SurfaceHolderCallback2C1807yA(aa, null);
        aa.b.getWindow().takeSurface(aa.c);
        aa.b.show();
    }
}
