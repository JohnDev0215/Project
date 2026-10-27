package androidx.appcompat.view.menu;

import a.b.j;
import a.b.o.i.g;
import a.b.o.i.i;
import a.b.o.i.n;
import a.b.o.i.p;
import a.b.p.c;
import a.b.p.h0;
import a.b.p.z;
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
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends z implements n.a, View.OnClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f1402e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1403f;
    public Drawable g;
    public g.b h;
    public h0 i;
    public b j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public int o;

    public class a extends h0 {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // a.b.p.h0
        public p b() {
            c.a aVar;
            b bVar = ActionMenuItemView.this.j;
            if (bVar == null || (aVar = c.this.u) == null) {
                return null;
            }
            return aVar.a();
        }

        @Override // a.b.p.h0
        public boolean c() {
            p pVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            g.b bVar = actionMenuItemView.h;
            return bVar != null && bVar.b(actionMenuItemView.f1402e) && (pVarB = b()) != null && pVarB.a();
        }
    }

    public static abstract class b {
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.k = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionMenuItemView, 0, 0);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.o = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.n = -1;
        setSaveEnabled(false);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean a() {
        return f() && this.f1402e.getIcon() == null;
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return f();
    }

    @Override // a.b.o.i.n.a
    public boolean c() {
        return true;
    }

    @Override // a.b.o.i.n.a
    public void e(i iVar, int i) {
        this.f1402e = iVar;
        setIcon(iVar.getIcon());
        setTitle(c() ? iVar.getTitleCondensed() : iVar.f189e);
        setId(iVar.f185a);
        setVisibility(iVar.isVisible() ? 0 : 8);
        setEnabled(iVar.isEnabled());
        if (iVar.hasSubMenu() && this.i == null) {
            this.i = new a();
        }
    }

    public boolean f() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    @Override // a.b.o.i.n.a
    public i getItemData() {
        return this.f1402e;
    }

    public final void h() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f1403f);
        if (this.g != null) {
            if (!((this.f1402e.y & 4) == 4) || (!this.k && !this.l)) {
                z = false;
            }
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.f1403f : null);
        CharSequence charSequence = this.f1402e.q;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = z3 ? null : this.f1402e.f189e;
        }
        setContentDescription(charSequence);
        CharSequence charSequence2 = this.f1402e.r;
        if (TextUtils.isEmpty(charSequence2)) {
            setTooltipText(z3 ? null : this.f1402e.f189e);
        } else {
            setTooltipText(charSequence2);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g.b bVar = this.h;
        if (bVar != null) {
            bVar.b(this.f1402e);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.k = g();
        h();
    }

    @Override // a.b.p.z, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        boolean zF = f();
        if (zF && (i3 = this.n) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.m) : this.m;
        if (mode != 1073741824 && this.m > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
        }
        if (zF || this.g == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.g.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        h0 h0Var;
        if (this.f1402e.hasSubMenu() && (h0Var = this.i) != null && h0Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.l != z) {
            this.l = z;
            i iVar = this.f1402e;
            if (iVar != null) {
                iVar.n.p();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.g = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.o;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            int i2 = this.o;
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
                intrinsicHeight = i2;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        h();
    }

    public void setItemInvoker(g.b bVar) {
        this.h = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.n = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b bVar) {
        this.j = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f1403f = charSequence;
        h();
    }
}
