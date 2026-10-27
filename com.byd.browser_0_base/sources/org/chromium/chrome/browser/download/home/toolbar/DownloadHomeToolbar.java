package org.chromium.chrome.browser.download.home.toolbar;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import defpackage.f81;
import defpackage.n3;
import defpackage.qh3;
import defpackage.zc0;
import java.util.ArrayList;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$menu;
import org.chromium.chrome.R$plurals;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;

/* JADX INFO: loaded from: classes.dex */
public class DownloadHomeToolbar extends SelectableListToolbar<f81> {
    public static final /* synthetic */ int B0 = 0;
    public qh3 A0;

    public DownloadHomeToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        o(R$menu.download_manager_menu);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        boolean z = this.b0;
        super.f(arrayList);
        if (this.b0) {
            int size = this.c0.c.size();
            View viewFindViewById = findViewById(R$id.selection_mode_share_menu_id);
            if (viewFindViewById != null) {
                if (BuildInfo.a.a.m) {
                    viewFindViewById.setVisibility(8);
                } else {
                    viewFindViewById.setContentDescription(getResources().getQuantityString(R$plurals.accessibility_share_selected_items, size, Integer.valueOf(size)));
                }
            }
            View viewFindViewById2 = findViewById(R$id.selection_mode_delete_menu_id);
            if (viewFindViewById2 != null) {
                viewFindViewById2.setContentDescription(getResources().getQuantityString(R$plurals.accessibility_remove_selected_items, size, Integer.valueOf(size)));
            }
            if (z) {
                return;
            }
            n3.X0("Android.DownloadManager.SelectionEstablished");
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        qh3 qh3Var = this.A0;
        if (qh3Var != null) {
            qh3Var.c();
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        post(new zc0(2, this));
    }
}
