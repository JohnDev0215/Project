package com.android.car.developeroptions.biometrics.face;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.VideoPreference;
import com.android.car.developeroptions.widget.VideoPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class FaceSettingsVideoPreferenceController extends VideoPreferenceController {
    private static final String KEY_VIDEO = "security_settings_face_video";
    private VideoPreference mVideoPreference;

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FaceSettingsVideoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public FaceSettingsVideoPreferenceController(Context context) {
        this(context, KEY_VIDEO);
    }

    @Override // com.android.car.developeroptions.widget.VideoPreferenceController, com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        VideoPreference videoPreference = (VideoPreference) preferenceScreen.findPreference(KEY_VIDEO);
        this.mVideoPreference = videoPreference;
        videoPreference.onViewVisible(false);
    }
}
