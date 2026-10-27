package com.facebook.imagepipeline.debug;

import com.facebook.common.references.SharedReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class NoOpCloseableReferenceLeakTracker implements CloseableReferenceLeakTracker {
    @Override // com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker
    public boolean isSet() {
        return false;
    }

    @Override // com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker
    public void trackCloseableReferenceLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace) {
    }
}
