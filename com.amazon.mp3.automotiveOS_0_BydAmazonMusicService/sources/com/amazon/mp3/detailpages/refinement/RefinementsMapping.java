package com.amazon.mp3.detailpages.refinement;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.filter.database.DbFilter;
import com.amazon.mp3.library.refinement.filter.database.DbFilters;
import com.amazon.mp3.library.refinement.query.arg.SingleArg;
import com.amazon.mp3.library.refinement.query.arg.SingleArgType;
import com.amazon.mp3.library.refinement.sort.DbSort;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementsMapping.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b(\u0010)J\u001e\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u0002H\u0002J\u001e\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00040\u0002H\u0002R9\u0010\n\u001a$\u0012\u0004\u0012\u00020\t\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u00020\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR)\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\rR)\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0012\u0010\rR9\u0010\u0014\u001a$\u0012\u0004\u0012\u00020\t\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\rR)\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\rR)\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0019\u0010\rR-\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001a\u0012\u0002\b\u00030\u00010\b8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\rR9\u0010\u001d\u001a$\u0012\u0004\u0012\u00020\t\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00040\u00020\b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR)\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b \u0010\rR)\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e0\b8\u0006¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\rR9\u0010$\u001a$\u0012\u0004\u0012\u00020\t\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020#\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00040\u00020\b8\u0006¢\u0006\f\n\u0004\b$\u0010\u000b\u001a\u0004\b%\u0010\rR)\u0010&\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u000e0\b8\u0006¢\u0006\f\n\u0004\b&\u0010\u000b\u001a\u0004\b'\u0010\r¨\u0006*"}, d2 = {"Lcom/amazon/mp3/detailpages/refinement/RefinementsMapping;", "", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "Lcom/amazon/mp3/library/item/MusicTrack;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "trackPurchasedFilter", "Lcom/amazon/mp3/library/item/Album;", "albumPurchasedFilter", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "trackFilterMapping", "Ljava/util/Map;", "getTrackFilterMapping", "()Ljava/util/Map;", "Lcom/amazon/mp3/library/refinement/sort/DbSort;", "trackSortMapping", "getTrackSortMapping", "catalogTrackSortMapping", "getCatalogTrackSortMapping", "Lcom/amazon/mp3/library/item/Artist;", "artistFilterMapping", "getArtistFilterMapping", "artistSortMapping", "getArtistSortMapping", "followedArtistsSortMapping", "getFollowedArtistsSortMapping", "Lcom/amazon/mp3/library/refinement/Refinable;", "tracksListFilterMapping", "getTracksListFilterMapping", "albumsFilterMapping", "getAlbumsFilterMapping", "albumsSortMapping", "getAlbumsSortMapping", "likedAlbumsSortMapping", "getLikedAlbumsSortMapping", "Lcom/amazon/mp3/library/item/Playlist;", "playlistsFilterMapping", "getPlaylistsFilterMapping", "playlistsSortMapping", "getPlaylistsSortMapping", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RefinementsMapping {
    public static final RefinementsMapping INSTANCE;
    private static final Map<RefinementFilterType, Filter<Album, ?, DatabaseSource<Album>>> albumsFilterMapping;
    private static final Map<RefinementFilterType, DbSort<Album>> albumsSortMapping;
    private static final Map<RefinementFilterType, Filter<Artist, ?, DatabaseSource<Artist>>> artistFilterMapping;
    private static final Map<RefinementFilterType, DbSort<Artist>> artistSortMapping;
    private static final Map<RefinementFilterType, DbSort<MusicTrack>> catalogTrackSortMapping;
    private static final Map<RefinementFilterType, DbSort<Artist>> followedArtistsSortMapping;
    private static final Map<RefinementFilterType, DbSort<Album>> likedAlbumsSortMapping;
    private static final Map<RefinementFilterType, Filter<Playlist, ?, DatabaseSource<Playlist>>> playlistsFilterMapping;
    private static final Map<RefinementFilterType, DbSort<Playlist>> playlistsSortMapping;
    private static final Map<RefinementFilterType, Filter<MusicTrack, ?, DatabaseSource<MusicTrack>>> trackFilterMapping;
    private static final Map<RefinementFilterType, DbSort<MusicTrack>> trackSortMapping;
    private static final Map<RefinementFilterType, Object> tracksListFilterMapping;

    private RefinementsMapping() {
    }

    public final Map<RefinementFilterType, Filter<MusicTrack, ?, DatabaseSource<MusicTrack>>> getTrackFilterMapping() {
        return trackFilterMapping;
    }

    static {
        RefinementsMapping refinementsMapping = new RefinementsMapping();
        INSTANCE = refinementsMapping;
        RefinementFilterType refinementFilterType = RefinementFilterType.LIBRARY;
        SingleArgType singleArgType = SingleArgType.LESS_THAN;
        RefinementFilterType refinementFilterType2 = RefinementFilterType.PURCHASED;
        RefinementFilterType refinementFilterType3 = RefinementFilterType.ULTRA_HD;
        SingleArgType singleArgType2 = SingleArgType.LIKE;
        RefinementFilterType refinementFilterType4 = RefinementFilterType.ATMOS;
        RefinementFilterType refinementFilterType5 = RefinementFilterType.RA360;
        RefinementFilterType refinementFilterType6 = RefinementFilterType.LYRICS;
        String string = LyricsStatus.AVAILABLE.toString();
        Intrinsics.checkNotNullExpressionValue(string, "AVAILABLE.toString()");
        trackFilterMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType, new DbFilter("ownership_status", new SingleArg("300", singleArgType))), TuplesKt.to(refinementFilterType2, refinementsMapping.trackPurchasedFilter()), TuplesKt.to(refinementFilterType3, new DbFilter("content_encoding", new SingleArg("%uhdAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType4, new DbFilter("content_encoding", new SingleArg("%atmosAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType5, new DbFilter("content_encoding", new SingleArg("%ra360Available%", singleArgType2))), TuplesKt.to(refinementFilterType6, new DbFilter("lyrics_state", new SingleArg(string, null, 2, null))));
        RefinementFilterType refinementFilterType7 = RefinementFilterType.TITLE;
        RefinementFilterType refinementFilterType8 = RefinementFilterType.ALBUM_NAME;
        RefinementFilterType refinementFilterType9 = RefinementFilterType.ARTIST_NAME;
        RefinementFilterType refinementFilterType10 = RefinementFilterType.DATE_ADDED;
        trackSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType2, new DbSort("ownership_status", null, 2, null)), TuplesKt.to(refinementFilterType7, new DbSort("sort_title", null, 2, null)), TuplesKt.to(refinementFilterType8, new DbSort("sort_album", null, 2, null)), TuplesKt.to(refinementFilterType9, new DbSort("sort_artist", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", refinementFilterType10.defaultSortOrder())));
        RefinementFilterType refinementFilterType11 = RefinementFilterType.DURATION;
        catalogTrackSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType7, new DbSort("PrimePlaylistTracks.sort_title", null, 2, null)), TuplesKt.to(refinementFilterType8, new DbSort("sort_album", null, 2, null)), TuplesKt.to(refinementFilterType9, new DbSort("sort_artist", null, 2, null)), TuplesKt.to(refinementFilterType11, new DbSort("PrimePlaylistTracks.duration", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", refinementFilterType10.defaultSortOrder())), TuplesKt.to(RefinementFilterType.POPULARITY, new DbSort("popularity", null, 2, null)));
        artistFilterMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType, new DbFilter("ownership_status", new SingleArg("300", singleArgType))), TuplesKt.to(refinementFilterType3, new DbFilter("content_encoding", new SingleArg("%uhdAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType4, new DbFilter("content_encoding", new SingleArg("%atmosAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType5, new DbFilter("content_encoding", new SingleArg("%ra360Available%", singleArgType2))));
        SortOrder sortOrder = SortOrder.DESCENDING;
        artistSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType9, new DbSort(String.valueOf(CirrusMediaSource.ARTISTS_MAP.get("sort_name")), null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", sortOrder)));
        followedArtistsSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType9, new DbSort("sort_name", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", sortOrder)));
        tracksListFilterMapping = MapsKt__MapsKt.emptyMap();
        albumsFilterMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType3, new DbFilter("content_encoding", new SingleArg("%uhdAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType4, new DbFilter("content_encoding", new SingleArg("%atmosAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType5, new DbFilter("content_encoding", new SingleArg("%ra360Available%", singleArgType2))), TuplesKt.to(refinementFilterType2, refinementsMapping.albumPurchasedFilter()));
        albumsSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType2, new DbSort("ownership_status", null, 2, null)), TuplesKt.to(refinementFilterType8, new DbSort("sort_album", null, 2, null)), TuplesKt.to(refinementFilterType9, new DbSort("album_artist", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", refinementFilterType10.defaultSortOrder())));
        likedAlbumsSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType8, new DbSort("sort_title", null, 2, null)), TuplesKt.to(refinementFilterType9, new DbSort("sort_artist", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("date_created", sortOrder)));
        playlistsFilterMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementFilterType3, new DbFilter("content_encoding", new SingleArg("%uhdAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType4, new DbFilter("content_encoding", new SingleArg("%atmosAvailable%", singleArgType2))), TuplesKt.to(refinementFilterType5, new DbFilter("content_encoding", new SingleArg("%ra360Available%", singleArgType2))), TuplesKt.to(RefinementFilterType.FOLLOWING, new DbFilter(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new SingleArg("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist", singleArgType2))), TuplesKt.to(RefinementFilterType.BY_ME, new DbFilter(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new SingleArg("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist", null, 2, null))));
        playlistsSortMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(RefinementFilterType.LAST_TOUCHED, new DbSort("dateLastTouched", null, 2, null)), TuplesKt.to(RefinementFilterType.LAST_MODIFIED, new DbSort("dateLastModified", null, 2, null)), TuplesKt.to(refinementFilterType10, new DbSort("dateCreated", null, 2, null)), TuplesKt.to(refinementFilterType7, new DbSort("name", null, 2, null)), TuplesKt.to(refinementFilterType11, new DbSort("duration", null, 2, null)));
    }

    public final Map<RefinementFilterType, DbSort<MusicTrack>> getTrackSortMapping() {
        return trackSortMapping;
    }

    public final Map<RefinementFilterType, DbSort<MusicTrack>> getCatalogTrackSortMapping() {
        return catalogTrackSortMapping;
    }

    public final Map<RefinementFilterType, Filter<Artist, ?, DatabaseSource<Artist>>> getArtistFilterMapping() {
        return artistFilterMapping;
    }

    public final Map<RefinementFilterType, DbSort<Artist>> getArtistSortMapping() {
        return artistSortMapping;
    }

    public final Map<RefinementFilterType, DbSort<Artist>> getFollowedArtistsSortMapping() {
        return followedArtistsSortMapping;
    }

    public final Map<RefinementFilterType, Filter<Album, ?, DatabaseSource<Album>>> getAlbumsFilterMapping() {
        return albumsFilterMapping;
    }

    public final Map<RefinementFilterType, DbSort<Album>> getAlbumsSortMapping() {
        return albumsSortMapping;
    }

    public final Map<RefinementFilterType, DbSort<Album>> getLikedAlbumsSortMapping() {
        return likedAlbumsSortMapping;
    }

    public final Map<RefinementFilterType, Filter<Playlist, ?, DatabaseSource<Playlist>>> getPlaylistsFilterMapping() {
        return playlistsFilterMapping;
    }

    public final Map<RefinementFilterType, DbSort<Playlist>> getPlaylistsSortMapping() {
        return playlistsSortMapping;
    }

    private final Filter<MusicTrack, ?, DatabaseSource<MusicTrack>> trackPurchasedFilter() {
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return new DbFilters(new DbFilter("ownership_status", new SingleArg("200", SingleArgType.LESS_THAN)));
        }
        return new DbFilters(new DbFilter("asin", new SingleArg("", SingleArgType.NOT_EQUALS)), new DbFilter("ownership_status", new SingleArg("200", SingleArgType.LESS_THAN)));
    }

    private final Filter<Album, ?, DatabaseSource<Album>> albumPurchasedFilter() {
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return new DbFilters(new DbFilter("ownership_status", new SingleArg("200", SingleArgType.LESS_THAN)));
        }
        return new DbFilters(new DbFilter("album_asin", new SingleArg("", SingleArgType.NOT_EQUALS)), new DbFilter("ownership_status", new SingleArg("200", SingleArgType.LESS_THAN)));
    }
}
