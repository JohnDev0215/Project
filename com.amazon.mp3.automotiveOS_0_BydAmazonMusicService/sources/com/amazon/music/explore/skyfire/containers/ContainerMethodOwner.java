package com.amazon.music.explore.skyfire.containers;

import CoreInterface.v1_0.Method;

/* JADX INFO: loaded from: classes4.dex */
public interface ContainerMethodOwner {

    public interface CloseCallback {
        void onClose();
    }

    String getOwnerId();

    void handleMethod(String str, Method method);

    void setCloseCallback(CloseCallback closeCallback);
}
