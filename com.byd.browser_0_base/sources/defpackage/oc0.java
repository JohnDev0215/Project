package defpackage;

import J.N;
import android.content.Context;
import defpackage.di1;
import java.io.File;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: DownloadLocationDialogCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class oc0 implements di1.a {
    public nc0 e;
    public PropertyModel f;
    public PropertyModel g;
    public j<PropertyModel, DownloadLocationCustomView, v62> h;
    public DownloadLocationCustomView i;
    public bi1 j;
    public long k;
    public int l;
    public String m;
    public Context n;
    public boolean o;
    public boolean p;
    public boolean q;

    @Override // di1.a
    public final void b(int i) {
        String str;
        if (i != 1) {
            DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) this.e;
            long j = downloadDialogBridge.a;
            if (j != 0) {
                N.M9BtabC7(j, downloadDialogBridge);
                WindowAndroid windowAndroid = downloadDialogBridge.c;
                if (windowAndroid != null) {
                    zl1 zl1Var = (zl1) am1.a.e(windowAndroid.r);
                    if (zl1Var != null) {
                        zl1Var.destroy();
                        zl1Var.f.finish();
                    }
                    downloadDialogBridge.c = null;
                }
            }
        } else {
            String fileName = this.i.getFileName();
            ma0 directoryOption = this.i.getDirectoryOption();
            boolean dontShowAgain = this.i.getDontShowAgain();
            if (directoryOption == null || (str = directoryOption.b) == null || fileName == null) {
                DownloadDialogBridge downloadDialogBridge2 = (DownloadDialogBridge) this.e;
                long j2 = downloadDialogBridge2.a;
                if (j2 != 0) {
                    N.M9BtabC7(j2, downloadDialogBridge2);
                    WindowAndroid windowAndroid2 = downloadDialogBridge2.c;
                    if (windowAndroid2 != null) {
                        zl1 zl1Var2 = (zl1) am1.a.e(windowAndroid2.r);
                        if (zl1Var2 != null) {
                            zl1Var2.destroy();
                            zl1Var2.f.finish();
                        }
                        downloadDialogBridge2.c = null;
                    }
                }
            } else {
                oj2.U(directoryOption.e, 3, "MobileDownload.Location.Dialog.DirectoryType");
                ((DownloadDialogBridge) this.e).a(new File(str, fileName).getAbsolutePath());
                if (!this.q) {
                    N.MPBZLcVx(oj2.k(Profile.d()).a, "download.prompt_for_download_android", dontShowAgain ? 2 : 1);
                }
            }
        }
        this.f = null;
        this.i = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            this.j.b(1, propertyModel);
        } else {
            if (i != 1) {
                return;
            }
            this.j.b(2, propertyModel);
        }
    }
}
