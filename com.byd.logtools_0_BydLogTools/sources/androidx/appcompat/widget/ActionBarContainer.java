package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f349c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f350d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f351e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Drawable f352f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Drawable f353g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    Drawable f354h;
    boolean i;
    boolean j;
    private int k;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    private int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f352f;
        if (drawable != null && drawable.isStateful()) {
            this.f352f.setState(getDrawableState());
        }
        Drawable drawable2 = this.f353g;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f353g.setState(getDrawableState());
        }
        Drawable drawable3 = this.f354h;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f354h.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f349c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f352f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f353g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f354h;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f350d = findViewById(b.a.f.f2091a);
        this.f351e = findViewById(b.a.f.f2096f);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f348b || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[PHI: r0
  0x0048: PHI (r0v8 boolean) = (r0v1 boolean), (r0v1 boolean), (r0v0 boolean) binds: [B:31:0x00a5, B:33:0x00a9, B:15:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            super.onLayout(r5, r6, r7, r8, r9)
            android.view.View r5 = r4.f349c
            r7 = 8
            r9 = 1
            r0 = 0
            if (r5 == 0) goto L13
            int r1 = r5.getVisibility()
            if (r1 == r7) goto L13
            r1 = r9
            goto L14
        L13:
            r1 = r0
        L14:
            if (r5 == 0) goto L33
            int r2 = r5.getVisibility()
            if (r2 == r7) goto L33
            int r7 = r4.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r2 = r5.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r2 = (android.widget.FrameLayout.LayoutParams) r2
            int r3 = r5.getMeasuredHeight()
            int r3 = r7 - r3
            int r2 = r2.bottomMargin
            int r3 = r3 - r2
            int r7 = r7 - r2
            r5.layout(r6, r3, r8, r7)
        L33:
            boolean r6 = r4.i
            if (r6 == 0) goto L4b
            android.graphics.drawable.Drawable r5 = r4.f354h
            if (r5 == 0) goto L48
            int r6 = r4.getMeasuredWidth()
            int r7 = r4.getMeasuredHeight()
            r5.setBounds(r0, r0, r6, r7)
            goto Lbe
        L48:
            r9 = r0
            goto Lbe
        L4b:
            android.graphics.drawable.Drawable r6 = r4.f352f
            if (r6 == 0) goto La3
            android.view.View r6 = r4.f350d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L75
            android.graphics.drawable.Drawable r6 = r4.f352f
            android.view.View r7 = r4.f350d
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f350d
            int r8 = r8.getTop()
            android.view.View r0 = r4.f350d
            int r0 = r0.getRight()
            android.view.View r2 = r4.f350d
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L75:
            android.view.View r6 = r4.f351e
            if (r6 == 0) goto L9d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L9d
            android.graphics.drawable.Drawable r6 = r4.f352f
            android.view.View r7 = r4.f351e
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f351e
            int r8 = r8.getTop()
            android.view.View r0 = r4.f351e
            int r0 = r0.getRight()
            android.view.View r2 = r4.f351e
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L9d:
            android.graphics.drawable.Drawable r6 = r4.f352f
            r6.setBounds(r0, r0, r0, r0)
        La2:
            r0 = r9
        La3:
            r4.j = r1
            if (r1 == 0) goto L48
            android.graphics.drawable.Drawable r6 = r4.f353g
            if (r6 == 0) goto L48
            int r7 = r5.getLeft()
            int r8 = r5.getTop()
            int r0 = r5.getRight()
            int r5 = r5.getBottom()
            r6.setBounds(r7, r8, r0, r5)
        Lbe:
            if (r9 == 0) goto Lc3
            r4.invalidate()
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        if (this.f350d == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.k) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.f350d == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        View view = this.f349c;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!b(this.f350d) ? a(this.f350d) : !b(this.f351e) ? a(this.f351e) : 0) + a(this.f349c), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f352f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f352f);
        }
        this.f352f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f350d;
            if (view != null) {
                this.f352f.setBounds(view.getLeft(), this.f350d.getTop(), this.f350d.getRight(), this.f350d.getBottom());
            }
        }
        boolean z = true;
        if (!this.i ? this.f352f != null || this.f353g != null : this.f354h != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f354h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f354h);
        }
        this.f354h = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.i && (drawable2 = this.f354h) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.i ? !(this.f352f != null || this.f353g != null) : this.f354h == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f353g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f353g);
        }
        this.f353g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.j && (drawable2 = this.f353g) != null) {
                drawable2.setBounds(this.f349c.getLeft(), this.f349c.getTop(), this.f349c.getRight(), this.f349c.getBottom());
            }
        }
        boolean z = true;
        if (!this.i ? this.f352f != null || this.f353g != null : this.f354h != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f349c;
        if (view != null) {
            removeView(view);
        }
        this.f349c = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f348b = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f352f;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f353g;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f354h;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f352f && !this.i) || (drawable == this.f353g && this.j) || ((drawable == this.f354h && this.i) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b.g.k.t.m0(this, new b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.a.j.f2120a);
        this.f352f = typedArrayObtainStyledAttributes.getDrawable(b.a.j.f2121b);
        this.f353g = typedArrayObtainStyledAttributes.getDrawable(b.a.j.f2123d);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.a.j.j, -1);
        boolean z = true;
        if (getId() == b.a.f.N) {
            this.i = true;
            this.f354h = typedArrayObtainStyledAttributes.getDrawable(b.a.j.f2122c);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.i ? this.f352f != null || this.f353g != null : this.f354h != null) {
            z = false;
        }
        setWillNotDraw(z);
    }
}
