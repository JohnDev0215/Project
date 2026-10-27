package com.amazon.mp3.library.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MediaProvider extends ContentProvider {
    public static final String AUTHORITY = "com.amazon.mp3.Media";
    private static final String TAG = MediaProvider.class.getSimpleName();
    private static MediaProvider sInstance = null;
    private ProviderSourceFactory mProviderSources = null;

    public static abstract class Recent implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + MediaProvider.AUTHORITY + "/nowplaying/recent");
    }

    public static Uri removeTracksSegment(Uri contentUri) {
        return Uri.parse(contentUri.toString().replace("/tracks", ""));
    }

    public static class CirrusBaseColumns implements BaseColumns {

        public static class Source {
            public static final List<Integer> ALL_SOURCES = Collections.unmodifiableList(Arrays.asList(0, 1));

            public static String convertToSourceId(int source) {
                return source == 1 ? "cirrus-local" : "cirrus";
            }

            public static int convertToSource(String str) {
                return "cirrus-local".equals(str) ? 1 : 0;
            }
        }
    }

    public static final class NowPlaying implements BaseColumns {
        public static final Uri CONTENT_URI;
        public static final Uri SHUFFLE_CONTENT_URI;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append("content://");
            String str = MediaProvider.AUTHORITY;
            sb.append(str);
            sb.append("/nowplaying");
            CONTENT_URI = Uri.parse(sb.toString());
            SHUFFLE_CONTENT_URI = Uri.parse("content://" + str + "/nowplaying/shuffled");
        }
    }

    public static abstract class PrimeBrowseTracksCollection {
        private static final String[] PATH_SEGMENTS = {"prime", "browse", "contentSource", PageUriUtils.FILTER_QUERY_PARAM, "tracks"};

        public static Uri getContentUri(ContentSource contentSource, String filter) {
            Uri.Builder builderAuthority = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY);
            String[] strArr = PATH_SEGMENTS;
            return builderAuthority.path(strArr[0]).appendPath(strArr[1]).appendPath(contentSource.toString()).appendPath(filter).appendPath(strArr[4]).build();
        }

        public static ContentSource getContentSource(final Uri contentUri) {
            if (isCollection(contentUri)) {
                return ContentSource.fromString(contentUri.getPathSegments().get(2));
            }
            return null;
        }

        public static boolean isCollection(final Uri contentUri) {
            if (contentUri == null) {
                return false;
            }
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments.size() != PATH_SEGMENTS.length || ContentSource.fromString(pathSegments.get(2)) == null) {
                return false;
            }
            int i = 0;
            while (true) {
                String[] strArr = PATH_SEGMENTS;
                if (i >= strArr.length) {
                    return true;
                }
                if (i != 2 && i != 3 && !strArr[i].equals(pathSegments.get(i))) {
                    return false;
                }
                i++;
            }
        }

        public static abstract class FirstTrack {
            private static final String[] PATH_SEGMENTS = {"prime", "browse", "contentSource", PageUriUtils.FILTER_QUERY_PARAM, "tracks", "firstTrack"};

            public static Uri getContentUri(ContentSource contentSource, String firstTrackAsin) {
                return PrimeBrowseTracksCollection.getContentUri(contentSource, PageUriUtils.FILTER_QUERY_PARAM).buildUpon().appendPath(firstTrackAsin).build();
            }

            public static boolean isCollectionFirstTrack(final Uri contentUri) {
                if (contentUri == null) {
                    return false;
                }
                List<String> pathSegments = contentUri.getPathSegments();
                if (pathSegments.size() != PATH_SEGMENTS.length || ContentSource.fromString(pathSegments.get(2)) == null) {
                    return false;
                }
                int i = 0;
                while (true) {
                    String[] strArr = PATH_SEGMENTS;
                    if (i >= strArr.length) {
                        return true;
                    }
                    if (i != 2 && i != 3 && i != 5 && !strArr[i].equals(pathSegments.get(i))) {
                        return false;
                    }
                    i++;
                }
            }

            @Nullable
            public static String getFirstTrackAsin(Uri contentUri) {
                if (contentUri == null) {
                    return null;
                }
                if (isCollectionFirstTrack(contentUri)) {
                    return contentUri.getPathSegments().get(5);
                }
                Log.warning(MediaProvider.TAG, "Cannot get asin for %s, it is not a Prime Browse Tracks Collection first track", contentUri.toString());
                return null;
            }
        }

        public enum ContentSource {
            ARTIST("artist"),
            NEW_TO_PRIME("newToPrime"),
            POPULAR_IN_PRIME("popularInPrime"),
            RECOMMENDED("recommended"),
            SIMILAR("similar"),
            SEARCH("search");

            private final String mContentSource;

            ContentSource(String contentSource) {
                this.mContentSource = contentSource;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.mContentSource;
            }

            public static final ContentSource fromString(String val) {
                for (ContentSource contentSource : values()) {
                    if (contentSource.mContentSource.equals(val)) {
                        return contentSource;
                    }
                }
                return null;
            }

            public static final ContentSource fromRankType(PaginatedRankType rankType) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[rankType.ordinal()];
                if (i == 1) {
                    return RECOMMENDED;
                }
                if (i == 2) {
                    return SIMILAR;
                }
                if (i == 3 || i == 4) {
                    return NEW_TO_PRIME;
                }
                if (i != 5) {
                    return null;
                }
                return POPULAR_IN_PRIME;
            }
        }
    }

    public static abstract class Tracks extends CirrusBaseColumns {
        public static Uri getContentUri(String source) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/tracks");
        }

        public static Uri getPrimeAdditionalTrackContentUri(String source, String primeSource, String asin) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/tracks/prime/" + primeSource + "/" + asin);
        }

        public static Uri getContentUri(String source, long trackLuid) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/tracks/" + trackLuid);
        }

        public static Uri getContentUri(String source, String trackLuid) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/tracks/" + Uri.encode(trackLuid));
        }

        public static Uri getFilterContentUri(String source, String filter) {
            return MediaProvider.buildFilterContentUri(source, "tracks", Uri.encode(filter));
        }

        public static boolean isTrack(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("tracks");
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean isValidTrackUri(final android.net.Uri r4) {
            /*
                r0 = 0
                if (r4 != 0) goto L4
                return r0
            L4:
                java.util.List r1 = r4.getPathSegments()
                boolean r2 = isTrack(r4)
                r3 = 1
                if (r2 == 0) goto L23
                boolean r2 = isPrimeAdditionalTrack(r4)
                if (r2 == 0) goto L17
                r2 = 5
                goto L18
            L17:
                r2 = 3
            L18:
                java.lang.Object r1 = r1.get(r2)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                goto L34
            L23:
                boolean r2 = com.amazon.mp3.library.provider.MediaProvider.Playlists.isPlaylist(r4)
                if (r2 == 0) goto L36
                r2 = 6
                java.lang.Object r1 = r1.get(r2)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = android.text.TextUtils.isEmpty(r1)
            L34:
                r1 = r1 ^ r3
                goto L37
            L36:
                r1 = r0
            L37:
                if (r1 != 0) goto L46
                java.lang.String r2 = com.amazon.mp3.library.provider.MediaProvider.access$000()
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r3[r0] = r4
                java.lang.String r4 = "Content uri %s is a not track"
                com.amazon.mp3.util.Log.warning(r2, r4, r3)
            L46:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.MediaProvider.Tracks.isValidTrackUri(android.net.Uri):boolean");
        }

        public static boolean isTrackCollection(final Uri contentUri, final String... bulkOperationRequestIds) {
            if (contentUri == null) {
                return false;
            }
            if (bulkOperationRequestIds != null && bulkOperationRequestIds.length > 0) {
                for (String str : bulkOperationRequestIds) {
                    if (str != null && contentUri.toString().equalsIgnoreCase(str)) {
                        return true;
                    }
                }
            }
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments != null) {
                return pathSegments.get(pathSegments.size() - 1).equals("tracks");
            }
            return false;
        }

        public static boolean isPrimeAdditionalTrack(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() > 5 && "tracks".equals(pathSegments.get(2)) && "prime".equals(pathSegments.get(3));
        }

        public static boolean isCatalogTrackInPlaylist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() > 3 && "tracks".equals(pathSegments.get(2)) && PrimePlaylistUtil.isAsin(pathSegments.get(3));
        }

        public static boolean isPrimeAsinLuidTrack(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() != 4) {
                return false;
            }
            return "tracks".equals(pathSegments.get(2)) && pathSegments.get(3).matches("prime-.*[^0-9\\-].*?");
        }

        public static String getPrimeAdditionalTrackAsin(final Uri contentUri) {
            if (contentUri == null) {
                return null;
            }
            if (isPrimeAdditionalTrack(contentUri)) {
                return contentUri.getPathSegments().get(5);
            }
            Log.warning("MediaProvider.Tracks", "Cannot get asin for %s, it is not prime additional track.", contentUri.toString());
            return null;
        }

        public static String getPrimeAsinLuidTrackAsin(final Uri contentUri) {
            if (contentUri == null) {
                return null;
            }
            if (isPrimeAsinLuidTrack(contentUri)) {
                if (contentUri.getPathSegments().size() == 4) {
                    String[] strArrSplit = contentUri.getPathSegments().get(3).split("prime-");
                    if (strArrSplit.length == 2) {
                        return strArrSplit[1];
                    }
                }
            } else {
                Log.warning("MediaProvider.Tracks", "Cannot get asin for %s, it is not prime-asin luid track.", contentUri.toString());
            }
            return null;
        }

        @Nullable
        public static Uri getNonTrackCollectionUri(final Uri tracksUri) {
            if (tracksUri == null || !"tracks".equals(tracksUri.getLastPathSegment())) {
                return null;
            }
            Uri.Builder builderAuthority = new Uri.Builder().scheme(tracksUri.getScheme()).authority(tracksUri.getAuthority());
            for (String str : tracksUri.getPathSegments()) {
                if (!"tracks".equals(str)) {
                    builderAuthority.appendPath(str);
                }
            }
            return builderAuthority.build();
        }

        public static Uri getSanitizedUri(final Uri contentUri) {
            Uri nonTrackCollectionUri = getNonTrackCollectionUri(contentUri);
            return nonTrackCollectionUri != null ? nonTrackCollectionUri : contentUri;
        }
    }

    public static abstract class Albums extends CirrusBaseColumns {
        public static long getAlbumId(final Uri contentUri) {
            List<String> pathSegments;
            if (!isAlbum(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return Long.MIN_VALUE;
            }
            try {
                return Long.parseLong(pathSegments.get(3));
            } catch (NumberFormatException unused) {
                Log.debug(MediaProvider.TAG, "NumberFormatException when parsing albumId");
                return Long.MIN_VALUE;
            }
        }

        public static Uri getContentUri(String source) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/albums");
        }

        public static Uri getContentUri(String source, long albumId) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/albums/" + albumId);
        }

        public static Uri getFilterContentUri(String source, String filter) {
            return MediaProvider.buildFilterContentUri(source, "albums", Uri.encode(filter));
        }

        public static boolean isAlbum(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("albums");
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, long albumId) {
                return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/albums/" + albumId + "/tracks");
            }

            public static boolean isAlbumTracks(final Uri contentUri) {
                List<String> pathSegments;
                return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 5 && Albums.isAlbum(contentUri) && pathSegments.get(4).equals("tracks");
            }

            public static boolean isAllAsinTracksAlbum(final Context context, final Uri contentUri) {
                if (!isAlbumTracks(contentUri)) {
                    return false;
                }
                Cursor cursorQuery = context.getContentResolver().query(contentUri, new String[]{"asin"}, null, null, null);
                if (cursorQuery == null) {
                    return true;
                }
                try {
                    int columnIndex = cursorQuery.getColumnIndex("asin");
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(columnIndex);
                        if (string != null && !string.matches(".*[^0-9\\-].*?")) {
                            return false;
                        }
                    }
                    return true;
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }

            public static boolean isAllLuidTracksAlbum(final Context context, final Uri contentUri) {
                if (!isAlbumTracks(contentUri)) {
                    return false;
                }
                Cursor cursorQuery = context.getContentResolver().query(contentUri, new String[]{"luid"}, null, null, null);
                if (cursorQuery == null) {
                    return true;
                }
                try {
                    int columnIndex = cursorQuery.getColumnIndex("luid");
                    while (cursorQuery.moveToNext()) {
                        if (cursorQuery.getString(columnIndex) == null) {
                            return false;
                        }
                    }
                    return true;
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
        }
    }

    public static class Genres extends CirrusBaseColumns {
        public static long getGenreId(final Uri contentUri) {
            List<String> pathSegments;
            if (!isGenre(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 4) {
                return Long.MIN_VALUE;
            }
            try {
                return Long.parseLong(pathSegments.get(3));
            } catch (NumberFormatException unused) {
                Log.debug(MediaProvider.TAG, "NumberFormatException when parsing genreId");
                return Long.MIN_VALUE;
            }
        }

        public static boolean isGenre(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("genres");
        }

        public static Uri getContentUri(String source, long genreId) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/genres/" + genreId);
        }

        public static Uri getFilterContentUri(String source, String filter) {
            return MediaProvider.buildFilterContentUri(source, "genres", Uri.encode(filter));
        }

        public static Uri getLocalContentUri(String genreId) {
            return MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI.buildUpon().appendPath(genreId).appendPath("members").build();
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, long genreId) {
                return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/genres/" + genreId + "/tracks");
            }
        }
    }

    public static abstract class Artists extends CirrusBaseColumns {
        public static long getArtistId(@Nullable final Uri contentUri) {
            List<String> pathSegments;
            if (!isArtist(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return Long.MIN_VALUE;
            }
            try {
                return Long.parseLong(pathSegments.get(3));
            } catch (NumberFormatException unused) {
                Log.debug(MediaProvider.TAG, "NumberFormatException when parsing artistId");
                return Long.MIN_VALUE;
            }
        }

        public static Uri getContentUri(String source) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/artists");
        }

        public static Uri getFilterContentUri(String source, String filter) {
            return MediaProvider.buildFilterContentUri(source, "artists", Uri.encode(filter));
        }

        public static Uri getContentUri(String source, long artistId) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/artists/" + artistId);
        }

        public static boolean isArtist(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("artists");
        }

        public static abstract class Albums {
            public static Uri getContentUri(String source, long artistId) {
                return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/artists/" + artistId + "/albums");
            }
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, long artistId) {
                return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/artists/" + artistId + "/tracks");
            }

            public static boolean isArtistTracks(final Uri contentUri) {
                List<String> pathSegments;
                return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 5 && Artists.isArtist(contentUri) && pathSegments.get(4).equals("tracks");
            }
        }
    }

    public static class Station {
        public static final Uri CONTENT_URI_BASE = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY).appendPath("library").build();

        public static boolean isStation(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("stations");
        }

        public static boolean isLegacyStation(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 4) {
                return false;
            }
            String str = pathSegments.get(3);
            if (pathSegments.get(2).equals("stations")) {
                return str.equals("GENRE") || str.equals("ARTIST");
            }
            return false;
        }

        public static boolean isStationFromAnything(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 5 && pathSegments.get(2).equals("stations") && pathSegments.get(3).equals("seed");
        }

        public static Uri getContentUri(String key) {
            return getContentUri("cirrus", key);
        }

        private static Uri getContentUri(String source, String key) {
            return CONTENT_URI_BASE.buildUpon().appendPath(source).appendPath("stations").appendPath(key).build();
        }

        public static Uri getContentUriWithSeeds(String seedId) {
            return CONTENT_URI_BASE.buildUpon().appendPath("cirrus").appendPath("stations").appendPath("seed").appendPath(Uri.encode(seedId)).build();
        }

        public static String getStationFromAnythingSeedId(final Uri contentUri) {
            if (isStationFromAnything(contentUri)) {
                return contentUri.getPathSegments().get(4);
            }
            return null;
        }

        public static String getLegacySeedId(final Uri contentUri) {
            List<String> pathSegments;
            return (!isStation(contentUri) || !isLegacyStation(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 5) ? "" : pathSegments.get(4);
        }

        public static String getKey(final Uri contentUri) {
            List<String> pathSegments;
            return (isStation(contentUri) && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4) ? pathSegments.get(3) : "";
        }
    }

    public static class Alexa {
        public static final Uri CONTENT_URI_BASE = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY).appendPath("library").build();

        public static boolean isAlexaCollection(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("alexa");
        }

        public static Uri getContentUri() {
            return CONTENT_URI_BASE.buildUpon().appendPath("cirrus").appendPath("alexa").build();
        }
    }

    public static class AlexaRecentlyPlayed {
        public static final Uri CONTENT_URI_BASE = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY).appendPath("library").build();

        public static boolean isAlexaRecentlyPlayedCollection(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("alexarecents");
        }

        public static Uri getContentUri() {
            return CONTENT_URI_BASE.buildUpon().appendPath("cirrus").appendPath("alexarecents").build();
        }
    }

    public static class Casting {
        public static final Uri CONTENT_URI_BASE = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY).appendPath("library").build();

        public static boolean isCastingCollection(Uri contentUri) {
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("casting");
        }
    }

    public static class CastingRecentlyPlayed {
        public static final Uri CONTENT_URI_BASE = new Uri.Builder().scheme("content").authority(MediaProvider.AUTHORITY).appendPath("library").build();

        public static boolean isCastingRecentlyPlayedCollection(final Uri contentUri) {
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("castingrecents");
        }
    }

    public static abstract class Playlists extends CirrusBaseColumns {

        public enum VisibilityState {
            PRIVATE(0),
            PUBLIC(1),
            NO_STATE(-1);

            private static final Map<Integer, VisibilityState> lookup = new HashMap();
            private final int visibilityStateInt;

            static {
                for (VisibilityState visibilityState : EnumSet.allOf(VisibilityState.class)) {
                    lookup.put(Integer.valueOf(visibilityState.getInt()), visibilityState);
                }
            }

            VisibilityState(final int visibilityStateInt) {
                this.visibilityStateInt = visibilityStateInt;
            }

            public int getInt() {
                return this.visibilityStateInt;
            }

            public String getString() {
                return toString();
            }

            public static VisibilityState fromInt(final int stateInt) {
                Map<Integer, VisibilityState> map = lookup;
                if (map.containsKey(Integer.valueOf(stateInt))) {
                    return map.get(Integer.valueOf(stateInt));
                }
                return NO_STATE;
            }

            public static VisibilityState fromString(final String value) {
                try {
                    return valueOf(value);
                } catch (IllegalArgumentException unused) {
                    return NO_STATE;
                }
            }

            private String getLocalizedText() {
                int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[ordinal()];
                if (i != 1) {
                    return i != 2 ? "" : AmazonApplication.getApplication().getString(R.string.dmusic_playlist_visibility_private_text);
                }
                return AmazonApplication.getApplication().getString(R.string.dmusic_playlist_visibility_public_text);
            }

            public String getInitialSubtitleTextWithSeparator() {
                return (this != NO_STATE && Feature.playlists_privacy_enforcement_privacy_status.isEnabled()) ? String.format("%s • ", getLocalizedText()) : "";
            }
        }

        public static long getPlaylistId(final Uri contentUri) {
            List<String> pathSegments;
            if (!isPlaylist(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 5) {
                return Long.MIN_VALUE;
            }
            try {
                return Long.parseLong(pathSegments.get(4));
            } catch (NumberFormatException unused) {
                Log.debug(MediaProvider.TAG, "NumberFormatException when parsing playlistId");
                return Long.MIN_VALUE;
            }
        }

        public static String getPlaylistLuid(Uri contentUri) {
            if (UdoPlaylistTracks.isUdoPlaylistTracks(contentUri)) {
                contentUri = extractPlaylistUri(contentUri);
            }
            Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(contentUri);
            if (playlist != null) {
                return playlist.getLuid();
            }
            return null;
        }

        public static Uri extractPlaylistUri(final Uri udoPlaylistTracksUri) {
            return MediaProvider.createUriFromStringSegs(udoPlaylistTracksUri.getPathSegments().subList(0, 5));
        }

        public static Uri getContentUri(String source) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/playlists");
        }

        public static Uri getFilterContentUri(String source, String filter) {
            return MediaProvider.buildFilterContentUri(source, "playlists", Uri.encode(filter));
        }

        public static boolean isPlaylist(final Uri contentUri) {
            List<String> pathSegments;
            if (contentUri == null || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() < 3) {
                return false;
            }
            return pathSegments.get(2).equals("playlists");
        }

        public static Uri getCatalogContentUri(final Cursor c, final String sourceId, final String asin) {
            if (SharedUserPlaylists.isSharedUserPlaylist(c) || EntityPlaylists.isEntityPlaylist(c)) {
                return SharedUserPlaylists.getContentUri(sourceId, asin);
            }
            return PrimePlaylists.getContentUri(sourceId, asin);
        }

        public static String getCatalogPlaylistId(final Uri contentUri) {
            if (SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
                return SharedUserPlaylists.getPlaylistKey(contentUri);
            }
            return PrimePlaylists.isPrimePlaylist(contentUri) ? PrimePlaylists.getPlaylistAsin(contentUri) : "";
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.MediaProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType;

        static {
            int[] iArr = new int[Playlists.VisibilityState.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState = iArr;
            try {
                iArr[Playlists.VisibilityState.PUBLIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[Playlists.VisibilityState.PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[PaginatedRankType.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType = iArr2;
            try {
                iArr2[PaginatedRankType.RECOMMENDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.SIMILAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.NEWLY_RELEASED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.NEWLY_ADDED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedRankType[PaginatedRankType.POPULAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static abstract class UdoPlaylists extends Playlists {
        public static final Uri getContentUri(String sourceId) {
            return Playlists.getContentUri(sourceId).buildUpon().appendEncodedPath("udo").build();
        }

        public static Uri getContentUri(String source, long id) {
            return getContentUri(source).buildUpon().appendEncodedPath(String.valueOf(id)).build();
        }

        public static Uri getContentUri(Context context, String sourceId, String name) {
            Cursor cursorQuery = context.getContentResolver().query(getContentUri(sourceId), new String[]{"_id", "name"}, "name=?", new String[]{name}, null);
            try {
                if (cursorQuery == null) {
                    Log.verbose(MediaProvider.TAG, "The cursor is null for " + sourceId + " and playlist: " + name);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return null;
                }
                Uri contentUri = getContentUri(sourceId, cursorQuery.getLong(0));
                cursorQuery.close();
                return contentUri;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        public static Uri getContentUriFromLuid(Context context, String sourceId, String luid) {
            Cursor cursorQuery = context.getContentResolver().query(getContentUri(sourceId), new String[]{"_id", "Playlist.luid"}, "Playlist.luid=?", new String[]{luid}, null);
            try {
                if (cursorQuery == null) {
                    Log.verbose(MediaProvider.TAG, "The cursor is null for " + sourceId + " and playlist: " + luid);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                if (!cursorQuery.moveToFirst()) {
                    cursorQuery.close();
                    return null;
                }
                Uri contentUri = getContentUri(sourceId, cursorQuery.getLong(0));
                cursorQuery.close();
                return contentUri;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        public static String getLuid(final Context context, final String pid) {
            Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(context).rawQuery("SELECT luid FROM Playlist WHERE _id = ?", new String[]{pid});
            try {
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        return cursorRawQuery.getString(0);
                    }
                } catch (Exception e) {
                    Log.error(MediaProvider.TAG, "Failed to get luid by _id: " + pid, e);
                }
                return null;
            } finally {
                DbUtil.closeCursor(cursorRawQuery);
            }
        }

        public static boolean isUdoPlaylist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && Playlists.isPlaylist(contentUri) && pathSegments.get(3).equals("udo");
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, long id) {
                return UdoPlaylists.getContentUri(source, id).buildUpon().appendEncodedPath("tracks").build();
            }

            public static Uri appendTracksToUri(final Uri udoPlaylistUri) {
                if (udoPlaylistUri == null) {
                    return null;
                }
                return udoPlaylistUri.buildUpon().appendEncodedPath("tracks").build();
            }
        }
    }

    public static abstract class PrimeArtists extends Artists {
        public static final Uri getContentUri(String source, long artistId) {
            if (Long.MIN_VALUE == artistId) {
                return null;
            }
            return Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source + "/artists/prime/" + artistId);
        }

        public static boolean isPrimeArtist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && Artists.isArtist(contentUri) && pathSegments.get(3).equals("prime");
        }

        public static long getArtistId(final Uri contentUri) {
            List<String> pathSegments;
            if (!isPrimeArtist(contentUri) || (pathSegments = contentUri.getPathSegments()) == null || pathSegments.size() <= 4) {
                return Long.MIN_VALUE;
            }
            try {
                return Long.parseLong(pathSegments.get(4));
            } catch (NumberFormatException unused) {
                Log.debug(MediaProvider.TAG, "NumberFormatException when parsing artistId");
                return Long.MIN_VALUE;
            }
        }
    }

    public static abstract class PrimePlaylists extends Playlists {
        public static final Uri getContentUri(String sourceId) {
            return Playlists.getContentUri(sourceId).buildUpon().appendEncodedPath("prime").build();
        }

        public static Uri getContentUri(String source, String asin) {
            return getContentUri(source).buildUpon().appendEncodedPath(asin).build();
        }

        public static Uri getContentUri(String source, long id) {
            return getContentUri(source).buildUpon().appendEncodedPath(String.valueOf(id)).build();
        }

        public static boolean isPrimePlaylist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && Playlists.isPlaylist(contentUri) && pathSegments.get(3).equals("prime");
        }

        public static String getPlaylistAsin(final Uri contentUri) {
            List<String> pathSegments;
            return (isPrimePlaylist(contentUri) && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 5) ? pathSegments.get(4) : "";
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, String asin) {
                return PrimePlaylists.getContentUri(source, asin).buildUpon().appendEncodedPath("tracks").build();
            }
        }
    }

    public static abstract class SharedUserPlaylists extends Playlists {
        public static final Uri getContentUri(String sourceId) {
            return Playlists.getContentUri(sourceId).buildUpon().appendEncodedPath("shareduserplaylist").build();
        }

        public static Uri getContentUri(String source, String playlistKey) {
            return getContentUri(source).buildUpon().appendEncodedPath(playlistKey).build();
        }

        public static boolean isSharedUserPlaylist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && Playlists.isPlaylist(contentUri) && pathSegments.get(3).equals("shareduserplaylist");
        }

        public static boolean isSharedUserPlaylist(Cursor c) {
            int columnIndex = c.getColumnIndex("is_shared_user_playlist");
            if (columnIndex != -1) {
                return "1".equals(c.getString(columnIndex));
            }
            return false;
        }

        public static boolean isSharedUserPlaylist(final Context context, final String id) {
            Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(context).rawQuery("SELECT _id FROM PrimePlaylists WHERE is_shared_user_playlist = '1' AND luid = '" + id + "'", null);
            try {
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        return true;
                    }
                } catch (Exception e) {
                    Log.error(MediaProvider.TAG, "Failed to get shared user playlist by id: " + id, e);
                }
                return false;
            } finally {
                DbUtil.closeCursor(cursorRawQuery);
            }
        }

        public static String getPlaylistKey(final Uri contentUri) {
            List<String> pathSegments;
            return (isSharedUserPlaylist(contentUri) && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4) ? pathSegments.get(4) : "";
        }
    }

    public static abstract class SmartPlaylists extends Playlists {
        public static Uri getContentUri(String source, long id) {
            return Uri.parse("content://" + MediaProvider.AUTHORITY + JsonPointer.SEPARATOR + "library" + JsonPointer.SEPARATOR + source + "/playlists/smart" + JsonPointer.SEPARATOR + String.valueOf(id));
        }

        public static Uri.Builder setSmartPlaylistsToQuery(Uri.Builder toDecorate, EnumSet<AvailableSmartPlaylists> playlistsToShow) {
            if (playlistsToShow.isEmpty()) {
                return toDecorate;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<AvailableSmartPlaylists> it = playlistsToShow.iterator();
            while (it.hasNext()) {
                sb.append(it.next().mParamValue);
                sb.append(',');
            }
            sb.setLength(sb.length() - 1);
            toDecorate.appendQueryParameter("smartPlaylistsToShow", sb.toString());
            return toDecorate;
        }

        public static boolean isSmartPlaylist(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && Playlists.isPlaylist(contentUri) && pathSegments.get(3).equals("smart");
        }

        public static long getPlaylistId(final Uri contentUri) {
            if (!isSmartPlaylist(contentUri)) {
                return Long.MIN_VALUE;
            }
            List<String> pathSegments = contentUri.getPathSegments();
            if (pathSegments.size() >= 4) {
                return Long.parseLong(pathSegments.get(4));
            }
            return Long.MIN_VALUE;
        }

        public static long queryValueToDatabaseValue(String mediaProviderParamValue) throws IllegalArgumentException {
            mediaProviderParamValue.hashCode();
            switch (mediaProviderParamValue) {
                case "purchased":
                    return 0L;
                case "addedToCloud":
                    return 1L;
                case "recentlyPlayedTracks":
                    return 3L;
                case "addedToDevice":
                    return 2L;
                default:
                    throw new IllegalArgumentException("Unknown smartplaylist type");
            }
        }

        public enum AvailableSmartPlaylists {
            LATEST_PURCHASES("purchased"),
            LATESET_UPLOADS("addedToCloud"),
            RECENTLY_ADDED("addedToDevice"),
            RECENTLY_PLAYED_TRACKS("recentlyPlayedTracks");

            final String mParamValue;

            AvailableSmartPlaylists(String playlistId) {
                this.mParamValue = playlistId;
            }
        }

        public static abstract class Tracks {
            public static Uri getContentUri(String source, long id) {
                return SmartPlaylists.getContentUri(source, id).buildUpon().appendEncodedPath("tracks").build();
            }
        }
    }

    public static abstract class EntityPlaylists extends Playlists {
        public static boolean isEntityPlaylist(Cursor c) {
            int columnIndex = c.getColumnIndex("is_entity_playlist");
            if (columnIndex != -1) {
                return "1".equals(c.getString(columnIndex));
            }
            return false;
        }

        public static boolean isEntityPlaylist(final Context context, final String id) {
            Cursor cursorRawQuery = CirrusDatabase.getReadOnlyDatabase(context).rawQuery("SELECT _id FROM PrimePlaylists WHERE is_entity_playlist = '1' AND luid = '" + id + "'", null);
            try {
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        return true;
                    }
                } catch (Exception e) {
                    Log.error(MediaProvider.TAG, "Failed to get entity playlist by id: " + id, e);
                }
                return false;
            } finally {
                DbUtil.closeCursor(cursorRawQuery);
            }
        }
    }

    public static abstract class UdoPlaylistTracks implements BaseColumns {
        public static Uri getContentUri(String source, long playlistId, long trackId) {
            return UdoPlaylists.getContentUri(source, playlistId).buildUpon().appendEncodedPath("tracks/").appendEncodedPath(String.valueOf(trackId)).build();
        }

        public static Uri getContentUri(String source, long playlistId) {
            return UdoPlaylists.getContentUri(source, playlistId).buildUpon().appendEncodedPath("tracks").build();
        }

        public static Uri getContentUriFromLuid(Context context, String sourceId, String playlistLuid) {
            return UdoPlaylists.getContentUriFromLuid(context, sourceId, playlistLuid).buildUpon().appendEncodedPath("tracks").build();
        }

        public static boolean isUdoPlaylistTracks(final Uri contentUri) {
            List<String> pathSegments;
            return contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() > 5 && Playlists.isPlaylist(contentUri) && pathSegments.get(5).equals("tracks");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (Station.isStation(uri)) {
            Context context = getContext();
            int iDelete = StationStorageUtil.delete(context, selection, selectionArgs);
            context.getContentResolver().notifyChange(uri, null);
            return iDelete;
        }
        return getProviderSourceOrThrow(uri).delete(uri, selection, selectionArgs);
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (Station.isStation(uri)) {
            Context context = getContext();
            StationStorageUtil.insert(context, values);
            context.getContentResolver().notifyChange(uri, null);
            return values.length;
        }
        return getProviderSourceOrThrow(uri).bulkInsert(uri, values);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        if (Station.isStation(uri)) {
            Context context = getContext();
            StationStorageUtil.insert(context, values);
            context.getContentResolver().notifyChange(uri, null);
            return uri;
        }
        return getProviderSourceOrThrow(uri).insert(uri, values);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (Station.isStation(uri)) {
            Context context = getContext();
            int iUpdate = StationStorageUtil.update(context, values, selection, selectionArgs);
            context.getContentResolver().notifyChange(uri, null);
            return iUpdate;
        }
        return getProviderSourceOrThrow(uri).update(uri, values, selection, selectionArgs);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (Station.isStation(uri)) {
            return StationStorageUtil.query(getContext(), projection, selection, selectionArgs, sortOrder);
        }
        return getProviderSourceOrThrow(uri).query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mProviderSources = ProviderSourceFactory.getInstance(getContext());
        setInstance(this);
        return true;
    }

    private static synchronized void setInstance(MediaProvider instance) {
        sInstance = instance;
    }

    public static synchronized MediaProvider getInstance() {
        return sInstance;
    }

    public static boolean isFilterable(final Uri contentUri) {
        List<String> pathSegments;
        if (contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && pathSegments.size() >= 4 && pathSegments.get(2).equals(PageUriUtils.FILTER_QUERY_PARAM) && pathSegments.size() == 5) {
            return !TextUtils.isEmpty(pathSegments.get(4));
        }
        return false;
    }

    public static boolean isSmartPlaylist(Uri uri) {
        return uri.getPathSegments().get(3).equals("smart");
    }

    public static String getSource(Uri uri) {
        if (uri == null) {
            return "";
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.get(0).equals("library")) {
            return pathSegments.get(1);
        }
        return pathSegments.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri createUriFromStringSegs(final List<String> segs) {
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        sb.append(AUTHORITY);
        for (int i = 0; i < segs.size(); i++) {
            sb.append(JsonPointer.SEPARATOR);
            sb.append(segs.get(i));
        }
        return Uri.parse(sb.toString());
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        getProviderSourceOrThrow(uri);
        return DefaultUriMatcher.getType(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri buildFilterContentUri(String source, String type, String filter) {
        String str = "content://" + AUTHORITY + "/library/" + source + "/" + PageUriUtils.FILTER_QUERY_PARAM + "/" + type;
        if (filter != null && filter.length() > 0) {
            str = str + "/" + filter;
        }
        return Uri.parse(str);
    }

    private ProviderSource getProviderSourceOrThrow(Uri uri) {
        ProviderSource source = this.mProviderSources.getSource(uri.getPathSegments().get(uri.getPathSegments().get(0).equals("library") ? 1 : 0));
        if (source != null) {
            return source;
        }
        throw new IllegalArgumentException("Provider source for: " + uri.toString() + " is invalid");
    }

    public static boolean isAsin(final String input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        return input.matches(".*[^0-9\\-].*?");
    }
}
