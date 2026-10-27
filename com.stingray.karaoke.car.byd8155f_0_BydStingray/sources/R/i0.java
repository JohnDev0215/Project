package r;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import q.C2149f;
import q.MenuC2151h;
import q.MenuItemC2152i;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends AbstractC2231T {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public MenuItemC2152i f21496A;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f21497x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f21498y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public f0 f21499z;

    public i0(Context context, boolean z6) {
        super(context, z6);
        if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
            this.f21497x = 21;
            this.f21498y = 22;
        } else {
            this.f21497x = 22;
            this.f21498y = 21;
        }
    }

    @Override // r.AbstractC2231T, android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        C2149f c2149f;
        int headersCount;
        int iPointToPosition;
        int i7;
        if (this.f21499z != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                headersCount = headerViewListAdapter.getHeadersCount();
                c2149f = (C2149f) headerViewListAdapter.getWrappedAdapter();
            } else {
                c2149f = (C2149f) adapter;
                headersCount = 0;
            }
            MenuItemC2152i item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i7 = iPointToPosition - headersCount) < 0 || i7 >= c2149f.getCount()) ? null : c2149f.getItem(i7);
            MenuItemC2152i menuItemC2152i = this.f21496A;
            if (menuItemC2152i != item) {
                MenuC2151h menuC2151h = c2149f.f21044a;
                if (menuItemC2152i != null) {
                    this.f21499z.r(menuC2151h, menuItemC2152i);
                }
                this.f21496A = item;
                if (item != null) {
                    this.f21499z.y(menuC2151h, item);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i7 == this.f21497x) {
            if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listMenuItemView == null || i7 != this.f21498y) {
            return super.onKeyDown(i7, keyEvent);
        }
        setSelection(-1);
        ListAdapter adapter = getAdapter();
        (adapter instanceof HeaderViewListAdapter ? (C2149f) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (C2149f) adapter).f21044a.c(false);
        return true;
    }

    public void setHoverListener(f0 f0Var) {
        this.f21499z = f0Var;
    }

    @Override // r.AbstractC2231T, android.widget.AbsListView
    public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
        super.setSelector(drawable);
    }
}
