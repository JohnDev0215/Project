package com.android.car.settings.security;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.Logger;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmPasswordFragmentFactory {
    private static final Logger LOG = new Logger((Class<?>) ConfirmPasswordFragmentFactory.class);

    public static Fragment getFragment(Context context) {
        Fragment fragment;
        int passwordQuality = new LockPatternUtils(context).getKeyguardStoredPasswordQuality(UserHandle.myUserId());
        if (passwordQuality == 0) {
            return null;
        }
        if (passwordQuality == 65536) {
            fragment = new ConfirmLockPatternFragment();
        } else if (passwordQuality == 131072 || passwordQuality == 196608) {
            fragment = ConfirmLockPinPasswordFragment.newPinInstance();
        } else if (passwordQuality == 262144 || passwordQuality == 327680) {
            fragment = ConfirmLockPinPasswordFragment.newPasswordInstance();
        } else {
            LOG.e("Unexpected password quality: " + passwordQuality);
            fragment = ConfirmLockPinPasswordFragment.newPasswordInstance();
        }
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY, passwordQuality);
        fragment.setArguments(bundle);
        return fragment;
    }
}
