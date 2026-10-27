package org.chromium.components.browser_ui.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import defpackage.C0173a;
import defpackage.C0188h0;
import defpackage.a8;
import defpackage.ao3;
import defpackage.d73;
import defpackage.gq2;
import defpackage.ii;
import defpackage.uv;
import defpackage.vi;
import defpackage.vl;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.bottomsheet.i;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes2.dex */
class BottomSheet extends FrameLayout implements i.b, View.OnLayoutChangeListener {
    public static Callback<Throwable> C;
    public float A;
    public C0188h0 B;
    public final DecelerateInterpolator e;
    public final org.chromium.base.b<vi> f;
    public final Rect g;
    public final int[] h;
    public final float i;
    public ViewGroup j;
    public final i k;
    public ValueAnimator l;
    public int m;
    public int n;
    public float o;
    public float p;
    public int q;
    public int r;
    public int s;
    public ii t;
    public TouchRestrictingFrameLayout u;
    public float v;
    public TouchRestrictingFrameLayout w;
    public boolean x;
    public boolean y;
    public boolean z;

    public static class ShadowLayerView extends View {
        public final int e;

        public ShadowLayerView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = context.getResources().getDimensionPixelSize(R$dimen.bottom_sheet_shadow_length);
            setTranslationX((LocalizationUtils.isLayoutRtl() ? 1 : -1) * r1);
            setTranslationY(-r1);
        }

