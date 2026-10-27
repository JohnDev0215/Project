package m3;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import e2.W;
import e2.f0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: renamed from: m3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1805e extends View implements I {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f19017A;
    public final int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final int f19018C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int f19019D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final int f19020E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final StringBuilder f19021F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Formatter f19022G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final A2.l f19023H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final CopyOnWriteArraySet f19024I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final Point f19025J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final float f19026K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f19027L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public long f19028M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f19029N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public Rect f19030O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final ValueAnimator f19031P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f19032Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f19033R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f19034S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public long f19035T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public long f19036U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public long f19037V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public long f19038W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f19039a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public long[] f19040b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean[] f19041c0;
    public final Rect l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Rect f19042m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f19043n;
    public final Rect o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Paint f19044p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Paint f19045q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Paint f19046r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Paint f19047s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Paint f19048t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Paint f19049u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Drawable f19050v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f19051w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f19052x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f19053y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f19054z;

    public C1805e(Context context) {
        super(context, null, 0);
        this.l = new Rect();
        this.f19042m = new Rect();
        this.f19043n = new Rect();
        this.o = new Rect();
        Paint paint = new Paint();
        this.f19044p = paint;
        Paint paint2 = new Paint();
        this.f19045q = paint2;
        Paint paint3 = new Paint();
        this.f19046r = paint3;
        Paint paint4 = new Paint();
        this.f19047s = paint4;
        Paint paint5 = new Paint();
        this.f19048t = paint5;
        Paint paint6 = new Paint();
        this.f19049u = paint6;
        paint6.setAntiAlias(true);
        this.f19024I = new CopyOnWriteArraySet();
        this.f19025J = new Point();
        float f10 = context.getResources().getDisplayMetrics().density;
        this.f19026K = f10;
        this.f19020E = a(f10, -50);
        int iA = a(f10, 4);
        int iA2 = a(f10, 26);
        int iA3 = a(f10, 4);
        int iA4 = a(f10, 12);
        int iA5 = a(f10, 0);
        int iA6 = a(f10, 16);
        this.f19051w = iA;
        this.f19052x = iA2;
        this.f19053y = 0;
        this.f19054z = iA3;
        this.f19017A = iA4;
        this.B = iA5;
        this.f19018C = iA6;
        paint.setColor(-1);
        paint6.setColor(-1);
        paint2.setColor(-855638017);
        paint3.setColor(872415231);
        paint4.setColor(-1291845888);
        paint5.setColor(872414976);
        this.f19050v = null;
        StringBuilder sb = new StringBuilder();
        this.f19021F = sb;
        this.f19022G = new Formatter(sb, Locale.getDefault());
        this.f19023H = new A2.l(this, 26);
        this.f19019D = (Math.max(iA5, Math.max(iA4, iA6)) + 1) / 2;
        this.f19032Q = 1.0f;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.f19031P = valueAnimator;
        valueAnimator.addUpdateListener(new O3.f(this, 1));
        this.f19036U = -9223372036854775807L;
        this.f19028M = -9223372036854775807L;
        this.f19027L = 20;
        setFocusable(true);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static int a(float f10, int i7) {
        return (int) ((i7 * f10) + 0.5f);
    }

    private long getPositionIncrement() {
        long j3 = this.f19028M;
        if (j3 != -9223372036854775807L) {
            return j3;
        }
        long j10 = this.f19036U;
        if (j10 == -9223372036854775807L) {
            return 0L;
        }
        return j10 / ((long) this.f19027L);
    }

    private String getProgressText() {
        return AbstractC1343A.w(this.f19021F, this.f19022G, this.f19037V);
    }

    private long getScrubberPosition() {
        Rect rect = this.f19042m;
        if (rect.width() <= 0 || this.f19036U == -9223372036854775807L) {
            return 0L;
        }
        return (((long) this.o.width()) * this.f19036U) / ((long) rect.width());
    }

    public final boolean b(long j3) {
        long j10 = this.f19036U;
        if (j10 <= 0) {
            return false;
        }
        long j11 = this.f19034S ? this.f19035T : this.f19037V;
        long j12 = AbstractC1343A.j(j11 + j3, 0L, j10);
        if (j12 == j11) {
            return false;
        }
        if (this.f19034S) {
            f(j12);
        } else {
            c(j12);
        }
        e();
        return true;
    }

    public final void c(long j3) {
        this.f19035T = j3;
        this.f19034S = true;
        setPressed(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        Iterator it = this.f19024I.iterator();
        while (it.hasNext()) {
            s sVar = ((ViewOnClickListenerC1808h) it.next()).l;
            sVar.B0 = true;
            TextView textView = sVar.f19100O;
            if (textView != null) {
                textView.setText(AbstractC1343A.w(sVar.f19102Q, sVar.f19103R, j3));
            }
            sVar.l.f();
        }
    }

    public final void d(boolean z6) {
        W w10;
        removeCallbacks(this.f19023H);
        this.f19034S = false;
        setPressed(false);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        for (ViewOnClickListenerC1808h viewOnClickListenerC1808h : this.f19024I) {
            long j3 = this.f19035T;
            s sVar = viewOnClickListenerC1808h.l;
            sVar.B0 = false;
            if (!z6 && (w10 = sVar.f19137u0) != null) {
                if (sVar.f19079A0) {
                    if (w10.R0(17) && w10.R0(10)) {
                        f0 f0VarT0 = w10.t0();
                        int iO = f0VarT0.o();
                        int i7 = 0;
                        while (true) {
                            long jT = AbstractC1343A.T(f0VarT0.m(i7, sVar.f19105T, 0L).f14094m);
                            if (j3 < jT) {
                                break;
                            }
                            if (i7 == iO - 1) {
                                j3 = jT;
                                break;
                            } else {
                                j3 -= jT;
                                i7++;
                            }
                        }
                        w10.I0(j3, i7);
                    }
                } else if (w10.R0(5)) {
                    w10.k(j3);
                }
                sVar.o();
            }
            sVar.l.g();
        }
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f19050v;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidate();
        }
    }

    public final void e() {
        Rect rect = this.f19043n;
        Rect rect2 = this.f19042m;
        rect.set(rect2);
        Rect rect3 = this.o;
        rect3.set(rect2);
        long j3 = this.f19034S ? this.f19035T : this.f19037V;
        if (this.f19036U > 0) {
            rect.right = Math.min(rect2.left + ((int) ((((long) rect2.width()) * this.f19038W) / this.f19036U)), rect2.right);
            rect3.right = Math.min(rect2.left + ((int) ((((long) rect2.width()) * j3) / this.f19036U)), rect2.right);
        } else {
            int i7 = rect2.left;
            rect.right = i7;
            rect3.right = i7;
        }
        invalidate(this.l);
    }

    public final void f(long j3) {
        if (this.f19035T == j3) {
            return;
        }
        this.f19035T = j3;
        Iterator it = this.f19024I.iterator();
        while (it.hasNext()) {
            s sVar = ((ViewOnClickListenerC1808h) it.next()).l;
            TextView textView = sVar.f19100O;
            if (textView != null) {
                textView.setText(AbstractC1343A.w(sVar.f19102Q, sVar.f19103R, j3));
            }
        }
    }

    public long getPreferredUpdateDelay() {
        int iWidth = (int) (this.f19042m.width() / this.f19026K);
        if (iWidth != 0) {
            long j3 = this.f19036U;
            if (j3 != 0 && j3 != -9223372036854775807L) {
                return j3 / ((long) iWidth);
            }
        }
        return Long.MAX_VALUE;
    }

    @Override // android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f19050v;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.save();
        Rect rect = this.f19042m;
        int iHeight = rect.height();
        int iCenterY = rect.centerY() - (iHeight / 2);
        int i7 = iCenterY + iHeight;
        long j3 = this.f19036U;
        Paint paint = this.f19046r;
        Rect rect2 = this.o;
        if (j3 <= 0) {
            canvas.drawRect(rect.left, iCenterY, rect.right, i7, paint);
        } else {
            Rect rect3 = this.f19043n;
            int i10 = rect3.left;
            int i11 = rect3.right;
            int iMax = Math.max(Math.max(rect.left, i11), rect2.right);
            int i12 = rect.right;
            if (iMax < i12) {
                canvas.drawRect(iMax, iCenterY, i12, i7, paint);
            }
            int iMax2 = Math.max(i10, rect2.right);
            if (i11 > iMax2) {
                canvas.drawRect(iMax2, iCenterY, i11, i7, this.f19045q);
            }
            if (rect2.width() > 0) {
                canvas.drawRect(rect2.left, iCenterY, rect2.right, i7, this.f19044p);
            }
            if (this.f19039a0 != 0) {
                long[] jArr = this.f19040b0;
                jArr.getClass();
                boolean[] zArr = this.f19041c0;
                zArr.getClass();
                int i13 = this.f19054z;
                int i14 = i13 / 2;
                int i15 = 0;
                int i16 = 0;
                while (i16 < this.f19039a0) {
                    canvas.drawRect(Math.min(rect.width() - i13, Math.max(i15, ((int) ((((long) rect.width()) * AbstractC1343A.j(jArr[i16], 0L, this.f19036U)) / this.f19036U)) - i14)) + rect.left, iCenterY, r1 + i13, i7, zArr[i16] ? this.f19048t : this.f19047s);
                    i16++;
                    i15 = i15;
                    i13 = i13;
                }
            }
        }
        if (this.f19036U > 0) {
            int i17 = AbstractC1343A.i(rect2.right, rect2.left, rect.right);
            int iCenterY2 = rect2.centerY();
            Drawable drawable = this.f19050v;
            if (drawable == null) {
                canvas.drawCircle(i17, iCenterY2, (int) ((((this.f19034S || isFocused()) ? this.f19018C : isEnabled() ? this.f19017A : this.B) * this.f19032Q) / 2.0f), this.f19049u);
            } else {
                int intrinsicWidth = ((int) (drawable.getIntrinsicWidth() * this.f19032Q)) / 2;
                int intrinsicHeight = ((int) (drawable.getIntrinsicHeight() * this.f19032Q)) / 2;
                drawable.setBounds(i17 - intrinsicWidth, iCenterY2 - intrinsicHeight, i17 + intrinsicWidth, iCenterY2 + intrinsicHeight);
                drawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        if (!this.f19034S || z6) {
            return;
        }
        d(false);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName("android.widget.SeekBar");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.SeekBar");
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.f19036U <= 0) {
            return;
        }
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onKeyDown(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            boolean r0 = r4.isEnabled()
            if (r0 == 0) goto L2e
            long r0 = r4.getPositionIncrement()
            r2 = 66
            r3 = 1
            if (r5 == r2) goto L25
            switch(r5) {
                case 21: goto L13;
                case 22: goto L14;
                case 23: goto L25;
                default: goto L12;
            }
        L12:
            goto L2e
        L13:
            long r0 = -r0
        L14:
            boolean r0 = r4.b(r0)
            if (r0 == 0) goto L2e
            A2.l r5 = r4.f19023H
            r4.removeCallbacks(r5)
            r0 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r5, r0)
            return r3
        L25:
            boolean r0 = r4.f19034S
            if (r0 == 0) goto L2e
            r5 = 0
            r4.d(r5)
            return r3
        L2e:
            boolean r4 = super.onKeyDown(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.C1805e.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int paddingBottom;
        int paddingBottom2;
        Rect rect;
        int i13 = i11 - i7;
        int i14 = i12 - i10;
        int paddingLeft = getPaddingLeft();
        int paddingRight = i13 - getPaddingRight();
        int i15 = this.f19033R ? 0 : this.f19019D;
        int i16 = this.f19053y;
        int i17 = this.f19051w;
        int i18 = this.f19052x;
        if (i16 == 1) {
            paddingBottom = (i14 - getPaddingBottom()) - i18;
            paddingBottom2 = ((i14 - getPaddingBottom()) - i17) - Math.max(i15 - (i17 / 2), 0);
        } else {
            paddingBottom = (i14 - i18) / 2;
            paddingBottom2 = (i14 - i17) / 2;
        }
        Rect rect2 = this.l;
        rect2.set(paddingLeft, paddingBottom, paddingRight, i18 + paddingBottom);
        this.f19042m.set(rect2.left + i15, paddingBottom2, rect2.right - i15, i17 + paddingBottom2);
        if (AbstractC1343A.f15195a >= 29 && ((rect = this.f19030O) == null || rect.width() != i13 || this.f19030O.height() != i14)) {
            Rect rect3 = new Rect(0, 0, i13, i14);
            this.f19030O = rect3;
            setSystemGestureExclusionRects(Collections.singletonList(rect3));
        }
        e();
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        int i11 = this.f19052x;
        if (mode == 0) {
            size = i11;
        } else if (mode != 1073741824) {
            size = Math.min(i11, size);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i7), size);
        Drawable drawable = this.f19050v;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidate();
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        Drawable drawable = this.f19050v;
        if (drawable == null || AbstractC1343A.f15195a < 23 || !drawable.setLayoutDirection(i7)) {
            return;
        }
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.isEnabled()
            r1 = 0
            if (r0 == 0) goto La1
            long r2 = r9.f19036U
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 > 0) goto L11
            goto La1
        L11:
            android.graphics.Point r0 = r9.f19025J
            float r2 = r10.getX()
            int r2 = (int) r2
            float r3 = r10.getY()
            int r3 = (int) r3
            r0.set(r2, r3)
            int r2 = r0.x
            int r0 = r0.y
            int r3 = r10.getAction()
            android.graphics.Rect r4 = r9.o
            android.graphics.Rect r5 = r9.f19042m
            r6 = 1
            if (r3 == 0) goto L7d
            r7 = 3
            if (r3 == r6) goto L6e
            r8 = 2
            if (r3 == r8) goto L38
            if (r3 == r7) goto L6e
            goto La1
        L38:
            boolean r10 = r9.f19034S
            if (r10 == 0) goto La1
            int r10 = r9.f19020E
            if (r0 >= r10) goto L52
            int r10 = r9.f19029N
            int r2 = r2 - r10
            int r2 = r2 / r7
            int r2 = r2 + r10
            float r10 = (float) r2
            int r10 = (int) r10
            int r0 = r5.left
            int r1 = r5.right
            int r10 = h2.AbstractC1343A.i(r10, r0, r1)
            r4.right = r10
            goto L60
        L52:
            r9.f19029N = r2
            float r10 = (float) r2
            int r10 = (int) r10
            int r0 = r5.left
            int r1 = r5.right
            int r10 = h2.AbstractC1343A.i(r10, r0, r1)
            r4.right = r10
        L60:
            long r0 = r9.getScrubberPosition()
            r9.f(r0)
            r9.e()
            r9.invalidate()
            return r6
        L6e:
            boolean r0 = r9.f19034S
            if (r0 == 0) goto La1
            int r10 = r10.getAction()
            if (r10 != r7) goto L79
            r1 = r6
        L79:
            r9.d(r1)
            return r6
        L7d:
            float r10 = (float) r2
            float r0 = (float) r0
            int r10 = (int) r10
            int r0 = (int) r0
            android.graphics.Rect r2 = r9.l
            boolean r0 = r2.contains(r10, r0)
            if (r0 == 0) goto La1
            int r0 = r5.left
            int r1 = r5.right
            int r10 = h2.AbstractC1343A.i(r10, r0, r1)
            r4.right = r10
            long r0 = r9.getScrubberPosition()
            r9.c(r0)
            r9.e()
            r9.invalidate()
            return r6
        La1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.C1805e.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i7, Bundle bundle) {
        if (super.performAccessibilityAction(i7, bundle)) {
            return true;
        }
        if (this.f19036U <= 0) {
            return false;
        }
        if (i7 == 8192) {
            if (b(-getPositionIncrement())) {
                d(false);
            }
        } else {
            if (i7 != 4096) {
                return false;
            }
            if (b(getPositionIncrement())) {
                d(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    public void setAdMarkerColor(int i7) {
        this.f19047s.setColor(i7);
        invalidate(this.l);
    }

    public void setBufferedColor(int i7) {
        this.f19045q.setColor(i7);
        invalidate(this.l);
    }

    public void setBufferedPosition(long j3) {
        if (this.f19038W == j3) {
            return;
        }
        this.f19038W = j3;
        e();
    }

    public void setDuration(long j3) {
        if (this.f19036U == j3) {
            return;
        }
        this.f19036U = j3;
        if (this.f19034S && j3 == -9223372036854775807L) {
            d(true);
        }
        e();
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        if (!this.f19034S || z6) {
            return;
        }
        d(true);
    }

    public void setKeyCountIncrement(int i7) {
        AbstractC1346c.c(i7 > 0);
        this.f19027L = i7;
        this.f19028M = -9223372036854775807L;
    }

    public void setKeyTimeIncrement(long j3) {
        AbstractC1346c.c(j3 > 0);
        this.f19027L = -1;
        this.f19028M = j3;
    }

    public void setPlayedAdMarkerColor(int i7) {
        this.f19048t.setColor(i7);
        invalidate(this.l);
    }

    public void setPlayedColor(int i7) {
        this.f19044p.setColor(i7);
        invalidate(this.l);
    }

    public void setPosition(long j3) {
        if (this.f19037V == j3) {
            return;
        }
        this.f19037V = j3;
        setContentDescription(getProgressText());
        e();
    }

    public void setScrubberColor(int i7) {
        this.f19049u.setColor(i7);
        invalidate(this.l);
    }

    public void setUnplayedColor(int i7) {
        this.f19046r.setColor(i7);
        invalidate(this.l);
    }
}
