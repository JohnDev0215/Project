package p;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class za1 extends BaseAdapter {
    public int c = -1;
    public final /* synthetic */ ab1 e;

    public za1(ab1 ab1Var) {
        this.e = ab1Var;
        a();
    }

    public final void a() {
        si1 si1Var = this.e.k;
        xi1 xi1Var = si1Var.v;
        if (xi1Var != null) {
            si1Var.i();
            ArrayList arrayList = si1Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((xi1) arrayList.get(i)) == xi1Var) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final xi1 getItem(int i) {
        ab1 ab1Var = this.e;
        si1 si1Var = ab1Var.k;
        si1Var.i();
        ArrayList arrayList = si1Var.j;
        ab1Var.getClass();
        int i2 = this.c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (xi1) arrayList.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ab1 ab1Var = this.e;
        si1 si1Var = ab1Var.k;
        si1Var.i();
        int size = si1Var.j.size();
        ab1Var.getClass();
        return this.c < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.e.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((pj1) view).c(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
