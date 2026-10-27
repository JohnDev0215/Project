package com.android.providers.downloads;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.MediaStore;

/* JADX INFO: loaded from: classes.dex */
public class MediaStoreDownloadsHelper {
    public static String getDocIdForMediaStoreDownload(long j, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "msd:" : "msf:");
        sb.append(j);
        return sb.toString();
    }

    public static boolean isMediaStoreDownload(String str) {
        return str != null && (str.startsWith("msf:") || str.startsWith("msd:"));
    }

    public static long getMediaStoreId(String str) {
        return Long.parseLong(getMediaStoreIdString(str));
    }

    public static String getMediaStoreIdString(String str) {
        return str.substring(str.indexOf(":") + 1);
    }

    public static boolean isMediaStoreDownloadDir(String str) {
        return str != null && str.startsWith("msd:");
    }

    public static Uri getMediaStoreUri(String str) {
        return ContentUris.withAppendedId(MediaStore.Downloads.EXTERNAL_CONTENT_URI, getMediaStoreId(str));
    }
}
