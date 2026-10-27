package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeDurationPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_duration_settings";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeDurationPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_duration_settings", lifecycle);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        int zenDuration = getZenDuration();
        if (zenDuration < 0) {
            return this.mContext.getString(R.string.zen_mode_duration_summary_always_prompt);
        }
        if (zenDuration == 0) {
            return this.mContext.getString(R.string.zen_mode_duration_summary_forever);
        }
        if (zenDuration >= 60) {
            int i = zenDuration / 60;
            return this.mContext.getResources().getQuantityString(R.plurals.zen_mode_duration_summary_time_hours, i, Integer.valueOf(i));
        }
        return this.mContext.getResources().getString(R.string.zen_mode_duration_summary_time_minutes, Integer.valueOf(zenDuration));
    }
}
