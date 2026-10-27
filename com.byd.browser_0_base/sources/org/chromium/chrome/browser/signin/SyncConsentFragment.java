package org.chromium.chrome.browser.signin;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import defpackage.oj2;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;

/* JADX INFO: loaded from: classes2.dex */
public class SyncConsentFragment extends SyncConsentFragmentBase {
    public static final /* synthetic */ int t0 = 0;
    public int s0;

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void J0(boolean z) {
        String str;
        int i = this.s0;
        if (i != 0) {
            if (i == 1) {
                str = "Signin.SigninCompletedAccessPoint.WithDefault";
            } else if (i == 2) {
                str = "Signin.SigninCompletedAccessPoint.NotDefault";
            } else if (i == 3) {
                str = "Signin.SigninCompletedAccessPoint.NewAccountNoExistingAccount";
            }
            oj2.U(this.n0, 46, str);
        }
        FragmentActivity fragmentActivityD = D();
        if (fragmentActivityD != null) {
            fragmentActivityD.finish();
        }
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void L0(Runnable runnable, String str, boolean z) {
        O0(runnable, str, z);
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase
    public final void M0() {
        D().finish();
    }

    @Override // org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        String str;
        super.c0(bundle);
        int i = this.j.getInt("SyncConsentFragment.PersonalizedPromoAction", 0);
        this.s0 = i;
        if (i != 0) {
            if (i == 1) {
                str = "Signin.SigninStartedAccessPoint.WithDefault";
            } else if (i == 2) {
                str = "Signin.SigninStartedAccessPoint.NotDefault";
            } else if (i != 3) {
                return;
            } else {
                str = "Signin.SigninStartedAccessPoint.NewAccountNoExistingAccount";
            }
            oj2.U(this.n0, 46, str);
        }
    }
}
