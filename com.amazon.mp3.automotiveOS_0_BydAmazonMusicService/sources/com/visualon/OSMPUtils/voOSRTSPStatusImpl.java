package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class voOSRTSPStatusImpl implements voOSRTSPStatus {
    voOSType.VOOSMP_SOURCE_STREAMTYPE nTrackType;
    int uAverageJitter;
    int uAverageLatency;
    int uPacketDuplicated;
    int uPacketLost;
    int uPacketRecved;
    int uPacketSent;

    public voOSRTSPStatusImpl() {
    }

    public voOSRTSPStatusImpl(voOSType.VOOSMP_SOURCE_STREAMTYPE voosmp_source_streamtype, int i, int i2, int i3, int i4, int i5, int i6) {
        this.nTrackType = voosmp_source_streamtype;
        this.uPacketRecved = i;
        this.uPacketDuplicated = i2;
        this.uPacketLost = i3;
        this.uPacketSent = i4;
        this.uAverageJitter = i5;
        this.uAverageLatency = i6;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType() {
        return this.nTrackType;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getPacketRecved() {
        return this.uPacketRecved;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getPacketDuplicated() {
        return this.uPacketDuplicated;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getPacketLost() {
        return this.uPacketLost;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getPacketSent() {
        return this.uPacketSent;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getAverageJitter() {
        return this.uAverageJitter;
    }

    @Override // com.visualon.OSMPUtils.voOSRTSPStatus
    public int getAverageLatency() {
        return this.uAverageLatency;
    }
}
