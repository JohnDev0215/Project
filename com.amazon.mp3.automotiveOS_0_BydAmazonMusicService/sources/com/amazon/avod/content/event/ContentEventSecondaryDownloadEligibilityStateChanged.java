package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.google.common.base.Preconditions;
import java.util.Collection;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventSecondaryDownloadEligibilityStateChanged extends ContentEventBase {
    private final Collection<DownloadRequest> mCancelledRequests;
    private final boolean mIsEligible;
    private final boolean mIsLastCancellation;
    private final boolean mWasSeek;

    public ContentEventSecondaryDownloadEligibilityStateChanged(boolean z, boolean z2, boolean z3, @Nonnull Collection<DownloadRequest> collection, @Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType) {
        super((PlayableContent) Preconditions.checkNotNull(playableContent, "content"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType"));
        this.mIsEligible = z;
        this.mWasSeek = z2;
        this.mIsLastCancellation = z3;
        this.mCancelledRequests = (Collection) Preconditions.checkNotNull(collection, "cancelledRequests");
    }

    public boolean areSecondaryDownloadsAllowed() {
        return this.mIsEligible;
    }

    public boolean stateChangeDueToSeek() {
        return this.mWasSeek;
    }

    public boolean isLastCancellation() {
        return this.mIsLastCancellation;
    }

    @Nonnull
    public Collection<DownloadRequest> getCancelledRequests() {
        return this.mCancelledRequests;
    }
}
