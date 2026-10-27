package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class voOSOutputConnectStatusImpl implements voOSOutputConnectStatus {
    boolean mConnect;
    voOSType.VOOSMP_OUTPUT_CONTROL_TYPE mType;

    @Override // com.visualon.OSMPUtils.voOSOutputConnectStatus
    public voOSType.VOOSMP_OUTPUT_CONTROL_TYPE getOutputConnectType() {
        return null;
    }

    @Override // com.visualon.OSMPUtils.voOSOutputConnectStatus
    public boolean isConnected() {
        return false;
    }

    public voOSOutputConnectStatusImpl(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type, boolean z) {
        this.mType = voosmp_output_control_type;
        this.mConnect = z;
    }
}
