package i3;

import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: compiled from: PlaybackStateCompatUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class u {
    public static Long a(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return 0L;
        }
        if (playbackStateCompat.t() != 3) {
            return Long.valueOf(playbackStateCompat.s());
        }
        return Long.valueOf(Float.valueOf(playbackStateCompat.s() + ((SystemClock.elapsedRealtime() - playbackStateCompat.q()) * playbackStateCompat.r())).longValue());
    }
}
