package com.amazon.avod.content.image;

import java.util.Map;
import java.util.NavigableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface TrickplayManifest {
    void delete(long j);

    @Nullable
    Map.Entry<Long, String> getClosest(long j);

    int getImageHeight();

    int getImageWidth();

    @Nonnull
    NavigableMap<Long, String> getTimecodeToFilepathMap();

    boolean isLive();

    boolean isValid();

    void put(long j, @Nonnull String str);
}
