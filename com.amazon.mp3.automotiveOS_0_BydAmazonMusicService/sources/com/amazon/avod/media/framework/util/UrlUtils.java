package com.amazon.avod.media.framework.util;

import android.net.Uri;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class UrlUtils {
    public static boolean isLocal(URL url) {
        return isLocalProtocol(url.getProtocol());
    }

    public static boolean isLocal(Uri uri) {
        return isLocalProtocol(uri.getScheme());
    }

    public static boolean isHttpsUrl(String str) {
        return str != null && str.length() > 7 && str.substring(0, 8).equalsIgnoreCase("https://");
    }

    private static boolean isLocalProtocol(String str) {
        return str != null && str.equals("file");
    }
}
