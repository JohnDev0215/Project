package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SlidingWindowPolicy implements RetentionPolicy {
    private final long mLookAheadInNanoseconds;
    private final long mLookBehindInNanoseconds;

    public SlidingWindowPolicy(long j, long j2) {
        this.mLookBehindInNanoseconds = j;
        this.mLookAheadInNanoseconds = j2;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI smoothStreamingURI, long j, @Nonnull ContentSessionContext contentSessionContext) {
        if (smoothStreamingURI.isExpired()) {
            return false;
        }
        long jMax = Math.max(j - this.mLookBehindInNanoseconds, 0L);
        long j2 = j + this.mLookAheadInNanoseconds;
        long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
        return jMax <= smoothStreamingURI.getDurationInNanos() + presentationTimeInNanos && presentationTimeInNanos <= j2;
    }
}
