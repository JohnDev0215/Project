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
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import b.b.p.j.g;
import b.b.p.j.i;
import b.b.p.j.l;
import b.b.p.j.m;
import b.b.p.j.n;
import b.b.p.j.p;
import b.b.p.j.r;
import b.b.q.g0;
import b.b.q.t;
import b.h.m.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuPresenter extends b.b.p.j.b implements b.a {
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f365b;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f365b);
        }

        public SavedState(Parcel parcel) {
            this.f365b = parcel.readInt();
        }
    }

    public class a extends l {
        public a(Context context, r rVar, View view) {
            super(context, rVar, view, false, b.b.a.actionOverflowMenuStyle);
            if (!((i) rVar.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.l;
                f(view2 == null ? (View) ActionMenuPresenter.this.j : view2);
            }
            j(ActionMenuPresenter.this.D);
        }

        @Override // b.b.p.j.l
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
        public p a() {
            a aVar = ActionMenuPresenter.this.A;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f367b;

        public c(e eVar) {
            this.f367b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.f1590d != null) {
                ActionMenuPresenter.this.f1590d.d();
            }
            View view = (View) ActionMenuPresenter.this.j;
            if (view != null && view.getWindowToken() != null && this.f367b.m()) {
                ActionMenuPresenter.this.z = this.f367b;
            }
            ActionMenuPresenter.this.B = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        public class a extends t {
            public final /* synthetic */ ActionMenuPresenter k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.k = actionMenuPresenter;
            }

            @Override // b.b.q.t
            public p b() {
                e eVar = ActionMenuPresenter.this.z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // b.b.q.t
            public boolean c() {
                ActionMenuPresenter.this.O();
                return true;
            }

            @Override // b.b.q.t
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
            super(context, null, b.b.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            g0.a(this, getContentDescription());
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
        public boolean setFrame(int i2, int i3, int i4, int i5) {
            boolean frame = super.setFrame(i2, i3, i4, i5);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                b.h.f.l.a.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends l {
        public e(Context context, g gVar, View view, boolean z) {
            super(context, gVar, view, z, b.b.a.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.D);
        }

        @Override // b.b.p.j.l
        public void e() {
            if (ActionMenuPresenter.this.f1590d != null) {
                ActionMenuPresenter.this.f1590d.close();
            }
            ActionMenuPresenter.this.z = null;
            super.e();
        }
    }

    public class f implements m.a {
        public f() {
        }

        @Override // b.b.p.j.m.a
        public void a(g gVar, boolean z) {
            if (gVar instanceof r) {
                gVar.F().e(false);
            }
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                aVarQ.a(gVar, z);
            }
        }

        @Override // b.b.p.j.m.a
        public boolean b(g gVar) {
            if (gVar == ActionMenuPresenter.this.f1590d) {
                return false;
            }
            ActionMenuPresenter.this.E = ((r) gVar).getItem().getItemId();
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                return aVarQ.b(gVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, b.b.g.abc_action_menu_layout, b.b.g.abc_action_menu_item_layout);
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
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
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
            this.s = b.b.p.a.b(this.f1589c).d();
        }
        g gVar = this.f1590d;
        if (gVar != null) {
            gVar.M(true);
        }
    }

    public void K(boolean z) {
        this.w = z;
    }

    public void L(ActionMenuView actionMenuView) {
        this.j = actionMenuView;
        actionMenuView.b(this.f1590d);
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
        g gVar;
        if (!this.o || I() || (gVar = this.f1590d) == null || this.j == null || this.B != null || gVar.B().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f1589c, this.f1590d, this.l, true));
        this.B = cVar;
        ((View) this.j).post(cVar);
        return true;
    }

    @Override // b.b.p.j.b, b.b.p.j.m
    public void a(g gVar, boolean z) {
        C();
        super.a(gVar, z);
    }

    @Override // b.h.m.b.a
    public void b(boolean z) {
        if (z) {
            super.m(null);
            return;
        }
        g gVar = this.f1590d;
        if (gVar != null) {
            gVar.e(false);
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
    /* JADX WARN: Type inference failed for: r15v1, types: [b.b.p.j.i] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // b.b.p.j.m
    public boolean d() {
        ArrayList<i> arrayListG;
        int size;
        int i2;
        int iL;
        ?? r0;
        int i3;
        ActionMenuPresenter actionMenuPresenter = this;
        g gVar = actionMenuPresenter.f1590d;
        View view = null;
        ?? r3 = 0;
        if (gVar != null) {
            arrayListG = gVar.G();
            size = arrayListG.size();
        } else {
            arrayListG = null;
            size = 0;
        }
        int i4 = actionMenuPresenter.s;
        int i5 = actionMenuPresenter.r;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.j;
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            i iVar = arrayListG.get(i8);
            if (iVar.requiresActionButton()) {
                i6++;
            } else if (iVar.n()) {
                i7++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.w && iVar.isActionViewExpanded()) {
                i4 = 0;
            }
        }
        if (actionMenuPresenter.o && (z || i7 + i6 > i4)) {
            i4--;
        }
        int i9 = i4 - i6;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.y;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.u) {
            int i10 = actionMenuPresenter.x;
            iL = i5 / i10;
            i2 = i10 + ((i5 % i10) / iL);
        } else {
            i2 = 0;
            iL = 0;
        }
        int i11 = 0;
        int i12 = 0;
        ?? r02 = actionMenuPresenter;
        while (i11 < size) {
            i iVar2 = arrayListG.get(i11);
            if (iVar2.requiresActionButton()) {
                View viewR = r02.r(iVar2, view, viewGroup);
                if (r02.u) {
                    iL -= ActionMenuView.L(viewR, i2, iL, iMakeMeasureSpec, r3);
                } else {
                    viewR.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewR.getMeasuredWidth();
                i5 -= measuredWidth;
                if (i12 == 0) {
                    i12 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.t(true);
                r0 = r3;
                i3 = size;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i9 > 0 || z2) && i5 > 0 && (!r02.u || iL > 0);
                boolean z4 = z3;
                i3 = size;
                if (z3) {
                    View viewR2 = r02.r(iVar2, null, viewGroup);
                    if (r02.u) {
                        int iL2 = ActionMenuView.L(viewR2, i2, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z4 = false;
                        }
                    } else {
                        viewR2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z5 = z4;
                    int measuredWidth2 = viewR2.getMeasuredWidth();
                    i5 -= measuredWidth2;
                    if (i12 == 0) {
                        i12 = measuredWidth2;
                    }
                    z3 = z5 & (!r02.u ? i5 + i12 <= 0 : i5 < 0);
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z2) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i13 = 0; i13 < i11; i13++) {
                        i iVar3 = arrayListG.get(i13);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i9++;
                            }
                            iVar3.t(false);
                        }
                    }
                }
                if (z3) {
                    i9--;
                }
                iVar2.t(z3);
                r0 = 0;
            } else {
                r0 = r3;
                i3 = size;
                iVar2.t(r0);
            }
            i11++;
            r3 = r0;
            size = i3;
            view = null;
            r02 = this;
        }
        return true;
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f365b = this.E;
        return savedState;
    }

    @Override // b.b.p.j.b, b.b.p.j.m
    public void f(Context context, g gVar) {
        super.f(context, gVar);
        Resources resources = context.getResources();
        b.b.p.a aVarB = b.b.p.a.b(context);
        if (!this.p) {
            this.o = aVarB.h();
        }
        if (!this.v) {
            this.q = aVarB.c();
        }
        if (!this.t) {
            this.s = aVarB.d();
        }
        int measuredWidth = this.q;
        if (this.o) {
            if (this.l == null) {
                d dVar = new d(this.f1588b);
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

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
        int i2;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i2 = ((SavedState) parcelable).f365b) > 0 && (menuItemFindItem = this.f1590d.findItem(i2)) != null) {
            m((r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // b.b.p.j.b
    public void l(i iVar, n.a aVar) {
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.j);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    @Override // b.b.p.j.b, b.b.p.j.m
    public boolean m(r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        r rVar2 = rVar;
        while (rVar2.i0() != this.f1590d) {
            rVar2 = (r) rVar2.i0();
        }
        View viewD = D(rVar2.getItem());
        if (viewD == null) {
            return false;
        }
        this.E = rVar.getItem().getItemId();
        int size = rVar.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        a aVar = new a(this.f1589c, rVar, viewD);
        this.A = aVar;
        aVar.g(z);
        this.A.k();
        super.m(rVar);
        return true;
    }

    @Override // b.b.p.j.b, b.b.p.j.m
    public void n(boolean z) {
        super.n(z);
        ((View) this.j).requestLayout();
        g gVar = this.f1590d;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<i> arrayListU = gVar.u();
            int size = arrayListU.size();
            for (int i2 = 0; i2 < size; i2++) {
                b.h.m.b bVarB = arrayListU.get(i2).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        g gVar2 = this.f1590d;
        ArrayList<i> arrayListB = gVar2 != null ? gVar2.B() : null;
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
                this.l = new d(this.f1588b);
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

    @Override // b.b.p.j.b
    public boolean p(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.l) {
            return false;
        }
        return super.p(viewGroup, i2);
    }

    @Override // b.b.p.j.b
    public View r(i iVar, View view, ViewGroup viewGroup) {
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.j()) {
            actionView = super.r(iVar, view, viewGroup);
        }
        actionView.setVisibility(iVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // b.b.p.j.b
    public n s(ViewGroup viewGroup) {
        n nVar = this.j;
        n nVarS = super.s(viewGroup);
        if (nVar != nVarS) {
            ((ActionMenuView) nVarS).setPresenter(this);
        }
        return nVarS;
    }

    @Override // b.b.p.j.b
    public boolean u(int i2, i iVar) {
        return iVar.l();
    }
}
