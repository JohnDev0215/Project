package com.amazon.avod.content;

/* JADX INFO: loaded from: classes2.dex */
public interface LocalContentState {
    long getDownloadedTimeAfterPositionInNanos(long j);

    long getFirstAvailableTimeInNanos();

    long getLastAvailableTimeInNanos();

    long getRemainingNeededSizeInBytes();

    long getTotalNeededSizeInBytes();

    boolean hasDownloadErrors();

    boolean hasUpdatesForContentView();

    boolean isFullyDownloadedFromNanos(long j);
}