        @Override // android.view.View
        public final void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int i3 = this.e;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((i3 * 2) + size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2) + i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
    }

    public class a extends vl {
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;

        public a(int i, int i2) {
            this.f = i;
            this.g = i2;
        }

        @Override // defpackage.vl
        public final void b() {
            BottomSheet bottomSheet = BottomSheet.this;
            if (bottomSheet.z) {
                return;
            }
            bottomSheet.l = null;
            StringBuilder sb = new StringBuilder("Ending settle animation: target: ");
            int i = this.f;
            sb.append(i);
            sb.append(", content null: ");
            sb.append(bottomSheet.t == null);
            Log.i("cr_BottomSheet", sb.toString());
            bottomSheet.p(i, this.g);
            bottomSheet.r = -1;
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ int e;

        public b(int i) {
            this.e = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            BottomSheet bottomSheet = BottomSheet.this;
            if (valueAnimator != bottomSheet.l) {
                return;
            }
            bottomSheet.r(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.e);
        }
    }

    public BottomSheet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new DecelerateInterpolator(1.0f);
        this.f = new org.chromium.base.b<>();
        this.g = new Rect();
        this.h = new int[2];
        this.o = -1.0f;
        this.q = 0;
        this.r = -1;
        this.s = -1;
        this.i = getResources().getDimensionPixelSize(R$dimen.bottom_sheet_min_full_half_distance);
        this.k = new i(context, this);
        this.x = true;
    }

    public final void a(int i, int i2) {
        this.r = i;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.p, j(i));
        this.l = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(218L);
        this.l.setInterpolator(this.e);
        this.l.addListener(new a(i, i2));
        this.l.addUpdateListener(new b(i2));
        StringBuilder sb = new StringBuilder("Starting settle animation: target: ");
        sb.append(i);
        sb.append(", content null: ");
        sb.append(this.t == null);
        Log.i("cr_BottomSheet", sb.toString());
        p(4, i2);
        this.l.start();
    }

    public final void b() {
        if (this.o != -1.0f) {
            return;
        }
        this.t.d().measure(View.MeasureSpec.makeMeasureSpec(e(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.n, Integer.MIN_VALUE));
        this.o = this.t.d().getMeasuredHeight();
    }

    public final float c() {
        ii iiVar;
        if (this.n <= 0 || (iiVar = this.t) == null) {
            return 0.0f;
        }
        float fS = iiVar.s();
        if (l()) {
            b();
            return Math.min(this.n, this.o) / this.n;
        }
        if (fS == 0.0f) {
            return 1.0f;
        }
        return fS;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int d(boolean r6, float r7) {
        /*
            r5 = this;
            int r0 = r5.g()
            boolean r1 = r5.m()
            r2 = 1
            if (r1 == 0) goto L1c
            ii r1 = r5.t
            r3 = 0
            if (r1 == 0) goto L19
            boolean r1 = r1.q()
            if (r1 == 0) goto L17
            goto L19
        L17:
            r1 = r3
            goto L1a
        L19:
            r1 = r2
        L1a:
            if (r1 == 0) goto L1d
        L1c:
            r3 = r2
        L1d:
            int r1 = r0 + 1
        L1f:
            r4 = 3
            if (r1 >= r4) goto L47
            if (r1 != r2) goto L2b
            boolean r4 = r5.n()
            if (r4 != 0) goto L2b
            goto L44
        L2b:
            r4 = 2
            if (r1 != r4) goto L31
            if (r3 == 0) goto L31
            goto L44
        L31:
            float r4 = r5.j(r1)
            int r4 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r4 > 0) goto L43
            float r4 = r5.j(r1)
            int r4 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r4 != 0) goto L44
            if (r6 != 0) goto L44
        L43:
            r0 = r1
        L44:
            int r1 = r1 + 1
            goto L1f
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.bottomsheet.BottomSheet.d(boolean, float):int");
    }

    public final int e() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.bottom_sheet_narrow_width_threshold);
        int i = this.m;
        return i > dimensionPixelSize ? getResources().getDimensionPixelSize(R$dimen.bottom_sheet_narrow_width) : i;
    }

    public final float f() {
        ii iiVar = this.t;
        return (iiVar != null ? iiVar.u() : true ? 0.0f : i()) * this.n;
    }

    public final int g() {
        ii iiVar = this.t;
        return ((iiVar != null ? iiVar.u() : true) || !n()) ? 0 : 1;
    }

    public final float h() {
        ii iiVar = this.t;
        if (iiVar != null && iiVar.t() && n()) {
            return i() * this.n * this.A;
        }
        return 0.0f;
    }

    public final float i() {
        int measuredHeight;
        ViewGroup.LayoutParams layoutParams;
        if (this.n <= 0 || !n()) {
            return 0.0f;
        }
        ii iiVar = this.t;
        if (iiVar != null && iiVar.j() != 0) {
            return this.t.j() / this.n;
        }
        ii iiVar2 = this.t;
        View viewG = (iiVar2 == null || iiVar2.g() == null) ? null : this.t.g();
        if (viewG == null) {
            measuredHeight = getResources().getDimensionPixelSize(R$dimen.bottom_sheet_peek_height);
        } else {
            int height = viewG.getHeight();
            if (height != 0 || (layoutParams = viewG.getLayoutParams()) == null || (height = layoutParams.height) > 0) {
                measuredHeight = height;
            } else {
                viewG.measure(View.MeasureSpec.makeMeasureSpec(e(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.n, Integer.MIN_VALUE));
                measuredHeight = viewG.getMeasuredHeight();
            }
        }
        return measuredHeight / this.n;
    }

    public final float j(int i) {
        if (l() && i == 3) {
            b();
        }
        float fI = 0.0f;
        if (i != 0) {
            if (i == 1) {
                fI = i();
            } else if (i != 2) {
                if (i != 3) {
                    throw new IllegalArgumentException(gq2.e("Invalid state: ", i));
                }
                fI = c();
            } else if (this.n > 0 && m()) {
                float fN = this.t.n();
                if (fN == 0.0f) {
                    fN = 0.75f;
                }
                fI = fN;
            }
        }
        return fI * this.n;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int k(float r9, float r10) {
        /*
            r8 = this;
            float r0 = r8.f()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 > 0) goto Ld
            int r8 = r8.g()
            return r8
        Ld:
            float r0 = r8.c()
            int r1 = r8.n
            float r1 = (float) r1
            float r0 = r0 * r1
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            r1 = 3
            if (r0 < 0) goto L1b
            return r1
        L1b:
            r0 = 0
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r2 = 0
            r3 = 1
            if (r0 >= 0) goto L24
            r0 = r3
            goto L25
        L24:
            r0 = r2
        L25:
            if (r0 == 0) goto L34
            ii r4 = r8.t
            if (r4 == 0) goto L30
            boolean r4 = r4.u()
            goto L31
        L30:
            r4 = r3
        L31:
            if (r4 != 0) goto L34
            float r9 = r9 - r10
        L34:
            int r10 = r8.s
            r4 = 2
            if (r0 == 0) goto L3e
            int r1 = r8.d(r0, r9)
            goto L65
        L3e:
            int r5 = r8.d(r0, r9)
            r6 = r4
        L43:
            int r7 = r5 + 1
            if (r6 <= r7) goto L65
            if (r6 != r4) goto L50
            boolean r7 = r8.m()
            if (r7 != 0) goto L50
            goto L62
        L50:
            if (r6 != r3) goto L59
            boolean r7 = r8.n()
            if (r7 != 0) goto L59
            goto L62
        L59:
            float r7 = r8.j(r6)
            int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r7 > 0) goto L62
            r1 = r6
        L62:
            int r6 = r6 + (-1)
            goto L43
        L65:
            if (r1 != r10) goto L68
            goto Laf
        L68:
            r5 = -1
            if (r10 == r5) goto Lae
            r5 = 4
            if (r10 != r5) goto L6f
            goto Lae
        L6f:
            float r5 = r8.j(r10)
            float r6 = r8.j(r1)
            float r6 = r6 - r5
            float r9 = r9 - r5
            float r9 = r9 / r6
            float r9 = java.lang.Math.abs(r9)
            if (r1 != r4) goto L81
            goto La3
        L81:
            if (r0 == 0) goto L87
            if (r10 <= r4) goto L87
            if (r1 < r4) goto L8d
        L87:
            if (r0 != 0) goto L8f
            if (r10 >= r4) goto L8f
            if (r1 <= r4) goto L8f
        L8d:
            r0 = r3
            goto L90
        L8f:
            r0 = r2
        L90:
            if (r0 != 0) goto L93
            goto La3
        L93:
            ii r8 = r8.t
            if (r8 == 0) goto La0
            boolean r8 = r8.q()
            if (r8 == 0) goto L9e
            goto La0
        L9e:
            r8 = r2
            goto La1
        La0:
            r8 = r3
        La1:
            if (r8 != 0) goto La7
        La3:
            r8 = 1053609165(0x3ecccccd, float:0.4)
            goto Laa
        La7:
            r8 = 1050253722(0x3e99999a, float:0.3)
        Laa:
            int r8 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r8 <= 0) goto Laf
        Lae:
            r2 = r3
        Laf:
            if (r2 == 0) goto Lb2
            r10 = r1
        Lb2:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.bottomsheet.BottomSheet.k(float, float):int");
    }

    public final boolean l() {
        ii iiVar = this.t;
        return iiVar != null && iiVar.s() == -1.0f;
    }

    public final boolean m() {
        ii iiVar = this.t;
        if (iiVar == null) {
            return false;
        }
        return ((((((float) this.n) * 0.25f) > this.i ? 1 : ((((float) this.n) * 0.25f) == this.i ? 0 : -1)) < 0) || iiVar.n() == -2.0f || this.t.s() == -1.0f) ? false : true;
    }

    public final boolean n() {
        ii iiVar = this.t;
        return (iiVar == null || iiVar.j() == -2) ? false : true;
    }

    public final void o(ii iiVar) {
        this.t = iiVar;
        if (iiVar != null && l()) {
            iiVar.d().addOnLayoutChangeListener(this);
            this.o = -1.0f;
            int i = this.q;
            if (i != 0 && i != 1) {
                if ((this.l != null) || i != 4) {
                    s(i, 0, true);
                }
            }
            if (this.q == 2) {
                s(3, 0, true);
            }
        }
        Iterator<vi> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.w.setBackgroundColor(0);
                return;
            }
            ((vi) aVar.next()).b(iiVar);
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!(motionEvent.getY() > 0.0f) && motionEvent.getActionMasked() == 0) {
            return false;
        }
        if (!this.x) {
            return true;
        }
        if (this.l != null && this.r == 0) {
            return false;
        }
        i iVar = this.k;
        GestureDetector gestureDetector = iVar.a;
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
        gestureDetector.onTouchEvent(motionEventObtain);
        return iVar.d;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.o = -1.0f;
        int i9 = this.q;
        if (i9 == 0 || i9 == 1) {
            return;
        }
        if ((this.l != null) || i9 != 4) {
            s(i9, 0, true);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(motionEvent.getY() > 0.0f) && motionEvent.getActionMasked() == 0) {
            return false;
        }
        if (!this.x) {
            return true;
        }
        i iVar = this.k;
        iVar.getClass();
        if (motionEvent.getActionMasked() != 0) {
            GestureDetector gestureDetector = iVar.a;
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
            gestureDetector.onTouchEvent(motionEventObtain);
        }
        if (iVar.d && (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3)) {
            iVar.d = false;
            VelocityTracker velocityTracker = iVar.c;
            velocityTracker.computeCurrentVelocity(1000);
            BottomSheet bottomSheet = (BottomSheet) iVar.b;
            bottomSheet.q(true, a8.o((((-velocityTracker.getYVelocity()) * 218.0f) / 2000.0f) + bottomSheet.p, bottomSheet.f(), bottomSheet.c() * bottomSheet.n));
        }
        return true;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            ao3.e(this, "BottomSheet.onWindowFocusChagned");
        }
    }

    public final void p(int i, int i2) {
        int i3 = this.q;
        if (i == i3) {
            return;
        }
        ii iiVar = this.t;
        if (iiVar == null && i != 0) {
            Log.i("cr_BottomSheet", "Content null while open! ");
            final Throwable th = new Throwable("This is not a crash. See https://crbug.com/1126872 for details.");
            PostTask.c(d73.g, new Runnable() { // from class: org.chromium.components.browser_ui.bottomsheet.a
                @Override // java.lang.Runnable
                public final void run() {
                    BottomSheet.C.onResult(th);
                }
            });
            s(0, 0, false);
            return;
        }
        if (i == -1) {
            s(k(this.p, 0.0f), 0, false);
            return;
        }
        if (i != 4 || i3 == 4) {
            i3 = -1;
        }
        this.s = i3;
        this.q = i;
        if (i == 2 || i == 3) {
            announceForAccessibility(getResources().getString(i == 3 ? iiVar.r() : iiVar.h()));
            setFocusable(true);
            setFocusableInTouchMode(true);
            String string = getResources().getString(this.t.p());
            if (this.t.u()) {
                StringBuilder sbK = C0173a.k(string, ". ");
                sbK.append(getResources().getString(R$string.bottom_sheet_accessibility_description));
                string = sbK.toString();
            }
            setContentDescription(string);
            if (getFocusedChild() == null) {
                requestFocus();
            }
        }
        Iterator<vi> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((vi) aVar.next()).d(this.q, i2);
            }
        }
    }

    public final void q(boolean z, float f) {
        ValueAnimator valueAnimator = this.l;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l = null;
        }
        if (this.t == null) {
            return;
        }
        if (z) {
            s(k(f, -(this.p - f)), 1, true);
        } else {
            p(4, 1);
            r(f, 1);
        }
    }

    public final void r(float f, int i) {
        this.p = f;
        float fH = (this.n - f) + h();
        if (this.y && a8.h(fH, getTranslationY())) {
            return;
        }
        setTranslationY(fH);
        int iG = g();
        if (n() && (!this.y || this.r == 1)) {
            iG = 1;
        }
        float fJ = j(iG);
        boolean zH = a8.h(this.p, fJ);
        boolean z = this.p < fJ;
        boolean z2 = !n() && this.r == 0;
        boolean z3 = this.y;
        org.chromium.base.b<vi> bVar = this.f;
        if (z3 && (z || zH || z2)) {
            if (z3) {
                this.y = false;
                Iterator<vi> it = bVar.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((vi) aVar.next()).c(i);
                    }
                }
                if (this.t != null) {
                    announceForAccessibility(getResources().getString(this.t.i()));
                }
                clearFocus();
                setFocusable(false);
                setFocusableInTouchMode(false);
                setContentDescription(null);
            }
        } else if (!z3 && this.r != 0 && this.p > fJ && !z3) {
            this.y = true;
            Iterator<vi> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((vi) aVar2.next()).e();
                }
            }
        }
        float fH2 = this.p - h();
        if (fH2 <= j(0) && this.v <= 0.0f) {
            return;
        }
        int i2 = this.n;
        float f2 = i2 > 0 ? fH2 / i2 : 0.0f;
        float fC = c() - 0.0f;
        float fO = fC == 0.0f ? 0.0f : a8.o((f2 - 0.0f) / fC, 0.0f, 1.0f);
        if (fH2 < j(0)) {
            this.v = 0.0f;
        } else {
            this.v = a8.h(fO, 0.0f) ? 0.0f : fO;
        }
        Iterator<vi> it3 = bVar.iterator();
        while (true) {
            b.a aVar3 = (b.a) it3;
            if (!aVar3.hasNext()) {
                return;
            } else {
                ((vi) aVar3.next()).a(this.v);
            }
        }
    }

    public final void s(int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder("Setting sheet state: state: ");
        sb.append(this.q);
        sb.append(", content null: ");
        sb.append(this.t == null);
        Log.i("cr_BottomSheet", sb.toString());
        if (i == 4) {
            return;
        }
        if (i == 2 && !m()) {
            i = 3;
        }
        ValueAnimator valueAnimator = this.l;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.l = null;
        }
        this.r = i;
        if (z && (i != this.q || this.p != j(i))) {
            a(i, i2);
            return;
        }
        r(j(i), i2);
        p(this.r, i2);
        this.r = -1;
    }

    public final boolean t(MotionEvent motionEvent) {
        if (this.p < j(1) || h() > 0.0f) {
            return false;
        }
        if (this.y || this.B.c()) {
            return true;
        }
        Rect rect = this.g;
        return motionEvent.getRawX() > ((float) rect.left) && motionEvent.getRawX() < ((float) (getWidth() + rect.left));
    }

    public final void u(ii iiVar) {
        if (this.t == iiVar) {
            return;
        }
        StringBuilder sb = new StringBuilder("Setting sheet content: state: ");
        sb.append(this.q);
        sb.append(", content null: ");
        sb.append(iiVar == null);
        Log.i("cr_BottomSheet", sb.toString());
        if (iiVar == null) {
            Thread.dumpStack();
        }
        ii iiVar2 = this.t;
        if (iiVar2 != null) {
            iiVar2.d().removeOnLayoutChangeListener(this);
        }
        if (iiVar != null && getParent() == null) {
            this.j.addView(this);
        } else if (iiVar == null) {
            if (this.j.getParent() == null) {
                throw new uv("Attempting to detach sheet that was not in the hierarchy!");
            }
            this.j.removeView(this);
        }
        View viewD = iiVar != null ? iiVar.d() : null;
        ii iiVar3 = this.t;
        View viewD2 = iiVar3 != null ? iiVar3.d() : null;
        TouchRestrictingFrameLayout touchRestrictingFrameLayout = this.u;
        if (viewD2 != null && viewD2.getParent() != null) {
            touchRestrictingFrameLayout.removeView(viewD2);
        }
        if (viewD != null && touchRestrictingFrameLayout != viewD.getParent()) {
            touchRestrictingFrameLayout.addView(viewD);
        }
        View viewG = iiVar != null ? iiVar.g() : null;
        ii iiVar4 = this.t;
        View viewG2 = iiVar4 != null ? iiVar4.g() : null;
        TouchRestrictingFrameLayout touchRestrictingFrameLayout2 = this.w;
        if (viewG2 != null && viewG2.getParent() != null) {
            touchRestrictingFrameLayout2.removeView(viewG2);
        }
        if (viewG != null && touchRestrictingFrameLayout2 != viewG.getParent()) {
            touchRestrictingFrameLayout2.addView(viewG);
        }
        o(iiVar);
    }

    public final void v() {
        getLayoutParams().width = e();
        setTranslationX(((this.m - r0) * (LocalizationUtils.isLayoutRtl() ? -1 : 1)) / 2.0f);
        ao3.e(this, "BottomSheet.sizeAndPositionSheetInParent");
    }
}
