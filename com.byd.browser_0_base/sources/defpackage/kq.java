package defpackage;

import android.os.PersistableBundle;
import org.chromium.chrome.browser.base.SplitCompatMinidumpUploadJobService;
import org.chromium.components.minidump_uploader.b;

/* JADX INFO: compiled from: ChromeMinidumpUploadJobServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class kq extends SplitCompatMinidumpUploadJobService.a {
    @Override // org.chromium.chrome.browser.base.SplitCompatMinidumpUploadJobService.a
    public final b a(PersistableBundle persistableBundle) {
        return new b(new mq(this.a, persistableBundle));
    }
}
