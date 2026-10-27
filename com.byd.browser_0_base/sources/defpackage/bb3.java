package defpackage;

import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import defpackage.q12;
import org.chromium.components.content_capture.ContentCaptureFrame;

/* JADX INFO: compiled from: TitleUpdateTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bb3 extends ip1 {
    public final ContentCaptureFrame k;

    public bb3(ContentCaptureFrame contentCaptureFrame, q12 q12Var) {
        super(null, q12Var);
        this.k = contentCaptureFrame;
    }

    @Override // defpackage.ip1
    public final void s() {
        ip1.q("TitleUpdateTask.updateTitle");
        q12.a aVarO = o();
        vd1 vd1VarF = vd1.f();
        ContentCaptureSession contentCaptureSession = aVarO.a;
        AutofillId autofillId = this.i.a.b;
        ContentCaptureFrame contentCaptureFrame = this.k;
        long j = contentCaptureFrame.a;
        ((m12) vd1VarF).getClass();
        AutofillId autofillIdNewAutofillId = contentCaptureSession.newAutofillId(autofillId, j);
        ((m12) vd1.f()).getClass();
        aVarO.a.notifyViewTextChanged(autofillIdNewAutofillId, contentCaptureFrame.e);
    }
}
