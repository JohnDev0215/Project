package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import defpackage.a1;
import defpackage.a2;
import defpackage.b9;
import defpackage.i3;
import defpackage.k1;
import defpackage.p1;
import defpackage.r1;
import defpackage.u1;
import defpackage.v1;
import defpackage.v2;
import defpackage.w1;
import defpackage.y1;
import defpackage.y7;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuPresenter extends k1 implements b9.a {
    public a A;
    public c B;
    public b C;
    public final f D;
    public int E;
    public d l;
    public Drawable m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public int x;
    public final SparseBooleanArray y;
    public e z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int b;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.b);
        }

        public SavedState(Parcel parcel) {
            this.b = parcel.readInt();
        }
    }

    public class a extends u1 {
        public a(Context context, a2 a2Var, View view) {
            super(context, a2Var, view, false, R$attr.actionOverflowMenuStyle);
            if (!((r1) a2Var.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.l;
                f(view2 == null ? (View) ActionMenuPresenter.this.j : view2);
            }
            j(ActionMenuPresenter.this.D);
        }

        @Override // defpackage.u1
        public void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.A = null;
            actionMenuPresenter.E = 0;
            super.e();
        }
    }

    public class b extends ActionMenuItemView.b {
        public b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public y1 a() {
            a aVar = ActionMenuPresenter.this.A;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    public class c implements Runnable {
        public e b;

        public c(e eVar) {
            this.b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.d != null) {
                ActionMenuPresenter.this.d.d();
            }
            View view = (View) ActionMenuPresenter.this.j;
            if (view != null && view.getWindowToken() != null && this.b.m()) {
                ActionMenuPresenter.this.z = this.b;
            }
            ActionMenuPresenter.this.B = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        public class a extends v2 {
            public final /* synthetic */ ActionMenuPresenter k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.k = actionMenuPresenter;
            }

            @Override // defpackage.v2
            public y1 b() {
                e eVar = ActionMenuPresenter.this.z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // defpackage.v2
            public boolean c() {
                ActionMenuPresenter.this.O();
                return true;
            }

            @Override // defpackage.v2
            public boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.B != null) {
                    return false;
                }
                actionMenuPresenter.F();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            i3.a(this, getContentDescription());
            setOnTouchListener(new a(this, ActionMenuPresenter.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.O();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                y7.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends u1 {
        public e(Context context, p1 p1Var, View view, boolean z) {
            super(context, p1Var, view, z, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.D);
        }

        @Override // defpackage.u1
        public void e() {
            if (ActionMenuPresenter.this.d != null) {
                ActionMenuPresenter.this.d.close();
            }
            ActionMenuPresenter.this.z = null;
            super.e();
        }
    }

    public class f implements v1.a {
        public f() {
        }

        @Override // v1.a
        public void a(p1 p1Var, boolean z) {
            if (p1Var instanceof a2) {
                p1Var.F().e(false);
            }
            v1.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                aVarQ.a(p1Var, z);
            }
        }

        @Override // v1.a
        public boolean b(p1 p1Var) {
            if (p1Var == ActionMenuPresenter.this.d) {
                return false;
            }
            ActionMenuPresenter.this.E = ((a2) p1Var).getItem().getItemId();
            v1.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                return aVarQ.b(p1Var);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.y = new SparseBooleanArray();
        this.D = new f();
    }

    public boolean C() {
        return F() | G();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View D(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof w1.a) && ((w1.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable E() {
        d dVar = this.l;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }

    public boolean F() {
        Object obj;
        c cVar = this.B;
        if (cVar != null && (obj = this.j) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.B = null;
            return true;
        }
        e eVar = this.z;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean G() {
        a aVar = this.A;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean H() {
        return this.B != null || I();
    }

    public boolean I() {
        e eVar = this.z;
        return eVar != null && eVar.d();
    }

    public void J(Configuration configuration) {
        if (!this.t) {
            this.s = a1.b(this.c).d();
        }
        p1 p1Var = this.d;
        if (p1Var != null) {
            p1Var.M(true);
        }
    }

    public void K(boolean z) {
        this.w = z;
    }

    public void L(ActionMenuView actionMenuView) {
        this.j = actionMenuView;
        actionMenuView.b(this.d);
    }

    public void M(Drawable drawable) {
        d dVar = this.l;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.n = true;
            this.m = drawable;
        }
    }

    public void N(boolean z) {
        this.o = z;
        this.p = true;
    }

    public boolean O() {
        p1 p1Var;
        if (!this.o || I() || (p1Var = this.d) == null || this.j == null || this.B != null || p1Var.B().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.c, this.d, this.l, true));
        this.B = cVar;
        ((View) this.j).post(cVar);
        return true;
    }

    @Override // defpackage.k1, defpackage.v1
    public void a(p1 p1Var, boolean z) {
        C();
        super.a(p1Var, z);
    }

    @Override // b9.a
    public void b(boolean z) {
        if (z) {
            super.m(null);
            return;
        }
        p1 p1Var = this.d;
        if (p1Var != null) {
            p1Var.e(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.ActionMenuPresenter] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r15v1, types: [r1] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // defpackage.v1
    public boolean d() {
        ArrayList<r1> arrayListG;
        int size;
        int i;
        int iL;
        ?? r0;
        int i2;
        ActionMenuPresenter actionMenuPresenter = this;
        p1 p1Var = actionMenuPresenter.d;
        View view = null;
        ?? r3 = 0;
        if (p1Var != null) {
            arrayListG = p1Var.G();
            size = arrayListG.size();
        } else {
            arrayListG = null;
            size = 0;
        }
        int i3 = actionMenuPresenter.s;
        int i4 = actionMenuPresenter.r;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.j;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            r1 r1Var = arrayListG.get(i7);
            if (r1Var.requiresActionButton()) {
                i5++;
            } else if (r1Var.n()) {
                i6++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.w && r1Var.isActionViewExpanded()) {
                i3 = 0;
            }
        }
        if (actionMenuPresenter.o && (z || i6 + i5 > i3)) {
            i3--;
        }
        int i8 = i3 - i5;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.y;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.u) {
            int i9 = actionMenuPresenter.x;
            iL = i4 / i9;
            i = i9 + ((i4 % i9) / iL);
        } else {
            i = 0;
            iL = 0;
        }
        int i10 = 0;
        int i11 = 0;
        ?? r02 = actionMenuPresenter;
        while (i10 < size) {
            r1 r1Var2 = arrayListG.get(i10);
            if (r1Var2.requiresActionButton()) {
                View viewR = r02.r(r1Var2, view, viewGroup);
                if (r02.u) {
                    iL -= ActionMenuView.L(viewR, i, iL, iMakeMeasureSpec, r3);
                } else {
                    viewR.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewR.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                int groupId = r1Var2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                r1Var2.t(true);
                r0 = r3;
                i2 = size;
            } else if (r1Var2.n()) {
                int groupId2 = r1Var2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i8 > 0 || z2) && i4 > 0 && (!r02.u || iL > 0);
                boolean z4 = z3;
                i2 = size;
                if (z3) {
                    View viewR2 = r02.r(r1Var2, null, viewGroup);
                    if (r02.u) {
                        int iL2 = ActionMenuView.L(viewR2, i, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z4 = false;
                        }
                    } else {
                        viewR2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z5 = z4;
                    int measuredWidth2 = viewR2.getMeasuredWidth();
                    i4 -= measuredWidth2;
                    if (i11 == 0) {
                        i11 = measuredWidth2;
                    }
                    z3 = z5 & (!r02.u ? i4 + i11 <= 0 : i4 < 0);
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z2) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i12 = 0; i12 < i10; i12++) {
                        r1 r1Var3 = arrayListG.get(i12);
                        if (r1Var3.getGroupId() == groupId2) {
                            if (r1Var3.l()) {
                                i8++;
                            }
                            r1Var3.t(false);
                        }
                    }
                }
                if (z3) {
                    i8--;
                }
                r1Var2.t(z3);
                r0 = 0;
            } else {
                r0 = r3;
                i2 = size;
                r1Var2.t(r0);
            }
            i10++;
            r3 = r0;
            size = i2;
            view = null;
            r02 = this;
        }
        return true;
    }

    @Override // defpackage.v1
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.b = this.E;
        return savedState;
    }

    @Override // defpackage.k1, defpackage.v1
    public void f(Context context, p1 p1Var) {
        super.f(context, p1Var);
        Resources resources = context.getResources();
        a1 a1VarB = a1.b(context);
        if (!this.p) {
            this.o = a1VarB.h();
        }
        if (!this.v) {
            this.q = a1VarB.c();
        }
        if (!this.t) {
            this.s = a1VarB.d();
        }
        int measuredWidth = this.q;
        if (this.o) {
            if (this.l == null) {
                d dVar = new d(this.b);
                this.l = dVar;
                if (this.n) {
                    dVar.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.l.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.l.getMeasuredWidth();
        } else {
            this.l = null;
        }
        this.r = measuredWidth;
        this.x = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i = ((SavedState) parcelable).b) > 0 && (menuItemFindItem = this.d.findItem(i)) != null) {
            m((a2) menuItemFindItem.getSubMenu());
        }
    }

    @Override // defpackage.k1
    public void l(r1 r1Var, w1.a aVar) {
        aVar.e(r1Var, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.j);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    @Override // defpackage.k1, defpackage.v1
    public boolean m(a2 a2Var) {
        boolean z = false;
        if (!a2Var.hasVisibleItems()) {
            return false;
        }
        a2 a2Var2 = a2Var;
        while (a2Var2.i0() != this.d) {
            a2Var2 = (a2) a2Var2.i0();
        }
        View viewD = D(a2Var2.getItem());
        if (viewD == null) {
            return false;
        }
        this.E = a2Var.getItem().getItemId();
        int size = a2Var.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = a2Var.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        a aVar = new a(this.c, a2Var, viewD);
        this.A = aVar;
        aVar.g(z);
        this.A.k();
        super.m(a2Var);
        return true;
    }

    @Override // defpackage.k1, defpackage.v1
    public void n(boolean z) {
        super.n(z);
        ((View) this.j).requestLayout();
        p1 p1Var = this.d;
        boolean z2 = false;
        if (p1Var != null) {
            ArrayList<r1> arrayListU = p1Var.u();
            int size = arrayListU.size();
            for (int i = 0; i < size; i++) {
                b9 b9VarB = arrayListU.get(i).b();
                if (b9VarB != null) {
                    b9VarB.i(this);
                }
            }
        }
        p1 p1Var2 = this.d;
        ArrayList<r1> arrayListB = p1Var2 != null ? p1Var2.B() : null;
        if (this.o && arrayListB != null) {
            int size2 = arrayListB.size();
            if (size2 == 1) {
                z2 = !arrayListB.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.l == null) {
                this.l = new d(this.b);
            }
            ViewGroup viewGroup = (ViewGroup) this.l.getParent();
            if (viewGroup != this.j) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.l);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.j;
                actionMenuView.addView(this.l, actionMenuView.F());
            }
        } else {
            d dVar = this.l;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.j;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.l);
                }
            }
        }
        ((ActionMenuView) this.j).setOverflowReserved(this.o);
    }

    @Override // defpackage.k1
    public boolean p(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.l) {
            return false;
        }
        return super.p(viewGroup, i);
    }

    @Override // defpackage.k1
    public View r(r1 r1Var, View view, ViewGroup viewGroup) {
        View actionView = r1Var.getActionView();
        if (actionView == null || r1Var.j()) {
            actionView = super.r(r1Var, view, viewGroup);
        }
        actionView.setVisibility(r1Var.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // defpackage.k1
    public w1 s(ViewGroup viewGroup) {
        w1 w1Var = this.j;
        w1 w1VarS = super.s(viewGroup);
        if (w1Var != w1VarS) {
            ((ActionMenuView) w1VarS).setPresenter(this);
        }
        return w1VarS;
    }

    @Override // defpackage.k1
    public boolean u(int i, r1 r1Var) {
        return r1Var.l();
    }
}
