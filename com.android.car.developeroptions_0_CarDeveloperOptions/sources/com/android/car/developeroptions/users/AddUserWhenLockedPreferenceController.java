package com.android.car.developeroptions.users;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class AddUserWhenLockedPreferenceController extends TogglePreferenceController {
    private final UserCapabilities mUserCaps;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AddUserWhenLockedPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserCaps = UserCapabilities.create(context);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mUserCaps.updateAddUserCapabilities(this.mContext);
        RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
        if (!isAvailable()) {
            restrictedSwitchPreference.setVisible(false);
        } else {
            restrictedSwitchPreference.setDisabledByAdmin(this.mUserCaps.disallowAddUser() ? this.mUserCaps.getEnforcedAdmin() : null);
            restrictedSwitchPreference.setVisible(this.mUserCaps.mUserSwitcherEnabled);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!this.mUserCaps.isAdmin() || this.mUserCaps.disallowAddUser() || this.mUserCaps.disallowAddUserSetByAdmin()) {
            return 4;
        }
        return this.mUserCaps.mUserSwitcherEnabled ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "add_users_when_locked", 0) == 1;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.Global.putInt(this.mContext.getContentResolver(), "add_users_when_locked", z ? 1 : 0);
    }
}
