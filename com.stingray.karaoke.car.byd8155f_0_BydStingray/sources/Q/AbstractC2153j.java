package q;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: renamed from: q.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2153j implements InterfaceC2159p, InterfaceC2157n, AdapterView.OnItemClickListener {
    public Rect l;

    public static int m(ListAdapter listAdapter, Context context, int i7) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i10 = 0;
        int i11 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i12 = 0; i12 < count; i12++) {
            int itemViewType = listAdapter.getItemViewType(i12);
            if (itemViewType != i11) {
                view = null;
                i11 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i12, view, frameLayout);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i7) {
                return i7;
            }
            if (measuredWidth > i10) {
                i10 = measuredWidth;
            }
        }
        return i10;
    }

    public static boolean u(MenuC2151h menuC2151h) {
        int size = menuC2151h.f21052f.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = menuC2151h.getItem(i7);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // q.InterfaceC2157n
    public final boolean f(MenuItemC2152i menuItemC2152i) {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final void h(Context context, MenuC2151h menuC2151h) {
    }

    @Override // q.InterfaceC2157n
    public final boolean k(MenuItemC2152i menuItemC2152i) {
        return false;
    }

    public abstract void l(MenuC2151h menuC2151h);

    public abstract void n(View view);

    public abstract void o(boolean z6);

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j3) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        (listAdapter instanceof HeaderViewListAdapter ? (C2149f) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (C2149f) listAdapter).f21044a.p((MenuItem) listAdapter.getItem(i7), this, !(this instanceof ViewOnKeyListenerC2148e) ? 0 : 4);
    }

    public abstract void p(int i7);

    public abstract void q(int i7);

    public abstract void r(PopupWindow.OnDismissListener onDismissListener);

    public abstract void s(boolean z6);

    public abstract void t(int i7);
}
