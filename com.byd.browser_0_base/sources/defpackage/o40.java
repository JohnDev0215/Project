package defpackage;

import J.N;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.ntp.ForeignSessionHelper;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.chrome.browser.ui.signin.k;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o40 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ o40(Object obj, int i, boolean z) {
        this.e = i;
        this.g = obj;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j;
        switch (this.e) {
            case 0:
                ((CustomTabToolbar.b) this.g).x(this.f);
                return;
            case 1:
                String str = (String) this.g;
                boolean z = this.f;
                synchronized (DownloadManagerBridge.a) {
                    SharedPreferences sharedPreferences = xz.a.getSharedPreferences("download_id_mappings", 0);
                    j = sharedPreferences.getLong(str, -1L);
                    if (j != -1) {
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        editorEdit.remove(str);
                        editorEdit.apply();
                    }
                    break;
                }
                if (j == -1 || z) {
                    return;
                }
                ((DownloadManager) xz.a.getSystemService("download")).remove(j);
                return;
            case 2:
                gj2 gj2Var = (gj2) this.g;
                boolean z2 = this.f;
                gj2Var.g = z2;
                ForeignSessionHelper foreignSessionHelper = new ForeignSessionHelper(gj2Var.f);
                N.Mq_fLm3S(foreignSessionHelper.a, z2);
                N.MHB2z4$M(foreignSessionHelper.a);
                foreignSessionHelper.a = 0L;
                return;
            default:
                k kVar = (k) this.g;
                SyncConsentFragmentBase syncConsentFragmentBase = kVar.b;
                syncConsentFragmentBase.L0(new ve3(3, kVar), syncConsentFragmentBase.f0, this.f);
                return;
        }
    }
}
