package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.m;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class e extends BaseAdapter {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    f f221c;
    private int d = -1;
    private boolean e;
    private final boolean f;
    private final LayoutInflater g;
    private final int h;

    public e(f fVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f = z;
        this.g = layoutInflater;
        this.f221c = fVar;
        this.h = i;
        a();
    }

    void a() {
        h hVarV = this.f221c.v();
        if (hVarV != null) {
            ArrayList<h> arrayListZ = this.f221c.z();
            int size = arrayListZ.size();
            for (int i = 0; i < size; i++) {
                if (arrayListZ.get(i) == hVarV) {
                    this.d = i;
                    return;
                }
            }
        }
        this.d = -1;
    }

    public f b() {
        return this.f221c;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h getItem(int i) {
        ArrayList<h> arrayListZ = this.f ? this.f221c.z() : this.f221c.E();
        int i2 = this.d;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListZ.get(i);
    }

    public void d(boolean z) {
        this.e = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.d < 0 ? (this.f ? this.f221c.z() : this.f221c.E()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.g.inflate(this.h, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.l(this.f221c.F() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        m.a aVar = (m.a) view;
        if (this.e) {
            listMenuItemView.k(true);
        }
        aVar.j(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
