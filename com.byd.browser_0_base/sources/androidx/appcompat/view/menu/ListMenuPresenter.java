package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    public Context e;
    public LayoutInflater f;
    public MenuBuilder g;
    public ExpandedMenuView h;
    public final int i = 0;
    public final int j;
    public MenuPresenter.Callback k;
    public MenuAdapter l;

    public class MenuAdapter extends BaseAdapter {
        public int e = -1;

        public MenuAdapter() {
            a();
        }

        public final void a() {
            MenuBuilder menuBuilder = ListMenuPresenter.this.g;
            MenuItemImpl menuItemImpl = menuBuilder.v;
            if (menuItemImpl != null) {
                menuBuilder.i();
                ArrayList<MenuItemImpl> arrayList = menuBuilder.j;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) == menuItemImpl) {
                        this.e = i;
                        return;
                    }
                }
            }
            this.e = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public final MenuItemImpl getItem(int i) {
            ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
            MenuBuilder menuBuilder = listMenuPresenter.g;
            menuBuilder.i();
            ArrayList<MenuItemImpl> arrayList = menuBuilder.j;
            listMenuPresenter.getClass();
            int i2 = i + 0;
            int i3 = this.e;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayList.get(i2);
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
            MenuBuilder menuBuilder = listMenuPresenter.g;
            menuBuilder.i();
            int size = menuBuilder.j.size();
            listMenuPresenter.getClass();
            int i = size + 0;
            return this.e < 0 ? i : i - 1;
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
                view = listMenuPresenter.f.inflate(listMenuPresenter.j, viewGroup, false);
            }
            ((MenuView.ItemView) view).c(getItem(i));
            return view;
        }

        @Override // android.widget.BaseAdapter
        public final void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(Context context, int i) {
        this.j = i;
        this.e = context;
        this.f = LayoutInflater.from(context);
    }

    public final ListAdapter a() {
        if (this.l == null) {
            this.l = new MenuAdapter();
        }
        return this.l;
    }

    public final MenuView b(ViewGroup viewGroup) {
        if (this.h == null) {
            this.h = (ExpandedMenuView) this.f.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.l == null) {
                this.l = new MenuAdapter();
            }
            this.h.setAdapter((ListAdapter) this.l);
            this.h.setOnItemClickListener(this);
        }
        return this.h;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.k;
        if (callback != null) {
            callback.c(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z) {
        MenuAdapter menuAdapter = this.l;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void f(MenuPresenter.Callback callback) {
        this.k = callback;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean g(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final int getId() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void h(Context context, MenuBuilder menuBuilder) {
        int i = this.i;
        if (i != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            this.e = contextThemeWrapper;
            this.f = LayoutInflater.from(contextThemeWrapper);
        } else if (this.e != null) {
            this.e = context;
            if (this.f == null) {
                this.f = LayoutInflater.from(context);
            }
        }
        this.g = menuBuilder;
        MenuAdapter menuAdapter = this.l;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
        SparseArray<Parcelable> sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.h.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean k(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        MenuDialogHelper menuDialogHelper = new MenuDialogHelper(subMenuBuilder);
        Context context = subMenuBuilder.a;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertController.AlertParams alertParams = builder.a;
        ListMenuPresenter listMenuPresenter = new ListMenuPresenter(alertParams.a, R.layout.abc_list_menu_item_layout);
        menuDialogHelper.g = listMenuPresenter;
        listMenuPresenter.k = menuDialogHelper;
        subMenuBuilder.b(listMenuPresenter, context);
        alertParams.p = menuDialogHelper.g.a();
        alertParams.q = menuDialogHelper;
        View view = subMenuBuilder.o;
        if (view != null) {
            alertParams.e = view;
        } else {
            alertParams.c = subMenuBuilder.n;
            alertParams.d = subMenuBuilder.m;
        }
        alertParams.n = menuDialogHelper;
        AlertDialog alertDialogA = builder.a();
        menuDialogHelper.f = alertDialogA;
        alertDialogA.setOnDismissListener(menuDialogHelper);
        WindowManager.LayoutParams attributes = menuDialogHelper.f.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        menuDialogHelper.f.show();
        MenuPresenter.Callback callback = this.k;
        if (callback == null) {
            return true;
        }
        callback.d(subMenuBuilder);
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        if (this.h == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.h;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean m(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.g.q(this.l.getItem(i), this, 0);
    }
}
