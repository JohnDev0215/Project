package defpackage;

import J.N;
import android.R;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MC extends ActionMode.Callback2 {
    public final /* synthetic */ NC s;

    public MC(NC nc, LC lc) {
        this.s = nc;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == AbstractC0371Um.S3) {
            ((C0704eD) this.s.t).a();
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.T3) {
            C0704eD c0704eD = (C0704eD) this.s.t;
            WebContentsImpl webContentsImpl = c0704eD.a.w;
            webContentsImpl.O();
            N.MdSkKRWg(webContentsImpl.t, webContentsImpl);
            SelectionPopupControllerImpl selectionPopupControllerImpl = c0704eD.a;
            if (selectionPopupControllerImpl.w.T() != null) {
                selectionPopupControllerImpl.w.T().a();
            }
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.U3) {
            ((C0704eD) this.s.t).a.J();
            actionMode.finish();
        } else {
            ActionMode.Callback callback = this.s.x;
            if (callback != null) {
                return callback.onActionItemClicked(actionMode, menuItem);
            }
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(this.s.u.getResources().getInteger(AbstractC0389Vm.o) >= 2 ? this.s.u.getString(AbstractC0566bn.l0) : null);
        actionMode.setSubtitle((CharSequence) null);
        SelectionPopupControllerImpl.D(this.s.u, actionMode, menu);
        if (!((C0704eD) this.s.t).a.n()) {
            menu.removeItem(AbstractC0371Um.S3);
        }
        if (!((C0704eD) this.s.t).a.f31J) {
            menu.removeItem(AbstractC0371Um.U3);
        }
        if (!((C0704eD) this.s.t).a.o()) {
            menu.removeItem(AbstractC0371Um.T3);
        }
        MenuItem menuItemFindItem = menu.findItem(AbstractC0371Um.T3);
        if (menuItemFindItem != null) {
            menuItemFindItem.setTitle(R.string.paste_as_plain_text);
        }
        menu.removeItem(AbstractC0371Um.Q3);
        menu.removeItem(AbstractC0371Um.P3);
        menu.removeItem(AbstractC0371Um.V3);
        menu.removeItem(AbstractC0371Um.X3);
        ActionMode.Callback callback = this.s.x;
        if (callback != null) {
            callback.onCreateActionMode(actionMode, menu);
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        ActionMode.Callback callback = this.s.x;
        if (callback != null) {
            callback.onDestroyActionMode(actionMode);
        }
        this.s.v = null;
    }

    @Override // android.view.ActionMode.Callback2
    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        rect.set(this.s.w);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        ActionMode.Callback callback = this.s.x;
        if (callback != null) {
            return callback.onPrepareActionMode(actionMode, menu);
        }
        return false;
    }
}
