package com.amazon.mp3.library.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.amazon.mp3.library.provider.source.nowplaying.RecentItemsManager;

/* JADX INFO: loaded from: classes3.dex */
public class RecentItemsProviderSource implements ProviderSource {
    private Context mContext;

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int delete(Uri uri, String selection, String[] selectionArgs) throws SQLException {
        throw new RuntimeException("NowPlayingSource.delete() not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Uri insert(Uri uri, ContentValues values) {
        throw new RuntimeException("NowPlayingSource.insert() not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int bulkInsert(Uri uri, ContentValues[] values) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public void onRegistered(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int iMatch = DefaultUriMatcher.match(uri);
        String queryParameter = uri.getQueryParameter("limit");
        if (iMatch == 20) {
            return RecentItemsManager.getInstance(this.mContext).queryRecentItems(projection, selection, selectionArgs, sortOrder, queryParameter);
        }
        throw new IllegalArgumentException("NowPlayingSource.query(): unknown uri " + uri);
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new RuntimeException("NowPlayingSource.update() not implemented");
    }
}
