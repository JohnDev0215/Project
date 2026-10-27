package org.chromium.content.browser;

/* JADX INFO: loaded from: classes3.dex */
public interface OverscrollRefreshHandler {
    void pull(float f);

    void release(boolean z);

    void reset();

    void setEnabled(boolean z);

    boolean start();
}
