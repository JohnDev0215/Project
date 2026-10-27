package com.amazon.mp3.library.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.db.ColumnMappedCursor;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MergedProviderSource implements ProviderSource {
    Context mContext;

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public synchronized void onRegistered(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int delete(Uri uri, String selection, String[] selectionArgs) throws SQLException {
        throw new UnsupportedOperationException("Delete not available for merged URI");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Insert not available for merged URI");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Update not available for merged URI");
    }

    private Cursor queryAlbum(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int[] orderedSources = getOrderedSources(uri);
        String[] strArrMergeColumnArrays = DbUtil.mergeColumnArrays(projection, new String[]{"track_count"});
        for (int i : orderedSources) {
            Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Albums.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), Long.parseLong(uri.getLastPathSegment())), strArrMergeColumnArrays, selection, selectionArgs, sortOrder);
            if (cursorQuery != null && cursorQuery.getCount() == 1 && cursorQuery.moveToFirst() && cursorQuery.getLong(cursorQuery.getColumnIndex("track_count")) > 0) {
                cursorQuery.move(-1);
                return cursorQuery;
            }
            DbUtil.closeCursor(cursorQuery);
        }
        return null;
    }

    private Cursor queryAlbums(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        HashMap<String, String> map = CirrusMediaSource.ALBUMS_MAP;
        sQLiteQueryBuilder.setProjectionMap(map);
        sQLiteQueryBuilder.setTables("Track");
        List<String> pathSegments = uri.getPathSegments();
        String strReplace = (pathSegments == null || pathSegments.size() != 5) ? "" : pathSegments.get(4).replace('*', '%');
        String[] strArr = (String[]) Arrays.copyOf(projection, projection.length + 1);
        strArr[projection.length] = "min(source) as source";
        if ("1".equals(uri.getQueryParameter("restrictFields"))) {
            String[] strArrBuildLikeCriteriaAndSortOrderForFilter = DbUtil.buildLikeCriteriaAndSortOrderForFilter(strReplace, new String[]{"album", "album_artist"});
            if (sortOrder == null) {
                sortOrder = "LOWER(sort_album)";
            }
            if (selection == null) {
                selection = strArrBuildLikeCriteriaAndSortOrderForFilter[0];
            } else {
                selection = DbUtil.applyBinaryOperator(strArrBuildLikeCriteriaAndSortOrderForFilter[0], "AND ", "(" + selection + ")");
            }
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(getDb(), strArr, selection, selectionArgs, "album_id", null, sortOrder);
        if (cursorQuery == null) {
            return null;
        }
        return new ColumnMappedCursor(cursorQuery, map);
    }

    private Cursor queryArtist(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        for (int i : getOrderedSources(uri)) {
            Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Artists.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), Long.parseLong(uri.getLastPathSegment())), projection, selection, selectionArgs, sortOrder);
            if (cursorQuery != null && cursorQuery.getCount() == 1) {
                return cursorQuery;
            }
            DbUtil.closeCursor(cursorQuery);
        }
        return null;
    }

    private Cursor queryAlbumArtists(final Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return queryArtistsBase(uri, selection, selectionArgs, constructAlbumArtistQueryString(uri, selection));
    }

    private Cursor queryArtistsBase(final Uri uri, String selection, String[] selectionArgs, String innerQuery) {
        String str = AmazonApplication.getCapabilities().supportsLocalizedCollation() ? " COLLATE LOCALIZED ASC" : " ASC";
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        HashMap<String, String> map = CirrusMediaSource.ARTISTS_MAP;
        sb.append(map.get("_id"));
        sb.append(", ");
        sb.append(map.get("name"));
        sb.append(", ");
        sb.append(map.get("sort_name"));
        sb.append(", count(distinct ");
        sb.append("_id");
        sb.append("), count(distinct ");
        sb.append("album_id");
        sb.append("), max(");
        sb.append("download_state");
        sb.append("), ");
        sb.append(IntlConfiguration.MARKETPLACE);
        sb.append(", min(");
        sb.append(PageUriUtils.SOURCE_QUERY_PARAM);
        sb.append(") as ");
        sb.append(PageUriUtils.SOURCE_QUERY_PARAM);
        sb.append(" from ( ");
        sb.append(innerQuery);
        sb.append(") group by ");
        sb.append(map.get("_id"));
        sb.append(" order by ");
        sb.append(map.get("sort_name"));
        sb.append(str);
        sb.append(", ");
        sb.append(map.get("name"));
        sb.append(str);
        Cursor cursorRawQuery = CirrusDatabase.getWritableDatabase(this.mContext).rawQuery(sb.toString(), selectionArgs);
        if (cursorRawQuery == null) {
            return null;
        }
        return new ColumnMappedCursor(cursorRawQuery, map);
    }

    private String constructAlbumArtistQueryString(Uri uri, String commonSelection) {
        StringBuilder sb = new StringBuilder();
        sb.append("album_artist_id as ");
        HashMap<String, String> map = CirrusMediaSource.ARTISTS_MAP;
        sb.append(map.get("_id"));
        return constructArtistQueryString(uri, commonSelection, new ArrayList(Arrays.asList(sb.toString(), "album_artist as " + map.get("name"), "sort_album_artist as " + map.get("sort_name"))), "album_artist_id", "album_artist");
    }

    private String constructArtistQueryString(Uri uri, String commonSelection, List<String> projection, String idColumnName, String columnName) {
        List<String> pathSegments;
        ArrayList arrayList = new ArrayList(Arrays.asList(PageUriUtils.SOURCE_QUERY_PARAM, "download_state", "_id", "album_id", IntlConfiguration.MARKETPLACE));
        arrayList.addAll(projection);
        String strReplace = (MediaProvider.isFilterable(uri) && (pathSegments = uri.getPathSegments()) != null && pathSegments.size() == 5) ? pathSegments.get(4).replace('*', '%') : "";
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("".equals(strReplace) ? "" : DbUtil.applyBinaryOperator("", " AND ", DbUtil.buildLikeCriteriaAndSortOrderForFilter(strReplace, new String[]{columnName})[0]), " AND ", commonSelection);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track");
        return sQLiteQueryBuilder.buildQuery((String[]) arrayList.toArray(new String[arrayList.size()]), strApplyBinaryOperator, null, null, null, null, null);
    }

    private Cursor queryTrack(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track LEFT OUTER JOIN LocalTrackUri ON Track.luid=LocalTrackUri.track_luid");
        String str = uri.getPathSegments().get(3);
        String strApplyBinaryOperator = DbUtil.applyBinaryOperator("source=?", "AND", DbUtil.applyBinaryOperator("match_hash=?", "AND", selection));
        String str2 = sortOrder == null ? "track_num ASC" : sortOrder;
        int[] orderedSources = getOrderedSources(uri);
        char c = 0;
        int i = 0;
        while (i < orderedSources.length) {
            String[] strArr = new String[2];
            strArr[c] = Integer.toString(orderedSources[i]);
            strArr[1] = str;
            Cursor cursorQuery = sQLiteQueryBuilder.query(getDb(), projection, strApplyBinaryOperator, DbUtil.mergeColumnArrays(strArr, selectionArgs), null, null, str2, "1");
            if (cursorQuery != null && cursorQuery.getCount() == 1) {
                return cursorQuery;
            }
            DbUtil.closeCursor(cursorQuery);
            i++;
            c = 0;
        }
        return null;
    }

    private Cursor queryTracks(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track");
        String[] strArr = (String[]) Arrays.copyOf(projection, projection.length + 1);
        strArr[projection.length] = "MIN(source) as source";
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() > 4) {
            String str = pathSegments.get(4);
            selection = DbUtil.applyBinaryOperator(DbUtil.applyBinaryOperator("artist LIKE '%" + str + "%'", "OR", "album LIKE '%" + str + "%'"), "OR", "title LIKE '%" + str + "%'");
        }
        return sQLiteQueryBuilder.query(CirrusDatabase.getReadOnlyDatabase(this.mContext), strArr, selection, selectionArgs, "match_hash", null, sortOrder, null);
    }

    private Cursor queryGenre(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        for (int i : getOrderedSources(uri)) {
            Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Genres.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), Long.parseLong(uri.getLastPathSegment())), projection, selection, selectionArgs, sortOrder);
            if (cursorQuery != null && cursorQuery.getCount() == 1) {
                return cursorQuery;
            }
            DbUtil.closeCursor(cursorQuery);
        }
        return null;
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 1) {
            return queryTracks(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch == 2) {
            return queryTrack(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch == 3) {
            return queryAlbums(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch == 4) {
            return queryAlbum(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch == 7) {
            return queryAlbumArtists(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch == 8) {
            return queryArtist(uri, projection, selection, selectionArgs, sortOrder);
        }
        if (iMatch != 25) {
            return null;
        }
        return queryGenre(uri, projection, selection, selectionArgs, sortOrder);
    }

    private int[] getOrderedSources(Uri uri) {
        int lastSource = CirrusDatabaseUtil.getLastSource(uri);
        List<Integer> list = MediaProvider.CirrusBaseColumns.Source.ALL_SOURCES;
        int[] iArr = new int[list.size()];
        iArr[0] = lastSource;
        Iterator<Integer> it = list.iterator();
        int i = 1;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (iIntValue != lastSource) {
                iArr[i] = iIntValue;
                i++;
            }
        }
        return iArr;
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int bulkInsert(Uri uri, ContentValues[] values) {
        throw new UnsupportedOperationException("BulkInsert not available for merged URI");
    }

    private synchronized SQLiteDatabase getDb() {
        return CirrusDatabase.getWritableDatabase(this.mContext);
    }
}
