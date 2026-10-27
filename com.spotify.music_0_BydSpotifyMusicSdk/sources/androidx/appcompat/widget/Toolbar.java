package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import p.b9;
import p.bm2;
import p.cm2;
import p.dm2;
import p.e6;
import p.em2;
import p.ev2;
import p.fa;
import p.fm2;
import p.g4;
import p.gm2;
import p.hk0;
import p.hm2;
import p.hx0;
import p.im2;
import p.j60;
import p.jt2;
import p.kk0;
import p.l13;
import p.lh2;
import p.lt2;
import p.p01;
import p.r3;
import p.r52;
import p.rv;
import p.si1;
import p.t3;
import p.ui1;
import p.v4;
import p.xe1;
import p.xi1;
import p.yz1;
import p.z8;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup implements ui1 {
    public int A;
    public r52 B;
    public int C;
    public int D;
    public final int E;
    public CharSequence F;
    public CharSequence G;
    public ColorStateList H;
    public ColorStateList I;
    public boolean J;
    public boolean K;
    public final ArrayList L;
    public final ArrayList M;
    public final int[] N;
    public final kk0 O;
    public ArrayList P;
    public final p01 Q;
    public hm2 R;
    public g4 S;
    public dm2 T;
    public boolean U;
    public OnBackInvokedCallback V;
    public OnBackInvokedDispatcher W;
    public boolean a0;
    public final rv b0;
    public ActionMenuView c;
    public fa e;
    public fa k;
    public z8 l;
    public b9 m;
    public final Drawable n;
    public final CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public z8 f11p;
    public View q;
    public Context r;
    public int s;
    public int t;
    public int u;
    public final int v;
    public final int w;
    public int x;
    public int y;
    public int z;

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i = 0; i < menu.size(); i++) {
            arrayList.add(menu.getItem(i));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new lh2(getContext());
    }

    public static em2 h() {
        em2 em2Var = new em2(-2, -2);
        em2Var.b = 0;
        em2Var.a = 8388627;
        return em2Var;
    }

    public static em2 i(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof em2) {
            em2 em2Var = (em2) layoutParams;
            em2 em2Var2 = new em2(em2Var);
            em2Var2.b = 0;
            em2Var2.b = em2Var.b;
            return em2Var2;
        }
        if (layoutParams instanceof r3) {
            em2 em2Var3 = new em2((r3) layoutParams);
            em2Var3.b = 0;
            return em2Var3;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            em2 em2Var4 = new em2(layoutParams);
            em2Var4.b = 0;
            return em2Var4;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        em2 em2Var5 = new em2(marginLayoutParams);
        em2Var5.b = 0;
        ((ViewGroup.MarginLayoutParams) em2Var5).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) em2Var5).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) em2Var5).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) em2Var5).bottomMargin = marginLayoutParams.bottomMargin;
        return em2Var5;
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return xe1.b(marginLayoutParams) + xe1.c(marginLayoutParams);
    }

    public static int m(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(int i, ArrayList arrayList) {
        WeakHashMap weakHashMap = zt2.a;
        boolean z = jt2.d(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, jt2.d(this));
        arrayList.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                em2 em2Var = (em2) childAt.getLayoutParams();
                if (em2Var.b == 0 && t(childAt) && j(em2Var.a) == absoluteGravity) {
                    arrayList.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            em2 em2Var2 = (em2) childAt2.getLayoutParams();
            if (em2Var2.b == 0 && t(childAt2) && j(em2Var2.a) == absoluteGravity) {
                arrayList.add(childAt2);
            }
        }
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        em2 em2VarH = layoutParams == null ? h() : !checkLayoutParams(layoutParams) ? i(layoutParams) : (em2) layoutParams;
        em2VarH.b = 1;
        if (!z || this.q == null) {
            addView(view, em2VarH);
        } else {
            view.setLayoutParams(em2VarH);
            this.M.add(view);
        }
    }

    public final void c() {
        if (this.f11p == null) {
            z8 z8Var = new z8(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.f11p = z8Var;
            z8Var.setImageDrawable(this.n);
            this.f11p.setContentDescription(this.o);
            em2 em2VarH = h();
            em2VarH.a = (this.v & 112) | 8388611;
            em2VarH.b = 2;
            this.f11p.setLayoutParams(em2VarH);
            this.f11p.setOnClickListener(new e6(1, this));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof em2);
    }

    public final void d() {
        if (this.B == null) {
            r52 r52Var = new r52();
            r52Var.a = 0;
            r52Var.b = 0;
            r52Var.c = Integer.MIN_VALUE;
            r52Var.d = Integer.MIN_VALUE;
            r52Var.e = 0;
            r52Var.f = 0;
            r52Var.g = false;
            r52Var.h = false;
            this.B = r52Var;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.c;
        if (actionMenuView.x == null) {
            si1 si1Var = (si1) actionMenuView.getMenu();
            if (this.T == null) {
                this.T = new dm2(this);
            }
            this.c.setExpandedActionViewsExclusive(true);
            si1Var.b(this.T, this.r);
            u();
        }
    }

    public final void f() {
        if (this.c == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.c = actionMenuView;
            actionMenuView.setPopupTheme(this.s);
            this.c.setOnMenuItemClickListener(this.Q);
            ActionMenuView actionMenuView2 = this.c;
            hk0 hk0Var = new hk0(6, this);
            actionMenuView2.C = null;
            actionMenuView2.D = hk0Var;
            em2 em2VarH = h();
            em2VarH.a = (this.v & 112) | 8388613;
            this.c.setLayoutParams(em2VarH);
            b(this.c, false);
        }
    }

    public final void g() {
        if (this.l == null) {
            this.l = new z8(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            em2 em2VarH = h();
            em2VarH.a = (this.v & 112) | 8388611;
            this.l.setLayoutParams(em2VarH);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return i(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        z8 z8Var = this.f11p;
        if (z8Var != null) {
            return z8Var.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        z8 z8Var = this.f11p;
        if (z8Var != null) {
            return z8Var.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        r52 r52Var = this.B;
        if (r52Var != null) {
            return r52Var.g ? r52Var.a : r52Var.b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.D;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        r52 r52Var = this.B;
        if (r52Var != null) {
            return r52Var.a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        r52 r52Var = this.B;
        if (r52Var != null) {
            return r52Var.b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        r52 r52Var = this.B;
        if (r52Var != null) {
            return r52Var.g ? r52Var.b : r52Var.a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.C;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        si1 si1Var;
        ActionMenuView actionMenuView = this.c;
        return (actionMenuView == null || (si1Var = actionMenuView.x) == null || !si1Var.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.D, 0));
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = zt2.a;
        return jt2.d(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = zt2.a;
        return jt2.d(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.C, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        b9 b9Var = this.m;
        if (b9Var != null) {
            return b9Var.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        b9 b9Var = this.m;
        if (b9Var != null) {
            return b9Var.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.c.getMenu();
    }

    public View getNavButtonView() {
        return this.l;
    }

    public CharSequence getNavigationContentDescription() {
        z8 z8Var = this.l;
        if (z8Var != null) {
            return z8Var.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        z8 z8Var = this.l;
        if (z8Var != null) {
            return z8Var.getDrawable();
        }
        return null;
    }

    public g4 getOuterActionMenuPresenter() {
        return this.S;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.c.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.r;
    }

    public int getPopupTheme() {
        return this.s;
    }

    public CharSequence getSubtitle() {
        return this.G;
    }

    public final TextView getSubtitleTextView() {
        return this.k;
    }

    public CharSequence getTitle() {
        return this.F;
    }

    public int getTitleMarginBottom() {
        return this.A;
    }

    public int getTitleMarginEnd() {
        return this.y;
    }

    public int getTitleMarginStart() {
        return this.x;
    }

    public int getTitleMarginTop() {
        return this.z;
    }

    public final TextView getTitleTextView() {
        return this.e;
    }

    public j60 getWrapper() {
        Drawable drawable;
        if (this.R == null) {
            hm2 hm2Var = new hm2();
            hm2Var.n = 0;
            hm2Var.a = this;
            hm2Var.h = getTitle();
            hm2Var.i = getSubtitle();
            hm2Var.g = hm2Var.h != null;
            hm2Var.f = getNavigationIcon();
            v4 v4VarJ = v4.J(getContext(), null, yz1.a, R.attr.actionBarStyle);
            hm2Var.o = v4VarJ.t(15);
            CharSequence charSequenceA = v4VarJ.A(27);
            if (!TextUtils.isEmpty(charSequenceA)) {
                hm2Var.g = true;
                hm2Var.h = charSequenceA;
                if ((hm2Var.b & 8) != 0) {
                    Toolbar toolbar = hm2Var.a;
                    toolbar.setTitle(charSequenceA);
                    if (hm2Var.g) {
                        zt2.l(toolbar.getRootView(), charSequenceA);
                    }
                }
            }
            CharSequence charSequenceA2 = v4VarJ.A(25);
            if (!TextUtils.isEmpty(charSequenceA2)) {
                hm2Var.i = charSequenceA2;
                if ((hm2Var.b & 8) != 0) {
                    setSubtitle(charSequenceA2);
                }
            }
            Drawable drawableT = v4VarJ.t(20);
            if (drawableT != null) {
                hm2Var.e = drawableT;
                hm2Var.c();
            }
            Drawable drawableT2 = v4VarJ.t(17);
            if (drawableT2 != null) {
                hm2Var.d = drawableT2;
                hm2Var.c();
            }
            if (hm2Var.f == null && (drawable = hm2Var.o) != null) {
                hm2Var.f = drawable;
                int i = hm2Var.b & 4;
                Toolbar toolbar2 = hm2Var.a;
                if (i != 0) {
                    toolbar2.setNavigationIcon(drawable);
                } else {
                    toolbar2.setNavigationIcon((Drawable) null);
                }
            }
            hm2Var.a(v4VarJ.w(10, 0));
            int iY = v4VarJ.y(9, 0);
            if (iY != 0) {
                View viewInflate = LayoutInflater.from(getContext()).inflate(iY, (ViewGroup) this, false);
                View view = hm2Var.c;
                if (view != null && (hm2Var.b & 16) != 0) {
                    removeView(view);
                }
                hm2Var.c = viewInflate;
                if (viewInflate != null && (hm2Var.b & 16) != 0) {
                    addView(viewInflate);
                }
                hm2Var.a(hm2Var.b | 16);
            }
            int layoutDimension = ((TypedArray) v4VarJ.k).getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = layoutDimension;
                setLayoutParams(layoutParams);
            }
            int iR = v4VarJ.r(7, -1);
            int iR2 = v4VarJ.r(3, -1);
            if (iR >= 0 || iR2 >= 0) {
                int iMax = Math.max(iR, 0);
                int iMax2 = Math.max(iR2, 0);
                d();
                this.B.a(iMax, iMax2);
            }
            int iY2 = v4VarJ.y(28, 0);
            if (iY2 != 0) {
                Context context = getContext();
                this.t = iY2;
                fa faVar = this.e;
                if (faVar != null) {
                    faVar.setTextAppearance(context, iY2);
                }
            }
            int iY3 = v4VarJ.y(26, 0);
            if (iY3 != 0) {
                Context context2 = getContext();
                this.u = iY3;
                fa faVar2 = this.k;
                if (faVar2 != null) {
                    faVar2.setTextAppearance(context2, iY3);
                }
            }
            int iY4 = v4VarJ.y(22, 0);
            if (iY4 != 0) {
                setPopupTheme(iY4);
            }
            v4VarJ.L();
            if (R.string.abc_action_bar_up_description != hm2Var.n) {
                hm2Var.n = R.string.abc_action_bar_up_description;
                if (TextUtils.isEmpty(getNavigationContentDescription())) {
                    int i2 = hm2Var.n;
                    hm2Var.j = i2 != 0 ? getContext().getString(i2) : null;
                    hm2Var.b();
                }
            }
            hm2Var.j = getNavigationContentDescription();
            setNavigationOnClickListener(new t3(hm2Var));
            this.R = hm2Var;
        }
        return this.R;
    }

    public final int j(int i) {
        WeakHashMap weakHashMap = zt2.a;
        int iD = jt2.d(this);
        int absoluteGravity = Gravity.getAbsoluteGravity(i, iD) & 7;
        return (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) ? absoluteGravity : iD == 1 ? 5 : 3;
    }

    public final int k(View view, int i) {
        em2 em2Var = (em2) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = em2Var.a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.E & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) em2Var).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) em2Var).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) em2Var).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public final void n() {
        Iterator it = this.P.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        Iterator it2 = ((CopyOnWriteArrayList) this.O.k).iterator();
        while (it2.hasNext()) {
            ((hx0) it2.next()).a.j();
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.P = currentMenuItems2;
    }

    public final boolean o(View view) {
        return view.getParent() == this || this.M.contains(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.b0);
        u();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.K = false;
        }
        if (!this.K) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.K = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.K = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0293 A[LOOP:0: B:104:0x0291->B:105:0x0293, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02af A[LOOP:1: B:107:0x02ad->B:108:0x02af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02ce A[LOOP:2: B:110:0x02cc->B:111:0x02ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x031c A[LOOP:3: B:119:0x031a->B:120:0x031c, LOOP_END] */
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
    /* JADX WARN: Removed duplicated region for block: B:65:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x021d  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 813
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iL;
        int iMax;
        int iCombineMeasuredStates;
        int iL2;
        int iM;
        int iCombineMeasuredStates2;
        int iMax2;
        boolean zA = ev2.a(this);
        int i3 = !zA ? 1 : 0;
        int i4 = 0;
        if (t(this.l)) {
            s(this.l, i, 0, i2, this.w);
            iL = l(this.l) + this.l.getMeasuredWidth();
            iMax = Math.max(0, m(this.l) + this.l.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.l.getMeasuredState());
        } else {
            iL = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (t(this.f11p)) {
            s(this.f11p, i, 0, i2, this.w);
            iL = l(this.f11p) + this.f11p.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.f11p) + this.f11p.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f11p.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iL);
        int iMax4 = Math.max(0, currentContentInsetStart - iL);
        int[] iArr = this.N;
        iArr[zA ? 1 : 0] = iMax4;
        if (t(this.c)) {
            s(this.c, i, iMax3, i2, this.w);
            iL2 = l(this.c) + this.c.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.c) + this.c.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.c.getMeasuredState());
        } else {
            iL2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = Math.max(currentContentInsetEnd, iL2) + iMax3;
        iArr[i3] = Math.max(0, currentContentInsetEnd - iL2);
        if (t(this.q)) {
            iMax5 += r(this.q, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.q) + this.q.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.q.getMeasuredState());
        }
        if (t(this.m)) {
            iMax5 += r(this.m, i, iMax5, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.m) + this.m.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.m.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (((em2) childAt.getLayoutParams()).b == 0 && t(childAt)) {
                iMax5 += r(childAt, i, iMax5, i2, 0, iArr);
                iMax = Math.max(iMax, m(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i6 = this.z + this.A;
        int i7 = this.x + this.y;
        if (t(this.e)) {
            r(this.e, i, iMax5 + i7, i2, i6, iArr);
            int iL3 = l(this.e) + this.e.getMeasuredWidth();
            iM = m(this.e) + this.e.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.e.getMeasuredState());
            iMax2 = iL3;
        } else {
            iM = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (t(this.k)) {
            iMax2 = Math.max(iMax2, r(this.k, i, iMax5 + i7, i2, iM + i6, iArr));
            iM += m(this.k) + this.k.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.k.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, iM);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (!this.U) {
            i4 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i8 = 0; i8 < childCount2; i8++) {
            View childAt2 = getChildAt(i8);
            if (t(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i4 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i4);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof gm2)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        gm2 gm2Var = (gm2) parcelable;
        super.onRestoreInstanceState(gm2Var.c);
        ActionMenuView actionMenuView = this.c;
        si1 si1Var = actionMenuView != null ? actionMenuView.x : null;
        int i = gm2Var.k;
        if (i != 0 && this.T != null && si1Var != null && (menuItemFindItem = si1Var.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (gm2Var.l) {
            rv rvVar = this.b0;
            removeCallbacks(rvVar);
            post(rvVar);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        d();
        r52 r52Var = this.B;
        boolean z = i == 1;
        if (z == r52Var.g) {
            return;
        }
        r52Var.g = z;
        if (!r52Var.h) {
            r52Var.a = r52Var.e;
            r52Var.b = r52Var.f;
            return;
        }
        if (z) {
            int i2 = r52Var.d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = r52Var.e;
            }
            r52Var.a = i2;
            int i3 = r52Var.c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = r52Var.f;
            }
            r52Var.b = i3;
            return;
        }
        int i4 = r52Var.c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = r52Var.e;
        }
        r52Var.a = i4;
        int i5 = r52Var.d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = r52Var.f;
        }
        r52Var.b = i5;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        g4 g4Var;
        xi1 xi1Var;
        gm2 gm2Var = new gm2(super.onSaveInstanceState());
        dm2 dm2Var = this.T;
        if (dm2Var != null && (xi1Var = dm2Var.e) != null) {
            gm2Var.k = xi1Var.a;
        }
        ActionMenuView actionMenuView = this.c;
        gm2Var.l = (actionMenuView == null || (g4Var = actionMenuView.B) == null || !g4Var.e()) ? false : true;
        return gm2Var;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.J = false;
        }
        if (!this.J) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.J = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.J = false;
        }
        return true;
    }

    public final int p(View view, int i, int i2, int[] iArr) {
        em2 em2Var = (em2) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) em2Var).leftMargin - iArr[0];
        int iMax = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iK, iMax + measuredWidth, view.getMeasuredHeight() + iK);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) em2Var).rightMargin + iMax;
    }

    public final int q(View view, int i, int i2, int[] iArr) {
        em2 em2Var = (em2) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) em2Var).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iK, iMax, view.getMeasuredHeight() + iK);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) em2Var).leftMargin);
    }

    public final int r(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void s(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if (this.a0 != z) {
            this.a0 = z;
            u();
        }
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(l13.f(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.U = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.D) {
            this.D = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.C) {
            this.C = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(l13.f(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(l13.f(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.l.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(fm2 fm2Var) {
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.c.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.s != i) {
            this.s = i;
            if (i == 0) {
                this.r = getContext();
            } else {
                this.r = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.A = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.y = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.x = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.z = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public final boolean t(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public final void u() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcherA = cm2.a(this);
            dm2 dm2Var = this.T;
            boolean z = false;
            int i = 1;
            if (((dm2Var == null || dm2Var.e == null) ? false : true) && onBackInvokedDispatcherA != null) {
                WeakHashMap weakHashMap = zt2.a;
                if (lt2.b(this) && this.a0) {
                    z = true;
                }
            }
            if (z && this.W == null) {
                if (this.V == null) {
                    this.V = cm2.b(new bm2(this, i));
                }
                cm2.c(onBackInvokedDispatcherA, this.V);
                this.W = onBackInvokedDispatcherA;
                return;
            }
            if (z || (onBackInvokedDispatcher = this.W) == null) {
                return;
            }
            cm2.d(onBackInvokedDispatcher, this.V);
            this.W = null;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, R.attr.toolbarStyle);
        this.E = 8388627;
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.N = new int[2];
        this.O = new kk0(new bm2(this, 0));
        this.P = new ArrayList();
        int i2 = 3;
        this.Q = new p01(i2, this);
        this.b0 = new rv(i2, this);
        Context context2 = getContext();
        int[] iArr = yz1.x;
        v4 v4VarJ = v4.J(context2, attributeSet, iArr, R.attr.toolbarStyle);
        zt2.j(this, context, iArr, attributeSet, (TypedArray) v4VarJ.k, R.attr.toolbarStyle);
        this.t = v4VarJ.y(28, 0);
        this.u = v4VarJ.y(19, 0);
        this.E = ((TypedArray) v4VarJ.k).getInteger(0, 8388627);
        this.v = ((TypedArray) v4VarJ.k).getInteger(2, 48);
        int iR = v4VarJ.r(22, 0);
        iR = v4VarJ.G(27) ? v4VarJ.r(27, iR) : iR;
        this.A = iR;
        this.z = iR;
        this.y = iR;
        this.x = iR;
        int iR2 = v4VarJ.r(25, -1);
        if (iR2 >= 0) {
            this.x = iR2;
        }
        int iR3 = v4VarJ.r(24, -1);
        if (iR3 >= 0) {
            this.y = iR3;
        }
        int iR4 = v4VarJ.r(26, -1);
        if (iR4 >= 0) {
            this.z = iR4;
        }
        int iR5 = v4VarJ.r(23, -1);
        if (iR5 >= 0) {
            this.A = iR5;
        }
        this.w = v4VarJ.s(13, -1);
        int iR6 = v4VarJ.r(9, Integer.MIN_VALUE);
        int iR7 = v4VarJ.r(5, Integer.MIN_VALUE);
        int iS = v4VarJ.s(7, 0);
        int iS2 = v4VarJ.s(8, 0);
        d();
        r52 r52Var = this.B;
        r52Var.h = false;
        if (iS != Integer.MIN_VALUE) {
            r52Var.e = iS;
            r52Var.a = iS;
        }
        if (iS2 != Integer.MIN_VALUE) {
            r52Var.f = iS2;
            r52Var.b = iS2;
        }
        if (iR6 != Integer.MIN_VALUE || iR7 != Integer.MIN_VALUE) {
            r52Var.a(iR6, iR7);
        }
        this.C = v4VarJ.r(10, Integer.MIN_VALUE);
        this.D = v4VarJ.r(6, Integer.MIN_VALUE);
        this.n = v4VarJ.t(4);
        this.o = v4VarJ.A(3);
        CharSequence charSequenceA = v4VarJ.A(21);
        if (!TextUtils.isEmpty(charSequenceA)) {
            setTitle(charSequenceA);
        }
        CharSequence charSequenceA2 = v4VarJ.A(18);
        if (!TextUtils.isEmpty(charSequenceA2)) {
            setSubtitle(charSequenceA2);
        }
        this.r = getContext();
        setPopupTheme(v4VarJ.y(17, 0));
        Drawable drawableT = v4VarJ.t(16);
        if (drawableT != null) {
            setNavigationIcon(drawableT);
        }
        CharSequence charSequenceA3 = v4VarJ.A(15);
        if (!TextUtils.isEmpty(charSequenceA3)) {
            setNavigationContentDescription(charSequenceA3);
        }
        Drawable drawableT2 = v4VarJ.t(11);
        if (drawableT2 != null) {
            setLogo(drawableT2);
        }
        CharSequence charSequenceA4 = v4VarJ.A(12);
        if (!TextUtils.isEmpty(charSequenceA4)) {
            setLogoDescription(charSequenceA4);
        }
        if (v4VarJ.G(29)) {
            setTitleTextColor(v4VarJ.q(29));
        }
        if (v4VarJ.G(20)) {
            setSubtitleTextColor(v4VarJ.q(20));
        }
        if (v4VarJ.G(14)) {
            getMenuInflater().inflate(v4VarJ.y(14, 0), getMenu());
        }
        v4VarJ.L();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        em2 em2Var = new em2(context, attributeSet);
        em2Var.a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yz1.b);
        em2Var.a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        em2Var.b = 0;
        return em2Var;
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        z8 z8Var = this.f11p;
        if (z8Var != null) {
            z8Var.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.f11p.setImageDrawable(drawable);
        } else {
            z8 z8Var = this.f11p;
            if (z8Var != null) {
                z8Var.setImageDrawable(this.n);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.m == null) {
                this.m = new b9(getContext(), null, 0);
            }
            if (!o(this.m)) {
                b(this.m, true);
            }
        } else {
            b9 b9Var = this.m;
            if (b9Var != null && o(b9Var)) {
                removeView(this.m);
                this.M.remove(this.m);
            }
        }
        b9 b9Var2 = this.m;
        if (b9Var2 != null) {
            b9Var2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.m == null) {
            this.m = new b9(getContext(), null, 0);
        }
        b9 b9Var = this.m;
        if (b9Var != null) {
            b9Var.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        z8 z8Var = this.l;
        if (z8Var != null) {
            z8Var.setContentDescription(charSequence);
            im2.a(this.l, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!o(this.l)) {
                b(this.l, true);
            }
        } else {
            z8 z8Var = this.l;
            if (z8Var != null && o(z8Var)) {
                removeView(this.l);
                this.M.remove(this.l);
            }
        }
        z8 z8Var2 = this.l;
        if (z8Var2 != null) {
            z8Var2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            fa faVar = this.k;
            if (faVar != null && o(faVar)) {
                removeView(this.k);
                this.M.remove(this.k);
            }
        } else {
            if (this.k == null) {
                Context context = getContext();
                fa faVar2 = new fa(context, null);
                this.k = faVar2;
                faVar2.setSingleLine();
                this.k.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.u;
                if (i != 0) {
                    this.k.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.I;
                if (colorStateList != null) {
                    this.k.setTextColor(colorStateList);
                }
            }
            if (!o(this.k)) {
                b(this.k, true);
            }
        }
        fa faVar3 = this.k;
        if (faVar3 != null) {
            faVar3.setText(charSequence);
        }
        this.G = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.I = colorStateList;
        fa faVar = this.k;
        if (faVar != null) {
            faVar.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            fa faVar = this.e;
            if (faVar != null && o(faVar)) {
                removeView(this.e);
                this.M.remove(this.e);
            }
        } else {
            if (this.e == null) {
                Context context = getContext();
                fa faVar2 = new fa(context, null);
                this.e = faVar2;
                faVar2.setSingleLine();
                this.e.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.t;
                if (i != 0) {
                    this.e.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.H;
                if (colorStateList != null) {
                    this.e.setTextColor(colorStateList);
                }
            }
            if (!o(this.e)) {
                b(this.e, true);
            }
        }
        fa faVar3 = this.e;
        if (faVar3 != null) {
            faVar3.setText(charSequence);
        }
        this.F = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.H = colorStateList;
        fa faVar = this.e;
        if (faVar != null) {
            faVar.setTextColor(colorStateList);
        }
    }
}
