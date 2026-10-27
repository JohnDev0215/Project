package org.chromium.content.browser;

/* JADX INFO: loaded from: classes3.dex */
public interface PositionObserver {

    public interface Listener {
        void onPositionChanged(int i, int i2);
    }

    void addListener(Listener listener);

    void clearListener();

    int getPositionX();

    int getPositionY();

    void removeListener(Listener listener);
}
