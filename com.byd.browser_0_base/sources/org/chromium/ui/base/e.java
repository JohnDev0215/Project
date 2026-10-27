package org.chromium.ui.base;

import J.N;
import android.os.Handler;
import android.os.Looper;
import android.view.AttachedSurfaceControl;
import android.view.FrameMetrics;
import android.view.Window;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: OverlayTransformApiHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e implements AttachedSurfaceControl.OnBufferTransformHintChangedListener, Window.OnFrameMetricsAvailableListener {
    public final WindowAndroid a;
    public final WeakReference<Window> b;
    public boolean c;
    public boolean d;

    public e(WindowAndroid windowAndroid) {
        Window window;
        this.a = windowAndroid;
        WeakReference<Window> weakReference = new WeakReference<>(windowAndroid.p());
        this.b = weakReference;
        Window window2 = weakReference.get();
        if (window2 == null) {
            return;
        }
        if (window2.getRootSurfaceControl() != null) {
            b();
        } else {
            if (this.d || (window = weakReference.get()) == null) {
                return;
            }
            window.addOnFrameMetricsAvailableListener(this, new Handler(Looper.myLooper()));
            this.d = true;
        }
    }

    public final void a() {
        Window window;
        AttachedSurfaceControl rootSurfaceControl;
        Window window2;
        if (this.d && (window2 = this.b.get()) != null) {
            window2.removeOnFrameMetricsAvailableListener(this);
            this.d = false;
        }
        if (!this.c || (window = this.b.get()) == null || (rootSurfaceControl = window.getRootSurfaceControl()) == null) {
            return;
        }
        rootSurfaceControl.removeOnBufferTransformHintChangedListener(this);
        this.c = false;
    }

    public final void b() {
        Window window;
        AttachedSurfaceControl rootSurfaceControl;
        if (this.c || (window = this.b.get()) == null || (rootSurfaceControl = window.getRootSurfaceControl()) == null) {
            return;
        }
        rootSurfaceControl.addOnBufferTransformHintChangedListener(this);
        this.c = true;
    }

    public final int c() {
        AttachedSurfaceControl rootSurfaceControl;
        Window window = this.b.get();
        if (window == null || (rootSurfaceControl = window.getRootSurfaceControl()) == null) {
            return 0;
        }
        try {
            int bufferTransformHint = rootSurfaceControl.getBufferTransformHint();
            int i = 1;
            if (bufferTransformHint != 0) {
                if (bufferTransformHint == 1) {
                    return 2;
                }
                i = 3;
                if (bufferTransformHint != 2) {
                    if (bufferTransformHint == 3) {
                        return 5;
                    }
                    i = 4;
                    if (bufferTransformHint != 4) {
                        return bufferTransformHint != 7 ? 0 : 6;
                    }
                }
            }
            return i;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    @Override // android.view.AttachedSurfaceControl.OnBufferTransformHintChangedListener
    public final void onBufferTransformHintChanged(int i) {
        WindowAndroid windowAndroid = this.a;
        long j = windowAndroid.g;
        if (j != 0) {
            N.MGuj6mQj(j, windowAndroid);
        }
    }

    @Override // android.view.Window.OnFrameMetricsAvailableListener
    public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
        Window window2;
        if (window.getRootSurfaceControl() != null) {
            if (this.d && (window2 = this.b.get()) != null) {
                window2.removeOnFrameMetricsAvailableListener(this);
                this.d = false;
            }
            b();
        }
    }
}
