package defpackage;

import J.N;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.os.Build;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.media.PhotoCapabilities;
import org.chromium.media.VideoCapture;
import org.chromium.media.VideoCaptureFormat;

/* JADX INFO: renamed from: cI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0598cI extends VideoCapture implements Camera.PreviewCallback {
    public static final SparseArray r;
    public int f;
    public final Object g;
    public long h;
    public int i;
    public int j;
    public Camera.Area k;
    public Camera.Parameters l;
    public Camera m;
    public ReentrantLock n;
    public boolean o;
    public int[] p;
    public SurfaceTexture q;

    static {
        SparseArray sparseArray = new SparseArray();
        r = sparseArray;
        sparseArray.append(2850, "incandescent");
        r.append(2950, "warm-fluorescent");
        r.append(4250, "fluorescent");
        r.append(4600, "twilight");
        r.append(5500, "daylight");
        r.append(6000, "cloudy-daylight");
        r.append(7000, "shade");
    }

    public C0598cI(int i, long j) {
        super(i, j);
        this.g = new Object();
        this.n = new ReentrantLock();
    }

    public static Camera.CameraInfo e(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
            return cameraInfo;
        } catch (RuntimeException e) {
            Ts.a("VideoCapture", "getCameraInfo: Camera.getCameraInfo: " + e, new Object[0]);
            return null;
        }
    }

    public static Camera.Parameters f(Camera camera) {
        try {
            return camera.getParameters();
        } catch (RuntimeException e) {
            Ts.a("VideoCapture", "getCameraParameters: android.hardware.Camera.getParameters: " + e, new Object[0]);
            if (camera == null) {
                return null;
            }
            camera.release();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.VideoCaptureFormat[] g(int r15) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0598cI.g(int):org.chromium.media.VideoCaptureFormat[]");
    }

    @Override // org.chromium.media.VideoCapture
    public boolean allocate(int i, int i2, int i3, boolean z) {
        int i4;
        try {
            this.m = Camera.open(this.d);
            Camera.CameraInfo cameraInfoE = e(this.d);
            if (cameraInfoE == null) {
                this.m.release();
                this.m = null;
                return false;
            }
            this.a = cameraInfoE.orientation;
            this.b = cameraInfoE.facing == 0;
            b();
            Camera.Parameters parametersF = f(this.m);
            if (parametersF == null) {
                this.m = null;
                return false;
            }
            List<int[]> supportedPreviewFpsRange = parametersF.getSupportedPreviewFpsRange();
            if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() == 0) {
                Ts.a("VideoCapture", "allocate: no fps range found", new Object[0]);
                return false;
            }
            ArrayList arrayList = new ArrayList(supportedPreviewFpsRange.size());
            for (int[] iArr : supportedPreviewFpsRange) {
                arrayList.add(new KH(iArr[0], iArr[1]));
            }
            KH kh = (KH) Collections.min(arrayList, new JH(i3 * 1000));
            int[] iArr2 = {kh.a, kh.b};
            int i5 = iArr2[0];
            int i6 = iArr2[1];
            int i7 = i;
            int i8 = i2;
            int i9 = Integer.MAX_VALUE;
            for (Camera.Size size : parametersF.getSupportedPreviewSizes()) {
                int iAbs = Math.abs(size.height - i2) + Math.abs(size.width - i);
                if (iAbs < i9) {
                    int i10 = size.width;
                    if (i10 % 32 == 0) {
                        i8 = size.height;
                        i7 = i10;
                        i9 = iAbs;
                    }
                }
            }
            if (i9 == Integer.MAX_VALUE) {
                Ts.a("VideoCapture", "allocate: can not find a multiple-of-32 resolution", new Object[0]);
                return false;
            }
            if (parametersF.isVideoStabilizationSupported()) {
                parametersF.getVideoStabilization();
                parametersF.setVideoStabilization(true);
            }
            if (parametersF.getSupportedFocusModes().contains("continuous-video")) {
                parametersF.setFocusMode("continuous-video");
            }
            int i11 = iArr2[1] / 1000;
            String[] strArr = MH.a;
            int length = strArr.length;
            int i12 = 0;
            while (true) {
                if (i12 >= length) {
                    i4 = 842094169;
                    break;
                }
                if (strArr[i12].contentEquals(Build.MODEL)) {
                    i4 = 17;
                    break;
                }
                i12++;
            }
            this.c = new VideoCaptureFormat(i7, i8, i11, i4);
            parametersF.setPictureSize(i7, i8);
            parametersF.setPreviewSize(i7, i8);
            parametersF.setPreviewFpsRange(iArr2[0], iArr2[1]);
            parametersF.setPreviewFormat(this.c.d);
            try {
                this.m.setParameters(parametersF);
                int[] iArr3 = new int[1];
                this.p = iArr3;
                GLES20.glGenTextures(1, iArr3, 0);
                GLES20.glBindTexture(36197, this.p[0]);
                GLES20.glTexParameterf(36197, 10241, 9729.0f);
                GLES20.glTexParameterf(36197, 10240, 9729.0f);
                GLES20.glTexParameteri(36197, 10242, 33071);
                GLES20.glTexParameteri(36197, 10243, 33071);
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.p[0]);
                this.q = surfaceTexture;
                surfaceTexture.setOnFrameAvailableListener(null);
                try {
                    this.m.setPreviewTexture(this.q);
                    this.m.setErrorCallback(new NH(this, null));
                    VideoCaptureFormat videoCaptureFormat = this.c;
                    this.f = (ImageFormat.getBitsPerPixel(videoCaptureFormat.d) * (videoCaptureFormat.a * videoCaptureFormat.b)) / 8;
                    for (int i13 = 0; i13 < 3; i13++) {
                        this.m.addCallbackBuffer(new byte[this.f]);
                    }
                    return true;
                } catch (IOException e) {
                    Ts.a("VideoCapture", "allocate: " + e, new Object[0]);
                    return false;
                }
            } catch (RuntimeException e2) {
                Ts.a("VideoCapture", "setParameters: " + e2, new Object[0]);
                return false;
            }
        } catch (RuntimeException e3) {
            Ts.a("VideoCapture", "allocate: Camera.open: " + e3, new Object[0]);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
        Camera camera = this.m;
        if (camera == null) {
            return;
        }
        if (camera == null) {
            Ts.a("VideoCapture", "stopCaptureAndBlockUntilStopped: mCamera is null", new Object[0]);
        } else {
            this.n.lock();
            try {
                if (this.o) {
                    this.o = false;
                    this.n.unlock();
                    this.m.stopPreview();
                    this.m.setPreviewCallbackWithBuffer(null);
                }
            } finally {
                this.n.unlock();
            }
        }
        try {
            this.m.setPreviewTexture(null);
            if (this.p != null) {
                GLES20.glDeleteTextures(1, this.p, 0);
            }
            this.c = null;
            this.m.release();
            this.m = null;
        } catch (IOException e) {
            Ts.a("VideoCapture", "deallocate: failed to deallocate camera, " + e, new Object[0]);
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void getPhotoCapabilitiesAsync(long j) {
        int[] iArr;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        int iIntValue4;
        int i;
        int[] iArr2;
        int iIndexOfValue;
        Camera.Parameters parametersF = f(this.m);
        if (parametersF == null) {
            N.MumkJZ3e(this.e, this, j, null);
            return;
        }
        boolean[] zArr = new boolean[3];
        double[] dArr = new double[16];
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[3];
        int[][] iArr5 = new int[3][];
        Ts.d("VideoCapture", " CAM params: %s", parametersF.flatten());
        iArr3[0] = 0;
        iArr3[1] = 0;
        iArr3[2] = 0;
        iArr3[3] = 0;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = 0;
        for (Camera.Size size : parametersF.getSupportedPictureSizes()) {
            int i6 = size.width;
            if (i6 < i3) {
                i3 = i6;
            }
            int i7 = size.height;
            if (i7 < i2) {
                i2 = i7;
            }
            int i8 = size.width;
            if (i8 > i5) {
                i5 = i8;
            }
            int i9 = size.height;
            if (i9 > i4) {
                i4 = i9;
            }
        }
        Camera.Size previewSize = parametersF.getPreviewSize();
        iArr3[4] = i2;
        iArr3[5] = i4;
        iArr3[7] = 1;
        iArr3[6] = previewSize.height;
        iArr3[8] = i3;
        iArr3[9] = i5;
        iArr3[11] = 1;
        iArr3[10] = previewSize.width;
        if (parametersF.isZoomSupported()) {
            iIntValue = parametersF.getZoomRatios().get(parametersF.getMaxZoom()).intValue();
            iIntValue3 = parametersF.getZoomRatios().get(parametersF.getZoom()).intValue();
            iIntValue4 = parametersF.getZoomRatios().get(0).intValue();
            if (parametersF.getZoomRatios().size() > 1) {
                iIntValue2 = parametersF.getZoomRatios().get(1).intValue() - parametersF.getZoomRatios().get(0).intValue();
                iArr = iArr4;
            } else {
                iArr = iArr4;
                iIntValue2 = 0;
            }
        } else {
            iArr = iArr4;
            iIntValue = 0;
            iIntValue2 = 0;
            iIntValue3 = 0;
            iIntValue4 = 0;
        }
        dArr[0] = iIntValue4;
        dArr[1] = iIntValue;
        dArr[2] = iIntValue3;
        dArr[3] = iIntValue2;
        List<String> supportedFocusModes = parametersF.getSupportedFocusModes();
        ArrayList arrayList = new ArrayList(3);
        if (supportedFocusModes.contains("continuous-video") || supportedFocusModes.contains("continuous-picture") || supportedFocusModes.contains("edof")) {
            arrayList.add(4);
        }
        if (supportedFocusModes.contains("auto") || supportedFocusModes.contains("macro")) {
            arrayList.add(3);
        }
        if (supportedFocusModes.contains("infinity") || supportedFocusModes.contains("fixed")) {
            arrayList.add(2);
        }
        iArr5[0] = (int[]) VideoCapture.c(arrayList).clone();
        String focusMode = parametersF.getFocusMode();
        iArr[0] = (focusMode.equals("continuous-video") || focusMode.equals("continuous-picture") || focusMode.equals("edof")) ? 4 : (focusMode.equals("auto") || focusMode.equals("macro")) ? 3 : (focusMode.equals("infinity") || focusMode.equals("fixed")) ? 2 : 1;
        ArrayList arrayList2 = new ArrayList(2);
        arrayList2.add(4);
        if (parametersF.isAutoExposureLockSupported()) {
            arrayList2.add(2);
        }
        iArr5[1] = (int[]) VideoCapture.c(arrayList2).clone();
        iArr[1] = (parametersF.isAutoExposureLockSupported() && parametersF.getAutoExposureLock()) ? 2 : 4;
        dArr[11] = parametersF.getExposureCompensationStep();
        dArr[8] = parametersF.getMinExposureCompensation() * r1;
        dArr[9] = parametersF.getMaxExposureCompensation() * r1;
        dArr[10] = parametersF.getExposureCompensation() * r1;
        ArrayList arrayList3 = new ArrayList(2);
        List<String> supportedWhiteBalance = parametersF.getSupportedWhiteBalance();
        if (supportedWhiteBalance != null) {
            if (supportedWhiteBalance.isEmpty()) {
                i = 4;
            } else {
                i = 4;
                arrayList3.add(4);
            }
            if (parametersF.isAutoWhiteBalanceLockSupported()) {
                arrayList3.add(2);
            }
        } else {
            i = 4;
        }
        iArr5[2] = (int[]) VideoCapture.c(arrayList3).clone();
        if (parametersF.isAutoWhiteBalanceLockSupported() && parametersF.getAutoWhiteBalanceLock()) {
            i = 2;
        }
        iArr[2] = i;
        iArr3[12] = r.keyAt(0);
        SparseArray sparseArray = r;
        iArr3[13] = sparseArray.keyAt(sparseArray.size() - 1);
        iArr3[15] = 50;
        if (i == 2 && (iIndexOfValue = r.indexOfValue(parametersF.getWhiteBalance())) >= 0) {
            iArr3[14] = r.keyAt(iIndexOfValue);
        }
        List<String> supportedFlashModes = parametersF.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            zArr[0] = supportedFlashModes.contains("torch");
            zArr[1] = "torch".equals(parametersF.getFlashMode());
            zArr[2] = supportedFlashModes.contains("red-eye");
            ArrayList arrayList4 = new ArrayList(0);
            if (supportedFlashModes.contains("off")) {
                arrayList4.add(1);
            }
            if (supportedFlashModes.contains("auto")) {
                arrayList4.add(2);
            }
            if (supportedFlashModes.contains("on")) {
                arrayList4.add(3);
            }
            iArr2 = (int[]) VideoCapture.c(arrayList4).clone();
        } else {
            iArr2 = null;
        }
        N.MumkJZ3e(this.e, this, j, new PhotoCapabilities(zArr, dArr, iArr3, iArr2, iArr, iArr5));
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.n.lock();
        try {
            if (!this.o) {
                if (camera != null) {
                    return;
                } else {
                    return;
                }
            }
            if (bArr.length == this.f) {
                N.Mq5gIPzl(this.e, this, bArr, this.f, a());
            } else {
                N.M651cEC1(this.e, this, 8);
            }
            this.n.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        } finally {
            this.n.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void setPhotoOptions(double d, int i, double d2, int i2, double d3, double d4, double[] dArr, boolean z, double d5, double d6, int i3, double d7, boolean z2, boolean z3, int i4, boolean z4, boolean z5, double d8) {
        int iAbs;
        Camera.Parameters parametersF = f(this.m);
        if (parametersF == null) {
            return;
        }
        if (parametersF.isZoomSupported() && d > 0.0d) {
            List<Integer> zoomRatios = parametersF.getZoomRatios();
            int i5 = 1;
            while (i5 < zoomRatios.size() && d >= zoomRatios.get(i5).intValue()) {
                i5++;
            }
            parametersF.setZoom(i5 - 1);
        }
        String str = "auto";
        if (i == 2) {
            parametersF.setFocusMode("fixed");
        } else if (i == 3) {
            parametersF.setFocusMode("auto");
        } else if (i == 4) {
            parametersF.setFocusMode("continuous-picture");
        }
        if (parametersF.isAutoExposureLockSupported()) {
            if (i2 == 2) {
                parametersF.setAutoExposureLock(true);
            } else if (i2 != 1) {
                parametersF.setAutoExposureLock(false);
            }
        }
        if (d3 > 0.0d) {
            this.i = (int) Math.round(d3);
        }
        if (d4 > 0.0d) {
            this.j = (int) Math.round(d4);
        }
        Camera.Area area = this.k;
        if (area != null && !area.rect.isEmpty() && d > 0.0d) {
            this.k = null;
        }
        if (i == 1 || i2 == 1) {
            this.k = null;
        }
        if ((parametersF.getMaxNumMeteringAreas() > 0 || parametersF.getMaxNumFocusAreas() > 0) && dArr.length > 0) {
            int iRound = (int) (Math.round(dArr[0] * 2000.0d) - 1000);
            int iRound2 = (int) (Math.round(dArr[1] * 2000.0d) - 1000);
            Camera.Area area2 = new Camera.Area(new Rect(Math.max(-1000, iRound - 125), Math.max(-1000, iRound2 - 125), Math.min(1000, iRound + 125), Math.min(1000, iRound2 + 125)), 1000);
            this.k = area2;
            area2.rect.toString();
        }
        Camera.Area area3 = this.k;
        if (area3 != null) {
            parametersF.setFocusAreas(Arrays.asList(area3));
            parametersF.setMeteringAreas(Arrays.asList(this.k));
        }
        if (z) {
            parametersF.setExposureCompensation((int) Math.round(d5 / ((double) parametersF.getExposureCompensationStep())));
        }
        if (i3 == 4 && parametersF.getSupportedWhiteBalance() != null) {
            parametersF.setWhiteBalance("auto");
        } else if (i3 == 2 && parametersF.isAutoWhiteBalanceLockSupported()) {
            parametersF.setAutoWhiteBalanceLock(true);
        }
        if (d8 > 0.0d) {
            int i6 = (int) d8;
            List<String> supportedWhiteBalance = parametersF.getSupportedWhiteBalance();
            int i7 = Integer.MAX_VALUE;
            String str2 = null;
            for (int i8 = 0; i8 < r.size(); i8++) {
                if (supportedWhiteBalance.contains(r.valueAt(i8)) && (iAbs = Math.abs(i6 - r.keyAt(i8))) < i7) {
                    str2 = (String) r.valueAt(i8);
                    i7 = iAbs;
                }
            }
            if (str2 != null) {
                parametersF.setWhiteBalance(str2);
            }
        }
        if (parametersF.getSupportedFlashModes() != null) {
            if (z4 && z5) {
                parametersF.setFlashMode("torch");
            } else if (i4 != 0) {
                if (i4 == 1) {
                    parametersF.setFlashMode("off");
                } else if (i4 == 2) {
                    if (z2 && z3) {
                        str = "red-eye";
                    }
                    parametersF.setFlashMode(str);
                } else if (i4 == 3) {
                    parametersF.setFlashMode("on");
                }
            }
        }
        try {
            this.m.setParameters(parametersF);
            if (i != 3) {
                return;
            }
            this.m.autoFocus(new LH(this));
        } catch (RuntimeException e) {
            Ts.a("VideoCapture", "setParameters: ", e);
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCaptureMaybeAsync() {
        if (this.m == null) {
            Ts.a("VideoCapture", "startCaptureAsync: mCamera is null", new Object[0]);
            return false;
        }
        this.n.lock();
        try {
            if (this.o) {
                return true;
            }
            this.n.unlock();
            this.m.setPreviewCallbackWithBuffer(this);
            try {
                this.m.startPreview();
                this.n.lock();
                try {
                    N.MPaf3s5k(this.e, this);
                    this.o = true;
                    return true;
                } finally {
                }
            } catch (RuntimeException e) {
                Ts.a("VideoCapture", "startCaptureAsync: Camera.startPreview: " + e, new Object[0]);
                return false;
            }
        } finally {
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean stopCaptureAndBlockUntilStopped() {
        if (this.m == null) {
            Ts.a("VideoCapture", "stopCaptureAndBlockUntilStopped: mCamera is null", new Object[0]);
            return true;
        }
        this.n.lock();
        try {
            if (!this.o) {
                return true;
            }
            this.o = false;
            this.n.unlock();
            this.m.stopPreview();
            this.m.setPreviewCallbackWithBuffer(null);
            return true;
        } finally {
            this.n.unlock();
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void takePhotoAsync(long j) {
        if (this.m == null || !this.o) {
            Ts.a("VideoCapture", "takePhotoAsync: mCamera is null or is not running", new Object[0]);
            N.MdZBZ$ST(this.e, this, j, null);
            return;
        }
        synchronized (this.g) {
            if (this.h != 0) {
                N.MdZBZ$ST(this.e, this, j, null);
                return;
            }
            this.h = j;
            Camera.Parameters parametersF = f(this.m);
            this.l = parametersF;
            if (parametersF == null) {
                N.MdZBZ$ST(this.e, this, j, null);
                return;
            }
            Camera.Parameters parametersF2 = f(this.m);
            if (parametersF2 == null) {
                N.MdZBZ$ST(this.e, this, j, null);
                return;
            }
            parametersF2.setRotation(a());
            if (this.i > 0 || this.j > 0) {
                Camera.Size size = null;
                int i = Integer.MAX_VALUE;
                for (Camera.Size size2 : parametersF2.getSupportedPictureSizes()) {
                    int i2 = this.i;
                    int iAbs = i2 > 0 ? Math.abs(size2.width - i2) : 0;
                    int i3 = this.j;
                    int iAbs2 = iAbs + (i3 > 0 ? Math.abs(size2.height - i3) : 0);
                    if (iAbs2 < i) {
                        size = size2;
                        i = iAbs2;
                    }
                }
                if (i != Integer.MAX_VALUE) {
                    int i4 = size.width;
                    parametersF2.setPictureSize(size.width, size.height);
                }
            }
            try {
                parametersF2.flatten();
                this.m.setParameters(parametersF2);
                this.m.takePicture(null, null, null, new OH(this, null));
            } catch (RuntimeException e) {
                Ts.a("VideoCapture", "setParameters " + e, new Object[0]);
                N.MdZBZ$ST(this.e, this, j, null);
            }
        }
    }
}
