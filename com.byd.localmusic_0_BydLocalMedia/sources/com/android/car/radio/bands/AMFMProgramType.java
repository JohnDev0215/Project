package com.android.car.radio.bands;

import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.os.UserHandle;
import com.airbnb.lottie.utils.Utils;
import com.android.car.broadcastradio.support.platform.ProgramSelectorExt;
import com.android.car.radio.platform.RadioTunerExt;
import com.byd.music.utils.LogUtil;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
abstract class AMFMProgramType extends ProgramType {
    private static final String TAG = "BcRadioApp.ProgramType";

    protected abstract int getLeadingDigitsFactor();

    AMFMProgramType(int i) {
        super(i);
    }

    private List<RadioManager.BandDescriptor> getBands(RegionConfig regionConfig) {
        return this == ProgramType.AM ? regionConfig.getAmConfig() : regionConfig.getFmConfig();
    }

    @Override // com.android.car.radio.bands.ProgramType
    public void tuneToDefault(final RadioTunerExt radioTunerExt, RegionConfig regionConfig, final RadioTunerExt.TuneCallback tuneCallback) {
        List<RadioManager.BandDescriptor> bands = getBands(regionConfig);
        if (bands.size() == 0) {
            LogUtil.e(TAG, "No " + getEnglishName() + " bands provided by the hardware");
            return;
        }
        RadioManager.BandDescriptor bandDescriptor = bands.get(new Random().nextInt(bands.size()));
        radioTunerExt.tune(ProgramSelectorExt.createAmFmSelector(((r0.nextInt(bandDescriptor.getUpperLimit() - bandDescriptor.getLowerLimit()) + bandDescriptor.getLowerLimit()) / bandDescriptor.getSpacing()) * bandDescriptor.getSpacing()), new RadioTunerExt.TuneCallback() { // from class: com.android.car.radio.bands.-$$Lambda$AMFMProgramType$zlBk12VV-G2n0rJtHYKpMg64zIA
            @Override // com.android.car.radio.platform.RadioTunerExt.TuneCallback
            public final void onFinished(boolean z) {
                AMFMProgramType.lambda$tuneToDefault$0(tuneCallback, radioTunerExt, z);
            }
        });
    }

    static /* synthetic */ void lambda$tuneToDefault$0(RadioTunerExt.TuneCallback tuneCallback, RadioTunerExt radioTunerExt, boolean z) {
        if (!z) {
            tuneCallback.onFinished(false);
        } else {
            radioTunerExt.seek(true, tuneCallback);
        }
    }

    @Override // com.android.car.radio.bands.ProgramType
    public boolean isComplete(RegionConfig regionConfig, int i) {
        int leadingDigitsFactor = i * getLeadingDigitsFactor();
        for (RadioManager.BandDescriptor bandDescriptor : getBands(regionConfig)) {
            if (bandDescriptor.getLowerLimit() <= leadingDigitsFactor && leadingDigitsFactor <= bandDescriptor.getUpperLimit() && (leadingDigitsFactor - bandDescriptor.getLowerLimit()) % bandDescriptor.getSpacing() == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public ProgramSelector parseDigits(float f) {
        return ProgramSelectorExt.createAmFmSelector((long) (f * getLeadingDigitsFactor()));
    }

    private static int numLength(int i) {
        if (i == 0) {
            return 0;
        }
        return ((int) Math.floor(Math.log10(i))) + 1;
    }

    private static int divCeil(int i, int i2) {
        return ((i - 1) / i2) + 1;
    }

    @Override // com.android.car.radio.bands.ProgramType
    public boolean[] getValidAppendices(RegionConfig regionConfig, int i) {
        boolean[] zArr = new boolean[10];
        int leadingDigitsFactor = getLeadingDigitsFactor();
        int iNumLength = numLength(i);
        int[] iArr = {1, 10, 100, 1000, 10000, UserHandle.PER_USER_RANGE, 1000000, 10000000, 100000000, Utils.SECOND_IN_NANOS};
        for (RadioManager.BandDescriptor bandDescriptor : getBands(regionConfig)) {
            int lowerLimit = bandDescriptor.getLowerLimit();
            int upperLimit = bandDescriptor.getUpperLimit();
            int spacing = bandDescriptor.getSpacing();
            int iNumLength2 = numLength(lowerLimit / leadingDigitsFactor);
            int iNumLength3 = numLength(upperLimit / leadingDigitsFactor);
            int iMax = Math.max(iNumLength + 1, iNumLength2);
            while (iMax <= iNumLength3) {
                int i2 = iMax - iNumLength;
                int i3 = iArr[i2];
                int i4 = iArr[i2 - 1];
                int i5 = i4 * leadingDigitsFactor;
                int iMax2 = Math.max((i5 / spacing) * spacing, spacing);
                int[] iArr2 = iArr;
                int iMin = Math.min((((i + 1) * i3) * leadingDigitsFactor) - 1, upperLimit);
                for (int iDivCeil = (divCeil(Math.max(0, Math.max((i * i3) * leadingDigitsFactor, i5) - lowerLimit), spacing) * spacing) + lowerLimit; iDivCeil <= iMin; iDivCeil += iMax2) {
                    zArr[((iDivCeil / leadingDigitsFactor) % i3) / i4] = true;
                }
                iMax++;
                iArr = iArr2;
            }
        }
        return zArr;
    }
}
