package com.facebook.drawee.backends.pipeline.info;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface ImageOriginListener {
    void onImageLoaded(String controllerId, int imageOrigin, boolean successful, @Nullable String ultimateProducerName);
}
