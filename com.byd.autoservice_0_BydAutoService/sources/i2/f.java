package i2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import java.util.ArrayList;
import l0.b0;
import l0.t;
import m0.c;
import n.m;
import n.r;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements n.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NavigationMenuView f3321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f3322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m.a f3323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public n.g f3324e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3325f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f3326g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public LayoutInflater f3327h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3328i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3329j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f3330k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f3331l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f3332m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3333n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3334o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3335p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3336q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3338s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3339t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3340u;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3337r = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3341v = -1;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final View.OnClickListener f3342w = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z3 = true;
            f.this.K(true);
            n.i itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean zO = fVar.f3324e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.f3326g.D(itemData);
            } else {
                z3 = false;
            }
            f.this.K(false);
            if (z3) {
                f.this.n(false);
            }
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class c extends RecyclerView.g<l> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<e> f3344c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public n.i f3345d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3346e;

        public c() {
            B();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void q(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f1474b).D();
            }
        }

        public final void B() {
            if (this.f3346e) {
                return;
            }
            boolean z3 = true;
            this.f3346e = true;
            this.f3344c.clear();
            this.f3344c.add(new d());
            int i3 = -1;
            int size = f.this.f3324e.G().size();
            int i4 = 0;
            boolean z4 = false;
            int size2 = 0;
            while (i4 < size) {
                n.i iVar = f.this.f3324e.G().get(i4);
                if (iVar.isChecked()) {
                    D(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i4 != 0) {
                            this.f3344c.add(new C0022f(f.this.f3340u, 0));
                        }
                        this.f3344c.add(new g(iVar));
                        int size3 = this.f3344c.size();
                        int size4 = subMenu.size();
                        int i5 = 0;
                        boolean z5 = false;
                        while (i5 < size4) {
                            n.i iVar2 = (n.i) subMenu.getItem(i5);
                            if (iVar2.isVisible()) {
                                if (!z5 && iVar2.getIcon() != null) {
                                    z5 = z3;
                                }
                                if (iVar2.isCheckable()) {
                                    iVar2.s(false);
                                }
                                if (iVar.isChecked()) {
                                    D(iVar);
                                }
                                this.f3344c.add(new g(iVar2));
                            }
                            i5++;
                            z3 = true;
                        }
                        if (z5) {
                            u(size3, this.f3344c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i3) {
                        size2 = this.f3344c.size();
                        z4 = iVar.getIcon() != null;
                        if (i4 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f3344c;
                            int i6 = f.this.f3340u;
                            arrayList.add(new C0022f(i6, i6));
                        }
                    } else if (!z4 && iVar.getIcon() != null) {
                        u(size2, this.f3344c.size());
                        z4 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f3351b = z4;
                    this.f3344c.add(gVar);
                    i3 = groupId;
                }
                i4++;
                z3 = true;
            }
            this.f3346e = false;
        }

        public void C(Bundle bundle) {
            n.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            n.i iVarA2;
            int i3 = bundle.getInt("android:menu:checked", 0);
            if (i3 != 0) {
                this.f3346e = true;
                int size = this.f3344c.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    e eVar = this.f3344c.get(i4);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i3) {
                        D(iVarA2);
                        break;
                    }
                    i4++;
                }
                this.f3346e = false;
                B();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f3344c.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    e eVar2 = this.f3344c.get(i5);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void D(n.i iVar) {
            if (this.f3345d == iVar || !iVar.isCheckable()) {
                return;
            }
            n.i iVar2 = this.f3345d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f3345d = iVar;
            iVar.setChecked(true);
        }

        public void E(boolean z3) {
            this.f3346e = z3;
        }

        public void F() {
            B();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3344c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i3) {
            return i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i3) {
            e eVar = this.f3344c.get(i3);
            if (eVar instanceof C0022f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        public final void u(int i3, int i4) {
            while (i3 < i4) {
                ((g) this.f3344c.get(i3)).f3351b = true;
                i3++;
            }
        }

        public Bundle v() {
            Bundle bundle = new Bundle();
            n.i iVar = this.f3345d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f3344c.size();
            for (int i3 = 0; i3 < size; i3++) {
                e eVar = this.f3344c.get(i3);
                if (eVar instanceof g) {
                    n.i iVarA = ((g) eVar).a();
                    View actionView = iVarA != null ? iVarA.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(iVarA.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public n.i w() {
            return this.f3345d;
        }

        public int x() {
            int i3 = f.this.f3322c.getChildCount() == 0 ? 0 : 1;
            for (int i4 = 0; i4 < f.this.f3326g.c(); i4++) {
                if (f.this.f3326g.e(i4) == 0) {
                    i3++;
                }
            }
            return i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void j(l lVar, int i3) {
            int iE = e(i3);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.f1474b).setText(((g) this.f3344c.get(i3)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0022f c0022f = (C0022f) this.f3344c.get(i3);
                    lVar.f1474b.setPadding(0, c0022f.b(), 0, c0022f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f1474b;
            navigationMenuItemView.setIconTintList(f.this.f3331l);
            f fVar = f.this;
            if (fVar.f3329j) {
                navigationMenuItemView.setTextAppearance(fVar.f3328i);
            }
            ColorStateList colorStateList = f.this.f3330k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.f3332m;
            t.m0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f3344c.get(i3);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f3351b);
            navigationMenuItemView.setHorizontalPadding(f.this.f3333n);
            navigationMenuItemView.setIconPadding(f.this.f3334o);
            f fVar2 = f.this;
            if (fVar2.f3336q) {
                navigationMenuItemView.setIconSize(fVar2.f3335p);
            }
            navigationMenuItemView.setMaxLines(f.this.f3338s);
            navigationMenuItemView.e(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public l l(ViewGroup viewGroup, int i3) {
            if (i3 == 0) {
                f fVar = f.this;
                return new i(fVar.f3327h, viewGroup, fVar.f3342w);
            }
            if (i3 == 1) {
                return new k(f.this.f3327h, viewGroup);
            }
            if (i3 == 2) {
                return new j(f.this.f3327h, viewGroup);
            }
            if (i3 != 3) {
                return null;
            }
            return new b(f.this.f3322c);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: renamed from: i2.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class C0022f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3348a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3349b;

        public C0022f(int i3, int i4) {
            this.f3348a = i3;
            this.f3349b = i4;
        }

        public int a() {
            return this.f3349b;
        }

        public int b() {
            return this.f3348a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n.i f3350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3351b;

        public g(n.i iVar) {
            this.f3350a = iVar;
        }

        public n.i a() {
            return this.f3350a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends w0.l {
        public h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // w0.l, l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            cVar.d0(c.b.a(f.this.f3326g.x(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false));
            this.f1474b.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R$layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R$layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static abstract class l extends RecyclerView.c0 {
        public l(View view) {
            super(view);
        }
    }

    public void A(int i3) {
        this.f3325f = i3;
    }

    public void B(Drawable drawable) {
        this.f3332m = drawable;
        n(false);
    }

    public void C(int i3) {
        this.f3333n = i3;
        n(false);
    }

    public void D(int i3) {
        this.f3334o = i3;
        n(false);
    }

    public void E(int i3) {
        if (this.f3335p != i3) {
            this.f3335p = i3;
            this.f3336q = true;
            n(false);
        }
    }

    public void F(ColorStateList colorStateList) {
        this.f3331l = colorStateList;
        n(false);
    }

    public void G(int i3) {
        this.f3338s = i3;
        n(false);
    }

    public void H(int i3) {
        this.f3328i = i3;
        this.f3329j = true;
        n(false);
    }

    public void I(ColorStateList colorStateList) {
        this.f3330k = colorStateList;
        n(false);
    }

    public void J(int i3) {
        this.f3341v = i3;
        NavigationMenuView navigationMenuView = this.f3321b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i3);
        }
    }

    public void K(boolean z3) {
        c cVar = this.f3326g;
        if (cVar != null) {
            cVar.E(z3);
        }
    }

    public final void L() {
        int i3 = (this.f3322c.getChildCount() == 0 && this.f3337r) ? this.f3339t : 0;
        NavigationMenuView navigationMenuView = this.f3321b;
        navigationMenuView.setPadding(0, i3, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // n.m
    public void a(n.g gVar, boolean z3) {
        m.a aVar = this.f3323d;
        if (aVar != null) {
            aVar.a(gVar, z3);
        }
    }

    @Override // n.m
    public int c() {
        return this.f3325f;
    }

    @Override // n.m
    public boolean d() {
        return false;
    }

    @Override // n.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f3321b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f3321b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f3326g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.v());
        }
        if (this.f3322c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f3322c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // n.m
    public void f(Context context, n.g gVar) {
        this.f3327h = LayoutInflater.from(context);
        this.f3324e = gVar;
        this.f3340u = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    public void g(View view) {
        this.f3322c.addView(view);
        NavigationMenuView navigationMenuView = this.f3321b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f3321b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f3326g.C(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f3322c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // n.m
    public boolean i(n.g gVar, n.i iVar) {
        return false;
    }

    @Override // n.m
    public boolean j(n.g gVar, n.i iVar) {
        return false;
    }

    public void l(b0 b0Var) {
        int iH = b0Var.h();
        if (this.f3339t != iH) {
            this.f3339t = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.f3321b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.g(this.f3322c, b0Var);
    }

    @Override // n.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // n.m
    public void n(boolean z3) {
        c cVar = this.f3326g;
        if (cVar != null) {
            cVar.F();
        }
    }

    public n.i o() {
        return this.f3326g.w();
    }

    public int p() {
        return this.f3322c.getChildCount();
    }

    public Drawable q() {
        return this.f3332m;
    }

    public int r() {
        return this.f3333n;
    }

    public int s() {
        return this.f3334o;
    }

    public int t() {
        return this.f3338s;
    }

    public ColorStateList u() {
        return this.f3330k;
    }

    public ColorStateList v() {
        return this.f3331l;
    }

    public n.n w(ViewGroup viewGroup) {
        if (this.f3321b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f3327h.inflate(R$layout.design_navigation_menu, viewGroup, false);
            this.f3321b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f3321b));
            if (this.f3326g == null) {
                this.f3326g = new c();
            }
            int i3 = this.f3341v;
            if (i3 != -1) {
                this.f3321b.setOverScrollMode(i3);
            }
            this.f3322c = (LinearLayout) this.f3327h.inflate(R$layout.design_navigation_item_header, (ViewGroup) this.f3321b, false);
            this.f3321b.setAdapter(this.f3326g);
        }
        return this.f3321b;
    }

    public View x(int i3) {
        View viewInflate = this.f3327h.inflate(i3, (ViewGroup) this.f3322c, false);
        g(viewInflate);
        return viewInflate;
    }

    public void y(boolean z3) {
        if (this.f3337r != z3) {
            this.f3337r = z3;
            L();
        }
    }

    public void z(n.i iVar) {
        this.f3326g.D(iVar);
    }
}
