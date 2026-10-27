package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyTonePreferenceController extends SettingPrefController {
    public EmergencyTonePreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mPreference = new SettingPref(this, 1, "emergency_tone", "emergency_tone", 0, 1, 2, 0) { // from class: com.android.car.developeroptions.notification.EmergencyTonePreferenceController.1
            @Override // com.android.car.developeroptions.notification.SettingPref
            public boolean isApplicable(Context context2) {
                TelephonyManager telephonyManager = (TelephonyManager) context2.getSystemService("phone");
                return telephonyManager != null && telephonyManager.getCurrentPhoneType() == 2;
            }

            @Override // com.android.car.developeroptions.notification.SettingPref
            protected String getCaption(Resources resources, int i) {
                if (i == 0) {
                    return resources.getString(R.string.emergency_tone_silent);
                }
                if (i == 1) {
                    return resources.getString(R.string.emergency_tone_alert);
                }
                if (i == 2) {
                    return resources.getString(R.string.emergency_tone_vibrate);
                }
                throw new IllegalArgumentException();
            }
        };
    }
}
