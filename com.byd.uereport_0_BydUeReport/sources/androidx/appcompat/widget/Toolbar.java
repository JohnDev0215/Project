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
import java.util.ArrayList;
import java.util.List;
import l.a;
import o0.t;
import q.g;
import q.i;
import q.m;
import q.r;
import r.e0;
import r.f0;
import r.k0;
import r.p;
import r.x;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public CharSequence A;
    public CharSequence B;
    public ColorStateList C;
    public ColorStateList D;
    public boolean E;
    public boolean F;
    public final ArrayList<View> G;
    public final ArrayList<View> H;
    public final int[] I;
    public f J;
    public final ActionMenuView.e K;
    public f0 L;
    public ActionMenuPresenter M;
    public d N;
    public m.a O;
    public g.a P;
    public boolean Q;
    public final Runnable R;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionMenuView f761d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f762e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f763f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ImageButton f764g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView f765h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f766i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f767j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ImageButton f768k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f769l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Context f770m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f771n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f772o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f773p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f774q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f775r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f776s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f777t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f778u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f779v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public x f780w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f781x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f782y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f783z;

    public class a implements ActionMenuView.e {
        public a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.J;
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g f789d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public i f790e;

        public d() {
        }

        @Override // q.m
        public void a(g gVar, boolean z7) {
        }

        @Override // q.m
        public void b(Context context, g gVar) {
            i iVar;
            g gVar2 = this.f789d;
            if (gVar2 != null && (iVar = this.f790e) != null) {
                gVar2.f(iVar);
            }
            this.f789d = gVar;
        }

        @Override // q.m
        public void d(Parcelable parcelable) {
        }

        @Override // q.m
        public boolean f(r rVar) {
            return false;
        }

        @Override // q.m
        public void g(boolean z7) {
            if (this.f790e != null) {
                g gVar = this.f789d;
                boolean z8 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i8 = 0;
                    while (true) {
                        if (i8 >= size) {
                            break;
                        }
                        if (this.f789d.getItem(i8) == this.f790e) {
                            z8 = true;
                            break;
                        }
                        i8++;
                    }
                }
                if (z8) {
                    return;
                }
                l(this.f789d, this.f790e);
            }
        }

        @Override // q.m
        public int i() {
            return 0;
        }

        @Override // q.m
        public boolean j() {
            return false;
        }

        @Override // q.m
        public Parcelable k() {
            return null;
        }

        @Override // q.m
        public boolean l(g gVar, i iVar) {
            KeyEvent.Callback callback = Toolbar.this.f769l;
            if (callback instanceof p.c) {
                ((p.c) callback).f();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f769l);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f768k);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f769l = null;
            toolbar3.a();
            this.f790e = null;
            Toolbar.this.requestLayout();
            iVar.q(false);
            return true;
        }

        @Override // q.m
        public boolean m(g gVar, i iVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f768k.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f768k);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f768k);
            }
            Toolbar.this.f769l = iVar.getActionView();
            this.f790e = iVar;
            ViewParent parent2 = Toolbar.this.f769l.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f769l);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f5929a = 8388611 | (toolbar4.f774q & 112);
                eVarGenerateDefaultLayoutParams.f792b = 2;
                toolbar4.f769l.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.f769l);
            }
            Toolbar.this.G();
            Toolbar.this.requestLayout();
            iVar.q(true);
            KeyEvent.Callback callback = Toolbar.this.f769l;
            if (callback instanceof p.c) {
                ((p.c) callback).c();
            }
            return true;
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        return new p.g(getContext());
    }

    public boolean A() {
        ActionMenuView actionMenuView = this.f761d;
        return actionMenuView != null && actionMenuView.J();
    }

    public final int B(View view, int i8, int[] iArr, int i9) {
        e eVar = (e) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i8 + Math.max(0, i10);
        iArr[0] = Math.max(0, -i10);
        int iQ = q(view, i9);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    public final int C(View view, int i8, int[] iArr, int i9) {
        e eVar = (e) view.getLayoutParams();
        int i10 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i8 - Math.max(0, i10);
        iArr[1] = Math.max(0, -i10);
        int iQ = q(view, i9);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    public final int D(View view, int i8, int i9, int i10, int i11, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i12 = marginLayoutParams.leftMargin - iArr[0];
        int i13 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i12) + Math.max(0, i13);
        iArr[0] = Math.max(0, -i12);
        iArr[1] = Math.max(0, -i13);
        view.measure(ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + iMax + i9, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i11, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void E(View view, int i8, int i9, int i10, int i11, int i12) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i9, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i11, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i12 >= 0) {
            if (mode != 0) {
                i12 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i12);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i12, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final void F() {
        removeCallbacks(this.R);
        post(this.R);
    }

    public void G() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f792b != 2 && childAt != this.f761d) {
                removeViewAt(childCount);
                this.H.add(childAt);
            }
        }
    }

    public void H(int i8, int i9) {
        h();
        this.f780w.g(i8, i9);
    }

    public void I(g gVar, ActionMenuPresenter actionMenuPresenter) {
        if (gVar == null && this.f761d == null) {
            return;
        }
        k();
        g gVarN = this.f761d.N();
        if (gVarN == gVar) {
            return;
        }
        if (gVarN != null) {
            gVarN.Q(this.M);
            gVarN.Q(this.N);
        }
        if (this.N == null) {
            this.N = new d();
        }
        actionMenuPresenter.K(true);
        if (gVar != null) {
            gVar.c(actionMenuPresenter, this.f770m);
            gVar.c(this.N, this.f770m);
        } else {
            actionMenuPresenter.b(this.f770m, null);
            this.N.b(this.f770m, null);
            actionMenuPresenter.g(true);
            this.N.g(true);
        }
        this.f761d.setPopupTheme(this.f771n);
        this.f761d.setPresenter(actionMenuPresenter);
        this.M = actionMenuPresenter;
    }

    public void J(m.a aVar, g.a aVar2) {
        this.O = aVar;
        this.P = aVar2;
        ActionMenuView actionMenuView = this.f761d;
        if (actionMenuView != null) {
            actionMenuView.O(aVar, aVar2);
        }
    }

    public void K(Context context, int i8) {
        this.f773p = i8;
        TextView textView = this.f763f;
        if (textView != null) {
            textView.setTextAppearance(context, i8);
        }
    }

    public void L(Context context, int i8) {
        this.f772o = i8;
        TextView textView = this.f762e;
        if (textView != null) {
            textView.setTextAppearance(context, i8);
        }
    }

    public final boolean M() {
        if (!this.Q) {
            return false;
        }
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
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
        ActionMenuView actionMenuView = this.f761d;
        return actionMenuView != null && actionMenuView.P();
    }

    public void a() {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            addView(this.H.get(size));
        }
        this.H.clear();
    }

    public final void b(List<View> list, int i8) {
        boolean z7 = t.A(this) == 1;
        int childCount = getChildCount();
        int iB = o0.d.b(i8, t.A(this));
        list.clear();
        if (!z7) {
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f792b == 0 && N(childAt) && p(eVar.f5929a) == iB) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i10 = childCount - 1; i10 >= 0; i10--) {
            View childAt2 = getChildAt(i10);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f792b == 0 && N(childAt2) && p(eVar2.f5929a) == iB) {
                list.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f792b = 1;
        if (!z7 || this.f769l == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.H.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f761d) != null && actionMenuView.K();
    }

    public void e() {
        d dVar = this.N;
        i iVar = dVar == null ? null : dVar.f790e;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f761d;
        if (actionMenuView != null) {
            actionMenuView.B();
        }
    }

    public void g() {
        if (this.f768k == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.f768k = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.f766i);
            this.f768k.setContentDescription(this.f767j);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f5929a = 8388611 | (this.f774q & 112);
            eVarGenerateDefaultLayoutParams.f792b = 2;
            this.f768k.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f768k.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.f768k;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.f768k;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        x xVar = this.f780w;
        if (xVar != null) {
            return xVar.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i8 = this.f782y;
        return i8 != Integer.MIN_VALUE ? i8 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        x xVar = this.f780w;
        if (xVar != null) {
            return xVar.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        x xVar = this.f780w;
        if (xVar != null) {
            return xVar.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        x xVar = this.f780w;
        if (xVar != null) {
            return xVar.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i8 = this.f781x;
        return i8 != Integer.MIN_VALUE ? i8 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        g gVarN;
        ActionMenuView actionMenuView = this.f761d;
        return actionMenuView != null && (gVarN = actionMenuView.N()) != null && gVarN.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.f782y, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return t.A(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return t.A(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f781x, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f765h;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f765h;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f761d.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f764g;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f764g;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.M;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f761d.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f770m;
    }

    public int getPopupTheme() {
        return this.f771n;
    }

    public CharSequence getSubtitle() {
        return this.B;
    }

    public final TextView getSubtitleTextView() {
        return this.f763f;
    }

    public CharSequence getTitle() {
        return this.A;
    }

    public int getTitleMarginBottom() {
        return this.f779v;
    }

    public int getTitleMarginEnd() {
        return this.f777t;
    }

    public int getTitleMarginStart() {
        return this.f776s;
    }

    public int getTitleMarginTop() {
        return this.f778u;
    }

    public final TextView getTitleTextView() {
        return this.f762e;
    }

    public p getWrapper() {
        if (this.L == null) {
            this.L = new f0(this, true);
        }
        return this.L;
    }

    public final void h() {
        if (this.f780w == null) {
            this.f780w = new x();
        }
    }

    public final void i() {
        if (this.f765h == null) {
            this.f765h = new AppCompatImageView(getContext());
        }
    }

    public final void j() {
        k();
        if (this.f761d.N() == null) {
            g gVar = (g) this.f761d.getMenu();
            if (this.N == null) {
                this.N = new d();
            }
            this.f761d.setExpandedActionViewsExclusive(true);
            gVar.c(this.N, this.f770m);
        }
    }

    public final void k() {
        if (this.f761d == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f761d = actionMenuView;
            actionMenuView.setPopupTheme(this.f771n);
            this.f761d.setOnMenuItemClickListener(this.K);
            this.f761d.O(this.O, this.P);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f5929a = 8388613 | (this.f774q & 112);
            this.f761d.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f761d, false);
        }
    }

    public final void l() {
        if (this.f764g == null) {
            this.f764g = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f5929a = 8388611 | (this.f774q & 112);
            this.f764g.setLayoutParams(eVarGenerateDefaultLayoutParams);
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
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof a.C0076a ? new e((a.C0076a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.R);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.F = false;
        }
        if (!this.F) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.F = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.F = false;
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
    public void onMeasure(int i8, int i9) {
        char c8;
        char c9;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.I;
        if (k0.b(this)) {
            c9 = 1;
            c8 = 0;
        } else {
            c8 = 1;
            c9 = 0;
        }
        if (N(this.f764g)) {
            E(this.f764g, i8, 0, i9, 0, this.f775r);
            measuredWidth = this.f764g.getMeasuredWidth() + s(this.f764g);
            iMax = Math.max(0, this.f764g.getMeasuredHeight() + t(this.f764g));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f764g.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (N(this.f768k)) {
            E(this.f768k, i8, 0, i9, 0, this.f775r);
            measuredWidth = this.f768k.getMeasuredWidth() + s(this.f768k);
            iMax = Math.max(iMax, this.f768k.getMeasuredHeight() + t(this.f768k));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f768k.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c9] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (N(this.f761d)) {
            E(this.f761d, i8, iMax3, i9, 0, this.f775r);
            measuredWidth2 = this.f761d.getMeasuredWidth() + s(this.f761d);
            iMax = Math.max(iMax, this.f761d.getMeasuredHeight() + t(this.f761d));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f761d.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c8] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (N(this.f769l)) {
            iMax4 += D(this.f769l, i8, iMax4, i9, 0, iArr);
            iMax = Math.max(iMax, this.f769l.getMeasuredHeight() + t(this.f769l));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f769l.getMeasuredState());
        }
        if (N(this.f765h)) {
            iMax4 += D(this.f765h, i8, iMax4, i9, 0, iArr);
            iMax = Math.max(iMax, this.f765h.getMeasuredHeight() + t(this.f765h));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f765h.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (((e) childAt.getLayoutParams()).f792b == 0 && N(childAt)) {
                iMax4 += D(childAt, i8, iMax4, i9, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i11 = this.f778u + this.f779v;
        int i12 = this.f776s + this.f777t;
        if (N(this.f762e)) {
            D(this.f762e, i8, iMax4 + i12, i9, i11, iArr);
            int measuredWidth3 = this.f762e.getMeasuredWidth() + s(this.f762e);
            measuredHeight = this.f762e.getMeasuredHeight() + t(this.f762e);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f762e.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (N(this.f763f)) {
            iMax2 = Math.max(iMax2, D(this.f763f, i8, iMax4 + i12, i9, measuredHeight + i11, iArr));
            measuredHeight += this.f763f.getMeasuredHeight() + t(this.f763f);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f763f.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i8, (-16777216) & iCombineMeasuredStates2), M() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i9, iCombineMeasuredStates2 << 16));
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
        ActionMenuView actionMenuView = this.f761d;
        g gVarN = actionMenuView != null ? actionMenuView.N() : null;
        int i8 = savedState.f784f;
        if (i8 != 0 && this.N != null && gVarN != null && (menuItemFindItem = gVarN.findItem(i8)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f785g) {
            F();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i8) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i8);
        }
        h();
        this.f780w.f(i8 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.N;
        if (dVar != null && (iVar = dVar.f790e) != null) {
            savedState.f784f = iVar.getItemId();
        }
        savedState.f785g = A();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.E = false;
        }
        if (!this.E) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.E = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.E = false;
        }
        return true;
    }

    public final int p(int i8) {
        int iA = t.A(this);
        int iB = o0.d.b(i8, iA) & 7;
        return (iB == 1 || iB == 3 || iB == 5) ? iB : iA == 1 ? 5 : 3;
    }

    public final int q(View view, int i8) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i9 = i8 > 0 ? (measuredHeight - i8) / 2 : 0;
        int iR = r(eVar.f5929a);
        if (iR == 48) {
            return getPaddingTop() - i9;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i9;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i10 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i10) {
            iMax = i10;
        } else {
            int i11 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i12 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i11 < i12) {
                iMax = Math.max(0, iMax - (i12 - i11));
            }
        }
        return paddingTop + iMax;
    }

    public final int r(int i8) {
        int i9 = i8 & 112;
        return (i9 == 16 || i9 == 48 || i9 == 80) ? i9 : this.f783z & 112;
    }

    public final int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return o0.g.b(marginLayoutParams) + o0.g.a(marginLayoutParams);
    }

    public void setCollapseContentDescription(int i8) {
        setCollapseContentDescription(i8 != 0 ? getContext().getText(i8) : null);
    }

    public void setCollapseIcon(int i8) {
        setCollapseIcon(m.a.d(getContext(), i8));
    }

    public void setCollapsible(boolean z7) {
        this.Q = z7;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i8) {
        if (i8 < 0) {
            i8 = Integer.MIN_VALUE;
        }
        if (i8 != this.f782y) {
            this.f782y = i8;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i8) {
        if (i8 < 0) {
            i8 = Integer.MIN_VALUE;
        }
        if (i8 != this.f781x) {
            this.f781x = i8;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i8) {
        setLogo(m.a.d(getContext(), i8));
    }

    public void setLogoDescription(int i8) {
        setLogoDescription(getContext().getText(i8));
    }

    public void setNavigationContentDescription(int i8) {
        setNavigationContentDescription(i8 != 0 ? getContext().getText(i8) : null);
    }

    public void setNavigationIcon(int i8) {
        setNavigationIcon(m.a.d(getContext(), i8));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f764g.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.J = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f761d.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i8) {
        if (this.f771n != i8) {
            this.f771n = i8;
            if (i8 == 0) {
                this.f770m = getContext();
            } else {
                this.f770m = new ContextThemeWrapper(getContext(), i8);
            }
        }
    }

    public void setSubtitle(int i8) {
        setSubtitle(getContext().getText(i8));
    }

    public void setSubtitleTextColor(int i8) {
        setSubtitleTextColor(ColorStateList.valueOf(i8));
    }

    public void setTitle(int i8) {
        setTitle(getContext().getText(i8));
    }

    public void setTitleMarginBottom(int i8) {
        this.f779v = i8;
        requestLayout();
    }

    public void setTitleMarginEnd(int i8) {
        this.f777t = i8;
        requestLayout();
    }

    public void setTitleMarginStart(int i8) {
        this.f776s = i8;
        requestLayout();
    }

    public void setTitleMarginTop(int i8) {
        this.f778u = i8;
        requestLayout();
    }

    public void setTitleTextColor(int i8) {
        setTitleTextColor(ColorStateList.valueOf(i8));
    }

    public final int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final int u(List<View> list, int[] iArr) {
        int i8 = iArr[0];
        int i9 = iArr[1];
        int size = list.size();
        int i10 = 0;
        int measuredWidth = 0;
        while (i10 < size) {
            View view = list.get(i10);
            e eVar = (e) view.getLayoutParams();
            int i11 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i8;
            int i12 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i9;
            int iMax = Math.max(0, i11);
            int iMax2 = Math.max(0, i12);
            int iMax3 = Math.max(0, -i11);
            int iMax4 = Math.max(0, -i12);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i10++;
            i9 = iMax4;
            i8 = iMax3;
        }
        return measuredWidth;
    }

    public boolean v() {
        d dVar = this.N;
        return (dVar == null || dVar.f790e == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f761d;
        return actionMenuView != null && actionMenuView.H();
    }

    public void x(int i8) {
        getMenuInflater().inflate(i8, getMenu());
    }

    public final boolean y(View view) {
        return view.getParent() == this || this.H.contains(view);
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f761d;
        return actionMenuView != null && actionMenuView.I();
    }

    public static class e extends a.C0076a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f792b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f792b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i8, int i9) {
            super(i8, i9);
            this.f792b = 0;
            this.f5929a = 8388627;
        }

        public e(e eVar) {
            super((a.C0076a) eVar);
            this.f792b = 0;
            this.f792b = eVar.f792b;
        }

        public e(a.C0076a c0076a) {
            super(c0076a);
            this.f792b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f792b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f792b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f768k;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.f768k.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.f768k;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f766i);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!y(this.f765h)) {
                c(this.f765h, true);
            }
        } else {
            ImageView imageView = this.f765h;
            if (imageView != null && y(imageView)) {
                removeView(this.f765h);
                this.H.remove(this.f765h);
            }
        }
        ImageView imageView2 = this.f765h;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f765h;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f764g;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!y(this.f764g)) {
                c(this.f764g, true);
            }
        } else {
            ImageButton imageButton = this.f764g;
            if (imageButton != null && y(imageButton)) {
                removeView(this.f764g);
                this.H.remove(this.f764g);
            }
        }
        ImageButton imageButton2 = this.f764g;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f763f;
            if (textView != null && y(textView)) {
                removeView(this.f763f);
                this.H.remove(this.f763f);
            }
        } else {
            if (this.f763f == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f763f = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f763f.setEllipsize(TextUtils.TruncateAt.END);
                int i8 = this.f773p;
                if (i8 != 0) {
                    this.f763f.setTextAppearance(context, i8);
                }
                ColorStateList colorStateList = this.D;
                if (colorStateList != null) {
                    this.f763f.setTextColor(colorStateList);
                }
            }
            if (!y(this.f763f)) {
                c(this.f763f, true);
            }
        }
        TextView textView2 = this.f763f;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.B = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.D = colorStateList;
        TextView textView = this.f763f;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f762e;
            if (textView != null && y(textView)) {
                removeView(this.f762e);
                this.H.remove(this.f762e);
            }
        } else {
            if (this.f762e == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f762e = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f762e.setEllipsize(TextUtils.TruncateAt.END);
                int i8 = this.f772o;
                if (i8 != 0) {
                    this.f762e.setTextAppearance(context, i8);
                }
                ColorStateList colorStateList = this.C;
                if (colorStateList != null) {
                    this.f762e.setTextColor(colorStateList);
                }
            }
            if (!y(this.f762e)) {
                c(this.f762e, true);
            }
        }
        TextView textView2 = this.f762e;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.A = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.C = colorStateList;
        TextView textView = this.f762e;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f784f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f785g;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f784f = parcel.readInt();
            this.f785g = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f784f);
            parcel.writeInt(this.f785g ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f783z = 8388627;
        this.G = new ArrayList<>();
        this.H = new ArrayList<>();
        this.I = new int[2];
        this.K = new a();
        this.R = new b();
        Context context2 = getContext();
        int[] iArr = R$styleable.Toolbar;
        e0 e0VarV = e0.v(context2, attributeSet, iArr, i8, 0);
        t.j0(this, context, iArr, attributeSet, e0VarV.r(), i8, 0);
        this.f772o = e0VarV.n(R$styleable.Toolbar_titleTextAppearance, 0);
        this.f773p = e0VarV.n(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.f783z = e0VarV.l(R$styleable.Toolbar_android_gravity, this.f783z);
        this.f774q = e0VarV.l(R$styleable.Toolbar_buttonGravity, 48);
        int iE = e0VarV.e(R$styleable.Toolbar_titleMargin, 0);
        int i9 = R$styleable.Toolbar_titleMargins;
        iE = e0VarV.s(i9) ? e0VarV.e(i9, iE) : iE;
        this.f779v = iE;
        this.f778u = iE;
        this.f777t = iE;
        this.f776s = iE;
        int iE2 = e0VarV.e(R$styleable.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.f776s = iE2;
        }
        int iE3 = e0VarV.e(R$styleable.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.f777t = iE3;
        }
        int iE4 = e0VarV.e(R$styleable.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.f778u = iE4;
        }
        int iE5 = e0VarV.e(R$styleable.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.f779v = iE5;
        }
        this.f775r = e0VarV.f(R$styleable.Toolbar_maxButtonHeight, -1);
        int iE6 = e0VarV.e(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = e0VarV.e(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = e0VarV.f(R$styleable.Toolbar_contentInsetLeft, 0);
        int iF2 = e0VarV.f(R$styleable.Toolbar_contentInsetRight, 0);
        h();
        this.f780w.e(iF, iF2);
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.f780w.g(iE6, iE7);
        }
        this.f781x = e0VarV.e(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.f782y = e0VarV.e(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f766i = e0VarV.g(R$styleable.Toolbar_collapseIcon);
        this.f767j = e0VarV.p(R$styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceP = e0VarV.p(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceP)) {
            setTitle(charSequenceP);
        }
        CharSequence charSequenceP2 = e0VarV.p(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceP2)) {
            setSubtitle(charSequenceP2);
        }
        this.f770m = getContext();
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
        int i10 = R$styleable.Toolbar_titleTextColor;
        if (e0VarV.s(i10)) {
            setTitleTextColor(e0VarV.c(i10));
        }
        int i11 = R$styleable.Toolbar_subtitleTextColor;
        if (e0VarV.s(i11)) {
            setSubtitleTextColor(e0VarV.c(i11));
        }
        int i12 = R$styleable.Toolbar_menu;
        if (e0VarV.s(i12)) {
            x(e0VarV.n(i12, 0));
        }
        e0VarV.w();
    }
}
