package org.chromium.media;

import J.N;
import android.media.MediaPlayer;
import android.os.SystemClock;
import defpackage.AbstractC0510an;
import defpackage.Is;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaServerCrashListener implements MediaPlayer.OnErrorListener {
    public MediaPlayer a;
    public long b = -1;
    public long c;

    public MediaServerCrashListener(long j) {
        this.c = j;
    }

    public static MediaServerCrashListener create(long j) {
        return new MediaServerCrashListener(j);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            N.Mm$QSrAo(this.c, this, true);
            releaseWatchdog();
        }
        return true;
    }

    public void releaseWatchdog() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.release();
        this.a = null;
    }

    public boolean startListening() {
        if (this.a != null) {
            return true;
        }
        try {
            this.a = MediaPlayer.create(Is.a, AbstractC0510an.a);
        } catch (IllegalStateException e) {
            Ts.a("crMediaCrashListener", "Exception while creating the watchdog player.", e);
        } catch (RuntimeException e2) {
            Ts.a("crMediaCrashListener", "Exception while creating the watchdog player.", e2);
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
            Ts.a("crMediaCrashListener", "Unable to create watchdog player, treating it as server crash.", new Object[0]);
            N.Mm$QSrAo(this.c, this, false);
            this.b = jElapsedRealtime;
        }
        return false;
    }
}
