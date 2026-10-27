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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f1626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1627c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1628d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1629e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LayoutInflater f1630f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1631g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i2) {
        this.f1629e = z;
        this.f1630f = layoutInflater;
        this.f1626b = gVar;
        this.f1631g = i2;
        a();
    }

    public void a() {
        i iVarX = this.f1626b.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f1626b.B();
            int size = arrayListB.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (arrayListB.get(i2) == iVarX) {
                    this.f1627c = i2;
                    return;
                }
            }
        }
        this.f1627c = -1;
    }

    public g b() {
        return this.f1626b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i2) {
        ArrayList<i> arrayListB = this.f1629e ? this.f1626b.B() : this.f1626b.G();
        int i3 = this.f1627c;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayListB.get(i2);
    }

    public void d(boolean z) {
        this.f1628d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f1627c < 0 ? (this.f1629e ? this.f1626b.B() : this.f1626b.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1630f.inflate(this.f1631g, viewGroup, false);
        }
        int groupId = getItem(i2).getGroupId();
        int i3 = i2 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f1626b.H() && groupId != (i3 >= 0 ? getItem(i3).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f1628d) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.e(getItem(i2), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
