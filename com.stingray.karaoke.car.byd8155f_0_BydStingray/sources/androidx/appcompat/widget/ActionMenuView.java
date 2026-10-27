package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import l3.C1764u;
import n0.C1886o;
import q.InterfaceC2150g;
import q.MenuC2151h;
import q.MenuItemC2152i;
import r.AbstractC2235X;
import r.C2234W;
import r.C2247f;
import r.C2249h;
import r.C2250i;
import r.C2252k;
import r.InterfaceC2251j;
import r.InterfaceC2253l;
import r.u0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends AbstractC2235X implements InterfaceC2150g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public MenuC2151h f11332A;
    public Context B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f11333C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public C2250i f11334D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public u0 f11335E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f11336F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f11337G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final int f11338H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final int f11339I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public InterfaceC2253l f11340J;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f10 = context.getResources().getDisplayMetrics().density;
        this.f11338H = (int) (56.0f * f10);
        this.f11339I = (int) (f10 * 4.0f);
        this.B = context;
        this.f11333C = 0;
    }

    public static C2252k i() {
        C2252k c2252k = new C2252k(-2, -2);
        c2252k.f21520a = false;
        ((LinearLayout.LayoutParams) c2252k).gravity = 16;
        return c2252k;
    }

    public static C2252k j(ViewGroup.LayoutParams layoutParams) {
        C2252k c2252k;
        if (layoutParams == null) {
            return i();
        }
        if (layoutParams instanceof C2252k) {
            C2252k c2252k2 = (C2252k) layoutParams;
            c2252k = new C2252k(c2252k2);
            c2252k.f21520a = c2252k2.f21520a;
        } else {
            c2252k = new C2252k(layoutParams);
        }
        if (((LinearLayout.LayoutParams) c2252k).gravity <= 0) {
            ((LinearLayout.LayoutParams) c2252k).gravity = 16;
        }
        return c2252k;
    }

    @Override // q.InterfaceC2150g
    public final boolean a(MenuItemC2152i menuItemC2152i) {
        return this.f11332A.p(menuItemC2152i, null, 0);
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2252k;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // r.AbstractC2235X
    /* JADX INFO: renamed from: e */
    public final /* bridge */ /* synthetic */ C2234W generateDefaultLayoutParams() {
        return i();
    }

    @Override // r.AbstractC2235X
    /* JADX INFO: renamed from: f */
    public final C2234W generateLayoutParams(AttributeSet attributeSet) {
        return new C2252k(getContext(), attributeSet);
    }

    @Override // r.AbstractC2235X
    /* JADX INFO: renamed from: g */
    public final /* bridge */ /* synthetic */ C2234W generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return i();
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public Menu getMenu() {
        if (this.f11332A == null) {
            Context context = getContext();
            MenuC2151h menuC2151h = new MenuC2151h(context);
            this.f11332A = menuC2151h;
            menuC2151h.f21051e = new C1764u(this, 19);
            C2250i c2250i = new C2250i(context);
            this.f11334D = c2250i;
            c2250i.f21491v = true;
            c2250i.f21492w = true;
            c2250i.f21485p = new C1886o(6);
            this.f11332A.b(c2250i, this.B);
            C2250i c2250i2 = this.f11334D;
            c2250i2.f21487r = this;
            this.f11332A = c2250i2.f21484n;
        }
        return this.f11332A;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        C2250i c2250i = this.f11334D;
        C2249h c2249h = c2250i.f21488s;
        if (c2249h != null) {
            return c2249h.getDrawable();
        }
        if (c2250i.f21490u) {
            return c2250i.f21489t;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.f11333C;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean k(int i7) {
        boolean zA = false;
        if (i7 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i7 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i7);
        if (i7 < getChildCount() && (childAt instanceof InterfaceC2251j)) {
            zA = ((InterfaceC2251j) childAt).a();
        }
        return (i7 <= 0 || !(childAt2 instanceof InterfaceC2251j)) ? zA : zA | ((InterfaceC2251j) childAt2).c();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C2250i c2250i = this.f11334D;
        if (c2250i != null) {
            c2250i.d();
            C2247f c2247f = this.f11334D.f21478C;
            if (c2247f == null || !c2247f.b()) {
                return;
            }
            this.f11334D.e();
            this.f11334D.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C2250i c2250i = this.f11334D;
        if (c2250i != null) {
            c2250i.e();
            C2247f c2247f = c2250i.f21479D;
            if (c2247f == null || !c2247f.b()) {
                return;
            }
            c2247f.f21095i.dismiss();
        }
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int width;
        int paddingLeft;
        if (!this.f11336F) {
            super.onLayout(z6, i7, i10, i11, i12);
            return;
        }
        int childCount = getChildCount();
        int i13 = (i12 - i10) / 2;
        int dividerWidth = getDividerWidth();
        int i14 = i11 - i7;
        int paddingRight = (i14 - getPaddingRight()) - getPaddingLeft();
        boolean z10 = getLayoutDirection() == 1;
        int i15 = 0;
        int i16 = 0;
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() != 8) {
                C2252k c2252k = (C2252k) childAt.getLayoutParams();
                if (c2252k.f21520a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (k(i17)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z10) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) c2252k).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) c2252k).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i18 = i13 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i18, width, measuredHeight + i18);
                    paddingRight -= measuredWidth;
                    i15 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) c2252k).leftMargin) + ((LinearLayout.LayoutParams) c2252k).rightMargin;
                    k(i17);
                    i16++;
                }
            }
        }
        if (childCount == 1 && i15 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i19 = (i14 / 2) - (measuredWidth2 / 2);
            int i20 = i13 - (measuredHeight2 / 2);
            childAt2.layout(i19, i20, measuredWidth2 + i19, measuredHeight2 + i20);
            return;
        }
        int i21 = i16 - (i15 ^ 1);
        int iMax = Math.max(0, i21 > 0 ? paddingRight / i21 : 0);
        if (z10) {
            int width2 = getWidth() - getPaddingRight();
            for (int i22 = 0; i22 < childCount; i22++) {
                View childAt3 = getChildAt(i22);
                C2252k c2252k2 = (C2252k) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !c2252k2.f21520a) {
                    int i23 = width2 - ((LinearLayout.LayoutParams) c2252k2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i24 = i13 - (measuredHeight3 / 2);
                    childAt3.layout(i23 - measuredWidth3, i24, i23, measuredHeight3 + i24);
                    width2 = i23 - ((measuredWidth3 + ((LinearLayout.LayoutParams) c2252k2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i25 = 0; i25 < childCount; i25++) {
            View childAt4 = getChildAt(i25);
            C2252k c2252k3 = (C2252k) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !c2252k3.f21520a) {
                int i26 = paddingLeft2 + ((LinearLayout.LayoutParams) c2252k3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i27 = i13 - (measuredHeight4 / 2);
                childAt4.layout(i26, i27, i26 + measuredWidth4, measuredHeight4 + i27);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) c2252k3).rightMargin + iMax + i26;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v36 */
    @Override // r.AbstractC2235X, android.view.View
    public final void onMeasure(int i7, int i10) {
        int i11;
        boolean z6;
        int i12;
        boolean z10;
        int i13;
        int i14;
        int i15;
        ?? r42;
        int i16;
        int i17;
        int i18;
        MenuC2151h menuC2151h;
        boolean z11 = this.f11336F;
        boolean z12 = View.MeasureSpec.getMode(i7) == 1073741824;
        this.f11336F = z12;
        if (z11 != z12) {
            this.f11337G = 0;
        }
        int size = View.MeasureSpec.getSize(i7);
        if (this.f11336F && (menuC2151h = this.f11332A) != null && size != this.f11337G) {
            this.f11337G = size;
            menuC2151h.o(true);
        }
        int childCount = getChildCount();
        if (!this.f11336F || childCount <= 0) {
            for (int i19 = 0; i19 < childCount; i19++) {
                C2252k c2252k = (C2252k) getChildAt(i19).getLayoutParams();
                ((LinearLayout.LayoutParams) c2252k).rightMargin = 0;
                ((LinearLayout.LayoutParams) c2252k).leftMargin = 0;
            }
            super.onMeasure(i7, i10);
            return;
        }
        int mode = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i7);
        int size3 = View.MeasureSpec.getSize(i10);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingBottom, -2);
        int i20 = size2 - paddingRight;
        int i21 = this.f11338H;
        int i22 = i20 / i21;
        int i23 = i20 % i21;
        if (i22 == 0) {
            setMeasuredDimension(i20, 0);
            return;
        }
        int i24 = (i23 / i22) + i21;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i25 = 0;
        int iMax2 = 0;
        int i26 = 0;
        boolean z13 = false;
        int i27 = 0;
        long j3 = 0;
        while (true) {
            i11 = this.f11339I;
            if (i26 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i26);
            int i28 = size3;
            int i29 = i20;
            if (childAt.getVisibility() == 8) {
                i16 = mode;
                i17 = paddingBottom;
            } else {
                boolean z14 = childAt instanceof ActionMenuItemView;
                int i30 = i25 + 1;
                if (z14) {
                    childAt.setPadding(i11, 0, i11, 0);
                }
                C2252k c2252k2 = (C2252k) childAt.getLayoutParams();
                c2252k2.f21523f = false;
                c2252k2.c = 0;
                c2252k2.f21521b = 0;
                c2252k2.d = false;
                ((LinearLayout.LayoutParams) c2252k2).leftMargin = 0;
                ((LinearLayout.LayoutParams) c2252k2).rightMargin = 0;
                c2252k2.f21522e = z14 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText());
                int i31 = c2252k2.f21520a ? 1 : i22;
                C2252k c2252k3 = (C2252k) childAt.getLayoutParams();
                i16 = mode;
                i17 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z14 ? (ActionMenuItemView) childAt : null;
                boolean z15 = (actionMenuItemView == null || TextUtils.isEmpty(actionMenuItemView.getText())) ? false : true;
                if (i31 <= 0 || (z15 && i31 < 2)) {
                    i18 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i31 * i24, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i18 = measuredWidth / i24;
                    if (measuredWidth % i24 != 0) {
                        i18++;
                    }
                    if (z15 && i18 < 2) {
                        i18 = 2;
                    }
                }
                c2252k3.d = !c2252k3.f21520a && z15;
                c2252k3.f21521b = i18;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i18 * i24, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i18);
                if (c2252k2.d) {
                    i27++;
                }
                if (c2252k2.f21520a) {
                    z13 = true;
                }
                i22 -= i18;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i18 == 1) {
                    j3 |= (long) (1 << i26);
                }
                i25 = i30;
            }
            i26++;
            size3 = i28;
            i20 = i29;
            paddingBottom = i17;
            mode = i16;
        }
        int i32 = mode;
        int i33 = i20;
        int i34 = size3;
        boolean z16 = z13 && i25 == 2;
        boolean z17 = false;
        while (i27 > 0 && i22 > 0) {
            int i35 = Integer.MAX_VALUE;
            int i36 = 0;
            int i37 = 0;
            long j10 = 0;
            while (i37 < childCount2) {
                C2252k c2252k4 = (C2252k) getChildAt(i37).getLayoutParams();
                boolean z18 = z17;
                if (c2252k4.d) {
                    int i38 = c2252k4.f21521b;
                    if (i38 < i35) {
                        j10 = 1 << i37;
                        i35 = i38;
                        i36 = 1;
                    } else if (i38 == i35) {
                        j10 |= 1 << i37;
                        i36++;
                    }
                }
                i37++;
                z17 = z18;
            }
            z6 = z17;
            j3 |= j10;
            if (i36 > i22) {
                break;
            }
            int i39 = i35 + 1;
            int i40 = 0;
            while (i40 < childCount2) {
                View childAt2 = getChildAt(i40);
                C2252k c2252k5 = (C2252k) childAt2.getLayoutParams();
                int i41 = iMax;
                int i42 = childMeasureSpec;
                int i43 = childCount2;
                long j11 = 1 << i40;
                if ((j10 & j11) != 0) {
                    if (z16 && c2252k5.f21522e) {
                        r42 = 1;
                        r42 = 1;
                        if (i22 == 1) {
                            childAt2.setPadding(i11 + i24, 0, i11, 0);
                        }
                    } else {
                        r42 = 1;
                    }
                    c2252k5.f21521b += r42;
                    c2252k5.f21523f = r42;
                    i22--;
                } else if (c2252k5.f21521b == i39) {
                    j3 |= j11;
                }
                i40++;
                childMeasureSpec = i42;
                iMax = i41;
                childCount2 = i43;
            }
            z17 = true;
        }
        z6 = z17;
        int i44 = iMax;
        int i45 = childMeasureSpec;
        int i46 = childCount2;
        boolean z19 = !z13 && i25 == 1;
        if (i22 <= 0 || j3 == 0 || (i22 >= i25 - 1 && !z19 && iMax2 <= 1)) {
            i12 = i46;
            z10 = z6;
        } else {
            float fBitCount = Long.bitCount(j3);
            if (!z19) {
                if ((j3 & 1) != 0 && !((C2252k) getChildAt(0).getLayoutParams()).f21522e) {
                    fBitCount -= 0.5f;
                }
                int i47 = i46 - 1;
                if ((j3 & ((long) (1 << i47))) != 0 && !((C2252k) getChildAt(i47).getLayoutParams()).f21522e) {
                    fBitCount -= 0.5f;
                }
            }
            int i48 = fBitCount > 0.0f ? (int) ((i22 * i24) / fBitCount) : 0;
            boolean z20 = z6;
            i12 = i46;
            for (int i49 = 0; i49 < i12; i49++) {
                if ((j3 & ((long) (1 << i49))) != 0) {
                    View childAt3 = getChildAt(i49);
                    C2252k c2252k6 = (C2252k) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        c2252k6.c = i48;
                        c2252k6.f21523f = true;
                        if (i49 == 0 && !c2252k6.f21522e) {
                            ((LinearLayout.LayoutParams) c2252k6).leftMargin = (-i48) / 2;
                        }
                        z20 = true;
                    } else {
                        if (c2252k6.f21520a) {
                            c2252k6.c = i48;
                            c2252k6.f21523f = true;
                            ((LinearLayout.LayoutParams) c2252k6).rightMargin = (-i48) / 2;
                            z20 = true;
                        } else {
                            if (i49 != 0) {
                                ((LinearLayout.LayoutParams) c2252k6).leftMargin = i48 / 2;
                            }
                            if (i49 != i12 - 1) {
                                ((LinearLayout.LayoutParams) c2252k6).rightMargin = i48 / 2;
                            }
                        }
                    }
                }
            }
            z10 = z20;
        }
        if (z10) {
            int i50 = 0;
            while (i50 < i12) {
                View childAt4 = getChildAt(i50);
                C2252k c2252k7 = (C2252k) childAt4.getLayoutParams();
                if (c2252k7.f21523f) {
                    i15 = i45;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((c2252k7.f21521b * i24) + c2252k7.c, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i15);
                } else {
                    i15 = i45;
                }
                i50++;
                i45 = i15;
            }
        }
        if (i32 != 1073741824) {
            i14 = i33;
            i13 = i44;
        } else {
            i13 = i34;
            i14 = i33;
        }
        setMeasuredDimension(i14, i13);
    }

    public void setExpandedActionViewsExclusive(boolean z6) {
        this.f11334D.f21477A = z6;
    }

    public void setOnMenuItemClickListener(InterfaceC2253l interfaceC2253l) {
        this.f11340J = interfaceC2253l;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        C2250i c2250i = this.f11334D;
        C2249h c2249h = c2250i.f21488s;
        if (c2249h != null) {
            c2249h.setImageDrawable(drawable);
        } else {
            c2250i.f21490u = true;
            c2250i.f21489t = drawable;
        }
    }

    public void setOverflowReserved(boolean z6) {
    }

    public void setPopupTheme(int i7) {
        if (this.f11333C != i7) {
            this.f11333C = i7;
            if (i7 == 0) {
                this.B = getContext();
            } else {
                this.B = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    public void setPresenter(C2250i c2250i) {
        this.f11334D = c2250i;
        c2250i.f21487r = this;
        this.f11332A = c2250i.f21484n;
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2252k(getContext(), attributeSet);
    }
}
