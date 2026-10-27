package i3;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;

/* JADX INFO: compiled from: MediaItemWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(MediaBrowserCompat.MediaItem mediaItem) {
        return e(mediaItem.h().h());
    }

    public static String b(MediaMetadataCompat mediaMetadataCompat) {
        return e(mediaMetadataCompat.j().h());
    }

    public static String c(MediaBrowserCompat.MediaItem mediaItem) {
        String strJ = mediaItem.j();
        return strJ != null ? strJ : "";
    }

    public static String d(MediaBrowserCompat.MediaItem mediaItem) {
        return e(mediaItem.h().m());
    }

    private static String e(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String f(MediaBrowserCompat.MediaItem mediaItem) {
        CharSequence charSequenceP = mediaItem.h().p();
        return !TextUtils.isEmpty(charSequenceP) ? charSequenceP.toString() : "";
    }

    public static String g(MediaBrowserCompat.MediaItem mediaItem) {
        return e(mediaItem.h().q());
    }

    public static boolean h(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundleC = mediaItem.h().c();
        return bundleC != null && bundleC.getLong("android.media.IS_EXPLICIT", 0L) == 1;
    }

    public static boolean i(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2) {
        if (!j(mediaItem, mediaItem2)) {
            return false;
        }
        return TextUtils.equals(g(mediaItem2), g(mediaItem)) && TextUtils.equals(f(mediaItem), f(mediaItem2));
    }

    public static boolean j(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2) {
        return TextUtils.equals(c(mediaItem), c(mediaItem2));
    }
}
