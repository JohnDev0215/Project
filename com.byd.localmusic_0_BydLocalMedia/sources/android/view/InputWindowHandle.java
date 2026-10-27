package android.view;

import android.graphics.Region;
import android.os.IBinder;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class InputWindowHandle {
    public boolean canReceiveKeys;
    public long dispatchingTimeoutNanos;
    public int displayId;
    public int frameBottom;
    public int frameLeft;
    public int frameRight;
    public int frameTop;
    public boolean hasFocus;
    public boolean hasWallpaper;
    public InputApplicationHandle inputApplicationHandle;
    public int inputFeatures;
    public int layoutParamsFlags;
    public int layoutParamsType;
    public String name;
    public int ownerPid;
    public int ownerUid;
    public boolean paused;
    private long ptr;
    public boolean replaceTouchableRegionWithCrop;
    public float scaleFactor;
    public int surfaceInset;
    public IBinder token;
    public boolean visible;
    public final Region touchableRegion = new Region();
    public int portalToDisplayId = -1;
    public WeakReference<SurfaceControl> touchableRegionSurfaceControl = new WeakReference<>(null);

    private native void nativeDispose();

    public InputWindowHandle(InputApplicationHandle inputApplicationHandle, int i) {
        this.inputApplicationHandle = inputApplicationHandle;
        this.displayId = i;
    }

    public String toString() {
        String str = this.name;
        if (str == null) {
            str = "";
        }
        return str + ", frame=[" + this.frameLeft + "," + this.frameTop + "," + this.frameRight + "," + this.frameBottom + "], touchableRegion=" + this.touchableRegion + ", visible=" + this.visible;
    }

    protected void finalize() throws Throwable {
        try {
            nativeDispose();
        } finally {
            super.finalize();
        }
    }

    public void replaceTouchableRegionWithCrop(SurfaceControl surfaceControl) {
        setTouchableRegionCrop(surfaceControl);
        this.replaceTouchableRegionWithCrop = true;
    }

    public void setTouchableRegionCrop(SurfaceControl surfaceControl) {
        this.touchableRegionSurfaceControl = new WeakReference<>(surfaceControl);
    }
}
