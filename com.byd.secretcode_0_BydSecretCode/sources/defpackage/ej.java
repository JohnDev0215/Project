package defpackage;

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
import defpackage.ea;
import defpackage.v1;
import java.util.ArrayList;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class ej implements v1 {
    public NavigationMenuView b;
    public LinearLayout c;
    public v1.a d;
    public p1 e;
    public int f;
    public c g;
    public LayoutInflater h;
    public int i;
    public boolean j;
    public ColorStateList k;
    public ColorStateList l;
    public Drawable m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public int s;
    public int t;
    public int u;
    public boolean r = true;
    public int v = -1;
    public final View.OnClickListener w = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            ej.this.K(true);
            r1 itemData = ((NavigationMenuItemView) view).getItemData();
            ej ejVar = ej.this;
            boolean zO = ejVar.e.O(itemData, ejVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                ej.this.g.D(itemData);
            } else {
                z = false;
            }
            ej.this.K(false);
            if (z) {
                ej.this.n(false);
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
        public final ArrayList<e> c = new ArrayList<>();
        public r1 d;
        public boolean e;

        public c() {
            B();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void q(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.b).D();
            }
        }

        public final void B() {
            if (this.e) {
                return;
            }
            boolean z = true;
            this.e = true;
            this.c.clear();
            this.c.add(new d());
            int i = -1;
            int size = ej.this.e.G().size();
            int i2 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i2 < size) {
                r1 r1Var = ej.this.e.G().get(i2);
                if (r1Var.isChecked()) {
                    D(r1Var);
                }
                if (r1Var.isCheckable()) {
                    r1Var.s(false);
                }
                if (r1Var.hasSubMenu()) {
                    SubMenu subMenu = r1Var.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.c.add(new f(ej.this.u, 0));
                        }
                        this.c.add(new g(r1Var));
                        int size3 = this.c.size();
                        int size4 = subMenu.size();
                        int i3 = 0;
                        boolean z3 = false;
                        while (i3 < size4) {
                            r1 r1Var2 = (r1) subMenu.getItem(i3);
                            if (r1Var2.isVisible()) {
                                if (!z3 && r1Var2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (r1Var2.isCheckable()) {
                                    r1Var2.s(false);
                                }
                                if (r1Var.isChecked()) {
                                    D(r1Var);
                                }
                                this.c.add(new g(r1Var2));
                            }
                            i3++;
                            z = true;
                        }
                        if (z3) {
                            u(size3, this.c.size());
                        }
                    }
                } else {
                    int groupId = r1Var.getGroupId();
                    if (groupId != i) {
                        size2 = this.c.size();
                        z2 = r1Var.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.c;
                            int i4 = ej.this.u;
                            arrayList.add(new f(i4, i4));
                        }
                    } else if (!z2 && r1Var.getIcon() != null) {
                        u(size2, this.c.size());
                        z2 = true;
                    }
                    g gVar = new g(r1Var);
                    gVar.b = z2;
                    this.c.add(gVar);
                    i = groupId;
                }
                i2++;
                z = true;
            }
            this.e = false;
        }

        public void C(Bundle bundle) {
            r1 r1VarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            r1 r1VarA2;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.e = true;
                int size = this.c.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.c.get(i2);
                    if ((eVar instanceof g) && (r1VarA2 = ((g) eVar).a()) != null && r1VarA2.getItemId() == i) {
                        D(r1VarA2);
                        break;
                    }
                    i2++;
                }
                this.e = false;
                B();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.c.get(i3);
                    if ((eVar2 instanceof g) && (r1VarA = ((g) eVar2).a()) != null && (actionView = r1VarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(r1VarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void D(r1 r1Var) {
            if (this.d == r1Var || !r1Var.isCheckable()) {
                return;
            }
            r1 r1Var2 = this.d;
            if (r1Var2 != null) {
                r1Var2.setChecked(false);
            }
            this.d = r1Var;
            r1Var.setChecked(true);
        }

        public void E(boolean z) {
            this.e = z;
        }

        public void F() {
            B();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i) {
            e eVar = this.c.get(i);
            if (eVar instanceof f) {
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

        public final void u(int i, int i2) {
            while (i < i2) {
                ((g) this.c.get(i)).b = true;
                i++;
            }
        }

        public Bundle v() {
            Bundle bundle = new Bundle();
            r1 r1Var = this.d;
            if (r1Var != null) {
                bundle.putInt("android:menu:checked", r1Var.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.c.get(i);
                if (eVar instanceof g) {
                    r1 r1VarA = ((g) eVar).a();
                    View actionView = r1VarA != null ? r1VarA.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(r1VarA.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public r1 w() {
            return this.d;
        }

        public int x() {
            int i = ej.this.c.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < ej.this.g.c(); i2++) {
                if (ej.this.g.e(i2) == 0) {
                    i++;
                }
            }
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void j(l lVar, int i) {
            int iE = e(i);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.b).setText(((g) this.c.get(i)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    f fVar = (f) this.c.get(i);
                    lVar.b.setPadding(0, fVar.b(), 0, fVar.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.b;
            navigationMenuItemView.setIconTintList(ej.this.l);
            ej ejVar = ej.this;
            if (ejVar.j) {
                navigationMenuItemView.setTextAppearance(ejVar.i);
            }
            ColorStateList colorStateList = ej.this.k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = ej.this.m;
            t9.m0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.c.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.b);
            navigationMenuItemView.setHorizontalPadding(ej.this.n);
            navigationMenuItemView.setIconPadding(ej.this.o);
            ej ejVar2 = ej.this;
            if (ejVar2.q) {
                navigationMenuItemView.setIconSize(ejVar2.p);
            }
            navigationMenuItemView.setMaxLines(ej.this.s);
            navigationMenuItemView.e(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public l l(ViewGroup viewGroup, int i) {
            if (i == 0) {
                ej ejVar = ej.this;
                return new i(ejVar.h, viewGroup, ejVar.w);
            }
            if (i == 1) {
                return new k(ej.this.h, viewGroup);
            }
            if (i == 2) {
                return new j(ej.this.h, viewGroup);
            }
            if (i != 3) {
                return null;
            }
            return new b(ej.this.c);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class f implements e {
        public final int a;
        public final int b;

        public f(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {
        public final r1 a;
        public boolean b;

        public g(r1 r1Var) {
            this.a = r1Var;
        }

        public r1 a() {
            return this.a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends fd {
        public h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // defpackage.fd, defpackage.a9
        public void g(View view, ea eaVar) {
            super.g(view, eaVar);
            eaVar.d0(ea.b.a(ej.this.g.x(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false));
            this.b.setOnClickListener(onClickListener);
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
        NavigationMenuView navigationMenuView = this.b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void K(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.E(z);
        }
    }

    public final void L() {
        int i2 = (this.c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
        v1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(p1Var, z);
        }
    }

    @Override // defpackage.v1
    public int c() {
        return this.f;
    }

    @Override // defpackage.v1
    public boolean d() {
        return false;
    }

    @Override // defpackage.v1
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.v());
        }
        if (this.c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // defpackage.v1
    public void f(Context context, p1 p1Var) {
        this.h = LayoutInflater.from(context);
        this.e = p1Var;
        this.u = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    public void g(View view) {
        this.c.addView(view);
        NavigationMenuView navigationMenuView = this.b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.g.C(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // defpackage.v1
    public boolean i(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public boolean j(p1 p1Var, r1 r1Var) {
        return false;
    }

    public void l(ba baVar) {
        int iH = baVar.h();
        if (this.t != iH) {
            this.t = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, baVar.e());
        t9.g(this.c, baVar);
    }

    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        return false;
    }

    @Override // defpackage.v1
    public void n(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.F();
        }
    }

    public r1 o() {
        return this.g.w();
    }

    public int p() {
        return this.c.getChildCount();
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

    public w1 w(ViewGroup viewGroup) {
        if (this.b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.h.inflate(R$layout.design_navigation_menu, viewGroup, false);
            this.b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.b));
            if (this.g == null) {
                this.g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.b.setOverScrollMode(i2);
            }
            this.c = (LinearLayout) this.h.inflate(R$layout.design_navigation_item_header, (ViewGroup) this.b, false);
            this.b.setAdapter(this.g);
        }
        return this.b;
    }

    public View x(int i2) {
        View viewInflate = this.h.inflate(i2, (ViewGroup) this.c, false);
        g(viewInflate);
        return viewInflate;
    }

    public void y(boolean z) {
        if (this.r != z) {
            this.r = z;
            L();
        }
    }

    public void z(r1 r1Var) {
        this.g.D(r1Var);
    }
}
