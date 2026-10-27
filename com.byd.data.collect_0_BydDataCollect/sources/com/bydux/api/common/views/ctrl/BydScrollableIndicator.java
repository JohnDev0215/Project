package com.bydux.api.common.views.ctrl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydScrollableIndicator extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Drawable f3590c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f3591d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f3592e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3593f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<Rect> f3594g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f3595h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f3596i;
    private float j;
    private Rect k;
    private Rect l;
    private Rect m;
    private Rect n;
    private int o;
    private boolean p;
    private boolean q;
    private h r;
    private g s;
    private Handler t;
    private int u;
    private long v;
    private int w;
    private f x;
    private Rect y;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            int i2 = BydScrollableIndicator.this.l.left + iIntValue;
            int i3 = BydScrollableIndicator.this.l.top;
            int i4 = BydScrollableIndicator.this.f3593f + i2;
            int i5 = BydScrollableIndicator.this.l.bottom;
            if ((iIntValue < 0 && i2 < BydScrollableIndicator.this.f3593f * BydScrollableIndicator.this.o) || (iIntValue > 0 && i2 > BydScrollableIndicator.this.f3593f * BydScrollableIndicator.this.o)) {
                return;
            }
            BydScrollableIndicator.this.k.set(i2, i3, i4, i5);
            BydScrollableIndicator.this.f3590c.setBounds(BydScrollableIndicator.this.k);
            BydScrollableIndicator.this.invalidate();
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
            bydScrollableIndicator.l = bydScrollableIndicator.y;
            BydScrollableIndicator.this.p = false;
            BydScrollableIndicator bydScrollableIndicator2 = BydScrollableIndicator.this;
            bydScrollableIndicator2.setSelection(bydScrollableIndicator2.o);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            BydScrollableIndicator.this.p = true;
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Rect f3599b;

        c(Rect rect) {
            this.f3599b = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BydScrollableIndicator.this.q) {
                return;
            }
            BydScrollableIndicator.this.r.a(BydScrollableIndicator.this.getId(), BydScrollableIndicator.this.o);
            BydScrollableIndicator.this.f3590c.setBounds(this.f3599b);
            BydScrollableIndicator.this.invalidate();
        }
    }

    class d implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f3601a;

        d(Rect rect) {
            this.f3601a = rect;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i2 = (int) (r0.left + fFloatValue);
            int i3 = this.f3601a.top;
            int i4 = BydScrollableIndicator.this.f3593f + i2;
            int i5 = this.f3601a.bottom;
            if ((fFloatValue < 0.0f && i2 < BydScrollableIndicator.this.f3593f * BydScrollableIndicator.this.o) || (fFloatValue > 0.0f && i2 > BydScrollableIndicator.this.f3593f * BydScrollableIndicator.this.o)) {
                return;
            }
            BydScrollableIndicator.this.k.set(i2, i3, i4, i5);
            BydScrollableIndicator.this.f3590c.setBounds(BydScrollableIndicator.this.k);
            BydScrollableIndicator.this.invalidate();
        }
    }

    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect f3603a;

        e(Rect rect) {
            this.f3603a = rect;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BydScrollableIndicator.this.l = this.f3603a;
            BydScrollableIndicator.this.p = false;
            BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
            bydScrollableIndicator.setSelection(bydScrollableIndicator.o);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            BydScrollableIndicator.this.p = true;
        }
    }

    public interface f {
        void a();
    }

    public interface g {
        boolean a(int i2, int i3);
    }

    public interface h {
        void a(int i2, int i3);
    }

    public BydScrollableIndicator(Context context) {
        this(context, null);
    }

    private int k(MotionEvent motionEvent) {
        if (this.f3594g.size() == 0) {
            return -1;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        for (int i2 = 0; i2 < this.f3594g.size(); i2++) {
            if (this.f3594g.get(i2).contains(x, y)) {
                return i2;
            }
        }
        return -1;
    }

    private Rect l() {
        int i2 = (this.f3590c.getBounds().left + this.f3590c.getBounds().right) / 2;
        int i3 = (this.f3590c.getBounds().top + this.f3590c.getBounds().bottom) / 2;
        for (Rect rect : this.f3594g) {
            if (rect.contains(i2, i3)) {
                return rect;
            }
        }
        return null;
    }

    private Rect m(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        for (Rect rect : this.f3594g) {
            if (rect.contains(x, y)) {
                return rect;
            }
        }
        return null;
    }

    private void n(MotionEvent motionEvent) {
        this.q = this.l.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        Rect rectM = m(motionEvent);
        this.y = rectM;
        if (rectM == null) {
            d.a.a.f.b.b.a.f("mDownRect == null");
            return;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, rectM.left - this.l.left);
        valueAnimatorOfInt.addUpdateListener(new a());
        valueAnimatorOfInt.addListener(new b());
        valueAnimatorOfInt.setDuration(this.u);
        valueAnimatorOfInt.start();
    }

    private boolean o(MotionEvent motionEvent) {
        g gVar;
        int iK = k(motionEvent);
        if (iK == -1 || iK >= getChildCount() || (gVar = this.s) == null) {
            return false;
        }
        return gVar.a(getId(), iK);
    }

    private void p(MotionEvent motionEvent) {
        if (!this.q) {
            if (this.r != null) {
                Rect rect = this.y;
                t(rect);
                setChildSelected(this.o);
                this.t.postDelayed(new c(rect), 100L);
                return;
            }
            return;
        }
        Rect rectL = l();
        if (r()) {
            rectL = this.n;
        }
        if (s()) {
            rectL = this.m;
        }
        if (rectL == null) {
            return;
        }
        float f2 = this.j;
        int i2 = this.f3593f;
        float f3 = f2 % i2;
        if (f2 > 0.0f) {
            if (f3 > i2 / 2.0f) {
                f3 = -(i2 - f3);
            }
        } else if ((-f3) > i2 / 2.0f) {
            f3 += i2;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f3, 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new d(rectL));
        valueAnimatorOfFloat.addListener(new e(rectL));
        valueAnimatorOfFloat.setDuration(this.u);
        valueAnimatorOfFloat.start();
        t(rectL);
        setChildSelected(this.o);
        h hVar = this.r;
        if (hVar != null) {
            hVar.a(getId(), this.o);
        }
    }

    private boolean q() {
        if (this.v == 0 || System.currentTimeMillis() - this.v >= this.w) {
            this.v = System.currentTimeMillis();
            return false;
        }
        this.v = System.currentTimeMillis();
        return true;
    }

    private boolean r() {
        return this.f3590c.getBounds().right >= getWidth();
    }

    private boolean s() {
        return this.f3590c.getBounds().left <= 0;
    }

    private void setChildSelected(int i2) {
        if (i2 < 0) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if (!(getChildAt(i3) instanceof FrameLayout)) {
                    throw new IllegalArgumentException("item should be in FrameLayout");
                }
                ((FrameLayout) getChildAt(i3)).getChildAt(0).setSelected(false);
            }
            return;
        }
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (!(getChildAt(i4) instanceof FrameLayout)) {
                throw new IllegalArgumentException("item should be in FrameLayout");
            }
            if (i2 != i4) {
                ((FrameLayout) getChildAt(i4)).getChildAt(0).setSelected(false);
            }
        }
        ((FrameLayout) getChildAt(i2)).getChildAt(0).setSelected(true);
    }

    private void t(Rect rect) {
        for (int i2 = 0; i2 < this.f3594g.size(); i2++) {
            if (rect.left == this.f3594g.get(i2).left) {
                this.o = i2;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.f3590c.draw(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getSelection() {
        return this.o;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.f3593f = getWidth() / getChildCount();
        int childCount = getChildCount();
        this.f3594g.clear();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            int left = childAt.getLeft();
            int top = childAt.getTop();
            int right = childAt.getRight();
            int bottom = childAt.getBottom();
            Rect rect = new Rect(left, top, right, bottom);
            this.f3594g.add(rect);
            if (i6 == this.o) {
                if (!this.q) {
                    this.k.set(left, top, right, bottom);
                    this.f3590c.setBounds(this.k);
                }
                this.l = rect;
            }
            if (i6 == 0) {
                this.m = rect;
            }
            if (i6 == childCount - 1) {
                this.n = rect;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            boolean r0 = r6.isEnabled()
            r1 = 0
            if (r0 != 0) goto Lf
            com.bydux.api.common.views.ctrl.BydScrollableIndicator$f r7 = r6.x
            if (r7 == 0) goto Le
            r7.a()
        Le:
            return r1
        Lf:
            int r0 = r7.getAction()
            r2 = 1
            if (r0 == 0) goto L73
            if (r0 == r2) goto L6f
            r1 = 2
            if (r0 == r1) goto L20
            r1 = 3
            if (r0 == r1) goto L6f
            goto L9a
        L20:
            boolean r0 = r6.q
            if (r0 != 0) goto L25
            return r2
        L25:
            float r7 = r7.getX()
            float r0 = r6.f3595h
            float r7 = r7 - r0
            r6.f3596i = r7
            r6.j = r7
            android.graphics.Rect r0 = r6.l
            int r1 = r0.left
            float r1 = (float) r1
            float r1 = r1 + r7
            int r7 = (int) r1
            int r1 = r6.f3593f
            int r3 = r7 + r1
            android.graphics.Rect r4 = r6.n
            int r4 = r4.left
            r5 = 0
            if (r7 < r4) goto L47
            int r3 = r4 + r1
            r6.j = r5
            r7 = r4
        L47:
            android.graphics.Rect r4 = r6.m
            int r4 = r4.left
            if (r7 > r4) goto L52
            int r3 = r4 + r1
            r6.j = r5
            r7 = r4
        L52:
            android.graphics.Rect r1 = r6.k
            int r4 = r1.left
            if (r4 == r7) goto L9a
            int r4 = r1.right
            if (r4 != r3) goto L5d
            goto L9a
        L5d:
            int r4 = r0.top
            int r0 = r0.bottom
            r1.set(r7, r4, r3, r0)
            android.graphics.drawable.Drawable r7 = r6.f3590c
            android.graphics.Rect r0 = r6.k
            r7.setBounds(r0)
            r6.invalidate()
            goto L9a
        L6f:
            r6.p(r7)
            goto L9a
        L73:
            boolean r0 = r6.p
            if (r0 != 0) goto L9b
            boolean r0 = r6.q()
            if (r0 != 0) goto L9b
            boolean r0 = r6.o(r7)
            if (r0 == 0) goto L84
            goto L9b
        L84:
            r0 = -1
            r6.setChildSelected(r0)
            android.graphics.drawable.Drawable r0 = r6.f3591d
            r6.f3590c = r0
            android.graphics.Rect r1 = r6.k
            r0.setBounds(r1)
            float r0 = r7.getX()
            r6.f3595h = r0
            r6.n(r7)
        L9a:
            return r2
        L9b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.ctrl.BydScrollableIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackground(int i2) {
        setBackgroundResource(i2);
    }

    public void setDisableItemListener(g gVar) {
        this.s = gVar;
    }

    public void setDisableListener(f fVar) {
        this.x = fVar;
    }

    public void setDownTouchTimeout(int i2) {
        if (this.u >= i2) {
            throw new IllegalArgumentException("ScrollableIndicator timeout should be bigger than animDuration");
        }
        this.w = i2;
    }

    public void setOnItemClickListener(h hVar) {
        this.r = hVar;
    }

    public void setSelectedBackground(Drawable drawable) {
        this.f3590c = drawable;
        this.f3591d = drawable;
        drawable.setBounds(this.k);
    }

    public void setSelection(int i2) {
        d.a.a.f.b.b.a.c("setSelection position = " + i2);
        if (i2 > getChildCount() - 1 || i2 < 0) {
            d.a.a.f.b.b.a.c("setSelection: set unSelected item!");
            return;
        }
        this.o = i2;
        if (!(getChildAt(i2) instanceof FrameLayout)) {
            throw new IllegalArgumentException("item should be in FrameLayout");
        }
        if (this.f3590c == this.f3592e) {
            this.f3590c = this.f3591d;
        }
        int childCount = getChildCount();
        this.f3594g.clear();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            Rect rect = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            this.f3594g.add(rect);
            if (i3 == this.o) {
                this.k.set(rect);
                this.f3590c.setBounds(this.k);
                ((FrameLayout) getChildAt(i3)).getChildAt(0).setSelected(true);
                this.l = rect;
            } else {
                ((FrameLayout) getChildAt(i3)).getChildAt(0).setSelected(false);
            }
        }
        invalidate();
    }

    public void setmAnimDuration(int i2) {
        this.u = i2;
    }

    public BydScrollableIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydScrollableIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f3594g = new ArrayList();
        this.k = new Rect();
        this.u = 220;
        this.v = 0L;
        this.w = 200;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.a.a.e.BydScrollableIndicator, i2, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(d.a.a.e.BydScrollableIndicator_byd_unselected_bg);
        typedArrayObtainStyledAttributes.recycle();
        setBackgroundDrawable(drawable);
        this.f3592e = drawable;
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(d.a.a.e.BydScrollableIndicator_byd_selected_bg);
        if (drawable2 == null) {
            this.f3590c = drawable2;
            this.f3591d = drawable2;
        } else {
            this.f3590c = drawable2;
            this.f3591d = drawable2;
        }
        this.f3589b = context;
        this.f3590c.setBounds(this.k);
        this.t = new Handler(Looper.getMainLooper());
    }
}
