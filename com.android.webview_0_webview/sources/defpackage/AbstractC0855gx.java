package defpackage;

import android.view.autofill.AutofillId;
import java.util.Iterator;
import org.chromium.components.content_capture.ContentCaptureData;

/* JADX INFO: renamed from: gx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0855gx extends AbstractC0687dx {
    public final ContentCaptureData k;

    public AbstractC0855gx(C0632cx c0632cx, ContentCaptureData contentCaptureData, C0799fx c0799fx) {
        super(c0632cx, c0799fx);
        this.k = contentCaptureData;
    }

    @Override // defpackage.Su
    public Object c() {
        r("ProcessContentTaskBase.processContent");
        C0743ex c0743exP = p();
        if (c0743exP != null) {
            v(c0743exP, this.k);
        }
        return Boolean.TRUE;
    }

    public abstract AutofillId u(C0743ex c0743ex, ContentCaptureData contentCaptureData);

    public final boolean v(C0743ex c0743ex, ContentCaptureData contentCaptureData) {
        C0743ex c0743ex2;
        if (contentCaptureData == null) {
            return false;
        }
        if (!contentCaptureData.a()) {
            return u(c0743ex, contentCaptureData) != null;
        }
        if (contentCaptureData.b != null) {
            c0743ex2 = q(c0743ex, contentCaptureData);
            if (c0743ex2 == null) {
                return false;
            }
        } else {
            AutofillId autofillIdU = u(c0743ex, contentCaptureData);
            if (autofillIdU == null) {
                return false;
            }
            c0743ex2 = new C0743ex(c0743ex.a, autofillIdU);
        }
        Iterator it = contentCaptureData.d.iterator();
        while (it.hasNext()) {
            if (!v(c0743ex2, (ContentCaptureData) it.next())) {
                return false;
            }
        }
        return true;
    }
}
