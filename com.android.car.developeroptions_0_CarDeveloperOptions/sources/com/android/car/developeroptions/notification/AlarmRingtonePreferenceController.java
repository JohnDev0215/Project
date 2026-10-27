package com.android.car.developeroptions.notification;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AlarmRingtonePreferenceController extends RingtonePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "alarm_ringtone";
    }

    @Override // com.android.car.developeroptions.notification.RingtonePreferenceControllerBase
    public int getRingtoneType() {
        return 4;
    }

    public AlarmRingtonePreferenceController(Context context) {
        super(context);
    }
}
