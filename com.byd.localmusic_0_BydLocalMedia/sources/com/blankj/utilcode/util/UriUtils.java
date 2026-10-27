package com.blankj.utilcode.util;

import android.app.slice.SliceItem;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.CalendarContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public final class UriUtils {
    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Uri file2Uri(File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'file' of type File (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
        }
        return Uri.fromFile(file);
    }

    public static File uri2File(Uri uri) {
        Uri uri2;
        String str;
        if (uri == null) {
            throw new NullPointerException("Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= 24 && path != null) {
            String[] strArr = {"/external", "/external_path"};
            for (int i = 0; i < 2; i++) {
                String str2 = strArr[i];
                if (path.startsWith(str2 + "/")) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace(str2, ""));
                    if (file.exists()) {
                        Log.d("UriUtils", uri.toString() + " -> " + str2);
                        return file;
                    }
                }
            }
        }
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if (DocumentsContract.EXTERNAL_STORAGE_PROVIDER_AUTHORITY.equals(authority)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(SettingsStringUtil.DELIMITER);
                String str3 = strArrSplit[0];
                if (DocumentsContract.EXTERNAL_STORAGE_PRIMARY_EMULATED_ROOT_ID.equalsIgnoreCase(str3)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + strArrSplit[1]);
                }
                StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
                try {
                    Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                    Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
                    Method method2 = cls.getMethod("getUuid", new Class[0]);
                    Method method3 = cls.getMethod("getState", new Class[0]);
                    Method method4 = cls.getMethod("getPath", new Class[0]);
                    Method method5 = cls.getMethod(CalendarContract.CalendarColumns.IS_PRIMARY, new Class[0]);
                    Method method6 = cls.getMethod("isEmulated", new Class[0]);
                    Object objInvoke = method.invoke(storageManager, new Object[0]);
                    int length = Array.getLength(objInvoke);
                    int i2 = 0;
                    while (i2 < length) {
                        Object obj = Array.get(objInvoke, i2);
                        Object obj2 = objInvoke;
                        if ((Environment.MEDIA_MOUNTED.equals(method3.invoke(obj, new Object[0])) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(method3.invoke(obj, new Object[0]))) && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                            return new File(method4.invoke(obj, new Object[0]) + "/" + strArrSplit[1]);
                        }
                        i2++;
                        objInvoke = obj2;
                    }
                } catch (Exception e) {
                    Log.d("UriUtils", uri.toString() + " parse failed. " + e.toString() + " -> 1_0");
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            }
            if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId)) {
                    try {
                        return getFileFromUri(ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), "1_1");
                    } catch (NumberFormatException unused) {
                        if (documentId.startsWith("raw:")) {
                            return new File(documentId.substring(4));
                        }
                    }
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                return null;
            }
            if ("com.android.providers.media.documents".equals(authority)) {
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(SettingsStringUtil.DELIMITER);
                String str4 = strArrSplit2[0];
                if (SliceItem.FORMAT_IMAGE.equals(str4)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str4)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else {
                    if (!"audio".equals(str4)) {
                        Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                        return null;
                    }
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getFileFromUri(uri2, "_id=?", new String[]{strArrSplit2[1]}, "1_2");
            }
            if ("content".equals(scheme)) {
                return getFileFromUri(uri, "1_3");
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
            return null;
        }
        if ("content".equals(scheme)) {
            return getFileFromUri(uri, ExifInterface.GPS_MEASUREMENT_2D);
        }
        Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
        return null;
    }

    private static File getFileFromUri(Uri uri, String str) {
        return getFileFromUri(uri, null, null, str);
    }

    private static File getFileFromUri(Uri uri, String str, String[] strArr, String str2) {
        Cursor cursorQuery = Utils.getApp().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (cursorQuery == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            }
            if (!cursorQuery.moveToFirst()) {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            }
            int columnIndex = cursorQuery.getColumnIndex("_data");
            if (columnIndex > -1) {
                return new File(cursorQuery.getString(columnIndex));
            }
            Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
            return null;
        } catch (Exception unused) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            cursorQuery.close();
        }
    }
}
