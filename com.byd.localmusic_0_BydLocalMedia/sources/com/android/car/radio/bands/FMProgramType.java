package com.android.car.radio.bands;

import android.media.MediaMetrics;
import com.byd.music.R;

/* JADX INFO: loaded from: classes4.dex */
class FMProgramType extends AMFMProgramType {
    @Override // com.android.car.radio.bands.ProgramType
    public String getEnglishName() {
        return "FM";
    }

    @Override // com.android.car.radio.bands.AMFMProgramType
    protected int getLeadingDigitsFactor() {
        return 100;
    }

    FMProgramType(int i) {
        super(i);
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getLocalizedName() {
        return R.string.app_name;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public int getResourceId() {
        return R.drawable.ic_launcher_foreground;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public String format(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i == 0) {
            return "";
        }
        String string = Integer.toString(i);
        int i2 = string.charAt(0) == '1' ? 3 : 2;
        if (string.length() < i2) {
            return string;
        }
        return string.substring(0, i2) + MediaMetrics.SEPARATOR + string.substring(i2, string.length());
    }
}
