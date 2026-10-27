package defpackage;

import android.app.Dialog;
import android.os.Looper;
import android.view.WindowManager;
import org.chromium.base.ThreadUtils;
import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AA {
    public InterfaceC1862zA a;
    public Dialog b;
    public SurfaceHolderCallback2C1807yA c;
    public WindowManager.LayoutParams d;
    public boolean e;
    public Looper f;

    public final void a() {
        if (this.f == Looper.myLooper()) {
            return;
        }
        if (!ThreadUtils.j()) {
            throw new RuntimeException("DialogOverlayCore is on the wrong thread");
        }
        throw new RuntimeException("DialogOverlayCore is on the UI thread");
    }

    public final boolean b(Rect rect) {
        WindowManager.LayoutParams layoutParams = this.d;
        if (layoutParams.x == rect.b && layoutParams.y == rect.c && layoutParams.width == rect.d && layoutParams.height == rect.e) {
            return false;
        }
        WindowManager.LayoutParams layoutParams2 = this.d;
        layoutParams2.x = rect.b;
        layoutParams2.y = rect.c;
        layoutParams2.width = rect.d;
        layoutParams2.height = rect.e;
        return true;
    }

    public final void c() {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            try {
                this.b.dismiss();
            } catch (Exception unused) {
                Ts.f("DSCore", "Failed to dismiss overlay dialog.  \"WindowLeaked\" is ignorable.", new Object[0]);
            }
        }
        this.b = null;
        this.c = null;
    }
}
