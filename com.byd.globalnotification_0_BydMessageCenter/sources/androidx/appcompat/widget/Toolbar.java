package androidx.appcompat.widget;

import a.b.j;
import a.b.k.e;
import a.b.o.i.i;
import a.b.o.i.m;
import a.b.o.i.r;
import a.b.p.b1;
import a.b.p.d0;
import a.b.p.l;
import a.b.p.n;
import a.b.p.p0;
import a.b.p.x0;
import a.b.p.z;
import a.b.p.z0;
import a.h.k.q;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
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
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public boolean B;
    public boolean C;
    public final ArrayList<View> D;
    public final ArrayList<View> E;
    public final int[] F;
    public f G;
    public final ActionMenuView.e H;
    public z0 I;
    public a.b.p.c J;
    public d K;
    public boolean L;
    public final Runnable M;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ActionMenuView f1466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1467b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1468c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageButton f1469d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f1470e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1471f;
    public CharSequence g;
    public ImageButton h;
    public View i;
    public Context j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public p0 t;
    public int u;
    public int v;
    public int w;
    public CharSequence x;
    public CharSequence y;
    public ColorStateList z;

    public class a implements ActionMenuView.e {
        public a() {
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.v();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = Toolbar.this.K;
            i iVar = dVar == null ? null : dVar.f1476b;
            if (iVar != null) {
                iVar.collapseActionView();
            }
        }
    }

    public class d implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.b.o.i.g f1475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public i f1476b;

        public d() {
        }

        @Override // a.b.o.i.m
        public void b(a.b.o.i.g gVar, boolean z) {
        }

        @Override // a.b.o.i.m
        public boolean c(a.b.o.i.g gVar, i iVar) {
            Toolbar.this.c();
            ViewParent parent = Toolbar.this.h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.h);
            }
            Toolbar.this.i = iVar.getActionView();
            this.f1476b = iVar;
            ViewParent parent2 = Toolbar.this.i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.i);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f10a = 8388611 | (toolbar4.n & 112);
                eVarGenerateDefaultLayoutParams.f1478b = 2;
                toolbar4.i.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.i);
            }
            Toolbar toolbar6 = Toolbar.this;
            int childCount = toolbar6.getChildCount();
            while (true) {
                childCount--;
                if (childCount < 0) {
                    break;
                }
                View childAt = toolbar6.getChildAt(childCount);
                if (((e) childAt.getLayoutParams()).f1478b != 2 && childAt != toolbar6.f1466a) {
                    toolbar6.removeViewAt(childCount);
                    toolbar6.E.add(childAt);
                }
            }
            Toolbar.this.requestLayout();
            iVar.C = true;
            iVar.n.q(false);
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof a.b.o.b) {
                ((a.b.o.b) callback).a();
            }
            return true;
        }

        @Override // a.b.o.i.m
        public void d(m.a aVar) {
        }

        @Override // a.b.o.i.m
        public boolean f(r rVar) {
            return false;
        }

        @Override // a.b.o.i.m
        public boolean g() {
            return false;
        }

        @Override // a.b.o.i.m
        public void h(boolean z) {
            if (this.f1476b != null) {
                a.b.o.i.g gVar = this.f1475a;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.f1475a.getItem(i) == this.f1476b) {
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z2) {
                    return;
                }
                k(this.f1475a, this.f1476b);
            }
        }

        @Override // a.b.o.i.m
        public void j(Context context, a.b.o.i.g gVar) {
            i iVar;
            a.b.o.i.g gVar2 = this.f1475a;
            if (gVar2 != null && (iVar = this.f1476b) != null) {
                gVar2.d(iVar);
            }
            this.f1475a = gVar;
        }

        @Override // a.b.o.i.m
        public boolean k(a.b.o.i.g gVar, i iVar) {
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof a.b.o.b) {
                ((a.b.o.b) callback).f();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.i = null;
            int size = toolbar3.E.size();
            while (true) {
                size--;
                if (size < 0) {
                    toolbar3.E.clear();
                    this.f1476b = null;
                    Toolbar.this.requestLayout();
                    iVar.C = false;
                    iVar.n.q(false);
                    return true;
                }
                toolbar3.addView(toolbar3.E.get(size));
            }
        }
    }

    public static class e extends e.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1478b;

        public e(int i, int i2) {
            super(i, i2);
            this.f1478b = 0;
            this.f10a = 8388627;
        }

        public e(e.a aVar) {
            super(aVar);
            this.f1478b = 0;
        }

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1478b = 0;
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1478b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1478b = 0;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(e eVar) {
            super((e.a) eVar);
            this.f1478b = 0;
            this.f1478b = eVar.f1478b;
        }
    }

    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public static class g extends a.j.a.a {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1479c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1480d;

        public class a implements Parcelable.ClassLoaderCreator<g> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new g(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new g[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1479c = parcel.readInt();
            this.f1480d = parcel.readInt() != 0;
        }

        public g(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeInt(this.f1479c);
            parcel.writeInt(this.f1480d ? 1 : 0);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.a.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = 8388627;
        this.D = new ArrayList<>();
        this.E = new ArrayList<>();
        this.F = new int[2];
        this.H = new a();
        this.M = new b();
        x0 x0VarQ = x0.q(getContext(), attributeSet, j.Toolbar, i, 0);
        q.T(this, context, j.Toolbar, attributeSet, x0VarQ.f390b, i, 0);
        this.l = x0VarQ.l(j.Toolbar_titleTextAppearance, 0);
        this.m = x0VarQ.l(j.Toolbar_subtitleTextAppearance, 0);
        this.w = x0VarQ.f390b.getInteger(j.Toolbar_android_gravity, this.w);
        this.n = x0VarQ.f390b.getInteger(j.Toolbar_buttonGravity, 48);
        int iE = x0VarQ.e(j.Toolbar_titleMargin, 0);
        iE = x0VarQ.o(j.Toolbar_titleMargins) ? x0VarQ.e(j.Toolbar_titleMargins, iE) : iE;
        this.s = iE;
        this.r = iE;
        this.q = iE;
        this.p = iE;
        int iE2 = x0VarQ.e(j.Toolbar_titleMarginStart, -1);
        if (iE2 >= 0) {
            this.p = iE2;
        }
        int iE3 = x0VarQ.e(j.Toolbar_titleMarginEnd, -1);
        if (iE3 >= 0) {
            this.q = iE3;
        }
        int iE4 = x0VarQ.e(j.Toolbar_titleMarginTop, -1);
        if (iE4 >= 0) {
            this.r = iE4;
        }
        int iE5 = x0VarQ.e(j.Toolbar_titleMarginBottom, -1);
        if (iE5 >= 0) {
            this.s = iE5;
        }
        this.o = x0VarQ.f(j.Toolbar_maxButtonHeight, -1);
        int iE6 = x0VarQ.e(j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iE7 = x0VarQ.e(j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iF = x0VarQ.f(j.Toolbar_contentInsetLeft, 0);
        int iF2 = x0VarQ.f(j.Toolbar_contentInsetRight, 0);
        d();
        p0 p0Var = this.t;
        p0Var.h = false;
        if (iF != Integer.MIN_VALUE) {
            p0Var.f333e = iF;
            p0Var.f329a = iF;
        }
        if (iF2 != Integer.MIN_VALUE) {
            p0Var.f334f = iF2;
            p0Var.f330b = iF2;
        }
        if (iE6 != Integer.MIN_VALUE || iE7 != Integer.MIN_VALUE) {
            this.t.a(iE6, iE7);
        }
        this.u = x0VarQ.e(j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.v = x0VarQ.e(j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f1471f = x0VarQ.g(j.Toolbar_collapseIcon);
        this.g = x0VarQ.n(j.Toolbar_collapseContentDescription);
        CharSequence charSequenceN = x0VarQ.n(j.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceN)) {
            setTitle(charSequenceN);
        }
        CharSequence charSequenceN2 = x0VarQ.n(j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceN2)) {
            setSubtitle(charSequenceN2);
        }
        this.j = getContext();
        setPopupTheme(x0VarQ.l(j.Toolbar_popupTheme, 0));
        Drawable drawableG = x0VarQ.g(j.Toolbar_navigationIcon);
        if (drawableG != null) {
            setNavigationIcon(drawableG);
        }
        CharSequence charSequenceN3 = x0VarQ.n(j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceN3)) {
            setNavigationContentDescription(charSequenceN3);
        }
        Drawable drawableG2 = x0VarQ.g(j.Toolbar_logo);
        if (drawableG2 != null) {
            setLogo(drawableG2);
        }
        CharSequence charSequenceN4 = x0VarQ.n(j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceN4)) {
            setLogoDescription(charSequenceN4);
        }
        if (x0VarQ.o(j.Toolbar_titleTextColor)) {
            setTitleTextColor(x0VarQ.c(j.Toolbar_titleTextColor));
        }
        if (x0VarQ.o(j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(x0VarQ.c(j.Toolbar_subtitleTextColor));
        }
        if (x0VarQ.o(j.Toolbar_menu)) {
            getMenuInflater().inflate(x0VarQ.l(j.Toolbar_menu, 0), getMenu());
        }
        x0VarQ.f390b.recycle();
    }

    private MenuInflater getMenuInflater() {
        return new a.b.o.f(getContext());
    }

    public final void a(List<View> list, int i) {
        boolean z = q.p(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f1478b == 0 && u(childAt) && j(eVar.f10a) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f1478b == 0 && u(childAt2) && j(eVar2.f10a) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f1478b = 1;
        if (!z || this.i == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.E.add(view);
        }
    }

    public void c() {
        if (this.h == null) {
            l lVar = new l(getContext(), null, a.b.a.toolbarNavigationButtonStyle);
            this.h = lVar;
            lVar.setImageDrawable(this.f1471f);
            this.h.setContentDescription(this.g);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f10a = 8388611 | (this.n & 112);
            eVarGenerateDefaultLayoutParams.f1478b = 2;
            this.h.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.h.setOnClickListener(new c());
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public final void d() {
        if (this.t == null) {
            this.t = new p0();
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.f1466a;
        if (actionMenuView.p == null) {
            a.b.o.i.g gVar = (a.b.o.i.g) actionMenuView.getMenu();
            if (this.K == null) {
                this.K = new d();
            }
            this.f1466a.setExpandedActionViewsExclusive(true);
            gVar.b(this.K, this.j);
        }
    }

    public final void f() {
        if (this.f1466a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.f1466a = actionMenuView;
            actionMenuView.setPopupTheme(this.k);
            this.f1466a.setOnMenuItemClickListener(this.H);
            ActionMenuView actionMenuView2 = this.f1466a;
            actionMenuView2.u = null;
            actionMenuView2.v = null;
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f10a = 8388613 | (this.n & 112);
            this.f1466a.setLayoutParams(eVarGenerateDefaultLayoutParams);
            b(this.f1466a, false);
        }
    }

    public final void g() {
        if (this.f1469d == null) {
            this.f1469d = new l(getContext(), null, a.b.a.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f10a = 8388611 | (this.n & 112);
            this.f1469d.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        p0 p0Var = this.t;
        if (p0Var != null) {
            return p0Var.g ? p0Var.f329a : p0Var.f330b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        p0 p0Var = this.t;
        if (p0Var != null) {
            return p0Var.f329a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        p0 p0Var = this.t;
        if (p0Var != null) {
            return p0Var.f330b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        p0 p0Var = this.t;
        if (p0Var != null) {
            return p0Var.g ? p0Var.f330b : p0Var.f329a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.u;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        a.b.o.i.g gVar;
        ActionMenuView actionMenuView = this.f1466a;
        return actionMenuView != null && (gVar = actionMenuView.p) != null && gVar.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.v, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return q.p(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return q.p(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f1470e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f1470e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.f1466a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f1469d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f1469d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public a.b.p.c getOuterActionMenuPresenter() {
        return this.J;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.f1466a.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.j;
    }

    public int getPopupTheme() {
        return this.k;
    }

    public CharSequence getSubtitle() {
        return this.y;
    }

    public final TextView getSubtitleTextView() {
        return this.f1468c;
    }

    public CharSequence getTitle() {
        return this.x;
    }

    public int getTitleMarginBottom() {
        return this.s;
    }

    public int getTitleMarginEnd() {
        return this.q;
    }

    public int getTitleMarginStart() {
        return this.p;
    }

    public int getTitleMarginTop() {
        return this.r;
    }

    public final TextView getTitleTextView() {
        return this.f1467b;
    }

    public d0 getWrapper() {
        if (this.I == null) {
            this.I = new z0(this, true);
        }
        return this.I;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof e.a ? new e((e.a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public final int j(int i) {
        int iP = q.p(this);
        int absoluteGravity = Gravity.getAbsoluteGravity(i, iP) & 7;
        return (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) ? absoluteGravity : iP == 1 ? 5 : 3;
    }

    public final int k(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = eVar.f10a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.w & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i2;
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

    public final int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    public final int m(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void n(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    public final boolean o(View view) {
        return view.getParent() == this || this.E.contains(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.M);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0295 A[LOOP:0: B:109:0x0293->B:110:0x0295, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02b7 A[LOOP:1: B:112:0x02b5->B:113:0x02b7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02dc A[LOOP:2: B:115:0x02da->B:116:0x02dc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0331 A[LOOP:3: B:124:0x032f->B:125:0x0331, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x021b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r21, int r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 838
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        char c2;
        char c3;
        int iL;
        int iMax;
        int iCombineMeasuredStates;
        int iL2;
        int iM;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.F;
        boolean z = true;
        if (b1.b(this)) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (u(this.f1469d)) {
            t(this.f1469d, i, 0, i2, 0, this.o);
            iL = l(this.f1469d) + this.f1469d.getMeasuredWidth();
            iMax = Math.max(0, m(this.f1469d) + this.f1469d.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1469d.getMeasuredState());
        } else {
            iL = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (u(this.h)) {
            t(this.h, i, 0, i2, 0, this.o);
            iL = l(this.h) + this.h.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.h) + this.h.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, iL) + 0;
        iArr[c3] = Math.max(0, currentContentInsetStart - iL);
        if (u(this.f1466a)) {
            t(this.f1466a, i, iMax3, i2, 0, this.o);
            iL2 = l(this.f1466a) + this.f1466a.getMeasuredWidth();
            iMax = Math.max(iMax, m(this.f1466a) + this.f1466a.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1466a.getMeasuredState());
        } else {
            iL2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = Math.max(currentContentInsetEnd, iL2) + iMax3;
        iArr[c2] = Math.max(0, currentContentInsetEnd - iL2);
        if (u(this.i)) {
            iMax4 += s(this.i, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.i) + this.i.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.i.getMeasuredState());
        }
        if (u(this.f1470e)) {
            iMax4 += s(this.f1470e, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.f1470e) + this.f1470e.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1470e.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (((e) childAt.getLayoutParams()).f1478b == 0 && u(childAt)) {
                iMax4 += s(childAt, i, iMax4, i2, 0, iArr);
                iMax = Math.max(iMax, m(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i4 = this.r + this.s;
        int i5 = this.p + this.q;
        if (u(this.f1467b)) {
            s(this.f1467b, i, iMax4 + i5, i2, i4, iArr);
            int iL3 = l(this.f1467b) + this.f1467b.getMeasuredWidth();
            iM = m(this.f1467b) + this.f1467b.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1467b.getMeasuredState());
            iMax2 = iL3;
        } else {
            iM = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (u(this.f1468c)) {
            iMax2 = Math.max(iMax2, s(this.f1468c, i, iMax4 + i5, i2, iM + i4, iArr));
            iM = m(this.f1468c) + this.f1468c.getMeasuredHeight() + iM;
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f1468c.getMeasuredState());
        }
        int iMax5 = Math.max(iMax, iM);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax5;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax4 + iMax2, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16);
        if (!this.L) {
            z = false;
            break;
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            View childAt2 = getChildAt(i6);
            if (u(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                z = false;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, z ? 0 : iResolveSizeAndState2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.f892a);
        ActionMenuView actionMenuView = this.f1466a;
        a.b.o.i.g gVar2 = actionMenuView != null ? actionMenuView.p : null;
        int i = gVar.f1479c;
        if (i != 0 && this.K != null && gVar2 != null && (menuItemFindItem = gVar2.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (gVar.f1480d) {
            removeCallbacks(this.M);
            post(this.M);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        int i2;
        super.onRtlPropertiesChanged(i);
        d();
        p0 p0Var = this.t;
        boolean z = i == 1;
        if (z == p0Var.g) {
            return;
        }
        p0Var.g = z;
        if (p0Var.h) {
            if (z) {
                int i3 = p0Var.f332d;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = p0Var.f333e;
                }
                p0Var.f329a = i3;
                i2 = p0Var.f331c;
                if (i2 == Integer.MIN_VALUE) {
                }
            } else {
                int i4 = p0Var.f331c;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = p0Var.f333e;
                }
                p0Var.f329a = i4;
                i2 = p0Var.f332d;
                if (i2 == Integer.MIN_VALUE) {
                }
            }
            p0Var.f330b = i2;
        }
        p0Var.f329a = p0Var.f333e;
        i2 = p0Var.f334f;
        p0Var.f330b = i2;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar;
        g gVar = new g(super.onSaveInstanceState());
        d dVar = this.K;
        if (dVar != null && (iVar = dVar.f1476b) != null) {
            gVar.f1479c = iVar.f185a;
        }
        gVar.f1480d = p();
        return gVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    public boolean p() {
        ActionMenuView actionMenuView = this.f1466a;
        if (actionMenuView != null) {
            a.b.p.c cVar = actionMenuView.t;
            if (cVar != null && cVar.m()) {
                return true;
            }
        }
        return false;
    }

    public final int q(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iK, iMax + measuredWidth, view.getMeasuredHeight() + iK);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin + iMax;
    }

    public final int r(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iK = k(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iK, iMax, view.getMeasuredHeight() + iK);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    public final int s(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(a.b.l.a.a.b(getContext(), i));
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.h.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.h;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f1471f);
            }
        }
    }

    public void setCollapsible(boolean z) {
        this.L = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.u) {
            this.u = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(a.b.l.a.a.b(getContext(), i));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f1470e == null) {
                this.f1470e = new n(getContext(), null);
            }
            if (!o(this.f1470e)) {
                b(this.f1470e, true);
            }
        } else {
            ImageView imageView = this.f1470e;
            if (imageView != null && o(imageView)) {
                removeView(this.f1470e);
                this.E.remove(this.f1470e);
            }
        }
        ImageView imageView2 = this.f1470e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f1470e == null) {
            this.f1470e = new n(getContext(), null);
        }
        ImageView imageView = this.f1470e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.f1469d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(a.b.l.a.a.b(getContext(), i));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!o(this.f1469d)) {
                b(this.f1469d, true);
            }
        } else {
            ImageButton imageButton = this.f1469d;
            if (imageButton != null && o(imageButton)) {
                removeView(this.f1469d);
                this.E.remove(this.f1469d);
            }
        }
        ImageButton imageButton2 = this.f1469d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.f1469d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.G = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.f1466a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.k != i) {
            this.k = i;
            if (i == 0) {
                this.j = getContext();
            } else {
                this.j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1468c;
            if (textView != null && o(textView)) {
                removeView(this.f1468c);
                this.E.remove(this.f1468c);
            }
        } else {
            if (this.f1468c == null) {
                Context context = getContext();
                z zVar = new z(context, null);
                this.f1468c = zVar;
                zVar.setSingleLine();
                this.f1468c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.f1468c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f1468c.setTextColor(colorStateList);
                }
            }
            if (!o(this.f1468c)) {
                b(this.f1468c, true);
            }
        }
        TextView textView2 = this.f1468c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.f1468c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1467b;
            if (textView != null && o(textView)) {
                removeView(this.f1467b);
                this.E.remove(this.f1467b);
            }
        } else {
            if (this.f1467b == null) {
                Context context = getContext();
                z zVar = new z(context, null);
                this.f1467b = zVar;
                zVar.setSingleLine();
                this.f1467b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.l;
                if (i != 0) {
                    this.f1467b.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.z;
                if (colorStateList != null) {
                    this.f1467b.setTextColor(colorStateList);
                }
            }
            if (!o(this.f1467b)) {
                b(this.f1467b, true);
            }
        }
        TextView textView2 = this.f1467b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.x = charSequence;
    }

    public void setTitleMarginBottom(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.p = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.z = colorStateList;
        TextView textView = this.f1467b;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public final void t(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final boolean u(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public boolean v() {
        ActionMenuView actionMenuView = this.f1466a;
        if (actionMenuView != null) {
            a.b.p.c cVar = actionMenuView.t;
            if (cVar != null && cVar.n()) {
                return true;
            }
        }
        return false;
    }
}
