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
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import b.b.j;
import b.b.k.a;
import b.b.p.j.g;
import b.b.p.j.i;
import b.b.p.j.m;
import b.b.p.j.r;
import b.b.q.e0;
import b.b.q.f0;
import b.b.q.k0;
import b.b.q.p;
import b.b.q.x;
import b.h.m.t;
import java.util.ArrayList;
import java.util.List;

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
    public ActionMenuView f541b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f542c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f543d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageButton f544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageView f545f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f546g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f547h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageButton f548i;
    public View j;
    public Context k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public x u;
    public int v;
    public int w;
    public int x;
    public CharSequence y;
    public CharSequence z;

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
        public g f554b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public i f555c;

        public d() {
        }

        @Override // b.b.p.j.m
        public void a(g gVar, boolean z) {
        }

        @Override // b.b.p.j.m
        public int c() {
            return 0;
        }

        @Override // b.b.p.j.m
        public boolean d() {
            return false;
        }

        @Override // b.b.p.j.m
        public Parcelable e() {
            return null;
        }

        @Override // b.b.p.j.m
        public void f(Context context, g gVar) {
            i iVar;
            g gVar2 = this.f554b;
            if (gVar2 != null && (iVar = this.f555c) != null) {
                gVar2.f(iVar);
            }
            this.f554b = gVar;
        }

        @Override // b.b.p.j.m
        public void h(Parcelable parcelable) {
        }

        @Override // b.b.p.j.m
        public boolean i(g gVar, i iVar) {
            KeyEvent.Callback callback = Toolbar.this.j;
            if (callback instanceof b.b.p.c) {
                ((b.b.p.c) callback).f();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.j);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f548i);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.j = null;
            toolbar3.a();
            this.f555c = null;
            Toolbar.this.requestLayout();
            iVar.q(false);
            return true;
        }

        @Override // b.b.p.j.m
        public boolean j(g gVar, i iVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.f548i.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f548i);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f548i);
            }
            Toolbar.this.j = iVar.getActionView();
            this.f555c = iVar;
            ViewParent parent2 = Toolbar.this.j.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.j);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f1426a = 8388611 | (toolbar4.o & 112);
                eVarGenerateDefaultLayoutParams.f557b = 2;
                toolbar4.j.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.j);
            }
            Toolbar.this.G();
            Toolbar.this.requestLayout();
            iVar.q(true);
            KeyEvent.Callback callback = Toolbar.this.j;
            if (callback instanceof b.b.p.c) {
                ((b.b.p.c) callback).c();
            }
            return true;
        }

        @Override // b.b.p.j.m
        public boolean m(r rVar) {
            return false;
        }

        @Override // b.b.p.j.m
        public void n(boolean z) {
            if (this.f555c != null) {
                g gVar = this.f554b;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        if (this.f554b.getItem(i2) == this.f555c) {
                            z2 = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (z2) {
                    return;
                }
                i(this.f554b, this.f555c);
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
        return new b.b.p.g(getContext());
    }

    public boolean A() {
        ActionMenuView actionMenuView = this.f541b;
        return actionMenuView != null && actionMenuView.J();
    }

    public final int B(View view, int i2, int[] iArr, int i3) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i2 + Math.max(0, i4);
        iArr[0] = Math.max(0, -i4);
        int iQ = q(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    public final int C(View view, int i2, int[] iArr, int i3) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int iQ = q(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    public final int D(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + iMax + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void E(View view, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i6, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
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
            if (((e) childAt.getLayoutParams()).f557b != 2 && childAt != this.f541b) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    public void H(int i2, int i3) {
        h();
        this.u.g(i2, i3);
    }

    public void I(g gVar, ActionMenuPresenter actionMenuPresenter) {
        if (gVar == null && this.f541b == null) {
            return;
        }
        k();
        g gVarN = this.f541b.N();
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
            gVar.c(actionMenuPresenter, this.k);
            gVar.c(this.L, this.k);
        } else {
            actionMenuPresenter.f(this.k, null);
            this.L.f(this.k, null);
            actionMenuPresenter.n(true);
            this.L.n(true);
        }
        this.f541b.setPopupTheme(this.l);
        this.f541b.setPresenter(actionMenuPresenter);
        this.K = actionMenuPresenter;
    }

    public void J(m.a aVar, g.a aVar2) {
        this.M = aVar;
        this.N = aVar2;
        ActionMenuView actionMenuView = this.f541b;
        if (actionMenuView != null) {
            actionMenuView.O(aVar, aVar2);
        }
    }

    public void K(Context context, int i2) {
        this.n = i2;
        TextView textView = this.f543d;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void L(Context context, int i2) {
        this.m = i2;
        TextView textView = this.f542c;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public final boolean M() {
        if (!this.O) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
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
        ActionMenuView actionMenuView = this.f541b;
        return actionMenuView != null && actionMenuView.P();
    }

    public void a() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView(this.F.get(size));
        }
        this.F.clear();
    }

    public final void b(List<View> list, int i2) {
        boolean z = t.z(this) == 1;
        int childCount = getChildCount();
        int iB = b.h.m.d.b(i2, t.z(this));
        list.clear();
        if (!z) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f557b == 0 && N(childAt) && p(eVar.f1426a) == iB) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f557b == 0 && N(childAt2) && p(eVar2.f1426a) == iB) {
                list.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f557b = 1;
        if (!z || this.j == null) {
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
        return getVisibility() == 0 && (actionMenuView = this.f541b) != null && actionMenuView.K();
    }

    public void e() {
        d dVar = this.L;
        i iVar = dVar == null ? null : dVar.f555c;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f541b;
        if (actionMenuView != null) {
            actionMenuView.B();
        }
    }

    public void g() {
        if (this.f548i == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, b.b.a.toolbarNavigationButtonStyle);
            this.f548i = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.f546g);
            this.f548i.setContentDescription(this.f547h);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f1426a = 8388611 | (this.o & 112);
            eVarGenerateDefaultLayoutParams.f557b = 2;
            this.f548i.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.f548i.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.f548i;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.f548i;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        x xVar = this.u;
        if (xVar != null) {
            return xVar.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.w;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        x xVar = this.u;
        if (xVar != null) {
            return xVar.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        x xVar = this.u;
        if (xVar != null) {
            return xVar.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        x xVar = this.u;
        if (xVar != null) {
            return xVar.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.v;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        g gVarN;
        ActionMenuView actionMenuView = this.f541b;
        return actionMenuView != null && (gVarN = actionMenuView.N()) != null && gVarN.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.w, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return t.z(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return t.z(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.v, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f545f;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f545f;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f541b.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f544e;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f544e;
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
        return this.f541b.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.k;
    }

    public int getPopupTheme() {
        return this.l;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public final TextView getSubtitleTextView() {
        return this.f543d;
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public int getTitleMarginStart() {
        return this.q;
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public final TextView getTitleTextView() {
        return this.f542c;
    }

    public p getWrapper() {
        if (this.J == null) {
            this.J = new f0(this, true);
        }
        return this.J;
    }

    public final void h() {
        if (this.u == null) {
            this.u = new x();
        }
    }

    public final void i() {
        if (this.f545f == null) {
            this.f545f = new AppCompatImageView(getContext());
        }
    }

    public final void j() {
        k();
        if (this.f541b.N() == null) {
            g gVar = (g) this.f541b.getMenu();
            if (this.L == null) {
                this.L = new d();
            }
            this.f541b.setExpandedActionViewsExclusive(true);
            gVar.c(this.L, this.k);
        }
    }

    public final void k() {
        if (this.f541b == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f541b = actionMenuView;
            actionMenuView.setPopupTheme(this.l);
            this.f541b.setOnMenuItemClickListener(this.I);
            this.f541b.O(this.M, this.N);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f1426a = 8388613 | (this.o & 112);
            this.f541b.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f541b, false);
        }
    }

    public final void l() {
        if (this.f544e == null) {
            this.f544e = new AppCompatImageButton(getContext(), null, b.b.a.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f1426a = 8388611 | (this.o & 112);
            this.f544e.setLayoutParams(eVarGenerateDefaultLayoutParams);
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
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof a.C0014a ? new e((a.C0014a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
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
    public void onMeasure(int i2, int i3) {
        char c2;
        char c3;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.G;
        if (k0.b(this)) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (N(this.f544e)) {
            E(this.f544e, i2, 0, i3, 0, this.p);
            measuredWidth = this.f544e.getMeasuredWidth() + s(this.f544e);
            iMax = Math.max(0, this.f544e.getMeasuredHeight() + t(this.f544e));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f544e.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (N(this.f548i)) {
            E(this.f548i, i2, 0, i3, 0, this.p);
            measuredWidth = this.f548i.getMeasuredWidth() + s(this.f548i);
            iMax = Math.max(iMax, this.f548i.getMeasuredHeight() + t(this.f548i));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f548i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c3] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (N(this.f541b)) {
            E(this.f541b, i2, iMax3, i3, 0, this.p);
            measuredWidth2 = this.f541b.getMeasuredWidth() + s(this.f541b);
            iMax = Math.max(iMax, this.f541b.getMeasuredHeight() + t(this.f541b));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f541b.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c2] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (N(this.j)) {
            iMax4 += D(this.j, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.j.getMeasuredHeight() + t(this.j));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.j.getMeasuredState());
        }
        if (N(this.f545f)) {
            iMax4 += D(this.f545f, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.f545f.getMeasuredHeight() + t(this.f545f));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f545f.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (((e) childAt.getLayoutParams()).f557b == 0 && N(childAt)) {
                iMax4 += D(childAt, i2, iMax4, i3, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i5 = this.s + this.t;
        int i6 = this.q + this.r;
        if (N(this.f542c)) {
            D(this.f542c, i2, iMax4 + i6, i3, i5, iArr);
            int measuredWidth3 = this.f542c.getMeasuredWidth() + s(this.f542c);
            measuredHeight = this.f542c.getMeasuredHeight() + t(this.f542c);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f542c.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (N(this.f543d)) {
            iMax2 = Math.max(iMax2, D(this.f543d, i2, iMax4 + i6, i3, measuredHeight + i5, iArr));
            measuredHeight += this.f543d.getMeasuredHeight() + t(this.f543d);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f543d.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, (-16777216) & iCombineMeasuredStates2), M() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.j());
        ActionMenuView actionMenuView = this.f541b;
        g gVarN = actionMenuView != null ? actionMenuView.N() : null;
        int i2 = savedState.f549d;
        if (i2 != 0 && this.L != null && gVarN != null && (menuItemFindItem = gVarN.findItem(i2)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f550e) {
            F();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i2);
        }
        h();
        this.u.f(i2 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.L;
        if (dVar != null && (iVar = dVar.f555c) != null) {
            savedState.f549d = iVar.getItemId();
        }
        savedState.f550e = A();
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

    public final int p(int i2) {
        int iZ = t.z(this);
        int iB = b.h.m.d.b(i2, iZ) & 7;
        return (iB == 1 || iB == 3 || iB == 5) ? iB : iZ == 1 ? 5 : 3;
    }

    public final int q(View view, int i2) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int iR = r(eVar.f1426a);
        if (iR == 48) {
            return getPaddingTop() - i3;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public final int r(int i2) {
        int i3 = i2 & 112;
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.x & 112;
    }

    public final int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return b.h.m.g.b(marginLayoutParams) + b.h.m.g.a(marginLayoutParams);
    }

    public void setCollapseContentDescription(int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(int i2) {
        setCollapseIcon(b.b.l.a.a.d(getContext(), i2));
    }

    public void setCollapsible(boolean z) {
        this.O = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.w) {
            this.w = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.v) {
            this.v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i2) {
        setLogo(b.b.l.a.a.d(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(b.b.l.a.a.d(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        l();
        this.f544e.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.H = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f541b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.l != i2) {
            this.l = i2;
            if (i2 == 0) {
                this.k = getContext();
            } else {
                this.k = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(int i2) {
        setSubtitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.t = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.r = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.q = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.s = i2;
        requestLayout();
    }

    public void setTitleTextColor(int i2) {
        setTitleTextColor(ColorStateList.valueOf(i2));
    }

    public final int t(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final int u(List<View> list, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int size = list.size();
        int i4 = 0;
        int measuredWidth = 0;
        while (i4 < size) {
            View view = list.get(i4);
            e eVar = (e) view.getLayoutParams();
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i2;
            int i6 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i3;
            int iMax = Math.max(0, i5);
            int iMax2 = Math.max(0, i6);
            int iMax3 = Math.max(0, -i5);
            int iMax4 = Math.max(0, -i6);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i4++;
            i3 = iMax4;
            i2 = iMax3;
        }
        return measuredWidth;
    }

    public boolean v() {
        d dVar = this.L;
        return (dVar == null || dVar.f555c == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f541b;
        return actionMenuView != null && actionMenuView.H();
    }

    public void x(int i2) {
        getMenuInflater().inflate(i2, getMenu());
    }

    public final boolean y(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f541b;
        return actionMenuView != null && actionMenuView.I();
    }

    public static class e extends a.C0014a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f557b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f557b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i2, int i3) {
            super(i2, i3);
            this.f557b = 0;
            this.f1426a = 8388627;
        }

        public e(e eVar) {
            super((a.C0014a) eVar);
            this.f557b = 0;
            this.f557b = eVar.f557b;
        }

        public e(a.C0014a c0014a) {
            super(c0014a);
            this.f557b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f557b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f557b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.toolbarStyle);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f548i;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.f548i.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.f548i;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f546g);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!y(this.f545f)) {
                c(this.f545f, true);
            }
        } else {
            ImageView imageView = this.f545f;
            if (imageView != null && y(imageView)) {
                removeView(this.f545f);
                this.F.remove(this.f545f);
            }
        }
        ImageView imageView2 = this.f545f;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.f545f;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f544e;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!y(this.f544e)) {
                c(this.f544e, true);
            }
        } else {
            ImageButton imageButton = this.f544e;
            if (imageButton != null && y(imageButton)) {
                removeView(this.f544e);
                this.F.remove(this.f544e);
            }
        }
        ImageButton imageButton2 = this.f544e;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f543d;
            if (textView != null && y(textView)) {
                removeView(this.f543d);
                this.F.remove(this.f543d);
            }
        } else {
            if (this.f543d == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f543d = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f543d.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.n;
                if (i2 != 0) {
                    this.f543d.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.f543d.setTextColor(colorStateList);
                }
            }
            if (!y(this.f543d)) {
                c(this.f543d, true);
            }
        }
        TextView textView2 = this.f543d;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.B = colorStateList;
        TextView textView = this.f543d;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f542c;
            if (textView != null && y(textView)) {
                removeView(this.f542c);
                this.F.remove(this.f542c);
            }
        } else {
            if (this.f542c == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f542c = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f542c.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.m;
                if (i2 != 0) {
                    this.f542c.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f542c.setTextColor(colorStateList);
                }
            }
            if (!y(this.f542c)) {
                c(this.f542c, true);
            }
        }
        TextView textView2 = this.f542c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f542c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f549d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f550e;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f549d = parcel.readInt();
            this.f550e = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f549d);
            parcel.writeInt(this.f550e ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.I = new a();
        this.P = new b();
        Context context2 = getContext();
        int[] iArr = j.Toolbar;
        e0 e0VarV = e0.v(context2, attributeSet, iArr, i2, 0);
        t.i0(this, context, iArr, attributeSet, e0VarV.r(), i2, 0);
        this.m = e0VarV.n(j.Toolbar_titleTextAppearance, 0);
        this.n = e0VarV.n(j.Toolbar_subtitleTextAppearance, 0);
        this.x = e0VarV.l(j.Toolbar_android_gravity, this.x);
        this.o = e0VarV.l(j.Toolbar_buttonGravity, 48);
        int iE = e0VarV.e(j.Toolbar_titleMargin, 0);
        int i3 = j.Toolbar_titleMargins;
        iE = e0VarV.s(i3) ? e0VarV.e(i3, iE) : iE;
        this.t = iE;
        this.s = iE;
        this.r = iE;
        this.q = iE;
        int iE2 = e0VarV.e(j.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.q = iE2;
        }
        int iE3 = e0VarV.e(j.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.r = iE3;
        }
        int iE4 = e0VarV.e(j.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.s = iE4;
        }
        int iE5 = e0VarV.e(j.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.t = iE5;
        }
        this.p = e0VarV.f(j.Toolbar_maxButtonHeight, -1);
        int iE6 = e0VarV.e(j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = e0VarV.e(j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = e0VarV.f(j.Toolbar_contentInsetLeft, 0);
        int iF2 = e0VarV.f(j.Toolbar_contentInsetRight, 0);
        h();
        this.u.e(iF, iF2);
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.u.g(iE6, iE7);
        }
        this.v = e0VarV.e(j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.w = e0VarV.e(j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f546g = e0VarV.g(j.Toolbar_collapseIcon);
        this.f547h = e0VarV.p(j.Toolbar_collapseContentDescription);
        CharSequence charSequenceP = e0VarV.p(j.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceP)) {
            setTitle(charSequenceP);
        }
        CharSequence charSequenceP2 = e0VarV.p(j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceP2)) {
            setSubtitle(charSequenceP2);
        }
        this.k = getContext();
        setPopupTheme(e0VarV.n(j.Toolbar_popupTheme, 0));
        Drawable drawableG = e0VarV.g(j.Toolbar_navigationIcon);
        if (drawableG != null) {
            setNavigationIcon(drawableG);
        }
        CharSequence charSequenceP3 = e0VarV.p(j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceP3)) {
            setNavigationContentDescription(charSequenceP3);
        }
        Drawable drawableG2 = e0VarV.g(j.Toolbar_logo);
        if (drawableG2 != null) {
            setLogo(drawableG2);
        }
        CharSequence charSequenceP4 = e0VarV.p(j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceP4)) {
            setLogoDescription(charSequenceP4);
        }
        int i4 = j.Toolbar_titleTextColor;
        if (e0VarV.s(i4)) {
            setTitleTextColor(e0VarV.c(i4));
        }
        int i5 = j.Toolbar_subtitleTextColor;
        if (e0VarV.s(i5)) {
            setSubtitleTextColor(e0VarV.c(i5));
        }
        int i6 = j.Toolbar_menu;
        if (e0VarV.s(i6)) {
            x(e0VarV.n(i6, 0));
        }
        e0VarV.w();
    }
}
