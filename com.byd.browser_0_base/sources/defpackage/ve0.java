package defpackage;

import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;
import android.webkit.MimeTypeMap;
import java.io.File;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.download.DownloadCollectionBridge;

/* JADX INFO: compiled from: DuplicateDownloadClickableSpan.java */
/* JADX INFO: loaded from: classes.dex */
public final class ve0 extends ClickableSpan {
    public final Runnable e;
    public final OTRProfileID f;
    public final String g;
    public final int h;

    /* JADX INFO: compiled from: DuplicateDownloadClickableSpan.java */
    public class a extends i9<String> {
        public String h;

        public a() {
        }

        @Override // defpackage.i9
        public final String b() throws Throwable {
            ve0 ve0Var = ve0.this;
            File file = new File(ve0Var.g);
            String str = ve0Var.g;
            if (DownloadCollectionBridge.shouldPublishDownload(str)) {
                Uri uriB = DownloadCollectionBridge.b(file.getName());
                this.h = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString()));
                if (uriB != null) {
                    return uriB.toString();
                }
            } else if (file.exists()) {
                return str;
            }
            return null;
        }

        @Override // defpackage.i9
        public final void l(String str) {
            String str2 = str;
            ve0 ve0Var = ve0.this;
            Runnable runnable = ve0Var.e;
            if (runnable != null) {
                runnable.run();
            }
            if (str2 != null) {
                DownloadUtils.openDownload(str2, this.h, null, ve0Var.f, null, null, ve0Var.h);
            } else {
                DownloadManagerService.openDownloadsPage(ve0Var.f, ve0Var.h);
            }
        }
    }

    public ve0(String str, Runnable runnable, OTRProfileID oTRProfileID, int i) {
        this.e = runnable;
        this.f = oTRProfileID;
        this.g = str;
        this.h = i;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        new a().c(i9.e);
    }
}
