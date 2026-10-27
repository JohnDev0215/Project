package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import p.a4;
import p.b4;
import p.fa;
import p.h4;
import p.im2;
import p.pj1;
import p.ri1;
import p.si1;
import p.xi1;
import p.yz1;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends fa implements pj1, View.OnClickListener, h4 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public xi1 f4p;
    public CharSequence q;
    public Drawable r;
    public ri1 s;
    public a4 t;
    public b4 u;
    public boolean v;
    public boolean w;
    public final int x;
    public int y;
    public final int z;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.v = p();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yz1.c, 0, 0);
        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.z = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.y = -1;
        setSaveEnabled(false);
    }

    @Override // p.h4
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // p.h4
    public final boolean b() {
        return (TextUtils.isEmpty(getText()) ^ true) && this.f4p.getIcon() == null;
    }

    @Override // p.pj1
    public final void c(xi1 xi1Var) {
        this.f4p = xi1Var;
        setIcon(xi1Var.getIcon());
        setTitle(xi1Var.getTitleCondensed());
        setId(xi1Var.a);
        setVisibility(xi1Var.isVisible() ? 0 : 8);
        setEnabled(xi1Var.isEnabled());
        if (xi1Var.hasSubMenu() && this.t == null) {
            this.t = new a4(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // p.pj1
    public xi1 getItemData() {
        return this.f4p;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ri1 ri1Var = this.s;
        if (ri1Var != null) {
            ri1Var.d(this.f4p);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.v = p();
        q();
    }

    @Override // p.fa, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean z = !TextUtils.isEmpty(getText());
        if (z && (i3 = this.y) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int i4 = this.x;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i4) : i4;
        if (mode != 1073741824 && i4 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
        }
        if (z || this.r == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.r.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        a4 a4Var;
        if (this.f4p.hasSubMenu() && (a4Var = this.t) != null && a4Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean p() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void q() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.q);
        if (this.r != null && ((this.f4p.y & 4) != 4 || (!this.v && !this.w))) {
            z = false;
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.q : null);
        CharSequence charSequence = this.f4p.q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z3 ? null : this.f4p.e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f4p.r;
        if (TextUtils.isEmpty(charSequence2)) {
            im2.a(this, z3 ? null : this.f4p.e);
        } else {
            im2.a(this, charSequence2);
        }
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.w != z) {
            this.w = z;
            xi1 xi1Var = this.f4p;
            if (xi1Var != null) {
                si1 si1Var = xi1Var.n;
                si1Var.k = true;
                si1Var.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.r = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.z;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            if (intrinsicHeight > i) {
                intrinsicWidth = (int) (intrinsicWidth * (i / intrinsicHeight));
            } else {
                i = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i);
        }
        setCompoundDrawables(drawable, null, null, null);
        q();
    }

    public void setItemInvoker(ri1 ri1Var) {
        this.s = ri1Var;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        this.y = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b4 b4Var) {
        this.u = b4Var;
    }

    public void setTitle(CharSequence charSequence) {
        this.q = charSequence;
        q();
    }
}
