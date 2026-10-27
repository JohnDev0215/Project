package org.chromium.media;

import android.content.Context;
import android.media.MediaPlayer;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener {
    private static final int MEDIA_ERROR_DECODE = 1;
    private static final int MEDIA_ERROR_FORMAT = 0;
    private static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    private static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    private static final int MEDIA_ERROR_SERVER_DIED = 4;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    private final Context mContext;
    private long mNativeMediaPlayerListener;

    private native void nativeOnBufferingUpdate(long j, int i);

    private native void nativeOnMediaError(long j, int i);

    private native void nativeOnMediaInterrupted(long j);

    private native void nativeOnMediaPrepared(long j);

    private native void nativeOnPlaybackComplete(long j);

    private native void nativeOnSeekComplete(long j);

    private native void nativeOnVideoSizeChanged(long j, int i, int i2);

    private MediaPlayerListener(long j, Context context) {
        this.mNativeMediaPlayerListener = j;
        this.mContext = context;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        int i3 = 3;
        if (i != 1) {
            if (i == 100) {
                i3 = 4;
            } else if (i == 200) {
                i3 = 2;
            }
        } else if (i2 == -1007) {
            i3 = 1;
        } else if (i2 != -110) {
            i3 = 0;
        }
        nativeOnMediaError(this.mNativeMediaPlayerListener, i3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        nativeOnVideoSizeChanged(this.mNativeMediaPlayerListener, i, i2);
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        nativeOnSeekComplete(this.mNativeMediaPlayerListener);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        nativeOnBufferingUpdate(this.mNativeMediaPlayerListener, i);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        nativeOnPlaybackComplete(this.mNativeMediaPlayerListener);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        nativeOnMediaPrepared(this.mNativeMediaPlayerListener);
    }

    private static MediaPlayerListener create(long j, Context context, MediaPlayerBridge mediaPlayerBridge) {
        MediaPlayerListener mediaPlayerListener = new MediaPlayerListener(j, context);
        if (mediaPlayerBridge != null) {
            mediaPlayerBridge.setOnBufferingUpdateListener(mediaPlayerListener);
            mediaPlayerBridge.setOnCompletionListener(mediaPlayerListener);
            mediaPlayerBridge.setOnErrorListener(mediaPlayerListener);
            mediaPlayerBridge.setOnPreparedListener(mediaPlayerListener);
            mediaPlayerBridge.setOnSeekCompleteListener(mediaPlayerListener);
            mediaPlayerBridge.setOnVideoSizeChangedListener(mediaPlayerListener);
        }
        return mediaPlayerListener;
    }
}
