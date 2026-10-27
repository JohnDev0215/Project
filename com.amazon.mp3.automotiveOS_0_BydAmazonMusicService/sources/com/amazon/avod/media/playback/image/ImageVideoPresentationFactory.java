package com.amazon.avod.media.playback.image;

import android.content.Context;
import com.amazon.avod.media.playback.SinglePlayerVideoPresentation;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationFactoryBase;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.ViewFillingVideoPlayer;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.qahooks.VerificationLogger;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.File;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class ImageVideoPresentationFactory extends VideoPresentationFactoryBase {
    private final Context mContext;
    private final Provider<ImageVideoPlayerDiagnosticsController> mDiagControllerProvider;
    private final Provider<ImageVideoPlayer> mPlayerProvider;
    private final Provider<VideoPresentationEventReporter> mPresentationReporterProvider;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;

    public ImageVideoPresentationFactory(Provider<ImageVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<ImageVideoPlayerDiagnosticsController> provider3, Context context) {
        this(provider, provider2, provider3, context, UserWatchSessionIdManager.getInstance());
    }

    public ImageVideoPresentationFactory(@Nonnull Provider<ImageVideoPlayer> provider, @Nonnull Provider<VideoPresentationEventReporter> provider2, @Nonnull Provider<ImageVideoPlayerDiagnosticsController> provider3, @Nonnull Context context, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
        super(Lists.newArrayList("image/jpeg", "image/png"));
        this.mPlayerProvider = (Provider) Preconditions.checkNotNull(provider, "playerProvider");
        this.mPresentationReporterProvider = (Provider) Preconditions.checkNotNull(provider2, "presentationReporterProvider");
        this.mDiagControllerProvider = (Provider) Preconditions.checkNotNull(provider3, "diagControllerProvider");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationFactory
    public VideoPresentation newVideoPresentation(VideoSpecification videoSpecification, File file, VideoOptions videoOptions) {
        ImageVideoPlayer imageVideoPlayerNewPlayer = newPlayer(videoSpecification);
        if (imageVideoPlayerNewPlayer == null) {
            return null;
        }
        ImageVideoPlayerDiagnosticsController imageVideoPlayerDiagnosticsController = this.mDiagControllerProvider.get();
        imageVideoPlayerDiagnosticsController.setVideoSpecification(videoSpecification);
        return new SinglePlayerVideoPresentation(videoSpecification, videoOptions, new ViewFillingVideoPlayer(imageVideoPlayerNewPlayer, this.mContext), this.mPresentationReporterProvider.get(), imageVideoPlayerDiagnosticsController, newLifecycleEventHandler(), file, new VerificationLogger(), this.mUserWatchSessionIdManager);
    }

    ImageVideoPlayer newPlayer(VideoSpecification videoSpecification) {
        if (supportsMimeType(videoSpecification.getMimeType())) {
            return this.mPlayerProvider.get();
        }
        return null;
    }
}
