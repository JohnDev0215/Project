package android.view;

import android.graphics.Rect;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public interface ScrollCaptureCallback {
    void onScrollCaptureEnd(Runnable runnable);

    void onScrollCaptureImageRequest(ScrollCaptureSession scrollCaptureSession, Rect rect);

    void onScrollCaptureSearch(Consumer<Rect> consumer);

    void onScrollCaptureStart(ScrollCaptureSession scrollCaptureSession, Runnable runnable);
}
