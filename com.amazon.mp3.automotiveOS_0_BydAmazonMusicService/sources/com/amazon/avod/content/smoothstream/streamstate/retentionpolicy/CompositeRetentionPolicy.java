package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeRetentionPolicy implements RetentionPolicy {
    private final RetentionPolicy mPostPlayerBindPolicy;
    private final RetentionPolicy mPrePlayerBindPolicy;

    public CompositeRetentionPolicy(@Nonnull RetentionPolicy retentionPolicy, @Nonnull RetentionPolicy retentionPolicy2) {
        this.mPrePlayerBindPolicy = (RetentionPolicy) Preconditions.checkNotNull(retentionPolicy, "prePlayerBindPolicy");
        this.mPostPlayerBindPolicy = (RetentionPolicy) Preconditions.checkNotNull(retentionPolicy2, "postPlayerBindPolicy");
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI smoothStreamingURI, long j, @Nonnull ContentSessionContext contentSessionContext) {
        if (!contentSessionContext.getState().isBoundToPlayer()) {
            return this.mPrePlayerBindPolicy.shouldRetain(smoothStreamingURI, j, contentSessionContext);
        }
        return this.mPostPlayerBindPolicy.shouldRetain(smoothStreamingURI, j, contentSessionContext);
    }
}
