package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSTrackInfo {
    voOSAudioInfo getAudioInfo();

    int getBitrate();

    int getChunkCounts();

    int getCodec();

    long getDuration();

    char[] getFourCC();

    char[] getHeadData();

    int getHeadSize();

    int getSelectInfo();

    voOSSubtitleInfo getSubtitleInfo();

    int getTrackID();

    voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType();

    voOSVideoInfo getVideoInfo();

    boolean parse(Parcel parcel);
}
