package androidx.appcompat.widget;

import a.g.k.b;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class c extends androidx.appcompat.view.menu.b implements b.a {
    a A;
    RunnableC0033c B;
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

    private class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, a.a.a.l);
            if (!((androidx.appcompat.view.menu.i) rVar.getItem()).l()) {
                View view2 = c.this.l;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.b) c.this).j : view2);
            }
            j(c.this.D);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            c cVar = c.this;
            cVar.A = null;
            cVar.E = 0;
            super.e();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = c.this.A;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.c$c, reason: collision with other inner class name */
    private class RunnableC0033c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f912b;

        public RunnableC0033c(e eVar) {
            this.f912b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) c.this).f788d != null) {
                ((androidx.appcompat.view.menu.b) c.this).f788d.d();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) c.this).j;
            if (view != null && view.getWindowToken() != null && this.f912b.m()) {
                c.this.z = this.f912b;
            }
            c.this.B = null;
        }
    }

    private class d extends n implements ActionMenuView.a {

        class a extends g0 {
            final /* synthetic */ c k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(View view, c cVar) {
                super(view);
                this.k = cVar;
            }

            @Override // androidx.appcompat.widget.g0
            public androidx.appcompat.view.menu.p b() {
                e eVar = c.this.z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.g0
            public boolean c() {
                c.this.K();
                return true;
            }

            @Override // androidx.appcompat.widget.g0
            public boolean d() {
                c cVar = c.this;
                if (cVar.B != null) {
                    return false;
                }
                cVar.B();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, a.a.a.k);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            x0.a(this, getContentDescription());
            setOnTouchListener(new a(this, c.this));
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
            c.this.K();
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
            super(context, gVar, view, z, a.a.a.l);
            h(8388613);
            j(c.this.D);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            if (((androidx.appcompat.view.menu.b) c.this).f788d != null) {
                ((androidx.appcompat.view.menu.b) c.this).f788d.close();
            }
            c.this.z = null;
            super.e();
        }
    }

    private class f implements m.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.D().e(false);
            }
            m.a aVarM = c.this.m();
            if (aVarM != null) {
                aVarM.a(gVar, z);
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            if (gVar == ((androidx.appcompat.view.menu.b) c.this).f788d) {
                return false;
            }
            c.this.E = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            m.a aVarM = c.this.m();
            if (aVarM != null) {
                return aVarM.b(gVar);
            }
            return false;
        }
    }

    public c(Context context) {
        super(context, a.a.g.f23c, a.a.g.f22b);
        this.y = new SparseBooleanArray();
        this.D = new f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View z(MenuItem menuItem) {
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

    public Drawable A() {
        d dVar = this.l;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }

    public boolean B() {
        Object obj;
        RunnableC0033c runnableC0033c = this.B;
        if (runnableC0033c != null && (obj = this.j) != null) {
            ((View) obj).removeCallbacks(runnableC0033c);
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

    public boolean C() {
        a aVar = this.A;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean D() {
        return this.B != null || E();
    }

    public boolean E() {
        e eVar = this.z;
        return eVar != null && eVar.d();
    }

    public void F(Configuration configuration) {
        if (!this.t) {
            this.s = a.a.n.a.b(this.f787c).d();
        }
        androidx.appcompat.view.menu.g gVar = this.f788d;
        if (gVar != null) {
            gVar.K(true);
        }
    }

    public void G(boolean z) {
        this.w = z;
    }

    public void H(ActionMenuView actionMenuView) {
        this.j = actionMenuView;
        actionMenuView.b(this.f788d);
    }

    public void I(Drawable drawable) {
        d dVar = this.l;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.n = true;
            this.m = drawable;
        }
    }

    public void J(boolean z) {
        this.o = z;
        this.p = true;
    }

    public boolean K() {
        androidx.appcompat.view.menu.g gVar;
        if (!this.o || E() || (gVar = this.f788d) == null || this.j == null || this.B != null || gVar.z().isEmpty()) {
            return false;
        }
        RunnableC0033c runnableC0033c = new RunnableC0033c(new e(this.f787c, this.f788d, this.l, true));
        this.B = runnableC0033c;
        ((View) this.j).post(runnableC0033c);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        y();
        super.a(gVar, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.c] */
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
    public boolean c() {
        ArrayList<androidx.appcompat.view.menu.i> arrayListE;
        int size;
        int i;
        int iL;
        ?? r0;
        int i2;
        c cVar = this;
        androidx.appcompat.view.menu.g gVar = cVar.f788d;
        View view = null;
        ?? r3 = 0;
        if (gVar != null) {
            arrayListE = gVar.E();
            size = arrayListE.size();
        } else {
            arrayListE = null;
            size = 0;
        }
        int i3 = cVar.s;
        int i4 = cVar.r;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) cVar.j;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            androidx.appcompat.view.menu.i iVar = arrayListE.get(i7);
            if (iVar.requiresActionButton()) {
                i5++;
            } else if (iVar.n()) {
                i6++;
            } else {
                z = true;
            }
            if (cVar.w && iVar.isActionViewExpanded()) {
                i3 = 0;
            }
        }
        if (cVar.o && (z || i6 + i5 > i3)) {
            i3--;
        }
        int i8 = i3 - i5;
        SparseBooleanArray sparseBooleanArray = cVar.y;
        sparseBooleanArray.clear();
        if (cVar.u) {
            int i9 = cVar.x;
            iL = i4 / i9;
            i = i9 + ((i4 % i9) / iL);
        } else {
            i = 0;
            iL = 0;
        }
        int i10 = 0;
        int i11 = 0;
        ?? r02 = cVar;
        while (i10 < size) {
            androidx.appcompat.view.menu.i iVar2 = arrayListE.get(i10);
            if (iVar2.requiresActionButton()) {
                View viewN = r02.n(iVar2, view, viewGroup);
                if (r02.u) {
                    iL -= ActionMenuView.L(viewN, i, iL, iMakeMeasureSpec, r3);
                } else {
                    viewN.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewN.getMeasuredWidth();
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
                    View viewN2 = r02.n(iVar2, null, viewGroup);
                    if (r02.u) {
                        int iL2 = ActionMenuView.L(viewN2, i, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z4 = false;
                        }
                    } else {
                        viewN2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z5 = z4;
                    int measuredWidth2 = viewN2.getMeasuredWidth();
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
                        androidx.appcompat.view.menu.i iVar3 = arrayListE.get(i12);
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

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void d(Context context, androidx.appcompat.view.menu.g gVar) {
        super.d(context, gVar);
        Resources resources = context.getResources();
        a.a.n.a aVarB = a.a.n.a.b(context);
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
                d dVar = new d(this.f786b);
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

    @Override // androidx.appcompat.view.menu.b
    public void g(androidx.appcompat.view.menu.i iVar, n.a aVar) {
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.j);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean i(androidx.appcompat.view.menu.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.r rVar2 = rVar;
        while (rVar2.e0() != this.f788d) {
            rVar2 = (androidx.appcompat.view.menu.r) rVar2.e0();
        }
        View viewZ = z(rVar2.getItem());
        if (viewZ == null) {
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
        a aVar = new a(this.f787c, rVar, viewZ);
        this.A = aVar;
        aVar.g(z);
        this.A.k();
        super.i(rVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void j(boolean z) {
        super.j(z);
        ((View) this.j).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.f788d;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.i> arrayListS = gVar.s();
            int size = arrayListS.size();
            for (int i = 0; i < size; i++) {
                a.g.k.b bVarB = arrayListS.get(i).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.f788d;
        ArrayList<androidx.appcompat.view.menu.i> arrayListZ = gVar2 != null ? gVar2.z() : null;
        if (this.o && arrayListZ != null) {
            int size2 = arrayListZ.size();
            if (size2 == 1) {
                z2 = !arrayListZ.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        d dVar = this.l;
        if (z2) {
            if (dVar == null) {
                this.l = new d(this.f786b);
            }
            ViewGroup viewGroup = (ViewGroup) this.l.getParent();
            if (viewGroup != this.j) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.l);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.j;
                actionMenuView.addView(this.l, actionMenuView.F());
            }
        } else if (dVar != null) {
            Object parent = dVar.getParent();
            Object obj = this.j;
            if (parent == obj) {
                ((ViewGroup) obj).removeView(this.l);
            }
        }
        ((ActionMenuView) this.j).setOverflowReserved(this.o);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean l(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.l) {
            return false;
        }
        return super.l(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b
    public View n(androidx.appcompat.view.menu.i iVar, View view, ViewGroup viewGroup) {
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.j()) {
            actionView = super.n(iVar, view, viewGroup);
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
    public androidx.appcompat.view.menu.n o(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.j;
        androidx.appcompat.view.menu.n nVarO = super.o(viewGroup);
        if (nVar != nVarO) {
            ((ActionMenuView) nVarO).setPresenter(this);
        }
        return nVarO;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean q(int i, androidx.appcompat.view.menu.i iVar) {
        return iVar.l();
    }

    public boolean y() {
        return B() | C();
    }
}
