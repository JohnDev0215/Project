package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearEntryPreferenceController extends PreferenceController<Preference> {
    private final UserManager mUserManager;

    public MasterClearEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return isUserRestricted() ? 3 : 0;
    }

    private boolean isUserRestricted() {
        return !(this.mUserManager.isAdminUser() || isDemoUser()) || this.mUserManager.hasUserRestriction("no_factory_reset");
    }

    private boolean isDemoUser() {
        return UserManager.isDeviceInDemoMode(getContext()) && this.mUserManager.isDemoUser();
    }
}
