package org.chromium.media;

import J.N;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import defpackage.xz;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenCapture extends Fragment {
    public static final /* synthetic */ int w = 0;
    public long e;
    public final Object f = new Object();
    public int g = 4;
    public MediaProjection h;
    public MediaProjectionManager i;
    public VirtualDisplay j;
    public Surface k;
    public ImageReader l;
    public HandlerThread m;
    public Handler n;
    public Display o;
    public int p;
    public Intent q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;

    public class a implements ImageReader.OnImageAvailableListener {
        public a() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            synchronized (ScreenCapture.this.f) {
                ScreenCapture screenCapture = ScreenCapture.this;
                if (screenCapture.g != 2) {
                    Log.e("cr_ScreenCapture", "Get captured frame in unexpected state.");
                    return;
                }
                if (screenCapture.d()) {
                    ScreenCapture.this.b();
                    ScreenCapture.this.c();
                    return;
                }
                try {
                    Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                    if (imageAcquireLatestImage == null) {
                        if (imageAcquireLatestImage != null) {
                            imageAcquireLatestImage.close();
                            return;
                        }
                        return;
                    }
                    try {
                        if (imageReader.getWidth() != imageAcquireLatestImage.getWidth() || imageReader.getHeight() != imageAcquireLatestImage.getHeight()) {
                            Log.e("cr_ScreenCapture", "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + imageAcquireLatestImage.getWidth() + "x" + imageAcquireLatestImage.getHeight() + ")");
                            throw new IllegalStateException();
                        }
                        int format = imageAcquireLatestImage.getFormat();
                        if (format != 1) {
                            if (format != 35) {
                                Log.e("cr_ScreenCapture", "Unexpected image format: " + imageAcquireLatestImage.getFormat());
                                throw new IllegalStateException();
                            }
                            if (imageAcquireLatestImage.getPlanes().length != 3) {
                                Log.e("cr_ScreenCapture", "Unexpected image planes for YUV_420_888 format: " + imageAcquireLatestImage.getPlanes().length);
                                throw new IllegalStateException();
                            }
                            ScreenCapture screenCapture2 = ScreenCapture.this;
                            N.MgLL$kUF(screenCapture2.e, screenCapture2, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getBuffer(), imageAcquireLatestImage.getPlanes()[2].getBuffer(), imageAcquireLatestImage.getPlanes()[1].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getPixelStride(), imageAcquireLatestImage.getCropRect().left, imageAcquireLatestImage.getCropRect().top, imageAcquireLatestImage.getCropRect().width(), imageAcquireLatestImage.getCropRect().height(), imageAcquireLatestImage.getTimestamp());
                        } else {
                            if (imageAcquireLatestImage.getPlanes().length != 1) {
                                Log.e("cr_ScreenCapture", "Unexpected image planes for RGBA_8888 format: " + imageAcquireLatestImage.getPlanes().length);
                                throw new IllegalStateException();
                            }
                            ScreenCapture screenCapture3 = ScreenCapture.this;
                            N.MjDBp6op(screenCapture3.e, screenCapture3, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getCropRect().left, imageAcquireLatestImage.getCropRect().top, imageAcquireLatestImage.getCropRect().width(), imageAcquireLatestImage.getCropRect().height(), imageAcquireLatestImage.getTimestamp());
                        }
                        imageAcquireLatestImage.close();
                    } catch (Throwable th) {
                        try {
                            imageAcquireLatestImage.close();
                        } catch (Throwable unused) {
                        }
                        throw th;
                    }
                } catch (IllegalStateException e) {
                    Log.e("cr_ScreenCapture", "acquireLatestImage():" + e);
                } catch (UnsupportedOperationException e2) {
                    Log.i("cr_ScreenCapture", "acquireLatestImage():" + e2);
                    ScreenCapture screenCapture4 = ScreenCapture.this;
                    if (screenCapture4.u == 35) {
                        screenCapture4.u = 1;
                        screenCapture4.b();
                        ScreenCapture.this.c();
                    }
                }
            }
        }
    }

    public class b extends MediaProjection.Callback {
        public b() {
        }

        @Override // android.media.projection.MediaProjection.Callback
        public final void onStop() {
            int i = ScreenCapture.w;
            ScreenCapture screenCapture = ScreenCapture.this;
            screenCapture.a(4);
            screenCapture.h = null;
            VirtualDisplay virtualDisplay = screenCapture.j;
            if (virtualDisplay == null) {
                return;
            }
            virtualDisplay.release();
            screenCapture.j = null;
        }
    }

    public ScreenCapture(long j) {
        this.e = j;
    }

    @CalledByNative
    public static ScreenCapture createScreenCaptureMachine(long j) {
        return new ScreenCapture(j);
    }

    public final void a(int i) {
        synchronized (this.f) {
            this.g = i;
            this.f.notifyAll();
        }
    }

    @CalledByNative
    public boolean allocate(int i, int i2) {
        this.s = i;
        this.t = i2;
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) xz.a.getSystemService("media_projection");
        this.i = mediaProjectionManager;
        if (mediaProjectionManager == null) {
            Log.e("cr_ScreenCapture", "mMediaProjectionManager is null");
            return false;
        }
        this.o = ((DisplayManager) xz.a.getSystemService("display")).getDisplay(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.o.getMetrics(displayMetrics);
        this.r = displayMetrics.densityDpi;
        return true;
    }

    public final void b() {
        ImageReader imageReader = this.l;
        if (imageReader != null) {
            imageReader.close();
        }
        ImageReader imageReaderNewInstance = ImageReader.newInstance(this.s, this.t, this.u, 2);
        this.l = imageReaderNewInstance;
        this.k = imageReaderNewInstance.getSurface();
        this.l.setOnImageAvailableListener(new a(), this.n);
    }

    public final void c() {
        VirtualDisplay virtualDisplay = this.j;
        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
        this.j = this.h.createVirtualDisplay("ScreenCapture", this.s, this.t, this.r, 16, this.k, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d() {
        /*
            r6 = this;
            android.view.Display r0 = r6.o
            int r0 = r0.getRotation()
            r1 = 180(0xb4, float:2.52E-43)
            r2 = 90
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L1e
            if (r0 == r4) goto L1c
            r5 = 2
            if (r0 == r5) goto L1a
            r5 = 3
            if (r0 == r5) goto L17
            goto L1e
        L17:
            r0 = 270(0x10e, float:3.78E-43)
            goto L1f
        L1a:
            r0 = r1
            goto L1f
        L1c:
            r0 = r2
            goto L1f
        L1e:
            r0 = r3
        L1f:
            if (r0 == 0) goto L27
            if (r0 == r2) goto L25
            if (r0 == r1) goto L27
        L25:
            r1 = r4
            goto L28
        L27:
            r1 = r3
        L28:
            int r2 = r6.p
            if (r1 != r2) goto L2d
            return r3
        L2d:
            r6.p = r1
            if (r1 != r4) goto L37
            int r2 = r6.s
            int r3 = r6.t
            if (r2 < r3) goto L3f
        L37:
            if (r1 != 0) goto L49
            int r1 = r6.t
            int r2 = r6.s
            if (r1 >= r2) goto L49
        L3f:
            int r1 = r6.s
            int r2 = r6.t
            r6.t = r1
            int r2 = r2 - r1
            int r2 = r2 + r1
            r6.s = r2
        L49:
            long r1 = r6.e
            J.N.Ml8UDaFX(r1, r6, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.ScreenCapture.d():boolean");
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && this.e != 0) {
            if (i2 == -1) {
                this.v = i2;
                this.q = intent;
                a(1);
            }
            N.M2auslLM(this.e, this, i2 == -1);
        }
    }

    @Override // android.app.Fragment
    public final void onAttach(Context context) {
        super.onAttach(context);
        a(0);
    }

    @Override // android.app.Fragment
    public final void onDetach() {
        super.onDetach();
        stopCapture();
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.e = 0L;
    }

    @CalledByNative
    public boolean startCapture() {
        synchronized (this.f) {
            if (this.g != 1) {
                Log.e("cr_ScreenCapture", "startCapture() invoked without user permission.");
                return false;
            }
            MediaProjection mediaProjection = this.i.getMediaProjection(this.v, this.q);
            this.h = mediaProjection;
            if (mediaProjection == null) {
                Log.e("cr_ScreenCapture", "mMediaProjection is null");
                return false;
            }
            mediaProjection.registerCallback(new b(), null);
            HandlerThread handlerThread = new HandlerThread("ScreenCapture");
            this.m = handlerThread;
            handlerThread.start();
            this.n = new Handler(this.m.getLooper());
            this.u = 1;
            d();
            b();
            c();
            a(2);
            return true;
        }
    }

    @CalledByNative
    public boolean startPrompt() {
        Activity activity = ApplicationStatus.c;
        if (activity == null) {
            Log.e("cr_ScreenCapture", "activity is null");
            return false;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(this, "screencapture");
        try {
            fragmentTransactionBeginTransaction.commit();
            synchronized (this.f) {
                while (this.g != 0) {
                    try {
                        this.f.wait();
                    } catch (InterruptedException e) {
                        Log.e("cr_ScreenCapture", "ScreenCaptureException: " + e);
                    }
                }
            }
            try {
                startActivityForResult(this.i.createScreenCaptureIntent(), 1);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("cr_ScreenCapture", "ScreenCaptureException " + e2);
                return false;
            }
        } catch (RuntimeException e3) {
            Log.e("cr_ScreenCapture", "ScreenCaptureExcaption " + e3);
            return false;
        }
    }

    @CalledByNative
    public void stopCapture() {
        synchronized (this.f) {
            MediaProjection mediaProjection = this.h;
            if (mediaProjection == null || this.g != 2) {
                a(4);
            } else {
                mediaProjection.stop();
                a(3);
                while (this.g != 4) {
                    try {
                        this.f.wait();
                    } catch (InterruptedException e) {
                        Log.e("cr_ScreenCapture", "ScreenCaptureEvent: " + e);
                    }
                }
            }
        }
    }

    @Override // android.app.Fragment
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        a(0);
    }
}
