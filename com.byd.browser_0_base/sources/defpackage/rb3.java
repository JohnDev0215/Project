package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/* JADX INFO: compiled from: ToolbarActionModeCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rb3 implements ActionMode.Callback {
    public k2 a;
    public boolean b;

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        int i = 0;
        boolean z = actionMode.getType() != 1;
        if (z != this.b) {
            if (z) {
                this.a.c();
            } else {
                this.a.b();
            }
            this.b = z;
        }
        int size = menu.size();
        while (true) {
            if (i >= size) {
                break;
            }
            int itemId = menu.getItem(i).getItemId();
            if (itemId == 16908341) {
                menu.removeItem(itemId);
                break;
            }
            i++;
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        if (this.b) {
            this.a.b();
            this.b = false;
        }
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }
}
