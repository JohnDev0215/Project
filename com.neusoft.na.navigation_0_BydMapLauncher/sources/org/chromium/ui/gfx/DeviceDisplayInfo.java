package org.chromium.ui.gfx;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("gfx")
public class DeviceDisplayInfo {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Context mAppContext;
    private final WindowManager mWinManager;
    private Point mTempPoint = new Point();
    private DisplayMetrics mTempMetrics = new DisplayMetrics();

    private int getPixelFormat() {
        return 1;
    }

    private native void nativeUpdateSharedDeviceDisplayInfo(int i, int i2, int i3, int i4, int i5, int i6, double d, int i7, int i8);

    private DeviceDisplayInfo(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mWinManager = (WindowManager) applicationContext.getSystemService("window");
    }

    public int getDisplayHeight() {
        getDisplay().getSize(this.mTempPoint);
        return this.mTempPoint.y;
    }

    public int getDisplayWidth() {
        getDisplay().getSize(this.mTempPoint);
        return this.mTempPoint.x;
    }

    public int getPhysicalDisplayHeight() {
        getDisplay().getRealSize(this.mTempPoint);
        return this.mTempPoint.y;
    }

    public int getPhysicalDisplayWidth() {
        getDisplay().getRealSize(this.mTempPoint);
        return this.mTempPoint.x;
    }

    public int getBitsPerPixel() {
        int pixelFormat = getPixelFormat();
        PixelFormat pixelFormat2 = new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelFormat, pixelFormat2);
        return pixelFormat2.bitsPerPixel;
    }

    public int getBitsPerComponent() {
        switch (getPixelFormat()) {
            case 4:
            case 6:
                return 5;
            case 5:
            default:
                return 8;
            case 7:
                return 4;
            case 8:
            case 9:
            case 10:
                return 0;
            case 11:
                return 2;
        }
    }

    public double getDIPScale() {
        getDisplay().getMetrics(this.mTempMetrics);
        return this.mTempMetrics.density;
    }

    private int getSmallestDIPWidth() {
        return this.mAppContext.getResources().getConfiguration().smallestScreenWidthDp;
    }

    public int getRotationDegrees() {
        int rotation = getDisplay().getRotation();
        if (rotation == 0) {
            return 0;
        }
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    public void updateNativeSharedDisplayInfo() {
        nativeUpdateSharedDeviceDisplayInfo(getDisplayHeight(), getDisplayWidth(), getPhysicalDisplayHeight(), getPhysicalDisplayWidth(), getBitsPerPixel(), getBitsPerComponent(), getDIPScale(), getSmallestDIPWidth(), getRotationDegrees());
    }

    private Display getDisplay() {
        return this.mWinManager.getDefaultDisplay();
    }

    public static DeviceDisplayInfo create(Context context) {
        return new DeviceDisplayInfo(context);
    }
}
