package defpackage;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import org.chromium.chrome.browser.ui.fast_checkout.R$id;

/* JADX INFO: compiled from: FastCheckoutMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dk0 implements Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ Runnable e;

    public dk0(Runnable runnable) {
        this.e = runnable;
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.settings_menu_id) {
            return false;
        }
        this.e.run();
        return true;
    }
}
