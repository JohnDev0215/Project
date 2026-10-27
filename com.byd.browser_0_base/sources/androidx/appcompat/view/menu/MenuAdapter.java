package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MenuAdapter extends BaseAdapter {
    public final MenuBuilder e;
    public int f = -1;
    public boolean g;
    public final boolean h;
    public final LayoutInflater i;
    public final int j;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
        this.h = z;
        this.i = layoutInflater;
        this.e = menuBuilder;
        this.j = i;
        a();
    }

    public final void a() {
        MenuBuilder menuBuilder = this.e;
        MenuItemImpl menuItemImpl = menuBuilder.v;
        if (menuItemImpl != null) {
            menuBuilder.i();
            ArrayList<MenuItemImpl> arrayList = menuBuilder.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i) == menuItemImpl) {
                    this.f = i;
                    return;
                }
            }
        }
        this.f = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final MenuItemImpl getItem(int i) {
        ArrayList<MenuItemImpl> arrayListL;
        boolean z = this.h;
        MenuBuilder menuBuilder = this.e;
        if (z) {
            menuBuilder.i();
            arrayListL = menuBuilder.j;
        } else {
            arrayListL = menuBuilder.l();
        }
        int i2 = this.f;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListL.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList<MenuItemImpl> arrayListL;
        boolean z = this.h;
        MenuBuilder menuBuilder = this.e;
        if (z) {
            menuBuilder.i();
            arrayListL = menuBuilder.j;
        } else {
            arrayListL = menuBuilder.l();
        }
        return this.f < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.i.inflate(this.j, viewGroup, false);
        }
        int i2 = getItem(i).b;
        int i3 = i - 1;
        int i4 = i3 >= 0 ? getItem(i3).b : i2;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.e.m() && i2 != i4) {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.g) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.c(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
