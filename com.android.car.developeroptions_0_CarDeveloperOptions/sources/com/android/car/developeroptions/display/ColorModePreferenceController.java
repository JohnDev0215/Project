package com.android.car.developeroptions.display;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.ColorDisplayManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class ColorModePreferenceController extends BasePreferenceController {
    private ColorDisplayManager mColorDisplayManager;

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

    public ColorModePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!((ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class)).isDeviceColorManaged() || ColorDisplayManager.areAccessibilityTransformsEnabled(this.mContext)) ? 4 : 1;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        int colorMode = getColorDisplayManager().getColorMode();
        if (colorMode == 3) {
            return this.mContext.getText(R.string.color_mode_option_automatic);
        }
        if (colorMode == 2) {
            return this.mContext.getText(R.string.color_mode_option_saturated);
        }
        if (colorMode == 1) {
            return this.mContext.getText(R.string.color_mode_option_boosted);
        }
        return this.mContext.getText(R.string.color_mode_option_natural);
    }

    ColorDisplayManager getColorDisplayManager() {
        if (this.mColorDisplayManager == null) {
            this.mColorDisplayManager = (ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class);
        }
        return this.mColorDisplayManager;
    }
}
