package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import defpackage.Gs;
import defpackage.Is;
import defpackage.Ts;
import defpackage.WQ;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContentUriUtils {
    public static Gs a;
    public static final Object b = new Object();

    public static AssetFileDescriptor a(String str) {
        ContentResolver contentResolver = Is.a.getContentResolver();
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
            Ts.f("ContentUriUtils", "Cannot open content uri: %s", str, e);
        }
        return null;
    }

    public static Uri b(File file) {
        synchronized (b) {
            if (a == null) {
                return null;
            }
            return ((WQ) a).a(file);
        }
    }

    public static String c(Uri uri, Context context, String str) {
        Cursor cursorQuery;
        String[] streamTypes;
        String extensionFromMimeType;
        if (uri == null) {
            return "";
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursorQuery = contentResolver.query(uri, null, null, null, null);
        } catch (NullPointerException unused) {
        }
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() >= 1) {
                    cursorQuery.moveToFirst();
                    int columnIndex = cursorQuery.getColumnIndex(str);
                    if (columnIndex == -1) {
                        cursorQuery.close();
                        return "";
                    }
                    String string = cursorQuery.getString(columnIndex);
                    if (d(cursorQuery) && (streamTypes = contentResolver.getStreamTypes(uri, "*/*")) != null && streamTypes.length > 0 && (extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(streamTypes[0])) != null) {
                        string = string + "." + extensionFromMimeType;
                    }
                    cursorQuery.close();
                    return string;
                    return "";
                }
            } finally {
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return "";
    }

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

    public static boolean d(Cursor cursor) {
        int columnIndex;
        return Build.VERSION.SDK_INT >= 24 && (columnIndex = cursor.getColumnIndex("flags")) > -1 && (cursor.getLong(columnIndex) & 512) != 0;
    }

    public static boolean delete(String str) {
        return Is.a.getContentResolver().delete(Uri.parse(str), null, null) > 0;
    }

    public static boolean e(Uri uri) {
        if (uri == null || !DocumentsContract.isDocumentUri(Is.a, uri)) {
            return false;
        }
        try {
            Cursor cursorQuery = Is.a.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() >= 1) {
                        cursorQuery.moveToFirst();
                        boolean zD = d(cursorQuery);
                        cursorQuery.close();
                        return zD;
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public static String getContentUriFromFilePath(String str) {
        try {
            Uri uriB = b(new File(str));
            if (uriB != null) {
                return uriB.toString();
            }
            return null;
        } catch (IllegalArgumentException e) {
            Ts.a("ContentUriUtils", "Cannot retrieve content uri from file: %s", str, e);
            return null;
        }
    }

    public static String getMimeType(String str) {
        ContentResolver contentResolver = Is.a.getContentResolver();
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

    public static String maybeGetDisplayName(String str) {
        try {
            String strC = c(Uri.parse(str), Is.a, "_display_name");
            if (TextUtils.isEmpty(strC)) {
                return null;
            }
            return strC;
        } catch (Exception e) {
            Ts.f("ContentUriUtils", "Cannot open content uri: %s", str, e);
            return null;
        }
    }

    public static int openContentUriForRead(String str) {
        AssetFileDescriptor assetFileDescriptorA = a(str);
        if (assetFileDescriptorA != null) {
            return assetFileDescriptorA.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }
}
