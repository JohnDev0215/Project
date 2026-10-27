package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f755e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private View f756g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private View f757h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private View f758i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    Drawable f759j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    Drawable f760k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    Drawable f761l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    boolean f762m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    boolean f763n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f764o;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        i0.u.n0(this, new b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.ActionBar);
        this.f759j = typedArrayObtainStyledAttributes.getDrawable(e.j.ActionBar_background);
        this.f760k = typedArrayObtainStyledAttributes.getDrawable(e.j.ActionBar_backgroundStacked);
        this.f764o = typedArrayObtainStyledAttributes.getDimensionPixelSize(e.j.ActionBar_height, -1);
        boolean z6 = true;
        if (getId() == e.f.split_action_bar) {
            this.f762m = true;
            this.f761l = typedArrayObtainStyledAttributes.getDrawable(e.j.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f762m ? this.f759j != null || this.f760k != null : this.f761l != null) {
            z6 = false;
        }
        setWillNotDraw(z6);
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
        Drawable drawable = this.f759j;
        if (drawable != null && drawable.isStateful()) {
            this.f759j.setState(getDrawableState());
        }
        Drawable drawable2 = this.f760k;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f760k.setState(getDrawableState());
        }
        Drawable drawable3 = this.f761l;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f761l.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f756g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f759j;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f760k;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f761l;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f757h = findViewById(e.f.action_bar);
        this.f758i = findViewById(e.f.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f755e || super.onInterceptTouchEvent(motionEvent);
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
            android.view.View r5 = r4.f756g
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
            boolean r6 = r4.f762m
            if (r6 == 0) goto L4b
            android.graphics.drawable.Drawable r5 = r4.f761l
            if (r5 == 0) goto L48
            int r6 = r4.getMeasuredWidth()
            int r7 = r4.getMeasuredHeight()
            r5.setBounds(r0, r0, r6, r7)
            goto Lbe
        L48:
            r9 = r0
            goto Lbe
        L4b:
            android.graphics.drawable.Drawable r6 = r4.f759j
            if (r6 == 0) goto La3
            android.view.View r6 = r4.f757h
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L75
            android.graphics.drawable.Drawable r6 = r4.f759j
            android.view.View r7 = r4.f757h
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f757h
            int r8 = r8.getTop()
            android.view.View r0 = r4.f757h
            int r0 = r0.getRight()
            android.view.View r2 = r4.f757h
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L75:
            android.view.View r6 = r4.f758i
            if (r6 == 0) goto L9d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L9d
            android.graphics.drawable.Drawable r6 = r4.f759j
            android.view.View r7 = r4.f758i
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f758i
            int r8 = r8.getTop()
            android.view.View r0 = r4.f758i
            int r0 = r0.getRight()
            android.view.View r2 = r4.f758i
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L9d:
            android.graphics.drawable.Drawable r6 = r4.f759j
            r6.setBounds(r0, r0, r0, r0)
        La2:
            r0 = r9
        La3:
            r4.f763n = r1
            if (r1 == 0) goto L48
            android.graphics.drawable.Drawable r6 = r4.f760k
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
    public void onMeasure(int i7, int i8) {
        int i9;
        if (this.f757h == null && View.MeasureSpec.getMode(i8) == Integer.MIN_VALUE && (i9 = this.f764o) >= 0) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.min(i9, View.MeasureSpec.getSize(i8)), Integer.MIN_VALUE);
        }
        super.onMeasure(i7, i8);
        if (this.f757h == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i8);
        View view = this.f756g;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!b(this.f757h) ? a(this.f757h) : !b(this.f758i) ? a(this.f758i) : 0) + a(this.f756g), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i8) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f759j;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f759j);
        }
        this.f759j = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f757h;
            if (view != null) {
                this.f759j.setBounds(view.getLeft(), this.f757h.getTop(), this.f757h.getRight(), this.f757h.getBottom());
            }
        }
        boolean z6 = true;
        if (!this.f762m ? this.f759j != null || this.f760k != null : this.f761l != null) {
            z6 = false;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f761l;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f761l);
        }
        this.f761l = drawable;
        boolean z6 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f762m && (drawable2 = this.f761l) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f762m ? !(this.f759j != null || this.f760k != null) : this.f761l == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f760k;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f760k);
        }
        this.f760k = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f763n && (drawable2 = this.f760k) != null) {
                drawable2.setBounds(this.f756g.getLeft(), this.f756g.getTop(), this.f756g.getRight(), this.f756g.getBottom());
            }
        }
        boolean z6 = true;
        if (!this.f762m ? this.f759j != null || this.f760k != null : this.f761l != null) {
            z6 = false;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(l0 l0Var) {
        View view = this.f756g;
        if (view != null) {
            removeView(view);
        }
        this.f756g = l0Var;
        if (l0Var != null) {
            addView(l0Var);
            ViewGroup.LayoutParams layoutParams = l0Var.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            l0Var.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z6) {
        this.f755e = z6;
        setDescendantFocusability(z6 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f759j;
        if (drawable != null) {
            drawable.setVisible(z6, false);
        }
        Drawable drawable2 = this.f760k;
        if (drawable2 != null) {
            drawable2.setVisible(z6, false);
        }
        Drawable drawable3 = this.f761l;
        if (drawable3 != null) {
            drawable3.setVisible(z6, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i7) {
        if (i7 != 0) {
            return super.startActionModeForChild(view, callback, i7);
        }
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f759j && !this.f762m) || (drawable == this.f760k && this.f763n) || ((drawable == this.f761l && this.f762m) || super.verifyDrawable(drawable));
    }
}
