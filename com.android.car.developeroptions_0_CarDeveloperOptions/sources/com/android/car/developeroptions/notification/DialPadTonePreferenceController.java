package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class DialPadTonePreferenceController extends SettingPrefController {
    public DialPadTonePreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mPreference = new SettingPref(this, 2, "dial_pad_tones", "dtmf_tone", 1, new int[0]) { // from class: com.android.car.developeroptions.notification.DialPadTonePreferenceController.1
            @Override // com.android.car.developeroptions.notification.SettingPref
            public boolean isApplicable(Context context2) {
                return Utils.isVoiceCapable(context2);
            }
        };
    }
}
