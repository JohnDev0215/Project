package org.chromium.chrome.browser.toolbar.top;

import android.view.View;
import defpackage.s41;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;

/* JADX INFO: compiled from: ToolbarPhone.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends s41 {
    public final /* synthetic */ ToolbarPhone e;

    public j(ToolbarPhone toolbarPhone) {
        this.e = toolbarPhone;
    }

    @Override // defpackage.s41
    public final View a() {
        return this.e.findViewById(R$id.url_bar);
    }

    @Override // defpackage.s41
    public final View b() {
        int i = ToolbarPhone.I0;
        ToolbarPhone toolbarPhone = this.e;
        MenuButton menuButton = toolbarPhone.getMenuButtonCoordinator().e;
        return menuButton != null && menuButton.getVisibility() == 0 ? toolbarPhone.getMenuButtonCoordinator().e : toolbarPhone.getCurrentTabView();
    }
}
