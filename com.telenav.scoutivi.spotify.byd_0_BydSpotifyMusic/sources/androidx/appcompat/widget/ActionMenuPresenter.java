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
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.k;
import androidx.appcompat.widget.ActionMenuView;
import i0.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class ActionMenuPresenter extends androidx.appcompat.view.menu.a implements b.a {
    private boolean A;
    private int B;
    private final SparseBooleanArray C;
    e D;
    a E;
    c F;
    private b G;
    final f H;
    int I;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    d f803p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Drawable f804q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f805r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f806s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f807t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f808u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f809v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f810w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f811x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f812y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f813z;

    @SuppressLint({"BanParcelableUsage"})
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f814e;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f814e);
        }

        SavedState(Parcel parcel) {
            this.f814e = parcel.readInt();
        }
    }

    private class a extends androidx.appcompat.view.menu.i {
        public a(Context context, androidx.appcompat.view.menu.m mVar, View view) {
            super(context, mVar, view, false, e.a.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.g) mVar.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.f803p;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f613n : view2);
            }
            j(ActionMenuPresenter.this.H);
        }

        @Override // androidx.appcompat.view.menu.i
        protected void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.E = null;
            actionMenuPresenter.I = 0;
            super.e();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public j.e a() {
            a aVar = ActionMenuPresenter.this.E;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    private class c implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private e f817e;

        public c(e eVar) {
            this.f817e = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f607h != null) {
                ((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f607h.d();
            }
            View view = (View) ((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f613n;
            if (view != null && view.getWindowToken() != null && this.f817e.m()) {
                ActionMenuPresenter.this.D = this.f817e;
            }
            ActionMenuPresenter.this.F = null;
        }
    }

    private class d extends m implements ActionMenuView.a {

        class a extends e0 {

            /* JADX INFO: renamed from: o, reason: collision with root package name */
            final /* synthetic */ ActionMenuPresenter f820o;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.f820o = actionMenuPresenter;
            }

            @Override // androidx.appcompat.widget.e0
            public j.e b() {
                e eVar = ActionMenuPresenter.this.D;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.e0
            public boolean c() {
                ActionMenuPresenter.this.K();
                return true;
            }

            @Override // androidx.appcompat.widget.e0
            public boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.F != null) {
                    return false;
                }
                actionMenuPresenter.B();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, e.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            u0.a(this, getContentDescription());
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
            ActionMenuPresenter.this.K();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i7, int i8, int i9, int i10) {
            boolean frame = super.setFrame(i7, i8, i9, i10);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                a0.a.f(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    private class e extends androidx.appcompat.view.menu.i {
        public e(Context context, androidx.appcompat.view.menu.e eVar, View view, boolean z6) {
            super(context, eVar, view, z6, e.a.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.H);
        }

        @Override // androidx.appcompat.view.menu.i
        protected void e() {
            if (((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f607h != null) {
                ((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f607h.close();
            }
            ActionMenuPresenter.this.D = null;
            super.e();
        }
    }

    private class f implements j.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.j.a
        public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
            if (eVar instanceof androidx.appcompat.view.menu.m) {
                eVar.D().e(false);
            }
            j.a aVarM = ActionMenuPresenter.this.m();
            if (aVarM != null) {
                aVarM.a(eVar, z6);
            }
        }

        @Override // androidx.appcompat.view.menu.j.a
        public boolean b(androidx.appcompat.view.menu.e eVar) {
            if (eVar == ((androidx.appcompat.view.menu.a) ActionMenuPresenter.this).f607h) {
                return false;
            }
            ActionMenuPresenter.this.I = ((androidx.appcompat.view.menu.m) eVar).getItem().getItemId();
            j.a aVarM = ActionMenuPresenter.this.m();
            if (aVarM != null) {
                return aVarM.b(eVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, e.g.abc_action_menu_layout, e.g.abc_action_menu_item_layout);
        this.C = new SparseBooleanArray();
        this.H = new f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View z(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f613n;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if ((childAt instanceof k.a) && ((k.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable A() {
        d dVar = this.f803p;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f805r) {
            return this.f804q;
        }
        return null;
    }

    public boolean B() {
        Object obj;
        c cVar = this.F;
        if (cVar != null && (obj = this.f613n) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.F = null;
            return true;
        }
        e eVar = this.D;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean C() {
        a aVar = this.E;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean D() {
        return this.F != null || E();
    }

    public boolean E() {
        e eVar = this.D;
        return eVar != null && eVar.d();
    }

    public void F(Configuration configuration) {
        if (!this.f811x) {
            this.f810w = i.a.b(this.f606g).d();
        }
        androidx.appcompat.view.menu.e eVar = this.f607h;
        if (eVar != null) {
            eVar.K(true);
        }
    }

    public void G(boolean z6) {
        this.A = z6;
    }

    public void H(ActionMenuView actionMenuView) {
        this.f613n = actionMenuView;
        actionMenuView.b(this.f607h);
    }

    public void I(Drawable drawable) {
        d dVar = this.f803p;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.f805r = true;
            this.f804q = drawable;
        }
    }

    public void J(boolean z6) {
        this.f806s = z6;
        this.f807t = true;
    }

    public boolean K() {
        androidx.appcompat.view.menu.e eVar;
        if (!this.f806s || E() || (eVar = this.f607h) == null || this.f613n == null || this.F != null || eVar.z().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f606g, this.f607h, this.f803p, true));
        this.F = cVar;
        ((View) this.f613n).post(cVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.a, androidx.appcompat.view.menu.j
    public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
        y();
        super.a(eVar, z6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.ActionMenuPresenter] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r15v1, types: [androidx.appcompat.view.menu.g] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.j
    public boolean c() {
        ArrayList<androidx.appcompat.view.menu.g> arrayListE;
        int size;
        int i7;
        int iL;
        ?? r02;
        int i8;
        ActionMenuPresenter actionMenuPresenter = this;
        androidx.appcompat.view.menu.e eVar = actionMenuPresenter.f607h;
        View view = null;
        ?? r32 = 0;
        if (eVar != null) {
            arrayListE = eVar.E();
            size = arrayListE.size();
        } else {
            arrayListE = null;
            size = 0;
        }
        int i9 = actionMenuPresenter.f810w;
        int i10 = actionMenuPresenter.f809v;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f613n;
        boolean z6 = false;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < size; i13++) {
            androidx.appcompat.view.menu.g gVar = arrayListE.get(i13);
            if (gVar.o()) {
                i11++;
            } else if (gVar.n()) {
                i12++;
            } else {
                z6 = true;
            }
            if (actionMenuPresenter.A && gVar.isActionViewExpanded()) {
                i9 = 0;
            }
        }
        if (actionMenuPresenter.f806s && (z6 || i12 + i11 > i9)) {
            i9--;
        }
        int i14 = i9 - i11;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.C;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.f812y) {
            int i15 = actionMenuPresenter.B;
            iL = i10 / i15;
            i7 = i15 + ((i10 % i15) / iL);
        } else {
            i7 = 0;
            iL = 0;
        }
        int i16 = 0;
        int i17 = 0;
        ?? r03 = actionMenuPresenter;
        while (i16 < size) {
            androidx.appcompat.view.menu.g gVar2 = arrayListE.get(i16);
            if (gVar2.o()) {
                View viewN = r03.n(gVar2, view, viewGroup);
                if (r03.f812y) {
                    iL -= ActionMenuView.L(viewN, i7, iL, iMakeMeasureSpec, r32);
                } else {
                    viewN.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewN.getMeasuredWidth();
                i10 -= measuredWidth;
                if (i17 == 0) {
                    i17 = measuredWidth;
                }
                int groupId = gVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                gVar2.u(true);
                r02 = r32;
                i8 = size;
            } else if (gVar2.n()) {
                int groupId2 = gVar2.getGroupId();
                boolean z7 = sparseBooleanArray.get(groupId2);
                boolean z8 = (i14 > 0 || z7) && i10 > 0 && (!r03.f812y || iL > 0);
                boolean z9 = z8;
                i8 = size;
                if (z8) {
                    View viewN2 = r03.n(gVar2, null, viewGroup);
                    if (r03.f812y) {
                        int iL2 = ActionMenuView.L(viewN2, i7, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z9 = false;
                        }
                    } else {
                        viewN2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z10 = z9;
                    int measuredWidth2 = viewN2.getMeasuredWidth();
                    i10 -= measuredWidth2;
                    if (i17 == 0) {
                        i17 = measuredWidth2;
                    }
                    z8 = z10 & (!r03.f812y ? i10 + i17 <= 0 : i10 < 0);
                }
                if (z8 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z7) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i18 = 0; i18 < i16; i18++) {
                        androidx.appcompat.view.menu.g gVar3 = arrayListE.get(i18);
                        if (gVar3.getGroupId() == groupId2) {
                            if (gVar3.l()) {
                                i14++;
                            }
                            gVar3.u(false);
                        }
                    }
                }
                if (z8) {
                    i14--;
                }
                gVar2.u(z8);
                r02 = 0;
            } else {
                r02 = r32;
                i8 = size;
                gVar2.u(r02);
            }
            i16++;
            r32 = r02;
            size = i8;
            view = null;
            r03 = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.a, androidx.appcompat.view.menu.j
    public void d(Context context, androidx.appcompat.view.menu.e eVar) {
        super.d(context, eVar);
        Resources resources = context.getResources();
        i.a aVarB = i.a.b(context);
        if (!this.f807t) {
            this.f806s = aVarB.h();
        }
        if (!this.f813z) {
            this.f808u = aVarB.c();
        }
        if (!this.f811x) {
            this.f810w = aVarB.d();
        }
        int measuredWidth = this.f808u;
        if (this.f806s) {
            if (this.f803p == null) {
                d dVar = new d(this.f605e);
                this.f803p = dVar;
                if (this.f805r) {
                    dVar.setImageDrawable(this.f804q);
                    this.f804q = null;
                    this.f805r = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f803p.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f803p.getMeasuredWidth();
        } else {
            this.f803p = null;
        }
        this.f809v = measuredWidth;
        this.B = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.a
    public void g(androidx.appcompat.view.menu.g gVar, k.a aVar) {
        aVar.e(gVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f613n);
        if (this.G == null) {
            this.G = new b();
        }
        actionMenuItemView.setPopupCallback(this.G);
    }

    @Override // androidx.appcompat.view.menu.a, androidx.appcompat.view.menu.j
    public boolean i(androidx.appcompat.view.menu.m mVar) {
        boolean z6 = false;
        if (!mVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.m mVar2 = mVar;
        while (mVar2.e0() != this.f607h) {
            mVar2 = (androidx.appcompat.view.menu.m) mVar2.e0();
        }
        View viewZ = z(mVar2.getItem());
        if (viewZ == null) {
            return false;
        }
        this.I = mVar.getItem().getItemId();
        int size = mVar.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                break;
            }
            MenuItem item = mVar.getItem(i7);
            if (item.isVisible() && item.getIcon() != null) {
                z6 = true;
                break;
            }
            i7++;
        }
        a aVar = new a(this.f606g, mVar, viewZ);
        this.E = aVar;
        aVar.g(z6);
        this.E.k();
        super.i(mVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.a, androidx.appcompat.view.menu.j
    public void j(boolean z6) {
        super.j(z6);
        ((View) this.f613n).requestLayout();
        androidx.appcompat.view.menu.e eVar = this.f607h;
        boolean z7 = false;
        if (eVar != null) {
            ArrayList<androidx.appcompat.view.menu.g> arrayListS = eVar.s();
            int size = arrayListS.size();
            for (int i7 = 0; i7 < size; i7++) {
                i0.b bVarB = arrayListS.get(i7).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        androidx.appcompat.view.menu.e eVar2 = this.f607h;
        ArrayList<androidx.appcompat.view.menu.g> arrayListZ = eVar2 != null ? eVar2.z() : null;
        if (this.f806s && arrayListZ != null) {
            int size2 = arrayListZ.size();
            if (size2 == 1) {
                z7 = !arrayListZ.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z7 = true;
            }
        }
        if (z7) {
            if (this.f803p == null) {
                this.f803p = new d(this.f605e);
            }
            ViewGroup viewGroup = (ViewGroup) this.f803p.getParent();
            if (viewGroup != this.f613n) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f803p);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f613n;
                actionMenuView.addView(this.f803p, actionMenuView.F());
            }
        } else {
            d dVar = this.f803p;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.f613n;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f803p);
                }
            }
        }
        ((ActionMenuView) this.f613n).setOverflowReserved(this.f806s);
    }

    @Override // androidx.appcompat.view.menu.a
    public boolean l(ViewGroup viewGroup, int i7) {
        if (viewGroup.getChildAt(i7) == this.f803p) {
            return false;
        }
        return super.l(viewGroup, i7);
    }

    @Override // androidx.appcompat.view.menu.a
    public View n(androidx.appcompat.view.menu.g gVar, View view, ViewGroup viewGroup) {
        View actionView = gVar.getActionView();
        if (actionView == null || gVar.j()) {
            actionView = super.n(gVar, view, viewGroup);
        }
        actionView.setVisibility(gVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.a
    public androidx.appcompat.view.menu.k o(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.k kVar = this.f613n;
        androidx.appcompat.view.menu.k kVarO = super.o(viewGroup);
        if (kVar != kVarO) {
            ((ActionMenuView) kVarO).setPresenter(this);
        }
        return kVarO;
    }

    @Override // androidx.appcompat.view.menu.a
    public boolean q(int i7, androidx.appcompat.view.menu.g gVar) {
        return gVar.l();
    }

    public boolean y() {
        return B() | C();
    }
}
