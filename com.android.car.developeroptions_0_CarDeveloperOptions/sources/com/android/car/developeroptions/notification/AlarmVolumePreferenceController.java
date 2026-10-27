package com.android.car.developeroptions.notification;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class AlarmVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_ALARM_VOLUME = "alarm_volume";

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return 4;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return R.drawable.dropdown_ic_arrow_disabled_focused_holo_light;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_ALARM_VOLUME;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public AlarmVolumePreferenceController(Context context) {
        super(context, KEY_ALARM_VOLUME);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(com.android.car.developeroptions.R.bool.config_show_alarm_volume) || ((VolumeSeekBarPreferenceController) this).mHelper.isSingleVolume()) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_ALARM_VOLUME);
    }
}
