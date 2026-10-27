package p;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class ua2 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ View e;

    public /* synthetic */ ua2(View view, int i) {
        this.c = i;
        this.e = view;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        Object item;
        int i2 = this.c;
        View view2 = this.e;
        switch (i2) {
            case 0:
                ((SearchView) view2).p(i);
                break;
            default:
                if (i < 0) {
                    ib1 ib1Var = ((ye1) view2).m;
                    item = !ib1Var.G.isShowing() ? null : ib1Var.k.getSelectedItem();
                } else {
                    item = ((ye1) view2).getAdapter().getItem(i);
                }
                ye1 ye1Var = (ye1) view2;
                ye1.a(ye1Var, item);
                AdapterView.OnItemClickListener onItemClickListener = ye1Var.getOnItemClickListener();
                ib1 ib1Var2 = ye1Var.m;
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        view = ib1Var2.G.isShowing() ? ib1Var2.k.getSelectedView() : null;
                        i = !ib1Var2.G.isShowing() ? -1 : ib1Var2.k.getSelectedItemPosition();
                        j = !ib1Var2.G.isShowing() ? Long.MIN_VALUE : ib1Var2.k.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(ib1Var2.k, view, i, j);
                }
                ib1Var2.dismiss();
                break;
        }
    }
}
