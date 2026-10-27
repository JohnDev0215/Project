package com.android.systemui.shared.system;

import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;

/* JADX INFO: loaded from: classes.dex */
public class SurfaceControlCompat {
    final SurfaceControl mSurfaceControl;

    public SurfaceControlCompat(SurfaceControl surfaceControl) {
        this.mSurfaceControl = surfaceControl;
    }

    public SurfaceControlCompat(View v) {
        ViewRootImpl viewRootImpl = v.getViewRootImpl();
        this.mSurfaceControl = viewRootImpl != null ? viewRootImpl.getSurfaceControl() : null;
    }

    public boolean isValid() {
        SurfaceControl surfaceControl = this.mSurfaceControl;
        return surfaceControl != null && surfaceControl.isValid();
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }
}
