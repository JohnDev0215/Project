package org.chromium.content.browser.input;

import J.N;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.SuggestionSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.android.webview.R;
import defpackage.C0590cA;
import defpackage.InterfaceC1074kt;
import defpackage.Is;
import defpackage.KA;
import defpackage.LA;
import defpackage.LD;
import defpackage.MA;
import defpackage.MD;
import defpackage.ND;
import defpackage.OA;
import defpackage.PA;
import defpackage.QB;
import defpackage.RA;
import defpackage.TA;
import defpackage.UB;
import defpackage.WA;
import defpackage.WB;
import defpackage.XN;
import defpackage.Xz;
import defpackage.YB;
import defpackage.Yz;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ImeAdapterImpl extends Xz implements Yz, InterfaceC1074kt, MD {
    public int C;
    public boolean F;
    public boolean G;
    public Configuration I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f30J;
    public int K;
    public String L;
    public int M;
    public int N;
    public boolean O;
    public boolean P;
    public long s;
    public ND t;
    public LA u;
    public KA v;
    public ShowKeyboardResultReceiver w;
    public final WebContentsImpl x;
    public ViewAndroidDelegate y;
    public PA z;
    public final List A = new ArrayList();
    public int B = 0;
    public int D = 0;
    public int E = 0;
    public final Rect H = new Rect();

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class ShowKeyboardResultReceiver extends ResultReceiver {
        public final WeakReference s;

        public ShowKeyboardResultReceiver(ImeAdapterImpl imeAdapterImpl, Handler handler) {
            super(handler);
            this.s = new WeakReference(imeAdapterImpl);
        }

        @Override // android.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            ImeAdapterImpl imeAdapterImpl = (ImeAdapterImpl) this.s.get();
            if (imeAdapterImpl == null) {
                return;
            }
            View viewQ = imeAdapterImpl.Q();
            if (i == 2) {
                viewQ.getWindowVisibleDisplayFrame(imeAdapterImpl.H);
            } else if (XN.a(viewQ) && i == 0) {
                imeAdapterImpl.x.W();
            }
        }
    }

    public ImeAdapterImpl(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.x = webContentsImpl;
        this.y = webContentsImpl.D();
        WA wa = new WA(Is.a, this.x.H(), this);
        this.I = new Configuration(Q().getResources().getConfiguration());
        this.z = new PA(wa, new RA(this), new MA());
        this.t = wa;
        this.s = N.MhbsQh1H(this, this.x);
        C0590cA c0590cAP = C0590cA.P(this.x);
        c0590cAP.s.f(this);
        if (c0590cAP.v) {
            onAttachedToWindow();
        }
    }

    public static ImeAdapterImpl P(WebContents webContents) {
        return (ImeAdapterImpl) ((WebContentsImpl) webContents).S(ImeAdapterImpl.class, TA.a);
    }

    private void cancelComposition() {
        if (this.u != null) {
            Y();
        }
    }

    private void focusedNodeChanged(boolean z) {
        PA pa = this.z;
        if (pa != null) {
            pa.a = z;
            pa.d = null;
            pa.e = false;
            pa.n = null;
        }
        if (this.B == 0 || this.u == null || !z) {
            return;
        }
        this.O = true;
    }

    private void onConnectedToRenderProcess() {
        this.P = true;
        if (this.v == null) {
            this.v = new WB(this.t);
        }
        X();
    }

    private void onNativeDestroyed() {
        X();
        this.s = 0L;
        this.P = false;
        PA pa = this.z;
        if (pa != null) {
            pa.a = false;
            pa.d = null;
            pa.e = false;
            pa.n = null;
        }
    }

    private void onResizeScrollableViewport(boolean z) {
        if (!z) {
            this.H.setEmpty();
            return;
        }
        if (this.H.isEmpty()) {
            return;
        }
        Rect rect = new Rect();
        Q().getWindowVisibleDisplayFrame(rect);
        if (rect.equals(this.H)) {
            return;
        }
        if (rect.width() == this.H.width()) {
            this.x.W();
        }
        this.H.setEmpty();
    }

    private void populateImeTextSpansFromJava(CharSequence charSequence, long j) {
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
                        N.M$b45Vvn(j, spannableString.getSpanStart(suggestionSpan), spannableString.getSpanEnd(suggestionSpan), z3 || z4, z, iIntValue, (16777215 & iIntValue) + (((int) (Color.alpha(iIntValue) * 0.4f)) << 24), z4 ? new String[0] : suggestionSpan.getSuggestions());
                    }
                }
            }
        }
    }

    private void setCharacterBounds(float[] fArr) {
        PA pa = this.z;
        if (pa == null) {
            return;
        }
        View viewQ = Q();
        if (pa.a && !Arrays.equals(fArr, pa.d)) {
            pa.n = null;
            pa.d = fArr;
            if (pa.e) {
                pa.b(viewQ);
            }
        }
    }

    private void updateFrameInfo(float f, float f2, boolean z, boolean z2, float f3, float f4, float f5) {
        PA pa = this.z;
        if (pa == null) {
            return;
        }
        View viewQ = Q();
        if (pa.a) {
            OA oa = pa.t;
            int[] iArr = pa.p;
            Objects.requireNonNull((MA) oa);
            viewQ.getLocationOnScreen(iArr);
            float f6 = pa.p[0];
            float f7 = r2[1] + f2;
            if (!pa.e || f != pa.f || f6 != pa.g || f7 != pa.h || z != pa.i || z2 != pa.j || f3 != pa.k || f4 != pa.l || f5 != pa.m) {
                pa.n = null;
                pa.e = true;
                pa.f = f;
                pa.g = f6;
                pa.h = f7;
                pa.i = z;
                pa.j = z2;
                pa.k = f3;
                pa.l = f4;
                pa.m = f5;
            }
            if (pa.b || (pa.c && pa.n == null)) {
                pa.b(viewQ);
            }
        }
    }

    private void updateOnTouchDown() {
        this.H.setEmpty();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateState(int r17, int r18, int r19, int r20, boolean r21, boolean r22, java.lang.String r23, int r24, int r25, int r26, int r27, boolean r28) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.updateState(int, int, int, int, boolean, boolean, java.lang.String, int, int, int, int, boolean):void");
    }

    public final boolean O() {
        return this.B != 0;
    }

    public final View Q() {
        return this.y.getContainerView();
    }

    public final void R() {
        LA la;
        if (S()) {
            ViewGroup containerView = this.y.getContainerView();
            if (((WA) this.t).c(containerView)) {
                ND nd = this.t;
                IBinder windowToken = containerView.getWindowToken();
                WA wa = (WA) nd;
                wa.d = null;
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                try {
                    InputMethodManager inputMethodManagerB = wa.b();
                    if (inputMethodManagerB != null) {
                        inputMethodManagerB.hideSoftInputFromWindow(windowToken, 0, null);
                    }
                } finally {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                }
            }
            if (O() || (la = this.u) == null) {
                return;
            }
            Y();
            ((QB) la).h();
        }
    }

    public final boolean S() {
        return this.s != 0 && this.P;
    }

    public boolean T() {
        int i = this.B;
        if (i != 0) {
            return !(i == 8 || i == 12 || i == 9 || i == 10 || i == 11 || i == 13);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.inputmethod.InputConnection U(android.view.inputmethod.EditorInfo r19) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.U(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    public final void V() {
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            ((LD) it.next()).c();
        }
        if (!this.F || this.x.T() == null) {
            return;
        }
        this.x.T().a();
    }

    public boolean W(int i) {
        if (!S()) {
            return false;
        }
        if (this.E == 0) {
            if (i == 5) {
                long j = this.s;
                if (j != 0) {
                    N.Mm_z91JF(j, this, 1);
                }
                return true;
            }
            if (i == 7) {
                long j2 = this.s;
                if (j2 != 0) {
                    N.Mm_z91JF(j2, this, 2);
                }
                return true;
            }
        }
        b0(66, 22);
        return true;
    }

    public void X() {
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.O = false;
        R();
    }

    public void Y() {
        if (S()) {
            ND nd = this.t;
            View viewQ = Q();
            InputMethodManager inputMethodManagerB = ((WA) nd).b();
            if (inputMethodManagerB != null) {
                inputMethodManagerB.restartInput(viewQ);
            }
            LA la = this.u;
            if (la != null) {
                Objects.requireNonNull((QB) la);
            }
        }
    }

    public boolean Z(CharSequence charSequence, int i, boolean z, int i2) {
        if (!S()) {
            return false;
        }
        V();
        long jUptimeMillis = SystemClock.uptimeMillis();
        N.M1qwlrOP(this.s, this, null, 7, 0, jUptimeMillis, 229, 0, false, i2);
        if (z) {
            N.Mb6t43di(this.s, this, charSequence, charSequence.toString(), i);
        } else {
            N.Mlslst_P(this.s, this, charSequence, charSequence.toString(), i);
        }
        N.M1qwlrOP(this.s, this, null, 9, 0, jUptimeMillis, 229, 0, false, i2);
        return true;
    }

    public boolean a0(KeyEvent keyEvent) {
        int i;
        if (!S()) {
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
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            ((LD) it.next()).d(keyEvent);
        }
        V();
        long j = this.s;
        int metaState = keyEvent.getMetaState();
        int i2 = (metaState & 1) != 0 ? 1 : 0;
        if ((metaState & 2) != 0) {
            i2 |= 4;
        }
        if ((metaState & 4096) != 0) {
            i2 |= 2;
        }
        if ((R.raw.empty & metaState) != 0) {
            i2 |= 512;
        }
        return N.M1qwlrOP(j, this, keyEvent, i, (metaState & 2097152) != 0 ? i2 | 1024 : i2, keyEvent.getEventTime(), keyEvent.getKeyCode(), keyEvent.getScanCode(), false, keyEvent.getUnicodeChar());
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void b(boolean z, boolean z2) {
        UB ub;
        if (!z && z2) {
            X();
        }
        KA ka = this.v;
        if (ka != null) {
            WB wb = (WB) ka;
            if (!z && (ub = wb.d) != null) {
                ub.a();
            }
            YB yb = wb.b;
            if (yb != null) {
                yb.u.set(z);
            }
        }
    }

    public void b0(int i, int i2) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        a0(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0, 0, -1, 0, i2));
        a0(new KeyEvent(jUptimeMillis, jUptimeMillis, 1, i, 0, 0, -1, 0, i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c0() {
        /*
            r9 = this;
            boolean r0 = r9.S()
            if (r0 != 0) goto L7
            return
        L7:
            android.view.View r0 = r9.Q()
            ND r1 = r9.t
            org.chromium.content.browser.input.ImeAdapterImpl$ShowKeyboardResultReceiver r2 = r9.w
            if (r2 != 0) goto L1d
            org.chromium.content.browser.input.ImeAdapterImpl$ShowKeyboardResultReceiver r2 = new org.chromium.content.browser.input.ImeAdapterImpl$ShowKeyboardResultReceiver
            android.os.Handler r3 = new android.os.Handler
            r3.<init>()
            r2.<init>(r9, r3)
            r9.w = r2
        L1d:
            org.chromium.content.browser.input.ImeAdapterImpl$ShowKeyboardResultReceiver r2 = r9.w
            WA r1 = (defpackage.WA) r1
            r3 = 0
            r1.d = r3
            org.chromium.ui.base.WindowAndroid r3 = r1.b
            android.app.Activity r3 = defpackage.WA.a(r3)
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L7e
            android.content.Context r6 = r1.a
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 26
            if (r7 >= r8) goto L37
            goto L5d
        L37:
            gO r6 = defpackage.AbstractC0827gO.c(r6)
            int r6 = r6.b
            gO r7 = defpackage.AbstractC0827gO.c(r3)
            int r7 = r7.b
            if (r7 == r6) goto L5d
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8[r4] = r7
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8[r5] = r6
            java.lang.String r6 = "IMM"
            java.lang.String r7 = "Activity's display ID(%d) does not match context's display ID(%d). Using a workaround to show soft input on the correct display..."
            defpackage.Ts.f(r6, r7, r8)
            r6 = 0
            goto L5e
        L5d:
            r6 = 1
        L5e:
            if (r6 != 0) goto L7e
            android.view.Window r3 = r3.getWindow()
            r3.setLocalFocus(r5, r5)
            MD r3 = r1.c
            if (r3 == 0) goto L7e
            org.chromium.content.browser.input.ImeAdapterImpl r3 = (org.chromium.content.browser.input.ImeAdapterImpl) r3
            LA r3 = r3.u
            if (r3 == 0) goto L73
            r3 = 1
            goto L74
        L73:
            r3 = 0
        L74:
            if (r3 != 0) goto L7e
            VA r3 = new VA
            r3.<init>(r1, r0, r4, r2)
            r1.d = r3
            goto L81
        L7e:
            r1.d(r0, r4, r2)
        L81:
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.keyboard
            if (r0 == r5) goto L92
            org.chromium.content.browser.webcontents.WebContentsImpl r0 = r9.x
            r0.W()
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.ImeAdapterImpl.c0():void");
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onAttachedToWindow() {
        YB yb;
        KA ka = this.v;
        if (ka == null || (yb = ((WB) ka).b) == null) {
            return;
        }
        yb.w.set(yb.t.getWindowToken());
        yb.x.set(yb.t.getRootView());
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onConfigurationChanged(Configuration configuration) {
        if (S()) {
            Configuration configuration2 = this.I;
            if (configuration2.keyboard == configuration.keyboard && configuration2.keyboardHidden == configuration.keyboardHidden && configuration2.hardKeyboardHidden == configuration.hardKeyboardHidden) {
                return;
            }
            this.I = new Configuration(configuration);
            if ((this.B == 0 || this.D == 1) ? false : true) {
                Y();
                c0();
            } else if (O()) {
                Y();
                if (this.I.keyboard != 1) {
                    c0();
                } else {
                    R();
                }
            }
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onDetachedFromWindow() {
        X();
        KA ka = this.v;
        if (ka != null) {
            WB wb = (WB) ka;
            UB ub = wb.d;
            if (ub != null) {
                ub.a();
            }
            YB yb = wb.b;
            if (yb != null) {
                yb.w.set(null);
                yb.x.set(null);
            }
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onWindowFocusChanged(boolean z) {
        UB ub;
        KA ka = this.v;
        if (ka != null) {
            WB wb = (WB) ka;
            if (!z && (ub = wb.d) != null) {
                ub.a();
            }
            YB yb = wb.b;
            if (yb != null) {
                yb.v.set(z);
            }
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        ND nd = this.t;
        if (nd != null) {
            ((WA) nd).b = windowAndroid;
        }
    }
}
