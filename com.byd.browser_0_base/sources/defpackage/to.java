package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.content.R$string;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: ChromeActionModeHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class to {
    public final mc a;
    public Tab b;

    /* JADX INFO: compiled from: ChromeActionModeHandler.java */
    public static class a extends ActionMode.Callback2 {
        public static final /* synthetic */ int e = 0;
        public final Tab a;
        public final SelectionPopupControllerImpl b;
        public final Callback<String> c;
        public final nw2<ck2> d;

        public a(Tab tab, WebContents webContents, Callback<String> callback, nw2<ck2> nw2Var) {
            this.a = tab;
            SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(webContents);
            selectionPopupControllerImplJ.getClass();
            this.b = selectionPopupControllerImplJ;
            this.c = callback;
            this.d = nw2Var;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            SelectionPopupControllerImpl selectionPopupControllerImpl = this.b;
            if (!selectionPopupControllerImpl.m()) {
                return true;
            }
            if (menuItem.getItemId() != R$id.select_action_menu_web_search) {
                return selectionPopupControllerImpl.o(actionMode, menuItem);
            }
            LocaleManager.getInstance().b(TabUtils.b(this.a), new n62(this, 1, selectionPopupControllerImpl.z));
            selectionPopupControllerImpl.i();
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            System.currentTimeMillis();
            int i = z7.t() ? 7 : 5;
            SelectionPopupControllerImpl selectionPopupControllerImpl = this.b;
            selectionPopupControllerImpl.s = i;
            actionMode.setTitle(DeviceFormFactor.b(selectionPopupControllerImpl.h) ? selectionPopupControllerImpl.g.getString(R$string.actionbar_textselection_title) : null);
            actionMode.setSubtitle((CharSequence) null);
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public final void onDestroyActionMode(ActionMode actionMode) {
            SelectionPopupControllerImpl selectionPopupControllerImpl = this.b;
            selectionPopupControllerImpl.q = null;
            selectionPopupControllerImpl.r.r(Boolean.valueOf(selectionPopupControllerImpl.m()));
            if (selectionPopupControllerImpl.y) {
                selectionPopupControllerImpl.d();
            }
        }

        @Override // android.view.ActionMode.Callback2
        public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            rect.set(this.b.k());
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            n3.X0("MobileActionBarShown.Floating");
            this.b.p(actionMode, menu);
            List<ResolveInfo> listC = wt1.c(wt1.a, 983040);
            HashSet hashSet = new HashSet();
            CollectionUtil.a(listC, new so(0, hashSet));
            List<ResolveInfo> listC2 = wt1.c(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 131072);
            HashSet hashSet2 = new HashSet();
            CollectionUtil.a(listC2, new so(0, hashSet2));
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.getItem(i);
                if (item.getGroupId() == R$id.select_action_menu_text_processing_menus && item.getIntent() != null && item.getIntent().getComponent() != null) {
                    String packageName = item.getIntent().getComponent().getPackageName();
                    if (hashSet.contains(packageName) || hashSet2.contains(packageName)) {
                        item.setVisible(false);
                    }
                }
            }
            return true;
        }
    }

    public to(z2 z2Var, ec2 ec2Var, zp1 zp1Var) {
        this.a = new mc(this, 1, ec2Var, zp1Var);
        new ro(this, z2Var);
    }
}
