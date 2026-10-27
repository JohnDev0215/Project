package com.amazon.music.curate.data;

import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.data.converter.Converters;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.PlaylistIntents;
import com.amazon.musicplaylist.model.AbstractPlaylist;
import com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry;
import com.amazon.musicplaylist.model.CatalogPlaylist;
import com.amazon.musicplaylist.model.CatalogPlaylistTrackEntry;
import com.amazon.musicplaylist.model.CatalogTrack;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryResponse;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2Response;
import com.amazon.musicplaylist.model.GetSongRecommendationsResponse;
import com.amazon.musicplaylist.model.LibraryPlaylistTrackEntry;
import com.amazon.musicplaylist.model.OwnedPlaylist;
import com.amazon.musicplaylist.model.PersonalizedPlaylist;
import com.amazon.musicplaylist.model.PlaylistMetadata;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 32\u00020\u0001:\u00013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013Jf\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00182!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00160\u001d2!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00160\u001dJ\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%J\u001a\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0018J\u0016\u0010*\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u0018J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020.0%2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0018J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u000200H\u0002J\u0016\u00101\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u00064"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistService;", "", "api", "Lcom/amazon/music/curate/data/PlaylistServiceApi;", "context", "Landroid/content/Context;", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "(Lcom/amazon/music/curate/data/PlaylistServiceApi;Landroid/content/Context;Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "getApi", "()Lcom/amazon/music/curate/data/PlaylistServiceApi;", "getContext", "()Landroid/content/Context;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "appendTrackToPlaylist", "Lcom/amazon/music/curate/model/Playlist;", Environment.PLAYLIST_PATH, "tracks", "", "Lcom/amazon/music/curate/model/Track;", "getCatalogPlaylist", "", "playlistId", "", "isPersonalized", "", "pageToken", "onSuccess", "Lkotlin/Function1;", "Lcom/amazon/music/curate/data/GetCatalogPlaylistResponse;", "Lkotlin/ParameterName;", "name", "response", "onError", "error", "getPersonalizedPlaylists", "Lrx/Observable;", "Lcom/amazon/music/curate/data/GetPersonalizedPlaylistsResponse;", "getUserPlaylist", "Lcom/amazon/music/curate/data/GetUserPlaylistResponse;", "userPlaylistId", "getUserPlaylistRecs", "Lcom/amazon/music/curate/data/UserPlaylistRecsResponse;", "contentSubscriptionMode", "getUserPlaylists", "Lcom/amazon/music/curate/data/GetUserPlaylistsResponse;", "isPersonalizedCatalogPlaylist", "Lcom/amazon/musicplaylist/model/PersonalizedPlaylist;", "removeTrackFromPlaylist", "entryId", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistService {
    private static final Logger LOG;
    private final PlaylistServiceApi api;
    private final Context context;
    private final FeatureFlagProvider featureFlagProvider;

    public PlaylistService(PlaylistServiceApi api, Context context, FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(featureFlagProvider, "featureFlagProvider");
        this.api = api;
        this.context = context;
        this.featureFlagProvider = featureFlagProvider;
    }

    public final PlaylistServiceApi getApi() {
        return this.api;
    }

    public final Context getContext() {
        return this.context;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        return this.featureFlagProvider;
    }

    static {
        Logger logger = LoggerFactory.getLogger(PlaylistService.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PlaylistService::class.java.simpleName)");
        LOG = logger;
    }

    public final Observable<GetUserPlaylistsResponse> getUserPlaylists(final String pageToken) {
        Observable<GetUserPlaylistsResponse> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.PlaylistService$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistService.m1932getUserPlaylists$lambda1(this.f$0, pageToken, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(Observable.OnSubs…scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getUserPlaylists$lambda-1, reason: not valid java name */
    public static final void m1932getUserPlaylists$lambda1(PlaylistService this$0, String str, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            GetOwnedPlaylistsInLibraryResponse getOwnedPlaylistsInLibraryResponseFetchPlaylists = this$0.api.fetchPlaylists(str);
            ArrayList arrayList = new ArrayList();
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Got ");
            List<PlaylistMetadata> playlists = getOwnedPlaylistsInLibraryResponseFetchPlaylists.getPlaylists();
            sb.append(playlists == null ? null : Integer.valueOf(playlists.size()));
            sb.append(" playlists");
            logger.debug(sb.toString());
            List<PlaylistMetadata> playlists2 = getOwnedPlaylistsInLibraryResponseFetchPlaylists.getPlaylists();
            if (playlists2 != null) {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(playlists2, 10));
                for (PlaylistMetadata playlist : playlists2) {
                    Converters.Companion companion = Converters.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
                    arrayList2.add(companion.convertPlaylistMetadata(playlist));
                }
            }
            subscriber.onNext(new GetUserPlaylistsResponse(arrayList, getOwnedPlaylistsInLibraryResponseFetchPlaylists.getNextPageToken()));
            subscriber.onCompleted();
        } catch (VolleyError e) {
            LOG.warn("Failed to get playlists from service", (Throwable) e);
            subscriber.onError(e);
        }
    }

    public static /* synthetic */ GetUserPlaylistResponse getUserPlaylist$default(PlaylistService playlistService, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return playlistService.getUserPlaylist(str, str2);
    }

    public final GetUserPlaylistResponse getUserPlaylist(String userPlaylistId, String pageToken) throws ServiceException {
        AbstractPlaylist abstractPlaylist;
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        try {
            GetPlaylistsByIdV2Response getPlaylistsByIdV2ResponseFetchPlaylist = this.api.fetchPlaylist(userPlaylistId, pageToken);
            ArrayList arrayList = new ArrayList();
            List<AbstractPlaylist> playlists = getPlaylistsByIdV2ResponseFetchPlaylist.getPlaylists();
            if (playlists == null) {
                abstractPlaylist = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : playlists) {
                    if (((AbstractPlaylist) obj).getClass().isAssignableFrom(OwnedPlaylist.class)) {
                        arrayList2.add(obj);
                    }
                }
                abstractPlaylist = (AbstractPlaylist) arrayList2.get(0);
            }
            if (abstractPlaylist != null) {
                OwnedPlaylist ownedPlaylist = (OwnedPlaylist) abstractPlaylist;
                LOG.debug("Got " + ownedPlaylist.getTracks().size() + " tracks");
                List<AbstractPlaylistTrackEntry> tracks = ownedPlaylist.getTracks();
                Intrinsics.checkNotNullExpressionValue(tracks, "ownedPlaylist\n                    .tracks");
                ArrayList<AbstractPlaylistTrackEntry> arrayList3 = new ArrayList();
                for (Object obj2 : tracks) {
                    AbstractPlaylistTrackEntry abstractPlaylistTrackEntry = (AbstractPlaylistTrackEntry) obj2;
                    if (abstractPlaylistTrackEntry.getClass().isAssignableFrom(LibraryPlaylistTrackEntry.class) || abstractPlaylistTrackEntry.getClass().isAssignableFrom(CatalogPlaylistTrackEntry.class)) {
                        arrayList3.add(obj2);
                    }
                }
                ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
                for (AbstractPlaylistTrackEntry abstractPlaylistTrackEntry2 : arrayList3) {
                    arrayList4.add(Intrinsics.areEqual(abstractPlaylistTrackEntry2.getClass(), LibraryPlaylistTrackEntry.class) ? Converters.INSTANCE.convertLibraryTrack((LibraryPlaylistTrackEntry) abstractPlaylistTrackEntry2) : Converters.INSTANCE.convertCatalogTrack((CatalogPlaylistTrackEntry) abstractPlaylistTrackEntry2));
                }
                CollectionsKt___CollectionsKt.toCollection(arrayList4, arrayList);
                return new GetUserPlaylistResponse(Converters.INSTANCE.convertPlaylist(ownedPlaylist), arrayList, getPlaylistsByIdV2ResponseFetchPlaylist.getNextPageToken());
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicplaylist.model.OwnedPlaylist");
        } catch (VolleyError e) {
            LOG.warn("Failed to get playlist " + userPlaylistId + " from service", (Throwable) e);
            throw new ServiceException(e);
        }
    }

    public final UserPlaylistRecsResponse getUserPlaylistRecs(String userPlaylistId, String contentSubscriptionMode) throws ServiceException {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(contentSubscriptionMode, "contentSubscriptionMode");
        try {
            GetSongRecommendationsResponse getSongRecommendationsResponseFetchRecommendations = this.api.fetchRecommendations(userPlaylistId, contentSubscriptionMode);
            ArrayList arrayList = new ArrayList();
            List<CatalogPlaylistTrackEntry> tracks = getSongRecommendationsResponseFetchRecommendations.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "response\n                    .tracks");
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
            for (CatalogPlaylistTrackEntry track : tracks) {
                Converters.Companion companion = Converters.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(track, "track");
                arrayList2.add(companion.convertCatalogTrack(track));
            }
            CollectionsKt___CollectionsKt.toCollection(arrayList2, arrayList);
            LOG.debug("Got " + getSongRecommendationsResponseFetchRecommendations.getTracks().size() + " track recs");
            return new UserPlaylistRecsResponse(arrayList);
        } catch (VolleyError e) {
            LOG.warn("Failed to get playlist recs for " + userPlaylistId + " from service", (Throwable) e);
            throw new ServiceException(e);
        }
    }

    public final Playlist appendTrackToPlaylist(Playlist playlist, List<Track> tracks) throws ServiceException {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        LOG.debug("appendTrackToPlaylist()");
        try {
            PlaylistServiceApi playlistServiceApi = this.api;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
            for (Track track : tracks) {
                arrayList.add(CatalogTrack.builder().withAsin(track.getAsin()).withMarketplaceId(track.getMarketplace()).build());
            }
            playlistServiceApi.appendTrackToPlaylist(playlist, arrayList);
            LocalBroadcastManager.getInstance(this.context).sendBroadcast(PlaylistIntents.INSTANCE.createSyncPlaylistIntent(playlist.getId()));
            Converters.Companion companion = Converters.INSTANCE;
            AbstractPlaylist abstractPlaylist = PlaylistServiceApi.fetchPlaylist$default(this.api, playlist.getId(), null, 2, null).getPlaylists().get(0);
            if (abstractPlaylist != null) {
                return companion.convertPlaylist((OwnedPlaylist) abstractPlaylist);
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicplaylist.model.OwnedPlaylist");
        } catch (VolleyError e) {
            LOG.warn(Intrinsics.stringPlus("Failed to append tracks to playlist ", playlist.getTitle()), (Throwable) e);
            throw new ServiceException(e);
        }
    }

    public final void removeTrackFromPlaylist(Playlist playlist, String entryId) throws ServiceException {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(entryId, "entryId");
        try {
            this.api.removeTrackFromPlaylist(playlist, entryId);
            LocalBroadcastManager.getInstance(this.context).sendBroadcast(PlaylistIntents.INSTANCE.createSyncPlaylistIntent(playlist.getId()));
        } catch (VolleyError e) {
            LOG.warn("Failed to remove Track " + entryId + " from  playlist " + playlist.getTitle(), (Throwable) e);
            throw new ServiceException(e);
        }
    }

    public final Observable<GetPersonalizedPlaylistsResponse> getPersonalizedPlaylists() {
        Observable<GetPersonalizedPlaylistsResponse> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.curate.data.PlaylistService$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistService.m1931getPersonalizedPlaylists$lambda9(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create(Observable.OnSubs…scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getPersonalizedPlaylists$lambda-9, reason: not valid java name */
    public static final void m1931getPersonalizedPlaylists$lambda9(PlaylistService this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            com.amazon.musicplaylist.model.GetPersonalizedPlaylistsResponse getPersonalizedPlaylistsResponseFetchPersonalizedPlaylists = this$0.api.fetchPersonalizedPlaylists(this$0.featureFlagProvider.isMyLikesInMadeForYouEnabled());
            ArrayList arrayList = new ArrayList();
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Got ");
            List<PersonalizedPlaylist> playlists = getPersonalizedPlaylistsResponseFetchPersonalizedPlaylists.getPlaylists();
            sb.append(playlists == null ? null : Integer.valueOf(playlists.size()));
            sb.append(" playlists");
            logger.debug(sb.toString());
            List<PersonalizedPlaylist> playlists2 = getPersonalizedPlaylistsResponseFetchPersonalizedPlaylists.getPlaylists();
            Intrinsics.checkNotNullExpressionValue(playlists2, "response.playlists");
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : playlists2) {
                PersonalizedPlaylist it = (PersonalizedPlaylist) obj;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (!this$0.isPersonalizedCatalogPlaylist(it) || it.getMetadata().isIsCustomerFollowing()) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(Converters.INSTANCE.convertPersonalizedPlayist((PersonalizedPlaylist) it2.next()));
            }
            subscriber.onNext(new GetPersonalizedPlaylistsResponse(arrayList));
            subscriber.onCompleted();
        } catch (VolleyError e) {
            LOG.debug("Failed to get personalized playlists from service", (Throwable) e);
            subscriber.onError(e);
        }
    }

    private final boolean isPersonalizedCatalogPlaylist(PersonalizedPlaylist playlist) {
        return PlaylistServiceApi.INSTANCE.getPERSONALIZED_CATALOG_PLAYLIST_TYPE().contains(playlist.getPlaylistType());
    }

    public final void getCatalogPlaylist(String playlistId, boolean isPersonalized, String pageToken, Function1<? super GetCatalogPlaylistResponse, Unit> onSuccess, Function1<? super String, Unit> onError) {
        AbstractPlaylist abstractPlaylist;
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        try {
            GetPlaylistsByIdV2Response getPlaylistsByIdV2ResponseFetchPlaylist = this.api.fetchPlaylist(playlistId, pageToken);
            ArrayList arrayList = new ArrayList();
            List<AbstractPlaylist> playlists = getPlaylistsByIdV2ResponseFetchPlaylist.getPlaylists();
            if (playlists == null) {
                abstractPlaylist = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : playlists) {
                    if (((AbstractPlaylist) obj).getClass().isAssignableFrom(CatalogPlaylist.class)) {
                        arrayList2.add(obj);
                    }
                }
                abstractPlaylist = (AbstractPlaylist) arrayList2.get(0);
            }
            if (abstractPlaylist != null) {
                CatalogPlaylist catalogPlaylist = (CatalogPlaylist) abstractPlaylist;
                LOG.debug("Got " + catalogPlaylist.getTracks().size() + " tracks");
                List<CatalogPlaylistTrackEntry> tracks = catalogPlaylist.getTracks();
                Intrinsics.checkNotNullExpressionValue(tracks, "catalogPlaylist\n                    .tracks");
                ArrayList<CatalogPlaylistTrackEntry> arrayList3 = new ArrayList();
                for (Object obj2 : tracks) {
                    if (((CatalogPlaylistTrackEntry) obj2).getClass().isAssignableFrom(CatalogPlaylistTrackEntry.class)) {
                        arrayList3.add(obj2);
                    }
                }
                ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
                for (CatalogPlaylistTrackEntry catalogPlaylistTrackEntry : arrayList3) {
                    Converters.Companion companion = Converters.INSTANCE;
                    if (catalogPlaylistTrackEntry == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicplaylist.model.CatalogPlaylistTrackEntry");
                    }
                    arrayList4.add(companion.convertCatalogTrack(catalogPlaylistTrackEntry));
                }
                CollectionsKt___CollectionsKt.toCollection(arrayList4, arrayList);
                onSuccess.invoke(new GetCatalogPlaylistResponse(Converters.INSTANCE.convertCatalogPlayist(catalogPlaylist, isPersonalized), arrayList, getPlaylistsByIdV2ResponseFetchPlaylist.getNextPageToken()));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicplaylist.model.CatalogPlaylist");
        } catch (VolleyError e) {
            LOG.warn("Failed to get playlist from service", (Throwable) e);
            String localizedMessage = e.getLocalizedMessage();
            Intrinsics.checkNotNullExpressionValue(localizedMessage, "error.localizedMessage");
            onError.invoke(localizedMessage);
        }
    }
}
