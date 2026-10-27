package org.chromium.chrome.browser.password_manager.settings;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.n;
import defpackage.n3;
import defpackage.oj2;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordReauthenticationFragment extends Fragment {
    public FragmentManager Z;

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        if (i == 2) {
            if (i2 == -1) {
                oj2.U(0, 3, "PasswordManager.ReauthToAccessPasswordInSettings");
                long jCurrentTimeMillis = System.currentTimeMillis();
                int i3 = this.j.getInt("scope");
                n3.B = Long.valueOf(jCurrentTimeMillis);
                n3.C = i3;
            } else {
                oj2.U(1, 3, "PasswordManager.ReauthToAccessPasswordInSettings");
                n3.B = null;
                n3.C = 0;
            }
            FragmentManager fragmentManager = this.Z;
            fragmentManager.getClass();
            fragmentManager.u(fragmentManager.new n(-1, 0), false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) throws Exception {
        super.c0(bundle);
        this.Z = this.v;
        if (bundle == null) {
            Intent intentCreateConfirmDeviceCredentialIntent = ((KeyguardManager) D().getSystemService("keyguard")).createConfirmDeviceCredentialIntent(null, O(this.j.getInt("description", 0)));
            if (intentCreateConfirmDeviceCredentialIntent != null) {
                G0(intentCreateConfirmDeviceCredentialIntent, 2);
            } else {
                this.Z.O();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        bundle.putBoolean("has_been_suspended", true);
    }
}
