package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.ProviderSource;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingSource implements ProviderSource {
    private static final String MEDIA_PROVIDER_AUTHORITY = MediaProvider.AUTHORITY;

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public void onRegistered(Context context) {
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int iMatch = DefaultUriMatcher.match(uri);
        boolean zIsShuffled = PlaybackControllerProvider.getController(ControlSource.APP_UI).isShuffled();
        if (iMatch == 18) {
            if (zIsShuffled) {
                return PlayQueue.getInstance().query(projection, "shuffle");
            }
            return PlayQueue.getInstance().query(projection, "udo");
        }
        throw new IllegalArgumentException("NowPlayingSource.query(): unknown uri " + uri);
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int delete(Uri uri, String selection, String[] selectionArgs) throws SQLException {
        throw new UnsupportedOperationException("NowPlayingSource.delete() not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("NowPlayingSource.insert() not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int bulkInsert(Uri uri, ContentValues[] values) {
        throw new UnsupportedOperationException("NowPlayingSource.bulkInsert()  Not implemented");
    }

    @Override // com.amazon.mp3.library.provider.ProviderSource
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("NowPlayingSource.update() not implemented");
    }
}
