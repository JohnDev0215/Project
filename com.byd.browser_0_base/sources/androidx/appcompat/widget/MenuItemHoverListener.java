package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* JADX INFO: loaded from: classes.dex */
public interface MenuItemHoverListener {
    void c(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    void d(MenuBuilder menuBuilder, MenuItem menuItem);
}
