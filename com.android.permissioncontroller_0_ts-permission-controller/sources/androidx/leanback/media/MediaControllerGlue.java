package androidx.leanback.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class MediaControllerGlue extends PlaybackControlGlue {
    static final boolean DEBUG = false;
    static final String TAG = "MediaControllerGlue";
    private final MediaControllerCompat.Callback mCallback;
    MediaControllerCompat mMediaController;

    public MediaControllerGlue(Context context, int[] fastForwardSpeeds, int[] rewindSpeeds) {
        super(context, fastForwardSpeeds, rewindSpeeds);
        this.mCallback = new MediaControllerCompat.Callback() { // from class: androidx.leanback.media.MediaControllerGlue.1
            @Override // android.support.v4.media.session.MediaControllerCompat.Callback
            public void onMetadataChanged(MediaMetadataCompat metadata) {
                MediaControllerGlue.this.onMetadataChanged();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback
            public void onPlaybackStateChanged(PlaybackStateCompat state) {
                MediaControllerGlue.this.onStateChanged();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback
            public void onSessionDestroyed() {
                MediaControllerGlue.this.mMediaController = null;
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.Callback
            public void onSessionEvent(String event, Bundle extras) {
            }
        };
    }

    public void attachToMediaController(MediaControllerCompat mediaController) {
        if (mediaController != this.mMediaController) {
            detach();
            this.mMediaController = mediaController;
            if (mediaController != null) {
                mediaController.registerCallback(this.mCallback);
            }
            onMetadataChanged();
            onStateChanged();
        }
    }

    public void detach() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.mCallback);
        }
        this.mMediaController = null;
    }

    public final MediaControllerCompat getMediaController() {
        return this.mMediaController;
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public boolean hasValidMedia() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        return (mediaControllerCompat == null || mediaControllerCompat.getMetadata() == null) ? false : true;
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public boolean isMediaPlaying() {
        return this.mMediaController.getPlaybackState().getState() == 3;
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public int getCurrentSpeedId() {
        int speed = (int) this.mMediaController.getPlaybackState().getPlaybackSpeed();
        if (speed == 0) {
            return 0;
        }
        if (speed == 1) {
            return 1;
        }
        if (speed > 0) {
            int[] seekSpeeds = getFastForwardSpeeds();
            for (int index = 0; index < seekSpeeds.length; index++) {
                if (speed == seekSpeeds[index]) {
                    return index + 10;
                }
            }
        } else {
            int[] seekSpeeds2 = getRewindSpeeds();
            for (int index2 = 0; index2 < seekSpeeds2.length; index2++) {
                if ((-speed) == seekSpeeds2[index2]) {
                    return (-10) - index2;
                }
            }
        }
        Log.w(TAG, "Couldn't find index for speed " + speed);
        return -1;
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public CharSequence getMediaTitle() {
        return this.mMediaController.getMetadata().getDescription().getTitle();
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public CharSequence getMediaSubtitle() {
        return this.mMediaController.getMetadata().getDescription().getSubtitle();
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public int getMediaDuration() {
        return (int) this.mMediaController.getMetadata().getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public int getCurrentPosition() {
        return (int) this.mMediaController.getPlaybackState().getPosition();
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public Drawable getMediaArt() {
        Bitmap bitmap = this.mMediaController.getMetadata().getDescription().getIconBitmap();
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(getContext().getResources(), bitmap);
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public long getSupportedActions() {
        long result = 0;
        long actions = this.mMediaController.getPlaybackState().getActions();
        if ((512 & actions) != 0) {
            result = 0 | 64;
        }
        if ((actions & 32) != 0) {
            result |= 256;
        }
        if ((actions & 16) != 0) {
            result |= 16;
        }
        if ((64 & actions) != 0) {
            result |= 128;
        }
        if ((8 & actions) != 0) {
            return result | 32;
        }
        return result;
    }

    @Override // androidx.leanback.media.PlaybackControlGlue
    public void play(int speed) {
        if (speed == 1) {
            this.mMediaController.getTransportControls().play();
        } else if (speed > 0) {
            this.mMediaController.getTransportControls().fastForward();
        } else {
            this.mMediaController.getTransportControls().rewind();
        }
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void pause() {
        this.mMediaController.getTransportControls().pause();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void next() {
        this.mMediaController.getTransportControls().skipToNext();
    }

    @Override // androidx.leanback.media.PlaybackGlue
    public void previous() {
        this.mMediaController.getTransportControls().skipToPrevious();
    }
}
