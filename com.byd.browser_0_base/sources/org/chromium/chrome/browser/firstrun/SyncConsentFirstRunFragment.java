package org.chromium.chrome.browser.firstrun;

import android.accounts.Account;
import android.view.View;
import defpackage.hn0;
import defpackage.pm2;
import defpackage.z7;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.firstrun.SyncConsentFirstRunFragment;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes.dex */
public class SyncConsentFirstRunFragment extends SyncConsentFragmentBase implements hn0 {
    public static final /* synthetic */ int s0 = 0;

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void J0(boolean z) {
        if (z) {
            SharedPreferencesManager.getInstance().l("first_run_signin_setup", true);
        }
        b().E();
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void L0(final Runnable runnable, final String str, final boolean z) {
        b().R0(5);
        if (z) {
            b().R0(12);
        }
        if (!b().V().getBoolean("IsChildAccount", false)) {
            O0(runnable, str, z);
            return;
        }
        final Profile profileD = Profile.d();
        z7.m().getClass();
        z7.D(profileD).q(new Runnable() { // from class: mx2
            @Override // java.lang.Runnable
            public final void run() {
                int i = SyncConsentFirstRunFragment.s0;
                SyncConsentFirstRunFragment syncConsentFirstRunFragment = this.e;
                syncConsentFirstRunFragment.getClass();
                z7.m().getClass();
                CoreAccountInfo coreAccountInfoA = z7.v(profileD).a(1);
                String str2 = str;
                boolean z2 = z;
                Runnable runnable2 = runnable;
                if (coreAccountInfoA == null) {
                    syncConsentFirstRunFragment.O0(runnable2, str2, z2);
                } else {
                    if (!str2.equals(coreAccountInfoA.getEmail())) {
                        throw new IllegalStateException("Child accounts should only be allowed to sync with a single account");
                    }
                    syncConsentFirstRunFragment.J0(z2);
                    runnable2.run();
                }
            }
        });
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void M0() {
        pm2 pm2Var = pm2.b;
        pm2Var.getClass();
        pm2Var.a.n(System.currentTimeMillis(), "ntp.signin_promo_suppression_period_start");
        b().R0(6);
        b().E();
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void P0(List<Account> list) {
        String str = this.f0;
        if (str != null && org.chromium.components.signin.b.d(str, list) == null) {
            b().a1();
        } else {
            super.P0(list);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0045  */
    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a0(android.content.Context r4) {
        /*
            r3 = this;
            super.a0(r4)
            org.chromium.components.signin.AccountManagerFacade r4 = org.chromium.components.signin.AccountManagerFacadeProvider.getInstance()
            s62 r4 = r4.b()
            java.util.List r4 = org.chromium.components.signin.b.e(r4)
            jn0 r0 = r3.b()
            android.os.Bundle r0 = r0.V()
            java.lang.String r1 = "IsChildAccount"
            r2 = 0
            boolean r0 = r0.getBoolean(r1, r2)
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L26
            r4 = 0
            goto L2e
        L26:
            java.lang.Object r4 = r4.get(r2)
            android.accounts.Account r4 = (android.accounts.Account) r4
            java.lang.String r4 = r4.name
        L2e:
            if (r0 != 0) goto L45
            sk r1 = defpackage.bq.a
            java.lang.String r1 = "TangibleSync"
            boolean r1 = J.N.M09VlOh_(r1)
            if (r1 == 0) goto L45
            android.os.Bundle r4 = org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase.K0(r2, r4)
            java.lang.String r0 = "SyncConsentFragment.ShowTangibleSyncConsentView"
            r1 = 1
            r4.putBoolean(r0, r1)
            goto L4e
        L45:
            android.os.Bundle r4 = org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase.K0(r2, r4)
            java.lang.String r1 = "SyncConsentFragmentBase.ChildAccountStatus"
            r4.putBoolean(r1, r0)
        L4e:
            r3.C0(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.firstrun.SyncConsentFirstRunFragment.a0(android.content.Context):void");
    }

    @Override // defpackage.hn0
    public final void h() {
        View view = this.f12J;
        if (view == null) {
            return;
        }
        View viewFindViewById = view.findViewById(R$id.signin_title);
        if (viewFindViewById == null) {
            viewFindViewById = this.f12J.findViewById(R$id.sync_consent_title);
        }
        viewFindViewById.sendAccessibilityEvent(8);
    }
}
