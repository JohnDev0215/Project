package defpackage;

import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import java.util.HashMap;

/* JADX INFO: compiled from: PlatformSession.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q12 {
    public final a a;
    public HashMap<Long, a> b;

    /* JADX INFO: compiled from: PlatformSession.java */
    public static final class a {
        public final ContentCaptureSession a;
        public final AutofillId b;

        public a(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
            this.a = contentCaptureSession;
            this.b = autofillId;
        }
    }

    public q12(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
        this.a = new a(contentCaptureSession, autofillId);
    }

    public final HashMap<Long, a> a() {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        return this.b;
    }
}
