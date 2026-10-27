package com.android.providers.downloads;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.UriPermission;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.media.MediaFile;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.FileObserver;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.Downloads;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.content.FileSystemProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public class DownloadStorageProvider extends FileSystemProvider {
    private DownloadManager mDm;
    private static final String[] DEFAULT_ROOT_PROJECTION = {"root_id", "flags", "icon", "title", "document_id", "query_args"};
    private static final String[] DEFAULT_DOCUMENT_PROJECTION = {"document_id", "mime_type", "_display_name", "summary", "last_modified", "flags", "_size"};

    public boolean onCreate() {
        super.onCreate(DEFAULT_DOCUMENT_PROJECTION);
        DownloadManager downloadManager = (DownloadManager) getContext().getSystemService("download");
        this.mDm = downloadManager;
        downloadManager.setAccessAllDownloads(true);
        this.mDm.setAccessFilename(true);
        return true;
    }

    private static String[] resolveRootProjection(String[] strArr) {
        return strArr != null ? strArr : DEFAULT_ROOT_PROJECTION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] resolveDocumentProjection(String[] strArr) {
        return strArr != null ? strArr : DEFAULT_DOCUMENT_PROJECTION;
    }

    private void copyNotificationUri(MatrixCursor matrixCursor, Cursor cursor) {
        List<Uri> notificationUris = cursor.getNotificationUris();
        if (notificationUris != null) {
            matrixCursor.setNotificationUris(getContext().getContentResolver(), notificationUris);
        }
    }

    static void onDownloadProviderDelete(Context context, long j) {
        context.revokeUriPermission(DocumentsContract.buildDocumentUri("com.android.providers.downloads.documents", Long.toString(j)), -1);
    }

    static void onMediaProviderDownloadsDelete(Context context, long[] jArr, String[] strArr) {
        for (int i = 0; i < jArr.length; i++) {
            context.revokeUriPermission(DocumentsContract.buildDocumentUri("com.android.providers.downloads.documents", MediaStoreDownloadsHelper.getDocIdForMediaStoreDownload(jArr[i], strArr[i] == null)), -1);
        }
    }

    static void revokeAllMediaStoreUriPermissions(Context context) {
        List outgoingUriPermissions = context.getContentResolver().getOutgoingUriPermissions();
        int size = outgoingUriPermissions.size();
        StringBuilder sb = new StringBuilder("Revoking permissions for uris: ");
        for (int i = 0; i < size; i++) {
            Uri uri = ((UriPermission) outgoingUriPermissions.get(i)).getUri();
            if ("com.android.providers.downloads.documents".equals(uri.getAuthority()) && MediaStoreDownloadsHelper.isMediaStoreDownload(DocumentsContract.getDocumentId(uri))) {
                context.revokeUriPermission(uri, -1);
                sb.append(uri + ",");
            }
        }
        Log.d("DownloadStorageProvider", sb.toString());
    }

    public Cursor queryRoots(String[] strArr) throws FileNotFoundException {
        getPublicDownloadsDirectory().mkdirs();
        MatrixCursor matrixCursor = new MatrixCursor(resolveRootProjection(strArr));
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("root_id", "downloads");
        rowBuilderNewRow.add("flags", 15);
        rowBuilderNewRow.add("icon", Integer.valueOf(R.mipmap.ic_launcher_download));
        rowBuilderNewRow.add("title", getContext().getString(R.string.root_downloads));
        rowBuilderNewRow.add("document_id", "downloads");
        rowBuilderNewRow.add("query_args", FileSystemProvider.SUPPORTED_QUERY_ARGS);
        return matrixCursor;
    }

    public DocumentsContract.Path findDocumentPath(String str, String str2) throws FileNotFoundException {
        String str3 = str == null ? "downloads" : null;
        if (str == null) {
            str = "downloads";
        }
        return new DocumentsContract.Path(str3, findDocumentPath(getFileForDocId(str), getFileForDocId(str2)));
    }

    public String createDocument(String str, String str2, String str3) throws FileNotFoundException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String strCreateDocument = super.createDocument(str, str2, str3);
            if (!"vnd.android.document/directory".equals(str2) && !RawDocumentsHelper.isRawDocId(str) && !MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                File fileForDocId = getFileForDocId(strCreateDocument);
                strCreateDocument = Long.toString(this.mDm.addCompletedDownload(fileForDocId.getName(), fileForDocId.getName(), true, str2, fileForDocId.getAbsolutePath(), 0L, false, true));
            }
            return strCreateDocument;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void deleteDocument(String str) throws FileNotFoundException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!RawDocumentsHelper.isRawDocId(str) && !MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                if (this.mDm.remove(Long.parseLong(str)) == 1) {
                    return;
                }
                throw new IllegalStateException("Failed to delete " + str);
            }
            super.deleteDocument(str);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public String renameDocument(String str, String str2) throws FileNotFoundException {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (!RawDocumentsHelper.isRawDocId(str) && !MediaStoreDownloadsHelper.isMediaStoreDownloadDir(str)) {
                String strBuildValidFatFilename = FileUtils.buildValidFatFilename(str2);
                if (MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                    return renameMediaStoreDownload(str, strBuildValidFatFilename);
                }
                if (!this.mDm.rename(getContext(), Long.parseLong(str), strBuildValidFatFilename)) {
                    throw new IllegalStateException("Failed to rename to " + strBuildValidFatFilename + " in downloadsManager");
                }
                return null;
            }
            return super.renameDocument(str, str2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.android.internal.content.FileSystemProvider, com.android.providers.downloads.DownloadStorageProvider] */
    public Cursor queryDocument(String str, String[] strArr) throws Throwable {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ?? r2 = 0;
        ?? r22 = 0;
        try {
            if (RawDocumentsHelper.isRawDocId(str)) {
                Cursor cursorQueryDocument = super.queryDocument((String) str, strArr);
                IoUtils.closeQuietly((AutoCloseable) null);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                return cursorQueryDocument;
            }
            DownloadsCursor downloadsCursor = new DownloadsCursor(strArr, getContext().getContentResolver());
            if ("downloads".equals(str)) {
                includeDefaultDocument(downloadsCursor);
            } else {
                try {
                    if (MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                        Cursor cursorQuery = getContext().getContentResolver().query(MediaStoreDownloadsHelper.getMediaStoreUri(str), null, null, null);
                        copyNotificationUri(downloadsCursor, cursorQuery);
                        boolean zMoveToFirst = cursorQuery.moveToFirst();
                        str = cursorQuery;
                        if (zMoveToFirst) {
                            includeDownloadFromMediaStore(downloadsCursor, cursorQuery, null, false);
                            str = cursorQuery;
                        }
                    } else {
                        Cursor cursorQuery2 = this.mDm.query(new DownloadManager.Query().setFilterById(Long.parseLong(str)));
                        copyNotificationUri(downloadsCursor, cursorQuery2);
                        boolean zMoveToFirst2 = cursorQuery2.moveToFirst();
                        str = cursorQuery2;
                        if (zMoveToFirst2) {
                            includeDownloadFromCursor(downloadsCursor, cursorQuery2, null, null);
                            str = cursorQuery2;
                        }
                    }
                    r22 = str;
                } catch (Throwable th) {
                    th = th;
                    r2 = str;
                    IoUtils.closeQuietly((AutoCloseable) r2);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
            downloadsCursor.start();
            IoUtils.closeQuietly((AutoCloseable) r22);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return downloadsCursor;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Cursor queryChildDocuments(String str, String[] strArr, String str2) throws FileNotFoundException {
        return queryChildDocuments(str, strArr, str2, false);
    }

    public Cursor queryChildDocumentsForManage(String str, String[] strArr, String str2) throws FileNotFoundException {
        return queryChildDocuments(str, strArr, str2, true);
    }

    private Cursor queryChildDocuments(String str, String[] strArr, String str2, boolean z) throws Throwable {
        Cursor cursorQuery;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Cursor cursor = null;
        try {
            if (RawDocumentsHelper.isRawDocId(str)) {
                Cursor cursorQueryChildDocuments = super.queryChildDocuments(str, strArr, str2);
                IoUtils.closeQuietly((AutoCloseable) null);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                return cursorQueryChildDocuments;
            }
            DownloadsCursor downloadsCursor = new DownloadsCursor(strArr, getContext().getContentResolver());
            ArrayList<Uri> arrayList = new ArrayList<>();
            if (MediaStoreDownloadsHelper.isMediaStoreDownloadDir(str)) {
                includeDownloadsFromMediaStore(downloadsCursor, null, null, arrayList, MediaStoreDownloadsHelper.getMediaStoreIdString(str), -1, z);
            } else {
                if (z) {
                    cursorQuery = this.mDm.query(new DownloadManager.Query().setOnlyIncludeVisibleInDownloadsUi(true));
                } else {
                    cursorQuery = this.mDm.query(new DownloadManager.Query().setOnlyIncludeVisibleInDownloadsUi(true).setFilterByStatus(8));
                }
                Cursor cursor2 = cursorQuery;
                try {
                    Set<String> hashSet = new HashSet<>();
                    while (cursor2.moveToNext()) {
                        includeDownloadFromCursor(downloadsCursor, cursor2, hashSet, null);
                    }
                    arrayList.add(cursor2.getNotificationUri());
                    includeDownloadsFromMediaStore(downloadsCursor, null, hashSet, arrayList, null, -1, z);
                    includeFilesFromSharedStorage(downloadsCursor, hashSet, null);
                    cursor = cursor2;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursor2;
                    IoUtils.closeQuietly(cursor);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
            downloadsCursor.setNotificationUris(getContext().getContentResolver(), arrayList);
            downloadsCursor.start();
            IoUtils.closeQuietly(cursor);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return downloadsCursor;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Cursor queryRecentDocuments(String str, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) throws Throwable {
        int i;
        DownloadsCursor downloadsCursor = new DownloadsCursor(strArr, getContext().getContentResolver());
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        int i2 = 12;
        if (bundle != null && (i = bundle.getInt("android:query-arg-limit", -1)) >= 0) {
            Bundle bundle2 = new Bundle();
            downloadsCursor.setExtras(bundle2);
            bundle2.putStringArray("android.content.extra.HONORED_ARGS", new String[]{"android:query-arg-limit"});
            i2 = i;
        }
        ArrayList<Uri> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mDm.query(new DownloadManager.Query().setOnlyIncludeVisibleInDownloadsUi(true).setFilterByStatus(8));
            try {
                Set<String> hashSet = new HashSet<>();
                while (cursorQuery.moveToNext() && downloadsCursor.getCount() < i2) {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("media_type"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("mediaprovider_uri"));
                    if (string != null && ((!MediaFile.isImageMimeType(string) && !MediaFile.isVideoMimeType(string) && !MediaFile.isDocumentMimeType(string)) || TextUtils.isEmpty(string2))) {
                        includeDownloadFromCursor(downloadsCursor, cursorQuery, hashSet, null);
                    }
                }
                arrayList.add(cursorQuery.getNotificationUri());
                Bundle bundle3 = new Bundle();
                bundle3.putBoolean("android:query-arg-exclude-media", true);
                includeDownloadsFromMediaStore(downloadsCursor, bundle3, hashSet, arrayList, null, i2 - downloadsCursor.getCount(), false);
                IoUtils.closeQuietly(cursorQuery);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                downloadsCursor.setNotificationUris(getContext().getContentResolver(), arrayList);
                downloadsCursor.start();
                return downloadsCursor;
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
                IoUtils.closeQuietly(cursor);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Cursor querySearchDocuments(String str, String[] strArr, Bundle bundle) throws Throwable {
        DownloadsCursor downloadsCursor = new DownloadsCursor(strArr, getContext().getContentResolver());
        ArrayList<Uri> arrayList = new ArrayList<>();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mDm.query(new DownloadManager.Query().setOnlyIncludeVisibleInDownloadsUi(true).setFilterByString(DocumentsContract.getSearchDocumentsQuery(bundle)));
            try {
                Set<String> hashSet = new HashSet<>();
                while (cursorQuery.moveToNext()) {
                    includeDownloadFromCursor(downloadsCursor, cursorQuery, hashSet, bundle);
                }
                arrayList.add(cursorQuery.getNotificationUri());
                includeDownloadsFromMediaStore(downloadsCursor, bundle, hashSet, arrayList, null, -1, true);
                includeSearchFilesFromSharedStorage(downloadsCursor, strArr, hashSet, bundle);
                IoUtils.closeQuietly(cursorQuery);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                String[] handledQueryArguments = DocumentsContract.getHandledQueryArguments(bundle);
                if (handledQueryArguments.length > 0) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArray("android.content.extra.HONORED_ARGS", handledQueryArguments);
                    downloadsCursor.setExtras(bundle2);
                }
                downloadsCursor.setNotificationUris(getContext().getContentResolver(), arrayList);
                downloadsCursor.start();
                return downloadsCursor;
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
                IoUtils.closeQuietly(cursor);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void includeSearchFilesFromSharedStorage(DownloadsCursor downloadsCursor, String[] strArr, Set<String> set, Bundle bundle) throws FileNotFoundException {
        Cursor cursorQuerySearchDocuments = super.querySearchDocuments(getPublicDownloadsDirectory(), strArr, set, bundle);
        try {
            boolean z = bundle.getBoolean("android:query-arg-exclude-media", false);
            while (cursorQuerySearchDocuments.moveToNext()) {
                String string = cursorQuerySearchDocuments.getString(cursorQuerySearchDocuments.getColumnIndexOrThrow("mime_type"));
                if (!z || !isMediaMimeType(string)) {
                    includeFileFromSharedStorage(downloadsCursor, getFileForDocId(cursorQuerySearchDocuments.getString(cursorQuerySearchDocuments.getColumnIndexOrThrow("document_id"))));
                }
            }
            if (cursorQuerySearchDocuments != null) {
                cursorQuerySearchDocuments.close();
            }
        } catch (Throwable th) {
            if (cursorQuerySearchDocuments != null) {
                try {
                    cursorQuerySearchDocuments.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public String getDocumentType(String str) throws FileNotFoundException {
        Uri downloadUri;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (RawDocumentsHelper.isRawDocId(str)) {
                return super.getDocumentType(str);
            }
            ContentResolver contentResolver = getContext().getContentResolver();
            if (MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                downloadUri = MediaStoreDownloadsHelper.getMediaStoreUri(str);
            } else {
                downloadUri = this.mDm.getDownloadUri(Long.parseLong(str));
            }
            return contentResolver.getType(downloadUri);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Uri downloadUri;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (RawDocumentsHelper.isRawDocId(str)) {
                return super.openDocument(str, str2, cancellationSignal);
            }
            ContentResolver contentResolver = getContext().getContentResolver();
            if (MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
                downloadUri = MediaStoreDownloadsHelper.getMediaStoreUri(str);
            } else {
                downloadUri = this.mDm.getDownloadUri(Long.parseLong(str));
            }
            return contentResolver.openFileDescriptor(downloadUri, str2, cancellationSignal);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    protected File getFileForDocId(String str, boolean z) throws Throwable {
        if (RawDocumentsHelper.isRawDocId(str)) {
            return new File(RawDocumentsHelper.getAbsoluteFilePath(str));
        }
        if (MediaStoreDownloadsHelper.isMediaStoreDownload(str)) {
            return getFileForMediaStoreDownload(str);
        }
        if ("downloads".equals(str)) {
            return getPublicDownloadsDirectory();
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mDm.query(new DownloadManager.Query().setFilterById(Long.parseLong(str)));
            try {
                String string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("local_filename")) : null;
                IoUtils.closeQuietly(cursorQuery);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                if (string == null) {
                    throw new IllegalStateException("File has no filepath. Could not be found.");
                }
                return new File(string);
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
                IoUtils.closeQuietly(cursor);
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected String getDocIdForFile(File file) throws FileNotFoundException {
        return RawDocumentsHelper.getDocIdForFile(file);
    }

    protected Uri buildNotificationUri(String str) {
        return DocumentsContract.buildChildDocumentsUri("com.android.providers.downloads.documents", str);
    }

    private static boolean isMediaMimeType(String str) {
        return MediaFile.isImageMimeType(str) || MediaFile.isVideoMimeType(str) || MediaFile.isAudioMimeType(str) || MediaFile.isDocumentMimeType(str);
    }

    private void includeDefaultDocument(MatrixCursor matrixCursor) {
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", "downloads");
        rowBuilderNewRow.add("_display_name", getContext().getString(R.string.root_downloads));
        rowBuilderNewRow.add("mime_type", "vnd.android.document/directory");
        rowBuilderNewRow.add("flags", 40);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0119 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void includeDownloadFromCursor(android.database.MatrixCursor r22, android.database.Cursor r23, java.util.Set<java.lang.String> r24, android.os.Bundle r25) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadStorageProvider.includeDownloadFromCursor(android.database.MatrixCursor, android.database.Cursor, java.util.Set, android.os.Bundle):void");
    }

    private void includeDownload(MatrixCursor matrixCursor, String str, String str2, String str3, long j, String str4, long j2, int i, boolean z) {
        int i2 = i | 6;
        if (str4.startsWith("image/")) {
            i2 |= 1;
        }
        if (typeSupportsMetadata(str4)) {
            i2 |= 16384;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", str);
        rowBuilderNewRow.add("_display_name", str2);
        rowBuilderNewRow.add("summary", str3);
        rowBuilderNewRow.add("_size", j == -1 ? null : Long.valueOf(j));
        rowBuilderNewRow.add("mime_type", str4);
        rowBuilderNewRow.add("flags", Integer.valueOf(i2));
        if (z) {
            return;
        }
        rowBuilderNewRow.add("last_modified", Long.valueOf(j2));
    }

    private void includeFilesFromSharedStorage(DownloadsCursor downloadsCursor, Set<String> set, String str) throws FileNotFoundException {
        for (File file : FileUtils.listFilesOrEmpty(getPublicDownloadsDirectory())) {
            boolean zContains = set.contains(file.getAbsolutePath());
            boolean z = str == null || file.getName().contains(str);
            if (!zContains && z) {
                includeFileFromSharedStorage(downloadsCursor, file);
            }
        }
    }

    private void includeFileFromSharedStorage(MatrixCursor matrixCursor, File file) throws FileNotFoundException {
        includeFile(matrixCursor, (String) null, file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File getPublicDownloadsDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    private String renameMediaStoreDownload(String str, String str2) {
        File fileForMediaStoreDownload = getFileForMediaStoreDownload(str);
        File file = new File(fileForMediaStoreDownload.getParentFile(), str2);
        if (file.exists()) {
            throw new IllegalStateException("Already exists " + file);
        }
        if (!fileForMediaStoreDownload.renameTo(file)) {
            throw new IllegalStateException("Failed to rename from " + fileForMediaStoreDownload + " to " + file);
        }
        if (!file.isDirectory() && str2.toLowerCase(Locale.ROOT).endsWith(".nomedia")) {
            return getDocIdForMediaStoreDownloadUri(MediaStore.scanFile(getContext().getContentResolver(), file.getParentFile()), true);
        }
        MediaStore.scanFile(getContext().getContentResolver(), fileForMediaStoreDownload);
        return getDocIdForMediaStoreDownloadUri(MediaStore.scanFile(getContext().getContentResolver(), file), file.isDirectory());
    }

    private static String getDocIdForMediaStoreDownloadUri(Uri uri, boolean z) {
        if (uri != null) {
            return MediaStoreDownloadsHelper.getDocIdForMediaStoreDownload(Long.parseLong(uri.getLastPathSegment()), z);
        }
        return null;
    }

    private File getFileForMediaStoreDownload(String str) {
        Uri mediaStoreUri = MediaStoreDownloadsHelper.getMediaStoreUri(str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                Cursor cursorQueryForSingleItem = queryForSingleItem(mediaStoreUri, new String[]{"_data"}, null, null, null);
                try {
                    String string = cursorQueryForSingleItem.getString(0);
                    if (string == null) {
                        throw new IllegalStateException("Missing _data for " + mediaStoreUri);
                    }
                    File file = new File(string);
                    if (cursorQueryForSingleItem != null) {
                        cursorQueryForSingleItem.close();
                    }
                    return file;
                } catch (Throwable th) {
                    if (cursorQueryForSingleItem != null) {
                        try {
                            cursorQueryForSingleItem.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private Pair<String, String> getRelativePathAndDisplayNameForDownload(long j) {
        Uri uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Downloads.getContentUri("external"), j);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                Cursor cursorQueryForSingleItem = queryForSingleItem(uriWithAppendedId, new String[]{"relative_path", "_display_name"}, null, null, null);
                try {
                    String string = cursorQueryForSingleItem.getString(0);
                    String string2 = cursorQueryForSingleItem.getString(1);
                    if (string == null || string2 == null) {
                        throw new IllegalStateException("relative_path and _display_name should not be null for " + uriWithAppendedId);
                    }
                    Pair<String, String> pairCreate = Pair.create(string, string2);
                    if (cursorQueryForSingleItem != null) {
                        cursorQueryForSingleItem.close();
                    }
                    return pairCreate;
                } catch (Throwable th) {
                    if (cursorQueryForSingleItem != null) {
                        try {
                            cursorQueryForSingleItem.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private Cursor queryForSingleItem(Uri uri, String[] strArr, String str, String[] strArr2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Cursor cursorQuery = getContext().getContentResolver().query(uri, strArr, ContentResolver.createSqlQueryBundle(str, strArr2, null), cancellationSignal);
        if (cursorQuery == null) {
            throw new FileNotFoundException("Missing cursor for " + uri);
        }
        if (cursorQuery.getCount() < 1) {
            IoUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("No item at " + uri);
        }
        if (cursorQuery.getCount() > 1) {
            IoUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("Multiple items at " + uri);
        }
        if (cursorQuery.moveToFirst()) {
            return cursorQuery;
        }
        IoUtils.closeQuietly(cursorQuery);
        throw new FileNotFoundException("Failed to read row from " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void includeDownloadsFromMediaStore(android.database.MatrixCursor r8, android.os.Bundle r9, java.util.Set<java.lang.String> r10, java.util.ArrayList<android.net.Uri> r11, java.lang.String r12, int r13, boolean r14) {
        /*
            r7 = this;
            if (r13 != 0) goto L3
            return
        L3:
            long r0 = android.os.Binder.clearCallingIdentity()
            java.lang.String r2 = "external"
            android.net.Uri r3 = android.provider.MediaStore.Downloads.getContentUri(r2)
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            android.util.Pair r12 = r7.buildSearchSelection(r9, r10, r12)
            java.lang.Object r5 = r12.first
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "android:query-arg-sql-selection"
            r4.putString(r6, r5)
            java.lang.Object r12 = r12.second
            java.lang.String[] r12 = (java.lang.String[]) r12
            java.lang.String r5 = "android:query-arg-sql-selection-args"
            r4.putStringArray(r5, r12)
            r12 = -1
            if (r13 == r12) goto L30
            java.lang.String r12 = "android:query-arg-limit"
            r4.putInt(r12, r13)
        L30:
            r12 = 1
            if (r14 == 0) goto L38
            java.lang.String r13 = "android:query-arg-match-pending"
            r4.putInt(r13, r12)
        L38:
            android.content.Context r13 = r7.getContext()     // Catch: java.lang.Throwable -> L80
            android.content.ContentResolver r13 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L80
            r14 = 0
            android.database.Cursor r13 = r13.query(r3, r14, r4, r14)     // Catch: java.lang.Throwable -> L80
            r14 = 0
            if (r9 == 0) goto L53
            java.lang.String r3 = "android:query-arg-exclude-media"
            boolean r9 = r9.getBoolean(r3, r14)     // Catch: java.lang.Throwable -> L51
            if (r9 == 0) goto L53
            goto L54
        L51:
            r7 = move-exception
            goto L75
        L53:
            r12 = r14
        L54:
            boolean r9 = r13.moveToNext()     // Catch: java.lang.Throwable -> L51
            if (r9 == 0) goto L5e
            r7.includeDownloadFromMediaStore(r8, r13, r10, r12)     // Catch: java.lang.Throwable -> L51
            goto L54
        L5e:
            android.net.Uri r7 = android.provider.MediaStore.Files.getContentUri(r2)     // Catch: java.lang.Throwable -> L51
            r11.add(r7)     // Catch: java.lang.Throwable -> L51
            android.net.Uri r7 = android.provider.MediaStore.Downloads.getContentUri(r2)     // Catch: java.lang.Throwable -> L51
            r11.add(r7)     // Catch: java.lang.Throwable -> L51
            if (r13 == 0) goto L71
            r13.close()     // Catch: java.lang.Throwable -> L80
        L71:
            android.os.Binder.restoreCallingIdentity(r0)
            return
        L75:
            if (r13 == 0) goto L7f
            r13.close()     // Catch: java.lang.Throwable -> L7b
            goto L7f
        L7b:
            r8 = move-exception
            r7.addSuppressed(r8)     // Catch: java.lang.Throwable -> L80
        L7f:
            throw r7     // Catch: java.lang.Throwable -> L80
        L80:
            r7 = move-exception
            android.os.Binder.restoreCallingIdentity(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadStorageProvider.includeDownloadsFromMediaStore(android.database.MatrixCursor, android.os.Bundle, java.util.Set, java.util.ArrayList, java.lang.String, int, boolean):void");
    }

    private void includeDownloadFromMediaStore(MatrixCursor matrixCursor, Cursor cursor, Set<String> set, boolean z) {
        String mimeType = getMimeType(cursor);
        if (z && MediaFile.isDocumentMimeType(mimeType)) {
            return;
        }
        String docIdForMediaStoreDownload = MediaStoreDownloadsHelper.getDocIdForMediaStoreDownload(cursor.getLong(cursor.getColumnIndex("_id")), "vnd.android.document/directory".equals(mimeType));
        String string = cursor.getString(cursor.getColumnIndex("_display_name"));
        long j = cursor.getLong(cursor.getColumnIndex("_size"));
        long j2 = cursor.getLong(cursor.getColumnIndex("date_modified")) * 1000;
        boolean z2 = cursor.getInt(cursor.getColumnIndex("is_pending")) == 1;
        int i = z2 ? 8192 : 0;
        if ("vnd.android.document/directory".equals(mimeType)) {
            i |= 8;
        }
        if (!z2) {
            i |= 64;
        }
        includeDownload(matrixCursor, docIdForMediaStoreDownload, string, null, j, mimeType, j2, i, z2);
        if (set != null) {
            set.add(cursor.getString(cursor.getColumnIndex("_data")));
        }
    }

    private String getMimeType(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("mime_type"));
        return string == null ? "vnd.android.document/directory" : string;
    }

    private Pair<String, String[]> buildSearchSelection(Bundle bundle, Set<String> set, String str) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (str == null && set != null && set.size() > 0) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("_data NOT IN (");
            sb.append(TextUtils.join(",", Collections.nCopies(set.size(), "?")));
            sb.append(")");
            arrayList.addAll(set);
        }
        if (str != null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("relative_path=?");
            Pair<String, String> relativePathAndDisplayNameForDownload = getRelativePathAndDisplayNameForDownload(Long.parseLong(str));
            arrayList.add(((String) relativePathAndDisplayNameForDownload.first) + ((String) relativePathAndDisplayNameForDownload.second) + "/");
        } else {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("relative_path=?");
            arrayList.add(Environment.DIRECTORY_DOWNLOADS + "/");
        }
        if (bundle != null) {
            if (bundle.getBoolean("android:query-arg-exclude-media", false)) {
                if (sb.length() > 0) {
                    sb.append(" AND ");
                }
                sb.append("mime_type NOT LIKE ?");
                arrayList.add("image/%");
                sb.append(" AND ");
                sb.append("mime_type NOT LIKE ?");
                arrayList.add("audio/%");
                sb.append(" AND ");
                sb.append("mime_type NOT LIKE ?");
                arrayList.add("video/%");
            }
            String string = bundle.getString("android:query-arg-display-name");
            if (!TextUtils.isEmpty(string)) {
                if (sb.length() > 0) {
                    sb.append(" AND ");
                }
                sb.append("_display_name LIKE ?");
                arrayList.add("%" + string + "%");
            }
            long j = bundle.getLong("android:query-arg-last-modified-after", -1L);
            if (j != -1) {
                if (sb.length() > 0) {
                    sb.append(" AND ");
                }
                sb.append("date_modified > " + (j / 1000));
            }
            long j2 = bundle.getLong("android:query-arg-file-size-over", -1L);
            if (j2 != -1) {
                if (sb.length() > 0) {
                    sb.append(" AND ");
                }
                sb.append("_size > " + j2);
            }
            String[] stringArray = bundle.getStringArray("android:query-arg-mime-types");
            if (stringArray != null && stringArray.length > 0) {
                if (sb.length() > 0) {
                    sb.append(" AND ");
                }
                sb.append("(");
                ArrayList arrayList2 = new ArrayList();
                StringBuilder sb2 = new StringBuilder();
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray) {
                    if (!TextUtils.isEmpty(str2) && str2.endsWith("/*")) {
                        arrayList3.add(str2);
                    } else {
                        if (arrayList2.size() > 0) {
                            sb2.append(",");
                        }
                        sb2.append("?");
                        arrayList2.add(str2);
                    }
                }
                int i = 0;
                while (true) {
                    String str3 = " OR ";
                    if (i >= arrayList3.size()) {
                        break;
                    }
                    sb.append("mime_type LIKE ?");
                    if (i == arrayList3.size() - 1) {
                        str3 = "";
                    }
                    sb.append(str3);
                    arrayList.add(((String) arrayList3.get(i)).substring(0, r2.length() - 1) + "%");
                    i++;
                }
                if (arrayList2.size() > 0) {
                    if (arrayList3.size() > 0) {
                        sb.append(" OR ");
                    }
                    sb.append("mime_type IN (");
                    sb.append(sb2.toString());
                    sb.append(")");
                    arrayList.addAll(arrayList2);
                }
                sb.append(")");
            }
        }
        return new Pair<>(sb.toString(), (String[]) arrayList.toArray(new String[0]));
    }

    private static final class DownloadsCursor extends MatrixCursor {

        @GuardedBy({"mLock"})
        private static ContentChangedRelay mFileWatcher;
        private static final Object mLock = new Object();

        @GuardedBy({"mLock"})
        private static int mOpenCursorCount;
        private final ContentResolver mResolver;

        DownloadsCursor(String[] strArr, ContentResolver contentResolver) {
            super(DownloadStorageProvider.resolveDocumentProjection(strArr));
            this.mResolver = contentResolver;
        }

        void start() {
            synchronized (mLock) {
                int i = mOpenCursorCount;
                mOpenCursorCount = i + 1;
                if (i == 0) {
                    ContentChangedRelay contentChangedRelay = new ContentChangedRelay(this.mResolver, Arrays.asList(DownloadStorageProvider.getPublicDownloadsDirectory()));
                    mFileWatcher = contentChangedRelay;
                    contentChangedRelay.startWatching();
                }
            }
        }

        @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            super.close();
            synchronized (mLock) {
                int i = mOpenCursorCount - 1;
                mOpenCursorCount = i;
                if (i == 0) {
                    mFileWatcher.stopWatching();
                    mFileWatcher = null;
                }
            }
        }
    }

    private static class ContentChangedRelay extends FileObserver {
        private final ContentResolver mResolver;

        public ContentChangedRelay(ContentResolver contentResolver, List<File> list) {
            super(list, 4044);
            this.mResolver = contentResolver;
        }

        @Override // android.os.FileObserver
        public void startWatching() {
            super.startWatching();
        }

        @Override // android.os.FileObserver
        public void stopWatching() {
            super.stopWatching();
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if ((i & 4044) != 0) {
                this.mResolver.notifyChange(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, (ContentObserver) null, false);
                this.mResolver.notifyChange(Downloads.Impl.CONTENT_URI, (ContentObserver) null, false);
            }
        }
    }
}
