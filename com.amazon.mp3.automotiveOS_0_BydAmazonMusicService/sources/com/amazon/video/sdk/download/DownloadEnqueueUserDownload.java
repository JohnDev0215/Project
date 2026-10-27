package com.amazon.video.sdk.download;

import android.content.Context;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.audioformat.AudioFormatProvider;
import com.amazon.avod.userdownload.GeneralDownloadConfig;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadRequest;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.userdownload.reporting.QueueCause;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.TimedTextStreamMatcher;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtils;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadEnqueueUserDownload.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadEnqueueUserDownload;", "", "()V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadEnqueueUserDownload {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MultiTrackAudioUtils mtaUtils = new MultiTrackAudioUtils();

    /* JADX INFO: compiled from: DownloadEnqueueUserDownload.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JF\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadEnqueueUserDownload$Companion;", "", "()V", "mtaUtils", "Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "enqueueDownloadOrError", "Lcom/amazon/video/sdk/download/DownloadEnqueueUserDownloadWrapper;", "config", "Lcom/amazon/video/sdk/player/ContentConfig;", "downloadQuality", "Lcom/amazon/video/sdk/download/DownloadQuality;", "context", "Landroid/content/Context;", "audioStreamMatchers", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "timedTextStreamMatchers", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "downloadManager", "Lcom/amazon/avod/userdownload/internal/PlaybackDownloadManager;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadEnqueueUserDownloadWrapper enqueueDownloadOrError(ContentConfig config, DownloadQuality downloadQuality, Context context, List<AudioStreamMatcher> audioStreamMatchers, List<TimedTextStreamMatcher> timedTextStreamMatchers, PlaybackDownloadManager downloadManager) throws PlaybackDownloadRequest.IllegalPlaybackDownloadRequestException, BoltException, PlaybackDownloadRequest.DuplicatePlaybackDownloadRequestException {
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(downloadQuality, "downloadQuality");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(downloadManager, "downloadManager");
            DLog.logf("DWNLD_SDK DownloadEnqueueUserDownload enqueueDownloadOrError %s %s %s %s", config, downloadQuality, audioStreamMatchers, timedTextStreamMatchers);
            ImmutableList<String> immutableListOf = ImmutableList.of();
            Intrinsics.checkNotNullExpressionValue(immutableListOf, "of()");
            if (audioStreamMatchers != null) {
                immutableListOf = DownloadEnqueueUserDownload.mtaUtils.generateAudioTrackIdsFromStreamMatchers(audioStreamMatchers);
            }
            if (immutableListOf.isEmpty()) {
                DLog.warnf("DWNLD_SDK DownloadEnqueueUserDownload: falling back to PRS for audio track list");
                DownloadGetDownloadRequestResourcesWrapper downloadPRSResources = DownloadGetDownloadRequestResources.INSTANCE.getDownloadPRSResources(config, context);
                if (downloadPRSResources.getError() != null) {
                    return new DownloadEnqueueUserDownloadWrapper(null, downloadPRSResources.getError());
                }
                immutableListOf = downloadPRSResources.getAudioTrackIds();
            }
            AudioFormat audioFormatDetermineAudioFormat = AudioFormatProvider.getInstance().determineAudioFormat();
            Intrinsics.checkNotNullExpressionValue(audioFormatDetermineAudioFormat, "getInstance().determineAudioFormat()");
            MediaQuality mediaQualityDownloadQualityToMediaQuality = DownloadQuality.INSTANCE.downloadQualityToMediaQuality(downloadQuality);
            PlaybackDownloadRequest.Builder builderNewBuilder = PlaybackDownloadRequest.newBuilder(PlaybackDownloads.getInstance().getLocationConfig(), GeneralDownloadConfig.getInstance(), AdsConfig.getInstance().shouldEnableFragmentDownloaderForSSAIDownloads());
            builderNewBuilder.setTitleId(config.getTitleId());
            builderNewBuilder.setSessionContext(config.getSessionContext()).setMediaQuality(mediaQualityDownloadQualityToMediaQuality).setAudioTrackIds(immutableListOf).setAudioFormat(audioFormatDetermineAudioFormat).setPlayerSDKPlaybackToken(Optional.of(config.getPlaybackToken())).setPlayerSDKAudioStreamMatchers(Optional.fromNullable(AudioStreamMatcher.INSTANCE.toJSON(audioStreamMatchers))).setPlayerSDKTimedTextStreamMatchers(Optional.fromNullable(TimedTextStreamMatcher.INSTANCE.toJSON(timedTextStreamMatchers)));
            builderNewBuilder.setStorageLocation(downloadManager.getPreferredDownloadLocation());
            PlaybackDownload playbackDownloadQueue = PlaybackDownloads.getInstance().getDownloadManager().queue(builderNewBuilder.build(), QueueCause.SDK_QUEUE);
            Intrinsics.checkNotNullExpressionValue(playbackDownloadQueue, "getInstance().downloadMa…(), QueueCause.SDK_QUEUE)");
            return new DownloadEnqueueUserDownloadWrapper(playbackDownloadQueue, null);
        }
    }
}
