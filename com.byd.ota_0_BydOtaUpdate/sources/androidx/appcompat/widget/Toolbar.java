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
import b.f.m.s;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public Drawable E3;
    public CharSequence F3;
    public ImageButton G3;
    public View H3;
    public Context I3;
    public int J3;
    public int K3;
    public int L3;
    public int M3;
    public int N3;
    public int O3;
    public int P3;
    public int Q3;
    public int R3;
    public x S3;
    public int T3;
    public int U3;
    public int V3;
    public CharSequence W3;
    public CharSequence X3;
    public ColorStateList Y3;
    public ColorStateList Z3;
    public boolean a4;
    public boolean b4;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionMenuView f274c;
    public final ArrayList<View> c4;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f275d;
    public final ArrayList<View> d4;
    public final int[] e4;
    public f f4;
    public final ActionMenuView.e g4;
    public f0 h4;
    public ActionMenuPresenter i4;
    public d j4;
    public m.a k4;
    public g.a l4;
    public boolean m4;
    public final Runnable n4;
    public TextView s;
    public ImageButton x;
    public ImageView y;

    public class a implements ActionMenuView.e {
        public a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.f4;
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
            Toolbar.this.N();
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

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public g f279c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public i f280d;

        public d() {
        }

        @Override // b.b.p.j.m
        public void a(g gVar, boolean z) {
        }

        @Override // b.b.p.j.m
        public boolean c() {
            return false;
        }

        @Override // b.b.p.j.m
        public void d(Context context, g gVar) {
            i iVar;
            g gVar2 = this.f279c;
            if (gVar2 != null && (iVar = this.f280d) != null) {
                gVar2.f(iVar);
            }
            this.f279c = gVar;
        }

        @Override // b.b.p.j.m
        public boolean e(g gVar, i iVar) {
            KeyEvent.Callback callback = Toolbar.this.H3;
            if (callback instanceof b.b.p.c) {
                ((b.b.p.c) callback).d();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.H3);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.G3);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.H3 = null;
            toolbar3.a();
            this.f280d = null;
            Toolbar.this.requestLayout();
            iVar.q(false);
            return true;
        }

        @Override // b.b.p.j.m
        public boolean g(g gVar, i iVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.G3.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.G3);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.G3);
            }
            Toolbar.this.H3 = iVar.getActionView();
            this.f280d = iVar;
            ViewParent parent2 = Toolbar.this.H3.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.H3);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f780a = 8388611 | (toolbar4.M3 & 112);
                eVarGenerateDefaultLayoutParams.f281b = 2;
                toolbar4.H3.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.H3);
            }
            Toolbar.this.G();
            Toolbar.this.requestLayout();
            iVar.q(true);
            KeyEvent.Callback callback = Toolbar.this.H3;
            if (callback instanceof b.b.p.c) {
                ((b.b.p.c) callback).c();
            }
            return true;
        }

        @Override // b.b.p.j.m
        public boolean i(r rVar) {
            return false;
        }

        @Override // b.b.p.j.m
        public void j(boolean z) {
            if (this.f280d != null) {
                g gVar = this.f279c;
                boolean z2 = false;
                if (gVar != null) {
                    int size = gVar.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        if (this.f279c.getItem(i2) == this.f280d) {
                            z2 = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (z2) {
                    return;
                }
                e(this.f279c, this.f280d);
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
        ActionMenuView actionMenuView = this.f274c;
        return actionMenuView != null && actionMenuView.H();
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
        removeCallbacks(this.n4);
        post(this.n4);
    }

    public void G() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f281b != 2 && childAt != this.f274c) {
                removeViewAt(childCount);
                this.d4.add(childAt);
            }
        }
    }

    public void H(int i2, int i3) {
        h();
        this.S3.g(i2, i3);
    }

    public void I(g gVar, ActionMenuPresenter actionMenuPresenter) {
        if (gVar == null && this.f274c == null) {
            return;
        }
        k();
        g gVarL = this.f274c.L();
        if (gVarL == gVar) {
            return;
        }
        if (gVarL != null) {
            gVarL.O(this.i4);
            gVarL.O(this.j4);
        }
        if (this.j4 == null) {
            this.j4 = new d();
        }
        actionMenuPresenter.H(true);
        if (gVar != null) {
            gVar.c(actionMenuPresenter, this.I3);
            gVar.c(this.j4, this.I3);
        } else {
            actionMenuPresenter.d(this.I3, null);
            this.j4.d(this.I3, null);
            actionMenuPresenter.j(true);
            this.j4.j(true);
        }
        this.f274c.setPopupTheme(this.J3);
        this.f274c.setPresenter(actionMenuPresenter);
        this.i4 = actionMenuPresenter;
    }

    public void J(Context context, int i2) {
        this.L3 = i2;
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void K(Context context, int i2) {
        this.K3 = i2;
        TextView textView = this.f275d;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public final boolean L() {
        if (!this.m4) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (M(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean M(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public boolean N() {
        ActionMenuView actionMenuView = this.f274c;
        return actionMenuView != null && actionMenuView.N();
    }

    public void a() {
        for (int size = this.d4.size() - 1; size >= 0; size--) {
            addView(this.d4.get(size));
        }
        this.d4.clear();
    }

    public final void b(List<View> list, int i2) {
        boolean z = s.v(this) == 1;
        int childCount = getChildCount();
        int iA = b.f.m.d.a(i2, s.v(this));
        list.clear();
        if (!z) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f281b == 0 && M(childAt) && p(eVar.f780a) == iA) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = childCount - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f281b == 0 && M(childAt2) && p(eVar2.f780a) == iA) {
                list.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f281b = 1;
        if (!z || this.H3 == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.d4.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f274c) != null && actionMenuView.I();
    }

    public void e() {
        d dVar = this.j4;
        i iVar = dVar == null ? null : dVar.f280d;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.f274c;
        if (actionMenuView != null) {
            actionMenuView.z();
        }
    }

    public void g() {
        if (this.G3 == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, b.b.a.N);
            this.G3 = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.E3);
            this.G3.setContentDescription(this.F3);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f780a = 8388611 | (this.M3 & 112);
            eVarGenerateDefaultLayoutParams.f281b = 2;
            this.G3.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.G3.setOnClickListener(new c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.G3;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.G3;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        x xVar = this.S3;
        if (xVar != null) {
            return xVar.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.U3;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        x xVar = this.S3;
        if (xVar != null) {
            return xVar.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        x xVar = this.S3;
        if (xVar != null) {
            return xVar.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        x xVar = this.S3;
        if (xVar != null) {
            return xVar.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.T3;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        g gVarL;
        ActionMenuView actionMenuView = this.f274c;
        return actionMenuView != null && (gVarL = actionMenuView.L()) != null && gVarL.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.U3, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return s.v(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return s.v(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.T3, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.y;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.y;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        j();
        return this.f274c.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.x;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.x;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.i4;
    }

    public Drawable getOverflowIcon() {
        j();
        return this.f274c.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.I3;
    }

    public int getPopupTheme() {
        return this.J3;
    }

    public CharSequence getSubtitle() {
        return this.X3;
    }

    public final TextView getSubtitleTextView() {
        return this.s;
    }

    public CharSequence getTitle() {
        return this.W3;
    }

    public int getTitleMarginBottom() {
        return this.R3;
    }

    public int getTitleMarginEnd() {
        return this.P3;
    }

    public int getTitleMarginStart() {
        return this.O3;
    }

    public int getTitleMarginTop() {
        return this.Q3;
    }

    public final TextView getTitleTextView() {
        return this.f275d;
    }

    public p getWrapper() {
        if (this.h4 == null) {
            this.h4 = new f0(this, true);
        }
        return this.h4;
    }

    public final void h() {
        if (this.S3 == null) {
            this.S3 = new x();
        }
    }

    public final void i() {
        if (this.y == null) {
            this.y = new AppCompatImageView(getContext());
        }
    }

    public final void j() {
        k();
        if (this.f274c.L() == null) {
            g gVar = (g) this.f274c.getMenu();
            if (this.j4 == null) {
                this.j4 = new d();
            }
            this.f274c.setExpandedActionViewsExclusive(true);
            gVar.c(this.j4, this.I3);
        }
    }

    public final void k() {
        if (this.f274c == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f274c = actionMenuView;
            actionMenuView.setPopupTheme(this.J3);
            this.f274c.setOnMenuItemClickListener(this.g4);
            this.f274c.M(this.k4, this.l4);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f780a = 8388613 | (this.M3 & 112);
            this.f274c.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f274c, false);
        }
    }

    public final void l() {
        if (this.x == null) {
            this.x = new AppCompatImageButton(getContext(), null, b.b.a.N);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f780a = 8388611 | (this.M3 & 112);
            this.x.setLayoutParams(eVarGenerateDefaultLayoutParams);
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
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof a.C0005a ? new e((a.C0005a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.n4);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.b4 = false;
        }
        if (!this.b4) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.b4 = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.b4 = false;
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
        int[] iArr = this.e4;
        if (k0.b(this)) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (M(this.x)) {
            E(this.x, i2, 0, i3, 0, this.N3);
            measuredWidth = this.x.getMeasuredWidth() + s(this.x);
            iMax = Math.max(0, this.x.getMeasuredHeight() + t(this.x));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.x.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (M(this.G3)) {
            E(this.G3, i2, 0, i3, 0, this.N3);
            measuredWidth = this.G3.getMeasuredWidth() + s(this.G3);
            iMax = Math.max(iMax, this.G3.getMeasuredHeight() + t(this.G3));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.G3.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c3] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (M(this.f274c)) {
            E(this.f274c, i2, iMax3, i3, 0, this.N3);
            measuredWidth2 = this.f274c.getMeasuredWidth() + s(this.f274c);
            iMax = Math.max(iMax, this.f274c.getMeasuredHeight() + t(this.f274c));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f274c.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c2] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (M(this.H3)) {
            iMax4 += D(this.H3, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.H3.getMeasuredHeight() + t(this.H3));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.H3.getMeasuredState());
        }
        if (M(this.y)) {
            iMax4 += D(this.y, i2, iMax4, i3, 0, iArr);
            iMax = Math.max(iMax, this.y.getMeasuredHeight() + t(this.y));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.y.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (((e) childAt.getLayoutParams()).f281b == 0 && M(childAt)) {
                iMax4 += D(childAt, i2, iMax4, i3, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + t(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i5 = this.Q3 + this.R3;
        int i6 = this.O3 + this.P3;
        if (M(this.f275d)) {
            D(this.f275d, i2, iMax4 + i6, i3, i5, iArr);
            int measuredWidth3 = this.f275d.getMeasuredWidth() + s(this.f275d);
            measuredHeight = this.f275d.getMeasuredHeight() + t(this.f275d);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f275d.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (M(this.s)) {
            iMax2 = Math.max(iMax2, D(this.s, i2, iMax4 + i6, i3, measuredHeight + i5, iArr));
            measuredHeight += this.s.getMeasuredHeight() + t(this.s);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.s.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, (-16777216) & iCombineMeasuredStates2), L() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f274c;
        g gVarL = actionMenuView != null ? actionMenuView.L() : null;
        int i2 = savedState.s;
        if (i2 != 0 && this.j4 != null && gVarL != null && (menuItemFindItem = gVarL.findItem(i2)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.x) {
            F();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i2);
        }
        h();
        this.S3.f(i2 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.j4;
        if (dVar != null && (iVar = dVar.f280d) != null) {
            savedState.s = iVar.getItemId();
        }
        savedState.x = A();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.a4 = false;
        }
        if (!this.a4) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.a4 = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.a4 = false;
        }
        return true;
    }

    public final int p(int i2) {
        int iV = s.v(this);
        int iA = b.f.m.d.a(i2, iV) & 7;
        return (iA == 1 || iA == 3 || iA == 5) ? iA : iV == 1 ? 5 : 3;
    }

    public final int q(View view, int i2) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int iR = r(eVar.f780a);
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
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.V3 & 112;
    }

    public final int s(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return b.f.m.g.b(marginLayoutParams) + b.f.m.g.a(marginLayoutParams);
    }

    public void setCollapseContentDescription(int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(int i2) {
        setCollapseIcon(b.b.l.a.a.d(getContext(), i2));
    }

    public void setCollapsible(boolean z) {
        this.m4 = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.U3) {
            this.U3 = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.T3) {
            this.T3 = i2;
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
        this.x.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
        this.f4 = fVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        j();
        this.f274c.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.J3 != i2) {
            this.J3 = i2;
            if (i2 == 0) {
                this.I3 = getContext();
            } else {
                this.I3 = new ContextThemeWrapper(getContext(), i2);
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
        this.R3 = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.P3 = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.O3 = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.Q3 = i2;
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
        d dVar = this.j4;
        return (dVar == null || dVar.f280d == null) ? false : true;
    }

    public boolean w() {
        ActionMenuView actionMenuView = this.f274c;
        return actionMenuView != null && actionMenuView.F();
    }

    public void x(int i2) {
        getMenuInflater().inflate(i2, getMenu());
    }

    public final boolean y(View view) {
        return view.getParent() == this || this.d4.contains(view);
    }

    public boolean z() {
        ActionMenuView actionMenuView = this.f274c;
        return actionMenuView != null && actionMenuView.G();
    }

    public static class e extends a.C0005a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f281b;

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f281b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i2, int i3) {
            super(i2, i3);
            this.f281b = 0;
            this.f780a = 8388627;
        }

        public e(e eVar) {
            super((a.C0005a) eVar);
            this.f281b = 0;
            this.f281b = eVar.f281b;
        }

        public e(a.C0005a c0005a) {
            super(c0005a);
            this.f281b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f281b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f281b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.O);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.G3;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            this.G3.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.G3;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.E3);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!y(this.y)) {
                c(this.y, true);
            }
        } else {
            ImageView imageView = this.y;
            if (imageView != null && y(imageView)) {
                removeView(this.y);
                this.d4.remove(this.y);
            }
        }
        ImageView imageView2 = this.y;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.y;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.x;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            l();
            if (!y(this.x)) {
                c(this.x, true);
            }
        } else {
            ImageButton imageButton = this.x;
            if (imageButton != null && y(imageButton)) {
                removeView(this.x);
                this.d4.remove(this.x);
            }
        }
        ImageButton imageButton2 = this.x;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.s;
            if (textView != null && y(textView)) {
                removeView(this.s);
                this.d4.remove(this.s);
            }
        } else {
            if (this.s == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.s = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.s.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.L3;
                if (i2 != 0) {
                    this.s.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.Z3;
                if (colorStateList != null) {
                    this.s.setTextColor(colorStateList);
                }
            }
            if (!y(this.s)) {
                c(this.s, true);
            }
        }
        TextView textView2 = this.s;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.X3 = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.Z3 = colorStateList;
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f275d;
            if (textView != null && y(textView)) {
                removeView(this.f275d);
                this.d4.remove(this.f275d);
            }
        } else {
            if (this.f275d == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.f275d = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.f275d.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.K3;
                if (i2 != 0) {
                    this.f275d.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.Y3;
                if (colorStateList != null) {
                    this.f275d.setTextColor(colorStateList);
                }
            }
            if (!y(this.f275d)) {
                c(this.f275d, true);
            }
        }
        TextView textView2 = this.f275d;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.W3 = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.Y3 = colorStateList;
        TextView textView = this.f275d;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int s;
        public boolean x;

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
            this.s = parcel.readInt();
            this.x = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.s);
            parcel.writeInt(this.x ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.V3 = 8388627;
        this.c4 = new ArrayList<>();
        this.d4 = new ArrayList<>();
        this.e4 = new int[2];
        this.g4 = new a();
        this.n4 = new b();
        Context context2 = getContext();
        int[] iArr = j.m3;
        e0 e0VarU = e0.u(context2, attributeSet, iArr, i2, 0);
        s.R(this, context, iArr, attributeSet, e0VarU.q(), i2, 0);
        this.K3 = e0VarU.m(j.O3, 0);
        this.L3 = e0VarU.m(j.F3, 0);
        this.V3 = e0VarU.k(j.n3, this.V3);
        this.M3 = e0VarU.k(j.o3, 48);
        int iD = e0VarU.d(j.I3, 0);
        int i3 = j.N3;
        iD = e0VarU.r(i3) ? e0VarU.d(i3, iD) : iD;
        this.R3 = iD;
        this.Q3 = iD;
        this.P3 = iD;
        this.O3 = iD;
        int iD2 = e0VarU.d(j.L3, -1);
        if (iD2 >= 0) {
            this.O3 = iD2;
        }
        int iD3 = e0VarU.d(j.K3, -1);
        if (iD3 >= 0) {
            this.P3 = iD3;
        }
        int iD4 = e0VarU.d(j.M3, -1);
        if (iD4 >= 0) {
            this.Q3 = iD4;
        }
        int iD5 = e0VarU.d(j.J3, -1);
        if (iD5 >= 0) {
            this.R3 = iD5;
        }
        this.N3 = e0VarU.e(j.z3, -1);
        int iD6 = e0VarU.d(j.v3, Integer.MIN_VALUE);
        int iD7 = e0VarU.d(j.r3, Integer.MIN_VALUE);
        int iE = e0VarU.e(j.t3, 0);
        int iE2 = e0VarU.e(j.u3, 0);
        h();
        this.S3.e(iE, iE2);
        if (iD6 != Integer.MIN_VALUE || iD7 != Integer.MIN_VALUE) {
            this.S3.g(iD6, iD7);
        }
        this.T3 = e0VarU.d(j.w3, Integer.MIN_VALUE);
        this.U3 = e0VarU.d(j.s3, Integer.MIN_VALUE);
        this.E3 = e0VarU.f(j.q3);
        this.F3 = e0VarU.o(j.p3);
        CharSequence charSequenceO = e0VarU.o(j.H3);
        if (!TextUtils.isEmpty(charSequenceO)) {
            setTitle(charSequenceO);
        }
        CharSequence charSequenceO2 = e0VarU.o(j.E3);
        if (!TextUtils.isEmpty(charSequenceO2)) {
            setSubtitle(charSequenceO2);
        }
        this.I3 = getContext();
        setPopupTheme(e0VarU.m(j.D3, 0));
        Drawable drawableF = e0VarU.f(j.C3);
        if (drawableF != null) {
            setNavigationIcon(drawableF);
        }
        CharSequence charSequenceO3 = e0VarU.o(j.B3);
        if (!TextUtils.isEmpty(charSequenceO3)) {
            setNavigationContentDescription(charSequenceO3);
        }
        Drawable drawableF2 = e0VarU.f(j.x3);
        if (drawableF2 != null) {
            setLogo(drawableF2);
        }
        CharSequence charSequenceO4 = e0VarU.o(j.y3);
        if (!TextUtils.isEmpty(charSequenceO4)) {
            setLogoDescription(charSequenceO4);
        }
        int i4 = j.P3;
        if (e0VarU.r(i4)) {
            setTitleTextColor(e0VarU.c(i4));
        }
        int i5 = j.G3;
        if (e0VarU.r(i5)) {
            setSubtitleTextColor(e0VarU.c(i5));
        }
        int i6 = j.A3;
        if (e0VarU.r(i6)) {
            x(e0VarU.m(i6, 0));
        }
        e0VarU.v();
    }
}
