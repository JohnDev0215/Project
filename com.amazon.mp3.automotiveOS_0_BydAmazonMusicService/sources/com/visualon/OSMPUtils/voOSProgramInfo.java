package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSProgramInfo {
    int getProgramID();

    String getProgramName();

    voOSType.VOOSMP_SRC_PROGRAM_TYPE getProgramType();

    int getSelInfo();

    int getStreamCount();

    voOSStreamInfo[] getStreamInfo();

    boolean parse(Parcel parcel);
}
