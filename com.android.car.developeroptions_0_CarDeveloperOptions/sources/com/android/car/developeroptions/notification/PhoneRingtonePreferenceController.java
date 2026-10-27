package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.Utils;

/* JADX INFO: loaded from: classes.dex */
public class PhoneRingtonePreferenceController extends RingtonePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "ringtone";
    }

    @Override // com.android.car.developeroptions.notification.RingtonePreferenceControllerBase
    public int getRingtoneType() {
        return 1;
    }

    public PhoneRingtonePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.notification.RingtonePreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Utils.isVoiceCapable(this.mContext);
    }
}
