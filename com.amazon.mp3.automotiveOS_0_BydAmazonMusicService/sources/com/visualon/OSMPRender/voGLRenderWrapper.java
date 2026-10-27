package com.visualon.OSMPRender;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.core.os.EnvironmentCompat;
import com.visualon.OSMPSensor.voSphericalParameter;
import com.visualon.OSMPSensor.voSphericalVideoView;
import com.visualon.OSMPUtils.voLog;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes7.dex */
public class voGLRenderWrapper implements GLSurfaceView.Renderer {
    private static final String TAG = "voGLRenderWrapper";
    private Context context_;
    private onGLRenderListener listener_;
    private long nativeContext_ = 0;
    private String libPath_ = null;
    private float heading_ = 0.0f;
    private float pitch_ = 0.0f;
    private float fov_ = 0.0f;
    private boolean changed_ = false;
    private boolean spherical_ = false;
    private boolean portrait_ = false;
    private boolean cube_ = false;
    private voSphericalVideoView SphericalView_ = null;

    public interface onGLRenderListener {
        void onGLInitFinish();
    }

    protected native int nativeDraw();

    protected native int nativeGLCreated(String str, int i);

    protected native int nativeGLDestroy();

    protected native int nativeSetDpi(float f, float f2);

    protected native int nativeSetSphericalParameter(Object obj);

    protected native int nativeSetSphericalVideoView(float f, float f2, float f3);

    protected native int nativeSurfaceChanged(int i, int i2);

    public static boolean isProbablyEmulator() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith(EnvironmentCompat.MEDIA_UNKNOWN)) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk") && !str2.contains("Emulator") && !str2.contains("Android SDK built for x86")) {
                return false;
            }
        }
        return true;
    }

    public voGLRenderWrapper(onGLRenderListener onglrenderlistener, Context context) {
        this.listener_ = onglrenderlistener;
        this.context_ = context;
    }

    public void setSpherical(boolean z) {
        this.spherical_ = z;
    }

    public void setCube(boolean z) {
        this.cube_ = z;
    }

    public void setLibraryPath(String str) {
        this.libPath_ = str;
    }

    public int setSphericalVideoView(float f, float f2, float f3) {
        this.heading_ = f;
        this.pitch_ = f2;
        this.fov_ = f3;
        this.changed_ = true;
        return 0;
    }

    public int setSphericalVideoView(voSphericalVideoView vosphericalvideoview) {
        this.SphericalView_ = vosphericalvideoview;
        return 0;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, gl10.glGetString(7937), new Object[0]);
        }
        FeatureInfo[] systemAvailableFeatures = this.context_.getPackageManager().getSystemAvailableFeatures();
        int i = 65536;
        if (systemAvailableFeatures != null && systemAvailableFeatures.length > 0) {
            for (FeatureInfo featureInfo : systemAvailableFeatures) {
                if (featureInfo.name == null) {
                    int i2 = featureInfo.reqGlEsVersion;
                    if (i2 != 0) {
                        voLog.d(TAG, "GLES version is " + featureInfo.reqGlEsVersion, new Object[0]);
                        i = i2;
                    } else {
                        voLog.d(TAG, "GLES version not found.", new Object[0]);
                    }
                }
            }
        }
        nativeGLCreated(this.libPath_, i);
        onGLRenderListener onglrenderlistener = this.listener_;
        if (onglrenderlistener != null) {
            onglrenderlistener.onGLInitFinish();
        }
        this.portrait_ = isPortraitMode();
        DisplayMetrics displayMetrics = this.context_.getApplicationContext().getResources().getDisplayMetrics();
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "onSurfaceCreated %f %f", Float.valueOf(displayMetrics.xdpi), Float.valueOf(displayMetrics.ydpi));
        }
        nativeSetDpi(displayMetrics.xdpi, displayMetrics.ydpi);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        onGLRenderListener onglrenderlistener = this.listener_;
        if (onglrenderlistener != null) {
            onglrenderlistener.onGLInitFinish();
        }
        this.portrait_ = isPortraitMode();
        nativeSurfaceChanged(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        voSphericalVideoView vosphericalvideoview;
        if ((this.spherical_ || this.cube_) && (vosphericalvideoview = this.SphericalView_) != null) {
            nativeSetSphericalParameter(new voSphericalParameter(this.portrait_, vosphericalvideoview.getRotation(), this.SphericalView_.getFOV()));
        }
        nativeDraw();
    }

    public long getNativeWrapper() {
        return this.nativeContext_;
    }

    public void destroy() {
        this.listener_ = null;
    }

    private boolean isPortraitMode() {
        return this.context_.getResources().getConfiguration().orientation == 1;
    }
}
