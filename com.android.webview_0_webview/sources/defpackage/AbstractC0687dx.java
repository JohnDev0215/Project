package defpackage;

import android.content.LocusId;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureContext;
import android.view.contentcapture.ContentCaptureSession;
import org.chromium.components.content_capture.ContentCaptureData;

/* JADX INFO: renamed from: dx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0687dx extends Su {
    public static Boolean j;
    public final C0632cx h;
    public final C0799fx i;

    public AbstractC0687dx(C0632cx c0632cx, C0799fx c0799fx) {
        this.h = c0632cx;
        this.i = c0799fx;
        if (j == null) {
            j = Boolean.valueOf(Yw.a());
        }
    }

    public C0743ex p() {
        C0632cx c0632cx = this.h;
        if (c0632cx == null || c0632cx.isEmpty()) {
            return this.i.c();
        }
        C0743ex c0743exC = this.i.c();
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c0743exC = q(c0743exC, (ContentCaptureData) this.h.get(size));
            if (c0743exC == null) {
                break;
            }
        }
        return c0743exC;
    }

    public C0743ex q(C0743ex c0743ex, ContentCaptureData contentCaptureData) {
        C0743ex c0743ex2 = (C0743ex) this.i.b().get(Long.valueOf(contentCaptureData.a));
        if (c0743ex2 != null || TextUtils.isEmpty(contentCaptureData.b)) {
            return c0743ex2;
        }
        ContentCaptureSession contentCaptureSessionCreateContentCaptureSession = c0743ex.a.createContentCaptureSession(new ContentCaptureContext.Builder(new LocusId(contentCaptureData.b)).build());
        c0743ex.a.newAutofillId(this.i.c().b, contentCaptureData.a);
        C0743ex c0743ex3 = new C0743ex(contentCaptureSessionCreateContentCaptureSession, s(c0743ex, contentCaptureData));
        this.i.b().put(Long.valueOf(contentCaptureData.a), c0743ex3);
        return c0743ex3;
    }

    public void r(String str) {
        if (j.booleanValue()) {
            Ts.d("ContentCapture", str, new Object[0]);
        }
    }

    public AutofillId s(C0743ex c0743ex, ContentCaptureData contentCaptureData) {
        ViewStructure viewStructureNewVirtualViewStructure = c0743ex.a.newVirtualViewStructure(c0743ex.b, contentCaptureData.a);
        if (!contentCaptureData.a()) {
            viewStructureNewVirtualViewStructure.setText(contentCaptureData.b);
        }
        Rect rect = contentCaptureData.c;
        viewStructureNewVirtualViewStructure.setDimens(rect.left, rect.top, 0, 0, rect.width(), rect.height());
        c0743ex.a.notifyViewAppeared(viewStructureNewVirtualViewStructure);
        return viewStructureNewVirtualViewStructure.getAutofillId();
    }

    @Override // defpackage.Su
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public void n(Boolean bool) {
    }
}
