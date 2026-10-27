package com.amazon.mp3.library.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.playback.service.PlaybackService;
import com.amazon.mp3.util.CompositeImageGenerator;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.FileCache;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonPointer;
import com.visualon.OSMPUtils.voOSType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class ExternalProvider extends ContentProvider {
    public static final Uri AUTHORITY_URI;
    private static final Columns[] DEFAULT_RECENT_PROJECTION;
    private static final Columns[] DEFAULT_RECENT_SEARCH_PROJECTION;
    public static final Uri IMAGE_BASE;
    private static final String[] INTERAL_RECENT_PROJECTION;
    private static final String[] INTERNAL_ARTISTS_SEARCH_PROJECTION;
    private static final String[] INTERNAL_PLAYLISTS_SEARCH_PROJECTION;
    private static final String[] INTERNAL_SEARCH_PROJECTION;
    private static final String[] INTERNAL_TRACKS_SEARCH_PROJECTION;
    public static final Uri NOWPLAYING_URI;
    public static final Uri RECENT_BASE;
    private static final Columns[] SEARCH_RESULTS_PROJECTION;
    private static final String TAG = ExternalProvider.class.getSimpleName();
    public static final Uri THUMBNAIL_BASE;
    private static HashMap<String, Columns> sColumnsMap;
    private static final FileCache sFileCache;
    private static final CompositeImageGenerator sImageGenerator;
    private static final HashMap<Integer, String> sMatchTypes;
    private static final UriMatcher sUriMatcher;
    private CacheManager mCacheManager = null;
    private final long mPlaybackStartTime = 0;
    private final Executor mExecutor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final HashSet<Uri> mPendingUris = new HashSet<>();

    interface Skippable {
        boolean shouldSkip(CursorWalker walker);
    }

    private Cursor transformLibraryCursor(Cursor sourceCursor, String[] projection) {
        return sourceCursor;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    static {
        Uri uri = Uri.parse("content://com.amazon.mp3.content");
        AUTHORITY_URI = uri;
        THUMBNAIL_BASE = uri.buildUpon().appendPath("thumbnail").build();
        IMAGE_BASE = uri.buildUpon().appendPath(ContextMappingConstants.IMAGE).build();
        RECENT_BASE = uri.buildUpon().appendPath("recent").build();
        NOWPLAYING_URI = uri.buildUpon().appendPath("nowplaying").build();
        sImageGenerator = new CompositeImageGenerator(0.5f, 1.0f, 46.0f, 30.0f, 46.0f, 84.0f, 50, -1);
        sColumnsMap = new HashMap<>();
        sFileCache = new FileCache("External", 100, -2147483648L);
        INTERNAL_SEARCH_PROJECTION = new String[]{"_id", "title", "artist", "album_art_id"};
        INTERNAL_TRACKS_SEARCH_PROJECTION = new String[]{"_id", "luid", "title", "artist", "album_art_id", "album_id", "album"};
        INTERNAL_PLAYLISTS_SEARCH_PROJECTION = new String[]{"_id", "name"};
        INTERNAL_ARTISTS_SEARCH_PROJECTION = new String[]{"_id", "name"};
        Columns columns = Columns.ARTIST;
        Columns columns2 = Columns.LAST_ACCESSED;
        INTERAL_RECENT_PROJECTION = new String[]{"id", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "name", PageUriUtils.SOURCE_QUERY_PARAM, columns.mColumn, columns2.mColumn};
        Columns columns3 = Columns.KEY;
        Columns columns4 = Columns.COUNT;
        Columns columns5 = Columns.AVAILABLE;
        Columns columns6 = Columns.THUMBNAIL_URI;
        Columns columns7 = Columns.IMAGE_URI;
        Columns columns8 = Columns.VIEW_INTENT_URI;
        Columns columns9 = Columns.TYPE;
        Columns columns10 = Columns.TITLE;
        DEFAULT_RECENT_PROJECTION = new Columns[]{columns3, columns4, columns2, columns5, columns6, columns7, columns8, columns9, columns10, columns};
        Columns columns11 = Columns.SUGGEST_COLUMN_TEXT_1;
        Columns columns12 = Columns.SUGGEST_COLUMN_TEXT_2;
        Columns columns13 = Columns.SUGGEST_COLUMN_ICON_1;
        Columns columns14 = Columns.SUGGEST_COLUMN_INTENT_ACTION;
        Columns columns15 = Columns.SUGGEST_COLUMN_INTENT_DATA;
        Columns columns16 = Columns.SUGGEST_COLUMN_SHORTCUT_ID;
        Columns columns17 = Columns.SUGGESTION_INDEX;
        DEFAULT_RECENT_SEARCH_PROJECTION = new Columns[]{columns3, columns4, columns2, columns5, columns6, columns7, columns8, columns9, columns10, columns, columns11, columns12, columns13, columns14, columns15, columns16, columns17};
        SEARCH_RESULTS_PROJECTION = new Columns[]{columns4, columns3, columns11, columns12, columns13, columns14, columns15, columns16, columns17};
        UriMatcher uriMatcher = new UriMatcher(-1);
        sUriMatcher = uriMatcher;
        uriMatcher.addURI("com.amazon.mp3.content", "app_state", 24);
        uriMatcher.addURI("com.amazon.mp3.content", "recent", 1);
        uriMatcher.addURI("com.amazon.mp3.content", "recent/*", 2);
        uriMatcher.addURI("com.amazon.mp3.content", "thumbnail/*", 3);
        uriMatcher.addURI("com.amazon.mp3.content", "image/*", 4);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/tracks", 10);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/tracks/*", 11);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/albums", 12);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/albums/*", 13);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/albums/*/tracks", 14);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists", 15);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists/*", 16);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists/*/albums", 17);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists/*/albums/*", 18);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists/*/albums/*/tracks", 19);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/artists/*/tracks", 20);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/playlists", 21);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/playlists/*", 22);
        uriMatcher.addURI("com.amazon.mp3.content", "library/*/playlists/*/tracks", 23);
        uriMatcher.addURI("com.amazon.mp3.content", "search_suggest_query", 6);
        uriMatcher.addURI("com.amazon.mp3.content", "search_suggest_query/*", 6);
        uriMatcher.addURI("com.amazon.mp3.content", "search_suggest_shortcut", 7);
        uriMatcher.addURI("com.amazon.mp3.content", "search_suggest_shortcut/*", 7);
        uriMatcher.addURI("com.amazon.mp3.content", "recent/nowplaying", 8);
        uriMatcher.addURI("com.amazon.mp3.content", "nowplaying", 8);
        HashMap<Integer, String> map = new HashMap<>();
        sMatchTypes = map;
        map.put(1, "vnd.android.cursor.dir/vnd.amazon.mp3.recent");
        map.put(3, "vnd.android.cursor.item/vnd.amazonmp3.artwork");
        map.put(4, "vnd.android.cursor.item/vnd.amazonmp3.artwork");
        map.put(2, "vnd.android.cursor.item/vnd.amazon.mp3.recent");
        map.put(9, "vnd.android.cursor.item/vnd.amazon.mp3.recent");
        map.put(10, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(20, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(14, "vnd.android.cursor.dir/vnd.amazonmp3.track");
        map.put(11, "vnd.android.cursor.item/vnd.amazonmp3.track");
        map.put(12, "vnd.android.cursor.dir/vnd.amazonmp3.album");
        map.put(17, "vnd.android.cursor.dir/vnd.amazonmp3.album");
        map.put(13, "vnd.android.cursor.item/vnd.amazonmp3.album");
        map.put(15, "vnd.android.cursor.dir/vnd.amazonmp3.artist");
        map.put(16, "vnd.android.cursor.item/vnd.amazonmp3.artist");
        map.put(21, "vnd.android.cursor.dir/vnd.amazonmp3.playlists");
        map.put(22, "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist");
        map.put(23, "vnd.android.cursor.dir/vnd.amazonmp3.udoplaylisttrack");
    }

    private CacheManager getCacheManager() {
        if (this.mCacheManager == null) {
            this.mCacheManager = CacheManager.getInstance();
        }
        return this.mCacheManager;
    }

    public enum Columns {
        KEY("key"),
        COUNT("_COUNT"),
        TYPE(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE),
        TITLE("title"),
        LAST_ACCESSED("lastAccessed"),
        DOWNLOAD_STATE("download_state"),
        THUMBNAIL_URI("thumbnailUri"),
        IMAGE_URI("previewUri"),
        VIEW_INTENT_URI("viewIntentUri"),
        ARTIST("artist"),
        AVAILABLE("available"),
        PLAYLIST_KIND("playlistKind"),
        SUGGEST_COLUMN_TEXT_1("suggest_text_1"),
        SUGGEST_COLUMN_TEXT_2("suggest_text_2"),
        SUGGEST_COLUMN_ICON_1("suggest_icon_1"),
        SUGGEST_COLUMN_ICON_2("suggest_icon_2"),
        SUGGEST_COLUMN_INTENT_ACTION("suggest_intent_action"),
        SUGGEST_COLUMN_INTENT_DATA("suggest_intent_data"),
        SUGGEST_COLUMN_SHORTCUT_ID("suggest_shortcut_id"),
        SUGGESTION_INDEX("suggestionIndex");

        public final String mColumn;

        Columns(String column) {
            this.mColumn = column;
            ExternalProvider.sColumnsMap.put(column, this);
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mColumn;
        }

        public static String[] getProjection(Columns[] columns) {
            String[] strArr = new String[columns.length];
            int length = columns.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                strArr[i2] = columns[i].toString();
                i++;
                i2++;
            }
            return strArr;
        }

        public static Columns getColumn(String column) {
            return (Columns) ExternalProvider.sColumnsMap.get(column);
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String order) {
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1) {
            return queryRecent(uri, projection, selection, selectionArgs, order);
        }
        if (iMatch == 2) {
            return queryRecentItem(uri, projection);
        }
        switch (iMatch) {
            case 6:
                return querySuggestions(uri);
            case 7:
            case 8:
                return null;
            case 9:
                return queryEncodedRecentItem(uri, projection, selection, selectionArgs, order);
            case 10:
            case 11:
            case 14:
            case 19:
            case 20:
            case 23:
                return queryLibraryTracks(iMatch, uri, projection, selection, selectionArgs, order);
            case 12:
            case 13:
            case 17:
            case 18:
                return queryLibraryAlbums(iMatch, uri, projection, selection, selectionArgs, order);
            case 15:
            case 16:
                return queryLibraryArtists(iMatch, uri, projection, selection, selectionArgs, order);
            case 21:
            case 22:
                return queryLibraryPlaylists(iMatch, uri, projection, selection, selectionArgs, order);
            case 24:
                return queryAppState();
            default:
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Cursor queryAppState() {
        int i;
        boolean zIsSignedIn = AccountCredentialUtil.get(getContext()).isSignedIn();
        boolean z = false;
        boolean z2 = zIsSignedIn && CirrusDatabaseUtil.getCachedTrackCount(AmazonApplication.getContext(), "cirrus", true) > 0;
        Cursor cursorQuery = AmazonApplication.getContext().getContentResolver().query(MediaProvider.Tracks.getContentUri("cirrus-local"), new String[]{"_id"}, null, null, null);
        if (cursorQuery != null) {
            try {
                z = cursorQuery.getCount() > 0;
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        if (z2 || z) {
            i = (zIsSignedIn ? 1 : 0) | 2;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"state"});
        matrixCursor.newRow().add(Integer.valueOf(i));
        return matrixCursor;
    }

    public Cursor queryEncodedRecentItem(Uri uri, String[] projection, String selection, String[] selectionArgs, String order) {
        String str = uri.getScheme() + "://" + uri.getAuthority() + "/recent/" + URLDecoder.decode(uri.getLastPathSegment()) + "?" + uri.getQuery();
        if (str.equals(uri.toString())) {
            return queryRecentItem(uri, projection);
        }
        return query(Uri.parse(str), projection, selection, selectionArgs, order);
    }

    private Cursor querySuggestions(Uri uri) {
        String lowerCase = uri.getLastPathSegment().toLowerCase(Locale.US);
        String queryParameter = uri.getQueryParameter("limit");
        int i = 10;
        if (queryParameter != null) {
            try {
                i = Integer.parseInt(queryParameter);
            } catch (NumberFormatException unused) {
            }
        }
        return runSearchQuery(lowerCase, i, Columns.getProjection(SEARCH_RESULTS_PROJECTION));
    }

    private Cursor queryRecentItem(Uri uri, String[] projection) {
        String[] strArr;
        List<String> pathSegments = uri.getPathSegments();
        int i = (pathSegments.size() <= 0 || !"recent".equals(pathSegments.get(0))) ? 0 : 1;
        if (pathSegments.size() < (i != 0 ? 2 : 1)) {
            return null;
        }
        if (projection == null) {
            projection = Columns.getProjection(DEFAULT_RECENT_PROJECTION);
        }
        Uri uri2 = Uri.parse(decodeKey(pathSegments.get(i)));
        String strLookupKind = lookupKind(uri2);
        if (strLookupKind == null) {
            return null;
        }
        int iMatch = DefaultUriMatcher.match(uri2);
        if (iMatch == 2) {
            strArr = INTERNAL_TRACKS_SEARCH_PROJECTION;
        } else if (iMatch == 4) {
            strArr = INTERNAL_SEARCH_PROJECTION;
        } else if (iMatch == 8) {
            strArr = INTERNAL_ARTISTS_SEARCH_PROJECTION;
        } else {
            if (iMatch != 13 && iMatch != 14) {
                return null;
            }
            strArr = INTERNAL_PLAYLISTS_SEARCH_PROJECTION;
        }
        String[] strArr2 = strArr;
        String str = uri2.getPathSegments().get(1);
        Cursor cursorQuery = MediaProvider.getInstance().query(uri2, strArr2, null, null, null);
        MatrixCursor matrixCursor = getMatrixCursor(new CursorWalker[]{new CursorWalker(cursorQuery, str, strLookupKind)}, projection, 1);
        DbUtil.closeCursor(cursorQuery);
        return matrixCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryLibraryTracks(int r21, android.net.Uri r22, java.lang.String[] r23, java.lang.String r24, java.lang.String[] r25, java.lang.String r26) {
        /*
            r20 = this;
            r0 = r21
            java.util.List r1 = r22.getPathSegments()
            r2 = 1
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            r2 = 10
            r3 = 0
            if (r0 == r2) goto L83
            r1 = 11
            r2 = 3
            if (r0 == r1) goto L70
            r1 = 14
            java.lang.String r4 = "/tracks"
            if (r0 == r1) goto L4e
            r1 = 23
            if (r0 == r1) goto L4e
            r1 = 19
            if (r0 == r1) goto L2b
            r1 = 20
            if (r0 == r1) goto L4e
            r5 = r3
            goto L88
        L2b:
            java.util.List r0 = r22.getPathSegments()
            r1 = 5
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = decodeKey(r0)
            r1.append(r0)
            r1.append(r4)
            java.lang.String r0 = r1.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto L87
        L4e:
            java.util.List r0 = r22.getPathSegments()
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = decodeKey(r0)
            r1.append(r0)
            r1.append(r4)
            java.lang.String r0 = r1.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto L87
        L70:
            java.util.List r0 = r22.getPathSegments()
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r0 = decodeKey(r0)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto L87
        L83:
            android.net.Uri r0 = com.amazon.mp3.library.provider.MediaProvider.Tracks.getContentUri(r1)
        L87:
            r5 = r0
        L88:
            java.lang.String r6 = "_id"
            java.lang.String r7 = "luid"
            java.lang.String r8 = "title"
            java.lang.String r9 = "local_uri"
            java.lang.String r10 = "track_num"
            java.lang.String r11 = "album"
            java.lang.String r12 = "album_id"
            java.lang.String r13 = "album_art_id"
            java.lang.String r14 = "artist"
            java.lang.String r15 = "artist_id"
            java.lang.String r16 = "album_artist"
            java.lang.String r17 = "album_artist_id"
            java.lang.String r18 = "disc_num"
            java.lang.String r19 = "duration"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19}
            if (r5 == 0) goto Lc3
            com.amazon.mp3.library.provider.MediaProvider r4 = com.amazon.mp3.library.provider.MediaProvider.getInstance()
            r7 = r24
            r8 = r25
            r9 = r26
            android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)
            r1 = r20
            r2 = r23
            android.database.Cursor r0 = r1.transformLibraryCursor(r0, r2)
            return r0
        Lc3:
            r1 = r20
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.ExternalProvider.queryLibraryTracks(int, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a5 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryLibraryAlbums(int r10, android.net.Uri r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15) {
        /*
            r9 = this;
            java.util.List r0 = r11.getPathSegments()
            r1 = 1
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 12
            r2 = 0
            if (r10 == r1) goto L8d
            r0 = 13
            r1 = 3
            if (r10 == r0) goto L7a
            r0 = 17
            if (r10 == r0) goto L34
            r0 = 18
            if (r10 == r0) goto L20
            r4 = r2
            goto L92
        L20:
            java.util.List r10 = r11.getPathSegments()
            r11 = 5
            java.lang.Object r10 = r10.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = decodeKey(r10)
            android.net.Uri r10 = android.net.Uri.parse(r10)
            goto L91
        L34:
            java.util.List r10 = r11.getPathSegments()
            java.lang.Object r10 = r10.get(r1)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = decodeKey(r10)
            com.amazon.mp3.library.provider.MediaProvider r3 = com.amazon.mp3.library.provider.MediaProvider.getInstance()
            android.net.Uri r4 = android.net.Uri.parse(r10)
            java.lang.String r10 = "_id"
            java.lang.String[] r5 = new java.lang.String[]{r10}
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)
            if (r0 == 0) goto L75
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L70
            if (r1 == 0) goto L75
            int r10 = r0.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L70
            long r3 = r0.getLong(r10)     // Catch: java.lang.Throwable -> L70
            java.lang.String r10 = com.amazon.mp3.library.provider.MediaProvider.getSource(r11)     // Catch: java.lang.Throwable -> L70
            android.net.Uri r10 = com.amazon.mp3.library.provider.MediaProvider.Artists.Albums.getContentUri(r10, r3)     // Catch: java.lang.Throwable -> L70
            goto L76
        L70:
            r10 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r0)
            throw r10
        L75:
            r10 = r2
        L76:
            com.amazon.mp3.util.DbUtil.closeCursor(r0)
            goto L91
        L7a:
            java.util.List r10 = r11.getPathSegments()
            java.lang.Object r10 = r10.get(r1)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = decodeKey(r10)
            android.net.Uri r10 = android.net.Uri.parse(r10)
            goto L91
        L8d:
            android.net.Uri r10 = com.amazon.mp3.library.provider.MediaProvider.Albums.getContentUri(r0)
        L91:
            r4 = r10
        L92:
            if (r4 == 0) goto La5
            com.amazon.mp3.library.provider.MediaProvider r3 = com.amazon.mp3.library.provider.MediaProvider.getInstance()
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r15
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            android.database.Cursor r10 = r9.transformLibraryCursor(r10, r12)
            return r10
        La5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.ExternalProvider.queryLibraryAlbums(int, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryLibraryArtists(int r10, android.net.Uri r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15) {
        /*
            r9 = this;
            java.util.List r0 = r11.getPathSegments()
            r1 = 1
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 15
            r2 = 0
            if (r10 == r1) goto L2a
            r0 = 16
            if (r10 == r0) goto L16
            r4 = r2
            goto L2f
        L16:
            java.util.List r10 = r11.getPathSegments()
            r11 = 3
            java.lang.Object r10 = r10.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = decodeKey(r10)
            android.net.Uri r10 = android.net.Uri.parse(r10)
            goto L2e
        L2a:
            android.net.Uri r10 = com.amazon.mp3.library.provider.MediaProvider.Artists.getContentUri(r0)
        L2e:
            r4 = r10
        L2f:
            if (r4 == 0) goto L42
            com.amazon.mp3.library.provider.MediaProvider r3 = com.amazon.mp3.library.provider.MediaProvider.getInstance()
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r15
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            android.database.Cursor r10 = r9.transformLibraryCursor(r10, r12)
            return r10
        L42:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.ExternalProvider.queryLibraryArtists(int, android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private Cursor queryLibraryPlaylists(int nUriType, Uri uri, String[] projection, String selection, String[] selectionArgs, String order) {
        Uri uri2 = (nUriType == 21 || nUriType == 22) ? Uri.parse(decodeKey(uri.getPathSegments().get(3))) : null;
        if (uri2 != null) {
            return transformLibraryCursor(MediaProvider.getInstance().query(uri2, projection, selection, selectionArgs, order), projection);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor queryRecent(android.net.Uri r21, java.lang.String[] r22, java.lang.String r23, java.lang.String[] r24, java.lang.String r25) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.ExternalProvider.queryRecent(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private void fillCursorWithRecents(final MatrixCursor baseCursor, final int nLimit) {
        Uri[] uriArr;
        String str;
        String[] strArr;
        String[] strArr2;
        HashSet hashSet;
        MatrixCursor matrixCursor = baseCursor;
        String str2 = "cirrus";
        int i = 0;
        Uri[] uriArr2 = {MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus", 0L), MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus", 1L), MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus-local", 2L)};
        String[] strArr3 = {"date_purchased", "date_created", "date_created"};
        String[] strArr4 = {"album_id", "album", "album_artist", "date_purchased", "date_created"};
        Cursor cursorQuery = null;
        for (int i2 = 3; i < i2; i2 = 3) {
            if (AccountCredentialUtil.get(getContext()).isSignedOut() && str2.equals(MediaProvider.getSource(uriArr2[i]))) {
                uriArr = uriArr2;
                str = str2;
                strArr = strArr4;
                strArr2 = strArr3;
            } else {
                HashSet hashSet2 = new HashSet(nLimit);
                if (baseCursor.getCount() > 0) {
                    int position = baseCursor.getPosition();
                    int columnIndex = matrixCursor.getColumnIndex("id");
                    baseCursor.moveToFirst();
                    while (!baseCursor.isAfterLast()) {
                        hashSet2.add(matrixCursor.getString(columnIndex));
                        baseCursor.moveToNext();
                    }
                    matrixCursor.moveToPosition(position);
                }
                try {
                    HashSet hashSet3 = hashSet2;
                    cursorQuery = MediaProvider.getInstance().query(uriArr2[i], strArr4, null, null, null);
                    if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                        uriArr = uriArr2;
                        str = str2;
                        strArr = strArr4;
                        strArr2 = strArr3;
                    } else {
                        int columnIndex2 = cursorQuery.getColumnIndex("album");
                        int columnIndex3 = cursorQuery.getColumnIndex(strArr3[i]);
                        int columnIndex4 = cursorQuery.getColumnIndex("album_id");
                        int columnIndex5 = cursorQuery.getColumnIndex("album_artist");
                        uriArr = uriArr2;
                        String source = MediaProvider.getSource(uriArr2[i]);
                        str = str2;
                        while (true) {
                            String string = cursorQuery.getString(columnIndex4);
                            if (hashSet3.add(string)) {
                                hashSet = hashSet3;
                                String string2 = cursorQuery.getString(columnIndex2);
                                long j = cursorQuery.getLong(columnIndex3);
                                strArr = strArr4;
                                String string3 = cursorQuery.getString(columnIndex5);
                                if (!TextUtils.isEmpty(string2)) {
                                    strArr2 = strArr3;
                                    MatrixCursor.RowBuilder rowBuilderNewRow = baseCursor.newRow();
                                    rowBuilderNewRow.add(string);
                                    rowBuilderNewRow.add("albums");
                                    rowBuilderNewRow.add(string2);
                                    rowBuilderNewRow.add(source);
                                    rowBuilderNewRow.add(string3);
                                    rowBuilderNewRow.add(Long.valueOf(j));
                                }
                                if (baseCursor.getCount() < nLimit || !cursorQuery.moveToNext()) {
                                    break;
                                }
                                hashSet3 = hashSet;
                                strArr4 = strArr;
                                strArr3 = strArr2;
                            } else {
                                hashSet = hashSet3;
                                strArr = strArr4;
                            }
                            strArr2 = strArr3;
                            if (baseCursor.getCount() < nLimit) {
                                break;
                                break;
                            } else {
                                hashSet3 = hashSet;
                                strArr4 = strArr;
                                strArr3 = strArr2;
                            }
                        }
                        if (baseCursor.getCount() > 0) {
                            return;
                        }
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
            i++;
            matrixCursor = baseCursor;
            str2 = str;
            uriArr2 = uriArr;
            strArr4 = strArr;
            strArr3 = strArr2;
        }
    }

    private CursorWalker createCursorWalker(final String[] projection, final String kind, final Uri cirrus, final Uri local) {
        Cursor cursorQuery = MediaProvider.getInstance().query(local, projection, null, null, null);
        String source = "cirrus-local";
        if (AccountCredentialUtil.get(getContext()).isSignedOut()) {
            return new CursorWalker(cursorQuery, "cirrus-local", kind);
        }
        Cursor cursorQuery2 = MediaProvider.getInstance().query(cirrus, projection, null, null, null);
        if (cursorQuery2.getCount() > cursorQuery.getCount()) {
            DbUtil.closeCursor(cursorQuery);
            source = MediaProvider.getSource(cirrus);
            cursorQuery = cursorQuery2;
        } else {
            DbUtil.closeCursor(cursorQuery2);
        }
        return new CursorWalker(cursorQuery, source, kind);
    }

    public Cursor runSearchQuery(final String query, final int nLimit, String[] resultProjection) {
        if (MediaProvider.getInstance() == null) {
            return null;
        }
        if (resultProjection == null) {
            resultProjection = Columns.getProjection(DEFAULT_RECENT_SEARCH_PROJECTION);
        }
        CursorWalker cursorWalkerCreateCursorWalker = createCursorWalker(INTERNAL_PLAYLISTS_SEARCH_PROJECTION, "playlists", MediaProvider.Playlists.getFilterContentUri("cirrus", query).buildUpon().appendQueryParameter("restrictFields", "1").build(), MediaProvider.Playlists.getFilterContentUri("cirrus-local", query).buildUpon().appendQueryParameter("restrictFields", "1").build());
        CursorWalker cursorWalkerCreateCursorWalker2 = createCursorWalker(INTERNAL_ARTISTS_SEARCH_PROJECTION, "artists", MediaProvider.Artists.getFilterContentUri("merged", query).buildUpon().appendQueryParameter("restrictFields", "1").build(), MediaProvider.Artists.getFilterContentUri("cirrus-local", query).buildUpon().appendQueryParameter("restrictFields", "1").build());
        CursorWalker cursorWalkerCreateCursorWalker3 = createCursorWalker(INTERNAL_SEARCH_PROJECTION, "albums", MediaProvider.Albums.getFilterContentUri("merged", query).buildUpon().appendQueryParameter("restrictFields", "1").build(), MediaProvider.Albums.getFilterContentUri("cirrus-local", query).buildUpon().appendQueryParameter("restrictFields", "1").build());
        CursorWalker cursorWalkerCreateCursorWalker4 = createCursorWalker(INTERNAL_TRACKS_SEARCH_PROJECTION, "tracks", MediaProvider.Tracks.getFilterContentUri("merged", query).buildUpon().appendQueryParameter("restrictFields", "1").build(), MediaProvider.Tracks.getFilterContentUri("cirrus-local", query).buildUpon().appendQueryParameter("restrictFields", "1").build());
        MatrixCursor matrixCursor = getMatrixCursor(new CursorWalker[]{cursorWalkerCreateCursorWalker, cursorWalkerCreateCursorWalker2, cursorWalkerCreateCursorWalker3, cursorWalkerCreateCursorWalker4}, resultProjection, nLimit);
        cursorWalkerCreateCursorWalker.close();
        cursorWalkerCreateCursorWalker2.close();
        cursorWalkerCreateCursorWalker3.close();
        cursorWalkerCreateCursorWalker4.close();
        return matrixCursor;
    }

    private static class DrawableThread extends Thread {
        private final long mAlbumId;
        private Drawable mDrawable;
        private final int mSize;

        public DrawableThread(int size, long albumid) {
            this.mSize = size;
            this.mAlbumId = albumid;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.mDrawable = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, this.mSize, Long.toString(this.mAlbumId), false);
        }
    }

    String lookupKind(Uri uri) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch == 2) {
            return "tracks";
        }
        if (iMatch == 4) {
            return "albums";
        }
        if (iMatch == 8) {
            return "artists";
        }
        if (iMatch == 13) {
            return "playlists_smart";
        }
        if (iMatch != 14) {
            return null;
        }
        return "playlists_udo";
    }

    private DrawableResult getAlbumArt(int artSize, long albumid, boolean bAllowLoading) {
        Drawable drawable;
        Drawable drawable2;
        String string = Long.toString(albumid);
        CacheManager cacheManager = getCacheManager();
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ALBUM;
        boolean z = true;
        if (cacheManager.contains(itemType, artSize, string)) {
            drawable2 = getCacheManager().get(itemType, artSize, string);
            z = false;
        } else if (bAllowLoading || getCacheManager().hasOnDisk(itemType, artSize, string)) {
            DrawableThread drawableThread = new DrawableThread(artSize, albumid);
            drawableThread.start();
            try {
                drawableThread.join();
                drawable = drawableThread.mDrawable;
            } catch (InterruptedException unused) {
                drawable = null;
            }
            z = drawable != null ? !getCacheManager().hasOnDisk(ImageLoaderFactory.ItemType.ALBUM, artSize, string) : false;
            drawable2 = drawable;
        } else {
            drawable2 = getCacheManager().get(itemType, artSize, string);
        }
        return new DrawableResult(drawable2, z);
    }

    private synchronized void queueBackgroundArtworkLoad(final Uri uri, final boolean bHadDefault) {
        if (!this.mPendingUris.contains(uri)) {
            this.mPendingUris.add(uri);
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.mp3.library.provider.ExternalProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ExternalProvider.this.generateBitmap(uri, true, bHadDefault);
                    } catch (FileNotFoundException unused) {
                        Log.debug(ExternalProvider.TAG, "Unable to generate artwork for %s", uri);
                    }
                    ExternalProvider.this.finishBackgroundArtworkLoad(uri);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void finishBackgroundArtworkLoad(Uri uri) {
        this.mPendingUris.remove(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0105 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap generateBitmap(android.net.Uri r17, final boolean r18, final boolean r19) throws java.io.FileNotFoundException {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.provider.ExternalProvider.generateBitmap(android.net.Uri, boolean, boolean):android.graphics.Bitmap");
    }

    private Uri getTargetUri(Uri uri) {
        Uri uri2 = Uri.parse(decodeKey(uri.getPathSegments().get(1)));
        List<String> pathSegments = uri2.getPathSegments();
        if (pathSegments == null || pathSegments.size() == 0) {
            return uri2;
        }
        String str = pathSegments.get(0);
        if (!str.equals("library")) {
            return uri2;
        }
        try {
            StringBuilder sb = new StringBuilder(uri2.getScheme() + "://" + uri2.getAuthority() + "/" + str + "/" + MediaProvider.CirrusBaseColumns.Source.convertToSourceId(Integer.parseInt(pathSegments.get(1))));
            for (int i = 2; i < pathSegments.size(); i++) {
                sb.append(JsonPointer.SEPARATOR);
                sb.append(pathSegments.get(i));
            }
            return Uri.parse(sb.toString());
        } catch (NumberFormatException unused) {
            return uri2;
        }
    }

    private static String cacheKeyFromUri(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        String str = pathSegments.get(0) + "-" + pathSegments.get(1);
        String queryParameter = uri.getQueryParameter("size");
        if (queryParameter == null || "".equals(queryParameter)) {
            return str;
        }
        return str + "-" + queryParameter;
    }

    private int artworkSizeFromUri(Uri uri) {
        int iMatch = sUriMatcher.match(uri);
        String queryParameter = uri.getQueryParameter("size");
        if (queryParameter != null && !"".equals(queryParameter)) {
            try {
                return Integer.parseInt(queryParameter);
            } catch (NumberFormatException unused) {
            }
        }
        if (iMatch != 3) {
            return iMatch != 4 ? -1 : 380;
        }
        return 190;
    }

    private static class PipeCreator {
        private PipeCreator() {
        }

        public ParcelFileDescriptor[] createPipe() throws IOException {
            return ParcelFileDescriptor.createPipe();
        }
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        String strCacheKeyFromUri = cacheKeyFromUri(uri);
        FileCache fileCache = sFileCache;
        File file = fileCache.get(strCacheKeyFromUri);
        if (file != null && file.exists()) {
            return ParcelFileDescriptor.open(file, 268435456);
        }
        File file2 = fileCache.get(strCacheKeyFromUri + ".default");
        if (file2 != null && file2.exists()) {
            queueBackgroundArtworkLoad(uri, true);
            return ParcelFileDescriptor.open(file2, 268435456);
        }
        final Bitmap bitmapGenerateBitmap = generateBitmap(uri, false, false);
        if (bitmapGenerateBitmap != null) {
            try {
                ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = new PipeCreator().createPipe();
                final ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArrCreatePipe[1]);
                new Thread("Bitmap compress thread") { // from class: com.amazon.mp3.library.provider.ExternalProvider.3
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            bitmapGenerateBitmap.compress(Bitmap.CompressFormat.PNG, 100, autoCloseOutputStream);
                            autoCloseOutputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                }.start();
                return parcelFileDescriptorArrCreatePipe[0];
            } catch (IOException unused) {
                throw new FileNotFoundException();
            }
        }
        throw new FileNotFoundException();
    }

    public static Uri buildThumbnailUri(int size, Uri target) {
        return buildThumbnailUriFromKey(size, encodeKey(target));
    }

    public static Uri buildThumbnailUriFromKey(int size, String encodedKey) {
        Uri uriBuild;
        if (size == 190) {
            uriBuild = THUMBNAIL_BASE.buildUpon().appendPath(encodedKey).build();
        } else if (size == 380) {
            uriBuild = IMAGE_BASE.buildUpon().appendPath(encodedKey).build();
        } else {
            uriBuild = IMAGE_BASE.buildUpon().appendPath(encodedKey).appendQueryParameter("size", Integer.toString(size)).build();
        }
        if (uriBuild != null) {
            return uriBuild.buildUpon().appendQueryParameter("hash", sFileCache.has(cacheKeyFromUri(uriBuild)) ? "1" : "0").build();
        }
        return uriBuild;
    }

    private MatrixCursor getMatrixCursor(CursorWalker[] walkers, String[] projection, int nLimit) {
        return getMatrixCursor(walkers, projection, nLimit, null, null);
    }

    private MatrixCursor getMatrixCursor(CursorWalker[] walkers, String[] projection, int nLimit, Skippable skip, String syntheticKey) {
        boolean zAddRow;
        Columns[] columnsArr = new Columns[projection.length];
        int length = projection.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            columnsArr[i2] = Columns.getColumn(projection[i]);
            i++;
            i2++;
        }
        MatrixCursor matrixCursor = new MatrixCursor(projection, nLimit);
        int count = 0;
        for (CursorWalker cursorWalker : walkers) {
            if (cursorWalker != null) {
                count += cursorWalker.getCount();
            }
        }
        String string = Integer.toString(Math.min(nLimit, count));
        do {
            zAddRow = false;
            for (CursorWalker cursorWalker2 : walkers) {
                if (cursorWalker2 != null && cursorWalker2.hasMore()) {
                    zAddRow |= addRow(cursorWalker2, matrixCursor, columnsArr, string, skip, syntheticKey);
                }
            }
            if (matrixCursor.getCount() >= nLimit) {
                break;
            }
        } while (zAddRow);
        return matrixCursor;
    }

    private static Uri createTargetUri(String source, String kind, String id) {
        return createTargetUri(source, kind, id, false);
    }

    public static Uri createTargetUri(String source, String kind, String id, boolean promoteTrackToAlbum) {
        if ("albums".equals(kind)) {
            return MediaProvider.Albums.getContentUri(source, Long.parseLong(id));
        }
        if ("artists".equals(kind)) {
            return MediaProvider.Artists.getContentUri(source, Long.parseLong(id));
        }
        if ("tracks".equals(kind)) {
            Uri contentUri = MediaProvider.Tracks.getContentUri(source, id);
            return promoteTrackToAlbum ? createTargetUri(source, "albums", Long.toString(getAlbumIdFromTrack(contentUri))) : contentUri;
        }
        if ("playlists_smart".equals(kind)) {
            return MediaProvider.SmartPlaylists.getContentUri(source, Long.parseLong(id));
        }
        if ("playlists_udo".equals(kind)) {
            return MediaProvider.UdoPlaylists.getContentUri(source, Long.parseLong(id));
        }
        if ("playlists_prime".equals(kind)) {
            return MediaProvider.PrimePlaylists.getContentUri(source, id);
        }
        return null;
    }

    private long getLastAccessedTime(Uri itemUri) {
        Cursor cursorQuery = MediaProvider.getInstance().query(MediaProvider.Recent.CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build(), new String[]{TetheredMessageKey.timestamp}, "key=?", new String[]{itemUri.toString()}, null);
        long j = (cursorQuery == null || !cursorQuery.moveToFirst()) ? 0L : cursorQuery.getLong(0);
        DbUtil.closeCursor(cursorQuery);
        return j;
    }

    private static long getAlbumIdFromTrack(Uri trackUri) {
        Cursor cursorQuery = MediaProvider.getInstance().query(trackUri, new String[]{"album_id"}, null, null, null);
        long j = (cursorQuery == null || !cursorQuery.moveToFirst()) ? -1L : cursorQuery.getLong(cursorQuery.getColumnIndex("album_id"));
        DbUtil.closeCursor(cursorQuery);
        return j;
    }

    private String buildViewUri(String kind, Uri targetUri, String fullId, String syntheticKey) {
        Uri.Builder builderBuildUpon = RECENT_BASE.buildUpon();
        String source = MediaProvider.getSource(targetUri);
        if ("nowplaying".equals(syntheticKey)) {
            builderBuildUpon.appendPath("nowplaying");
        } else if ("tracks".equals(kind)) {
            long albumIdFromTrack = getAlbumIdFromTrack(targetUri);
            if (albumIdFromTrack != -1) {
                builderBuildUpon.appendPath(encodeKey(MediaProvider.Albums.getContentUri(source, albumIdFromTrack)));
            } else {
                builderBuildUpon.appendPath(encodeKey(Uri.parse("content://" + MediaProvider.AUTHORITY + "/library/" + source)));
            }
        } else {
            builderBuildUpon.appendPath(fullId);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uriBuild, "vnd.android.cursor.item/vnd.amazon.mp3.recent");
        intent.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        intent.addFlags(268435456);
        intent.addFlags(2097152);
        return intent.toUri(1);
    }

    private boolean addRow(CursorWalker walker, MatrixCursor resultCursor, Columns[] columns, String count, Skippable skip, String syntheticKey) {
        if (skip != null && skip.shouldSkip(walker)) {
            return walker.moveToNext();
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = resultCursor.newRow();
        String id = walker.getId();
        String source = walker.getSource();
        try {
            source = MediaProvider.CirrusBaseColumns.Source.convertToSourceId(Integer.parseInt(source));
        } catch (NumberFormatException unused) {
        }
        String kind = walker.getKind();
        if (walker.getKind().equals("playlists")) {
            if (walker.getAsin().isEmpty()) {
                kind = "playlists_udo";
            } else {
                id = walker.getAsin();
                kind = "playlists_prime";
            }
        }
        Uri uriCreateTargetUri = createTargetUri(source, kind, id);
        String strEncodeKey = uriCreateTargetUri != null ? encodeKey(uriCreateTargetUri) : syntheticKey;
        for (Columns columns2 : columns) {
            String title = "1";
            if (columns2 != null) {
                switch (AnonymousClass4.$SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[columns2.ordinal()]) {
                    case 1:
                        if (syntheticKey != null) {
                            title = syntheticKey;
                            continue;
                        } else {
                            title = strEncodeKey;
                        }
                        rowBuilderNewRow.add(title);
                        break;
                    case 2:
                        title = count;
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 3:
                    case 4:
                        title = walker.getTitle();
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 5:
                    case 6:
                        if ("playlists_smart".equals(kind) || "playlists_udo".equals(kind)) {
                            title = "playlists";
                        } else {
                            title = kind;
                            continue;
                        }
                        rowBuilderNewRow.add(title);
                        break;
                    case 7:
                        if ("nowplaying".equals(syntheticKey)) {
                            title = Long.toString(Math.max(0L, PlaybackService.getLastUserInteractionTime()));
                            continue;
                        } else {
                            title = walker.getLastAccessed();
                            if (TextUtils.isEmpty(title) && !"tracks".equals(kind) && !"nowplaying".equals(syntheticKey)) {
                                title = Long.toString(getLastAccessedTime(uriCreateTargetUri));
                            }
                        }
                        rowBuilderNewRow.add(title);
                        break;
                    case 8:
                        title = "LOCAL";
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 9:
                    case 10:
                        Uri uriBuildThumbnailUri = buildThumbnailUri(190, uriCreateTargetUri);
                        if (uriBuildThumbnailUri != null && "nowplaying".equals(syntheticKey)) {
                            uriBuildThumbnailUri = uriBuildThumbnailUri.buildUpon().appendQueryParameter("nowplaying", "1").build();
                        }
                        if (uriBuildThumbnailUri != null) {
                            title = uriBuildThumbnailUri.toString();
                            rowBuilderNewRow.add(title);
                        } else {
                            title = "";
                        }
                        break;
                    case 11:
                        Uri uriBuildThumbnailUri2 = buildThumbnailUri(380, uriCreateTargetUri);
                        if (uriBuildThumbnailUri2 != null && "nowplaying".equals(syntheticKey)) {
                            uriBuildThumbnailUri2 = uriBuildThumbnailUri2.buildUpon().appendQueryParameter("nowplaying", "1").build();
                        }
                        if (uriBuildThumbnailUri2 != null) {
                            title = uriBuildThumbnailUri2.toString();
                            rowBuilderNewRow.add(title);
                        } else {
                            title = "";
                        }
                        break;
                    case 12:
                        title = buildViewUri(kind, uriCreateTargetUri, strEncodeKey, syntheticKey);
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 13:
                        title = walker.getArtist();
                        if (title == null || "".equals(title)) {
                            title = "-";
                        } else {
                            continue;
                        }
                        rowBuilderNewRow.add(title);
                        break;
                    case 14:
                        title = "android.intent.action.VIEW";
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 15:
                        title = RECENT_BASE.buildUpon().appendPath(strEncodeKey).toString();
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 16:
                        title = "_-1";
                        continue;
                        rowBuilderNewRow.add(title);
                        break;
                    case 17:
                        rowBuilderNewRow.add(title);
                        break;
                    default:
                        title = "";
                        break;
                }
            } else {
                title = "";
            }
            rowBuilderNewRow.add(title);
        }
        return walker.moveToNext();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.ExternalProvider$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns;

        static {
            int[] iArr = new int[Columns.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns = iArr;
            try {
                iArr[Columns.KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.COUNT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.TITLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_TEXT_1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_TEXT_2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.LAST_ACCESSED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.DOWNLOAD_STATE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_ICON_1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.THUMBNAIL_URI.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.IMAGE_URI.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.VIEW_INTENT_URI.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.ARTIST.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_INTENT_ACTION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_INTENT_DATA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGEST_COLUMN_SHORTCUT_ID.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$ExternalProvider$Columns[Columns.SUGGESTION_INDEX.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sUriMatcher.match(uri);
        HashMap<Integer, String> map = sMatchTypes;
        return map.containsKey(Integer.valueOf(iMatch)) ? map.get(Integer.valueOf(iMatch)) : "";
    }

    private static class CursorWalker {
        private int mAlbumColumn;
        private int mAlbumCountColumn;
        private int mArtistColumn;
        private int mAsinColumn;
        private Cursor mCursor;
        private int mIdColumn;
        private String mKind;
        private int mKindColumn;
        private int mLastAccessedColumn;
        private boolean mMore;
        private String mSource;
        private int mSourceColumn;
        private int mTitleColumn;
        private int mTrackCountColumn;

        private static int getIndexForColumns(Cursor cursor, String... columns) {
            for (String str : columns) {
                int columnIndex = cursor.getColumnIndex(str);
                if (columnIndex != -1) {
                    return columnIndex;
                }
            }
            return -1;
        }

        public CursorWalker(Cursor cursor, String source, String kind) {
            this.mMore = false;
            this.mIdColumn = -1;
            this.mArtistColumn = -1;
            this.mTitleColumn = -1;
            this.mAlbumColumn = -1;
            this.mKindColumn = -1;
            this.mSourceColumn = -1;
            this.mLastAccessedColumn = -1;
            this.mTrackCountColumn = -1;
            this.mAlbumCountColumn = -1;
            this.mAsinColumn = -1;
            if (cursor == null) {
                this.mMore = false;
                return;
            }
            this.mCursor = cursor;
            this.mSource = source;
            this.mArtistColumn = getIndexForColumns(cursor, Columns.ARTIST.mColumn, "artist", "artist", "name");
            this.mIdColumn = getIndexForColumns(cursor, "_id", "_id", "id", "_id", "luid");
            this.mTitleColumn = getIndexForColumns(cursor, "title", "title", "name", "name", "name");
            this.mSourceColumn = getIndexForColumns(cursor, PageUriUtils.SOURCE_QUERY_PARAM, PageUriUtils.SOURCE_QUERY_PARAM);
            this.mAlbumColumn = getIndexForColumns(cursor, "album", "title", "name");
            this.mTrackCountColumn = getIndexForColumns(cursor, "track_count", "track_count");
            this.mAlbumCountColumn = getIndexForColumns(cursor, "track_count");
            this.mLastAccessedColumn = getIndexForColumns(cursor, Columns.LAST_ACCESSED.mColumn);
            if (TextUtils.isEmpty(kind)) {
                this.mKindColumn = cursor.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            } else {
                this.mKindColumn = -1;
                this.mKind = kind;
            }
            this.mAsinColumn = getIndexForColumns(cursor, "asin");
            moveToFirst();
        }

        public boolean moveToFirst() {
            boolean zMoveToFirst = this.mCursor.moveToFirst();
            this.mMore = zMoveToFirst;
            return zMoveToFirst;
        }

        public boolean moveToNext() {
            boolean zMoveToNext = this.mCursor.moveToNext();
            this.mMore = zMoveToNext;
            return zMoveToNext;
        }

        public void close() {
            DbUtil.closeCursor(this.mCursor);
        }

        public boolean hasMore() {
            return this.mMore;
        }

        public int getCount() {
            Cursor cursor = this.mCursor;
            if (cursor != null) {
                return cursor.getCount();
            }
            return 0;
        }

        public String getKind() {
            int i = this.mKindColumn;
            return i != -1 ? this.mCursor.getString(i) : this.mKind;
        }

        public String getArtist() {
            int i = this.mArtistColumn;
            return i != -1 ? this.mCursor.getString(i) : "";
        }

        public String getTitle() {
            int i = this.mTitleColumn;
            return i != -1 ? this.mCursor.getString(i) : "";
        }

        public String getId() {
            int i = this.mIdColumn;
            return i != -1 ? this.mCursor.getString(i) : "";
        }

        public String getSource() {
            int i = this.mSourceColumn;
            return i != -1 ? this.mCursor.getString(i) : this.mSource;
        }

        public String getLastAccessed() {
            int i = this.mLastAccessedColumn;
            return i != -1 ? this.mCursor.getString(i) : "";
        }

        public int getTrackCount() {
            int i = this.mTrackCountColumn;
            if (i != -1) {
                return this.mCursor.getInt(i);
            }
            return 0;
        }

        public int getAlbumCount() {
            int i = this.mAlbumCountColumn;
            if (i != -1) {
                return this.mCursor.getInt(i);
            }
            return 0;
        }

        public String getAsin() {
            int i = this.mAsinColumn;
            return i != -1 ? this.mCursor.getString(i) : "";
        }
    }

    public static String encodeKey(String key) {
        return Base64.encodeToString(key.getBytes(), 2);
    }

    public static String encodeKey(Uri key) {
        return encodeKey(key.toString());
    }

    public static String decodeKey(String key) {
        return new String(Base64.decode(URLDecoder.decode(key), 0));
    }

    public static void fireBulkUpdate(Context context) {
        Intent intent = new Intent("com.amazon.provider.CONTENT_BULK_UPDATE");
        intent.setDataAndType(RECENT_BASE, "vnd.android.cursor.dir/vnd.amazon.mp3.recent");
        context.sendBroadcast(intent);
    }

    public static void deleteRecentKey(Context context, String recentKey) {
        Uri.Builder builderBuildUpon = RECENT_BASE.buildUpon();
        builderBuildUpon.appendPath(encodeKey(recentKey));
        Intent intent = new Intent("com.amazon.provider.CONTENT_DELETE");
        intent.setDataAndType(builderBuildUpon.build(), "vnd.android.cursor.item/vnd.amazon.mp3.recent");
        context.sendBroadcast(intent);
    }

    public static void updateRecentKey(Context context, String recentKey) {
        Uri.Builder builderBuildUpon = RECENT_BASE.buildUpon();
        builderBuildUpon.appendPath(encodeKey(recentKey));
        Intent intent = new Intent("com.amazon.provider.CONTENT_UPDATE");
        intent.setDataAndType(builderBuildUpon.build(), "vnd.android.cursor.item/vnd.amazon.mp3.recent");
        intent.addFlags(1);
        context.sendBroadcast(intent);
    }

    private static class DrawableResult {
        private final Drawable mDrawable;
        private final boolean mIsDefault;

        public DrawableResult(Drawable d, boolean isDefault) {
            this.mDrawable = d;
            this.mIsDefault = isDefault;
        }

        public boolean isDefault() {
            return this.mIsDefault;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }
    }
}
