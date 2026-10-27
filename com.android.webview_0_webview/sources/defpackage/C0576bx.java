package defpackage;

import android.view.autofill.AutofillId;
import org.chromium.components.content_capture.ContentCaptureData;

/* JADX INFO: renamed from: bx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0576bx extends AbstractC0855gx {
    public C0576bx(C0632cx c0632cx, ContentCaptureData contentCaptureData, C0799fx c0799fx) {
        super(c0632cx, contentCaptureData, c0799fx);
    }

    @Override // defpackage.AbstractC0855gx
    public AutofillId u(C0743ex c0743ex, ContentCaptureData contentCaptureData) {
        AutofillId autofillIdNewAutofillId = c0743ex.a.newAutofillId(this.i.c().b, contentCaptureData.a);
        c0743ex.a.notifyViewTextChanged(autofillIdNewAutofillId, contentCaptureData.b);
        return autofillIdNewAutofillId;
    }
}
