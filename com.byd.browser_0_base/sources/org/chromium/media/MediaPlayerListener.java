package org.chromium.media;

import J.N;
import android.media.MediaPlayer;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener {
    public final long a;

    public MediaPlayerListener(long j) {
        this.a = j;
    }

    @CalledByNative
    public static MediaPlayerListener create(long j, MediaPlayerBridge mediaPlayerBridge) {
        MediaPlayerListener mediaPlayerListener = new MediaPlayerListener(j);
        if (mediaPlayerBridge != null) {
            mediaPlayerBridge.setOnCompletionListener(mediaPlayerListener);
            mediaPlayerBridge.setOnErrorListener(mediaPlayerListener);
            mediaPlayerBridge.setOnPreparedListener(mediaPlayerListener);
            mediaPlayerBridge.setOnVideoSizeChangedListener(mediaPlayerListener);
        }
        return mediaPlayerListener;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        N.MX$D6jYE(this.a, this);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
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
        N.Myj2LnkZ(this.a, this, i3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        N.MQTompEl(this.a, this);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        N.Mfq$ZJpW(this.a, this, i, i2);
    }
}
