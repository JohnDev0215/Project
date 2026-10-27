package defpackage;

import org.chromium.chrome.browser.download.MimeUtils;
import org.chromium.chrome.browser.download.b;

/* JADX INFO: compiled from: DownloadDelegateImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class xb0 extends d70 {
    @Override // defpackage.d70
    public final boolean e(String str) {
        return b.c(str);
    }

    @Override // defpackage.d70
    public final void f(String str) {
        md0.c(str);
    }

    @Override // defpackage.d70
    public final String g(String str, String str2, String str3) {
        return MimeUtils.remapGenericMimeType(str, str2, str3);
    }
}
