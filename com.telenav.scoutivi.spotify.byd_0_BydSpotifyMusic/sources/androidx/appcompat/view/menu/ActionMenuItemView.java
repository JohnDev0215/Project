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
import androidx.appcompat.view.menu.e;
import androidx.appcompat.view.menu.k;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.e0;
import androidx.appcompat.widget.u0;
import androidx.appcompat.widget.w;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends w implements k.a, View.OnClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    g f573k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private CharSequence f574l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Drawable f575m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    e.b f576n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private e0 f577o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    b f578p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f579q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f580r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f581s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f582t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f583u;

    private class a extends e0 {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.e0
        public j.e b() {
            b bVar = ActionMenuItemView.this.f578p;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // androidx.appcompat.widget.e0
        protected boolean c() {
            j.e eVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            e.b bVar = actionMenuItemView.f576n;
            return bVar != null && bVar.a(actionMenuItemView.f573k) && (eVarB = b()) != null && eVarB.b();
        }
    }

    public static abstract class b {
        public abstract j.e a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        return i7 >= 480 || (i7 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    private void h() {
        boolean z6 = true;
        boolean z7 = !TextUtils.isEmpty(this.f574l);
        if (this.f575m != null && (!this.f573k.B() || (!this.f579q && !this.f580r))) {
            z6 = false;
        }
        boolean z8 = z7 & z6;
        setText(z8 ? this.f574l : null);
        CharSequence contentDescription = this.f573k.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z8 ? null : this.f573k.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f573k.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            u0.a(this, z8 ? null : this.f573k.getTitle());
        } else {
            u0.a(this, tooltipText);
        }
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean a() {
        return f();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return f() && this.f573k.getIcon() == null;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public boolean d() {
        return true;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public void e(g gVar, int i7) {
        this.f573k = gVar;
        setIcon(gVar.getIcon());
        setTitle(gVar.i(this));
        setId(gVar.getItemId());
        setVisibility(gVar.isVisible() ? 0 : 8);
        setEnabled(gVar.isEnabled());
        if (gVar.hasSubMenu() && this.f577o == null) {
            this.f577o = new a();
        }
    }

    public boolean f() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.view.menu.k.a
    public g getItemData() {
        return this.f573k;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        e.b bVar = this.f576n;
        if (bVar != null) {
            bVar.a(this.f573k);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f579q = g();
        h();
    }

    @Override // androidx.appcompat.widget.w, android.widget.TextView, android.view.View
    protected void onMeasure(int i7, int i8) {
        int i9;
        boolean zF = f();
        if (zF && (i9 = this.f582t) >= 0) {
            super.setPadding(i9, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.f581s) : this.f581s;
        if (mode != 1073741824 && this.f581s > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i8);
        }
        if (zF || this.f575m == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f575m.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        e0 e0Var;
        if (this.f573k.hasSubMenu() && (e0Var = this.f577o) != null && e0Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z6) {
    }

    public void setChecked(boolean z6) {
    }

    public void setExpandedFormat(boolean z6) {
        if (this.f580r != z6) {
            this.f580r = z6;
            g gVar = this.f573k;
            if (gVar != null) {
                gVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f575m = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i7 = this.f583u;
            if (intrinsicWidth > i7) {
                intrinsicHeight = (int) (intrinsicHeight * (i7 / intrinsicWidth));
                intrinsicWidth = i7;
            }
            if (intrinsicHeight > i7) {
                intrinsicWidth = (int) (intrinsicWidth * (i7 / intrinsicHeight));
            } else {
                i7 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i7);
        }
        setCompoundDrawables(drawable, null, null, null);
        h();
    }

    public void setItemInvoker(e.b bVar) {
        this.f576n = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        this.f582t = i7;
        super.setPadding(i7, i8, i9, i10);
    }

    public void setPopupCallback(b bVar) {
        this.f578p = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f574l = charSequence;
        h();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        Resources resources = context.getResources();
        this.f579q = g();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.ActionMenuItemView, i7, 0);
        this.f581s = typedArrayObtainStyledAttributes.getDimensionPixelSize(e.j.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f583u = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f582t = -1;
        setSaveEnabled(false);
    }
}
