package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import defpackage.w1;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class o1 extends BaseAdapter {
    public p1 b;
    public int c = -1;
    public boolean d;
    public final boolean e;
    public final LayoutInflater f;
    public final int g;

    public o1(p1 p1Var, LayoutInflater layoutInflater, boolean z, int i) {
        this.e = z;
        this.f = layoutInflater;
        this.b = p1Var;
        this.g = i;
        a();
    }

    public void a() {
        r1 r1VarX = this.b.x();
        if (r1VarX != null) {
            ArrayList<r1> arrayListB = this.b.B();
            int size = arrayListB.size();
            for (int i = 0; i < size; i++) {
                if (arrayListB.get(i) == r1VarX) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }

    public p1 b() {
        return this.b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public r1 getItem(int i) {
        ArrayList<r1> arrayListB = this.e ? this.b.B() : this.b.G();
        int i2 = this.c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListB.get(i);
    }

    public void d(boolean z) {
        this.d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c < 0 ? (this.e ? this.b.B() : this.b.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f.inflate(this.g, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.b.H() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        w1.a aVar = (w1.a) view;
        if (this.d) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.e(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
