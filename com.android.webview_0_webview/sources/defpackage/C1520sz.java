package defpackage;

import android.view.MotionEvent;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* JADX INFO: renamed from: sz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1520sz extends KD implements LD, InterfaceC1074kt {
    public final EventForwarder s;
    public boolean t = true;

    public C1520sz(WebContents webContents, AbstractC1356pz abstractC1356pz) {
        this.s = webContents.F();
        ImeAdapterImpl.P(webContents).A.add(this);
    }

    public static float a(MotionEvent motionEvent, int i) {
        float axisValue = motionEvent.getAxisValue(i);
        if (Math.abs(axisValue) <= 0.2f) {
            return 0.0f;
        }
        return -axisValue;
    }

    @Override // defpackage.KD, defpackage.LD
    public void f(boolean z, boolean z2) {
        this.t = !z;
    }
}
