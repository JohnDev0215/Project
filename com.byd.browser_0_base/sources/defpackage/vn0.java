package defpackage;

import J.N;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content.R$id;
import org.chromium.content.R$menu;
import org.chromium.content.R$string;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: FloatingPastePopupMenu.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vn0 {
    public final View a;
    public final dy1 b;
    public final Context c;
    public ActionMode d;
    public Rect e;
    public final ActionMode.Callback f = null;

    /* JADX INFO: compiled from: FloatingPastePopupMenu.java */
    public class a extends ActionMode.Callback2 {
        public a() {
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            int i = R$id.select_action_menu_paste;
            vn0 vn0Var = vn0.this;
            if (itemId == i) {
                SelectionPopupControllerImpl selectionPopupControllerImpl = ((gi2) vn0Var.b).a;
                WebContentsImpl webContentsImpl = selectionPopupControllerImpl.i;
                webContentsImpl.R();
                N.MYRJ_nNk(webContentsImpl.f);
                selectionPopupControllerImpl.h();
                actionMode.finish();
            } else if (itemId == R$id.select_action_menu_paste_as_plain_text) {
                SelectionPopupControllerImpl selectionPopupControllerImpl2 = ((gi2) vn0Var.b).a;
                WebContentsImpl webContentsImpl2 = selectionPopupControllerImpl2.i;
                webContentsImpl2.R();
                N.MdSkKRWg(webContentsImpl2.f);
                selectionPopupControllerImpl2.h();
                actionMode.finish();
            } else if (itemId == R$id.select_action_menu_select_all) {
                SelectionPopupControllerImpl selectionPopupControllerImpl3 = ((gi2) vn0Var.b).a;
                WebContentsImpl webContentsImpl3 = selectionPopupControllerImpl3.i;
                webContentsImpl3.R();
                N.MNvj1u1S(webContentsImpl3.f);
                selectionPopupControllerImpl3.I = null;
                if (selectionPopupControllerImpl3.u) {
                    n3.X0("MobileActionMode.SelectAllWasEditable");
                } else {
                    n3.X0("MobileActionMode.SelectAllWasNonEditable");
                }
                actionMode.finish();
            } else {
                ActionMode.Callback callback = vn0Var.f;
                if (callback != null) {
                    return callback.onActionItemClicked(actionMode, menuItem);
                }
            }
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            vn0 vn0Var = vn0.this;
            boolean zA = DeviceFormFactor.a(vn0Var.c);
            Context context = vn0Var.c;
            actionMode.setTitle(zA ? context.getString(R$string.actionbar_textselection_title) : null);
            actionMode.setSubtitle((CharSequence) null);
            boolean z = SelectionPopupControllerImpl.M;
            try {
                actionMode.getMenuInflater().inflate(R$menu.select_action_menu, menu);
            } catch (Resources.NotFoundException unused) {
                new MenuInflater(context).inflate(R$menu.select_action_menu, menu);
            }
            gi2 gi2Var = (gi2) vn0Var.b;
            gi2Var.getClass();
            if (!Clipboard.getInstance().a()) {
                menu.removeItem(R$id.select_action_menu_paste);
            }
            if (!gi2Var.a.w) {
                menu.removeItem(R$id.select_action_menu_select_all);
            }
            if (!(!gi2Var.a.x ? false : Clipboard.getInstance().hasHTMLOrStyledText())) {
                menu.removeItem(R$id.select_action_menu_paste_as_plain_text);
            }
            MenuItem menuItemFindItem = menu.findItem(R$id.select_action_menu_paste_as_plain_text);
            if (menuItemFindItem != null) {
                menuItemFindItem.setTitle(R.string.paste_as_plain_text);
            }
            menu.removeItem(R$id.select_action_menu_cut);
            menu.removeItem(R$id.select_action_menu_copy);
            menu.removeItem(R$id.select_action_menu_web_search);
            ActionMode.Callback callback = vn0Var.f;
            if (callback == null) {
                return true;
            }
            callback.onCreateActionMode(actionMode, menu);
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public final void onDestroyActionMode(ActionMode actionMode) {
            vn0 vn0Var = vn0.this;
            ActionMode.Callback callback = vn0Var.f;
            if (callback != null) {
                callback.onDestroyActionMode(actionMode);
            }
            vn0Var.d = null;
        }

        @Override // android.view.ActionMode.Callback2
        public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            rect.set(vn0.this.e);
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            ActionMode.Callback callback = vn0.this.f;
            if (callback != null) {
                return callback.onPrepareActionMode(actionMode, menu);
            }
            return false;
        }
    }

    public vn0(Context context, View view, gi2 gi2Var) {
        this.a = view;
        this.b = gi2Var;
        this.c = context;
    }
}
