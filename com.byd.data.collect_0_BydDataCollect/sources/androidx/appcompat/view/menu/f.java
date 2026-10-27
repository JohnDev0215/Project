package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    g f300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f301c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f303e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final LayoutInflater f304f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f305g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i2) {
        this.f303e = z;
        this.f304f = layoutInflater;
        this.f300b = gVar;
        this.f305g = i2;
        a();
    }

    void a() {
        i iVarX = this.f300b.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f300b.B();
            int size = arrayListB.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (arrayListB.get(i2) == iVarX) {
                    this.f301c = i2;
                    return;
                }
            }
        }
        this.f301c = -1;
    }

    public g b() {
        return this.f300b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i2) {
        ArrayList<i> arrayListB = this.f303e ? this.f300b.B() : this.f300b.G();
        int i3 = this.f301c;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayListB.get(i2);
    }

    public void d(boolean z) {
        this.f302d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f301c < 0 ? (this.f303e ? this.f300b.B() : this.f300b.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f304f.inflate(this.f305g, viewGroup, false);
        }
        int groupId = getItem(i2).getGroupId();
        int i3 = i2 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f300b.H() && groupId != (i3 >= 0 ? getItem(i3).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f302d) {
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
