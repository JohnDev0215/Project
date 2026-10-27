package androidx.appcompat.widget;

import B5.RunnableC0079c;
import F1.C0250i;
import F1.E;
import F1.H;
import X1.i;
import a5.e;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import com.stingray.drivemodedetector.R;
import h3.y;
import h6.f;
import java.util.ArrayList;
import java.util.Iterator;
import n.AbstractC1850a;
import p.C2069d;
import q.MenuC2151h;
import q.MenuItemC2152i;
import r.A0;
import r.B0;
import r.C2218F;
import r.C2247f;
import r.C2250i;
import r.C2262u;
import r.C2263v;
import r.InterfaceC2224L;
import r.n0;
import r.t0;
import r.u0;
import r.v0;
import r.w0;
import r.x0;
import r.y0;
import r.z0;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f11351A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f11352C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f11353D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public n0 f11354E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f11355F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f11356G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final int f11357H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public CharSequence f11358I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public CharSequence f11359J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public ColorStateList f11360K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public ColorStateList f11361L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f11362M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f11363N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final ArrayList f11364O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final ArrayList f11365P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final int[] f11366Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final C0250i f11367R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public ArrayList f11368S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final u0 f11369T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public A0 f11370U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public w0 f11371V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public boolean f11372W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public OnBackInvokedCallback f11373a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public OnBackInvokedDispatcher f11374b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f11375c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final RunnableC0079c f11376d0;
    public ActionMenuView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2218F f11377m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C2218F f11378n;
    public C2262u o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2263v f11379p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Drawable f11380q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final CharSequence f11381r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2262u f11382s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f11383t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Context f11384u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f11385v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f11386w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f11387x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f11388y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f11389z;

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static x0 g() {
        x0 x0Var = new x0(-2, -2);
        x0Var.f21569b = 0;
        x0Var.f21568a = 8388627;
        return x0Var;
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i7 = 0; i7 < menu.size(); i7++) {
            arrayList.add(menu.getItem(i7));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new C2069d(getContext());
    }

    public static x0 h(ViewGroup.LayoutParams layoutParams) {
        boolean z6 = layoutParams instanceof x0;
        if (z6) {
            x0 x0Var = (x0) layoutParams;
            x0 x0Var2 = new x0(x0Var);
            x0Var2.f21569b = 0;
            x0Var2.f21569b = x0Var.f21569b;
            return x0Var2;
        }
        if (z6) {
            x0 x0Var3 = new x0((x0) layoutParams);
            x0Var3.f21569b = 0;
            return x0Var3;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            x0 x0Var4 = new x0(layoutParams);
            x0Var4.f21569b = 0;
            return x0Var4;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        x0 x0Var5 = new x0(marginLayoutParams);
        x0Var5.f21569b = 0;
        ((ViewGroup.MarginLayoutParams) x0Var5).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) x0Var5).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) x0Var5).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) x0Var5).bottomMargin = marginLayoutParams.bottomMargin;
        return x0Var5;
    }

    public static int j(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(int i7, ArrayList arrayList) {
        boolean z6 = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, getLayoutDirection());
        arrayList.clear();
        if (!z6) {
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                x0 x0Var = (x0) childAt.getLayoutParams();
                if (x0Var.f21569b == 0 && r(childAt)) {
                    int i11 = x0Var.f21568a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i11, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i12 = childCount - 1; i12 >= 0; i12--) {
            View childAt2 = getChildAt(i12);
            x0 x0Var2 = (x0) childAt2.getLayoutParams();
            if (x0Var2.f21569b == 0 && r(childAt2)) {
                int i13 = x0Var2.f21568a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i13, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    public final void b(View view, boolean z6) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        x0 x0VarG = layoutParams == null ? g() : !checkLayoutParams(layoutParams) ? h(layoutParams) : (x0) layoutParams;
        x0VarG.f21569b = 1;
        if (!z6 || this.f11383t == null) {
            addView(view, x0VarG);
        } else {
            view.setLayoutParams(x0VarG);
            this.f11365P.add(view);
        }
    }

    public final void c() {
        if (this.f11382s == null) {
            C2262u c2262u = new C2262u(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.f11382s = c2262u;
            c2262u.setImageDrawable(this.f11380q);
            this.f11382s.setContentDescription(this.f11381r);
            x0 x0VarG = g();
            x0VarG.f21568a = (this.f11388y & 112) | 8388611;
            x0VarG.f21569b = 2;
            this.f11382s.setLayoutParams(x0VarG);
            this.f11382s.setOnClickListener(new e(this, 4));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof x0);
    }

    public final void d() {
        if (this.f11354E == null) {
            n0 n0Var = new n0();
            n0Var.f21534a = 0;
            n0Var.f21535b = 0;
            n0Var.c = Integer.MIN_VALUE;
            n0Var.d = Integer.MIN_VALUE;
            n0Var.f21536e = 0;
            n0Var.f21537f = 0;
            n0Var.f21538g = false;
            n0Var.f21539h = false;
            this.f11354E = n0Var;
        }
    }

    public final void e() {
        if (this.l == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.l = actionMenuView;
            actionMenuView.setPopupTheme(this.f11385v);
            this.l.setOnMenuItemClickListener(this.f11369T);
            ActionMenuView actionMenuView2 = this.l;
            u0 u0Var = new u0(this);
            actionMenuView2.getClass();
            actionMenuView2.f11335E = u0Var;
            x0 x0VarG = g();
            x0VarG.f21568a = (this.f11388y & 112) | 8388613;
            this.l.setLayoutParams(x0VarG);
            b(this.l, false);
        }
        ActionMenuView actionMenuView3 = this.l;
        if (actionMenuView3.f11332A == null) {
            MenuC2151h menuC2151h = (MenuC2151h) actionMenuView3.getMenu();
            if (this.f11371V == null) {
                this.f11371V = new w0(this);
            }
            this.l.setExpandedActionViewsExclusive(true);
            menuC2151h.b(this.f11371V, this.f11384u);
            s();
        }
    }

    public final void f() {
        if (this.o == null) {
            this.o = new C2262u(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            x0 x0VarG = g();
            x0VarG.f21568a = (this.f11388y & 112) | 8388611;
            this.o.setLayoutParams(x0VarG);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return g();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return h(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        C2262u c2262u = this.f11382s;
        if (c2262u != null) {
            return c2262u.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        C2262u c2262u = this.f11382s;
        if (c2262u != null) {
            return c2262u.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        n0 n0Var = this.f11354E;
        if (n0Var != null) {
            return n0Var.f21538g ? n0Var.f21534a : n0Var.f21535b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i7 = this.f11356G;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        n0 n0Var = this.f11354E;
        if (n0Var != null) {
            return n0Var.f21534a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        n0 n0Var = this.f11354E;
        if (n0Var != null) {
            return n0Var.f21535b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        n0 n0Var = this.f11354E;
        if (n0Var != null) {
            return n0Var.f21538g ? n0Var.f21535b : n0Var.f21534a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i7 = this.f11355F;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        MenuC2151h menuC2151h;
        ActionMenuView actionMenuView = this.l;
        return (actionMenuView == null || (menuC2151h = actionMenuView.f11332A) == null || !menuC2151h.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.f11356G, 0));
    }

    public int getCurrentContentInsetLeft() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f11355F, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        C2263v c2263v = this.f11379p;
        if (c2263v != null) {
            return c2263v.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        C2263v c2263v = this.f11379p;
        if (c2263v != null) {
            return c2263v.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.l.getMenu();
    }

    public View getNavButtonView() {
        return this.o;
    }

    public CharSequence getNavigationContentDescription() {
        C2262u c2262u = this.o;
        if (c2262u != null) {
            return c2262u.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        C2262u c2262u = this.o;
        if (c2262u != null) {
            return c2262u.getDrawable();
        }
        return null;
    }

    public C2250i getOuterActionMenuPresenter() {
        return null;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.l.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f11384u;
    }

    public int getPopupTheme() {
        return this.f11385v;
    }

    public CharSequence getSubtitle() {
        return this.f11359J;
    }

    public final TextView getSubtitleTextView() {
        return this.f11378n;
    }

    public CharSequence getTitle() {
        return this.f11358I;
    }

    public int getTitleMarginBottom() {
        return this.f11353D;
    }

    public int getTitleMarginEnd() {
        return this.B;
    }

    public int getTitleMarginStart() {
        return this.f11351A;
    }

    public int getTitleMarginTop() {
        return this.f11352C;
    }

    public final TextView getTitleTextView() {
        return this.f11377m;
    }

    public InterfaceC2224L getWrapper() {
        Drawable drawable;
        if (this.f11370U == null) {
            A0 a02 = new A0();
            a02.l = 0;
            a02.f21408a = this;
            a02.f21413h = getTitle();
            a02.f21414i = getSubtitle();
            a02.f21412g = a02.f21413h != null;
            a02.f21411f = getNavigationIcon();
            y yVarF = y.F(getContext(), null, AbstractC1850a.f19352a, R.attr.actionBarStyle);
            a02.f21417m = yVarF.s(15);
            TypedArray typedArray = (TypedArray) yVarF.f15500n;
            CharSequence text = typedArray.getText(27);
            if (!TextUtils.isEmpty(text)) {
                a02.f21412g = true;
                a02.f21413h = text;
                if ((a02.f21409b & 8) != 0) {
                    Toolbar toolbar = a02.f21408a;
                    toolbar.setTitle(text);
                    if (a02.f21412g) {
                        H.h(toolbar.getRootView(), text);
                    }
                }
            }
            CharSequence text2 = typedArray.getText(25);
            if (!TextUtils.isEmpty(text2)) {
                a02.f21414i = text2;
                if ((a02.f21409b & 8) != 0) {
                    setSubtitle(text2);
                }
            }
            Drawable drawableS = yVarF.s(20);
            if (drawableS != null) {
                a02.f21410e = drawableS;
                a02.c();
            }
            Drawable drawableS2 = yVarF.s(17);
            if (drawableS2 != null) {
                a02.d = drawableS2;
                a02.c();
            }
            if (a02.f21411f == null && (drawable = a02.f21417m) != null) {
                a02.f21411f = drawable;
                int i7 = a02.f21409b & 4;
                Toolbar toolbar2 = a02.f21408a;
                if (i7 != 0) {
                    toolbar2.setNavigationIcon(drawable);
                } else {
                    toolbar2.setNavigationIcon((Drawable) null);
                }
            }
            a02.a(typedArray.getInt(10, 0));
            int resourceId = typedArray.getResourceId(9, 0);
            if (resourceId != 0) {
                View viewInflate = LayoutInflater.from(getContext()).inflate(resourceId, (ViewGroup) this, false);
                View view = a02.c;
                if (view != null && (a02.f21409b & 16) != 0) {
                    removeView(view);
                }
                a02.c = viewInflate;
                if (viewInflate != null && (a02.f21409b & 16) != 0) {
                    addView(viewInflate);
                }
                a02.a(a02.f21409b | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = layoutDimension;
                setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(7, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(3, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                int iMax = Math.max(dimensionPixelOffset, 0);
                int iMax2 = Math.max(dimensionPixelOffset2, 0);
                d();
                this.f11354E.a(iMax, iMax2);
            }
            int resourceId2 = typedArray.getResourceId(28, 0);
            if (resourceId2 != 0) {
                Context context = getContext();
                this.f11386w = resourceId2;
                C2218F c2218f = this.f11377m;
                if (c2218f != null) {
                    c2218f.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(26, 0);
            if (resourceId3 != 0) {
                Context context2 = getContext();
                this.f11387x = resourceId3;
                C2218F c2218f2 = this.f11378n;
                if (c2218f2 != null) {
                    c2218f2.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(22, 0);
            if (resourceId4 != 0) {
                setPopupTheme(resourceId4);
            }
            yVarF.G();
            if (R.string.abc_action_bar_up_description != a02.l) {
                a02.l = R.string.abc_action_bar_up_description;
                if (TextUtils.isEmpty(getNavigationContentDescription())) {
                    int i10 = a02.l;
                    a02.f21415j = i10 != 0 ? getContext().getString(i10) : null;
                    a02.b();
                }
            }
            a02.f21415j = getNavigationContentDescription();
            setNavigationOnClickListener(new e(a02));
            this.f11370U = a02;
        }
        return this.f11370U;
    }

    public final int i(View view, int i7) {
        x0 x0Var = (x0) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i10 = i7 > 0 ? (measuredHeight - i7) / 2 : 0;
        int i11 = x0Var.f21568a & 112;
        if (i11 != 16 && i11 != 48 && i11 != 80) {
            i11 = this.f11357H & 112;
        }
        if (i11 == 48) {
            return getPaddingTop() - i10;
        }
        if (i11 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) x0Var).bottomMargin) - i10;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i12 = ((ViewGroup.MarginLayoutParams) x0Var).topMargin;
        if (iMax < i12) {
            iMax = i12;
        } else {
            int i13 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i14 = ((ViewGroup.MarginLayoutParams) x0Var).bottomMargin;
            if (i13 < i14) {
                iMax = Math.max(0, iMax - (i14 - i13));
            }
        }
        return paddingTop + iMax;
    }

    public final void l() {
        Iterator it = this.f11368S.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        Iterator it2 = this.f11367R.f2338a.iterator();
        if (it2.hasNext()) {
            ((i) it2.next()).getClass();
            throw null;
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.f11368S = currentMenuItems2;
    }

    public final boolean m(View view) {
        return view.getParent() == this || this.f11365P.contains(view);
    }

    public final int n(View view, int i7, int i10, int[] iArr) {
        x0 x0Var = (x0) view.getLayoutParams();
        int i11 = ((ViewGroup.MarginLayoutParams) x0Var).leftMargin - iArr[0];
        int iMax = Math.max(0, i11) + i7;
        iArr[0] = Math.max(0, -i11);
        int i12 = i(view, i10);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, i12, iMax + measuredWidth, view.getMeasuredHeight() + i12);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) x0Var).rightMargin + iMax;
    }

    public final int o(View view, int i7, int i10, int[] iArr) {
        x0 x0Var = (x0) view.getLayoutParams();
        int i11 = ((ViewGroup.MarginLayoutParams) x0Var).rightMargin - iArr[1];
        int iMax = i7 - Math.max(0, i11);
        iArr[1] = Math.max(0, -i11);
        int i12 = i(view, i10);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, i12, iMax, view.getMeasuredHeight() + i12);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) x0Var).leftMargin);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        s();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f11376d0);
        s();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f11363N = false;
        }
        if (!this.f11363N) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f11363N = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f11363N = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0298 A[LOOP:0: B:105:0x0296->B:106:0x0298, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02b5 A[LOOP:1: B:108:0x02b3->B:109:0x02b5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02d3 A[LOOP:2: B:111:0x02d1->B:112:0x02d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0321 A[LOOP:3: B:120:0x031f->B:121:0x0321, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0221  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        char c;
        char c10;
        int iJ;
        int iMax;
        int iCombineMeasuredStates;
        int iJ2;
        int iK;
        int iCombineMeasuredStates2;
        int iMax2;
        int i11 = 0;
        if (getLayoutDirection() == 1) {
            c10 = 1;
            c = 0;
        } else {
            c = 1;
            c10 = 0;
        }
        if (r(this.o)) {
            q(this.o, i7, 0, i10, this.f11389z);
            iJ = j(this.o) + this.o.getMeasuredWidth();
            iMax = Math.max(0, k(this.o) + this.o.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.o.getMeasuredState());
        } else {
            iJ = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (r(this.f11382s)) {
            q(this.f11382s, i7, 0, i10, this.f11389z);
            iJ = j(this.f11382s) + this.f11382s.getMeasuredWidth();
            iMax = Math.max(iMax, k(this.f11382s) + this.f11382s.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f11382s.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iJ);
        int iMax4 = Math.max(0, currentContentInsetStart - iJ);
        int[] iArr = this.f11366Q;
        iArr[c10] = iMax4;
        if (r(this.l)) {
            q(this.l, i7, iMax3, i10, this.f11389z);
            iJ2 = j(this.l) + this.l.getMeasuredWidth();
            iMax = Math.max(iMax, k(this.l) + this.l.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.l.getMeasuredState());
        } else {
            iJ2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, iJ2);
        iArr[c] = Math.max(0, currentContentInsetEnd - iJ2);
        if (r(this.f11383t)) {
            iMax5 += p(this.f11383t, i7, iMax5, i10, 0, iArr);
            iMax = Math.max(iMax, k(this.f11383t) + this.f11383t.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f11383t.getMeasuredState());
        }
        if (r(this.f11379p)) {
            iMax5 += p(this.f11379p, i7, iMax5, i10, 0, iArr);
            iMax = Math.max(iMax, k(this.f11379p) + this.f11379p.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f11379p.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((x0) childAt.getLayoutParams()).f21569b == 0 && r(childAt)) {
                iMax5 += p(childAt, i7, iMax5, i10, 0, iArr);
                iMax = Math.max(iMax, k(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i13 = this.f11352C + this.f11353D;
        int i14 = this.f11351A + this.B;
        if (r(this.f11377m)) {
            p(this.f11377m, i7, iMax5 + i14, i10, i13, iArr);
            int iJ3 = j(this.f11377m) + this.f11377m.getMeasuredWidth();
            iK = k(this.f11377m) + this.f11377m.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f11377m.getMeasuredState());
            iMax2 = iJ3;
        } else {
            iK = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (r(this.f11378n)) {
            iMax2 = Math.max(iMax2, p(this.f11378n, i7, iMax5 + i14, i10, iK + i13, iArr));
            iK += k(this.f11378n) + this.f11378n.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f11378n.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, iK);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i7, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i10, iCombineMeasuredStates2 << 16);
        if (!this.f11372W) {
            i11 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i15 = 0; i15 < childCount2; i15++) {
            View childAt2 = getChildAt(i15);
            if (r(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i11 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i11);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof z0)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        z0 z0Var = (z0) parcelable;
        super.onRestoreInstanceState(z0Var.l);
        ActionMenuView actionMenuView = this.l;
        MenuC2151h menuC2151h = actionMenuView != null ? actionMenuView.f11332A : null;
        int i7 = z0Var.f21574n;
        if (i7 != 0 && this.f11371V != null && menuC2151h != null && (menuItemFindItem = menuC2151h.findItem(i7)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (z0Var.o) {
            RunnableC0079c runnableC0079c = this.f11376d0;
            removeCallbacks(runnableC0079c);
            post(runnableC0079c);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        d();
        n0 n0Var = this.f11354E;
        boolean z6 = i7 == 1;
        if (z6 == n0Var.f21538g) {
            return;
        }
        n0Var.f21538g = z6;
        if (!n0Var.f21539h) {
            n0Var.f21534a = n0Var.f21536e;
            n0Var.f21535b = n0Var.f21537f;
            return;
        }
        if (z6) {
            int i10 = n0Var.d;
            if (i10 == Integer.MIN_VALUE) {
                i10 = n0Var.f21536e;
            }
            n0Var.f21534a = i10;
            int i11 = n0Var.c;
            if (i11 == Integer.MIN_VALUE) {
                i11 = n0Var.f21537f;
            }
            n0Var.f21535b = i11;
            return;
        }
        int i12 = n0Var.c;
        if (i12 == Integer.MIN_VALUE) {
            i12 = n0Var.f21536e;
        }
        n0Var.f21534a = i12;
        int i13 = n0Var.d;
        if (i13 == Integer.MIN_VALUE) {
            i13 = n0Var.f21537f;
        }
        n0Var.f21535b = i13;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        C2250i c2250i;
        C2247f c2247f;
        MenuItemC2152i menuItemC2152i;
        z0 z0Var = new z0(super.onSaveInstanceState());
        w0 w0Var = this.f11371V;
        if (w0Var != null && (menuItemC2152i = w0Var.f21566m) != null) {
            z0Var.f21574n = menuItemC2152i.f21067a;
        }
        ActionMenuView actionMenuView = this.l;
        z0Var.o = (actionMenuView == null || (c2250i = actionMenuView.f11334D) == null || (c2247f = c2250i.f21478C) == null || !c2247f.b()) ? false : true;
        return z0Var;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f11362M = false;
        }
        if (!this.f11362M) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f11362M = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f11362M = false;
        }
        return true;
    }

    public final int p(View view, int i7, int i10, int i11, int i12, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i13 = marginLayoutParams.leftMargin - iArr[0];
        int i14 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i14) + Math.max(0, i13);
        iArr[0] = Math.max(0, -i13);
        iArr[1] = Math.max(0, -i14);
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + iMax + i10, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i11, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i12, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void q(View view, int i7, int i10, int i11, int i12) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i10, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i11, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i12 >= 0) {
            if (mode != 0) {
                i12 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i12);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i12, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final boolean r(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public final void s() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcherA = v0.a(this);
            w0 w0Var = this.f11371V;
            boolean z6 = (w0Var == null || w0Var.f21566m == null || onBackInvokedDispatcherA == null || !isAttachedToWindow() || !this.f11375c0) ? false : true;
            if (z6 && this.f11374b0 == null) {
                if (this.f11373a0 == null) {
                    this.f11373a0 = v0.b(new t0(this, 0));
                }
                v0.c(onBackInvokedDispatcherA, this.f11373a0);
                this.f11374b0 = onBackInvokedDispatcherA;
                return;
            }
            if (z6 || (onBackInvokedDispatcher = this.f11374b0) == null) {
                return;
            }
            v0.d(onBackInvokedDispatcher, this.f11373a0);
            this.f11374b0 = null;
        }
    }

    public void setBackInvokedCallbackEnabled(boolean z6) {
        if (this.f11375c0 != z6) {
            this.f11375c0 = z6;
            s();
        }
    }

    public void setCollapseContentDescription(int i7) {
        setCollapseContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setCollapseIcon(int i7) {
        setCollapseIcon(f.k(getContext(), i7));
    }

    public void setCollapsible(boolean z6) {
        this.f11372W = z6;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.f11356G) {
            this.f11356G = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.f11355F) {
            this.f11355F = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i7) {
        setLogo(f.k(getContext(), i7));
    }

    public void setLogoDescription(int i7) {
        setLogoDescription(getContext().getText(i7));
    }

    public void setNavigationContentDescription(int i7) {
        setNavigationContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setNavigationIcon(int i7) {
        setNavigationIcon(f.k(getContext(), i7));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        f();
        this.o.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(y0 y0Var) {
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.l.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i7) {
        if (this.f11385v != i7) {
            this.f11385v = i7;
            if (i7 == 0) {
                this.f11384u = getContext();
            } else {
                this.f11384u = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    public void setSubtitle(int i7) {
        setSubtitle(getContext().getText(i7));
    }

    public void setSubtitleTextColor(int i7) {
        setSubtitleTextColor(ColorStateList.valueOf(i7));
    }

    public void setTitle(int i7) {
        setTitle(getContext().getText(i7));
    }

    public void setTitleMarginBottom(int i7) {
        this.f11353D = i7;
        requestLayout();
    }

    public void setTitleMarginEnd(int i7) {
        this.B = i7;
        requestLayout();
    }

    public void setTitleMarginStart(int i7) {
        this.f11351A = i7;
        requestLayout();
    }

    public void setTitleMarginTop(int i7) {
        this.f11352C = i7;
        requestLayout();
    }

    public void setTitleTextColor(int i7) {
        setTitleTextColor(ColorStateList.valueOf(i7));
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, R.attr.toolbarStyle);
        this.f11357H = 8388627;
        this.f11364O = new ArrayList();
        this.f11365P = new ArrayList();
        this.f11366Q = new int[2];
        this.f11367R = new C0250i(new t0(this, 1));
        this.f11368S = new ArrayList();
        this.f11369T = new u0(this);
        this.f11376d0 = new RunnableC0079c(this, 29);
        Context context2 = getContext();
        int[] iArr = AbstractC1850a.f19366s;
        y yVarF = y.F(context2, attributeSet, iArr, R.attr.toolbarStyle);
        int[] iArr2 = H.f2303a;
        E.b(this, context, iArr, attributeSet, (TypedArray) yVarF.f15500n, R.attr.toolbarStyle, 0);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        this.f11386w = typedArray.getResourceId(28, 0);
        this.f11387x = typedArray.getResourceId(19, 0);
        this.f11357H = typedArray.getInteger(0, 8388627);
        this.f11388y = typedArray.getInteger(2, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(22, 0);
        dimensionPixelOffset = typedArray.hasValue(27) ? typedArray.getDimensionPixelOffset(27, dimensionPixelOffset) : dimensionPixelOffset;
        this.f11353D = dimensionPixelOffset;
        this.f11352C = dimensionPixelOffset;
        this.B = dimensionPixelOffset;
        this.f11351A = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.f11351A = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.B = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(26, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.f11352C = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(23, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.f11353D = dimensionPixelOffset5;
        }
        this.f11389z = typedArray.getDimensionPixelSize(13, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(9, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(5, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(7, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(8, 0);
        d();
        n0 n0Var = this.f11354E;
        n0Var.f21539h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            n0Var.f21536e = dimensionPixelSize;
            n0Var.f21534a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            n0Var.f21537f = dimensionPixelSize2;
            n0Var.f21535b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            n0Var.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.f11355F = typedArray.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        this.f11356G = typedArray.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        this.f11380q = yVarF.s(4);
        this.f11381r = typedArray.getText(3);
        CharSequence text = typedArray.getText(21);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(18);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.f11384u = getContext();
        setPopupTheme(typedArray.getResourceId(17, 0));
        Drawable drawableS = yVarF.s(16);
        if (drawableS != null) {
            setNavigationIcon(drawableS);
        }
        CharSequence text3 = typedArray.getText(15);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawableS2 = yVarF.s(11);
        if (drawableS2 != null) {
            setLogo(drawableS2);
        }
        CharSequence text4 = typedArray.getText(12);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(29)) {
            setTitleTextColor(yVarF.p(29));
        }
        if (typedArray.hasValue(20)) {
            setSubtitleTextColor(yVarF.p(20));
        }
        if (typedArray.hasValue(14)) {
            getMenuInflater().inflate(typedArray.getResourceId(14, 0), getMenu());
        }
        yVarF.G();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        x0 x0Var = new x0(context, attributeSet);
        x0Var.f21568a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.f19353b);
        x0Var.f21568a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        x0Var.f21569b = 0;
        return x0Var;
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        C2262u c2262u = this.f11382s;
        if (c2262u != null) {
            c2262u.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.f11382s.setImageDrawable(drawable);
        } else {
            C2262u c2262u = this.f11382s;
            if (c2262u != null) {
                c2262u.setImageDrawable(this.f11380q);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f11379p == null) {
                this.f11379p = new C2263v(getContext(), 0);
            }
            if (!m(this.f11379p)) {
                b(this.f11379p, true);
            }
        } else {
            C2263v c2263v = this.f11379p;
            if (c2263v != null && m(c2263v)) {
                removeView(this.f11379p);
                this.f11365P.remove(this.f11379p);
            }
        }
        C2263v c2263v2 = this.f11379p;
        if (c2263v2 != null) {
            c2263v2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f11379p == null) {
            this.f11379p = new C2263v(getContext(), 0);
        }
        C2263v c2263v = this.f11379p;
        if (c2263v != null) {
            c2263v.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            f();
        }
        C2262u c2262u = this.o;
        if (c2262u != null) {
            c2262u.setContentDescription(charSequence);
            B0.a(this.o, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            f();
            if (!m(this.o)) {
                b(this.o, true);
            }
        } else {
            C2262u c2262u = this.o;
            if (c2262u != null && m(c2262u)) {
                removeView(this.o);
                this.f11365P.remove(this.o);
            }
        }
        C2262u c2262u2 = this.o;
        if (c2262u2 != null) {
            c2262u2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            C2218F c2218f = this.f11378n;
            if (c2218f != null && m(c2218f)) {
                removeView(this.f11378n);
                this.f11365P.remove(this.f11378n);
            }
        } else {
            if (this.f11378n == null) {
                Context context = getContext();
                C2218F c2218f2 = new C2218F(context, null);
                this.f11378n = c2218f2;
                c2218f2.setSingleLine();
                this.f11378n.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f11387x;
                if (i7 != 0) {
                    this.f11378n.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.f11361L;
                if (colorStateList != null) {
                    this.f11378n.setTextColor(colorStateList);
                }
            }
            if (!m(this.f11378n)) {
                b(this.f11378n, true);
            }
        }
        C2218F c2218f3 = this.f11378n;
        if (c2218f3 != null) {
            c2218f3.setText(charSequence);
        }
        this.f11359J = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.f11361L = colorStateList;
        C2218F c2218f = this.f11378n;
        if (c2218f != null) {
            c2218f.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            C2218F c2218f = this.f11377m;
            if (c2218f != null && m(c2218f)) {
                removeView(this.f11377m);
                this.f11365P.remove(this.f11377m);
            }
        } else {
            if (this.f11377m == null) {
                Context context = getContext();
                C2218F c2218f2 = new C2218F(context, null);
                this.f11377m = c2218f2;
                c2218f2.setSingleLine();
                this.f11377m.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f11386w;
                if (i7 != 0) {
                    this.f11377m.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.f11360K;
                if (colorStateList != null) {
                    this.f11377m.setTextColor(colorStateList);
                }
            }
            if (!m(this.f11377m)) {
                b(this.f11377m, true);
            }
        }
        C2218F c2218f3 = this.f11377m;
        if (c2218f3 != null) {
            c2218f3.setText(charSequence);
        }
        this.f11358I = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.f11360K = colorStateList;
        C2218F c2218f = this.f11377m;
        if (c2218f != null) {
            c2218f.setTextColor(colorStateList);
        }
    }
}
