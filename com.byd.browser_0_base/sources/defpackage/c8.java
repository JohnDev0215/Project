package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import org.chromium.components.webxr.ArCoreJavaUtils;
import org.chromium.content.browser.ScreenOrientationProviderImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ArImmersiveOverlay.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c8 implements SurfaceHolder.Callback2, View.OnTouchListener, qe2 {
    public ArCoreJavaUtils e;
    public Activity f;
    public boolean g;
    public Integer h;
    public boolean i;
    public WebContents j;
    public boolean k;
    public HashMap<Integer, a> l;
    public Integer m;

    /* JADX INFO: compiled from: ArImmersiveOverlay.java */
    public class a {
        public float a;
        public float b;
        public boolean c = true;

        public a(float f, float f2) {
            this.a = f;
            this.b = f2;
        }
    }

    public final void a() {
        if (this.i) {
            return;
        }
        this.i = true;
        ArCoreJavaUtils arCoreJavaUtils = this.e;
        long j = arCoreJavaUtils.a;
        if (j != 0) {
            new a8().V(j, arCoreJavaUtils);
        }
        throw null;
    }

    public final void b(boolean z) {
        for (Map.Entry<Integer, a> entry : this.l.entrySet()) {
            ArCoreJavaUtils arCoreJavaUtils = this.e;
            Integer num = this.m;
            boolean z2 = num != null && num.equals(entry.getKey());
            boolean z3 = z ? false : entry.getValue().c;
            int iIntValue = entry.getKey().intValue();
            float f = entry.getValue().a;
            float f2 = entry.getValue().b;
            long j = arCoreJavaUtils.a;
            if (j != 0) {
                new a8().X(j, arCoreJavaUtils, z2, z3, iIntValue, f, f2);
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1 || actionMasked == 5 || actionMasked == 6 || actionMasked == 3 || actionMasked == 2) {
            if (actionMasked == 0) {
                int pointerId = motionEvent.getPointerId(0);
                Integer numValueOf = Integer.valueOf(pointerId);
                this.m = numValueOf;
                if (this.l.put(numValueOf, new a(motionEvent.getX(0), motionEvent.getY(0))) != null) {
                    Log.w("cr_ArImmersiveOverlay", "New pointer with ID " + pointerId + " introduced by ACTION_DOWN when old pointer with the same ID already exists.");
                }
                b(false);
            }
            if (actionMasked == 1 || actionMasked == 3) {
                b(true);
                this.m = null;
                this.l.clear();
            }
            if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                if (this.l.put(Integer.valueOf(pointerId2), new a(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex))) != null) {
                    Log.w("cr_ArImmersiveOverlay", "New pointer with ID " + pointerId2 + " introduced by ACTION_POINTER_DOWN when old pointer with the same ID already exists.");
                }
                b(false);
            }
            if (actionMasked == 6) {
                int pointerId3 = motionEvent.getPointerId(motionEvent.getActionIndex());
                if (this.l.containsKey(Integer.valueOf(pointerId3))) {
                    this.l.get(Integer.valueOf(pointerId3)).c = false;
                    b(false);
                    Integer num = this.m;
                    if (num != null && num.intValue() == pointerId3) {
                        this.m = null;
                    }
                    this.l.remove(Integer.valueOf(pointerId3));
                } else {
                    Log.w("cr_ArImmersiveOverlay", "Pointer with ID " + pointerId3 + " not found in mPointerIdToData, ignoring ACTION_POINTER_UP for it.");
                }
            }
            if (actionMasked == 2) {
                for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                    int pointerId4 = motionEvent.getPointerId(i);
                    a aVar = this.l.get(Integer.valueOf(pointerId4));
                    if (aVar == null) {
                        Log.w("cr_ArImmersiveOverlay", "Pointer with ID " + pointerId4 + "(index " + i + ") not found in mPointerIdToData, ignoring ACTION_MOVE for it.");
                    } else {
                        aVar.a = motionEvent.getX(i);
                        aVar.b = motionEvent.getY(i);
                    }
                }
                b(false);
            }
        }
        if (this.k) {
            throw null;
        }
        return true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        int i4;
        int i5;
        ya0 ya0Var = this.j.V().h;
        if (this.g) {
            ya0Var.getClass();
            return;
        }
        ScreenOrientationProviderImpl.getInstance().e = this;
        if (this.h == null) {
            this.h = Integer.valueOf(this.f.getRequestedOrientation());
        }
        this.f.setRequestedOrientation(14);
        Point point = ya0Var.c;
        int i6 = point.x;
        int i7 = point.y;
        if (i2 < i6 || i3 < i7) {
            i4 = i7;
            i5 = i6;
        } else {
            i5 = i2;
            i4 = i3;
        }
        int i8 = ya0Var.i;
        ArCoreJavaUtils arCoreJavaUtils = this.e;
        Surface surface = surfaceHolder.getSurface();
        WindowAndroid windowAndroidV = this.j.V();
        long j = arCoreJavaUtils.a;
        if (j != 0) {
            new a8().W(j, arCoreJavaUtils, surface, windowAndroidV, i8, i5, i4);
        }
        this.g = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        a();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }
}
