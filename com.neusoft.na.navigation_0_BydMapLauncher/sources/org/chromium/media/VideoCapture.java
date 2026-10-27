package org.chromium.media;

import android.content.Context;
import android.view.WindowManager;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
public abstract class VideoCapture {
    protected int mCameraNativeOrientation;
    protected VideoCaptureFormat mCaptureFormat = null;
    protected final Context mContext;
    protected final int mId;
    protected boolean mInvertDeviceOrientationReadings;
    protected final long mNativeVideoCaptureDeviceAndroid;

    public abstract boolean allocate(int i, int i2, int i3);

    public abstract void deallocate();

    public abstract PhotoCapabilities getPhotoCapabilities();

    public native void nativeOnError(long j, String str);

    public native void nativeOnFrameAvailable(long j, byte[] bArr, int i, int i2);

    public native void nativeOnPhotoTaken(long j, long j2, byte[] bArr);

    public abstract boolean startCapture();

    public abstract boolean stopCapture();

    public abstract boolean takePhoto(long j);

    VideoCapture(Context context, int i, long j) {
        this.mContext = context;
        this.mId = i;
        this.mNativeVideoCaptureDeviceAndroid = j;
    }

    public final int queryWidth() {
        return this.mCaptureFormat.mWidth;
    }

    public final int queryHeight() {
        return this.mCaptureFormat.mHeight;
    }

    public final int queryFrameRate() {
        return this.mCaptureFormat.mFramerate;
    }

    public final int getColorspace() {
        int i = this.mCaptureFormat.mPixelFormat;
        int i2 = 17;
        if (i != 17) {
            i2 = 35;
            if (i != 35) {
                i2 = AndroidImageFormat.YV12;
                if (i != 842094169) {
                    return 0;
                }
            }
        }
        return i2;
    }

    protected final int getCameraRotation() {
        return (this.mCameraNativeOrientation + (this.mInvertDeviceOrientationReadings ? 360 - getDeviceRotation() : getDeviceRotation())) % 360;
    }

    protected final int getDeviceRotation() {
        Context context = this.mContext;
        if (context == null) {
            return 0;
        }
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }
}
