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
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView {
    public MenuItemImpl k;
    public CharSequence l;
    public Drawable m;
    public MenuBuilder.ItemInvoker n;
    public ForwardingListener o;
    public PopupCallback p;
    public boolean q;
    public boolean r;
    public final int s;
    public int t;
    public final int u;

    public class ActionMenuItemForwardingListener extends ForwardingListener {
        public ActionMenuItemForwardingListener() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public final ShowableListMenu b() {
            PopupCallback popupCallback = ActionMenuItemView.this.p;
            if (popupCallback != null) {
                return popupCallback.a();
            }
            return null;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public final boolean c() {
            ShowableListMenu showableListMenuB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            MenuBuilder.ItemInvoker itemInvoker = actionMenuItemView.n;
            return itemInvoker != null && itemInvoker.a(actionMenuItemView.k) && (showableListMenuB = b()) != null && showableListMenuB.b();
        }
    }

    public static abstract class PopupCallback {
        public abstract ShowableListMenu a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public final boolean a() {
        return q();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
    public final boolean b() {
        return q() && this.k.getIcon() == null;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public final void c(MenuItemImpl menuItemImpl) {
        this.k = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitleCondensed());
        setId(menuItemImpl.a);
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.o == null) {
            this.o = new ActionMenuItemForwardingListener();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.k;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MenuBuilder.ItemInvoker itemInvoker = this.n;
        if (itemInvoker != null) {
            itemInvoker.a(this.k);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.q = r();
        s();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean zQ = q();
        if (zQ && (i3 = this.t) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int i4 = this.s;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i4) : i4;
        if (mode != 1073741824 && i4 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
        }
        if (zQ || this.m == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.m.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener;
        if (this.k.hasSubMenu() && (forwardingListener = this.o) != null && forwardingListener.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean q() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean r() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void s() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.l);
        if (this.m != null) {
            if (!((this.k.y & 4) == 4) || (!this.q && !this.r)) {
                z = false;
            }
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.l : null);
        CharSequence charSequence = this.k.q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z3 ? null : this.k.e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.k.r;
        if (TextUtils.isEmpty(charSequence2)) {
            setTooltipText(z3 ? null : this.k.e);
        } else {
            setTooltipText(charSequence2);
        }
    }

    public void setExpandedFormat(boolean z) {
        if (this.r != z) {
            this.r = z;
            MenuItemImpl menuItemImpl = this.k;
            if (menuItemImpl != null) {
                MenuBuilder menuBuilder = menuItemImpl.n;
                menuBuilder.k = true;
                menuBuilder.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.m = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.u;
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
        s();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.n = itemInvoker;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        this.t = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        this.p = popupCallback;
    }

    public void setTitle(CharSequence charSequence) {
        this.l = charSequence;
        s();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.q = r();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMenuItemView, i, 0);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.u = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.t = -1;
        setSaveEnabled(false);
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }
}
