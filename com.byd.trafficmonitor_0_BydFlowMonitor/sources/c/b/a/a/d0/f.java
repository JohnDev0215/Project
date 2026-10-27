package c.b.a.a.d0;

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
import b.b.p.j.m;
import b.b.p.j.r;
import b.h.m.b0;
import b.h.m.c0.c;
import b.h.m.t;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import java.util.ArrayList;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements b.b.p.j.m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NavigationMenuView f3758b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f3759c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m.a f3760d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.b.p.j.g f3761e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3762f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f3763g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public LayoutInflater f3764h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3765i;
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
            b.b.p.j.i itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean zO = fVar.f3761e.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.f3763g.F(itemData);
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
        public final ArrayList<e> f3767c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.b.p.j.i f3768d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3769e;

        public c() {
            D();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void l(l lVar, int i2) {
            int iE = e(i2);
            if (iE != 0) {
                if (iE == 1) {
                    ((TextView) lVar.f1185b).setText(((g) this.f3767c.get(i2)).a().getTitle());
                    return;
                } else {
                    if (iE != 2) {
                        return;
                    }
                    C0082f c0082f = (C0082f) this.f3767c.get(i2);
                    lVar.f1185b.setPadding(0, c0082f.b(), 0, c0082f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.f1185b;
            navigationMenuItemView.setIconTintList(f.this.l);
            f fVar = f.this;
            if (fVar.j) {
                navigationMenuItemView.setTextAppearance(fVar.f3765i);
            }
            ColorStateList colorStateList = f.this.k;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.m;
            t.n0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f3767c.get(i2);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f3774b);
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
        public l n(ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                f fVar = f.this;
                return new i(fVar.f3764h, viewGroup, fVar.w);
            }
            if (i2 == 1) {
                return new k(f.this.f3764h, viewGroup);
            }
            if (i2 == 2) {
                return new j(f.this.f3764h, viewGroup);
            }
            if (i2 != 3) {
                return null;
            }
            return new b(f.this.f3759c);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public void s(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.f1185b).D();
            }
        }

        public final void D() {
            if (this.f3769e) {
                return;
            }
            boolean z = true;
            this.f3769e = true;
            this.f3767c.clear();
            this.f3767c.add(new d());
            int i2 = -1;
            int size = f.this.f3761e.G().size();
            int i3 = 0;
            boolean z2 = false;
            int size2 = 0;
            while (i3 < size) {
                b.b.p.j.i iVar = f.this.f3761e.G().get(i3);
                if (iVar.isChecked()) {
                    F(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i3 != 0) {
                            this.f3767c.add(new C0082f(f.this.u, 0));
                        }
                        this.f3767c.add(new g(iVar));
                        int size3 = this.f3767c.size();
                        int size4 = subMenu.size();
                        int i4 = 0;
                        boolean z3 = false;
                        while (i4 < size4) {
                            b.b.p.j.i iVar2 = (b.b.p.j.i) subMenu.getItem(i4);
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
                                this.f3767c.add(new g(iVar2));
                            }
                            i4++;
                            z = true;
                        }
                        if (z3) {
                            w(size3, this.f3767c.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i2) {
                        size2 = this.f3767c.size();
                        z2 = iVar.getIcon() != null;
                        if (i3 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f3767c;
                            int i5 = f.this.u;
                            arrayList.add(new C0082f(i5, i5));
                        }
                    } else if (!z2 && iVar.getIcon() != null) {
                        w(size2, this.f3767c.size());
                        z2 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f3774b = z2;
                    this.f3767c.add(gVar);
                    i2 = groupId;
                }
                i3++;
                z = true;
            }
            this.f3769e = false;
        }

        public void E(Bundle bundle) {
            b.b.p.j.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            b.b.p.j.i iVarA2;
            int i2 = bundle.getInt("android:menu:checked", 0);
            if (i2 != 0) {
                this.f3769e = true;
                int size = this.f3767c.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    }
                    e eVar = this.f3767c.get(i3);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i2) {
                        F(iVarA2);
                        break;
                    }
                    i3++;
                }
                this.f3769e = false;
                D();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f3767c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    e eVar2 = this.f3767c.get(i4);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void F(b.b.p.j.i iVar) {
            if (this.f3768d == iVar || !iVar.isCheckable()) {
                return;
            }
            b.b.p.j.i iVar2 = this.f3768d;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f3768d = iVar;
            iVar.setChecked(true);
        }

        public void G(boolean z) {
            this.f3769e = z;
        }

        public void H() {
            D();
            h();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f3767c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long d(int i2) {
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e(int i2) {
            e eVar = this.f3767c.get(i2);
            if (eVar instanceof C0082f) {
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

        public final void w(int i2, int i3) {
            while (i2 < i3) {
                ((g) this.f3767c.get(i2)).f3774b = true;
                i2++;
            }
        }

        public Bundle x() {
            Bundle bundle = new Bundle();
            b.b.p.j.i iVar = this.f3768d;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f3767c.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = this.f3767c.get(i2);
                if (eVar instanceof g) {
                    b.b.p.j.i iVarA = ((g) eVar).a();
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

        public b.b.p.j.i y() {
            return this.f3768d;
        }

        public int z() {
            int i2 = f.this.f3759c.getChildCount() == 0 ? 0 : 1;
            for (int i3 = 0; i3 < f.this.f3763g.c(); i3++) {
                if (f.this.f3763g.e(i3) == 0) {
                    i2++;
                }
            }
            return i2;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: renamed from: c.b.a.a.d0.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class C0082f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3771a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3772b;

        public C0082f(int i2, int i3) {
            this.f3771a = i2;
            this.f3772b = i3;
        }

        public int a() {
            return this.f3772b;
        }

        public int b() {
            return this.f3771a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.b.p.j.i f3773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3774b;

        public g(b.b.p.j.i iVar) {
            this.f3773a = iVar;
        }

        public b.b.p.j.i a() {
            return this.f3773a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends b.r.d.l {
        public h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // b.r.d.l, b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            cVar.e0(c.b.a(f.this.f3763g.z(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(c.b.a.a.h.design_navigation_item, viewGroup, false));
            this.f1185b.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class j extends l {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.b.a.a.h.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class k extends l {
        public k(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(c.b.a.a.h.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static abstract class l extends RecyclerView.c0 {
        public l(View view) {
            super(view);
        }
    }

    public void A(int i2) {
        this.f3762f = i2;
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
        this.f3765i = i2;
        this.j = true;
        n(false);
    }

    public void I(ColorStateList colorStateList) {
        this.k = colorStateList;
        n(false);
    }

    public void J(int i2) {
        this.v = i2;
        NavigationMenuView navigationMenuView = this.f3758b;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void K(boolean z) {
        c cVar = this.f3763g;
        if (cVar != null) {
            cVar.G(z);
        }
    }

    public final void L() {
        int i2 = (this.f3759c.getChildCount() == 0 && this.r) ? this.t : 0;
        NavigationMenuView navigationMenuView = this.f3758b;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // b.b.p.j.m
    public void a(b.b.p.j.g gVar, boolean z) {
        m.a aVar = this.f3760d;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.m
    public int c() {
        return this.f3762f;
    }

    @Override // b.b.p.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.f3758b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f3758b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f3763g;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.x());
        }
        if (this.f3759c != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f3759c.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // b.b.p.j.m
    public void f(Context context, b.b.p.j.g gVar) {
        this.f3764h = LayoutInflater.from(context);
        this.f3761e = gVar;
        this.u = context.getResources().getDimensionPixelOffset(c.b.a.a.d.design_navigation_separator_vertical_padding);
    }

    public void g(View view) {
        this.f3759c.addView(view);
        NavigationMenuView navigationMenuView = this.f3758b;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f3758b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f3763g.E(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f3759c.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // b.b.p.j.m
    public boolean i(b.b.p.j.g gVar, b.b.p.j.i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public boolean j(b.b.p.j.g gVar, b.b.p.j.i iVar) {
        return false;
    }

    public void l(b0 b0Var) {
        int iH = b0Var.h();
        if (this.t != iH) {
            this.t = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.f3758b;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.g(this.f3759c, b0Var);
    }

    @Override // b.b.p.j.m
    public boolean m(r rVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public void n(boolean z) {
        c cVar = this.f3763g;
        if (cVar != null) {
            cVar.H();
        }
    }

    public b.b.p.j.i o() {
        return this.f3763g.y();
    }

    public int p() {
        return this.f3759c.getChildCount();
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

    public b.b.p.j.n w(ViewGroup viewGroup) {
        if (this.f3758b == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f3764h.inflate(c.b.a.a.h.design_navigation_menu, viewGroup, false);
            this.f3758b = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f3758b));
            if (this.f3763g == null) {
                this.f3763g = new c();
            }
            int i2 = this.v;
            if (i2 != -1) {
                this.f3758b.setOverScrollMode(i2);
            }
            this.f3759c = (LinearLayout) this.f3764h.inflate(c.b.a.a.h.design_navigation_item_header, (ViewGroup) this.f3758b, false);
            this.f3758b.setAdapter(this.f3763g);
        }
        return this.f3758b;
    }

    public View x(int i2) {
        View viewInflate = this.f3764h.inflate(i2, (ViewGroup) this.f3759c, false);
        g(viewInflate);
        return viewInflate;
    }

    public void y(boolean z) {
        if (this.r != z) {
            this.r = z;
            L();
        }
    }

    public void z(b.b.p.j.i iVar) {
        this.f3763g.F(iVar);
    }
}
