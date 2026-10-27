package com.amazon.avod.content.downloading;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentViewPersistence {
    CachedContentView getContentView(@Nonnull StreamType streamType);

    Set<SmoothStreamingURI> getInitFragments(@Nonnull StreamType streamType);

    void setContentView(@Nonnull StreamType streamType, @Nonnull CachedContentView cachedContentView);

    void updateInitFragmentMap(@Nonnull StreamType streamType, @Nonnull Set<SmoothStreamingURI> set);
}
