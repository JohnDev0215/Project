package androidx.leanback.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: loaded from: classes.dex */
public class MediaControllerAdapter extends PlayerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "MediaControllerAdapter";
    private MediaControllerCompat mController;
    Handler mHandler = new Handler();
    private final Runnable mPositionUpdaterRunnable = new Runnable() { // from class: androidx.leanback.media.MediaControllerAdapter.1
        @Override // java.lang.Runnable
        public void run() {
            MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
            MediaControllerAdapter.this.mHandler.postDelayed(this, MediaControllerAdapter.this.getUpdatePeriod());
        }
    };
    boolean mIsBuffering = DEBUG;
    MediaControllerCompat.Callback mMediaControllerCallback = new MediaControllerCompat.Callback() { // from class: androidx.leanback.media.MediaControllerAdapter.2
        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            if (MediaControllerAdapter.this.mIsBuffering && state.getState() != 6) {
                MediaControllerAdapter.this.getCallback().onBufferingStateChanged(MediaControllerAdapter.this, MediaControllerAdapter.DEBUG);
                MediaControllerAdapter.this.getCallback().onBufferedPositionChanged(MediaControllerAdapter.this);
                MediaControllerAdapter.this.mIsBuffering = MediaControllerAdapter.DEBUG;
            }
            if (state.getState() != 0) {
                if (state.getState() == 1) {
                    MediaControllerAdapter.this.getCallback().onPlayCompleted(MediaControllerAdapter.this);
                    return;
                }
                if (state.getState() == 2) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                    return;
                }
                if (state.getState() == 3) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                    return;
                }
                if (state.getState() == 6) {
                    MediaControllerAdapter.this.mIsBuffering = true;
                    MediaControllerAdapter.this.getCallback().onBufferingStateChanged(MediaControllerAdapter.this, true);
                    MediaControllerAdapter.this.getCallback().onBufferedPositionChanged(MediaControllerAdapter.this);
                    return;
                }
                if (state.getState() == 7) {
                    CharSequence errorMessage = state.getErrorMessage();
                    if (errorMessage == null) {
                        MediaControllerAdapter.this.getCallback().onError(MediaControllerAdapter.this, state.getErrorCode(), "");
                        return;
                    } else {
                        MediaControllerAdapter.this.getCallback().onError(MediaControllerAdapter.this, state.getErrorCode(), state.getErrorMessage().toString());
                        return;
                    }
                }
                if (state.getState() == 4) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                } else if (state.getState() == 5) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat metadata) {
            MediaControllerAdapter.this.getCallback().onMetadataChanged(MediaControllerAdapter.this);
        }
    };

    int getUpdatePeriod() {
        return 16;
    }

    public MediaControllerAdapter(MediaControllerCompat controller) {
        if (controller == null) {
            throw new NullPointerException("Object of MediaControllerCompat is null");
        }
        this.mController = controller;
    }

    public MediaControllerCompat getMediaController() {
        return this.mController;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void play() {
        this.mController.getTransportControls().play();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void pause() {
        this.mController.getTransportControls().pause();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void seekTo(long positionInMs) {
        this.mController.getTransportControls().seekTo(positionInMs);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void next() {
        this.mController.getTransportControls().skipToNext();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void previous() {
        this.mController.getTransportControls().skipToPrevious();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void fastForward() {
        this.mController.getTransportControls().fastForward();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void rewind() {
        this.mController.getTransportControls().rewind();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void setRepeatAction(int repeatActionIndex) {
        int repeatMode = mapRepeatActionToRepeatMode(repeatActionIndex);
        this.mController.getTransportControls().setRepeatMode(repeatMode);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void setShuffleAction(int shuffleActionIndex) {
        int shuffleMode = mapShuffleActionToShuffleMode(shuffleActionIndex);
        this.mController.getTransportControls().setShuffleMode(shuffleMode);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public boolean isPlaying() {
        if (this.mController.getPlaybackState() == null) {
            return DEBUG;
        }
        if (this.mController.getPlaybackState().getState() == 3 || this.mController.getPlaybackState().getState() == 4 || this.mController.getPlaybackState().getState() == 5) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getCurrentPosition() {
        if (this.mController.getPlaybackState() == null) {
            return 0L;
        }
        return this.mController.getPlaybackState().getPosition();
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getBufferedPosition() {
        if (this.mController.getPlaybackState() == null) {
            return 0L;
        }
        return this.mController.getPlaybackState().getBufferedPosition();
    }

    public CharSequence getMediaTitle() {
        if (this.mController.getMetadata() == null) {
            return "";
        }
        return this.mController.getMetadata().getDescription().getTitle();
    }

    public CharSequence getMediaSubtitle() {
        if (this.mController.getMetadata() == null) {
            return "";
        }
        return this.mController.getMetadata().getDescription().getSubtitle();
    }

    public Drawable getMediaArt(Context context) {
        Bitmap bitmap;
        if (this.mController.getMetadata() == null || (bitmap = this.mController.getMetadata().getDescription().getIconBitmap()) == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getDuration() {
        if (this.mController.getMetadata() == null) {
            return 0L;
        }
        return (int) this.mController.getMetadata().getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void onAttachedToHost(PlaybackGlueHost host) {
        this.mController.registerCallback(this.mMediaControllerCallback);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void onDetachedFromHost() {
        this.mController.unregisterCallback(this.mMediaControllerCallback);
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public void setProgressUpdatingEnabled(boolean enabled) {
        this.mHandler.removeCallbacks(this.mPositionUpdaterRunnable);
        if (!enabled) {
            return;
        }
        this.mHandler.postDelayed(this.mPositionUpdaterRunnable, getUpdatePeriod());
    }

    @Override // androidx.leanback.media.PlayerAdapter
    public long getSupportedActions() {
        if (this.mController.getPlaybackState() == null) {
            return 0L;
        }
        long actionsFromController = this.mController.getPlaybackState().getActions();
        long supportedActions = (actionsFromController & 512) != 0 ? 0 | 64 : 0L;
        if ((actionsFromController & 32) != 0) {
            supportedActions |= 256;
        }
        if ((actionsFromController & 16) != 0) {
            supportedActions |= 16;
        }
        if ((64 & actionsFromController) != 0) {
            supportedActions |= 128;
        }
        if ((8 & actionsFromController) != 0) {
            supportedActions |= 32;
        }
        if ((PlaybackStateCompat.ACTION_SET_REPEAT_MODE & actionsFromController) != 0) {
            supportedActions |= 512;
        }
        if ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE & actionsFromController) != 0) {
            return supportedActions | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return supportedActions;
    }

    private int mapRepeatActionToRepeatMode(int repeatActionIndex) {
        if (repeatActionIndex == 0) {
            return 0;
        }
        if (repeatActionIndex != 1) {
            return repeatActionIndex != 2 ? -1 : 1;
        }
        return 2;
    }

    private int mapShuffleActionToShuffleMode(int shuffleActionIndex) {
        if (shuffleActionIndex != 0) {
            return shuffleActionIndex != 1 ? -1 : 1;
        }
        return 0;
    }
}
