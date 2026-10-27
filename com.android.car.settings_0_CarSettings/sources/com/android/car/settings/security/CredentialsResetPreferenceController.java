package com.android.car.settings.security;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class CredentialsResetPreferenceController extends PreferenceController<Preference> {
    private final UserManager mUserManager;

    public CredentialsResetPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return this.mUserManager.hasUserRestriction("no_config_credentials") ? 3 : 0;
    }
}
