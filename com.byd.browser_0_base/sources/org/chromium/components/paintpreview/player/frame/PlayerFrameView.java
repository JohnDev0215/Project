package org.chromium.components.paintpreview.player.frame;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.FrameLayout;
import defpackage.d22;
import defpackage.e22;
import defpackage.es3;
import defpackage.g22;
import defpackage.gq0;
import defpackage.kq3;
import defpackage.t12;
import defpackage.x12;
import defpackage.y12;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.accessibility.a;
import org.chromium.ui.accessibility.AccessibilityState;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerFrameView extends FrameLayout {
    public final t12 e;
    public final x12 f;
    public final d22 g;
    public List<View> h;
    public List<Rect> i;
    public Matrix j;
    public final Matrix k;
    public kq3 l;

    public PlayerFrameView(Context context, boolean z, y12 y12Var, gq0 gq0Var, g22 g22Var) {
        super(context);
        this.k = new Matrix();
        setWillNotDraw(false);
        this.g = y12Var;
        this.e = new t12(new es3(18, this), g22Var);
        this.f = new x12(context, z, gq0Var);
    }

    public final void a() {
        if (this.h == null || this.i == null) {
            removeAllViews();
            return;
        }
        for (int i = 0; i < this.h.size(); i++) {
            View view = this.h.get(i);
            if (view.getVisibility() != 0) {
                removeView(view);
            } else {
                if (view.getParent() == null) {
                    addView(this.h.get(i));
                } else if (view.getParent() != this) {
                    throw new IllegalStateException("Sub-frame view already has a parent.");
                }
                Rect rect = this.i.get(i);
                view.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        kq3 kq3Var = this.l;
        AccessibilityNodeProvider accessibilityNodeProviderL = kq3Var != null ? ((WebContentsAccessibilityImpl) kq3Var).l() : null;
        return accessibilityNodeProviderL != null ? accessibilityNodeProviderL : super.getAccessibilityNodeProvider();
    }

    public x12 getGestureDetector() {
        return this.f;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.save();
        canvas.concat(this.k);
        canvas.concat(this.j);
        t12 t12Var = this.e;
        if (!t12Var.h && t12Var.b != null) {
            Rect rect = t12Var.c;
            if (!rect.isEmpty() && t12Var.a.getWidth() > 0 && t12Var.a.getHeight() > 0) {
                TraceEvent.q("PlayerFrameBitmapPainter.onDraw", null);
                int height = rect.top / t12Var.a.getHeight();
                int iCeil = (int) Math.ceil(((double) rect.bottom) / ((double) t12Var.a.getHeight()));
                int width = rect.left / t12Var.a.getWidth();
                int iCeil2 = (int) Math.ceil(((double) rect.right) / ((double) t12Var.a.getWidth()));
                int iMin = Math.min(iCeil, t12Var.b.length);
                int i = 0;
                int iMin2 = Math.min(iCeil2, iMin >= 1 ? t12Var.b[iMin - 1].length : 0);
                while (height < iMin) {
                    int i2 = width;
                    while (i2 < iMin2) {
                        Bitmap bitmap = t12Var.b[height][i2];
                        if (bitmap != null) {
                            int iMax = Math.max(rect.left - (t12Var.a.getWidth() * i2), i);
                            int iMax2 = Math.max(rect.top - (t12Var.a.getHeight() * height), i);
                            int iMin3 = Math.min(t12Var.a.getWidth(), (rect.right + iMax) - (t12Var.a.getWidth() * i2));
                            int iMin4 = Math.min(t12Var.a.getHeight(), (rect.bottom + iMax2) - (t12Var.a.getHeight() * height));
                            Rect rect2 = t12Var.d;
                            rect2.set(iMax, iMax2, iMin3, iMin4);
                            int iMax3 = Math.max((t12Var.a.getWidth() * i2) - rect.left, 0);
                            int iMax4 = Math.max((t12Var.a.getHeight() * height) - rect.top, 0);
                            int iWidth = rect2.width() + iMax3;
                            int iHeight = rect2.height() + iMax4;
                            Rect rect3 = t12Var.e;
                            rect3.set(iMax3, iMax4, iWidth, iHeight);
                            canvas.drawBitmap(bitmap, rect2, rect3, (Paint) null);
                            Runnable runnable = t12Var.g;
                            if (runnable != null) {
                                runnable.run();
                                t12Var.g = null;
                            }
                        }
                        i2++;
                        i = 0;
                    }
                    height++;
                    i = 0;
                }
                TraceEvent.C("PlayerFrameBitmapPainter.onDraw");
            }
        }
        canvas.restore();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.l != null) {
            if (!AccessibilityState.f) {
                AccessibilityState.a();
            }
            if (AccessibilityState.e.b) {
                WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) this.l;
                webContentsAccessibilityImpl.getClass();
                if (!webContentsAccessibilityImpl.onHoverEvent(motionEvent.getAction())) {
                    return false;
                }
                float x = motionEvent.getX();
                a aVar = webContentsAccessibilityImpl.f;
                return N.Mx2ry6ai(webContentsAccessibilityImpl.j, aVar.i().b() + x, aVar.i().getScrollY() + motionEvent.getY());
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        y12 y12Var = (y12) this.g;
        boolean zIsIdentity = y12Var.n.isIdentity();
        e22 e22Var = y12Var.i;
        if (!zIsIdentity) {
            e22Var.getClass();
            e22Var.a = new Size(width, height);
            if (e22Var.e) {
                return;
            }
            e22Var.f = new Size(Math.min(width, 2500), Math.min(Math.round(height / 2.0f), 2500));
            return;
        }
        boolean z2 = y12Var.j;
        Size size = y12Var.b;
        if (!z2) {
            float width2 = width / size.getWidth();
            y12Var.m = width2;
            if (y12Var.l == 0.0f) {
                y12Var.l = width2;
            }
            for (int i5 = 0; i5 < y12Var.c.size(); i5++) {
                ((y12) y12Var.e.get(i5)).e(y12Var.l);
            }
            y12Var.h(new Size(width, Math.round(height / 2.0f)));
        }
        float fB = e22Var.b();
        if (fB == 0.0f) {
            fB = y12Var.l;
        }
        if (width > 0 && height > 0) {
            e22Var.g(Math.max(0, Math.min(Math.round(e22Var.c()), Math.round(size.getWidth() * fB) - width)), Math.max(0, Math.min(Math.round(e22Var.d()), Math.round(size.getHeight() * fB) - height)));
            e22Var.a = new Size(width, height);
            if (!e22Var.e) {
                e22Var.f = new Size(Math.min(width, 2500), Math.min(Math.round(height / 2.0f), 2500));
            }
            float fB2 = e22Var.b();
            e22Var.f(fB);
            y12Var.j(fB2 != fB);
        }
        Runnable runnable = y12Var.r;
        if (runnable != null) {
            runnable.run();
            y12Var.r = null;
        }
    }

    @Override // android.view.View
    public final void onProvideVirtualStructure(ViewStructure viewStructure) {
        kq3 kq3Var = this.l;
        if (kq3Var != null) {
            ((WebContentsAccessibilityImpl) kq3Var).s(viewStructure);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.f.a(motionEvent);
    }

    public void setWebContentsAccessibility(kq3 kq3Var) {
        this.l = kq3Var;
    }
}
