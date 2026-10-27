package com.android.systemui.shared.system;

import android.graphics.HardwareRenderer;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes.dex */
public class ViewRootImplCompat {
    private final ViewRootImpl mViewRoot;

    public ViewRootImplCompat(View view) {
        this.mViewRoot = view == null ? null : view.getViewRootImpl();
    }

    public SurfaceControl getRenderSurfaceControl() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getRenderSurfaceControl();
    }

    public SurfaceControl getSurfaceControl() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getSurfaceControl();
    }

    public boolean isValid() {
        return this.mViewRoot != null;
    }

    public View getView() {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getView();
    }

    public void registerRtFrameCallback(final LongConsumer callback) {
        ViewRootImpl viewRootImpl = this.mViewRoot;
        if (viewRootImpl != null) {
            viewRootImpl.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: com.android.systemui.shared.system.ViewRootImplCompat.1
                public void onFrameDraw(long l) {
                    callback.accept(l);
                }
            });
        }
    }
}
