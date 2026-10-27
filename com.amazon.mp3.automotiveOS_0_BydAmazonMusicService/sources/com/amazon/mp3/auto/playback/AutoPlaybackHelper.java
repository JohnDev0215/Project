package com.amazon.mp3.auto.playback;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.work.Data;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.auto.ContentMetadataEnabilityProvider;
import com.amazon.mp3.auto.VoiceUtil;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.navigation.playback.TracksPlaybackHelper;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AutoPlaybackHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J,\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\"\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J8\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010 \u001a\u00020\u00192\b\b\u0002\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ,\u0010!\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\"2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0018\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\"\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\"\u0010(\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020)2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\"\u0010*\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020+2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/mp3/auto/playback/AutoPlaybackHelper;", "", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "playbackScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;Lkotlinx/coroutines/CoroutineScope;)V", "enabilityProvider", "Lcom/amazon/mp3/auto/ContentMetadataEnabilityProvider;", "getBootstrap", "Lcom/amazon/podcast/bootstrap/Bootstrap;", "deeplinkPath", "", "startAlbumPlayback", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "startingTrack", "isVoiceInitiated", "", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "startArtistPlayback", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "startPlayback", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "eligibilityVerified", "startPlaylistPlayback", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "startPodcastPlayback", "podcastMetadata", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "startStationPlayback", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "startTrackPlayback", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "startUserPlaylistPlayback", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoPlaybackHelper {
    private static final String TAG = AutoPlaybackHelper.class.getSimpleName();
    private final Context context;
    private final ContentMetadataEnabilityProvider enabilityProvider;
    private final ExperienceMode experienceMode;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;
    private final CoroutineScope playbackScope;

    public AutoPlaybackHelper(Context context, PageType pageType, PlaybackPageType playbackPageType, ExperienceMode experienceMode, CoroutineScope playbackScope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(playbackScope, "playbackScope");
        this.context = context;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.experienceMode = experienceMode;
        this.playbackScope = playbackScope;
        this.enabilityProvider = new ContentMetadataEnabilityProvider();
    }

    public final void startPlayback(ContentMetadata contentMetadata, String startingTrack, boolean eligibilityVerified, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (eligibilityVerified || this.enabilityProvider.isContentAccessAvailable(contentMetadata)) {
            String str = TAG;
            Log.debug(str, Intrinsics.stringPlus("Starting playback for entity ", contentMetadata));
            if (contentMetadata instanceof AlbumMetadata) {
                startAlbumPlayback((AlbumMetadata) contentMetadata, startingTrack, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof PlaylistMetadata) {
                startPlaylistPlayback((PlaylistMetadata) contentMetadata, startingTrack, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof UserPlaylistMetadata) {
                startUserPlaylistPlayback((UserPlaylistMetadata) contentMetadata, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof StationMetadata) {
                startStationPlayback((StationMetadata) contentMetadata, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof ArtistMetadata) {
                startArtistPlayback((ArtistMetadata) contentMetadata, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof TrackMetadata) {
                startTrackPlayback((TrackMetadata) contentMetadata, isVoiceInitiated, playbackListener);
                return;
            }
            if (contentMetadata instanceof PodcastMetadata) {
                startPodcastPlayback((PodcastMetadata) contentMetadata, isVoiceInitiated);
                return;
            }
            Log.error(str, "Do not support playing " + contentMetadata + ", will not start playback");
            return;
        }
        if (contentMetadata instanceof AlbumMetadata ? true : contentMetadata instanceof PlaylistMetadata ? true : contentMetadata instanceof ArtistMetadata ? true : contentMetadata instanceof TrackMetadata ? true : contentMetadata instanceof UserPlaylistMetadata) {
            Log.warning(TAG, "User is not eligible to access " + contentMetadata + ", will start SFA instead");
            StationPlaybackUtil.INSTANCE.startSFA(contentMetadata, this.context, this.playbackPageType, this.pageType, (32 & 16) != 0 ? null : this.experienceMode, (32 & 32) != 0 ? false : false, isVoiceInitiated);
            return;
        }
        if (contentMetadata instanceof StationMetadata) {
            startStationPlayback((StationMetadata) contentMetadata, isVoiceInitiated, playbackListener);
            return;
        }
        Log.error(TAG, "Do not support playing " + contentMetadata + ", will not start playback");
    }

    private final void startAlbumPlayback(AlbumMetadata contentMetadata, final String startingTrack, final boolean isVoiceInitiated, final PlaybackListener playbackListener) {
        Log.debug(TAG, Intrinsics.stringPlus("Starting Playback for album: ", contentMetadata.getAsin()));
        VoiceUtil voiceUtil = VoiceUtil.INSTANCE;
        voiceUtil.sendClickEvent(ActionType.PLAY_ALBUM, this.pageType, this.experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(this.context, contentMetadata.getAsin(), startingTrack, this.playbackPageType, new QueryAlbumByAsin(this.context), playbackListener, new CTAPrimeCache.AlbumMetadataChangeListener() { // from class: com.amazon.mp3.auto.playback.AutoPlaybackHelper$startAlbumPlayback$playbackTask$1
            @Override // com.amazon.mp3.prime.CTAPrimeCache.AlbumMetadataChangeListener
            public void onAlbumIdChanged(long albumId) {
            }

            @Override // com.amazon.mp3.prime.CTAPrimeCache.AlbumMetadataChangeListener
            public void onAlbumAsinChanged(String albumAsin) {
                Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
                Log.debug(AutoPlaybackHelper.TAG, Intrinsics.stringPlus("album ASIN has changed, the new album ASIN is: ", albumAsin));
                PrimeCollectionTask primeCollectionTaskCreatePlaybackTask2 = PrimeCollectionTask.createPlaybackTask(this.this$0.context, albumAsin, startingTrack, this.this$0.playbackPageType, new QueryAlbumByAsin(this.this$0.context), playbackListener);
                primeCollectionTaskCreatePlaybackTask2.withPlaybackControlSource(VoiceUtil.INSTANCE.getControlSource(isVoiceInitiated));
                primeCollectionTaskCreatePlaybackTask2.execute();
            }
        });
        primeCollectionTaskCreatePlaybackTask.withPlaybackControlSource(voiceUtil.getControlSource(isVoiceInitiated));
        primeCollectionTaskCreatePlaybackTask.execute();
    }

    private final void startPlaylistPlayback(PlaylistMetadata contentMetadata, String startingTrack, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Log.debug(TAG, Intrinsics.stringPlus("Starting Playback for playlist: ", contentMetadata.getAsin()));
        VoiceUtil voiceUtil = VoiceUtil.INSTANCE;
        voiceUtil.sendClickEvent(ActionType.PLAY_PLAYLIST, this.pageType, this.experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        if (!Feature.should_migrate_free_to_cq_for_non_station.isEnabled() && PlaybackHandlerUtil.INSTANCE.isOnDemandPlaylist(contentMetadata)) {
            FreeTierPlaybackUtil.startPlaylistOnDemand(this.context, voiceUtil.getControlSource(isVoiceInitiated), new PlayableEntityIdentifier(contentMetadata.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, false, this.playbackPageType, MediaCollectionType.GOLDEN_PLAYLIST, null);
            return;
        }
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTaskCreatePlaybackTask = GetPrimePlaylistTracksTask.createPlaybackTask(this.context, contentMetadata.getAsin(), startingTrack, this.playbackPageType, playbackListener);
        getPrimePlaylistTracksTaskCreatePlaybackTask.withPlaybackControlSource(voiceUtil.getControlSource(isVoiceInitiated));
        getPrimePlaylistTracksTaskCreatePlaybackTask.execute(new Void[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [T, android.net.Uri] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, android.net.Uri, java.lang.Object] */
    private final void startUserPlaylistPlayback(UserPlaylistMetadata contentMetadata, final boolean isVoiceInitiated, PlaybackListener playbackListener) {
        String str = TAG;
        Log.debug(str, "Starting Playback for user playlist: " + contentMetadata.getPlaylistId() + ' ' + contentMetadata.getUri());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? uri = contentMetadata.getUri();
        objectRef.element = uri;
        if (Intrinsics.areEqual((Object) uri, Uri.EMPTY)) {
            objectRef.element = PlaybackHandlerUtil.INSTANCE.generateUserPlaylistContentUri(this.context, contentMetadata);
        }
        if (objectRef.element == 0) {
            Log.error(str, "Unable to get content Uri for UserPlaylist " + contentMetadata + " to start playback");
            if (playbackListener == null) {
                return;
            }
            PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.DATABASE_NO_CONTENT, null, 2, null);
            return;
        }
        VoiceUtil voiceUtil = VoiceUtil.INSTANCE;
        voiceUtil.sendClickEvent(ActionType.PLAY_PLAYLIST, this.pageType, this.experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        StartPlaybackUtil.startCollectionPlayback(this.context, (Data.MAX_DATA_BYTES & 2) != 0 ? null : contentMetadata.getTitle(), (Uri) objectRef.element, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : false, (Data.MAX_DATA_BYTES & 64) != 0, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, this.playbackPageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : false, voiceUtil.getControlSource(isVoiceInitiated), (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : playbackListener, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, false, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.auto.playback.AutoPlaybackHelper.startUserPlaylistPlayback.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                PlayQueueSequencer.getInstance().start(VoiceUtil.INSTANCE.getControlSource(isVoiceInitiated), objectRef.element, null, null, null, 0, false, new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, this.playbackPageType), null, System.currentTimeMillis());
            }
        });
    }

    private final void startStationPlayback(StationMetadata contentMetadata, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Log.debug(TAG, "Starting Playback for station: " + contentMetadata.getKey() + ' ' + contentMetadata.getUri());
        StationPlaybackUtil.INSTANCE.playStation(contentMetadata, this.context, this.playbackPageType, this.pageType, this.experienceMode, (32 & 32) != 0 ? false : false, isVoiceInitiated, (32 & 128) != 0 ? null : playbackListener);
    }

    private final void startArtistPlayback(ArtistMetadata contentMetadata, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Log.debug(TAG, "Starting Playback for artist: " + ((Object) contentMetadata.getAsin()) + ' ' + contentMetadata.getUri());
        new PrimeArtistPlaybackHandler(this.context, VoiceUtil.INSTANCE.getControlSource(isVoiceInitiated)).startPlayback(PrimeItemsTransformationUtil.convertToPrimeArtist(contentMetadata), PrimeCollectionTask.Task.PLAYBACK);
    }

    private final void startTrackPlayback(TrackMetadata contentMetadata, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Log.debug(TAG, "Starting Playback for track: " + ((Object) contentMetadata.getAsin()) + ' ' + contentMetadata.getUri());
        VoiceUtil.INSTANCE.sendClickEvent(ActionType.PLAY_SONG, this.pageType, this.experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        BuildersKt__Builders_commonKt.launch$default(this.playbackScope, null, null, new AnonymousClass1(contentMetadata, this, isVoiceInitiated, playbackListener, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.playback.AutoPlaybackHelper$startTrackPlayback$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoPlaybackHelper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.auto.playback.AutoPlaybackHelper$startTrackPlayback$1", f = "AutoPlaybackHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TrackMetadata $contentMetadata;
        final /* synthetic */ boolean $isVoiceInitiated;
        final /* synthetic */ PlaybackListener $playbackListener;
        int label;
        final /* synthetic */ AutoPlaybackHelper this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TrackMetadata trackMetadata, AutoPlaybackHelper autoPlaybackHelper, boolean z, PlaybackListener playbackListener, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$contentMetadata = trackMetadata;
            this.this$0 = autoPlaybackHelper;
            this.$isVoiceInitiated = z;
            this.$playbackListener = playbackListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$contentMetadata, this.this$0, this.$isVoiceInitiated, this.$playbackListener, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String asin = this.$contentMetadata.getAsin();
            if (asin != null) {
                AutoPlaybackHelper autoPlaybackHelper = this.this$0;
                boolean z = this.$isVoiceInitiated;
                new TracksPlaybackHelper(autoPlaybackHelper.context, PrimeCollectionTask.Task.PLAYBACK, autoPlaybackHelper.playbackPageType, VoiceUtil.INSTANCE.getControlSource(z), this.$playbackListener, false, 32, null).loadPlayback(asin);
            }
            return Unit.INSTANCE;
        }
    }

    private final void startPodcastPlayback(PodcastMetadata podcastMetadata, boolean isVoiceInitiated) {
        String target = podcastMetadata.getTarget();
        VoiceUtil.INSTANCE.sendClickEvent(DeeplinksUtils.isPodcastEpisode(target) ? ActionType.PLAY_PODCAST_EPISODE : ActionType.PLAY_PODCAST_SHOW, this.pageType, this.experienceMode, podcastMetadata.getViewId(), isVoiceInitiated);
        PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).pause();
        Podcast.create(getBootstrap(target)).startPlayback();
    }

    private final Bootstrap getBootstrap(String deeplinkPath) {
        PodcastDeeplink podcastDeeplink = new PodcastDeeplink(deeplinkPath, false, CarModeUtility.isInCarMode() ? PodcastDeeplink.Source.CARMODE : null);
        Context context = this.context;
        UserInfo userInfo = new UserInfo();
        ActionBarInfo actionBarInfo = ActionBarInfo.INSTANCE;
        FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
        NetworkSettings networkSettings = new NetworkSettings();
        DownloadSettings downloadSettings = new DownloadSettings(null);
        PodcastSharingProvider podcastSharingProvider = new PodcastSharingProvider();
        FeatureGateProviderImpl featureGateProviderImpl = new FeatureGateProviderImpl();
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Intrinsics.checkNotNullExpressionValue(currentActivity, "getCurrentActivity()");
        return new Bootstrap(podcastDeeplink, context, userInfo, actionBarInfo, fragmentInfo, networkSettings, downloadSettings, podcastSharingProvider, featureGateProviderImpl, false, new PodcastDynamicMessaging(currentActivity), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }
}
