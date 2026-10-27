package com.king.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.f;
import java.io.IOException;

/* JADX INFO: compiled from: CameraManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4572a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f4573b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.king.zxing.camera.b f4574c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.king.zxing.camera.open.a f4575d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.king.zxing.camera.a f4576e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Rect f4577f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Rect f4578g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4579h;
    private boolean i;
    private int j = -1;
    private int k;
    private int l;
    private boolean m;
    private float n;
    private int o;
    private int p;
    private final e q;
    private b r;
    private a s;
    private boolean t;

    /* JADX INFO: compiled from: CameraManager.java */
    public interface a {
        void a(boolean z, boolean z2, float f2);
    }

    /* JADX INFO: compiled from: CameraManager.java */
    public interface b {
        void a(boolean z);
    }

    public d(Context context) {
        this.f4573b = context.getApplicationContext();
        com.king.zxing.camera.b bVar = new com.king.zxing.camera.b(context);
        this.f4574c = bVar;
        this.q = new e(bVar);
    }

    public f a(byte[] bArr, int i, int i2) {
        if (e() == null) {
            return null;
        }
        if (this.m) {
            return new f(bArr, i, i2, 0, 0, i, i2, false);
        }
        int iMin = (int) (Math.min(i, i2) * this.n);
        return new f(bArr, i, i2, ((i - iMin) / 2) + this.p, ((i2 - iMin) / 2) + this.o, iMin, iMin, false);
    }

    public void b() {
        com.king.zxing.camera.open.a aVar = this.f4575d;
        if (aVar != null) {
            aVar.a().release();
            this.f4575d = null;
            this.f4577f = null;
            this.f4578g = null;
        }
        this.t = false;
        b bVar = this.r;
        if (bVar != null) {
            bVar.a(false);
        }
    }

    public Point c() {
        return this.f4574c.b();
    }

    public synchronized Rect d() {
        if (this.f4577f == null) {
            if (this.f4575d == null) {
                return null;
            }
            Point pointB = this.f4574c.b();
            if (pointB == null) {
                return null;
            }
            int i = pointB.x;
            int i2 = pointB.y;
            if (this.m) {
                this.f4577f = new Rect(0, 0, i, i2);
            } else {
                int iMin = (int) (Math.min(i, i2) * this.n);
                int i3 = ((i - iMin) / 2) + this.p;
                int i4 = ((i2 - iMin) / 2) + this.o;
                this.f4577f = new Rect(i3, i4, i3 + iMin, iMin + i4);
            }
        }
        return this.f4577f;
    }

    public synchronized Rect e() {
        if (this.f4578g == null) {
            Rect rectD = d();
            if (rectD == null) {
                return null;
            }
            Rect rect = new Rect(rectD);
            Point pointB = this.f4574c.b();
            Point pointC = this.f4574c.c();
            if (pointB != null && pointC != null) {
                int i = rect.left;
                int i2 = pointB.y;
                int i3 = pointC.x;
                rect.left = (i * i2) / i3;
                rect.right = (rect.right * i2) / i3;
                int i4 = rect.top;
                int i5 = pointB.x;
                int i6 = pointC.y;
                rect.top = (i4 * i5) / i6;
                rect.bottom = (rect.bottom * i5) / i6;
                this.f4578g = rect;
            }
            return null;
        }
        return this.f4578g;
    }

    public com.king.zxing.camera.open.a f() {
        return this.f4575d;
    }

    public Point g() {
        return this.f4574c.c();
    }

    public synchronized boolean h() {
        return this.f4575d != null;
    }

    public void i(SurfaceHolder surfaceHolder) throws IOException {
        int i;
        com.king.zxing.camera.open.a aVarA = this.f4575d;
        if (aVarA == null) {
            aVarA = com.king.zxing.camera.open.b.a(this.j);
            if (aVarA == null) {
                throw new IOException("Camera.open() failed to return object from driver");
            }
            this.f4575d = aVarA;
        }
        if (!this.f4579h) {
            this.f4579h = true;
            this.f4574c.e(aVarA);
            int i2 = this.k;
            if (i2 > 0 && (i = this.l) > 0) {
                p(i2, i);
                this.k = 0;
                this.l = 0;
            }
        }
        Camera cameraA = aVarA.a();
        Camera.Parameters parameters = cameraA.getParameters();
        String strFlatten = parameters == null ? null : parameters.flatten();
        try {
            this.f4574c.g(aVarA, false);
        } catch (RuntimeException unused) {
            String str = f4572a;
            Log.w(str, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(str, "Resetting to saved camera params: " + strFlatten);
            if (strFlatten != null) {
                Camera.Parameters parameters2 = cameraA.getParameters();
                parameters2.unflatten(strFlatten);
                try {
                    cameraA.setParameters(parameters2);
                    this.f4574c.g(aVarA, true);
                } catch (RuntimeException unused2) {
                    Log.w(f4572a, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
        cameraA.setPreviewDisplay(surfaceHolder);
    }

    public synchronized void j(Handler handler, int i) {
        com.king.zxing.camera.open.a aVar = this.f4575d;
        if (aVar != null && this.i) {
            this.q.a(handler, i);
            aVar.a().setOneShotPreviewCallback(this.q);
        }
    }

    public void k(boolean z, float f2) {
        a aVar = this.s;
        if (aVar != null) {
            aVar.a(this.t, z, f2);
        }
    }

    public void l(int i) {
        this.p = i;
    }

    public void m(float f2) {
        this.n = f2;
    }

    public void n(int i) {
        this.o = i;
    }

    public void o(boolean z) {
        this.m = z;
    }

    public synchronized void p(int i, int i2) {
        if (this.f4579h) {
            Point pointC = this.f4574c.c();
            int i3 = pointC.x;
            if (i > i3) {
                i = i3;
            }
            int i4 = pointC.y;
            if (i2 > i4) {
                i2 = i4;
            }
            int i5 = (i3 - i) / 2;
            int i6 = (i4 - i2) / 2;
            this.f4577f = new Rect(i5, i6, i + i5, i2 + i6);
            Log.d(f4572a, "Calculated manual framing rect: " + this.f4577f);
            this.f4578g = null;
        } else {
            this.k = i;
            this.l = i2;
        }
    }

    public void q(a aVar) {
        this.s = aVar;
    }

    public void r(b bVar) {
        this.r = bVar;
    }

    public synchronized void s(boolean z) {
        com.king.zxing.camera.open.a aVar = this.f4575d;
        if (aVar != null && z != this.f4574c.d(aVar.a())) {
            com.king.zxing.camera.a aVar2 = this.f4576e;
            boolean z2 = aVar2 != null;
            if (z2) {
                aVar2.d();
                this.f4576e = null;
            }
            this.t = z;
            this.f4574c.h(aVar.a(), z);
            if (z2) {
                com.king.zxing.camera.a aVar3 = new com.king.zxing.camera.a(this.f4573b, aVar.a());
                this.f4576e = aVar3;
                aVar3.c();
            }
            b bVar = this.r;
            if (bVar != null) {
                bVar.a(z);
            }
        }
    }

    public void t() {
        com.king.zxing.camera.open.a aVar = this.f4575d;
        if (aVar == null || this.i) {
            return;
        }
        aVar.a().startPreview();
        this.i = true;
        this.f4576e = new com.king.zxing.camera.a(this.f4573b, aVar.a());
    }

    public void u() {
        com.king.zxing.camera.a aVar = this.f4576e;
        if (aVar != null) {
            aVar.d();
            this.f4576e = null;
        }
        com.king.zxing.camera.open.a aVar2 = this.f4575d;
        if (aVar2 == null || !this.i) {
            return;
        }
        aVar2.a().stopPreview();
        this.q.a(null, 0);
        this.i = false;
    }
}
