package com.amazon.mp3.library.provider.source.cirrus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.db.ColumnMappedCursor;
import com.amazon.mp3.library.db.SafeMergeCursor;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.ProviderSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistTrackUtil;
import com.amazon.mp3.library.util.EditPlaylistMetricsLogger;
import com.amazon.mp3.library.util.PlaylistCache;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase;
import com.amazon.mp3.util.Clock;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.curate.provider.EntityType;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusMediaSource implements ProviderSource {
    public static final HashMap<String, String> ALBUMS_MAP;
    public static final HashMap<String, String> ARTISTS_MAP;
    public static final Uri BASE_URI;
    public static final Uri CIRRUS_TRACKS_URI;
    public static final Uri CIRRUS_URI;
    public static final HashMap<String, String> GENRE_MAP;
    public static final Uri LOCAL_TRACKS_URI;
    public static final Uri LOCAL_URI;
    public static final String MAX_NON_PREVIOUS_QUERY;
    public static final String MAX_NON_PREVIOUS_QUERY_FOR_PLAYLISTS;
    public static final Uri NOTIFICATION_URI;
    public static final Uri NOTIFICATION_URI_LOCAL;
    public static final HashMap<String, String> PLAYLISTS_MAP_IN;
    public static final HashMap<String, String> PLAYLISTS_MAP_OUT;
    public static final Uri PLAYLISTS_NOTIFICATION_URI;
    public static final HashMap<String, String> PRIME_PLAYLISTS_MAP;
    public static final HashMap<String, String> PRIME_PLAYLISTS_TRACKS_MAP;
    private static final String TAG;
    public static final Uri TRACKS_NOTIFICATION_URI;
    public static final List<Long> SMART_PLAYLIST_IDS = Collections.unmodifiableList(Arrays.asList(0L, 1L));
    static final String[] PLAYLISTS_PROJECTION = {"_id", PageUriUtils.SOURCE_QUERY_PARAM, "name", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "download_state", "luid", "version", "track_count", "duration", "0 as is_prime", "\"\" as asin", "0 as is_new", SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "visibility", "is_sonic_rush", "is_sonic_rush_on_demand", "is_sonic_rush_golden", "playmode_eligibility", "dateLastTouched", "dateLastModified", "dateCreated", "shared_id", "numberOfFollowers"};
    static final String[] PRIME_PLAYLISTS_PROJECTION = {"_id", PageUriUtils.SOURCE_QUERY_PARAM, "name", "\"vnd.android.cursor.item/vnd.amazonmp3.primeplaylist\" as type", "download_state", "luid", "version", "track_count", "duration", "is_prime", "asin", "is_new", MediaTrack.ROLE_DESCRIPTION, "is_shared_user_playlist", "is_entity_playlist", "visibility", "is_sonic_rush", "is_sonic_rush_on_demand", "is_sonic_rush_golden", "playmode_eligibility", "dateLastTouched", "dateLastModified", "dateCreated"};
    public static final String[] PRIME_PLAYLISTS_TRACK_PROJECTION = {"PrimePlaylistTracks._id", "PrimePlaylistTracks.title", "PrimePlaylistTracks.asin", "PrimePlaylistTracks.sort_title", "PrimePlaylistTracks.track_num", "PrimePlaylistTracks.album", "PrimePlaylistTracks.album_asin", "PrimePlaylistTracks.album_artist", "PrimePlaylistTracks.album_artist_asin", "PrimePlaylistTracks.artist", "PrimePlaylistTracks.artist_asin", "PrimePlaylistTracks.genre", downloadStateColumnName(), "PrimePlaylistTracks.lyrics_state", "PrimePlaylistTracks.duration", "PrimePlaylistTracks.match_hash", "PrimePlaylistTracks.catalog_status", "PrimePlaylistTracks.fileSizeKilobytes", "PrimePlaylistTracks.album_art_url_large", "PrimePlaylistTracks.album_art_url_thumbnail", "PrimePlaylistTracks.track_source", "PrimePlaylistToTrack.is_new", "local_uri", "IFNULL(Track.prime_status,PrimePlaylistTracks.catalog_status) as prime_status", "IFNULL(Track.ownership_status," + ContentOwnershipStatus.NOT_IN_LIBRARY.getValue() + ") as ownership_status", "album_id", "IFNULL(Track.luid, \"\" ) as luid", "IFNULL(Track.playlist_track_status, " + MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue() + " ) as playlist_track_status", "\"mp3\" as extension", TrackType.CATALOG_PLAYLIST_TRACK.getTypeId() + " as track_type", "PrimePlaylistTracks.artwork_id", "PrimePlaylistTracks.is_explicit", "PrimePlaylistTracks.content_encoding", "PrimePlaylistTracks.asset_qualities", "PrimePlaylistTracks.asset_type", "PrimePlaylistTracks.is_exclusion_tagged", "PrimePlaylistTracks.playmode_eligibility", "PrimePlaylistTracks.catalog_status_tiers"};
    private Context mContext = null;
    private Boolean isELFEnabledForChild = Boolean.FALSE;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        String str = MediaProvider.AUTHORITY;
        sb.append(str);
        sb.append("/");
        sb.append("library");
        sb.append("/");
        sb.append("cirrus");
        Uri uri = Uri.parse(sb.toString());
        NOTIFICATION_URI = uri;
        TAG = CirrusMediaSource.class.getSimpleName();
        NOTIFICATION_URI_LOCAL = Uri.parse("content://" + str + "/library/cirrus-local");
        TRACKS_NOTIFICATION_URI = Uri.withAppendedPath(uri, "tracks");
        PLAYLISTS_NOTIFICATION_URI = Uri.withAppendedPath(uri, "playlists");
        Uri uri2 = Uri.parse("content://" + str + "/library");
        BASE_URI = uri2;
        Uri uriWithAppendedPath = Uri.withAppendedPath(uri2, "cirrus");
        CIRRUS_URI = uriWithAppendedPath;
        Uri uriWithAppendedPath2 = Uri.withAppendedPath(uri2, "cirrus-local");
        LOCAL_URI = uriWithAppendedPath2;
        CIRRUS_TRACKS_URI = Uri.withAppendedPath(uriWithAppendedPath, "tracks");
        LOCAL_TRACKS_URI = Uri.withAppendedPath(uriWithAppendedPath2, "tracks");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("max(CASE WHEN (prime_status < 400) THEN prime_status ELSE ");
        ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.UNKNOWN;
        sb2.append(contentCatalogStatus.getValue());
        sb2.append(" END)");
        String string = sb2.toString();
        MAX_NON_PREVIOUS_QUERY = string;
        String str2 = "max(CASE WHEN (Track.prime_status < 400) THEN Track.prime_status ELSE " + contentCatalogStatus.getValue() + " END)";
        MAX_NON_PREVIOUS_QUERY_FOR_PLAYLISTS = str2;
        HashMap<String, String> map = new HashMap<>();
        ALBUMS_MAP = map;
        map.put("_id", "album_id");
        map.put("album_art_id", "album_art_id");
        map.put(PageUriUtils.SOURCE_QUERY_PARAM, PageUriUtils.SOURCE_QUERY_PARAM);
        map.put("artist", "album_artist");
        map.put("artist_id", "album_artist_id");
        map.put("title", "album");
        map.put("track_count", "count(album_id)");
        map.put("download_state", "max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN download_state ELSE 0 END)  as download_state");
        map.put("download_status", "(CASE WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2  THEN 2  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 0  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 1 ELSE 2 END ) AS download_status");
        map.put("date_created", "min(date_created)");
        map.put("sort_artist", "sort_album_artist");
        map.put("sort_title", "sort_album");
        map.put(IntlConfiguration.MARKETPLACE, IntlConfiguration.MARKETPLACE);
        map.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "min(prime_status)");
        map.put("content_catalog_status_max_non_previous", string);
        map.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "max(prime_status)");
        map.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "min(ownership_status)");
        map.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, "max(ownership_status)");
        map.put("count", "count(*)");
        map.put("album_asin", "album_asin");
        map.put("artist_asin", "artist_asin");
        map.put("content_encoding", "group_concat(content_encoding,',')");
        map.put("playmode_eligibility", "album_playmode_eligibility");
        HashMap<String, String> map2 = new HashMap<>();
        GENRE_MAP = map2;
        map2.put("_id", "genre_id");
        map2.put("name", ParserUtil.GENRE_SEGMENT_NAME);
        map2.put(PageUriUtils.SOURCE_QUERY_PARAM, PageUriUtils.SOURCE_QUERY_PARAM);
        map2.put("track_count", "count(_id)");
        map2.put("album_count", "count(distinct album_id)");
        map2.put("download_state", "max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN download_state ELSE 0 END)  as download_state");
        map2.put("download_status", "(CASE WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2  THEN 2  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 0  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 1 ELSE 2 END ) AS download_status");
        map2.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "min(prime_status)");
        map2.put("content_catalog_status_max_non_previous", string);
        map2.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "max(prime_status)");
        map2.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "min(ownership_status)");
        map2.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, "max(ownership_status)");
        map2.put("count", "count(*)");
        HashMap<String, String> map3 = new HashMap<>();
        PLAYLISTS_MAP_IN = map3;
        map3.put("_id", "Playlist._id as _id");
        map3.put("name", "Playlist.name");
        map3.put(PageUriUtils.SOURCE_QUERY_PARAM, "Playlist.source");
        map3.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Playlist.type");
        map3.put("luid", "Playlist.luid");
        map3.put("version", "Playlist.version");
        map3.put("dateLastModified", "Playlist.dateLastModified");
        map3.put("dateLastTouched", "max((coalesce (main.recently_played.last_played_time, 0)) / 1000,Playlist.dateCreated) as dateLastTouched");
        map3.put("dateCreated", "dateCreated");
        map3.put("track_count", "count(Track.luid)");
        map3.put("duration", "sum(Track.duration)");
        map3.put("download_state", "Playlist.download_state");
        map3.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "min(Track.ownership_status) as content_ownership_status_min");
        map3.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, "max(Track.ownership_status) as content_ownership_status_max");
        map3.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "min(Track.prime_status) as content_catalog_status_min");
        map3.put("content_catalog_status_max_non_previous", str2 + " as content_catalog_status_max_non_previous");
        map3.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "max(Track.prime_status) as content_catalog_status_max");
        map3.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "min(Track.ownership_status) as content_ownership_status_min");
        map3.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "min(Track.prime_status) as content_catalog_status_min");
        map3.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "max(Track.prime_status) as content_catalog_status_max");
        map3.put("visibility", "Playlist.visibility");
        map3.put("is_sonic_rush", "Playlist.is_sonic_rush as is_sonic_rush");
        map3.put("is_sonic_rush_on_demand", "Playlist.is_sonic_rush_on_demand as is_sonic_rush_on_demand");
        map3.put("is_sonic_rush_golden", "Playlist.is_sonic_rush_golden as is_sonic_rush_golden");
        map3.put("playmode_eligibility", "Playlist.playmode_eligibility as playmode_eligibility");
        map3.put("shared_id", "Playlist.shared_id");
        map3.put("numberOfFollowers", "Playlist.numberOfFollowers");
        HashMap<String, String> map4 = new HashMap<>();
        PRIME_PLAYLISTS_MAP = map4;
        map4.put("_id", "PrimePlaylists._id as _id");
        map4.put("name", "PrimePlaylists.title as name");
        map4.put("luid", "PrimePlaylists.luid as luid");
        map4.put(PageUriUtils.SOURCE_QUERY_PARAM, "PrimePlaylists.source as source");
        map4.put("track_count", "count(PrimePlaylistToTrack.track_asin) as track_count");
        map4.put("download_state", "max(PrimePlaylistTracks.download_state) as download_state");
        map4.put("duration", "PrimePlaylists.duration as duration");
        map4.put("asin", "PrimePlaylists.asin as asin");
        map4.put("is_new", "PrimePlaylists.is_new as is_new");
        map4.put("version", "PrimePlaylists.version as version");
        map4.put(MediaTrack.ROLE_DESCRIPTION, "PrimePlaylists.description as description");
        map4.put("is_prime", "PrimePlaylists.is_prime as is_prime");
        map4.put("is_shared_user_playlist", "PrimePlaylists.is_shared_user_playlist as is_shared_user_playlist");
        map4.put("is_entity_playlist", "PrimePlaylists.is_entity_playlist as is_entity_playlist");
        map4.put("is_nightwing", "PrimePlaylists.is_nightwing as is_nightwing");
        map4.put("is_nightwing_on_demand", "PrimePlaylists.is_nightwing_on_demand as is_nightwing_on_demand");
        map4.put("is_sonic_rush", "PrimePlaylists.is_sonic_rush as is_sonic_rush");
        map4.put("is_sonic_rush_on_demand", "PrimePlaylists.is_sonic_rush_on_demand as is_sonic_rush_on_demand");
        map4.put("is_sonic_rush_golden", "PrimePlaylists.is_sonic_rush_golden as is_sonic_rush_golden");
        map4.put("playmode_eligibility", "PrimePlaylists.playmode_eligibility as playmode_eligibility");
        map4.put("dateLastTouched", "max((coalesce (main.recently_played.last_played_time, 0)) / 1000,PrimePlaylists.created_date) as dateLastTouched");
        map4.put("dateCreated", "PrimePlaylists.created_date as dateCreated");
        map4.put("dateLastModified", "PrimePlaylists.last_modified_date as dateLastModified");
        map4.put("visibility", "PrimePlaylists.visibility as visibility");
        HashMap<String, String> map5 = new HashMap<>();
        PRIME_PLAYLISTS_TRACKS_MAP = map5;
        map5.put("_id", "PrimePlaylistTracks._id as _id");
        map5.put("title", "PrimePlaylistTracks.title as title");
        map5.put("sort_title", "PrimePlaylistTracks.sort_title as sort_title");
        map5.put("track_num", "PrimePlaylistTracks.track_num as track_num");
        map5.put("album_id", "album_id");
        map5.put("artist", "PrimePlaylistTracks.artist as artist");
        map5.put("album", "PrimePlaylistTracks.album as album");
        map5.put(ParserUtil.GENRE_SEGMENT_NAME, "PrimePlaylistTracks.genre as genre");
        map5.put("album_artist", "album_artist");
        map5.put("luid", "luid");
        map5.put("match_hash", "PrimePlaylistTracks.match_hash as match_hash");
        map5.put("duration", "PrimePlaylistTracks.duration as duration");
        map5.put("download_state", "PrimePlaylistTracks.download_state as download_state");
        map5.put("extension", "extension");
        map5.put("lyrics_state", "PrimePlaylistTracks.lyrics_state as lyrics_state");
        map5.put("prime_status", "PrimePlaylistTracks.catalog_status as prime_status");
        map5.put("ownership_status", ContentOwnershipStatus.ADDED + " as ownership_status as ownership_status");
        map5.put("asin", "asin");
        map5.put("artist_asin", "artist_asin");
        map5.put("album_asin", "album_asin");
        map5.put("album_artist_asin", "album_artist_asin");
        map5.put(PageUriUtils.SOURCE_QUERY_PARAM, PageUriUtils.SOURCE_QUERY_PARAM);
        map5.put("track_type", "track_type");
        map5.put("playlist_track_status", "playlist_track_status");
        map5.put("catalog_status", "catalog_status");
        map5.put("fileSizeKilobytes", "fileSizeKilobytes");
        map5.put("album_art_url_large", "album_art_url_large");
        map5.put("album_art_url_thumbnail", "album_art_url_thumbnail");
        map5.put("artwork_id", "artwork_id");
        map5.put("track_source", "track_source");
        map5.put("is_explicit", "is_explicit");
        map5.put("content_encoding", "content_encoding");
        map5.put("local_uri", "local_uri");
        map5.put("asset_qualities", "asset_qualities");
        map5.put("asset_type", "asset_type");
        map5.put("is_exclusion_tagged", "is_exclusion_tagged");
        map5.put("playmode_eligibility", "playmode_eligibility");
        map5.put("catalog_status_tiers", "catalog_status_tiers");
        HashMap<String, String> map6 = new HashMap<>();
        PLAYLISTS_MAP_OUT = map6;
        map6.putAll(map3);
        map6.put("_id", "_id");
        HashMap<String, String> map7 = new HashMap<>();
        ARTISTS_MAP = map7;
        map7.put("_id", "aid");
        map7.put(PageUriUtils.SOURCE_QUERY_PARAM, PageUriUtils.SOURCE_QUERY_PARAM);
        map7.put("artist_asin", "artist_asin");
        map7.put("name", "aname");
        map7.put("track_count", "count(distinct _id)");
        map7.put("album_count", "count(distinct album_id)");
        map7.put("follow_status", "follow_status");
        map7.put("date_created", "artist_date_created");
        map7.put("enabled_notification_events", "enabled_notification_events");
        map7.put("sort_name", "sort_aname");
        map7.put("download_state", "max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN download_state ELSE 0 END)  as download_state");
        map7.put("download_status", "(CASE WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2  THEN 2  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 0  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 1 ELSE 2 END ) AS download_status");
        map7.put(IntlConfiguration.MARKETPLACE, IntlConfiguration.MARKETPLACE);
        map7.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "min(prime_status)");
        map7.put("content_catalog_status_max_non_previous", string);
        map7.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, "min(ownership_status)");
        map7.put(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, "max(ownership_status)");
        map7.put(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "max(prime_status)");
        map7.put("count", "count(*)");
    }

    private static final String downloadStateColumnName() {
        return "(CASE WHEN Track.download_state == " + String.valueOf(0) + " THEN Track.download_state ELSE PrimePlaylistTracks.download_state END) as download_state";
    }

    private int deleteLocalTrackFromLibrary(Uri uri, String selection, String[] selectionArgs) {
        if (!matchLocal(uri)) {
            return 0;
        }
        return getWriteableDb().delete("Track", DbUtil.applyBinaryOperator("source=1", " AND ", selection), selectionArgs);
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 1) {
            return deleteLocalTrackFromLibrary(uri, selection, selectionArgs);
        }
        if (iMatch == 14) {
            return deleteUdoPlaylist(uri);
        }
        if (iMatch == 17) {
            return deleteUdoPlaylistTrack(uri);
        }
        throw new UnsupportedOperationException("Not implemented for URI: " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001f  */
    @Override // com.amazon.mp3.library.provider.ProviderSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.net.Uri insert(android.net.Uri r3, android.content.ContentValues r4) {
        /*
            r2 = this;
            int r0 = com.amazon.mp3.library.provider.DefaultUriMatcher.match(r3)
            r1 = 11
            if (r0 == r1) goto L37
            r1 = 14
            if (r0 == r1) goto L32
            r1 = 29
            if (r0 == r1) goto L1f
            r1 = 32
            if (r0 == r1) goto L19
            r1 = 34
            if (r0 != r1) goto L2a
            goto L1f
        L19:
            android.content.Context r0 = r2.mContext
            com.amazon.mp3.prime.station.StationStorageUtil.insert(r0, r4)
            return r3
        L1f:
            boolean r0 = matchLocal(r3)
            if (r0 == 0) goto L2a
            android.net.Uri r3 = r2.createLocalPrimePlaylist(r3, r4)
            return r3
        L2a:
            java.lang.UnsupportedOperationException r3 = new java.lang.UnsupportedOperationException
            java.lang.String r4 = "Not implemented"
            r3.<init>(r4)
            throw r3
        L32:
            android.net.Uri r3 = r2.addUdoPlaylistTrack(r3, r4)
            return r3
        L37:
            android.net.Uri r3 = r2.createUdoPlaylist(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 14) {
            return addUdoPlaylistTracks(uri, values);
        }
        if (iMatch == 28 || iMatch == 33) {
            return addPrimePlaylistToTracks(uri, values);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a6 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ac A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b2 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c6 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d3 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d9 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0103 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0117 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011d A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0123 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0129 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0134 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013f A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0149 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0153 A[Catch: all -> 0x0180, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x015f A[Catch: all -> 0x0180, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x001d, B:11:0x002b, B:12:0x0033, B:14:0x0040, B:16:0x0048, B:25:0x008d, B:26:0x00a0, B:52:0x0170, B:53:0x0175, B:28:0x00a6, B:29:0x00ac, B:30:0x00b2, B:31:0x00c6, B:32:0x00d3, B:33:0x00d9, B:35:0x00ea, B:37:0x00f5, B:38:0x0103, B:39:0x0117, B:40:0x011d, B:41:0x0123, B:42:0x0129, B:43:0x0134, B:44:0x013f, B:45:0x0149, B:46:0x0153, B:47:0x015f, B:17:0x0060, B:21:0x006c, B:23:0x0084), top: B:59:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x016c  */
    @Override // com.amazon.mp3.library.provider.ProviderSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.database.Cursor query(android.net.Uri r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 14) {
            return renameUdoPlaylist(uri, values);
        }
        if ((iMatch == 29 || iMatch == 34) && matchLocal(uri)) {
            return updateLocalPrimePlaylist(values, selection, selectionArgs);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public void onRegistered(Context context) {
        if (context != null) {
            this.mContext = context;
        }
    }

    public static String getUdoPlaylistSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "LOWER(name) COLLATE LOCALIZED ASC" : "LOWER(name) ASC";
    }

    public static String getPrimePlaylistSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "LOWER(PrimePlaylists.title) COLLATE LOCALIZED ASC" : "LOWER(PrimePlaylists.title) ASC";
    }

    public static String getAlbumNameSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "sort_album_artist COLLATE LOCALIZED ASC" : "sort_album_artist ASC";
    }

    public static String getAlbumTrackSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "sort_album COLLATE LOCALIZED ASC, disc_num COLLATE LOCALIZED ASC, track_num COLLATE LOCALIZED ASC" : "sort_album ASC, disc_num ASC, track_num ASC";
    }

    public static String getTrackSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "sort_title COLLATE LOCALIZED ASC , sort_artist COLLATE LOCALIZED ASC" : "sort_title ASC , sort_artist ASC";
    }

    public static String getAlbumSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "sort_album COLLATE LOCALIZED ASC,sort_artist COLLATE LOCALIZED ASC" : "sort_album ASC,sort_artist ASC";
    }

    public static String getArtistSortOrder() {
        return AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "sort_artist COLLATE LOCALIZED ASC" : "sort_artist ASC";
    }

    public static boolean match(Uri uri) {
        return matchLocal(uri) || matchCloud(uri);
    }

    public static boolean matchLocal(Uri uri) {
        return "cirrus-local".equals(MediaProvider.getSource(uri));
    }

    public static boolean matchCloud(Uri uri) {
        return "cirrus".equals(MediaProvider.getSource(uri));
    }

    public static Long getAlbumOrArtistIdByAsin(final Context context, final Uri uri, final String asin) {
        String str;
        String str2;
        int columnIndex;
        if (uri == null || StringUtils.isBlank(asin)) {
            Log.error(TAG, "uri and asin cannot be null");
            return null;
        }
        if (MediaProvider.Albums.isAlbum(uri)) {
            str = "album_id";
            str2 = "album_asin";
        } else {
            if (!MediaProvider.Artists.isArtist(uri)) {
                Log.error(TAG, "Not an album or artist uri: " + uri.toString());
                return null;
            }
            str = "artist_id";
            str2 = "artist_asin";
        }
        Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(context).rawQuery("SELECT " + str + " FROM Track WHERE " + str2 + " = '" + asin + "'", null);
        try {
            try {
                if (cursorRawQuery.moveToFirst() && (columnIndex = cursorRawQuery.getColumnIndex(str)) != -1) {
                    return Long.valueOf(cursorRawQuery.getLong(columnIndex));
                }
            } catch (Exception e) {
                Log.error(TAG, "Failed to get Id by asin: " + asin, e);
            }
            return null;
        } finally {
            DbUtil.closeCursor(cursorRawQuery);
        }
    }

    private Cursor queryGenres(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int i;
        String[] strArrMergeColumnArrays;
        String strApplyBinaryOperator;
        if (!MediaProvider.isFilterable(uri)) {
            i = 0;
        } else {
            if (type == 24) {
                return filter(uri, projection, selection, selectionArgs, sortOrder);
            }
            i = 1;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        HashMap<String, String> map = GENRE_MAP;
        sQLiteQueryBuilder.setProjectionMap(map);
        sQLiteQueryBuilder.setTables("Track");
        String str = (sortOrder != null || type == 25) ? sortOrder : "LOWER(genre) COLLATE LOCALIZED ASC";
        if (type == 25) {
            strApplyBinaryOperator = DbUtil.applyBinaryOperator("ownership_status<300", "AND", DbUtil.applyBinaryOperator("genre_id=?", "AND", selection));
            strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{uri.getPathSegments().get(i + 3)}, selectionArgs);
        } else {
            strArrMergeColumnArrays = selectionArgs;
            strApplyBinaryOperator = selection;
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(getDb(), projection, strApplyBinaryOperator, strArrMergeColumnArrays, "genre_id", null, str);
        if (cursorQuery == null) {
            return null;
        }
        return new ColumnMappedCursor(cursorQuery, map);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c7 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryAlbums(final int r17, final android.net.Uri r18, java.lang.String[] r19, java.lang.String r20, java.lang.String[] r21, java.lang.String r22) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.queryAlbums(int, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private Cursor queryArtists(final int type, final Uri uri, String selection, String[] selectionArgs, String sortOrder) {
        String str = constructArtistQueryString(type, uri, selection) + " UNION " + constructAlbumArtistQueryString(type, uri, selection);
        String[] strArr = (String[]) Arrays.copyOf(selectionArgs, selectionArgs.length * 2);
        System.arraycopy(selectionArgs, 0, strArr, selectionArgs.length, selectionArgs.length);
        return queryArtistsBase(type, uri, selection, strArr, sortOrder, str);
    }

    private Cursor queryAlbumArtists(final int type, final Uri uri, String selection, String[] selectionArgs, String sortOrder) {
        if (isFollowedArtists(type)) {
            return queryFollowedArtists(sortOrder);
        }
        return queryArtistsBase(type, uri, selection, selectionArgs, sortOrder, constructAlbumArtistQueryString(type, uri, selection));
    }

    private boolean isFollowedArtists(int type) {
        return type == 7 && AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled();
    }

    private Cursor queryArtistsBase(final int type, final Uri uri, String selection, String[] selectionArgs, String sortOrder, String innerQuery) {
        String str = AmazonApplication.getCapabilities().supportsLocalizedCollation() ? " COLLATE LOCALIZED ASC" : " ASC";
        if (type == 8 || TextUtils.isEmpty(sortOrder)) {
            StringBuilder sb = new StringBuilder();
            HashMap<String, String> map = ARTISTS_MAP;
            sb.append(map.get("sort_name"));
            sb.append(" ");
            sb.append(str);
            sb.append(", ");
            sb.append(map.get("name"));
            sb.append(" ");
            sb.append(str);
            sortOrder = sb.toString();
        }
        Cursor cursorRawQuery = getWriteableDb().rawQuery(getBaseArtistSelection(innerQuery, sortOrder), selectionArgs);
        if (cursorRawQuery == null) {
            return null;
        }
        return new ColumnMappedCursor(cursorRawQuery, ARTISTS_MAP);
    }

    private String getBaseArtistSelection(String innerQuery, String sortOrderString) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        HashMap<String, String> map = ARTISTS_MAP;
        sb.append(map.get("_id"));
        sb.append(",");
        sb.append(map.get("name"));
        sb.append(",");
        sb.append(map.get("artist_asin"));
        sb.append(",");
        sb.append(map.get(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN));
        sb.append(",");
        sb.append(map.get(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX));
        sb.append(",");
        sb.append(map.get(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN));
        sb.append(",");
        sb.append(map.get("content_catalog_status_max_non_previous"));
        sb.append(",");
        sb.append(map.get(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX));
        sb.append(",");
        sb.append(map.get("sort_name"));
        sb.append(", count(distinct ");
        sb.append("_id");
        sb.append("), count(distinct ");
        sb.append("album_id");
        sb.append("), max(CASE WHEN (");
        sb.append("prime_status < 400 OR ownership_status < 200");
        sb.append(") THEN ");
        sb.append("download_state");
        sb.append(" ELSE 0 END) ");
        sb.append("download_state");
        sb.append(", ");
        sb.append("(CASE WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2  THEN 2  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 0  WHEN  min(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 2 AND  max(CASE WHEN (prime_status < 400 OR ownership_status < 200) THEN (download_state) ELSE 2 END) == 0 THEN 1 ELSE 2 END ) AS download_status");
        sb.append(",");
        sb.append(IntlConfiguration.MARKETPLACE);
        sb.append(",");
        sb.append(PageUriUtils.SOURCE_QUERY_PARAM);
        sb.append(" from ( ");
        sb.append(innerQuery);
        sb.append(") group by ");
        sb.append(map.get("_id"));
        sb.append(" order by ");
        sb.append(sortOrderString);
        return sb.toString();
    }

    private Cursor queryFollowedArtists(String sortOrder) {
        Cursor cursorRawQuery = getWriteableDb().rawQuery(LikesAndFollowsDBOperations.followedArtistsQuery(sortOrder), null);
        if (cursorRawQuery != null) {
            try {
                if (cursorRawQuery.moveToFirst()) {
                    return new ColumnMappedCursor(cursorRawQuery, ARTISTS_MAP);
                }
            } catch (Exception e) {
                Log.debug(TAG, "Error parsing the artists caused by ", e);
            }
        }
        return null;
    }

    private String constructArtistQueryString(int type, Uri uri, String commonSelection) {
        StringBuilder sb = new StringBuilder();
        sb.append("artist_id as ");
        HashMap<String, String> map = ARTISTS_MAP;
        sb.append(map.get("_id"));
        return constructArtistQueryString(type, uri, commonSelection, new ArrayList(Arrays.asList(sb.toString(), "artist as " + map.get("name"), "sort_artist as " + map.get("sort_name"))), "artist_id", "artist");
    }

    private String constructAlbumArtistQueryString(int type, Uri uri, String commonSelection) {
        StringBuilder sb = new StringBuilder();
        sb.append("album_artist_id as ");
        HashMap<String, String> map = ARTISTS_MAP;
        sb.append(map.get("_id"));
        return constructArtistQueryString(type, uri, commonSelection, new ArrayList(Arrays.asList(sb.toString(), "album_artist as " + map.get("name"), "sort_album_artist as " + map.get("sort_name"))), "album_artist_id", "album_artist");
    }

    private String constructArtistQueryString(int type, Uri uri, String commonSelection, List<String> projection, String idColumnName, String columnName) {
        String strApplyBinaryOperator;
        List<String> pathSegments;
        ArrayList arrayList = new ArrayList(Arrays.asList(PageUriUtils.SOURCE_QUERY_PARAM, "download_state", "ownership_status", "prime_status", "_id", "artist_asin", "album_id", IntlConfiguration.MARKETPLACE, "date_created"));
        arrayList.addAll(projection);
        if (type == 8) {
            try {
                strApplyBinaryOperator = DbUtil.applyBinaryOperator("", "AND", idColumnName + "=" + Long.parseLong(uri.getPathSegments().get(3)));
            } catch (NumberFormatException unused) {
                strApplyBinaryOperator = "";
            }
        } else {
            strApplyBinaryOperator = "";
        }
        String strReplace = (MediaProvider.isFilterable(uri) && (pathSegments = uri.getPathSegments()) != null && pathSegments.size() == 5) ? pathSegments.get(4).replace('*', '%') : "";
        if (!"".equals(strReplace)) {
            strApplyBinaryOperator = DbUtil.applyBinaryOperator(strApplyBinaryOperator, " AND ", DbUtil.buildLikeCriteriaAndSortOrderForFilter(strReplace, new String[]{columnName})[0]);
        }
        String strApplyBinaryOperator2 = DbUtil.applyBinaryOperator(strApplyBinaryOperator, " AND ", commonSelection);
        if (this.isELFEnabledForChild.booleanValue()) {
            strApplyBinaryOperator2 = getSelectionForChildUser(strApplyBinaryOperator2);
        }
        String str = strApplyBinaryOperator2;
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track");
        return sQLiteQueryBuilder.buildQuery((String[]) arrayList.toArray(new String[arrayList.size()]), str, null, null, null, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryTracks(final int r18, final android.net.Uri r19, java.lang.String[] r20, java.lang.String r21, java.lang.String[] r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.queryTracks(int, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private String getSelectionWithDecouplingFilter(final String selection) {
        return DbUtil.applyBinaryOperator("playlist_track_status<" + MusicTrack.PlaylistTrackStatus.CATALOG_TRACK_NOT_IN_LIBRARY.getValue(), "AND", selection);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00de A[Catch: all -> 0x01e3, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x000f, B:9:0x0015, B:11:0x0023, B:17:0x0059, B:27:0x008d, B:30:0x00b2, B:32:0x00c9, B:40:0x00de, B:50:0x00f2, B:52:0x00fa, B:60:0x012b, B:65:0x013d, B:67:0x0147, B:74:0x0176, B:76:0x0185, B:79:0x0196, B:81:0x019c, B:84:0x01a7, B:86:0x01ad, B:89:0x01b8, B:91:0x01be, B:94:0x01ca, B:69:0x0157, B:71:0x015e, B:72:0x0165, B:53:0x0111, B:55:0x0119, B:31:0x00ba, B:13:0x003a, B:15:0x0042), top: B:100:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0185 A[Catch: all -> 0x01e3, TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x000f, B:9:0x0015, B:11:0x0023, B:17:0x0059, B:27:0x008d, B:30:0x00b2, B:32:0x00c9, B:40:0x00de, B:50:0x00f2, B:52:0x00fa, B:60:0x012b, B:65:0x013d, B:67:0x0147, B:74:0x0176, B:76:0x0185, B:79:0x0196, B:81:0x019c, B:84:0x01a7, B:86:0x01ad, B:89:0x01b8, B:91:0x01be, B:94:0x01ca, B:69:0x0157, B:71:0x015e, B:72:0x0165, B:53:0x0111, B:55:0x0119, B:31:0x00ba, B:13:0x003a, B:15:0x0042), top: B:100:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized android.database.Cursor queryPlaylists(android.net.Uri r22, java.lang.String r23, java.lang.String[] r24, java.lang.String r25) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.queryPlaylists(android.net.Uri, java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private Cursor createPlaylistHeaderCursor(String type) {
        MatrixCursor matrixCursor = new MatrixCursor(PLAYLISTS_PROJECTION);
        ContentOwnershipStatus contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.NON_CATALOG;
        matrixCursor.addRow(new Object[]{-1, "cirrus", "", type, 5, "", -1, 0, 0, "0", "", "0", Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
        return matrixCursor;
    }

    private Cursor queryPrimePlaylists(long playlistId, Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        try {
            attachCirrusToRecentsDb(db);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("PrimePlaylists LEFT JOIN PrimePlaylistToTrack ON PrimePlaylists._id=PrimePlaylistToTrack.playlist_id LEFT JOIN PrimePlaylistTracks ON  PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin LEFT JOIN main.recently_played ON PrimePlaylists.asin=main.recently_played.collection_id");
            sQLiteQueryBuilder.setProjectionMap(PRIME_PLAYLISTS_MAP);
            Cursor cursorQuery = sQLiteQueryBuilder.query(db, PRIME_PLAYLISTS_PROJECTION, selection, selectionArgs, "PrimePlaylists._id", null, getPrimePlaylistSortOrder());
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return new ColumnMappedCursor(cursorQuery, PLAYLISTS_MAP_OUT);
        } catch (SQLiteException e) {
            Log.error(TAG, "Querying prime playlists failed.", e);
            return null;
        } finally {
            detachCirrusFromRecentsDb(db);
        }
        detachCirrusFromRecentsDb(db);
    }

    public static Cursor queryIsTrackDownloadedInPrimePlaylistTracksTable(String[] selectionArgs, Context context) {
        try {
            SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(context);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("PrimePlaylistTracks");
            Cursor cursorQuery = sQLiteQueryBuilder.query(readOnlyDatabase, null, "PrimePlaylistTracks.asin=? AND PrimePlaylistTracks.download_state=0", selectionArgs, null, null, null);
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Querying isTrackInPrimePlaylistTracksTable failed", e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d A[Catch: all -> 0x0085, TryCatch #2 {, blocks: (B:5:0x0007, B:6:0x001d, B:8:0x0032, B:12:0x0041, B:17:0x0068, B:20:0x006d, B:30:0x0081, B:31:0x0084, B:27:0x007c, B:11:0x003d, B:14:0x004d, B:16:0x005a, B:26:0x0075), top: B:37:0x0007, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a A[Catch: all -> 0x0072, SQLiteException -> 0x0074, Merged into TryCatch #0 {all -> 0x0072, SQLiteException -> 0x0074, blocks: (B:14:0x004d, B:16:0x005a, B:26:0x0075), top: B:35:0x004d, outer: #2 }, TRY_LEAVE] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[Catch: all -> 0x0085, DONT_GENERATE, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:5:0x0007, B:6:0x001d, B:8:0x0032, B:12:0x0041, B:17:0x0068, B:20:0x006d, B:30:0x0081, B:31:0x0084, B:27:0x007c, B:11:0x003d, B:14:0x004d, B:16:0x005a, B:26:0x0075), top: B:37:0x0007, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized android.database.Cursor queryUdoPlaylists(long r9, android.net.Uri r11, java.lang.String r12, java.lang.String[] r13, java.lang.String r14) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = -1
            int r11 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r11 == 0) goto L1d
            java.lang.String r11 = "Playlist._id=?"
            java.lang.String r0 = "AND"
            java.lang.String r12 = com.amazon.mp3.util.DbUtil.applyBinaryOperator(r11, r0, r12)     // Catch: java.lang.Throwable -> L85
            r11 = 1
            java.lang.String[] r11 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L85
            r0 = 0
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L85
            r11[r0] = r9     // Catch: java.lang.Throwable -> L85
            java.lang.String[] r13 = com.amazon.mp3.util.DbUtil.mergeColumnArrays(r11, r13)     // Catch: java.lang.Throwable -> L85
        L1d:
            r3 = r12
            r4 = r13
            android.database.sqlite.SQLiteQueryBuilder r0 = new android.database.sqlite.SQLiteQueryBuilder     // Catch: java.lang.Throwable -> L85
            r0.<init>()     // Catch: java.lang.Throwable -> L85
            java.lang.String r9 = "Playlist LEFT JOIN main.recently_played ON Playlist._id=main.recently_played.collection_id LEFT JOIN PlaylistTrack ON Playlist._id=PlaylistTrack.udo_playlist_id LEFT JOIN Track ON PlaylistTrack.track_luid=Track.luid"
            r0.setTables(r9)     // Catch: java.lang.Throwable -> L85
            java.util.HashMap<java.lang.String, java.lang.String> r9 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.PLAYLISTS_MAP_IN     // Catch: java.lang.Throwable -> L85
            r0.setProjectionMap(r9)     // Catch: java.lang.Throwable -> L85
            java.lang.String r5 = "Playlist._id"
            if (r14 == 0) goto L3d
            java.lang.String r9 = "dateLastModified"
            boolean r9 = r14.contains(r9)     // Catch: java.lang.Throwable -> L85
            if (r9 == 0) goto L3d
            java.lang.String r9 = "dateLastModified DESC"
            goto L41
        L3d:
            java.lang.String r9 = getUdoPlaylistSortOrder()     // Catch: java.lang.Throwable -> L85
        L41:
            r7 = r9
            android.content.Context r9 = r8.mContext     // Catch: java.lang.Throwable -> L85
            com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase r9 = com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase.getInstance(r9)     // Catch: java.lang.Throwable -> L85
            android.database.sqlite.SQLiteDatabase r9 = r9.getDb()     // Catch: java.lang.Throwable -> L85
            r10 = 0
            r8.attachCirrusToRecentsDb(r9)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            java.lang.String[] r2 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.PLAYLISTS_PROJECTION     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            r6 = 0
            r1 = r9
            android.database.Cursor r11 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            if (r11 == 0) goto L6d
            int r12 = r11.getPosition()     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            r11.moveToPosition(r12)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            com.amazon.mp3.library.db.ColumnMappedCursor r12 = new com.amazon.mp3.library.db.ColumnMappedCursor     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            java.util.HashMap<java.lang.String, java.lang.String> r13 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.PLAYLISTS_MAP_OUT     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            r12.<init>(r11, r13)     // Catch: java.lang.Throwable -> L72 android.database.sqlite.SQLiteException -> L74
            r8.detachCirrusFromRecentsDb(r9)     // Catch: java.lang.Throwable -> L85
            monitor-exit(r8)
            return r12
        L6d:
            r8.detachCirrusFromRecentsDb(r9)     // Catch: java.lang.Throwable -> L85
            monitor-exit(r8)
            return r10
        L72:
            r10 = move-exception
            goto L81
        L74:
            r11 = move-exception
            java.lang.String r12 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.TAG     // Catch: java.lang.Throwable -> L72
            java.lang.String r13 = "Querying udo playlists failed."
            com.amazon.mp3.util.Log.error(r12, r13, r11)     // Catch: java.lang.Throwable -> L72
            r8.detachCirrusFromRecentsDb(r9)     // Catch: java.lang.Throwable -> L85
            monitor-exit(r8)
            return r10
        L81:
            r8.detachCirrusFromRecentsDb(r9)     // Catch: java.lang.Throwable -> L85
            throw r10     // Catch: java.lang.Throwable -> L85
        L85:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.queryUdoPlaylists(long, android.net.Uri, java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private Cursor querySingleUdoPlaylist(final Uri uri) {
        return queryUdoPlaylists(Long.parseLong(uri.getPathSegments().get(4)), uri, null, null, null);
    }

    private Cursor queryUdoPlaylistTracks(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Objects.requireNonNull(projection);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("udo_playlist_id=?", "AND", selection);
        if (this.isELFEnabledForChild.booleanValue()) {
            strApplyBinaryOperator = getSelectionForChildUser(strApplyBinaryOperator);
        }
        String str = strApplyBinaryOperator;
        String[] strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{uri.getPathSegments().get(4)}, selectionArgs);
        boolean zEquals = "true".equals(uri.getQueryParameter("scratch"));
        sQLiteQueryBuilder.setTables(zEquals ? "Track INNER JOIN PlaylistTrackScratch ON Track.luid=PlaylistTrackScratch.track_luid LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid" : "Track INNER JOIN PlaylistTrack ON Track.luid=PlaylistTrack.track_luid LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid");
        String[] strArr = new String[projection.length];
        for (int i = 0; i < projection.length; i++) {
            String str2 = projection[i];
            if (str2.equals("udo_playlist_track_id")) {
                str2 = (zEquals ? "PlaylistTrackScratch" : "PlaylistTrack") + "._id AS udo_playlist_track_id";
            } else if (str2.equals("_id")) {
                str2 = "Track._id";
            }
            strArr[i] = str2;
        }
        if (sortOrder == null) {
            sortOrder = "udo ASC";
        }
        return sQLiteQueryBuilder.query(getDb(), strArr, str, strArrMergeColumnArrays, null, null, sortOrder);
    }

    private String getSmartPlaylistWhereClause(long id) {
        String str = "date_created > (1000 * strftime('%s', 'now', '-90 days'))";
        if (id == 0) {
            str = "((purchased = 1) OR (purchased IS NULL AND uploaded IS NULL AND order_id NOT NULL))";
        } else if (id != 1 && id != 2) {
            if (id == 3) {
                str = "last_played_time > (1000 * strftime('%s', 'now', '-90 days')) AND (Track.title IS NOT NULL  OR PrimePlaylistTracks.title IS NOT NULL)";
            } else {
                throw new IllegalArgumentException("Unknown smart playlist queried!");
            }
        }
        if (id != 3) {
            str = str + " AND ownership_status < " + String.valueOf(300);
        }
        return this.isELFEnabledForChild.booleanValue() ? getSelectionForChildUser(str) : str;
    }

    private Cursor querySmartPlaylistTracks(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        long j = Long.parseLong(uri.getPathSegments().get(4));
        if (j == 3) {
            return queryRecentlyPlayedTracks(projection, selection, selectionArgs);
        }
        String smartPlaylistWhereClause = getSmartPlaylistWhereClause(j);
        if (j == 0 && sortOrder == null) {
            sortOrder = "date_purchased DESC";
        }
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator(smartPlaylistWhereClause, "AND", selection);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid");
        if (sortOrder == null) {
            sortOrder = "date_created DESC";
        }
        return sQLiteQueryBuilder.query(getDb(), projection, strApplyBinaryOperator, selectionArgs, null, null, sortOrder);
    }

    private boolean isRecentlyPlayedSmartPlaylist(final Uri contentUri, final int matchType) {
        return matchType == 15 && MediaProvider.isSmartPlaylist(contentUri) && contentUri.getPathSegments().size() >= 5 && Long.parseLong(contentUri.getPathSegments().get(4)) == 3;
    }

    private synchronized Cursor queryRecentlyPlayedTracks(final String[] projection, String selection, String[] selectionArgs) {
        Cursor cursorQuery;
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        String[] strArr = (String[]) Arrays.copyOf(projection, projection.length);
        try {
            try {
                attachCirrusToRecentsDb(db);
                String strApplyBinaryOperator = DbUtil.applyBinaryOperator(getSmartPlaylistWhereClause(3L), "AND", selection);
                SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                sQLiteQueryBuilder.setTables("main.recently_played_tracks LEFT JOIN Track ON Track.asin=main.recently_played_tracks.recent_track_asin OR Track.luid=main.recently_played_tracks.recent_track_luid LEFT JOIN LocalTrackUri ON LocalTrackUri.track_asin=main.recently_played_tracks.recent_track_asin OR LocalTrackUri.track_luid=main.recently_played_tracks.recent_track_luid LEFT JOIN PrimePlaylistTracks ON PrimePlaylistTracks.asin=main.recently_played_tracks.recent_track_asin");
                Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).rawQuery("Select * from PrimePlaylistTracks", null);
                for (int i = 0; i < strArr.length; i++) {
                    if (cursorRawQuery.getColumnIndex(strArr[i]) != -1) {
                        if (!AmazonApplication.getCapabilities().isBadgingInRecentsTrackSupported() && strArr[i] == "content_encoding") {
                            strArr[i] = "null as " + strArr[i];
                        } else {
                            strArr[i] = "case when Track." + strArr[i] + " is null then PrimePlaylistTracks." + strArr[i] + " else Track." + strArr[i] + " end as " + strArr[i];
                        }
                    }
                }
                cursorQuery = sQLiteQueryBuilder.query(db, strArr, strApplyBinaryOperator, selectionArgs, "_uid", null, "main.recently_played_tracks.last_played_time DESC");
                cursorQuery.moveToPosition(cursorQuery.getPosition());
            } finally {
                detachCirrusFromRecentsDb(db);
            }
        } catch (SQLiteException e) {
            Log.error(TAG, "Querying recently played tracks failed.", e);
            return null;
        }
        return cursorQuery;
    }

    private void detachCirrusFromRecentsDb(final SQLiteDatabase recents) {
        try {
            recents.execSQL("detach database CirrusDatabase");
        } catch (Exception unused) {
        }
    }

    private void attachCirrusToRecentsDb(final SQLiteDatabase recents) {
        recents.execSQL("attach database ? as CirrusDatabase", new String[]{this.mContext.getDatabasePath("CirrusMediaSource.db").getPath()});
    }

    private Cursor querySingleSmartPlaylist(final Uri uri) throws Throwable {
        MatrixCursor matrixCursorQuerySmartPlaylists = querySmartPlaylists(uri);
        MatrixCursor matrixCursor = new MatrixCursor(PLAYLISTS_PROJECTION);
        long j = Long.parseLong(uri.getPathSegments().get(4));
        boolean z = 0 == j || 1 == j;
        int columnIndexOrThrow = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow("_id");
        int columnIndexOrThrow2 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow(PageUriUtils.SOURCE_QUERY_PARAM);
        int columnIndexOrThrow3 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow("name");
        int columnIndexOrThrow4 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        int columnIndexOrThrow5 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow("luid");
        int columnIndexOrThrow6 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow("track_count");
        int columnIndexOrThrow7 = matrixCursorQuerySmartPlaylists.getColumnIndexOrThrow("version");
        matrixCursorQuerySmartPlaylists.moveToPosition(-1);
        while (true) {
            if (!matrixCursorQuerySmartPlaylists.moveToNext()) {
                break;
            }
            if (matrixCursorQuerySmartPlaylists.getLong(columnIndexOrThrow) == j) {
                Object[] objArr = new Object[27];
                objArr[0] = Long.valueOf(matrixCursorQuerySmartPlaylists.getLong(columnIndexOrThrow));
                objArr[1] = Integer.valueOf(matrixCursorQuerySmartPlaylists.getInt(columnIndexOrThrow2));
                objArr[2] = matrixCursorQuerySmartPlaylists.getString(columnIndexOrThrow3);
                objArr[3] = matrixCursorQuerySmartPlaylists.getString(columnIndexOrThrow4);
                objArr[4] = 5;
                objArr[5] = matrixCursorQuerySmartPlaylists.getString(columnIndexOrThrow5);
                objArr[6] = matrixCursorQuerySmartPlaylists.getString(columnIndexOrThrow7);
                objArr[7] = Integer.valueOf(matrixCursorQuerySmartPlaylists.getInt(columnIndexOrThrow6));
                objArr[8] = -1;
                objArr[9] = "0";
                objArr[10] = "";
                objArr[11] = "0";
                objArr[12] = Integer.valueOf((z ? ContentOwnershipStatus.OWNED : ContentOwnershipStatus.ADDED).getValue());
                objArr[13] = Integer.valueOf((z ? ContentOwnershipStatus.OWNED : ContentOwnershipStatus.ADDED).getValue());
                ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.NON_CATALOG;
                objArr[14] = Integer.valueOf(contentCatalogStatus.getValue());
                objArr[15] = Integer.valueOf(contentCatalogStatus.getValue());
                objArr[16] = Integer.valueOf(contentCatalogStatus.getValue());
                objArr[17] = Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt());
                objArr[18] = 0;
                objArr[19] = 0;
                objArr[20] = 0;
                objArr[21] = "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}";
                objArr[22] = 0;
                objArr[23] = 0;
                objArr[24] = 0;
                objArr[25] = CirrusDatabase.Playlists.DEFAULT_SHARED_ID;
                objArr[26] = 0;
                matrixCursor.addRow(objArr);
            }
        }
        if (matrixCursor.getCount() != 0) {
            return matrixCursor;
        }
        return null;
    }

    private Cursor queryPrimeArtistTracks(final Uri uri, String[] projection, String sortOrder) {
        String[] strArr;
        try {
            SQLiteDatabase db = getDb();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("PrimeArtistTracks LEFT JOIN Track ON Track.asin=PrimeArtistTracks.track_asin OR Track.luid=PrimeArtistTracks.track_luid LEFT JOIN LocalTrackUri ON LocalTrackUri.track_asin=PrimeArtistTracks.track_asin OR LocalTrackUri.track_luid=PrimeArtistTracks.track_luid");
            if (projection != null) {
                strArr = (String[]) projection.clone();
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i].equals("_id")) {
                        strArr[i] = "Track._id";
                    }
                }
            } else {
                strArr = null;
            }
            String[] strArr2 = {Long.toString(MediaProvider.PrimeArtists.getArtistId(uri))};
            if (sortOrder == null) {
                sortOrder = "PrimeArtistTracks._id ASC";
            }
            Cursor cursorQuery = sQLiteQueryBuilder.query(db, strArr, "prime_artist_id=?", strArr2, null, null, sortOrder);
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Querying prime artist tracks failed.", e);
            return null;
        }
    }

    private Cursor queryPrimePlaylistTracks(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        List<String> pathSegments = uri.getPathSegments();
        int playlistId = new PrimePlaylistDatabaseManager().getPlaylistId(pathSegments.get(4), !"cirrus".equals(pathSegments.get(1)) ? 1 : 0);
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("playlist_id=? ", "AND", str);
        String[] strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{String.valueOf(playlistId)}, strArr2);
        String[] strArr3 = PRIME_PLAYLISTS_TRACK_PROJECTION;
        sQLiteQueryBuilder.setTables("PrimePlaylistToTrack LEFT JOIN PrimePlaylistTracks ON PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin LEFT JOIN Track ON PrimePlaylistToTrack.track_asin=Track.asin LEFT JOIN LocalTrackUri ON (LocalTrackUri.track_luid IS NULL OR LocalTrackUri.track_luid='') AND PrimePlaylistTracks.asin=LocalTrackUri.track_asin");
        Cursor cursorQuery = sQLiteQueryBuilder.query(getDb(), strArr3, strApplyBinaryOperator, strArrMergeColumnArrays, "PrimePlaylistTracks.asin", null, str2);
        if (cursorQuery == null) {
            return null;
        }
        return new ColumnMappedCursor(cursorQuery, PRIME_PLAYLISTS_TRACKS_MAP);
    }

    private Uri getTruePlaylistUri(final Uri uri) {
        Uri.Builder builderBuildUpon;
        List<String> pathSegments = uri.getPathSegments();
        long j = Long.parseLong(pathSegments.get(3));
        String str = pathSegments.get(1);
        if (j == 0 || j == 1 || j == 2) {
            builderBuildUpon = MediaProvider.SmartPlaylists.getContentUri(str, j).buildUpon();
        } else {
            builderBuildUpon = MediaProvider.UdoPlaylists.getContentUri(str, j).buildUpon();
        }
        for (int i = 4; i < pathSegments.size(); i++) {
            builderBuildUpon.appendPath(pathSegments.get(i));
        }
        return builderBuildUpon.build();
    }

    private Cursor querySearch(Uri uri) {
        String str;
        String source = MediaProvider.getSource(uri);
        String strReplace = uri.getLastPathSegment().toLowerCase(Locale.ROOT).replace("'", "''");
        String queryParameter = uri.getQueryParameter("limit");
        int i = 30;
        if (queryParameter != null) {
            try {
                i = Integer.parseInt(queryParameter);
            } catch (NumberFormatException unused) {
            }
        }
        if ("cirrus".equals(source)) {
            str = " AND source=" + Integer.toString(0);
        } else if ("cirrus-local".equals(source)) {
            str = " AND source=" + Integer.toString(1);
        } else {
            str = "";
        }
        return getWriteableDb().rawQuery("select distinct 'track' as type, title as name, artist, album_id as id from Track where title like '%" + strReplace + "%' " + str + " union select distinct 'artist' as " + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + ", album_artist as name, artist, album_artist_id as id from Track where album_artist like '%" + strReplace + "%' " + str + " union select distinct 'artist' as " + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + ", artist as name, artist, album_artist_id as id from Track where artist like '%" + strReplace + "%' " + str + " union select distinct 'album' as " + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + ", album as name, artist, album_id as id from Track where album like '%" + strReplace + "%' " + str + " order by name limit " + i, null);
    }

    private int renameUdoPlaylist(final Uri uri, final ContentValues values) {
        String lastPathSegment = uri.getLastPathSegment();
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(uri);
        if (matchCloud(uri) || (playlist.getDownloadState() == 0 && ConnectivityUtil.hasAnyInternetConnection(this.mContext))) {
            String strStringFromCursor = DbUtil.stringFromCursor(getDb().query("Playlist", new String[]{"luid"}, "_id=?", new String[]{lastPathSegment}, null, null, null));
            if (strStringFromCursor != null) {
                try {
                    if (new MC2PlaylistApi().renamePlaylist(this.mContext, strStringFromCursor, values.getAsString("name"))) {
                        notifyChange();
                        return 1;
                    }
                } catch (AbstractHttpClient.HttpClientException e) {
                    Log.error(TAG, "Encountered HttpClientException while renaming playlist:", e);
                    return 0;
                } catch (ServiceException e2) {
                    Log.error(TAG, "Encountered ServiceException while renaming playlist:", e2);
                }
            }
            return 0;
        }
        values.put("updatedSinceLastSync", "1");
        getWriteableDb().update("Playlist", values, "_id=?", new String[]{lastPathSegment});
        notifyChange();
        new EditPlaylistMetricsLogger(this.mContext).sendOfflinePlaylistEdited(EntityType.USER_PLAYLIST.name(), EntityIdType.CD_OBJECT_ID.getMetricsValue(), DbUtil.stringFromCursor(getDb().query("Playlist", new String[]{"luid"}, "_id=?", new String[]{lastPathSegment}, null, null, null)));
        return 1;
    }

    private Uri createUdoPlaylist(final Uri uri, final ContentValues values) {
        if (matchCloud(uri)) {
            values.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 0);
            return createUdoCloudPlaylist(values, false);
        }
        long jLongValue = values.containsKey("_id") ? values.getAsLong("_id").longValue() : Calendar.getInstance().getTimeInMillis();
        values.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 1);
        values.put("_id", Long.valueOf(jLongValue));
        values.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
        values.put("luid", "localPlaylist" + jLongValue);
        values.put("version", "1:0");
        values.put("exists_flag", (Integer) 1);
        values.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 1);
        values.put("updatedSinceLastSync", (Integer) 1);
        values.put("download_state", (Integer) 0);
        long jCurrentTimeSeconds = Clock.currentTimeSeconds();
        values.put("dateCreated", Long.valueOf(jCurrentTimeSeconds));
        values.put("dateLastModified", Long.valueOf(jCurrentTimeSeconds));
        Log.debug(TAG, "Insert a local playlist entry to DB: %s", Long.valueOf(jLongValue));
        try {
            getWriteableDb().insert("Playlist", null, values);
        } catch (Exception e) {
            Log.error(TAG, "failed to create a local playlist " + jLongValue, e);
        }
        notifyChange();
        return MediaProvider.UdoPlaylists.getContentUri("cirrus-local", jLongValue);
    }

    private Uri createUdoCloudPlaylist(final ContentValues values, final boolean createdInOfflineView) {
        try {
            long jCreatePlaylist = new MC2PlaylistApi().createPlaylist(this.mContext, values.getAsString("name"), createdInOfflineView);
            if (jCreatePlaylist == -1) {
                return null;
            }
            notifyChange();
            return MediaProvider.UdoPlaylists.getContentUri("cirrus", jCreatePlaylist);
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Encountered HttpClientException while creating playlist:", e);
            return null;
        } catch (ServiceException e2) {
            Log.error(TAG, "Encountered ServiceException while creating playlist:", e2);
            return null;
        }
    }

    private Uri createLocalPrimePlaylist(final Uri uri, final ContentValues values) {
        getWriteableDb().insert("PrimePlaylists", null, values);
        SettingsUtil.setHasOfflinePrimePlaylists(this.mContext, true);
        notifyChange();
        String asString = values.getAsString("asin");
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(uri)) {
            return MediaProvider.SharedUserPlaylists.getContentUri("cirrus-local", asString);
        }
        return MediaProvider.PrimePlaylists.getContentUri("cirrus-local", asString);
    }

    private int updateLocalPrimePlaylist(final ContentValues values, final String selection, final String[] selectionArgs) {
        int iUpdate = getWriteableDb().update("PrimePlaylists", values, selection, selectionArgs);
        notifyChange();
        return iUpdate;
    }

    public static String luidFromPlaylistUri(SQLiteDatabase db, Uri uri) {
        return DbUtil.stringFromCursor(db.query("Playlist", new String[]{"luid"}, "_id=? ", new String[]{String.valueOf(Long.parseLong(uri.getLastPathSegment()))}, null, null, null));
    }

    private int deleteUdoPlaylist(Uri uri) {
        boolean zDeletePlaylist;
        String strLuidFromPlaylistUri = luidFromPlaylistUri(getDb(), uri);
        if (matchCloud(uri)) {
            try {
                zDeletePlaylist = new MC2PlaylistApi().deletePlaylist(this.mContext, strLuidFromPlaylistUri);
            } catch (AbstractHttpClient.HttpClientException e) {
                Log.error(TAG, "Encountered HttpClientException while deleting playlist:", e);
                return 0;
            } catch (ServiceException e2) {
                Log.error(TAG, "Encountered ServiceException while deleting playlist:", e2);
                return 0;
            }
        } else {
            long j = Long.parseLong(uri.getLastPathSegment());
            boolean zDeleteDownloadedPlaylist = PlaylistTrackUtil.deleteDownloadedPlaylist(this.mContext, j);
            if (zDeleteDownloadedPlaylist) {
                PlaylistCache.getInstance().removePlaylist(uri);
                EditPlaylistMetricsLogger editPlaylistMetricsLogger = new EditPlaylistMetricsLogger(this.mContext);
                if (StringUtils.isNotBlank(strLuidFromPlaylistUri)) {
                    editPlaylistMetricsLogger.sendPlaylistDownloadDisabled(EntityType.USER_PLAYLIST.name(), EntityIdType.CD_OBJECT_ID.getMetricsValue(), strLuidFromPlaylistUri);
                } else {
                    editPlaylistMetricsLogger.sendPlaylistDownloadDisabled(EntityType.USER_PLAYLIST.name(), EntityIdType.PLAYLIST_ID.getMetricsValue(), Long.toString(j));
                }
            }
            zDeletePlaylist = zDeleteDownloadedPlaylist;
        }
        if (!zDeletePlaylist) {
            return 0;
        }
        notifyChange();
        return 1;
    }

    private int deleteUdoPlaylistTrack(Uri uri) {
        throwIfNotScratch(uri);
        SQLiteDatabase writeableDb = getWriteableDb();
        DbUtil.beginTransaction(writeableDb);
        try {
            String queryParameter = uri.getQueryParameter("udo");
            int iDelete = writeableDb.delete("PlaylistTrackScratch", "_id=?", new String[]{uri.getLastPathSegment()});
            if (iDelete > 0) {
                writeableDb.execSQL(" UPDATE PlaylistTrackScratch SET udo=udo-1 WHERE udo>=?", new String[]{queryParameter});
            }
            writeableDb.setTransactionSuccessful();
            writeableDb.endTransaction();
            notifyChange();
            return iDelete;
        } catch (Throwable th) {
            writeableDb.endTransaction();
            throw th;
        }
    }

    private int addPrimePlaylistToTracks(Uri uri, ContentValues[] values) {
        SQLiteDatabase writeableDb = getWriteableDb();
        DbUtil.beginTransaction(writeableDb);
        int i = 0;
        for (ContentValues contentValues : values) {
            try {
                if (addPrimePlaylistToTrack(contentValues) != -1) {
                    i++;
                }
            } finally {
                writeableDb.endTransaction();
            }
        }
        writeableDb.setTransactionSuccessful();
        if (i > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return i;
    }

    private long addPrimePlaylistToTrack(ContentValues values) {
        return getWriteableDb().insert("PrimePlaylistToTrack", null, values);
    }

    private int addUdoPlaylistTracks(Uri uri, ContentValues[] values) {
        throwIfNotScratch(uri);
        Long lValueOf = Long.valueOf(uri.getLastPathSegment());
        SQLiteDatabase writeableDb = getWriteableDb();
        DbUtil.beginTransaction(writeableDb);
        int i = 0;
        for (ContentValues contentValues : values) {
            try {
                if (rawAddUdoPlaylistTrack(lValueOf.longValue(), contentValues) != -1) {
                    i++;
                }
            } finally {
                writeableDb.endTransaction();
            }
        }
        writeableDb.setTransactionSuccessful();
        if (i > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return i;
    }

    private Uri addUdoPlaylistTrack(Uri uri, ContentValues values) {
        throwIfNotScratch(uri);
        Long lValueOf = Long.valueOf(uri.getLastPathSegment());
        long jRawAddUdoPlaylistTrack = rawAddUdoPlaylistTrack(lValueOf.longValue(), values);
        if (jRawAddUdoPlaylistTrack == -1) {
            return null;
        }
        this.mContext.getContentResolver().notifyChange(uri, null);
        return MediaProvider.UdoPlaylistTracks.getContentUri(MediaProvider.getSource(uri), lValueOf.longValue(), jRawAddUdoPlaylistTrack);
    }

    private long rawAddUdoPlaylistTrack(long playlistId, ContentValues values) {
        values.put("udo_playlist_id", Long.valueOf(playlistId));
        return getWriteableDb().insert("PlaylistTrackScratch", "_id", values);
    }

    private Cursor filterUdoPlaylists(final String filter, final String sourceId, final boolean udoPlaylistOnly, final String udoPlaylistExclude) {
        String strApplyBinaryOperator;
        String[] strArrMergeColumnArrays;
        if (filter == null || filter.length() == 0) {
            strApplyBinaryOperator = null;
        } else {
            strApplyBinaryOperator = "name LIKE '%" + filter.replace("'", "''") + "%'";
        }
        if (udoPlaylistOnly) {
            if (strApplyBinaryOperator != null) {
                strApplyBinaryOperator = strApplyBinaryOperator + " AND Playlist._id<>?";
            } else {
                strApplyBinaryOperator = "Playlist._id<>?";
            }
            strArrMergeColumnArrays = new String[]{udoPlaylistExclude};
        } else {
            strArrMergeColumnArrays = null;
        }
        if ("cirrus".equals(sourceId)) {
            strApplyBinaryOperator = DbUtil.applyBinaryOperator("Playlist.source=?", "AND", strApplyBinaryOperator);
            strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{Integer.toString(0)}, strArrMergeColumnArrays);
        } else if ("cirrus-local".equals(sourceId)) {
            strApplyBinaryOperator = DbUtil.applyBinaryOperator("Playlist.source=?", "AND", strApplyBinaryOperator);
            strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{Integer.toString(1)}, strArrMergeColumnArrays);
        }
        String str = strApplyBinaryOperator;
        String[] strArr = strArrMergeColumnArrays;
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        try {
            attachCirrusToRecentsDb(db);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("Playlist LEFT JOIN main.recently_played ON Playlist._id=main.recently_played.collection_id LEFT JOIN PlaylistTrack ON Playlist._id=PlaylistTrack.udo_playlist_id LEFT JOIN Track ON PlaylistTrack.track_luid=Track.luid");
            sQLiteQueryBuilder.setProjectionMap(PLAYLISTS_MAP_IN);
            Cursor cursorQuery = sQLiteQueryBuilder.query(db, PLAYLISTS_PROJECTION, str, strArr, "Playlist._id", null, "LOWER (name) ASC");
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Failed to filter Udo playlists. " + e);
            return null;
        } finally {
            detachCirrusFromRecentsDb(db);
        }
    }

    private Cursor filterPrimePlaylists(String str, String str2) {
        int i = !"cirrus".equals(str2) ? 1 : 0;
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("PrimePlaylists.source=?", "AND", "PrimePlaylists.is_following = ?");
        if (str != null && str.length() > 0) {
            strApplyBinaryOperator = strApplyBinaryOperator + " AND name LIKE '%" + str.replace("'", "''") + "%'";
        }
        String str3 = strApplyBinaryOperator;
        String[] strArr = {Integer.toString(i), "1"};
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PrimePlaylists LEFT JOIN PrimePlaylistToTrack ON PrimePlaylists._id=PrimePlaylistToTrack.playlist_id LEFT JOIN PrimePlaylistTracks ON  PrimePlaylistToTrack.track_asin=PrimePlaylistTracks.asin LEFT JOIN main.recently_played ON PrimePlaylists.asin=main.recently_played.collection_id");
        sQLiteQueryBuilder.setProjectionMap(PRIME_PLAYLISTS_MAP);
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        try {
            attachCirrusToRecentsDb(db);
            String[] strArr2 = PRIME_PLAYLISTS_PROJECTION;
            StringBuilder sb = new StringBuilder();
            sb.append("LOWER (name) ");
            sb.append(AmazonApplication.getCapabilities().supportsLocalizedCollation() ? "COLLATE LOCALIZED ASC" : "ASC");
            Cursor cursorQuery = sQLiteQueryBuilder.query(db, strArr2, str3, strArr, "PrimePlaylists._id", null, sb.toString());
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Filter prime playlists failed.", e);
            return null;
        } finally {
            detachCirrusFromRecentsDb(db);
        }
    }

    private Cursor filterPlaylists(Uri uri) throws Throwable {
        boolean z;
        String queryParameter;
        Cursor cursor;
        Cursor cursor2;
        MatrixCursor matrixCursorQuerySmartPlaylists;
        Cursor cursorCreatePlaylistHeaderCursor;
        Cursor cursor3;
        Cursor cursorCreatePlaylistHeaderCursor2;
        if ("udo_only_true".equals(uri.getQueryParameter("udo_only"))) {
            queryParameter = uri.getQueryParameter("udo_playlist_exclude_id");
            z = true;
        } else {
            z = false;
            queryParameter = null;
        }
        String lowerCase = uri.getPathSegments().size() >= 5 ? uri.getLastPathSegment().toLowerCase(Locale.US) : null;
        String source = MediaProvider.getSource(uri);
        Cursor cursorFilterUdoPlaylists = filterUdoPlaylists(lowerCase, source, z, queryParameter);
        if (cursorFilterUdoPlaylists != null) {
            cursorFilterUdoPlaylists.setNotificationUri(this.mContext.getContentResolver(), uri);
            cursorFilterUdoPlaylists = new ColumnMappedCursor(cursorFilterUdoPlaylists, PLAYLISTS_MAP_OUT);
        }
        if (z) {
            return cursorFilterUdoPlaylists;
        }
        Cursor cursorFilterPrimePlaylists = filterPrimePlaylists(lowerCase, source);
        if (lowerCase == null || lowerCase.length() == 0) {
            cursor = cursorFilterUdoPlaylists;
            cursor2 = cursorFilterPrimePlaylists;
            matrixCursorQuerySmartPlaylists = querySmartPlaylists(uri);
        } else {
            MatrixCursor matrixCursorQuerySmartPlaylists2 = querySmartPlaylists(uri);
            matrixCursorQuerySmartPlaylists = new MatrixCursor(PLAYLISTS_PROJECTION);
            int columnIndexOrThrow = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("name");
            int columnIndexOrThrow3 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow(PageUriUtils.SOURCE_QUERY_PARAM);
            int columnIndexOrThrow4 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            int columnIndexOrThrow5 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("luid");
            int columnIndexOrThrow6 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("track_count");
            int columnIndexOrThrow7 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("download_state");
            int columnIndexOrThrow8 = matrixCursorQuerySmartPlaylists2.getColumnIndexOrThrow("version");
            cursor2 = cursorFilterPrimePlaylists;
            matrixCursorQuerySmartPlaylists2.moveToPosition(-1);
            while (true) {
                if (!matrixCursorQuerySmartPlaylists2.moveToNext()) {
                    cursor = cursorFilterUdoPlaylists;
                    break;
                }
                cursor = cursorFilterUdoPlaylists;
                if (matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow2).toLowerCase(Locale.US).contains(lowerCase)) {
                    ContentOwnershipStatus contentOwnershipStatus = ContentOwnershipStatus.OWNED;
                    ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.NON_CATALOG;
                    matrixCursorQuerySmartPlaylists.addRow(new Object[]{Long.valueOf(matrixCursorQuerySmartPlaylists2.getLong(columnIndexOrThrow)), matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow3), matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow2), matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow4), Integer.valueOf(matrixCursorQuerySmartPlaylists2.getInt(columnIndexOrThrow7)), matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow5), matrixCursorQuerySmartPlaylists2.getString(columnIndexOrThrow8), Integer.valueOf(matrixCursorQuerySmartPlaylists2.getInt(columnIndexOrThrow6)), -1, "0", "", "0", Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
                    break;
                }
                cursorFilterUdoPlaylists = cursor;
            }
        }
        if ("true".equals(uri.getQueryParameter("include_playlist_headers"))) {
            Cursor cursorCreatePlaylistHeaderCursor3 = ("true".equals(uri.getQueryParameter("hideSmartPlaylistHeader")) || matrixCursorQuerySmartPlaylists == null || matrixCursorQuerySmartPlaylists.getCount() <= 0) ? null : createPlaylistHeaderCursor("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist.header");
            cursorCreatePlaylistHeaderCursor = (cursor == null || cursor.getCount() <= 0) ? null : createPlaylistHeaderCursor("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist.header");
            if (cursor2 != null && cursor2.getCount() > 0) {
                cursorCreatePlaylistHeaderCursor2 = createPlaylistHeaderCursor("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist.header");
                cursor3 = cursorCreatePlaylistHeaderCursor3;
                return new SafeMergeCursor(new Cursor[]{cursor3, matrixCursorQuerySmartPlaylists, cursorCreatePlaylistHeaderCursor, cursor, cursorCreatePlaylistHeaderCursor2, cursor2});
            }
            cursor3 = cursorCreatePlaylistHeaderCursor3;
        } else {
            cursorCreatePlaylistHeaderCursor = null;
            cursor3 = null;
        }
        cursorCreatePlaylistHeaderCursor2 = null;
        return new SafeMergeCursor(new Cursor[]{cursor3, matrixCursorQuerySmartPlaylists, cursorCreatePlaylistHeaderCursor, cursor, cursorCreatePlaylistHeaderCursor2, cursor2});
    }

    private Cursor filter(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] strArr;
        HashMap<String, String> map;
        String str;
        List<String> pathSegments = uri.getPathSegments();
        String strReplace = (pathSegments == null || pathSegments.size() != 5) ? "" : pathSegments.get(4).replace('*', '%');
        String queryParameter = uri.getQueryParameter("includeTracks");
        boolean z = queryParameter != null && "1".equals(queryParameter);
        boolean zEquals = "1".equals(uri.getQueryParameter("restrictFields"));
        int iMatch = DefaultUriMatcher.match(uri);
        String str2 = "Track";
        String str3 = "album_id";
        if (iMatch != 1) {
            if (iMatch == 3) {
                map = ALBUMS_MAP;
                if (z) {
                    strArr = new String[]{"title", "album", "album_artist", "artist"};
                } else if (zEquals) {
                    strArr = new String[]{"album", "album_artist"};
                } else {
                    strArr = new String[]{"album", "album_artist", "artist"};
                }
            } else if (iMatch == 7) {
                map = ARTISTS_MAP;
                strArr = new String[]{"album_artist", "artist"};
                str3 = "album_artist_id";
            } else if (iMatch == 24) {
                map = GENRE_MAP;
                strArr = new String[]{ParserUtil.GENRE_SEGMENT_NAME};
                str3 = "genre_id";
            } else {
                throw new IllegalArgumentException("search uri not recognized");
            }
            str = str3;
        } else {
            if (zEquals) {
                strArr = new String[]{"title", "artist"};
            } else {
                strArr = new String[]{"title", "album", "album_artist", "artist"};
            }
            map = null;
            str = null;
            str2 = "Track LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid";
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str2);
        sQLiteQueryBuilder.setDistinct(true);
        if (map != null) {
            sQLiteQueryBuilder.setProjectionMap(map);
        }
        String[] strArrBuildLikeCriteriaAndSortOrderForFilter = DbUtil.buildLikeCriteriaAndSortOrderForFilter(strReplace, strArr);
        String str4 = sortOrder == null ? strArrBuildLikeCriteriaAndSortOrderForFilter[1] : sortOrder;
        Cursor cursorQuery = sQLiteQueryBuilder.query(getDb(), projection, DbUtil.applyBinaryOperator(strArrBuildLikeCriteriaAndSortOrderForFilter[0], "AND ", "(" + selection + ")"), selectionArgs, str, null, str4);
        if (cursorQuery == null) {
            return null;
        }
        return map == null ? cursorQuery : new ColumnMappedCursor(cursorQuery, map);
    }

    private String smartPlaylistLuid(String source, long playlistId) {
        return "smart-" + source + "-" + Long.toString(playlistId);
    }

    private int countSmartPlaylistTracks(long j, String str) {
        if (j == 3) {
            return countRecentlyPlayedTracks(str);
        }
        String nonDeluxeSelection = DeluxeContentUtil.getNonDeluxeSelection();
        String[] nonDeluxeSelectionArgs = DeluxeContentUtil.getNonDeluxeSelectionArgs();
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator(nonDeluxeSelection, "AND", DbUtil.applyBinaryOperator("source=" + (!"cirrus".equals(str) ? 1 : 0), " AND ", getSmartPlaylistWhereClause(j)));
        Cursor cursorQuery = getDb().query("Track", new String[]{"COUNT(_id)"}, strApplyBinaryOperator, nonDeluxeSelectionArgs, null, null, null);
        int i = 0;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    i = cursorQuery.getInt(0);
                }
            } catch (Throwable th) {
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        }
        DbUtil.closeCursor(cursorQuery);
        if (j == 1) {
            android.util.Log.d("CirrusMediaSource", "count=" + i);
            android.util.Log.d("CirrusMediaSource", strApplyBinaryOperator);
        }
        return i;
    }

    private synchronized int countRecentlyPlayedTracks(final String sourceId) {
        int i;
        String nonDeluxeSelection = DeluxeContentUtil.getNonDeluxeSelection();
        String[] nonDeluxeSelectionArgs = DeluxeContentUtil.getNonDeluxeSelectionArgs();
        Cursor cursorQuery = null;
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        i = 0;
        try {
            try {
                attachCirrusToRecentsDb(db);
                String strApplyBinaryOperator = DbUtil.applyBinaryOperator(nonDeluxeSelection, "AND", getSmartPlaylistWhereClause(3L));
                SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                sQLiteQueryBuilder.setTables("main.recently_played_tracks LEFT JOIN Track ON Track.asin=main.recently_played_tracks.recent_track_asin OR Track.luid=main.recently_played_tracks.recent_track_luid LEFT JOIN LocalTrackUri ON LocalTrackUri.track_asin=main.recently_played_tracks.recent_track_asin OR LocalTrackUri.track_luid=main.recently_played_tracks.recent_track_luid LEFT JOIN PrimePlaylistTracks ON PrimePlaylistTracks.asin=main.recently_played_tracks.recent_track_asin");
                cursorQuery = sQLiteQueryBuilder.query(db, new String[]{" COUNT( DISTINCT _uid)"}, strApplyBinaryOperator, nonDeluxeSelectionArgs, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    i = cursorQuery.getInt(0);
                }
                DbUtil.closeCursor(cursorQuery);
            } catch (SQLiteException e) {
                Log.error(TAG, "Failed to get count of recently played tracks. " + e);
                DbUtil.closeCursor(cursorQuery);
            }
            detachCirrusFromRecentsDb(db);
        } catch (Throwable th) {
            DbUtil.closeCursor(cursorQuery);
            detachCirrusFromRecentsDb(db);
            throw th;
        }
        return i;
    }

    private MatrixCursor querySmartPlaylists(Uri uri) throws Throwable {
        Throwable th;
        Exception exc;
        Set setEmptySet;
        MatrixCursor matrixCursor = null;
        try {
            try {
                String queryParameter = uri.getQueryParameter("smartPlaylistsToShow");
                if (queryParameter == null) {
                    setEmptySet = Collections.emptySet();
                } else {
                    HashSet hashSet = new HashSet();
                    for (String str : StringUtil.SPLIT_CSV.split(queryParameter)) {
                        try {
                            hashSet.add(Long.valueOf(MediaProvider.SmartPlaylists.queryValueToDatabaseValue(str)));
                        } catch (IllegalArgumentException e) {
                            Log.warning(TAG, "Invalid query param value", e);
                        }
                    }
                    setEmptySet = hashSet;
                }
                MatrixCursor matrixCursor2 = new MatrixCursor(PLAYLISTS_PROJECTION);
                try {
                    String source = MediaProvider.getSource(uri);
                    if ("cirrus".equals(source)) {
                        if ((setEmptySet.isEmpty() || setEmptySet.contains(0L)) && AmazonApplication.getCapabilities().isStoreSupported()) {
                            ContentOwnershipStatus contentOwnershipStatus = ContentOwnershipStatus.OWNED;
                            ContentCatalogStatus contentCatalogStatus = ContentCatalogStatus.NON_CATALOG;
                            matrixCursor2.addRow(new Object[]{0L, 0, this.mContext.getString(R.string.dmusic_library_smart_playlist_latest_purchases), "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist", 5, smartPlaylistLuid(source, 0L), "-1", Integer.valueOf(countSmartPlaylistTracks(0L, source)), -1, "0", "", "0", Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentOwnershipStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(contentCatalogStatus.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
                        }
                        if (setEmptySet.isEmpty() || setEmptySet.contains(1L)) {
                            ContentOwnershipStatus contentOwnershipStatus2 = ContentOwnershipStatus.OWNED;
                            ContentCatalogStatus contentCatalogStatus2 = ContentCatalogStatus.NON_CATALOG;
                            matrixCursor2.addRow(new Object[]{1L, 0, this.mContext.getString(R.string.dmusic_library_smart_playlist_latest_uploads), "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist", 5, smartPlaylistLuid(source, 1L), "-1", Integer.valueOf(countSmartPlaylistTracks(1L, source)), -1, "0", "", "0", Integer.valueOf(contentOwnershipStatus2.getValue()), Integer.valueOf(contentOwnershipStatus2.getValue()), Integer.valueOf(contentCatalogStatus2.getValue()), Integer.valueOf(contentCatalogStatus2.getValue()), Integer.valueOf(contentCatalogStatus2.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
                        }
                        if (setEmptySet.isEmpty() || setEmptySet.contains(3L)) {
                            ContentOwnershipStatus contentOwnershipStatus3 = ContentOwnershipStatus.ADDED;
                            ContentCatalogStatus contentCatalogStatus3 = ContentCatalogStatus.NON_CATALOG;
                            matrixCursor2.addRow(new Object[]{3L, 0, this.mContext.getString(R.string.dmusic_listening_history), "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist", 5, smartPlaylistLuid(source, 3L), "-1", Integer.valueOf(countSmartPlaylistTracks(3L, source)), -1, "0", "", "0", Integer.valueOf(contentOwnershipStatus3.getValue()), Integer.valueOf(contentOwnershipStatus3.getValue()), Integer.valueOf(contentCatalogStatus3.getValue()), Integer.valueOf(contentCatalogStatus3.getValue()), Integer.valueOf(contentCatalogStatus3.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
                        }
                    } else if ("cirrus-local".equals(source) && (setEmptySet.isEmpty() || setEmptySet.contains(2L))) {
                        ContentOwnershipStatus contentOwnershipStatus4 = ContentOwnershipStatus.ADDED;
                        ContentCatalogStatus contentCatalogStatus4 = ContentCatalogStatus.NON_CATALOG;
                        matrixCursor2.addRow(new Object[]{2L, 1, this.mContext.getString(R.string.dmusic_library_smart_playlist_recently_added), "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist", 5, smartPlaylistLuid(source, 2L), "-1", Integer.valueOf(countSmartPlaylistTracks(2L, source)), -1, "0", "", "0", Integer.valueOf(contentOwnershipStatus4.getValue()), Integer.valueOf(contentOwnershipStatus4.getValue()), Integer.valueOf(contentCatalogStatus4.getValue()), Integer.valueOf(contentCatalogStatus4.getValue()), Integer.valueOf(contentCatalogStatus4.getValue()), Integer.valueOf(MediaProvider.Playlists.VisibilityState.NO_STATE.getInt()), 0, 0, 0, "{\"isOnDemandPlayable\": false, \"isStationFromAnythingPlayable\": true, \"shuffleType\": \"MIXED\"}", 0, 0, 0, CirrusDatabase.Playlists.DEFAULT_SHARED_ID, 0});
                    }
                    DbUtil.closeCursor(matrixCursor2);
                    return matrixCursor2;
                } catch (Exception e2) {
                    exc = e2;
                    matrixCursor = matrixCursor2;
                    exc.printStackTrace();
                    DbUtil.closeCursor(matrixCursor);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    matrixCursor = matrixCursor2;
                    DbUtil.closeCursor(matrixCursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e3) {
            exc = e3;
        }
    }

    private void notifyChange() {
        this.mContext.getContentResolver().notifyChange(NOTIFICATION_URI, null);
        this.mContext.getContentResolver().notifyChange(NOTIFICATION_URI_LOCAL, null);
    }

    private void throwIfNotScratch(Uri uri) {
        if (!matchCloud(uri) || "true".equals(uri.getQueryParameter("scratch"))) {
            return;
        }
        throw new IllegalArgumentException("Only scratch editing is supported for the uri: " + uri);
    }

    private Cursor queryAlexaRecentlyPlayed(final String[] projection, final String selection, final String[] selectionArgs) {
        SQLiteDatabase db = RecentlyPlayedDatabase.getInstance(this.mContext).getDb();
        try {
            attachCirrusToRecentsDb(db);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("main.alexa_recently_played LEFT JOIN Track ON Track.asin=main.alexa_recently_played.alexa_asin OR Track.luid=main.alexa_recently_played.alexa_luid LEFT JOIN LocalTrackUri ON LocalTrackUri.track_asin=main.alexa_recently_played.alexa_asin OR LocalTrackUri.track_luid=main.alexa_recently_played.alexa_luid");
            Cursor cursorQuery = sQLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, "main.alexa_recently_played._id DESC");
            cursorQuery.moveToPosition(cursorQuery.getPosition());
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.error(TAG, "Querying alexa recents failed.", e);
            return null;
        } finally {
            detachCirrusFromRecentsDb(db);
        }
    }

    private String getSelectionForChildUser(String selection) {
        return DbUtil.applyBinaryOperator(selection, "AND", "Track.is_explicit<1 AND Track.is_exclusion_tagged <1");
    }

    private synchronized SQLiteDatabase getDb() {
        return CirrusDatabase.getReadOnlyDatabase(this.mContext);
    }

    private synchronized SQLiteDatabase getWriteableDb() {
        return CirrusDatabase.getWritableDatabase(this.mContext);
    }

    public static Uri getCollectionUri(Uri tracksUri) {
        if (tracksUri == null) {
            return null;
        }
        String source = MediaProvider.getSource(tracksUri);
        int iMatch = DefaultUriMatcher.match(tracksUri);
        if (iMatch == 1) {
            return MediaProvider.Tracks.getFilterContentUri(source, null);
        }
        if (iMatch == 9) {
            return MediaProvider.Artists.getContentUri(source, MediaProvider.Artists.getArtistId(tracksUri));
        }
        if (iMatch == 26) {
            return MediaProvider.Genres.getContentUri(source, MediaProvider.Genres.getGenreId(tracksUri));
        }
        if (iMatch == 28) {
            return MediaProvider.PrimePlaylists.getContentUri(source, MediaProvider.PrimePlaylists.getPlaylistAsin(tracksUri));
        }
        if (iMatch == 33) {
            return MediaProvider.SharedUserPlaylists.getContentUri(source, MediaProvider.SharedUserPlaylists.getPlaylistKey(tracksUri));
        }
        if (iMatch == 39) {
            return MediaProvider.Artists.getContentUri(source, MediaProvider.PrimeArtists.getArtistId(tracksUri));
        }
        if (iMatch == 4 || iMatch == 5) {
            return MediaProvider.Albums.getContentUri(source, MediaProvider.Albums.getAlbumId(tracksUri));
        }
        if (iMatch == 15) {
            return MediaProvider.SmartPlaylists.getContentUri(source, MediaProvider.SmartPlaylists.getPlaylistId(tracksUri));
        }
        if (iMatch != 16) {
            return null;
        }
        return MediaProvider.UdoPlaylists.getContentUri(source, MediaProvider.Playlists.getPlaylistId(tracksUri));
    }
}
