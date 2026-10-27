package com.android.car.developeroptions.security;

import android.content.Context;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.users.OwnerInfoSettings;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class OwnerInfoPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private final LockPatternUtils mLockPatternUtils;
    private RestrictedPreference mOwnerInfoPref;
    private final Fragment mParent;

    public interface OwnerInfoCallback {
        void onOwnerInfoUpdated();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "owner_info_settings";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public OwnerInfoPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context);
        this.mParent = fragment;
        this.mLockPatternUtils = new LockPatternUtils(context);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mOwnerInfoPref = (RestrictedPreference) preferenceScreen.findPreference("owner_info_settings");
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        updateEnableState();
        updateSummary();
    }

    public void updateEnableState() {
        if (this.mOwnerInfoPref == null) {
            return;
        }
        if (isDeviceOwnerInfoEnabled()) {
            this.mOwnerInfoPref.setDisabledByAdmin(getDeviceOwner());
            return;
        }
        this.mOwnerInfoPref.setDisabledByAdmin(null);
        this.mOwnerInfoPref.setEnabled(!this.mLockPatternUtils.isLockScreenDisabled(MY_USER_ID));
        if (this.mOwnerInfoPref.isEnabled()) {
            this.mOwnerInfoPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.security.OwnerInfoPreferenceController.1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    OwnerInfoSettings.show(OwnerInfoPreferenceController.this.mParent);
                    return true;
                }
            });
        }
    }

    public void updateSummary() {
        String string;
        if (this.mOwnerInfoPref != null) {
            if (isDeviceOwnerInfoEnabled()) {
                this.mOwnerInfoPref.setSummary(getDeviceOwnerInfo());
                return;
            }
            RestrictedPreference restrictedPreference = this.mOwnerInfoPref;
            if (isOwnerInfoEnabled()) {
                string = getOwnerInfo();
            } else {
                string = this.mContext.getString(R.string.owner_info_settings_summary);
            }
            restrictedPreference.setSummary(string);
        }
    }

    boolean isDeviceOwnerInfoEnabled() {
        return this.mLockPatternUtils.isDeviceOwnerInfoEnabled();
    }

    String getDeviceOwnerInfo() {
        return this.mLockPatternUtils.getDeviceOwnerInfo();
    }

    boolean isOwnerInfoEnabled() {
        return this.mLockPatternUtils.isOwnerInfoEnabled(MY_USER_ID);
    }

    String getOwnerInfo() {
        return this.mLockPatternUtils.getOwnerInfo(MY_USER_ID);
    }

    RestrictedLockUtils.EnforcedAdmin getDeviceOwner() {
        return RestrictedLockUtilsInternal.getDeviceOwner(this.mContext);
    }
}
