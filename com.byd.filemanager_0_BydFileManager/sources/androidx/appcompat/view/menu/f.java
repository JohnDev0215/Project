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
    g f235b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f236c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f237d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f238e;
    private final LayoutInflater f;
    private final int g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f238e = z;
        this.f = layoutInflater;
        this.f235b = gVar;
        this.g = i;
        a();
    }

    void a() {
        i iVarX = this.f235b.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f235b.B();
            int size = arrayListB.size();
            for (int i = 0; i < size; i++) {
                if (arrayListB.get(i) == iVarX) {
                    this.f236c = i;
                    return;
                }
            }
        }
        this.f236c = -1;
    }

    public g b() {
        return this.f235b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i) {
        ArrayList<i> arrayListB = this.f238e ? this.f235b.B() : this.f235b.G();
        int i2 = this.f236c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListB.get(i);
    }

    public void d(boolean z) {
        this.f237d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f236c < 0 ? (this.f238e ? this.f235b.B() : this.f235b.G()).size() : r0.size() - 1;
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
        listMenuItemView.setGroupDividerEnabled(this.f235b.H() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f237d) {
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
