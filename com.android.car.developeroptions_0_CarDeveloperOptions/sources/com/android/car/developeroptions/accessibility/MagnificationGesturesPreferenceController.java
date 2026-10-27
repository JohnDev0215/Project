package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class MagnificationGesturesPreferenceController extends TogglePreferenceController {
    private boolean mIsFromSUW;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public MagnificationGesturesPreferenceController(Context context, String str) {
        super(context, str);
        this.mIsFromSUW = false;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return MagnificationPreferenceFragment.isChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_enabled");
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return MagnificationPreferenceFragment.setChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_enabled", z);
    }

    public void setIsFromSUW(boolean z) {
        this.mIsFromSUW = z;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Bundle extras = preference.getExtras();
        populateMagnificationGesturesPreferenceExtras(extras, this.mContext);
        extras.putBoolean("checked", isChecked());
        extras.putBoolean("from_suw", this.mIsFromSUW);
        return false;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "screen_magnification_gestures_preference_screen");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        int i;
        if (this.mIsFromSUW) {
            i = R.string.accessibility_screen_magnification_short_summary;
        } else {
            i = isChecked() ? R.string.accessibility_feature_state_on : R.string.accessibility_feature_state_off;
        }
        return this.mContext.getString(i);
    }

    static void populateMagnificationGesturesPreferenceExtras(Bundle bundle, Context context) {
        bundle.putString("preference_key", "accessibility_display_magnification_enabled");
        bundle.putInt("title_res", R.string.accessibility_screen_magnification_gestures_title);
        bundle.putInt("summary_res", R.string.accessibility_screen_magnification_summary);
        bundle.putInt("video_resource", R.raw.accessibility_screen_magnification);
    }
}
