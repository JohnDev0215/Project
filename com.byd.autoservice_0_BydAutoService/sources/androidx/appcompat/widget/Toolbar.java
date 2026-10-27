package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import i.a;
import java.util.ArrayList;
import java.util.List;
import l0.t;
import n.g;
import n.i;
import n.m;
import n.r;
import o.e0;
import o.f0;
import o.k0;
import o.p;
import o.x;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public ColorStateList B;
    public boolean C;
    public boolean D;
    public final ArrayList<View> E;
    public final ArrayList<View> F;
    public final int[] G;
    public f H;
    public final ActionMenuView.e I;
    public f0 J;
    public ActionMenuPresenter K;
    public d L;
    public m.a M;
    public g.a N;
    public boolean O;
    public final Runnable P;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ActionMenuView f664b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f665c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageButton f667e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageView f668f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f669g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f670h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageButton f671i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f672j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Context f673k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f674l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f676n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f677o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f678p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f679q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f680r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f681s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f682t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public x f683u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f684v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f685w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f686x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f687y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f688z;

    public class a implements ActionMenuView.e {
        public a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.H;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.O();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.e();
        }
    }

    public class d implements m {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f694b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public i f695c;

        public d() {
        }

        @Override // n.m
        public void a(g gVar, boolean z3) {
        }

        @Override // n.m
        public int c() {
            return 0;
        }

        @Override // n.m
        public boolean d() {
            return false;
        }

        @Override // n.m
        public Parcelable e() {
            return null;
        }

        @Override // n.m
        public void f(Context context, g gVar) {
            i iVar;
            g gVar2 = this.f694b;
            if (gVar2 != null && (iVar = this.f695c) != null) {
                gVar2.f(iVar);
            }
            this.f694b = gVar;
        }

        @Override // n.m
        public void h(Parcelable parcelable) {
        }

        @Override // n.m
        public boolean i(g gVar, i iVar) {
            KeyEvent.Callback callback = Toolbar.this.f672j;
            if (callback instanceof m.c) {
                ((m.c) callback).f();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f672j);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f671i);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f672j = null;
            toolbar3.a();
            this.f695c = null;
            Toolbar.this.requestLayout();
            iVar.q(false);
            return true;
        }

        @Override // n.m
        public boolean j(g gVar, i iVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f671i.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f671i);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f671i);
            }
            Toolbar.this.f672j = iVar.getActionView();
            this.f695c = iVar;
            ViewParent parent2 = Toolbar.this.f672j.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f672j);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f3135a = 8388611 | (toolbar4.f677o & 112);
                eVarGenerateDefaultLayoutParams.f697b = 2;
                toolbar4.f672j.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.f672j);
            }
            Toolbar.this.G();
            Toolbar.this.requestLayout();
            iVar.q(true);
            KeyEvent.Callback callback = Toolbar.this.f672j;
            if (callback instanceof m.c) {
                ((m.c) callback).c();
            }
            return true;
        }

        @Override // n.m
        public boolean m(r rVar) {
            return false;
        }

        @Override // n.m
        public void n(boolean z3) {
            if (this.f695c != null) {
                g gVar = this.f694b;
                boolean z4 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        }
                        if (this.f694b.getItem(i3) == this.f695c) {
                            z4 = true;
                            break;
                        }
                        i3++;
                    }
                }
                if (z4) {
                    return;
                }
                i(this.f694b, this.f695c);
            }
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        return new m.g(getContext());
    }

    public boolean A() {
        ActionMenuView actionMenuView = this.f664b;
        return actionMenuView != null && actionMenuView.J();
    }

    public final int B(View view, int i3, int[] iArr, int i4) {
        e eVar = (e) view.getLayoutParams();
        int i5 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i3 + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        int iQ = q(view, i4);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    public final int C(View view, int i3, int[] iArr, int i4) {
        e eVar = (e) view.getLayoutParams();
        int i5 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i3 - Math.max(0, i5);
        iArr[1] = Math.max(0, -i5);
        int iQ = q(view, i4);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    public final int D(View view, int i3, int i4, int i5, int i6, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i7 = marginLayoutParams.leftMargin - iArr[0];
        int i8 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i7) + Math.max(0, i8);
        iArr[0] = Math.max(0, -i7);
        iArr[1] = Math.max(0, -i8);
        view.measure(ViewGroup.getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight() + iMax + i4, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i5, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i6, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void E(View view, int i3, int i4, int i5, int i6, int i7) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i4, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i5, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i6, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i7 >= 0) {
            if (mode != 0) {
                i7 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i7);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final void F() {
        removeCallbacks(this.P);
        post(this.P);
    }

    public void G() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f697b != 2 && childAt != this.f664b) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    public void H(int i3, int i4) {
        h();
        this.f683u.g(i3, i4);
    }

    public void I(g gVar, ActionMenuPresenter actionMenuPresenter) {
        if (gVar == null && this.f664b == null) {
            return;
        }
        k();
        g gVarN = this.f664b.N();
        if (gVarN == gVar) {
            return;
        }
        if (gVarN != null) {
            gVarN.Q(this.K);
            gVarN.Q(this.L);
        }
        if (this.L == null) {
            this.L = new d();
        }
        actionMenuPresenter.K(true);
        if (gVar != null) {
            gVar.c(actionMenuPresenter, this.f673k);
            gVar.c(this.L, this.f673k);
        } else {
            actionMenuPresenter.f(this.f673k, null);
            this.L.f(this.f673k, null);
            actionMenuPresenter.n(true);
            this.L.n(true);
        }
        this.f664b.setPopupTheme(this.f674l);
        this.f664b.setPresenter(actionMenuPresenter);
        this.K = actionMenuPresenter;
    }

    public void J(m.a aVar, g.a aVar2) {
        this.M = aVar;
        this.N = aVar2;
        ActionMenuView actionMenuView = this.f664b;
        if (actionMenuView != null) {
            actionMenuView.O(aVar, aVar2);
        }
    }

    public void K(Context context, int i3) {
        this.f676n = i3;
        TextView textView = this.f666d;
        if (textView != null) {
            textView.setTextAppearance(context, i3);
        }
    }

    public void L(Context context, int i3) {
        this.f675m = i3;
        TextView textView = this.f665c;
        if (textView != null) {
            textView.setTextAppearance(context, i3);
        }
    }

    public final boolean M() {
        if (!this.O) {
            return false;
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (N(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean N(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public boolean O() {
        ActionMenuView actionMenuView = this.f664b;
        return actionMenuView != null && actionMenuView.P();
    }

    public void a() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView(this.F.get(size));
        }
        this.F.clear();
    }

    public final void b(List<View> list, int i3) {
        boolean z3 = t.z(this) == 1;
        int childCount = getChildCount();
        int iB = l0.d.b(i3, t.z(this));
        list.clear();
        if (!z3) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f697b == 0 && N(childAt) && p(eVar.f3135a) == iB) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i5 = childCount - 1; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f697b == 0 && N(childAt2) && p(eVar2.f3135a) == iB) {
                list.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f697b = 1;
        if (!z3 || this.f672j == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.F.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f664b) != null && actionMenuView.K();
    }

    public void e() {
        d dVar = this.L;
        i iVar = dVar == null ? null : dVar.f695c;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f664b;
        if (actionMenuView != null) {
            actionMenuView.B();
        }
    }

    public void g() {
        if (this.f671i == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.f671i = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.f669g);
            this.f671i.setContentDescription(this.f670h);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f3135a = 8388611 | (this.f677o & 112);
            eVarGenerateDefaultLayoutParams.f697b = 2;
            this.f671i.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f671i.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.f671i;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.f671i;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        x xVar = this.f683u;
        if (xVar != null) {
            return xVar.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i3 = this.f685w;
        return i3 != Integer.MIN_VALUE ? i3 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        x xVar = this.f683u;
        if (xVar != null) {
            return xVar.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        x xVar = this.f683u;
        if (xVar != null) {
            return xVar.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        x xVar = this.f683u;
        if (xVar != null) {
            return xVar.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i3 = this.f684v;
        return i3 != Integer.MIN_VALUE ? i3 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        g gVarN;
        ActionMenuView actionMenuView = this.f664b;
        return actionMenuView != null && (gVarN = actionMenuView.N()) != null && gVarN.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.f685w, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return t.z(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return t.z(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f684v, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f668f;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f668f;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f664b.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f667e;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f667e;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.K;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f664b.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f673k;
    }

    public int getPopupTheme() {
        return this.f674l;
    }

    public CharSequence getSubtitle() {
        return this.f688z;
    }

    public final TextView getSubtitleTextView() {
        return this.f666d;
    }

    public CharSequence getTitle() {
        return this.f687y;
    }

    public int getTitleMarginBottom() {
        return this.f682t;
    }

    public int getTitleMarginEnd() {
        return this.f680r;
    }

    public int getTitleMarginStart() {
        return this.f679q;
    }

    public int getTitleMarginTop() {
        return this.f681s;
    }

    public final TextView getTitleTextView() {
        return this.f665c;
    }

    public p getWrapper() {
        if (this.J == null) {
            this.J = new f0(this, true);
        }
        return this.J;
    }

    public final void h() {
        if (this.f683u == null) {
            this.f683u = new x();
        }
    }

    public final void i() {
        if (this.f668f == null) {
            this.f668f = new AppCompatImageView(getContext());
        }
    }

    public final void j() {
        k();
        if (this.f664b.N() == null) {
            g gVar = (g) this.f664b.getMenu();
            if (this.L == null) {
                this.L = new d();
            }
            this.f664b.setExpandedActionViewsExclusive(true);
            gVar.c(this.L, this.f673k);
        }
    }

    public final void k() {
        if (this.f664b == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f664b = actionMenuView;
            actionMenuView.setPopupTheme(this.f674l);
            this.f664b.setOnMenuItemClickListener(this.I);
            this.f664b.O(this.M, this.N);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f3135a = 8388613 | (this.f677o & 112);
            this.f664b.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f664b, false);
        }
    }

    public final void l() {
        if (this.f667e == null) {
            this.f667e = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f3135a = 8388611 | (this.f677o & 112);
            this.f667e.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

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

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof a.C0017a ? new e((a.C0017a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.P);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
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
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 783
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        char c3;
        char c4;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.G;
        if (k0.b(this)) {
            c4 = 1;
            c3 = 0;
        } else {
            c3 = 1;
            c4 = 0;
        }
        if (N(this.f667e)) {
            E(this.f667e, i3, 0, i4, 0, this.f678p);
            measuredWidth = this.f667e.getMeasuredWidth() + s(this.f667e);
            iMax = Math.max(0, this.f667e.getMeasuredHeight() + t(this.f667e));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f667e.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (N(this.f671i)) {
            E(this.f671i, i3, 0, i4, 0, this.f678p);
            measuredWidth = this.f671i.getMeasuredWidth() + s(this.f671i);
            iMax = Math.max(iMax, this.f671i.getMeasuredHeight() + t(this.f671i));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f671i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c4] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (N(this.f664b)) {
            E(this.f664b, i3, iMax3, i4, 0, this.f678p);
            measuredWidth2 = this.f664b.getMeasuredWidth() + s(this.f664b);
            iMax = Math.max(iMax, this.f664b.getMeasuredHeight() + t(this.f664b));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f664b.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c3] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (N(this.f672j)) {
            iMax4 += D(this.f672j, i3, iMax4, i4, 0, iArr);
            iMax = Math.max(iMax, this.f672j.getMeasuredHeight() + t(this.f672j));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f672j.getMeasuredState());
        }
        if (N(this.f668f)) {
            iMax4 += D(this.f668f, i3, iMax4, i4, 0, iArr);
            iMax = Math.max(iMax, this.f668f.getMeasuredHeight() + t(this.f668f));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f668f.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (((e) childAt.getLayoutParams()).f697b == 0 && N(childAt)) {
                iMax4 += D(childAt, i3, iMax4, i4, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i6 = this.f681s + this.f682t;
        int i7 = this.f679q + this.f680r;
        if (N(this.f665c)) {
            D(this.f665c, i3, iMax4 + i7, i4, i6, iArr);
            int measuredWidth3 = this.f665c.getMeasuredWidth() + s(this.f665c);
            measuredHeight = this.f665c.getMeasuredHeight() + t(this.f665c);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f665c.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (N(this.f666d)) {
            iMax2 = Math.max(iMax2, D(this.f666d, i3, iMax4 + i7, i4, measuredHeight + i6, iArr));
            measuredHeight += this.f666d.getMeasuredHeight() + t(this.f666d);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f666d.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i3, (-16777216) & iCombineMeasuredStates2), M() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i4, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        ActionMenuView actionMenuView = this.f664b;
        g gVarN = actionMenuView != null ? actionMenuView.N() : null;
        int i3 = savedState.f689d;
        if (i3 != 0 && this.L != null && gVarN != null && (menuItemFindItem = gVarN.findItem(i3)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f690e) {
            F();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i3) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i3);
        }
        h();
        this.f683u.f(i3 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.L;
        if (dVar != null && (iVar = dVar.f695c) != null) {
            savedState.f689d = iVar.getItemId();
        }
        savedState.f690e = A();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public final int p(int i3) {
        int iZ = t.z(this);
        int iB = l0.d.b(i3, iZ) & 7;
        return (iB == 1 || iB == 3 || iB == 5) ? iB : iZ == 1 ? 5 : 3;
    }

    public final int q(View view, int i3) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i3 > 0 ? (measuredHeight - i3) / 2 : 0;
        int iR = r(eVar.f3135a);
        if (iR == 48) {
            return getPaddingTop() - i4;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i4;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i5) {
            iMax = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i6 < i7) {
                iMax = Math.max(0, iMax - (i7 - i6));
            }
        }
        return paddingTop + iMax;
    }

    public final int r(int i3) {
        int i4 = i3 & 112;
        return (i4 == 16 || i4 == 48 || i4 == 80) ? i4 : this.f686x & 112;
    }

    public final int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return l0.g.b(marginLayoutParams) + l0.g.a(marginLayoutParams);
    }

    public void setCollapseContentDescription(int i3) {
        setCollapseContentDescription(i3 != 0 ? getContext().getText(i3) : null);
    }

    public void setCollapseIcon(int i3) {
        setCollapseIcon(j.a.d(getContext(), i3));
    }

    public void setCollapsible(boolean z3) {
        this.O = z3;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i3) {
        if (i3 < 0) {
            i3 = Integer.MIN_VALUE;
        }
        if (i3 != this.f685w) {
            this.f685w = i3;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i3) {
        if (i3 < 0) {
            i3 = Integer.MIN_VALUE;
        }
        if (i3 != this.f684v) {
            this.f684v = i3;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i3) {
        setLogo(j.a.d(getContext(), i3));
    }

    public void setLogoDescription(int i3) {
        setLogoDescription(getContext().getText(i3));
    }

    public void setNavigationContentDescription(int i3) {
        setNavigationContentDescription(i3 != 0 ? getContext().getText(i3) : null);
    }

    public void setNavigationIcon(int i3) {
        setNavigationIcon(j.a.d(getContext(), i3));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f667e.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.H = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f664b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i3) {
        if (this.f674l != i3) {
            this.f674l = i3;
            if (i3 == 0) {
                this.f673k = getContext();
            } else {
                this.f673k = new ContextThemeWrapper(getContext(), i3);
            }
        }
    }

    public void setSubtitle(int i3) {
        setSubtitle(getContext().getText(i3));
    }

    public void setSubtitleTextColor(int i3) {
        setSubtitleTextColor(ColorStateList.valueOf(i3));
    }

    public void setTitle(int i3) {
        setTitle(getContext().getText(i3));
    }

    public void setTitleMarginBottom(int i3) {
        this.f682t = i3;
        requestLayout();
    }

    public void setTitleMarginEnd(int i3) {
        this.f680r = i3;
        requestLayout();
    }

    public void setTitleMarginStart(int i3) {
        this.f679q = i3;
        requestLayout();
    }

    public void setTitleMarginTop(int i3) {
        this.f681s = i3;
        requestLayout();
    }

    public void setTitleTextColor(int i3) {
        setTitleTextColor(ColorStateList.valueOf(i3));
    }

    public final int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final int u(List<View> list, int[] iArr) {
        int i3 = iArr[0];
        int i4 = iArr[1];
        int size = list.size();
        int i5 = 0;
        int measuredWidth = 0;
        while (i5 < size) {
            View view = list.get(i5);
            e eVar = (e) view.getLayoutParams();
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i3;
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i4;
            int iMax = Math.max(0, i6);
            int iMax2 = Math.max(0, i7);
            int iMax3 = Math.max(0, -i6);
            int iMax4 = Math.max(0, -i7);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i5++;
            i4 = iMax4;
            i3 = iMax3;
        }
        return measuredWidth;
    }

    public boolean v() {
        d dVar = this.L;
        return (dVar == null || dVar.f695c == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f664b;
        return actionMenuView != null && actionMenuView.H();
    }

    public void x(int i3) {
        getMenuInflater().inflate(i3, getMenu());
    }

    public final boolean y(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f664b;
        return actionMenuView != null && actionMenuView.I();
    }

    public static class e extends a.C0017a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f697b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f697b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i3, int i4) {
            super(i3, i4);
            this.f697b = 0;
            this.f3135a = 8388627;
        }

        public e(e eVar) {
            super((a.C0017a) eVar);
            this.f697b = 0;
            this.f697b = eVar.f697b;
        }

        public e(a.C0017a c0017a) {
            super(c0017a);
            this.f697b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f697b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f697b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f671i;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.f671i.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.f671i;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f669g);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!y(this.f668f)) {
                c(this.f668f, true);
            }
        } else {
            ImageView imageView = this.f668f;
            if (imageView != null && y(imageView)) {
                removeView(this.f668f);
                this.F.remove(this.f668f);
            }
        }
        ImageView imageView2 = this.f668f;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f668f;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f667e;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!y(this.f667e)) {
                c(this.f667e, true);
            }
        } else {
            ImageButton imageButton = this.f667e;
            if (imageButton != null && y(imageButton)) {
                removeView(this.f667e);
                this.F.remove(this.f667e);
            }
        }
        ImageButton imageButton2 = this.f667e;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f666d;
            if (textView != null && y(textView)) {
                removeView(this.f666d);
                this.F.remove(this.f666d);
            }
        } else {
            if (this.f666d == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f666d = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f666d.setEllipsize(TextUtils.TruncateAt.END);
                int i3 = this.f676n;
                if (i3 != 0) {
                    this.f666d.setTextAppearance(context, i3);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.f666d.setTextColor(colorStateList);
                }
            }
            if (!y(this.f666d)) {
                c(this.f666d, true);
            }
        }
        TextView textView2 = this.f666d;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f688z = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.B = colorStateList;
        TextView textView = this.f666d;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f665c;
            if (textView != null && y(textView)) {
                removeView(this.f665c);
                this.F.remove(this.f665c);
            }
        } else {
            if (this.f665c == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f665c = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f665c.setEllipsize(TextUtils.TruncateAt.END);
                int i3 = this.f675m;
                if (i3 != 0) {
                    this.f665c.setTextAppearance(context, i3);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f665c.setTextColor(colorStateList);
                }
            }
            if (!y(this.f665c)) {
                c(this.f665c, true);
            }
        }
        TextView textView2 = this.f665c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f687y = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f665c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f689d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f690e;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f689d = parcel.readInt();
            this.f690e = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f689d);
            parcel.writeInt(this.f690e ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f686x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.I = new a();
        this.P = new b();
        Context context2 = getContext();
        int[] iArr = R$styleable.Toolbar;
        e0 e0VarV = e0.v(context2, attributeSet, iArr, i3, 0);
        t.h0(this, context, iArr, attributeSet, e0VarV.r(), i3, 0);
        this.f675m = e0VarV.n(R$styleable.Toolbar_titleTextAppearance, 0);
        this.f676n = e0VarV.n(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.f686x = e0VarV.l(R$styleable.Toolbar_android_gravity, this.f686x);
        this.f677o = e0VarV.l(R$styleable.Toolbar_buttonGravity, 48);
        int iE = e0VarV.e(R$styleable.Toolbar_titleMargin, 0);
        int i4 = R$styleable.Toolbar_titleMargins;
        iE = e0VarV.s(i4) ? e0VarV.e(i4, iE) : iE;
        this.f682t = iE;
        this.f681s = iE;
        this.f680r = iE;
        this.f679q = iE;
        int iE2 = e0VarV.e(R$styleable.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.f679q = iE2;
        }
        int iE3 = e0VarV.e(R$styleable.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.f680r = iE3;
        }
        int iE4 = e0VarV.e(R$styleable.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.f681s = iE4;
        }
        int iE5 = e0VarV.e(R$styleable.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.f682t = iE5;
        }
        this.f678p = e0VarV.f(R$styleable.Toolbar_maxButtonHeight, -1);
        int iE6 = e0VarV.e(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = e0VarV.e(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = e0VarV.f(R$styleable.Toolbar_contentInsetLeft, 0);
        int iF2 = e0VarV.f(R$styleable.Toolbar_contentInsetRight, 0);
        h();
        this.f683u.e(iF, iF2);
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.f683u.g(iE6, iE7);
        }
        this.f684v = e0VarV.e(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.f685w = e0VarV.e(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f669g = e0VarV.g(R$styleable.Toolbar_collapseIcon);
        this.f670h = e0VarV.p(R$styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceP = e0VarV.p(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceP)) {
            setTitle(charSequenceP);
        }
        CharSequence charSequenceP2 = e0VarV.p(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceP2)) {
            setSubtitle(charSequenceP2);
        }
        this.f673k = getContext();
        setPopupTheme(e0VarV.n(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableG = e0VarV.g(R$styleable.Toolbar_navigationIcon);
        if (drawableG != null) {
            setNavigationIcon(drawableG);
        }
        CharSequence charSequenceP3 = e0VarV.p(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceP3)) {
            setNavigationContentDescription(charSequenceP3);
        }
        Drawable drawableG2 = e0VarV.g(R$styleable.Toolbar_logo);
        if (drawableG2 != null) {
            setLogo(drawableG2);
        }
        CharSequence charSequenceP4 = e0VarV.p(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceP4)) {
            setLogoDescription(charSequenceP4);
        }
        int i5 = R$styleable.Toolbar_titleTextColor;
        if (e0VarV.s(i5)) {
            setTitleTextColor(e0VarV.c(i5));
        }
        int i6 = R$styleable.Toolbar_subtitleTextColor;
        if (e0VarV.s(i6)) {
            setSubtitleTextColor(e0VarV.c(i6));
        }
        int i7 = R$styleable.Toolbar_menu;
        if (e0VarV.s(i7)) {
            x(e0VarV.n(i7, 0));
        }
        e0VarV.w();
    }
}
