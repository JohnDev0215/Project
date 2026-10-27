package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvbcFrontendCapabilities extends FrontendCapabilities {
    private final int mAnnexCap;
    private final int mFecCap;
    private final int mModulationCap;

    private DvbcFrontendCapabilities(int i, int i2, int i3) {
        this.mModulationCap = i;
        this.mFecCap = i2;
        this.mAnnexCap = i3;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }

    public int getFecCapability() {
        return this.mFecCap;
    }

    public int getAnnexCapability() {
        return this.mAnnexCap;
    }
}
