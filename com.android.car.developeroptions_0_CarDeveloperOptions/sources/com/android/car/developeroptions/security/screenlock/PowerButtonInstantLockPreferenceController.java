package com.android.car.developeroptions.security.screenlock;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class PowerButtonInstantLockPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final LockPatternUtils mLockPatternUtils;
    private final int mUserId;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "power_button_instantly_locks";
    }

    public PowerButtonInstantLockPreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context);
        this.mUserId = i;
        this.mLockPatternUtils = lockPatternUtils;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (!this.mLockPatternUtils.isSecure(this.mUserId)) {
            return false;
        }
        int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId);
        return keyguardStoredPasswordQuality == 65536 || keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608 || keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((TwoStatePreference) preference).setChecked(this.mLockPatternUtils.getPowerButtonInstantlyLocks(this.mUserId));
        preference.setSummary(R.string.summary_placeholder);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mLockPatternUtils.setPowerButtonInstantlyLocks(((Boolean) obj).booleanValue(), this.mUserId);
        return true;
    }
}
