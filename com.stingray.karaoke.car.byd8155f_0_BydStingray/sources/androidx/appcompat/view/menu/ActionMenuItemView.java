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
import android.widget.Button;
import n.AbstractC1850a;
import q.AbstractC2145b;
import q.InterfaceC2150g;
import q.InterfaceC2158o;
import q.MenuC2151h;
import q.MenuItemC2152i;
import q.ViewOnTouchListenerC2144a;
import r.B0;
import r.C2218F;
import r.InterfaceC2251j;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends C2218F implements InterfaceC2158o, View.OnClickListener, InterfaceC2251j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f11266A;
    public final int B;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public MenuItemC2152i f11267r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f11268s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Drawable f11269t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public InterfaceC2150g f11270u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ViewOnTouchListenerC2144a f11271v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AbstractC2145b f11272w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f11273x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f11274y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f11275z;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.f11273x = h();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.c, 0, 0);
        this.f11275z = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.B = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f11266A = -1;
        setSaveEnabled(false);
    }

    @Override // r.InterfaceC2251j
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // q.InterfaceC2158o
    public final void b(MenuItemC2152i menuItemC2152i) {
        this.f11267r = menuItemC2152i;
        setIcon(menuItemC2152i.getIcon());
        setTitle(menuItemC2152i.getTitleCondensed());
        setId(menuItemC2152i.f21067a);
        setVisibility(menuItemC2152i.isVisible() ? 0 : 8);
        setEnabled(menuItemC2152i.isEnabled());
        if (menuItemC2152i.hasSubMenu() && this.f11271v == null) {
            this.f11271v = new ViewOnTouchListenerC2144a(this);
        }
    }

    @Override // r.InterfaceC2251j
    public final boolean c() {
        return !TextUtils.isEmpty(getText()) && this.f11267r.getIcon() == null;
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // q.InterfaceC2158o
    public MenuItemC2152i getItemData() {
        return this.f11267r;
    }

    public final boolean h() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        return i7 >= 480 || (i7 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void i() {
        boolean z6 = true;
        boolean z10 = !TextUtils.isEmpty(this.f11268s);
        if (this.f11269t != null && ((this.f11267r.f21087y & 4) != 4 || (!this.f11273x && !this.f11274y))) {
            z6 = false;
        }
        boolean z11 = z10 & z6;
        setText(z11 ? this.f11268s : null);
        CharSequence charSequence = this.f11267r.f21079q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z11 ? null : this.f11267r.f21069e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f11267r.f21080r;
        if (TextUtils.isEmpty(charSequence2)) {
            B0.a(this, z11 ? null : this.f11267r.f21069e);
        } else {
            B0.a(this, charSequence2);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        InterfaceC2150g interfaceC2150g = this.f11270u;
        if (interfaceC2150g != null) {
            interfaceC2150g.a(this.f11267r);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f11273x = h();
        i();
    }

    @Override // r.C2218F, android.widget.TextView, android.view.View
    public final void onMeasure(int i7, int i10) {
        int i11;
        boolean zIsEmpty = TextUtils.isEmpty(getText());
        if (!zIsEmpty && (i11 = this.f11266A) >= 0) {
            super.setPadding(i11, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i7, i10);
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int measuredWidth = getMeasuredWidth();
        int i12 = this.f11275z;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i12) : i12;
        if (mode != 1073741824 && i12 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i10);
        }
        if (!zIsEmpty || this.f11269t == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f11269t.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewOnTouchListenerC2144a viewOnTouchListenerC2144a;
        if (this.f11267r.hasSubMenu() && (viewOnTouchListenerC2144a = this.f11271v) != null && viewOnTouchListenerC2144a.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z6) {
    }

    public void setChecked(boolean z6) {
    }

    public void setExpandedFormat(boolean z6) {
        if (this.f11274y != z6) {
            this.f11274y = z6;
            MenuItemC2152i menuItemC2152i = this.f11267r;
            if (menuItemC2152i != null) {
                MenuC2151h menuC2151h = menuItemC2152i.f21077n;
                menuC2151h.f21057k = true;
                menuC2151h.o(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f11269t = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i7 = this.B;
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
        i();
    }

    public void setItemInvoker(InterfaceC2150g interfaceC2150g) {
        this.f11270u = interfaceC2150g;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i7, int i10, int i11, int i12) {
        this.f11266A = i7;
        super.setPadding(i7, i10, i11, i12);
    }

    public void setPopupCallback(AbstractC2145b abstractC2145b) {
        this.f11272w = abstractC2145b;
    }

    public void setTitle(CharSequence charSequence) {
        this.f11268s = charSequence;
        i();
    }
}
