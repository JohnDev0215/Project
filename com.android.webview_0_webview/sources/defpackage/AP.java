package defpackage;

import android.content.Intent;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.android.webview.R;
import java.util.Objects;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AP implements ActionMode.Callback {
    public final BD s;

    public AP(WebContents webContents) {
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(webContents);
        Objects.requireNonNull(selectionPopupControllerImplT);
        this.s = selectionPopupControllerImplT;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.s.h(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("new_search", true);
        int i = Zs.b(intent, R.anim.abc_fade_in).isEmpty() ^ true ? 7 : 5;
        BD bd = this.s;
        ((SelectionPopupControllerImpl) bd).F = i;
        bd.i(actionMode, menu);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) this.s;
        selectionPopupControllerImpl.E = null;
        if (selectionPopupControllerImpl.L) {
            selectionPopupControllerImpl.p();
        }
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        this.s.k(actionMode, menu);
        return true;
    }
}
