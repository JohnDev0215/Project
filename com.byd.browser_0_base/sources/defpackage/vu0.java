package defpackage;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.wp2;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.Clipboard;

/* JADX INFO: compiled from: HistoryClustersCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class vu0 extends RecyclerView.q implements Toolbar.OnMenuItemClickListener, wp2.b {
    public vu0() {
        throw null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        throw null;
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.search_menu_id || menuItem.getItemId() == R$id.close_menu_id || menuItem.getItemId() == R$id.selection_mode_open_in_incognito || menuItem.getItemId() == R$id.selection_mode_open_in_new_tab || menuItem.getItemId() == R$id.info_menu_id || menuItem.getItemId() == R$id.selection_mode_delete_menu_id || menuItem.getItemId() == R$id.optout_menu_id || menuItem.getItemId() == R$id.selection_mode_open_in_tab_group) {
            throw null;
        }
        if (menuItem.getItemId() != R$id.selection_mode_copy_link) {
            return false;
        }
        Clipboard.getInstance();
        throw null;
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }

    @Override // wp2.b
    public final void j(Object obj) {
    }
}
