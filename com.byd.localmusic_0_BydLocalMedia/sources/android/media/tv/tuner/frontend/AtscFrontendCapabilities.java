package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AtscFrontendCapabilities extends FrontendCapabilities {
    private final int mModulationCap;

    private AtscFrontendCapabilities(int i) {
        this.mModulationCap = i;
    }

    public int getModulationCapability() {
        return this.mModulationCap;
    }
}
