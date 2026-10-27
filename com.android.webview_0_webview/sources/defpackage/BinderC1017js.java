package defpackage;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import java.util.List;
import org.chromium.android_webview.services.CrashReceiverService;

/* JADX INFO: renamed from: js, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1017js extends AbstractBinderC0303Qq {
    public final /* synthetic */ CrashReceiverService a;

    public BinderC1017js(CrashReceiverService crashReceiverService) {
        this.a = crashReceiverService;
    }

    @Override // defpackage.InterfaceC0321Rq
    public void e(ParcelFileDescriptor[] parcelFileDescriptorArr, List list) {
        boolean z;
        int callingUid = Binder.getCallingUid();
        CrashReceiverService crashReceiverService = this.a;
        synchronized (crashReceiverService.s) {
            while (true) {
                z = true;
                if (!crashReceiverService.t) {
                    break;
                }
                try {
                    crashReceiverService.s.wait();
                } catch (InterruptedException e) {
                    Ts.a("CrashReceiverService", "Was interrupted when waiting to copy minidumps", e);
                    z = false;
                }
            }
            crashReceiverService.t = true;
        }
        if (!z) {
            Ts.a("CrashReceiverService", "something went wrong when waiting to copy minidumps, bailing!", new Object[0]);
            return;
        }
        try {
            if (CrashReceiverService.a(callingUid, parcelFileDescriptorArr, list)) {
                Xx.a(new JobInfo.Builder(42, new ComponentName(crashReceiverService, "org.chromium.android_webview.services.AwMinidumpUploadJobService")));
            }
            synchronized (crashReceiverService.s) {
                crashReceiverService.t = false;
                crashReceiverService.s.notifyAll();
            }
        } catch (Throwable th) {
            synchronized (crashReceiverService.s) {
                crashReceiverService.t = false;
                crashReceiverService.s.notifyAll();
                throw th;
            }
        }
    }
}
