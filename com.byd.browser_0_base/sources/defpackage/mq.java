package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.PersistableBundle;
import org.chromium.chrome.browser.base.SplitCompatMinidumpUploadJobService;

/* JADX INFO: compiled from: ChromeMinidumpUploaderDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class mq implements xh1 {
    public final Context a;
    public final PersistableBundle b;
    public final ConnectivityManager c;

    public mq(SplitCompatMinidumpUploadJobService splitCompatMinidumpUploadJobService, PersistableBundle persistableBundle) {
        this.a = splitCompatMinidumpUploadJobService;
        this.b = persistableBundle;
        this.c = (ConnectivityManager) splitCompatMinidumpUploadJobService.getSystemService("connectivity");
    }
}
