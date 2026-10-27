package com.amazon.mp3.curate.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playback.handler.StationPlaybackHandler;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.exception.PlaybackErrorHandler;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.voice.deeplink.StationItemProvider;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.provider.MusicItem;
import com.amazon.music.curate.provider.PlaybackItem;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaybackStatus;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.station.StationItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CuratePlaybackProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 H2\u00020\u0001:\u0001HB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\rH\u0002J\u0012\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000bH\u0016J!\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0002\u0010\u001bJ0\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0016J8\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0005H\u0003JD\u0010$\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016JD\u0010,\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016JD\u0010-\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016J<\u0010.\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010/\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016J<\u00100\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00101\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016JD\u00102\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016J\u001a\u00102\u001a\u00020\u001d2\u0006\u00105\u001a\u0002062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016JD\u00107\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00104\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0016J@\u00108\u001a\u00020\u001d2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<2\b\u00101\u001a\u0004\u0018\u00010&2\u0018\u0010*\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0+0\u001fH\u0002J\u0012\u0010=\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J(\u0010>\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J*\u0010?\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0005H\u0002J*\u0010@\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010A\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010B\u001a\u00020CH\u0003J*\u0010D\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010A\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010B\u001a\u00020CH\u0002J\b\u0010E\u001a\u00020\u001dH\u0002J\b\u0010F\u001a\u00020\u001dH\u0002J\b\u0010G\u001a\u00020\u001dH\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/amazon/mp3/curate/provider/CuratePlaybackProvider;", "Lcom/amazon/music/curate/provider/PlaybackProvider;", "()V", "isPlayStateChangedLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "liveData", "Lcom/amazon/music/curate/provider/PlaybackItem;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackItem", "Landroidx/lifecycle/LiveData;", "getPlaylistTracksUri", "Landroid/net/Uri;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "context", "Landroid/content/Context;", "isCurrentlyPlaying", "contentUri", "isNetworkAvailable", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "isPlayStateChanged", "isPlaying", "musicItem", "Lcom/amazon/music/curate/provider/MusicItem;", "(Landroid/content/Context;Lcom/amazon/music/curate/provider/MusicItem;)Ljava/lang/Boolean;", PageUriUtils.DO_PLAY_VALUE, "", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "index", "", "isShuffled", "playAlbum", "asin", "", "isShuffleOn", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", ContextMappingConstants.CONTENT_INFO, "", "playArtist", "playCatalogPlaylist", "playPlaylistOnDemand", "playlistId", "playSFA", ContextMappingConstants.ENTITY_ID, "playStation", "idType", "id", "mySoundtrack", "Lcom/amazon/music/curate/model/Station;", "playUserPlaylist", "sendUiClickMetrics", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "showPlaybackErrorDialog", "shuffle", "startPlayQueueSequencerAndNowPlaying", "startPlayback", "uri", "metricInformation", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "startPlaybackHelper", "stopAndClearQueue", "updatePlaybackItem", "updatePlaybackState", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CuratePlaybackProvider implements PlaybackProvider {
    private static final Logger LOG;
    private final MutableLiveData<Boolean> isPlayStateChangedLiveData;
    private final MutableLiveData<PlaybackItem> liveData;
    private final ActionValidatedPlaybackController playbackController;

    public CuratePlaybackProvider() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.NONE);
        this.playbackController = controller;
        this.liveData = new MutableLiveData<>();
        updatePlaybackItem();
        controller.addOnPlayStateChangedListener(new OnPlayStateChangedListener() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda3
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                CuratePlaybackProvider.m769_init_$lambda0(this.f$0);
            }
        });
        this.isPlayStateChangedLiveData = new MutableLiveData<>();
    }

    static {
        Logger logger = LoggerFactory.getLogger(CuratePlaybackProvider.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(CuratePlayback…r::class.java.simpleName)");
        LOG = logger;
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void shuffle(FragmentActivity activity, Playlist playlist, List<Track> tracks) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        play(activity, playlist, tracks, 0, true);
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playStation(station mySoundtrack, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(mySoundtrack, "mySoundtrack");
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(ActionType.PLAY_STATION);
        Boolean isPrime = mySoundtrack.getIsPrime();
        Intrinsics.checkNotNull(isPrime);
        boolean zBooleanValue = isPrime.booleanValue();
        Boolean isMusicUnlimited = mySoundtrack.getIsMusicUnlimited();
        Intrinsics.checkNotNull(isMusicUnlimited);
        boolean zBooleanValue2 = isMusicUnlimited.booleanValue();
        Boolean isFree = mySoundtrack.getIsFree();
        Intrinsics.checkNotNull(isFree);
        boolean zBooleanValue3 = isFree.booleanValue();
        Boolean isFreeOnDemandPlayable = mySoundtrack.getIsFreeOnDemandPlayable();
        Intrinsics.checkNotNull(isFreeOnDemandPlayable);
        new StationPlaybackHandler(activity, PlaybackPageType.MY_SOUNDTRACK).checkCanStartPlayback(new Station(new CatalogStatusTiers(zBooleanValue, zBooleanValue2, zBooleanValue3, isFreeOnDemandPlayable.booleanValue(), false, false, false, 112, null), mySoundtrack.getTitle(), mySoundtrack.getImageUrl(), mySoundtrack.getStationKey(), mySoundtrack.getExplicitMap()), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m769_init_$lambda0(CuratePlaybackProvider this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePlaybackItem();
        this$0.updatePlaybackState();
    }

    private final void updatePlaybackItem() {
        MediaItemId mediaItemId;
        PlayStatus playStatus = this.playbackController.getPlayStatus();
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        PlaybackItem playbackItem = null;
        String id = (currentMediaItem == null || (mediaItemId = currentMediaItem.getMediaItemId()) == null) ? null : mediaItemId.getId();
        if (playStatus != null && id != null) {
            playbackItem = new PlaybackItem(id, playStatus == PlayStatus.RENDERING ? PlaybackStatus.PLAYING : PlaybackStatus.PAUSED);
        }
        this.liveData.postValue(playbackItem);
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void play(FragmentActivity activity, Playlist playlist, List<Track> tracks, int index) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        play(activity, playlist, tracks, index, false);
    }

    @SuppressLint({"CheckResult"})
    private final void play(final FragmentActivity activity, final Playlist playlist, List<Track> tracks, final int index, final boolean isShuffled) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            Context applicationContext = activity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
            Context applicationContext2 = activity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "activity.applicationContext");
            Uri playlistTracksUri = getPlaylistTracksUri(playlist, applicationContext2);
            PlaybackPageType playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            StartPlaybackUtil.startCollectionPlayback(applicationContext, (Data.MAX_DATA_BYTES & 2) != 0 ? null : null, playlistTracksUri, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : isShuffled, (Data.MAX_DATA_BYTES & 64) != 0, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, playbackPageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : false, APP_UI, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider.play.1
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public void handlePlayback() {
                    CuratePlaybackProvider.this.startPlayQueueSequencerAndNowPlaying(activity, playlist, index, isShuffled);
                }
            });
            return;
        }
        startPlayQueueSequencerAndNowPlaying(activity, playlist, index, isShuffled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlayQueueSequencerAndNowPlaying(FragmentActivity activity, Playlist playlist, int index, boolean isShuffled) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, getPlaylistTracksUri(playlist, activity), null, null, null, index, isShuffled, new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, PlaybackPageType.PLAYLIST_DETAIL), null, Clock.now());
        activity.startActivity(LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI));
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
    }

    private final Uri getPlaylistTracksUri(Playlist playlist, Context context) {
        Uri uri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlist.getId()));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        return uri;
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public LiveData<PlaybackItem> getPlaybackItem() {
        return this.liveData;
    }

    private final void updatePlaybackState() {
        PlayStatus playStatus = this.playbackController.getPlayStatus();
        if (playStatus == PlayStatus.RENDERING || playStatus == PlayStatus.USER_PAUSED || playStatus == PlayStatus.SYSTEM_PAUSED) {
            this.isPlayStateChangedLiveData.postValue(Boolean.TRUE);
        }
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public LiveData<Boolean> isPlayStateChanged() {
        return this.isPlayStateChangedLiveData;
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public Boolean isPlaying(Context context, MusicItem musicItem) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (musicItem == null) {
            return null;
        }
        Uri uri = new MusicItemContentUri(context).getUri(musicItem, false);
        if (!MediaItemHelper.isCurrentlyPlayingCollection(uri)) {
            return null;
        }
        if (this.playbackController.getPlayStatus().shouldBePlaying()) {
            LOG.debug("{} is playing", String.valueOf(uri));
            return Boolean.TRUE;
        }
        LOG.debug("{} is paused", String.valueOf(uri));
        return Boolean.FALSE;
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playUserPlaylist(final FragmentActivity activity, final String id, final boolean isShuffleOn, final PlaybackPageType playbackPageType, final List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CuratePlaybackProvider.m773playUserPlaylist$lambda2(activity, id, this, contentInfo, playbackPageType, isShuffleOn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playUserPlaylist$lambda-2, reason: not valid java name */
    public static final void m773playUserPlaylist$lambda2(FragmentActivity fragmentActivity, String id, CuratePlaybackProvider this$0, List contentInfo, PlaybackPageType playbackPageType, boolean z) {
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentInfo, "$contentInfo");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        if (fragmentActivity == null) {
            return;
        }
        Uri contentUriFromLuid = MediaProvider.UdoPlaylists.getContentUriFromLuid(fragmentActivity.getApplicationContext(), "cirrus", id);
        if (contentUriFromLuid == null) {
            LOG.warn("null uri for user playlist id {}", id);
            this$0.showPlaybackErrorDialog(fragmentActivity);
            return;
        }
        this$0.sendUiClickMetrics(ActionType.PLAY_PLAYLIST, EntityIdType.PLAYLIST_ID, id, contentInfo);
        Uri uri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(contentUriFromLuid);
        PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, playbackPageType);
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        this$0.startPlayback(fragmentActivity, uri, z, playbackMetricInformation);
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playCatalogPlaylist(final FragmentActivity activity, final String asin, final boolean isShuffleOn, final PlaybackPageType playbackPageType, final List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
                CuratePlaybackProvider.m772playCatalogPlaylist$lambda4(activity, asin, this, contentInfo, playbackPageType, isShuffleOn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playCatalogPlaylist$lambda-4, reason: not valid java name */
    public static final void m772playCatalogPlaylist$lambda4(FragmentActivity fragmentActivity, String asin, CuratePlaybackProvider this$0, List contentInfo, PlaybackPageType playbackPageType, boolean z) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentInfo, "$contentInfo");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        if (fragmentActivity == null) {
            return;
        }
        PrimePlaylistUtil.fetchPrimePlaylist(new PrimePlaylistDatabaseManager(fragmentActivity), 0, asin);
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", asin);
        if (contentUri == null) {
            LOG.warn("null uri for catalog playlist asin {}", asin);
            this$0.showPlaybackErrorDialog(fragmentActivity);
        } else {
            this$0.sendUiClickMetrics(ActionType.PLAY_PLAYLIST, EntityIdType.ASIN, asin, contentInfo);
            this$0.startPlayback(fragmentActivity, contentUri, z, new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, playbackPageType));
        }
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playPlaylistOnDemand(FragmentActivity activity, String playlistId, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        if (Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            playCatalogPlaylist(activity, playlistId, false, playbackPageType, contentInfo);
            return;
        }
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        sendUiClickMetrics(ActionType.PLAY_PLAYLIST, EntityIdType.ASIN, playlistId, contentInfo);
        FreeTierPlaybackUtil.startPlaylistOnDemand(activity, new PlayableEntityIdentifier(playlistId, PlayableEntityIdentifierType.ASIN), true, true, true, playbackPageType, MediaCollectionType.GOLDEN_PLAYLIST, null);
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playSFA(FragmentActivity activity, String entityId, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        sendUiClickMetrics(ActionType.PLAY_STATION, EntityIdType.ASIN, entityId, contentInfo);
        if (playbackPageType == PlaybackPageType.PLAYLIST_DETAIL) {
            UpsellUtil.startStationFromAnything(activity, StationUtils.getAsinListSeedFromUserPlaylistLuid(activity, entityId), UpsellSourceEntity.PLAYLIST, MediaCollectionType.USER_PLAYLIST, false);
        } else {
            StationUtils.startSFA(activity, entityId, PlayableEntityIdentifierType.ASIN.toString(), playbackPageType, ControlSource.APP_UI);
        }
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playStation(FragmentActivity activity, String idType, String id, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo) {
        StationItem stationItemWithSeedId;
        EntityIdType entityIdType;
        Uri contentUriWithSeeds;
        Intrinsics.checkNotNullParameter(idType, "idType");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        if (Touch.SyncInterface.v1_0.EntityIdType.STATION_KEY.name().equals(idType)) {
            stationItemWithSeedId = new StationItemProvider().getStationItemWithKey(id, "", "");
            entityIdType = EntityIdType.STATION_KEY;
            contentUriWithSeeds = MediaProvider.Station.getContentUri(id);
            Intrinsics.checkNotNullExpressionValue(contentUriWithSeeds, "getContentUri(id)");
        } else {
            stationItemWithSeedId = new StationItemProvider().getStationItemWithSeedId(id, "", "");
            entityIdType = EntityIdType.STATION_SEED_ID;
            contentUriWithSeeds = MediaProvider.Station.getContentUriWithSeeds(id);
            Intrinsics.checkNotNullExpressionValue(contentUriWithSeeds, "getContentUriWithSeeds(id)");
        }
        if (stationItemWithSeedId == null) {
            LOG.warn("failed to get stationItem idtype = {}, id = {}", idType, id);
            showPlaybackErrorDialog(activity);
        }
        sendUiClickMetrics(ActionType.PLAY_STATION, entityIdType, id, contentInfo);
        if (isCurrentlyPlaying(contentUriWithSeeds)) {
            activity.startActivity(LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI));
            activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
        } else {
            stopAndClearQueue();
            new StationPlaybackHandler(activity, playbackPageType).checkCanStartPlayback(PrimeItemsTransformationUtil.toPrimeStation(stationItemWithSeedId), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
        }
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playAlbum(final FragmentActivity activity, final String asin, final boolean isShuffleOn, final PlaybackPageType playbackPageType, final List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException {
                CuratePlaybackProvider.m770playAlbum$lambda6(activity, asin, this, contentInfo, playbackPageType, isShuffleOn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playAlbum$lambda-6, reason: not valid java name */
    public static final void m770playAlbum$lambda6(FragmentActivity fragmentActivity, String asin, CuratePlaybackProvider this$0, List contentInfo, PlaybackPageType playbackPageType, boolean z) throws ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentInfo, "$contentInfo");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        if (fragmentActivity == null) {
            return;
        }
        new CtaAlbumRequester(fragmentActivity.getApplicationContext(), true).requestTracksForAsin(asin);
        Uri contentUri = MediaProvider.Albums.getContentUri("cirrus");
        if (contentUri == null) {
            LOG.warn("null uri for album sin {}", asin);
            this$0.showPlaybackErrorDialog(fragmentActivity);
            return;
        }
        Long albumOrArtistIdByAsin = CirrusMediaSource.getAlbumOrArtistIdByAsin(fragmentActivity.getApplicationContext(), contentUri, asin);
        if (albumOrArtistIdByAsin != null) {
            this$0.sendUiClickMetrics(ActionType.PLAY_ALBUM, EntityIdType.ASIN, asin, contentInfo);
            Uri albumTracksUri = MediaProvider.Albums.Tracks.getContentUri("cirrus", albumOrArtistIdByAsin.longValue());
            PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, playbackPageType);
            Intrinsics.checkNotNullExpressionValue(albumTracksUri, "albumTracksUri");
            this$0.startPlayback(fragmentActivity, albumTracksUri, z, playbackMetricInformation);
        }
    }

    @Override // com.amazon.music.curate.provider.PlaybackProvider
    public void playArtist(final FragmentActivity activity, final String asin, final boolean isShuffleOn, final PlaybackPageType playbackPageType, final List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CuratePlaybackProvider.m771playArtist$lambda8(activity, asin, this, contentInfo, playbackPageType, isShuffleOn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playArtist$lambda-8, reason: not valid java name */
    public static final void m771playArtist$lambda8(FragmentActivity fragmentActivity, String asin, CuratePlaybackProvider this$0, List contentInfo, PlaybackPageType playbackPageType, boolean z) {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentInfo, "$contentInfo");
        Intrinsics.checkNotNullParameter(playbackPageType, "$playbackPageType");
        if (fragmentActivity == null) {
            return;
        }
        Uri contentUri = MediaProvider.Artists.getContentUri("cirrus");
        if (Intrinsics.areEqual(asin, "0") || contentUri == null) {
            LOG.warn("invalid uri for artist asin {}", asin);
            this$0.showPlaybackErrorDialog(fragmentActivity);
            return;
        }
        Long albumOrArtistIdByAsin = CirrusMediaSource.getAlbumOrArtistIdByAsin(fragmentActivity.getApplicationContext(), contentUri, asin);
        if (albumOrArtistIdByAsin != null) {
            this$0.sendUiClickMetrics(ActionType.PLAY_ARTIST, EntityIdType.ASIN, asin, contentInfo);
            Uri artistTracksUri = MediaProvider.Artists.Tracks.getContentUri("cirrus", albumOrArtistIdByAsin.longValue());
            PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, playbackPageType);
            Intrinsics.checkNotNullExpressionValue(artistTracksUri, "artistTracksUri");
            this$0.startPlayback(fragmentActivity, artistTracksUri, z, playbackMetricInformation);
        }
    }

    @SuppressLint({"CheckResult"})
    private final void startPlayback(final FragmentActivity activity, final Uri uri, final boolean isShuffleOn, final PlaybackMetricInformation metricInformation) {
        if (activity == null || !isNetworkAvailable(activity)) {
            return;
        }
        if (uri == null) {
            LOG.warn("can't start playback when uri is null");
            showPlaybackErrorDialog(activity);
            return;
        }
        if (isCurrentlyPlaying(uri)) {
            activity.startActivity(LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI));
            activity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            return;
        }
        stopAndClearQueue();
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
            Context applicationContext = activity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
            Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(uri);
            Uri uri2 = nonTrackCollectionUri == null ? uri : nonTrackCollectionUri;
            PlaybackPageType pageType = metricInformation.getPageType();
            Intrinsics.checkNotNullExpressionValue(pageType, "metricInformation.pageType");
            ControlSource APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
            StartPlaybackUtil.startCollectionPlayback(applicationContext, (Data.MAX_DATA_BYTES & 2) != 0 ? null : null, uri2, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : isShuffleOn, (Data.MAX_DATA_BYTES & 64) != 0 ? true : true, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, pageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : false, APP_UI, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider.startPlayback.1
                @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                public void handlePlayback() {
                    CuratePlaybackProvider.this.startPlaybackHelper(activity, uri, isShuffleOn, metricInformation);
                }
            });
            return;
        }
        startPlaybackHelper(activity, uri, isShuffleOn, metricInformation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlaybackHelper(FragmentActivity activity, Uri uri, boolean isShuffleOn, PlaybackMetricInformation metricInformation) {
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, uri, null, null, null, 0, isShuffleOn, metricInformation, null, Clock.now());
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", uri);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", 0);
        if (activity == null) {
            return;
        }
        activity.startActivity(intentForContentUri);
    }

    private final boolean isCurrentlyPlaying(Uri contentUri) {
        Uri currentUri;
        if (contentUri == null || (currentUri = MediaItemHelper.getCurrentUri()) == null) {
            return false;
        }
        Uri uriBuild = contentUri.buildUpon().appendPath("tracks").build();
        return (contentUri.equals(currentUri) || uriBuild.equals(currentUri)) && !MediaProvider.PrimeBrowseTracksCollection.isCollection(uriBuild);
    }

    private final void stopAndClearQueue() {
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
        PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.curate.provider.CuratePlaybackProvider$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CuratePlaybackProvider.m774stopAndClearQueue$lambda9();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopAndClearQueue$lambda-9, reason: not valid java name */
    public static final void m774stopAndClearQueue$lambda9() {
        PlayQueueSequencer.getInstance().clear();
    }

    private final void sendUiClickMetrics(ActionType actionType, EntityIdType entityIdType, String entityId, List<? extends Map<String, String>> contentInfo) {
        if (actionType != null) {
            MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
            UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(actionType).withPageType(PageType.LIBRARY_LANDING_PAGE).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis());
            if (entityIdType != null) {
                builderWithEventTime.withEntityIdType(entityIdType);
            }
            if (entityId != null) {
                builderWithEventTime.withEntityId(entityId);
            }
            if (!contentInfo.isEmpty()) {
                builderWithEventTime.withContentInfo(contentInfo);
            }
            MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
        }
    }

    private final boolean isNetworkAvailable(FragmentActivity activity) {
        if (activity == null || ConnectivityUtil.hasAnyInternetConnection(activity.getApplicationContext())) {
            return true;
        }
        activity.startActivity(NetworkErrorDialogActivity.getStartIntent(activity));
        return false;
    }

    private final void showPlaybackErrorDialog(FragmentActivity activity) {
        if (activity == null) {
            return;
        }
        activity.startActivity(PlaybackErrorHandler.getNotificationIntent(activity, PlaybackException.PlaybackError.StreamingPlayback));
    }
}
