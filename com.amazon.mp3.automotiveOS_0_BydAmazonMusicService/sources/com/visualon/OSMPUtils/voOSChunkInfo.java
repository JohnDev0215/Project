package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSChunkInfo {
    int Bitrate();

    long ChunkID();

    int DownloadSize();

    long DownloadTime();

    long Duration();

    int ErrorCode();

    voOSType.VOOSMP_STREAMING_ERROR_TYPE ErrorType();

    int PeriodSequenceNumber();

    String RootUrl();

    long StartTime();

    long TimeScale();

    int Type();

    String Url();
}
