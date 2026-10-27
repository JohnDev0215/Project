package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.development.DevelopmentSettingsUtil;

/* JADX INFO: loaded from: classes5.dex */
public class DeveloperOptionsEntryPreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) DeveloperOptionsEntryPreferenceController.class);
    private UserManager mUserManager;

    public DeveloperOptionsEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return DevelopmentSettingsUtil.isDevelopmentSettingsEnabled(getContext(), this.mUserManager) ? 0 : 1;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        if (!DevelopmentSettingsUtil.isDeveloperOptionsModuleEnabled(getContext())) {
            LOG.i("Inconsistent state: developer options enabled, but developer options module disabled. Enabling module...");
            DevelopmentSettingsUtil.setDevelopmentSettingsEnabled(getContext(), true);
        }
        getContext().startActivity(preference.getIntent());
        return true;
    }
}
