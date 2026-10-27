package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface ItemLoader<KEY, VALUE> {
    @Nullable
    VALUE getIfCached(@NonNull KEY key);

    @NonNull
    VALUE load(@NonNull KEY key) throws IOException;
}
