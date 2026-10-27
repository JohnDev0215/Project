package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class Isdbs3FrontendCapabilities extends FrontendCapabilities {
    private final int mCodeRateCap;
    private final int mModulationCap;

    private Isdbs3FrontendCapabilities(int i, int i2) {
        this.mModulationCap = i;
        this.mCodeRateCap = i2;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }

    public int getCodeRateCapability() {
        return this.mCodeRateCap;
    }
}
