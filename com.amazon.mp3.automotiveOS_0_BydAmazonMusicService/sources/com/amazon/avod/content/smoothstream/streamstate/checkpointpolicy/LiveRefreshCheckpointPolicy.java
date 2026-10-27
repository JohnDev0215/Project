package com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy;

import com.amazon.avod.content.downloading.ContentViewPersistence;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LiveRefreshCheckpointPolicy implements CheckpointPolicy {
    private ContentViewPersistence mContentViewPersistence;

    public LiveRefreshCheckpointPolicy(@Nonnull ContentViewPersistence contentViewPersistence) {
        this.mContentViewPersistence = (ContentViewPersistence) Preconditions.checkNotNull(contentViewPersistence, "contentViewPersistence");
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpoint(StreamType streamType, CachedContentView cachedContentView) {
        this.mContentViewPersistence.setContentView(streamType, cachedContentView);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpointInitFragments(StreamType streamType, Set<SmoothStreamingURI> set) {
        this.mContentViewPersistence.updateInitFragmentMap(streamType, set);
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public CachedContentView rebuildFromCheckpoint(StreamType streamType) {
        CachedContentView contentView = this.mContentViewPersistence.getContentView(streamType);
        return contentView == null ? new CachedContentView() : contentView;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public Set<SmoothStreamingURI> rebuildInitFragmentsFromCheckpoint(StreamType streamType) {
        Set<SmoothStreamingURI> initFragments = this.mContentViewPersistence.getInitFragments(streamType);
        return initFragments != null ? initFragments : Sets.newHashSet();
    }
}
