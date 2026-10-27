package n;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;
import n.n;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f3927b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3928c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3929d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3930e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LayoutInflater f3931f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3932g;

    public f(g gVar, LayoutInflater layoutInflater, boolean z3, int i3) {
        this.f3930e = z3;
        this.f3931f = layoutInflater;
        this.f3927b = gVar;
        this.f3932g = i3;
        a();
    }

    public void a() {
        i iVarX = this.f3927b.x();
        if (iVarX != null) {
            ArrayList<i> arrayListB = this.f3927b.B();
            int size = arrayListB.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (arrayListB.get(i3) == iVarX) {
                    this.f3928c = i3;
                    return;
                }
            }
        }
        this.f3928c = -1;
    }

    public g b() {
        return this.f3927b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i3) {
        ArrayList<i> arrayListB = this.f3930e ? this.f3927b.B() : this.f3927b.G();
        int i4 = this.f3928c;
        if (i4 >= 0 && i3 >= i4) {
            i3++;
        }
        return arrayListB.get(i3);
    }

    public void d(boolean z3) {
        this.f3929d = z3;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3928c < 0 ? (this.f3930e ? this.f3927b.B() : this.f3927b.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i3) {
        return i3;
    }

    @Override // android.widget.Adapter
    public View getView(int i3, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f3931f.inflate(this.f3932g, viewGroup, false);
        }
        int groupId = getItem(i3).getGroupId();
        int i4 = i3 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f3927b.H() && groupId != (i4 >= 0 ? getItem(i4).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.f3929d) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.e(getItem(i3), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
