package com.android.car.radio.bands;

import com.byd.music.R;

/* JADX INFO: loaded from: classes4.dex */
class AMProgramType extends AMFMProgramType {
    @Override // com.android.car.radio.bands.ProgramType
    public String getEnglishName() {
        return "AM";
    }

    @Override // com.android.car.radio.bands.AMFMProgramType
    protected int getLeadingDigitsFactor() {
        return 1;
    }

    AMProgramType(int i) {
        super(i);
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getLocalizedName() {
        return R.string.app_name;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getResourceId() {
        return R.drawable.ic_launcher_background;
    }
}
