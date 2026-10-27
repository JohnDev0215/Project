package com.android.car.developeroptions.gestures;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class TapScreenGesturePreferenceController extends GesturePreferenceController {
    private static final String PREF_KEY_VIDEO = "gesture_tap_screen_video";
    private AmbientDisplayConfiguration mAmbientConfig;
    private final int mUserId;

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController
    protected String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TapScreenGesturePreferenceController(Context context, String str) {
        super(context, str);
        this.mUserId = UserHandle.myUserId();
    }

    public TapScreenGesturePreferenceController setConfig(AmbientDisplayConfiguration ambientDisplayConfiguration) {
        this.mAmbientConfig = ambientDisplayConfiguration;
        return this;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return !getAmbientConfig().tapSensorAvailable() ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.gestures.GesturePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return super.getSummary();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return getAmbientConfig().tapGestureEnabled(this.mUserId);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "doze_tap_gesture", z ? 1 : 0);
    }

    private AmbientDisplayConfiguration getAmbientConfig() {
        if (this.mAmbientConfig == null) {
            this.mAmbientConfig = new AmbientDisplayConfiguration(this.mContext);
        }
        return this.mAmbientConfig;
    }
}
