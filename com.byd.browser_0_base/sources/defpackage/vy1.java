package defpackage;

import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content.R$string;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: PaymentHandlerActionModeCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vy1 extends ActionMode.Callback2 {
    public final SelectionPopupControllerImpl a;

    public vy1(WebContents webContents) {
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(webContents);
        selectionPopupControllerImplJ.getClass();
        this.a = selectionPopupControllerImplJ;
        selectionPopupControllerImplJ.s = 0;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.a;
        if (selectionPopupControllerImpl.m()) {
            return selectionPopupControllerImpl.o(actionMode, menuItem);
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.a;
        actionMode.setTitle(DeviceFormFactor.b(selectionPopupControllerImpl.h) ? selectionPopupControllerImpl.g.getString(R$string.actionbar_textselection_title) : null);
        actionMode.setSubtitle((CharSequence) null);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.a;
        selectionPopupControllerImpl.q = null;
        selectionPopupControllerImpl.r.r(Boolean.valueOf(selectionPopupControllerImpl.m()));
        if (selectionPopupControllerImpl.y) {
            selectionPopupControllerImpl.d();
        }
    }

    @Override // android.view.ActionMode.Callback2
    public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        rect.set(this.a.k());
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        this.a.p(actionMode, menu);
        return true;
    }
}
