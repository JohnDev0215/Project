package com.android.car.settings.security;

import androidx.fragment.app.Fragment;
import com.android.car.settings.common.BaseCarSettingsActivity;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public class CheckLockActivity extends BaseCarSettingsActivity implements CheckLockListener {
    @Override // com.android.car.settings.common.BaseCarSettingsActivity
    protected Fragment getInitialFragment() {
        Fragment lockFragment = ConfirmPasswordFragmentFactory.getFragment(this);
        if (lockFragment == null) {
            setResult(-1);
            finish();
        }
        return lockFragment;
    }

    @Override // com.android.car.settings.security.CheckLockListener
    public void onLockVerified(LockscreenCredential lock) {
        setResult(-1);
        finish();
    }

    @Override // com.android.car.settings.common.BaseCarSettingsActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        PasswordHelper.zeroizeCredentials(new LockscreenCredential[0]);
    }
}
