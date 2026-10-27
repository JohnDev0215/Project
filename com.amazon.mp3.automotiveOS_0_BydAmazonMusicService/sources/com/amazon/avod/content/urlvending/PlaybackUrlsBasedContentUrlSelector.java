package com.amazon.avod.content.urlvending;

import android.net.Uri;
import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.media.framework.retry.RetriableCall;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackUrlsBasedContentUrlSelector implements ContentUrlSelector {
    private final AudioVideoUrls mAudioVideoUrls;
    private final EventDispatcher mContentEventDispatcher;
    private ContentUrl mCurrentContentUrl;
    private final FailoverManager mFailoverManager;
    private final FailoverRuleStatusManager mFailoverRuleStatusManager;
    private final boolean mIsHttpsFallbackAllowed;
    private final LiveOriginStatusManager mLiveOriginStatusManager;
    private ContentUrlSwitchingPolicy mSwitchingPolicy;
    private final Object mMutex = new Object();
    private boolean mIsHttpsEnforced = false;

    public PlaybackUrlsBasedContentUrlSelector(@Nonnull ContentUrlSwitchingPolicy contentUrlSwitchingPolicy, @Nonnull EventDispatcher eventDispatcher, @Nonnull boolean z, @Nullable String str, @Nonnull AudioVideoUrls audioVideoUrls, @Nonnull LiveOriginStatusManager liveOriginStatusManager, @Nonnull FailoverRuleStatusManager failoverRuleStatusManager) {
        ContentUrl defaultContentUrl;
        this.mSwitchingPolicy = (ContentUrlSwitchingPolicy) Preconditions.checkNotNull(contentUrlSwitchingPolicy, "switchingPolicy");
        this.mContentEventDispatcher = (EventDispatcher) Preconditions.checkNotNull(eventDispatcher, "contentEventDispatcher");
        this.mIsHttpsFallbackAllowed = z;
        this.mAudioVideoUrls = (AudioVideoUrls) Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls");
        FailoverManager failoverManager = (FailoverManager) Preconditions.checkNotNull(audioVideoUrls.getFailoverManager(), "failoverManager");
        this.mFailoverManager = failoverManager;
        this.mLiveOriginStatusManager = (LiveOriginStatusManager) Preconditions.checkNotNull(liveOriginStatusManager, "liveOriginStatusManager");
        this.mFailoverRuleStatusManager = (FailoverRuleStatusManager) Preconditions.checkNotNull(failoverRuleStatusManager, "failoverRuleStatusManager");
        if (str == null || failoverManager.getContentUrlForUrlSetId(str) == null) {
            defaultContentUrl = failoverManager.getDefaultContentUrl();
        } else {
            defaultContentUrl = failoverManager.getContentUrlForUrlSetId(str);
        }
        this.mCurrentContentUrl = defaultContentUrl;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    @Nonnull
    public ContentUrl getCurrentContentUrl() {
        ContentUrl contentUrlConvertToHttpsContentUrl;
        synchronized (this.mMutex) {
            contentUrlConvertToHttpsContentUrl = this.mIsHttpsEnforced ? ContentUrlSelectorUtil.INSTANCE.convertToHttpsContentUrl(this.mCurrentContentUrl) : this.mCurrentContentUrl;
        }
        return contentUrlConvertToHttpsContentUrl;
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode, long j) {
        synchronized (this.mMutex) {
            switchCurrentContentUrl(j, new ContentException(ContentException.ContentError.CDN_ERROR, "Testing CDN failover (LIVE)"), failoverType, true, UUID.randomUUID().toString(), "manual", Optional.fromNullable(failoverMode));
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void notifyDownloadSuccess(@Nullable DownloadStatistics downloadStatistics) {
        this.mFailoverRuleStatusManager.clearStatusMap("download success");
        if (downloadStatistics != null) {
            this.mLiveOriginStatusManager.updateOriginStatus(downloadStatistics);
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void notifyQoeDecrease(@Nonnull ContentUrl contentUrl, long j) {
        synchronized (this.mMutex) {
            if (contentUrl.equals(getCurrentContentUrl())) {
                switchCurrentContentUrl(j, new ContentException(ContentException.ContentError.CDN_ERROR, "QoE drops (LIVE)"), FailoverType.CDN, true, null, "qoe", Optional.absent());
            }
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public RetriableCall.RetryCode notifyDownloadFailure(@Nonnull ContentException contentException, @Nonnull ContentUrl contentUrl, int i, long j, boolean z, @Nullable String str, @Nullable DownloadStatistics downloadStatistics, @Nonnull DownloadType downloadType) {
        Preconditions.checkNotNull(contentException, "failureCause");
        Preconditions.checkNotNull(contentUrl, "targetUrl");
        Preconditions.checkNotNull(downloadType, "downloadType");
        if (downloadStatistics != null) {
            this.mLiveOriginStatusManager.updateOriginStatus(downloadStatistics);
        }
        synchronized (this.mMutex) {
            if (contentException.getErrorCode() == ContentException.ContentError.INVALID_CONTENT_LENGTH && !this.mIsHttpsEnforced && this.mIsHttpsFallbackAllowed) {
                this.mIsHttpsEnforced = true;
                DLog.warnf("Enforced download using https protocol");
                return RetriableCall.RetryCode.CONTINUE;
            }
            if (contentUrl.equals(getCurrentContentUrl())) {
                Pair<FailoverType, String> failoverType = this.mFailoverRuleStatusManager.getFailoverType(contentException, downloadType);
                FailoverType failoverType2 = failoverType.first;
                DLog.logf("Found failover type %s", failoverType2);
                if (failoverType2 == FailoverType.CLOSE_PLAYER) {
                    DLog.logf("Closing player due to signal receieved in HTTP response");
                    closePlayer();
                } else {
                    if (failoverType2 == FailoverType.NO_ACTION) {
                        return RetriableCall.RetryCode.CONTINUE;
                    }
                    return switchCurrentContentUrl(j, contentException, failoverType2, z, str, failoverType.second, Optional.absent());
                }
            }
            return RetriableCall.RetryCode.CONTINUE;
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public boolean notifyDownloadRedirect(@Nonnull ContentUrl contentUrl, @Nonnull Uri uri) {
        synchronized (this.mMutex) {
            if (!contentUrl.equals(getCurrentContentUrl())) {
                return false;
            }
            this.mCurrentContentUrl = new ContentUrl(uri.toString(), getCurrentContentUrl());
            return true;
        }
    }

    @Override // com.amazon.avod.content.urlvending.ContentUrlSelector
    public void setContentUrlSwitchingPolicy(@Nonnull ContentUrlSwitchingPolicy contentUrlSwitchingPolicy) {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(contentUrlSwitchingPolicy, "Parameter contentUrlSwitchingPolicy cannot be null.");
            this.mSwitchingPolicy = contentUrlSwitchingPolicy;
        }
    }

    RetriableCall.RetryCode switchCurrentContentUrl(long j, ContentException contentException, @Nonnull FailoverType failoverType, boolean z, @Nullable String str, @Nullable String str2, @Nonnull Optional<FailoverMode> optional) {
        FailoverType failoverType2;
        if (this.mFailoverManager.getExhaustiveContentUrlList().size() < 2) {
            return RetriableCall.RetryCode.CONTINUE;
        }
        ContentUrl currentContentUrl = getCurrentContentUrl();
        String sessionId = currentContentUrl.getSessionId();
        if (sessionId == null) {
            DLog.errorf("Cannot perform failover with null urlSetId");
            return RetriableCall.RetryCode.CONTINUE;
        }
        FailoverInfo failoverInfoForFailoverType = this.mFailoverManager.getFailoverInfoForFailoverType(sessionId, failoverType, this.mLiveOriginStatusManager.getBlacklistedOrigins());
        if (failoverInfoForFailoverType == null && failoverType != (failoverType2 = FailoverType.DEFAULT)) {
            DLog.warnf("No failover info found for failoverType %s, using DEFAULT", failoverType);
            failoverInfoForFailoverType = this.mFailoverManager.getFailoverInfoForFailoverType(sessionId, failoverType2, this.mLiveOriginStatusManager.getBlacklistedOrigins());
        }
        if (failoverInfoForFailoverType == null) {
            DLog.errorf("No failover info found for default. Not switching content urls");
            return RetriableCall.RetryCode.CONTINUE;
        }
        FailoverMode failoverModeOr = optional.or(failoverInfoForFailoverType.getFailoverMode());
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$urlvending$FailoverMode[failoverModeOr.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                DLog.logf("Performing discontinuous refresh");
                this.mContentEventDispatcher.postEvent(LiveStreamingRestartEvent.newOriginSwitchEvent(failoverInfoForFailoverType.getUrlSetId(), this.mAudioVideoUrls, TimeSpan.MAX_VALUE.getTotalMilliseconds(), z, str, str2, currentContentUrl.getSessionId()));
                return RetriableCall.RetryCode.STOP;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Unsupported FailoverMode: %s", failoverModeOr));
        }
        DLog.logf("Performing seamless failover");
        ContentUrl contentUrlForUrlSetId = this.mFailoverManager.getContentUrlForUrlSetId(failoverInfoForFailoverType.getUrlSetId());
        if (contentUrlForUrlSetId != null) {
            this.mCurrentContentUrl = contentUrlForUrlSetId;
        }
        if (contentUrlForUrlSetId != null) {
            DLog.logf("CDN Switched-newURL = %s, oldURL = %s, failure cause %s, failoverType %s, failoverMode %s, ruleId %s", contentUrlForUrlSetId, currentContentUrl, contentException, failoverType, failoverModeOr, str2);
            this.mContentEventDispatcher.postEvent(new CdnSwitchedEvent(currentContentUrl, contentUrlForUrlSetId, new TimeSpan(j), contentException, failoverType, failoverModeOr, str2));
            this.mSwitchingPolicy.onSwitchContentUrl();
        }
        return RetriableCall.RetryCode.CONTINUE;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.urlvending.PlaybackUrlsBasedContentUrlSelector$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$urlvending$FailoverMode;

        static {
            int[] iArr = new int[FailoverMode.values().length];
            $SwitchMap$com$amazon$avod$content$urlvending$FailoverMode = iArr;
            try {
                iArr[FailoverMode.SEAMLESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$urlvending$FailoverMode[FailoverMode.MANIFEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$urlvending$FailoverMode[FailoverMode.DISCONTINUOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void closePlayer() {
        this.mContentEventDispatcher.postEvent(new ClosePlayerEvent());
    }
}
