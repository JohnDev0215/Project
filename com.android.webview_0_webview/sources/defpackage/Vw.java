package defpackage;

import J.N;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.components.content_capture.ContentCaptureReceiverManager;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Vw {
    public ContentCaptureReceiverManager a;

    public Vw(WebContents webContents) {
        e(webContents);
    }

    public abstract void a(C0632cx c0632cx, ContentCaptureData contentCaptureData);

    public abstract void b(C0632cx c0632cx, long[] jArr);

    public abstract void c(C0632cx c0632cx, ContentCaptureData contentCaptureData);

    public abstract void d(C0632cx c0632cx);

    public void e(WebContents webContents) {
        if (N.MRrWU$ia()) {
            if (webContents == null) {
                this.a = null;
                return;
            }
            ContentCaptureReceiverManager contentCaptureReceiverManager = (ContentCaptureReceiverManager) N.MxegY8Dy(webContents);
            this.a = contentCaptureReceiverManager;
            contentCaptureReceiverManager.a = this;
        }
    }
}
