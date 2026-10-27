package com.facebook.drawee.backends.pipeline.info;

/* JADX INFO: loaded from: classes5.dex */
public interface ImagePerfNotifier {
    void notifyListenersOfVisibilityStateUpdate(ImagePerfState state, int visibilityState);

    void notifyStatusUpdated(ImagePerfState state, int imageLoadStatus);
}
