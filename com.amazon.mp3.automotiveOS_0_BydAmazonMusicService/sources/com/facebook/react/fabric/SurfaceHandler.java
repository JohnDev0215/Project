package com.facebook.react.fabric;

import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes6.dex */
@ThreadSafe
public interface SurfaceHandler {
    String getModuleName();

    int getSurfaceId();

    boolean isRunning();

    void setMountable(boolean z);

    void setSurfaceId(int i);

    void start();

    void stop();
}
