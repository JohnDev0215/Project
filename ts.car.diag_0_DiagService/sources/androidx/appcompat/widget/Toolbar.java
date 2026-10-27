package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
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
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.menu.f;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    private CharSequence A;
    private ColorStateList B;
    private ColorStateList C;
    private boolean D;
    private boolean E;
    private final ArrayList<View> F;
    private final ArrayList<View> G;
    private final int[] H;
    f I;
    private final ActionMenuView.e J;
    private V K;
    private ActionMenuPresenter L;
    private d M;
    private l.a N;
    private f.a O;
    private boolean P;
    private final Runnable Q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ActionMenuView f323c;
    private TextView d;
    private TextView e;
    private ImageButton f;
    private ImageView g;
    private Drawable h;
    private CharSequence i;
    ImageButton j;
    View k;
    private Context l;
    private int m;
    private int n;
    private int o;
    int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private L v;
    private int w;
    private int x;
    private int y;
    private CharSequence z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements ActionMenuView.e {
        a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            f fVar = Toolbar.this.I;
            if (fVar != null) {
                return fVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.m0();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d implements androidx.appcompat.view.menu.l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        androidx.appcompat.view.menu.f f327c;
        androidx.appcompat.view.menu.h d;

        d() {
        }

        @Override // androidx.appcompat.view.menu.l
        public void a(androidx.appcompat.view.menu.f fVar, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.l
        public boolean c() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.l
        public void d(Context context, androidx.appcompat.view.menu.f fVar) {
            androidx.appcompat.view.menu.h hVar;
            androidx.appcompat.view.menu.f fVar2 = this.f327c;
            if (fVar2 != null && (hVar = this.d) != null) {
                fVar2.f(hVar);
            }
            this.f327c = fVar;
        }

        @Override // androidx.appcompat.view.menu.l
        public boolean e(androidx.appcompat.view.menu.f fVar, androidx.appcompat.view.menu.h hVar) {
            KeyEvent.Callback callback = Toolbar.this.k;
            if (callback instanceof androidx.appcompat.c.c) {
                ((androidx.appcompat.c.c) callback).d();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.k);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.j);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.k = null;
            toolbar3.a();
            this.d = null;
            Toolbar.this.requestLayout();
            hVar.p(false);
            return true;
        }

        @Override // androidx.appcompat.view.menu.l
        public boolean g(androidx.appcompat.view.menu.f fVar, androidx.appcompat.view.menu.h hVar) {
            Toolbar.this.g();
            ViewParent parent = Toolbar.this.j.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.j);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.j);
            }
            Toolbar.this.k = hVar.getActionView();
            this.d = hVar;
            ViewParent parent2 = Toolbar.this.k.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.k);
                }
                e eVarGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                eVarGenerateDefaultLayoutParams.f95a = 8388611 | (toolbar4.p & 112);
                eVarGenerateDefaultLayoutParams.f328b = 2;
                toolbar4.k.setLayoutParams(eVarGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.k);
            }
            Toolbar.this.T();
            Toolbar.this.requestLayout();
            hVar.p(true);
            KeyEvent.Callback callback = Toolbar.this.k;
            if (callback instanceof androidx.appcompat.c.c) {
                ((androidx.appcompat.c.c) callback).c();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.l
        public void h(l.a aVar) {
        }

        @Override // androidx.appcompat.view.menu.l
        public boolean i(androidx.appcompat.view.menu.q qVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.l
        public void j(boolean z) {
            if (this.d != null) {
                androidx.appcompat.view.menu.f fVar = this.f327c;
                boolean z2 = false;
                if (fVar != null) {
                    int size = fVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.f327c.getItem(i) == this.d) {
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z2) {
                    return;
                }
                e(this.f327c, this.d);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(@NonNull Context context) {
        this(context, null);
    }

    private MenuInflater A() {
        return new androidx.appcompat.c.g(getContext());
    }

    private int F(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int G(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = 0;
        int measuredWidth = 0;
        while (i3 < size) {
            View view = list.get(i3);
            e eVar = (e) view.getLayoutParams();
            int i4 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - i;
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - i2;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, i5);
            int iMax3 = Math.max(0, -i4);
            int iMax4 = Math.max(0, -i5);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i3++;
            i2 = iMax4;
            i = iMax3;
        }
        return measuredWidth;
    }

    private boolean L(View view) {
        return view.getParent() == this || this.G.contains(view);
    }

    private int O(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int iQ = q(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iQ, iMax + measuredWidth, view.getMeasuredHeight() + iQ);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
    }

    private int P(View view, int i, int[] iArr, int i2) {
        e eVar = (e) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iQ = q(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iQ, iMax, view.getMeasuredHeight() + iQ);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int Q(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private void R(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void S() {
        removeCallbacks(this.Q);
        post(this.Q);
    }

    private void b(List<View> list, int i) {
        boolean z = ViewCompat.q(this) == 1;
        int childCount = getChildCount();
        int iA = androidx.core.view.d.a(i, ViewCompat.q(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f328b == 0 && l0(childAt) && p(eVar.f95a) == iA) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f328b == 0 && l0(childAt2) && p(eVar2.f95a) == iA) {
                list.add(childAt2);
            }
        }
    }

    private void c(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (e) layoutParams;
        eVarGenerateDefaultLayoutParams.f328b = 1;
        if (!z || this.k == null) {
            addView(view, eVarGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.G.add(view);
        }
    }

    private void h() {
        if (this.v == null) {
            this.v = new L();
        }
    }

    private void i() {
        if (this.g == null) {
            this.g = new C0068n(getContext());
        }
    }

    private void j() {
        k();
        if (this.f323c.U() == null) {
            androidx.appcompat.view.menu.f fVar = (androidx.appcompat.view.menu.f) this.f323c.M();
            if (this.M == null) {
                this.M = new d();
            }
            this.f323c.V(true);
            fVar.c(this.M, this.l);
        }
    }

    private void k() {
        if (this.f323c == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f323c = actionMenuView;
            actionMenuView.Z(this.m);
            this.f323c.X(this.J);
            this.f323c.W(this.N, this.O);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f95a = 8388613 | (this.p & 112);
            this.f323c.setLayoutParams(eVarGenerateDefaultLayoutParams);
            c(this.f323c, false);
        }
    }

    private boolean k0() {
        if (!this.P) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (l0(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private void l() {
        if (this.f == null) {
            this.f = new C0066l(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f95a = 8388611 | (this.p & 112);
            this.f.setLayoutParams(eVarGenerateDefaultLayoutParams);
        }
    }

    private boolean l0(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int p(int i) {
        int iQ = ViewCompat.q(this);
        int iA = androidx.core.view.d.a(i, iQ) & 7;
        return (iA == 1 || iA == 3 || iA == 5) ? iA : iQ == 1 ? 5 : 3;
    }

    private int q(View view, int i) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int iR = r(eVar.f95a);
        if (iR == 48) {
            return getPaddingTop() - i2;
        }
        if (iR == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i3 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i3) {
            iMax = i3;
        } else {
            int i4 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i4 < i5) {
                iMax = Math.max(0, iMax - (i5 - i4));
            }
        }
        return paddingTop + iMax;
    }

    private int r(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.y & 112;
    }

    private int y(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return androidx.core.view.g.b(marginLayoutParams) + androidx.core.view.g.a(marginLayoutParams);
    }

    @Nullable
    public CharSequence B() {
        ImageButton imageButton = this.f;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    @Nullable
    public Drawable C() {
        ImageButton imageButton = this.f;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public CharSequence D() {
        return this.A;
    }

    public CharSequence E() {
        return this.z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public B H() {
        if (this.K == null) {
            this.K = new V(this, true);
        }
        return this.K;
    }

    public boolean I() {
        d dVar = this.M;
        return (dVar == null || dVar.d == null) ? false : true;
    }

    public boolean J() {
        ActionMenuView actionMenuView = this.f323c;
        return actionMenuView != null && actionMenuView.O();
    }

    public void K(@MenuRes int i) {
        A().inflate(i, z());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean M() {
        ActionMenuView actionMenuView = this.f323c;
        return actionMenuView != null && actionMenuView.P();
    }

    public boolean N() {
        ActionMenuView actionMenuView = this.f323c;
        return actionMenuView != null && actionMenuView.Q();
    }

    void T() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f328b != 2 && childAt != this.f323c) {
                removeViewAt(childCount);
                this.G.add(childAt);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void U(boolean z) {
        this.P = z;
        requestLayout();
    }

    public void V(int i, int i2) {
        h();
        this.v.e(i, i2);
    }

    public void W(Drawable drawable) {
        if (drawable != null) {
            i();
            if (!L(this.g)) {
                c(this.g, true);
            }
        } else {
            ImageView imageView = this.g;
            if (imageView != null && L(imageView)) {
                removeView(this.g);
                this.G.remove(this.g);
            }
        }
        ImageView imageView2 = this.g;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void X(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            i();
        }
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void Y(androidx.appcompat.view.menu.f fVar, ActionMenuPresenter actionMenuPresenter) {
        if (fVar == null && this.f323c == null) {
            return;
        }
        k();
        androidx.appcompat.view.menu.f fVarU = this.f323c.U();
        if (fVarU == fVar) {
            return;
        }
        if (fVarU != null) {
            fVarU.O(this.L);
            fVarU.O(this.M);
        }
        if (this.M == null) {
            this.M = new d();
        }
        actionMenuPresenter.F(true);
        if (fVar != null) {
            fVar.c(actionMenuPresenter, this.l);
            fVar.c(this.M, this.l);
        } else {
            actionMenuPresenter.d(this.l, null);
            this.M.d(this.l, null);
            actionMenuPresenter.j(true);
            this.M.j(true);
        }
        this.f323c.Z(this.m);
        this.f323c.a0(actionMenuPresenter);
        this.L = actionMenuPresenter;
    }

    public void Z(@StringRes int i) {
        a0(i != 0 ? getContext().getText(i) : null);
    }

    void a() {
        for (int size = this.G.size() - 1; size >= 0; size--) {
            addView(this.G.get(size));
        }
        this.G.clear();
    }

    public void a0(@Nullable CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            l();
        }
        ImageButton imageButton = this.f;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void b0(@Nullable Drawable drawable) {
        if (drawable != null) {
            l();
            if (!L(this.f)) {
                c(this.f, true);
            }
        } else {
            ImageButton imageButton = this.f;
            if (imageButton != null && L(imageButton)) {
                removeView(this.f);
                this.G.remove(this.f);
            }
        }
        ImageButton imageButton2 = this.f;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void c0(View.OnClickListener onClickListener) {
        l();
        this.f.setOnClickListener(onClickListener);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f323c) != null && actionMenuView.R();
    }

    public void d0(@StyleRes int i) {
        if (this.m != i) {
            this.m = i;
            if (i == 0) {
                this.l = getContext();
            } else {
                this.l = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void e() {
        d dVar = this.M;
        androidx.appcompat.view.menu.h hVar = dVar == null ? null : dVar.d;
        if (hVar != null) {
            hVar.collapseActionView();
        }
    }

    public void e0(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.e;
            if (textView != null && L(textView)) {
                removeView(this.e);
                this.G.remove(this.e);
            }
        } else {
            if (this.e == null) {
                Context context = getContext();
                C0077x c0077x = new C0077x(context);
                this.e = c0077x;
                c0077x.setSingleLine();
                this.e.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.o;
                if (i != 0) {
                    this.e.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.C;
                if (colorStateList != null) {
                    this.e.setTextColor(colorStateList);
                }
            }
            if (!L(this.e)) {
                c(this.e, true);
            }
        }
        TextView textView2 = this.e;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.A = charSequence;
    }

    public void f() {
        ActionMenuView actionMenuView = this.f323c;
        if (actionMenuView != null) {
            actionMenuView.H();
        }
    }

    public void f0(Context context, @StyleRes int i) {
        this.o = i;
        TextView textView = this.e;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    void g() {
        if (this.j == null) {
            C0066l c0066l = new C0066l(getContext(), null, R$attr.toolbarNavigationButtonStyle);
            this.j = c0066l;
            c0066l.setImageDrawable(this.h);
            this.j.setContentDescription(this.i);
            e eVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            eVarGenerateDefaultLayoutParams.f95a = 8388611 | (this.p & 112);
            eVarGenerateDefaultLayoutParams.f328b = 2;
            this.j.setLayoutParams(eVarGenerateDefaultLayoutParams);
            this.j.setOnClickListener(new c());
        }
    }

    public void g0(@NonNull ColorStateList colorStateList) {
        this.C = colorStateList;
        TextView textView = this.e;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void h0(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.d;
            if (textView != null && L(textView)) {
                removeView(this.d);
                this.G.remove(this.d);
            }
        } else {
            if (this.d == null) {
                Context context = getContext();
                C0077x c0077x = new C0077x(context);
                this.d = c0077x;
                c0077x.setSingleLine();
                this.d.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.n;
                if (i != 0) {
                    this.d.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.d.setTextColor(colorStateList);
                }
            }
            if (!L(this.d)) {
                c(this.d, true);
            }
        }
        TextView textView2 = this.d;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void i0(Context context, @StyleRes int i) {
        this.n = i;
        TextView textView = this.d;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void j0(@NonNull ColorStateList colorStateList) {
        this.B = colorStateList;
        TextView textView = this.d;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    public boolean m0() {
        ActionMenuView actionMenuView = this.f323c;
        return actionMenuView != null && actionMenuView.b0();
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
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ActionBar.a ? new e((ActionBar.a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.Q);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.E = false;
        }
        if (!this.E) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.E = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.E = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02a3 A[LOOP:0: B:104:0x02a1->B:105:0x02a3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c5 A[LOOP:1: B:107:0x02c3->B:108:0x02c5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fe A[LOOP:2: B:116:0x02fc->B:117:0x02fe, LOOP_END] */
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
    /* JADX WARN: Removed duplicated region for block: B:91:0x0229  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 787
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        char c2;
        char c3;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.H;
        if (a0.b(this)) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (l0(this.f)) {
            R(this.f, i, 0, i2, 0, this.q);
            measuredWidth = this.f.getMeasuredWidth() + y(this.f);
            iMax = Math.max(0, this.f.getMeasuredHeight() + F(this.f));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (l0(this.j)) {
            R(this.j, i, 0, i2, 0, this.q);
            measuredWidth = this.j.getMeasuredWidth() + y(this.j);
            iMax = Math.max(iMax, this.j.getMeasuredHeight() + F(this.j));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.j.getMeasuredState());
        }
        int iX = x();
        int iMax3 = 0 + Math.max(iX, measuredWidth);
        iArr[c3] = Math.max(0, iX - measuredWidth);
        if (l0(this.f323c)) {
            R(this.f323c, i, iMax3, i2, 0, this.q);
            measuredWidth2 = this.f323c.getMeasuredWidth() + y(this.f323c);
            iMax = Math.max(iMax, this.f323c.getMeasuredHeight() + F(this.f323c));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f323c.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int iU = u();
        int iMax4 = iMax3 + Math.max(iU, measuredWidth2);
        iArr[c2] = Math.max(0, iU - measuredWidth2);
        if (l0(this.k)) {
            iMax4 += Q(this.k, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.k.getMeasuredHeight() + F(this.k));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.k.getMeasuredState());
        }
        if (l0(this.g)) {
            iMax4 += Q(this.g, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.g.getMeasuredHeight() + F(this.g));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.g.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (((e) childAt.getLayoutParams()).f328b == 0 && l0(childAt)) {
                iMax4 += Q(childAt, i, iMax4, i2, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + F(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i4 = this.t + this.u;
        int i5 = this.r + this.s;
        if (l0(this.d)) {
            Q(this.d, i, iMax4 + i5, i2, i4, iArr);
            int measuredWidth3 = this.d.getMeasuredWidth() + y(this.d);
            measuredHeight = this.d.getMeasuredHeight() + F(this.d);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.d.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (l0(this.e)) {
            iMax2 = Math.max(iMax2, Q(this.e, i, iMax4 + i5, i2, measuredHeight + i4, iArr));
            measuredHeight += this.e.getMeasuredHeight() + F(this.e);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.e.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2), k0() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f323c;
        androidx.appcompat.view.menu.f fVarU = actionMenuView != null ? actionMenuView.U() : null;
        int i = savedState.e;
        if (i != 0 && this.M != null && fVarU != null && (menuItemFindItem = fVarU.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f) {
            S();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        h();
        this.v.d(i == 1);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.h hVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.M;
        if (dVar != null && (hVar = dVar.d) != null) {
            savedState.e = hVar.getItemId();
        }
        savedState.f = N();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.D = false;
        }
        if (!this.D) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    public int s() {
        L l = this.v;
        if (l != null) {
            return l.a();
        }
        return 0;
    }

    public int t() {
        L l = this.v;
        if (l != null) {
            return l.b();
        }
        return 0;
    }

    public int u() {
        androidx.appcompat.view.menu.f fVarU;
        ActionMenuView actionMenuView = this.f323c;
        return actionMenuView != null && (fVarU = actionMenuView.U()) != null && fVarU.hasVisibleItems() ? Math.max(s(), Math.max(this.x, 0)) : s();
    }

    public int v() {
        return ViewCompat.q(this) == 1 ? u() : x();
    }

    public int w() {
        return ViewCompat.q(this) == 1 ? x() : u();
    }

    public int x() {
        return C() != null ? Math.max(t(), Math.max(this.w, 0)) : t();
    }

    public Menu z() {
        j();
        return this.f323c.M();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class e extends ActionBar.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f328b;

        public e(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f328b = 0;
        }

        void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public e(int i, int i2) {
            super(i, i2);
            this.f328b = 0;
            this.f95a = 8388627;
        }

        public e(e eVar) {
            super((ActionBar.a) eVar);
            this.f328b = 0;
            this.f328b = eVar.f328b;
        }

        public e(ActionBar.a aVar) {
            super(aVar);
            this.f328b = 0;
        }

        public e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f328b = 0;
            a(marginLayoutParams);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f328b = 0;
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        int e;
        boolean f;

        /* JADX INFO: compiled from: caradapter-bug */
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.e = parcel.readInt();
            this.f = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = 8388627;
        this.F = new ArrayList<>();
        this.G = new ArrayList<>();
        this.H = new int[2];
        this.J = new a();
        this.Q = new b();
        U u = U.u(getContext(), attributeSet, R$styleable.Toolbar, i, 0);
        ViewCompat.K(this, context, R$styleable.Toolbar, attributeSet, u.q(), i, 0);
        this.n = u.m(R$styleable.Toolbar_titleTextAppearance, 0);
        this.o = u.m(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.y = u.k(R$styleable.Toolbar_android_gravity, this.y);
        this.p = u.k(R$styleable.Toolbar_buttonGravity, 48);
        int iD = u.d(R$styleable.Toolbar_titleMargin, 0);
        iD = u.r(R$styleable.Toolbar_titleMargins) ? u.d(R$styleable.Toolbar_titleMargins, iD) : iD;
        this.u = iD;
        this.t = iD;
        this.s = iD;
        this.r = iD;
        int iD2 = u.d(R$styleable.Toolbar_titleMarginStart, -1);
        if (iD2 >= 0) {
            this.r = iD2;
        }
        int iD3 = u.d(R$styleable.Toolbar_titleMarginEnd, -1);
        if (iD3 >= 0) {
            this.s = iD3;
        }
        int iD4 = u.d(R$styleable.Toolbar_titleMarginTop, -1);
        if (iD4 >= 0) {
            this.t = iD4;
        }
        int iD5 = u.d(R$styleable.Toolbar_titleMarginBottom, -1);
        if (iD5 >= 0) {
            this.u = iD5;
        }
        this.q = u.e(R$styleable.Toolbar_maxButtonHeight, -1);
        int iD6 = u.d(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iD7 = u.d(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iE = u.e(R$styleable.Toolbar_contentInsetLeft, 0);
        int iE2 = u.e(R$styleable.Toolbar_contentInsetRight, 0);
        h();
        this.v.c(iE, iE2);
        if (iD6 != Integer.MIN_VALUE || iD7 != Integer.MIN_VALUE) {
            this.v.e(iD6, iD7);
        }
        this.w = u.d(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.x = u.d(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.h = u.f(R$styleable.Toolbar_collapseIcon);
        this.i = u.o(R$styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceO = u.o(R$styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceO)) {
            h0(charSequenceO);
        }
        CharSequence charSequenceO2 = u.o(R$styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceO2)) {
            e0(charSequenceO2);
        }
        this.l = getContext();
        d0(u.m(R$styleable.Toolbar_popupTheme, 0));
        Drawable drawableF = u.f(R$styleable.Toolbar_navigationIcon);
        if (drawableF != null) {
            b0(drawableF);
        }
        CharSequence charSequenceO3 = u.o(R$styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceO3)) {
            a0(charSequenceO3);
        }
        Drawable drawableF2 = u.f(R$styleable.Toolbar_logo);
        if (drawableF2 != null) {
            W(drawableF2);
        }
        CharSequence charSequenceO4 = u.o(R$styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceO4)) {
            X(charSequenceO4);
        }
        if (u.r(R$styleable.Toolbar_titleTextColor)) {
            j0(u.c(R$styleable.Toolbar_titleTextColor));
        }
        if (u.r(R$styleable.Toolbar_subtitleTextColor)) {
            g0(u.c(R$styleable.Toolbar_subtitleTextColor));
        }
        if (u.r(R$styleable.Toolbar_menu)) {
            K(u.m(R$styleable.Toolbar_menu, 0));
        }
        u.v();
    }
}
