package org.chromium.chrome.browser.password_check;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.preference.PreferenceManager;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckFragmentView extends androidx.preference.d {
    public b h0;
    public int i0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.passwords_check_title);
        PreferenceManager preferenceManager = this.a0;
        L0(preferenceManager.a(preferenceManager.a));
        this.i0 = (bundle == null || !bundle.containsKey("password-check-referrer")) ? this.j.getInt("password-check-referrer") : bundle.getInt("password-check-referrer");
        D0();
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        this.H = true;
        this.h0.c();
        if (D().isFinishing()) {
            int i = this.i0;
            if (i == 2 || i == 3) {
                this.h0.destroy();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        return this.h0.b(menuItem);
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        this.h0.e();
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putInt("password-check-referrer", this.i0);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        this.h0.d();
    }
}
