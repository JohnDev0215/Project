package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CdnSwitchedEvent {
    private final TimeSpan mEventTimeStamp;
    private final FailoverMode mFailoverMode;
    private final String mFailoverRuleId;
    private final FailoverType mFailoverType;
    private final ContentException mFailureCause;
    private final ContentUrl mNewContentUrl;
    private final ContentUrl mOldContentUrl;

    public CdnSwitchedEvent(@Nonnull ContentUrl contentUrl, @Nonnull ContentUrl contentUrl2, @Nonnull TimeSpan timeSpan, @Nonnull ContentException contentException, @Nonnull FailoverType failoverType, @Nonnull FailoverMode failoverMode, @Nullable String str) {
        this.mOldContentUrl = (ContentUrl) Preconditions.checkNotNull(contentUrl, "oldContentUrl");
        this.mNewContentUrl = (ContentUrl) Preconditions.checkNotNull(contentUrl2, "newContentUrl");
        this.mEventTimeStamp = (TimeSpan) Preconditions.checkNotNull(timeSpan, "presentationTime");
        this.mFailureCause = (ContentException) Preconditions.checkNotNull(contentException, "failureCause");
        this.mFailoverType = (FailoverType) Preconditions.checkNotNull(failoverType, "failoverType");
        this.mFailoverMode = (FailoverMode) Preconditions.checkNotNull(failoverMode, "failoverMode");
        this.mFailoverRuleId = str;
    }

    @Nonnull
    public ContentUrl getOldContentUrl() {
        return this.mOldContentUrl;
    }

    @Nonnull
    public ContentUrl getNewContentUrl() {
        return this.mNewContentUrl;
    }

    @Nonnull
    public TimeSpan getEventTimeStamp() {
        return this.mEventTimeStamp;
    }

    @Nonnull
    public ContentException getFailureCause() {
        return this.mFailureCause;
    }

    @Nonnull
    public FailoverType getFailoverType() {
        return this.mFailoverType;
    }

    @Nonnull
    public FailoverMode getFailoverMode() {
        return this.mFailoverMode;
    }

    @Nullable
    public String getFailoverRuleId() {
        return this.mFailoverRuleId;
    }
}
