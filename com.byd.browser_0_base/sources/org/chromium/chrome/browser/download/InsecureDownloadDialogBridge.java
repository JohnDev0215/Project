package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import defpackage.C0173a;
import defpackage.bi1;
import defpackage.ci1;
import defpackage.di1;
import defpackage.k01;
import defpackage.md0;
import defpackage.oj2;
import defpackage.ze0;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class InsecureDownloadDialogBridge {
    public long a;

    public InsecureDownloadDialogBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static InsecureDownloadDialogBridge create(long j) {
        return new InsecureDownloadDialogBridge(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CalledByNative
    public final void showDialog(WindowAndroid windowAndroid, String str, long j, long j2) {
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == 0) {
            N.MsGg6Bng(this.a, j2, false);
            return;
        }
        bi1 bi1VarP0 = ((ci1) activity).P0();
        ze0 ze0Var = new ze0(j2, 1, this);
        if (j > 0) {
            StringBuilder sbK = C0173a.k(str, " (");
            sbK.append(md0.a(activity, j));
            sbK.append(")");
            str = sbK.toString();
        }
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, new k01(ze0Var, bi1VarP0));
        bVar.e(di1.c, activity.getResources().getString(R$string.insecure_download_dialog_title));
        bVar.e(di1.f, str);
        bVar.e(di1.j, activity.getResources().getString(R$string.insecure_download_dialog_confirm_text));
        bVar.e(di1.m, activity.getResources().getString(R$string.insecure_download_dialog_discard_text));
        bVar.c(di1.u, 0);
        bi1VarP0.j(bVar.a(), 0, false);
        oj2.U(0, 4, "Download.MixedContentDialog.Events");
    }
}
