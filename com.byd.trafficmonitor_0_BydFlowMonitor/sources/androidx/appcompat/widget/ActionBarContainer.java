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
import b.b.f;
import b.b.j;
import b.b.q.b;
import b.h.m.t;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f344b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f345c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f346d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f347e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f349g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f350h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f351i;
    public boolean j;
    public int k;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public final int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f348f;
        if (drawable != null && drawable.isStateful()) {
            this.f348f.setState(getDrawableState());
        }
        Drawable drawable2 = this.f349g;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f349g.setState(getDrawableState());
        }
        Drawable drawable3 = this.f350h;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f350h.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f345c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f348f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f349g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f350h;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f346d = findViewById(f.action_bar);
        this.f347e = findViewById(f.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f344b || super.onInterceptTouchEvent(motionEvent);
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
            android.view.View r5 = r4.f345c
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
            boolean r6 = r4.f351i
            if (r6 == 0) goto L4b
            android.graphics.drawable.Drawable r5 = r4.f350h
            if (r5 == 0) goto L48
            int r6 = r4.getMeasuredWidth()
            int r7 = r4.getMeasuredHeight()
            r5.setBounds(r0, r0, r6, r7)
            goto Lbe
        L48:
            r9 = r0
            goto Lbe
        L4b:
            android.graphics.drawable.Drawable r6 = r4.f348f
            if (r6 == 0) goto La3
            android.view.View r6 = r4.f346d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L75
            android.graphics.drawable.Drawable r6 = r4.f348f
            android.view.View r7 = r4.f346d
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f346d
            int r8 = r8.getTop()
            android.view.View r0 = r4.f346d
            int r0 = r0.getRight()
            android.view.View r2 = r4.f346d
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L75:
            android.view.View r6 = r4.f347e
            if (r6 == 0) goto L9d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L9d
            android.graphics.drawable.Drawable r6 = r4.f348f
            android.view.View r7 = r4.f347e
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f347e
            int r8 = r8.getTop()
            android.view.View r0 = r4.f347e
            int r0 = r0.getRight()
            android.view.View r2 = r4.f347e
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L9d:
            android.graphics.drawable.Drawable r6 = r4.f348f
            r6.setBounds(r0, r0, r0, r0)
        La2:
            r0 = r9
        La3:
            r4.j = r1
            if (r1 == 0) goto L48
            android.graphics.drawable.Drawable r6 = r4.f349g
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
    public void onMeasure(int i2, int i3) {
        int i4;
        if (this.f346d == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = this.k) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i3)), Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
        if (this.f346d == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        View view = this.f345c;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!b(this.f346d) ? a(this.f346d) : !b(this.f347e) ? a(this.f347e) : 0) + a(this.f345c), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i3) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f348f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f348f);
        }
        this.f348f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f346d;
            if (view != null) {
                this.f348f.setBounds(view.getLeft(), this.f346d.getTop(), this.f346d.getRight(), this.f346d.getBottom());
            }
        }
        boolean z = true;
        if (!this.f351i ? this.f348f != null || this.f349g != null : this.f350h != null) {
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
        Drawable drawable3 = this.f350h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f350h);
        }
        this.f350h = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f351i && (drawable2 = this.f350h) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f351i ? !(this.f348f != null || this.f349g != null) : this.f350h == null) {
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
        Drawable drawable3 = this.f349g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f349g);
        }
        this.f349g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.j && (drawable2 = this.f349g) != null) {
                drawable2.setBounds(this.f345c.getLeft(), this.f345c.getTop(), this.f345c.getRight(), this.f345c.getBottom());
            }
        }
        boolean z = true;
        if (!this.f351i ? this.f348f != null || this.f349g != null : this.f350h != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f345c;
        if (view != null) {
            removeView(view);
        }
        this.f345c = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f344b = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.f348f;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f349g;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f350h;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f348f && !this.f351i) || (drawable == this.f349g && this.j) || ((drawable == this.f350h && this.f351i) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t.n0(this, new b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionBar);
        this.f348f = typedArrayObtainStyledAttributes.getDrawable(j.ActionBar_background);
        this.f349g = typedArrayObtainStyledAttributes.getDrawable(j.ActionBar_backgroundStacked);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.ActionBar_height, -1);
        boolean z = true;
        if (getId() == f.split_action_bar) {
            this.f351i = true;
            this.f350h = typedArrayObtainStyledAttributes.getDrawable(j.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f351i ? this.f348f != null || this.f349g != null : this.f350h != null) {
            z = false;
        }
        setWillNotDraw(z);
    }
}
