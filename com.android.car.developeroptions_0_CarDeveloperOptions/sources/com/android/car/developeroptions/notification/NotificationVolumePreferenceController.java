package com.android.car.developeroptions.notification;

import android.content.Context;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class NotificationVolumePreferenceController extends RingVolumePreferenceController {
    private static final String KEY_NOTIFICATION_VOLUME = "notification_volume";

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return 5;
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return R.drawable.ic_notifications_off_24dp;
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_NOTIFICATION_VOLUME;
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    public NotificationVolumePreferenceController(Context context) {
        super(context, KEY_NOTIFICATION_VOLUME);
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(R.bool.config_show_notification_volume) || Utils.isVoiceCapable(this.mContext) || ((VolumeSeekBarPreferenceController) this).mHelper.isSingleVolume()) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.notification.RingVolumePreferenceController, com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_NOTIFICATION_VOLUME);
    }
}
