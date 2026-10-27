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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import b.g.k.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class ActionMenuPresenter extends androidx.appcompat.view.menu.b implements b.a {
    a A;
    c B;
    private b C;
    final f D;
    int E;
    d l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y;
    e z;

    @SuppressLint({"BanParcelableUsage"})
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f268b;

        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

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

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f268b);
        }

        SavedState(Parcel parcel) {
            this.f268b = parcel.readInt();
        }
    }

    private class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, b.a.a.m);
            if (!((androidx.appcompat.view.menu.i) rVar.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.l;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).j : view2);
            }
            j(ActionMenuPresenter.this.D);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.A = null;
            actionMenuPresenter.E = 0;
            super.e();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = ActionMenuPresenter.this.A;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    private class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f270b;

        public c(e eVar) {
            this.f270b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f204d != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f204d.d();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).j;
            if (view != null && view.getWindowToken() != null && this.f270b.m()) {
                ActionMenuPresenter.this.z = this.f270b;
            }
            ActionMenuPresenter.this.B = null;
        }
    }

    private class d extends AppCompatImageView implements ActionMenuView.a {

        class a extends t {
            final /* synthetic */ ActionMenuPresenter k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.k = actionMenuPresenter;
            }

            @Override // androidx.appcompat.widget.t
            public androidx.appcompat.view.menu.p b() {
                e eVar = ActionMenuPresenter.this.z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.t
            public boolean c() {
                ActionMenuPresenter.this.O();
                return true;
            }

            @Override // androidx.appcompat.widget.t
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
            super(context, null, b.a.a.l);
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
        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                androidx.core.graphics.drawable.a.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    private class e extends androidx.appcompat.view.menu.l {
        public e(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z) {
            super(context, gVar, view, z, b.a.a.m);
            h(8388613);
            j(ActionMenuPresenter.this.D);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f204d != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f204d.close();
            }
            ActionMenuPresenter.this.z = null;
            super.e();
        }
    }

    private class f implements m.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.F().e(false);
            }
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                aVarQ.a(gVar, z);
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            if (gVar == ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f204d) {
                return false;
            }
            ActionMenuPresenter.this.E = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                return aVarQ.b(gVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, b.a.g.f1421c, b.a.g.f1420b);
        this.y = new SparseBooleanArray();
        this.D = new f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View D(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.j;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean C() {
        return G() | F();
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
            this.s = b.a.o.a.b(this.f203c).d();
        }
        androidx.appcompat.view.menu.g gVar = this.f204d;
        if (gVar != null) {
            gVar.M(true);
        }
    }

    public void K(boolean z) {
        this.w = z;
    }

    public void L(ActionMenuView actionMenuView) {
        this.j = actionMenuView;
        actionMenuView.b(this.f204d);
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
        androidx.appcompat.view.menu.g gVar;
        if (!this.o || I() || (gVar = this.f204d) == null || this.j == null || this.B != null || gVar.B().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f203c, this.f204d, this.l, true));
        this.B = cVar;
        ((View) this.j).post(cVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        C();
        super.a(gVar, z);
    }

    @Override // b.g.k.b.a
    public void b(boolean z) {
        if (z) {
            super.m(null);
            return;
        }
        androidx.appcompat.view.menu.g gVar = this.f204d;
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
    /* JADX WARN: Type inference failed for: r15v1, types: [androidx.appcompat.view.menu.i] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        ArrayList<androidx.appcompat.view.menu.i> arrayListG;
        int size;
        int i;
        int iL;
        ?? r0;
        int i2;
        ActionMenuPresenter actionMenuPresenter = this;
        androidx.appcompat.view.menu.g gVar = actionMenuPresenter.f204d;
        View view = null;
        ?? r3 = 0;
        if (gVar != null) {
            arrayListG = gVar.G();
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
            androidx.appcompat.view.menu.i iVar = arrayListG.get(i7);
            if (iVar.requiresActionButton()) {
                i5++;
            } else if (iVar.n()) {
                i6++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.w && iVar.isActionViewExpanded()) {
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
            androidx.appcompat.view.menu.i iVar2 = arrayListG.get(i10);
            if (iVar2.requiresActionButton()) {
                View viewR = r02.r(iVar2, view, viewGroup);
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
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.t(true);
                r0 = r3;
                i2 = size;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i8 > 0 || z2) && i4 > 0 && (!r02.u || iL > 0);
                boolean z4 = z3;
                i2 = size;
                if (z3) {
                    View viewR2 = r02.r(iVar2, null, viewGroup);
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
                        androidx.appcompat.view.menu.i iVar3 = arrayListG.get(i12);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i8++;
                            }
                            iVar3.t(false);
                        }
                    }
                }
                if (z3) {
                    i8--;
                }
                iVar2.t(z3);
                r0 = 0;
            } else {
                r0 = r3;
                i2 = size;
                iVar2.t(r0);
            }
            i10++;
            r3 = r0;
            size = i2;
            view = null;
            r02 = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f268b = this.E;
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void f(Context context, androidx.appcompat.view.menu.g gVar) {
        super.f(context, gVar);
        Resources resources = context.getResources();
        b.a.o.a aVarB = b.a.o.a.b(context);
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
                d dVar = new d(this.f202b);
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

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i = ((SavedState) parcelable).f268b) > 0 && (menuItemFindItem = this.f204d.findItem(i)) != null) {
            m((androidx.appcompat.view.menu.r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.b
    public void l(androidx.appcompat.view.menu.i iVar, n.a aVar) {
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.j);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean m(androidx.appcompat.view.menu.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.r rVar2 = rVar;
        while (rVar2.i0() != this.f204d) {
            rVar2 = (androidx.appcompat.view.menu.r) rVar2.i0();
        }
        View viewD = D(rVar2.getItem());
        if (viewD == null) {
            return false;
        }
        this.E = rVar.getItem().getItemId();
        int size = rVar.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        a aVar = new a(this.f203c, rVar, viewD);
        this.A = aVar;
        aVar.g(z);
        this.A.k();
        super.m(rVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void n(boolean z) {
        super.n(z);
        ((View) this.j).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.f204d;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.i> arrayListU = gVar.u();
            int size = arrayListU.size();
            for (int i = 0; i < size; i++) {
                b.g.k.b bVarB = arrayListU.get(i).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.f204d;
        ArrayList<androidx.appcompat.view.menu.i> arrayListB = gVar2 != null ? gVar2.B() : null;
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
                this.l = new d(this.f202b);
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

    @Override // androidx.appcompat.view.menu.b
    public boolean p(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.l) {
            return false;
        }
        return super.p(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b
    public View r(androidx.appcompat.view.menu.i iVar, View view, ViewGroup viewGroup) {
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

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.n s(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.j;
        androidx.appcompat.view.menu.n nVarS = super.s(viewGroup);
        if (nVar != nVarS) {
            ((ActionMenuView) nVarS).setPresenter(this);
        }
        return nVarS;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean u(int i, androidx.appcompat.view.menu.i iVar) {
        return iVar.l();
    }
}
