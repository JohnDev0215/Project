package p;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class lj1 extends ib0 {
    public final int u;
    public final int v;
    public wi1 w;
    public xi1 x;

    public lj1(Context context, boolean z) {
        super(context, z);
        if (1 == kj1.a(context.getResources().getConfiguration())) {
            this.u = 21;
            this.v = 22;
        } else {
            this.u = 22;
            this.v = 21;
        }
    }

    @Override // p.ib0, android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        pi1 pi1Var;
        int headersCount;
        int iPointToPosition;
        int i;
        if (this.w != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                headersCount = headerViewListAdapter.getHeadersCount();
                pi1Var = (pi1) headerViewListAdapter.getWrappedAdapter();
            } else {
                pi1Var = (pi1) adapter;
                headersCount = 0;
            }
            xi1 item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i = iPointToPosition - headersCount) < 0 || i >= pi1Var.getCount()) ? null : pi1Var.getItem(i);
            xi1 xi1Var = this.x;
            if (xi1Var != item) {
                si1 si1Var = pi1Var.c;
                if (xi1Var != null) {
                    this.w.n(si1Var, xi1Var);
                }
                this.x = item;
                if (item != null) {
                    this.w.o(si1Var, item);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i == this.u) {
            if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listMenuItemView == null || i != this.v) {
            return super.onKeyDown(i, keyEvent);
        }
        setSelection(-1);
        ListAdapter adapter = getAdapter();
        (adapter instanceof HeaderViewListAdapter ? (pi1) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (pi1) adapter).c.c(false);
        return true;
    }

    public void setHoverListener(wi1 wi1Var) {
        this.w = wi1Var;
    }

    @Override // p.ib0, android.widget.AbsListView
    public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
        super.setSelector(drawable);
    }
}
