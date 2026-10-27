package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSConnectPortInfoImpl implements voOSConnectPortInfo {
    int mAudioConnectPortIndex;
    int mVideoConnectPortIndex;

    public voOSConnectPortInfoImpl(int i, int i2) {
        this.mAudioConnectPortIndex = i;
        this.mVideoConnectPortIndex = i2;
    }

    @Override // com.visualon.OSMPUtils.voOSConnectPortInfo
    public int AudioConnectPort() {
        return this.mAudioConnectPortIndex;
    }

    @Override // com.visualon.OSMPUtils.voOSConnectPortInfo
    public int VideoConnectPort() {
        return this.mVideoConnectPortIndex;
    }
}
