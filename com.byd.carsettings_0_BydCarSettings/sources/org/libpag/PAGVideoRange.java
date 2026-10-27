package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class PAGVideoRange {
    public long endTime;
    public long playDuration;
    public boolean reversed;
    public long startTime;

    public PAGVideoRange(long j, long j2, long j3, boolean z) {
        this.startTime = 0L;
        this.endTime = 0L;
        this.playDuration = 0L;
        this.reversed = false;
        this.startTime = j;
        this.endTime = j2;
        this.playDuration = j3;
        this.reversed = z;
    }
}
