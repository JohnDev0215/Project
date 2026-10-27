package com.amazon.mp3.external.api.uri;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.find.utils.PageUriUtils;

/* JADX INFO: loaded from: classes3.dex */
public class QueryAlbumByAsin implements FindUriByAsinJob.QueryEntityById {
    private final Context mContext;

    public QueryAlbumByAsin(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri findCollectionUriByAsin(String asin) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(MediaProvider.Albums.getContentUri("merged"), new String[]{"_id", "album_asin", PageUriUtils.SOURCE_QUERY_PARAM}, "album_asin = ?", new String[]{asin}, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM));
            DbUtil.closeCursor(cursorQuery);
            return MediaProvider.Albums.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), j);
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri convertCollectionUriToTracksUri(final Uri collectionUri) {
        return MediaProvider.Albums.Tracks.getContentUri(MediaProvider.getSource(collectionUri), MediaProvider.Albums.getAlbumId(collectionUri));
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public int findTrackIndexInCollectionByAsin(final Uri collectionUri, final String trackAsin) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(collectionUri, new String[]{"asin"}, null, null, null);
        int position = -1;
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int columnIndex = cursorQuery.getColumnIndex("asin");
            while (true) {
                if (trackAsin.equals(cursorQuery.getString(columnIndex))) {
                    position = cursorQuery.getPosition();
                    break;
                }
                if (!cursorQuery.moveToNext()) {
                    break;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return position;
        }
        DbUtil.closeCursor(cursorQuery);
        return -1;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryEntityById
    public int findTrackIndexInCollectionByLuid(final Uri collectionUri, final String trackLuid) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(collectionUri, new String[]{"luid"}, "luid IS NOT NULL", null, null);
        int position = -1;
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int columnIndex = cursorQuery.getColumnIndex("luid");
            while (true) {
                if (trackLuid.equals(cursorQuery.getString(columnIndex))) {
                    position = cursorQuery.getPosition();
                    break;
                }
                if (!cursorQuery.moveToNext()) {
                    break;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return position;
        }
        DbUtil.closeCursor(cursorQuery);
        return -1;
    }
}
