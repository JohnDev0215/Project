package com.android.providers.downloads;

import android.content.Context;
import android.drm.DrmManagerClient;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class DownloadDrmHelper {
    public static boolean isDrmConvertNeeded(String str) {
        return "application/vnd.oma.drm.message".equals(str);
    }

    public static String modifyDrmFwLockFileExtension(String str) {
        if (str == null) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf != -1) {
            str = str.substring(0, iLastIndexOf);
        }
        return str.concat(".fl");
    }

    public static String getOriginalMimeType(Context context, File file, String str) {
        DrmManagerClient drmManagerClient = new DrmManagerClient(context);
        try {
            String string = file.toString();
            return drmManagerClient.canHandle(string, (String) null) ? drmManagerClient.getOriginalMimeType(string) : str;
        } finally {
            drmManagerClient.release();
        }
    }
}
