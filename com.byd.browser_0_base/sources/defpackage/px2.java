package defpackage;

import android.view.View;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class px2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ SyncConsentFragmentBase f;

    public /* synthetic */ px2(SyncConsentFragmentBase syncConsentFragmentBase, int i) {
        this.e = i;
        this.f = syncConsentFragmentBase;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        SyncConsentFragmentBase syncConsentFragmentBase = this.f;
        switch (i) {
            case 0:
            default:
                syncConsentFragmentBase.P0((List) obj);
                break;
            case 1:
                View view = (View) obj;
                int i2 = SyncConsentFragmentBase.r0;
                if (syncConsentFragmentBase.I0()) {
                    syncConsentFragmentBase.h0 = true;
                    n3.X0("Signin_Signin_WithAdvancedSyncSettings");
                    n3.S().a(syncConsentFragmentBase.f0).g(new ux2(syncConsentFragmentBase, view, true));
                    break;
                }
                break;
        }
    }
}
