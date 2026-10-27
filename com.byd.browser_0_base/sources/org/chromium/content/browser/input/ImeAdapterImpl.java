package org.chromium.content.browser.input;

import J.N;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.SuggestionSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import defpackage.bt;
import defpackage.f93;
import defpackage.h01;
import defpackage.i01;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.kx0;
import defpackage.l93;
import defpackage.lk3;
import defpackage.pd;
import defpackage.ss1;
import defpackage.xv2;
import defpackage.xz;
import defpackage.yv2;
import defpackage.z7;
import defpackage.zv2;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class ImeAdapterImpl implements jt3, lk3, h01.a {
    public boolean A;
    public a B;
    public boolean D;
    public boolean E;
    public long e;
    public h01 f;
    public bt g;
    public l93 h;
    public ShowKeyboardResultReceiver i;
    public final WebContentsImpl j;
    public final ViewAndroidDelegate k;
    public final org.chromium.content.browser.input.b l;
    public int o;
    public boolean r;
    public boolean s;
    public Configuration u;
    public int v;
    public int w;
    public String x;
    public int y;
    public int z;
    public final ArrayList m = new ArrayList();
    public int n = 0;
    public int p = 0;
    public int q = 0;
    public final Rect t = new Rect();
    public final SparseArray<ss1> C = new SparseArray<>();

    public static class ShowKeyboardResultReceiver extends ResultReceiver {
        public final WeakReference<ImeAdapterImpl> e;

        public ShowKeyboardResultReceiver(ImeAdapterImpl imeAdapterImpl, Handler handler) {
            super(handler);
            this.e = new WeakReference<>(imeAdapterImpl);
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            ImeAdapterImpl imeAdapterImpl = this.e.get();
            if (imeAdapterImpl != null && imeAdapterImpl.g()) {
                View viewD = imeAdapterImpl.d();
                if (i == 2) {
                    viewD.getWindowVisibleDisplayFrame(imeAdapterImpl.t);
                    return;
                }
                if ((!(viewD.isInTouchMode() ? viewD.isFocusableInTouchMode() : viewD.isFocusable()) ? true : viewD.hasFocus()) && i == 0) {
                    WebContentsImpl webContentsImpl = imeAdapterImpl.j;
                    webContentsImpl.R();
                    N.MgbVQff0(webContentsImpl.f);
                }
            }
        }
    }

    public class a implements zv2 {
        public a() {
        }

        public final View a() {
            return ImeAdapterImpl.this.j.u().getContainerView();
        }

        public final void b(int i, xv2 xv2Var) {
            ImeAdapterImpl imeAdapterImpl = ImeAdapterImpl.this;
            long j = imeAdapterImpl.e;
            if (j == 0) {
                return;
            }
            int i2 = (int) imeAdapterImpl.j.l.k;
            xv2Var.d.c -= i2;
            org.chromium.gfx.mojom.Rect rect = xv2Var.e;
            if (rect != null) {
                rect.c -= i2;
            }
            N.M3wL9MnJ(j, imeAdapterImpl, i, xv2Var.b());
        }
    }

    public static final class b {
        public static final pd a = new pd(23);
    }

    public ImeAdapterImpl(WebContentsImpl webContentsImpl) {
        this.j = webContentsImpl;
        this.k = webContentsImpl.u();
        i01 i01Var = new i01(xz.a, webContentsImpl.V(), this);
        this.u = new Configuration(d().getResources().getConfiguration());
        this.l = new org.chromium.content.browser.input.b(i01Var, new c(this), new org.chromium.content.browser.input.a());
        this.f = i01Var;
        this.e = N.MhbsQh1H(this, webContentsImpl);
        kt3.e(webContentsImpl).b(this);
    }

    public static ImeAdapterImpl b(WebContents webContents) {
        return (ImeAdapterImpl) ((WebContentsImpl) webContents).n0(ImeAdapterImpl.class, b.a);
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        h01 h01Var = this.f;
        if (h01Var != null) {
            h01Var.a(windowAndroid);
        }
    }

    @CalledByNative
    public final void cancelComposition() {
        if (this.g != null) {
            k();
        }
    }

    public final View d() {
        return this.k.getContainerView();
    }

    public final void e() {
        bt btVar;
        if (g()) {
            View containerView = this.k.getContainerView();
            if (this.f.d(containerView)) {
                this.f.b(containerView.getWindowToken());
            }
            if ((this.n != 0) || (btVar = this.g) == null) {
                return;
            }
            k();
            f93 f93Var = (f93) btVar;
            z7.c();
            f93.j jVar = f93.l;
            z7.c();
            try {
                f93Var.g.put(jVar);
            } catch (InterruptedException e) {
                Log.e("cr_Ime", "addToQueueOnUiThread interrupted", e);
            }
            f93Var.e.post(f93Var.a);
        }
    }

    @Override // defpackage.jt3
    public final void f(boolean z, boolean z2) {
        l93.a aVar;
        if (!z && z2) {
            j();
        }
        l93 l93Var = this.h;
        if (l93Var != null) {
            if (!z && (aVar = l93Var.d) != null) {
                z7.c();
                aVar.a = true;
            }
            ThreadedInputConnectionProxyView threadedInputConnectionProxyView = l93Var.b;
            if (threadedInputConnectionProxyView != null) {
                threadedInputConnectionProxyView.g.set(z);
            }
            if (l93Var.g != 1) {
                l93Var.g = 0;
            } else if (z) {
                l93Var.g = 2;
            }
        }
    }

    @CalledByNative
    public final void focusedNodeChanged(boolean z, int i, int i2, int i3, int i4) {
        Rect rect;
        org.chromium.content.browser.input.b bVar = this.l;
        if (bVar != null) {
            bVar.a = z;
            bVar.d = null;
            bVar.e = false;
            bVar.n = null;
        }
        if (this.n != 0 && this.g != null && z) {
            this.A = true;
        }
        WebContentsImpl webContentsImpl = this.j;
        if (webContentsImpl.k != null) {
            if (z) {
                float f = webContentsImpl.l.j;
                rect = new Rect((int) (i * f), (int) (i2 * f), (int) (i3 * f), (int) (i4 * f));
                rect.offset(0, (int) Math.floor(webContentsImpl.l.k));
            } else {
                rect = new Rect();
            }
            webContentsImpl.k.e(this.k.getContainerView(), rect, z);
        }
    }

    public final boolean g() {
        return this.e != 0 && this.D;
    }

    public final void h() {
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            ((kx0) it.next()).e();
        }
        if (this.r) {
            WebContentsImpl webContentsImpl = this.j;
            if (webContentsImpl.b0() != null) {
                RenderWidgetHostViewImpl renderWidgetHostViewImplO0 = webContentsImpl.b0();
                long j = renderWidgetHostViewImplO0.a;
                if (j == 0) {
                    return;
                }
                N.MQWja$xA(j, renderWidgetHostViewImplO0);
            }
        }
    }

    public final boolean i(int i) {
        if (!g()) {
            return false;
        }
        if (this.q == 0) {
            if (i == 5) {
                long j = this.e;
                if (j != 0) {
                    N.MrkJlyAt(j, this, 2);
                }
                return true;
            }
            if (i == 7) {
                long j2 = this.e;
                if (j2 != 0) {
                    N.MrkJlyAt(j2, this, 3);
                }
                return true;
            }
        }
        n(22);
        return true;
    }

    public final void j() {
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.A = false;
        e();
    }

    public final void k() {
        if (g()) {
            this.f.i(d());
            bt btVar = this.g;
            if (btVar != null) {
                btVar.getClass();
            }
        }
    }

    public final void l(CharSequence charSequence, int i, boolean z, int i2) {
        if (g()) {
            h();
            long jUptimeMillis = SystemClock.uptimeMillis();
            N.M1qwlrOP(this.e, this, null, 7, 0, jUptimeMillis, 229, 0, false, i2);
            if (z) {
                N.Mb6t43di(this.e, this, charSequence, charSequence.toString(), i);
            } else {
                N.Mlslst_P(this.e, this, charSequence, charSequence.toString(), i);
            }
            N.M1qwlrOP(this.e, this, null, 9, 0, jUptimeMillis, 229, 0, false, i2);
        }
    }

    public final boolean m(KeyEvent keyEvent) {
        int i;
        if (!g()) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            i = 8;
        } else {
            if (action != 1) {
                return false;
            }
            i = 9;
        }
        int i2 = i;
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            ((kx0) it.next()).getClass();
        }
        h();
        long j = this.e;
        int metaState = keyEvent.getMetaState();
        int i3 = (metaState & 1) != 0 ? 1 : 0;
        if ((metaState & 2) != 0) {
            i3 |= 4;
        }
        if ((metaState & 4096) != 0) {
            i3 |= 2;
        }
        if ((1048576 & metaState) != 0) {
            i3 |= 512;
        }
        return N.M1qwlrOP(j, this, keyEvent, i2, (metaState & 2097152) != 0 ? i3 | 1024 : i3, keyEvent.getEventTime(), keyEvent.getKeyCode(), keyEvent.getScanCode(), false, keyEvent.getUnicodeChar());
    }

    public final void n(int i) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        m(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, 66, 0, 0, -1, 0, i));
        m(new KeyEvent(jUptimeMillis, jUptimeMillis, 1, 66, 0, 0, -1, 0, i));
    }

    public final void o() {
        if (g()) {
            View viewD = d();
            WebContentsImpl webContentsImpl = this.j;
            int i = webContentsImpl.H().f;
            yv2 yv2Var = webContentsImpl.k;
            if (yv2Var == null || yv2Var.a() || (!(i == 2 || i == 4) || this.n == 2 || this.E)) {
                h01 h01Var = this.f;
                if (this.i == null) {
                    this.i = new ShowKeyboardResultReceiver(this, new Handler());
                }
                h01Var.e(viewD, 0, this.i);
                if (viewD.getResources().getConfiguration().keyboard != 1) {
                    webContentsImpl.R();
                    N.MgbVQff0(webContentsImpl.f);
                }
            }
        }
    }

    @Override // defpackage.jt3
    public final void onAttachedToWindow() {
        ThreadedInputConnectionProxyView threadedInputConnectionProxyView;
        l93 l93Var = this.h;
        if (l93Var == null || (threadedInputConnectionProxyView = l93Var.b) == null) {
            return;
        }
        AtomicReference<IBinder> atomicReference = threadedInputConnectionProxyView.i;
        View view = threadedInputConnectionProxyView.f;
        atomicReference.set(view.getWindowToken());
        threadedInputConnectionProxyView.j.set(view.getRootView());
    }

    @Override // defpackage.jt3
    public final void onConfigurationChanged(Configuration configuration) {
        if (g()) {
            Configuration configuration2 = this.u;
            if (configuration2.keyboard == configuration.keyboard && configuration2.keyboardHidden == configuration.keyboardHidden && configuration2.hardKeyboardHidden == configuration.hardKeyboardHidden) {
                return;
            }
            this.u = new Configuration(configuration);
            int i = this.n;
            if ((i == 0 || this.p == 1) ? false : true) {
                k();
                o();
                return;
            }
            if (i != 0) {
                k();
                if (this.u.keyboard != 1) {
                    o();
                } else {
                    e();
                }
            }
        }
    }

    @CalledByNative
    public final void onConnectedToRenderProcess() {
        this.D = true;
        if (this.h == null) {
            this.h = new l93(this.f);
        }
        j();
    }

    @Override // defpackage.jt3
    public final void onDetachedFromWindow() {
        j();
        l93 l93Var = this.h;
        if (l93Var != null) {
            l93.a aVar = l93Var.d;
            if (aVar != null) {
                z7.c();
                aVar.a = true;
            }
            ThreadedInputConnectionProxyView threadedInputConnectionProxyView = l93Var.b;
            if (threadedInputConnectionProxyView != null) {
                threadedInputConnectionProxyView.i.set(null);
                threadedInputConnectionProxyView.j.set(null);
            }
            l93Var.c = null;
        }
    }

    @CalledByNative
    public void onEditElementFocusedForStylusWriting(int i, int i2, int i3, int i4, int i5, int i6) {
        WebContentsImpl webContentsImpl = this.j;
        if (webContentsImpl.k == null) {
            return;
        }
        Rect rect = new Rect(i, i2, i3, i4);
        Point point = new Point(i5, i6);
        if (!rect.isEmpty()) {
            int[] iArr = new int[2];
            webContentsImpl.u().getContainerView().getLocationOnScreen(iArr);
            int iFloor = (int) Math.floor(webContentsImpl.l.k);
            rect.offset(0, iFloor);
            point.offset(iArr[0], iArr[1] + iFloor);
        }
        webContentsImpl.k.m(rect, point);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        j();
        this.e = 0L;
        this.D = false;
        org.chromium.content.browser.input.b bVar = this.l;
        if (bVar != null) {
            bVar.a = false;
            bVar.d = null;
            bVar.e = false;
            bVar.n = null;
        }
    }

    @CalledByNative
    public final void onResizeScrollableViewport(boolean z) {
        Rect rect = this.t;
        if (!z) {
            rect.setEmpty();
            return;
        }
        if (rect.isEmpty()) {
            return;
        }
        Rect rect2 = new Rect();
        d().getWindowVisibleDisplayFrame(rect2);
        if (rect2.equals(rect)) {
            return;
        }
        if (rect2.width() == rect.width()) {
            WebContentsImpl webContentsImpl = this.j;
            webContentsImpl.R();
            N.MgbVQff0(webContentsImpl.f);
        }
        rect.setEmpty();
    }

    @CalledByNative
    public final void onStylusWritingGestureActionCompleted(int i, int i2) {
        SparseArray<ss1> sparseArray = this.C;
        if (sparseArray.get(i) != null) {
            sparseArray.get(i).a(i2);
            sparseArray.remove(i);
        }
    }

    @Override // defpackage.jt3
    public final void onWindowFocusChanged(boolean z) {
        l93.a aVar;
        l93 l93Var = this.h;
        if (l93Var != null) {
            if (!z && (aVar = l93Var.d) != null) {
                z7.c();
                aVar.a = true;
            }
            ThreadedInputConnectionProxyView threadedInputConnectionProxyView = l93Var.b;
            if (threadedInputConnectionProxyView != null) {
                threadedInputConnectionProxyView.h.set(z);
            }
            if (!z) {
                l93Var.g = 1;
            } else if (z && l93Var.g == 2) {
                l93Var.g = 3;
            } else {
                l93Var.g = 0;
            }
        }
    }

    @CalledByNative
    public final void populateImeTextSpansFromJava(CharSequence charSequence, long j) {
        int iIntValue;
        if (charSequence instanceof SpannableString) {
            SpannableString spannableString = (SpannableString) charSequence;
            for (CharacterStyle characterStyle : (CharacterStyle[]) spannableString.getSpans(0, charSequence.length(), CharacterStyle.class)) {
                int spanFlags = spannableString.getSpanFlags(characterStyle);
                if (characterStyle instanceof BackgroundColorSpan) {
                    N.MqqhDONa(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle), ((BackgroundColorSpan) characterStyle).getBackgroundColor());
                } else if (characterStyle instanceof UnderlineSpan) {
                    N.MFfRzF$Z(j, spannableString.getSpanStart(characterStyle), spannableString.getSpanEnd(characterStyle));
                } else if (characterStyle instanceof SuggestionSpan) {
                    SuggestionSpan suggestionSpan = (SuggestionSpan) characterStyle;
                    boolean z = (spanFlags & 256) != 0;
                    boolean z2 = (suggestionSpan.getFlags() & 1) != 0;
                    boolean z3 = (suggestionSpan.getFlags() & 2) != 0;
                    boolean z4 = (suggestionSpan.getFlags() & 4) != 0;
                    if (z2 || z3 || z4) {
                        try {
                            iIntValue = ((Integer) SuggestionSpan.class.getMethod("getUnderlineColor", new Class[0]).invoke(suggestionSpan, new Object[0])).intValue();
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                            iIntValue = -2000107320;
                        }
                        int i = iIntValue;
                        N.M$b45Vvn(j, spannableString.getSpanStart(suggestionSpan), spannableString.getSpanEnd(suggestionSpan), z3 || z4, z, i, (16777215 & i) + (((int) (Color.alpha(i) * 0.4f)) << 24), z4 ? new String[0] : suggestionSpan.getSuggestions());
                    }
                }
            }
        }
    }

    @CalledByNative
    public final boolean requestStartStylusWriting() {
        WebContentsImpl webContentsImpl = this.j;
        if (webContentsImpl.k == null) {
            return false;
        }
        View viewD = d();
        if (!(!(viewD.isInTouchMode() ? viewD.isFocusableInTouchMode() : viewD.isFocusable()) ? true : viewD.hasFocus())) {
            if ((viewD.isInTouchMode() ? viewD.isFocusableInTouchMode() : viewD.isFocusable()) && !viewD.isFocused()) {
                viewD.requestFocus();
            }
        }
        yv2 yv2Var = webContentsImpl.k;
        if (yv2Var != null) {
            yv2Var.j(this.v, this.w, this.x);
        }
        a aVar = new a();
        this.B = aVar;
        return webContentsImpl.k.i(aVar);
    }

    @CalledByNative
    public final void setCharacterBounds(float[] fArr) {
        View viewD = d();
        org.chromium.content.browser.input.b bVar = this.l;
        if (bVar.a && !Arrays.equals(fArr, bVar.d)) {
            bVar.n = null;
            bVar.d = fArr;
            if (bVar.e) {
                bVar.a(viewD);
            }
        }
    }

    @CalledByNative
    public final void updateFrameInfo(float f, float f2, boolean z, boolean z2, float f3, float f4, float f5) {
        View viewD = d();
        org.chromium.content.browser.input.b bVar = this.l;
        if (bVar.a) {
            ((org.chromium.content.browser.input.a) bVar.t).getClass();
            int[] iArr = bVar.p;
            viewD.getLocationOnScreen(iArr);
            float f6 = iArr[0];
            float f7 = iArr[1] + f2;
            if (!bVar.e || f != bVar.f || f6 != bVar.g || f7 != bVar.h || z != bVar.i || z2 != bVar.j || f3 != bVar.k || f4 != bVar.l || f5 != bVar.m) {
                bVar.n = null;
                bVar.e = true;
                bVar.f = f;
                bVar.g = f6;
                bVar.h = f7;
                bVar.i = z;
                bVar.j = z2;
                bVar.k = f3;
                bVar.l = f4;
                bVar.m = f5;
            }
            if (bVar.b || (bVar.c && bVar.n == null)) {
                bVar.a(viewD);
            }
        }
    }

    @CalledByNative
    public final void updateOnTouchDown() {
        this.t.setEmpty();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0040  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateState(int r18, int r19, int r20, int r21, boolean r22, boolean r23, java.lang.String r24, int r25, int r26, int r27, int r28, boolean r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.updateState(int, int, int, int, boolean, boolean, java.lang.String, int, int, int, int, boolean, int, int):void");
    }
}
