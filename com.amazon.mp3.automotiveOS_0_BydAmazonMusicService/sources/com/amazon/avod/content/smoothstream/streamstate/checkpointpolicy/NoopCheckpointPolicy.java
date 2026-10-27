package com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import com.google.common.collect.Sets;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class NoopCheckpointPolicy implements CheckpointPolicy {
    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpoint(StreamType streamType, CachedContentView cachedContentView) {
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpointInitFragments(StreamType streamType, Set<SmoothStreamingURI> set) {
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public CachedContentView rebuildFromCheckpoint(StreamType streamType) {
        return new CachedContentView();
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public Set<SmoothStreamingURI> rebuildInitFragmentsFromCheckpoint(StreamType streamType) {
        return Sets.newHashSet();
    }
}
