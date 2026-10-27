package org.chromium.android_webview;

import J.N;
import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import defpackage.C0959iq;
import defpackage.C1182mq;
import defpackage.C1896zq;
import defpackage.InterfaceC0771fO;
import defpackage.InterfaceC1841yq;
import defpackage.JD;
import defpackage.Ys;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.chromium.android_webview.PopupTouchHandleDrawable;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.HandleViewResources;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PopupTouchHandleDrawable extends View implements InterfaceC0771fO {
    public float A;
    public int B;
    public int C;
    public boolean D;
    public boolean E;
    public float F;
    public final int[] G;
    public int H;
    public float I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Runnable f28J;
    public long K;
    public Runnable L;
    public long M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public final JD T;
    public boolean U;
    public Runnable V;
    public boolean W;
    public boolean a0;
    public final Ys b0;
    public final PopupWindow s;
    public final InterfaceC1841yq t;
    public WebContents u;
    public ViewGroup v;
    public C1896zq w;
    public Drawable x;
    public final long y;
    public float z;

    public PopupTouchHandleDrawable(Ys ys, WebContents webContents, ViewGroup viewGroup) {
        super(viewGroup.getContext());
        this.G = new int[2];
        this.H = 3;
        this.b0 = ys;
        ys.f(this);
        this.u = webContents;
        this.v = viewGroup;
        WindowAndroid windowAndroidH = webContents.H();
        this.I = windowAndroidH.t.d;
        PopupWindow popupWindow = new PopupWindow((Context) windowAndroidH.S().get(), (AttributeSet) null, R.attr.textSelectHandleWindowStyle);
        this.s = popupWindow;
        popupWindow.setSplitTouchEnabled(true);
        this.s.setClippingEnabled(false);
        this.s.setAnimationStyle(0);
        PopupWindow popupWindow2 = this.s;
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow2.setWindowLayoutType(1002);
        } else {
            try {
                PopupWindow.class.getMethod("setWindowLayoutType", Integer.TYPE).invoke(popupWindow2, 1002);
            } catch (IllegalAccessException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            }
        }
        this.s.setWidth(-2);
        this.s.setHeight(-2);
        this.F = 0.0f;
        this.N = false;
        setVisibility(4);
        this.P = viewGroup.hasWindowFocus();
        this.w = new C1896zq(viewGroup);
        this.t = new C0959iq(this);
        this.T = new C1182mq(this);
        GestureListenerManagerImpl.O(this.u).t.f(this.T);
        this.y = N.MQ7e2Y0w(this, HandleViewResources.getHandleHorizontalPaddingRatio());
    }

    public static void a(PopupTouchHandleDrawable popupTouchHandleDrawable, boolean z) {
        if (popupTouchHandleDrawable.O == z) {
            return;
        }
        popupTouchHandleDrawable.O = z;
        popupTouchHandleDrawable.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy() {
        this.b0.g(this);
        if (this.u == null) {
            return;
        }
        hide();
        GestureListenerManagerImpl gestureListenerManagerImplO = GestureListenerManagerImpl.O(this.u);
        if (gestureListenerManagerImplO != null) {
            gestureListenerManagerImplO.t.g(this.T);
        }
        this.u = null;
    }

    public static Drawable g(Context context, int i) {
        int[] iArr = HandleViewResources.b;
        return i != 0 ? i != 1 ? i != 2 ? HandleViewResources.b(context, iArr) : HandleViewResources.b(context, HandleViewResources.c) : HandleViewResources.b(context, iArr) : HandleViewResources.b(context, HandleViewResources.a);
    }

    private float getOriginXDip() {
        return this.z;
    }

    private float getOriginYDip() {
        return this.A;
    }

    private float getVisibleHeightDip() {
        if (this.x == null) {
            return 0.0f;
        }
        return r0.getIntrinsicHeight() / this.I;
    }

    private float getVisibleWidthDip() {
        if (this.x == null) {
            return 0.0f;
        }
        return r0.getIntrinsicWidth() / this.I;
    }

    private void hide() {
        this.M = 0L;
        l(false);
        this.F = 1.0f;
        if (this.s.isShowing()) {
            try {
                this.s.dismiss();
            } catch (IllegalArgumentException unused) {
            }
        }
        this.w.c.clear();
    }

    private void setOrientation(int i, boolean z, boolean z2) {
        boolean z3 = true;
        boolean z4 = this.H != i;
        if (this.D == z2 && this.E == z) {
            z3 = false;
        }
        this.H = i;
        this.D = z2;
        this.E = z;
        if (z4) {
            this.x = g(getContext(), this.H);
        }
        Drawable drawable = this.x;
        if (drawable != null) {
            drawable.setAlpha((int) (this.F * 255.0f));
        }
        if (z4 || z3) {
            k();
        }
    }

    private void setOrigin(float f, float f2) {
        if (this.z == f && this.A == f2 && !this.S) {
            return;
        }
        this.z = f;
        this.A = f2;
        if (this.N || this.S) {
            if (this.S) {
                this.S = false;
            }
            k();
        }
    }

    private void setVisible(boolean z) {
        if (this.N == z) {
            return;
        }
        this.N = z;
        i();
    }

    private void show() {
        if (this.u == null || this.s.isShowing()) {
            return;
        }
        C1896zq c1896zq = this.w;
        c1896zq.b();
        int i = c1896zq.b[0];
        C1896zq c1896zq2 = this.w;
        c1896zq2.b();
        int i2 = c1896zq2.b[1];
        if (this.B != i || this.C != i2) {
            this.B = i;
            this.C = i2;
            m();
        }
        this.w.a(this.t);
        this.s.setContentView(this);
        try {
            this.s.showAtLocation(this.v, 0, this.B + ((int) (this.z * this.I)), this.C + ((int) (this.A * this.I)));
        } catch (WindowManager.BadTokenException unused) {
            hide();
        }
    }

    @Override // defpackage.InterfaceC0771fO
    public void C(Display.Mode mode) {
    }

    @Override // defpackage.InterfaceC0771fO
    public void e(float f) {
        if (this.I != f) {
            this.I = f;
            this.a0 = true;
        }
    }

    public final void f() {
        if (this.s.isShowing()) {
            o();
            PopupWindow popupWindow = this.s;
            int i = this.B;
            float f = this.z;
            float f2 = this.I;
            popupWindow.update(i + ((int) (f * f2)), this.C + ((int) (this.A * f2)), getRight() - getLeft(), getBottom() - getTop());
            invalidate();
        }
    }

    public final boolean h() {
        boolean z;
        if (this.R && this.N && this.P && !this.O && !this.Q) {
            float f = this.z;
            float f2 = this.I;
            float[] fArr = {f * f2, this.A * f2};
            View view = this.v;
            while (view != null) {
                if (view != this.v) {
                    fArr[0] = fArr[0] - view.getScrollX();
                    fArr[1] = fArr[1] - view.getScrollY();
                }
                float intrinsicWidth = this.x.getIntrinsicWidth();
                float intrinsicHeight = this.x.getIntrinsicHeight();
                if (fArr[0] + intrinsicWidth < 0.0f || fArr[1] + intrinsicHeight < 0.0f || fArr[0] > view.getWidth() || fArr[1] > view.getHeight()) {
                    z = false;
                    break;
                }
                if (!view.getMatrix().isIdentity()) {
                    view.getMatrix().mapPoints(fArr);
                }
                fArr[0] = fArr[0] + view.getLeft();
                fArr[1] = fArr[1] + view.getTop();
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        if (this.s.isShowing()) {
            boolean zH = h();
            if ((getVisibility() == 0) == zH) {
                return;
            }
            Runnable runnable = this.f28J;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
            if (!zH) {
                o();
                return;
            }
            if (this.f28J == null) {
                this.f28J = new Runnable(this) { // from class: kq
                    public final PopupTouchHandleDrawable s;

                    {
                        this.s = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        PopupTouchHandleDrawable popupTouchHandleDrawable = this.s;
                        if (popupTouchHandleDrawable.getVisibility() == 0) {
                            return;
                        }
                        popupTouchHandleDrawable.F = 0.0f;
                        popupTouchHandleDrawable.K = AnimationUtils.currentAnimationTimeMillis();
                        popupTouchHandleDrawable.f();
                    }
                };
            }
            postOnAnimation(this.f28J);
        }
    }

    @Override // defpackage.InterfaceC0771fO
    public void j(float f) {
    }

    public final void k() {
        if (this.V == null) {
            this.V = new Runnable(this) { // from class: lq
                public final PopupTouchHandleDrawable s;

                {
                    this.s = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PopupTouchHandleDrawable popupTouchHandleDrawable = this.s;
                    popupTouchHandleDrawable.W = false;
                    popupTouchHandleDrawable.f();
                }
            };
        }
        if (this.W) {
            return;
        }
        this.W = true;
        postOnAnimation(this.V);
    }

    public final void l(boolean z) {
        if (this.Q == z) {
            return;
        }
        this.Q = z;
        if (z) {
            if (this.L == null) {
                this.L = new Runnable(this) { // from class: jq
                    public final PopupTouchHandleDrawable s;

                    {
                        this.s = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.s.l(false);
                    }
                };
            }
            removeCallbacks(this.L);
            postDelayed(this.L, Math.max(0L, this.M - SystemClock.uptimeMillis()));
        } else {
            Runnable runnable = this.L;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
        }
        i();
    }

    public final void m() {
        if (this.s.isShowing()) {
            this.M = SystemClock.uptimeMillis() + 300;
            l(true);
        }
    }

    public final void n() {
        this.a0 = false;
        if (this.x == null) {
            return;
        }
        Drawable drawableG = g(getContext(), this.H);
        this.x = drawableG;
        if (drawableG != null) {
            drawableG.setAlpha((int) (this.F * 255.0f));
        }
        if (isInLayout()) {
            return;
        }
        requestLayout();
    }

    public final void o() {
        int i = h() ? 0 : 4;
        if (i != 0 || getVisibility() == 0 || this.U) {
            this.U = false;
            setVisibility(i);
        } else {
            this.U = true;
            k();
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.R = true;
        i();
        WindowAndroid windowAndroidH = this.u.H();
        if (windowAndroidH != null) {
            windowAndroidH.t.a.put(this, null);
            this.I = windowAndroidH.t.d;
            n();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a0 && this.I == getResources().getDisplayMetrics().density) {
            n();
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        WindowAndroid windowAndroidH;
        super.onDetachedFromWindow();
        WebContents webContents = this.u;
        if (webContents != null && (windowAndroidH = webContents.H()) != null) {
            windowAndroidH.t.a.remove(this);
        }
        this.R = false;
        i();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.x == null) {
            return;
        }
        boolean z = this.D || this.E;
        if (z) {
            canvas.save();
            canvas.scale(this.D ? -1.0f : 1.0f, this.E ? -1.0f : 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        if (this.F != 1.0f) {
            float fMin = Math.min(1.0f, (AnimationUtils.currentAnimationTimeMillis() - this.K) / 200.0f);
            this.F = fMin;
            this.x.setAlpha((int) (fMin * 255.0f));
            k();
        }
        this.x.setBounds(0, 0, getRight() - getLeft(), getBottom() - getTop());
        this.x.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        Drawable drawable = this.x;
        if (drawable == null) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(drawable.getIntrinsicWidth(), this.x.getIntrinsicHeight());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.u == null) {
            return false;
        }
        this.v.getLocationOnScreen(this.G);
        float rawX = (motionEvent.getRawX() - motionEvent.getX()) - this.G[0];
        float rawY = (motionEvent.getRawY() - motionEvent.getY()) - this.G[1];
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        motionEventObtainNoHistory.offsetLocation(rawX, rawY);
        boolean zI = this.u.F().i(motionEventObtainNoHistory, true);
        motionEventObtainNoHistory.recycle();
        return zI;
    }

    @Override // defpackage.InterfaceC0771fO
    public void x(int i) {
        this.S = true;
    }

    @Override // defpackage.InterfaceC0771fO
    public void y(List list) {
    }
}
