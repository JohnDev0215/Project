package com.android.car.developeroptions.notification;

import android.content.Context;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class MediaVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private static final String KEY_MEDIA_VOLUME = "media_volume";

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return 3;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return R.drawable.ic_media_stream_off;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_MEDIA_VOLUME;
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

    public MediaVolumePreferenceController(Context context) {
        super(context, KEY_MEDIA_VOLUME);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_media_volume) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_MEDIA_VOLUME);
    }
}
