package i3;

import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;

/* JADX INFO: compiled from: MetadataWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class t {
    public static String a(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.ALBUM"));
    }

    public static String b(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.ALBUM_ART_URI"));
    }

    public static String c(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.ART_URI"));
    }

    public static String d(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.ARTIST"));
    }

    public static String e(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.DISPLAY_ICON_URI"));
    }

    public static int f(MediaMetadataCompat mediaMetadataCompat) {
        return (int) mediaMetadataCompat.k("android.media.metadata.DURATION");
    }

    public static String g(MediaMetadataCompat mediaMetadataCompat) {
        return i(mediaMetadataCompat.m("android.media.metadata.MEDIA_ID"));
    }

    public static String h(MediaMetadataCompat mediaMetadataCompat) {
        MediaDescriptionCompat mediaDescriptionCompatJ;
        return (mediaMetadataCompat == null || (mediaDescriptionCompatJ = mediaMetadataCompat.j()) == null) ? "" : i(mediaDescriptionCompatJ.m());
    }

    private static String i(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static String j(MediaMetadataCompat mediaMetadataCompat) {
        String strM = mediaMetadataCompat.m("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(strM)) {
            strM = mediaMetadataCompat.m("android.media.metadata.TITLE");
        }
        return i(strM);
    }

    public static String k(MediaMetadataCompat mediaMetadataCompat) {
        String strE = e(mediaMetadataCompat);
        if (!TextUtils.isEmpty(strE)) {
            return strE;
        }
        String strC = c(mediaMetadataCompat);
        return !TextUtils.isEmpty(strC) ? strC : b(mediaMetadataCompat);
    }
}
