package defpackage;

import android.view.autofill.AutofillId;
import defpackage.q12;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.components.content_capture.ContentCaptureData;
import org.chromium.components.content_capture.ContentCaptureFrame;

/* JADX INFO: compiled from: ProcessContentCaptureDataTask.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class o52 extends ip1 {
    public final ContentCaptureFrame k;

    public o52(jq0 jq0Var, ContentCaptureFrame contentCaptureFrame, q12 q12Var) {
        super(jq0Var, q12Var);
        this.k = contentCaptureFrame;
    }

    @Override // defpackage.ip1
    public final void s() {
        ContentCaptureFrame contentCaptureFrame;
        q12.a aVarP;
        ip1.q("ProcessContentTaskBase.processContent");
        q12.a aVarO = o();
        if (aVarO == null || (contentCaptureFrame = this.k) == null || contentCaptureFrame.d == null || (aVarP = p(aVarO, contentCaptureFrame)) == null) {
            return;
        }
        Iterator<jy> it = contentCaptureFrame.c.iterator();
        while (it.hasNext() && u(aVarP, (ContentCaptureData) it.next())) {
        }
    }

    public abstract AutofillId t(q12.a aVar, jy jyVar);

    public final boolean u(q12.a aVar, ContentCaptureData contentCaptureData) {
        if (contentCaptureData == null) {
            return false;
        }
        ArrayList<jy> arrayList = contentCaptureData.c;
        if (!((arrayList == null || arrayList.isEmpty()) ? false : true)) {
            return t(aVar, contentCaptureData) != null;
        }
        AutofillId autofillIdT = t(aVar, contentCaptureData);
        if (autofillIdT == null) {
            return false;
        }
        q12.a aVar2 = new q12.a(aVar.a, autofillIdT);
        Iterator<jy> it = contentCaptureData.c.iterator();
        while (it.hasNext()) {
            if (!u(aVar2, (ContentCaptureData) it.next())) {
                return false;
            }
        }
        return true;
    }
}
