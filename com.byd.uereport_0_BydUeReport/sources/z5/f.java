package z5;

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
import o0.b0;
import o0.t;
import p0.c;
import q.m;
import q.r;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f implements q.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public NavigationMenuView f9552d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinearLayout f9553e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public m.a f9554f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q.g f9555g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9556h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f9557i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public LayoutInflater f9558j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9559k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f9560l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f9561m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f9562n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f9563o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9564p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9565q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9566r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f9567s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f9569u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f9570v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f9571w;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f9568t = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f9572x = -1;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final View.OnClickListener f9573y = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z7 = true;
            f.this.K(true);
            q.i itemData = ((NavigationMenuItemView) view).getItemData();
            f fVar = f.this;
            boolean zO = fVar.f9555g.O(itemData, fVar, 0);
            if (itemData != null && itemData.isCheckable() && zO) {
                f.this.f9557i.j(itemData);
            } else {
                z7 = false;
            }
            f.this.K(false);
            if (z7) {
                f.this.g(false);
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<e> f9575a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public q.i f9576b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f9577c;

        public c() {
            h();
        }

        public final void a(int i8, int i9) {
            while (i8 < i9) {
                ((g) this.f9575a.get(i8)).f9582b = true;
                i8++;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            q.i iVar = this.f9576b;
            if (iVar != null) {
                bundle.putInt("android:menu:checked", iVar.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f9575a.size();
            for (int i8 = 0; i8 < size; i8++) {
                e eVar = this.f9575a.get(i8);
                if (eVar instanceof g) {
                    q.i iVarA = ((g) eVar).a();
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

        public q.i c() {
            return this.f9576b;
        }

        public int d() {
            int i8 = f.this.f9553e.getChildCount() == 0 ? 0 : 1;
            for (int i9 = 0; i9 < f.this.f9557i.getItemCount(); i9++) {
                if (f.this.f9557i.getItemViewType(i9) == 0) {
                    i8++;
                }
            }
            return i8;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(l lVar, int i8) {
            int itemViewType = getItemViewType(i8);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    ((TextView) lVar.itemView).setText(((g) this.f9575a.get(i8)).a().getTitle());
                    return;
                } else {
                    if (itemViewType != 2) {
                        return;
                    }
                    C0155f c0155f = (C0155f) this.f9575a.get(i8);
                    lVar.itemView.setPadding(0, c0155f.b(), 0, c0155f.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.itemView;
            navigationMenuItemView.setIconTintList(f.this.f9562n);
            f fVar = f.this;
            if (fVar.f9560l) {
                navigationMenuItemView.setTextAppearance(fVar.f9559k);
            }
            ColorStateList colorStateList = f.this.f9561m;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = f.this.f9563o;
            t.o0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f9575a.get(i8);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.f9582b);
            navigationMenuItemView.setHorizontalPadding(f.this.f9564p);
            navigationMenuItemView.setIconPadding(f.this.f9565q);
            f fVar2 = f.this;
            if (fVar2.f9567s) {
                navigationMenuItemView.setIconSize(fVar2.f9566r);
            }
            navigationMenuItemView.setMaxLines(f.this.f9569u);
            navigationMenuItemView.e(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public l onCreateViewHolder(ViewGroup viewGroup, int i8) {
            if (i8 == 0) {
                f fVar = f.this;
                return new i(fVar.f9558j, viewGroup, fVar.f9573y);
            }
            if (i8 == 1) {
                return new k(f.this.f9558j, viewGroup);
            }
            if (i8 == 2) {
                return new j(f.this.f9558j, viewGroup);
            }
            if (i8 != 3) {
                return null;
            }
            return new b(f.this.f9553e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void onViewRecycled(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.itemView).D();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return this.f9575a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long getItemId(int i8) {
            return i8;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i8) {
            e eVar = this.f9575a.get(i8);
            if (eVar instanceof C0155f) {
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

        public final void h() {
            if (this.f9577c) {
                return;
            }
            boolean z7 = true;
            this.f9577c = true;
            this.f9575a.clear();
            this.f9575a.add(new d());
            int i8 = -1;
            int size = f.this.f9555g.G().size();
            int i9 = 0;
            boolean z8 = false;
            int size2 = 0;
            while (i9 < size) {
                q.i iVar = f.this.f9555g.G().get(i9);
                if (iVar.isChecked()) {
                    j(iVar);
                }
                if (iVar.isCheckable()) {
                    iVar.s(false);
                }
                if (iVar.hasSubMenu()) {
                    SubMenu subMenu = iVar.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i9 != 0) {
                            this.f9575a.add(new C0155f(f.this.f9571w, 0));
                        }
                        this.f9575a.add(new g(iVar));
                        int size3 = this.f9575a.size();
                        int size4 = subMenu.size();
                        int i10 = 0;
                        boolean z9 = false;
                        while (i10 < size4) {
                            q.i iVar2 = (q.i) subMenu.getItem(i10);
                            if (iVar2.isVisible()) {
                                if (!z9 && iVar2.getIcon() != null) {
                                    z9 = z7;
                                }
                                if (iVar2.isCheckable()) {
                                    iVar2.s(false);
                                }
                                if (iVar.isChecked()) {
                                    j(iVar);
                                }
                                this.f9575a.add(new g(iVar2));
                            }
                            i10++;
                            z7 = true;
                        }
                        if (z9) {
                            a(size3, this.f9575a.size());
                        }
                    }
                } else {
                    int groupId = iVar.getGroupId();
                    if (groupId != i8) {
                        size2 = this.f9575a.size();
                        z8 = iVar.getIcon() != null;
                        if (i9 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f9575a;
                            int i11 = f.this.f9571w;
                            arrayList.add(new C0155f(i11, i11));
                        }
                    } else if (!z8 && iVar.getIcon() != null) {
                        a(size2, this.f9575a.size());
                        z8 = true;
                    }
                    g gVar = new g(iVar);
                    gVar.f9582b = z8;
                    this.f9575a.add(gVar);
                    i8 = groupId;
                }
                i9++;
                z7 = true;
            }
            this.f9577c = false;
        }

        public void i(Bundle bundle) {
            q.i iVarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            q.i iVarA2;
            int i8 = bundle.getInt("android:menu:checked", 0);
            if (i8 != 0) {
                this.f9577c = true;
                int size = this.f9575a.size();
                int i9 = 0;
                while (true) {
                    if (i9 >= size) {
                        break;
                    }
                    e eVar = this.f9575a.get(i9);
                    if ((eVar instanceof g) && (iVarA2 = ((g) eVar).a()) != null && iVarA2.getItemId() == i8) {
                        j(iVarA2);
                        break;
                    }
                    i9++;
                }
                this.f9577c = false;
                h();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f9575a.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    e eVar2 = this.f9575a.get(i10);
                    if ((eVar2 instanceof g) && (iVarA = ((g) eVar2).a()) != null && (actionView = iVarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(iVarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void j(q.i iVar) {
            if (this.f9576b == iVar || !iVar.isCheckable()) {
                return;
            }
            q.i iVar2 = this.f9576b;
            if (iVar2 != null) {
                iVar2.setChecked(false);
            }
            this.f9576b = iVar;
            iVar.setChecked(true);
        }

        public void k(boolean z7) {
            this.f9577c = z7;
        }

        public void l() {
            h();
            notifyDataSetChanged();
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: renamed from: z5.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class C0155f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9579a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f9580b;

        public C0155f(int i8, int i9) {
            this.f9579a = i8;
            this.f9580b = i9;
        }

        public int a() {
            return this.f9580b;
        }

        public int b() {
            return this.f9579a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q.i f9581a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f9582b;

        public g(q.i iVar) {
            this.f9581a = iVar;
        }

        public q.i a() {
            return this.f9581a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends c1.l {
        public h(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // c1.l, o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            cVar.e0(c.b.a(f.this.f9557i.d(), 0, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
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

    public void A(int i8) {
        this.f9556h = i8;
    }

    public void B(Drawable drawable) {
        this.f9563o = drawable;
        g(false);
    }

    public void C(int i8) {
        this.f9564p = i8;
        g(false);
    }

    public void D(int i8) {
        this.f9565q = i8;
        g(false);
    }

    public void E(int i8) {
        if (this.f9566r != i8) {
            this.f9566r = i8;
            this.f9567s = true;
            g(false);
        }
    }

    public void F(ColorStateList colorStateList) {
        this.f9562n = colorStateList;
        g(false);
    }

    public void G(int i8) {
        this.f9569u = i8;
        g(false);
    }

    public void H(int i8) {
        this.f9559k = i8;
        this.f9560l = true;
        g(false);
    }

    public void I(ColorStateList colorStateList) {
        this.f9561m = colorStateList;
        g(false);
    }

    public void J(int i8) {
        this.f9572x = i8;
        NavigationMenuView navigationMenuView = this.f9552d;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i8);
        }
    }

    public void K(boolean z7) {
        c cVar = this.f9557i;
        if (cVar != null) {
            cVar.k(z7);
        }
    }

    public final void L() {
        int i8 = (this.f9553e.getChildCount() == 0 && this.f9568t) ? this.f9570v : 0;
        NavigationMenuView navigationMenuView = this.f9552d;
        navigationMenuView.setPadding(0, i8, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // q.m
    public void a(q.g gVar, boolean z7) {
        m.a aVar = this.f9554f;
        if (aVar != null) {
            aVar.a(gVar, z7);
        }
    }

    @Override // q.m
    public void b(Context context, q.g gVar) {
        this.f9558j = LayoutInflater.from(context);
        this.f9555g = gVar;
        this.f9571w = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f9552d.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f9557i.i(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.f9553e.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    public void e(View view) {
        this.f9553e.addView(view);
        NavigationMenuView navigationMenuView = this.f9552d;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // q.m
    public boolean f(r rVar) {
        return false;
    }

    @Override // q.m
    public void g(boolean z7) {
        c cVar = this.f9557i;
        if (cVar != null) {
            cVar.l();
        }
    }

    public void h(b0 b0Var) {
        int iH = b0Var.h();
        if (this.f9570v != iH) {
            this.f9570v = iH;
            L();
        }
        NavigationMenuView navigationMenuView = this.f9552d;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, b0Var.e());
        t.h(this.f9553e, b0Var);
    }

    @Override // q.m
    public int i() {
        return this.f9556h;
    }

    @Override // q.m
    public boolean j() {
        return false;
    }

    @Override // q.m
    public Parcelable k() {
        Bundle bundle = new Bundle();
        if (this.f9552d != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f9552d.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f9557i;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.b());
        }
        if (this.f9553e != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.f9553e.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // q.m
    public boolean l(q.g gVar, q.i iVar) {
        return false;
    }

    @Override // q.m
    public boolean m(q.g gVar, q.i iVar) {
        return false;
    }

    public q.i o() {
        return this.f9557i.c();
    }

    public int p() {
        return this.f9553e.getChildCount();
    }

    public Drawable q() {
        return this.f9563o;
    }

    public int r() {
        return this.f9564p;
    }

    public int s() {
        return this.f9565q;
    }

    public int t() {
        return this.f9569u;
    }

    public ColorStateList u() {
        return this.f9561m;
    }

    public ColorStateList v() {
        return this.f9562n;
    }

    public q.n w(ViewGroup viewGroup) {
        if (this.f9552d == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.f9558j.inflate(R$layout.design_navigation_menu, viewGroup, false);
            this.f9552d = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.f9552d));
            if (this.f9557i == null) {
                this.f9557i = new c();
            }
            int i8 = this.f9572x;
            if (i8 != -1) {
                this.f9552d.setOverScrollMode(i8);
            }
            this.f9553e = (LinearLayout) this.f9558j.inflate(R$layout.design_navigation_item_header, (ViewGroup) this.f9552d, false);
            this.f9552d.setAdapter(this.f9557i);
        }
        return this.f9552d;
    }

    public View x(int i8) {
        View viewInflate = this.f9558j.inflate(i8, (ViewGroup) this.f9553e, false);
        e(viewInflate);
        return viewInflate;
    }

    public void y(boolean z7) {
        if (this.f9568t != z7) {
            this.f9568t = z7;
            L();
        }
    }

    public void z(q.i iVar) {
        this.f9557i.j(iVar);
    }
}
