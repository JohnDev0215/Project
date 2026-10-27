package defpackage;

import android.content.LocusId;
import android.os.Bundle;
import android.view.contentcapture.ContentCaptureContext;
import android.view.contentcapture.ContentCaptureSession;

/* JADX INFO: compiled from: PlatformAPIWrapperImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m12 extends vd1 {
    @Override // defpackage.vd1
    public final ContentCaptureSession d(ContentCaptureSession contentCaptureSession, String str, String str2) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putCharSequence("favicon", str2);
        }
        return contentCaptureSession.createContentCaptureSession(new ContentCaptureContext.Builder(new LocusId(str)).setExtras(bundle).build());
    }

    @Override // defpackage.vd1
    public final void i(ContentCaptureSession contentCaptureSession, String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putCharSequence("favicon", str);
        }
        contentCaptureSession.setContentCaptureContext(new ContentCaptureContext.Builder(contentCaptureSession.getContentCaptureContext().getLocusId()).setExtras(bundle).build());
    }
}
