package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.chromium.ui.modelutil.f;

/* JADX INFO: compiled from: ListMenuItemAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n81 extends f {
    public n81(ya1 ya1Var) {
        super(ya1Var);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        for (int i = 0; i < getCount(); i++) {
            if (!isEnabled(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.chromium.ui.modelutil.f, android.widget.Adapter
    public final long getItemId(int i) {
        return ((xa1) getItem(i)).b.h(o81.f);
    }

    @Override // org.chromium.ui.modelutil.f, android.widget.Adapter
    public final View getView(final int i, View view, final ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        view2.setOnClickListener(new View.OnClickListener() { // from class: m81
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                n81 n81Var = this.e;
                ((ListView) viewGroup).performItemClick(view3, i, ((xa1) n81Var.getItem(r1)).b.h(o81.f));
            }
        });
        view2.setEnabled(isEnabled(i));
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return getItemViewType(i) != 0 && ((xa1) getItem(i)).b.k(o81.g);
    }
}
