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
    private NavigationMenuView f3716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LinearLayout f3717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private m.a f3718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    androidx.appcompat.view.menu.g f3719e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3720f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    c f3721g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    LayoutInflater f3722h;
    int i;
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
            boolean zO = fVar.f3719e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.f3721g.E(itemData);
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
        private final ArrayList<e> f3724c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private androidx.appcompat.view.menu.i f3725d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f3726e;

        c() {
            C();
        }

        private void C() {
            if (this.f3726e) {
                return;
            }
            boolean z = true;
            this.f3726e = true;
            this.f3724c.clear();
            this.f3724c.add(new d());
            int i = -1;
            int size = f.this.f3719e.G().size();
            int i2 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i2 < size) {
                androidx.appcompat.view.menu.i iVar = f.this.f3719e.G().get(i2);
                if (iVar.isChecked()) {
                    E(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.f3724c.add(new C0069f(f.this.u, 0));
                        }
                        this.f3724c.add(new g(iVar));
                        int size3 = this.f3724c.size();
                        int size4 = subMenu.size();
                        int i3 = 0;
                        boolean z3 = false;
                        while (i3 < size4) {
                            androidx.appcompat.view.menu.i iVar2 = (androidx.appcompat.view.menu.i) subMenu.getItem(i3);
                            if (iVar2.isVisible()) {
                                if (!z3 && iVar2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (iVar2.isCheckable()) {
                                    iVar2.s(false);
                                }
                                if (iVar.isChecked()) {
                                    E(iVar);
                                }
                                this.f3724c.add(new g(iVar2));
                            }
                            i3++;
                            z = true;
                        }
                        if (z3) {
                            v(size3, this.f3724c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i) {
                        size2 = this.f3724c.size();
                        z2 = iVar.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f3724c;
                            int i4 = f.this.u;
                            arrayList.add(new C0069f(i4, i4));
                        }
                    } else if (!z2 && iVar.getIcon() != null) {
                        v(size2, this.f3724c.size());
                        z2 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f3731b = z2;
                    this.f3724c.add(gVar);
                    i = groupId;
                }
                i2++;
                z = true;
            }
            this.f3726e = false;
        }

        private void v(int i, int i2) {
            while (i < i2) {
                ((g) this.f3724c.get(i)).f3731b = true;
                i++;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public l m(ViewGroup viewGroup, int i) {
            if (i == 0) {
                f fVar = f.this;
                return new i(fVar.f3722h, viewGroup, fVar.w);
            }
            if (i == 1) {
                return new k(f.this.f3722h, viewGroup);
            }
            if (i == 2) {
                return new j(f.this.f3722h, viewGroup);
            }
            if (i != 3) {
                return null;
            }
            return new b(f.this.f3717c);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public void r(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f1721b).D();
            }
        }

        public void D(Bundle bundle) {
            androidx.appcompat.view.menu.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            androidx.appcompat.view.menu.i iVarA2;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.f3726e = true;
                int size = this.f3724c.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f3724c.get(i2);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i) {
                        E(iVarA2);
                        break;
                    }
                    i2++;
                }
                this.f3726e = false;
                C();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f3724c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f3724c.get(i3);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void E(androidx.appcompat.view.menu.i iVar) {
            if (this.f3725d == iVar || !iVar.isCheckable()) {
                return;
            }
            androidx.appcompat.view.menu.i iVar2 = this.f3725d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f3725d = iVar;
            iVar.setChecked(true);
        }

        public void F(boolean z) {
            this.f3726e = z;
        }

        public void G() {
            C();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3724c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i) {
            e eVar = this.f3724c.get(i);
            if (eVar instanceof C0069f) {
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

        public Bundle w() {
            Bundle bundle = new Bundle();
            androidx.appcompat.view.menu.i iVar = this.f3725d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f3724c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f3724c.get(i);
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

        public androidx.appcompat.view.menu.i x() {
            return this.f3725d;
        }

        int y() {
            int i = f.this.f3717c.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < f.this.f3721g.c(); i2++) {
                if (f.this.f3721g.e(i2) == 0) {
                    i++;
                }
            }
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public void k(l lVar, int i) {
            int iE = e(i);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.f1721b).setText(((g) this.f3724c.get(i)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0069f c0069f = (C0069f) this.f3724c.get(i);
                    lVar.f1721b.setPadding(0, c0069f.b(), 0, c0069f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f1721b;
            navigationMenuItemView.setIconTintList(f.this.l);
            f fVar = f.this;
            if (fVar.j) {
                navigationMenuItemView.setTextAppearance(fVar.i);
            }
            ColorStateList colorStateList = f.this.k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.m;
            t.m0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f3724c.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f3731b);
            navigationMenuItemView.setHorizontalPadding(f.this.n);
            navigationMenuItemView.setIconPadding(f.this.o);
            f fVar2 = f.this;
            if (fVar2.q) {
                navigationMenuItemView.setIconSize(fVar2.p);
            }
            navigationMenuItemView.setMaxLines(f.this.s);
            navigationMenuItemView.e(gVar.a(), 0);
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
    private static class C0069f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f3728a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f3729b;

        public C0069f(int i, int i2) {
            this.f3728a = i;
            this.f3729b = i2;
        }

        public int a() {
            return this.f3729b;
        }

        public int b() {
            return this.f3728a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final androidx.appcompat.view.menu.i f3730a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f3731b;

        g(androidx.appcompat.view.menu.i iVar) {
            this.f3730a = iVar;
        }

        public androidx.appcompat.view.menu.i a() {
            return this.f3730a;
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
            cVar.d0(c.b.a(f.this.f3721g.y(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(d.b.a.a.h.f4730d, viewGroup, false));
            this.f1721b.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(d.b.a.a.h.f4732f, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(d.b.a.a.h.f4733g, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static abstract class l extends RecyclerView.c0 {
        public l(View view) {
            super(view);
        }
    }

    private void L() {
        int i2 = (this.f3717c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f3716b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    public void A(int i2) {
        this.f3720f = i2;
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
        this.i = i2;
        this.j = true;
        n(false);
    }

    public void I(ColorStateList colorStateList) {
        this.k = colorStateList;
        n(false);
    }

    public void J(int i2) {
        this.v = i2;
        NavigationMenuView navigationMenuView = this.f3716b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void K(boolean z) {
        c cVar = this.f3721g;
        if (cVar != null) {
            cVar.F(z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        m.a aVar = this.f3718d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f3720f;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f3716b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f3716b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f3721g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.w());
        }
        if (this.f3717c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f3717c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, androidx.appcompat.view.menu.g gVar) {
        this.f3722h = LayoutInflater.from(context);
        this.f3719e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(d.b.a.a.d.n);
    }

    public void g(View view) {
        this.f3717c.addView(view);
        NavigationMenuView navigationMenuView = this.f3716b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f3716b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f3721g.D(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f3717c.restoreHierarchyState(sparseParcelableArray2);
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
        int iH = b0Var.h();
        if (this.t != iH) {
            this.t = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.f3716b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.g(this.f3717c, b0Var);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        c cVar = this.f3721g;
        if (cVar != null) {
            cVar.G();
        }
    }

    public androidx.appcompat.view.menu.i o() {
        return this.f3721g.x();
    }

    public int p() {
        return this.f3717c.getChildCount();
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
        if (this.f3716b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f3722h.inflate(d.b.a.a.h.f4734h, viewGroup, false);
            this.f3716b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f3716b));
            if (this.f3721g == null) {
                this.f3721g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f3716b.setOverScrollMode(i2);
            }
            this.f3717c = (LinearLayout) this.f3722h.inflate(d.b.a.a.h.f4731e, (ViewGroup) this.f3716b, false);
            this.f3716b.setAdapter(this.f3721g);
        }
        return this.f3716b;
    }

    public View x(int i2) {
        View viewInflate = this.f3722h.inflate(i2, (ViewGroup) this.f3717c, false);
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
        this.f3721g.E(iVar);
    }
}
