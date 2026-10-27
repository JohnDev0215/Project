package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: y3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionModeCallbackC1800y3 implements ActionMode.Callback {
    public final ActionMode.Callback s;
    public final TextView t;
    public Class u;
    public Method v;
    public boolean w;
    public boolean x = false;

    public ActionModeCallbackC1800y3(ActionMode.Callback callback, TextView textView) {
        this.s = callback;
        this.t = textView;
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
        this.s.onDestroyActionMode(actionMode);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ce  */
    @Override // android.view.ActionMode.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onPrepareActionMode(android.view.ActionMode r13, android.view.Menu r14) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ActionModeCallbackC1800y3.onPrepareActionMode(android.view.ActionMode, android.view.Menu):boolean");
    }
}
