package com.amazon.avod.content.smoothstream;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class FragmentUtils {
    FragmentUtils() {
    }

    public int getSampleNear(SmoothStreamingURI smoothStreamingURI, Fragment fragment, long j) {
        long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
        for (int i = 0; i < fragment.getSampleCount(); i++) {
            presentationTimeInNanos += TimeSpan.nanosecondsFromTimeScale(fragment.getSampleDuration(i), smoothStreamingURI.getQualityLevel().getTimeScale());
            if (presentationTimeInNanos >= j) {
                return i;
            }
        }
        return fragment.getSampleCount() - 1;
    }

    public long getFragmentDurationInNanoseconds(@Nonnull SmoothStreamingURI smoothStreamingURI, @Nonnull Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        Preconditions.checkNotNull(smoothStreamingURI, "uri");
        long sampleDuration = 0;
        for (int i = 0; i < fragment.getSampleCount(); i++) {
            sampleDuration += fragment.getSampleDuration(i);
        }
        return TimeSpan.nanosecondsFromTimeScale(sampleDuration, smoothStreamingURI.getQualityLevel().getTimeScale());
    }
}
