package com.facebook.imagepipeline.debug;

import com.facebook.common.references.SharedReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface CloseableReferenceLeakTracker {
    boolean isSet();

    void trackCloseableReferenceLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace);
}
