package org.chromium.components.download;

import J.N;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import defpackage.C1049kN;
import defpackage.C1105lN;
import defpackage.C1134lx;
import defpackage.Is;
import defpackage.Ts;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.BuildInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DownloadCollectionBridge {
    public static final List a = new ArrayList(Arrays.asList("tar.gz", "tar.z", "tar.bz2", "tar.bz", "user.js"));
    public static C1134lx b = new C1134lx();

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class DisplayNameInfo {
        public final String a;
        public final String b;

        public DisplayNameInfo(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        private String getDisplayName() {
            return this.b;
        }

        private String getDownloadUri() {
            return this.a;
        }
    }

    public static Uri a(String str, String str2, String str3, String str4) {
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        Objects.requireNonNull(b);
        C1049kN c1049kN = new C1049kN(uri, str, str2);
        Objects.requireNonNull(b);
        Uri uri2 = Uri.parse(str3);
        Uri uri3 = TextUtils.isEmpty(str4) ? null : Uri.parse(str4);
        if (uri2 == null) {
            c1049kN.b.remove("download_uri");
        } else {
            c1049kN.b.put("download_uri", uri2.toString());
        }
        if (uri3 == null) {
            c1049kN.b.remove("referer_uri");
        } else {
            c1049kN.b.put("referer_uri", uri3.toString());
        }
        c1049kN.b.put("date_expires", Long.valueOf(b()));
        try {
            return Is.a.getContentResolver().insert(c1049kN.a, c1049kN.b);
        } catch (Exception unused) {
            return null;
        }
    }

    public static long b() {
        return ((((long) N.M7rZ5uJE()) * 86400000) + System.currentTimeMillis()) / 1000;
    }

    public static boolean c() {
        return BuildInfo.a() || Build.VERSION.SDK_INT >= 29;
    }

    public static boolean copyFileToIntermediateUri(String str, String str2) {
        try {
            C1105lN c1105lND = d(str2);
            OutputStream outputStreamOpenOutputStream = c1105lND.s.getContentResolver().openOutputStream(c1105lND.t);
            FileInputStream fileInputStream = new FileInputStream(str);
            FileUtils.copy(fileInputStream, outputStreamOpenOutputStream);
            fileInputStream.close();
            outputStreamOpenOutputStream.close();
            return true;
        } catch (Exception e) {
            Ts.a("DownloadCollection", "Unable to copy content to pending Uri.", e);
            return false;
        }
    }

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

    public static C1105lN d(String str) {
        return new C1105lN(Is.a, Uri.parse(str));
    }

    public static void deleteIntermediateUri(String str) {
        C1105lN c1105lND = d(str);
        c1105lND.s.getContentResolver().delete(c1105lND.t, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0054 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean fileNameExists(java.lang.String r12) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_id"
            r1 = 0
            r2 = 1
            r3 = 0
            android.net.Uri r4 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            android.content.Context r5 = defpackage.Is.a     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            android.content.ContentResolver r6 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            android.net.Uri r7 = android.provider.MediaStore.setIncludePending(r4)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String[] r8 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r9 = "_display_name LIKE ?1"
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r10[r3] = r12     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r12 != 0) goto L28
            if (r12 == 0) goto L51
        L24:
            r12.close()
            goto L51
        L28:
            boolean r5 = r12.moveToNext()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L56
            if (r5 == 0) goto L24
            int r0 = r12.getColumnIndex(r0)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L56
            int r0 = r12.getInt(r0)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L56
            long r5 = (long) r0     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L56
            android.net.Uri r1 = android.content.ContentUris.withAppendedId(r4, r5)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L56
            goto L24
        L3c:
            r0 = move-exception
            goto L43
        L3e:
            r12 = move-exception
            goto L59
        L40:
            r12 = move-exception
            r0 = r12
            r12 = r1
        L43:
            java.lang.String r4 = "DownloadCollection"
            java.lang.String r5 = "Unable to check file name existence."
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L56
            r6[r3] = r0     // Catch: java.lang.Throwable -> L56
            defpackage.Ts.a(r4, r5, r6)     // Catch: java.lang.Throwable -> L56
            if (r12 == 0) goto L51
            goto L24
        L51:
            if (r1 == 0) goto L54
            goto L55
        L54:
            r2 = 0
        L55:
            return r2
        L56:
            r0 = move-exception
            r1 = r12
            r12 = r0
        L59:
            if (r1 == 0) goto L5e
            r1.close()
        L5e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.fileNameExists(java.lang.String):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDisplayName(java.lang.String r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_display_name"
            android.content.Context r1 = defpackage.Is.a
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 0
            android.net.Uri r3 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            if (r8 == 0) goto L35
            int r2 = r8.getCount()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L52
            if (r2 != 0) goto L21
            goto L35
        L21:
            boolean r2 = r8.moveToNext()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L52
            if (r2 == 0) goto L4e
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L52
            java.lang.String r0 = r8.getString(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L52
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
            goto L54
        L3d:
            r0 = move-exception
            r8 = r1
        L3f:
            java.lang.String r2 = "DownloadCollection"
            java.lang.String r3 = "Unable to get display name for download."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L52
            r5 = 0
            r4[r5] = r0     // Catch: java.lang.Throwable -> L52
            defpackage.Ts.a(r2, r3, r4)     // Catch: java.lang.Throwable -> L52
            if (r8 == 0) goto L51
        L4e:
            r8.close()
        L51:
            return r1
        L52:
            r0 = move-exception
            r1 = r8
        L54:
            if (r1 == 0) goto L59
            r1.close()
        L59:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.getDisplayName(java.lang.String):java.lang.String");
    }

    public static DisplayNameInfo[] getDisplayNamesForDownloads() throws Throwable {
        Cursor cursorQuery;
        Uri uri;
        ContentResolver contentResolver = Is.a.getContentResolver();
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
                            arrayList.add(new DisplayNameInfo(ContentUris.withAppendedId(uri, cursorQuery.getInt(cursorQuery.getColumnIndex("_id"))).toString(), cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"))));
                        }
                        DisplayNameInfo[] displayNameInfoArr = (DisplayNameInfo[]) arrayList.toArray(new DisplayNameInfo[0]);
                        cursorQuery.close();
                        return displayNameInfoArr;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                }
            } catch (Exception e2) {
                e = e2;
                Ts.a("DownloadCollection", "Unable to get display names for downloads.", e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
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

    public static boolean needToRetrieveDisplayNames() {
        return c();
    }

    public static int openIntermediateUri(String str) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = Is.a.getContentResolver().openFileDescriptor(Uri.parse(str), "rw");
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_expires", Long.valueOf(b()));
            Is.a.getContentResolver().update(Uri.parse(str), contentValues, null, null);
            return parcelFileDescriptorOpenFileDescriptor.detachFd();
        } catch (Exception e) {
            Ts.a("DownloadCollection", "Cannot open intermediate Uri.", e);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String publishDownload(java.lang.String r12) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "DownloadCollection"
            java.lang.String r1 = "mime_type"
            android.content.Context r2 = defpackage.Is.a
            android.content.ContentResolver r2 = r2.getContentResolver()
            r9 = 1
            r10 = 0
            r11 = 0
            android.net.Uri r4 = android.net.Uri.parse(r12)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            java.lang.String[] r5 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r2
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r3 == 0) goto L36
            int r4 = r3.getCount()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L9e
            if (r4 == 0) goto L36
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L9e
            if (r4 == 0) goto L36
            int r4 = r3.getColumnIndex(r1)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L9e
            java.lang.String r4 = r3.getString(r4)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L9e
            goto L37
        L34:
            r4 = move-exception
            goto L41
        L36:
            r4 = r11
        L37:
            if (r3 == 0) goto L50
            r3.close()
            goto L50
        L3d:
            r12 = move-exception
            goto La0
        L3f:
            r4 = move-exception
            r3 = r11
        L41:
            java.lang.String r5 = "Unable to get mimeType."
            java.lang.Object[] r6 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L9e
            r6[r10] = r4     // Catch: java.lang.Throwable -> L9e
            defpackage.Ts.a(r0, r5, r6)     // Catch: java.lang.Throwable -> L9e
            if (r3 == 0) goto L4f
            r3.close()
        L4f:
            r4 = r11
        L50:
            lN r12 = d(r12)
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Exception -> L73
            r3.<init>()     // Catch: java.lang.Exception -> L73
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Exception -> L73
            java.lang.String r6 = "is_pending"
            r3.put(r6, r5)     // Catch: java.lang.Exception -> L73
            java.lang.String r5 = "date_expires"
            r3.putNull(r5)     // Catch: java.lang.Exception -> L73
            android.content.Context r5 = r12.s     // Catch: java.lang.Exception -> L73
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Exception -> L73
            android.net.Uri r6 = r12.t     // Catch: java.lang.Exception -> L73
            r5.update(r6, r3, r11, r11)     // Catch: java.lang.Exception -> L73
            goto L7b
        L73:
            r3 = move-exception
            java.lang.String r5 = "MediaStoreUtils"
            java.lang.String r6 = "Unable to publish pending session."
            android.util.Log.e(r5, r6, r3)
        L7b:
            android.net.Uri r12 = r12.t
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 != 0) goto L99
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Exception -> L8f
            r3.<init>()     // Catch: java.lang.Exception -> L8f
            r3.put(r1, r4)     // Catch: java.lang.Exception -> L8f
            r2.update(r12, r3, r11, r11)     // Catch: java.lang.Exception -> L8f
            goto L99
        L8f:
            r1 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r9]
            r2[r10] = r1
            java.lang.String r1 = "Unable to modify mimeType."
            defpackage.Ts.a(r0, r1, r2)
        L99:
            java.lang.String r12 = r12.toString()
            return r12
        L9e:
            r12 = move-exception
            r11 = r3
        La0:
            if (r11 == 0) goto La5
            r11.close()
        La5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.download.DownloadCollectionBridge.publishDownload(java.lang.String):java.lang.String");
    }

    public static boolean renameDownloadUri(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        Uri uri = Uri.parse(str);
        contentValues.put("_display_name", str2);
        return Is.a.getContentResolver().update(uri, contentValues, null, null) == 1;
    }

    public static boolean shouldPublishDownload(String str) {
        if (!c() || str == null) {
            return false;
        }
        Objects.requireNonNull(b);
        return true;
    }
}
