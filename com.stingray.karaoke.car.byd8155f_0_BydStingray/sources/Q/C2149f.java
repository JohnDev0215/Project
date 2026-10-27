package q;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: renamed from: q.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2149f extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MenuC2151h f21044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21045b = -1;
    public boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayoutInflater f21046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f21047f;

    public C2149f(MenuC2151h menuC2151h, LayoutInflater layoutInflater, boolean z6, int i7) {
        this.d = z6;
        this.f21046e = layoutInflater;
        this.f21044a = menuC2151h;
        this.f21047f = i7;
        a();
    }

    public final void a() {
        MenuC2151h menuC2151h = this.f21044a;
        MenuItemC2152i menuItemC2152i = menuC2151h.f21064t;
        if (menuItemC2152i != null) {
            menuC2151h.i();
            ArrayList arrayList = menuC2151h.f21056j;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (((MenuItemC2152i) arrayList.get(i7)) == menuItemC2152i) {
                    this.f21045b = i7;
                    return;
                }
            }
        }
        this.f21045b = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final MenuItemC2152i getItem(int i7) {
        ArrayList arrayListK;
        MenuC2151h menuC2151h = this.f21044a;
        if (this.d) {
            menuC2151h.i();
            arrayListK = menuC2151h.f21056j;
        } else {
            arrayListK = menuC2151h.k();
        }
        int i10 = this.f21045b;
        if (i10 >= 0 && i7 >= i10) {
            i7++;
        }
        return (MenuItemC2152i) arrayListK.get(i7);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListK;
        MenuC2151h menuC2151h = this.f21044a;
        if (this.d) {
            menuC2151h.i();
            arrayListK = menuC2151h.f21056j;
        } else {
            arrayListK = menuC2151h.k();
        }
        return this.f21045b < 0 ? arrayListK.size() : arrayListK.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        boolean z6 = false;
        if (view == null) {
            view = this.f21046e.inflate(this.f21047f, viewGroup, false);
        }
        int i10 = getItem(i7).f21068b;
        int i11 = i7 - 1;
        int i12 = i11 >= 0 ? getItem(i11).f21068b : i10;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.f21044a.l() && i10 != i12) {
            z6 = true;
        }
        listMenuItemView.setGroupDividerEnabled(z6);
        InterfaceC2158o interfaceC2158o = (InterfaceC2158o) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        interfaceC2158o.b(getItem(i7));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
