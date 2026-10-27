package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import defpackage.fa1;
import defpackage.xz;
import java.io.File;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContentUriUtils {
    public static a a;
    public static final Object b = new Object();

    public interface a {
    }

    public static AssetFileDescriptor a(String str) {
        ContentResolver contentResolver = xz.a.getContentResolver();
        Uri uri = Uri.parse(str);
        try {
            if (e(uri)) {
                String[] streamTypes = contentResolver.getStreamTypes(uri, "*/*");
                if (streamTypes != null && streamTypes.length > 0) {
                    AssetFileDescriptor assetFileDescriptorOpenTypedAssetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(uri, streamTypes[0], null);
                    if (assetFileDescriptorOpenTypedAssetFileDescriptor == null || assetFileDescriptorOpenTypedAssetFileDescriptor.getStartOffset() == 0) {
                        return assetFileDescriptorOpenTypedAssetFileDescriptor;
                    }
                    try {
                        assetFileDescriptorOpenTypedAssetFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                    throw new SecurityException("Cannot open files with non-zero offset type.");
                }
            } else {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    return new AssetFileDescriptor(parcelFileDescriptorOpenFileDescriptor, 0L, -1L);
                }
            }
        } catch (Exception e) {
            fa1.l("ContentUriUtils", "Cannot open content uri: %s", str, e);
        }
        return null;
    }

    public static Uri b(File file) {
        synchronized (b) {
            if (a == null) {
                return null;
            }
            Context context = xz.a;
            return FileProvider.a(context, context.getPackageName() + ".FileProvider").b(file);
        }
    }

    public static String c(Uri uri, Context context) {
        String[] streamTypes;
        String extensionFromMimeType;
        if (uri == null) {
            return "";
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    boolean z = true;
                    if (cursorQuery.getCount() >= 1) {
                        cursorQuery.moveToFirst();
                        int columnIndex = cursorQuery.getColumnIndex("_display_name");
                        if (columnIndex == -1) {
                            cursorQuery.close();
                            return "";
                        }
                        String string = cursorQuery.getString(columnIndex);
                        int columnIndex2 = cursorQuery.getColumnIndex("flags");
                        if (columnIndex2 <= -1 || (cursorQuery.getLong(columnIndex2) & 512) == 0) {
                            z = false;
                        }
                        if (z && (streamTypes = contentResolver.getStreamTypes(uri, "*/*")) != null && streamTypes.length > 0 && (extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(streamTypes[0])) != null) {
                            string = string + "." + extensionFromMimeType;
                        }
                        cursorQuery.close();
                        return string;
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Throwable unused) {
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (NullPointerException unused2) {
        }
        return "";
    }

    @CalledByNative
    public static boolean contentUriExists(String str) {
        AssetFileDescriptor assetFileDescriptorA = a(str);
        boolean z = assetFileDescriptorA != null;
        if (assetFileDescriptorA != null) {
            try {
                assetFileDescriptorA.close();
            } catch (IOException unused) {
            }
        }
        return z;
    }

    public static boolean d(String str) {
        Uri uri;
        return (str == null || (uri = Uri.parse(str)) == null || !"content".equals(uri.getScheme())) ? false : true;
    }

    @CalledByNative
    public static boolean delete(String str) {
        return xz.a.getContentResolver().delete(Uri.parse(str), null, null) > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean e(android.net.Uri r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            android.content.Context r1 = defpackage.xz.a
            boolean r1 = android.provider.DocumentsContract.isDocumentUri(r1, r8)
            if (r1 != 0) goto Ld
            return r0
        Ld:
            android.content.Context r1 = defpackage.xz.a
            android.content.ContentResolver r2 = r1.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r8
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.NullPointerException -> L4f
            if (r8 == 0) goto L4a
            int r1 = r8.getCount()     // Catch: java.lang.Throwable -> L3f
            r2 = 1
            if (r1 < r2) goto L4a
            r8.moveToFirst()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = "flags"
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L3f
            r3 = -1
            if (r1 <= r3) goto L41
            long r3 = r8.getLong(r1)     // Catch: java.lang.Throwable -> L3f
            r5 = 512(0x200, double:2.53E-321)
            long r3 = r3 & r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 == 0) goto L41
            goto L42
        L3f:
            r1 = move-exception
            goto L46
        L41:
            r2 = r0
        L42:
            r8.close()     // Catch: java.lang.NullPointerException -> L4f
            return r2
        L46:
            r8.close()     // Catch: java.lang.Throwable -> L49
        L49:
            throw r1     // Catch: java.lang.NullPointerException -> L4f
        L4a:
            if (r8 == 0) goto L4f
            r8.close()     // Catch: java.lang.NullPointerException -> L4f
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ContentUriUtils.e(android.net.Uri):boolean");
    }

    @CalledByNative
    public static String getContentUriFromFilePath(String str) {
        try {
            Uri uriB = b(new File(str));
            if (uriB != null) {
                return uriB.toString();
            }
            return null;
        } catch (IllegalArgumentException e) {
            fa1.c("ContentUriUtils", "Cannot retrieve content uri from file: %s", str, e);
            return null;
        }
    }

    @CalledByNative
    public static String getMimeType(String str) {
        ContentResolver contentResolver = xz.a.getContentResolver();
        Uri uri = Uri.parse(str);
        if (!e(uri)) {
            return contentResolver.getType(uri);
        }
        String[] streamTypes = contentResolver.getStreamTypes(uri, "*/*");
        if (streamTypes == null || streamTypes.length <= 0) {
            return null;
        }
        return streamTypes[0];
    }

    @CalledByNative
    public static String maybeGetDisplayName(String str) {
        try {
            String strC = c(Uri.parse(str), xz.a);
            if (TextUtils.isEmpty(strC)) {
                return null;
            }
            return strC;
        } catch (Exception e) {
            fa1.l("ContentUriUtils", "Cannot open content uri: %s", str, e);
            return null;
        }
    }

    @CalledByNative
    public static int openContentUriForRead(String str) {
        AssetFileDescriptor assetFileDescriptorA = a(str);
        if (assetFileDescriptorA != null) {
            return assetFileDescriptorA.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }
}
