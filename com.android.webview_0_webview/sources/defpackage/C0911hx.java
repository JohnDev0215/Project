package defpackage;

import org.chromium.components.content_capture.ContentCaptureData;

/* JADX INFO: renamed from: hx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0911hx extends AbstractC0687dx {
    public C0911hx(C0632cx c0632cx, C0799fx c0799fx) {
        super(c0632cx, c0799fx);
    }

    @Override // defpackage.Su
    public Object c() {
        r("SessionRemovedTask.removeSession");
        C0743ex c0743ex = (C0743ex) this.i.b().remove(Long.valueOf(((ContentCaptureData) this.h.get(0)).a));
        if (c0743ex != null) {
            c0743ex.a.destroy();
            C0743ex c0743exC = this.i.c();
            if (this.h.size() > 2) {
                c0743exC = (C0743ex) this.i.b().get(Long.valueOf(((ContentCaptureData) this.h.get(1)).a));
            }
            if (c0743exC != null) {
                c0743exC.a.notifyViewDisappeared(c0743ex.b);
            }
        }
        return Boolean.TRUE;
    }
}
