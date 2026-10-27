package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* JADX INFO: loaded from: classes.dex */
public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    public MenuItemHoverListener E;

    public static class Api23Impl {
        private Api23Impl() {
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }
    }

    public static class MenuDropDownListView extends DropDownListView {
        public final int q;
        public final int r;
        public MenuItemHoverListener s;
        public MenuItemImpl t;

        public static class Api17Impl {
            private Api17Impl() {
            }
        }

        public MenuDropDownListView(Context context, boolean z) {
            super(context, z);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.q = 21;
                this.r = 22;
            } else {
                this.q = 22;
                this.r = 21;
            }
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public final boolean onHoverEvent(MotionEvent motionEvent) {
            MenuAdapter menuAdapter;
            int headersCount;
            int iPointToPosition;
            int i;
            if (this.s != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
                } else {
                    menuAdapter = (MenuAdapter) adapter;
                    headersCount = 0;
                }
                MenuItemImpl item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i = iPointToPosition - headersCount) < 0 || i >= menuAdapter.getCount()) ? null : menuAdapter.getItem(i);
                MenuItemImpl menuItemImpl = this.t;
                if (menuItemImpl != item) {
                    MenuBuilder menuBuilder = menuAdapter.e;
                    if (menuItemImpl != null) {
                        this.s.d(menuBuilder, menuItemImpl);
                    }
                    this.t = item;
                    if (item != null) {
                        this.s.c(menuBuilder, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.q) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.r) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ListAdapter adapter = getAdapter();
            (adapter instanceof HeaderViewListAdapter ? (MenuAdapter) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (MenuAdapter) adapter).e.c(false);
            return true;
        }

        public void setHoverListener(MenuItemHoverListener menuItemHoverListener) {
            this.s = menuItemHoverListener;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    public MenuPopupWindow(Context context, int i, int i2) {
        super(context, null, i, i2);
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public final void c(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        MenuItemHoverListener menuItemHoverListener = this.E;
        if (menuItemHoverListener != null) {
            menuItemHoverListener.c(menuBuilder, menuItemImpl);
        }
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public final void d(MenuBuilder menuBuilder, MenuItem menuItem) {
        MenuItemHoverListener menuItemHoverListener = this.E;
        if (menuItemHoverListener != null) {
            menuItemHoverListener.d(menuBuilder, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public final DropDownListView e(Context context, boolean z) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }
}
