package com.amazon.mp3.library.provider.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.MediaStore;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.db.ColumnMappedCursor;
import com.amazon.mp3.library.db.ContentTypeAppendedCursor;
import com.amazon.mp3.library.db.SafeMergeCursor;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.ProviderSource;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MimeTypeUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class LocalMediaSource implements ProviderSource {
    private static final HashMap<String, String> ALBUM_COLUMN_MAP;
    private static final HashMap<String, String> ARTIST_COLUMN_MAP;
    private static final HashMap<String, String> GENRE_COLUMN_MAP;
    public static final Uri LOCAL_TRACKS_URI;
    public static final Uri LOCAL_URI;
    private static final HashMap<String, String> PLAYLIST_COLUMN_MAP;
    private static final HashMap<String, String> PLAYLIST_TRACK_COLUMN_MAP;
    public static final Long RECENTLY_ADDED_SMART_PLAYLIST_ID;
    private static final String TAG = "LocalMediaSource";
    private static final HashMap<String, String> TRACK_COLUMN_MAP;
    private static boolean sUpdatedTrackColumnMap;
    private Context mContext = null;
    public static final Uri BASE_TRACK_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final Uri BASE_ALBUM_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    public static final Uri BASE_ARTIST_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
    public static final Uri BASE_PLAYLIST_URI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
    public static final Uri BASE_GENRE_URI = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;

    static {
        Uri uri = Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + ImagesContract.LOCAL);
        LOCAL_URI = uri;
        LOCAL_TRACKS_URI = Uri.withAppendedPath(uri, "tracks");
        RECENTLY_ADDED_SMART_PLAYLIST_ID = 0L;
        HashMap<String, String> map = new HashMap<>();
        TRACK_COLUMN_MAP = map;
        map.put("_id", "_id");
        map.put("luid", "_id");
        map.put("title", "title");
        map.put("album", "album");
        map.put("album_id", "album_id");
        map.put("album_artist_id", "artist_id");
        map.put("artist", "artist");
        map.put("artist_id", "artist_id");
        map.put("album_id", "album_id");
        map.put("album_art_id", "album_key");
        map.put("track_num", "track");
        map.put("disc_num", "track");
        map.put("local_uri", "_data");
        map.put("date_created", "date_added");
        map.put("size", "_size");
        map.put("duration", "duration");
        map.put("album_artist", "artist");
        HashMap<String, String> map2 = new HashMap<>();
        ALBUM_COLUMN_MAP = map2;
        map2.put("_id", "_id");
        map2.put("title", "album");
        map2.put("track_count", "numsongs");
        map2.put("artist", "artist");
        map2.put("artist_id", "artist_id");
        map2.put("album_art_id", "album_key");
        map2.put("count", "count(*)");
        HashMap<String, String> map3 = new HashMap<>();
        ARTIST_COLUMN_MAP = map3;
        map3.put("_id", "_id");
        map3.put("name", "artist");
        map3.put("album_count", "number_of_albums");
        map3.put("track_count", "number_of_tracks");
        map3.put("count", "count(*)");
        HashMap<String, String> map4 = new HashMap<>();
        PLAYLIST_COLUMN_MAP = map4;
        map4.put("_id", "_id");
        map4.put("name", "name");
        map4.put("count", "count(*)");
        HashMap<String, String> map5 = new HashMap<>();
        PLAYLIST_TRACK_COLUMN_MAP = map5;
        map5.put("_id", "audio_id");
        map5.put("udo", "play_order");
        map5.put("track_luid", "audio_id");
        map5.put("udo_playlist_track_id", "_id");
        map5.put("luid", "audio_id");
        map5.put("title", "title");
        map5.put("album", "album");
        map5.put("album_id", "album_id");
        map5.put("album_artist", "artist");
        map5.put("album_artist_id", "artist_id");
        map5.put("album_art_id", "album_key");
        map5.put("artist", "artist");
        map5.put("artist_id", "artist_id");
        map5.put("album_id", "album_id");
        map5.put("track_num", "track");
        map5.put("local_uri", "_data");
        map5.put("date_created", "date_added");
        map5.put("size", "_size");
        map5.put("duration", "duration");
        HashMap<String, String> map6 = new HashMap<>();
        GENRE_COLUMN_MAP = map6;
        map6.put("_id", "genre_id");
        map6.put("name", ParserUtil.GENRE_SEGMENT_NAME);
        map6.put("track_count", "count(genre)");
        map6.put("album_count", "count(distinct album_id)");
        map6.put("count", "count(*)");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public void onRegistered(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int iMatch = DefaultUriMatcher.match(uri);
        switch (iMatch) {
            case 1:
            case 2:
            case 5:
            case 9:
            case 26:
                return queryTracks(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 3:
            case 4:
            case 10:
            case 27:
                return queryAlbums(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 6:
            case 12:
            case 18:
            case 19:
            case 20:
            case 22:
            case 23:
            default:
                throw new IllegalArgumentException("Invalid content URI for LocalMediaSource: " + uri.toString());
            case 7:
            case 8:
                return queryArtists(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 11:
            case 13:
            case 14:
                return queryPlaylists(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 15:
                return querySmartPlaylistTracks(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 16:
            case 17:
                return queryUdoPlaylistTracks(iMatch, uri, projection, selection, selectionArgs, sortOrder);
            case 21:
                return querySearch(uri);
            case 24:
            case 25:
                return queryGenres(iMatch, uri, projection, selection, selectionArgs, sortOrder);
        }
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DefaultUriMatcher.match(uri) == 14) {
            return updateUdoPlaylist(uri, values);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 14) {
            return deleteUdoPlaylist(uri);
        }
        if (iMatch == 17) {
            return deleteUdoPlaylistTrack(uri);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Uri insert(Uri uri, ContentValues values) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 11) {
            return createUdoPlaylist(uri, values);
        }
        if (iMatch == 14) {
            return addTrackToUdoPlaylist(uri, values);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DefaultUriMatcher.match(uri) == 14) {
            return addTracksToUdoPlaylist(uri, values);
        }
        throw new UnsupportedOperationException("Not implemented");
    }

    private int addTracksToUdoPlaylist(Uri uri, ContentValues[] values) {
        int i = 0;
        for (ContentValues contentValues : values) {
            values[i] = toMediaStoreUdoPlaylistTrackContentValues(contentValues);
            i++;
        }
        int iBulkInsert = this.mContext.getContentResolver().bulkInsert(toMediaStorePlaylistUri(uri), values);
        if (iBulkInsert > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return iBulkInsert;
    }

    private Uri addTrackToUdoPlaylist(Uri uri, ContentValues values) {
        ContentValues mediaStoreUdoPlaylistTrackContentValues = toMediaStoreUdoPlaylistTrackContentValues(values);
        Uri uriInsert = this.mContext.getContentResolver().insert(toMediaStorePlaylistUri(uri), mediaStoreUdoPlaylistTrackContentValues);
        mediaStoreUdoPlaylistTrackContentValues.clear();
        if (uriInsert == null) {
            return null;
        }
        Uri uriWithAppendedId = ContentUris.withAppendedId(uri, Long.parseLong(uriInsert.getLastPathSegment()));
        if (uriWithAppendedId != null) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return uriWithAppendedId;
    }

    private int updateUdoPlaylist(Uri uri, ContentValues values) {
        if (!uri.getPathSegments().get(3).equals("udo")) {
            throw new IllegalArgumentException("Only UDO playlists can be deleted");
        }
        String lastPathSegment = uri.getLastPathSegment();
        int iUpdate = this.mContext.getContentResolver().update(MediaStore.Audio.Playlists.getContentUri("external"), DbUtil.remapContentValues(PLAYLIST_COLUMN_MAP, values), "_id=?", new String[]{lastPathSegment});
        if (iUpdate > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return iUpdate;
    }

    private int deleteUdoPlaylist(Uri uri) {
        if (!uri.getPathSegments().get(3).equals("udo")) {
            throw new IllegalArgumentException("Only UDO playlists can be deleted");
        }
        int iDelete = this.mContext.getContentResolver().delete(MediaStore.Audio.Playlists.getContentUri("external"), "_id=?", new String[]{uri.getLastPathSegment()});
        if (iDelete > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return iDelete;
    }

    private int deleteUdoPlaylistTrack(Uri uri) {
        long j = Long.parseLong(uri.getPathSegments().get(4));
        long j2 = Long.parseLong(uri.getLastPathSegment());
        ContentResolver contentResolver = this.mContext.getContentResolver();
        MediaStore.Audio.Playlists.Members.moveItem(this.mContext.getContentResolver(), j, Integer.parseInt(uri.getQueryParameter("udo")), DbUtil.intFromCursor(contentResolver.query(MediaProvider.UdoPlaylistTracks.getContentUri(ImagesContract.LOCAL, j), new String[]{"udo"}, null, null, "play_order DESC")) + 1);
        int iDelete = this.mContext.getContentResolver().delete(ContentUris.withAppendedId(MediaStore.Audio.Playlists.Members.getContentUri("external", j), j2), null, null);
        if (iDelete > 0) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return iDelete;
    }

    private Uri createUdoPlaylist(Uri uri, ContentValues values) {
        if (!uri.getLastPathSegment().equals("udo")) {
            throw new IllegalArgumentException("Only UDO playlists can be created");
        }
        String asString = values.getAsString("name");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", asString);
        Uri uriInsert = this.mContext.getContentResolver().insert(MediaStore.Audio.Playlists.getContentUri("external"), contentValues);
        if (uriInsert != null) {
            this.mContext.getContentResolver().notifyChange(uriInsert, null);
            try {
                return MediaProvider.UdoPlaylists.getContentUri(ImagesContract.LOCAL, Long.parseLong(uriInsert.getLastPathSegment()));
            } catch (NumberFormatException unused) {
                Log.warning("PlaylistUtil", "invalid uri format detected");
            }
        }
        return null;
    }

    private Cursor queryTracks(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] strArrMergeColumnArrays;
        String strApplyBinaryOperator;
        String strApplyBinaryOperator2 = DbUtil.applyBinaryOperator("is_music!=0", "AND", selection);
        if (MediaProvider.isFilterable(uri)) {
            return filter(uri, projection, strApplyBinaryOperator2, selectionArgs, sortOrder);
        }
        String[] strArrTransformTrackProjection = transformTrackProjection(projection);
        Uri uriBuild = BASE_TRACK_URI;
        int i = 0;
        if (type == 2) {
            uriBuild = uriBuild.buildUpon().appendPath(uri.getPathSegments().get(3)).build();
        } else if (type == 5) {
            String[] strArr = {uri.getPathSegments().get(3)};
            strApplyBinaryOperator2 = DbUtil.applyBinaryOperator("album_id=?", "AND", strApplyBinaryOperator2);
            selectionArgs = DbUtil.mergeColumnArrays(strArr, selectionArgs);
            if (sortOrder == null) {
                sortOrder = "track asc";
            }
        } else if (type == 9) {
            String[] strArr2 = {uri.getPathSegments().get(3)};
            strApplyBinaryOperator2 = DbUtil.applyBinaryOperator("artist_id=?", "AND", strApplyBinaryOperator2);
            selectionArgs = DbUtil.mergeColumnArrays(strArr2, selectionArgs);
        }
        Set<String> unsupportedTypes = MimeTypeUtil.getUnsupportedTypes();
        if (unsupportedTypes.size() > 0) {
            String[] strArr3 = new String[unsupportedTypes.size()];
            StringBuilder sb = new StringBuilder("mime_type");
            sb.append(" NOT IN (");
            for (String str : unsupportedTypes) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append('?');
                strArr3[i] = str;
                i++;
            }
            sb.append(')');
            strApplyBinaryOperator = DbUtil.applyBinaryOperator(sb.toString(), "AND", strApplyBinaryOperator2);
            strArrMergeColumnArrays = DbUtil.mergeColumnArrays(strArr3, selectionArgs);
        } else {
            strArrMergeColumnArrays = selectionArgs;
            strApplyBinaryOperator = strApplyBinaryOperator2;
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(uriBuild, strArrTransformTrackProjection, strApplyBinaryOperator, strArrMergeColumnArrays, sortOrder == null ? "title_key asc" : sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        updateTrackColumnMapIfNecessary();
        return transformResultCursor(cursorQuery, TRACK_COLUMN_MAP);
    }

    private Cursor queryGenres(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, final String sortOrder) {
        Uri uriBuild;
        if (MediaProvider.isFilterable(uri)) {
            return filter(uri, projection, null, null, sortOrder);
        }
        HashMap<String, String> map = GENRE_COLUMN_MAP;
        String[] strArrRemapProjection = DbUtil.remapProjection(map, projection);
        Uri uri2 = BASE_GENRE_URI;
        if (type == 25) {
            uriBuild = uri2.buildUpon().appendPath(uri.getPathSegments().get(3)).build();
        } else {
            uriBuild = uri2;
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(uriBuild, strArrRemapProjection, selection, selectionArgs, sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        return transformResultCursor(cursorQuery, map);
    }

    private Cursor queryAlbums(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] strArrMergeColumnArrays;
        Uri contentUri;
        String strApplyBinaryOperator;
        if (MediaProvider.isFilterable(uri)) {
            return filter(uri, projection, null, null, sortOrder);
        }
        String[] strArrTransformAlbumProjection = transformAlbumProjection(projection);
        Uri uri2 = BASE_ALBUM_URI;
        if (type == 4) {
            contentUri = uri2.buildUpon().appendPath(uri.getPathSegments().get(3)).build();
            strApplyBinaryOperator = selection;
            strArrMergeColumnArrays = selectionArgs;
        } else if (type == 10) {
            String str = uri.getPathSegments().get(3);
            String[] strArr = {str};
            Uri contentUri2 = MediaProvider.Tracks.getContentUri(ImagesContract.LOCAL);
            Cursor cursorQueryTracks = queryTracks(DefaultUriMatcher.match(contentUri2), contentUri2, new String[]{"artist_id"}, "artist=?", new String[]{str}, null);
            if (cursorQueryTracks == null) {
                return null;
            }
            long jLongFromCursor = DbUtil.longFromCursor(cursorQueryTracks);
            if (jLongFromCursor == -1) {
                return null;
            }
            contentUri = MediaStore.Audio.Artists.Albums.getContentUri("external", jLongFromCursor);
            strApplyBinaryOperator = DbUtil.applyBinaryOperator("artist=?", "AND", selection);
            strArrMergeColumnArrays = DbUtil.mergeColumnArrays(strArr, selectionArgs);
        } else {
            strArrMergeColumnArrays = selectionArgs;
            contentUri = uri2;
            strApplyBinaryOperator = selection;
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(contentUri, strArrTransformAlbumProjection, strApplyBinaryOperator, strArrMergeColumnArrays, sortOrder == null ? "album_key asc" : sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        return transformResultCursor(cursorQuery, ALBUM_COLUMN_MAP);
    }

    private Cursor queryArtists(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, final String sortOrder) {
        Uri uriBuild;
        if (MediaProvider.isFilterable(uri)) {
            return filter(uri, projection, null, null, sortOrder);
        }
        HashMap<String, String> map = ARTIST_COLUMN_MAP;
        String[] strArrRemapProjection = DbUtil.remapProjection(map, projection);
        Uri uri2 = BASE_ARTIST_URI;
        if (type == 8) {
            uriBuild = uri2.buildUpon().appendPath(uri.getPathSegments().get(3)).build();
        } else {
            uriBuild = uri2;
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(uriBuild, strArrRemapProjection, selection, selectionArgs, sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        return transformResultCursor(cursorQuery, map);
    }

    private String getSegment(Uri uri, int nSegment, String defaultValue) {
        return uri.getPathSegments().size() > nSegment ? uri.getPathSegments().get(nSegment) : defaultValue;
    }

    private Cursor queryPlaylists(final int type, final Uri uri, String[] unused, String selection, String[] selectionArgs, String sortOrder) {
        boolean z;
        String queryParameter;
        String segment;
        long j;
        String segment2;
        String strApplyBinaryOperator;
        String[] strArrMergeColumnArrays;
        String strApplyBinaryOperator2;
        String[] strArrMergeColumnArrays2;
        Cursor cursorQuery;
        String str;
        if ("udo_only_true".equals(uri.getQueryParameter("udo_only"))) {
            queryParameter = uri.getQueryParameter("udo_playlist_exclude_id");
            z = true;
        } else {
            z = false;
            queryParameter = null;
        }
        String[] strArr = {"_id", "name"};
        if (MediaProvider.isFilterable(uri)) {
            if (uri.getPathSegments().size() > 5) {
                segment = getSegment(uri, 4, null);
                segment2 = getSegment(uri, 5, null);
            } else {
                segment2 = getSegment(uri, 4, null);
                segment = null;
            }
            j = -1;
        } else {
            segment = getSegment(uri, 3, null);
            j = Long.parseLong(getSegment(uri, 4, "-1"));
            segment2 = null;
        }
        ContentTypeAppendedCursor contentTypeAppendedCursor = (segment == null || "smart".equals(segment)) ? new ContentTypeAppendedCursor(buildSmartPlaylistsCursor(strArr, segment2), AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist") : null;
        if (segment == null || "udo".equals(segment)) {
            HashMap<String, String> map = PLAYLIST_COLUMN_MAP;
            String[] strArrRemapProjection = DbUtil.remapProjection(map, strArr);
            if (j != -1) {
                String strApplyBinaryOperator3 = DbUtil.applyBinaryOperator("_id=?", "AND", selection);
                strArrMergeColumnArrays = DbUtil.mergeColumnArrays(new String[]{String.valueOf(j)}, selectionArgs);
                strApplyBinaryOperator = strApplyBinaryOperator3;
            } else {
                strApplyBinaryOperator = selection;
                strArrMergeColumnArrays = selectionArgs;
            }
            if (segment2 != null) {
                if (segment2.length() == 0) {
                    str = null;
                } else {
                    str = "name LIKE '%" + segment2.replace("'", "''") + "%'";
                }
                if (str != null) {
                    strApplyBinaryOperator = DbUtil.applyBinaryOperator(str, "AND", strApplyBinaryOperator);
                }
            }
            if (!z || queryParameter == null || queryParameter.length() <= 0) {
                strApplyBinaryOperator2 = strApplyBinaryOperator;
                strArrMergeColumnArrays2 = strArrMergeColumnArrays;
            } else {
                strApplyBinaryOperator2 = DbUtil.applyBinaryOperator("_id<>?", "AND", strApplyBinaryOperator);
                strArrMergeColumnArrays2 = DbUtil.mergeColumnArrays(new String[]{queryParameter}, strArrMergeColumnArrays);
            }
            cursorQuery = this.mContext.getContentResolver().query(BASE_PLAYLIST_URI, strArrRemapProjection, strApplyBinaryOperator2, strArrMergeColumnArrays2, sortOrder == null ? "LOWER(name) COLLATE LOCALIZED ASC" : sortOrder);
            if (cursorQuery != null) {
                cursorQuery = new ContentTypeAppendedCursor(transformResultCursor(cursorQuery, map), AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
            }
            if (z) {
                return cursorQuery;
            }
        } else {
            cursorQuery = null;
        }
        if (contentTypeAppendedCursor == null && cursorQuery == null) {
            return null;
        }
        return new SafeMergeCursor(new Cursor[]{contentTypeAppendedCursor, cursorQuery});
    }

    private Cursor queryUdoPlaylistTracks(final int type, final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] strArrTransformPlaylistTrackProjection = transformPlaylistTrackProjection(projection);
        List<String> pathSegments = uri.getPathSegments();
        Uri contentUri = MediaStore.Audio.Playlists.Members.getContentUri("external", Long.parseLong(pathSegments.get(4)));
        if (pathSegments.size() == 7) {
            String lastPathSegment = uri.getLastPathSegment();
            selection = DbUtil.applyBinaryOperator(selection, "AND", "_id=?");
            selectionArgs = DbUtil.mergeColumnArrays(selectionArgs, new String[]{lastPathSegment});
        }
        if (sortOrder == null) {
            sortOrder = "play_order ASC";
        }
        if (selection != null) {
            selection = (" " + selection).replace(" udo ", "play_order");
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(contentUri, strArrTransformPlaylistTrackProjection, selection, selectionArgs, sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        return transformResultCursor(cursorQuery, PLAYLIST_TRACK_COLUMN_MAP);
    }

    private Cursor querySmartPlaylistTracks(final int type, final Uri uri, String[] projection, String unused, final String[] selectionArgs, String sortOrder) {
        long j = Long.parseLong(uri.getPathSegments().get(4));
        if (j != RECENTLY_ADDED_SMART_PLAYLIST_ID.longValue()) {
            throw new IllegalArgumentException("Unknown smart playlist: " + j);
        }
        if (sortOrder == null) {
            sortOrder = "date_modified DESC";
        }
        return queryTracks(1, MediaProvider.Tracks.getContentUri(MediaProvider.getSource(uri)), projection, "date_modified > strftime('%s', 'now', '-90 days')", selectionArgs, sortOrder);
    }

    private boolean addQueryRow(MatrixCursor results, Cursor source, int[] indexes, String kind) {
        MatrixCursor.RowBuilder rowBuilderNewRow = results.newRow();
        rowBuilderNewRow.add(kind);
        for (int i : indexes) {
            rowBuilderNewRow.add(source.getString(i));
        }
        return source.moveToNext();
    }

    private Cursor querySearch(Uri uri) {
        String strReplace = uri.getLastPathSegment().toLowerCase(Locale.ROOT).replace("'", "''");
        String queryParameter = uri.getQueryParameter("limit");
        int i = 30;
        if (queryParameter != null) {
            try {
                i = Integer.parseInt(queryParameter);
            } catch (NumberFormatException unused) {
            }
        }
        int i2 = i;
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        String str = MediaProvider.AUTHORITY;
        sb.append(str);
        sb.append("/");
        sb.append("library");
        sb.append("/");
        sb.append(ImagesContract.LOCAL);
        sb.append("/filter/tracks/");
        sb.append(strReplace);
        sb.append("?restrict=1");
        Uri uri2 = Uri.parse(sb.toString());
        Uri uri3 = Uri.parse("content://" + str + "/library/" + ImagesContract.LOCAL + "/filter/artists/" + strReplace + "?restrict=1");
        Uri uri4 = Uri.parse("content://" + str + "/library/" + ImagesContract.LOCAL + "/filter/albums/" + strReplace + "?restrict=1");
        String[] strArr = {"title", "artist", "album_id", "album_art_id"};
        String[] strArr2 = {"name", "name", "_id", "_id"};
        String[] strArr3 = {"title", "artist", "_id", "album_art_id"};
        Cursor cursorQuery = query(uri2, strArr, null, null, null);
        Cursor cursorQuery2 = query(uri3, strArr2, null, null, null);
        Cursor cursorQuery3 = query(uri4, strArr3, null, null, null);
        int[] indexesFromProjection = DbUtil.getIndexesFromProjection(cursorQuery, strArr);
        int[] indexesFromProjection2 = DbUtil.getIndexesFromProjection(cursorQuery2, strArr2);
        int[] indexesFromProjection3 = DbUtil.getIndexesFromProjection(cursorQuery3, strArr3);
        boolean zAddQueryRow = cursorQuery != null && cursorQuery.moveToFirst();
        boolean zAddQueryRow2 = cursorQuery2 != null && cursorQuery2.moveToFirst();
        boolean z = cursorQuery3 != null && cursorQuery3.moveToFirst();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "name", "artist", "id", "artid"});
        boolean zAddQueryRow3 = z;
        int i3 = 0;
        while (i3 < i2 && (zAddQueryRow || zAddQueryRow2 || zAddQueryRow3)) {
            if (zAddQueryRow) {
                zAddQueryRow = addQueryRow(matrixCursor, cursorQuery, indexesFromProjection, "track");
                i3++;
            }
            if (zAddQueryRow2) {
                zAddQueryRow2 = addQueryRow(matrixCursor, cursorQuery2, indexesFromProjection2, "artist");
                i3++;
            }
            if (zAddQueryRow3) {
                zAddQueryRow3 = addQueryRow(matrixCursor, cursorQuery3, indexesFromProjection3, "album");
                i3++;
            }
        }
        DbUtil.closeCursor(cursorQuery);
        DbUtil.closeCursor(cursorQuery2);
        DbUtil.closeCursor(cursorQuery3);
        return matrixCursor;
    }

    private Cursor buildSmartPlaylistsCursor(String[] projection, String filter) {
        Object[] objArr = new Object[projection.length];
        MatrixCursor matrixCursor = new MatrixCursor(projection);
        String string = this.mContext.getString(R.string.dmusic_library_smart_playlist_recently_added);
        if (filter != null && filter.length() > 0) {
            Locale locale = Locale.US;
            if (!string.toLowerCase(locale).contains(filter.toLowerCase(locale))) {
                return matrixCursor;
            }
        }
        int i = 0;
        for (String str : projection) {
            if (str.equals("name")) {
                objArr[i] = string;
            } else if (str.equals("_id")) {
                objArr[i] = RECENTLY_ADDED_SMART_PLAYLIST_ID;
            } else {
                objArr[i] = null;
            }
            i++;
        }
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor filter(android.net.Uri r17, java.lang.String[] r18, java.lang.String r19, java.lang.String[] r20, java.lang.String r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            java.lang.String r2 = "restrictFields"
            r3 = r17
            java.lang.String r2 = r3.getQueryParameter(r2)
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L1b
            java.lang.String r6 = "1"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L1b
            r2 = r5
            goto L1c
        L1b:
            r2 = r4
        L1c:
            int r6 = com.amazon.mp3.library.provider.DefaultUriMatcher.match(r17)
            java.lang.String r7 = "album"
            java.lang.String r8 = "artist"
            if (r6 == r5) goto L58
            r9 = 3
            if (r6 == r9) goto L44
            r2 = 7
            if (r6 != r2) goto L3b
            java.util.HashMap<java.lang.String, java.lang.String> r2 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.ARTIST_COLUMN_MAP
            android.net.Uri r6 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.BASE_ARTIST_URI
            java.lang.String[] r1 = com.amazon.mp3.util.DbUtil.remapProjection(r2, r1)
            java.lang.String[] r7 = new java.lang.String[]{r8}
            r12 = r1
            r11 = r6
            goto L74
        L3b:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "search uri not recognized"
            r1.<init>(r2)
            throw r1
        L44:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.ALBUM_COLUMN_MAP
            android.net.Uri r9 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.BASE_ALBUM_URI
            java.lang.String[] r1 = r0.transformAlbumProjection(r1)
            if (r2 == 0) goto L53
            java.lang.String[] r7 = new java.lang.String[]{r7}
            goto L71
        L53:
            java.lang.String[] r7 = new java.lang.String[]{r7, r8}
            goto L71
        L58:
            updateTrackColumnMapIfNecessary()
            java.util.HashMap<java.lang.String, java.lang.String> r6 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.TRACK_COLUMN_MAP
            android.net.Uri r9 = com.amazon.mp3.library.provider.source.local.LocalMediaSource.BASE_TRACK_URI
            java.lang.String[] r1 = r0.transformTrackProjection(r1)
            java.lang.String r10 = "title"
            if (r2 == 0) goto L6d
            java.lang.String[] r7 = new java.lang.String[]{r10, r8}
            goto L71
        L6d:
            java.lang.String[] r7 = new java.lang.String[]{r10, r8, r7}
        L71:
            r12 = r1
            r2 = r6
            r11 = r9
        L74:
            java.util.List r1 = r17.getPathSegments()
            if (r1 == 0) goto L91
            int r3 = r1.size()
            r6 = 5
            if (r3 != r6) goto L91
            r3 = 4
            java.lang.Object r1 = r1.get(r3)
            java.lang.String r1 = (java.lang.String) r1
            r3 = 42
            r6 = 37
            java.lang.String r1 = r1.replace(r3, r6)
            goto L93
        L91:
            java.lang.String r1 = ""
        L93:
            java.lang.String[] r1 = com.amazon.mp3.util.DbUtil.buildLikeCriteriaAndSortOrderForFilter(r1, r7)
            if (r21 != 0) goto L9d
            r3 = r1[r5]
            r15 = r3
            goto L9f
        L9d:
            r15 = r21
        L9f:
            r1 = r1[r4]
            java.lang.String r3 = "AND"
            r4 = r19
            java.lang.String r13 = com.amazon.mp3.util.DbUtil.applyBinaryOperator(r4, r3, r1)
            android.content.Context r1 = r0.mContext
            android.content.ContentResolver r10 = r1.getContentResolver()
            r14 = 0
            android.database.Cursor r1 = r10.query(r11, r12, r13, r14, r15)
            android.database.Cursor r1 = r0.transformResultCursor(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.source.local.LocalMediaSource.filter(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private String[] transformTrackProjection(String[] input) {
        HashSet hashSet = new HashSet(Arrays.asList(input));
        if (hashSet.contains("album_art_id")) {
            hashSet.add("album");
            hashSet.add("album_artist");
            input = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }
        updateTrackColumnMapIfNecessary();
        return DbUtil.remapProjection(TRACK_COLUMN_MAP, input);
    }

    private String[] transformPlaylistTrackProjection(String[] input) {
        HashSet hashSet = new HashSet(Arrays.asList(input));
        if (hashSet.contains("album_art_id")) {
            hashSet.add("album");
            hashSet.add("album_artist");
            input = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }
        return DbUtil.remapProjection(PLAYLIST_TRACK_COLUMN_MAP, input);
    }

    private String[] transformAlbumProjection(String[] input) {
        if (input == null) {
            return input;
        }
        HashSet hashSet = new HashSet(Arrays.asList(input));
        if (hashSet.contains("album_art_id")) {
            hashSet.add("title");
            hashSet.add("artist");
            input = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }
        return DbUtil.remapProjection(ALBUM_COLUMN_MAP, input);
    }

    private Cursor transformResultCursor(Cursor c, HashMap<String, String> columnMap) {
        AlbumArtIdCursor albumArtIdCursor;
        if (c == null) {
            return null;
        }
        ColumnMappedCursor columnMappedCursor = new ColumnMappedCursor(c, columnMap);
        if ((columnMap == TRACK_COLUMN_MAP || columnMap == PLAYLIST_TRACK_COLUMN_MAP) && columnMappedCursor.getColumnIndex("album_art_id") != -1) {
            albumArtIdCursor = new AlbumArtIdCursor(columnMappedCursor, "album", "album_artist");
        } else {
            if (columnMap != ALBUM_COLUMN_MAP || columnMappedCursor.getColumnIndex("album_art_id") == -1) {
                return columnMappedCursor;
            }
            albumArtIdCursor = new AlbumArtIdCursor(columnMappedCursor, "title", "artist");
        }
        return albumArtIdCursor;
    }

    private static Uri toMediaStorePlaylistUri(Uri mediaStoreUri) {
        return ContentUris.withAppendedId(MediaStore.Audio.Playlists.getContentUri("external"), Long.parseLong(mediaStoreUri.getLastPathSegment()));
    }

    private static ContentValues toMediaStoreUdoPlaylistTrackContentValues(ContentValues values) {
        ContentValues contentValues = new ContentValues();
        long jLongValue = values.getAsLong("track_luid").longValue();
        int iIntValue = values.getAsInteger("udo").intValue();
        contentValues.put("audio_id", Long.valueOf(jLongValue));
        contentValues.put("play_order", Integer.valueOf(iIntValue));
        return contentValues;
    }

    private static class AlbumArtIdCursor extends CursorWrapper {
        private final int mAlbumArtistIndex;
        private final int mAlbumIndex;

        public AlbumArtIdCursor(Cursor cursor, String albumNameColumn, String albumArtistColumn) {
            super(cursor);
            this.mAlbumIndex = super.getColumnIndexOrThrow(albumNameColumn);
            this.mAlbumArtistIndex = super.getColumnIndexOrThrow(albumArtistColumn);
        }

        @Override // android.database.CursorWrapper, android.database.Cursor
        public int getColumnIndex(String columnName) {
            if ("album_art_id".equals(columnName)) {
                return Integer.MAX_VALUE;
            }
            return super.getColumnIndex(columnName);
        }

        @Override // android.database.CursorWrapper, android.database.Cursor
        public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
            int columnIndex = getColumnIndex(columnName);
            if (columnIndex != -1) {
                return columnIndex;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.database.CursorWrapper, android.database.Cursor
        public long getLong(int columnIndex) {
            if (columnIndex == Integer.MAX_VALUE) {
                String string = getString(this.mAlbumIndex);
                String string2 = getString(this.mAlbumArtistIndex);
                if (string == null) {
                    string = "";
                }
                if (string2 == null) {
                    string2 = "";
                }
                return ((long) ((string.hashCode() * 31) + string2.hashCode())) + 2147483647L;
            }
            return super.getLong(columnIndex);
        }

        @Override // android.database.CursorWrapper, android.database.Cursor
        public String getString(int columnIndex) {
            if (columnIndex == Integer.MAX_VALUE) {
                return Long.toString(getLong(columnIndex));
            }
            return super.getString(columnIndex);
        }
    }

    private static void updateTrackColumnMapIfNecessary() {
        if (sUpdatedTrackColumnMap) {
            return;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = AmazonApplication.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"album_artist"}, null, null, null);
                if (cursorQuery != null) {
                    sUpdatedTrackColumnMap = true;
                    if (cursorQuery.getColumnIndex("album_artist") != -1) {
                        Log.debug(TAG, "album_artist column exists");
                        TRACK_COLUMN_MAP.put("album_artist", "album_artist");
                    }
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (SQLiteException unused) {
                Log.debug(TAG, "album_artist column doesn't exist");
                sUpdatedTrackColumnMap = true;
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}
