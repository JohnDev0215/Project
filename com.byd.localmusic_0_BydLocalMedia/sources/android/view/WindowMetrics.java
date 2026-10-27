package android.view;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowMetrics {
    private final Rect mBounds;
    private final WindowInsets mWindowInsets;

    public WindowMetrics(Rect rect, WindowInsets windowInsets) {
        this.mBounds = rect;
        this.mWindowInsets = windowInsets;
    }

    public Rect getBounds() {
        return this.mBounds;
    }

    public WindowInsets getWindowInsets() {
        return this.mWindowInsets;
    }
}
