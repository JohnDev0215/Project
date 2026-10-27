package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentUrlSelectorFactory {
    private final CdnSwitchConfig mCdnSwitchConfig = CdnSwitchConfig.INSTANCE;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;

    public ContentUrlSelector newContentUrlSelector(@Nonnull AudioVideoUrls audioVideoUrls, @Nonnull EventDispatcher eventDispatcher, boolean z, boolean z2, @Nullable String str) {
        Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls");
        Preconditions.checkNotNull(eventDispatcher, "contentEventDispatcher");
        SmoothStreamingContentUrlSwitchingPolicy smoothStreamingContentUrlSwitchingPolicy = new SmoothStreamingContentUrlSwitchingPolicy(this.mCdnSwitchConfig.getMaxNumberTriesPerFragment(), new ImmutableMap.Builder().put(ContentException.ContentError.CDN_ERROR, Integer.valueOf(this.mCdnSwitchConfig.getMaxNumberTriesForCdnError())).put(ContentException.ContentError.NETWORK_ERROR, Integer.valueOf(this.mCdnSwitchConfig.getMaxNumberTriesForNetworkError())).build(), this.mCdnSwitchConfig.getWindowToRememberErrors(), this.mCdnSwitchConfig.getMaxNumberOfCDNSwitchesPerWindow(), this.mCdnSwitchConfig.getWindowToRememberCDNSwitches());
        ImmutableList<ContentUrl> contentUrls = audioVideoUrls.getContentUrls();
        if (z2 && this.mSmoothStreamingPlaybackConfig.isFailoverManagerBasedCDNSwitchingEnabled(audioVideoUrls.getContentUrls().get(0).getAdsTrackingEndpoint()) && audioVideoUrls.getFailoverManager() != null) {
            return new PlaybackUrlsBasedContentUrlSelector(smoothStreamingContentUrlSwitchingPolicy, eventDispatcher, z, str, audioVideoUrls, new LiveOriginStatusManager(), new FailoverRuleStatusManager(this.mSmoothStreamingPlaybackConfig.getFailoverMap()));
        }
        return new AudioVideoUrlsBasedContentUrlSelector(contentUrls, smoothStreamingContentUrlSwitchingPolicy, eventDispatcher, z, z2, str);
    }
}
