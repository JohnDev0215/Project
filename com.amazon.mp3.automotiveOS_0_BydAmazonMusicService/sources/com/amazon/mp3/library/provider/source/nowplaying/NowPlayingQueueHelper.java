package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingQueueHelper implements PlayQueueHelper {
    private Context mContext;

    public NowPlayingQueueHelper(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.PlayQueueHelper
    public boolean isUnlimitedCollection(Uri uri) {
        return DefaultUriMatcher.match(uri) == 1 || DefaultUriMatcher.match(uri) == 15 || DefaultUriMatcher.match(uri) == 13 || DefaultUriMatcher.match(uri) == 24 || DefaultUriMatcher.match(uri) == 27 || DefaultUriMatcher.match(uri) == 26 || DefaultUriMatcher.match(uri) == 7 || DefaultUriMatcher.match(uri) == 10 || DefaultUriMatcher.match(uri) == 9;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.PlayQueueHelper
    public String getCatalogPlaylistId(Uri contentUri) {
        return MediaProvider.Playlists.getCatalogPlaylistId(contentUri);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.PlayQueueHelper
    public Cursor queryNowPlayingTracks(String[] projection, String order) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        StringBuilder sb = new StringBuilder();
        sb.append(NowPlayingQueueTables.PLAYQUEUE_QUERY);
        sb.append("shuffle".equals(order) ? " ORDER BY shuffle" : " ORDER BY udo");
        return writableDatabase.rawQuery(sb.toString(), null);
    }
}
