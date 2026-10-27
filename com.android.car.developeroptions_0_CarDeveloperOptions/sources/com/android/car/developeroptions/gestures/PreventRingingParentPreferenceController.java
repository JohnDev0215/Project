package com.android.car.developeroptions.gestures;

import android.R;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class PreventRingingParentPreferenceController extends BasePreferenceController {
    final String SECURE_KEY;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public PreventRingingParentPreferenceController(Context context, String str) {
        super(context, str);
        this.SECURE_KEY = "volume_hush_gesture";
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_cecSoundbarModeDisabled_allowed) ? 1 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1);
        return this.mContext.getText(i != 1 ? i != 2 ? com.android.car.developeroptions.R.string.prevent_ringing_option_none_summary : com.android.car.developeroptions.R.string.prevent_ringing_option_mute_summary : com.android.car.developeroptions.R.string.prevent_ringing_option_vibrate_summary);
    }
}
