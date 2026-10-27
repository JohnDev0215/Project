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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.b;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class ActionMenuPresenter extends androidx.appcompat.view.menu.b implements b.a {
    c A;
    private b B;
    final f C;
    int D;
    d k;
    private Drawable l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private final SparseBooleanArray x;
    e y;
    a z;

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage"})
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f248c;

        /* JADX INFO: compiled from: caradapter-bug */
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
            parcel.writeInt(this.f248c);
        }

        SavedState(Parcel parcel) {
            this.f248c = parcel.readInt();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class a extends androidx.appcompat.view.menu.k {
        public a(Context context, androidx.appcompat.view.menu.q qVar, View view) {
            super(context, qVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.h) qVar.getItem()).k()) {
                View view2 = ActionMenuPresenter.this.k;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).j : view2);
            }
            j(ActionMenuPresenter.this.C);
        }

        @Override // androidx.appcompat.view.menu.k
        protected void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.z = null;
            actionMenuPresenter.D = 0;
            super.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.o a() {
            a aVar = ActionMenuPresenter.this.z;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private e f250c;

        public c(e eVar) {
            this.f250c = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).e != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).e.d();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).j;
            if (view != null && view.getWindowToken() != null && this.f250c.m()) {
                ActionMenuPresenter.this.y = this.f250c;
            }
            ActionMenuPresenter.this.A = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d extends C0068n implements ActionMenuView.a {

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends F {
            a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
            }

            @Override // androidx.appcompat.widget.F
            public androidx.appcompat.view.menu.o b() {
                e eVar = ActionMenuPresenter.this.y;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.F
            public boolean c() {
                ActionMenuPresenter.this.I();
                return true;
            }

            @Override // androidx.appcompat.widget.F
            public boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.A != null) {
                    return false;
                }
                actionMenuPresenter.A();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            W.a(this, getContentDescription());
            setOnTouchListener(new a(this, ActionMenuPresenter.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean g() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.I();
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
                androidx.core.graphics.drawable.a.k(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class e extends androidx.appcompat.view.menu.k {
        public e(Context context, androidx.appcompat.view.menu.f fVar, View view, boolean z) {
            super(context, fVar, view, z, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.C);
        }

        @Override // androidx.appcompat.view.menu.k
        protected void e() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).e != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).e.close();
            }
            ActionMenuPresenter.this.y = null;
            super.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class f implements l.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.l.a
        public void a(@NonNull androidx.appcompat.view.menu.f fVar, boolean z) {
            if (fVar instanceof androidx.appcompat.view.menu.q) {
                fVar.D().e(false);
            }
            l.a aVarM = ActionMenuPresenter.this.m();
            if (aVarM != null) {
                aVarM.a(fVar, z);
            }
        }

        @Override // androidx.appcompat.view.menu.l.a
        public boolean b(@NonNull androidx.appcompat.view.menu.f fVar) {
            if (fVar == ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).e) {
                return false;
            }
            ActionMenuPresenter.this.D = ((androidx.appcompat.view.menu.q) fVar).getItem().getItemId();
            l.a aVarM = ActionMenuPresenter.this.m();
            if (aVarM != null) {
                return aVarM.b(fVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.x = new SparseBooleanArray();
        this.C = new f();
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
            if ((childAt instanceof m.a) && ((m.a) childAt).b() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean A() {
        Object obj;
        c cVar = this.A;
        if (cVar != null && (obj = this.j) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.A = null;
            return true;
        }
        e eVar = this.y;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean B() {
        a aVar = this.z;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean C() {
        return this.A != null || D();
    }

    public boolean D() {
        e eVar = this.y;
        return eVar != null && eVar.d();
    }

    public void E(Configuration configuration) {
        if (!this.s) {
            this.r = androidx.appcompat.c.a.b(this.d).d();
        }
        androidx.appcompat.view.menu.f fVar = this.e;
        if (fVar != null) {
            fVar.K(true);
        }
    }

    public void F(boolean z) {
        this.v = z;
    }

    public void G(ActionMenuView actionMenuView) {
        this.j = actionMenuView;
        actionMenuView.b(this.e);
    }

    public void H(boolean z) {
        this.n = z;
        this.o = true;
    }

    public boolean I() {
        androidx.appcompat.view.menu.f fVar;
        if (!this.n || D() || (fVar = this.e) == null || this.j == null || this.A != null || fVar.z().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.d, this.e, this.k, true));
        this.A = cVar;
        ((View) this.j).post(cVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.l
    public void a(androidx.appcompat.view.menu.f fVar, boolean z) {
        y();
        super.a(fVar, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.ActionMenuPresenter] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r15v1, types: [androidx.appcompat.view.menu.h] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.l
    public boolean c() {
        ArrayList<androidx.appcompat.view.menu.h> arrayListE;
        int size;
        int i;
        int iS;
        ?? r0;
        int i2;
        ActionMenuPresenter actionMenuPresenter = this;
        androidx.appcompat.view.menu.f fVar = actionMenuPresenter.e;
        View view = null;
        ?? r3 = 0;
        if (fVar != null) {
            arrayListE = fVar.E();
            size = arrayListE.size();
        } else {
            arrayListE = null;
            size = 0;
        }
        int i3 = actionMenuPresenter.r;
        int i4 = actionMenuPresenter.q;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.j;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            androidx.appcompat.view.menu.h hVar = arrayListE.get(i7);
            if (hVar.requiresActionButton()) {
                i5++;
            } else if (hVar.m()) {
                i6++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.v && hVar.isActionViewExpanded()) {
                i3 = 0;
            }
        }
        if (actionMenuPresenter.n && (z || i6 + i5 > i3)) {
            i3--;
        }
        int i8 = i3 - i5;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.x;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.t) {
            int i9 = actionMenuPresenter.w;
            iS = i4 / i9;
            i = i9 + ((i4 % i9) / iS);
        } else {
            i = 0;
            iS = 0;
        }
        int i10 = 0;
        int i11 = 0;
        ?? r02 = actionMenuPresenter;
        while (i10 < size) {
            androidx.appcompat.view.menu.h hVar2 = arrayListE.get(i10);
            if (hVar2.requiresActionButton()) {
                View viewN = r02.n(hVar2, view, viewGroup);
                if (r02.t) {
                    iS -= ActionMenuView.S(viewN, i, iS, iMakeMeasureSpec, r3);
                } else {
                    viewN.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewN.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                int groupId = hVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                hVar2.s(true);
                r0 = r3;
                i2 = size;
            } else if (hVar2.m()) {
                int groupId2 = hVar2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i8 > 0 || z2) && i4 > 0 && (!r02.t || iS > 0);
                boolean z4 = z3;
                i2 = size;
                if (z3) {
                    View viewN2 = r02.n(hVar2, null, viewGroup);
                    if (r02.t) {
                        int iS2 = ActionMenuView.S(viewN2, i, iS, iMakeMeasureSpec, 0);
                        iS -= iS2;
                        if (iS2 == 0) {
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
                    z3 = z5 & (!r02.t ? i4 + i11 <= 0 : i4 < 0);
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z2) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i12 = 0; i12 < i10; i12++) {
                        androidx.appcompat.view.menu.h hVar3 = arrayListE.get(i12);
                        if (hVar3.getGroupId() == groupId2) {
                            if (hVar3.k()) {
                                i8++;
                            }
                            hVar3.s(false);
                        }
                    }
                }
                if (z3) {
                    i8--;
                }
                hVar2.s(z3);
                r0 = 0;
            } else {
                r0 = r3;
                i2 = size;
                hVar2.s(r0);
            }
            i10++;
            r3 = r0;
            size = i2;
            view = null;
            r02 = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.l
    public void d(@NonNull Context context, @Nullable androidx.appcompat.view.menu.f fVar) {
        super.d(context, fVar);
        Resources resources = context.getResources();
        androidx.appcompat.c.a aVarB = androidx.appcompat.c.a.b(context);
        if (!this.o) {
            this.n = aVarB.f();
        }
        if (!this.u) {
            this.p = aVarB.c();
        }
        if (!this.s) {
            this.r = aVarB.d();
        }
        int measuredWidth = this.p;
        if (this.n) {
            if (this.k == null) {
                d dVar = new d(this.f215c);
                this.k = dVar;
                if (this.m) {
                    dVar.setImageDrawable(this.l);
                    this.l = null;
                    this.m = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.k.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.k.getMeasuredWidth();
        } else {
            this.k = null;
        }
        this.q = measuredWidth;
        this.w = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.b
    public void f(androidx.appcompat.view.menu.h hVar, m.a aVar) {
        aVar.j(hVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.o((ActionMenuView) this.j);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.p(this.B);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.l
    public boolean i(androidx.appcompat.view.menu.q qVar) {
        boolean z = false;
        if (!qVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.q qVar2 = qVar;
        while (qVar2.e0() != this.e) {
            qVar2 = (androidx.appcompat.view.menu.q) qVar2.e0();
        }
        View viewZ = z(qVar2.getItem());
        if (viewZ == null) {
            return false;
        }
        qVar.getItem().getItemId();
        int size = qVar.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = qVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        a aVar = new a(this.d, qVar, viewZ);
        this.z = aVar;
        aVar.g(z);
        this.z.k();
        super.i(qVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.l
    public void j(boolean z) {
        super.j(z);
        ((View) this.j).requestLayout();
        androidx.appcompat.view.menu.f fVar = this.e;
        boolean z2 = false;
        if (fVar != null) {
            ArrayList<androidx.appcompat.view.menu.h> arrayListS = fVar.s();
            int size = arrayListS.size();
            for (int i = 0; i < size; i++) {
                androidx.core.view.b bVarB = arrayListS.get(i).b();
                if (bVarB != null) {
                    bVarB.k(this);
                }
            }
        }
        androidx.appcompat.view.menu.f fVar2 = this.e;
        ArrayList<androidx.appcompat.view.menu.h> arrayListZ = fVar2 != null ? fVar2.z() : null;
        if (this.n && arrayListZ != null) {
            int size2 = arrayListZ.size();
            if (size2 == 1) {
                z2 = !arrayListZ.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.k == null) {
                this.k = new d(this.f215c);
            }
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            if (viewGroup != this.j) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.j;
                actionMenuView.addView(this.k, actionMenuView.L());
            }
        } else {
            d dVar = this.k;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.j;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.k);
                }
            }
        }
        ((ActionMenuView) this.j).Y(this.n);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean l(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.k) {
            return false;
        }
        return super.l(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b
    public View n(androidx.appcompat.view.menu.h hVar, View view, ViewGroup viewGroup) {
        View actionView = hVar.getActionView();
        if (actionView == null || hVar.i()) {
            actionView = super.n(hVar, view, viewGroup);
        }
        actionView.setVisibility(hVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.m o(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.m mVar = this.j;
        androidx.appcompat.view.menu.m mVarO = super.o(viewGroup);
        if (mVar != mVarO) {
            ((ActionMenuView) mVarO).a0(this);
        }
        return mVarO;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean q(int i, androidx.appcompat.view.menu.h hVar) {
        return hVar.k();
    }

    public boolean y() {
        return B() | A();
    }
}
