package com.google.android.material.internal;

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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.r;
import androidx.recyclerview.widget.RecyclerView;
import b.g.k.b0;
import b.g.k.c0.c;
import b.g.k.t;
import java.util.ArrayList;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements androidx.appcompat.view.menu.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private NavigationMenuView f4126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LinearLayout f4127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private m.a f4128d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    androidx.appcompat.view.menu.g f4129e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4130f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    c f4131g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    LayoutInflater f4132h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f4133i;
    boolean j;
    ColorStateList k;
    ColorStateList l;
    Drawable m;
    int n;
    int o;
    int p;
    boolean q;
    private int s;
    private int t;
    int u;
    boolean r = true;
    private int v = -1;
    final View.OnClickListener w = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            f.this.K(true);
            androidx.appcompat.view.menu.i itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean zO = fVar.f4129e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.f4131g.D(itemData);
            } else {
                z = false;
            }
            f.this.K(false);
            if (z) {
                f.this.n(false);
            }
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private class c extends RecyclerView.g<l> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ArrayList<e> f4135c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private androidx.appcompat.view.menu.i f4136d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f4137e;

        c() {
            B();
        }

        private void B() {
            if (this.f4137e) {
                return;
            }
            boolean z = true;
            this.f4137e = true;
            this.f4135c.clear();
            this.f4135c.add(new d());
            int i2 = -1;
            int size = f.this.f4129e.G().size();
            int i3 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i3 < size) {
                androidx.appcompat.view.menu.i iVar = f.this.f4129e.G().get(i3);
                if (iVar.isChecked()) {
                    D(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i3 != 0) {
                            this.f4135c.add(new C0078f(f.this.u, 0));
                        }
                        this.f4135c.add(new g(iVar));
                        int size3 = this.f4135c.size();
                        int size4 = subMenu.size();
                        int i4 = 0;
                        boolean z3 = false;
                        while (i4 < size4) {
                            androidx.appcompat.view.menu.i iVar2 = (androidx.appcompat.view.menu.i) subMenu.getItem(i4);
                            if (iVar2.isVisible()) {
                                if (!z3 && iVar2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (iVar2.isCheckable()) {
                                    iVar2.s(false);
                                }
                                if (iVar.isChecked()) {
                                    D(iVar);
                                }
                                this.f4135c.add(new g(iVar2));
                            }
                            i4++;
                            z = true;
                        }
                        if (z3) {
                            u(size3, this.f4135c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i2) {
                        size2 = this.f4135c.size();
                        z2 = iVar.getIcon() != null;
                        if (i3 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f4135c;
                            int i5 = f.this.u;
                            arrayList.add(new C0078f(i5, i5));
                        }
                    } else if (!z2 && iVar.getIcon() != null) {
                        u(size2, this.f4135c.size());
                        z2 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f4142b = z2;
                    this.f4135c.add(gVar);
                    i2 = groupId;
                }
                i3++;
                z = true;
            }
            this.f4137e = false;
        }

        private void u(int i2, int i3) {
            while (i2 < i3) {
                ((g) this.f4135c.get(i2)).f4142b = true;
                i2++;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void q(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f1790b).D();
            }
        }

        public void C(Bundle bundle) {
            androidx.appcompat.view.menu.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            androidx.appcompat.view.menu.i iVarA2;
            int i2 = bundle.getInt("android:menu:checked", 0);
            if (i2 != 0) {
                this.f4137e = true;
                int size = this.f4135c.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    }
                    e eVar = this.f4135c.get(i3);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i2) {
                        D(iVarA2);
                        break;
                    }
                    i3++;
                }
                this.f4137e = false;
                B();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f4135c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    e eVar2 = this.f4135c.get(i4);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void D(androidx.appcompat.view.menu.i iVar) {
            if (this.f4136d == iVar || !iVar.isCheckable()) {
                return;
            }
            androidx.appcompat.view.menu.i iVar2 = this.f4136d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f4136d = iVar;
            iVar.setChecked(true);
        }

        public void E(boolean z) {
            this.f4137e = z;
        }

        public void F() {
            B();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f4135c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i2) {
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i2) {
            e eVar = this.f4135c.get(i2);
            if (eVar instanceof C0078f) {
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

        public Bundle v() {
            Bundle bundle = new Bundle();
            androidx.appcompat.view.menu.i iVar = this.f4136d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f4135c.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = this.f4135c.get(i2);
                if (eVar instanceof g) {
                    androidx.appcompat.view.menu.i iVarA = ((g) eVar).a();
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

        public androidx.appcompat.view.menu.i w() {
            return this.f4136d;
        }

        int x() {
            int i2 = f.this.f4127c.getChildCount() == 0 ? 0 : 1;
            for (int i3 = 0; i3 < f.this.f4131g.c(); i3++) {
                if (f.this.f4131g.e(i3) == 0) {
                    i2++;
                }
            }
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void j(l lVar, int i2) {
            int iE = e(i2);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.f1790b).setText(((g) this.f4135c.get(i2)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0078f c0078f = (C0078f) this.f4135c.get(i2);
                    lVar.f1790b.setPadding(0, c0078f.b(), 0, c0078f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f1790b;
            navigationMenuItemView.setIconTintList(f.this.l);
            f fVar = f.this;
            if (fVar.j) {
                navigationMenuItemView.setTextAppearance(fVar.f4133i);
            }
            ColorStateList colorStateList = f.this.k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.m;
            t.m0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f4135c.get(i2);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f4142b);
            navigationMenuItemView.setHorizontalPadding(f.this.n);
            navigationMenuItemView.setIconPadding(f.this.o);
            f fVar2 = f.this;
            if (fVar2.q) {
                navigationMenuItemView.setIconSize(fVar2.p);
            }
            navigationMenuItemView.setMaxLines(f.this.s);
            navigationMenuItemView.e(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public l l(ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                f fVar = f.this;
                return new i(fVar.f4132h, viewGroup, fVar.w);
            }
            if (i2 == 1) {
                return new k(f.this.f4132h, viewGroup);
            }
            if (i2 == 2) {
                return new j(f.this.f4132h, viewGroup);
            }
            if (i2 != 3) {
                return null;
            }
            return new b(f.this.f4127c);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class d implements e {
        d() {
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private interface e {
    }

    /* JADX INFO: renamed from: com.google.android.material.internal.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class C0078f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4139a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f4140b;

        public C0078f(int i2, int i3) {
            this.f4139a = i2;
            this.f4140b = i3;
        }

        public int a() {
            return this.f4140b;
        }

        public int b() {
            return this.f4139a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final androidx.appcompat.view.menu.i f4141a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4142b;

        g(androidx.appcompat.view.menu.i iVar) {
            this.f4141a = iVar;
        }

        public androidx.appcompat.view.menu.i a() {
            return this.f4141a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private class h extends androidx.recyclerview.widget.l {
        h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.l, b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(c.b.a(f.this.f4131g.x(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(d.b.a.a.h.design_navigation_item, viewGroup, false));
            this.f1790b.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(d.b.a.a.h.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(d.b.a.a.h.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static abstract class l extends RecyclerView.c0 {
        public l(View view) {
            super(view);
        }
    }

    private void L() {
        int i2 = (this.f4127c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f4126b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    public void A(int i2) {
        this.f4130f = i2;
    }

    public void B(Drawable drawable) {
        this.m = drawable;
        n(false);
    }

    public void C(int i2) {
        this.n = i2;
        n(false);
    }

    public void D(int i2) {
        this.o = i2;
        n(false);
    }

    public void E(int i2) {
        if (this.p != i2) {
            this.p = i2;
            this.q = true;
            n(false);
        }
    }

    public void F(ColorStateList colorStateList) {
        this.l = colorStateList;
        n(false);
    }

    public void G(int i2) {
        this.s = i2;
        n(false);
    }

    public void H(int i2) {
        this.f4133i = i2;
        this.j = true;
        n(false);
    }

    public void I(ColorStateList colorStateList) {
        this.k = colorStateList;
        n(false);
    }

    public void J(int i2) {
        this.v = i2;
        NavigationMenuView navigationMenuView = this.f4126b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void K(boolean z) {
        c cVar = this.f4131g;
        if (cVar != null) {
            cVar.E(z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        m.a aVar = this.f4128d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f4130f;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f4126b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f4126b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f4131g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.v());
        }
        if (this.f4127c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f4127c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, androidx.appcompat.view.menu.g gVar) {
        this.f4132h = LayoutInflater.from(context);
        this.f4129e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(d.b.a.a.d.design_navigation_separator_vertical_padding);
    }

    public void g(View view) {
        this.f4127c.addView(view);
        NavigationMenuView navigationMenuView = this.f4126b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f4126b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f4131g.C(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f4127c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(androidx.appcompat.view.menu.g gVar, androidx.appcompat.view.menu.i iVar) {
        return false;
    }

    public void l(b0 b0Var) {
        int i2 = b0Var.i();
        if (this.t != i2) {
            this.t = i2;
            L();
        }
        NavigationMenuView navigationMenuView = this.f4126b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.f());
        t.g(this.f4127c, b0Var);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        c cVar = this.f4131g;
        if (cVar != null) {
            cVar.F();
        }
    }

    public androidx.appcompat.view.menu.i o() {
        return this.f4131g.w();
    }

    public int p() {
        return this.f4127c.getChildCount();
    }

    public Drawable q() {
        return this.m;
    }

    public int r() {
        return this.n;
    }

    public int s() {
        return this.o;
    }

    public int t() {
        return this.s;
    }

    public ColorStateList u() {
        return this.k;
    }

    public ColorStateList v() {
        return this.l;
    }

    public androidx.appcompat.view.menu.n w(ViewGroup viewGroup) {
        if (this.f4126b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f4132h.inflate(d.b.a.a.h.design_navigation_menu, viewGroup, false);
            this.f4126b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f4126b));
            if (this.f4131g == null) {
                this.f4131g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f4126b.setOverScrollMode(i2);
            }
            this.f4127c = (LinearLayout) this.f4132h.inflate(d.b.a.a.h.design_navigation_item_header, (ViewGroup) this.f4126b, false);
            this.f4126b.setAdapter(this.f4131g);
        }
        return this.f4126b;
    }

    public View x(int i2) {
        View viewInflate = this.f4132h.inflate(i2, (ViewGroup) this.f4127c, false);
        g(viewInflate);
        return viewInflate;
    }

    public void y(boolean z) {
        if (this.r != z) {
            this.r = z;
            L();
        }
    }

    public void z(androidx.appcompat.view.menu.i iVar) {
        this.f4131g.D(iVar);
    }
}
