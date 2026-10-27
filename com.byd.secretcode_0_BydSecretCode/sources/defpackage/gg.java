package defpackage;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import com.byd.secretcode.camera.AutoFitTextureView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CameraController.java */
/* JADX INFO: loaded from: classes.dex */
public class gg {
    public static Activity a;
    public static final SparseIntArray b;
    public ImageReader c;
    public HandlerThread d;
    public Handler e;
    public AutoFitTextureView f;
    public Semaphore g;
    public String h;
    public CameraDevice i;
    public Size j;
    public CaptureRequest.Builder k;
    public CameraCaptureSession l;
    public CaptureRequest m;
    public File n;
    public Integer o;
    public boolean p;
    public int q;
    public MediaRecorder r;
    public TextureView.SurfaceTextureListener s;
    public final CameraDevice.StateCallback t;
    public CameraCaptureSession.CaptureCallback u;
    public ImageReader.OnImageAvailableListener v;

    /* JADX INFO: compiled from: CameraController.java */
    public class a implements TextureView.SurfaceTextureListener {
        public a() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            gg.this.B(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class b extends CameraDevice.StateCallback {
        public b() {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            gg.this.g.release();
            cameraDevice.close();
            gg.this.i = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            gg.this.g.release();
            cameraDevice.close();
            gg.this.i = null;
            gg.a.finish();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            gg.this.g.release();
            gg.this.i = cameraDevice;
            gg.this.H();
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class c extends CameraCaptureSession.StateCallback {
        public c() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            if (gg.this.i == null) {
                return;
            }
            gg.this.l = cameraCaptureSession;
            gg.this.k.set(CaptureRequest.CONTROL_AF_MODE, 4);
            gg ggVar = gg.this;
            ggVar.m = ggVar.k.build();
            try {
                gg.this.l.setRepeatingRequest(gg.this.m, gg.this.u, gg.this.e);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class d extends CameraCaptureSession.CaptureCallback {
        public d() {
        }

        public final void a(CaptureResult captureResult) {
            int i = gg.this.q;
            if (i == 1) {
                Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num == null) {
                    gg.this.u();
                    return;
                }
                if (4 == num.intValue() || 5 == num.intValue()) {
                    Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    if (num2 != null && num2.intValue() != 2) {
                        gg.this.C();
                        return;
                    } else {
                        gg.this.q = 4;
                        gg.this.u();
                        return;
                    }
                }
                return;
            }
            if (i == 2) {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num3 == null || num3.intValue() == 5 || num3.intValue() == 4) {
                    gg.this.q = 3;
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
            Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
            if (num4 == null || num4.intValue() != 5) {
                gg.this.q = 4;
                gg.this.u();
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            a(totalCaptureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            a(captureResult);
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class e extends CameraCaptureSession.CaptureCallback {
        public e() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            gg.this.F("图片地址: " + gg.this.n);
            Log.d("CameraController", gg.this.n.toString());
            gg.this.I();
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class f implements Runnable {
        public final /* synthetic */ String b;

        public f(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            mg.b(gg.a, this.b);
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public class g implements ImageReader.OnImageAvailableListener {
        public g() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            gg.this.e.post(new j(imageReader.acquireNextImage(), gg.this.n));
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public static class h {
        public static gg a = new gg(null);
    }

    /* JADX INFO: compiled from: CameraController.java */
    public static class i implements Comparator<Size> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Size size, Size size2) {
            return Long.signum((((long) size.getWidth()) * ((long) size.getHeight())) - (((long) size2.getWidth()) * ((long) size2.getHeight())));
        }
    }

    /* JADX INFO: compiled from: CameraController.java */
    public static class j implements Runnable {
        public final Image b;
        public final File c;

        public j(Image image, File file) {
            this.b = image;
            this.c = file;
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                r4 = this;
                android.media.Image r0 = r4.b
                android.media.Image$Plane[] r0 = r0.getPlanes()
                r1 = 0
                r0 = r0[r1]
                java.nio.ByteBuffer r0 = r0.getBuffer()
                int r1 = r0.remaining()
                byte[] r1 = new byte[r1]
                r0.get(r1)
                r0 = 0
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
                java.io.File r3 = r4.c     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L30
                r2.write(r1)     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L46
                android.media.Image r0 = r4.b
                r0.close()
                r2.close()     // Catch: java.io.IOException -> L41
                goto L45
            L2a:
                r0 = move-exception
                goto L33
            L2c:
                r1 = move-exception
                r2 = r0
                r0 = r1
                goto L47
            L30:
                r1 = move-exception
                r2 = r0
                r0 = r1
            L33:
                r0.printStackTrace()     // Catch: java.lang.Throwable -> L46
                android.media.Image r0 = r4.b
                r0.close()
                if (r2 == 0) goto L45
                r2.close()     // Catch: java.io.IOException -> L41
                goto L45
            L41:
                r0 = move-exception
                r0.printStackTrace()
            L45:
                return
            L46:
                r0 = move-exception
            L47:
                android.media.Image r1 = r4.b
                r1.close()
                if (r2 == 0) goto L56
                r2.close()     // Catch: java.io.IOException -> L52
                goto L56
            L52:
                r1 = move-exception
                r1.printStackTrace()
            L56:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: gg.j.run():void");
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
        sparseIntArray.append(0, 90);
        sparseIntArray.append(1, 0);
        sparseIntArray.append(2, 270);
        sparseIntArray.append(3, 180);
    }

    public /* synthetic */ gg(fg fgVar) {
        this();
    }

    public static Size v(Size[] sizeArr, int i2, int i3, int i4, int i5, Size size) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int width = size.getWidth();
        int height = size.getHeight();
        for (Size size2 : sizeArr) {
            if (size2.getWidth() <= i4 && size2.getHeight() <= i5 && size2.getHeight() == (size2.getWidth() * height) / width) {
                if (size2.getWidth() < i2 || size2.getHeight() < i3) {
                    arrayList2.add(size2);
                } else {
                    arrayList.add(size2);
                }
            }
        }
        if (arrayList.size() > 0) {
            return (Size) Collections.min(arrayList, new i());
        }
        if (arrayList2.size() > 0) {
            return (Size) Collections.max(arrayList2, new i());
        }
        Log.d("CameraController", "Couldn't find any suitable preview size");
        return sizeArr[0];
    }

    public static gg y(Activity activity) {
        a = activity;
        return h.a;
    }

    public void A(AutoFitTextureView autoFitTextureView) {
        this.f = autoFitTextureView;
        G();
        if (this.f.isAvailable()) {
            B(this.f.getWidth(), this.f.getHeight());
        } else {
            this.f.setSurfaceTextureListener(this.s);
        }
    }

    public final void B(int i2, int i3) {
        String str;
        E(i2, i3);
        x(i2, i3, a.getWindowManager().getDefaultDisplay().getRotation());
        CameraManager cameraManager = (CameraManager) a.getSystemService("camera");
        try {
            if (!this.g.tryAcquire(2500L, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Time out waiting to lock camera opening.");
            }
            if (e7.a(a, "android.permission.CAMERA") != 0 || (str = this.h) == null || str.isEmpty()) {
                return;
            }
            cameraManager.openCamera(this.h, this.t, this.e);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException unused) {
            BydLogHelper.logD("CameraController", "openCamera No cameras available on device");
        } catch (InterruptedException e3) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.", e3);
        }
    }

    public final void C() {
        try {
            this.k.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
            this.q = 2;
            this.l.capture(this.k.build(), this.u, this.e);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
        }
    }

    public final void D(CaptureRequest.Builder builder) {
        if (this.p) {
            builder.set(CaptureRequest.CONTROL_AE_MODE, 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gg.E(int, int):void");
    }

    public final void F(String str) {
        a.runOnUiThread(new f(str));
    }

    public final void G() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.d = handlerThread;
        handlerThread.start();
        this.e = new Handler(this.d.getLooper());
    }

    public final void H() {
        try {
            SurfaceTexture surfaceTexture = this.f.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(this.j.getWidth(), this.j.getHeight());
            Surface surface = new Surface(surfaceTexture);
            CaptureRequest.Builder builderCreateCaptureRequest = this.i.createCaptureRequest(1);
            this.k = builderCreateCaptureRequest;
            builderCreateCaptureRequest.addTarget(surface);
            this.i.createCaptureSession(Arrays.asList(surface, this.c.getSurface()), new c(), null);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
        }
    }

    public final void I() {
        try {
            this.k.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            D(this.k);
            this.l.capture(this.k.build(), this.u, this.e);
            this.q = 0;
            this.l.setRepeatingRequest(this.m, this.u, this.e);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
        }
    }

    public final void u() {
        try {
            CameraDevice cameraDevice = this.i;
            if (cameraDevice == null) {
                return;
            }
            CaptureRequest.Builder builderCreateCaptureRequest = cameraDevice.createCaptureRequest(2);
            builderCreateCaptureRequest.addTarget(this.c.getSurface());
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
            D(builderCreateCaptureRequest);
            builderCreateCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(z(a.getWindowManager().getDefaultDisplay().getRotation())));
            e eVar = new e();
            this.l.stopRepeating();
            this.l.capture(builderCreateCaptureRequest.build(), eVar, null);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void w() {
        this.g.release();
        CameraDevice cameraDevice = this.i;
        if (cameraDevice != null) {
            cameraDevice.close();
        }
        this.i = null;
    }

    public void x(int i2, int i3, int i4) {
        if (this.f == null || this.j == null) {
            return;
        }
        Matrix matrix = new Matrix();
        float f2 = i2;
        float f3 = i3;
        RectF rectF = new RectF(0.0f, 0.0f, f2, f3);
        RectF rectF2 = new RectF(0.0f, 0.0f, this.j.getHeight(), this.j.getWidth());
        float fCenterX = rectF.centerX();
        float fCenterY = rectF.centerY();
        if (1 == i4 || 3 == i4) {
            rectF2.offset(fCenterX - rectF2.centerX(), fCenterY - rectF2.centerY());
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            float fMax = Math.max((f3 / this.j.getHeight()) + 0.5f, f2 / this.j.getWidth());
            matrix.postScale(fMax, fMax, fCenterX, fCenterY);
            matrix.postRotate((i4 - 2) * 90, fCenterX, fCenterY);
            BydLogHelper.logD("CameraController", "configureTransform scale =" + fMax);
        } else if (2 == i4) {
            matrix.postRotate(180.0f, fCenterX, fCenterY);
        }
        BydLogHelper.logD("CameraController", "configureTransform viewWidth =" + i2 + ",viewHeight =" + i3 + ",centerX =" + fCenterX + ",centerY =" + fCenterY + ",mPreviewSize.getHeight() =" + this.j.getHeight() + ",mPreviewSize.getWidth() =" + this.j.getWidth() + ",rotation =" + i4);
        this.f.setTransform(matrix);
    }

    public final int z(int i2) {
        return ((b.get(i2) + this.o.intValue()) + 270) % 360;
    }

    public gg() {
        this.g = new Semaphore(1);
        this.h = null;
        this.q = 0;
        this.s = new a();
        this.t = new b();
        this.u = new d();
        this.v = new g();
    }
}
