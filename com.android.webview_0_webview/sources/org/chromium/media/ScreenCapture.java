package org.chromium.media;

import J.N;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import defpackage.HH;
import defpackage.IH;
import defpackage.Is;
import defpackage.Ts;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ScreenCapture extends Fragment {
    public HandlerThread A;
    public Handler B;
    public Display C;
    public int D;
    public Intent E;
    public int F;
    public int G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f32J;
    public final long s;
    public final Object t = new Object();
    public int u = 4;
    public MediaProjection v;
    public MediaProjectionManager w;
    public VirtualDisplay x;
    public Surface y;
    public ImageReader z;

    public ScreenCapture(long j) {
        this.s = j;
    }

    public static ScreenCapture createScreenCaptureMachine(long j) {
        return new ScreenCapture(j);
    }

    public boolean allocate(int i, int i2) {
        this.G = i;
        this.H = i2;
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) Is.a.getSystemService("media_projection");
        this.w = mediaProjectionManager;
        if (mediaProjectionManager == null) {
            Ts.a("ScreenCapture", "mMediaProjectionManager is null", new Object[0]);
            return false;
        }
        this.C = ((DisplayManager) Is.a.getSystemService("display")).getDisplay(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.C.getMetrics(displayMetrics);
        this.F = displayMetrics.densityDpi;
        return true;
    }

    public final void b(int i) {
        synchronized (this.t) {
            this.u = i;
            this.t.notifyAll();
        }
    }

    public final void c() {
        ImageReader imageReader = this.z;
        if (imageReader != null) {
            imageReader.close();
        }
        ImageReader imageReaderNewInstance = ImageReader.newInstance(this.G, this.H, this.I, 2);
        this.z = imageReaderNewInstance;
        this.y = imageReaderNewInstance.getSurface();
        this.z.setOnImageAvailableListener(new HH(this, null), this.B);
    }

    public final void d() {
        VirtualDisplay virtualDisplay = this.x;
        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
        this.x = this.v.createVirtualDisplay("ScreenCapture", this.G, this.H, this.F, 16, this.y, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e() {
        /*
            r6 = this;
            android.view.Display r0 = r6.C
            int r0 = r0.getRotation()
            r1 = 180(0xb4, float:2.52E-43)
            r2 = 90
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L20
            if (r0 == r4) goto L1d
            r5 = 2
            if (r0 == r5) goto L1a
            r5 = 3
            if (r0 == r5) goto L17
            goto L20
        L17:
            r0 = 270(0x10e, float:3.78E-43)
            goto L21
        L1a:
            r0 = 180(0xb4, float:2.52E-43)
            goto L21
        L1d:
            r0 = 90
            goto L21
        L20:
            r0 = 0
        L21:
            if (r0 == 0) goto L29
            if (r0 == r2) goto L27
            if (r0 == r1) goto L29
        L27:
            r1 = 1
            goto L2a
        L29:
            r1 = 0
        L2a:
            int r2 = r6.D
            if (r1 != r2) goto L2f
            return r3
        L2f:
            r6.D = r1
            if (r1 != r4) goto L39
            int r2 = r6.G
            int r3 = r6.H
            if (r2 < r3) goto L41
        L39:
            if (r1 != 0) goto L4b
            int r1 = r6.H
            int r2 = r6.G
            if (r1 >= r2) goto L4b
        L41:
            int r1 = r6.G
            int r2 = r6.H
            r6.H = r1
            int r2 = r2 - r1
            int r2 = r2 + r1
            r6.G = r2
        L4b:
            long r1 = r6.s
            J.N.Ml8UDaFX(r1, r6, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.ScreenCapture.e():boolean");
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return;
        }
        if (i2 == -1) {
            this.f32J = i2;
            this.E = intent;
            b(1);
        }
        N.M2auslLM(this.s, this, i2 == -1);
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        b(0);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        stopCapture();
    }

    public boolean startCapture() {
        synchronized (this.t) {
            if (this.u != 1) {
                Ts.a("ScreenCapture", "startCapture() invoked without user permission.", new Object[0]);
                return false;
            }
            MediaProjection mediaProjection = this.w.getMediaProjection(this.f32J, this.E);
            this.v = mediaProjection;
            if (mediaProjection == null) {
                Ts.a("ScreenCapture", "mMediaProjection is null", new Object[0]);
                return false;
            }
            mediaProjection.registerCallback(new IH(this, null), null);
            HandlerThread handlerThread = new HandlerThread("ScreenCapture");
            this.A = handlerThread;
            handlerThread.start();
            this.B = new Handler(this.A.getLooper());
            this.I = 1;
            e();
            c();
            d();
            b(2);
            return true;
        }
    }

    public boolean startPrompt() {
        ApplicationStatus.a();
        Ts.a("ScreenCapture", "activity is null", new Object[0]);
        return false;
    }

    public void stopCapture() {
        synchronized (this.t) {
            if (this.v == null || this.u != 2) {
                b(4);
            } else {
                this.v.stop();
                b(3);
                while (this.u != 4) {
                    try {
                        this.t.wait();
                    } catch (InterruptedException e) {
                        Ts.a("ScreenCapture", "ScreenCaptureEvent: " + e, new Object[0]);
                    }
                }
            }
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        b(0);
    }
}
