package b.b.p.j;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import b.b.p.j.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {
    public final int E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f935c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f936d = -1;
    public boolean s;
    public final boolean x;
    public final LayoutInflater y;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i2) {
        this.x = z;
        this.y = layoutInflater;
        this.f935c = gVar;
        this.E3 = i2;
        a();
    }

    public void a() {
        i iVarV = this.f935c.v();
        if (iVarV != null) {
            ArrayList<i> arrayListZ = this.f935c.z();
            int size = arrayListZ.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (arrayListZ.get(i2) == iVarV) {
                    this.f936d = i2;
                    return;
                }
            }
        }
        this.f936d = -1;
    }

    public g b() {
        return this.f935c;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i2) {
        ArrayList<i> arrayListZ = this.x ? this.f935c.z() : this.f935c.E();
        int i3 = this.f936d;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayListZ.get(i2);
    }

    public void d(boolean z) {
        this.s = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f936d < 0 ? (this.x ? this.f935c.z() : this.f935c.E()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.y.inflate(this.E3, viewGroup, false);
        }
        int groupId = getItem(i2).getGroupId();
        int i3 = i2 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f935c.F() && groupId != (i3 >= 0 ? getItem(i3).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.s) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.d(getItem(i2), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
