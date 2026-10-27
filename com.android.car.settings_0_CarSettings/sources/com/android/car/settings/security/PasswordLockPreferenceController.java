package com.android.car.settings.security;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class PasswordLockPreferenceController extends LockTypeBasePreferenceController {
    private static final int[] ALLOWED_PASSWORD_QUALITIES = {262144, 327680};

    public PasswordLockPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected Fragment fragmentToOpen() {
        return ChooseLockPinPasswordFragment.newPasswordInstance();
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected int[] allowedPasswordQualities() {
        return ALLOWED_PASSWORD_QUALITIES;
    }
}
