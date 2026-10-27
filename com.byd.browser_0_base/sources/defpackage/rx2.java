package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class rx2 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ SyncConsentFragmentBase f;

    public /* synthetic */ rx2(SyncConsentFragmentBase syncConsentFragmentBase, int i) {
        this.e = i;
        this.f = syncConsentFragmentBase;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z = false;
        int i = this.e;
        SyncConsentFragmentBase syncConsentFragmentBase = this.f;
        switch (i) {
            case 0:
                if (!syncConsentFragmentBase.a0 && syncConsentFragmentBase.I0()) {
                    syncConsentFragmentBase.q0 = new e1(syncConsentFragmentBase.x0(), syncConsentFragmentBase, syncConsentFragmentBase.o0);
                    break;
                }
                break;
            case 1:
                int i2 = SyncConsentFragmentBase.r0;
                syncConsentFragmentBase.getClass();
                n3.X0("Signin_Undo_Signin");
                syncConsentFragmentBase.l0 = false;
                syncConsentFragmentBase.M0();
                break;
            default:
                int i3 = SyncConsentFragmentBase.r0;
                if (syncConsentFragmentBase.I0()) {
                    syncConsentFragmentBase.h0 = true;
                    syncConsentFragmentBase.l0 = false;
                    n3.X0("Signin_Signin_WithDefaultSyncSettings");
                    n3.S().a(syncConsentFragmentBase.f0).g(new ux2(syncConsentFragmentBase, view, z));
                    break;
                }
                break;
        }
    }
}
