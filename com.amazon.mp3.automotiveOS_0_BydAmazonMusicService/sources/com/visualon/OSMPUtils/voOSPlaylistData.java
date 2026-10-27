package com.visualon.OSMPUtils;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSPlaylistData {
    byte[] getData();

    int getDataSize();

    int getErrorCode();

    voOSType.VOOSMP_STREAMING_ERROR_TYPE getErrorType();

    voOSType.VOOSMP_SRC_PLAYLIST_TYPE getPlaylistType();

    String getSZNewUrl();

    String getSZRootUrl();

    String getSZUrl();
}
