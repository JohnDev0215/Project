package org.chromium.chrome.browser.autofill.prefeditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$menu;

/* JADX INFO: loaded from: classes.dex */
public class EditorDialogToolbar extends Toolbar {
    public boolean b0;

    public EditorDialogToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b0 = true;
        o(R$menu.prefeditor_editor_menu);
        MenuItem menuItemFindItem = getMenu().findItem(R$id.delete_menu_id);
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible(this.b0);
        }
    }

    public void setShowDeleteMenuItem(boolean z) {
        this.b0 = z;
        MenuItem menuItemFindItem = getMenu().findItem(R$id.delete_menu_id);
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible(this.b0);
        }
    }
}
