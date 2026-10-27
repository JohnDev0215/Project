package org.chromium.chrome.browser.base;

import android.content.Context;
import android.os.PersistableBundle;
import org.chromium.base.BundleUtils;
import org.chromium.components.minidump_uploader.MinidumpUploadJobService;
import org.chromium.components.minidump_uploader.b;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatMinidumpUploadJobService extends MinidumpUploadJobService {
    public final String k = "kq";
    public a l;

    public static abstract class a {
        public SplitCompatMinidumpUploadJobService a;

        public abstract b a(PersistableBundle persistableBundle);
    }

    @Override // org.chromium.components.minidump_uploader.MinidumpUploadJobService
    public final org.chromium.components.minidump_uploader.a a(PersistableBundle persistableBundle) {
        return this.l.a(persistableBundle);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.k);
        this.l = aVar;
        aVar.a = this;
        super.attachBaseContext(contextB);
    }
}
