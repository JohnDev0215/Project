package defpackage;

import android.view.MotionEvent;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PN {
    public final ON a;

    public PN(ON on) {
        this.a = on;
    }

    public final void a(MotionEvent motionEvent, boolean z) {
        int iA = SN.a(motionEvent.getActionMasked());
        if (iA == 0 || iA == 9 || iA == 7) {
            b(-((NQ) this.a).a.A);
            return;
        }
        if (z) {
            if (iA == 1 || iA == 3 || iA == 10) {
                b(0.0f);
            }
        }
    }

    public final void b(float f) {
        WebContents webContents = ((NQ) this.a).a.y;
        if (webContents != null) {
            EventForwarder eventForwarderF = webContents.F();
            eventForwarderF.c = 0.0f;
            eventForwarderF.d = f;
        }
    }
}
