package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.stingray.drivemodedetector.R;
import n.AbstractC1850a;
import r.C2238a;
import r.o0;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f11290m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f11291n;
    public Drawable o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f11292p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f11293q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f11294r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11295s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f11296t;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackground(new C2238a(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.f19352a);
        boolean z6 = false;
        this.o = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f11292p = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f11296t = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f11294r = true;
            this.f11293q = typedArrayObtainStyledAttributes.getDrawable(1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f11294r ? !(this.o != null || this.f11292p != null) : this.f11293q == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.o;
        if (drawable != null && drawable.isStateful()) {
            this.o.setState(getDrawableState());
        }
        Drawable drawable2 = this.f11292p;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f11292p.setState(getDrawableState());
        }
        Drawable drawable3 = this.f11293q;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f11293q.setState(getDrawableState());
    }

    public View getTabContainer() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.o;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f11292p;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f11293q;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f11290m = findViewById(R.id.action_bar);
        this.f11291n = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.l || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        boolean z10 = true;
        if (this.f11294r) {
            Drawable drawable = this.f11293q;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z10 = false;
            }
        } else {
            if (this.o == null) {
                z10 = false;
            } else if (this.f11290m.getVisibility() == 0) {
                this.o.setBounds(this.f11290m.getLeft(), this.f11290m.getTop(), this.f11290m.getRight(), this.f11290m.getBottom());
            } else {
                View view = this.f11291n;
                if (view == null || view.getVisibility() != 0) {
                    this.o.setBounds(0, 0, 0, 0);
                } else {
                    this.o.setBounds(this.f11291n.getLeft(), this.f11291n.getTop(), this.f11291n.getRight(), this.f11291n.getBottom());
                }
            }
            this.f11295s = false;
        }
        if (z10) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        int i11;
        if (this.f11290m == null && View.MeasureSpec.getMode(i10) == Integer.MIN_VALUE && (i11 = this.f11296t) >= 0) {
            i10 = View.MeasureSpec.makeMeasureSpec(Math.min(i11, View.MeasureSpec.getSize(i10)), Integer.MIN_VALUE);
        }
        super.onMeasure(i7, i10);
        if (this.f11290m == null) {
            return;
        }
        View.MeasureSpec.getMode(i10);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.o;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.o);
        }
        this.o = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f11290m;
            if (view != null) {
                this.o.setBounds(view.getLeft(), this.f11290m.getTop(), this.f11290m.getRight(), this.f11290m.getBottom());
            }
        }
        boolean z6 = false;
        if (!this.f11294r ? !(this.o != null || this.f11292p != null) : this.f11293q == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f11293q;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f11293q);
        }
        this.f11293q = drawable;
        boolean z6 = this.f11294r;
        boolean z10 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z6 && (drawable2 = this.f11293q) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z6 ? !(this.o != null || this.f11292p != null) : this.f11293q == null) {
            z10 = true;
        }
        setWillNotDraw(z10);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.f11292p;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f11292p);
        }
        this.f11292p = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f11295s && this.f11292p != null) {
                throw null;
            }
        }
        boolean z6 = false;
        if (!this.f11294r ? !(this.o != null || this.f11292p != null) : this.f11293q == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(o0 o0Var) {
    }

    public void setTransitioning(boolean z6) {
        this.l = z6;
        setDescendantFocusability(z6 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.o;
        if (drawable != null) {
            drawable.setVisible(z6, false);
        }
        Drawable drawable2 = this.f11292p;
        if (drawable2 != null) {
            drawable2.setVisible(z6, false);
        }
        Drawable drawable3 = this.f11293q;
        if (drawable3 != null) {
            drawable3.setVisible(z6, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.o;
        boolean z6 = this.f11294r;
        return (drawable == drawable2 && !z6) || (drawable == this.f11292p && this.f11295s) || ((drawable == this.f11293q && z6) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i7) {
        if (i7 != 0) {
            return super.startActionModeForChild(view, callback, i7);
        }
        return null;
    }
}
