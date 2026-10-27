package com.amazon.music.find.data.database;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b<\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>¨\u0006?"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusTracksColumns;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ASIN", "ALBUM_ASIN", "ARTIST_ASIN", "DATE_UPDATED", "EXTENSION", "ORDER_ID", "VERSION", "EXISTS_FLAG", "LUID_HASH", "DATE_PURCHASED", "PURCHASED", "UPLOADED", "MARKETPLACE", "LYRICS_STATE", "STATUS", "CONTENT_OWNERSHIP_STATUS", "CONTENT_CATALOG_STATUS", "TRACK_TYPE", "POPULARITY", "PLAYLIST_TRACK_STATUS", "ASSET_QUALITIES", "ASSET_TYPE", "LUID", "TITLE", "SORT_TITLE", "ALBUM_ART_ID", "ALBUM", "SORT_ALBUM", "ALBUM_ID", "ALBUM_ARTIST", "SORT_ALBUM_ARTIST", "ALBUM_ARTIST_ID", "ARTIST", "SORT_ARTIST", Contexts.LibraryArtistDetail.artistId, "TRACK_NUM", "DISC_NUM", "LOCAL_URI", "REMOTE_URI", "LAST_URI_RESOLVE_TIME", "LAST_PLAYED", "DATE_CREATED", "SIZE", "BOOKMARK", "IS_PODCAST", "MATCH_HASH", "DURATION", "GENRE", "GENRE_ID", "IS_EXPLICIT", "CONTENT_ENCODING", "SOURCE", "DOWNLOAD_STATE", "DOWNLOAD_STATUS", "_ID", "_COUNT", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CirrusTracksColumns {
    ASIN("asin"),
    ALBUM_ASIN("album_asin"),
    ARTIST_ASIN("artist_asin"),
    DATE_UPDATED("date_updated"),
    EXTENSION("extension"),
    ORDER_ID("order_id"),
    VERSION("version"),
    EXISTS_FLAG("exists_flag"),
    LUID_HASH("luid_hash"),
    DATE_PURCHASED("date_purchased"),
    PURCHASED("purchased"),
    UPLOADED("uploaded"),
    MARKETPLACE(IntlConfiguration.MARKETPLACE),
    LYRICS_STATE("lyrics_state"),
    STATUS(NotificationCompat.CATEGORY_STATUS),
    CONTENT_OWNERSHIP_STATUS("ownership_status"),
    CONTENT_CATALOG_STATUS("prime_status"),
    TRACK_TYPE("track_type"),
    POPULARITY("popularity"),
    PLAYLIST_TRACK_STATUS("playlist_track_status"),
    ASSET_QUALITIES("asset_qualities"),
    ASSET_TYPE("asset_type"),
    LUID("luid"),
    TITLE("title"),
    SORT_TITLE("sort_title"),
    ALBUM_ART_ID("album_art_id"),
    ALBUM("album"),
    SORT_ALBUM("sort_album"),
    ALBUM_ID("album_id"),
    ALBUM_ARTIST("album_artist"),
    SORT_ALBUM_ARTIST("sort_album_artist"),
    ALBUM_ARTIST_ID("album_artist_id"),
    ARTIST("artist"),
    SORT_ARTIST("sort_artist"),
    ARTIST_ID("artist_id"),
    TRACK_NUM("track_num"),
    DISC_NUM("disc_num"),
    LOCAL_URI("local_uri"),
    REMOTE_URI("remote_uri"),
    LAST_URI_RESOLVE_TIME("last_uri_resolve_time"),
    LAST_PLAYED("last_played"),
    DATE_CREATED("date_created"),
    SIZE("size"),
    BOOKMARK("bookmark"),
    IS_PODCAST("is_podcast"),
    MATCH_HASH("match_hash"),
    DURATION("duration"),
    GENRE(ParserUtil.GENRE_SEGMENT_NAME),
    GENRE_ID("genre_id"),
    IS_EXPLICIT("is_explicit"),
    CONTENT_ENCODING("content_encoding"),
    SOURCE(PageUriUtils.SOURCE_QUERY_PARAM),
    DOWNLOAD_STATE("download_state"),
    DOWNLOAD_STATUS("download_status"),
    _ID("_id"),
    _COUNT("_count");

    private final String value;

    CirrusTracksColumns(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
