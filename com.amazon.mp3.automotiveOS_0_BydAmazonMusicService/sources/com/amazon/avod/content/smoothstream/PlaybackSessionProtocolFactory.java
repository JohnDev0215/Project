package com.amazon.avod.content.smoothstream;

import android.content.Context;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSessionProtocolFactory {
    private final SmoothStreamingPlaybackConfig mConfig;
    private final ContentManagementEventBus mContentTransport;
    private final Context mContext;
    private final DrmScheme mDrmScheme;
    private final LiveStreamingPlaybackConfig mLiveConfig;
    private final Mp4FragmentParser mMp4FragmentParser;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final DefaultQualityConfiguration mQualityConfiguration;

    public PlaybackSessionProtocolFactory(Mp4FragmentParser mp4FragmentParser, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, DefaultQualityConfiguration defaultQualityConfiguration, ContentManagementEventBus contentManagementEventBus, @Nonnull DrmScheme drmScheme, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull Context context) {
        this.mMp4FragmentParser = mp4FragmentParser;
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mLiveConfig = liveStreamingPlaybackConfig;
        this.mContentTransport = contentManagementEventBus;
        this.mQualityConfiguration = defaultQualityConfiguration;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mContext = (Context) Preconditions.checkNotNull(context, "appContext");
    }

    public PlaybackSessionProtocol newPlaybackSessionProtocol(@Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentAccessor contentAccessor, @Nonnull ReadyToWatch readyToWatch) {
        AdaptiveStreamingSessionProtocol adaptiveStreamingSessionProtocol = new AdaptiveStreamingSessionProtocol(this.mContext, this.mMp4FragmentParser, new SampleStreamFactory(this.mMp4FragmentParser, this.mContentTransport, this.mDrmScheme), this.mConfig, this.mLiveConfig, this.mQualityConfiguration, this.mContentTransport, this.mPlaybackEventReporter);
        adaptiveStreamingSessionProtocol.initialize(contentSessionContext, contentAccessor, readyToWatch);
        return adaptiveStreamingSessionProtocol;
    }
}
