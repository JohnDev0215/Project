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
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import o0.t;
import r.b;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f427d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f428e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f429f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f430g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f431h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f432i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f433j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f434k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f435l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f436m;

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
        Drawable drawable = this.f431h;
        if (drawable != null && drawable.isStateful()) {
            this.f431h.setState(getDrawableState());
        }
        Drawable drawable2 = this.f432i;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f432i.setState(getDrawableState());
        }
        Drawable drawable3 = this.f433j;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f433j.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f428e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f431h;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f432i;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f433j;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f429f = findViewById(R$id.action_bar);
        this.f430g = findViewById(R$id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f427d || super.onInterceptTouchEvent(motionEvent);
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
            android.view.View r5 = r4.f428e
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
            boolean r6 = r4.f434k
            if (r6 == 0) goto L4b
            android.graphics.drawable.Drawable r5 = r4.f433j
            if (r5 == 0) goto L48
            int r6 = r4.getMeasuredWidth()
            int r7 = r4.getMeasuredHeight()
            r5.setBounds(r0, r0, r6, r7)
            goto Lbe
        L48:
            r9 = r0
            goto Lbe
        L4b:
            android.graphics.drawable.Drawable r6 = r4.f431h
            if (r6 == 0) goto La3
            android.view.View r6 = r4.f429f
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L75
            android.graphics.drawable.Drawable r6 = r4.f431h
            android.view.View r7 = r4.f429f
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f429f
            int r8 = r8.getTop()
            android.view.View r0 = r4.f429f
            int r0 = r0.getRight()
            android.view.View r2 = r4.f429f
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L75:
            android.view.View r6 = r4.f430g
            if (r6 == 0) goto L9d
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L9d
            android.graphics.drawable.Drawable r6 = r4.f431h
            android.view.View r7 = r4.f430g
            int r7 = r7.getLeft()
            android.view.View r8 = r4.f430g
            int r8 = r8.getTop()
            android.view.View r0 = r4.f430g
            int r0 = r0.getRight()
            android.view.View r2 = r4.f430g
            int r2 = r2.getBottom()
            r6.setBounds(r7, r8, r0, r2)
            goto La2
        L9d:
            android.graphics.drawable.Drawable r6 = r4.f431h
            r6.setBounds(r0, r0, r0, r0)
        La2:
            r0 = r9
        La3:
            r4.f435l = r1
            if (r1 == 0) goto L48
            android.graphics.drawable.Drawable r6 = r4.f432i
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
    public void onMeasure(int i8, int i9) {
        int i10;
        if (this.f429f == null && View.MeasureSpec.getMode(i9) == Integer.MIN_VALUE && (i10 = this.f436m) >= 0) {
            i9 = View.MeasureSpec.makeMeasureSpec(Math.min(i10, View.MeasureSpec.getSize(i9)), Integer.MIN_VALUE);
        }
        super.onMeasure(i8, i9);
        if (this.f429f == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i9);
        View view = this.f428e;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!b(this.f429f) ? a(this.f429f) : !b(this.f430g) ? a(this.f430g) : 0) + a(this.f428e), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i9) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f431h;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f431h);
        }
        this.f431h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f429f;
            if (view != null) {
                this.f431h.setBounds(view.getLeft(), this.f429f.getTop(), this.f429f.getRight(), this.f429f.getBottom());
            }
        }
        boolean z7 = true;
        if (!this.f434k ? this.f431h != null || this.f432i != null : this.f433j != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f433j;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f433j);
        }
        this.f433j = drawable;
        boolean z7 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f434k && (drawable2 = this.f433j) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f434k ? !(this.f431h != null || this.f432i != null) : this.f433j == null) {
            z7 = true;
        }
        setWillNotDraw(z7);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f432i;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f432i);
        }
        this.f432i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f435l && (drawable2 = this.f432i) != null) {
                drawable2.setBounds(this.f428e.getLeft(), this.f428e.getTop(), this.f428e.getRight(), this.f428e.getBottom());
            }
        }
        boolean z7 = true;
        if (!this.f434k ? this.f431h != null || this.f432i != null : this.f433j != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f428e;
        if (view != null) {
            removeView(view);
        }
        this.f428e = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z7) {
        this.f427d = z7;
        setDescendantFocusability(z7 ? 393216 : CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        boolean z7 = i8 == 0;
        Drawable drawable = this.f431h;
        if (drawable != null) {
            drawable.setVisible(z7, false);
        }
        Drawable drawable2 = this.f432i;
        if (drawable2 != null) {
            drawable2.setVisible(z7, false);
        }
        Drawable drawable3 = this.f433j;
        if (drawable3 != null) {
            drawable3.setVisible(z7, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i8) {
        if (i8 != 0) {
            return super.startActionModeForChild(view, callback, i8);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f431h && !this.f434k) || (drawable == this.f432i && this.f435l) || ((drawable == this.f433j && this.f434k) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t.o0(this, new b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBar);
        this.f431h = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_background);
        this.f432i = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundStacked);
        this.f436m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_height, -1);
        boolean z7 = true;
        if (getId() == R$id.split_action_bar) {
            this.f434k = true;
            this.f433j = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f434k ? this.f431h != null || this.f432i != null : this.f433j != null) {
            z7 = false;
        }
        setWillNotDraw(z7);
    }
}
