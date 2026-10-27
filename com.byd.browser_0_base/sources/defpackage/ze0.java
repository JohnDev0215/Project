package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.download.DuplicateDownloadDialogBridge;
import org.chromium.chrome.browser.download.InsecureDownloadDialogBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ze0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object g;

    public /* synthetic */ ze0(long j, int i, Object obj) {
        this.e = i;
        this.g = obj;
        this.f = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        long j = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                DuplicateDownloadDialogBridge duplicateDownloadDialogBridge = (DuplicateDownloadDialogBridge) obj2;
                duplicateDownloadDialogBridge.getClass();
                N.MQbifKYb(duplicateDownloadDialogBridge.a, j, ((Boolean) obj).booleanValue());
                break;
            default:
                InsecureDownloadDialogBridge insecureDownloadDialogBridge = (InsecureDownloadDialogBridge) obj2;
                insecureDownloadDialogBridge.getClass();
                N.MsGg6Bng(insecureDownloadDialogBridge.a, j, ((Boolean) obj).booleanValue());
                break;
        }
    }
}
