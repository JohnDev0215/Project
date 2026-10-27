package a.b.o.i;

import a.b.o.i.n;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f171b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f173d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayoutInflater f174e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f175f;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f173d = z;
        this.f174e = layoutInflater;
        this.f170a = gVar;
        this.f175f = i;
        a();
    }

    public void a() {
        g gVar = this.f170a;
        i iVar = gVar.w;
        if (iVar != null) {
            gVar.i();
            ArrayList<i> arrayList = gVar.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i) == iVar) {
                    this.f171b = i;
                    return;
                }
            }
        }
        this.f171b = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public i getItem(int i) {
        ArrayList<i> arrayListL;
        if (this.f173d) {
            g gVar = this.f170a;
            gVar.i();
            arrayListL = gVar.j;
        } else {
            arrayListL = this.f170a.l();
        }
        int i2 = this.f171b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListL.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<i> arrayListL;
        if (this.f173d) {
            g gVar = this.f170a;
            gVar.i();
            arrayListL = gVar.j;
        } else {
            arrayListL = this.f170a.l();
        }
        return this.f171b < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f174e.inflate(this.f175f, viewGroup, false);
        }
        int i2 = getItem(i).f186b;
        int i3 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f170a.m() && i2 != (i3 >= 0 ? getItem(i3).f186b : i2));
        n.a aVar = (n.a) view;
        if (this.f172c) {
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
