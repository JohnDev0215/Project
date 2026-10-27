package com.amazon.mp3.library.provider.source.nowplaying;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingManager {
    private NowPlayingCollectionHandler mCollectionHandler;

    private static class NowPlayingManagerHolder {
        static NowPlayingManager sInstance = new NowPlayingManager();

        private NowPlayingManagerHolder() {
        }
    }

    private NowPlayingManager() {
        this.mCollectionHandler = new NowPlayingCollectionHandler(AmazonApplication.getContext());
    }

    public static NowPlayingManager getInstance() {
        return NowPlayingManagerHolder.sInstance;
    }

    public void registerListener(NowPlayingListener listener) {
        this.mCollectionHandler.registerListener(listener);
    }

    public boolean restore(JSONObject blob, boolean startPlayback) {
        return this.mCollectionHandler.restore(blob, startPlayback, false, false);
    }

    public int getTrackCount() {
        return this.mCollectionHandler.getTrackCount();
    }

    public void clear() {
        clear(true);
    }

    public void clear(final boolean clearPlayState) {
        this.mCollectionHandler.clear(clearPlayState);
    }

    public Uri getCurrentUri() {
        return this.mCollectionHandler.getUri();
    }

    public MusicTrack getCurrentTrack() {
        return this.mCollectionHandler.getTrack();
    }

    public int getTrackPosition() {
        return this.mCollectionHandler.getPlaybackPosition();
    }

    public String getCollectionName() {
        return this.mCollectionHandler.getCollectionName();
    }
}
