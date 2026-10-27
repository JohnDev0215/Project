package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PC extends ActionMode.Callback2 {
    public final /* synthetic */ ActionMode.Callback2 s;

    public PC(ActionMode.Callback2 callback2) {
        this.s = callback2;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.s.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.s.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        PostTask.a(AbstractC0538bE.a, new OC(this, actionMode), 0L);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.s.onPrepareActionMode(actionMode, menu);
    }
}
