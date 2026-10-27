package com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public interface CheckpointPolicy {
    void checkpoint(StreamType streamType, CachedContentView cachedContentView);

    void checkpointInitFragments(StreamType streamType, Set<SmoothStreamingURI> set);

    CachedContentView rebuildFromCheckpoint(StreamType streamType);

    Set<SmoothStreamingURI> rebuildInitFragmentsFromCheckpoint(StreamType streamType);
}
