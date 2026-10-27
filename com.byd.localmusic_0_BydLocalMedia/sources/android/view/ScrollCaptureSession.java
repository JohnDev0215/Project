package android.view;

import android.graphics.Point;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes3.dex */
public class ScrollCaptureSession {
    private ScrollCaptureClient mClient;
    private final Point mPositionInWindow;
    private final Rect mScrollBounds;
    private final Surface mSurface;

    public ScrollCaptureSession(Surface surface, Rect rect, Point point, ScrollCaptureClient scrollCaptureClient) {
        this.mSurface = surface;
        this.mScrollBounds = rect;
        this.mPositionInWindow = point;
        this.mClient = scrollCaptureClient;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public Rect getScrollBounds() {
        return this.mScrollBounds;
    }

    public Point getPositionInWindow() {
        return this.mPositionInWindow;
    }

    public void notifyBufferSent(long j, Rect rect) {
        ScrollCaptureClient scrollCaptureClient = this.mClient;
        if (scrollCaptureClient != null) {
            scrollCaptureClient.onRequestImageCompleted(j, rect);
        }
    }

    public void disconnect() {
        this.mClient = null;
        if (this.mSurface.isValid()) {
            this.mSurface.release();
        }
    }
}
