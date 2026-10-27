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
import java.util.ArrayList;
import o0.b;
import q.g;
import q.i;
import q.l;
import q.m;
import q.n;
import q.p;
import q.r;
import r.g0;
import r.t;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuPresenter extends q.b implements b.a {
    public final SparseBooleanArray A;
    public e B;
    public a C;
    public c D;
    public b E;
    public final f F;
    public int G;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d f476n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f477o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f478p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f479q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f480r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f481s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f482t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f483u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f484v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f485w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f486x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f487y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f488z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f489d;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f489d);
        }

        public SavedState(Parcel parcel) {
            this.f489d = parcel.readInt();
        }
    }

    public class a extends l {
        public a(Context context, r rVar, View view) {
            super(context, rVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((i) rVar.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.f476n;
                f(view2 == null ? (View) ActionMenuPresenter.this.f7075l : view2);
            }
            j(ActionMenuPresenter.this.F);
        }

        @Override // q.l
        public void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.C = null;
            actionMenuPresenter.G = 0;
            super.e();
        }
    }

    public class b extends ActionMenuItemView.b {
        public b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public p a() {
            a aVar = ActionMenuPresenter.this.C;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e f492d;

        public c(e eVar) {
            this.f492d = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.f7069f != null) {
                ActionMenuPresenter.this.f7069f.d();
            }
            View view = (View) ActionMenuPresenter.this.f7075l;
            if (view != null && view.getWindowToken() != null && this.f492d.m()) {
                ActionMenuPresenter.this.B = this.f492d;
            }
            ActionMenuPresenter.this.D = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        public class a extends t {

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ ActionMenuPresenter f495m;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.f495m = actionMenuPresenter;
            }

            @Override // r.t
            public p b() {
                e eVar = ActionMenuPresenter.this.B;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // r.t
            public boolean c() {
                ActionMenuPresenter.this.O();
                return true;
            }

            @Override // r.t
            public boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.D != null) {
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
        public boolean setFrame(int i8, int i9, int i10, int i11) {
            boolean frame = super.setFrame(i8, i9, i10, i11);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                h0.a.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends l {
        public e(Context context, g gVar, View view, boolean z7) {
            super(context, gVar, view, z7, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.F);
        }

        @Override // q.l
        public void e() {
            if (ActionMenuPresenter.this.f7069f != null) {
                ActionMenuPresenter.this.f7069f.close();
            }
            ActionMenuPresenter.this.B = null;
            super.e();
        }
    }

    public class f implements m.a {
        public f() {
        }

        @Override // q.m.a
        public void a(g gVar, boolean z7) {
            if (gVar instanceof r) {
                gVar.F().e(false);
            }
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                aVarQ.a(gVar, z7);
            }
        }

        @Override // q.m.a
        public boolean b(g gVar) {
            if (gVar == ActionMenuPresenter.this.f7069f) {
                return false;
            }
            ActionMenuPresenter.this.G = ((r) gVar).getItem().getItemId();
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                return aVarQ.b(gVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.A = new SparseBooleanArray();
        this.F = new f();
    }

    public boolean C() {
        return F() | G();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View D(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f7075l;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable E() {
        d dVar = this.f476n;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f478p) {
            return this.f477o;
        }
        return null;
    }

    public boolean F() {
        Object obj;
        c cVar = this.D;
        if (cVar != null && (obj = this.f7075l) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.D = null;
            return true;
        }
        e eVar = this.B;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean G() {
        a aVar = this.C;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean H() {
        return this.D != null || I();
    }

    public boolean I() {
        e eVar = this.B;
        return eVar != null && eVar.d();
    }

    public void J(Configuration configuration) {
        if (!this.f484v) {
            this.f483u = p.a.b(this.f7068e).d();
        }
        g gVar = this.f7069f;
        if (gVar != null) {
            gVar.M(true);
        }
    }

    public void K(boolean z7) {
        this.f487y = z7;
    }

    public void L(ActionMenuView actionMenuView) {
        this.f7075l = actionMenuView;
        actionMenuView.b(this.f7069f);
    }

    public void M(Drawable drawable) {
        d dVar = this.f476n;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.f478p = true;
            this.f477o = drawable;
        }
    }

    public void N(boolean z7) {
        this.f479q = z7;
        this.f480r = true;
    }

    public boolean O() {
        g gVar;
        if (!this.f479q || I() || (gVar = this.f7069f) == null || this.f7075l == null || this.D != null || gVar.B().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f7068e, this.f7069f, this.f476n, true));
        this.D = cVar;
        ((View) this.f7075l).post(cVar);
        return true;
    }

    @Override // q.b, q.m
    public void a(g gVar, boolean z7) {
        C();
        super.a(gVar, z7);
    }

    @Override // q.b, q.m
    public void b(Context context, g gVar) {
        super.b(context, gVar);
        Resources resources = context.getResources();
        p.a aVarB = p.a.b(context);
        if (!this.f480r) {
            this.f479q = aVarB.h();
        }
        if (!this.f486x) {
            this.f481s = aVarB.c();
        }
        if (!this.f484v) {
            this.f483u = aVarB.d();
        }
        int measuredWidth = this.f481s;
        if (this.f479q) {
            if (this.f476n == null) {
                d dVar = new d(this.f7067d);
                this.f476n = dVar;
                if (this.f478p) {
                    dVar.setImageDrawable(this.f477o);
                    this.f477o = null;
                    this.f478p = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f476n.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f476n.getMeasuredWidth();
        } else {
            this.f476n = null;
        }
        this.f482t = measuredWidth;
        this.f488z = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // o0.b.a
    public void c(boolean z7) {
        if (z7) {
            super.f(null);
            return;
        }
        g gVar = this.f7069f;
        if (gVar != null) {
            gVar.e(false);
        }
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
        int i8;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i8 = ((SavedState) parcelable).f489d) > 0 && (menuItemFindItem = this.f7069f.findItem(i8)) != null) {
            f((r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // q.b, q.m
    public boolean f(r rVar) {
        boolean z7 = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        r rVar2 = rVar;
        while (rVar2.i0() != this.f7069f) {
            rVar2 = (r) rVar2.i0();
        }
        View viewD = D(rVar2.getItem());
        if (viewD == null) {
            return false;
        }
        this.G = rVar.getItem().getItemId();
        int size = rVar.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i8);
            if (item.isVisible() && item.getIcon() != null) {
                z7 = true;
                break;
            }
            i8++;
        }
        a aVar = new a(this.f7068e, rVar, viewD);
        this.C = aVar;
        aVar.g(z7);
        this.C.k();
        super.f(rVar);
        return true;
    }

    @Override // q.b, q.m
    public void g(boolean z7) {
        super.g(z7);
        ((View) this.f7075l).requestLayout();
        g gVar = this.f7069f;
        boolean z8 = false;
        if (gVar != null) {
            ArrayList<i> arrayListU = gVar.u();
            int size = arrayListU.size();
            for (int i8 = 0; i8 < size; i8++) {
                o0.b bVarB = arrayListU.get(i8).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        g gVar2 = this.f7069f;
        ArrayList<i> arrayListB = gVar2 != null ? gVar2.B() : null;
        if (this.f479q && arrayListB != null) {
            int size2 = arrayListB.size();
            if (size2 == 1) {
                z8 = !arrayListB.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z8 = true;
            }
        }
        if (z8) {
            if (this.f476n == null) {
                this.f476n = new d(this.f7067d);
            }
            ViewGroup viewGroup = (ViewGroup) this.f476n.getParent();
            if (viewGroup != this.f7075l) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f476n);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f7075l;
                actionMenuView.addView(this.f476n, actionMenuView.F());
            }
        } else {
            d dVar = this.f476n;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.f7075l;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f476n);
                }
            }
        }
        ((ActionMenuView) this.f7075l).setOverflowReserved(this.f479q);
    }

    @Override // q.b
    public void h(i iVar, n.a aVar) {
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f7075l);
        if (this.E == null) {
            this.E = new b();
        }
        actionMenuItemView.setPopupCallback(this.E);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.ActionMenuPresenter] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r15v1, types: [q.i] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // q.m
    public boolean j() {
        ArrayList<i> arrayListG;
        int size;
        int i8;
        int iL;
        ?? r02;
        int i9;
        ActionMenuPresenter actionMenuPresenter = this;
        g gVar = actionMenuPresenter.f7069f;
        View view = null;
        ?? r32 = 0;
        if (gVar != null) {
            arrayListG = gVar.G();
            size = arrayListG.size();
        } else {
            arrayListG = null;
            size = 0;
        }
        int i10 = actionMenuPresenter.f483u;
        int i11 = actionMenuPresenter.f482t;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f7075l;
        boolean z7 = false;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < size; i14++) {
            i iVar = arrayListG.get(i14);
            if (iVar.requiresActionButton()) {
                i12++;
            } else if (iVar.n()) {
                i13++;
            } else {
                z7 = true;
            }
            if (actionMenuPresenter.f487y && iVar.isActionViewExpanded()) {
                i10 = 0;
            }
        }
        if (actionMenuPresenter.f479q && (z7 || i13 + i12 > i10)) {
            i10--;
        }
        int i15 = i10 - i12;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.A;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.f485w) {
            int i16 = actionMenuPresenter.f488z;
            iL = i11 / i16;
            i8 = i16 + ((i11 % i16) / iL);
        } else {
            i8 = 0;
            iL = 0;
        }
        int i17 = 0;
        int i18 = 0;
        ?? r03 = actionMenuPresenter;
        while (i17 < size) {
            i iVar2 = arrayListG.get(i17);
            if (iVar2.requiresActionButton()) {
                View viewR = r03.r(iVar2, view, viewGroup);
                if (r03.f485w) {
                    iL -= ActionMenuView.L(viewR, i8, iL, iMakeMeasureSpec, r32);
                } else {
                    viewR.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewR.getMeasuredWidth();
                i11 -= measuredWidth;
                if (i18 == 0) {
                    i18 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.t(true);
                r02 = r32;
                i9 = size;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z8 = sparseBooleanArray.get(groupId2);
                boolean z9 = (i15 > 0 || z8) && i11 > 0 && (!r03.f485w || iL > 0);
                boolean z10 = z9;
                i9 = size;
                if (z9) {
                    View viewR2 = r03.r(iVar2, null, viewGroup);
                    if (r03.f485w) {
                        int iL2 = ActionMenuView.L(viewR2, i8, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z10 = false;
                        }
                    } else {
                        viewR2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z11 = z10;
                    int measuredWidth2 = viewR2.getMeasuredWidth();
                    i11 -= measuredWidth2;
                    if (i18 == 0) {
                        i18 = measuredWidth2;
                    }
                    z9 = z11 & (!r03.f485w ? i11 + i18 <= 0 : i11 < 0);
                }
                if (z9 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z8) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i19 = 0; i19 < i17; i19++) {
                        i iVar3 = arrayListG.get(i19);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i15++;
                            }
                            iVar3.t(false);
                        }
                    }
                }
                if (z9) {
                    i15--;
                }
                iVar2.t(z9);
                r02 = 0;
            } else {
                r02 = r32;
                i9 = size;
                iVar2.t(r02);
            }
            i17++;
            r32 = r02;
            size = i9;
            view = null;
            r03 = this;
        }
        return true;
    }

    @Override // q.m
    public Parcelable k() {
        SavedState savedState = new SavedState();
        savedState.f489d = this.G;
        return savedState;
    }

    @Override // q.b
    public boolean p(ViewGroup viewGroup, int i8) {
        if (viewGroup.getChildAt(i8) == this.f476n) {
            return false;
        }
        return super.p(viewGroup, i8);
    }

    @Override // q.b
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

    @Override // q.b
    public n s(ViewGroup viewGroup) {
        n nVar = this.f7075l;
        n nVarS = super.s(viewGroup);
        if (nVar != nVarS) {
            ((ActionMenuView) nVarS).setPresenter(this);
        }
        return nVarS;
    }

    @Override // q.b
    public boolean u(int i8, i iVar) {
        return iVar.l();
    }
}
