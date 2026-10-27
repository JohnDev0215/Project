package com.king.zxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.king.zxing.camera.d;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: compiled from: CaptureHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4603a = h.class.getSimpleName();
    private int B;
    private int C;
    private m F;
    private boolean G;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Activity f4604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private CaptureHandler f4605c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private n f4606d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.king.zxing.camera.d f4607e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private l f4608f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private g f4609g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private f f4610h;
    private ViewfinderView i;
    private SurfaceHolder j;
    private View k;
    private Collection<BarcodeFormat> l;
    private Map<DecodeHintType, Object> m;
    private String n;
    private float q;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean p = true;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;
    private boolean u = true;
    private float A = 0.9f;
    private float D = 45.0f;
    private float E = 100.0f;
    private boolean o = false;

    public h(Activity activity, SurfaceView surfaceView, ViewfinderView viewfinderView, View view) {
        this.f4604b = activity;
        this.i = viewfinderView;
        this.k = view;
        this.j = surfaceView.getHolder();
    }

    private float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void b(boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        if (!parameters.isZoomSupported()) {
            Log.i(f4603a, "zoom not supported");
            return;
        }
        int maxZoom = parameters.getMaxZoom();
        int zoom = parameters.getZoom();
        if (z && zoom < maxZoom) {
            zoom++;
        } else if (zoom > 0) {
            zoom--;
        }
        parameters.setZoom(zoom);
        camera.setParameters(parameters);
    }

    private void c(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.f4607e.h()) {
            Log.w(f4603a, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            this.f4607e.i(surfaceHolder);
            if (this.f4605c == null) {
                CaptureHandler captureHandler = new CaptureHandler(this.f4604b, this.i, this.f4606d, this.l, this.m, this.n, this.f4607e);
                this.f4605c = captureHandler;
                captureHandler.k(this.x);
                this.f4605c.h(this.y);
                this.f4605c.i(this.r);
                this.f4605c.j(this.s);
            }
        } catch (IOException e2) {
            Log.w(f4603a, e2);
        } catch (RuntimeException e3) {
            Log.w(f4603a, "Unexpected error initializing camera", e3);
        }
    }

    private void d() {
        com.king.zxing.camera.d dVar = new com.king.zxing.camera.d(this.f4604b);
        this.f4607e = dVar;
        dVar.o(this.z);
        this.f4607e.m(this.A);
        this.f4607e.n(this.B);
        this.f4607e.l(this.C);
        View view = this.k;
        if (view == null || !this.G) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.king.zxing.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f4554b.f(view2);
            }
        });
        this.f4607e.q(new d.a() { // from class: com.king.zxing.e
            @Override // com.king.zxing.camera.d.a
            public final void a(boolean z, boolean z2, float f2) {
                this.f4592a.h(z, z2, f2);
            }
        });
        this.f4607e.r(new d.b() { // from class: com.king.zxing.b
            @Override // com.king.zxing.camera.d.b
            public final void a(boolean z) {
                this.f4553a.j(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(View view) {
        com.king.zxing.camera.d dVar = this.f4607e;
        if (dVar != null) {
            dVar.s(!this.k.isSelected());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(boolean z, boolean z2, float f2) {
        if (z2) {
            if (this.k.getVisibility() != 0) {
                this.k.setVisibility(0);
            }
        } else {
            if (z || this.k.getVisibility() != 0) {
                return;
            }
            this.k.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(boolean z) {
        this.k.setSelected(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(com.google.zxing.h hVar, Bitmap bitmap, float f2) {
        this.f4608f.d();
        this.f4609g.b();
        s(hVar, bitmap, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n(String str) {
        m mVar = this.F;
        if (mVar == null || !mVar.h(str)) {
            Intent intent = new Intent();
            intent.putExtra("SCAN_RESULT", str);
            this.f4604b.setResult(-1, intent);
            this.f4604b.finish();
        }
    }

    public void o() {
        this.f4608f = new l(this.f4604b);
        this.f4609g = new g(this.f4604b);
        this.f4610h = new f(this.f4604b);
        this.G = this.f4604b.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        d();
        this.f4606d = new n() { // from class: com.king.zxing.a
            @Override // com.king.zxing.n
            public final void a(com.google.zxing.h hVar, Bitmap bitmap, float f2) {
                this.f4552a.l(hVar, bitmap, f2);
            }
        };
        this.f4609g.c(this.v);
        this.f4609g.d(this.w);
        this.f4610h.b(this.D);
        this.f4610h.a(this.E);
    }

    public void p() {
        this.f4608f.g();
    }

    public void q() {
        CaptureHandler captureHandler = this.f4605c;
        if (captureHandler != null) {
            captureHandler.f();
            this.f4605c = null;
        }
        this.f4608f.e();
        this.f4610h.d();
        this.f4609g.close();
        this.f4607e.b();
        if (!this.o) {
            this.j.removeCallback(this);
        }
        View view = this.k;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.k.setSelected(false);
        this.k.setVisibility(4);
    }

    public void r(com.google.zxing.h hVar) {
        final String strF = hVar.f();
        if (this.t) {
            m mVar = this.F;
            if (mVar != null) {
                mVar.h(strF);
            }
            if (this.u) {
                v();
                return;
            }
            return;
        }
        if (this.v) {
            this.f4605c.postDelayed(new Runnable() { // from class: com.king.zxing.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f4590b.n(strF);
                }
            }, 100L);
            return;
        }
        m mVar2 = this.F;
        if (mVar2 == null || !mVar2.h(strF)) {
            Intent intent = new Intent();
            intent.putExtra("SCAN_RESULT", strF);
            this.f4604b.setResult(-1, intent);
            this.f4604b.finish();
        }
    }

    public void s(com.google.zxing.h hVar, Bitmap bitmap, float f2) {
        r(hVar);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(f4603a, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (this.o) {
            return;
        }
        this.o = true;
        c(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.o = false;
    }

    public void t() {
        this.f4609g.f();
        this.f4608f.f();
        if (this.o) {
            c(this.j);
        } else {
            this.j.addCallback(this);
        }
        this.f4610h.c(this.f4607e);
    }

    public boolean u(MotionEvent motionEvent) {
        Camera cameraA;
        if (!this.p || !this.f4607e.h() || (cameraA = this.f4607e.f().a()) == null || motionEvent.getPointerCount() <= 1) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 2) {
            float fA = a(motionEvent);
            float f2 = this.q;
            if (fA > f2 + 6.0f) {
                b(true, cameraA);
            } else if (fA < f2 - 6.0f) {
                b(false, cameraA);
            }
            this.q = fA;
        } else if (action == 5) {
            this.q = a(motionEvent);
        }
        return true;
    }

    public void v() {
        CaptureHandler captureHandler = this.f4605c;
        if (captureHandler != null) {
            captureHandler.g();
        }
    }

    public h w(m mVar) {
        this.F = mVar;
        return this;
    }
}
