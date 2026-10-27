package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class SmoothStreamingContentUrlSwitchingPolicy implements ContentUrlSwitchingPolicy {
    private final TimeSpan mCDNSwitchCountWindowLength;
    private SlidingWindowEventTracker mCDNSwitchWindow;
    private final Map<ContentException.ContentError, SlidingWindowEventTracker> mErrorCodeWindowMap = Maps.newHashMap();
    private final TimeSpan mErrorCountWindowLength;
    private final int mMaxErrorsForSingleResource;
    private final ImmutableMap<ContentException.ContentError, Integer> mMaxErrorsInWindowByErrorCode;
    private final int mMaxNumberOfCDNSwitchesPerWindow;

    SmoothStreamingContentUrlSwitchingPolicy(int i, ImmutableMap<ContentException.ContentError, Integer> immutableMap, TimeSpan timeSpan, int i2, TimeSpan timeSpan2) {
        Preconditions.checkArgument(i > 0, "The max number of retries for the resource cannot be negative");
        this.mMaxErrorsForSingleResource = i;
        Preconditions.checkArgument(i2 > 0, "The max number of retries before disabling CDN switch should be greater than 0");
        this.mMaxNumberOfCDNSwitchesPerWindow = i2;
        Preconditions.checkNotNull(immutableMap, "Cannot have a null map of ContentErrors to threshold values");
        UnmodifiableIterator<Integer> it = immutableMap.values().iterator();
        while (it.hasNext()) {
            Preconditions.checkArgument(it.next().intValue() > 0, "The max number of retries for threshold of errors cannot be negative");
        }
        this.mMaxErrorsInWindowByErrorCode = immutableMap;
        this.mErrorCountWindowLength = timeSpan;
        this.mCDNSwitchCountWindowLength = timeSpan2;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicy
    public boolean shouldSwitchContentUrlOnFailure(int i, ContentException contentException) {
        TimeSpan timeSpanNow = TimeSpan.now();
        boolean zShouldSwitchContentUrlOnFailure = shouldSwitchContentUrlOnFailure(i, contentException, timeSpanNow);
        if (zShouldSwitchContentUrlOnFailure) {
            this.mCDNSwitchWindow.recordEvent(timeSpanNow);
        }
        return zShouldSwitchContentUrlOnFailure;
    }

    boolean shouldSwitchContentUrlOnFailure(int i, ContentException contentException, TimeSpan timeSpan) {
        if (this.mCDNSwitchWindow == null) {
            this.mCDNSwitchWindow = new SlidingWindowEventTracker(this.mCDNSwitchCountWindowLength, this.mMaxNumberOfCDNSwitchesPerWindow);
        }
        if (this.mCDNSwitchWindow.isEventCountGreaterThanOrEqualToThreshold()) {
            return false;
        }
        ContentException.ContentError errorCode = contentException.getErrorCode();
        if (this.mMaxErrorsInWindowByErrorCode.containsKey(errorCode)) {
            SlidingWindowEventTracker slidingWindowEventTracker = this.mErrorCodeWindowMap.get(errorCode);
            if (slidingWindowEventTracker == null) {
                slidingWindowEventTracker = new SlidingWindowEventTracker(this.mErrorCountWindowLength, this.mMaxErrorsInWindowByErrorCode.get(errorCode).intValue());
                this.mErrorCodeWindowMap.put(errorCode, slidingWindowEventTracker);
            }
            slidingWindowEventTracker.recordEvent(timeSpan);
            if (slidingWindowEventTracker.isEventCountGreaterThanThreshold()) {
                return true;
            }
        }
        return i != 0 && i % this.mMaxErrorsForSingleResource == 0;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicy
    public void onSwitchContentUrl() {
        this.mErrorCodeWindowMap.clear();
    }
}
