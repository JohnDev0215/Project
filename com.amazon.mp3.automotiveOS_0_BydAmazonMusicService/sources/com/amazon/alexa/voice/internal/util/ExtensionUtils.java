package com.amazon.alexa.voice.internal.util;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public final class ExtensionUtils {
    private ExtensionUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static String getExtension(Uri uri) {
        String path;
        int iLastIndexOf;
        if (uri == null || (iLastIndexOf = (path = uri.getPath()).lastIndexOf(46)) == -1) {
            return null;
        }
        return path.substring(iLastIndexOf);
    }
}
