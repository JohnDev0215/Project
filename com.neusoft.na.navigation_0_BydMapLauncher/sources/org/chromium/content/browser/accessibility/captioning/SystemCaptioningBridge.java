package org.chromium.content.browser.accessibility.captioning;

/* JADX INFO: loaded from: classes3.dex */
public interface SystemCaptioningBridge {

    public interface SystemCaptioningBridgeListener {
        void onSystemCaptioningChanged(TextTrackSettings textTrackSettings);
    }

    void addListener(SystemCaptioningBridgeListener systemCaptioningBridgeListener);

    void removeListener(SystemCaptioningBridgeListener systemCaptioningBridgeListener);

    void syncToListener(SystemCaptioningBridgeListener systemCaptioningBridgeListener);
}
