package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Surface;
import defpackage.pa1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class lf2 implements pa1.a, LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ lf2(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.h = obj4;
        this.i = obj5;
    }

    @Override // pa1.a
    public final void a(int i) {
        nf2 nf2Var = (nf2) this.e;
        pa1 pa1Var = (pa1) this.f;
        Callback callback = (Callback) this.g;
        Rect rect = (Rect) this.h;
        Surface surface = (Surface) this.i;
        nf2Var.getClass();
        if (i == 6) {
            return;
        }
        Bitmap bitmap = pa1Var.d;
        if (i != 3 || bitmap == null) {
            callback.onResult(new Rect());
            return;
        }
        Rect rect2 = new Rect(0, 0, rect.width(), rect.height());
        Canvas canvasLockCanvas = surface.lockCanvas(rect2);
        canvasLockCanvas.drawColor(-1);
        canvasLockCanvas.drawBitmap(bitmap, (Rect) null, rect2, (Paint) null);
        surface.unlockCanvasAndPost(canvasLockCanvas);
        rect.offset(0, -nf2Var.e);
        callback.onResult(rect);
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        ke3 ke3Var = (ke3) this.e;
        String str = (String) this.f;
        Credential credential = (Credential) this.g;
        GURL gurl = (GURL) this.h;
        LargeIconBridge.LargeIconCallback largeIconCallback = (LargeIconBridge.LargeIconCallback) this.i;
        ke3Var.getClass();
        if (bitmap == null && str.equals(credential.getOriginUrl())) {
            ke3Var.d.b(gurl, ke3Var.e, largeIconCallback);
        } else {
            largeIconCallback.onLargeIconAvailable(bitmap, i, z, i2);
        }
    }
}
