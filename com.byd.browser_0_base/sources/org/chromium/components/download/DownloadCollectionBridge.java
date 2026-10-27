package org.chromium.components.download;

import J.N;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import defpackage.d70;
import defpackage.xz;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadCollectionBridge {
    public static final ArrayList a = new ArrayList(Arrays.asList("tar.gz", "tar.z", "tar.bz2", "tar.bz", "user.js"));
    public static d70 b = new d70();

    public static class DisplayNameInfo {
        public final String a;
        public final String b;

        public DisplayNameInfo(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @CalledByNative
        public final String getDisplayName() {
            return this.b;
        }

        @CalledByNative
        public final String getDownloadUri() {
            return this.a;
        }
    }

    public static Uri a(String str, String str2, String str3, String str4) {
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        String strG = b.g(str2, str3, str);
        Objects.requireNonNull(uri);
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues contentValues = new ContentValues();
        Objects.requireNonNull(str);
        contentValues.put("_display_name", str);
        Objects.requireNonNull(strG);
        contentValues.put("mime_type", strG);
        contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("date_modified", Long.valueOf(jCurrentTimeMillis));
        b.f(str3);
        if (!TextUtils.isEmpty(str4)) {
            Uri.parse(str4);
        }
        contentValues.put("date_expires", Long.valueOf(((((long) N.M7rZ5uJE()) * 86400000) + System.currentTimeMillis()) / 1000));
        try {
            return xz.a.getContentResolver().insert(uri, contentValues);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.net.Uri b(java.lang.String r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_id"
            r1 = 0
            android.net.Uri r2 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            android.content.Context r3 = defpackage.xz.a     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            android.content.ContentResolver r4 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            android.net.Uri r5 = android.provider.MediaStore.setIncludePending(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.lang.String[] r6 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.lang.String r7 = "_display_name LIKE ?1"
            r3 = 1
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r3 = 0
            r8[r3] = r10     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r9 = 0
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            if (r10 != 0) goto L28
            if (r10 == 0) goto L27
            r10.close()
        L27:
            return r1
        L28:
            boolean r3 = r10.moveToNext()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L52
            if (r3 == 0) goto L4e
            int r0 = r10.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L52
            int r0 = r10.getInt(r0)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L52
            long r3 = (long) r0     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L52
            android.net.Uri r0 = android.content.ContentUris.withAppendedId(r2, r3)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L52
            r10.close()
            return r0
        L3f:
            r0 = move-exception
            goto L45
        L41:
            r0 = move-exception
            goto L54
        L43:
            r0 = move-exception
            r10 = r1
        L45:
            java.lang.String r2 = "Unable to check file name existence."
            java.lang.String r3 = "cr_DownloadCollection"
            android.util.Log.e(r3, r2, r0)     // Catch: java.lang.Throwable -> L52
            if (r10 == 0) goto L51
        L4e:
            r10.close()
        L51:
            return r1
        L52:
            r0 = move-exception
            r1 = r10
        L54:
            if (r1 == 0) goto L59
            r1.close()
        L59:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.b(java.lang.String):android.net.Uri");
    }

    @CalledByNative
    public static boolean copyFileToIntermediateUri(String str, String str2) {
        try {
            Context context = xz.a;
            Uri uri = Uri.parse(str2);
            Objects.requireNonNull(context);
            Objects.requireNonNull(uri);
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
            FileInputStream fileInputStream = new FileInputStream(str);
            FileUtils.copy(fileInputStream, outputStreamOpenOutputStream);
            fileInputStream.close();
            outputStreamOpenOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.e("cr_DownloadCollection", "Unable to copy content to pending Uri.", e);
            return false;
        }
    }

    @CalledByNative
    public static String createIntermediateUriForPublish(String str, String str2, String str3, String str4) {
        String strSubstring;
        Uri uriA = a(str, str2, str3, str4);
        if (uriA != null) {
            return uriA.toString();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HHmmss.SSS", Locale.getDefault());
        Iterator it = a.iterator();
        while (true) {
            if (it.hasNext()) {
                String str5 = (String) it.next();
                if (str.endsWith(str5)) {
                    String strSubstring2 = str.substring(0, str.length() - str5.length());
                    if (strSubstring2.endsWith(".")) {
                        strSubstring = strSubstring2.substring(0, strSubstring2.length() - 1);
                        break;
                    }
                }
            } else {
                int iLastIndexOf = str.lastIndexOf(46);
                strSubstring = iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
            }
        }
        Uri uriA2 = a(strSubstring + " - " + simpleDateFormat.format(new Date()) + str.substring(strSubstring.length()), str2, str3, str4);
        if (uriA2 == null) {
            return null;
        }
        return uriA2.toString();
    }

    @CalledByNative
    public static void deleteIntermediateUri(String str) {
        Context context = xz.a;
        Uri uri = Uri.parse(str);
        Objects.requireNonNull(context);
        Objects.requireNonNull(uri);
        try {
            context.getContentResolver().delete(uri, null, null);
        } catch (Exception e) {
            Log.e("MediaStoreUtils", "Unable to delete pending session.", e);
        }
    }

    @CalledByNative
    public static boolean fileNameExists(String str) {
        return b(str) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDisplayName(java.lang.String r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_display_name"
            android.content.Context r1 = defpackage.xz.a
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 0
            android.net.Uri r3 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            if (r8 == 0) goto L35
            int r2 = r8.getCount()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L4c
            if (r2 != 0) goto L21
            goto L35
        L21:
            boolean r2 = r8.moveToNext()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L4c
            if (r2 == 0) goto L48
            int r0 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L4c
            java.lang.String r0 = r8.getString(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L4c
            r8.close()
            return r0
        L33:
            r0 = move-exception
            goto L3f
        L35:
            if (r8 == 0) goto L3a
            r8.close()
        L3a:
            return r1
        L3b:
            r0 = move-exception
            goto L4e
        L3d:
            r0 = move-exception
            r8 = r1
        L3f:
            java.lang.String r2 = "Unable to get display name for download."
            java.lang.String r3 = "cr_DownloadCollection"
            android.util.Log.e(r3, r2, r0)     // Catch: java.lang.Throwable -> L4c
            if (r8 == 0) goto L4b
        L48:
            r8.close()
        L4b:
            return r1
        L4c:
            r0 = move-exception
            r1 = r8
        L4e:
            if (r1 == 0) goto L53
            r1.close()
        L53:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.getDisplayName(java.lang.String):java.lang.String");
    }

    @CalledByNative
    public static DisplayNameInfo[] getDisplayNamesForDownloads() throws Throwable {
        Cursor cursorQuery;
        Uri uri;
        ContentResolver contentResolver = xz.a.getContentResolver();
        Cursor cursor = null;
        try {
            uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
            cursorQuery = contentResolver.query(MediaStore.setIncludePending(uri), new String[]{"_id", "_display_name"}, null, null, null);
        } catch (Exception e) {
            e = e;
            cursorQuery = null;
        } catch (Throwable th) {
            th = th;
        }
        if (cursorQuery != null) {
            try {
                try {
                    if (cursorQuery.getCount() != 0) {
                        ArrayList arrayList = new ArrayList();
                        while (cursorQuery.moveToNext()) {
                            arrayList.add(new DisplayNameInfo(ContentUris.withAppendedId(uri, cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("_id"))).toString(), cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"))));
                        }
                        DisplayNameInfo[] displayNameInfoArr = (DisplayNameInfo[]) arrayList.toArray(new DisplayNameInfo[0]);
                        cursorQuery.close();
                        return displayNameInfoArr;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Log.e("cr_DownloadCollection", "Unable to get display names for downloads.", e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorQuery;
            }
            th = th2;
            cursor = cursorQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    @CalledByNative
    public static int openIntermediateUri(String str) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = xz.a.getContentResolver().openFileDescriptor(Uri.parse(str), "rw");
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_expires", Long.valueOf(((((long) N.M7rZ5uJE()) * 86400000) + System.currentTimeMillis()) / 1000));
            xz.a.getContentResolver().update(Uri.parse(str), contentValues, null, null);
            return parcelFileDescriptorOpenFileDescriptor.detachFd();
        } catch (Exception e) {
            Log.e("cr_DownloadCollection", "Cannot open intermediate Uri.", e);
            return -1;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0099: MOVE (r9 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]) (LINE:154), block:B:38:0x0099 */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009c  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String publishDownload(java.lang.String r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "cr_DownloadCollection"
            java.lang.String r1 = "mime_type"
            android.content.Context r2 = defpackage.xz.a
            android.content.ContentResolver r2 = r2.getContentResolver()
            r9 = 0
            android.net.Uri r4 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String[] r5 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r2
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            if (r3 == 0) goto L34
            int r4 = r3.getCount()     // Catch: java.lang.Exception -> L32 java.lang.Throwable -> L98
            if (r4 == 0) goto L34
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Exception -> L32 java.lang.Throwable -> L98
            if (r4 == 0) goto L34
            int r4 = r3.getColumnIndexOrThrow(r1)     // Catch: java.lang.Exception -> L32 java.lang.Throwable -> L98
            java.lang.String r4 = r3.getString(r4)     // Catch: java.lang.Exception -> L32 java.lang.Throwable -> L98
            goto L35
        L32:
            r4 = move-exception
            goto L40
        L34:
            r4 = r9
        L35:
            if (r3 == 0) goto L4b
            r3.close()
            goto L4b
        L3b:
            r10 = move-exception
            goto L9a
        L3d:
            r3 = move-exception
            r4 = r3
            r3 = r9
        L40:
            java.lang.String r5 = "Unable to get mimeType."
            android.util.Log.e(r0, r5, r4)     // Catch: java.lang.Throwable -> L98
            if (r3 == 0) goto L4a
            r3.close()
        L4a:
            r4 = r9
        L4b:
            android.content.Context r3 = defpackage.xz.a
            android.net.Uri r10 = android.net.Uri.parse(r10)
            java.util.Objects.requireNonNull(r3)
            java.util.Objects.requireNonNull(r10)
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch: java.lang.Exception -> L73
            r5.<init>()     // Catch: java.lang.Exception -> L73
            r6 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L73
            java.lang.String r7 = "is_pending"
            r5.put(r7, r6)     // Catch: java.lang.Exception -> L73
            java.lang.String r6 = "date_expires"
            r5.putNull(r6)     // Catch: java.lang.Exception -> L73
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Exception -> L73
            r3.update(r10, r5, r9, r9)     // Catch: java.lang.Exception -> L73
            goto L7b
        L73:
            r3 = move-exception
            java.lang.String r5 = "MediaStoreUtils"
            java.lang.String r6 = "Unable to publish pending session."
            android.util.Log.e(r5, r6, r3)
        L7b:
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 != 0) goto L93
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Exception -> L8d
            r3.<init>()     // Catch: java.lang.Exception -> L8d
            r3.put(r1, r4)     // Catch: java.lang.Exception -> L8d
            r2.update(r10, r3, r9, r9)     // Catch: java.lang.Exception -> L8d
            goto L93
        L8d:
            r1 = move-exception
            java.lang.String r2 = "Unable to modify mimeType."
            android.util.Log.e(r0, r2, r1)
        L93:
            java.lang.String r10 = r10.toString()
            return r10
        L98:
            r10 = move-exception
            r9 = r3
        L9a:
            if (r9 == 0) goto L9f
            r9.close()
        L9f:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.publishDownload(java.lang.String):java.lang.String");
    }

    @CalledByNative
    public static boolean renameDownloadUri(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        Uri uri = Uri.parse(str);
        contentValues.put("_display_name", str2);
        return xz.a.getContentResolver().update(uri, contentValues, null, null) == 1;
    }

    @CalledByNative
    public static boolean shouldPublishDownload(String str) {
        if (str == null) {
            return false;
        }
        return !b.e(str);
    }
}
