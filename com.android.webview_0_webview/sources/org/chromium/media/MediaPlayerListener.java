package org.chromium.media;

import J.N;
import android.media.MediaPlayer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener {
    public long a;

    public MediaPlayerListener(long j) {
        this.a = j;
    }

    public static MediaPlayerListener create(long j, MediaPlayerBridge mediaPlayerBridge) {
        MediaPlayerListener mediaPlayerListener = new MediaPlayerListener(j);
        if (mediaPlayerBridge != null) {
            mediaPlayerBridge.b().setOnCompletionListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnErrorListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnPreparedListener(mediaPlayerListener);
            mediaPlayerBridge.b().setOnVideoSizeChangedListener(mediaPlayerListener);
        }
        return mediaPlayerListener;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        N.MX$D6jYE(this.a, this);
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
        N.Myj2LnkZ(this.a, this, i3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        N.MQTompEl(this.a, this);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        N.Mfq$ZJpW(this.a, this, i, i2);
    }
}
