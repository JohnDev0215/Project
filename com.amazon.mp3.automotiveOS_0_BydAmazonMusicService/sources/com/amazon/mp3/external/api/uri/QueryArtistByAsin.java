package com.amazon.mp3.external.api.uri;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.find.utils.PageUriUtils;

/* JADX INFO: loaded from: classes3.dex */
public class QueryArtistByAsin implements FindUriByAsinJob.QueryEntityById {
    private final Context mContext;

    public QueryArtistByAsin(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri findCollectionUriByAsin(String asin) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(MediaProvider.Artists.getContentUri("merged"), new String[]{"_id", "artist_asin", PageUriUtils.SOURCE_QUERY_PARAM}, "artist_asin = ?", new String[]{asin}, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM));
            DbUtil.closeCursor(cursorQuery);
            return MediaProvider.Artists.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), j);
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri convertCollectionUriToTracksUri(final Uri collectionUri) {
        return MediaProvider.Artists.Tracks.getContentUri(MediaProvider.getSource(collectionUri), MediaProvider.Artists.getArtistId(collectionUri));
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
