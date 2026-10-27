package com.amazon.mp3.util;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class IdGenerator {
    private static final String TAG = "IdGenerator";

    public static long generateArtistId(String artist) {
        if (artist == null) {
            Log.warning(TAG, "The artist is null. Returning an invalid artistId.");
            return 2147483647L;
        }
        return ((long) artist.toLowerCase(Locale.US).hashCode()) + 2147483647L;
    }

    public static long generateAlbumId(String albumAsin, String album) {
        if (albumAsin == null || album == null) {
            return 2147483647L;
        }
        Locale locale = Locale.US;
        return ((((long) album.toLowerCase(locale).hashCode()) + 2147483647L) * 31) + ((long) albumAsin.toLowerCase(locale).hashCode()) + 2147483647L + 2147483647L;
    }

    public static long generateGenreId(String genre) {
        if (TextUtils.isEmpty(genre)) {
            genre = DefaultStringType.GENRE.getDefault();
        }
        return ((long) genre.toLowerCase(Locale.US).hashCode()) + 2147483647L;
    }

    public static String generatePrimeLuid(String asin) {
        return "prime-" + asin;
    }

    public static boolean isPrimeLuid(String luid) {
        return luid != null && luid.startsWith("prime-");
    }

    public static boolean isLocalLuid(String luid) {
        return luid != null && luid.startsWith("local-");
    }

    public static long generatePlaylistId(final String luid) {
        return ((long) luid.hashCode()) + 2147483647L;
    }
}
