package defpackage;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.webview.R;
import org.chromium.components.autofill.AutofillProvider;

/* JADX INFO: renamed from: lw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionModeCallbackC1133lw implements ActionMode.Callback {
    public final Context s;
    public final AutofillProvider t;
    public final int u;
    public final int v;

    public ActionModeCallbackC1133lw(Context context, AutofillProvider autofillProvider) {
        this.s = context;
        this.t = autofillProvider;
        this.u = AbstractC1077kw.b(context.getResources(), "autofill", "string", "android");
        this.v = AbstractC1077kw.b(this.s.getResources(), "autofill", "id", "android");
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() != this.v) {
            return false;
        }
        this.t.e();
        actionMode.finish();
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return (this.u == 0 || this.v == 0) ? false : true;
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        if (this.u == 0 || !this.t.g()) {
            return true;
        }
        menu.add(0, this.v, R.attr.actionBarDivider, this.u).setShowAsActionFlags(4);
        return true;
    }
}
