package com.king.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.king.zxing.camera.open.CameraFacing;

/* JADX INFO: compiled from: CameraConfigurationManager.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f4564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Point f4567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Point f4568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Point f4569f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Point f4570g;

    b(Context context) {
        this.f4564a = context;
    }

    private void a(Camera.Parameters parameters, boolean z, boolean z2) {
        c.j(parameters, z);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f4564a);
        if (z2 || defaultSharedPreferences.getBoolean("preferences_disable_exposure", true)) {
            return;
        }
        c.e(parameters, z);
    }

    private void f(Camera.Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        a(parameters, FrontLightMode.readPref(sharedPreferences) == FrontLightMode.ON, z);
    }

    Point b() {
        return this.f4568e;
    }

    Point c() {
        return this.f4567d;
    }

    boolean d(Camera camera) {
        Camera.Parameters parameters;
        if (camera == null || (parameters = camera.getParameters()) == null) {
            return false;
        }
        String flashMode = parameters.getFlashMode();
        return "on".equals(flashMode) || "torch".equals(flashMode);
    }

    void e(com.king.zxing.camera.open.a aVar) {
        int i;
        Camera.Parameters parameters = aVar.a().getParameters();
        Display defaultDisplay = ((WindowManager) this.f4564a.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        if (rotation == 0) {
            i = 0;
        } else if (rotation == 1) {
            i = 90;
        } else if (rotation == 2) {
            i = 180;
        } else if (rotation == 3) {
            i = 270;
        } else {
            if (rotation % 90 != 0) {
                throw new IllegalArgumentException("Bad rotation: " + rotation);
            }
            i = (rotation + 360) % 360;
        }
        Log.i("CameraConfiguration", "Display at: " + i);
        int iC = aVar.c();
        Log.i("CameraConfiguration", "Camera at: " + iC);
        CameraFacing cameraFacingB = aVar.b();
        CameraFacing cameraFacing = CameraFacing.FRONT;
        if (cameraFacingB == cameraFacing) {
            iC = (360 - iC) % 360;
            Log.i("CameraConfiguration", "Front camera overriden to: " + iC);
        }
        this.f4566c = ((iC + 360) - i) % 360;
        Log.i("CameraConfiguration", "Final display orientation: " + this.f4566c);
        if (aVar.b() == cameraFacing) {
            Log.i("CameraConfiguration", "Compensating rotation for front camera");
            this.f4565b = (360 - this.f4566c) % 360;
        } else {
            this.f4565b = this.f4566c;
        }
        Log.i("CameraConfiguration", "Clockwise rotation from display to camera: " + this.f4565b);
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.f4567d = point;
        Log.i("CameraConfiguration", "Screen resolution in current orientation: " + this.f4567d);
        this.f4568e = c.b(parameters, this.f4567d);
        Log.i("CameraConfiguration", "Camera resolution: " + this.f4568e);
        this.f4569f = c.b(parameters, this.f4567d);
        Log.i("CameraConfiguration", "Best available preview size: " + this.f4569f);
        Point point2 = this.f4567d;
        boolean z = point2.x < point2.y;
        Point point3 = this.f4569f;
        int i2 = point3.x;
        int i3 = point3.y;
        if (z == (i2 < i3)) {
            this.f4570g = point3;
        } else {
            this.f4570g = new Point(i3, i2);
        }
        Log.i("CameraConfiguration", "Preview size on screen: " + this.f4570g);
    }

    void g(com.king.zxing.camera.open.a aVar, boolean z) {
        Camera cameraA = aVar.a();
        Camera.Parameters parameters = cameraA.getParameters();
        if (parameters == null) {
            Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i("CameraConfiguration", "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f4564a);
        if (parameters.isZoomSupported()) {
            parameters.setZoom(parameters.getMaxZoom() / 10);
        }
        f(parameters, defaultSharedPreferences, z);
        c.f(parameters, defaultSharedPreferences.getBoolean("preferences_auto_focus", true), defaultSharedPreferences.getBoolean("preferences_disable_continuous_focus", true), z);
        if (!z) {
            if (defaultSharedPreferences.getBoolean("preferences_invert_scan", false)) {
                c.h(parameters);
            }
            if (!defaultSharedPreferences.getBoolean("preferences_disable_barcode_scene_mode", true)) {
                c.d(parameters);
            }
            if (!defaultSharedPreferences.getBoolean("preferences_disable_metering", true)) {
                c.k(parameters);
                c.g(parameters);
                c.i(parameters);
            }
            parameters.setRecordingHint(true);
        }
        Point point = this.f4569f;
        parameters.setPreviewSize(point.x, point.y);
        cameraA.setParameters(parameters);
        cameraA.setDisplayOrientation(this.f4566c);
        Camera.Size previewSize = cameraA.getParameters().getPreviewSize();
        if (previewSize != null) {
            Point point2 = this.f4569f;
            if (point2.x == previewSize.width && point2.y == previewSize.height) {
                return;
            }
            Log.w("CameraConfiguration", "Camera said it supported preview size " + this.f4569f.x + 'x' + this.f4569f.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            Point point3 = this.f4569f;
            point3.x = previewSize.width;
            point3.y = previewSize.height;
        }
    }

    void h(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        a(parameters, z, false);
        camera.setParameters(parameters);
    }
}
