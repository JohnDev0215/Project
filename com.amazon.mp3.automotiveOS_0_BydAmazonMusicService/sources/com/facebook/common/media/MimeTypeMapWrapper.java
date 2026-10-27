package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class MimeTypeMapWrapper {
    private static final MimeTypeMap sMimeTypeMap = MimeTypeMap.getSingleton();
    private static final Map<String, String> sMimeTypeToExtensionMap = ImmutableMap.of("image/heif", "heif", "image/heic", "heic");
    private static final Map<String, String> sExtensionToMimeTypeMap = ImmutableMap.of("heif", "image/heif", "heic", "image/heic");

    public static String getMimeTypeFromExtension(String extension) {
        String str = sExtensionToMimeTypeMap.get(extension);
        return str != null ? str : sMimeTypeMap.getMimeTypeFromExtension(extension);
    }
}
