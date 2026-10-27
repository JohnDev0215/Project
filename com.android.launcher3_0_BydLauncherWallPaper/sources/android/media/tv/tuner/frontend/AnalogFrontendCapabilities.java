package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AnalogFrontendCapabilities extends FrontendCapabilities {
    private final int mSifStandardCap;
    private final int mTypeCap;

    private AnalogFrontendCapabilities(int i, int i2) {
        this.mTypeCap = i;
        this.mSifStandardCap = i2;
    }

    public int getSignalTypeCapability() {
        return this.mTypeCap;
    }

    public int getSifStandardCapability() {
        return this.mSifStandardCap;
    }
}
