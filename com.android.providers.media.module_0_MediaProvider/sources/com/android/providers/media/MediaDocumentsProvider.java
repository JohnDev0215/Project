package com.android.providers.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import androidx.core.content.MimeTypeFilter;
import com.android.providers.media.util.FileUtils;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class MediaDocumentsProvider extends DocumentsProvider {
    public static final String AUTHORITY = "com.android.providers.media.documents";
    public static final String METADATA_KEY_AUDIO = "android.media.metadata.audio";
    public static final String METADATA_KEY_VIDEO = "android.media.metadata.video";
    private static final String TAG = "MediaDocumentsProvider";
    static final String TYPE_ALBUM = "album";
    static final String TYPE_ARTIST = "artist";
    static final String TYPE_AUDIO = "audio";
    static final String TYPE_AUDIO_ROOT = "audio_root";
    static final String TYPE_DOCUMENT = "document";
    static final String TYPE_DOCUMENTS_BUCKET = "documents_bucket";
    static final String TYPE_DOCUMENTS_ROOT = "documents_root";
    static final String TYPE_IMAGE = "image";
    static final String TYPE_IMAGES_BUCKET = "images_bucket";
    static final String TYPE_IMAGES_ROOT = "images_root";
    static final String TYPE_VIDEO = "video";
    static final String TYPE_VIDEOS_BUCKET = "videos_bucket";
    static final String TYPE_VIDEOS_ROOT = "videos_root";
    private static final String SUPPORTED_QUERY_ARGS = joinNewline("android:query-arg-display-name", "android:query-arg-file-size-over", "android:query-arg-last-modified-after", "android:query-arg-mime-types");
    private static final String[] DEFAULT_ROOT_PROJECTION = {"root_id", "flags", "icon", "title", "document_id", "mime_types", "query_args"};
    private static final String[] DEFAULT_DOCUMENT_PROJECTION = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size"};
    private static final String IMAGE_MIME_TYPES = joinNewline("image/*");
    private static final String VIDEO_MIME_TYPES = joinNewline("video/*");
    private static final String AUDIO_MIME_TYPES = joinNewline("audio/*", "application/ogg", "application/x-flac");
    private static final String DOCUMENT_MIME_TYPES = joinNewline("*/*");
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static volatile boolean sMediaStoreReady = $assertionsDisabled;
    private static volatile boolean sReturnedImagesEmpty = $assertionsDisabled;
    private static volatile boolean sReturnedVideosEmpty = $assertionsDisabled;
    private static volatile boolean sReturnedAudioEmpty = $assertionsDisabled;
    private static volatile boolean sReturnedDocumentsEmpty = $assertionsDisabled;
    private static final Map<String, String> IMAGE_COLUMN_MAP = new HashMap();
    private static final Map<String, String> VIDEO_COLUMN_MAP = new HashMap();
    private static final Map<String, String> AUDIO_COLUMN_MAP = new HashMap();

    private interface AlbumQuery {
        public static final int ALBUM = 1;
        public static final int ALBUM_ID = 0;
        public static final String[] PROJECTION = {"album_id", MediaDocumentsProvider.TYPE_ALBUM};
    }

    private interface ArtistQuery {
        public static final int ARTIST = 1;
        public static final String[] PROJECTION = {"_id", MediaDocumentsProvider.TYPE_ARTIST};
        public static final int _ID = 0;
    }

    private interface DocumentQuery {
        public static final int DATE_MODIFIED = 4;
        public static final int DISPLAY_NAME = 1;
        public static final int MIME_TYPE = 2;
        public static final String[] PROJECTION = {"_id", "_display_name", "mime_type", "_size", "date_modified"};
        public static final int SIZE = 3;
        public static final int _ID = 0;
    }

    private interface DocumentsBucketQuery {
        public static final int BUCKET_DISPLAY_NAME = 1;
        public static final int BUCKET_ID = 0;
        public static final int DATE_MODIFIED = 2;
        public static final String[] PROJECTION = {"bucket_id", "bucket_display_name", "date_modified", "volume_name"};
        public static final String SORT_ORDER = "bucket_id, date_modified DESC";
        public static final int VOLUME_NAME = 3;
    }

    private interface ImageQuery {
        public static final int DATE_MODIFIED = 4;
        public static final int DISPLAY_NAME = 1;
        public static final int MIME_TYPE = 2;
        public static final String[] PROJECTION = {"_id", "_display_name", "mime_type", "_size", "date_modified"};
        public static final int SIZE = 3;
        public static final int _ID = 0;
    }

    private interface ImagesBucketQuery {
        public static final int BUCKET_DISPLAY_NAME = 1;
        public static final int BUCKET_ID = 0;
        public static final int DATE_MODIFIED = 2;
        public static final String[] PROJECTION = {"bucket_id", "bucket_display_name", "date_modified", "volume_name"};
        public static final String SORT_ORDER = "bucket_id, date_modified DESC";
        public static final int VOLUME_NAME = 3;
    }

    private interface ImagesBucketThumbnailQuery {
        public static final int BUCKET_ID = 1;
        public static final int DATE_MODIFIED = 2;
        public static final String[] PROJECTION = {"_id", "bucket_id", "date_modified"};
        public static final int _ID = 0;
    }

    private interface SongQuery {
        public static final int DATE_MODIFIED = 4;
        public static final int DISPLAY_NAME = 1;
        public static final int MIME_TYPE = 2;
        public static final String[] PROJECTION = {"_id", "_display_name", "mime_type", "_size", "date_modified"};
        public static final int SIZE = 3;
        public static final int _ID = 0;
    }

    private interface VideoQuery {
        public static final int DATE_MODIFIED = 4;
        public static final int DISPLAY_NAME = 1;
        public static final int MIME_TYPE = 2;
        public static final String[] PROJECTION = {"_id", "_display_name", "mime_type", "_size", "date_modified"};
        public static final int SIZE = 3;
        public static final int _ID = 0;
    }

    private interface VideosBucketQuery {
        public static final int BUCKET_DISPLAY_NAME = 1;
        public static final int BUCKET_ID = 0;
        public static final int DATE_MODIFIED = 2;
        public static final String[] PROJECTION = {"bucket_id", "bucket_display_name", "date_modified", "volume_name"};
        public static final String SORT_ORDER = "bucket_id, date_modified DESC";
        public static final int VOLUME_NAME = 3;
    }

    private interface VideosBucketThumbnailQuery {
        public static final int BUCKET_ID = 1;
        public static final int DATE_MODIFIED = 2;
        public static final String[] PROJECTION = {"_id", "bucket_id", "date_modified"};
        public static final int _ID = 0;
    }

    static {
        IMAGE_COLUMN_MAP.put("width", "ImageWidth");
        IMAGE_COLUMN_MAP.put("height", "ImageLength");
        IMAGE_COLUMN_MAP.put("datetaken", "DateTime");
        VIDEO_COLUMN_MAP.put("duration", "android.media.metadata.DURATION");
        VIDEO_COLUMN_MAP.put("height", "ImageLength");
        VIDEO_COLUMN_MAP.put("width", "ImageWidth");
        VIDEO_COLUMN_MAP.put("datetaken", "android.media.metadata.DATE");
        AUDIO_COLUMN_MAP.put(TYPE_ARTIST, "android.media.metadata.ARTIST");
        AUDIO_COLUMN_MAP.put("composer", "android.media.metadata.COMPOSER");
        AUDIO_COLUMN_MAP.put(TYPE_ALBUM, "android.media.metadata.ALBUM");
        AUDIO_COLUMN_MAP.put("year", "android.media.metadata.YEAR");
        AUDIO_COLUMN_MAP.put("duration", "android.media.metadata.DURATION");
    }

    private static String joinNewline(String... strArr) {
        return TextUtils.join("\n", strArr);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        notifyRootsChanged(getContext());
        return true;
    }

    private void enforceShellRestrictions() {
        if (UserHandle.getAppId(Binder.getCallingUid()) == 2000 && ((UserManager) getContext().getSystemService(UserManager.class)).hasUserRestriction("no_usb_file_transfer")) {
            throw new SecurityException("Shell user cannot access files for user " + UserHandle.myUserId());
        }
    }

    private static void notifyRootsChanged(Context context) {
        context.getContentResolver().notifyChange(DocumentsContract.buildRootsUri(AUTHORITY), (ContentObserver) null, $assertionsDisabled);
    }

    static void onMediaStoreReady(Context context, String str) {
        sMediaStoreReady = true;
        notifyRootsChanged(context);
    }

    static void onMediaStoreInsert(Context context, String str, int i, long j) {
        if ("internal".equals(str)) {
            return;
        }
        if (i == 1 && sReturnedImagesEmpty) {
            sReturnedImagesEmpty = $assertionsDisabled;
            notifyRootsChanged(context);
            return;
        }
        if (i == 3 && sReturnedVideosEmpty) {
            sReturnedVideosEmpty = $assertionsDisabled;
            notifyRootsChanged(context);
        } else if (i == 2 && sReturnedAudioEmpty) {
            sReturnedAudioEmpty = $assertionsDisabled;
            notifyRootsChanged(context);
        } else if (i == 6 && sReturnedDocumentsEmpty) {
            sReturnedDocumentsEmpty = $assertionsDisabled;
            notifyRootsChanged(context);
        }
    }

    static void onMediaStoreDelete(Context context, String str, int i, long j) {
        if ("internal".equals(str)) {
            return;
        }
        if (i == 1) {
            context.revokeUriPermission(DocumentsContract.buildDocumentUri(AUTHORITY, getDocIdForIdent(TYPE_IMAGE, j)), -1);
            notifyRootsChanged(context);
            return;
        }
        if (i == 3) {
            context.revokeUriPermission(DocumentsContract.buildDocumentUri(AUTHORITY, getDocIdForIdent(TYPE_VIDEO, j)), -1);
            notifyRootsChanged(context);
        } else if (i == 2) {
            context.revokeUriPermission(DocumentsContract.buildDocumentUri(AUTHORITY, getDocIdForIdent(TYPE_AUDIO, j)), -1);
            notifyRootsChanged(context);
        } else if (i == 6) {
            context.revokeUriPermission(DocumentsContract.buildDocumentUri(AUTHORITY, getDocIdForIdent(TYPE_DOCUMENT, j)), -1);
            notifyRootsChanged(context);
        }
    }

    private static class Ident {
        public long id;
        public String type;

        private Ident() {
        }
    }

    private static Ident getIdentForDocId(String str) {
        Ident ident = new Ident();
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            ident.type = str;
            ident.id = -1L;
        } else {
            ident.type = str.substring(0, iIndexOf);
            ident.id = Long.parseLong(str.substring(iIndexOf + 1));
        }
        return ident;
    }

    private static String getDocIdForIdent(String str, long j) {
        return str + ":" + j;
    }

    private static String[] resolveRootProjection(String[] strArr) {
        return strArr != null ? strArr : DEFAULT_ROOT_PROJECTION;
    }

    private static String[] resolveDocumentProjection(String[] strArr) {
        return strArr != null ? strArr : DEFAULT_DOCUMENT_PROJECTION;
    }

    static Pair<String, String[]> buildSearchSelection(String str, String[] strArr, long j, long j2, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (!str.isEmpty()) {
            sb.append(str2 + " LIKE ?");
            arrayList.add("%" + str + "%");
        }
        if (j != -1) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append(str4 + " > " + (j / 1000));
        }
        if (j2 != -1) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append(str5 + " > " + j2);
        }
        if (strArr != null && strArr.length > 0) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("(");
            ArrayList arrayList2 = new ArrayList();
            StringBuilder sb2 = new StringBuilder();
            ArrayList arrayList3 = new ArrayList();
            for (String str6 : strArr) {
                if (!TextUtils.isEmpty(str6) && str6.endsWith("/*")) {
                    arrayList3.add(str6);
                } else {
                    if (arrayList2.size() > 0) {
                        sb2.append(",");
                    }
                    sb2.append("?");
                    arrayList2.add(str6);
                }
            }
            int i = 0;
            while (true) {
                String str7 = " OR ";
                if (i >= arrayList3.size()) {
                    break;
                }
                sb.append(str3 + " LIKE ?");
                if (i == arrayList3.size() - 1) {
                    str7 = "";
                }
                sb.append(str7);
                arrayList.add(((String) arrayList3.get(i)).substring(0, r10.length() - 1) + "%");
                i++;
            }
            if (arrayList2.size() > 0) {
                if (arrayList3.size() > 0) {
                    sb.append(" OR ");
                }
                sb.append(str3 + " IN (");
                sb.append(sb2.toString());
                sb.append(")");
                arrayList.addAll(arrayList2);
            }
            sb.append(")");
        }
        return new Pair<>(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    static Pair<String, String[]> addDocumentSelection(String str, String[] strArr) {
        String str2;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || strArr == null) {
            str2 = "";
        } else {
            str2 = str + " AND ";
            for (String str3 : strArr) {
                arrayList.add(str3);
            }
        }
        String str4 = str2 + "media_type=?";
        arrayList.add("6");
        return new Pair<>(str4, (String[]) arrayList.toArray(new String[0]));
    }

    private static boolean shouldFilterMimeType(String[] strArr, String str, List<String> list) {
        list.clear();
        if (strArr == null) {
            return $assertionsDisabled;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (TextUtils.equals(strArr[i], "*/*") || TextUtils.equals(strArr[i], str)) {
                list.clear();
                return $assertionsDisabled;
            }
            if (MimeTypeFilter.matches(strArr[i], str)) {
                list.add(strArr[i]);
            }
        }
        return true;
    }

    private Uri getUriForDocumentId(String str) {
        Ident identForDocId = getIdentForDocId(str);
        if (TYPE_IMAGE.equals(identForDocId.type)) {
            long j = identForDocId.id;
            if (j != -1) {
                return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, j);
            }
        }
        if (TYPE_VIDEO.equals(identForDocId.type)) {
            long j2 = identForDocId.id;
            if (j2 != -1) {
                return ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, j2);
            }
        }
        if (TYPE_AUDIO.equals(identForDocId.type)) {
            long j3 = identForDocId.id;
            if (j3 != -1) {
                return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, j3);
            }
        }
        if (TYPE_DOCUMENT.equals(identForDocId.type) && identForDocId.id != -1) {
            return ContentUris.withAppendedId(MediaStore.Files.EXTERNAL_CONTENT_URI, identForDocId.id);
        }
        throw new UnsupportedOperationException("Unsupported document " + str);
    }

    @Override // android.provider.DocumentsProvider
    public void deleteDocument(String str) throws FileNotFoundException {
        enforceShellRestrictions();
        Uri uriForDocumentId = getUriForDocumentId(str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            getContext().getContentResolver().delete(uriForDocumentId, null, null);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.provider.DocumentsProvider
    public Bundle getDocumentMetadata(String str) throws FileNotFoundException {
        enforceShellRestrictions();
        return getDocumentMetadataFromIndex(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle getDocumentMetadataFromIndex(java.lang.String r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaDocumentsProvider.getDocumentMetadataFromIndex(java.lang.String):android.os.Bundle");
    }

    private static Bundle extractMetadataFromCursor(Cursor cursor, Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            int columnIndex = cursor.getColumnIndex(str);
            String str2 = map.get(str);
            if ("DateTime".equals(str2)) {
                if (!cursor.isNull(columnIndex)) {
                    bundle.putString(str2, DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), "MMM dd, yyyy, hh:mm"), cursor.getLong(columnIndex)).toString());
                }
            } else {
                int type = cursor.getType(columnIndex);
                if (type == 0) {
                    Log.d(TAG, "Unsupported type, null, for col: " + str2);
                } else if (type == 1) {
                    bundle.putInt(str2, cursor.getInt(columnIndex));
                } else if (type == 2) {
                    bundle.putFloat(str2, cursor.getFloat(columnIndex));
                } else if (type == 3) {
                    bundle.putString(str2, cursor.getString(columnIndex));
                } else {
                    if (type != 4) {
                        throw new RuntimeException("Data type not supported");
                    }
                    Log.d(TAG, "Unsupported type, blob, for col: " + str2);
                }
            }
        }
        return bundle;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryRoots(String[] strArr) throws FileNotFoundException {
        MatrixCursor matrixCursor = new MatrixCursor(resolveRootProjection(strArr));
        if (sMediaStoreReady) {
            includeImagesRoot(matrixCursor);
            includeVideosRoot(matrixCursor);
            includeAudioRoot(matrixCursor);
            includeDocumentsRoot(matrixCursor);
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryDocument(String str, String[] strArr) throws FileNotFoundException {
        enforceShellRestrictions();
        ContentResolver contentResolver = getContext().getContentResolver();
        MatrixCursor matrixCursor = new MatrixCursor(resolveDocumentProjection(strArr));
        Ident identForDocId = getIdentForDocId(str);
        String[] strArr2 = {Long.toString(identForDocId.id)};
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Cursor cursorQuery = null;
        try {
            if (TYPE_IMAGES_ROOT.equals(identForDocId.type)) {
                includeImagesRootDocument(matrixCursor);
            } else if (TYPE_IMAGES_BUCKET.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImagesBucketQuery.PROJECTION, "bucket_id=?", strArr2, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeImagesBucket(matrixCursor, cursorQuery);
                }
            } else if (TYPE_IMAGE.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImageQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeImage(matrixCursor, cursorQuery);
                }
            } else if (TYPE_VIDEOS_ROOT.equals(identForDocId.type)) {
                includeVideosRootDocument(matrixCursor);
            } else if (TYPE_VIDEOS_BUCKET.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideosBucketQuery.PROJECTION, "bucket_id=?", strArr2, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeVideosBucket(matrixCursor, cursorQuery);
                }
            } else if (TYPE_VIDEO.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideoQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeVideo(matrixCursor, cursorQuery);
                }
            } else if (TYPE_AUDIO_ROOT.equals(identForDocId.type)) {
                includeAudioRootDocument(matrixCursor);
            } else if (TYPE_ARTIST.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, ArtistQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeArtist(matrixCursor, cursorQuery);
                }
            } else if (TYPE_ALBUM.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, AlbumQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeAlbum(matrixCursor, cursorQuery);
                }
            } else if (TYPE_AUDIO.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, SongQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeAudio(matrixCursor, cursorQuery);
                }
            } else if (TYPE_DOCUMENTS_ROOT.equals(identForDocId.type)) {
                includeDocumentsRootDocument(matrixCursor);
            } else if (TYPE_DOCUMENTS_BUCKET.equals(identForDocId.type)) {
                Pair<String, String[]> pairAddDocumentSelection = addDocumentSelection("bucket_id=?", strArr2);
                cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentsBucketQuery.PROJECTION, (String) pairAddDocumentSelection.first, (String[]) pairAddDocumentSelection.second, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeDocumentsBucket(matrixCursor, cursorQuery);
                }
            } else if (TYPE_DOCUMENT.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentQuery.PROJECTION, "_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                if (cursorQuery.moveToFirst()) {
                    includeDocument(matrixCursor, cursorQuery);
                }
            } else {
                throw new UnsupportedOperationException("Unsupported document " + str);
            }
            return matrixCursor;
        } finally {
            FileUtils.closeQuietly((AutoCloseable) null);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryChildDocuments(String str, String[] strArr, String str2) throws FileNotFoundException {
        Cursor cursorQuery;
        enforceShellRestrictions();
        ContentResolver contentResolver = getContext().getContentResolver();
        MatrixCursor matrixCursor = new MatrixCursor(resolveDocumentProjection(strArr));
        Ident identForDocId = getIdentForDocId(str);
        String[] strArr2 = {Long.toString(identForDocId.id)};
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            long j = Long.MIN_VALUE;
            if (TYPE_IMAGES_ROOT.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImagesBucketQuery.PROJECTION, null, null, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    long j2 = cursorQuery.getLong(0);
                    if (j != j2) {
                        includeImagesBucket(matrixCursor, cursorQuery);
                        j = j2;
                    }
                }
            } else if (TYPE_IMAGES_BUCKET.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImageQuery.PROJECTION, "bucket_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeImage(matrixCursor, cursorQuery);
                }
            } else if (TYPE_VIDEOS_ROOT.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideosBucketQuery.PROJECTION, null, null, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    long j3 = cursorQuery.getLong(0);
                    if (j != j3) {
                        includeVideosBucket(matrixCursor, cursorQuery);
                        j = j3;
                    }
                }
            } else if (TYPE_VIDEOS_BUCKET.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideoQuery.PROJECTION, "bucket_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeVideo(matrixCursor, cursorQuery);
                }
            } else if (TYPE_AUDIO_ROOT.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, ArtistQuery.PROJECTION, null, null, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeArtist(matrixCursor, cursorQuery);
                }
            } else if (TYPE_ARTIST.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Artists.Albums.getContentUri("external", identForDocId.id), AlbumQuery.PROJECTION, null, null, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeAlbum(matrixCursor, cursorQuery);
                }
            } else if (TYPE_ALBUM.equals(identForDocId.type)) {
                cursorQuery = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, SongQuery.PROJECTION, "album_id=?", strArr2, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeAudio(matrixCursor, cursorQuery);
                }
            } else if (TYPE_DOCUMENTS_ROOT.equals(identForDocId.type)) {
                Pair<String, String[]> pairAddDocumentSelection = addDocumentSelection(null, null);
                cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentsBucketQuery.PROJECTION, (String) pairAddDocumentSelection.first, (String[]) pairAddDocumentSelection.second, "bucket_id, date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    long j4 = cursorQuery.getLong(0);
                    if (j != j4) {
                        includeDocumentsBucket(matrixCursor, cursorQuery);
                        j = j4;
                    }
                }
            } else if (TYPE_DOCUMENTS_BUCKET.equals(identForDocId.type)) {
                Pair<String, String[]> pairAddDocumentSelection2 = addDocumentSelection("bucket_id=?", strArr2);
                cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentQuery.PROJECTION, (String) pairAddDocumentSelection2.first, (String[]) pairAddDocumentSelection2.second, null);
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext()) {
                    includeDocument(matrixCursor, cursorQuery);
                }
            } else {
                throw new UnsupportedOperationException("Unsupported document " + str);
            }
            FileUtils.closeQuietly(cursorQuery);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return matrixCursor;
        } catch (Throwable th) {
            FileUtils.closeQuietly((AutoCloseable) null);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryRecentDocuments(String str, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Cursor cursorQuery;
        enforceShellRestrictions();
        ContentResolver contentResolver = getContext().getContentResolver();
        MatrixCursor matrixCursor = new MatrixCursor(resolveDocumentProjection(strArr));
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        int i = bundle != null ? bundle.getInt("android:query-arg-limit", -1) : -1;
        if (i < 0) {
            i = 64;
        } else {
            Bundle bundle2 = new Bundle();
            matrixCursor.setExtras(bundle2);
            bundle2.putStringArray("android.content.extra.HONORED_ARGS", new String[]{"android:query-arg-limit"});
        }
        int i2 = i;
        try {
            if (TYPE_IMAGES_ROOT.equals(str)) {
                cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImageQuery.PROJECTION, null, null, "date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext() && matrixCursor.getCount() < i2) {
                    includeImage(matrixCursor, cursorQuery);
                }
            } else if (TYPE_VIDEOS_ROOT.equals(str)) {
                cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideoQuery.PROJECTION, null, null, "date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext() && matrixCursor.getCount() < i2) {
                    includeVideo(matrixCursor, cursorQuery);
                }
            } else if (TYPE_DOCUMENTS_ROOT.equals(str)) {
                Pair<String, String[]> pairAddDocumentSelection = addDocumentSelection(null, null);
                cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentQuery.PROJECTION, (String) pairAddDocumentSelection.first, (String[]) pairAddDocumentSelection.second, "date_modified DESC");
                matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                while (cursorQuery.moveToNext() && matrixCursor.getCount() < i2) {
                    includeDocument(matrixCursor, cursorQuery);
                }
            } else {
                throw new UnsupportedOperationException("Unsupported root " + str);
            }
            FileUtils.closeQuietly(cursorQuery);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return matrixCursor;
        } catch (Throwable th) {
            FileUtils.closeQuietly((AutoCloseable) null);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r23v0 */
    /* JADX WARN: Type inference failed for: r23v1, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r23v2 */
    @Override // android.provider.DocumentsProvider
    public Cursor querySearchDocuments(String str, String[] strArr, Bundle bundle) throws Throwable {
        Cursor cursorQuery;
        enforceShellRestrictions();
        ContentResolver contentResolver = getContext().getContentResolver();
        MatrixCursor matrixCursor = new MatrixCursor(resolveDocumentProjection(strArr));
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        String string = bundle.getString("android:query-arg-display-name", "");
        long j = bundle.getLong("android:query-arg-last-modified-after", -1L);
        long j2 = bundle.getLong("android:query-arg-file-size-over", -1L);
        String[] stringArray = bundle.getStringArray("android:query-arg-mime-types");
        ArrayList arrayList = new ArrayList();
        ?? r23 = 0;
        cursor = null;
        cursor = null;
        Cursor cursor = null;
        try {
            try {
                if (TYPE_IMAGES_ROOT.equals(str)) {
                    boolean zShouldFilterMimeType = shouldFilterMimeType(stringArray, "image/*", arrayList);
                    if (stringArray == null || !zShouldFilterMimeType || arrayList.size() > 0) {
                        Pair<String, String[]> pairBuildSearchSelection = buildSearchSelection(string, (String[]) arrayList.toArray(new String[0]), j, j2, "_display_name", "mime_type", "date_modified", "_size");
                        cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImageQuery.PROJECTION, (String) pairBuildSearchSelection.first, (String[]) pairBuildSearchSelection.second, "date_modified DESC");
                        matrixCursor.setNotificationUri(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        while (cursorQuery.moveToNext()) {
                            includeImage(matrixCursor, cursorQuery);
                        }
                        cursor = cursorQuery;
                    }
                } else if (TYPE_VIDEOS_ROOT.equals(str)) {
                    boolean zShouldFilterMimeType2 = shouldFilterMimeType(stringArray, "video/*", arrayList);
                    if (stringArray == null || !zShouldFilterMimeType2 || arrayList.size() > 0) {
                        Pair<String, String[]> pairBuildSearchSelection2 = buildSearchSelection(string, (String[]) arrayList.toArray(new String[0]), j, j2, "_display_name", "mime_type", "date_modified", "_size");
                        cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideoQuery.PROJECTION, (String) pairBuildSearchSelection2.first, (String[]) pairBuildSearchSelection2.second, "date_modified DESC");
                        matrixCursor.setNotificationUri(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        while (cursorQuery.moveToNext()) {
                            includeVideo(matrixCursor, cursorQuery);
                        }
                        cursor = cursorQuery;
                    }
                } else {
                    if (TYPE_AUDIO_ROOT.equals(str)) {
                        boolean zShouldFilterMimeType3 = shouldFilterMimeType(stringArray, "audio/*", arrayList);
                        if (stringArray == null || !zShouldFilterMimeType3 || arrayList.size() > 0) {
                            Pair<String, String[]> pairBuildSearchSelection3 = buildSearchSelection(string, (String[]) arrayList.toArray(new String[0]), j, j2, "_display_name", "mime_type", "date_modified", "_size");
                            cursorQuery = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, SongQuery.PROJECTION, (String) pairBuildSearchSelection3.first, (String[]) pairBuildSearchSelection3.second, "date_modified DESC");
                            matrixCursor.setNotificationUri(contentResolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                            while (cursorQuery.moveToNext()) {
                                includeAudio(matrixCursor, cursorQuery);
                            }
                        }
                    } else if (TYPE_DOCUMENTS_ROOT.equals(str)) {
                        Pair<String, String[]> pairBuildSearchSelection4 = buildSearchSelection(string, stringArray, j, j2, "_display_name", "mime_type", "date_modified", "_size");
                        Pair<String, String[]> pairAddDocumentSelection = addDocumentSelection((String) pairBuildSearchSelection4.first, (String[]) pairBuildSearchSelection4.second);
                        cursorQuery = contentResolver.query(MediaStore.Files.EXTERNAL_CONTENT_URI, DocumentQuery.PROJECTION, (String) pairAddDocumentSelection.first, (String[]) pairAddDocumentSelection.second, "date_modified DESC");
                        matrixCursor.setNotificationUri(contentResolver, MediaStore.Files.EXTERNAL_CONTENT_URI);
                        while (cursorQuery.moveToNext()) {
                            includeDocument(matrixCursor, cursorQuery);
                        }
                    } else {
                        throw new UnsupportedOperationException("Unsupported root " + str);
                    }
                    cursor = cursorQuery;
                }
                FileUtils.closeQuietly(cursor);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                String[] handledQueryArguments = getHandledQueryArguments(bundle);
                if (handledQueryArguments.length > 0) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArray("android.content.extra.HONORED_ARGS", handledQueryArguments);
                    matrixCursor.setExtras(bundle2);
                }
                return matrixCursor;
            } catch (Throwable th) {
                th = th;
                r23 = str;
                FileUtils.closeQuietly((AutoCloseable) r23);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String[] getHandledQueryArguments(Bundle bundle) {
        if (bundle == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        if (bundle.keySet().contains("android:query-arg-exclude-media")) {
            arrayList.add("android:query-arg-exclude-media");
        }
        if (bundle.keySet().contains("android:query-arg-display-name")) {
            arrayList.add("android:query-arg-display-name");
        }
        if (bundle.keySet().contains("android:query-arg-file-size-over")) {
            arrayList.add("android:query-arg-file-size-over");
        }
        if (bundle.keySet().contains("android:query-arg-last-modified-after")) {
            arrayList.add("android:query-arg-last-modified-after");
        }
        if (bundle.keySet().contains("android:query-arg-mime-types")) {
            arrayList.add("android:query-arg-mime-types");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @Override // android.provider.DocumentsProvider
    public ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        enforceShellRestrictions();
        Uri uriForDocumentId = getUriForDocumentId(str);
        if (!"r".equals(str2)) {
            throw new IllegalArgumentException("Media is read-only");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getContext().getContentResolver().openFileDescriptor(uriForDocumentId, str2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.provider.DocumentsProvider
    public AssetFileDescriptor openDocumentThumbnail(String str, Point point, CancellationSignal cancellationSignal) throws FileNotFoundException {
        enforceShellRestrictions();
        Ident identForDocId = getIdentForDocId(str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (TYPE_IMAGES_BUCKET.equals(identForDocId.type)) {
                return openOrCreateImageThumbnailCleared(getImageForBucketCleared(identForDocId.id), point, cancellationSignal);
            }
            if (TYPE_IMAGE.equals(identForDocId.type)) {
                return openOrCreateImageThumbnailCleared(identForDocId.id, point, cancellationSignal);
            }
            if (TYPE_VIDEOS_BUCKET.equals(identForDocId.type)) {
                return openOrCreateVideoThumbnailCleared(getVideoForBucketCleared(identForDocId.id), point, cancellationSignal);
            }
            if (TYPE_VIDEO.equals(identForDocId.type)) {
                return openOrCreateVideoThumbnailCleared(identForDocId.id, point, cancellationSignal);
            }
            throw new UnsupportedOperationException("Unsupported document " + str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private boolean isEmpty(Uri uri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Cursor cursorQuery = contentResolver.query(uri, new String[]{"COUNT(_id)"}, null, null, null);
            try {
                if (!cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return true;
                }
                boolean z = cursorQuery.getInt(0) == 0;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return z;
            } finally {
            }
        } finally {
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
    }

    private void includeImagesRoot(MatrixCursor matrixCursor) {
        int i;
        if (isEmpty(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)) {
            i = 78;
            sReturnedImagesEmpty = true;
        } else {
            i = 14;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", TYPE_IMAGES_ROOT);
        rowBuilderNewRow.add("flags", Integer.valueOf(i));
        rowBuilderNewRow.add("title", getContext().getString(R.string.root_images));
        rowBuilderNewRow.add("document_id", TYPE_IMAGES_ROOT);
        rowBuilderNewRow.add("mime_types", IMAGE_MIME_TYPES);
        rowBuilderNewRow.add("query_args", SUPPORTED_QUERY_ARGS);
    }

    private void includeVideosRoot(MatrixCursor matrixCursor) {
        int i;
        if (isEmpty(MediaStore.Video.Media.EXTERNAL_CONTENT_URI)) {
            i = 78;
            sReturnedVideosEmpty = true;
        } else {
            i = 14;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", TYPE_VIDEOS_ROOT);
        rowBuilderNewRow.add("flags", Integer.valueOf(i));
        rowBuilderNewRow.add("title", getContext().getString(R.string.root_videos));
        rowBuilderNewRow.add("document_id", TYPE_VIDEOS_ROOT);
        rowBuilderNewRow.add("mime_types", VIDEO_MIME_TYPES);
        rowBuilderNewRow.add("query_args", SUPPORTED_QUERY_ARGS);
    }

    private void includeAudioRoot(MatrixCursor matrixCursor) {
        int i;
        if (isEmpty(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)) {
            i = 74;
            sReturnedAudioEmpty = true;
        } else {
            i = 10;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", TYPE_AUDIO_ROOT);
        rowBuilderNewRow.add("flags", Integer.valueOf(i));
        rowBuilderNewRow.add("title", getContext().getString(R.string.root_audio));
        rowBuilderNewRow.add("document_id", TYPE_AUDIO_ROOT);
        rowBuilderNewRow.add("mime_types", AUDIO_MIME_TYPES);
        rowBuilderNewRow.add("query_args", SUPPORTED_QUERY_ARGS);
    }

    private void includeDocumentsRoot(MatrixCursor matrixCursor) {
        int i;
        if (isEmpty(MediaStore.Files.EXTERNAL_CONTENT_URI)) {
            i = 78;
            sReturnedDocumentsEmpty = true;
        } else {
            i = 14;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", TYPE_DOCUMENTS_ROOT);
        rowBuilderNewRow.add("flags", Integer.valueOf(i));
        rowBuilderNewRow.add("title", getContext().getString(R.string.root_documents));
        rowBuilderNewRow.add("document_id", TYPE_DOCUMENTS_ROOT);
        rowBuilderNewRow.add("mime_types", DOCUMENT_MIME_TYPES);
        rowBuilderNewRow.add("query_args", SUPPORTED_QUERY_ARGS);
    }

    private void includeImagesRootDocument(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", TYPE_IMAGES_ROOT);
        rowBuilderNewRow.add("_display_name", getContext().getString(R.string.root_images));
        rowBuilderNewRow.add("flags", 48);
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeVideosRootDocument(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", TYPE_VIDEOS_ROOT);
        rowBuilderNewRow.add("_display_name", getContext().getString(R.string.root_videos));
        rowBuilderNewRow.add("flags", 48);
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeAudioRootDocument(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", TYPE_AUDIO_ROOT);
        rowBuilderNewRow.add("_display_name", getContext().getString(R.string.root_audio));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeDocumentsRootDocument(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", TYPE_DOCUMENTS_ROOT);
        rowBuilderNewRow.add("_display_name", getContext().getString(R.string.root_documents));
        rowBuilderNewRow.add("flags", 48);
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeImagesBucket(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_IMAGES_BUCKET, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cleanUpMediaBucketName(cursor.getString(1), cursor.getString(3)));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(2) * 1000));
        rowBuilderNewRow.add("flags", 49);
    }

    private void includeImage(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_IMAGE, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cursor.getString(1));
        rowBuilderNewRow.add("_size", Long.valueOf(cursor.getLong(3)));
        rowBuilderNewRow.add("mime_type", cursor.getString(2));
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(4) * 1000));
        rowBuilderNewRow.add("flags", 16389);
    }

    private void includeVideosBucket(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_VIDEOS_BUCKET, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cleanUpMediaBucketName(cursor.getString(1), cursor.getString(3)));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(2) * 1000));
        rowBuilderNewRow.add("flags", 49);
    }

    private void includeVideo(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_VIDEO, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cursor.getString(1));
        rowBuilderNewRow.add("_size", Long.valueOf(cursor.getLong(3)));
        rowBuilderNewRow.add("mime_type", cursor.getString(2));
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(4) * 1000));
        rowBuilderNewRow.add("flags", 16389);
    }

    private void includeDocumentsBucket(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_DOCUMENTS_BUCKET, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cleanUpMediaBucketName(cursor.getString(1), cursor.getString(3)));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(2) * 1000));
        rowBuilderNewRow.add("flags", 48);
    }

    private void includeDocument(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_DOCUMENT, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cursor.getString(1));
        rowBuilderNewRow.add("_size", Long.valueOf(cursor.getLong(3)));
        rowBuilderNewRow.add("mime_type", cursor.getString(2));
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(4) * 1000));
        rowBuilderNewRow.add("flags", 4);
    }

    private void includeArtist(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_ARTIST, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cleanUpMediaDisplayName(cursor.getString(1)));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeAlbum(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_ALBUM, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cleanUpMediaDisplayName(cursor.getString(1)));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
    }

    private void includeAudio(MatrixCursor matrixCursor, Cursor cursor) {
        String docIdForIdent = getDocIdForIdent(TYPE_AUDIO, cursor.getLong(0));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", docIdForIdent);
        rowBuilderNewRow.add("_display_name", cursor.getString(1));
        rowBuilderNewRow.add("_size", Long.valueOf(cursor.getLong(3)));
        rowBuilderNewRow.add("mime_type", cursor.getString(2));
        rowBuilderNewRow.add("last_modified", Long.valueOf(cursor.getLong(4) * 1000));
        rowBuilderNewRow.add("flags", 16388);
    }

    private long getImageForBucketCleared(long j) throws FileNotFoundException {
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ImagesBucketThumbnailQuery.PROJECTION, "bucket_id=" + j, null, "date_modified DESC");
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
            FileUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("No video found for bucket");
        } finally {
            FileUtils.closeQuietly(cursorQuery);
        }
    }

    private AssetFileDescriptor openOrCreateImageThumbnailCleared(long j, Point point, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.content.extra.SIZE", point);
        return getContext().getContentResolver().openTypedAssetFile(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, j), "image/*", bundle, cancellationSignal);
    }

    private long getVideoForBucketCleared(long j) throws FileNotFoundException {
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VideosBucketThumbnailQuery.PROJECTION, "bucket_id=" + j, null, "date_modified DESC");
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
            FileUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("No video found for bucket");
        } finally {
            FileUtils.closeQuietly(cursorQuery);
        }
    }

    private AssetFileDescriptor openOrCreateVideoThumbnailCleared(long j, Point point, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.content.extra.SIZE", point);
        return getContext().getContentResolver().openTypedAssetFile(ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, j), "image/*", bundle, cancellationSignal);
    }

    private String cleanUpMediaDisplayName(String str) {
        return !"<unknown>".equals(str) ? str : getContext().getResources().getString(R.string.unknown);
    }

    private String cleanUpMediaBucketName(String str, String str2) {
        return !TextUtils.isEmpty(str) ? str : !Objects.equals(str2, "external_primary") ? str2 : getContext().getResources().getString(R.string.unknown);
    }
}
