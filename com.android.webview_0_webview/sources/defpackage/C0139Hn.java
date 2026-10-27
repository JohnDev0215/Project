package defpackage;

import J.N;
import android.content.ClipDescription;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ZoomButtonsController;
import java.util.Objects;
import org.chromium.android_webview.AwAutofillClient;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.gfx.AwGLFunctor;
import org.chromium.base.BuildInfo;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.device.gamepad.GamepadList;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.EventForwarder;

/* JADX INFO: renamed from: Hn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0139Hn implements InterfaceC1840yp {
    public ComponentCallbacks2 b;
    public final /* synthetic */ AwContents d;
    public int a = 0;
    public final Rect c = new Rect();

    public C0139Hn(AwContents awContents, C1838yn c1838yn) {
        this.d = awContents;
    }

    @Override // defpackage.InterfaceC1840yp
    public void a(int i, int i2, int i3, int i4) {
        C1347pq c1347pq = this.d.T;
        if (c1347pq.b) {
            c1347pq.b = false;
            c1347pq.a.removeMessages(1);
        }
        this.d.P.f(i, i2);
    }

    @Override // defpackage.InterfaceC1840yp
    public void b() {
        boolean z;
        if (this.d.t(0) || this.d.u.isInTouchMode()) {
            return;
        }
        AwSettings awSettings = this.d.S;
        synchronized (awSettings.f) {
            z = awSettings.e0;
        }
        if (z) {
            AwContents awContents = this.d;
            N.MunvchpP(awContents.s, awContents);
        }
    }

    @Override // defpackage.InterfaceC1840yp
    public void c(int i, int i2, boolean z, boolean z2) {
        int scrollX = this.d.u.getScrollX();
        int scrollY = this.d.u.getScrollY();
        C0463Zo c0463Zo = this.d.P;
        ((C0121Gn) c0463Zo.a).a.L.h(c0463Zo.b(i), c0463Zo.c(i2));
        c0463Zo.f(((C0121Gn) c0463Zo.a).a(), ((C0121Gn) c0463Zo.a).b());
        AwContents awContents = this.d;
        C0904hq c0904hq = awContents.Q;
        if (c0904hq != null) {
            awContents.u.getScrollX();
            this.d.u.getScrollY();
            C0463Zo c0463Zo2 = this.d.P;
            int i3 = c0463Zo2.d;
            int i4 = c0463Zo2.e;
            if (c0904hq.h && scrollX == c0904hq.a.getScrollX() && scrollY == c0904hq.a.getScrollY()) {
                if (i3 > 0) {
                    int i5 = c0904hq.f;
                    int i6 = scrollX + i5;
                    if (i6 < 0) {
                        c0904hq.d.onPull(i5 / c0904hq.a.getWidth());
                        if (!c0904hq.e.isFinished()) {
                            c0904hq.e.onRelease();
                        }
                    } else if (i6 > i3) {
                        c0904hq.e.onPull(i5 / c0904hq.a.getWidth());
                        if (!c0904hq.d.isFinished()) {
                            c0904hq.d.onRelease();
                        }
                    }
                    c0904hq.f = 0;
                }
                if (i4 > 0 || c0904hq.a.getOverScrollMode() == 0) {
                    int i7 = c0904hq.g;
                    int i8 = scrollY + i7;
                    if (i8 < 0) {
                        c0904hq.b.onPull(i7 / c0904hq.a.getHeight());
                        if (!c0904hq.c.isFinished()) {
                            c0904hq.c.onRelease();
                        }
                    } else if (i8 > i4) {
                        c0904hq.c.onPull(i7 / c0904hq.a.getHeight());
                        if (!c0904hq.b.isFinished()) {
                            c0904hq.b.onRelease();
                        }
                    }
                    c0904hq.g = 0;
                }
            }
        }
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeHorizontalScrollOffset() {
        return ((C0121Gn) this.d.P.a).a();
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeHorizontalScrollRange() {
        C0463Zo c0463Zo = this.d.P;
        return c0463Zo.f + c0463Zo.d;
    }

    @Override // defpackage.InterfaceC1840yp
    public void computeScroll() {
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        N.MkR1ETvU(awContents.s, awContents, AnimationUtils.currentAnimationTimeMillis());
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollExtent() {
        return this.d.P.g;
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollOffset() {
        return ((C0121Gn) this.d.P.a).b();
    }

    @Override // defpackage.InterfaceC1840yp
    public int computeVerticalScrollRange() {
        return this.d.P.d();
    }

    public final void d() {
        int i;
        AwContents awContents = this.d;
        AwSettings awSettings = awContents.S;
        boolean z = awContents.Y && awContents.u.isHardwareAccelerated() && ((i = this.a) == 0 || i == 2);
        synchronized (awSettings.f) {
            if (awSettings.M != z) {
                awSettings.M = z;
                awSettings.m0.b();
            }
        }
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.d.t(0)) {
            return false;
        }
        if (AwContents.u(keyEvent)) {
            this.d.S.M(true);
        }
        if (GamepadList.a(keyEvent)) {
            return true;
        }
        if (((U8) this.d.E).h != T8.g) {
            C0252Ob c0252Ob = (C0252Ob) this.d.E;
            Objects.requireNonNull(c0252Ob);
            try {
                TraceEvent.x("WebViewContentsClientAdapter.shouldOverrideKeyEvent", null);
                if (c0252Ob.h.shouldOverrideKeyEvent(c0252Ob.e, keyEvent)) {
                    return this.d.L.b(keyEvent);
                }
            } finally {
                TraceEvent.A("WebViewContentsClientAdapter.shouldOverrideKeyEvent");
            }
        }
        EventForwarder eventForwarderF = this.d.A.F();
        long j = eventForwarderF.b;
        if (j == 0) {
            return false;
        }
        return N.MZE$0qqv(j, eventForwarderF, keyEvent);
    }

    @Override // defpackage.InterfaceC1840yp
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        InterfaceC0817gE interfaceC0817gER;
        if (this.d.t(0) || (interfaceC0817gER = this.d.r()) == null) {
            return null;
        }
        return ((WebContentsAccessibilityImpl) interfaceC0817gER).k();
    }

    @Override // defpackage.InterfaceC1840yp
    public void onAttachedToWindow() {
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        if (awContents.Y) {
            Ts.f("AwContents", "onAttachedToWindow called when already attached. Ignoring", new Object[0]);
            return;
        }
        awContents.Y = true;
        ((Wz) awContents.B).b();
        AwContents awContents2 = this.d;
        N.MtQhJq6u(awContents2.s, awContents2, awContents2.u.getWidth(), this.d.u.getHeight());
        d();
        this.d.J();
        this.d.a0();
        if (this.b != null) {
            return;
        }
        ComponentCallbacks2C0031Bn componentCallbacks2C0031Bn = new ComponentCallbacks2C0031Bn(this.d, null);
        this.b = componentCallbacks2C0031Bn;
        this.d.w.registerComponentCallbacks(componentCallbacks2C0031Bn);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onCheckIsTextEditor() {
        ImeAdapterImpl imeAdapterImplP;
        if (this.d.t(0) || (imeAdapterImplP = ImeAdapterImpl.P(this.d.A)) == null) {
            return false;
        }
        return imeAdapterImplP.T();
    }

    @Override // defpackage.InterfaceC1840yp
    public void onConfigurationChanged(Configuration configuration) {
        if (this.d.t(0)) {
            return;
        }
        ((Wz) this.d.B).c(configuration);
        this.d.L.g(configuration);
    }

    @Override // defpackage.InterfaceC1840yp
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (this.d.t(0)) {
            return null;
        }
        return ImeAdapterImpl.P(this.d.A).U(editorInfo);
    }

    @Override // defpackage.InterfaceC1840yp
    public void onDetachedFromWindow() {
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        if (!awContents.Y) {
            Ts.f("AwContents", "onDetachedFromWindow called when already detached. Ignoring", new Object[0]);
            return;
        }
        awContents.Y = false;
        AwAutofillClient awAutofillClient = awContents.s0;
        if (awAutofillClient != null) {
            awAutofillClient.hideAutofillPopup();
        }
        AwContents awContents2 = this.d;
        N.MqVX7tnS(awContents2.s, awContents2);
        ((Wz) this.d.B).d();
        d();
        this.d.J();
        AwContents.b(this.d, null);
        ComponentCallbacks2 componentCallbacks2 = this.b;
        if (componentCallbacks2 != null) {
            this.d.w.unregisterComponentCallbacks(componentCallbacks2);
            this.b = null;
        }
        C1347pq c1347pq = this.d.T;
        if (c1347pq.b) {
            c1347pq.b = false;
            c1347pq.a.removeMessages(1);
        }
        ZoomButtonsController zoomButtonsControllerA = this.d.O.a();
        if (zoomButtonsControllerA != null) {
            zoomButtonsControllerA.setVisible(false);
        }
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onDragEvent(DragEvent dragEvent) {
        if (this.d.t(0)) {
            return false;
        }
        return this.d.A.F().d(dragEvent, this.d.u);
    }

    @Override // defpackage.InterfaceC1840yp
    public void onDraw(Canvas canvas) {
        boolean zDraw;
        InterfaceC0286Pr awGLFunctor;
        if (this.d.t(0)) {
            TraceEvent.G("EarlyOut_destroyed");
            canvas.drawColor(this.d.n());
            return;
        }
        if (!canvas.isHardwareAccelerated() && !canvas.getClipBounds(this.c)) {
            TraceEvent.G("EarlyOut_software_empty_clip");
            return;
        }
        if (canvas.isHardwareAccelerated()) {
            AwContents awContents = this.d;
            if (awContents.v == null) {
                C1222nb c1222nb = (C1222nb) awContents.M;
                Objects.requireNonNull(c1222nb);
                InterfaceC0555bc interfaceC0555bc = BuildInfo.a() ? c1222nb.a.A.d : null;
                if (interfaceC0555bc != null) {
                    awGLFunctor = new C0268Or(interfaceC0555bc);
                } else {
                    AwContents awContents2 = this.d;
                    awGLFunctor = new AwGLFunctor(awContents2.M, awContents2.u);
                }
                AwContents.b(this.d, awGLFunctor);
            }
        }
        C0463Zo c0463Zo = this.d.P;
        c0463Zo.f(((C0121Gn) c0463Zo.a).a(), ((C0121Gn) c0463Zo.a).b());
        int scrollX = this.d.u.getScrollX();
        int scrollY = this.d.u.getScrollY();
        if (!this.d.u.getGlobalVisibleRect(AwContents.M0)) {
            AwContents.M0.setEmpty();
        }
        Rect rect = AwContents.M0;
        int i = Build.VERSION.SDK_INT;
        if (i == 24 || i == 25) {
            AwContents awContents3 = this.d;
            if (awContents3.A0 == null) {
                awContents3.A0 = new Paint();
                this.d.A0.setColor(Color.argb(1, 0, 0, 0));
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setScale(0.0f, 0.0f, 0.0f, 0.1f);
                this.d.A0.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            }
            canvas.drawRect(0.0f, 0.0f, 1.0f, 1.0f, this.d.A0);
        }
        AwContents awContents4 = this.d;
        boolean zMTXp4fn_ = N.MTXp4fn_(awContents4.s, awContents4, canvas, canvas.isHardwareAccelerated(), scrollX, scrollY, rect.left, rect.top, rect.right, rect.bottom, AbstractC0193Kn.a);
        if (canvas.isHardwareAccelerated()) {
            AwContents awContents5 = this.d;
            if (N.Mcwwaem4(awContents5.s, awContents5)) {
                TraceEvent.G("DrawBackgroundColor");
                canvas.drawColor(this.d.n());
            }
        }
        if (zMTXp4fn_ && canvas.isHardwareAccelerated() && !AbstractC0193Kn.a) {
            zMTXp4fn_ = this.d.v.c(canvas);
        }
        if (zMTXp4fn_) {
            canvas.translate(-(this.d.u.getScrollX() - scrollX), -(this.d.u.getScrollY() - scrollY));
        } else {
            TraceEvent.G("NativeDrawFailed");
            canvas.drawColor(this.d.n());
        }
        AwContents awContents6 = this.d;
        C0904hq c0904hq = awContents6.Q;
        if (c0904hq != null) {
            C0463Zo c0463Zo2 = awContents6.P;
            int i2 = c0463Zo2.d;
            int i3 = c0463Zo2.e;
            int scrollX2 = c0904hq.a.getScrollX();
            int scrollY2 = c0904hq.a.getScrollY();
            int width = c0904hq.a.getWidth();
            int height = c0904hq.a.getHeight();
            if (c0904hq.b.isFinished()) {
                zDraw = false;
            } else {
                int iSave = canvas.save();
                canvas.translate(scrollX2, Math.min(0, scrollY2));
                c0904hq.b.setSize(width, height);
                zDraw = c0904hq.b.draw(canvas) | false;
                canvas.restoreToCount(iSave);
            }
            if (!c0904hq.c.isFinished()) {
                int iSave2 = canvas.save();
                canvas.translate((-width) + scrollX2, Math.max(i3, scrollY2) + height);
                canvas.rotate(180.0f, width, 0.0f);
                c0904hq.c.setSize(width, height);
                zDraw |= c0904hq.c.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
            if (!c0904hq.d.isFinished()) {
                int iSave3 = canvas.save();
                canvas.rotate(270.0f);
                canvas.translate((-height) - scrollY2, Math.min(0, scrollX2));
                c0904hq.d.setSize(height, width);
                zDraw |= c0904hq.d.draw(canvas);
                canvas.restoreToCount(iSave3);
            }
            if (!c0904hq.e.isFinished()) {
                int iSave4 = canvas.save();
                canvas.rotate(90.0f);
                canvas.translate(scrollY2, -(Math.max(scrollX2, i2) + width));
                c0904hq.e.setSize(height, width);
                zDraw |= c0904hq.e.draw(canvas);
                canvas.restoreToCount(iSave4);
            }
            if (zDraw) {
                this.d.postInvalidateOnAnimation();
            }
        }
        AwContents awContents7 = this.d;
        if (awContents7.w0) {
            awContents7.u.getRootView().invalidate();
            this.d.w0 = false;
        }
        if (Es.e().h("highlight-all-webviews")) {
            canvas.drawColor(Color.argb(80, 252, 252, 109));
        }
    }

    @Override // defpackage.InterfaceC1840yp
    public void onFocusChanged(boolean z, int i, Rect rect) {
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        awContents.l0 = z;
        ((Wz) awContents.B).f(z);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.d.t(0)) {
            return false;
        }
        return this.d.A.F().e(motionEvent);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.d.t(0)) {
            return false;
        }
        return this.d.A.F().f(motionEvent);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.d.t(0)) {
            return false;
        }
        EventForwarder eventForwarderF = this.d.A.F();
        long j = eventForwarderF.b;
        if (j == 0) {
            return false;
        }
        return N.MRbfSEI1(j, eventForwarderF, keyEvent, i);
    }

    @Override // defpackage.InterfaceC1840yp
    public void onMeasure(int i, int i2) {
        C0265Oo c0265Oo = this.d.N;
        Objects.requireNonNull(c0265Oo);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i);
        float f = c0265Oo.c;
        float f2 = c0265Oo.e;
        double d = c0265Oo.h;
        int i3 = (int) (((double) (f * f2)) * d);
        int i4 = (int) (((double) (c0265Oo.d * f2)) * d);
        boolean z = false;
        c0265Oo.a = mode2 != 0;
        c0265Oo.b = mode == 1073741824;
        if (mode == Integer.MIN_VALUE && i3 > size) {
            z = true;
        }
        c0265Oo.i = z;
        c0265Oo.j = size;
        if (!c0265Oo.b && !z) {
            size = i3;
        }
        if (!c0265Oo.a) {
            size2 = i4;
        }
        if (size < i3) {
            size |= 16777216;
        }
        if (size2 < i4) {
            size2 |= 16777216;
        }
        ((C0103Fn) c0265Oo.k).a.L.setMeasuredDimension(size2, size);
    }

    @Override // defpackage.InterfaceC1840yp
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        C0463Zo c0463Zo = awContents.P;
        c0463Zo.f = i;
        c0463Zo.g = i2;
        awContents.N.b();
        AwContents awContents2 = this.d;
        N.MNxSIAOu(awContents2.s, awContents2, i, i2, i3, i4);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d.t(0)) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.d.S.M(false);
        }
        AwContents awContents = this.d;
        N.MQn4UPnT(awContents.s, awContents);
        AwContents awContents2 = this.d;
        awContents2.P.h = true;
        boolean zG = awContents2.A.F().g(motionEvent);
        C0463Zo c0463Zo = this.d.P;
        c0463Zo.h = false;
        if (c0463Zo.i) {
            c0463Zo.i = false;
            c0463Zo.f(c0463Zo.j, c0463Zo.k);
        }
        if (motionEvent.getActionMasked() == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            float fMax = Math.max(motionEvent.getTouchMajor(), motionEvent.getTouchMinor());
            if (!N.MbmAQ8fm()) {
                float fM = this.d.m();
                x /= fM;
                y /= fM;
                fMax /= fM;
            }
            AwContents awContents3 = this.d;
            N.MbVTYFDu(awContents3.s, awContents3, x, y, fMax);
        }
        if (this.d.Q != null) {
            if (motionEvent.getActionMasked() == 0) {
                this.d.Q.h = true;
            } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                C0904hq c0904hq = this.d.Q;
                c0904hq.h = false;
                c0904hq.b.onRelease();
                c0904hq.c.onRelease();
                c0904hq.d.onRelease();
                c0904hq.e.onRelease();
            }
        }
        return zG;
    }

    @Override // defpackage.InterfaceC1840yp
    public void onVisibilityChanged(View view, int i) {
        boolean z = this.d.u.getVisibility() == 0;
        AwContents awContents = this.d;
        if (awContents.W == z) {
            return;
        }
        awContents.W(z);
    }

    @Override // defpackage.InterfaceC1840yp
    public void onWindowFocusChanged(boolean z) {
        ClipDescription primaryClipDescription;
        if (this.d.t(0)) {
            return;
        }
        AwContents awContents = this.d;
        awContents.m0 = z;
        ((Wz) awContents.B).g(z);
        Clipboard clipboard = Clipboard.getInstance();
        if (clipboard.c == 0 || !z || !BuildInfo.a() || (primaryClipDescription = clipboard.b.getPrimaryClipDescription()) == null) {
            return;
        }
        N.MWrNP8sy(clipboard.c, clipboard, C1295ot.a(primaryClipDescription));
    }

    @Override // defpackage.InterfaceC1840yp
    public void onWindowVisibilityChanged(int i) {
        boolean z = i == 0;
        AwContents awContents = this.d;
        if (awContents.X == z) {
            return;
        }
        awContents.X(z);
    }

    @Override // defpackage.InterfaceC1840yp
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (this.d.t(0)) {
            return false;
        }
        this.d.r();
        return false;
    }

    @Override // defpackage.InterfaceC1840yp
    public void setLayerType(int i, Paint paint) {
        this.a = i;
        d();
    }
}
