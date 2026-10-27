package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import n.g;
import n.i;
import n.n;
import n.p;
import o.g0;
import o.t;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements n.a, View.OnClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f288f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f289g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f290h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g.b f291i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public t f292j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f293k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f294l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f295m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f296n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f297o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f298p;

    public class a extends t {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // o.t
        public p b() {
            b bVar = ActionMenuItemView.this.f293k;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // o.t
        public boolean c() {
            p pVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            g.b bVar = actionMenuItemView.f291i;
            return bVar != null && bVar.a(actionMenuItemView.f288f) && (pVarB = b()) != null && pVarB.b();
        }
    }

    public static abstract class b {
        public abstract p a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean a() {
        return f();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return f() && this.f288f.getIcon() == null;
    }

    @Override // n.n.a
    public boolean d() {
        return true;
    }

    @Override // n.n.a
    public void e(i iVar, int i3) {
        this.f288f = iVar;
        setIcon(iVar.getIcon());
        setTitle(iVar.i(this));
        setId(iVar.getItemId());
        setVisibility(iVar.isVisible() ? 0 : 8);
        setEnabled(iVar.isEnabled());
        if (iVar.hasSubMenu() && this.f292j == null) {
            this.f292j = new a();
        }
    }

    public boolean f() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i3 = configuration.screenWidthDp;
        return i3 >= 480 || (i3 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    @Override // n.n.a
    public i getItemData() {
        return this.f288f;
    }

    public final void h() {
        boolean z3 = true;
        boolean z4 = !TextUtils.isEmpty(this.f289g);
        if (this.f290h != null && (!this.f288f.A() || (!this.f294l && !this.f295m))) {
            z3 = false;
        }
        boolean z5 = z4 & z3;
        setText(z5 ? this.f289g : null);
        CharSequence contentDescription = this.f288f.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z5 ? null : this.f288f.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f288f.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            g0.a(this, z5 ? null : this.f288f.getTitle());
        } else {
            g0.a(this, tooltipText);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g.b bVar = this.f291i;
        if (bVar != null) {
            bVar.a(this.f288f);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f294l = g();
        h();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i3, int i4) {
        int i5;
        boolean zF = f();
        if (zF && (i5 = this.f297o) >= 0) {
            super.setPadding(i5, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i3, i4);
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.f296n) : this.f296n;
        if (mode != 1073741824 && this.f296n > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i4);
        }
        if (zF || this.f290h == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f290h.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t tVar;
        if (this.f288f.hasSubMenu() && (tVar = this.f292j) != null && tVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z3) {
    }

    public void setChecked(boolean z3) {
    }

    public void setExpandedFormat(boolean z3) {
        if (this.f295m != z3) {
            this.f295m = z3;
            i iVar = this.f288f;
            if (iVar != null) {
                iVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f290h = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i3 = this.f298p;
            if (intrinsicWidth > i3) {
                intrinsicHeight = (int) (intrinsicHeight * (i3 / intrinsicWidth));
                intrinsicWidth = i3;
            }
            if (intrinsicHeight > i3) {
                intrinsicWidth = (int) (intrinsicWidth * (i3 / intrinsicHeight));
            } else {
                i3 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i3);
        }
        setCompoundDrawables(drawable, null, null, null);
        h();
    }

    public void setItemInvoker(g.b bVar) {
        this.f291i = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i3, int i4, int i5, int i6) {
        this.f297o = i3;
        super.setPadding(i3, i4, i5, i6);
    }

    public void setPopupCallback(b bVar) {
        this.f293k = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f289g = charSequence;
        h();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        Resources resources = context.getResources();
        this.f294l = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i3, 0);
        this.f296n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f298p = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f297o = -1;
        setSaveEnabled(false);
    }
}
