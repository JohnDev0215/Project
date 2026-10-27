package org.chromium.chrome.browser.toolbar.top;

import android.view.View;
import defpackage.af1;
import defpackage.s41;
import defpackage.z6;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;

/* JADX INFO: compiled from: ToolbarPhone.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends s41 {
    public final /* synthetic */ ToolbarPhone e;

    public k(ToolbarPhone toolbarPhone) {
        this.e = toolbarPhone;
    }

    @Override // defpackage.s41
    public final View a() {
        return this.e.z;
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
