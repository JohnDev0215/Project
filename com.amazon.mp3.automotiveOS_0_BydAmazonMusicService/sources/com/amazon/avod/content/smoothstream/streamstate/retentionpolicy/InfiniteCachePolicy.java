package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class InfiniteCachePolicy implements RetentionPolicy {
    @Override // com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicy
    public boolean shouldRetain(SmoothStreamingURI smoothStreamingURI, long j, @Nonnull ContentSessionContext contentSessionContext) {
        return true;
    }
}
