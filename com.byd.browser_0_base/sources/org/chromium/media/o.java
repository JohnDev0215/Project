package org.chromium.media;

import J.N;
import android.graphics.Rect;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SparseIntArray;
import defpackage.C0175b;
import defpackage.fa1;
import defpackage.xz;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.media.VideoCapture;

/* JADX INFO: compiled from: VideoCaptureCamera2.java */
/* JADX INFO: loaded from: classes3.dex */
public final class o extends VideoCapture {
    public static final String[] G = {"Pixel 3", "Pixel 3 XL"};
    public static final SparseIntArray H;
    public int A;
    public int B;
    public boolean C;
    public int D;
    public boolean E;
    public boolean F;
    public final Object f;
    public CameraDevice g;
    public CameraCaptureSession h;
    public CaptureRequest i;
    public CaptureRequest.Builder j;
    public ImageReader k;
    public final Handler l;
    public final ConditionVariable m;
    public Range<Integer> n;
    public int o;
    public final float p;
    public Rect q;
    public int r;
    public int s;
    public int t;
    public float u;
    public int v;
    public long w;
    public MeteringRectangle x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class a implements ImageReader.OnImageAvailableListener {
        public final long a;

        public a(long j) {
            this.a = j;
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            byte[] bArrArray;
            o oVar = o.this;
            TraceEvent.v0("VideoCaptureCamera2.java", "CrPhotoReaderListener.onImageAvailable");
            try {
                Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                try {
                    if (imageAcquireLatestImage == null) {
                        throw new IllegalStateException();
                    }
                    if (imageAcquireLatestImage.getFormat() != 256) {
                        fa1.b("VideoCapture", "Unexpected image format: %d", Integer.valueOf(imageAcquireLatestImage.getFormat()));
                        throw new IllegalStateException();
                    }
                    try {
                        try {
                            bArrArray = imageAcquireLatestImage.getPlanes()[0].getBuffer().array();
                        } catch (UnsupportedOperationException unused) {
                            ByteBuffer buffer = imageAcquireLatestImage.getPlanes()[0].getBuffer();
                            byte[] bArr = new byte[buffer.remaining()];
                            try {
                                buffer.get(bArr);
                            } catch (Throwable unused2) {
                            }
                            bArrArray = bArr;
                        }
                    } catch (Throwable unused3) {
                        bArrArray = null;
                    }
                    byte[] bArr2 = bArrArray;
                    o oVar2 = o.this;
                    N.MdZBZ$ST(oVar2.e, oVar2, this.a, bArr2);
                    imageAcquireLatestImage.close();
                    o.e(oVar, 73);
                } catch (Throwable th) {
                    if (imageAcquireLatestImage != null) {
                        try {
                            imageAcquireLatestImage.close();
                        } catch (Throwable unused4) {
                        }
                    }
                    throw th;
                }
            } catch (IllegalStateException unused5) {
                oVar.d(this.a);
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class b extends CameraCaptureSession.StateCallback {
        public final ImageReader a;
        public final CaptureRequest b;
        public final long c;

        public b(ImageReader imageReader, CaptureRequest captureRequest, long j) {
            this.a = imageReader;
            this.b = captureRequest;
            this.c = j;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onClosed(CameraCaptureSession cameraCaptureSession) {
            this.a.close();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Log.e("cr_VideoCapture", "failed configuring capture session");
            o.this.d(this.c);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
            long j = this.c;
            o oVar = o.this;
            TraceEvent.v0("VideoCaptureCamera2.java", "CrPhotoSessionListener.onConfigured");
            try {
                TraceEvent.v0("VideoCaptureCamera2.java", "Calling CameraCaptureSession.capture()");
                cameraCaptureSession.capture(this.b, null, null);
            } catch (CameraAccessException e) {
                Log.e("cr_VideoCapture", "capture() CameraAccessException", e);
                oVar.d(j);
            } catch (IllegalStateException e2) {
                Log.e("cr_VideoCapture", "capture() IllegalStateException", e2);
                oVar.d(j);
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class c implements ImageReader.OnImageAvailableListener {
        public c() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            try {
                Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                o oVar = o.this;
                try {
                    if (imageAcquireLatestImage == null) {
                        N.M651cEC1(oVar.e, oVar, 9);
                        if (imageAcquireLatestImage != null) {
                            imageAcquireLatestImage.close();
                            return;
                        }
                        return;
                    }
                    if (imageAcquireLatestImage.getFormat() != 35 || imageAcquireLatestImage.getPlanes().length != 3) {
                        N.MhmwjISE(oVar.e, oVar, 71, "Unexpected image format: " + imageAcquireLatestImage.getFormat() + " or #planes: " + imageAcquireLatestImage.getPlanes().length);
                        throw new IllegalStateException();
                    }
                    if (imageReader.getWidth() == imageAcquireLatestImage.getWidth() && imageReader.getHeight() == imageAcquireLatestImage.getHeight()) {
                        o oVar2 = o.this;
                        N.MlTacwJQ(oVar2.e, oVar2, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getBuffer(), imageAcquireLatestImage.getPlanes()[2].getBuffer(), imageAcquireLatestImage.getPlanes()[1].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getPixelStride(), imageAcquireLatestImage.getWidth(), imageAcquireLatestImage.getHeight(), oVar.a(), imageAcquireLatestImage.getTimestamp());
                        imageAcquireLatestImage.close();
                        return;
                    }
                    N.MhmwjISE(oVar.e, oVar, 72, "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + imageAcquireLatestImage.getWidth() + "x" + imageAcquireLatestImage.getHeight() + ")");
                    throw new IllegalStateException();
                } catch (Throwable th) {
                    if (imageAcquireLatestImage != null) {
                        try {
                            imageAcquireLatestImage.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            } catch (IllegalStateException e) {
                Log.e("cr_VideoCapture", "acquireLatestImage():", e);
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class d extends CameraCaptureSession.StateCallback {
        public final CaptureRequest a;

        /* JADX INFO: compiled from: VideoCaptureCamera2.java */
        public class a extends CameraCaptureSession.CaptureCallback {
            public a() {
            }

            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                Long l = (Long) totalCaptureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                if (l == null) {
                    return;
                }
                o.this.w = l.longValue();
            }
        }

        public d(CaptureRequest captureRequest) {
            this.a = captureRequest;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onClosed(CameraCaptureSession cameraCaptureSession) {
            o.this.h = null;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            o oVar = o.this;
            oVar.f(3);
            oVar.h = null;
            N.MhmwjISE(oVar.e, oVar, 70, "Camera session configuration error");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
            o oVar = o.this;
            oVar.h = cameraCaptureSession;
            try {
                cameraCaptureSession.setRepeatingRequest(this.a, new a(), null);
                oVar.f(2);
                N.MPaf3s5k(oVar.e, oVar);
            } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                Log.e("cr_VideoCapture", "setRepeatingRequest: ", e);
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class e extends CameraDevice.StateCallback {
        public e() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onClosed(CameraDevice cameraDevice) {
            o oVar = o.this;
            if (oVar.h != null) {
                oVar.h = null;
            }
            oVar.m.open();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onDisconnected(CameraDevice cameraDevice) {
            Log.e("cr_VideoCapture", "cameraDevice was closed unexpectedly");
            cameraDevice.close();
            o oVar = o.this;
            oVar.g = null;
            oVar.f(3);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onError(CameraDevice cameraDevice, int i) {
            Log.e("cr_VideoCapture", "cameraDevice encountered an error");
            cameraDevice.close();
            o oVar = o.this;
            oVar.g = null;
            oVar.f(3);
            N.MhmwjISE(oVar.e, oVar, 69, C0175b.j("Camera device error ", Integer.toString(i)));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public final void onOpened(CameraDevice cameraDevice) {
            Log.e("cr_VideoCapture", "CameraDevice.StateCallback onOpened");
            o oVar = o.this;
            oVar.g = cameraDevice;
            oVar.m.close();
            oVar.f(1);
            o.e(oVar, 114);
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class f implements Runnable {
        public final long e;

        public f(long j) {
            this.e = j;
        }

        /* JADX WARN: Removed duplicated region for block: B:110:0x026a  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 1255
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.o.f.run():void");
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class g {
        public final double a;
        public final int b;
        public final double c;
        public final int d;
        public final double e;
        public final double f;
        public final double[] g;
        public final boolean h;
        public final double i;
        public final double j;
        public final int k;
        public final double l;
        public final boolean m;
        public final boolean n;
        public final int o;
        public final boolean p;
        public final boolean q;
        public final double r;

        public g(double d, int i, double d2, int i2, double d3, double d4, double[] dArr, boolean z, double d5, double d6, int i3, double d7, boolean z2, boolean z3, int i4, boolean z4, boolean z5, double d8) {
            this.a = d;
            this.b = i;
            this.c = d2;
            this.d = i2;
            this.e = d3;
            this.f = d4;
            this.g = dArr;
            this.h = z;
            this.i = d5;
            this.j = d6;
            this.k = i3;
            this.l = d7;
            this.m = z2;
            this.n = z3;
            this.o = i4;
            this.p = z4;
            this.q = z5;
            this.r = d8;
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class h implements Runnable {
        public final g e;

        public h(g gVar) {
            this.e = gVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            o oVar = o.this;
            CameraCharacteristics cameraCharacteristicsI = o.i(oVar.d);
            if (cameraCharacteristicsI == null) {
                return;
            }
            Rect rect = (Rect) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            g gVar = this.e;
            double d = gVar.a;
            if (d != 0.0d) {
                float fMax = Math.max(1.0f, Math.min((float) d, oVar.p));
                float f = (fMax - 1.0f) / (fMax * 2.0f);
                float f2 = 1.0f - f;
                oVar.q = new Rect(Math.round(rect.width() * f), Math.round(rect.height() * f), Math.round(rect.width() * f2), Math.round(rect.height() * f2));
                oVar.q.toString();
            }
            int i = gVar.b;
            if (i != 0) {
                oVar.t = i;
            }
            double d2 = gVar.c;
            if (d2 != 0.0d) {
                oVar.u = (float) d2;
            }
            int i2 = gVar.d;
            if (i2 != 0) {
                oVar.v = i2;
            }
            double d3 = gVar.j;
            if (d3 != 0.0d) {
                oVar.w = (long) (d3 * 100000.0d);
            }
            int i3 = gVar.k;
            if (i3 != 0) {
                oVar.z = i3;
            }
            double d4 = gVar.e;
            if (d4 > 0.0d) {
                oVar.r = (int) Math.round(d4);
            }
            double d5 = gVar.f;
            if (d5 > 0.0d) {
                oVar.s = (int) Math.round(d5);
            }
            MeteringRectangle meteringRectangle = oVar.x;
            if (meteringRectangle != null && !meteringRectangle.getRect().isEmpty() && gVar.a > 0.0d) {
                oVar.x = null;
            }
            if (oVar.t == 1 || oVar.v == 1) {
                oVar.x = null;
            }
            if ((((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0 || ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0 || ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB)).intValue() > 0) && gVar.g.length > 0) {
                Rect rect2 = oVar.q.isEmpty() ? rect : oVar.q;
                int iRound = (int) Math.round(gVar.g[0] * ((double) rect2.width()));
                int iRound2 = (int) Math.round(gVar.g[1] * ((double) rect2.height()));
                if (rect2.equals(oVar.q)) {
                    iRound += (rect.width() - rect2.width()) / 2;
                    iRound2 += (rect.height() - rect2.height()) / 2;
                }
                int iWidth = rect2.width() / 8;
                int iHeight = rect2.height() / 8;
                oVar.x = new MeteringRectangle(Math.max(0, iRound - (iWidth / 2)), Math.max(0, iRound2 - (iHeight / 2)), iWidth, iHeight, 1000);
                double d6 = gVar.g[0];
                double d7 = gVar.g[1];
                rect2.toString();
                rect.toString();
                oVar.x.toString();
            }
            if (gVar.h) {
                oVar.y = (int) Math.round(gVar.i / ((double) ((Rational) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP)).floatValue()));
            }
            double d8 = gVar.l;
            if (d8 > 0.0d) {
                oVar.B = (int) Math.round(d8);
            }
            double d9 = gVar.r;
            if (d9 > 0.0d) {
                oVar.A = (int) Math.round(d9);
            }
            if (gVar.m) {
                oVar.C = gVar.n;
            }
            int i4 = gVar.o;
            if (i4 != 0) {
                oVar.D = i4;
            }
            if (gVar.p) {
                oVar.E = gVar.q;
            }
            if (oVar.h != null) {
                oVar.g(oVar.j);
                if (gVar.o != 0) {
                    oVar.j.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                }
                oVar.i = oVar.j.build();
                try {
                    oVar.h.setRepeatingRequest(oVar.i, null, null);
                } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                    Log.e("cr_VideoCapture", "setRepeatingRequest: ", e);
                }
            }
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            o oVar = o.this;
            CameraDevice cameraDevice = oVar.g;
            if (cameraDevice == null) {
                return;
            }
            cameraDevice.close();
            oVar.f(3);
            oVar.q = new Rect();
        }
    }

    /* JADX INFO: compiled from: VideoCaptureCamera2.java */
    public class j implements Runnable {
        public final long e;

        public j(long j) {
            this.e = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            TraceEvent.v0("VideoCaptureCamera2.java", "TakePhotoTask.run");
            o oVar = o.this;
            CameraDevice cameraDevice = oVar.g;
            long j = this.e;
            if (cameraDevice == null || oVar.o != 2) {
                Log.e("cr_VideoCapture", "TakePhoto failed because mCameraDevice == null || mCameraState != CameraState.STARTED");
                oVar.d(j);
                return;
            }
            CameraCharacteristics cameraCharacteristicsI = o.i(oVar.d);
            if (cameraCharacteristicsI == null) {
                Log.e("cr_VideoCapture", "cameraCharacteristics error");
                oVar.d(j);
                return;
            }
            Size sizeH = o.h(((StreamConfigurationMap) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(256), oVar.r, oVar.s);
            int i = oVar.r;
            int i2 = oVar.s;
            if (sizeH != null) {
                sizeH.getWidth();
                sizeH.getHeight();
            }
            TraceEvent.v0("VideoCaptureCamera2.java", "TakePhotoTask.run creating ImageReader");
            ImageReader imageReaderNewInstance = ImageReader.newInstance(sizeH != null ? sizeH.getWidth() : oVar.c.a, sizeH != null ? sizeH.getHeight() : oVar.c.b, 256, 1);
            imageReaderNewInstance.setOnImageAvailableListener(oVar.new a(j), oVar.l);
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(imageReaderNewInstance.getSurface());
            try {
                CaptureRequest.Builder builderCreateCaptureRequest = oVar.g.createCaptureRequest(2);
                if (builderCreateCaptureRequest == null) {
                    Log.e("cr_VideoCapture", "photoRequestBuilder error");
                    oVar.d(j);
                    return;
                }
                builderCreateCaptureRequest.addTarget(imageReaderNewInstance.getSurface());
                builderCreateCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(oVar.a()));
                TraceEvent.v0("VideoCaptureCamera2.java", "TakePhotoTask.run calling configureCommonCaptureSettings");
                oVar.g(builderCreateCaptureRequest);
                TraceEvent.v0("VideoCaptureCamera2.java", "TakePhotoTask.run calling photoRequestBuilder.build()");
                b bVar = o.this.new b(imageReaderNewInstance, builderCreateCaptureRequest.build(), this.e);
                try {
                    TraceEvent.v0("VideoCaptureCamera2.java", "TakePhotoTask.run calling mCameraDevice.createCaptureSession()");
                    oVar.g.createCaptureSession(arrayList, bVar, oVar.l);
                } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                    Log.e("cr_VideoCapture", "createCaptureSession: " + e);
                    oVar.d(j);
                }
            } catch (CameraAccessException e2) {
                Log.e("cr_VideoCapture", "createCaptureRequest() error ", e2);
                oVar.d(j);
            }
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        H = sparseIntArray;
        sparseIntArray.append(2850, 2);
        sparseIntArray.append(2950, 4);
        sparseIntArray.append(4250, 3);
        sparseIntArray.append(4600, 7);
        sparseIntArray.append(5000, 5);
        sparseIntArray.append(6000, 6);
        sparseIntArray.append(TestInstrumentManagerImplCallback.SET_INSTRUMENT_THEME, 8);
    }

    public o(int i2, long j2) {
        super(i2, j2);
        this.f = new Object();
        this.m = new ConditionVariable();
        this.o = 3;
        this.p = 1.0f;
        this.q = new Rect();
        this.t = 4;
        this.u = 1.0f;
        this.v = 4;
        this.z = 4;
        this.A = -1;
        this.D = 1;
        N.Mqw5545M(j2, this);
        HandlerThread handlerThread = new HandlerThread("VideoCaptureCamera2_CameraThread");
        handlerThread.start();
        this.l = new Handler(handlerThread.getLooper());
        CameraCharacteristics cameraCharacteristicsI = i(i2);
        if (cameraCharacteristicsI != null) {
            this.p = ((Float) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
        }
    }

    public static void e(o oVar, int i2) {
        CaptureRequest.Builder builderCreateCaptureRequest;
        boolean z = false;
        if (oVar.g != null) {
            TraceEvent traceEventH0 = TraceEvent.H0("VideoCaptureCamera2.createPreviewObjectsAndStartPreview", null);
            try {
                VideoCaptureFormat videoCaptureFormat = oVar.c;
                ImageReader imageReaderNewInstance = ImageReader.newInstance(videoCaptureFormat.a, videoCaptureFormat.b, videoCaptureFormat.d, 2);
                oVar.k = imageReaderNewInstance;
                imageReaderNewInstance.setOnImageAvailableListener(oVar.new c(), oVar.l);
                try {
                    builderCreateCaptureRequest = oVar.g.createCaptureRequest(1);
                    oVar.j = builderCreateCaptureRequest;
                } catch (CameraAccessException | IllegalArgumentException | SecurityException e2) {
                    Log.e("cr_VideoCapture", "createCaptureRequest: ", e2);
                    if (traceEventH0 != null) {
                    }
                }
                if (builderCreateCaptureRequest == null) {
                    Log.e("cr_VideoCapture", "mPreviewRequestBuilder error");
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                } else {
                    builderCreateCaptureRequest.addTarget(oVar.k.getSurface());
                    oVar.j.set(CaptureRequest.CONTROL_MODE, 1);
                    oVar.j.set(CaptureRequest.NOISE_REDUCTION_MODE, 1);
                    oVar.j.set(CaptureRequest.EDGE_MODE, 1);
                    CameraCharacteristics cameraCharacteristicsI = i(oVar.d);
                    if (cameraCharacteristicsI != null) {
                        int[] iArr = (int[]) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
                        int length = iArr.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            if (iArr[i3] == 1) {
                                oVar.j.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
                                break;
                            }
                            i3++;
                        }
                        oVar.g(oVar.j);
                        if (oVar.F) {
                            oVar.j.set(CaptureRequest.CONTROL_MODE, 2);
                            oVar.j.set(CaptureRequest.CONTROL_SCENE_MODE, 1);
                        }
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(oVar.k.getSurface());
                        oVar.i = oVar.j.build();
                        try {
                            oVar.g.createCaptureSession(arrayList, oVar.new d(oVar.i), null);
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                            }
                            z = true;
                        } catch (CameraAccessException | IllegalArgumentException | SecurityException e3) {
                            Log.e("cr_VideoCapture", "createCaptureSession: ", e3);
                            if (traceEventH0 != null) {
                                traceEventH0.close();
                            }
                        }
                    } else if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
        if (z) {
            return;
        }
        oVar.f(3);
        N.MhmwjISE(oVar.e, oVar, i2, "Error starting or restarting preview");
    }

    public static Size h(Size[] sizeArr, int i2, int i3) {
        if (sizeArr == null) {
            return null;
        }
        Size size = null;
        int i4 = Integer.MAX_VALUE;
        for (Size size2 : sizeArr) {
            int iAbs = (i2 > 0 ? Math.abs(size2.getWidth() - i2) : 0) + (i3 > 0 ? Math.abs(size2.getHeight() - i3) : 0);
            if (iAbs < i4) {
                size = size2;
                i4 = iAbs;
            }
        }
        if (i4 != Integer.MAX_VALUE) {
            return size;
        }
        fa1.c("VideoCapture", "Couldn't find resolution close to (%dx%d)", Integer.valueOf(i2), Integer.valueOf(i3));
        return null;
    }

    public static CameraCharacteristics i(int i2) {
        try {
            return ((CameraManager) xz.a.getSystemService("camera")).getCameraCharacteristics(String.valueOf(i2));
        } catch (CameraAccessException | AssertionError | IllegalArgumentException | NullPointerException e2) {
            Log.e("cr_VideoCapture", "getCameraCharacteristics: ", e2);
            return null;
        }
    }

    public static String j(int i2) {
        try {
            String[] cameraIdList = ((CameraManager) xz.a.getSystemService("camera")).getCameraIdList();
            if (i2 < cameraIdList.length) {
                return cameraIdList[i2];
            }
            fa1.b("VideoCapture", "Invalid camera index: ", Integer.valueOf(i2));
            return null;
        } catch (CameraAccessException e2) {
            Log.e("cr_VideoCapture", "manager.getCameraIdList: ", e2);
            return null;
        }
    }

    public static int k(int i2) {
        try {
            return Integer.parseInt(j(i2));
        } catch (NumberFormatException unused) {
            fa1.b("VideoCapture", "Invalid camera index: ", Integer.valueOf(i2));
            return -1;
        }
    }

    public static int l(int i2) {
        try {
            String[] cameraIdList = ((CameraManager) xz.a.getSystemService("camera")).getCameraIdList();
            for (int i3 = 0; i3 < cameraIdList.length; i3++) {
                if (Integer.parseInt(cameraIdList[i3]) == i2) {
                    return i3;
                }
            }
            return -1;
        } catch (CameraAccessException e2) {
            Log.e("cr_VideoCapture", "manager.getCameraIdList: ", e2);
            return -1;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean allocate(int i2, int i3, int i4, boolean z) {
        N.Mqw5545M(this.e, this);
        synchronized (this.f) {
            int i5 = this.o;
            if (i5 != 0 && i5 != 1) {
                CameraCharacteristics cameraCharacteristicsI = i(this.d);
                if (cameraCharacteristicsI == null) {
                    return false;
                }
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                this.a = iIntValue;
                if (iIntValue == 0 || iIntValue == 180) {
                    i3 = i2;
                    i2 = i3;
                }
                Size sizeH = h(streamConfigurationMap.getOutputSizes(35), i2, i3);
                if (sizeH == null) {
                    Log.e("cr_VideoCapture", "No supported resolutions.");
                    return false;
                }
                List<Range> listAsList = Arrays.asList((Range[]) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
                if (listAsList.isEmpty()) {
                    Log.e("cr_VideoCapture", "No supported framerate ranges.");
                    return false;
                }
                ArrayList arrayList = new ArrayList(listAsList.size());
                int i6 = ((Integer) ((Range) listAsList.get(0)).getUpper()).intValue() > 1000 ? 1 : 1000;
                for (Range range : listAsList) {
                    arrayList.add(new VideoCapture.a(((Integer) range.getLower()).intValue() * i6, ((Integer) range.getUpper()).intValue() * i6));
                }
                VideoCapture.a aVar = (VideoCapture.a) Collections.min(arrayList, new m(i4 * 1000));
                this.n = new Range<>(Integer.valueOf(aVar.a / i6), Integer.valueOf(aVar.b / i6));
                sizeH.getWidth();
                sizeH.getHeight();
                this.n.getLower();
                this.n.getUpper();
                this.c = new VideoCaptureFormat(sizeH.getWidth(), sizeH.getHeight(), i4, 35);
                this.b = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
                this.F = z;
                return true;
            }
            Log.e("cr_VideoCapture", "allocate() invoked while Camera is busy opening/configuring.");
            return false;
        }
    }

    public final void f(int i2) {
        synchronized (this.f) {
            this.o = i2;
            this.f.notifyAll();
        }
    }

    public final void finalize() {
        this.l.getLooper().quit();
    }

    public final void g(CaptureRequest.Builder builder) {
        int iValueAt;
        int iAbs;
        boolean z;
        TraceEvent traceEventH0 = TraceEvent.H0("VideoCaptureCamera2.configureCommonCaptureSettings", null);
        try {
            CameraCharacteristics cameraCharacteristicsI = i(this.d);
            int i2 = this.t;
            if (i2 == 4) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            } else if (i2 == 2) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, 0);
                builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                builder.set(CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(1.0f / this.u));
            }
            int i3 = this.v;
            if (i3 == 1 || i3 == 2) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 0);
                long j2 = this.w;
                if (j2 != 0) {
                    builder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf(j2));
                } else if (cameraCharacteristicsI != null) {
                    Range range = (Range) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                    builder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, Long.valueOf(((Long) range.getLower()).longValue() + ((((Long) range.getUpper()).longValue() + ((Long) range.getLower()).longValue()) / 2)));
                }
            } else {
                builder.set(CaptureRequest.CONTROL_MODE, 1);
                builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                String[] strArr = G;
                int i4 = 0;
                while (true) {
                    if (i4 >= 2) {
                        z = false;
                        break;
                    } else {
                        if (strArr[i4].contentEquals(Build.MODEL)) {
                            z = true;
                            break;
                        }
                        i4++;
                    }
                }
                if (!z) {
                    builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.n);
                }
            }
            if (this.E) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.v == 4 ? 1 : 0));
                builder.set(CaptureRequest.FLASH_MODE, 2);
            } else {
                int i5 = this.D;
                if (i5 == 1) {
                    builder.set(CaptureRequest.FLASH_MODE, 0);
                } else if (i5 == 2) {
                    builder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.C ? 4 : 2));
                } else if (i5 == 3) {
                    builder.set(CaptureRequest.CONTROL_AE_MODE, 3);
                    builder.set(CaptureRequest.FLASH_MODE, 1);
                }
                builder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
            }
            builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.y));
            int i6 = this.z;
            if (i6 == 4) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.FALSE);
                builder.set(CaptureRequest.CONTROL_AWB_MODE, 1);
            } else if (i6 == 1) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.FALSE);
                builder.set(CaptureRequest.CONTROL_AWB_MODE, 0);
            } else if (i6 == 2) {
                builder.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.TRUE);
            }
            int i7 = this.A;
            if (i7 > 0) {
                if (cameraCharacteristicsI != null) {
                    int[] iArr = (int[]) cameraCharacteristicsI.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
                    int i8 = Integer.MAX_VALUE;
                    iValueAt = -1;
                    int i9 = 0;
                    while (true) {
                        SparseIntArray sparseIntArray = H;
                        if (i9 >= sparseIntArray.size()) {
                            break;
                        }
                        int iValueAt2 = sparseIntArray.valueAt(i9);
                        int i10 = 0;
                        while (true) {
                            if (i10 >= iArr.length) {
                                i10 = -1;
                                break;
                            } else if (iValueAt2 == iArr[i10]) {
                                break;
                            } else {
                                i10++;
                            }
                        }
                        if (i10 != -1 && (iAbs = Math.abs(i7 - sparseIntArray.keyAt(i9))) < i8) {
                            iValueAt = sparseIntArray.valueAt(i9);
                            i8 = iAbs;
                        }
                        i9++;
                    }
                } else {
                    iValueAt = -1;
                }
                if (iValueAt != -1) {
                    builder.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(iValueAt));
                }
            }
            MeteringRectangle meteringRectangle = this.x;
            if (meteringRectangle != null) {
                MeteringRectangle[] meteringRectangleArr = {meteringRectangle};
                meteringRectangle.toString();
                builder.set(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr);
                builder.set(CaptureRequest.CONTROL_AE_REGIONS, meteringRectangleArr);
                builder.set(CaptureRequest.CONTROL_AWB_REGIONS, meteringRectangleArr);
            }
            if (!this.q.isEmpty()) {
                builder.set(CaptureRequest.SCALER_CROP_REGION, this.q);
            }
            int i11 = this.B;
            if (i11 > 0) {
                builder.set(CaptureRequest.SENSOR_SENSITIVITY, Integer.valueOf(i11));
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final void getPhotoCapabilitiesAsync(long j2) {
        N.Mqw5545M(this.e, this);
        this.l.post(new f(j2));
    }

    @Override // org.chromium.media.VideoCapture
    public final void setPhotoOptions(double d2, int i2, double d3, int i3, double d4, double d5, double[] dArr, boolean z, double d6, double d7, int i4, double d8, boolean z2, boolean z3, int i5, boolean z4, boolean z5, double d9) {
        N.Mqw5545M(this.e, this);
        this.l.post(new h(new g(d2, i2, d3, i3, d4, d5, dArr, z, d6, d7, i4, d8, z2, z3, i5, z4, z5, d9)));
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean startCaptureMaybeAsync() {
        int i2 = this.d;
        N.Mqw5545M(this.e, this);
        f(0);
        CameraManager cameraManager = (CameraManager) xz.a.getSystemService("camera");
        e eVar = new e();
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            int iL = l(i2);
            if (iL < 0) {
                fa1.b("VideoCapture", "Invalid camera Id: ", Integer.valueOf(i2));
                return false;
            }
            TraceEvent.v0("VideoCaptureCamera2.java", "VideoCaptureCamera2.startCaptureMaybeAsync calling manager.openCamera");
            cameraManager.openCamera(cameraIdList[iL], eVar, this.l);
            return true;
        } catch (CameraAccessException | IllegalArgumentException | SecurityException e2) {
            Log.e("cr_VideoCapture", "allocate: manager.openCamera: ", e2);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final boolean stopCaptureAndBlockUntilStopped() {
        int i2;
        N.Mqw5545M(this.e, this);
        TraceEvent traceEventH0 = TraceEvent.H0("VideoCaptureCamera2.stopCaptureAndBlockUntilStopped", null);
        try {
            synchronized (this.f) {
                while (true) {
                    i2 = this.o;
                    if (i2 == 2 || i2 == 3) {
                        break;
                    }
                    try {
                        this.f.wait();
                    } catch (InterruptedException e2) {
                        Log.e("cr_VideoCapture", "CaptureStartedEvent: ", e2);
                    }
                }
                if (i2 == 3) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return true;
                }
                this.l.post(new i());
                this.m.block();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return true;
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public final void takePhotoAsync(long j2) {
        N.Mqw5545M(this.e, this);
        TraceEvent.v0("VideoCaptureCamera2.java", "takePhotoAsync");
        this.l.post(new j(j2));
    }

    @Override // org.chromium.media.VideoCapture
    public final void deallocate() {
    }
}
