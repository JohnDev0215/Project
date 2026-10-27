package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewStructure;
import org.chromium.components.content_capture.ContentCaptureController;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ww extends Vw {
    public C0799fx b;
    public final View c;

    public Ww(View view, ViewStructure viewStructure, WebContents webContents) {
        super(webContents);
        this.c = view;
        if (viewStructure != null) {
            this.b = new C0799fx(view.getContentCaptureSession(), viewStructure.getAutofillId());
        }
    }

    public static Vw f(Context context, View view, ViewStructure viewStructure, WebContents webContents) {
        if (ContentCaptureController.b == null) {
            Xw.e(context.getApplicationContext());
        }
        if (ContentCaptureController.b.b()) {
            return new Ww(view, viewStructure, webContents);
        }
        return null;
    }

    public static Vw g(Context context, View view, WebContents webContents) {
        return f(context, view, null, webContents);
    }

    @Override // defpackage.Vw
    public void a(C0632cx c0632cx, ContentCaptureData contentCaptureData) {
        if (this.b == null) {
            C0799fx c0799fxA = C0799fx.a(this.c);
            this.b = c0799fxA;
            if (c0799fxA == null) {
                return;
            }
        }
        new Zw(c0632cx, contentCaptureData, this.b).d(Su.g);
    }

    @Override // defpackage.Vw
    public void b(C0632cx c0632cx, long[] jArr) {
        if (c0632cx.isEmpty() || this.b == null) {
            return;
        }
        new C0520ax(c0632cx, jArr, this.b).d(Su.g);
    }

    @Override // defpackage.Vw
    public void c(C0632cx c0632cx, ContentCaptureData contentCaptureData) {
        if (this.b == null) {
            return;
        }
        new C0576bx(c0632cx, contentCaptureData, this.b).d(Su.g);
    }

    @Override // defpackage.Vw
    public void d(C0632cx c0632cx) {
        if (c0632cx.isEmpty() || this.b == null) {
            return;
        }
        new C0911hx(c0632cx, this.b).d(Su.g);
    }
}
