package c.b.a.a.q;

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
import b.b.e.j.m;
import b.b.e.j.r;
import b.g.j.b0;
import b.g.j.c0.c;
import b.g.j.t;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import java.util.ArrayList;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements b.b.e.j.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NavigationMenuView f1781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f1782c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m.a f1783d;
    public b.b.e.j.g e;
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
            f.this.K(true);
            b.b.e.j.i itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean zO = fVar.e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.g.D(itemData);
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
    public static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class c extends RecyclerView.g<l> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<e> f1785c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.b.e.j.i f1786d;
        public boolean e;

        public c() {
            B();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void q(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f443b).D();
            }
        }

        public final void B() {
            if (this.e) {
                return;
            }
            boolean z = true;
            this.e = true;
            this.f1785c.clear();
            this.f1785c.add(new d());
            int i = -1;
            int size = f.this.e.G().size();
            int i2 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i2 < size) {
                b.b.e.j.i iVar = f.this.e.G().get(i2);
                if (iVar.isChecked()) {
                    D(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.t(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.f1785c.add(new C0056f(f.this.u, 0));
                        }
                        this.f1785c.add(new g(iVar));
                        int size3 = this.f1785c.size();
                        int size4 = subMenu.size();
                        int i3 = 0;
                        boolean z3 = false;
                        while (i3 < size4) {
                            b.b.e.j.i iVar2 = (b.b.e.j.i) subMenu.getItem(i3);
                            if (iVar2.isVisible()) {
                                if (!z3 && iVar2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (iVar2.isCheckable()) {
                                    iVar2.t(false);
                                }
                                if (iVar.isChecked()) {
                                    D(iVar);
                                }
                                this.f1785c.add(new g(iVar2));
                            }
                            i3++;
                            z = true;
                        }
                        if (z3) {
                            u(size3, this.f1785c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i) {
                        size2 = this.f1785c.size();
                        z2 = iVar.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f1785c;
                            int i4 = f.this.u;
                            arrayList.add(new C0056f(i4, i4));
                        }
                    } else if (!z2 && iVar.getIcon() != null) {
                        u(size2, this.f1785c.size());
                        z2 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f1790b = z2;
                    this.f1785c.add(gVar);
                    i = groupId;
                }
                i2++;
                z = true;
            }
            this.e = false;
        }

        public void C(Bundle bundle) {
            b.b.e.j.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            b.b.e.j.i iVarA2;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.e = true;
                int size = this.f1785c.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f1785c.get(i2);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i) {
                        D(iVarA2);
                        break;
                    }
                    i2++;
                }
                this.e = false;
                B();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f1785c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f1785c.get(i3);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void D(b.b.e.j.i iVar) {
            if (this.f1786d == iVar || !iVar.isCheckable()) {
                return;
            }
            b.b.e.j.i iVar2 = this.f1786d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f1786d = iVar;
            iVar.setChecked(true);
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
            return this.f1785c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i) {
            e eVar = this.f1785c.get(i);
            if (eVar instanceof C0056f) {
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
                ((g) this.f1785c.get(i)).f1790b = true;
                i++;
            }
        }

        public Bundle v() {
            Bundle bundle = new Bundle();
            b.b.e.j.i iVar = this.f1786d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f1785c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f1785c.get(i);
                if (eVar instanceof g) {
                    b.b.e.j.i iVarA = ((g) eVar).a();
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

        public b.b.e.j.i w() {
            return this.f1786d;
        }

        public int x() {
            int i = f.this.f1782c.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < f.this.g.c(); i2++) {
                if (f.this.g.e(i2) == 0) {
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
                    ((TextView) lVar.f443b).setText(((g) this.f1785c.get(i)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0056f c0056f = (C0056f) this.f1785c.get(i);
                    lVar.f443b.setPadding(0, c0056f.b(), 0, c0056f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f443b;
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
            g gVar = (g) this.f1785c.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f1790b);
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
        public l l(ViewGroup viewGroup, int i) {
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
            return new b(f.this.f1782c);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: renamed from: c.b.a.a.q.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class C0056f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1788b;

        public C0056f(int i, int i2) {
            this.f1787a = i;
            this.f1788b = i2;
        }

        public int a() {
            return this.f1788b;
        }

        public int b() {
            return this.f1787a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.b.e.j.i f1789a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1790b;

        public g(b.b.e.j.i iVar) {
            this.f1789a = iVar;
        }

        public b.b.e.j.i a() {
            return this.f1789a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends b.o.a.l {
        public h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // b.o.a.l, b.g.j.a
        public void g(View view, b.g.j.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(c.b.a(f.this.g.x(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false));
            this.f443b.setOnClickListener(onClickListener);
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
        NavigationMenuView navigationMenuView = this.f1781b;
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
        int i2 = (this.f1782c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f1781b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // b.b.e.j.m
    public void a(b.b.e.j.g gVar, boolean z) {
        m.a aVar = this.f1783d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.e.j.m
    public int c() {
        return this.f;
    }

    @Override // b.b.e.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.e.j.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f1781b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f1781b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.v());
        }
        if (this.f1782c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f1782c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // b.b.e.j.m
    public void f(Context context, b.b.e.j.g gVar) {
        this.h = LayoutInflater.from(context);
        this.e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    public void g(View view) {
        this.f1782c.addView(view);
        NavigationMenuView navigationMenuView = this.f1781b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // b.b.e.j.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f1781b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.g.C(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f1782c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // b.b.e.j.m
    public boolean i(b.b.e.j.g gVar, b.b.e.j.i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public boolean j(b.b.e.j.g gVar, b.b.e.j.i iVar) {
        return false;
    }

    public void l(b0 b0Var) {
        int iH = b0Var.h();
        if (this.t != iH) {
            this.t = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.f1781b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.g(this.f1782c, b0Var);
    }

    @Override // b.b.e.j.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public void n(boolean z) {
        c cVar = this.g;
        if (cVar != null) {
            cVar.F();
        }
    }

    public b.b.e.j.i o() {
        return this.g.w();
    }

    public int p() {
        return this.f1782c.getChildCount();
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

    public b.b.e.j.n w(ViewGroup viewGroup) {
        if (this.f1781b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.h.inflate(R$layout.design_navigation_menu, viewGroup, false);
            this.f1781b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f1781b));
            if (this.g == null) {
                this.g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f1781b.setOverScrollMode(i2);
            }
            this.f1782c = (LinearLayout) this.h.inflate(R$layout.design_navigation_item_header, (ViewGroup) this.f1781b, false);
            this.f1781b.setAdapter(this.g);
        }
        return this.f1781b;
    }

    public View x(int i2) {
        View viewInflate = this.h.inflate(i2, (ViewGroup) this.f1782c, false);
        g(viewInflate);
        return viewInflate;
    }

    public void y(boolean z) {
        if (this.r != z) {
            this.r = z;
            L();
        }
    }

    public void z(b.b.e.j.i iVar) {
        this.g.D(iVar);
    }
}
