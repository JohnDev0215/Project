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
import l0.b;
import n.g;
import n.i;
import n.l;
import n.m;
import n.n;
import n.p;
import n.r;
import o.g0;
import o.t;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuPresenter extends n.b implements b.a {
    public a A;
    public c B;
    public b C;
    public final f D;
    public int E;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d f371l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f372m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f373n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f374o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f375p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f376q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f377r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f378s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f379t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f380u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f381v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f382w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f383x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final SparseBooleanArray f384y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public e f385z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f386b;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeInt(this.f386b);
        }

        public SavedState(Parcel parcel) {
            this.f386b = parcel.readInt();
        }
    }

    public class a extends l {
        public a(Context context, r rVar, View view) {
            super(context, rVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((i) rVar.getItem()).l()) {
                View view2 = ActionMenuPresenter.this.f371l;
                f(view2 == null ? (View) ActionMenuPresenter.this.f3876j : view2);
            }
            j(ActionMenuPresenter.this.D);
        }

        @Override // n.l
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
        public e f389b;

        public c(e eVar) {
            this.f389b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.f3870d != null) {
                ActionMenuPresenter.this.f3870d.d();
            }
            View view = (View) ActionMenuPresenter.this.f3876j;
            if (view != null && view.getWindowToken() != null && this.f389b.m()) {
                ActionMenuPresenter.this.f385z = this.f389b;
            }
            ActionMenuPresenter.this.B = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        public class a extends t {

            /* JADX INFO: renamed from: k, reason: collision with root package name */
            public final /* synthetic */ ActionMenuPresenter f392k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.f392k = actionMenuPresenter;
            }

            @Override // o.t
            public p b() {
                e eVar = ActionMenuPresenter.this.f385z;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // o.t
            public boolean c() {
                ActionMenuPresenter.this.O();
                return true;
            }

            @Override // o.t
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
        public boolean setFrame(int i3, int i4, int i5, int i6) {
            boolean frame = super.setFrame(i3, i4, i5, i6);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                e0.a.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends l {
        public e(Context context, g gVar, View view, boolean z3) {
            super(context, gVar, view, z3, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(ActionMenuPresenter.this.D);
        }

        @Override // n.l
        public void e() {
            if (ActionMenuPresenter.this.f3870d != null) {
                ActionMenuPresenter.this.f3870d.close();
            }
            ActionMenuPresenter.this.f385z = null;
            super.e();
        }
    }

    public class f implements m.a {
        public f() {
        }

        @Override // n.m.a
        public void a(g gVar, boolean z3) {
            if (gVar instanceof r) {
                gVar.F().e(false);
            }
            m.a aVarQ = ActionMenuPresenter.this.q();
            if (aVarQ != null) {
                aVarQ.a(gVar, z3);
            }
        }

        @Override // n.m.a
        public boolean b(g gVar) {
            if (gVar == ActionMenuPresenter.this.f3870d) {
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
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.f384y = new SparseBooleanArray();
        this.D = new f();
    }

    public boolean C() {
        return F() | G();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View D(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f3876j;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable E() {
        d dVar = this.f371l;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f373n) {
            return this.f372m;
        }
        return null;
    }

    public boolean F() {
        Object obj;
        c cVar = this.B;
        if (cVar != null && (obj = this.f3876j) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.B = null;
            return true;
        }
        e eVar = this.f385z;
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
        e eVar = this.f385z;
        return eVar != null && eVar.d();
    }

    public void J(Configuration configuration) {
        if (!this.f379t) {
            this.f378s = m.a.b(this.f3869c).d();
        }
        g gVar = this.f3870d;
        if (gVar != null) {
            gVar.M(true);
        }
    }

    public void K(boolean z3) {
        this.f382w = z3;
    }

    public void L(ActionMenuView actionMenuView) {
        this.f3876j = actionMenuView;
        actionMenuView.b(this.f3870d);
    }

    public void M(Drawable drawable) {
        d dVar = this.f371l;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.f373n = true;
            this.f372m = drawable;
        }
    }

    public void N(boolean z3) {
        this.f374o = z3;
        this.f375p = true;
    }

    public boolean O() {
        g gVar;
        if (!this.f374o || I() || (gVar = this.f3870d) == null || this.f3876j == null || this.B != null || gVar.B().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f3869c, this.f3870d, this.f371l, true));
        this.B = cVar;
        ((View) this.f3876j).post(cVar);
        return true;
    }

    @Override // n.b, n.m
    public void a(g gVar, boolean z3) {
        C();
        super.a(gVar, z3);
    }

    @Override // l0.b.a
    public void b(boolean z3) {
        if (z3) {
            super.m(null);
            return;
        }
        g gVar = this.f3870d;
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
    /* JADX WARN: Type inference failed for: r15v1, types: [n.i] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // n.m
    public boolean d() {
        ArrayList<i> arrayListG;
        int size;
        int i3;
        int iL;
        ?? r02;
        int i4;
        ActionMenuPresenter actionMenuPresenter = this;
        g gVar = actionMenuPresenter.f3870d;
        View view = null;
        ?? r3 = 0;
        if (gVar != null) {
            arrayListG = gVar.G();
            size = arrayListG.size();
        } else {
            arrayListG = null;
            size = 0;
        }
        int i5 = actionMenuPresenter.f378s;
        int i6 = actionMenuPresenter.f377r;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f3876j;
        boolean z3 = false;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            i iVar = arrayListG.get(i9);
            if (iVar.requiresActionButton()) {
                i7++;
            } else if (iVar.n()) {
                i8++;
            } else {
                z3 = true;
            }
            if (actionMenuPresenter.f382w && iVar.isActionViewExpanded()) {
                i5 = 0;
            }
        }
        if (actionMenuPresenter.f374o && (z3 || i8 + i7 > i5)) {
            i5--;
        }
        int i10 = i5 - i7;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.f384y;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.f380u) {
            int i11 = actionMenuPresenter.f383x;
            iL = i6 / i11;
            i3 = i11 + ((i6 % i11) / iL);
        } else {
            i3 = 0;
            iL = 0;
        }
        int i12 = 0;
        int i13 = 0;
        ?? r03 = actionMenuPresenter;
        while (i12 < size) {
            i iVar2 = arrayListG.get(i12);
            if (iVar2.requiresActionButton()) {
                View viewR = r03.r(iVar2, view, viewGroup);
                if (r03.f380u) {
                    iL -= ActionMenuView.L(viewR, i3, iL, iMakeMeasureSpec, r3);
                } else {
                    viewR.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewR.getMeasuredWidth();
                i6 -= measuredWidth;
                if (i13 == 0) {
                    i13 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.t(true);
                r02 = r3;
                i4 = size;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z4 = sparseBooleanArray.get(groupId2);
                boolean z5 = (i10 > 0 || z4) && i6 > 0 && (!r03.f380u || iL > 0);
                boolean z6 = z5;
                i4 = size;
                if (z5) {
                    View viewR2 = r03.r(iVar2, null, viewGroup);
                    if (r03.f380u) {
                        int iL2 = ActionMenuView.L(viewR2, i3, iL, iMakeMeasureSpec, 0);
                        iL -= iL2;
                        if (iL2 == 0) {
                            z6 = false;
                        }
                    } else {
                        viewR2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z7 = z6;
                    int measuredWidth2 = viewR2.getMeasuredWidth();
                    i6 -= measuredWidth2;
                    if (i13 == 0) {
                        i13 = measuredWidth2;
                    }
                    z5 = z7 & (!r03.f380u ? i6 + i13 <= 0 : i6 < 0);
                }
                if (z5 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z4) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i14 = 0; i14 < i12; i14++) {
                        i iVar3 = arrayListG.get(i14);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i10++;
                            }
                            iVar3.t(false);
                        }
                    }
                }
                if (z5) {
                    i10--;
                }
                iVar2.t(z5);
                r02 = 0;
            } else {
                r02 = r3;
                i4 = size;
                iVar2.t(r02);
            }
            i12++;
            r3 = r02;
            size = i4;
            view = null;
            r03 = this;
        }
        return true;
    }

    @Override // n.m
    public Parcelable e() {
        SavedState savedState = new SavedState();
        savedState.f386b = this.E;
        return savedState;
    }

    @Override // n.b, n.m
    public void f(Context context, g gVar) {
        super.f(context, gVar);
        Resources resources = context.getResources();
        m.a aVarB = m.a.b(context);
        if (!this.f375p) {
            this.f374o = aVarB.h();
        }
        if (!this.f381v) {
            this.f376q = aVarB.c();
        }
        if (!this.f379t) {
            this.f378s = aVarB.d();
        }
        int measuredWidth = this.f376q;
        if (this.f374o) {
            if (this.f371l == null) {
                d dVar = new d(this.f3868b);
                this.f371l = dVar;
                if (this.f373n) {
                    dVar.setImageDrawable(this.f372m);
                    this.f372m = null;
                    this.f373n = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f371l.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f371l.getMeasuredWidth();
        } else {
            this.f371l = null;
        }
        this.f377r = measuredWidth;
        this.f383x = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
        int i3;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i3 = ((SavedState) parcelable).f386b) > 0 && (menuItemFindItem = this.f3870d.findItem(i3)) != null) {
            m((r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // n.b
    public void l(i iVar, n.a aVar) {
        aVar.e(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f3876j);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    @Override // n.b, n.m
    public boolean m(r rVar) {
        boolean z3 = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        r rVar2 = rVar;
        while (rVar2.i0() != this.f3870d) {
            rVar2 = (r) rVar2.i0();
        }
        View viewD = D(rVar2.getItem());
        if (viewD == null) {
            return false;
        }
        this.E = rVar.getItem().getItemId();
        int size = rVar.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i3);
            if (item.isVisible() && item.getIcon() != null) {
                z3 = true;
                break;
            }
            i3++;
        }
        a aVar = new a(this.f3869c, rVar, viewD);
        this.A = aVar;
        aVar.g(z3);
        this.A.k();
        super.m(rVar);
        return true;
    }

    @Override // n.b, n.m
    public void n(boolean z3) {
        super.n(z3);
        ((View) this.f3876j).requestLayout();
        g gVar = this.f3870d;
        boolean z4 = false;
        if (gVar != null) {
            ArrayList<i> arrayListU = gVar.u();
            int size = arrayListU.size();
            for (int i3 = 0; i3 < size; i3++) {
                l0.b bVarB = arrayListU.get(i3).b();
                if (bVarB != null) {
                    bVarB.i(this);
                }
            }
        }
        g gVar2 = this.f3870d;
        ArrayList<i> arrayListB = gVar2 != null ? gVar2.B() : null;
        if (this.f374o && arrayListB != null) {
            int size2 = arrayListB.size();
            if (size2 == 1) {
                z4 = !arrayListB.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z4 = true;
            }
        }
        if (z4) {
            if (this.f371l == null) {
                this.f371l = new d(this.f3868b);
            }
            ViewGroup viewGroup = (ViewGroup) this.f371l.getParent();
            if (viewGroup != this.f3876j) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f371l);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f3876j;
                actionMenuView.addView(this.f371l, actionMenuView.F());
            }
        } else {
            d dVar = this.f371l;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.f3876j;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f371l);
                }
            }
        }
        ((ActionMenuView) this.f3876j).setOverflowReserved(this.f374o);
    }

    @Override // n.b
    public boolean p(ViewGroup viewGroup, int i3) {
        if (viewGroup.getChildAt(i3) == this.f371l) {
            return false;
        }
        return super.p(viewGroup, i3);
    }

    @Override // n.b
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

    @Override // n.b
    public n s(ViewGroup viewGroup) {
        n nVar = this.f3876j;
        n nVarS = super.s(viewGroup);
        if (nVar != nVarS) {
            ((ActionMenuView) nVarS).setPresenter(this);
        }
        return nVarS;
    }

    @Override // n.b
    public boolean u(int i3, i iVar) {
        return iVar.l();
    }
}
