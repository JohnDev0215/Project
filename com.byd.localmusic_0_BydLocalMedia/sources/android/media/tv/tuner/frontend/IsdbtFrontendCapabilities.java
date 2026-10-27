package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class IsdbtFrontendCapabilities extends FrontendCapabilities {
    private final int mBandwidthCap;
    private final int mCodeRateCap;
    private final int mGuardIntervalCap;
    private final int mModeCap;
    private final int mModulationCap;

    private IsdbtFrontendCapabilities(int i, int i2, int i3, int i4, int i5) {
        this.mModeCap = i;
        this.mBandwidthCap = i2;
        this.mModulationCap = i3;
        this.mCodeRateCap = i4;
        this.mGuardIntervalCap = i5;
    }

    public int getModeCapability() {
        return this.mModeCap;
    }

    public int getBandwidthCapability() {
        return this.mBandwidthCap;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }

    public int getCodeRateCapability() {
        return this.mCodeRateCap;
    }

    public int getGuardIntervalCapability() {
        return this.mGuardIntervalCap;
    }
}
