package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class ObjectUtils {
    public static Object getDefaultValue(@NonNull Class<?> cls) {
        if (cls != Void.TYPE && cls != null && cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                return Boolean.FALSE;
            }
            if (cls == Integer.TYPE) {
                return 0;
            }
            if (cls == Long.TYPE) {
                return 0L;
            }
            if (cls == Short.TYPE) {
                return (short) 0;
            }
            if (cls == Character.TYPE) {
                return (char) 0;
            }
            if (cls == Byte.TYPE) {
                return (byte) 0;
            }
            if (cls == Double.TYPE) {
                return Double.valueOf(0.0d);
            }
            if (cls == Float.TYPE) {
                return Float.valueOf(0.0f);
            }
        }
        return null;
    }

    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return obj == obj2;
        }
        return obj.equals(obj2);
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isInterlude(MediaItem mediaItem) {
        return mediaItem != null && mediaItem.isInterlude();
    }

    public static boolean isAdvertisement(MediaItem mediaItem) {
        return (mediaItem == null || mediaItem.getMediaCollectionInfo(null, MediaCollectionId.Type.ADVERTISEMENT) == null) ? false : true;
    }

    private ObjectUtils() {
    }

    public static boolean getIsOnDemandQueue(MediaItem mediaItem) {
        return Boolean.parseBoolean(mediaItem.getMetricsContext().get("isOnDemandQueue"));
    }

    @NonNull
    public static SelectionSourceType getSelectionSourceTypeFromMediaCollectionType(@NonNull MediaCollectionType mediaCollectionType) {
        try {
            return SelectionSourceType.fromValue(mediaCollectionType.getMetricsName());
        } catch (IllegalArgumentException unused) {
            return SelectionSourceType.SONGS;
        }
    }
}
