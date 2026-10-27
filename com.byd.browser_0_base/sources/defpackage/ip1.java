package defpackage;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import defpackage.q12;
import org.chromium.components.content_capture.ContentCaptureFrame;

/* JADX INFO: compiled from: NotificationTask.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ip1 extends i9<Boolean> {
    public static Boolean j;
    public final jq0 h;
    public final q12 i;

    public ip1(jq0 jq0Var, q12 q12Var) {
        this.h = jq0Var;
        this.i = q12Var;
        if (j == null) {
            j = Boolean.valueOf(iv.e().g("dump-captured-content-to-logcat-for-testing"));
        }
    }

    public static void q(String str) {
        if (j.booleanValue()) {
            Log.i("cr_ContentCapture", str);
        }
    }

    public static AutofillId r(q12.a aVar, jy jyVar) {
        vd1 vd1VarF = vd1.f();
        ContentCaptureSession contentCaptureSession = aVar.a;
        long j2 = jyVar.a;
        ((m12) vd1VarF).getClass();
        ViewStructure viewStructureNewVirtualViewStructure = contentCaptureSession.newVirtualViewStructure(aVar.b, j2);
        viewStructureNewVirtualViewStructure.setText(jyVar.a());
        Rect rect = jyVar.b;
        viewStructureNewVirtualViewStructure.setDimens(rect.left, rect.top, 0, 0, rect.width(), rect.height());
        ((m12) vd1.f()).getClass();
        aVar.a.notifyViewAppeared(viewStructureNewVirtualViewStructure);
        return viewStructureNewVirtualViewStructure.getAutofillId();
    }

    @Override // defpackage.i9
    public final Boolean b() {
        try {
            s();
        } catch (NullPointerException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            int length = stackTrace.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (stackTraceElement.getClassName().startsWith("android.view.contentcapture.MainContentCaptureSession") && stackTraceElement.getMethodName().startsWith("sendEvent")) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                throw e;
            }
            Log.e("cr_ContentCapture", "PlatformException", e);
        }
        return Boolean.TRUE;
    }

    @Override // defpackage.i9
    public final /* bridge */ /* synthetic */ void l(Boolean bool) {
    }

    public final q12.a o() {
        q12 q12Var = this.i;
        jq0 jq0Var = this.h;
        if (jq0Var == null || jq0Var.isEmpty()) {
            return q12Var.a;
        }
        q12.a aVarP = q12Var.a;
        int size = jq0Var.size();
        do {
            size--;
            if (size < 0) {
                break;
            }
            aVarP = p(aVarP, jq0Var.get(size));
        } while (aVarP != null);
        return aVarP;
    }

    public final q12.a p(q12.a aVar, ContentCaptureFrame contentCaptureFrame) {
        q12 q12Var = this.i;
        q12.a aVar2 = q12Var.a().get(Long.valueOf(contentCaptureFrame.a));
        if (aVar2 != null) {
            return aVar2;
        }
        String str = contentCaptureFrame.d;
        if (TextUtils.isEmpty(str)) {
            return aVar2;
        }
        ContentCaptureSession contentCaptureSessionD = vd1.f().d(aVar.a, str, contentCaptureFrame.f);
        vd1 vd1VarF = vd1.f();
        AutofillId autofillId = q12Var.a.b;
        ((m12) vd1VarF).getClass();
        ContentCaptureSession contentCaptureSession = aVar.a;
        long j2 = contentCaptureFrame.a;
        contentCaptureSession.newAutofillId(autofillId, j2);
        q12.a aVar3 = new q12.a(contentCaptureSessionD, r(aVar, contentCaptureFrame));
        q12Var.a().put(Long.valueOf(j2), aVar3);
        return aVar3;
    }

    public abstract void s();
}
