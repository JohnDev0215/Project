package com.telenav.scoutivi.spotify.sdk.byd.dx;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.vivid.sdk.music.RequestCallBack;
import com.vivid.sdk.music.ResponseCode;
import com.vivid.sdk.spotify.MediaControllerCallBack;
import com.vivid.sdk.spotify.PlayState;
import com.vivid.sdk.spotify.RepeatMode;
import com.vivid.sdk.spotify.ShuffleMode;
import com.vivid.sdk.spotify.SpotifyMediaManager;

/* JADX INFO: loaded from: classes5.dex */
public class SpotifyBYDDXMediaManager {
    private final String TAG;
    private SpotifyMediaManager mManager;

    private SpotifyBYDDXMediaManager() {
        this.TAG = "SpotifyBYDDXMediaManager";
        this.mManager = SpotifyMediaManager.getInstance();
    }

    public static SpotifyBYDDXMediaManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final SpotifyBYDDXMediaManager INSTANCE = new SpotifyBYDDXMediaManager();

        private Holder() {
        }
    }

    public void init(Context context, RequestCallBack requestCallBack) {
        this.mManager.init(context, requestCallBack);
    }

    public void destroy() {
        this.mManager.destroy();
    }

    public Drawable getAppIcon() {
        return this.mManager.getAppIcon();
    }

    public String getAppName() {
        return this.mManager.getAppName();
    }

    public void play(final RequestCallBack requestCallBack) {
        this.mManager.play(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.1
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void pause(final RequestCallBack requestCallBack) {
        this.mManager.pause(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.2
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void stop(final RequestCallBack requestCallBack) {
        this.mManager.stop(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.3
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void resume(final RequestCallBack requestCallBack) {
        this.mManager.resume(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.4
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void skipToPrevious(final RequestCallBack requestCallBack) {
        this.mManager.skipToPrevious(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.5
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void skipToNext(final RequestCallBack requestCallBack) {
        this.mManager.skipToNext(new RequestCallBack() { // from class: com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager.6
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onSuccess();
                }
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyBYDDXMediaManager.this.mManager.moveAppToFront(requestCallBack);
            }
        });
    }

    public void addFavorite(RequestCallBack requestCallBack) {
        this.mManager.addFavorite(requestCallBack);
    }

    public void removeFavorite(RequestCallBack requestCallBack) {
        this.mManager.removeFavorite(requestCallBack);
    }

    public void setRepeatMode(RepeatMode repeatMode, RequestCallBack requestCallBack) {
        this.mManager.setRepeatMode(repeatMode, requestCallBack);
    }

    public void setShuffleMode(ShuffleMode shuffleMode, RequestCallBack requestCallBack) {
        this.mManager.setShuffleMode(shuffleMode, requestCallBack);
    }

    public void replay(RequestCallBack requestCallBack) {
        this.mManager.replay(requestCallBack);
    }

    public void openPlayList(RequestCallBack requestCallBack) {
        this.mManager.openPlayList(requestCallBack);
    }

    public void closePlayList(RequestCallBack requestCallBack) {
        this.mManager.closePlayList(requestCallBack);
    }

    public void moveAppToFront(RequestCallBack requestCallBack) {
        this.mManager.moveAppToFront(requestCallBack);
    }

    public PlayState getPlayState() {
        return this.mManager.getPlayState();
    }

    public boolean addMediaControllerCallback(MediaControllerCallBack mediaControllerCallBack) {
        return this.mManager.addMediaControllerCallback(mediaControllerCallBack);
    }

    public boolean removeMediaControllerCallback(MediaControllerCallBack mediaControllerCallBack) {
        return this.mManager.removeMediaControllerCallback(mediaControllerCallBack);
    }

    public boolean isServiceConnected() {
        return this.mManager.isServiceConnected();
    }
}
