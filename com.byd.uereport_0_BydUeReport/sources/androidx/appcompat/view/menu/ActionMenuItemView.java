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
import q.g;
import q.i;
import q.n;
import q.p;
import r.g0;
import r.t;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements n.a, View.OnClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public i f395h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f396i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f397j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public g.b f398k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public t f399l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f400m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f401n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f402o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f403p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f404q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f405r;

    public class a extends t {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // r.t
        public p b() {
            b bVar = ActionMenuItemView.this.f400m;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // r.t
        public boolean c() {
            p pVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            g.b bVar = actionMenuItemView.f398k;
            return bVar != null && bVar.a(actionMenuItemView.f395h) && (pVarB = b()) != null && pVarB.c();
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
        return f() && this.f395h.getIcon() == null;
    }

    @Override // q.n.a
    public boolean d() {
        return true;
    }

    @Override // q.n.a
    public void e(i iVar, int i8) {
        this.f395h = iVar;
        setIcon(iVar.getIcon());
        setTitle(iVar.i(this));
        setId(iVar.getItemId());
        setVisibility(iVar.isVisible() ? 0 : 8);
        setEnabled(iVar.isEnabled());
        if (iVar.hasSubMenu() && this.f399l == null) {
            this.f399l = new a();
        }
    }

    public boolean f() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i8 = configuration.screenWidthDp;
        return i8 >= 480 || (i8 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    @Override // q.n.a
    public i getItemData() {
        return this.f395h;
    }

    public final void h() {
        boolean z7 = true;
        boolean z8 = !TextUtils.isEmpty(this.f396i);
        if (this.f397j != null && (!this.f395h.A() || (!this.f401n && !this.f402o))) {
            z7 = false;
        }
        boolean z9 = z8 & z7;
        setText(z9 ? this.f396i : null);
        CharSequence contentDescription = this.f395h.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z9 ? null : this.f395h.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f395h.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            g0.a(this, z9 ? null : this.f395h.getTitle());
        } else {
            g0.a(this, tooltipText);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g.b bVar = this.f398k;
        if (bVar != null) {
            bVar.a(this.f395h);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f401n = g();
        h();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        int i10;
        boolean zF = f();
        if (zF && (i10 = this.f404q) >= 0) {
            super.setPadding(i10, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i8, i9);
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.f403p) : this.f403p;
        if (mode != 1073741824 && this.f403p > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i9);
        }
        if (zF || this.f397j == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f397j.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t tVar;
        if (this.f395h.hasSubMenu() && (tVar = this.f399l) != null && tVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z7) {
    }

    public void setChecked(boolean z7) {
    }

    public void setExpandedFormat(boolean z7) {
        if (this.f402o != z7) {
            this.f402o = z7;
            i iVar = this.f395h;
            if (iVar != null) {
                iVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f397j = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i8 = this.f405r;
            if (intrinsicWidth > i8) {
                intrinsicHeight = (int) (intrinsicHeight * (i8 / intrinsicWidth));
                intrinsicWidth = i8;
            }
            if (intrinsicHeight > i8) {
                intrinsicWidth = (int) (intrinsicWidth * (i8 / intrinsicHeight));
            } else {
                i8 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i8);
        }
        setCompoundDrawables(drawable, null, null, null);
        h();
    }

    public void setItemInvoker(g.b bVar) {
        this.f398k = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i8, int i9, int i10, int i11) {
        this.f404q = i8;
        super.setPadding(i8, i9, i10, i11);
    }

    public void setPopupCallback(b bVar) {
        this.f400m = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f396i = charSequence;
        h();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        Resources resources = context.getResources();
        this.f401n = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i8, 0);
        this.f403p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f405r = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f404q = -1;
        setSaveEnabled(false);
    }
}
