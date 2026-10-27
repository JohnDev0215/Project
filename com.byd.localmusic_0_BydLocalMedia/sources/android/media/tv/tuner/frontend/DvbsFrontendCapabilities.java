package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvbsFrontendCapabilities extends FrontendCapabilities {
    private final long mInnerFecCap;
    private final int mModulationCap;
    private final int mStandard;

    private DvbsFrontendCapabilities(int i, long j, int i2) {
        this.mModulationCap = i;
        this.mInnerFecCap = j;
        this.mStandard = i2;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }

    public long getInnerFecCapability() {
        return this.mInnerFecCap;
    }

    public int getStandardCapability() {
        return this.mStandard;
    }
}
