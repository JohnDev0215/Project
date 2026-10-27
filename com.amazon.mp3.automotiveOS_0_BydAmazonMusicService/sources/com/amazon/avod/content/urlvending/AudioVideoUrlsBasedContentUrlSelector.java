package com.amazon.avod.content.urlvending;

import android.net.Uri;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.media.framework.retry.RetriableCall;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AudioVideoUrlsBasedContentUrlSelector implements ContentUrlSelector {
    private final EventDispatcher mContentEventDispatcher;
    private int mCurrentContentUrlIndex;
    private boolean mIsHttpsEnforced;
    private final boolean mIsHttpsFallbackAllowed;
    private final boolean mIsLiveStream;
    private final Object mMutex = new Object();
    private final List<ContentUrl> mProvidedContentUrls;
    private ContentUrlSwitchingPolicy mSwitchingPolicy;

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public boolean notifyDownloadRedirect(@Nonnull ContentUrl contentUrl, @Nonnull Uri uri) {
        return false;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void notifyDownloadSuccess(@Nullable DownloadStatistics downloadStatistics) {
    }

    public AudioVideoUrlsBasedContentUrlSelector(@Nonnull List<ContentUrl> list, @Nonnull ContentUrlSwitchingPolicy contentUrlSwitchingPolicy, @Nonnull EventDispatcher eventDispatcher, boolean z, boolean z2, @Nullable String str) {
        this.mProvidedContentUrls = (List) Preconditions.checkNotNull(list, "Cannot have a null ContentUrls list.");
        Preconditions.checkArgument(!list.isEmpty(), "Cannot have an empty ContentUrls list.");
        this.mSwitchingPolicy = (ContentUrlSwitchingPolicy) Preconditions.checkNotNull(contentUrlSwitchingPolicy, "switchingPolicy");
        this.mContentEventDispatcher = (EventDispatcher) Preconditions.checkNotNull(eventDispatcher, "contentEventDispatcher");
        this.mIsHttpsEnforced = false;
        this.mCurrentContentUrlIndex = getContentUrlIndex(str);
        this.mIsHttpsFallbackAllowed = z;
        this.mIsLiveStream = z2;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode, long j) {
        synchronized (this.mMutex) {
            if (FailoverType.CDN.equals(failoverType)) {
                DLog.logf("Manually triggering seamless CDN failover");
                switchCurrentContentUrl(j, new ContentException(ContentException.ContentError.CDN_ERROR, "Testing CDN failover"), "manual");
            }
        }
    }

    void switchCurrentContentUrl(long j, ContentException contentException, @Nullable String str) {
        if (this.mProvidedContentUrls.size() < 2) {
            return;
        }
        ContentUrl currentContentUrl = getCurrentContentUrl();
        if (this.mCurrentContentUrlIndex < this.mProvidedContentUrls.size() - 1) {
            this.mCurrentContentUrlIndex++;
        } else {
            this.mCurrentContentUrlIndex = 0;
        }
        ContentUrl currentContentUrl2 = getCurrentContentUrl();
        DLog.logf("CDN Switched-newURL = %s, oldURL = %s, failure cause %s", currentContentUrl2, currentContentUrl, contentException);
        this.mContentEventDispatcher.postEvent(new CdnSwitchedEvent(currentContentUrl, currentContentUrl2, new TimeSpan(j), contentException, FailoverType.CDN, FailoverMode.SEAMLESS, str));
        this.mSwitchingPolicy.onSwitchContentUrl();
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    @Nonnull
    public ContentUrl getCurrentContentUrl() {
        ContentUrl contentUrlConvertToHttpsContentUrl;
        synchronized (this.mMutex) {
            contentUrlConvertToHttpsContentUrl = this.mProvidedContentUrls.get(this.mCurrentContentUrlIndex);
            if (this.mIsHttpsEnforced) {
                contentUrlConvertToHttpsContentUrl = ContentUrlSelectorUtil.INSTANCE.convertToHttpsContentUrl(contentUrlConvertToHttpsContentUrl);
            }
        }
        return contentUrlConvertToHttpsContentUrl;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void notifyQoeDecrease(@Nonnull ContentUrl contentUrl, long j) {
        synchronized (this.mMutex) {
            if (contentUrl.equals(getCurrentContentUrl())) {
                switchCurrentContentUrl(j, new ContentException(ContentException.ContentError.CDN_ERROR, "QoE drops (VOD)"), "qoe");
            }
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public RetriableCall.RetryCode notifyDownloadFailure(@Nonnull ContentException contentException, @Nonnull ContentUrl contentUrl, int i, long j, boolean z, @Nullable String str, @Nullable DownloadStatistics downloadStatistics, @Nonnull DownloadType downloadType) {
        Preconditions.checkNotNull(contentException, "failureCause");
        Preconditions.checkNotNull(contentUrl, "targetUrl");
        Preconditions.checkNotNull(downloadType, "downloadType");
        synchronized (this.mMutex) {
            if (contentException.getErrorCode() == ContentException.ContentError.INVALID_CONTENT_LENGTH && !this.mIsHttpsEnforced && this.mIsHttpsFallbackAllowed) {
                this.mIsHttpsEnforced = true;
                DLog.warnf("Enforced download using https protocol");
                return RetriableCall.RetryCode.CONTINUE;
            }
            if (this.mIsLiveStream && contentException.getStatusCode() == 400) {
                this.mContentEventDispatcher.postEvent(LiveStreamingRestartEvent.newOriginSwitchEvent());
            } else if (contentUrl.equals(getCurrentContentUrl()) && this.mSwitchingPolicy.shouldSwitchContentUrlOnFailure(i, contentException)) {
                switchCurrentContentUrl(j, contentException, null);
            }
            return RetriableCall.RetryCode.CONTINUE;
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void setContentUrlSwitchingPolicy(@Nonnull ContentUrlSwitchingPolicy contentUrlSwitchingPolicy) {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(contentUrlSwitchingPolicy, "Parameter contentUrlSwitchingPolicy cannot be null.");
            this.mSwitchingPolicy = contentUrlSwitchingPolicy;
        }
    }

    int getContentUrlIndex(String str) {
        if (str == null) {
            return 0;
        }
        int size = this.mProvidedContentUrls.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(this.mProvidedContentUrls.get(i).getSessionId())) {
                return i;
            }
        }
        return -1;
    }
}
