package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: LGEmailActionModeWorkaroundImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class v41 extends ActionMode.Callback2 {
    public final /* synthetic */ ActionMode.Callback2 a;

    /* JADX INFO: compiled from: LGEmailActionModeWorkaroundImpl.java */
    public class a implements Runnable {
        public final /* synthetic */ ActionMode e;

        public a(ActionMode actionMode) {
            this.e = actionMode;
        }

        @Override // java.lang.Runnable
        public final void run() {
            v41.this.a.onDestroyActionMode(this.e);
        }
    }

    public v41(ActionMode.Callback2 callback2) {
        this.a = callback2;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.a.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.a.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        PostTask.c(rh3.a, new a(actionMode));
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.a.onPrepareActionMode(actionMode, menu);
    }
}
