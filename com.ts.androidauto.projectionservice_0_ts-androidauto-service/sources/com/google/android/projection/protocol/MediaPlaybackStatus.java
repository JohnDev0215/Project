package com.google.android.projection.protocol;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlaybackStatus implements CarServiceProvider, MediaPlaybackStatusCallbacks {
    private static final boolean DBG = true;
    private static final String TAG = "AHU.MEDIA_PLAYBACK";
    private final MediaPlaybackStatusListener mListener;
    private long mNativeMediaPlaybackStatus;

    public interface MediaPlaybackStatusListener {
        void onMediaMetadata(String str, String str2, String str3, byte[] bArr, String str4, int i, int i2);

        void onMediaStatus(int i, String str, int i2, boolean z, boolean z2, boolean z3);
    }

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native int nativeReportAction(int i);

    private native void nativeShutdown();

    public MediaPlaybackStatus(MediaPlaybackStatusListener listener) {
        this.mListener = listener;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver) == 0) {
            return DBG;
        }
        return false;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeMediaPlaybackStatus;
    }

    public int reportAction(int action) {
        return nativeReportAction(action);
    }

    @Override // com.google.android.projection.protocol.MediaPlaybackStatusCallbacks
    public void mediaPlaybackStatusCallback(int state, String source, int playback_seconds, boolean shuffle, boolean repeat, boolean repeat_one) {
        Log.i("AHU.MEDIA_PLAYBACK", "State: " + state);
        Log.i("AHU.MEDIA_PLAYBACK", "Source: " + source);
        Log.i("AHU.MEDIA_PLAYBACK", "Playback: " + playback_seconds);
        Log.i("AHU.MEDIA_PLAYBACK", "Shuffle: " + shuffle);
        Log.i("AHU.MEDIA_PLAYBACK", "Repeat: " + repeat);
        Log.i("AHU.MEDIA_PLAYBACK", "Repeat One: " + repeat_one);
        this.mListener.onMediaStatus(state, source, playback_seconds, shuffle, repeat, repeat_one);
    }

    @Override // com.google.android.projection.protocol.MediaPlaybackStatusCallbacks
    public void mediaPlaybackMetadataCallback(String song, String album, String artist, byte[] album_art, String playlist, int duration_seconds, int ratings) {
        Log.i("AHU.MEDIA_PLAYBACK", "Song: " + song);
        Log.i("AHU.MEDIA_PLAYBACK", "Album: " + album);
        Log.i("AHU.MEDIA_PLAYBACK", "Artist: " + artist);
        Log.i("AHU.MEDIA_PLAYBACK", "Playlist: " + playlist);
        Log.i("AHU.MEDIA_PLAYBACK", "Duration: " + duration_seconds);
        Log.i("AHU.MEDIA_PLAYBACK", "Ratings: " + ratings);
        this.mListener.onMediaMetadata(song, album, artist, album_art, playlist, duration_seconds, ratings);
    }
}
