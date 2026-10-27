package com.android.car.developeroptions.notification;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class CallVolumePreferenceController extends VolumeSeekBarPreferenceController {
    private AudioManager mAudioManager;

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getMuteIcon() {
        return R.drawable.ic_local_phone_24_lib;
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

    public CallVolumePreferenceController(Context context, String str) {
        super(context, str);
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(R.bool.config_show_call_volume) || ((VolumeSeekBarPreferenceController) this).mHelper.isSingleVolume()) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController, com.android.car.developeroptions.notification.AdjustVolumeRestrictedPreferenceController, com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "call_volume");
    }

    @Override // com.android.car.developeroptions.notification.VolumeSeekBarPreferenceController
    public int getAudioStream() {
        return this.mAudioManager.isBluetoothScoOn() ? 6 : 0;
    }
}
