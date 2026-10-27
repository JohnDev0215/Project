package com.amazon.avod.playback.smoothstream;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface Manifest {
    StreamIndex[] getAvailableStreams();

    long getDuration();

    int getMajorVersion();

    int getMinorVersion();

    ProtectionHeader getProtectionHeader();

    long getTimeScale();
}
