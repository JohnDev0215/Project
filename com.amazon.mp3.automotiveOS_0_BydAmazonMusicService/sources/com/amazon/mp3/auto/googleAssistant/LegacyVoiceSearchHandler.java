package com.amazon.mp3.auto.googleAssistant;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import com.amazon.mp3.auto.AutoHelper;
import com.amazon.mp3.auto.SubDeviceConnectionUtils;
import com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler$primePlaylistListener$2;
import com.amazon.mp3.auto.playback.ContentMetadataPlaybackHandler;
import com.amazon.mp3.auto.provider.AutoItem;
import com.amazon.mp3.auto.provider.AutoSearchBrowser;
import com.amazon.mp3.auto.provider.AutoSearchListener;
import com.amazon.mp3.auto.provider.GetPrimePlaylistTask;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.recentlyplayed.RecentsPlaybackHelper;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.auto.googleAssistant.GoogleAssistantError;
import com.amazon.music.media.auto.googleAssistant.GoogleAssistantVoiceSearchQuery;
import com.amazon.music.media.auto.googleAssistant.GoogleAssistantVoiceSearchUtility;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.util.GoogleAssistantMetricsUtil;
import com.amazon.music.media.auto.util.PlaybackStateUtility;
import com.amazon.music.media.auto.util.SubDeviceConnectionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.config.OnMediaSearchHandler;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.station.StationItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyVoiceSearchHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010 \u001a\u00020!J\u001a\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J \u0010$\u001a\u00020\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'J$\u0010(\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010%\u001a\u00020!H\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006+"}, d2 = {"Lcom/amazon/mp3/auto/googleAssistant/LegacyVoiceSearchHandler;", "Lcom/amazon/music/media/playback/config/OnMediaSearchHandler;", "context", "Landroid/content/Context;", "mediaBrowser", "Lcom/amazon/mp3/auto/provider/AutoSearchBrowser;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "(Landroid/content/Context;Lcom/amazon/mp3/auto/provider/AutoSearchBrowser;Landroid/support/v4/media/session/MediaSessionCompat;)V", "TAG", "", "kotlin.jvm.PlatformType", "primePlaylistListener", "Lcom/amazon/mp3/auto/provider/GetPrimePlaylistTask$PrimePlaylistRetrievedListener;", "getPrimePlaylistListener", "()Lcom/amazon/mp3/auto/provider/GetPrimePlaylistTask$PrimePlaylistRetrievedListener;", "primePlaylistListener$delegate", "Lkotlin/Lazy;", "convertAlbumMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "autoItem", "Lcom/amazon/mp3/auto/provider/AutoItem;", "convertArtistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "convertContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "convertTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "handlePauseRequest", "", "handlePlayFromSearch", SearchIntents.EXTRA_QUERY, "startPlayBack", "", "handlePlaybackError", "contentMetadata", "loadTracks", "startPlayback", "requestTime", "", "onMediaSearch", "bundle", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LegacyVoiceSearchHandler implements OnMediaSearchHandler {
    private final String TAG;
    private final Context context;
    private final AutoSearchBrowser mediaBrowser;
    private final MediaSessionCompat mediaSession;

    /* JADX INFO: renamed from: primePlaylistListener$delegate, reason: from kotlin metadata */
    private final Lazy primePlaylistListener;

    /* JADX INFO: compiled from: LegacyVoiceSearchHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutoItem.AutoItemType.values().length];
            iArr[AutoItem.AutoItemType.ARTIST.ordinal()] = 1;
            iArr[AutoItem.AutoItemType.ALBUM.ordinal()] = 2;
            iArr[AutoItem.AutoItemType.TRACK.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LegacyVoiceSearchHandler(Context context, AutoSearchBrowser mediaBrowser, MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        this.context = context;
        this.mediaBrowser = mediaBrowser;
        this.mediaSession = mediaSession;
        this.TAG = LegacyVoiceSearchHandler.class.getSimpleName();
        this.primePlaylistListener = LazyKt__LazyJVMKt.lazy(new Function0<LegacyVoiceSearchHandler$primePlaylistListener$2.AnonymousClass1>() { // from class: com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler$primePlaylistListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler$primePlaylistListener$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final LegacyVoiceSearchHandler legacyVoiceSearchHandler = this.this$0;
                return new GetPrimePlaylistTask.PrimePlaylistRetrievedListener() { // from class: com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler$primePlaylistListener$2.1
                    @Override // com.amazon.mp3.auto.provider.GetPrimePlaylistTask.PrimePlaylistRetrievedListener
                    public void onPrimePlaylistRetrieved(CatalogPlaylist catalogPlaylist, boolean startCallBack, long requestTime) {
                        if (catalogPlaylist == null) {
                            return;
                        }
                        if (UserSubscriptionUtil.isNightwingOnly()) {
                            if (catalogPlaylist.isFree() && catalogPlaylist.isFreeOnDemand()) {
                                FreeTierPlaybackUtil.playCloudStation(legacyVoiceSearchHandler.context, new PlayableEntityIdentifier(catalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, false, PlaybackPageType.GOLDEN_PLAYLIST);
                                return;
                            } else {
                                FreeTierPlaybackUtil.playCloudStation(legacyVoiceSearchHandler.context, (List<PlayableEntityIdentifier>) CollectionsKt__CollectionsJVMKt.listOf(new PlayableEntityIdentifier(catalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN)), true, false, false, PlaybackPageType.SFA, Clock.now(), (UpsellInformation) null);
                                return;
                            }
                        }
                        String asin = catalogPlaylist.getAsin();
                        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        Set setEmptySet = SetsKt__SetsKt.emptySet();
                        Uri contentUri = catalogPlaylist.getContentUri();
                        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
                        PlaylistMetadata playlistMetadata = new PlaylistMetadata(null, contentUri, null, asin, null, null, null, null, null, null, null, setEmptySet, listEmptyList, false, null, null, null, null, false, null, false, null, null, false, null, 33548277, null);
                        Log.debug(legacyVoiceSearchHandler.TAG, Intrinsics.stringPlus("Starting playback for prime playlist entity ", playlistMetadata));
                        ContentMetadataPlaybackHandler.handle$default(new ContentMetadataPlaybackHandler(legacyVoiceSearchHandler.context, PageType.UNKNOWN, PlaybackPageType.UNKNOWN, null, null, null, 56, null), playlistMetadata, true, false, null, 12, null);
                    }

                    @Override // com.amazon.mp3.auto.provider.GetPrimePlaylistTask.PrimePlaylistRetrievedListener
                    public void onPlaylistRetrievalFailed(Exception e) {
                        Intrinsics.checkNotNullParameter(e, "e");
                        Log.error(legacyVoiceSearchHandler.TAG, "onPlaylistRetrievalFailed: ", e);
                    }
                };
            }
        });
    }

    private final GetPrimePlaylistTask.PrimePlaylistRetrievedListener getPrimePlaylistListener() {
        return (GetPrimePlaylistTask.PrimePlaylistRetrievedListener) this.primePlaylistListener.getValue();
    }

    @Override // com.amazon.music.media.playback.config.OnMediaSearchHandler
    public void onMediaSearch(String query, Bundle bundle, boolean startPlayback) {
        Log.debug(this.TAG, "onMediaSearch with query %s", query);
        GoogleAssistantVoiceSearchQuery onPlayFromSearch = GoogleAssistantVoiceSearchUtility.INSTANCE.parseOnPlayFromSearch(bundle, query);
        String query2 = onPlayFromSearch.getQuery();
        String album = onPlayFromSearch.getAlbum();
        String artist = onPlayFromSearch.getArtist();
        String genre = onPlayFromSearch.getGenre();
        String playlist = onPlayFromSearch.getPlaylist();
        String title = onPlayFromSearch.getTitle();
        String focus = onPlayFromSearch.getFocus();
        SubDeviceConnectionUtil.DefaultImpls.sendSubDeviceConnectedEvent$default(SubDeviceConnectionUtils.INSTANCE, SubDeviceType.GOOGLE_ASSISTANT_VOICE.toString(), false, 2, null);
        MetricsLogger.sendThirdPartyVoiceSearchEvent(ThirdPartyVoiceAssistantName.GOOGLE_ASSISTANT.name(), query2, album, artist, genre, playlist, title, focus, null, null);
        Log.info(this.TAG, "Calling legacy Tenzing Search for Google Assistant voice search");
        handlePlayFromSearch(query, startPlayback);
    }

    public final void handlePlayFromSearch(final String query, final boolean startPlayBack) {
        Log.debug(this.TAG, "handlePlayFromSearch: query=%s", query);
        handlePauseRequest();
        String str = this.TAG;
        Object[] objArr = new Object[1];
        objArr[0] = query == null ? "empty query" : query;
        Log.debug(str, "starting voice search with query: %s", objArr);
        final long jNow = Clock.now();
        AutoSearchBrowser autoSearchBrowser = this.mediaBrowser;
        if (autoSearchBrowser != null) {
            autoSearchBrowser.search(query, new AutoSearchListener() { // from class: com.amazon.mp3.auto.googleAssistant.LegacyVoiceSearchHandler$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.auto.provider.AutoSearchListener
                public final void onAutoSearchCompleted(AutoItem autoItem) {
                    LegacyVoiceSearchHandler.m353handlePlayFromSearch$lambda0(this.f$0, query, startPlayBack, jNow, autoItem);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePlayFromSearch$lambda-0, reason: not valid java name */
    public static final void m353handlePlayFromSearch$lambda0(LegacyVoiceSearchHandler this$0, String str, boolean z, long j, AutoItem autoItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (autoItem == null) {
            Log.debug(this$0.TAG, "No search results from query: %s", str);
        } else {
            Log.debug(this$0.TAG, "Search result from query: %s is: %s", str, autoItem.getTitle());
            this$0.loadTracks(autoItem, z, j);
        }
    }

    private final void handlePauseRequest() {
        PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).pause();
    }

    public final void loadTracks(AutoItem autoItem, boolean startPlayback, long requestTime) {
        List listListOf;
        if (autoItem == null) {
            Log.debug(this.TAG, "loadTracks: Auto Item is null");
            return;
        }
        Log.debug(this.TAG, "loading tracks");
        Uri contentUri = autoItem.getContentUri();
        Log.debug(this.TAG, "loading content uri: " + contentUri + " from item with type: " + autoItem.getType());
        if (autoItem.isRecentlyPlayed()) {
            if (!UserSubscriptionUtil.isNightwingOnly()) {
                if (startPlayback) {
                    RecentsPlaybackHelper.handleRemoteRecentPlayback(this.context, autoItem.getRecentlyPlayedItem(), false);
                    return;
                }
                return;
            } else if (Intrinsics.areEqual(MediaProvider.getSource(autoItem.getContentUri()), "cirrus-local")) {
                NowPlayingManager.getInstance().restore(autoItem.getTrackProviderState(), startPlayback);
                return;
            } else {
                RecentsPlaybackHelper.handleRemoteRecentPlaybackForNightwing(this.context, autoItem.getRecentlyPlayedItem(), false);
                return;
            }
        }
        if (autoItem.getType() == AutoItem.AutoItemType.PRIME_STATION && autoItem.getStationItem() != null) {
            StationItem stationItem = autoItem.getStationItem();
            if (UserSubscriptionUtil.isNightwingOnly()) {
                if (TextUtils.isEmpty(stationItem.getSFASeed())) {
                    listListOf = CollectionsKt__CollectionsJVMKt.listOf(new PlayableEntityIdentifier(stationItem.getKey(), PlayableEntityIdentifierType.STATION_KEY));
                } else {
                    listListOf = CollectionsKt__CollectionsJVMKt.listOf(new PlayableEntityIdentifier(stationItem.getSFASeed(), PlayableEntityIdentifierType.STATION_SEED_ID));
                }
                FreeTierPlaybackUtil.playCloudStation(this.context, (List<PlayableEntityIdentifier>) listListOf, startPlayback, true, false, PlaybackPageType.SFA, Clock.now(), (UpsellInformation) null);
                return;
            }
            if (TextUtils.isEmpty(stationItem.getSFASeed())) {
                StationUtils.playStation(this.context, stationItem, startPlayback, false, PlaybackPageType.PRIME_BROWSE_STATIONS_LIST, requestTime, (UpsellInformation) null, ControlSource.APP_UI, true);
                return;
            } else {
                StationUtils.startSFA(this.context, stationItem.getSFASeed(), "STATION_SEED_ID", startPlayback, false, PlaybackPageType.RECENTLY_PLAYED, null, ControlSource.APP_UI, true);
                return;
            }
        }
        if (autoItem.getType() == AutoItem.AutoItemType.PRIME_PLAYLIST) {
            new GetPrimePlaylistTask(this.context, getPrimePlaylistListener(), new PrimePlaylistDatabaseManager(this.context), MediaProvider.Playlists.getCatalogPlaylistId(contentUri), PrimePlaylistsTable.translateSource("cirrus"), false, startPlayback, requestTime).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        if (autoItem.getType() != AutoItem.AutoItemType.SHUFFLE_ALL_TRACKS || AutoHelper.hasMusic(this.context, autoItem.getContentUri())) {
            ContentMetadata contentMetadataConvertContentMetadata = convertContentMetadata(autoItem);
            Log.debug(this.TAG, Intrinsics.stringPlus("Starting playback for entity ", contentMetadataConvertContentMetadata));
            if (contentMetadataConvertContentMetadata != null) {
                String asin = autoItem.getAsin();
                if (!(asin == null || asin.length() == 0)) {
                    try {
                        ContentMetadataPlaybackHandler.handle$default(new ContentMetadataPlaybackHandler(this.context, PageType.UNKNOWN, PlaybackPageType.UNKNOWN, null, null, null, 56, null), contentMetadataConvertContentMetadata, true, false, null, 12, null);
                        return;
                    } catch (Exception e) {
                        Log.error(this.TAG, Intrinsics.stringPlus("Error starting playback for entity ", contentMetadataConvertContentMetadata), e);
                        String strStringPlus = Intrinsics.stringPlus("Unknown runtime playback error ", e.getMessage());
                        PlaybackStateUtility.INSTANCE.setErrorPlaybackState(this.mediaSession, this.context, Bundle.EMPTY, null, null);
                        GoogleAssistantMetricsUtil googleAssistantMetricsUtil = GoogleAssistantMetricsUtil.INSTANCE;
                        GoogleAssistantError googleAssistantError = GoogleAssistantError.PLAYBACK_ERROR;
                        String TAG = this.TAG;
                        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                        googleAssistantMetricsUtil.emitGoogleAssistantVoiceFailedMetric(googleAssistantError, strStringPlus, TAG);
                        return;
                    }
                }
            }
            handlePlaybackError(contentMetadataConvertContentMetadata, autoItem);
        }
    }

    private final void handlePlaybackError(ContentMetadata contentMetadata, AutoItem autoItem) {
        String strStringPlus = "Content metadata was null";
        if (contentMetadata == null) {
            Log.error(this.TAG, "Cannot start playback for null content metadata");
        } else {
            String asin = autoItem.getAsin();
            if (asin == null || asin.length() == 0) {
                Log.error(this.TAG, Intrinsics.stringPlus("Cannot start playback for content metadata with null asin. Content uri: ", autoItem.getContentUri()));
                strStringPlus = Intrinsics.stringPlus("asin in content metadata was null. Content uri: ", autoItem.getContentUri());
            }
        }
        PlaybackStateUtility.INSTANCE.setErrorPlaybackState(this.mediaSession, this.context, Bundle.EMPTY, null, null);
        GoogleAssistantMetricsUtil googleAssistantMetricsUtil = GoogleAssistantMetricsUtil.INSTANCE;
        GoogleAssistantError googleAssistantError = GoogleAssistantError.PLAYBACK_ERROR;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        googleAssistantMetricsUtil.emitGoogleAssistantVoiceFailedMetric(googleAssistantError, strStringPlus, TAG);
    }

    private final ContentMetadata convertContentMetadata(AutoItem autoItem) {
        AutoItem.AutoItemType type = autoItem.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            return convertArtistMetadata(autoItem);
        }
        if (i == 2) {
            return convertAlbumMetadata(autoItem);
        }
        if (i == 3) {
            return convertTrackMetadata(autoItem);
        }
        Log.error(this.TAG, "Unsupported entity type {}", autoItem.getType());
        return null;
    }

    private final ArtistMetadata convertArtistMetadata(AutoItem autoItem) {
        String asin = autoItem.getAsin();
        Uri contentUri = autoItem.getContentUri();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        return new ArtistMetadata(null, contentUri, null, null, asin, null, null, null, null, null, null, false, null, null, false, null, null, false, false, null, 1048557, null);
    }

    private final AlbumMetadata convertAlbumMetadata(AutoItem autoItem) {
        String asin = autoItem.getAsin();
        Set setEmptySet = SetsKt__SetsKt.emptySet();
        Uri contentUri = autoItem.getContentUri();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        return new AlbumMetadata(null, contentUri, null, asin, null, null, null, null, null, null, null, setEmptySet, false, null, null, null, null, null, null, null, 1046517, null);
    }

    private final TrackMetadata convertTrackMetadata(AutoItem autoItem) {
        String asin = autoItem.getAsin();
        Uri contentUri = autoItem.getContentUri();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        return new TrackMetadata(null, contentUri, null, asin, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1048587, 15, null);
    }
}
