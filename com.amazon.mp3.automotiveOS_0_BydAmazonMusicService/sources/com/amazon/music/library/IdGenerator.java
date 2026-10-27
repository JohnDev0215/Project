package com.amazon.music.library;

import com.amazon.mp3.environment.Environment;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class IdGenerator {
    static final String ID_SEPARATOR = Character.toString(31);
    public static final String ADDED = generateSmartPlaylistId(Playlist$Smart.Added);
    public static final String PURCHASED = generateSmartPlaylistId(Playlist$Smart.Purchased);

    private IdGenerator() {
    }

    public static String getCirrusIdFromOwnedPlaylistId(String str) {
        return getPartsForId(Environment.PLAYLIST_PATH, str, 2)[1];
    }

    public static String generateOwnedPlaylistId(String str) {
        return generateIdFromParts(Environment.PLAYLIST_PATH, str);
    }

    static String generateSmartPlaylistId(Playlist$Smart playlist$Smart) {
        return generateIdFromParts("smart", playlist$Smart.name());
    }

    private static boolean hasPrefix(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        String str3 = ID_SEPARATOR;
        return str2.contains(str3) && str2.split(str3)[0].equals(str);
    }

    private static String generateIdFromParts(String str, String... strArr) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : strArr) {
            sb.append(ID_SEPARATOR);
            sb.append(str2);
        }
        return sb.toString();
    }

    private static String[] getPartsForId(String str, String str2, int i) throws UnsupportedOperationException {
        if (StringUtils.isEmpty(str2)) {
            throw new UnsupportedOperationException("id was null.");
        }
        String[] strArrSplit = str2.split(ID_SEPARATOR);
        if (strArrSplit.length != i) {
            throw new UnsupportedOperationException(String.format("id was unrecognizable: %s", str2));
        }
        if (hasPrefix(str, str2)) {
            return strArrSplit;
        }
        throw new UnsupportedOperationException(String.format("id was not a valid %s: %s", str, str2));
    }
}
