package com.android.car.radio.bands;

import android.hardware.radio.ProgramSelector;
import com.android.car.radio.platform.RadioTunerExt;
import com.byd.music.R;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes4.dex */
class DABProgramType extends ProgramType {
    private static final String TAG = "BcRadioApp.ProgramType";

    @Override // com.android.car.radio.bands.ProgramType
    public String getEnglishName() {
        return "DAB";
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getResourceId() {
        return 0;
    }

    DABProgramType(int i) {
        super(i);
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getLocalizedName() {
        return R.string.app_name;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public void tuneToDefault(RadioTunerExt radioTunerExt, RegionConfig regionConfig, RadioTunerExt.TuneCallback tuneCallback) {
        LogUtil.e(TAG, "Tunning to a default DAB channel is not supported yet");
    }

    @Override // com.android.car.radio.bands.ProgramType
    public boolean isComplete(RegionConfig regionConfig, int i) {
        LogUtil.e(TAG, "Manual entry of DAB channels is not supported");
        return false;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public boolean[] getValidAppendices(RegionConfig regionConfig, int i) {
        throw new UnsupportedOperationException("Manual entry of DAB channels is not supported");
    }

    @Override // com.android.car.radio.bands.ProgramType
    public ProgramSelector parseDigits(float f) {
        throw new UnsupportedOperationException("Manual entry of DAB channels is not supported");
    }
}
