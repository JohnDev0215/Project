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
    g f290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f291c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f292d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f293e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final LayoutInflater f294f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f295g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f293e = z;
        this.f294f = layoutInflater;
        this.f290b = gVar;
        this.f295g = i;
        a();
    }

    void a() {
        i iVarX = this.f290b.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f290b.B();
            int size = arrayListB.size();
            for (int i = 0; i < size; i++) {
                if (arrayListB.get(i) == iVarX) {
                    this.f291c = i;
                    return;
                }
            }
        }
        this.f291c = -1;
    }

    public g b() {
        return this.f290b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i) {
        ArrayList<i> arrayListB = this.f293e ? this.f290b.B() : this.f290b.G();
        int i2 = this.f291c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListB.get(i);
    }

    public void d(boolean z) {
        this.f292d = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f291c < 0 ? (this.f293e ? this.f290b.B() : this.f290b.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f294f.inflate(this.f295g, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f290b.H() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f292d) {
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
