package com.amazon.music.curate.data;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.curate.endpoint.Endpoint;
import com.amazon.music.curate.endpoint.Realm;
import com.amazon.music.curate.extension.UserResultKt;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistCall;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequest;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequestSerializer;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistResponse;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistResponseDeserializer;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryCall;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryRequest;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryRequestSerializer;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryResponse;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryResponseDeserializer;
import com.amazon.musicplaylist.model.GetPersonalizedPlaylistsCall;
import com.amazon.musicplaylist.model.GetPersonalizedPlaylistsRequest;
import com.amazon.musicplaylist.model.GetPersonalizedPlaylistsRequestSerializer;
import com.amazon.musicplaylist.model.GetPersonalizedPlaylistsResponseDeserializer;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2Call;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2Request;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2RequestSerializer;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2Response;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2ResponseDeserializer;
import com.amazon.musicplaylist.model.GetSongRecommendationsCall;
import com.amazon.musicplaylist.model.GetSongRecommendationsRequest;
import com.amazon.musicplaylist.model.GetSongRecommendationsRequestSerializer;
import com.amazon.musicplaylist.model.GetSongRecommendationsResponse;
import com.amazon.musicplaylist.model.GetSongRecommendationsResponseDeserializer;
import com.amazon.musicplaylist.model.RemoveTracksFromPlaylistCall;
import com.amazon.musicplaylist.model.RemoveTracksFromPlaylistRequest;
import com.amazon.musicplaylist.model.RemoveTracksFromPlaylistRequestSerializer;
import com.amazon.musicplaylist.model.RemoveTracksFromPlaylistResponseDeserializer;
import com.amazon.musicplaylist.model.TrackId;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistServiceApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017J\u0010\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0016\u0010 \u001a\u00020!2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006$"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistServiceApi;", "", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "(Lcom/amazon/hermes/RequestInterceptor;)V", "MAPPER", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "appendTrackToPlaylist", "Lcom/amazon/musicplaylist/model/AppendTracksToPlaylistResponse;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "trackIds", "", "Lcom/amazon/musicplaylist/model/TrackId;", "fetchPersonalizedPlaylists", "Lcom/amazon/musicplaylist/model/GetPersonalizedPlaylistsResponse;", "withMadeForYouType", "", "fetchPlaylist", "Lcom/amazon/musicplaylist/model/GetPlaylistsByIdV2Response;", "userPlaylistId", "", "pageToken", "fetchPlaylists", "Lcom/amazon/musicplaylist/model/GetOwnedPlaylistsInLibraryResponse;", "fetchRecommendations", "Lcom/amazon/musicplaylist/model/GetSongRecommendationsResponse;", "contentSubscriptionMode", "playlistUrl", "Ljava/net/URL;", "removeTrackFromPlaylist", "", "entryId", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistServiceApi {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private static final Set<String> PERSONALIZED_CATALOG_PLAYLIST_TYPE;
    private static final String PERSONALIZED_PLAYLIST_MADE_FOR_YOU = "MADE_FOR_YOU";
    private static final String PERSONALIZED_PLAYLIST_WEEKLY_MIXTAPE = "WEEKLY_MIXTAPE";
    private static final String PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2019_MEMORY = "YEAR_IN_REVIEW_2019_MEMORY";
    private static final String PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2020_MEMORY = "YEAR_IN_REVIEW_2020_MEMORY";
    private final ObjectMapper MAPPER;
    private final RequestInterceptor requestInterceptor;

    /* JADX INFO: compiled from: PlaylistServiceApi.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistServiceApi$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "PERSONALIZED_CATALOG_PLAYLIST_TYPE", "", "", "getPERSONALIZED_CATALOG_PLAYLIST_TYPE", "()Ljava/util/Set;", "PERSONALIZED_PLAYLIST_MADE_FOR_YOU", "PERSONALIZED_PLAYLIST_WEEKLY_MIXTAPE", "PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2019_MEMORY", "PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2020_MEMORY", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getPERSONALIZED_CATALOG_PLAYLIST_TYPE() {
            return PlaylistServiceApi.PERSONALIZED_CATALOG_PLAYLIST_TYPE;
        }
    }

    public PlaylistServiceApi(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        this.requestInterceptor = requestInterceptor;
        ObjectMapper objectMapper = new ObjectMapper();
        this.MAPPER = objectMapper;
        GetPlaylistsByIdV2RequestSerializer.register(objectMapper);
        GetPlaylistsByIdV2ResponseDeserializer.register(objectMapper);
        GetOwnedPlaylistsInLibraryRequestSerializer.register(objectMapper);
        GetOwnedPlaylistsInLibraryResponseDeserializer.register(objectMapper);
        GetSongRecommendationsRequestSerializer.register(objectMapper);
        GetSongRecommendationsResponseDeserializer.register(objectMapper);
        AppendTracksToPlaylistRequestSerializer.register(objectMapper);
        AppendTracksToPlaylistResponseDeserializer.register(objectMapper);
        GetPersonalizedPlaylistsRequestSerializer.register(objectMapper);
        GetPersonalizedPlaylistsResponseDeserializer.register(objectMapper);
        RemoveTracksFromPlaylistRequestSerializer.register(objectMapper);
        RemoveTracksFromPlaylistResponseDeserializer.register(objectMapper);
    }

    public final RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    static {
        Logger logger = LoggerFactory.getLogger(PlaylistServiceApi.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PlaylistServic…i::class.java.simpleName)");
        LOG = logger;
        PERSONALIZED_CATALOG_PLAYLIST_TYPE = SetsKt__SetsKt.setOf((Object[]) new String[]{PERSONALIZED_PLAYLIST_WEEKLY_MIXTAPE, PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2019_MEMORY, PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2020_MEMORY});
    }

    public static /* synthetic */ GetPlaylistsByIdV2Response fetchPlaylist$default(PlaylistServiceApi playlistServiceApi, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return playlistServiceApi.fetchPlaylist(str, str2);
    }

    public final GetPlaylistsByIdV2Response fetchPlaylist(String userPlaylistId, String pageToken) throws VolleyError {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        LOG.debug("Fetching playlist");
        GetPlaylistsByIdV2Response getPlaylistsByIdV2ResponseExecute = new GetPlaylistsByIdV2Call(playlistUrl(), GetPlaylistsByIdV2Request.builder().withPlaylistIds(CollectionsKt__CollectionsJVMKt.listOf(userPlaylistId)).withPageToken(pageToken).withRequestedMetadata(CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"title", "artistName", "albumCoverImageFull", "asin", "artistAsin", "albumAsin", "albumName", "objectId", "duration", IntlConfiguration.MARKETPLACE})).build(), this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(getPlaylistsByIdV2ResponseExecute, "call.execute(MAPPER)");
        return getPlaylistsByIdV2ResponseExecute;
    }

    public final GetOwnedPlaylistsInLibraryResponse fetchPlaylists(String pageToken) throws VolleyError {
        LOG.debug("Fetching playlists");
        GetOwnedPlaylistsInLibraryResponse getOwnedPlaylistsInLibraryResponseExecute = new GetOwnedPlaylistsInLibraryCall(playlistUrl(), GetOwnedPlaylistsInLibraryRequest.builder().withPageSize(50).withPageToken(pageToken).build(), this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(getOwnedPlaylistsInLibraryResponseExecute, "call.execute(MAPPER)");
        return getOwnedPlaylistsInLibraryResponseExecute;
    }

    public final GetSongRecommendationsResponse fetchRecommendations(String userPlaylistId, String contentSubscriptionMode) throws VolleyError {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        LOG.debug("Fetching recommendations");
        GetSongRecommendationsResponse getSongRecommendationsResponseExecute = new GetSongRecommendationsCall(playlistUrl(), GetSongRecommendationsRequest.builder().withPlaylistId(userPlaylistId).withContentSubscriptionMode(contentSubscriptionMode).withNumTracksRequested(20).build(), this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(getSongRecommendationsResponseExecute, "call.execute(MAPPER)");
        return getSongRecommendationsResponseExecute;
    }

    public final AppendTracksToPlaylistResponse appendTrackToPlaylist(Playlist playlist, List<? extends TrackId> trackIds) throws VolleyError {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        LOG.debug("appendTrackToPlaylist(" + playlist.getId() + ')');
        Iterator<T> it = trackIds.iterator();
        while (it.hasNext()) {
            LOG.debug(Intrinsics.stringPlus("  ", (TrackId) it.next()));
        }
        AppendTracksToPlaylistResponse appendTracksToPlaylistResponseExecute = new AppendTracksToPlaylistCall(playlistUrl(), AppendTracksToPlaylistRequest.builder().withPlaylistId(playlist.getId()).withTracks(trackIds).withVersion(playlist.getVersion()).withRejectDuplicateTracks(true).build(), this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(appendTracksToPlaylistResponseExecute, "call.execute(MAPPER)");
        return appendTracksToPlaylistResponseExecute;
    }

    public final void removeTrackFromPlaylist(Playlist playlist, String entryId) throws VolleyError {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(entryId, "entryId");
        new RemoveTracksFromPlaylistCall(playlistUrl(), RemoveTracksFromPlaylistRequest.builder().withPlaylistId(playlist.getId()).withVersion(playlist.getVersion()).withEntryIds(CollectionsKt__CollectionsJVMKt.listOf(entryId)).build(), this.requestInterceptor).execute(this.MAPPER);
    }

    public final com.amazon.musicplaylist.model.GetPersonalizedPlaylistsResponse fetchPersonalizedPlaylists(boolean withMadeForYouType) throws VolleyError {
        List<String> listListOf;
        LOG.debug("Fetching Personalized Playlists");
        if (withMadeForYouType) {
            listListOf = CollectionsKt__CollectionsJVMKt.listOf(PERSONALIZED_PLAYLIST_MADE_FOR_YOU);
        } else {
            listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{PERSONALIZED_PLAYLIST_WEEKLY_MIXTAPE, PERSONALIZED_PLAYLIST_YEAR_IN_REVIEW_2019_MEMORY});
        }
        com.amazon.musicplaylist.model.GetPersonalizedPlaylistsResponse getPersonalizedPlaylistsResponseExecute = new GetPersonalizedPlaylistsCall(playlistUrl(), GetPersonalizedPlaylistsRequest.builder().withPlaylistTypes(listListOf).build(), this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(getPersonalizedPlaylistsResponseExecute, "call.execute(MAPPER)");
        return getPersonalizedPlaylistsResponseExecute;
    }

    private final URL playlistUrl() {
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = UserResultKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        Marketplace libraryHomeMarketplace = user != null ? user.getLibraryHomeMarketplace() : null;
        if (libraryHomeMarketplace == null) {
            libraryHomeMarketplace = Marketplace.USA;
        }
        Endpoint.Companion companion = Endpoint.INSTANCE;
        Realm.Companion companion2 = Realm.INSTANCE;
        String region = libraryHomeMarketplace.getRegion();
        Intrinsics.checkNotNullExpressionValue(region, "marketplace.region");
        return new URL(companion.getPlaylistServiceEndpoint(companion2.fromString(region)));
    }
}
