package com.amazon.mp3.library.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.library.provider.MediaProvider;

/* JADX INFO: loaded from: classes3.dex */
public class CreatePlaylistTask extends AsyncTask<Void, Void, Result> {
    private final Context mContext;
    private final OnPlaylistCreatedListener mListener;
    private final String mName;
    private final String mSourceId;

    public interface OnPlaylistCreatedListener {
        void onPlaylistCreated(Result result, boolean success);
    }

    public static final class Result {
        public final String mPlaylistLuid;
        public final Uri mPlaylistUri;

        public Result(final Uri playlistUri, final String playlistLuid) {
            this.mPlaylistUri = playlistUri;
            this.mPlaylistLuid = playlistLuid;
        }
    }

    public CreatePlaylistTask(Context context, String sourceId, String name, OnPlaylistCreatedListener listener) {
        this.mContext = context;
        this.mSourceId = sourceId;
        this.mName = name;
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... params) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.mName);
        Uri uriInsert = contentResolver.insert(MediaProvider.UdoPlaylists.getContentUri(this.mSourceId), contentValues);
        return new Result(uriInsert, MediaProvider.Playlists.getPlaylistLuid(uriInsert));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(final Result result) {
        OnPlaylistCreatedListener onPlaylistCreatedListener = this.mListener;
        if (onPlaylistCreatedListener != null) {
            onPlaylistCreatedListener.onPlaylistCreated(result, result.mPlaylistUri != null);
        }
    }
}
