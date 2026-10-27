package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSRTSPStatus {
    int getAverageJitter();

    int getAverageLatency();

    int getPacketDuplicated();

    int getPacketLost();

    int getPacketRecved();

    int getPacketSent();

    voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType();
}
