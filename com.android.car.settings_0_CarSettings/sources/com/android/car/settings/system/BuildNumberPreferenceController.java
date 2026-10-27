package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.development.DevelopmentSettingsUtil;

/* JADX INFO: loaded from: classes5.dex */
public class BuildNumberPreferenceController extends PreferenceController<Preference> {
    private int mDevHitCountdown;
    private UserManager mUserManager;

    public BuildNumberPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onResumeInternal() {
        this.mDevHitCountdown = DevelopmentSettingsUtil.isDevelopmentSettingsEnabled(getContext(), this.mUserManager) ? -1 : getTapsToBecomeDeveloper();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(Build.DISPLAY);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        if (!this.mUserManager.isAdminUser() || !DevelopmentSettingsUtil.isDeviceProvisioned(getContext())) {
            return false;
        }
        int i = this.mDevHitCountdown;
        if (i > 0) {
            int i2 = i - 1;
            this.mDevHitCountdown = i2;
            if (i2 == 0) {
                DevelopmentSettingsUtil.setDevelopmentSettingsEnabled(getContext(), true);
            } else {
                int tapsToBecomeDeveloper = getTapsToBecomeDeveloper() - getTapsToShowToast();
            }
        }
        return true;
    }

    private int getTapsToBecomeDeveloper() {
        return getContext().getResources().getInteger(R.integer.enable_developer_settings_click_count);
    }

    private int getTapsToShowToast() {
        return getContext().getResources().getInteger(R.integer.enable_developer_settings_clicks_to_show_toast_count);
    }
}
