package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC1251o3;
import defpackage.N;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {
    public View s;
    public View t;
    public Drawable u;
    public Drawable v;
    public Drawable w;
    public boolean x;
    public boolean y;
    public int z;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AbstractC1251o3.C(this, new N(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.a);
        boolean z = false;
        this.u = typedArrayObtainStyledAttributes.getDrawable(0);
        this.v = typedArrayObtainStyledAttributes.getDrawable(2);
        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == AbstractC0371Um.o4) {
            this.x = true;
            this.w = typedArrayObtainStyledAttributes.getDrawable(1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.x ? !(this.u != null || this.v != null) : this.w == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.u;
        if (drawable != null && drawable.isStateful()) {
            this.u.setState(getDrawableState());
        }
        Drawable drawable2 = this.v;
        if (drawable2 != null && drawable2.isStateful()) {
            this.v.setState(getDrawableState());
        }
        Drawable drawable3 = this.w;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.w.setState(getDrawableState());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.v;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.w;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.s = findViewById(AbstractC0371Um.h);
        this.t = findViewById(AbstractC0371Um.s);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        boolean z2 = true;
        if (this.x) {
            Drawable drawable = this.w;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.u == null) {
                z2 = false;
            } else if (this.s.getVisibility() == 0) {
                this.u.setBounds(this.s.getLeft(), this.s.getTop(), this.s.getRight(), this.s.getBottom());
            } else {
                View view = this.t;
                if (view == null || view.getVisibility() != 0) {
                    this.u.setBounds(0, 0, 0, 0);
                } else {
                    this.u.setBounds(this.t.getLeft(), this.t.getTop(), this.t.getRight(), this.t.getBottom());
                }
            }
            this.y = false;
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        if (this.s == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.z) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.s == null) {
            return;
        }
        View.MeasureSpec.getMode(i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.v;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.w;
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
    public boolean verifyDrawable(Drawable drawable) {
        if (drawable != this.u || this.x) {
            Drawable drawable2 = this.v;
            if ((drawable != this.w || !this.x) && !super.verifyDrawable(drawable)) {
                return false;
            }
        }
        return true;
    }
}
