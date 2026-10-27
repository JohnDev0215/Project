package com.android.car.developeroptions.location;

import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class LocationForWorkPreferenceController extends LocationBasePreferenceController {
    private RestrictedSwitchPreference mPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "managed_profile_location_switch";
    }

    public LocationForWorkPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"managed_profile_location_switch".equals(preference.getKey())) {
            return false;
        }
        boolean zIsChecked = this.mPreference.isChecked();
        UserManager userManager = this.mUserManager;
        userManager.setUserRestriction("no_share_location", !zIsChecked, Utils.getManagedProfile(userManager));
        this.mPreference.setSummary(zIsChecked ? R.string.switch_on_text : R.string.switch_off_text);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference("managed_profile_location_switch");
    }

    @Override // com.android.car.developeroptions.location.LocationBasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Utils.getManagedProfile(this.mUserManager) != null;
    }

    @Override // com.android.car.developeroptions.location.LocationEnabler.LocationModeChangeListener
    public void onLocationModeChanged(int i, boolean z) {
        if (this.mPreference.isVisible() && isAvailable()) {
            RestrictedLockUtils.EnforcedAdmin shareLocationEnforcedAdmin = this.mLocationEnabler.getShareLocationEnforcedAdmin(Utils.getManagedProfile(this.mUserManager).getIdentifier());
            boolean zIsManagedProfileRestrictedByBase = this.mLocationEnabler.isManagedProfileRestrictedByBase();
            if (!zIsManagedProfileRestrictedByBase && shareLocationEnforcedAdmin != null) {
                this.mPreference.setDisabledByAdmin(shareLocationEnforcedAdmin);
                this.mPreference.setChecked(false);
                return;
            }
            boolean zIsEnabled = this.mLocationEnabler.isEnabled(i);
            this.mPreference.setEnabled(zIsEnabled);
            int i2 = R.string.switch_off_text;
            if (!zIsEnabled) {
                this.mPreference.setChecked(false);
            } else {
                this.mPreference.setChecked(!zIsManagedProfileRestrictedByBase);
                if (!zIsManagedProfileRestrictedByBase) {
                    i2 = R.string.switch_on_text;
                }
            }
            this.mPreference.setSummary(i2);
        }
    }
}
