package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.x;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public final class cl1 implements MenuPresenter {
    public int A;
    public int B;
    public int C;
    public NavigationMenuView e;
    public LinearLayout f;
    public MenuBuilder g;
    public int h;
    public c i;
    public LayoutInflater j;
    public ColorStateList l;
    public ColorStateList n;
    public ColorStateList o;
    public Drawable p;
    public RippleDrawable q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public boolean y;
    public int k = 0;
    public int m = 0;
    public boolean z = true;
    public int D = -1;
    public final a E = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
            cl1 cl1Var = cl1.this;
            c cVar = cl1Var.i;
            boolean z = true;
            if (cVar != null) {
                cVar.j = true;
            }
            MenuItemImpl itemData = navigationMenuItemView.getItemData();
            boolean zQ = cl1Var.g.q(itemData, cl1Var, 0);
            if (itemData != null && itemData.isCheckable() && zQ) {
                cl1Var.i.E(itemData);
            } else {
                z = false;
            }
            c cVar2 = cl1Var.i;
            if (cVar2 != null) {
                cVar2.j = false;
            }
            if (z) {
                cl1Var.d(false);
            }
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class b extends l {
        public b(LinearLayout linearLayout) {
            super(linearLayout);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class c extends RecyclerView.e<l> {
        public final ArrayList<e> h = new ArrayList<>();
        public MenuItemImpl i;
        public boolean j;

        public c() {
            D();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void D() {
            boolean z;
            if (this.j) {
                return;
            }
            this.j = true;
            ArrayList<e> arrayList = this.h;
            arrayList.clear();
            arrayList.add(new d());
            cl1 cl1Var = cl1.this;
            int size = cl1Var.g.l().size();
            boolean z2 = false;
            int i = -1;
            int i2 = 0;
            boolean z3 = false;
            int size2 = 0;
            while (i2 < size) {
                MenuItemImpl menuItemImpl = cl1Var.g.l().get(i2);
                if (menuItemImpl.isChecked()) {
                    E(menuItemImpl);
                }
                if (menuItemImpl.isCheckable()) {
                    menuItemImpl.f(z2);
                }
                if (menuItemImpl.hasSubMenu()) {
                    SubMenuBuilder subMenuBuilder = menuItemImpl.o;
                    if (subMenuBuilder.hasVisibleItems()) {
                        if (i2 != 0) {
                            arrayList.add(new f(cl1Var.C, z2 ? 1 : 0));
                        }
                        arrayList.add(new g(menuItemImpl));
                        int size3 = subMenuBuilder.size();
                        int i3 = z2 ? 1 : 0;
                        int i4 = i3;
                        while (i3 < size3) {
                            MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenuBuilder.getItem(i3);
                            if (menuItemImpl2.isVisible()) {
                                if (i4 == 0 && menuItemImpl2.getIcon() != null) {
                                    i4 = 1;
                                }
                                if (menuItemImpl2.isCheckable()) {
                                    menuItemImpl2.f(z2);
                                }
                                if (menuItemImpl.isChecked()) {
                                    E(menuItemImpl);
                                }
                                arrayList.add(new g(menuItemImpl2));
                            }
                            i3++;
                            z2 = false;
                        }
                        if (i4 != 0) {
                            int size4 = arrayList.size();
                            for (int size5 = arrayList.size(); size5 < size4; size5++) {
                                ((g) arrayList.get(size5)).b = true;
                            }
                        }
                    }
                    z = true;
                } else {
                    int i5 = menuItemImpl.b;
                    if (i5 != i) {
                        size2 = arrayList.size();
                        z3 = menuItemImpl.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            int i6 = cl1Var.C;
                            arrayList.add(new f(i6, i6));
                        }
                    } else {
                        if (!z3 && menuItemImpl.getIcon() != null) {
                            int size6 = arrayList.size();
                            for (int i7 = size2; i7 < size6; i7++) {
                                ((g) arrayList.get(i7)).b = true;
                            }
                            z = true;
                            z3 = true;
                        }
                        g gVar = new g(menuItemImpl);
                        gVar.b = z3;
                        arrayList.add(gVar);
                        i = i5;
                    }
                    z = true;
                    g gVar2 = new g(menuItemImpl);
                    gVar2.b = z3;
                    arrayList.add(gVar2);
                    i = i5;
                }
                i2++;
                z2 = false;
            }
            this.j = z2 ? 1 : 0;
        }

        public final void E(MenuItemImpl menuItemImpl) {
            if (this.i == menuItemImpl || !menuItemImpl.isCheckable()) {
                return;
            }
            MenuItemImpl menuItemImpl2 = this.i;
            if (menuItemImpl2 != null) {
                menuItemImpl2.setChecked(false);
            }
            this.i = menuItemImpl;
            menuItemImpl.setChecked(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            return this.h.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final long l(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int m(int i) {
            e eVar = this.h.get(i);
            if (eVar instanceof f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a.hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            int iM = m(i);
            ArrayList<e> arrayList = this.h;
            View view = ((l) a0Var).e;
            cl1 cl1Var = cl1.this;
            if (iM != 0) {
                if (iM != 1) {
                    if (iM == 2) {
                        f fVar = (f) arrayList.get(i);
                        view.setPadding(cl1Var.v, fVar.a, cl1Var.w, fVar.b);
                        return;
                    } else {
                        if (iM != 3) {
                            return;
                        }
                        om3.k(view, new dl1(this, i, true));
                        return;
                    }
                }
                TextView textView = (TextView) view;
                textView.setText(((g) arrayList.get(i)).a.e);
                int i2 = cl1Var.k;
                if (i2 != 0) {
                    textView.setTextAppearance(i2);
                }
                textView.setPadding(cl1Var.x, textView.getPaddingTop(), 0, textView.getPaddingBottom());
                ColorStateList colorStateList = cl1Var.l;
                if (colorStateList != null) {
                    textView.setTextColor(colorStateList);
                }
                om3.k(textView, new dl1(this, i, true));
                return;
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
            navigationMenuItemView.setIconTintList(cl1Var.o);
            int i3 = cl1Var.m;
            if (i3 != 0) {
                navigationMenuItemView.setTextAppearance(i3);
            }
            ColorStateList colorStateList2 = cl1Var.n;
            if (colorStateList2 != null) {
                navigationMenuItemView.setTextColor(colorStateList2);
            }
            Drawable drawable = cl1Var.p;
            Drawable drawableNewDrawable = drawable != null ? drawable.getConstantState().newDrawable() : null;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(navigationMenuItemView, drawableNewDrawable);
            RippleDrawable rippleDrawable = cl1Var.q;
            if (rippleDrawable != null) {
                navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
            }
            g gVar = (g) arrayList.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.b);
            int i4 = cl1Var.r;
            int i5 = cl1Var.s;
            navigationMenuItemView.setPadding(i4, i5, i4, i5);
            navigationMenuItemView.setIconPadding(cl1Var.t);
            if (cl1Var.y) {
                navigationMenuItemView.setIconSize(cl1Var.u);
            }
            navigationMenuItemView.setMaxLines(cl1Var.A);
            navigationMenuItemView.c(gVar.a);
            om3.k(navigationMenuItemView, new dl1(this, i, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            RecyclerView.a0 iVar;
            cl1 cl1Var = cl1.this;
            if (i == 0) {
                iVar = new i(cl1Var.j, recyclerView, cl1Var.E);
            } else if (i == 1) {
                iVar = new k(cl1Var.j, recyclerView);
            } else if (i == 2) {
                iVar = new j(cl1Var.j, recyclerView);
            } else {
                if (i != 3) {
                    return null;
                }
                iVar = new b(cl1Var.f);
            }
            return iVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void z(RecyclerView.a0 a0Var) {
            l lVar = (l) a0Var;
            if (lVar instanceof i) {
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.e;
                FrameLayout frameLayout = navigationMenuItemView.D;
                if (frameLayout != null) {
                    frameLayout.removeAllViews();
                }
                navigationMenuItemView.C.setCompoundDrawables(null, null, null, null);
            }
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
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {
        public final MenuItemImpl a;
        public boolean b;

        public g(MenuItemImpl menuItemImpl) {
            this.a = menuItemImpl;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class h extends x {
        public h(NavigationMenuView navigationMenuView) {
            super(navigationMenuView);
        }

        @Override // androidx.recyclerview.widget.x, defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            int i2;
            super.d(view, accessibilityNodeInfoCompat);
            cl1 cl1Var = cl1.this;
            if (cl1Var.f.getChildCount() == 0) {
                i = 0;
                i2 = 0;
            } else {
                i = 0;
                i2 = 1;
            }
            while (i < cl1Var.i.k()) {
                int iM = cl1Var.i.m(i);
                if (iM == 0 || iM == 1) {
                    i2++;
                }
                i++;
            }
            accessibilityNodeInfoCompat.a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i2, 1, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends l {
        /* JADX WARN: Illegal instructions before constructor call */
        public i(LayoutInflater layoutInflater, RecyclerView recyclerView, a aVar) {
            View viewInflate = layoutInflater.inflate(R$layout.design_navigation_item, (ViewGroup) recyclerView, false);
            super(viewInflate);
            viewInflate.setOnClickListener(aVar);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class j extends l {
        public j(LayoutInflater layoutInflater, RecyclerView recyclerView) {
            super(layoutInflater.inflate(R$layout.design_navigation_item_separator, (ViewGroup) recyclerView, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class k extends l {
        public k(LayoutInflater layoutInflater, RecyclerView recyclerView) {
            super(layoutInflater.inflate(R$layout.design_navigation_item_subheader, (ViewGroup) recyclerView, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static abstract class l extends RecyclerView.a0 {
        public l(View view) {
            super(view);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z) {
        c cVar = this.i;
        if (cVar != null) {
            cVar.D();
            cVar.o();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean g(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final int getId() {
        return this.h;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void h(Context context, MenuBuilder menuBuilder) {
        this.j = LayoutInflater.from(context);
        this.g = menuBuilder;
        this.C = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
        MenuItemImpl menuItemImpl;
        View actionView;
        ParcelableSparseArray parcelableSparseArray;
        MenuItemImpl menuItemImpl2;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.e.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                c cVar = this.i;
                cVar.getClass();
                int i2 = bundle2.getInt("android:menu:checked", 0);
                ArrayList<e> arrayList = cVar.h;
                if (i2 != 0) {
                    cVar.j = true;
                    int size = arrayList.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        }
                        e eVar = arrayList.get(i3);
                        if ((eVar instanceof g) && (menuItemImpl2 = ((g) eVar).a) != null && menuItemImpl2.a == i2) {
                            cVar.E(menuItemImpl2);
                            break;
                        }
                        i3++;
                    }
                    cVar.j = false;
                    cVar.D();
                }
                SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
                if (sparseParcelableArray2 != null) {
                    int size2 = arrayList.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        e eVar2 = arrayList.get(i4);
                        if ((eVar2 instanceof g) && (menuItemImpl = ((g) eVar2).a) != null && (actionView = menuItemImpl.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray2.get(menuItemImpl.a)) != null) {
                            actionView.restoreHierarchyState(parcelableSparseArray);
                        }
                    }
                }
            }
            SparseArray<Parcelable> sparseParcelableArray3 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray3 != null) {
                this.f.restoreHierarchyState(sparseParcelableArray3);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean k(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        Bundle bundle = new Bundle();
        if (this.e != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.e.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.i;
        if (cVar != null) {
            cVar.getClass();
            Bundle bundle2 = new Bundle();
            MenuItemImpl menuItemImpl = cVar.i;
            if (menuItemImpl != null) {
                bundle2.putInt("android:menu:checked", menuItemImpl.a);
            }
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            ArrayList<e> arrayList = cVar.h;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = arrayList.get(i2);
                if (eVar instanceof g) {
                    MenuItemImpl menuItemImpl2 = ((g) eVar).a;
                    View actionView = menuItemImpl2 != null ? menuItemImpl2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray2.put(menuItemImpl2.a, parcelableSparseArray);
                    }
                }
            }
            bundle2.putSparseParcelableArray("android:menu:action_views", sparseArray2);
            bundle.putBundle("android:menu:adapter", bundle2);
        }
        if (this.f != null) {
            SparseArray<Parcelable> sparseArray3 = new SparseArray<>();
            this.f.saveHierarchyState(sparseArray3);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray3);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean m(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z) {
    }
}
