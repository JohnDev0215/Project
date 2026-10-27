package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.share.screenshot.ScreenshotShareSheetDialog;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ScreenshotCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public class se2 extends ud {
    public final WindowAndroid k;
    public final ScreenshotShareSheetDialog l;

    public se2(Activity activity, WindowAndroid windowAndroid, String str, rq rqVar, ji jiVar) {
        super(activity, str, rqVar, jiVar);
        this.k = windowAndroid;
        this.l = new ScreenshotShareSheetDialog();
    }
}
