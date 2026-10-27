package q;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;
import q.n;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f7124d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7125e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7126f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f7127g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LayoutInflater f7128h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7129i;

    public f(g gVar, LayoutInflater layoutInflater, boolean z7, int i8) {
        this.f7127g = z7;
        this.f7128h = layoutInflater;
        this.f7124d = gVar;
        this.f7129i = i8;
        a();
    }

    public void a() {
        i iVarX = this.f7124d.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f7124d.B();
            int size = arrayListB.size();
            for (int i8 = 0; i8 < size; i8++) {
                if (arrayListB.get(i8) == iVarX) {
                    this.f7125e = i8;
                    return;
                }
            }
        }
        this.f7125e = -1;
    }

    public g b() {
        return this.f7124d;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i8) {
        ArrayList<i> arrayListB = this.f7127g ? this.f7124d.B() : this.f7124d.G();
        int i9 = this.f7125e;
        if (i9 >= 0 && i8 >= i9) {
            i8++;
        }
        return arrayListB.get(i8);
    }

    public void d(boolean z7) {
        this.f7126f = z7;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f7125e < 0 ? (this.f7127g ? this.f7124d.B() : this.f7124d.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        return i8;
    }

    @Override // android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f7128h.inflate(this.f7129i, viewGroup, false);
        }
        int groupId = getItem(i8).getGroupId();
        int i9 = i8 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f7124d.H() && groupId != (i9 >= 0 ? getItem(i9).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f7126f) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.e(getItem(i8), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
