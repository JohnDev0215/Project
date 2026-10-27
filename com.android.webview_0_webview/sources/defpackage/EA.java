package defpackage;

import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EA implements Runnable {
    public final /* synthetic */ AA s;
    public final /* synthetic */ Rect t;

    public EA(DialogOverlayImpl dialogOverlayImpl, AA aa, Rect rect) {
        this.s = aa;
        this.t = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        AA aa = this.s;
        Rect rect = this.t;
        if (aa.b == null || aa.d.token == null || !aa.b(rect)) {
            return;
        }
        aa.b.getWindow().setAttributes(aa.d);
    }
}
