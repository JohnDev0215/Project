package com.amazon.avod.media.playback.android;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.SinglePlayerVideoPresentation;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayerBase;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationFactoryBase;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.ViewFillingVideoPlayer;
import com.amazon.avod.media.playback.android.AndroidVideoPlayerV2;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.qahooks.VerificationLogger;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidVideoPresentationFactory extends VideoPresentationFactoryBase {
    private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
    private final AndroidVideoPlayerV2Config mConfig;
    private final MediaSystemSharedContext mContext;
    private final Provider<AndroidVideoPlayerDiagnosticsController> mDiagControllerProvider;
    private final Provider<AndroidVideoPlayer> mPlayerProvider;
    private final Provider<VideoPresentationEventReporter> mPresentationReporterProvider;
    private final Provider<QOSCommunicationService> mQOSCommunicationServiceProvider;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;

    public AndroidVideoPresentationFactory(Provider<AndroidVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<AndroidVideoPlayerDiagnosticsController> provider3, MediaSystemSharedContext mediaSystemSharedContext, Provider<QOSCommunicationService> provider4, AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
        this(provider, provider2, provider3, mediaSystemSharedContext, provider4, AndroidVideoPlayerV2Config.getInstance(), androidVideoPlayerV2Cache, UserWatchSessionIdManager.getInstance());
    }

    @VisibleForTesting
    AndroidVideoPresentationFactory(@Nonnull Provider<AndroidVideoPlayer> provider, @Nonnull Provider<VideoPresentationEventReporter> provider2, @Nonnull Provider<AndroidVideoPlayerDiagnosticsController> provider3, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull Provider<QOSCommunicationService> provider4, @Nonnull AndroidVideoPlayerV2Config androidVideoPlayerV2Config, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
        super(Lists.newArrayList(voMimeTypes.VOVIDEO_MP4));
        this.mPlayerProvider = (Provider) Preconditions.checkNotNull(provider, "viewProvider");
        this.mPresentationReporterProvider = (Provider) Preconditions.checkNotNull(provider2, "presentationReporterProvider");
        this.mDiagControllerProvider = (Provider) Preconditions.checkNotNull(provider3, "diagControllerProvider");
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mQOSCommunicationServiceProvider = (Provider) Preconditions.checkNotNull(provider4, "qosCommunicationServiceProvider");
        this.mConfig = (AndroidVideoPlayerV2Config) Preconditions.checkNotNull(androidVideoPlayerV2Config, "config");
        this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationFactory
    public VideoPresentation newVideoPresentation(VideoSpecification videoSpecification, File file, VideoOptions videoOptions) {
        VideoPlayerBase videoPlayerBaseNewPlayer = newPlayer(videoSpecification);
        if (videoPlayerBaseNewPlayer == null) {
            return null;
        }
        AndroidVideoPlayerDiagnosticsController androidVideoPlayerDiagnosticsController = this.mDiagControllerProvider.get();
        androidVideoPlayerDiagnosticsController.setVideoSpecification(videoSpecification);
        return new SinglePlayerVideoPresentation(videoSpecification, videoOptions, new ViewFillingVideoPlayer(videoPlayerBaseNewPlayer, this.mContext.getAppContext()), this.mPresentationReporterProvider.get(), androidVideoPlayerDiagnosticsController, newLifecycleEventHandler(), file, new VerificationLogger(), this.mUserWatchSessionIdManager);
    }

    @Nullable
    private VideoPlayerBase newPlayer(@Nonnull VideoSpecification videoSpecification) {
        if (!supportsMimeType(videoSpecification.getMimeType())) {
            return null;
        }
        if (this.mConfig.isAndroidVideoPlayerV2Enabled()) {
            return new AndroidVideoPlayerV2.Factory(this.mContext, this.mConfig, this.mQOSCommunicationServiceProvider.get(), this.mAndroidVideoPlayerV2Cache).create();
        }
        return this.mPlayerProvider.get();
    }
}
