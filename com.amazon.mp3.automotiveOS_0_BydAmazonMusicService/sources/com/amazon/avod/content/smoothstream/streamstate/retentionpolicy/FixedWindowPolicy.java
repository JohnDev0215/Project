package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class FixedWindowPolicy implements RetentionPolicy {
    private final long mEndPositionInNanoseconds;
    private final long mStartPositionInNanoseconds;

    public FixedWindowPolicy(@Nonnull ContentSessionContext contentSessionContext) {
        this(contentSessionContext.getState().getPlayPositionInNanos(), contentSessionContext.getSpecification().getStartTime().getTotalNanoSeconds() + contentSessionContext.getDuration().getTotalNanoSeconds());
    }

    FixedWindowPolicy(long j, long j2) {
        this.mStartPositionInNanoseconds = j;
        this.mEndPositionInNanoseconds = j2;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI smoothStreamingURI, long j, @Nonnull ContentSessionContext contentSessionContext) {
        long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
        return this.mStartPositionInNanoseconds <= smoothStreamingURI.getDurationInNanos() + presentationTimeInNanos && presentationTimeInNanos <= this.mEndPositionInNanoseconds;
    }
}
