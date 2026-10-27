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
    private NavigationMenuView f4307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LinearLayout f4308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private m.a f4309d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    androidx.appcompat.view.menu.g f4310e;
    private int f;
    c g;
    LayoutInflater h;
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
            boolean zO = fVar.f4310e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.g.F(itemData);
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
        private final ArrayList<e> f4312c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private androidx.appcompat.view.menu.i f4313d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f4314e;

        c() {
            D();
        }

        private void D() {
            if (this.f4314e) {
                return;
            }
            boolean z = true;
            this.f4314e = true;
            this.f4312c.clear();
            this.f4312c.add(new d());
            int i = -1;
            int size = f.this.f4310e.G().size();
            int i2 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i2 < size) {
                androidx.appcompat.view.menu.i iVar = f.this.f4310e.G().get(i2);
                if (iVar.isChecked()) {
                    F(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.f4312c.add(new C0107f(f.this.u, 0));
                        }
                        this.f4312c.add(new g(iVar));
                        int size3 = this.f4312c.size();
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
                                    F(iVar);
                                }
                                this.f4312c.add(new g(iVar2));
                            }
                            i3++;
                            z = true;
                        }
                        if (z3) {
                            w(size3, this.f4312c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i) {
                        size2 = this.f4312c.size();
                        z2 = iVar.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f4312c;
                            int i4 = f.this.u;
                            arrayList.add(new C0107f(i4, i4));
                        }
                    } else if (!z2 && iVar.getIcon() != null) {
                        w(size2, this.f4312c.size());
                        z2 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f4318b = z2;
                    this.f4312c.add(gVar);
                    i = groupId;
                }
                i2++;
                z = true;
            }
            this.f4314e = false;
        }

        private void w(int i, int i2) {
            while (i < i2) {
                ((g) this.f4312c.get(i)).f4318b = true;
                i++;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void l(l lVar, int i) {
            int iE = e(i);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.f1300b).setText(((g) this.f4312c.get(i)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0107f c0107f = (C0107f) this.f4312c.get(i);
                    lVar.f1300b.setPadding(0, c0107f.b(), 0, c0107f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f1300b;
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
            g gVar = (g) this.f4312c.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f4318b);
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
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public l n(ViewGroup viewGroup, int i) {
            if (i == 0) {
                f fVar = f.this;
                return new i(fVar.h, viewGroup, fVar.w);
            }
            if (i == 1) {
                return new k(f.this.h, viewGroup);
            }
            if (i == 2) {
                return new j(f.this.h, viewGroup);
            }
            if (i != 3) {
                return null;
            }
            return new b(f.this.f4308c);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public void s(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f1300b).D();
            }
        }

        public void E(Bundle bundle) {
            androidx.appcompat.view.menu.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            androidx.appcompat.view.menu.i iVarA2;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.f4314e = true;
                int size = this.f4312c.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f4312c.get(i2);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i) {
                        F(iVarA2);
                        break;
                    }
                    i2++;
                }
                this.f4314e = false;
                D();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f4312c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f4312c.get(i3);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void F(androidx.appcompat.view.menu.i iVar) {
            if (this.f4313d == iVar || !iVar.isCheckable()) {
                return;
            }
            androidx.appcompat.view.menu.i iVar2 = this.f4313d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f4313d = iVar;
            iVar.setChecked(true);
        }

        public void G(boolean z) {
            this.f4314e = z;
        }

        public void H() {
            D();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f4312c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i) {
            e eVar = this.f4312c.get(i);
            if (eVar instanceof C0107f) {
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

        public Bundle x() {
            Bundle bundle = new Bundle();
            androidx.appcompat.view.menu.i iVar = this.f4313d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f4312c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f4312c.get(i);
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

        public androidx.appcompat.view.menu.i y() {
            return this.f4313d;
        }

        int z() {
            int i = f.this.f4308c.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < f.this.g.c(); i2++) {
                if (f.this.g.e(i2) == 0) {
                    i++;
                }
            }
            return i;
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
    private static class C0107f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4315a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f4316b;

        public C0107f(int i, int i2) {
            this.f4315a = i;
            this.f4316b = i2;
        }

        public int a() {
            return this.f4316b;
        }

        public int b() {
            return this.f4315a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final androidx.appcompat.view.menu.i f4317a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4318b;

        g(androidx.appcompat.view.menu.i iVar) {
            this.f4317a = iVar;
        }

        public androidx.appcompat.view.menu.i a() {
            return this.f4317a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private class h extends androidx.recyclerview.widget.m {
        h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.m, b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(c.b.a(f.this.g.z(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(c.a.a.a.h.f2287d, viewGroup, false));
            this.f1300b.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.a.a.a.h.f, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.a.a.a.h.g, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    private static abstract class l extends RecyclerView.c0 {
        public l(View view) {
            super(view);
        }
    }

    private void L() {
        int i2 = (this.f4308c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f4307b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    public void A(int i2) {
        this.f = i2;
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
        NavigationMenuView navigationMenuView = this.f4307b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void K(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.G(z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        m.a aVar = this.f4309d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public int c() {
        return this.f;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f4307b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f4307b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.x());
        }
        if (this.f4308c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f4308c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, androidx.appcompat.view.menu.g gVar) {
        this.h = LayoutInflater.from(context);
        this.f4310e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(c.a.a.a.d.n);
    }

    public void g(View view) {
        this.f4308c.addView(view);
        NavigationMenuView navigationMenuView = this.f4307b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f4307b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.g.E(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f4308c.restoreHierarchyState(sparseParcelableArray2);
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
        NavigationMenuView navigationMenuView = this.f4307b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.g(this.f4308c, b0Var);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.H();
        }
    }

    public androidx.appcompat.view.menu.i o() {
        return this.g.y();
    }

    public int p() {
        return this.f4308c.getChildCount();
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
        if (this.f4307b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.h.inflate(c.a.a.a.h.h, viewGroup, false);
            this.f4307b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f4307b));
            if (this.g == null) {
                this.g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f4307b.setOverScrollMode(i2);
            }
            this.f4308c = (LinearLayout) this.h.inflate(c.a.a.a.h.f2288e, (ViewGroup) this.f4307b, false);
            this.f4307b.setAdapter(this.g);
        }
        return this.f4307b;
    }

    public View x(int i2) {
        View viewInflate = this.h.inflate(i2, (ViewGroup) this.f4308c, false);
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
        this.g.F(iVar);
    }
}
