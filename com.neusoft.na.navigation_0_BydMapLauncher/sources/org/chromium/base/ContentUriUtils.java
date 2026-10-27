package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ContentUriUtils {
    private static final String TAG = "ContentUriUtils";
    private static FileProviderUtil sFileProviderUtil;
    private static final Object sLock = new Object();

    public interface FileProviderUtil {
        Uri getContentUriFromFile(Context context, File file);
    }

    private ContentUriUtils() {
    }

    public static void setFileProviderUtil(FileProviderUtil fileProviderUtil) {
        synchronized (sLock) {
            sFileProviderUtil = fileProviderUtil;
        }
    }

    public static Uri getContentUriFromFile(Context context, File file) {
        synchronized (sLock) {
            FileProviderUtil fileProviderUtil = sFileProviderUtil;
            if (fileProviderUtil == null) {
                return null;
            }
            return fileProviderUtil.getContentUriFromFile(context, file);
        }
    }

    public static int openContentUriForRead(Context context, String str) {
        ParcelFileDescriptor parcelFileDescriptor = getParcelFileDescriptor(context, str);
        if (parcelFileDescriptor != null) {
            return parcelFileDescriptor.detachFd();
        }
        return -1;
    }

    public static boolean contentUriExists(Context context, String str) {
        return getParcelFileDescriptor(context, str) != null;
    }

    public static String getMimeType(Context context, String str) {
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            return null;
        }
        return contentResolver.getType(Uri.parse(str));
    }

    private static ParcelFileDescriptor getParcelFileDescriptor(Context context, String str) {
        try {
            return context.getContentResolver().openFileDescriptor(Uri.parse(str), "r");
        } catch (FileNotFoundException e) {
            android.util.Log.w(TAG, "Cannot find content uri: " + str, e);
            return null;
        } catch (IllegalArgumentException e2) {
            android.util.Log.w(TAG, "Unknown content uri: " + str, e2);
            return null;
        } catch (IllegalStateException e3) {
            android.util.Log.w(TAG, "Unknown content uri: " + str, e3);
            return null;
        } catch (SecurityException e4) {
            android.util.Log.w(TAG, "Cannot open content uri: " + str, e4);
            return null;
        }
    }

    public static String getDisplayName(Uri uri, ContentResolver contentResolver, String str) {
        if (contentResolver != null && uri != null) {
            Cursor cursorQuery = null;
            try {
                cursorQuery = contentResolver.query(uri, null, null, null, null);
                if (cursorQuery != null && cursorQuery.getCount() >= 1) {
                    cursorQuery.moveToFirst();
                    int columnIndex = cursorQuery.getColumnIndex(str);
                    if (columnIndex > -1) {
                        String string = cursorQuery.getString(columnIndex);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return "";
            } catch (NullPointerException unused) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        return "";
    }
}
