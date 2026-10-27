package com.amazon.music.media.playback.config;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public interface ImagePlaceholderProvider {

    public enum PlaceholderType {
        PLACEHOLDER,
        ERROR,
        DEFAULT
    }

    @Nullable
    Bitmap getPlaceholderImage(@NonNull MediaItem.ImageType imageType, @NonNull PlaceholderType placeholderType, @Nullable Uri uri, int i, int i2);
}
