package com.amazon.avod.content.image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface TrickplayIndex {
    @Nullable
    TrickplayImage findClosestImage(long j);

    @Nullable
    String getImageFileForTimecode(long j);

    @Nonnull
    TrickplayManifest getRawManifest();
}
