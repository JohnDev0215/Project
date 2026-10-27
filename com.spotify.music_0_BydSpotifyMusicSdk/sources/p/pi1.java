package p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class pi1 extends BaseAdapter {
    public final si1 c;
    public int e = -1;
    public boolean k;
    public final boolean l;
    public final LayoutInflater m;
    public final int n;

    public pi1(si1 si1Var, LayoutInflater layoutInflater, boolean z, int i) {
        this.l = z;
        this.m = layoutInflater;
        this.c = si1Var;
        this.n = i;
        a();
    }

    public final void a() {
        si1 si1Var = this.c;
        xi1 xi1Var = si1Var.v;
        if (xi1Var != null) {
            si1Var.i();
            ArrayList arrayList = si1Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((xi1) arrayList.get(i)) == xi1Var) {
                    this.e = i;
                    return;
                }
            }
        }
        this.e = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final xi1 getItem(int i) {
        ArrayList arrayListL;
        boolean z = this.l;
        si1 si1Var = this.c;
        if (z) {
            si1Var.i();
            arrayListL = si1Var.j;
        } else {
            arrayListL = si1Var.l();
        }
        int i2 = this.e;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (xi1) arrayListL.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListL;
        boolean z = this.l;
        si1 si1Var = this.c;
        if (z) {
            si1Var.i();
            arrayListL = si1Var.j;
        } else {
            arrayListL = si1Var.l();
        }
        return this.e < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.m.inflate(this.n, viewGroup, false);
        }
        int i2 = getItem(i).b;
        int i3 = i - 1;
        int i4 = i3 >= 0 ? getItem(i3).b : i2;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.c.m() && i2 != i4) {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        pj1 pj1Var = (pj1) view;
        if (this.k) {
            listMenuItemView.setForceShowIcon(true);
        }
        pj1Var.c(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
