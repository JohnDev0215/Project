package com.amazon.mp3.playback.state;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlayerPlaybackProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryEqualizerStateManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 42\u00020\u00012\u00020\u0002:\u00014B\u001f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b2\u00103J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0003H\u0002J\b\u0010\b\u001a\u00020\u0003H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0006\u0010\u0012\u001a\u00020\u000bJ\u0006\u0010\u0013\u001a\u00020\u000bR\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001c\u0010%\u001a\n $*\u0004\u0018\u00010#0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00100¨\u00065"}, d2 = {"Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "", "playStateUpdated", "mediaItemUpdated", "mediaCollectionUpdated", "videoPlaybackStarted", "podcastPlaybackStarted", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "contentModel", "", "updateItemPlaybackState", "clearSavedStates", "onPlayStateChanged", "", "playState", "onPlaybackStateChange", "onRefresh", "onDestroy", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/playback/state/PageStateManager;", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "getPageStateManager", "()Lcom/amazon/mp3/playback/state/PageStateManager;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/podcast/media/playback/Playback;", "kotlin.jvm.PlatformType", "podcastPlayback", "Lcom/amazon/podcast/media/playback/Playback;", "Lcom/amazon/music/media/playback/MediaItem;", "savedMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "savedPlayState", "Ljava/lang/Integer;", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "savedMediaCollectionInfo", "Lcom/amazon/music/media/playback/MediaCollectionInfo;", "isPlayingVideoContent", "Z", "isPlayingPodcastContent", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/playback/state/PageStateManager;Lcom/amazon/music/media/playback/PlaybackController;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryEqualizerStateManager implements OnPlayStateChangedListener, Playback.StateCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private boolean isPlayingPodcastContent;
    private boolean isPlayingVideoContent;
    private final PageStateManager pageStateManager;
    private final PlaybackController playbackController;
    private final Playback podcastPlayback;
    private MediaCollectionInfo savedMediaCollectionInfo;
    private MediaItem savedMediaItem;
    private Integer savedPlayState;

    public LibraryEqualizerStateManager(Context context, PageStateManager pageStateManager, PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageStateManager, "pageStateManager");
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.context = context;
        this.pageStateManager = pageStateManager;
        this.playbackController = playbackController;
        Playback playback = Podcast.getPlayback();
        this.podcastPlayback = playback;
        playbackController.addOnPlayStateChangedListener(this);
        playback.addStateCallback(this);
        onPlayStateChanged();
    }

    public final PageStateManager getPageStateManager() {
        return this.pageStateManager;
    }

    /* JADX INFO: compiled from: LibraryEqualizerStateManager.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u000e\u001a\u00020\rJ\u001a\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010\u0014\u001a\u00020\rH\u0002¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager$Companion;", "", "()V", "getPlaybackState", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "getPodcastPlaystate", "metadata", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "isCollectionBeingPlayed", "", "isPodcastContentPlaying", "isTrackBeingPlayed", "trackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "isVideoContentPlaying", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getPlaybackState(ContentMetadata contentMetadata, PlaybackController playbackController) {
            boolean zIsCollectionBeingPlayed;
            Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
            Intrinsics.checkNotNullParameter(playbackController, "playbackController");
            if (isVideoContentPlaying()) {
                return 0;
            }
            if (isPodcastContentPlaying()) {
                if (contentMetadata instanceof PodcastMetadata) {
                    return getPodcastPlaystate((PodcastMetadata) contentMetadata);
                }
                return 0;
            }
            if (contentMetadata instanceof TrackMetadata) {
                zIsCollectionBeingPlayed = isTrackBeingPlayed((TrackMetadata) contentMetadata, playbackController.getCurrentMediaItem());
            } else {
                zIsCollectionBeingPlayed = isCollectionBeingPlayed(contentMetadata);
            }
            PlayStatus playStatus = playbackController.getPlayStatus();
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(playStatus, "playStatus");
            int iConvertToPlayState = contentPlaybackUtils.convertToPlayState(playStatus);
            if (zIsCollectionBeingPlayed) {
                Log.debug("****", "PlayStatus: " + playStatus + " EqualizerState: " + iConvertToPlayState);
            }
            if (zIsCollectionBeingPlayed) {
                return iConvertToPlayState;
            }
            return 0;
        }

        public final boolean isPodcastContentPlaying() {
            int playbackState = Podcast.getPlayback().getPlaybackState();
            return (playbackState == 0 || playbackState == 1) ? false : true;
        }

        private final int getPodcastPlaystate(PodcastMetadata metadata) {
            if (Intrinsics.areEqual(metadata.getCatalogId(), Podcast.getMediaId()) || Intrinsics.areEqual(metadata.getCatalogId(), Podcast.getMediaCollectionId())) {
                return Podcast.getPlayback().getPlaybackState();
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isVideoContentPlaying() {
            PlaybackConfig playbackConfig;
            com.amazon.music.media.playback.Playback playback = com.amazon.music.media.playback.Playback.getInstance();
            Player player = null;
            if (playback != null && (playbackConfig = playback.getPlaybackConfig()) != null) {
                ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                PlayerPlaybackProvider defaultPlaybackProvider = playbackConfig.getDefaultPlaybackProvider(childUserUtil.getMusicRequestIdentityContextToken(applicationContext));
                if (defaultPlaybackProvider != null) {
                    player = defaultPlaybackProvider.getPlayer();
                }
            }
            return player instanceof PrimeVideoPlayer;
        }

        private final boolean isTrackBeingPlayed(TrackMetadata trackMetadata, MediaItem mediaItem) {
            if (mediaItem == null) {
                return false;
            }
            MediaItemId.Type type = MediaItemId.Type.LUID;
            String id = mediaItem.getMediaItemId(type) != null ? mediaItem.getMediaItemId(type).getId() : null;
            MediaItemId.Type type2 = MediaItemId.Type.ASIN;
            String id2 = mediaItem.getMediaItemId(type2) != null ? mediaItem.getMediaItemId(type2).getId() : null;
            if (TextUtils.isEmpty(trackMetadata.getLuid()) || !Intrinsics.areEqual(trackMetadata.getLuid(), id)) {
                return !TextUtils.isEmpty(trackMetadata.getAsin()) && Intrinsics.areEqual(trackMetadata.getAsin(), id2);
            }
            return true;
        }

        private final boolean isCollectionBeingPlayed(ContentMetadata contentMetadata) {
            return MediaItemHelper.isCurrentlyPlayingCollection(contentMetadata.getUri());
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        if (INSTANCE.isPodcastContentPlaying()) {
            if (podcastPlaybackStarted()) {
                onPlaybackStateChange(Podcast.getPlayback().getPlaybackState());
            }
        } else if (playStateUpdated() || mediaItemUpdated() || mediaCollectionUpdated() || videoPlaybackStarted()) {
            this.pageStateManager.performActionPerContentModel(new AnonymousClass1(this));
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.state.LibraryEqualizerStateManager$onPlayStateChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryEqualizerStateManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<BaseViewContentModel, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, LibraryEqualizerStateManager.class, "updateItemPlaybackState", "updateItemPlaybackState(Lcom/amazon/music/views/library/models/base/BaseViewContentModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContentModel baseViewContentModel) {
            invoke2(baseViewContentModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewContentModel p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((LibraryEqualizerStateManager) this.receiver).updateItemPlaybackState(p0);
        }
    }

    private final boolean playStateUpdated() {
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        PlayStatus playStatus = this.playbackController.getPlayStatus();
        Intrinsics.checkNotNullExpressionValue(playStatus, "playbackController.playStatus");
        int iConvertToPlayState = contentPlaybackUtils.convertToPlayState(playStatus);
        Integer num = this.savedPlayState;
        boolean z = num == null || iConvertToPlayState != num.intValue();
        this.savedPlayState = Integer.valueOf(iConvertToPlayState);
        return z;
    }

    private final boolean mediaItemUpdated() {
        boolean z = !Intrinsics.areEqual(this.savedMediaItem, this.playbackController.getCurrentMediaItem());
        this.savedMediaItem = this.playbackController.getCurrentMediaItem();
        return z;
    }

    private final boolean mediaCollectionUpdated() {
        boolean z = !Intrinsics.areEqual(this.savedMediaCollectionInfo, this.playbackController.getMediaCollectionInfo());
        this.savedMediaCollectionInfo = this.playbackController.getMediaCollectionInfo();
        return z;
    }

    private final boolean videoPlaybackStarted() {
        boolean zIsVideoContentPlaying = INSTANCE.isVideoContentPlaying();
        boolean z = zIsVideoContentPlaying && !this.isPlayingVideoContent;
        this.isPlayingVideoContent = zIsVideoContentPlaying;
        return z;
    }

    private final boolean podcastPlaybackStarted() {
        boolean zIsPodcastContentPlaying = INSTANCE.isPodcastContentPlaying();
        boolean z = zIsPodcastContentPlaying && !this.isPlayingPodcastContent;
        this.isPlayingVideoContent = zIsPodcastContentPlaying;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateItemPlaybackState(BaseViewContentModel contentModel) {
        contentModel.updatePlayState(INSTANCE.getPlaybackState(contentModel.getMetadata(), this.playbackController));
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int playState) {
        this.pageStateManager.performActionPerContentModel(new Function1<BaseViewContentModel, Unit>() { // from class: com.amazon.mp3.playback.state.LibraryEqualizerStateManager.onPlaybackStateChange.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseViewContentModel baseViewContentModel) {
                invoke2(baseViewContentModel);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseViewContentModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (model.getContentType() == 11 || model.getContentType() == 12) {
                    LibraryEqualizerStateManager.this.getPageStateManager().updatePodcastPlayState(model, playState);
                } else {
                    model.updatePlayState(0);
                }
            }
        });
    }

    public final void onRefresh() {
        clearSavedStates();
        onPlayStateChanged();
        this.playbackController.addOnPlayStateChangedListener(this);
    }

    private final void clearSavedStates() {
        this.savedMediaItem = null;
        this.savedPlayState = null;
        this.savedMediaCollectionInfo = null;
        this.isPlayingVideoContent = false;
        this.isPlayingPodcastContent = false;
    }

    public final void onDestroy() {
        this.playbackController.removeOnPlayStateChangedListener(this);
        this.podcastPlayback.removeStateCallback(this);
    }
}
