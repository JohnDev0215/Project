package com.amazon.mp3.detailpages.album.actions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.work.Data;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.util.TrackInitiatedPlaybackHelper;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlayTrackInCollectionAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Ji\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0018J\u0091\u0001\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010!Ji\u0010\"\u001a\u00020\u001a2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0004¢\u0006\u0002\u0010#J\u0099\u0001\u0010$\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001e2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001eH\u0002¢\u0006\u0002\u0010%R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/mp3/detailpages/album/actions/PlayTrackInCollectionAction;", "", "()V", "TAG", "", "getNoOpCloudQueueHandler", "Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "contentUri", "Landroid/net/Uri;", "position", "", "shuffle", "", "selection", "selectionArgs", "", "playbackMetricInfo", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "shouldDisplayNowPlayingScreen", "sortOrder", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/MusicTrack;Landroid/net/Uri;IZLjava/lang/String;[Ljava/lang/String;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;ZLjava/lang/String;)Lcom/amazon/mp3/cloudqueue/NoOpCloudQueueClientHandler;", "initiateCloudQueuePlayback", "", "isCustomerSelectedEntity", "collectionName", "musicTrackList", "", "augmentations", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/MusicTrack;Landroid/net/Uri;ZLjava/lang/String;[Ljava/lang/String;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;ZZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "initiatePlayQueuePlayback", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/MusicTrack;Landroid/net/Uri;IZLjava/lang/String;[Ljava/lang/String;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;ZLjava/lang/String;)V", "startTrackInContainerPlayback", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/MusicTrack;Landroid/net/Uri;IZLjava/lang/String;[Ljava/lang/String;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;ZZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayTrackInCollectionAction {
    public static final PlayTrackInCollectionAction INSTANCE = new PlayTrackInCollectionAction();
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlayTrackInCollectionAction.class).getSimpleName();

    private PlayTrackInCollectionAction() {
    }

    @SuppressLint({"CheckResult"})
    public final void initiateCloudQueuePlayback(FragmentActivity activity, MusicTrack track, Uri contentUri, boolean shuffle, String selection, String[] selectionArgs, PlaybackMetricInformation playbackMetricInfo, boolean isCustomerSelectedEntity, boolean shouldDisplayNowPlayingScreen, String sortOrder, String collectionName, List<? extends MusicTrack> musicTrackList, List<? extends PlaybackAugmentation> augmentations) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(playbackMetricInfo, "playbackMetricInfo");
        Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
        Intrinsics.checkNotNullParameter(collectionName, "collectionName");
        Intrinsics.checkNotNullParameter(musicTrackList, "musicTrackList");
        if (contentUri == null) {
            return;
        }
        List<MusicTrack> tracksForPlayback = TrackInitiatedPlaybackHelper.getTracksForPlayback(musicTrackList, track, new EligibilityUtil(null, 1, 0 == true ? 1 : 0), contentUri);
        INSTANCE.startTrackInContainerPlayback(activity, track, contentUri, TrackInitiatedPlaybackHelper.findTrackPositionInList(tracksForPlayback, track), shuffle, selection, selectionArgs, playbackMetricInfo, isCustomerSelectedEntity, shouldDisplayNowPlayingScreen, sortOrder, collectionName, tracksForPlayback, augmentations);
    }

    private final void startTrackInContainerPlayback(FragmentActivity activity, MusicTrack track, Uri contentUri, int position, boolean shuffle, String selection, String[] selectionArgs, PlaybackMetricInformation playbackMetricInfo, boolean isCustomerSelectedEntity, boolean shouldDisplayNowPlayingScreen, String sortOrder, String collectionName, List<? extends MusicTrack> musicTrackList, List<? extends PlaybackAugmentation> augmentations) {
        if (track.isAllOwned()) {
            PlaybackPageType pageType = playbackMetricInfo.getPageType();
            Intrinsics.checkNotNullExpressionValue(pageType, "playbackMetricInfo.pageType");
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            StartPlaybackUtil.startMusicTracksPlayback(activity, contentUri, collectionName, musicTrackList, (14336 & 16) != 0 ? 0 : position, (14336 & 32) != 0 ? false : shuffle, (14336 & 64) != 0 ? true : shouldDisplayNowPlayingScreen, pageType, (14336 & 256) != 0 ? false : isCustomerSelectedEntity, APP_UI, (14336 & 1024) != 0 ? null : null, (14336 & 2048) != 0 ? false : false, (14336 & 4096) != 0 ? null : null, (14336 & 8192) != 0 ? null : augmentations, isCustomerSelectedEntity, getNoOpCloudQueueHandler(activity, track, contentUri, position, shuffle, selection, selectionArgs, playbackMetricInfo, shouldDisplayNowPlayingScreen, sortOrder));
            return;
        }
        StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
        PlaybackPageType pageType2 = playbackMetricInfo.getPageType();
        ControlSource APP_UI2 = ControlSource.APP_UI;
        NoOpCloudQueueClientHandler noOpCloudQueueHandler = getNoOpCloudQueueHandler(activity, track, contentUri, position, shuffle, selection, selectionArgs, playbackMetricInfo, shouldDisplayNowPlayingScreen, sortOrder);
        Intrinsics.checkNotNullExpressionValue(pageType2, "pageType");
        Intrinsics.checkNotNullExpressionValue(APP_UI2, "APP_UI");
        StartPlaybackUtil.startCollectionPlayback(activity, (Data.MAX_DATA_BYTES & 2) != 0 ? null : collectionName, contentUri, musicTrackList, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : position, (Data.MAX_DATA_BYTES & 32) != 0 ? false : shuffle, (Data.MAX_DATA_BYTES & 64) != 0 ? true : shouldDisplayNowPlayingScreen, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, pageType2, (Data.MAX_DATA_BYTES & 512) != 0 ? false : isCustomerSelectedEntity, APP_UI2, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : augmentations, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, isCustomerSelectedEntity, noOpCloudQueueHandler);
    }

    public final void initiatePlayQueuePlayback(FragmentActivity activity, MusicTrack track, Uri contentUri, int position, boolean shuffle, String selection, String[] selectionArgs, PlaybackMetricInformation playbackMetricInfo, boolean shouldDisplayNowPlayingScreen, String sortOrder) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(playbackMetricInfo, "playbackMetricInfo");
        Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
        PlaybackControllerProvider.Companion companion = PlaybackControllerProvider.INSTANCE;
        ControlSource controlSource = ControlSource.APP_UI;
        ActionValidatedPlaybackController controller = companion.getController(controlSource);
        long jNow = Clock.now();
        if (MediaItemHelper.isCurrentlyPlayingItem(track.getAsin())) {
            controller.play();
        } else {
            PlayQueueSequencer.getInstance().start(controlSource, contentUri, selection, selectionArgs, null, position, shuffle, playbackMetricInfo, track, jNow);
        }
        if (shouldDisplayNowPlayingScreen) {
            Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", sortOrder);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", contentUri);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", position);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_SHUFFLE", shuffle);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_PLAYING", true);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK", track);
            if ((activity == null || activity.isFinishing()) ? false : true) {
                if (activity != null) {
                    try {
                        activity.startActivity(intentForContentUri);
                    } catch (Exception e) {
                        Log.error(TAG, Intrinsics.stringPlus("Unable to start Now Playing, exception: ", e.getMessage()));
                        return;
                    }
                }
                if (activity == null) {
                    return;
                }
                activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }
        }
    }

    private final NoOpCloudQueueClientHandler getNoOpCloudQueueHandler(final FragmentActivity activity, final MusicTrack track, final Uri contentUri, final int position, final boolean shuffle, final String selection, final String[] selectionArgs, final PlaybackMetricInformation playbackMetricInfo, final boolean shouldDisplayNowPlayingScreen, final String sortOrder) {
        return new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.detailpages.album.actions.PlayTrackInCollectionAction.getNoOpCloudQueueHandler.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                PlayTrackInCollectionAction.INSTANCE.initiatePlayQueuePlayback(activity, track, contentUri, position, shuffle, selection, selectionArgs, playbackMetricInfo, shouldDisplayNowPlayingScreen, sortOrder);
            }
        };
    }
}
