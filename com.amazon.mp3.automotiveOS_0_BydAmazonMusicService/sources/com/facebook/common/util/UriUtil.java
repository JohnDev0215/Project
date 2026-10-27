package com.facebook.common.util;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import com.facebook.infer.annotation.Assertions;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class UriUtil {
    private static final Uri LOCAL_CONTACT_IMAGE_URI = Uri.withAppendedPath((Uri) Assertions.assumeNotNull(ContactsContract.AUTHORITY_URI), "display_photo");

    @Nullable
    public static URL uriToUrl(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNetworkUri(@Nullable Uri uri) {
        String schemeOrNull = getSchemeOrNull(uri);
        return "https".equals(schemeOrNull) || "http".equals(schemeOrNull);
    }

    public static boolean isLocalFileUri(@Nullable Uri uri) {
        return "file".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalContentUri(@Nullable Uri uri) {
        return "content".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalContactUri(Uri uri) {
        return uri.getPath() != null && isLocalContentUri(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith((String) Assertions.assumeNotNull(LOCAL_CONTACT_IMAGE_URI.getPath()));
    }

    public static boolean isLocalCameraUri(Uri uri) {
        String string = uri.toString();
        return string.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || string.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean isLocalAssetUri(@Nullable Uri uri) {
        return "asset".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalResourceUri(@Nullable Uri uri) {
        return "res".equals(getSchemeOrNull(uri));
    }

    public static boolean isQualifiedResourceUri(@Nullable Uri uri) {
        return "android.resource".equals(getSchemeOrNull(uri));
    }

    public static boolean isDataUri(@Nullable Uri uri) {
        return "data".equals(getSchemeOrNull(uri));
    }

    @Nullable
    public static String getSchemeOrNull(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }

    @Nullable
    public static String getRealPathFromUri(ContentResolver contentResolver, Uri uri) throws Throwable {
        int columnIndex;
        Cursor cursor = null;
        string = null;
        string = null;
        String string = null;
        if (isLocalContentUri(uri)) {
            try {
                Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_data")) != -1) {
                            string = cursorQuery.getString(columnIndex);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery == null) {
                    return string;
                }
                cursorQuery.close();
                return string;
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            if (isLocalFileUri(uri)) {
                return uri.getPath();
            }
            return null;
        }
    }

    @Nullable
    public static AssetFileDescriptor getAssetFileDescriptor(ContentResolver contentResolver, final Uri srcUri) {
        if (isLocalContentUri(srcUri)) {
            try {
                return contentResolver.openAssetFileDescriptor(srcUri, "r");
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    public static Uri getUriForResourceId(int resourceId) {
        return new Uri.Builder().scheme("res").path(String.valueOf(resourceId)).build();
    }
}
