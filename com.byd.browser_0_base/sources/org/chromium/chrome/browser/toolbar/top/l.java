package org.chromium.chrome.browser.toolbar.top;

import android.view.View;
import defpackage.af1;
import defpackage.s41;
import defpackage.z6;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;

/* JADX INFO: compiled from: ToolbarTablet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l extends s41 {
    public final /* synthetic */ ToolbarTablet e;

    public l(ToolbarTablet toolbarTablet) {
        this.e = toolbarTablet;
    }

    @Override // defpackage.s41
    public final View a() {
        return this.e.findViewById(R$id.url_bar);
    }

    @Override // defpackage.s41
    public final View b() {
        return this.e.getCurrentTabView();
    }

    @Override // defpackage.s41
    public final boolean c() {
        MenuButton menuButton;
        af1 menuButtonCoordinator = this.e.getMenuButtonCoordinator();
        z6 z6Var = menuButtonCoordinator.d;
        if (z6Var == null || (menuButton = menuButtonCoordinator.e) == null) {
            return false;
        }
        return z6Var.e(menuButton.getImageButton());
    }
}
