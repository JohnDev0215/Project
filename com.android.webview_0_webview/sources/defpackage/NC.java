package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NC implements ZC {
    public final View s;
    public final YC t;
    public final Context u;
    public ActionMode v;
    public Rect w;
    public ActionMode.Callback x;

    public NC(Context context, View view, YC yc, ActionMode.Callback callback) {
        this.s = view;
        this.t = yc;
        this.u = context;
        this.x = callback;
    }

    @Override // defpackage.ZC
    public void a() {
        ActionMode actionMode = this.v;
        if (actionMode != null) {
            actionMode.finish();
            this.v = null;
        }
    }

    @Override // defpackage.ZC
    public void b(Rect rect) {
        ActionMode actionModeStartActionMode;
        this.w = rect;
        ActionMode actionMode = this.v;
        if (actionMode != null) {
            actionMode.invalidateContentRect();
        } else if (actionMode == null && (actionModeStartActionMode = this.s.startActionMode(new MC(this, null), 1)) != null) {
            SC.b(this.u, actionModeStartActionMode);
            this.v = actionModeStartActionMode;
        }
    }
}
