package com.amazon.avod.playbackclient.trickplay.download;

import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface TrickplayLoadingResponse {
    long getBifFileSize();

    @Nonnull
    PluginLoadStatus getLoadingStatus();

    @Nonnull
    Optional<TrickplayIndex> getTrickplayIndex();
}
