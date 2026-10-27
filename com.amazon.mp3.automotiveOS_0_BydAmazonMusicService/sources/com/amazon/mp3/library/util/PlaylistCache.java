package com.amazon.mp3.library.util;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.FileCache;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaylistCache {
    private static PlaylistCache sInstance;
    private Context mContext;
    private FileCache mFileCache = new FileCache("playlists", Integer.MIN_VALUE, -2147483648L);

    public static synchronized PlaylistCache getInstance() {
        if (sInstance == null) {
            sInstance = new PlaylistCache(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private PlaylistCache(final Context context) {
        this.mContext = context;
    }

    private static String getKey(final Uri uri) {
        return Long.toString(PlaylistUtil.getPlaylistId(uri));
    }

    public boolean removePlaylist(Uri playlistUri) {
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(playlistUri)) {
            return false;
        }
        this.mFileCache.remove(getKey(playlistUri));
        return true;
    }
}
