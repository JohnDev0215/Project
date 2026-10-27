package com.amazon.mp3.download;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;

/* JADX INFO: loaded from: classes3.dex */
public class ImageCacheUri {
    public static Uri generateUri(int imageItemType, long id) {
        return generateUri(imageItemType, String.valueOf(id));
    }

    public static Uri generateUri(int imageItemType, String id) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("cache").authority(ContextMappingConstants.IMAGE).appendPath(String.valueOf(imageItemType)).appendPath(id);
        return builder.build();
    }

    public static int getImageItemType(@NonNull Uri uri) {
        if (validate(uri)) {
            return Integer.parseInt(uri.getPathSegments().get(0));
        }
        return -1;
    }

    @Nullable
    public static String getId(@NonNull Uri uri) {
        if (validate(uri)) {
            return uri.getPathSegments().get(1);
        }
        return null;
    }

    public static boolean isImageCacheUri(@NonNull Uri uri) {
        return validate(uri);
    }

    private static boolean validate(@NonNull Uri uri) {
        return "cache".equals(uri.getScheme()) && ContextMappingConstants.IMAGE.equals(uri.getAuthority());
    }
}
