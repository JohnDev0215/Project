package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import defpackage.bz;
import defpackage.in3;
import defpackage.om3;
import defpackage.u5;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    public static final int[] G = {R.attr.state_checked};
    public static final c H = new c();
    public static final d I = new d();
    public boolean A;
    public int B;
    public int C;
    public boolean D;
    public int E;
    public com.google.android.material.badge.a F;
    public boolean e;
    public int f;
    public int g;
    public float h;
    public float i;
    public float j;
    public int k;
    public boolean l;
    public final FrameLayout m;
    public final View n;
    public final ImageView o;
    public final ViewGroup p;
    public final TextView q;
    public final TextView r;
    public int s;
    public MenuItemImpl t;
    public ColorStateList u;
    public Drawable v;
    public Drawable w;
    public ValueAnimator x;
    public c y;
    public float z;

    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
            if (navigationBarItemView.o.getVisibility() == 0) {
                com.google.android.material.badge.a aVar = navigationBarItemView.F;
                if (aVar != null) {
                    Rect rect = new Rect();
                    ImageView imageView = navigationBarItemView.o;
                    imageView.getDrawingRect(rect);
                    aVar.setBounds(rect);
                    aVar.g(imageView, null);
                }
            }
        }
    }

    public class b implements Runnable {
        public final /* synthetic */ int e;

        public b(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int[] iArr = NavigationBarItemView.G;
            NavigationBarItemView.this.f(this.e);
        }
    }

    public static class c {
        public float a(float f, float f2) {
            return 1.0f;
        }
    }

    public static class d extends c {
        @Override // com.google.android.material.navigation.NavigationBarItemView.c
        public final float a(float f, float f2) {
            LinearInterpolator linearInterpolator = u5.a;
            return (f * 0.6f) + 0.4f;
        }
    }

    public NavigationBarItemView(Context context) {
        super(context);
        this.e = false;
        this.s = -1;
        this.y = H;
        this.z = 0.0f;
        this.A = false;
        this.B = 0;
        this.C = 0;
        this.D = false;
        this.E = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.m = (FrameLayout) findViewById(R$id.navigation_bar_item_icon_container);
        this.n = findViewById(R$id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(R$id.navigation_bar_item_icon_view);
        this.o = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.navigation_bar_item_labels_group);
        this.p = viewGroup;
        TextView textView = (TextView) findViewById(R$id.navigation_bar_item_small_label_view);
        this.q = textView;
        TextView textView2 = (TextView) findViewById(R$id.navigation_bar_item_large_label_view);
        this.r = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.f = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.g = viewGroup.getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(textView, 2);
        om3.d.p(textView2, 2);
        setFocusable(true);
        float textSize = textView.getTextSize();
        float textSize2 = textView2.getTextSize();
        this.h = textSize - textSize2;
        this.i = (textSize2 * 1.0f) / textSize;
        this.j = (textSize * 1.0f) / textSize2;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(android.widget.TextView r4, int r5) {
        /*
            r4.setTextAppearance(r5)
            android.content.Context r0 = r4.getContext()
            r1 = 0
            if (r5 != 0) goto Lc
        La:
            r5 = r1
            goto L4e
        Lc:
            int[] r2 = com.google.android.material.R$styleable.TextAppearance
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r5, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            int r3 = com.google.android.material.R$styleable.TextAppearance_android_textSize
            boolean r3 = r5.getValue(r3, r2)
            r5.recycle()
            if (r3 != 0) goto L23
            goto La
        L23:
            int r5 = r2.getComplexUnit()
            r3 = 2
            if (r5 != r3) goto L40
            int r5 = r2.data
            float r5 = android.util.TypedValue.complexToFloat(r5)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r5 = r5 * r0
            int r5 = java.lang.Math.round(r5)
            goto L4e
        L40:
            int r5 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r5 = android.util.TypedValue.complexToDimensionPixelSize(r5, r0)
        L4e:
            if (r5 == 0) goto L54
            float r5 = (float) r5
            r4.setTextSize(r1, r5)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.b(android.widget.TextView, int):void");
    }

    public static void d(float f, float f2, int i, TextView textView) {
        textView.setScaleX(f);
        textView.setScaleY(f2);
        textView.setVisibility(i);
    }

    public static void e(View view, int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        layoutParams.gravity = i2;
        view.setLayoutParams(layoutParams);
    }

    public static void g(ViewGroup viewGroup, int i) {
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), i);
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.m;
        return frameLayout != null ? frameLayout : this.o;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int iIndexOfChild = viewGroup.indexOfChild(this);
        int i = 0;
        for (int i2 = 0; i2 < iIndexOfChild; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i++;
            }
        }
        return i;
    }

    private int getSuggestedIconHeight() {
        com.google.android.material.badge.a aVar = this.F;
        int minimumHeight = aVar != null ? aVar.getMinimumHeight() / 2 : 0;
        return this.o.getMeasuredWidth() + Math.max(minimumHeight, ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin) + minimumHeight;
    }

    private int getSuggestedIconWidth() {
        com.google.android.material.badge.a aVar = this.F;
        int minimumWidth = aVar == null ? 0 : aVar.getMinimumWidth() - this.F.i.b.q.intValue();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.rightMargin) + this.o.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.leftMargin);
    }

    public final void a(float f, float f2) {
        View view = this.n;
        if (view != null) {
            c cVar = this.y;
            cVar.getClass();
            LinearInterpolator linearInterpolator = u5.a;
            view.setScaleX((0.6f * f) + 0.4f);
            view.setScaleY(cVar.a(f, f2));
            view.setAlpha(u5.a(0.0f, 1.0f, f2 == 0.0f ? 0.8f : 0.0f, f2 == 0.0f ? 1.0f : 0.2f, f));
        }
        this.z = f;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public final void c(MenuItemImpl menuItemImpl) {
        this.t = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.e);
        setId(menuItemImpl.a);
        if (!TextUtils.isEmpty(menuItemImpl.q)) {
            setContentDescription(menuItemImpl.q);
        }
        setTooltipText(!TextUtils.isEmpty(menuItemImpl.r) ? menuItemImpl.r : menuItemImpl.e);
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        this.e = true;
    }

    public final void f(int i) {
        View view = this.n;
        if (view == null) {
            return;
        }
        int iMin = Math.min(this.B, i - (this.E * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = this.D && this.k == 2 ? iMin : this.C;
        layoutParams.width = iMin;
        view.setLayoutParams(layoutParams);
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.n;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public com.google.android.material.badge.a getBadge() {
        return this.F;
    }

    public int getItemBackgroundResId() {
        return R$drawable.mtrl_navigation_bar_item_background;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.t;
    }

    public int getItemDefaultMarginResId() {
        return R$dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.s;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        ViewGroup viewGroup = this.p;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return viewGroup.getMeasuredHeight() + getSuggestedIconHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        ViewGroup viewGroup = this.p;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), viewGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.t;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.t.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, G);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        com.google.android.material.badge.a aVar = this.F;
        if (aVar != null && aVar.isVisible()) {
            MenuItemImpl menuItemImpl = this.t;
            CharSequence charSequence = menuItemImpl.e;
            if (!TextUtils.isEmpty(menuItemImpl.q)) {
                charSequence = this.t.q;
            }
            accessibilityNodeInfo.setContentDescription(((Object) charSequence) + ", " + ((Object) this.F.c()));
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            accessibilityNodeInfoCompat.o(false);
            accessibilityNodeInfoCompat.k(AccessibilityNodeInfoCompat.a.g);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R$string.item_view_role_description));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new b(i));
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.n;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.A = z;
        View view = this.n;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i) {
        this.C = i;
        f(getWidth());
    }

    public void setActiveIndicatorMarginHorizontal(int i) {
        this.E = i;
        f(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.D = z;
    }

    public void setActiveIndicatorWidth(int i) {
        this.B = i;
        f(getWidth());
    }

    public void setBadge(com.google.android.material.badge.a aVar) {
        com.google.android.material.badge.a aVar2 = this.F;
        if (aVar2 == aVar) {
            return;
        }
        boolean z = aVar2 != null;
        ImageView imageView = this.o;
        if (z && imageView != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            if (this.F != null) {
                setClipChildren(true);
                setClipToPadding(true);
                com.google.android.material.badge.a aVar3 = this.F;
                if (aVar3 != null) {
                    if (aVar3.d() != null) {
                        aVar3.d().setForeground(null);
                    } else {
                        imageView.getOverlay().remove(aVar3);
                    }
                }
                this.F = null;
            }
        }
        this.F = aVar;
        if (imageView != null) {
            if (aVar != null) {
                setClipChildren(false);
                setClipToPadding(false);
                com.google.android.material.badge.a aVar4 = this.F;
                Rect rect = new Rect();
                imageView.getDrawingRect(rect);
                aVar4.setBounds(rect);
                aVar4.g(imageView, null);
                if (aVar4.d() != null) {
                    aVar4.d().setForeground(aVar4);
                } else {
                    imageView.getOverlay().add(aVar4);
                }
            }
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setChecked(boolean r13) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.q.setEnabled(z);
        this.r.setEnabled(z);
        this.o.setEnabled(z);
        if (!z) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.k.a(this, null);
        } else {
            PointerIcon systemIcon = PointerIcon.getSystemIcon(getContext(), 1002);
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.k.a(this, systemIcon);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.v) {
            return;
        }
        this.v = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = drawable.mutate();
            this.w = drawable;
            ColorStateList colorStateList = this.u;
            if (colorStateList != null) {
                drawable.setTintList(colorStateList);
            }
        }
        this.o.setImageDrawable(drawable);
    }

    public void setIconSize(int i) {
        ImageView imageView = this.o;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        imageView.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.u = colorStateList;
        if (this.t == null || (drawable = this.w) == null) {
            return;
        }
        drawable.setTintList(colorStateList);
        this.w.invalidateSelf();
    }

    public void setItemBackground(int i) {
        Drawable drawable;
        if (i == 0) {
            drawable = null;
        } else {
            Context context = getContext();
            Object obj = bz.a;
            drawable = context.getDrawable(i);
        }
        setItemBackground(drawable);
    }

    public void setItemPaddingBottom(int i) {
        if (this.g != i) {
            this.g = i;
            MenuItemImpl menuItemImpl = this.t;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public void setItemPaddingTop(int i) {
        if (this.f != i) {
            this.f = i;
            MenuItemImpl menuItemImpl = this.t;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public void setItemPosition(int i) {
        this.s = i;
    }

    public void setLabelVisibilityMode(int i) {
        if (this.k != i) {
            this.k = i;
            if (this.D && i == 2) {
                this.y = I;
            } else {
                this.y = H;
            }
            f(getWidth());
            MenuItemImpl menuItemImpl = this.t;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public void setShifting(boolean z) {
        if (this.l != z) {
            this.l = z;
            MenuItemImpl menuItemImpl = this.t;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i) {
        TextView textView = this.r;
        b(textView, i);
        float textSize = this.q.getTextSize();
        float textSize2 = textView.getTextSize();
        this.h = textSize - textSize2;
        this.i = (textSize2 * 1.0f) / textSize;
        this.j = (textSize * 1.0f) / textSize2;
    }

    public void setTextAppearanceInactive(int i) {
        TextView textView = this.q;
        b(textView, i);
        float textSize = textView.getTextSize();
        float textSize2 = this.r.getTextSize();
        this.h = textSize - textSize2;
        this.i = (textSize2 * 1.0f) / textSize;
        this.j = (textSize * 1.0f) / textSize2;
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.q.setTextColor(colorStateList);
            this.r.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.q.setText(charSequence);
        this.r.setText(charSequence);
        MenuItemImpl menuItemImpl = this.t;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.q)) {
            setContentDescription(charSequence);
        }
        MenuItemImpl menuItemImpl2 = this.t;
        if (menuItemImpl2 != null && !TextUtils.isEmpty(menuItemImpl2.r)) {
            charSequence = this.t.r;
        }
        setTooltipText(charSequence);
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(this, drawable);
    }
}
