package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class Atsc3PlpInfo {
    private final boolean mLlsFlag;
    private final int mPlpId;

    private Atsc3PlpInfo(int i, boolean z) {
        this.mPlpId = i;
        this.mLlsFlag = z;
    }

    public int getPlpId() {
        return this.mPlpId;
    }

    public boolean getLlsFlag() {
        return this.mLlsFlag;
    }
}
