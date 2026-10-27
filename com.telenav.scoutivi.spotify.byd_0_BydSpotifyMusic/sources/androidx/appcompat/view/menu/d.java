package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.k;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends BaseAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    e f656e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f657g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f658h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f659i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final LayoutInflater f660j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f661k;

    public d(e eVar, LayoutInflater layoutInflater, boolean z6, int i7) {
        this.f659i = z6;
        this.f660j = layoutInflater;
        this.f656e = eVar;
        this.f661k = i7;
        a();
    }

    void a() {
        g gVarV = this.f656e.v();
        if (gVarV != null) {
            ArrayList<g> arrayListZ = this.f656e.z();
            int size = arrayListZ.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (arrayListZ.get(i7) == gVarV) {
                    this.f657g = i7;
                    return;
                }
            }
        }
        this.f657g = -1;
    }

    public e b() {
        return this.f656e;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public g getItem(int i7) {
        ArrayList<g> arrayListZ = this.f659i ? this.f656e.z() : this.f656e.E();
        int i8 = this.f657g;
        if (i8 >= 0 && i7 >= i8) {
            i7++;
        }
        return arrayListZ.get(i7);
    }

    public void d(boolean z6) {
        this.f658h = z6;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f657g < 0 ? (this.f659i ? this.f656e.z() : this.f656e.E()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f660j.inflate(this.f661k, viewGroup, false);
        }
        int groupId = getItem(i7).getGroupId();
        int i8 = i7 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f656e.F() && groupId != (i8 >= 0 ? getItem(i8).getGroupId() : groupId));
        k.a aVar = (k.a) view;
        if (this.f658h) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.e(getItem(i7), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
