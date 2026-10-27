package com.amazon.avod.content.readytowatch;

/* JADX INFO: loaded from: classes2.dex */
public class ReadyToWatchResult {
    private final long mTimeUntilDownloadCompletesInNanoseconds;
    private final long mTimeUntilReadyToWatchInNanoseconds;

    public ReadyToWatchResult(long j, long j2) {
        this.mTimeUntilReadyToWatchInNanoseconds = j;
        this.mTimeUntilDownloadCompletesInNanoseconds = j2;
    }

    public boolean isReadyToWatch() {
        return this.mTimeUntilReadyToWatchInNanoseconds == 0;
    }
}
