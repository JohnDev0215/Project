package defpackage;

import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import defpackage.q12;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.components.content_capture.ContentCaptureFrame;

/* JADX INFO: compiled from: ContentUpdateTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yy extends o52 {
    public yy(jq0 jq0Var, ContentCaptureFrame contentCaptureFrame, q12 q12Var) {
        super(jq0Var, contentCaptureFrame, q12Var);
    }

    @Override // defpackage.o52
    public final AutofillId t(q12.a aVar, jy jyVar) {
        ContentCaptureData contentCaptureData = (ContentCaptureData) jyVar;
        vd1 vd1VarF = vd1.f();
        AutofillId autofillId = this.i.a.b;
        long j = contentCaptureData.a;
        ((m12) vd1VarF).getClass();
        ContentCaptureSession contentCaptureSession = aVar.a;
        AutofillId autofillIdNewAutofillId = contentCaptureSession.newAutofillId(autofillId, j);
        ((m12) vd1.f()).getClass();
        contentCaptureSession.notifyViewTextChanged(autofillIdNewAutofillId, contentCaptureData.d);
        return autofillIdNewAutofillId;
    }
}
