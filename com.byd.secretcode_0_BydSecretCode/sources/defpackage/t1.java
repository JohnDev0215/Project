package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: MenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t1 implements y1, v1, AdapterView.OnItemClickListener {
    public Rect b;

    public static boolean A(p1 p1Var) {
        int size = p1Var.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = p1Var.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public static o1 B(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (o1) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (o1) listAdapter;
    }

    public static int r(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i2 = 0;
        int i3 = 0;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    @Override // defpackage.v1
    public int c() {
        return 0;
    }

    @Override // defpackage.v1
    public void f(Context context, p1 p1Var) {
    }

    @Override // defpackage.v1
    public boolean i(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public boolean j(p1 p1Var, r1 r1Var) {
        return false;
    }

    public abstract void o(p1 p1Var);

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        B(listAdapter).b.O((MenuItem) listAdapter.getItem(i), this, p() ? 0 : 4);
    }

    public boolean p() {
        return true;
    }

    public Rect q() {
        return this.b;
    }

    public abstract void s(View view);

    public void t(Rect rect) {
        this.b = rect;
    }

    public abstract void u(boolean z);

    public abstract void v(int i);

    public abstract void w(int i);

    public abstract void x(PopupWindow.OnDismissListener onDismissListener);

    public abstract void y(boolean z);

    public abstract void z(int i);
}
