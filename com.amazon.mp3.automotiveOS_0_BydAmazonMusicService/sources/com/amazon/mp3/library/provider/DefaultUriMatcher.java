package com.amazon.mp3.library.provider;

import android.content.UriMatcher;
import android.net.Uri;
import java.security.InvalidParameterException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultUriMatcher {
    private static final HashMap<Integer, String> sMatchTypes;
    private static final UriMatcher sUriMatcher;

    static {
        String str = MediaProvider.AUTHORITY;
        UriMatcher uriMatcher = new UriMatcher(-1);
        sUriMatcher = uriMatcher;
        uriMatcher.addURI(str, "library/*/filter", 1);
        uriMatcher.addURI(str, "library/*/filter/tracks", 1);
        uriMatcher.addURI(str, "library/*/filter/tracks/*", 1);
        uriMatcher.addURI(str, "library/*/filter/albums", 3);
        uriMatcher.addURI(str, "library/*/filter/albums/*", 3);
        uriMatcher.addURI(str, "library/*/filter/artists", 7);
        uriMatcher.addURI(str, "library/*/filter/artists/*", 7);
        uriMatcher.addURI(str, "library/*/filter/playlists", 11);
        uriMatcher.addURI(str, "library/*/filter/playlists/*", 11);
        uriMatcher.addURI(str, "library/*/filter/genres", 24);
        uriMatcher.addURI(str, "library/*/filter/genres/*", 24);
        uriMatcher.addURI(str, "library/*/filter/udo_playlists/udo", 11);
        uriMatcher.addURI(str, "library/*/filter/udo_playlists/udo/*", 11);
        uriMatcher.addURI(str, "library/*/tracks", 1);
        uriMatcher.addURI(str, "library/*/tracks/#", 2);
        uriMatcher.addURI(str, "library/*/tracks/prime/cta/*", 2);
        uriMatcher.addURI(str, "library/*/tracks/prime/primeplaylist/*", 2);
        uriMatcher.addURI(str, "library/*/tracks/*", 2);
        uriMatcher.addURI(str, "library/*/albums", 3);
        uriMatcher.addURI(str, "library/*/albums/#", 4);
        uriMatcher.addURI(str, "library/*/albums/#/tracks", 5);
        uriMatcher.addURI(str, "library/*/artists", 7);
        uriMatcher.addURI(str, "library/*/artists/prime", 40);
        uriMatcher.addURI(str, "library/*/artists/#", 8);
        uriMatcher.addURI(str, "library/*/artists/prime/#", 39);
        uriMatcher.addURI(str, "library/*/artists/#/tracks", 9);
        uriMatcher.addURI(str, "library/*/artists/prime/#/tracks", 39);
        uriMatcher.addURI(str, "library/*/artists/*/albums", 10);
        uriMatcher.addURI(str, "library/*/artists/#/albums", 10);
        uriMatcher.addURI(str, "library/*/genres", 24);
        uriMatcher.addURI(str, "library/*/genres/#", 25);
        uriMatcher.addURI(str, "library/*/genres/#/tracks", 26);
        uriMatcher.addURI(str, "library/*/genres/#/albums", 27);
        uriMatcher.addURI(str, "library/*/playlists/", 11);
        uriMatcher.addURI(str, "library/*/playlists/#", 12);
        uriMatcher.addURI(str, "library/*/playlists/#/*", 12);
        uriMatcher.addURI(str, "library/*/playlists/udo", 11);
        uriMatcher.addURI(str, "library/*/playlists/udo/#/", 14);
        uriMatcher.addURI(str, "library/*/playlists/udo/#/tracks", 16);
        uriMatcher.addURI(str, "library/*/playlists/udo/#/tracks/#", 17);
        uriMatcher.addURI(str, "library/*/playlists/smart", 11);
        uriMatcher.addURI(str, "library/*/playlists/smart/#/", 13);
        uriMatcher.addURI(str, "library/*/playlists/smart/#/tracks", 15);
        uriMatcher.addURI(str, "library/*/playlists/prime/*", 28);
        uriMatcher.addURI(str, "library/*/playlists/prime/*/tracks", 28);
        uriMatcher.addURI(str, "library/*/playlists/prime", 29);
        uriMatcher.addURI(str, "library/*/playlists/shareduserplaylist/*", 33);
        uriMatcher.addURI(str, "library/*/playlists/shareduserplaylist/*/tracks", 33);
        uriMatcher.addURI(str, "library/*/playlists/shareduserplaylist", 34);
        uriMatcher.addURI(str, "prime/browse/*/tracks", 31);
        uriMatcher.addURI(str, "library/*/stations/*", 32);
        uriMatcher.addURI(str, "library/*/alexa", 35);
        uriMatcher.addURI(str, "library/*/alexarecents", 36);
        uriMatcher.addURI(str, "library/*/casting", 37);
        uriMatcher.addURI(str, "library/*/castingrecents", 38);
        uriMatcher.addURI(str, "nowplaying", 18);
        uriMatcher.addURI(str, "nowplaying/#", 19);
        uriMatcher.addURI(str, "nowplaying/shuffled", 18);
        uriMatcher.addURI(str, "nowplaying/shuffled/#", 19);
        uriMatcher.addURI(str, "nowplaying/recent", 20);
        uriMatcher.addURI(str, "library/*/search_suggest_query/*", 21);
        HashMap<Integer, String> map = new HashMap<>();
        sMatchTypes = map;
        map.put(1, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(9, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(5, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(15, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(2, "vnd.android.cursor.item/vnd.amazonmp3.track");
        map.put(3, "vnd.android.cursor.dir/vnd.amazonmp3.album");
        map.put(10, "vnd.android.cursor.dir/vnd.amazonmp3.album");
        map.put(4, "vnd.android.cursor.item/vnd.amazonmp3.album");
        map.put(7, "vnd.android.cursor.dir/vnd.amazonmp3.artist");
        map.put(40, "vnd.android.cursor.item/vnd.amazonmp3.primeartist");
        map.put(39, "vnd.android.cursor.item/vnd.amazonmp3.primeartist");
        map.put(8, "vnd.android.cursor.item/vnd.amazonmp3.artist");
        map.put(11, "vnd.android.cursor.dir/vnd.amazonmp3.playlists");
        map.put(13, "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist");
        map.put(14, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
        map.put(16, "vnd.android.cursor.dir/vnd.amazonmp3.udoplaylisttrack");
        map.put(18, "vnd.android.cursor.dir/vnd.amazonmp3.nowplaying");
        map.put(21, "vnd.android.cursor.dir/vnd.android.search.suggest");
        map.put(20, "vnd.android.cursor.dir/vnd.amazonmp3.recent");
        map.put(24, "vnd.android.cursor.dir/vnd.amazonmp3.genre");
        map.put(25, "vnd.android.cursor.item/vnd.amazonmp3.genre");
        map.put(26, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(27, "vnd.android.cursor.dir/vnd.amazonmp3.album");
        map.put(28, "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist");
        map.put(29, "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist");
        map.put(33, "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist");
        map.put(32, "vnd.android.cursor.item/vnd.amazonmp3.primestation");
        map.put(35, "vnd.android.cursor.item/vnd.amazonmp3.alexa");
    }

    public static int match(Uri uri) {
        return sUriMatcher.match(uri);
    }

    public static String getType(Uri uri) {
        if (uri == null) {
            throw new InvalidParameterException("Uri cannot be null");
        }
        String str = sMatchTypes.get(Integer.valueOf(sUriMatcher.match(uri)));
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    private DefaultUriMatcher() {
    }
}
