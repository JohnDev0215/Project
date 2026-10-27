package com.amazon.mp3.external.api.uri;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class QuerySongByAsin implements FindUriByAsinJob.QueryEntityById {
    private final Context mContext;

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri convertCollectionUriToTracksUri(final Uri collectionUri) {
        return collectionUri;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public int findTrackIndexInCollectionByAsin(final Uri collectionUri, final String trackAsin) {
        return 0;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryEntityById
    public int findTrackIndexInCollectionByLuid(final Uri collectionUri, final String trackLuid) {
        return 0;
    }

    public QuerySongByAsin(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.external.api.uri.FindUriByAsinJob.QueryByAsin
    public Uri findCollectionUriByAsin(String asin) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(MediaProvider.Tracks.getContentUri("merged"), new String[]{"_id", "asin", PageUriUtils.SOURCE_QUERY_PARAM}, "asin = ?", new String[]{asin}, null);
        if (cursorQuery == null) {
            return null;
        }
        if (cursorQuery.moveToFirst()) {
            long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM));
            DbUtil.closeCursor(cursorQuery);
            return MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(i), j);
        }
        DbUtil.closeCursor(cursorQuery);
        return null;
    }

    public HashMap<String, Uri> findCollectionUrisByAsin(List<String> asins) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < asins.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("?");
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(MediaProvider.Tracks.getContentUri("merged"), new String[]{"_id", "asin", PageUriUtils.SOURCE_QUERY_PARAM}, "asin in (" + sb.toString() + ")", (String[]) asins.toArray(new String[0]), null);
        HashMap<String, Uri> map = new HashMap<>();
        if (cursorQuery == null) {
            return null;
        }
        while (cursorQuery.moveToNext()) {
            map.put(cursorQuery.getString(cursorQuery.getColumnIndex("asin")), MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM))), cursorQuery.getLong(cursorQuery.getColumnIndex("_id"))));
        }
        DbUtil.closeCursor(cursorQuery);
        return map;
    }
}
