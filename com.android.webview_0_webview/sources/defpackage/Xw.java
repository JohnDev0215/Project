package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.LocusId;
import android.view.contentcapture.ContentCaptureCondition;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.DataRemovalRequest;
import java.util.Set;
import org.chromium.base.BuildInfo;
import org.chromium.components.content_capture.ContentCaptureController;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Xw extends ContentCaptureController {
    public boolean c;
    public ContentCaptureManager d;

    public Xw(Context context) {
        this.d = (ContentCaptureManager) context.getSystemService(ContentCaptureManager.class);
        g();
    }

    public static void e(Context context) {
        ContentCaptureController.b = new Xw(context);
    }

    private void f(String str) {
        if (Yw.a()) {
            Ts.d("ContentCapture", str, new Object[0]);
        }
    }

    private void g() {
        ContentCaptureManager contentCaptureManager = this.d;
        if (contentCaptureManager == null) {
            f("ContentCaptureManager isn't available.");
            return;
        }
        ComponentName serviceComponentName = contentCaptureManager.getServiceComponentName();
        if (serviceComponentName == null) {
            f("Service isn't available.");
            return;
        }
        boolean zEquals = "com.google.android.as".equals(serviceComponentName.getPackageName());
        this.c = zEquals;
        if (!zEquals) {
            StringBuilder sbF = Z7.f("Package doesn't match, current one is ");
            sbF.append(this.d.getServiceComponentName().getPackageName());
            f(sbF.toString());
            if (!BuildInfo.b() && !Yw.a()) {
                return;
            }
        }
        boolean zIsContentCaptureEnabled = this.d.isContentCaptureEnabled();
        this.c = zIsContentCaptureEnabled;
        if (zIsContentCaptureEnabled) {
            return;
        }
        f("ContentCapture disabled.");
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public boolean b() {
        return this.c;
    }

    public void c() {
        ContentCaptureManager contentCaptureManager = this.d;
        if (contentCaptureManager == null) {
            return;
        }
        contentCaptureManager.removeData(new DataRemovalRequest.Builder().forEverything().build());
    }

    public void d(String[] strArr) {
        if (this.d == null) {
            return;
        }
        DataRemovalRequest.Builder builder = new DataRemovalRequest.Builder();
        for (String str : strArr) {
            builder = builder.addLocusId(new LocusId(str), 0);
        }
        this.d.removeData(builder.build());
    }

    @Override // org.chromium.components.content_capture.ContentCaptureController
    public void pullWhitelist() {
        boolean[] zArr;
        Set<ContentCaptureCondition> contentCaptureConditions = this.d.getContentCaptureConditions();
        String[] strArr = null;
        if (contentCaptureConditions != null) {
            strArr = new String[contentCaptureConditions.size()];
            zArr = new boolean[contentCaptureConditions.size()];
            int i = 0;
            for (ContentCaptureCondition contentCaptureCondition : contentCaptureConditions) {
                strArr[i] = contentCaptureCondition.getLocusId().getId();
                zArr[i] = (contentCaptureCondition.getFlags() & 2) != 0;
                i++;
            }
        } else {
            zArr = null;
        }
        a(strArr, zArr);
    }
}
