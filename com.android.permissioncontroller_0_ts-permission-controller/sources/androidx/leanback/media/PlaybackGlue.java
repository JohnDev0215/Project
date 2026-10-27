package androidx.leanback.media;

import android.content.Context;
import androidx.leanback.media.PlaybackGlueHost;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PlaybackGlue {
    private final Context mContext;
    private PlaybackGlueHost mPlaybackGlueHost;
    ArrayList<PlayerCallback> mPlayerCallbacks;

    public static abstract class PlayerCallback {
        public void onPreparedStateChanged(PlaybackGlue glue) {
        }

        public void onPlayStateChanged(PlaybackGlue glue) {
        }

        public void onPlayCompleted(PlaybackGlue glue) {
        }
    }

    public PlaybackGlue(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isPrepared() {
        return true;
    }

    public void addPlayerCallback(PlayerCallback playerCallback) {
        if (this.mPlayerCallbacks == null) {
            this.mPlayerCallbacks = new ArrayList<>();
        }
        this.mPlayerCallbacks.add(playerCallback);
    }

    public void removePlayerCallback(PlayerCallback callback) {
        ArrayList<PlayerCallback> arrayList = this.mPlayerCallbacks;
        if (arrayList != null) {
            arrayList.remove(callback);
        }
    }

    protected List<PlayerCallback> getPlayerCallbacks() {
        if (this.mPlayerCallbacks == null) {
            return null;
        }
        return new ArrayList(this.mPlayerCallbacks);
    }

    public boolean isPlaying() {
        return false;
    }

    public void play() {
    }

    public void playWhenPrepared() {
        if (isPrepared()) {
            play();
        } else {
            addPlayerCallback(new PlayerCallback() { // from class: androidx.leanback.media.PlaybackGlue.1
                @Override // androidx.leanback.media.PlaybackGlue.PlayerCallback
                public void onPreparedStateChanged(PlaybackGlue glue) {
                    if (glue.isPrepared()) {
                        PlaybackGlue.this.removePlayerCallback(this);
                        PlaybackGlue.this.play();
                    }
                }
            });
        }
    }

    public void pause() {
    }

    public void next() {
    }

    public void previous() {
    }

    public final void setHost(PlaybackGlueHost host) {
        PlaybackGlueHost playbackGlueHost = this.mPlaybackGlueHost;
        if (playbackGlueHost == host) {
            return;
        }
        if (playbackGlueHost != null) {
            playbackGlueHost.attachToGlue(null);
        }
        this.mPlaybackGlueHost = host;
        if (host != null) {
            host.attachToGlue(this);
        }
    }

    protected void onHostStart() {
    }

    protected void onHostStop() {
    }

    protected void onHostResume() {
    }

    protected void onHostPause() {
    }

    protected void onAttachedToHost(PlaybackGlueHost host) {
        this.mPlaybackGlueHost = host;
        host.setHostCallback(new PlaybackGlueHost.HostCallback() { // from class: androidx.leanback.media.PlaybackGlue.2
            @Override // androidx.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostStart() {
                PlaybackGlue.this.onHostStart();
            }

            @Override // androidx.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostStop() {
                PlaybackGlue.this.onHostStop();
            }

            @Override // androidx.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostResume() {
                PlaybackGlue.this.onHostResume();
            }

            @Override // androidx.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostPause() {
                PlaybackGlue.this.onHostPause();
            }

            @Override // androidx.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostDestroy() {
                PlaybackGlue.this.setHost(null);
            }
        });
    }

    protected void onDetachedFromHost() {
        PlaybackGlueHost playbackGlueHost = this.mPlaybackGlueHost;
        if (playbackGlueHost != null) {
            playbackGlueHost.setHostCallback(null);
            this.mPlaybackGlueHost = null;
        }
    }

    public PlaybackGlueHost getHost() {
        return this.mPlaybackGlueHost;
    }
}
