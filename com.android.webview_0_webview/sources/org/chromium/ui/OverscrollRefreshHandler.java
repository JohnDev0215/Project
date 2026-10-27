package org.chromium.ui;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface OverscrollRefreshHandler {
    void pull(float f, float f2);

    void release(boolean z);

    void reset();

    boolean start(int i, float f, float f2, boolean z);
}
