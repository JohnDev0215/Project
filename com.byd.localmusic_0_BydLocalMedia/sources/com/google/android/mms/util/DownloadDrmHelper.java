package com.google.android.mms.util;

import android.content.Context;
import android.drm.DrmManagerClient;
import android.media.MediaMetrics;
import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadDrmHelper {
    public static final String EXTENSION_DRM_MESSAGE = ".dm";
    public static final String EXTENSION_INTERNAL_FWDL = ".fl";
    public static final String MIMETYPE_DRM_MESSAGE = "application/vnd.oma.drm.message";
    private static final String TAG = "DownloadDrmHelper";

    public static boolean isDrmMimeType(Context context, String str) {
        if (context == null) {
            return false;
        }
        try {
            DrmManagerClient drmManagerClient = new DrmManagerClient(context);
            if (str == null || str.length() <= 0) {
                return false;
            }
            return drmManagerClient.canHandle("", str);
        } catch (IllegalArgumentException unused) {
            Log.w(TAG, "DrmManagerClient instance could not be created, context is Illegal.");
            return false;
        } catch (IllegalStateException unused2) {
            Log.w(TAG, "DrmManagerClient didn't initialize properly.");
            return false;
        }
    }

    public static boolean isDrmConvertNeeded(String str) {
        return "application/vnd.oma.drm.message".equals(str);
    }

    public static String modifyDrmFwLockFileExtension(String str) {
        if (str == null) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(MediaMetrics.SEPARATOR);
        if (iLastIndexOf != -1) {
            str = str.substring(0, iLastIndexOf);
        }
        return str.concat(EXTENSION_INTERNAL_FWDL);
    }

    public static String getOriginalMimeType(Context context, String str, String str2) {
        DrmManagerClient drmManagerClient = new DrmManagerClient(context);
        try {
            return drmManagerClient.canHandle(str, (String) null) ? drmManagerClient.getOriginalMimeType(str) : str2;
        } catch (IllegalArgumentException unused) {
            Log.w(TAG, "Can't get original mime type since path is null or empty string.");
            return str2;
        } catch (IllegalStateException unused2) {
            Log.w(TAG, "DrmManagerClient didn't initialize properly.");
            return str2;
        }
    }
}
