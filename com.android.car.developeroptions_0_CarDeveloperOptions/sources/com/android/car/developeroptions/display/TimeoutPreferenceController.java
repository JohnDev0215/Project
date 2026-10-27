package com.android.car.developeroptions.display;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class TimeoutPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final String mScreenTimeoutKey;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public TimeoutPreferenceController(Context context, String str) {
        super(context);
        this.mScreenTimeoutKey = str;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mScreenTimeoutKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        TimeoutListPreference timeoutListPreference = (TimeoutListPreference) preference;
        timeoutListPreference.setValue(String.valueOf(Settings.System.getLong(this.mContext.getContentResolver(), "screen_off_timeout", 30000L)));
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        if (devicePolicyManager != null) {
            timeoutListPreference.removeUnusableTimeouts(devicePolicyManager.getMaximumTimeToLock(null, UserHandle.myUserId()), RestrictedLockUtilsInternal.checkIfMaximumTimeToLockIsSet(this.mContext));
        }
        updateTimeoutPreferenceDescription(timeoutListPreference, Long.parseLong(timeoutListPreference.getValue()));
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_config_screen_timeout", UserHandle.myUserId());
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            timeoutListPreference.removeUnusableTimeouts(0L, enforcedAdminCheckIfRestrictionEnforced);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        try {
            int i = Integer.parseInt((String) obj);
            Settings.System.putInt(this.mContext.getContentResolver(), "screen_off_timeout", i);
            updateTimeoutPreferenceDescription((TimeoutListPreference) preference, i);
            return true;
        } catch (NumberFormatException e) {
            Log.e("TimeoutPrefContr", "could not persist screen timeout setting", e);
            return true;
        }
    }

    public static CharSequence getTimeoutDescription(long j, CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2) {
        if (j >= 0 && charSequenceArr != null && charSequenceArr2 != null && charSequenceArr2.length == charSequenceArr.length) {
            for (int i = 0; i < charSequenceArr2.length; i++) {
                if (j == Long.parseLong(charSequenceArr2[i].toString())) {
                    return charSequenceArr[i];
                }
            }
        }
        return null;
    }

    private void updateTimeoutPreferenceDescription(TimeoutListPreference timeoutListPreference, long j) {
        String string;
        CharSequence[] entries = timeoutListPreference.getEntries();
        CharSequence[] entryValues = timeoutListPreference.getEntryValues();
        if (timeoutListPreference.isDisabledByAdmin()) {
            string = this.mContext.getString(R.string.disabled_by_policy_title);
        } else {
            CharSequence timeoutDescription = getTimeoutDescription(j, entries, entryValues);
            string = timeoutDescription == null ? "" : this.mContext.getString(R.string.screen_timeout_summary, timeoutDescription);
        }
        timeoutListPreference.setSummary(string);
    }
}
