package org.chromium.media;

import J.N;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.opengl.GLES20;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import defpackage.fa1;
import defpackage.gq2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.media.VideoCapture;

/* JADX INFO: compiled from: VideoCaptureCamera.java */
/* JADX INFO: loaded from: classes3.dex */
public final class n extends VideoCapture implements Camera.PreviewCallback {
    public static final SparseArray<String> r;
    public int f;
    public final Object g;
    public long h;
    public int i;
    public int j;
    public Camera.Area k;
    public Camera.Parameters l;
    public Camera m;
    public final ReentrantLock n;
    public boolean o;
    public int[] p;
    public SurfaceTexture q;

    /* JADX INFO: compiled from: VideoCaptureCamera.java */
    public static class b {
        public static final String[] a = {"SAMSUNG-SGH-I747", "ODROID-U2", "XT1092", "XT1095", "XT1096", "XT1097"};
    }

    /* JADX INFO: compiled from: VideoCaptureCamera.java */
    public class c implements Camera.ErrorCallback {
        public c() {
        }

        @Override // android.hardware.Camera.ErrorCallback
        public final void onError(int i, Camera camera) {
            n nVar = n.this;
            N.MhmwjISE(nVar.e, nVar, 68, gq2.e("Error id: ", i));
            synchronized (n.this.g) {
                n nVar2 = n.this;
                long j = nVar2.h;
                if (j == 0) {
                    return;
                }
                nVar2.d(j);
                n.this.h = 0L;
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera.java */
    public class d implements Camera.PictureCallback {
        public d() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public final void onPictureTaken(byte[] bArr, Camera camera) {
            try {
                n.this.l.flatten();
                camera.setParameters(n.this.l);
            } catch (RuntimeException e) {
                Log.e("cr_VideoCapture", "onPictureTaken, setParameters() " + e);
            }
            try {
                camera.startPreview();
            } catch (RuntimeException e2) {
                Log.e("cr_VideoCapture", "onPictureTaken, startPreview() " + e2);
            }
            synchronized (n.this.g) {
                n nVar = n.this;
                long j = nVar.h;
                if (j != 0) {
                    N.MdZBZ$ST(nVar.e, nVar, j, bArr);
                }
                n.this.h = 0L;
            }
        }
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        r = sparseArray;
        sparseArray.append(2850, "incandescent");
        sparseArray.append(2950, "warm-fluorescent");
        sparseArray.append(4250, "fluorescent");
        sparseArray.append(4600, "twilight");
        sparseArray.append(TsNormalNum.THOUSAND_FIVE_HALF, "daylight");
        sparseArray.append(6000, "cloudy-daylight");
        sparseArray.append(TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME, "shade");
    }

    public n(int i, long j) {
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
            Log.e("cr_VideoCapture", "getCameraInfo: Camera.getCameraInfo: " + e);
            return null;
        }
    }

    public static Camera.Parameters f(Camera camera) {
        try {
            return camera.getParameters();
        } catch (RuntimeException e) {
            Log.e("cr_VideoCapture", "getCameraParameters: android.hardware.Camera.getParameters: " + e);
            if (camera == null) {
                return null;
            }
            camera.release();
            return null;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean allocate(int i, int i2, int i3, boolean z) {
        int i4;
        int i5 = this.d;
        try {
            this.m = Camera.open(i5);
            Camera.CameraInfo cameraInfoE = e(i5);
            if (cameraInfoE == null) {
                this.m.release();
                this.m = null;
                return false;
            }
            this.a = cameraInfoE.orientation;
            this.b = cameraInfoE.facing == 0;
            VideoCapture.b();
            Camera.Parameters parametersF = f(this.m);
            if (parametersF == null) {
                this.m = null;
                return false;
            }
            List<int[]> supportedPreviewFpsRange = parametersF.getSupportedPreviewFpsRange();
            if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() == 0) {
                Log.e("cr_VideoCapture", "allocate: no fps range found");
                return false;
            }
            ArrayList arrayList = new ArrayList(supportedPreviewFpsRange.size());
            for (int[] iArr : supportedPreviewFpsRange) {
                arrayList.add(new VideoCapture.a(iArr[0], iArr[1]));
            }
            VideoCapture.a aVar = (VideoCapture.a) Collections.min(arrayList, new m(i3 * 1000));
            int[] iArr2 = {aVar.a, aVar.b};
            int i6 = i;
            int i7 = i2;
            int i8 = Integer.MAX_VALUE;
            for (Camera.Size size : parametersF.getSupportedPreviewSizes()) {
                int iAbs = Math.abs(size.height - i2) + Math.abs(size.width - i);
                if (iAbs < i8) {
                    int i9 = size.width;
                    if (i9 % 32 == 0) {
                        i7 = size.height;
                        i6 = i9;
                        i8 = iAbs;
                    }
                }
            }
            if (i8 == Integer.MAX_VALUE) {
                Log.e("cr_VideoCapture", "allocate: can not find a multiple-of-32 resolution");
                return false;
            }
            if (parametersF.isVideoStabilizationSupported()) {
                parametersF.getVideoStabilization();
                parametersF.setVideoStabilization(true);
            }
            if (parametersF.getSupportedFocusModes().contains("continuous-video")) {
                parametersF.setFocusMode("continuous-video");
            }
            int i10 = iArr2[1] / 1000;
            String[] strArr = b.a;
            int i11 = 0;
            while (true) {
                if (i11 >= 6) {
                    i4 = 842094169;
                    break;
                }
                if (strArr[i11].contentEquals(Build.MODEL)) {
                    i4 = 17;
                    break;
                }
                i11++;
            }
            this.c = new VideoCaptureFormat(i6, i7, i10, i4);
            parametersF.setPictureSize(i6, i7);
            parametersF.setPreviewSize(i6, i7);
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
                    this.m.setErrorCallback(new c());
                    VideoCaptureFormat videoCaptureFormat = this.c;
                    this.f = (ImageFormat.getBitsPerPixel(videoCaptureFormat.d) * (videoCaptureFormat.a * videoCaptureFormat.b)) / 8;
                    for (int i12 = 0; i12 < 3; i12++) {
                        this.m.addCallbackBuffer(new byte[this.f]);
                    }
                    return true;
                } catch (IOException e) {
                    Log.e("cr_VideoCapture", "allocate: " + e);
                    return false;
                }
            } catch (RuntimeException e2) {
                Log.e("cr_VideoCapture", "setParameters: " + e2);
                return false;
            }
        } catch (RuntimeException e3) {
            Log.e("cr_VideoCapture", "allocate: Camera.open: " + e3);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final void deallocate() {
        if (this.m == null) {
            return;
        }
        stopCaptureAndBlockUntilStopped();
        try {
            this.m.setPreviewTexture(null);
            int[] iArr = this.p;
            if (iArr != null) {
                GLES20.glDeleteTextures(1, iArr, 0);
            }
            this.c = null;
            this.m.release();
            this.m = null;
        } catch (IOException e) {
            Log.e("cr_VideoCapture", "deallocate: failed to deallocate camera, " + e);
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final void getPhotoCapabilitiesAsync(long j) {
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        int iIntValue4;
        int i;
        int[] iArr;
        int iIndexOfValue;
        Camera.Parameters parametersF = f(this.m);
        if (parametersF == null) {
            this.m = null;
            N.MumkJZ3e(this.e, this, j, null);
            return;
        }
        boolean[] zArr = new boolean[3];
        double[] dArr = new double[16];
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[3];
        int[][] iArr4 = new int[3][];
        fa1.g("VideoCapture", " CAM params: %s", parametersF.flatten());
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        for (Camera.Size size : parametersF.getSupportedPictureSizes()) {
            int i6 = size.width;
            if (i6 < i5) {
                i5 = i6;
            }
            int i7 = size.height;
            if (i7 < i2) {
                i2 = i7;
            }
            if (i6 > i3) {
                i3 = i6;
            }
            if (i7 > i4) {
                i4 = i7;
            }
        }
        Camera.Size previewSize = parametersF.getPreviewSize();
        iArr2[4] = i2;
        iArr2[5] = i4;
        iArr2[7] = 1;
        iArr2[6] = previewSize.height;
        iArr2[8] = i5;
        iArr2[9] = i3;
        iArr2[11] = 1;
        iArr2[10] = previewSize.width;
        if (parametersF.isZoomSupported()) {
            iIntValue = parametersF.getZoomRatios().get(parametersF.getMaxZoom()).intValue();
            iIntValue3 = parametersF.getZoomRatios().get(parametersF.getZoom()).intValue();
            iIntValue4 = parametersF.getZoomRatios().get(0).intValue();
            iIntValue2 = parametersF.getZoomRatios().size() > 1 ? parametersF.getZoomRatios().get(1).intValue() - parametersF.getZoomRatios().get(0).intValue() : 0;
        } else {
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
        iArr4[0] = (int[]) VideoCapture.c(arrayList).clone();
        String focusMode = parametersF.getFocusMode();
        iArr3[0] = (focusMode.equals("continuous-video") || focusMode.equals("continuous-picture") || focusMode.equals("edof")) ? 4 : (focusMode.equals("auto") || focusMode.equals("macro")) ? 3 : (focusMode.equals("infinity") || focusMode.equals("fixed")) ? 2 : 1;
        ArrayList arrayList2 = new ArrayList(2);
        arrayList2.add(4);
        if (parametersF.isAutoExposureLockSupported()) {
            arrayList2.add(2);
        }
        iArr4[1] = (int[]) VideoCapture.c(arrayList2).clone();
        iArr3[1] = (parametersF.isAutoExposureLockSupported() && parametersF.getAutoExposureLock()) ? 2 : 4;
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
        iArr4[2] = (int[]) VideoCapture.c(arrayList3).clone();
        if (parametersF.isAutoWhiteBalanceLockSupported() && parametersF.getAutoWhiteBalanceLock()) {
            i = 2;
        }
        iArr3[2] = i;
        SparseArray<String> sparseArray = r;
        iArr2[12] = sparseArray.keyAt(0);
        iArr2[13] = sparseArray.keyAt(sparseArray.size() - 1);
        iArr2[15] = 50;
        if (i == 2 && (iIndexOfValue = sparseArray.indexOfValue(parametersF.getWhiteBalance())) >= 0) {
            iArr2[14] = sparseArray.keyAt(iIndexOfValue);
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
            iArr = (int[]) VideoCapture.c(arrayList4).clone();
        } else {
            iArr = null;
        }
        N.MumkJZ3e(this.e, this, j, new PhotoCapabilities(zArr, dArr, iArr2, iArr, iArr3, iArr4));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #0 {all -> 0x0039, blocks: (B:3:0x0005, B:11:0x0016, B:13:0x001b, B:14:0x0027), top: B:26:0x0005 }] */
    @Override // android.hardware.Camera.PreviewCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onPreviewFrame(byte[] r9, android.hardware.Camera r10) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.n
            r0.lock()
            boolean r1 = r8.o     // Catch: java.lang.Throwable -> L39
            if (r1 != 0) goto L14
            r0.unlock()
            if (r10 == 0) goto L13
            if (r9 == 0) goto L13
            r10.addCallbackBuffer(r9)
        L13:
            return
        L14:
            if (r9 == 0) goto L27
            int r1 = r9.length     // Catch: java.lang.Throwable -> L39
            int r6 = r8.f     // Catch: java.lang.Throwable -> L39
            if (r1 != r6) goto L27
            long r2 = r8.e     // Catch: java.lang.Throwable -> L39
            int r7 = r8.a()     // Catch: java.lang.Throwable -> L39
            r4 = r8
            r5 = r9
            J.N.Mq5gIPzl(r2, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L39
            goto L2e
        L27:
            long r1 = r8.e     // Catch: java.lang.Throwable -> L39
            r3 = 8
            J.N.M651cEC1(r1, r8, r3)     // Catch: java.lang.Throwable -> L39
        L2e:
            r0.unlock()
            if (r10 == 0) goto L38
            if (r9 == 0) goto L38
            r10.addCallbackBuffer(r9)
        L38:
            return
        L39:
            r8 = move-exception
            r0.unlock()
            if (r10 == 0) goto L44
            if (r9 == 0) goto L44
            r10.addCallbackBuffer(r9)
        L44:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.n.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    @Override // org.chromium.media.VideoCapture
    public final void setPhotoOptions(double d2, int i, double d3, int i2, double d4, double d5, double[] dArr, boolean z, double d6, double d7, int i3, double d8, boolean z2, boolean z3, int i4, boolean z4, boolean z5, double d9) {
        Camera.Area area;
        String str;
        int iAbs;
        Camera.Parameters parametersF = f(this.m);
        if (parametersF == null) {
            this.m = null;
            return;
        }
        if (parametersF.isZoomSupported() && d2 > 0.0d) {
            List<Integer> zoomRatios = parametersF.getZoomRatios();
            int i5 = 1;
            while (i5 < zoomRatios.size()) {
                List<Integer> list = zoomRatios;
                if (d2 < zoomRatios.get(i5).intValue()) {
                    break;
                }
                i5++;
                zoomRatios = list;
            }
            parametersF.setZoom(i5 - 1);
        }
        if (i == 2) {
            parametersF.setFocusMode("fixed");
        } else if (i == 3) {
            parametersF.setFocusMode("auto");
        } else if (i == 4) {
            parametersF.setFocusMode("continuous-picture");
        }
        int i6 = 0;
        if (parametersF.isAutoExposureLockSupported()) {
            if (i2 == 2) {
                parametersF.setAutoExposureLock(true);
            } else if (i2 != 1) {
                parametersF.setAutoExposureLock(false);
            }
        }
        if (d4 > 0.0d) {
            this.i = (int) Math.round(d4);
        }
        if (d5 > 0.0d) {
            this.j = (int) Math.round(d5);
        }
        Camera.Area area2 = this.k;
        if (area2 == null || area2.rect.isEmpty() || d2 <= 0.0d) {
            area = null;
        } else {
            area = null;
            this.k = null;
        }
        if (i == 1 || i2 == 1) {
            this.k = area;
        }
        if ((parametersF.getMaxNumMeteringAreas() > 0 || parametersF.getMaxNumFocusAreas() > 0) && dArr.length > 0) {
            int iRound = (int) (Math.round(dArr[0] * 2000.0d) - 1000);
            int iRound2 = (int) (Math.round(dArr[1] * 2000.0d) - 1000);
            Camera.Area area3 = new Camera.Area(new Rect(Math.max(-1000, iRound - 125), Math.max(-1000, iRound2 - 125), Math.min(1000, iRound + 125), Math.min(1000, iRound2 + 125)), 1000);
            this.k = area3;
            area3.rect.toString();
        }
        Camera.Area area4 = this.k;
        if (area4 != null) {
            parametersF.setFocusAreas(Arrays.asList(area4));
            parametersF.setMeteringAreas(Arrays.asList(this.k));
        }
        if (z) {
            parametersF.setExposureCompensation((int) Math.round(d6 / ((double) parametersF.getExposureCompensationStep())));
        }
        if (i3 != 4 || parametersF.getSupportedWhiteBalance() == null) {
            str = "auto";
            if (i3 == 2 && parametersF.isAutoWhiteBalanceLockSupported()) {
                parametersF.setAutoWhiteBalanceLock(true);
            }
        } else {
            str = "auto";
            parametersF.setWhiteBalance(str);
        }
        if (d9 > 0.0d) {
            int i7 = (int) d9;
            List<String> supportedWhiteBalance = parametersF.getSupportedWhiteBalance();
            int i8 = Integer.MAX_VALUE;
            String strValueAt = null;
            while (true) {
                SparseArray<String> sparseArray = r;
                if (i6 >= sparseArray.size()) {
                    break;
                }
                if (supportedWhiteBalance.contains(sparseArray.valueAt(i6)) && (iAbs = Math.abs(i7 - sparseArray.keyAt(i6))) < i8) {
                    strValueAt = sparseArray.valueAt(i6);
                    i8 = iAbs;
                }
                i6++;
            }
            if (strValueAt != null) {
                parametersF.setWhiteBalance(strValueAt);
            }
        }
        if (parametersF.getSupportedFlashModes() != null) {
            if (z4 && z5) {
                parametersF.setFlashMode("torch");
            } else if (i4 != 0) {
                if (i4 == 1) {
                    parametersF.setFlashMode("off");
                } else if (i4 == 2) {
                    parametersF.setFlashMode((z2 && z3) ? "red-eye" : str);
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
            this.m.autoFocus(new a());
        } catch (RuntimeException e) {
            Log.e("cr_VideoCapture", "setParameters: ", e);
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean startCaptureMaybeAsync() {
        if (this.m == null) {
            Log.e("cr_VideoCapture", "startCaptureAsync: mCamera is null");
            return false;
        }
        ReentrantLock reentrantLock = this.n;
        reentrantLock.lock();
        try {
            if (this.o) {
                return true;
            }
            reentrantLock.unlock();
            this.m.setPreviewCallbackWithBuffer(this);
            try {
                this.m.startPreview();
                reentrantLock.lock();
                try {
                    N.MPaf3s5k(this.e, this);
                    this.o = true;
                    return true;
                } finally {
                }
            } catch (RuntimeException e) {
                Log.e("cr_VideoCapture", "startCaptureAsync: Camera.startPreview: " + e);
                return false;
            }
        } finally {
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean stopCaptureAndBlockUntilStopped() {
        if (this.m == null) {
            Log.e("cr_VideoCapture", "stopCaptureAndBlockUntilStopped: mCamera is null");
            return true;
        }
        ReentrantLock reentrantLock = this.n;
        reentrantLock.lock();
        try {
            if (!this.o) {
                return true;
            }
            this.o = false;
            reentrantLock.unlock();
            this.m.stopPreview();
            this.m.setPreviewCallbackWithBuffer(null);
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final void takePhotoAsync(long j) {
        if (this.m == null || !this.o) {
            Log.e("cr_VideoCapture", "takePhotoAsync: mCamera is null or is not running");
            d(j);
            return;
        }
        synchronized (this.g) {
            if (this.h != 0) {
                d(j);
                return;
            }
            this.h = j;
            Camera.Parameters parametersF = f(this.m);
            this.l = parametersF;
            if (parametersF == null) {
                this.m = null;
                d(j);
                return;
            }
            Camera.Parameters parametersF2 = f(this.m);
            if (parametersF2 == null) {
                this.m = null;
                d(j);
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
                    parametersF2.setPictureSize(size.width, size.height);
                }
            }
            try {
                parametersF2.flatten();
                this.m.setParameters(parametersF2);
                this.m.takePicture(null, null, null, new d());
            } catch (RuntimeException e) {
                Log.e("cr_VideoCapture", "setParameters " + e);
                d(j);
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera.java */
    public class a implements Camera.AutoFocusCallback {
        @Override // android.hardware.Camera.AutoFocusCallback
        public final void onAutoFocus(boolean z, Camera camera) {
        }
    }
}
