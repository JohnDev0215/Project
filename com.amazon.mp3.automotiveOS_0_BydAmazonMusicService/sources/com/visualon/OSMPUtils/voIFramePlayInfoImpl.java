package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voIFramePlayInfoImpl implements voIFramePlayInfo {
    private static final String TAG = "@@@voIFramePlayInfoImpl";
    private boolean mEnable;
    private float mSpeedRate;

    public voIFramePlayInfoImpl(boolean z, float f) {
        this.mEnable = z;
        this.mSpeedRate = f;
    }

    @Override // com.visualon.OSMPUtils.voIFramePlayInfo
    public boolean isEnable() {
        return this.mEnable;
    }

    @Override // com.visualon.OSMPUtils.voIFramePlayInfo
    public float getSpeedRate() {
        return this.mSpeedRate;
    }

    @Override // com.visualon.OSMPUtils.voIFramePlayInfo
    public void setEnable(boolean z) {
        this.mEnable = z;
    }

    @Override // com.visualon.OSMPUtils.voIFramePlayInfo
    public void setSpeedRate(float f) {
        this.mSpeedRate = f;
    }
}
