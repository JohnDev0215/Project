package com.android.car.settings.security;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class PinLockPreferenceController extends LockTypeBasePreferenceController {
    private static final int[] ALLOWED_PASSWORD_QUALITIES = {131072, 196608};

    public PinLockPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected Fragment fragmentToOpen() {
        return ChooseLockPinPasswordFragment.newPinInstance();
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected int[] allowedPasswordQualities() {
        return ALLOWED_PASSWORD_QUALITIES;
    }
}
