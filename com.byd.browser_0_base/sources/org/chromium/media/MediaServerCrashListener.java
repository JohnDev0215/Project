package org.chromium.media;

import J.N;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.Log;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class MediaServerCrashListener implements MediaPlayer.OnErrorListener {
    public MediaPlayer a;
    public long b = -1;
    public final long c;

    public MediaServerCrashListener(long j) {
        this.c = j;
    }

    @CalledByNative
    public static MediaServerCrashListener create(long j) {
        return new MediaServerCrashListener(j);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            N.Mm$QSrAo(this.c, this, true);
            releaseWatchdog();
        }
        return true;
    }

    @CalledByNative
    public void releaseWatchdog() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.release();
        this.a = null;
    }

    @CalledByNative
    public boolean startListening() {
        if (this.a != null) {
            return true;
        }
        try {
            this.a = MediaPlayer.create(xz.a, R$raw.empty);
        } catch (IllegalStateException e) {
            Log.e("cr_crMediaCrashListener", "Exception while creating the watchdog player.", e);
        } catch (RuntimeException e2) {
            Log.e("cr_crMediaCrashListener", "Exception while creating the watchdog player.", e2);
        }
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            mediaPlayer.setOnErrorListener(this);
            this.b = -1L;
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.b;
        if (j == -1 || jElapsedRealtime - j > 5000) {
            Log.e("cr_crMediaCrashListener", "Unable to create watchdog player, treating it as server crash.");
            N.Mm$QSrAo(this.c, this, false);
            this.b = jElapsedRealtime;
        }
        return false;
    }
}
