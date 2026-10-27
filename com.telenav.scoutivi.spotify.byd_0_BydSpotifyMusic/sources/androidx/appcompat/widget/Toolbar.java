package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.e;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private CharSequence C;
    private CharSequence D;
    private ColorStateList E;
    private ColorStateList F;
    private boolean G;
    private boolean H;
    private final ArrayList<View> I;
    private final ArrayList<View> J;
    private final int[] K;
    f L;
    private final ActionMenuView.e M;
    private t0 N;
    private ActionMenuPresenter O;
    private d P;
    private j.a Q;
    private e.a R;
    private boolean S;
    private final Runnable T;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ActionMenuView f948e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f949g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextView f950h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ImageButton f951i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ImageView f952j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Drawable f953k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private CharSequence f954l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    ImageButton f955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    View f956n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Context f957o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f958p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f959q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f960r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    int f961s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f962t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f963u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f964v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f965w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f966x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private k0 f967y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f968z;

    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.L;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.N();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.e();
        }
    }

    private class d implements androidx.appcompat.view.menu.j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        androidx.appcompat.view.menu.e f974e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        androidx.appcompat.view.menu.g f975g;

        d() {
        }

        @Override // androidx.appcompat.view.menu.j
        public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
        }

        @Override // androidx.appcompat.view.menu.j
        public boolean c() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.j
        public void d(Context context, androidx.appcompat.view.menu.e eVar) {
            androidx.appcompat.view.menu.g gVar;
            androidx.appcompat.view.menu.e eVar2 = this.f974e;
            if (eVar2 != null && (gVar = this.f975g) != null) {
                eVar2.f(gVar);
            }
            this.f974e = eVar;
        }

        @Override // androidx.appcompat.view.menu.j
        public boolean e(androidx.appcompat.view.menu.e eVar, androidx.appcompat.view.menu.g gVar) {
            KeyEvent.Callback callback = Toolbar.this.f956n;
            if (callback instanceof i.c) {
                ((i.c) callback).f();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f956n);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f955m);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f956n = null;
            toolbar3.a();
            this.f975g = null;
            Toolbar.this.requestLayout();
            gVar.r(false);
            return true;
        }

        @Override // androidx.appcompat.view.menu.j
        public boolean f(androidx.appcompat.view.menu.e eVar, androidx.appcompat.view.menu.g gVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f955m.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f955m);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f955m);
            }
            Toolbar.this.f956n = gVar.getActionView();
            this.f975g = gVar;
            ViewParent parent2 = Toolbar.this.f956n.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f956n);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f501a = 8388611 | (toolbar4.f961s & 112);
                eVarGenerateDefaultLayoutParams.f977b = 2;
                toolbar4.f956n.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.f956n);
            }
            Toolbar.this.G();
            Toolbar.this.requestLayout();
            gVar.r(true);
            KeyEvent.Callback callback = Toolbar.this.f956n;
            if (callback instanceof i.c) {
                ((i.c) callback).c();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.j
        public boolean i(androidx.appcompat.view.menu.m mVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.j
        public void j(boolean z6) {
            if (this.f975g != null) {
                androidx.appcompat.view.menu.e eVar = this.f974e;
                boolean z7 = false;
                if (eVar != null) {
                    int size = eVar.size();
                    int i7 = 0;
                    while (true) {
                        if (i7 >= size) {
                            break;
                        }
                        if (this.f974e.getItem(i7) == this.f975g) {
                            z7 = true;
                            break;
                        }
                        i7++;
                    }
                }
                if (z7) {
                    return;
                }
                e(this.f974e, this.f975g);
            }
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.toolbarStyle);
    }

    private int B(View view, int i7, int[] iArr, int i8) {
        e eVar = (e) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i7 + Math.max(0, i9);
        iArr[0] = Math.max(0, -i9);
        int iQ = q(view, i8);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    private int C(View view, int i7, int[] iArr, int i8) {
        e eVar = (e) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i7 - Math.max(0, i9);
        iArr[1] = Math.max(0, -i9);
        int iQ = q(view, i8);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int D(View view, int i7, int i8, int i9, int i10, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i11 = marginLayoutParams.leftMargin - iArr[0];
        int i12 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i11) + Math.max(0, i12);
        iArr[0] = Math.max(0, -i11);
        iArr[1] = Math.max(0, -i12);
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight() + iMax + i8, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i9, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private void E(View view, int i7, int i8, int i9, int i10, int i11) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i9, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i11 >= 0) {
            if (mode != 0) {
                i11 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i11);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void F() {
        removeCallbacks(this.T);
        post(this.T);
    }

    private boolean L() {
        if (!this.S) {
            return false;
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (M(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean M(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private void b(List<View> list, int i7) {
        boolean z6 = i0.u.y(this) == 1;
        int childCount = getChildCount();
        int iB = i0.e.b(i7, i0.u.y(this));
        list.clear();
        if (!z6) {
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f977b == 0 && M(childAt) && p(eVar.f501a) == iB) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i9 = childCount - 1; i9 >= 0; i9--) {
            View childAt2 = getChildAt(i9);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f977b == 0 && M(childAt2) && p(eVar2.f501a) == iB) {
                list.add(childAt2);
            }
        }
    }

    private void c(View view, boolean z6) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f977b = 1;
        if (!z6 || this.f956n == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.J.add(view);
        }
    }

    private MenuInflater getMenuInflater() {
        return new i.g(getContext());
    }

    private void h() {
        if (this.f967y == null) {
            this.f967y = new k0();
        }
    }

    private void i() {
        if (this.f952j == null) {
            this.f952j = new m(getContext());
        }
    }

    private void j() {
        k();
        if (this.f948e.N() == null) {
            androidx.appcompat.view.menu.e eVar = (androidx.appcompat.view.menu.e) this.f948e.getMenu();
            if (this.P == null) {
                this.P = new d();
            }
            this.f948e.setExpandedActionViewsExclusive(true);
            eVar.c(this.P, this.f957o);
        }
    }

    private void k() {
        if (this.f948e == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f948e = actionMenuView;
            actionMenuView.setPopupTheme(this.f958p);
            this.f948e.setOnMenuItemClickListener(this.M);
            this.f948e.O(this.Q, this.R);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f501a = 8388613 | (this.f961s & 112);
            this.f948e.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f948e, false);
        }
    }

    private void l() {
        if (this.f951i == null) {
            this.f951i = new k(getContext(), null, e.a.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f501a = 8388611 | (this.f961s & 112);
            this.f951i.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    private int p(int i7) {
        int iY = i0.u.y(this);
        int iB = i0.e.b(i7, iY) & 7;
        return (iB == 1 || iB == 3 || iB == 5) ? iB : iY == 1 ? 5 : 3;
    }

    private int q(View view, int i7) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i8 = i7 > 0 ? (measuredHeight - i7) / 2 : 0;
        int iR = r(eVar.f501a);
        if (iR == 48) {
            return getPaddingTop() - i8;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i8;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i9 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i9) {
            iMax = i9;
        } else {
            int i10 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i11 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i10 < i11) {
                iMax = Math.max(0, iMax - (i11 - i10));
            }
        }
        return paddingTop + iMax;
    }

    private int r(int i7) {
        int i8 = i7 & 112;
        return (i8 == 16 || i8 == 48 || i8 == 80) ? i8 : this.B & 112;
    }

    private int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return i0.h.b(marginLayoutParams) + i0.h.a(marginLayoutParams);
    }

    private int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int u(List<View> list, int[] iArr) {
        int i7 = iArr[0];
        int i8 = iArr[1];
        int size = list.size();
        int i9 = 0;
        int measuredWidth = 0;
        while (i9 < size) {
            View view = list.get(i9);
            e eVar = (e) view.getLayoutParams();
            int i10 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i7;
            int i11 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i8;
            int iMax = Math.max(0, i10);
            int iMax2 = Math.max(0, i11);
            int iMax3 = Math.max(0, -i10);
            int iMax4 = Math.max(0, -i11);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i9++;
            i8 = iMax4;
            i7 = iMax3;
        }
        return measuredWidth;
    }

    private boolean y(View view) {
        return view.getParent() == this || this.J.contains(view);
    }

    public boolean A() {
        ActionMenuView actionMenuView = this.f948e;
        return actionMenuView != null && actionMenuView.J();
    }

    void G() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f977b != 2 && childAt != this.f948e) {
                removeViewAt(childCount);
                this.J.add(childAt);
            }
        }
    }

    public void H(int i7, int i8) {
        h();
        this.f967y.g(i7, i8);
    }

    public void I(androidx.appcompat.view.menu.e eVar, ActionMenuPresenter actionMenuPresenter) {
        if (eVar == null && this.f948e == null) {
            return;
        }
        k();
        androidx.appcompat.view.menu.e eVarN = this.f948e.N();
        if (eVarN == eVar) {
            return;
        }
        if (eVarN != null) {
            eVarN.O(this.O);
            eVarN.O(this.P);
        }
        if (this.P == null) {
            this.P = new d();
        }
        actionMenuPresenter.G(true);
        if (eVar != null) {
            eVar.c(actionMenuPresenter, this.f957o);
            eVar.c(this.P, this.f957o);
        } else {
            actionMenuPresenter.d(this.f957o, null);
            this.P.d(this.f957o, null);
            actionMenuPresenter.j(true);
            this.P.j(true);
        }
        this.f948e.setPopupTheme(this.f958p);
        this.f948e.setPresenter(actionMenuPresenter);
        this.O = actionMenuPresenter;
    }

    public void J(Context context, int i7) {
        this.f960r = i7;
        TextView textView = this.f950h;
        if (textView != null) {
            textView.setTextAppearance(context, i7);
        }
    }

    public void K(Context context, int i7) {
        this.f959q = i7;
        TextView textView = this.f949g;
        if (textView != null) {
            textView.setTextAppearance(context, i7);
        }
    }

    public boolean N() {
        ActionMenuView actionMenuView = this.f948e;
        return actionMenuView != null && actionMenuView.P();
    }

    void a() {
        for (int size = this.J.size() - 1; size >= 0; size--) {
            addView(this.J.get(size));
        }
        this.J.clear();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f948e) != null && actionMenuView.K();
    }

    public void e() {
        d dVar = this.P;
        androidx.appcompat.view.menu.g gVar = dVar == null ? null : dVar.f975g;
        if (gVar != null) {
            gVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f948e;
        if (actionMenuView != null) {
            actionMenuView.B();
        }
    }

    void g() {
        if (this.f955m == null) {
            k kVar = new k(getContext(), null, e.a.toolbarNavigationButtonStyle);
            this.f955m = kVar;
            kVar.setImageDrawable(this.f953k);
            this.f955m.setContentDescription(this.f954l);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f501a = 8388611 | (this.f961s & 112);
            eVarGenerateDefaultLayoutParams.f977b = 2;
            this.f955m.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f955m.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.f955m;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.f955m;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        k0 k0Var = this.f967y;
        if (k0Var != null) {
            return k0Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i7 = this.A;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        k0 k0Var = this.f967y;
        if (k0Var != null) {
            return k0Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        k0 k0Var = this.f967y;
        if (k0Var != null) {
            return k0Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        k0 k0Var = this.f967y;
        if (k0Var != null) {
            return k0Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i7 = this.f968z;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.e eVarN;
        ActionMenuView actionMenuView = this.f948e;
        return actionMenuView != null && (eVarN = actionMenuView.N()) != null && eVarN.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.A, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return i0.u.y(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return i0.u.y(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f968z, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f952j;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f952j;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f948e.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f951i;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f951i;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.O;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f948e.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.f957o;
    }

    public int getPopupTheme() {
        return this.f958p;
    }

    public CharSequence getSubtitle() {
        return this.D;
    }

    final TextView getSubtitleTextView() {
        return this.f950h;
    }

    public CharSequence getTitle() {
        return this.C;
    }

    public int getTitleMarginBottom() {
        return this.f966x;
    }

    public int getTitleMarginEnd() {
        return this.f964v;
    }

    public int getTitleMarginStart() {
        return this.f963u;
    }

    public int getTitleMarginTop() {
        return this.f965w;
    }

    final TextView getTitleTextView() {
        return this.f949g;
    }

    public a0 getWrapper() {
        if (this.N == null) {
            this.N = new t0(this, true);
        }
        return this.N;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof a.C0013a ? new e((a.C0013a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.T);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.H = false;
        }
        if (!this.H) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.H = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.H = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x029f A[LOOP:0: B:104:0x029d->B:105:0x029f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c1 A[LOOP:1: B:107:0x02bf->B:108:0x02c1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fa A[LOOP:2: B:116:0x02f8->B:117:0x02fa, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0227  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 783
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.K;
        boolean zB = w0.b(this);
        int i9 = !zB ? 1 : 0;
        if (M(this.f951i)) {
            E(this.f951i, i7, 0, i8, 0, this.f962t);
            measuredWidth = this.f951i.getMeasuredWidth() + s(this.f951i);
            iMax = Math.max(0, this.f951i.getMeasuredHeight() + t(this.f951i));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f951i.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (M(this.f955m)) {
            E(this.f955m, i7, 0, i8, 0, this.f962t);
            measuredWidth = this.f955m.getMeasuredWidth() + s(this.f955m);
            iMax = Math.max(iMax, this.f955m.getMeasuredHeight() + t(this.f955m));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f955m.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[zB ? 1 : 0] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (M(this.f948e)) {
            E(this.f948e, i7, iMax3, i8, 0, this.f962t);
            measuredWidth2 = this.f948e.getMeasuredWidth() + s(this.f948e);
            iMax = Math.max(iMax, this.f948e.getMeasuredHeight() + t(this.f948e));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f948e.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[i9] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (M(this.f956n)) {
            iMax4 += D(this.f956n, i7, iMax4, i8, 0, iArr);
            iMax = Math.max(iMax, this.f956n.getMeasuredHeight() + t(this.f956n));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f956n.getMeasuredState());
        }
        if (M(this.f952j)) {
            iMax4 += D(this.f952j, i7, iMax4, i8, 0, iArr);
            iMax = Math.max(iMax, this.f952j.getMeasuredHeight() + t(this.f952j));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f952j.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (((e) childAt.getLayoutParams()).f977b == 0 && M(childAt)) {
                iMax4 += D(childAt, i7, iMax4, i8, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i11 = this.f965w + this.f966x;
        int i12 = this.f963u + this.f964v;
        if (M(this.f949g)) {
            D(this.f949g, i7, iMax4 + i12, i8, i11, iArr);
            int measuredWidth3 = this.f949g.getMeasuredWidth() + s(this.f949g);
            measuredHeight = this.f949g.getMeasuredHeight() + t(this.f949g);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f949g.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (M(this.f950h)) {
            iMax2 = Math.max(iMax2, D(this.f950h, i7, iMax4 + i12, i8, measuredHeight + i11, iArr));
            measuredHeight += this.f950h.getMeasuredHeight() + t(this.f950h);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f950h.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i7, (-16777216) & iCombineMeasuredStates2), L() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i8, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        ActionMenuView actionMenuView = this.f948e;
        androidx.appcompat.view.menu.e eVarN = actionMenuView != null ? actionMenuView.N() : null;
        int i7 = savedState.f969h;
        if (i7 != 0 && this.P != null && eVarN != null && (menuItemFindItem = eVarN.findItem(i7)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f970i) {
            F();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        h();
        this.f967y.f(i7 == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.g gVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.P;
        if (dVar != null && (gVar = dVar.f975g) != null) {
            savedState.f969h = gVar.getItemId();
        }
        savedState.f970i = A();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.G = false;
        }
        if (!this.G) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.G = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.G = false;
        }
        return true;
    }

    public void setCollapseContentDescription(int i7) {
        setCollapseContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setCollapseIcon(int i7) {
        setCollapseIcon(f.a.b(getContext(), i7));
    }

    public void setCollapsible(boolean z6) {
        this.S = z6;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.A) {
            this.A = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.f968z) {
            this.f968z = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i7) {
        setLogo(f.a.b(getContext(), i7));
    }

    public void setLogoDescription(int i7) {
        setLogoDescription(getContext().getText(i7));
    }

    public void setNavigationContentDescription(int i7) {
        setNavigationContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setNavigationIcon(int i7) {
        setNavigationIcon(f.a.b(getContext(), i7));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f951i.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.L = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f948e.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i7) {
        if (this.f958p != i7) {
            this.f958p = i7;
            if (i7 == 0) {
                this.f957o = getContext();
            } else {
                this.f957o = new ContextThemeWrapper(getContext(), i7);
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
        this.f966x = i7;
        requestLayout();
    }

    public void setTitleMarginEnd(int i7) {
        this.f964v = i7;
        requestLayout();
    }

    public void setTitleMarginStart(int i7) {
        this.f963u = i7;
        requestLayout();
    }

    public void setTitleMarginTop(int i7) {
        this.f965w = i7;
        requestLayout();
    }

    public void setTitleTextColor(int i7) {
        setTitleTextColor(ColorStateList.valueOf(i7));
    }

    public boolean v() {
        d dVar = this.P;
        return (dVar == null || dVar.f975g == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f948e;
        return actionMenuView != null && actionMenuView.H();
    }

    public void x(int i7) {
        getMenuInflater().inflate(i7, getMenu());
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f948e;
        return actionMenuView != null && actionMenuView.I();
    }

    public static class e extends a.C0013a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f977b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f977b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i7, int i8) {
            super(i7, i8);
            this.f977b = 0;
            this.f501a = 8388627;
        }

        public e(e eVar) {
            super((a.C0013a) eVar);
            this.f977b = 0;
            this.f977b = eVar.f977b;
        }

        public e(a.C0013a c0013a) {
            super(c0013a);
            this.f977b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f977b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f977b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.B = 8388627;
        this.I = new ArrayList<>();
        this.J = new ArrayList<>();
        this.K = new int[2];
        this.M = new a();
        this.T = new b();
        Context context2 = getContext();
        int[] iArr = e.j.Toolbar;
        s0 s0VarV = s0.v(context2, attributeSet, iArr, i7, 0);
        i0.u.i0(this, context, iArr, attributeSet, s0VarV.r(), i7, 0);
        this.f959q = s0VarV.n(e.j.Toolbar_titleTextAppearance, 0);
        this.f960r = s0VarV.n(e.j.Toolbar_subtitleTextAppearance, 0);
        this.B = s0VarV.l(e.j.Toolbar_android_gravity, this.B);
        this.f961s = s0VarV.l(e.j.Toolbar_buttonGravity, 48);
        int iE = s0VarV.e(e.j.Toolbar_titleMargin, 0);
        int i8 = e.j.Toolbar_titleMargins;
        iE = s0VarV.s(i8) ? s0VarV.e(i8, iE) : iE;
        this.f966x = iE;
        this.f965w = iE;
        this.f964v = iE;
        this.f963u = iE;
        int iE2 = s0VarV.e(e.j.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.f963u = iE2;
        }
        int iE3 = s0VarV.e(e.j.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.f964v = iE3;
        }
        int iE4 = s0VarV.e(e.j.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.f965w = iE4;
        }
        int iE5 = s0VarV.e(e.j.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.f966x = iE5;
        }
        this.f962t = s0VarV.f(e.j.Toolbar_maxButtonHeight, -1);
        int iE6 = s0VarV.e(e.j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = s0VarV.e(e.j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = s0VarV.f(e.j.Toolbar_contentInsetLeft, 0);
        int iF2 = s0VarV.f(e.j.Toolbar_contentInsetRight, 0);
        h();
        this.f967y.e(iF, iF2);
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.f967y.g(iE6, iE7);
        }
        this.f968z = s0VarV.e(e.j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.A = s0VarV.e(e.j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f953k = s0VarV.g(e.j.Toolbar_collapseIcon);
        this.f954l = s0VarV.p(e.j.Toolbar_collapseContentDescription);
        CharSequence charSequenceP = s0VarV.p(e.j.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceP)) {
            setTitle(charSequenceP);
        }
        CharSequence charSequenceP2 = s0VarV.p(e.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceP2)) {
            setSubtitle(charSequenceP2);
        }
        this.f957o = getContext();
        setPopupTheme(s0VarV.n(e.j.Toolbar_popupTheme, 0));
        Drawable drawableG = s0VarV.g(e.j.Toolbar_navigationIcon);
        if (drawableG != null) {
            setNavigationIcon(drawableG);
        }
        CharSequence charSequenceP3 = s0VarV.p(e.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceP3)) {
            setNavigationContentDescription(charSequenceP3);
        }
        Drawable drawableG2 = s0VarV.g(e.j.Toolbar_logo);
        if (drawableG2 != null) {
            setLogo(drawableG2);
        }
        CharSequence charSequenceP4 = s0VarV.p(e.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceP4)) {
            setLogoDescription(charSequenceP4);
        }
        int i9 = e.j.Toolbar_titleTextColor;
        if (s0VarV.s(i9)) {
            setTitleTextColor(s0VarV.c(i9));
        }
        int i10 = e.j.Toolbar_subtitleTextColor;
        if (s0VarV.s(i10)) {
            setSubtitleTextColor(s0VarV.c(i10));
        }
        int i11 = e.j.Toolbar_menu;
        if (s0VarV.s(i11)) {
            x(s0VarV.n(i11, 0));
        }
        s0VarV.w();
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f955m;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.f955m.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.f955m;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f953k);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!y(this.f952j)) {
                c(this.f952j, true);
            }
        } else {
            ImageView imageView = this.f952j;
            if (imageView != null && y(imageView)) {
                removeView(this.f952j);
                this.J.remove(this.f952j);
            }
        }
        ImageView imageView2 = this.f952j;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f952j;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f951i;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!y(this.f951i)) {
                c(this.f951i, true);
            }
        } else {
            ImageButton imageButton = this.f951i;
            if (imageButton != null && y(imageButton)) {
                removeView(this.f951i);
                this.J.remove(this.f951i);
            }
        }
        ImageButton imageButton2 = this.f951i;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f950h;
            if (textView != null && y(textView)) {
                removeView(this.f950h);
                this.J.remove(this.f950h);
            }
        } else {
            if (this.f950h == null) {
                Context context = getContext();
                w wVar = new w(context);
                this.f950h = wVar;
                wVar.setSingleLine();
                this.f950h.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f960r;
                if (i7 != 0) {
                    this.f950h.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.F;
                if (colorStateList != null) {
                    this.f950h.setTextColor(colorStateList);
                }
            }
            if (!y(this.f950h)) {
                c(this.f950h, true);
            }
        }
        TextView textView2 = this.f950h;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.D = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.F = colorStateList;
        TextView textView = this.f950h;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f949g;
            if (textView != null && y(textView)) {
                removeView(this.f949g);
                this.J.remove(this.f949g);
            }
        } else {
            if (this.f949g == null) {
                Context context = getContext();
                w wVar = new w(context);
                this.f949g = wVar;
                wVar.setSingleLine();
                this.f949g.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f959q;
                if (i7 != 0) {
                    this.f949g.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.E;
                if (colorStateList != null) {
                    this.f949g.setTextColor(colorStateList);
                }
            }
            if (!y(this.f949g)) {
                c(this.f949g, true);
            }
        }
        TextView textView2 = this.f949g;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.C = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.E = colorStateList;
        TextView textView = this.f949g;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f969h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f970i;

        class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f969h = parcel.readInt();
            this.f970i = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f969h);
            parcel.writeInt(this.f970i ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
